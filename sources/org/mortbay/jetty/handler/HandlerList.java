package org.mortbay.jetty.handler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mortbay.jetty.Handler;
import org.mortbay.jetty.HttpConnection;
import org.mortbay.jetty.Request;

/* loaded from: classes3.dex */
public class HandlerList extends HandlerCollection {
    @Override // org.mortbay.jetty.handler.HandlerCollection, org.mortbay.jetty.Handler
    public void handle(String str, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, int i) throws IOException, ServletException {
        Handler[] handlers = getHandlers();
        if (handlers == null || !isStarted()) {
            return;
        }
        Request request = HttpConnection.getCurrentConnection().getRequest();
        for (Handler handler : handlers) {
            handler.handle(str, httpServletRequest, httpServletResponse, i);
            if (request.isHandled()) {
                return;
            }
        }
    }
}
