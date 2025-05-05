package org.mortbay.jetty.deployer;

import java.util.ArrayList;
import org.mortbay.component.AbstractLifeCycle;
import org.mortbay.jetty.HandlerContainer;
import org.mortbay.jetty.handler.ContextHandler;

/* loaded from: classes3.dex */
public class WebAppDeployer extends AbstractLifeCycle {
    static /* synthetic */ Class class$org$mortbay$jetty$handler$ContextHandler;
    static /* synthetic */ Class class$org$mortbay$jetty$webapp$WebAppContext;
    private boolean _allowDuplicates;
    private String[] _configurationClasses;
    private HandlerContainer _contexts;
    private String _defaultsDescriptor;
    private ArrayList _deployed;
    private boolean _extract;
    private boolean _parentLoaderPriority;
    private String _webAppDir;

    public String[] getConfigurationClasses() {
        return this._configurationClasses;
    }

    public void setConfigurationClasses(String[] strArr) {
        this._configurationClasses = strArr;
    }

    public HandlerContainer getContexts() {
        return this._contexts;
    }

    public void setContexts(HandlerContainer handlerContainer) {
        this._contexts = handlerContainer;
    }

    public String getDefaultsDescriptor() {
        return this._defaultsDescriptor;
    }

    public void setDefaultsDescriptor(String str) {
        this._defaultsDescriptor = str;
    }

    public boolean isExtract() {
        return this._extract;
    }

    public void setExtract(boolean z) {
        this._extract = z;
    }

    public boolean isParentLoaderPriority() {
        return this._parentLoaderPriority;
    }

    public void setParentLoaderPriority(boolean z) {
        this._parentLoaderPriority = z;
    }

    public String getWebAppDir() {
        return this._webAppDir;
    }

    public void setWebAppDir(String str) {
        this._webAppDir = str;
    }

    public boolean getAllowDuplicates() {
        return this._allowDuplicates;
    }

    public void setAllowDuplicates(boolean z) {
        this._allowDuplicates = z;
    }

    @Override // org.mortbay.component.AbstractLifeCycle
    public void doStart() throws Exception {
        this._deployed = new ArrayList();
        scan();
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x016c  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0199  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x019c A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void scan() throws java.lang.Exception {
        /*
            Method dump skipped, instructions count: 467
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mortbay.jetty.deployer.WebAppDeployer.scan():void");
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    @Override // org.mortbay.component.AbstractLifeCycle
    public void doStop() throws Exception {
        int size = this._deployed.size();
        while (true) {
            int i = size - 1;
            if (size <= 0) {
                return;
            }
            ((ContextHandler) this._deployed.get(i)).stop();
            size = i;
        }
    }
}
