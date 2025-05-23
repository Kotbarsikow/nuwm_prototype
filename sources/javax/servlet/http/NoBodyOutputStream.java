package javax.servlet.http;

import java.io.IOException;
import java.util.ResourceBundle;
import javax.servlet.ServletOutputStream;

/* compiled from: HttpServlet.java */
/* loaded from: classes2.dex */
class NoBodyOutputStream extends ServletOutputStream {
    private static final String LSTRING_FILE = "javax.servlet.http.LocalStrings";
    private static ResourceBundle lStrings = ResourceBundle.getBundle(LSTRING_FILE);
    private int contentLength = 0;

    NoBodyOutputStream() {
    }

    int getContentLength() {
        return this.contentLength;
    }

    @Override // java.io.OutputStream
    public void write(int i) {
        this.contentLength++;
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        if (i2 >= 0) {
            this.contentLength += i2;
        } else {
            lStrings.getString("err.io.negativelength");
            throw new IOException("negative length");
        }
    }
}
