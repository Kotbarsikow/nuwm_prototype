package org.mortbay.jetty.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mortbay.jetty.HttpConnection;
import org.mortbay.jetty.HttpGenerator;
import org.mortbay.jetty.MimeTypes;
import org.mortbay.jetty.servlet.ServletHandler;
import org.mortbay.util.ByteArrayISO8859Writer;

/* loaded from: classes3.dex */
public class ErrorHandler extends AbstractHandler {
    boolean _showStacks = true;
    String _cacheControl = "must-revalidate,no-cache,no-store";

    @Override // org.mortbay.jetty.Handler
    public void handle(String str, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, int i) throws IOException {
        HttpConnection currentConnection = HttpConnection.getCurrentConnection();
        currentConnection.getRequest().setHandled(true);
        String method = httpServletRequest.getMethod();
        if (method.equals("GET") || method.equals("POST") || method.equals("HEAD")) {
            httpServletResponse.setContentType(MimeTypes.TEXT_HTML_8859_1);
            String str2 = this._cacheControl;
            if (str2 != null) {
                httpServletResponse.setHeader("Cache-Control", str2);
            }
            ByteArrayISO8859Writer byteArrayISO8859Writer = new ByteArrayISO8859Writer(4096);
            handleErrorPage(httpServletRequest, byteArrayISO8859Writer, currentConnection.getResponse().getStatus(), currentConnection.getResponse().getReason());
            byteArrayISO8859Writer.flush();
            httpServletResponse.setContentLength(byteArrayISO8859Writer.size());
            byteArrayISO8859Writer.writeTo(httpServletResponse.getOutputStream());
            byteArrayISO8859Writer.destroy();
        }
    }

    protected void handleErrorPage(HttpServletRequest httpServletRequest, Writer writer, int i, String str) throws IOException {
        writeErrorPage(httpServletRequest, writer, i, str, this._showStacks);
    }

    protected void writeErrorPage(HttpServletRequest httpServletRequest, Writer writer, int i, String str, boolean z) throws IOException {
        if (str == null) {
            str = HttpGenerator.getReason(i);
        }
        String str2 = str;
        writer.write("<html>\n<head>\n");
        writeErrorPageHead(httpServletRequest, writer, i, str2);
        writer.write("</head>\n<body>");
        writeErrorPageBody(httpServletRequest, writer, i, str2, z);
        writer.write("\n</body>\n</html>\n");
    }

    protected void writeErrorPageHead(HttpServletRequest httpServletRequest, Writer writer, int i, String str) throws IOException {
        writer.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\"/>\n");
        writer.write("<title>Error ");
        writer.write(Integer.toString(i));
        writer.write(32);
        write(writer, str);
        writer.write("</title>\n");
    }

    protected void writeErrorPageBody(HttpServletRequest httpServletRequest, Writer writer, int i, String str, boolean z) throws IOException {
        writeErrorPageMessage(httpServletRequest, writer, i, str, httpServletRequest.getRequestURI());
        if (z) {
            writeErrorPageStacks(httpServletRequest, writer);
        }
        writer.write("<hr /><i><small>Powered by Jetty://</small></i>");
        for (int i2 = 0; i2 < 20; i2++) {
            writer.write("<br/>                                                \n");
        }
    }

    protected void writeErrorPageMessage(HttpServletRequest httpServletRequest, Writer writer, int i, String str, String str2) throws IOException {
        writer.write("<h2>HTTP ERROR ");
        writer.write(Integer.toString(i));
        writer.write("</h2>\n<p>Problem accessing ");
        write(writer, str2);
        writer.write(". Reason:\n<pre>    ");
        write(writer, str);
        writer.write("</pre></p>");
    }

    protected void writeErrorPageStacks(HttpServletRequest httpServletRequest, Writer writer) throws IOException {
        for (Throwable th = (Throwable) httpServletRequest.getAttribute(ServletHandler.__J_S_ERROR_EXCEPTION); th != null; th = th.getCause()) {
            writer.write("<h3>Caused by:</h3><pre>");
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            th.printStackTrace(printWriter);
            printWriter.flush();
            write(writer, stringWriter.getBuffer().toString());
            writer.write("</pre>\n");
        }
    }

    public String getCacheControl() {
        return this._cacheControl;
    }

    public void setCacheControl(String str) {
        this._cacheControl = str;
    }

    public boolean isShowStacks() {
        return this._showStacks;
    }

    public void setShowStacks(boolean z) {
        this._showStacks = z;
    }

    protected void write(Writer writer, String str) throws IOException {
        if (str == null) {
            return;
        }
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt == '&') {
                writer.write("&amp;");
            } else if (charAt == '<') {
                writer.write("&lt;");
            } else if (charAt == '>') {
                writer.write("&gt;");
            } else if (Character.isISOControl(charAt) && !Character.isWhitespace(charAt)) {
                writer.write(63);
            } else {
                writer.write(charAt);
            }
        }
    }
}
