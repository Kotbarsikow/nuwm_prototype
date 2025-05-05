package org.mortbay.servlet;

import com.google.common.net.HttpHeaders;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.HashSet;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mortbay.jetty.HttpHeaderValues;
import org.mortbay.util.IO;

/* loaded from: classes3.dex */
public class ProxyServlet implements Servlet {
    protected HashSet _DontProxyHeaders;
    protected ServletConfig _config;
    protected ServletContext _context;

    @Override // javax.servlet.Servlet
    public void destroy() {
    }

    public ProxyServlet() {
        HashSet hashSet = new HashSet();
        this._DontProxyHeaders = hashSet;
        hashSet.add("proxy-connection");
        this._DontProxyHeaders.add("connection");
        this._DontProxyHeaders.add(HttpHeaderValues.KEEP_ALIVE);
        this._DontProxyHeaders.add("transfer-encoding");
        this._DontProxyHeaders.add("te");
        this._DontProxyHeaders.add("trailer");
        this._DontProxyHeaders.add("proxy-authorization");
        this._DontProxyHeaders.add("proxy-authenticate");
        this._DontProxyHeaders.add("upgrade");
    }

    @Override // javax.servlet.Servlet
    public void init(ServletConfig servletConfig) throws ServletException {
        this._config = servletConfig;
        this._context = servletConfig.getServletContext();
    }

    @Override // javax.servlet.Servlet
    public ServletConfig getServletConfig() {
        return this._config;
    }

