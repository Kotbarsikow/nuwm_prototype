package org.mortbay.servlet.jetty;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mortbay.io.UncheckedPrintWriter;
import org.mortbay.jetty.HttpConnection;
import org.mortbay.jetty.HttpHeaderValues;
import org.mortbay.servlet.GzipFilter;

/* loaded from: classes3.dex */
public class IncludableGzipFilter extends GzipFilter {
    boolean _uncheckedPrintWriter = false;

    @Override // org.mortbay.servlet.GzipFilter, org.mortbay.servlet.UserAgentFilter, javax.servlet.Filter
    public void init(FilterConfig filterConfig) throws ServletException {
        super.init(filterConfig);
        String initParameter = filterConfig.getInitParameter("uncheckedPrintWriter");
        if (initParameter != null) {
            this._uncheckedPrintWriter = Boolean.valueOf(initParameter).booleanValue();
        }
    }

    @Override // org.mortbay.servlet.GzipFilter
    protected GzipFilter.GZIPResponseWrapper newGZIPResponseWrapper(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        return new IncludableResponseWrapper(httpServletRequest, httpServletResponse);
    }

    public class IncludableResponseWrapper extends GzipFilter.GZIPResponseWrapper {
        public IncludableResponseWrapper(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
            super(httpServletRequest, httpServletResponse);
        }

        @Override // org.mortbay.servlet.GzipFilter.GZIPResponseWrapper
        protected GzipFilter.GzipStream newGzipStream(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, long j, int i, int i2) throws IOException {
            return IncludableGzipFilter.this.new IncludableGzipStream(httpServletRequest, httpServletResponse, j, i, i2);
        }
    }

    public class IncludableGzipStream extends GzipFilter.GzipStream {
        public IncludableGzipStream(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, long j, int i, int i2) throws IOException {
            super(httpServletRequest, httpServletResponse, j, i, i2);
        }

        @Override // org.mortbay.servlet.GzipFilter.GzipStream
        protected boolean setContentEncodingGzip() {
            HttpConnection.getCurrentConnection().getResponseFields().put("Content-Encoding", HttpHeaderValues.GZIP);
            return true;
        }
    }

    @Override // org.mortbay.servlet.GzipFilter
    protected PrintWriter newWriter(OutputStream outputStream, String str) throws UnsupportedEncodingException {
        if (this._uncheckedPrintWriter) {
            return str == null ? new UncheckedPrintWriter(outputStream) : new UncheckedPrintWriter(new OutputStreamWriter(outputStream, str));
        }
        return super.newWriter(outputStream, str);
    }
}
