package org.mortbay.jetty.handler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mortbay.jetty.Handler;
import org.mortbay.jetty.HandlerContainer;
import org.mortbay.jetty.Server;

/* loaded from: classes3.dex */
public class HandlerWrapper extends AbstractHandlerContainer {
    private Handler _handler;

    public Handler getHandler() {
        return this._handler;
    }

    public void setHandler(Handler handler) {
        try {
            Handler handler2 = this._handler;
            if (getServer() != null) {
                getServer().getContainer().update(this, handler2, handler, "handler");
            }
            if (handler != null) {
                handler.setServer(getServer());
            }
            this._handler = handler;
            if (handler2 == null || !handler2.isStarted()) {
                return;
            }
            handler2.stop();
        } catch (Exception e) {
            IllegalStateException illegalStateException = new IllegalStateException();
            illegalStateException.initCause(e);
            throw illegalStateException;
        }
    }

    public void addHandler(Handler handler) {
        Handler handler2 = getHandler();
        if (handler2 != null && !(handler instanceof HandlerContainer)) {
            throw new IllegalArgumentException("Cannot add");
        }
        setHandler(handler);
        if (handler2 != null) {
            ((HandlerContainer) handler).addHandler(handler2);
        }
    }

    public void removeHandler(Handler handler) {
        Handler handler2 = getHandler();
        if (handler2 != null && (handler2 instanceof HandlerContainer)) {
            ((HandlerContainer) handler2).removeHandler(handler);
        } else {
            if (handler2 != null && handler.equals(handler2)) {
                setHandler(null);
                return;
            }
            throw new IllegalStateException("Cannot remove");
        }
    }

    @Override // org.mortbay.jetty.handler.AbstractHandler, org.mortbay.component.AbstractLifeCycle
    protected void doStart() throws Exception {
        Handler handler = this._handler;
        if (handler != null) {
            handler.start();
        }
        super.doStart();
    }

    @Override // org.mortbay.jetty.handler.AbstractHandler, org.mortbay.component.AbstractLifeCycle
    protected void doStop() throws Exception {
        super.doStop();
        Handler handler = this._handler;
        if (handler != null) {
            handler.stop();
        }
    }

    public void handle(String str, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, int i) throws IOException, ServletException {
        if (this._handler == null || !isStarted()) {
            return;
        }
        this._handler.handle(str, httpServletRequest, httpServletResponse, i);
    }

    @Override // org.mortbay.jetty.handler.AbstractHandler, org.mortbay.jetty.Handler
    public void setServer(Server server) {
        Server server2 = getServer();
        super.setServer(server);
        Handler handler = getHandler();
        if (handler != null) {
            handler.setServer(server);
        }
        if (server == null || server == server2) {
            return;
        }
        server.getContainer().update(this, (Object) null, this._handler, "handler");
    }

    @Override // org.mortbay.jetty.handler.AbstractHandlerContainer
    protected Object expandChildren(Object obj, Class cls) {
        return expandHandler(this._handler, obj, cls);
    }
}
