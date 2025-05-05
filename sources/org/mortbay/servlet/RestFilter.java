package org.mortbay.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mortbay.util.IO;
import org.mortbay.util.URIUtil;

/* loaded from: classes3.dex */
public class RestFilter implements Filter {
    private static final String HTTP_METHOD_DELETE = "DELETE";
    private static final String HTTP_METHOD_GET = "GET";
    private static final String HTTP_METHOD_PUT = "PUT";
    private long _maxPutSize;
    private FilterConfig filterConfig;

    @Override // javax.servlet.Filter
    public void destroy() {
    }

    @Override // javax.servlet.Filter
    public void init(FilterConfig filterConfig) throws UnavailableException {
        this.filterConfig = filterConfig;
        String initParameter = filterConfig.getInitParameter("maxPutSize");
        if (initParameter != null) {
            this._maxPutSize = Long.parseLong(initParameter);
        }
    }

    private File locateFile(HttpServletRequest httpServletRequest) {
        return new File(this.filterConfig.getServletContext().getRealPath(URIUtil.addPaths(httpServletRequest.getServletPath(), httpServletRequest.getPathInfo())));
    }

    @Override // javax.servlet.Filter
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (!(servletRequest instanceof HttpServletRequest) || !(servletResponse instanceof HttpServletResponse)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        if (httpServletRequest.getMethod().equals("GET")) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        if (httpServletRequest.getMethod().equals("PUT")) {
            doPut(httpServletRequest, httpServletResponse);
        } else if (httpServletRequest.getMethod().equals("DELETE")) {
            doDelete(httpServletRequest, httpServletResponse);
        } else {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
    }

    protected void doPut(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        File locateFile = locateFile(httpServletRequest);
        if (locateFile.exists() && !locateFile.delete()) {
            httpServletResponse.sendError(403);
            return;
        }
        FileOutputStream fileOutputStream = new FileOutputStream(locateFile);
        try {
            if (this._maxPutSize <= 0) {
                IO.copy(httpServletRequest.getInputStream(), fileOutputStream);
            } else {
                if (httpServletRequest.getContentLength() > this._maxPutSize) {
                    httpServletResponse.sendError(403);
                    return;
                }
                IO.copy(httpServletRequest.getInputStream(), fileOutputStream, this._maxPutSize);
            }
            fileOutputStream.close();
            httpServletResponse.setStatus(204);
        } finally {
            fileOutputStream.close();
        }
    }

    protected void doDelete(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        File locateFile = locateFile(httpServletRequest);
        if (!locateFile.exists()) {
            httpServletResponse.sendError(404);
        } else if (IO.delete(locateFile)) {
            httpServletResponse.setStatus(204);
        } else {
            httpServletResponse.sendError(500);
        }
    }
}
