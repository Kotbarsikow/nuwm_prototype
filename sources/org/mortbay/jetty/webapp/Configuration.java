package org.mortbay.jetty.webapp;

import java.io.Serializable;

/* loaded from: classes3.dex */
public interface Configuration extends Serializable {
    void configureClassLoader() throws Exception;

    void configureDefaults() throws Exception;

    void configureWebApp() throws Exception;

    void deconfigureWebApp() throws Exception;

    WebAppContext getWebAppContext();

    void setWebAppContext(WebAppContext webAppContext);
}
