package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.format.InputAccessor;
import com.fasterxml.jackson.core.format.MatchStrength;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.io.MergedStream;
import com.fasterxml.jackson.core.io.UTF32Reader;
import com.fasterxml.jackson.core.sym.BytesToNameCanonicalizer;
import com.fasterxml.jackson.core.sym.CharsToNameCanonicalizer;
import java.io.ByteArrayInputStream;
import java.io.CharConversionException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/* loaded from: classes.dex */
public final class ByteSourceJsonBootstrapper {
    static final byte UTF8_BOM_1 = -17;
    static final byte UTF8_BOM_2 = -69;
    static final byte UTF8_BOM_3 = -65;
    protected boolean _bigEndian;
    private final boolean _bufferRecyclable;
    protected int _bytesPerChar;
    protected final IOContext _context;
    protected final InputStream _in;
    protected final byte[] _inputBuffer;
    private int _inputEnd;
    protected int _inputProcessed;
    private int _inputPtr;

    public ByteSourceJsonBootstrapper(IOContext iOContext, InputStream inputStream) {
        this._bigEndian = true;
        this._bytesPerChar = 0;
        this._context = iOContext;
        this._in = inputStream;
        this._inputBuffer = iOContext.allocReadIOBuffer();
        this._inputPtr = 0;
        this._inputEnd = 0;
        this._inputProcessed = 0;
        this._bufferRecyclable = true;
    }

