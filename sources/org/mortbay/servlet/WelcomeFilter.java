package org.mortbay.servlet;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.mortbay.util.URIUtil;

/* loaded from: classes3.dex */
public class WelcomeFilter implements Filter {
    private String welcome;

    @Override // javax.servlet.Filter
    public void destroy() {
    }

    @Override // javax.servlet.Filter
    public void init(FilterConfig filterConfig) {
        String initParameter = filterConfig.getInitParameter("welcome");
        this.welcome = initParameter;
        if (initParameter == null) {
            this.welcome = "index.html";
        }
    }

    @Override // javax.servlet.Filter
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String servletPath = ((HttpServletRequest) servletRequest).getServletPath();
        if (this.welcome != null && servletPath.endsWith(URIUtil.SLASH)) {
            servletRequest.getRequestDispatcher(new StringBuffer().append(servletPath).append(this.welcome).toString()).forward(servletRequest, servletResponse);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
