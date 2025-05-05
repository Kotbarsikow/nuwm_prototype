package org.mortbay.jetty.servlet;

import java.io.Serializable;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.UnavailableException;
import org.mortbay.component.AbstractLifeCycle;
import org.mortbay.log.Log;
import org.mortbay.util.Loader;

/* loaded from: classes3.dex */
public class Holder extends AbstractLifeCycle implements Serializable {
    static /* synthetic */ Class class$org$mortbay$jetty$servlet$Holder;
    protected transient Class _class;
    protected String _className;
    protected String _displayName;
    protected boolean _extInstance;
    protected Map _initParams;
    protected String _name;
    protected ServletHandler _servletHandler;

    public void destroyInstance(Object obj) throws Exception {
    }

    protected Holder() {
    }

    protected Holder(Class cls) {
        this._class = cls;
        if (cls != null) {
            this._className = cls.getName();
            this._name = new StringBuffer().append(cls.getName()).append("-").append(hashCode()).toString();
        }
    }

    @Override // org.mortbay.component.AbstractLifeCycle
    public void doStart() throws Exception {
        String str;
        if (this._class == null && ((str = this._className) == null || str.equals(""))) {
            throw new UnavailableException("No class for Servlet or Filter", -1);
        }
        if (this._class == null) {
            try {
                Class cls = class$org$mortbay$jetty$servlet$Holder;
                if (cls == null) {
                    cls = class$("org.mortbay.jetty.servlet.Holder");
                    class$org$mortbay$jetty$servlet$Holder = cls;
                }
                this._class = Loader.loadClass(cls, this._className);
                if (Log.isDebugEnabled()) {
                    Log.debug("Holding {}", this._class);
                }
            } catch (Exception e) {
                Log.warn(e);
                throw new UnavailableException(e.getMessage(), -1);
            }
        }
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    @Override // org.mortbay.component.AbstractLifeCycle
    public void doStop() {
        if (this._extInstance) {
            return;
        }
        this._class = null;
    }

    public String getClassName() {
        return this._className;
    }

    public Class getHeldClass() {
        return this._class;
    }

    public String getDisplayName() {
        return this._displayName;
    }

    public String getInitParameter(String str) {
        Map map = this._initParams;
        if (map == null) {
            return null;
        }
        return (String) map.get(str);
    }

    public Enumeration getInitParameterNames() {
        Map map = this._initParams;
        if (map == null) {
            return Collections.enumeration(Collections.EMPTY_LIST);
        }
        return Collections.enumeration(map.keySet());
    }

    public Map getInitParameters() {
        return this._initParams;
    }

    public String getName() {
        return this._name;
    }

    public ServletHandler getServletHandler() {
        return this._servletHandler;
    }

    public synchronized Object newInstance() throws InstantiationException, IllegalAccessException {
        Class cls;
        cls = this._class;
        if (cls == null) {
            throw new InstantiationException(new StringBuffer("!").append(this._className).toString());
        }
        return cls.newInstance();
    }

    public void setClassName(String str) {
        this._className = str;
        this._class = null;
    }

    public void setHeldClass(Class cls) {
        this._class = cls;
        this._className = cls != null ? cls.getName() : null;
    }

    public void setDisplayName(String str) {
        this._displayName = str;
    }

    public void setInitParameter(String str, String str2) {
        if (this._initParams == null) {
            this._initParams = new HashMap(3);
        }
        this._initParams.put(str, str2);
    }

    public void setInitParameters(Map map) {
        this._initParams = map;
    }

    public void setName(String str) {
        this._name = str;
    }

    public void setServletHandler(ServletHandler servletHandler) {
        this._servletHandler = servletHandler;
    }

    public String toString() {
        return this._name;
    }
}
