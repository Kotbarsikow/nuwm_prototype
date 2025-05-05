package com.m_myr.nuwm.nuwmschedule.utils;

import java.io.IOException;
import java.io.InputStream;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.internal.Util;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;

/* loaded from: classes2.dex */
public class RequestBodyUtil {
    private static final int SEGMENT_SIZE = 4096;

    public interface TransferListener {
        void transferred(long num);
    }

    public static RequestBody create(final String contentType, final InputStream inputStream, TransferListener addlistener) {
        return new RequestBody(contentType, inputStream) { // from class: com.m_myr.nuwm.nuwmschedule.utils.RequestBodyUtil.1
            private TransferListener listener;
            final /* synthetic */ String val$contentType;
            final /* synthetic */ InputStream val$inputStream;

            {
                this.val$contentType = contentType;
                this.val$inputStream = inputStream;
                this.listener = TransferListener.this;
            }

            @Override // okhttp3.RequestBody
            public MediaType contentType() {
                return MediaType.parse(this.val$contentType);
            }

            @Override // okhttp3.RequestBody
            public long contentLength() {
                try {
                    return this.val$inputStream.available();
                } catch (IOException unused) {
                    return 0L;
                }
            }

            @Override // okhttp3.RequestBody
            public void writeTo(BufferedSink sink) throws IOException {
                Source source = null;
                try {
                    try {
                        source = Okio.source(this.val$inputStream);
                        long j = 0;
                        while (true) {
                            long read = source.read(sink.buffer(), 4096L);
                            if (read == -1) {
                                break;
                            }
                            j += read;
                            sink.flush();
                            TransferListener.this.transferred(j);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } finally {
                    Util.closeQuietly(source);
                }
            }
        };
    }
}
