package org.mortbay.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/* loaded from: classes3.dex */
public class ConcatServlet extends HttpServlet {
    ServletContext _context;
    boolean _development;
    long _lastModified;

    @Override // javax.servlet.GenericServlet
    public void init() throws ServletException {
        this._lastModified = System.currentTimeMillis();
        this._context = getServletContext();
        this._development = "true".equals(getInitParameter("development"));
    }

    @Override // javax.servlet.http.HttpServlet
    protected long getLastModified(HttpServletRequest httpServletRequest) {
        if (this._development) {
            return -1L;
        }
        return this._lastModified;
    }

    @Override // javax.servlet.http.HttpServlet
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String queryString = httpServletRequest.getQueryString();
        if (queryString == null) {
            httpServletResponse.sendError(204);
            return;
        }
        String[] split = queryString.split("\\&");
        String str = null;
        for (String str2 : split) {
            String mimeType = this._context.getMimeType(str2);
            if (mimeType != null) {
                if (str == null) {
                    str = mimeType;
                } else if (!str.equals(mimeType)) {
                    httpServletResponse.sendError(415);
                    return;
                }
            }
        }
        if (str != null) {
            httpServletResponse.setContentType(str);
        }
        for (String str3 : split) {
            RequestDispatcher requestDispatcher = this._context.getRequestDispatcher(str3);
            if (requestDispatcher != null) {
                requestDispatcher.include(httpServletRequest, httpServletResponse);
            }
        }
    }
}
