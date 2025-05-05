package javax.servlet.http;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import javax.servlet.ServletOutputStream;

/* compiled from: HttpServlet.java */
/* loaded from: classes2.dex */
class NoBodyResponse implements HttpServletResponse {
    private boolean didSetContentLength;
    private NoBodyOutputStream noBody = new NoBodyOutputStream();
    private HttpServletResponse resp;
    private PrintWriter writer;

    NoBodyResponse(HttpServletResponse httpServletResponse) {
        this.resp = httpServletResponse;
    }

    void setContentLength() {
        if (this.didSetContentLength) {
            return;
        }
        this.resp.setContentLength(this.noBody.getContentLength());
    }

    @Override // javax.servlet.ServletResponse
    public void setContentLength(int i) {
        this.resp.setContentLength(i);
        this.didSetContentLength = true;
    }

    @Override // javax.servlet.ServletResponse
    public void setCharacterEncoding(String str) {
        this.resp.setCharacterEncoding(str);
    }

    @Override // javax.servlet.ServletResponse
    public void setContentType(String str) {
        this.resp.setContentType(str);
    }

    @Override // javax.servlet.ServletResponse
    public String getContentType() {
        return this.resp.getContentType();
    }

    @Override // javax.servlet.ServletResponse
    public ServletOutputStream getOutputStream() throws IOException {
        return this.noBody;
    }

    @Override // javax.servlet.ServletResponse
    public String getCharacterEncoding() {
        return this.resp.getCharacterEncoding();
    }

    @Override // javax.servlet.ServletResponse
    public PrintWriter getWriter() throws UnsupportedEncodingException {
        if (this.writer == null) {
            this.writer = new PrintWriter(new OutputStreamWriter(this.noBody, getCharacterEncoding()));
        }
        return this.writer;
    }

    @Override // javax.servlet.ServletResponse
    public void setBufferSize(int i) throws IllegalStateException {
        this.resp.setBufferSize(i);
    }

    @Override // javax.servlet.ServletResponse
    public int getBufferSize() {
        return this.resp.getBufferSize();
    }

    @Override // javax.servlet.ServletResponse
    public void reset() throws IllegalStateException {
        this.resp.reset();
    }

    @Override // javax.servlet.ServletResponse
    public void resetBuffer() throws IllegalStateException {
        this.resp.resetBuffer();
    }

    @Override // javax.servlet.ServletResponse
    public boolean isCommitted() {
        return this.resp.isCommitted();
    }

    @Override // javax.servlet.ServletResponse
    public void flushBuffer() throws IOException {
        this.resp.flushBuffer();
    }

    @Override // javax.servlet.ServletResponse
    public void setLocale(Locale locale) {
        this.resp.setLocale(locale);
    }

    @Override // javax.servlet.ServletResponse
    public Locale getLocale() {
        return this.resp.getLocale();
    }

    @Override // javax.servlet.http.HttpServletResponse
    public void addCookie(Cookie cookie) {
        this.resp.addCookie(cookie);
    }

    @Override // javax.servlet.http.HttpServletResponse
    public boolean containsHeader(String str) {
        return this.resp.containsHeader(str);
    }

    @Override // javax.servlet.http.HttpServletResponse
    public void setStatus(int i, String str) {
        this.resp.setStatus(i, str);
    }

    @Override // javax.servlet.http.HttpServletResponse
    public void setStatus(int i) {
        this.resp.setStatus(i);
    }

    @Override // javax.servlet.http.HttpServletResponse
    public void setHeader(String str, String str2) {
        this.resp.setHeader(str, str2);
    }

    @Override // javax.servlet.http.HttpServletResponse
    public void setIntHeader(String str, int i) {
        this.resp.setIntHeader(str, i);
    }

    @Override // javax.servlet.http.HttpServletResponse
    public void setDateHeader(String str, long j) {
        this.resp.setDateHeader(str, j);
    }

    @Override // javax.servlet.http.HttpServletResponse
    public void sendError(int i, String str) throws IOException {
        this.resp.sendError(i, str);
    }

    @Override // javax.servlet.http.HttpServletResponse
    public void sendError(int i) throws IOException {
        this.resp.sendError(i);
    }

    @Override // javax.servlet.http.HttpServletResponse
    public void sendRedirect(String str) throws IOException {
        this.resp.sendRedirect(str);
    }

    @Override // javax.servlet.http.HttpServletResponse
    public String encodeURL(String str) {
        return this.resp.encodeURL(str);
    }

    @Override // javax.servlet.http.HttpServletResponse
    public String encodeRedirectURL(String str) {
        return this.resp.encodeRedirectURL(str);
    }

    @Override // javax.servlet.http.HttpServletResponse
    public void addHeader(String str, String str2) {
        this.resp.addHeader(str, str2);
    }

    @Override // javax.servlet.http.HttpServletResponse
    public void addDateHeader(String str, long j) {
        this.resp.addDateHeader(str, j);
    }

    @Override // javax.servlet.http.HttpServletResponse
    public void addIntHeader(String str, int i) {
        this.resp.addIntHeader(str, i);
    }

    @Override // javax.servlet.http.HttpServletResponse
    public String encodeUrl(String str) {
        return encodeURL(str);
    }

    @Override // javax.servlet.http.HttpServletResponse
    public String encodeRedirectUrl(String str) {
        return encodeRedirectURL(str);
    }
}
