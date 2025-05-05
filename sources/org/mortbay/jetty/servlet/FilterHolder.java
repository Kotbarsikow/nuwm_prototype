package org.mortbay.jetty.servlet;

import com.google.firebase.messaging.Constants;
import java.util.Enumeration;
import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import org.mortbay.log.Log;

/* loaded from: classes3.dex */
public class FilterHolder extends Holder {
    static /* synthetic */ Class class$javax$servlet$Filter;
    private transient Config _config;
    private transient Filter _filter;

    public static int dispatch(String str) {
        if ("request".equalsIgnoreCase(str)) {
            return 1;
        }
        if ("forward".equalsIgnoreCase(str)) {
            return 2;
        }
        if ("include".equalsIgnoreCase(str)) {
            return 4;
        }
        if (Constants.IPC_BUNDLE_KEY_SEND_ERROR.equalsIgnoreCase(str)) {
            return 8;
        }
        throw new IllegalArgumentException(str);
    }

    public FilterHolder() {
    }

    public FilterHolder(Class cls) {
        super(cls);
    }

    public FilterHolder(Filter filter) {
        setFilter(filter);
    }

    @Override // org.mortbay.jetty.servlet.Holder, org.mortbay.component.AbstractLifeCycle
    public void doStart() throws Exception {
        super.doStart();
        Class cls = class$javax$servlet$Filter;
        if (cls == null) {
            cls = class$("javax.servlet.Filter");
            class$javax$servlet$Filter = cls;
        }
        if (!cls.isAssignableFrom(this._class)) {
            String stringBuffer = new StringBuffer().append(this._class).append(" is not a javax.servlet.Filter").toString();
            super.stop();
            throw new IllegalStateException(stringBuffer);
        }
        if (this._filter == null) {
            this._filter = (Filter) newInstance();
        }
        this._filter = getServletHandler().customizeFilter(this._filter);
        Config config = new Config();
        this._config = config;
        this._filter.init(config);
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    @Override // org.mortbay.jetty.servlet.Holder, org.mortbay.component.AbstractLifeCycle
    public void doStop() {
        Filter filter = this._filter;
        if (filter != null) {
            try {
                destroyInstance(filter);
            } catch (Exception e) {
                Log.warn(e);
            }
        }
        if (!this._extInstance) {
            this._filter = null;
        }
        this._config = null;
        super.doStop();
    }

    @Override // org.mortbay.jetty.servlet.Holder
    public void destroyInstance(Object obj) throws Exception {
        if (obj == null) {
            return;
        }
        Filter filter = (Filter) obj;
        filter.destroy();
        getServletHandler().customizeFilterDestroy(filter);
    }

    public synchronized void setFilter(Filter filter) {
        this._filter = filter;
        this._extInstance = true;
        setHeldClass(filter.getClass());
        if (getName() == null) {
            setName(filter.getClass().getName());
        }
    }

    public Filter getFilter() {
        return this._filter;
    }

    @Override // org.mortbay.jetty.servlet.Holder
    public String toString() {
        return getName();
    }

    class Config implements FilterConfig {
        Config() {
        }

        @Override // javax.servlet.FilterConfig
        public String getFilterName() {
            return FilterHolder.this._name;
        }

        @Override // javax.servlet.FilterConfig
        public ServletContext getServletContext() {
            return FilterHolder.this._servletHandler.getServletContext();
        }

        @Override // javax.servlet.FilterConfig
        public String getInitParameter(String str) {
            return FilterHolder.this.getInitParameter(str);
        }

        @Override // javax.servlet.FilterConfig
        public Enumeration getInitParameterNames() {
            return FilterHolder.this.getInitParameterNames();
        }
    }
}
