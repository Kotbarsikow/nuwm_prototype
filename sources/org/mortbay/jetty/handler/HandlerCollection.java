package org.mortbay.jetty.handler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mortbay.jetty.Handler;
import org.mortbay.jetty.Server;
import org.mortbay.util.LazyList;
import org.mortbay.util.MultiException;

/* loaded from: classes3.dex */
public class HandlerCollection extends AbstractHandlerContainer {
    static /* synthetic */ Class class$org$mortbay$jetty$Handler;
    private Handler[] _handlers;

    public Handler[] getHandlers() {
        return this._handlers;
    }

    public void setHandlers(Handler[] handlerArr) {
        Handler[] handlerArr2 = this._handlers;
        Handler[] handlerArr3 = handlerArr2 == null ? null : (Handler[]) handlerArr2.clone();
        if (getServer() != null) {
            getServer().getContainer().update((Object) this, (Object[]) handlerArr3, (Object[]) handlerArr, "handler");
        }
        Server server = getServer();
        MultiException multiException = new MultiException();
        for (int i = 0; handlerArr != null && i < handlerArr.length; i++) {
            if (handlerArr[i].getServer() != server) {
                handlerArr[i].setServer(server);
            }
        }
        this._handlers = handlerArr;
        for (int i2 = 0; handlerArr3 != null && i2 < handlerArr3.length; i2++) {
            Handler handler = handlerArr3[i2];
            if (handler != null) {
                try {
                    if (handler.isStarted()) {
                        handlerArr3[i2].stop();
                    }
                } catch (Throwable th) {
                    multiException.add(th);
                }
            }
        }
        multiException.ifExceptionThrowRuntime();
    }

    public void handle(String str, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, int i) throws IOException, ServletException {
        if (this._handlers == null || !isStarted()) {
            return;
        }
        MultiException multiException = null;
        int i2 = 0;
        while (true) {
            Handler[] handlerArr = this._handlers;
            if (i2 >= handlerArr.length) {
                break;
            }
            try {
                handlerArr[i2].handle(str, httpServletRequest, httpServletResponse, i);
            } catch (IOException e) {
                throw e;
            } catch (RuntimeException e2) {
                throw e2;
            } catch (Exception e3) {
                if (multiException == null) {
                    multiException = new MultiException();
                }
                multiException.add(e3);
            }
            i2++;
        }
        if (multiException != null) {
            if (multiException.size() == 1) {
                throw new ServletException(multiException.getThrowable(0));
            }
            throw new ServletException(multiException);
        }
    }

    @Override // org.mortbay.jetty.handler.AbstractHandler, org.mortbay.component.AbstractLifeCycle
    protected void doStart() throws Exception {
        MultiException multiException = new MultiException();
        if (this._handlers != null) {
            int i = 0;
            while (true) {
                Handler[] handlerArr = this._handlers;
                if (i >= handlerArr.length) {
                    break;
                }
                try {
                    handlerArr[i].start();
                } catch (Throwable th) {
                    multiException.add(th);
                }
                i++;
            }
        }
        super.doStart();
        multiException.ifExceptionThrow();
    }

    @Override // org.mortbay.jetty.handler.AbstractHandler, org.mortbay.component.AbstractLifeCycle
    protected void doStop() throws Exception {
        MultiException multiException = new MultiException();
        try {
            super.doStop();
        } catch (Throwable th) {
            multiException.add(th);
        }
        Handler[] handlerArr = this._handlers;
        if (handlerArr != null) {
            int length = handlerArr.length;
            while (true) {
                int i = length - 1;
                if (length <= 0) {
                    break;
                }
                try {
                    this._handlers[i].stop();
                } catch (Throwable th2) {
                    multiException.add(th2);
                }
                length = i;
            }
        }
        multiException.ifExceptionThrow();
    }

    @Override // org.mortbay.jetty.handler.AbstractHandler, org.mortbay.jetty.Handler
    public void setServer(Server server) {
        Server server2 = getServer();
        super.setServer(server);
        Handler[] handlers = getHandlers();
        for (int i = 0; handlers != null && i < handlers.length; i++) {
            handlers[i].setServer(server);
        }
        if (server == null || server == server2) {
            return;
        }
        server.getContainer().update((Object) this, (Object[]) null, (Object[]) this._handlers, "handler");
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    @Override // org.mortbay.jetty.HandlerContainer
    public void addHandler(Handler handler) {
        Handler[] handlers = getHandlers();
        Class cls = class$org$mortbay$jetty$Handler;
        if (cls == null) {
            cls = class$("org.mortbay.jetty.Handler");
            class$org$mortbay$jetty$Handler = cls;
        }
        setHandlers((Handler[]) LazyList.addToArray(handlers, handler, cls));
    }

    @Override // org.mortbay.jetty.HandlerContainer
    public void removeHandler(Handler handler) {
        Handler[] handlers = getHandlers();
        if (handlers == null || handlers.length <= 0) {
            return;
        }
        setHandlers((Handler[]) LazyList.removeFromArray(handlers, handler));
    }

    @Override // org.mortbay.jetty.handler.AbstractHandlerContainer
    protected Object expandChildren(Object obj, Class cls) {
        Handler[] handlers = getHandlers();
        for (int i = 0; handlers != null && i < handlers.length; i++) {
            obj = expandHandler(handlers[i], obj, cls);
        }
        return obj;
    }
}