    @Override // javax.servlet.Servlet
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        HttpURLConnection httpURLConnection;
        InputStream inputStream;
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        if ("CONNECT".equalsIgnoreCase(httpServletRequest.getMethod())) {
            handleConnect(httpServletRequest, httpServletResponse);
            return;
        }
        String requestURI = httpServletRequest.getRequestURI();
        if (httpServletRequest.getQueryString() != null) {
            requestURI = new StringBuffer().append(requestURI).append("?").append(httpServletRequest.getQueryString()).toString();
        }
        URLConnection openConnection = proxyHttpURL(httpServletRequest.getScheme(), httpServletRequest.getServerName(), httpServletRequest.getServerPort(), requestURI).openConnection();
        int i = 0;
        openConnection.setAllowUserInteraction(false);
        if (openConnection instanceof HttpURLConnection) {
            httpURLConnection = (HttpURLConnection) openConnection;
            httpURLConnection.setRequestMethod(httpServletRequest.getMethod());
            httpURLConnection.setInstanceFollowRedirects(false);
        } else {
            httpURLConnection = null;
        }
        String header = httpServletRequest.getHeader("Connection");
        if (header != null) {
            header = header.toLowerCase();
            if (header.equals(HttpHeaderValues.KEEP_ALIVE) || header.equals(HttpHeaderValues.CLOSE)) {
                header = null;
            }
        }
        Enumeration headerNames = httpServletRequest.getHeaderNames();
        boolean z = false;
        boolean z2 = false;
        while (headerNames.hasMoreElements()) {
            String str = (String) headerNames.nextElement();
            String lowerCase = str.toLowerCase();
            if (!this._DontProxyHeaders.contains(lowerCase) && (header == null || header.indexOf(lowerCase) < 0)) {
                if ("content-type".equals(lowerCase)) {
                    z2 = true;
                }
                Enumeration headers = httpServletRequest.getHeaders(str);
                while (headers.hasMoreElements()) {
                    String str2 = (String) headers.nextElement();
                    if (str2 != null) {
                        openConnection.addRequestProperty(str, str2);
                        z |= "X-Forwarded-For".equalsIgnoreCase(str);
                    }
                }
            }
        }
        openConnection.setRequestProperty("Via", "1.1 (jetty)");
        if (!z) {
            openConnection.addRequestProperty("X-Forwarded-For", httpServletRequest.getRemoteAddr());
            openConnection.addRequestProperty(HttpHeaders.X_FORWARDED_PROTO, httpServletRequest.getScheme());
            openConnection.addRequestProperty(HttpHeaders.X_FORWARDED_HOST, httpServletRequest.getServerName());
            openConnection.addRequestProperty("X-Forwarded-Server", httpServletRequest.getLocalName());
        }
        String header2 = httpServletRequest.getHeader("Cache-Control");
        if (header2 != null && (header2.indexOf(HttpHeaderValues.NO_CACHE) >= 0 || header2.indexOf("no-store") >= 0)) {
            openConnection.setUseCaches(false);
        }
        try {
            openConnection.setDoInput(true);
            ServletInputStream inputStream2 = httpServletRequest.getInputStream();
            if (z2) {
                openConnection.setDoOutput(true);
                IO.copy(inputStream2, openConnection.getOutputStream());
            }
            openConnection.connect();
        } catch (Exception e) {
            this._context.log("proxy", e);
        }
        if (httpURLConnection != null) {
            inputStream = httpURLConnection.getErrorStream();
            httpServletResponse.setStatus(httpURLConnection.getResponseCode(), httpURLConnection.getResponseMessage());
        } else {
            inputStream = null;
        }
        if (inputStream == null) {
            try {
                inputStream = openConnection.getInputStream();
            } catch (Exception e2) {
                this._context.log("stream", e2);
                inputStream = httpURLConnection.getErrorStream();
            }
        }
        httpServletResponse.setHeader("Date", null);
        httpServletResponse.setHeader("Server", null);
        String headerFieldKey = openConnection.getHeaderFieldKey(0);
        String headerField = openConnection.getHeaderField(0);
        while (true) {
            if (headerFieldKey == null && headerField == null) {
                break;
            }
            String lowerCase2 = headerFieldKey != null ? headerFieldKey.toLowerCase() : null;
            if (headerFieldKey != null && headerField != null && !this._DontProxyHeaders.contains(lowerCase2)) {
                httpServletResponse.addHeader(headerFieldKey, headerField);
            }
            i++;
            headerFieldKey = openConnection.getHeaderFieldKey(i);
            headerField = openConnection.getHeaderField(i);
        }
        httpServletResponse.addHeader("Via", "1.1 (jetty)");
        if (inputStream != null) {
            IO.copy(inputStream, httpServletResponse.getOutputStream());
        }
    }

    protected URL proxyHttpURL(String str, String str2, int i, String str3) throws MalformedURLException {
        return new URL(str, str2, i, str3);
    }

    public void handleConnect(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        String str;
        String str2;
        String requestURI = httpServletRequest.getRequestURI();
        int indexOf = requestURI.indexOf(58);
        if (indexOf < 0) {
            str = "";
            str2 = "";
        } else {
            str = requestURI.substring(indexOf + 1);
            str2 = requestURI.substring(0, indexOf);
            if (str2.indexOf(47) > 0) {
                str2 = str2.substring(str2.indexOf(47) + 1);
            }
        }
        InetSocketAddress inetSocketAddress = new InetSocketAddress(str2, Integer.parseInt(str));
        ServletInputStream inputStream = httpServletRequest.getInputStream();
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        Socket socket = new Socket(inetSocketAddress.getAddress(), inetSocketAddress.getPort());
        httpServletResponse.setStatus(200);
        httpServletResponse.setHeader("Connection", HttpHeaderValues.CLOSE);
        httpServletResponse.flushBuffer();
        IO.copyThread(socket.getInputStream(), outputStream);
        IO.copy(inputStream, socket.getOutputStream());
    }

    @Override // javax.servlet.Servlet
    public String getServletInfo() {
        return "Proxy Servlet";
    }

    public static class Transparent extends ProxyServlet {
        String _prefix;
        String _proxyTo;

        public Transparent() {
        }

        public Transparent(String str, String str2, int i) {
            this._prefix = str;
            this._proxyTo = new StringBuffer("http://").append(str2).append(":").append(i).toString();
        }

        @Override // org.mortbay.servlet.ProxyServlet, javax.servlet.Servlet
        public void init(ServletConfig servletConfig) throws ServletException {
            if (servletConfig.getInitParameter("ProxyTo") != null) {
                this._proxyTo = servletConfig.getInitParameter("ProxyTo");
            }
            if (servletConfig.getInitParameter("Prefix") != null) {
                this._prefix = servletConfig.getInitParameter("Prefix");
            }
            if (this._proxyTo == null) {
                throw new UnavailableException("No ProxyTo");
            }
            super.init(servletConfig);
            ServletContext servletContext = this._context;
            StringBuffer stringBuffer = new StringBuffer("Transparent ProxyServlet @ ");
            String str = this._prefix;
            if (str == null) {
                str = "-";
            }
            servletContext.log(stringBuffer.append(str).append(" to ").append(this._proxyTo).toString());
        }

        @Override // org.mortbay.servlet.ProxyServlet
        protected URL proxyHttpURL(String str, String str2, int i, String str3) throws MalformedURLException {
            String str4 = this._prefix;
            if (str4 != null && !str3.startsWith(str4)) {
                return null;
            }
            if (this._prefix != null) {
                return new URL(new StringBuffer().append(this._proxyTo).append(str3.substring(this._prefix.length())).toString());
            }
            return new URL(new StringBuffer().append(this._proxyTo).append(str3).toString());
        }
    }
}
