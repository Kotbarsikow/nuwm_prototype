package org.mortbay.jetty.handler;

import org.mortbay.jetty.Handler;
import org.mortbay.jetty.HandlerContainer;
import org.mortbay.util.LazyList;

/* loaded from: classes3.dex */
public abstract class AbstractHandlerContainer extends AbstractHandler implements HandlerContainer {
    static /* synthetic */ Class class$org$mortbay$jetty$Handler;

    protected Object expandChildren(Object obj, Class cls) {
        return obj;
    }

    @Override // org.mortbay.jetty.HandlerContainer
    public Handler[] getChildHandlers() {
        Object expandChildren = expandChildren(null, null);
        Class cls = class$org$mortbay$jetty$Handler;
        if (cls == null) {
            cls = class$("org.mortbay.jetty.Handler");
            class$org$mortbay$jetty$Handler = cls;
        }
        return (Handler[]) LazyList.toArray(expandChildren, cls);
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    @Override // org.mortbay.jetty.HandlerContainer
    public Handler[] getChildHandlersByClass(Class cls) {
        Object expandChildren = expandChildren(null, cls);
        Class cls2 = class$org$mortbay$jetty$Handler;
        if (cls2 == null) {
            cls2 = class$("org.mortbay.jetty.Handler");
            class$org$mortbay$jetty$Handler = cls2;
        }
        return (Handler[]) LazyList.toArray(expandChildren, cls2);
    }

    @Override // org.mortbay.jetty.HandlerContainer
    public Handler getChildHandlerByClass(Class cls) {
        Object expandChildren = expandChildren(null, cls);
        if (expandChildren == null) {
            return null;
        }
        return (Handler) LazyList.get(expandChildren, 0);
    }

    protected Object expandHandler(Handler handler, Object obj, Class cls) {
        if (handler == null) {
            return obj;
        }
        if (handler != null && (cls == null || cls.isAssignableFrom(handler.getClass()))) {
            obj = LazyList.add(obj, handler);
        }
        if (handler instanceof AbstractHandlerContainer) {
            return ((AbstractHandlerContainer) handler).expandChildren(obj, cls);
        }
        if (!(handler instanceof HandlerContainer)) {
            return obj;
        }
        HandlerContainer handlerContainer = (HandlerContainer) handler;
        return LazyList.addArray(obj, cls == null ? handlerContainer.getChildHandlers() : handlerContainer.getChildHandlersByClass(cls));
    }
}
