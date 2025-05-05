package org.mortbay.jetty.handler;

import com.google.android.gms.common.ConnectionResult;
import java.io.IOException;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mortbay.jetty.Handler;
import org.mortbay.jetty.HttpConnection;
import org.mortbay.jetty.MimeTypes;
import org.mortbay.jetty.Request;
import org.mortbay.jetty.Server;
import org.mortbay.log.Log;
import org.mortbay.util.ByteArrayISO8859Writer;
import org.mortbay.util.IO;
import org.mortbay.util.StringUtil;
import org.mortbay.util.URIUtil;

/* loaded from: classes3.dex */
public class DefaultHandler extends AbstractHandler {
    static /* synthetic */ Class class$org$mortbay$jetty$handler$ContextHandler;
    byte[] _favicon;
    long _faviconModified = (System.currentTimeMillis() / 1000) * 1000;
    boolean _serveIcon = true;

    public DefaultHandler() {
        try {
            URL resource = getClass().getClassLoader().getResource("org/mortbay/jetty/favicon.ico");
            if (resource != null) {
                this._favicon = IO.readBytes(resource.openStream());
            }
        } catch (Exception e) {
            Log.warn(e);
        }
    }

    @Override // org.mortbay.jetty.Handler
    public void handle(String str, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, int i) throws IOException, ServletException {
        Handler[] childHandlersByClass;
        Request request = httpServletRequest instanceof Request ? (Request) httpServletRequest : HttpConnection.getCurrentConnection().getRequest();
        if (httpServletResponse.isCommitted() || request.isHandled()) {
            return;
        }
        request.setHandled(true);
        String method = httpServletRequest.getMethod();
        if (this._serveIcon && this._favicon != null && method.equals("GET") && httpServletRequest.getRequestURI().equals("/favicon.ico")) {
            if (httpServletRequest.getDateHeader("If-Modified-Since") == this._faviconModified) {
                httpServletResponse.setStatus(304);
                return;
            }
            httpServletResponse.setStatus(200);
            httpServletResponse.setContentType("image/x-icon");
            httpServletResponse.setContentLength(this._favicon.length);
            httpServletResponse.setDateHeader("Last-Modified", this._faviconModified);
            httpServletResponse.getOutputStream().write(this._favicon);
            return;
        }
        if (!method.equals("GET") || !httpServletRequest.getRequestURI().equals(URIUtil.SLASH)) {
            httpServletResponse.sendError(404);
            return;
        }
        httpServletResponse.setStatus(404);
        httpServletResponse.setContentType(MimeTypes.TEXT_HTML);
        ByteArrayISO8859Writer byteArrayISO8859Writer = new ByteArrayISO8859Writer(ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED);
        StringUtil.replace(StringUtil.replace(httpServletRequest.getRequestURI(), "<", "&lt;"), ">", "&gt;");
        byteArrayISO8859Writer.write("<HTML>\n<HEAD>\n<TITLE>Error 404 - Not Found");
        byteArrayISO8859Writer.write("</TITLE>\n<BODY>\n<H2>Error 404 - Not Found.</H2>\n");
        byteArrayISO8859Writer.write("No context on this server matched or handled this request.<BR>");
        byteArrayISO8859Writer.write("Contexts known to this server are: <ul>");
        Server server = getServer();
        if (server == null) {
            childHandlersByClass = null;
        } else {
            Class cls = class$org$mortbay$jetty$handler$ContextHandler;
            if (cls == null) {
                cls = class$("org.mortbay.jetty.handler.ContextHandler");
                class$org$mortbay$jetty$handler$ContextHandler = cls;
            }
            childHandlersByClass = server.getChildHandlersByClass(cls);
        }
        for (int i2 = 0; childHandlersByClass != null && i2 < childHandlersByClass.length; i2++) {
            ContextHandler contextHandler = (ContextHandler) childHandlersByClass[i2];
            if (contextHandler.isRunning()) {
                byteArrayISO8859Writer.write("<li><a href=\"");
                if (contextHandler.getVirtualHosts() != null && contextHandler.getVirtualHosts().length > 0) {
                    byteArrayISO8859Writer.write(new StringBuffer("http://").append(contextHandler.getVirtualHosts()[0]).append(":").append(httpServletRequest.getLocalPort()).toString());
                }
                byteArrayISO8859Writer.write(contextHandler.getContextPath());
                if (contextHandler.getContextPath().length() > 1 && contextHandler.getContextPath().endsWith(URIUtil.SLASH)) {
                    byteArrayISO8859Writer.write(URIUtil.SLASH);
                }
                byteArrayISO8859Writer.write("\">");
                byteArrayISO8859Writer.write(contextHandler.getContextPath());
                if (contextHandler.getVirtualHosts() != null && contextHandler.getVirtualHosts().length > 0) {
                    byteArrayISO8859Writer.write(new StringBuffer("&nbsp;@&nbsp;").append(contextHandler.getVirtualHosts()[0]).append(":").append(httpServletRequest.getLocalPort()).toString());
                }
                byteArrayISO8859Writer.write("&nbsp;--->&nbsp;");
                byteArrayISO8859Writer.write(contextHandler.toString());
                byteArrayISO8859Writer.write("</a></li>\n");
            } else {
                byteArrayISO8859Writer.write("<li>");
                byteArrayISO8859Writer.write(contextHandler.getContextPath());
                if (contextHandler.getVirtualHosts() != null && contextHandler.getVirtualHosts().length > 0) {
                    byteArrayISO8859Writer.write(new StringBuffer("&nbsp;@&nbsp;").append(contextHandler.getVirtualHosts()[0]).append(":").append(httpServletRequest.getLocalPort()).toString());
                }
                byteArrayISO8859Writer.write("&nbsp;--->&nbsp;");
                byteArrayISO8859Writer.write(contextHandler.toString());
                if (contextHandler.isFailed()) {
                    byteArrayISO8859Writer.write(" [failed]");
                }
                if (contextHandler.isStopped()) {
                    byteArrayISO8859Writer.write(" [stopped]");
                }
                byteArrayISO8859Writer.write("</li>\n");
            }
        }
        for (int i3 = 0; i3 < 10; i3++) {
            byteArrayISO8859Writer.write("\n<!-- Padding for IE                  -->");
        }
        byteArrayISO8859Writer.write("\n</BODY>\n</HTML>\n");
        byteArrayISO8859Writer.flush();
        httpServletResponse.setContentLength(byteArrayISO8859Writer.size());
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        byteArrayISO8859Writer.writeTo(outputStream);
        outputStream.close();
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    public boolean getServeIcon() {
        return this._serveIcon;
    }

    public void setServeIcon(boolean z) {
        this._serveIcon = z;
    }
}
