package okhttp3;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import javax.annotation.Nullable;
import okhttp3.internal.Util;
import okio.BufferedSink;
import okio.ByteString;
import okio.Okio;
import okio.Source;

/* loaded from: classes3.dex */
public abstract class RequestBody {
    public long contentLength() throws IOException {
        return -1L;
    }

    @Nullable
    public abstract MediaType contentType();

    public abstract void writeTo(BufferedSink bufferedSink) throws IOException;

    public static RequestBody create(@Nullable MediaType mediaType, String str) {
        Charset charset = Util.UTF_8;
        if (mediaType != null && (charset = mediaType.charset()) == null) {
            charset = Util.UTF_8;
            mediaType = MediaType.parse(mediaType + "; charset=utf-8");
        }
        return create(mediaType, str.getBytes(charset));
    }

    /* renamed from: okhttp3.RequestBody$1 */
    class AnonymousClass1 extends RequestBody {
        final /* synthetic */ ByteString val$content;

        AnonymousClass1(ByteString byteString) {
            r2 = byteString;
        }

        @Override // okhttp3.RequestBody
        @Nullable
        public MediaType contentType() {
            return MediaType.this;
        }

        @Override // okhttp3.RequestBody
        public long contentLength() throws IOException {
            return r2.size();
        }

        @Override // okhttp3.RequestBody
        public void writeTo(BufferedSink bufferedSink) throws IOException {
            bufferedSink.write(r2);
        }
    }

    public static RequestBody create(@Nullable MediaType mediaType, ByteString byteString) {
        return new RequestBody() { // from class: okhttp3.RequestBody.1
            final /* synthetic */ ByteString val$content;

            AnonymousClass1(ByteString byteString2) {
                r2 = byteString2;
            }

            @Override // okhttp3.RequestBody
            @Nullable
            public MediaType contentType() {
                return MediaType.this;
            }

            @Override // okhttp3.RequestBody
            public long contentLength() throws IOException {
                return r2.size();
            }

            @Override // okhttp3.RequestBody
            public void writeTo(BufferedSink bufferedSink) throws IOException {
                bufferedSink.write(r2);
            }
        };
    }

    public static RequestBody create(@Nullable MediaType mediaType, byte[] bArr) {
        return create(mediaType, bArr, 0, bArr.length);
    }

    /* renamed from: okhttp3.RequestBody$2 */
    class AnonymousClass2 extends RequestBody {
        final /* synthetic */ int val$byteCount;
        final /* synthetic */ byte[] val$content;
        final /* synthetic */ int val$offset;

        AnonymousClass2(int i, byte[] bArr, int i2) {
            r2 = i;
            r3 = bArr;
            r4 = i2;
        }

        @Override // okhttp3.RequestBody
        @Nullable
        public MediaType contentType() {
            return MediaType.this;
        }

        @Override // okhttp3.RequestBody
        public long contentLength() {
            return r2;
        }

        @Override // okhttp3.RequestBody
        public void writeTo(BufferedSink bufferedSink) throws IOException {
            bufferedSink.write(r3, r4, r2);
        }
    }

    public static RequestBody create(@Nullable MediaType mediaType, byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw new NullPointerException("content == null");
        }
        Util.checkOffsetAndCount(bArr.length, i, i2);
        return new RequestBody() { // from class: okhttp3.RequestBody.2
            final /* synthetic */ int val$byteCount;
            final /* synthetic */ byte[] val$content;
            final /* synthetic */ int val$offset;

            AnonymousClass2(int i22, byte[] bArr2, int i3) {
                r2 = i22;
                r3 = bArr2;
                r4 = i3;
            }

            @Override // okhttp3.RequestBody
            @Nullable
            public MediaType contentType() {
                return MediaType.this;
            }

            @Override // okhttp3.RequestBody
            public long contentLength() {
                return r2;
            }

            @Override // okhttp3.RequestBody
            public void writeTo(BufferedSink bufferedSink) throws IOException {
                bufferedSink.write(r3, r4, r2);
            }
        };
    }

    /* renamed from: okhttp3.RequestBody$3 */
    class AnonymousClass3 extends RequestBody {
        final /* synthetic */ File val$file;

        AnonymousClass3(File file) {
            r2 = file;
        }

        @Override // okhttp3.RequestBody
        @Nullable
        public MediaType contentType() {
            return MediaType.this;
        }

        @Override // okhttp3.RequestBody
        public long contentLength() {
            return r2.length();
        }

        @Override // okhttp3.RequestBody
        public void writeTo(BufferedSink bufferedSink) throws IOException {
            Source source = null;
            try {
                source = Okio.source(r2);
                bufferedSink.writeAll(source);
            } finally {
                Util.closeQuietly(source);
            }
        }
    }

    public static RequestBody create(@Nullable MediaType mediaType, File file) {
        if (file == null) {
            throw new NullPointerException("file == null");
        }
        return new RequestBody() { // from class: okhttp3.RequestBody.3
            final /* synthetic */ File val$file;

            AnonymousClass3(File file2) {
                r2 = file2;
            }

            @Override // okhttp3.RequestBody
            @Nullable
            public MediaType contentType() {
                return MediaType.this;
            }

            @Override // okhttp3.RequestBody
            public long contentLength() {
                return r2.length();
            }

            @Override // okhttp3.RequestBody
            public void writeTo(BufferedSink bufferedSink) throws IOException {
                Source source = null;
                try {
                    source = Okio.source(r2);
                    bufferedSink.writeAll(source);
                } finally {
                    Util.closeQuietly(source);
                }
            }
        };
    }
}
