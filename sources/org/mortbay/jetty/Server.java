package org.mortbay.jetty;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import org.mortbay.component.Container;
import org.mortbay.component.LifeCycle;
import org.mortbay.jetty.bio.SocketConnector;
import org.mortbay.jetty.handler.HandlerCollection;
import org.mortbay.jetty.handler.HandlerWrapper;
import org.mortbay.jetty.security.UserRealm;
import org.mortbay.log.Log;
import org.mortbay.thread.QueuedThreadPool;
import org.mortbay.thread.ThreadPool;
import org.mortbay.util.Attributes;
import org.mortbay.util.AttributesMap;
import org.mortbay.util.LazyList;
import org.mortbay.util.MultiException;

/* loaded from: classes3.dex */
public class Server extends HandlerWrapper implements Attributes {
    public static final String SNAPSHOT_VERSION = "6.1-SNAPSHOT";
    public static final String UNKNOWN_VERSION = "6.1.x";
    private static String _version;
    static /* synthetic */ Class class$java$lang$Runtime;
    static /* synthetic */ Class class$java$lang$Thread;
    static /* synthetic */ Class class$org$mortbay$jetty$Connector;
    static /* synthetic */ Class class$org$mortbay$jetty$Server;
    static /* synthetic */ Class class$org$mortbay$jetty$Server$Graceful;
    static /* synthetic */ Class class$org$mortbay$jetty$security$UserRealm;
    private static ShutdownHookThread hookThread = new ShutdownHookThread();
    private Connector[] _connectors;
    private UserRealm[] _realms;
    private SessionIdManager _sessionIdManager;
    private ThreadPool _threadPool;
    private Container _container = new Container();
    private boolean _sendServerVersion = true;
    private boolean _sendDateHeader = false;
    private AttributesMap _attributes = new AttributesMap();
    private List _dependentLifeCycles = new ArrayList();
    private int _graceful = 0;

    public interface Graceful {
        void setShutdown(boolean z);
    }

