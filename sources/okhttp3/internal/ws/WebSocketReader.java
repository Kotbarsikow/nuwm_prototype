package okhttp3.internal.ws;

import com.google.common.base.Ascii;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;

/* loaded from: classes3.dex */
final class WebSocketReader {
    boolean closed;
    final FrameCallback frameCallback;
    long frameLength;
    final boolean isClient;
    boolean isControlFrame;
    boolean isFinalFrame;
    private final Buffer.UnsafeCursor maskCursor;
    private final byte[] maskKey;
    int opcode;
    final BufferedSource source;
    private final Buffer controlFrameBuffer = new Buffer();
    private final Buffer messageFrameBuffer = new Buffer();

    public interface FrameCallback {
        void onReadClose(int i, String str);

        void onReadMessage(String str) throws IOException;

        void onReadMessage(ByteString byteString) throws IOException;

        void onReadPing(ByteString byteString);

        void onReadPong(ByteString byteString);
    }

    WebSocketReader(boolean z, BufferedSource bufferedSource, FrameCallback frameCallback) {
        if (bufferedSource == null) {
            throw new NullPointerException("source == null");
        }
        if (frameCallback == null) {
            throw new NullPointerException("frameCallback == null");
        }
        this.isClient = z;
        this.source = bufferedSource;
        this.frameCallback = frameCallback;
        this.maskKey = z ? null : new byte[4];
        this.maskCursor = z ? null : new Buffer.UnsafeCursor();
    }

    void processNextFrame() throws IOException {
        readHeader();
        if (this.isControlFrame) {
            readControlFrame();
        } else {
            readMessageFrame();
        }
    }

    private void readHeader() throws IOException {
        String str;
        if (this.closed) {
            throw new IOException("closed");
        }
        long timeoutNanos = this.source.timeout().timeoutNanos();
        this.source.timeout().clearTimeout();
        try {
            byte readByte = this.source.readByte();
            this.source.timeout().timeout(timeoutNanos, TimeUnit.NANOSECONDS);
            this.opcode = readByte & Ascii.SI;
            boolean z = (readByte & 128) != 0;
            this.isFinalFrame = z;
            boolean z2 = (readByte & 8) != 0;
            this.isControlFrame = z2;
            if (z2 && !z) {
                throw new ProtocolException("Control frames must be final.");
            }
            boolean z3 = (readByte & 64) != 0;
            boolean z4 = (readByte & 32) != 0;
            boolean z5 = (readByte & 16) != 0;
            if (z3 || z4 || z5) {
                throw new ProtocolException("Reserved flags are unsupported.");
            }
            byte readByte2 = this.source.readByte();
            boolean z6 = (readByte2 & 128) != 0;
            if (z6 == this.isClient) {
                if (this.isClient) {
                    str = "Server-sent frames must not be masked.";
                } else {
                    str = "Client-sent frames must be masked.";
                }
                throw new ProtocolException(str);
            }
            long j = readByte2 & Byte.MAX_VALUE;
            this.frameLength = j;
            if (j == 126) {
                this.frameLength = this.source.readShort() & 65535;
            } else if (j == 127) {
                long readLong = this.source.readLong();
                this.frameLength = readLong;
                if (readLong < 0) {
                    throw new ProtocolException("Frame length 0x" + Long.toHexString(this.frameLength) + " > 0x7FFFFFFFFFFFFFFF");
                }
            }
            if (this.isControlFrame && this.frameLength > 125) {
                throw new ProtocolException("Control frame must be less than 125B.");
            }
            if (z6) {
                this.source.readFully(this.maskKey);
            }
        } catch (Throwable th) {
            this.source.timeout().timeout(timeoutNanos, TimeUnit.NANOSECONDS);
            throw th;
        }
    }

    private void readControlFrame() throws IOException {
        short s;
        String str;
        long j = this.frameLength;
        if (j > 0) {
            this.source.readFully(this.controlFrameBuffer, j);
            if (!this.isClient) {
                this.controlFrameBuffer.readAndWriteUnsafe(this.maskCursor);
                this.maskCursor.seek(0L);
                WebSocketProtocol.toggleMask(this.maskCursor, this.maskKey);
                this.maskCursor.close();
            }
        }
        switch (this.opcode) {
            case 8:
                long size = this.controlFrameBuffer.size();
                if (size == 1) {
                    throw new ProtocolException("Malformed close payload length of 1.");
                }
                if (size == 0) {
                    s = 1005;
                    str = "";
                } else {
                    s = this.controlFrameBuffer.readShort();
                    str = this.controlFrameBuffer.readUtf8();
                    String closeCodeExceptionMessage = WebSocketProtocol.closeCodeExceptionMessage(s);
                    if (closeCodeExceptionMessage != null) {
                        throw new ProtocolException(closeCodeExceptionMessage);
                    }
                }
                this.frameCallback.onReadClose(s, str);
                this.closed = true;
                return;
            case 9:
                this.frameCallback.onReadPing(this.controlFrameBuffer.readByteString());
                return;
            case 10:
                this.frameCallback.onReadPong(this.controlFrameBuffer.readByteString());
                return;
            default:
                throw new ProtocolException("Unknown control opcode: " + Integer.toHexString(this.opcode));
        }
    }

    private void readMessageFrame() throws IOException {
        int i = this.opcode;
        if (i != 1 && i != 2) {
            throw new ProtocolException("Unknown opcode: " + Integer.toHexString(i));
        }
        readMessage();
        if (i == 1) {
            this.frameCallback.onReadMessage(this.messageFrameBuffer.readUtf8());
        } else {
            this.frameCallback.onReadMessage(this.messageFrameBuffer.readByteString());
        }
    }

    private void readUntilNonControlFrame() throws IOException {
        while (!this.closed) {
            readHeader();
            if (!this.isControlFrame) {
                return;
            } else {
                readControlFrame();
            }
        }
    }

    private void readMessage() throws IOException {
        while (!this.closed) {
            long j = this.frameLength;
            if (j > 0) {
                this.source.readFully(this.messageFrameBuffer, j);
                if (!this.isClient) {
                    this.messageFrameBuffer.readAndWriteUnsafe(this.maskCursor);
                    this.maskCursor.seek(this.messageFrameBuffer.size() - this.frameLength);
                    WebSocketProtocol.toggleMask(this.maskCursor, this.maskKey);
                    this.maskCursor.close();
                }
            }
            if (this.isFinalFrame) {
                return;
            }
            readUntilNonControlFrame();
            if (this.opcode != 0) {
                throw new ProtocolException("Expected continuation opcode. Got: " + Integer.toHexString(this.opcode));
            }
        }
        throw new IOException("closed");
    }
}
