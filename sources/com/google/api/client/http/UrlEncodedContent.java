package com.google.api.client.http;

import com.google.api.client.util.Data;
import com.google.api.client.util.FieldInfo;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Types;
import com.google.api.client.util.escape.CharEscapers;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes2.dex */
public class UrlEncodedContent extends AbstractHttpContent {
    private Object data;

    public UrlEncodedContent(Object obj) {
        super(UrlEncodedParser.MEDIA_TYPE);
        setData(obj);
    }

    @Override // com.google.api.client.http.HttpContent, com.google.api.client.util.StreamingContent
    public void writeTo(OutputStream outputStream) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, getCharset()));
        boolean z = true;
        for (Map.Entry<String, Object> entry : Data.mapOf(this.data).entrySet()) {
            Object value = entry.getValue();
            if (value != null) {
                String escapeUri = CharEscapers.escapeUri(entry.getKey());
                Class<?> cls = value.getClass();
                if ((value instanceof Iterable) || cls.isArray()) {
                    Iterator it = Types.iterableOf(value).iterator();
                    while (it.hasNext()) {
                        z = appendParam(z, bufferedWriter, escapeUri, it.next());
                    }
                } else {
                    z = appendParam(z, bufferedWriter, escapeUri, value);
                }
            }
        }
        bufferedWriter.flush();
    }

    @Override // com.google.api.client.http.AbstractHttpContent
    public UrlEncodedContent setMediaType(HttpMediaType httpMediaType) {
        super.setMediaType(httpMediaType);
        return this;
    }

    public final Object getData() {
        return this.data;
    }

    public UrlEncodedContent setData(Object obj) {
        this.data = Preconditions.checkNotNull(obj);
        return this;
    }

    public static UrlEncodedContent getContent(HttpRequest httpRequest) {
        HttpContent content = httpRequest.getContent();
        if (content != null) {
            return (UrlEncodedContent) content;
        }
        UrlEncodedContent urlEncodedContent = new UrlEncodedContent(new HashMap());
        httpRequest.setContent(urlEncodedContent);
        return urlEncodedContent;
    }

    private static boolean appendParam(boolean z, Writer writer, String str, Object obj) throws IOException {
        if (obj != null && !Data.isNull(obj)) {
            if (z) {
                z = false;
            } else {
                writer.write("&");
            }
            writer.write(str);
            String escapeUri = CharEscapers.escapeUri(obj instanceof Enum ? FieldInfo.of((Enum<?>) obj).getName() : obj.toString());
            if (escapeUri.length() != 0) {
                writer.write("=");
                writer.write(escapeUri);
            }
        }
        return z;
    }
}