    static {
        String str;
        Class cls = class$org$mortbay$jetty$Server;
        if (cls == null) {
            cls = class$("org.mortbay.jetty.Server");
            class$org$mortbay$jetty$Server = cls;
        }
        if (cls.getPackage() != null) {
            Class cls2 = class$org$mortbay$jetty$Server;
            if (cls2 == null) {
                cls2 = class$("org.mortbay.jetty.Server");
                class$org$mortbay$jetty$Server = cls2;
            }
            if (cls2.getPackage().getImplementationVersion() != null) {
                Class cls3 = class$org$mortbay$jetty$Server;
                if (cls3 == null) {
                    cls3 = class$("org.mortbay.jetty.Server");
                    class$org$mortbay$jetty$Server = cls3;
                }
                str = cls3.getPackage().getImplementationVersion();
                _version = str;
            }
        }
        str = UNKNOWN_VERSION;
        _version = str;
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    public Server() {
        setServer(this);
    }

    public Server(int i) {
        setServer(this);
        SocketConnector socketConnector = new SocketConnector();
        socketConnector.setPort(i);
        setConnectors(new Connector[]{socketConnector});
    }

    public static String getVersion() {
        return _version;
    }

    public Container getContainer() {
        return this._container;
    }

    public boolean getStopAtShutdown() {
        return hookThread.contains(this);
    }

    public void setStopAtShutdown(boolean z) {
        if (z) {
            hookThread.add(this);
        } else {
            hookThread.remove(this);
        }
    }

    public Connector[] getConnectors() {
        return this._connectors;
    }

    public void addConnector(Connector connector) {
        Connector[] connectors = getConnectors();
        Class cls = class$org$mortbay$jetty$Connector;
        if (cls == null) {
            cls = class$("org.mortbay.jetty.Connector");
            class$org$mortbay$jetty$Connector = cls;
        }
        setConnectors((Connector[]) LazyList.addToArray(connectors, connector, cls));
    }

    public void removeConnector(Connector connector) {
        setConnectors((Connector[]) LazyList.removeFromArray(getConnectors(), connector));
    }

    public void setConnectors(Connector[] connectorArr) {
        if (connectorArr != null) {
            for (Connector connector : connectorArr) {
                connector.setServer(this);
            }
        }
        this._container.update((Object) this, (Object[]) this._connectors, (Object[]) connectorArr, "connector");
        this._connectors = connectorArr;
    }

    public ThreadPool getThreadPool() {
        return this._threadPool;
    }

    public void setThreadPool(ThreadPool threadPool) {
        this._container.update((Object) this, (Object) this._threadPool, (Object) threadPool, "threadpool", true);
        this._threadPool = threadPool;
    }

    @Override // org.mortbay.jetty.handler.HandlerWrapper, org.mortbay.jetty.handler.AbstractHandler, org.mortbay.component.AbstractLifeCycle
    protected void doStart() throws Exception {
        Log.info(new StringBuffer("jetty-").append(_version).toString());
        HttpGenerator.setServerVersion(_version);
        MultiException multiException = new MultiException();
        int i = 0;
        int i2 = 0;
        while (true) {
            UserRealm[] userRealmArr = this._realms;
            if (userRealmArr == null || i2 >= userRealmArr.length) {
                break;
            }
            UserRealm userRealm = userRealmArr[i2];
            if (userRealm instanceof LifeCycle) {
                ((LifeCycle) userRealm).start();
            }
            i2++;
        }
        Iterator it = this._dependentLifeCycles.iterator();
        while (it.hasNext()) {
            try {
                ((LifeCycle) it.next()).start();
            } catch (Throwable th) {
                multiException.add(th);
            }
        }
        if (this._threadPool == null) {
            setThreadPool(new QueuedThreadPool());
        }
        SessionIdManager sessionIdManager = this._sessionIdManager;
        if (sessionIdManager != null) {
            sessionIdManager.start();
        }
        try {
            ThreadPool threadPool = this._threadPool;
            if (threadPool instanceof LifeCycle) {
                ((LifeCycle) threadPool).start();
            }
        } catch (Throwable th2) {
            multiException.add(th2);
        }
        try {
            super.doStart();
        } catch (Throwable th3) {
            Log.warn("Error starting handlers", th3);
        }
        if (this._connectors != null) {
            while (true) {
                Connector[] connectorArr = this._connectors;
                if (i >= connectorArr.length) {
                    break;
                }
                try {
                    connectorArr[i].start();
                } catch (Throwable th4) {
                    multiException.add(th4);
                }
                i++;
            }
        }
        multiException.ifExceptionThrow();
    }

    /* JADX WARN: Can't wrap try/catch for region: R(18:0|1|(2:2|(1:1)(3:6|(2:8|9)(1:11)|10))|13|(8:15|(2:17|(2:18|(5:20|21|22|24|25)(1:29)))(0)|30|(1:32)|33|(2:36|34)|37|38)|39|(12:41|(2:42|(4:44|45|47|48)(0))|53|54|(1:56)|57|58|(1:60)|62|(3:64|(5:67|68|70|71|65)|75)|76|77)(0)|52|53|54|(0)|57|58|(0)|62|(0)|76|77) */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0099, code lost:
    
        r1 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x009a, code lost:
    
        r0.add(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x0082, code lost:
    
        r1 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0083, code lost:
    
        r0.add(r1);
     */
    /* JADX WARN: Removed duplicated region for block: B:56:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0093 A[Catch: all -> 0x0099, TRY_LEAVE, TryCatch #2 {all -> 0x0099, blocks: (B:58:0x008d, B:60:0x0093), top: B:57:0x008d }] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00a5  */
    @Override // org.mortbay.jetty.handler.HandlerWrapper, org.mortbay.jetty.handler.AbstractHandler, org.mortbay.component.AbstractLifeCycle
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void doStop() throws java.lang.Exception {
        /*
            r6 = this;
            org.mortbay.util.MultiException r0 = new org.mortbay.util.MultiException
            r0.<init>()
            r1 = 0
            r2 = 0
        L7:
            org.mortbay.jetty.security.UserRealm[] r3 = r6._realms
            if (r3 == 0) goto L1c
            int r4 = r3.length
            if (r2 >= r4) goto L1c
            r3 = r3[r2]
            boolean r4 = r3 instanceof org.mortbay.component.LifeCycle
            if (r4 == 0) goto L19
            org.mortbay.component.LifeCycle r3 = (org.mortbay.component.LifeCycle) r3
            r3.stop()
        L19:
            int r2 = r2 + 1
            goto L7
        L1c:
            int r2 = r6._graceful
            if (r2 <= 0) goto L67
            org.mortbay.jetty.Connector[] r2 = r6._connectors
            java.lang.String r3 = "Graceful shutdown {}"
            if (r2 == 0) goto L40
            int r2 = r2.length
        L27:
            int r4 = r2 + (-1)
            if (r2 <= 0) goto L40
            org.mortbay.jetty.Connector[] r2 = r6._connectors
            r2 = r2[r4]
            org.mortbay.log.Log.info(r3, r2)
            org.mortbay.jetty.Connector[] r2 = r6._connectors     // Catch: java.lang.Throwable -> L3a
            r2 = r2[r4]     // Catch: java.lang.Throwable -> L3a
            r2.close()     // Catch: java.lang.Throwable -> L3a
            goto L3e
        L3a:
            r2 = move-exception
            r0.add(r2)
        L3e:
            r2 = r4
            goto L27
        L40:
            java.lang.Class r2 = org.mortbay.jetty.Server.class$org$mortbay$jetty$Server$Graceful
            if (r2 != 0) goto L4c
            java.lang.String r2 = "org.mortbay.jetty.Server$Graceful"
            java.lang.Class r2 = class$(r2)
            org.mortbay.jetty.Server.class$org$mortbay$jetty$Server$Graceful = r2
        L4c:
            org.mortbay.jetty.Handler[] r2 = r6.getChildHandlersByClass(r2)
        L50:
            int r4 = r2.length
            if (r1 >= r4) goto L61
            r4 = r2[r1]
            org.mortbay.jetty.Server$Graceful r4 = (org.mortbay.jetty.Server.Graceful) r4
            org.mortbay.log.Log.info(r3, r4)
            r5 = 1
            r4.setShutdown(r5)
            int r1 = r1 + 1
            goto L50
        L61:
            int r1 = r6._graceful
            long r1 = (long) r1
            java.lang.Thread.sleep(r1)
        L67:
            org.mortbay.jetty.Connector[] r1 = r6._connectors
            if (r1 == 0) goto L7e
            int r1 = r1.length
        L6c:
            int r2 = r1 + (-1)
            if (r1 <= 0) goto L7e
            org.mortbay.jetty.Connector[] r1 = r6._connectors     // Catch: java.lang.Throwable -> L78
            r1 = r1[r2]     // Catch: java.lang.Throwable -> L78
            r1.stop()     // Catch: java.lang.Throwable -> L78
            goto L7c
        L78:
            r1 = move-exception
            r0.add(r1)
        L7c:
            r1 = r2
            goto L6c
        L7e:
            super.doStop()     // Catch: java.lang.Throwable -> L82
            goto L86
        L82:
            r1 = move-exception
            r0.add(r1)
        L86:
            org.mortbay.jetty.SessionIdManager r1 = r6._sessionIdManager
            if (r1 == 0) goto L8d
            r1.stop()
        L8d:
            org.mortbay.thread.ThreadPool r1 = r6._threadPool     // Catch: java.lang.Throwable -> L99
            boolean r2 = r1 instanceof org.mortbay.component.LifeCycle     // Catch: java.lang.Throwable -> L99
            if (r2 == 0) goto L9d
            org.mortbay.component.LifeCycle r1 = (org.mortbay.component.LifeCycle) r1     // Catch: java.lang.Throwable -> L99
            r1.stop()     // Catch: java.lang.Throwable -> L99
            goto L9d
        L99:
            r1 = move-exception
            r0.add(r1)
        L9d:
            java.util.List r1 = r6._dependentLifeCycles
            boolean r1 = r1.isEmpty()
            if (r1 != 0) goto Lc4
            java.util.List r1 = r6._dependentLifeCycles
            int r2 = r1.size()
            java.util.ListIterator r1 = r1.listIterator(r2)
        Laf:
            boolean r2 = r1.hasPrevious()
            if (r2 == 0) goto Lc4
            java.lang.Object r2 = r1.previous()     // Catch: java.lang.Throwable -> Lbf
            org.mortbay.component.LifeCycle r2 = (org.mortbay.component.LifeCycle) r2     // Catch: java.lang.Throwable -> Lbf
            r2.stop()     // Catch: java.lang.Throwable -> Lbf
            goto Laf
        Lbf:
            r2 = move-exception
            r0.add(r2)
            goto Laf
        Lc4:
            r0.ifExceptionThrow()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mortbay.jetty.Server.doStop():void");
    }

    public void handle(HttpConnection httpConnection) throws IOException, ServletException {
        String pathInfo = httpConnection.getRequest().getPathInfo();
        if (Log.isDebugEnabled()) {
            Log.debug(new StringBuffer("REQUEST ").append(pathInfo).append(" on ").append(httpConnection).toString());
            handle(pathInfo, httpConnection.getRequest(), httpConnection.getResponse(), 1);
            Log.debug(new StringBuffer("RESPONSE ").append(pathInfo).append("  ").append(httpConnection.getResponse().getStatus()).toString());
            return;
        }
        handle(pathInfo, httpConnection.getRequest(), httpConnection.getResponse(), 1);
    }

    public void join() throws InterruptedException {
        getThreadPool().join();
    }

    public UserRealm[] getUserRealms() {
        return this._realms;
    }

    public void setUserRealms(UserRealm[] userRealmArr) {
        this._container.update((Object) this, (Object[]) this._realms, (Object[]) userRealmArr, "realm", true);
        this._realms = userRealmArr;
    }

    public void addUserRealm(UserRealm userRealm) {
        UserRealm[] userRealms = getUserRealms();
        Class cls = class$org$mortbay$jetty$security$UserRealm;
        if (cls == null) {
            cls = class$("org.mortbay.jetty.security.UserRealm");
            class$org$mortbay$jetty$security$UserRealm = cls;
        }
        setUserRealms((UserRealm[]) LazyList.addToArray(userRealms, userRealm, cls));
    }

    public void removeUserRealm(UserRealm userRealm) {
        setUserRealms((UserRealm[]) LazyList.removeFromArray(getUserRealms(), userRealm));
    }

    public SessionIdManager getSessionIdManager() {
        return this._sessionIdManager;
    }

    public void setSessionIdManager(SessionIdManager sessionIdManager) {
        this._container.update((Object) this, (Object) this._sessionIdManager, (Object) sessionIdManager, "sessionIdManager", true);
        this._sessionIdManager = sessionIdManager;
    }

    public void setSendServerVersion(boolean z) {
        this._sendServerVersion = z;
    }

    public boolean getSendServerVersion() {
        return this._sendServerVersion;
    }

    public void setSendDateHeader(boolean z) {
        this._sendDateHeader = z;
    }

    public boolean getSendDateHeader() {
        return this._sendDateHeader;
    }

    public void addLifeCycle(LifeCycle lifeCycle) {
        if (lifeCycle == null) {
            return;
        }
        if (!this._dependentLifeCycles.contains(lifeCycle)) {
            this._dependentLifeCycles.add(lifeCycle);
            this._container.addBean(lifeCycle);
        }
        try {
            if (isStarted()) {
                lifeCycle.start();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void removeLifeCycle(LifeCycle lifeCycle) {
        if (lifeCycle == null) {
            return;
        }
        this._dependentLifeCycles.remove(lifeCycle);
        this._container.removeBean(lifeCycle);
    }

    private static class ShutdownHookThread extends Thread {
        private boolean hooked;
        private ArrayList servers;

        private ShutdownHookThread() {
            this.hooked = false;
            this.servers = new ArrayList();
        }

        /* synthetic */ ShutdownHookThread(AnonymousClass1 anonymousClass1) {
            this();
        }

        private void createShutdownHook() {
            Class cls;
            Class<?> cls2;
            if (Boolean.getBoolean("JETTY_NO_SHUTDOWN_HOOK") || this.hooked) {
                return;
            }
            try {
                if (Server.class$java$lang$Runtime == null) {
                    cls = Server.class$("java.lang.Runtime");
                    Server.class$java$lang$Runtime = cls;
                } else {
                    cls = Server.class$java$lang$Runtime;
                }
                if (Server.class$java$lang$Thread == null) {
                    cls2 = Server.class$("java.lang.Thread");
                    Server.class$java$lang$Thread = cls2;
                } else {
                    cls2 = Server.class$java$lang$Thread;
                }
                cls.getMethod("addShutdownHook", cls2).invoke(Runtime.getRuntime(), this);
                this.hooked = true;
            } catch (Exception e) {
                if (Log.isDebugEnabled()) {
                    Log.debug("No shutdown hook in JVM ", e);
                }
            }
        }

        public boolean add(Server server) {
            createShutdownHook();
            return this.servers.add(server);
        }

        public boolean contains(Server server) {
            return this.servers.contains(server);
        }

        public boolean addAll(Collection collection) {
            createShutdownHook();
            return this.servers.addAll(collection);
        }

        public void clear() {
            createShutdownHook();
            this.servers.clear();
        }

        public boolean remove(Server server) {
            createShutdownHook();
            return this.servers.remove(server);
        }

        public boolean removeAll(Collection collection) {
            createShutdownHook();
            return this.servers.removeAll(collection);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            setName("Shutdown");
            Log.info("Shutdown hook executing");
            Iterator it = this.servers.iterator();
            while (it.hasNext()) {
                Server server = (Server) it.next();
                if (server != null) {
                    try {
                        server.stop();
                    } catch (Exception e) {
                        Log.warn(e);
                    }
                    Log.info("Shutdown hook complete");
                    try {
                        Thread.sleep(1000L);
                    } catch (Exception e2) {
                        Log.warn(e2);
                    }
                }
            }
        }
    }

    @Override // org.mortbay.jetty.handler.HandlerWrapper, org.mortbay.jetty.HandlerContainer
    public void addHandler(Handler handler) {
        if (getHandler() == null) {
            setHandler(handler);
        } else {
            if (getHandler() instanceof HandlerCollection) {
                ((HandlerCollection) getHandler()).addHandler(handler);
                return;
            }
            HandlerCollection handlerCollection = new HandlerCollection();
            handlerCollection.setHandlers(new Handler[]{getHandler(), handler});
            setHandler(handlerCollection);
        }
    }

    @Override // org.mortbay.jetty.handler.HandlerWrapper, org.mortbay.jetty.HandlerContainer
    public void removeHandler(Handler handler) {
        if (getHandler() instanceof HandlerCollection) {
            ((HandlerCollection) getHandler()).removeHandler(handler);
        }
    }

    public Handler[] getHandlers() {
        if (getHandler() instanceof HandlerCollection) {
            return ((HandlerCollection) getHandler()).getHandlers();
        }
        return null;
    }

    public void setHandlers(Handler[] handlerArr) {
        HandlerCollection handlerCollection;
        if (getHandler() instanceof HandlerCollection) {
            handlerCollection = (HandlerCollection) getHandler();
        } else {
            handlerCollection = new HandlerCollection();
            setHandler(handlerCollection);
        }
        handlerCollection.setHandlers(handlerArr);
    }

    @Override // org.mortbay.util.Attributes
    public void clearAttributes() {
        this._attributes.clearAttributes();
    }

    @Override // org.mortbay.util.Attributes
    public Object getAttribute(String str) {
        return this._attributes.getAttribute(str);
    }

    @Override // org.mortbay.util.Attributes
    public Enumeration getAttributeNames() {
        return AttributesMap.getAttributeNamesCopy(this._attributes);
    }

    @Override // org.mortbay.util.Attributes
    public void removeAttribute(String str) {
        this._attributes.removeAttribute(str);
    }

    @Override // org.mortbay.util.Attributes
    public void setAttribute(String str, Object obj) {
        this._attributes.setAttribute(str, obj);
    }

    public int getGracefulShutdown() {
        return this._graceful;
    }

    public void setGracefulShutdown(int i) {
        this._graceful = i;
    }
}
