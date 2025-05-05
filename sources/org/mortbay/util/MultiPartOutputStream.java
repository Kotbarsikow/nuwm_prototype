package org.mortbay.util;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes3.dex */
public class MultiPartOutputStream extends FilterOutputStream {
    public static String MULTIPART_MIXED = "multipart/mixed";
    public static String MULTIPART_X_MIXED_REPLACE = "multipart/x-mixed-replace";
    private static byte[] __CRLF;
    private static byte[] __DASHDASH;
    private String boundary;
    private byte[] boundaryBytes;
    private boolean inPart;

    static {
        try {
            __CRLF = "\r\n".getBytes(StringUtil.__ISO_8859_1);
            __DASHDASH = "--".getBytes(StringUtil.__ISO_8859_1);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public MultiPartOutputStream(OutputStream outputStream) throws IOException {
        super(outputStream);
        this.inPart = false;
        String stringBuffer = new StringBuffer("jetty").append(System.identityHashCode(this)).append(Long.toString(System.currentTimeMillis(), 36)).toString();
        this.boundary = stringBuffer;
        this.boundaryBytes = stringBuffer.getBytes(StringUtil.__ISO_8859_1);
        this.inPart = false;
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.inPart) {
            this.out.write(__CRLF);
        }
        this.out.write(__DASHDASH);
        this.out.write(this.boundaryBytes);
        this.out.write(__DASHDASH);
        this.out.write(__CRLF);
        this.inPart = false;
        super.close();
    }

    public String getBoundary() {
        return this.boundary;
    }

    public OutputStream getOut() {
        return this.out;
    }

    public void startPart(String str) throws IOException {
        if (this.inPart) {
            this.out.write(__CRLF);
        }
        this.inPart = true;
        this.out.write(__DASHDASH);
        this.out.write(this.boundaryBytes);
        this.out.write(__CRLF);
        this.out.write(new StringBuffer("Content-Type: ").append(str).toString().getBytes(StringUtil.__ISO_8859_1));
        this.out.write(__CRLF);
        this.out.write(__CRLF);
    }

    public void startPart(String str, String[] strArr) throws IOException {
        if (this.inPart) {
            this.out.write(__CRLF);
        }
        this.inPart = true;
        this.out.write(__DASHDASH);
        this.out.write(this.boundaryBytes);
        this.out.write(__CRLF);
        this.out.write(new StringBuffer("Content-Type: ").append(str).toString().getBytes(StringUtil.__ISO_8859_1));
        this.out.write(__CRLF);
        for (int i = 0; strArr != null && i < strArr.length; i++) {
            this.out.write(strArr[i].getBytes(StringUtil.__ISO_8859_1));
            this.out.write(__CRLF);
        }
        this.out.write(__CRLF);
    }
}
