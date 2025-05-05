package org.mortbay.jetty.handler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mortbay.jetty.HandlerContainer;
import org.mortbay.jetty.HttpConnection;
import org.mortbay.jetty.Request;
import org.mortbay.util.URIUtil;

/* loaded from: classes3.dex */
public class MovedContextHandler extends ContextHandler {
    boolean _discardPathInfo;
    boolean _discardQuery;
    String _newContextURL;
    boolean _permanent;
    Redirector _redirector;

    public MovedContextHandler() {
        Redirector redirector = new Redirector();
        this._redirector = redirector;
        addHandler(redirector);
    }

    public MovedContextHandler(HandlerContainer handlerContainer, String str, String str2) {
        super(handlerContainer, str);
        this._newContextURL = str2;
        Redirector redirector = new Redirector();
        this._redirector = redirector;
        addHandler(redirector);
    }

    public boolean isDiscardPathInfo() {
        return this._discardPathInfo;
    }

    public void setDiscardPathInfo(boolean z) {
        this._discardPathInfo = z;
    }

    public String getNewContextURL() {
        return this._newContextURL;
    }

    public void setNewContextURL(String str) {
        this._newContextURL = str;
    }

    public boolean isPermanent() {
        return this._permanent;
    }

    public void setPermanent(boolean z) {
        this._permanent = z;
    }

    public boolean isDiscardQuery() {
        return this._discardQuery;
    }

    public void setDiscardQuery(boolean z) {
        this._discardQuery = z;
    }

    private class Redirector extends AbstractHandler {
        private Redirector() {
        }

        @Override // org.mortbay.jetty.Handler
        public void handle(String str, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, int i) throws IOException, ServletException {
            if (MovedContextHandler.this._newContextURL == null) {
                return;
            }
            Request request = httpServletRequest instanceof Request ? (Request) httpServletRequest : HttpConnection.getCurrentConnection().getRequest();
            String str2 = MovedContextHandler.this._newContextURL;
            if (!MovedContextHandler.this._discardPathInfo && httpServletRequest.getPathInfo() != null) {
                str2 = URIUtil.addPaths(str2, httpServletRequest.getPathInfo());
            }
            if (!MovedContextHandler.this._discardQuery && httpServletRequest.getQueryString() != null) {
                str2 = new StringBuffer().append(str2).append("?").append(httpServletRequest.getQueryString()).toString();
            }
            httpServletResponse.sendRedirect(str2);
            if (MovedContextHandler.this._permanent) {
                httpServletResponse.setStatus(301);
            }
            request.setHandled(true);
        }
    }
}
