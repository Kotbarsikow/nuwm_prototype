package org.mortbay.jetty.handler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mortbay.jetty.Request;
import org.mortbay.jetty.RequestLog;
import org.mortbay.jetty.Response;
import org.mortbay.jetty.Server;
import org.mortbay.log.Log;

/* loaded from: classes3.dex */
public class RequestLogHandler extends HandlerWrapper {
    private RequestLog _requestLog;

    @Override // org.mortbay.jetty.handler.HandlerWrapper, org.mortbay.jetty.Handler
    public void handle(String str, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, int i) throws IOException, ServletException {
        RequestLog requestLog;
        super.handle(str, httpServletRequest, httpServletResponse, i);
        if (i != 1 || (requestLog = this._requestLog) == null) {
            return;
        }
        requestLog.log((Request) httpServletRequest, (Response) httpServletResponse);
    }

    public void setRequestLog(RequestLog requestLog) {
        RequestLog requestLog2;
        try {
            RequestLog requestLog3 = this._requestLog;
            if (requestLog3 != null) {
                requestLog3.stop();
            }
        } catch (Exception e) {
            Log.warn(e);
        }
        if (getServer() != null) {
            getServer().getContainer().update((Object) this, (Object) this._requestLog, (Object) requestLog, "logimpl", true);
        }
        this._requestLog = requestLog;
        try {
            if (!isStarted() || (requestLog2 = this._requestLog) == null) {
                return;
            }
            requestLog2.start();
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }

    @Override // org.mortbay.jetty.handler.HandlerWrapper, org.mortbay.jetty.handler.AbstractHandler, org.mortbay.jetty.Handler
    public void setServer(Server server) {
        if (this._requestLog != null) {
            if (getServer() != null && getServer() != server) {
                getServer().getContainer().update((Object) this, (Object) this._requestLog, (Object) null, "logimpl", true);
            }
            super.setServer(server);
            if (server == null || server == getServer()) {
                return;
            }
            server.getContainer().update((Object) this, (Object) null, (Object) this._requestLog, "logimpl", true);
            return;
        }
        super.setServer(server);
    }

    public RequestLog getRequestLog() {
        return this._requestLog;
    }

    @Override // org.mortbay.jetty.handler.HandlerWrapper, org.mortbay.jetty.handler.AbstractHandler, org.mortbay.component.AbstractLifeCycle
    protected void doStart() throws Exception {
        super.doStart();
        RequestLog requestLog = this._requestLog;
        if (requestLog != null) {
            requestLog.start();
        }
    }

    @Override // org.mortbay.jetty.handler.HandlerWrapper, org.mortbay.jetty.handler.AbstractHandler, org.mortbay.component.AbstractLifeCycle
    protected void doStop() throws Exception {
        super.doStop();
        RequestLog requestLog = this._requestLog;
        if (requestLog != null) {
            requestLog.stop();
        }
    }
}