    public ByteSourceJsonBootstrapper(IOContext iOContext, byte[] bArr, int i, int i2) {
        this._bigEndian = true;
        this._bytesPerChar = 0;
        this._context = iOContext;
        this._in = null;
        this._inputBuffer = bArr;
        this._inputPtr = i;
        this._inputEnd = i2 + i;
        this._inputProcessed = -i;
        this._bufferRecyclable = false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x005b, code lost:
    
        if (checkUTF16((r1[r4 + 1] & 255) | ((r1[r4] & 255) << 8)) != false) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x003e, code lost:
    
        if (checkUTF16(r1 >>> 16) != false) goto L17;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.fasterxml.jackson.core.JsonEncoding detectEncoding() throws java.io.IOException, com.fasterxml.jackson.core.JsonParseException {
        /*
            r7 = this;
            r0 = 4
            boolean r1 = r7.ensureLoaded(r0)
            r2 = 1
            r3 = 2
            if (r1 == 0) goto L41
            byte[] r1 = r7._inputBuffer
            int r4 = r7._inputPtr
            r5 = r1[r4]
            int r5 = r5 << 24
            int r6 = r4 + 1
            r6 = r1[r6]
            r6 = r6 & 255(0xff, float:3.57E-43)
            int r6 = r6 << 16
            r5 = r5 | r6
            int r6 = r4 + 2
            r6 = r1[r6]
            r6 = r6 & 255(0xff, float:3.57E-43)
            int r6 = r6 << 8
            r5 = r5 | r6
            int r4 = r4 + 3
            r1 = r1[r4]
            r1 = r1 & 255(0xff, float:3.57E-43)
            r1 = r1 | r5
            boolean r4 = r7.handleBOM(r1)
            if (r4 == 0) goto L31
            goto L5d
        L31:
            boolean r4 = r7.checkUTF32(r1)
            if (r4 == 0) goto L38
            goto L5d
        L38:
            int r1 = r1 >>> 16
            boolean r1 = r7.checkUTF16(r1)
            if (r1 == 0) goto L84
            goto L5d
        L41:
            boolean r1 = r7.ensureLoaded(r3)
            if (r1 == 0) goto L84
            byte[] r1 = r7._inputBuffer
            int r4 = r7._inputPtr
            r5 = r1[r4]
            r5 = r5 & 255(0xff, float:3.57E-43)
            int r5 = r5 << 8
            int r4 = r4 + r2
            r1 = r1[r4]
            r1 = r1 & 255(0xff, float:3.57E-43)
            r1 = r1 | r5
            boolean r1 = r7.checkUTF16(r1)
            if (r1 == 0) goto L84
        L5d:
            int r1 = r7._bytesPerChar
            if (r1 == r2) goto L81
            if (r1 == r3) goto L77
            if (r1 != r0) goto L6f
            boolean r0 = r7._bigEndian
            if (r0 == 0) goto L6c
            com.fasterxml.jackson.core.JsonEncoding r0 = com.fasterxml.jackson.core.JsonEncoding.UTF32_BE
            goto L86
        L6c:
            com.fasterxml.jackson.core.JsonEncoding r0 = com.fasterxml.jackson.core.JsonEncoding.UTF32_LE
            goto L86
        L6f:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.String r1 = "Internal error"
            r0.<init>(r1)
            throw r0
        L77:
            boolean r0 = r7._bigEndian
            if (r0 == 0) goto L7e
            com.fasterxml.jackson.core.JsonEncoding r0 = com.fasterxml.jackson.core.JsonEncoding.UTF16_BE
            goto L86
        L7e:
            com.fasterxml.jackson.core.JsonEncoding r0 = com.fasterxml.jackson.core.JsonEncoding.UTF16_LE
            goto L86
        L81:
            com.fasterxml.jackson.core.JsonEncoding r0 = com.fasterxml.jackson.core.JsonEncoding.UTF8
            goto L86
        L84:
            com.fasterxml.jackson.core.JsonEncoding r0 = com.fasterxml.jackson.core.JsonEncoding.UTF8
        L86:
            com.fasterxml.jackson.core.io.IOContext r1 = r7._context
            r1.setEncoding(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper.detectEncoding():com.fasterxml.jackson.core.JsonEncoding");
    }

    /* renamed from: com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$fasterxml$jackson$core$JsonEncoding;

        static {
            int[] iArr = new int[JsonEncoding.values().length];
            $SwitchMap$com$fasterxml$jackson$core$JsonEncoding = iArr;
            try {
                iArr[JsonEncoding.UTF32_BE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonEncoding[JsonEncoding.UTF32_LE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonEncoding[JsonEncoding.UTF16_BE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonEncoding[JsonEncoding.UTF16_LE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonEncoding[JsonEncoding.UTF8.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public Reader constructReader() throws IOException {
        JsonEncoding encoding = this._context.getEncoding();
        int i = AnonymousClass1.$SwitchMap$com$fasterxml$jackson$core$JsonEncoding[encoding.ordinal()];
        if (i == 1 || i == 2) {
            IOContext iOContext = this._context;
            return new UTF32Reader(iOContext, this._in, this._inputBuffer, this._inputPtr, this._inputEnd, iOContext.getEncoding().isBigEndian());
        }
        if (i == 3 || i == 4 || i == 5) {
            InputStream inputStream = this._in;
            if (inputStream == null) {
                inputStream = new ByteArrayInputStream(this._inputBuffer, this._inputPtr, this._inputEnd);
            } else if (this._inputPtr < this._inputEnd) {
                inputStream = new MergedStream(this._context, inputStream, this._inputBuffer, this._inputPtr, this._inputEnd);
            }
            return new InputStreamReader(inputStream, encoding.getJavaName());
        }
        throw new RuntimeException("Internal error");
    }

    public JsonParser constructParser(int i, ObjectCodec objectCodec, BytesToNameCanonicalizer bytesToNameCanonicalizer, CharsToNameCanonicalizer charsToNameCanonicalizer, boolean z, boolean z2) throws IOException, JsonParseException {
        if (detectEncoding() == JsonEncoding.UTF8 && z) {
            return new UTF8StreamJsonParser(this._context, i, this._in, objectCodec, bytesToNameCanonicalizer.makeChild(z, z2), this._inputBuffer, this._inputPtr, this._inputEnd, this._bufferRecyclable);
        }
        return new ReaderBasedJsonParser(this._context, i, constructReader(), objectCodec, charsToNameCanonicalizer.makeChild(z, z2));
    }

    public static MatchStrength hasJSONFormat(InputAccessor inputAccessor) throws IOException {
        if (!inputAccessor.hasMoreBytes()) {
            return MatchStrength.INCONCLUSIVE;
        }
        byte nextByte = inputAccessor.nextByte();
        if (nextByte == -17) {
            if (!inputAccessor.hasMoreBytes()) {
                return MatchStrength.INCONCLUSIVE;
            }
            if (inputAccessor.nextByte() != -69) {
                return MatchStrength.NO_MATCH;
            }
            if (!inputAccessor.hasMoreBytes()) {
                return MatchStrength.INCONCLUSIVE;
            }
            if (inputAccessor.nextByte() != -65) {
                return MatchStrength.NO_MATCH;
            }
            if (!inputAccessor.hasMoreBytes()) {
                return MatchStrength.INCONCLUSIVE;
            }
            nextByte = inputAccessor.nextByte();
        }
        int skipSpace = skipSpace(inputAccessor, nextByte);
        if (skipSpace < 0) {
            return MatchStrength.INCONCLUSIVE;
        }
        if (skipSpace == 123) {
            int skipSpace2 = skipSpace(inputAccessor);
            if (skipSpace2 < 0) {
                return MatchStrength.INCONCLUSIVE;
            }
            if (skipSpace2 == 34 || skipSpace2 == 125) {
                return MatchStrength.SOLID_MATCH;
            }
            return MatchStrength.NO_MATCH;
        }
        if (skipSpace == 91) {
            int skipSpace3 = skipSpace(inputAccessor);
            if (skipSpace3 < 0) {
                return MatchStrength.INCONCLUSIVE;
            }
            if (skipSpace3 == 93 || skipSpace3 == 91) {
                return MatchStrength.SOLID_MATCH;
            }
            return MatchStrength.SOLID_MATCH;
        }
        MatchStrength matchStrength = MatchStrength.WEAK_MATCH;
        if (skipSpace == 34) {
            return matchStrength;
        }
        if (skipSpace <= 57 && skipSpace >= 48) {
            return matchStrength;
        }
        if (skipSpace == 45) {
            int skipSpace4 = skipSpace(inputAccessor);
            if (skipSpace4 < 0) {
                return MatchStrength.INCONCLUSIVE;
            }
            return (skipSpace4 > 57 || skipSpace4 < 48) ? MatchStrength.NO_MATCH : matchStrength;
        }
        if (skipSpace == 110) {
            return tryMatch(inputAccessor, "ull", matchStrength);
        }
        if (skipSpace == 116) {
            return tryMatch(inputAccessor, "rue", matchStrength);
        }
        if (skipSpace == 102) {
            return tryMatch(inputAccessor, "alse", matchStrength);
        }
        return MatchStrength.NO_MATCH;
    }

    private static MatchStrength tryMatch(InputAccessor inputAccessor, String str, MatchStrength matchStrength) throws IOException {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!inputAccessor.hasMoreBytes()) {
                return MatchStrength.INCONCLUSIVE;
            }
            if (inputAccessor.nextByte() != str.charAt(i)) {
                return MatchStrength.NO_MATCH;
            }
        }
        return matchStrength;
    }

    private static int skipSpace(InputAccessor inputAccessor) throws IOException {
        if (inputAccessor.hasMoreBytes()) {
            return skipSpace(inputAccessor, inputAccessor.nextByte());
        }
        return -1;
    }

    private static int skipSpace(InputAccessor inputAccessor, byte b) throws IOException {
        while (true) {
            int i = b & 255;
            if (i != 32 && i != 13 && i != 10 && i != 9) {
                return i;
            }
            if (!inputAccessor.hasMoreBytes()) {
                return -1;
            }
            b = inputAccessor.nextByte();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0044  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean handleBOM(int r7) throws java.io.IOException {
        /*
            r6 = this;
            r0 = -16842752(0xfffffffffeff0000, float:-1.6947657E38)
            r1 = 65534(0xfffe, float:9.1833E-41)
            r2 = 65279(0xfeff, float:9.1475E-41)
            r3 = 0
            r4 = 1
            if (r7 == r0) goto L30
            r0 = -131072(0xfffffffffffe0000, float:NaN)
            r5 = 4
            if (r7 == r0) goto L26
            if (r7 == r2) goto L1c
            if (r7 == r1) goto L16
            goto L35
        L16:
            java.lang.String r0 = "2143"
            r6.reportWeirdUCS4(r0)
            goto L30
        L1c:
            r6._bigEndian = r4
            int r7 = r6._inputPtr
            int r7 = r7 + r5
            r6._inputPtr = r7
            r6._bytesPerChar = r5
            return r4
        L26:
            int r7 = r6._inputPtr
            int r7 = r7 + r5
            r6._inputPtr = r7
            r6._bytesPerChar = r5
            r6._bigEndian = r3
            return r4
        L30:
            java.lang.String r0 = "3412"
            r6.reportWeirdUCS4(r0)
        L35:
            int r0 = r7 >>> 16
            r5 = 2
            if (r0 != r2) goto L44
            int r7 = r6._inputPtr
            int r7 = r7 + r5
            r6._inputPtr = r7
            r6._bytesPerChar = r5
            r6._bigEndian = r4
            return r4
        L44:
            if (r0 != r1) goto L50
            int r7 = r6._inputPtr
            int r7 = r7 + r5
            r6._inputPtr = r7
            r6._bytesPerChar = r5
            r6._bigEndian = r3
            return r4
        L50:
            int r7 = r7 >>> 8
            r0 = 15711167(0xefbbbf, float:2.2016034E-38)
            if (r7 != r0) goto L62
            int r7 = r6._inputPtr
            int r7 = r7 + 3
            r6._inputPtr = r7
            r6._bytesPerChar = r4
            r6._bigEndian = r4
            return r4
        L62:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper.handleBOM(int):boolean");
    }

    private boolean checkUTF32(int i) throws IOException {
        if ((i >> 8) == 0) {
            this._bigEndian = true;
        } else if ((16777215 & i) == 0) {
            this._bigEndian = false;
        } else if (((-16711681) & i) == 0) {
            reportWeirdUCS4("3412");
        } else {
            if ((i & (-65281)) != 0) {
                return false;
            }
            reportWeirdUCS4("2143");
        }
        this._bytesPerChar = 4;
        return true;
    }

    private boolean checkUTF16(int i) {
        if ((65280 & i) == 0) {
            this._bigEndian = true;
        } else {
            if ((i & 255) != 0) {
                return false;
            }
            this._bigEndian = false;
        }
        this._bytesPerChar = 2;
        return true;
    }

    private void reportWeirdUCS4(String str) throws IOException {
        throw new CharConversionException("Unsupported UCS-4 endianness (" + str + ") detected");
    }

    protected boolean ensureLoaded(int i) throws IOException {
        int read;
        int i2 = this._inputEnd - this._inputPtr;
        while (i2 < i) {
            InputStream inputStream = this._in;
            if (inputStream == null) {
                read = -1;
            } else {
                byte[] bArr = this._inputBuffer;
                int i3 = this._inputEnd;
                read = inputStream.read(bArr, i3, bArr.length - i3);
            }
            if (read < 1) {
                return false;
            }
            this._inputEnd += read;
            i2 += read;
        }
        return true;
    }
}
