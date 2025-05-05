package org.mortbay.jetty.webapp;

import org.mortbay.log.Log;
import org.mortbay.resource.Resource;
import org.mortbay.xml.XmlConfiguration;

/* loaded from: classes3.dex */
public class JettyWebXmlConfiguration implements Configuration {
    private WebAppContext _context;

    @Override // org.mortbay.jetty.webapp.Configuration
    public void configureClassLoader() throws Exception {
    }

    @Override // org.mortbay.jetty.webapp.Configuration
    public void configureDefaults() throws Exception {
    }

    @Override // org.mortbay.jetty.webapp.Configuration
    public void deconfigureWebApp() throws Exception {
    }

    @Override // org.mortbay.jetty.webapp.Configuration
    public void setWebAppContext(WebAppContext webAppContext) {
        this._context = webAppContext;
    }

    @Override // org.mortbay.jetty.webapp.Configuration
    public WebAppContext getWebAppContext() {
        return this._context;
    }

    @Override // org.mortbay.jetty.webapp.Configuration
    public void configureWebApp() throws Exception {
        if (this._context.isStarted()) {
            if (Log.isDebugEnabled()) {
                Log.debug("Cannot configure webapp after it is started");
                return;
            }
            return;
        }
        if (Log.isDebugEnabled()) {
            Log.debug("Configuring web-jetty.xml");
        }
        Resource webInf = getWebAppContext().getWebInf();
        if (webInf == null || !webInf.isDirectory()) {
            return;
        }
        Resource addPath = webInf.addPath("jetty6-web.xml");
        if (!addPath.exists()) {
            addPath = webInf.addPath("jetty-web.xml");
        }
        if (!addPath.exists()) {
            addPath = webInf.addPath("web-jetty.xml");
        }
        if (addPath.exists()) {
            String[] serverClasses = this._context.getServerClasses();
            try {
                this._context.setServerClasses(null);
                if (Log.isDebugEnabled()) {
                    Log.debug(new StringBuffer("Configure: ").append(addPath).toString());
                }
                new XmlConfiguration(addPath.getURL()).configure(getWebAppContext());
            } finally {
                if (this._context.getServerClasses() == null) {
                    this._context.setServerClasses(serverClasses);
                }
            }
        }
    }
}
