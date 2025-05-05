package org.mortbay.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/* loaded from: classes3.dex */
public class NoJspServlet extends HttpServlet {
    @Override // javax.servlet.http.HttpServlet
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletResponse.sendError(500, "JSP support not configured");
    }
}
