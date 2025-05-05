package org.mortbay.util.ajax;

import androidx.core.os.EnvironmentCompat;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mortbay.jetty.HttpHeaderValues;

/* loaded from: classes3.dex */
public class AjaxFilter implements Filter {
    ServletContext context;

    @Override // javax.servlet.Filter
    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();
    }

    public ServletContext getContext() {
        return this.context;
    }

    @Override // javax.servlet.Filter
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String[] parameterValues = servletRequest.getParameterValues("ajax");
        String[] parameterValues2 = servletRequest.getParameterValues("message");
        if (parameterValues != null && parameterValues.length > 0) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            printWriter.println("<ajax-response>");
            AjaxResponse ajaxResponse = new AjaxResponse(httpServletRequest, printWriter);
            for (int i = 0; i < parameterValues.length; i++) {
                handle(parameterValues[i], parameterValues2[i], httpServletRequest, ajaxResponse);
            }
            printWriter.println("</ajax-response>");
            byte[] bytes = stringWriter.toString().getBytes("UTF-8");
            httpServletResponse.setHeader("Pragma", HttpHeaderValues.NO_CACHE);
            httpServletResponse.addHeader("Cache-Control", "must-revalidate,no-cache,no-store");
            httpServletResponse.setDateHeader("Expires", 0L);
            httpServletResponse.setContentType("text/xml; charset=UTF-8");
            httpServletResponse.setContentLength(bytes.length);
            httpServletResponse.getOutputStream().write(bytes);
            httpServletResponse.flushBuffer();
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void handle(String str, String str2, HttpServletRequest httpServletRequest, AjaxResponse ajaxResponse) {
        ajaxResponse.elementResponse(null, new StringBuffer("<span class=\"error\">No implementation for ").append(str).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(httpServletRequest.getParameter("member")).append("</span>").toString());
    }

    @Override // javax.servlet.Filter
    public void destroy() {
        this.context = null;
    }

    public static String encodeText(String str) {
        String str2;
        StringBuffer stringBuffer = null;
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt == '&') {
                str2 = "&amp;";
            } else if (charAt == '<') {
                str2 = "&lt;";
            } else {
                str2 = charAt != '>' ? null : "&gt;";
            }
            if (str2 != null) {
                if (stringBuffer == null) {
                    stringBuffer = new StringBuffer(str.length() * 2);
                    stringBuffer.append(str.subSequence(0, i));
                }
                stringBuffer.append(str2);
            } else if (stringBuffer != null) {
                stringBuffer.append(charAt);
            }
        }
        return stringBuffer != null ? stringBuffer.toString() : str;
    }

    public static class AjaxResponse {
        private PrintWriter out;
        private HttpServletRequest request;

        private AjaxResponse(HttpServletRequest httpServletRequest, PrintWriter printWriter) {
            this.out = printWriter;
            this.request = httpServletRequest;
        }

        public void elementResponse(String str, String str2) {
            if (str == null) {
                str = this.request.getParameter("id");
            }
            if (str == null) {
                str = EnvironmentCompat.MEDIA_UNKNOWN;
            }
            this.out.println(new StringBuffer("<response type=\"element\" id=\"").append(str).append("\">").append(str2).append("</response>").toString());
        }

        public void objectResponse(String str, String str2) {
            if (str == null) {
                str = this.request.getParameter("id");
            }
            if (str == null) {
                str = EnvironmentCompat.MEDIA_UNKNOWN;
            }
            this.out.println(new StringBuffer("<response type=\"object\" id=\"").append(str).append("\">").append(str2).append("</response>").toString());
        }
    }
}
