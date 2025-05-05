package org.mortbay.jetty.deployer;

import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.Map;
import org.mortbay.component.AbstractLifeCycle;
import org.mortbay.jetty.handler.ContextHandler;
import org.mortbay.jetty.handler.ContextHandlerCollection;
import org.mortbay.log.Log;
import org.mortbay.resource.Resource;
import org.mortbay.util.Scanner;
import org.mortbay.xml.XmlConfiguration;

/* loaded from: classes3.dex */
public class ContextDeployer extends AbstractLifeCycle {
    public static final String NAME = "ConfiguredDeployer";
    private ConfigurationManager _configMgr;
    private Resource _configurationDir;
    private ContextHandlerCollection _contexts;
    private ScannerListener _scannerListener;
    private int _scanInterval = 10;
    private Map _currentDeployments = new HashMap();
    private boolean _recursive = false;
    private Scanner _scanner = new Scanner();

    protected class ScannerListener implements Scanner.DiscreteListener {
        protected ScannerListener() {
        }

        @Override // org.mortbay.util.Scanner.DiscreteListener
        public void fileAdded(String str) throws Exception {
            ContextDeployer.this.deploy(str);
        }

        @Override // org.mortbay.util.Scanner.DiscreteListener
        public void fileChanged(String str) throws Exception {
            ContextDeployer.this.redeploy(str);
        }

        @Override // org.mortbay.util.Scanner.DiscreteListener
        public void fileRemoved(String str) throws Exception {
            ContextDeployer.this.undeploy(str);
        }

        public String toString() {
            return "ContextDeployer$Scanner";
        }
    }

    public ContextHandlerCollection getContexts() {
        return this._contexts;
    }

    public void setContexts(ContextHandlerCollection contextHandlerCollection) {
        if (isStarted() || isStarting()) {
            throw new IllegalStateException("Cannot set Contexts after deployer start");
        }
        this._contexts = contextHandlerCollection;
    }

    public void setScanInterval(int i) {
        if (isStarted() || isStarting()) {
            throw new IllegalStateException("Cannot change scan interval after deployer start");
        }
        this._scanInterval = i;
    }

    public int getScanInterval() {
        return this._scanInterval;
    }

    public void setConfigurationDir(String str) throws Exception {
        setConfigurationDir(Resource.newResource(str));
    }

    public void setConfigurationDir(File file) throws Exception {
        setConfigurationDir(Resource.newResource(file.toURL()));
    }

    public void setConfigurationDir(Resource resource) {
        if (isStarted() || isStarting()) {
            throw new IllegalStateException("Cannot change hot deploy dir after deployer start");
        }
        this._configurationDir = resource;
    }

    public void setDirectory(String str) throws Exception {
        setConfigurationDir(str);
    }

    public String getDirectory() {
        return getConfigurationDir().getName();
    }

    public Resource getConfigurationDir() {
        return this._configurationDir;
    }

    public void setConfigurationManager(ConfigurationManager configurationManager) {
        this._configMgr = configurationManager;
    }

    public ConfigurationManager getConfigurationManager() {
        return this._configMgr;
    }

    public void setRecursive(boolean z) {
        this._recursive = z;
    }

    public boolean getRecursive() {
        return this._recursive;
    }

    public boolean isRecursive() {
        return this._recursive;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deploy(String str) throws Exception {
        ContextHandler createContext = createContext(str);
        Log.info(new StringBuffer("Deploy ").append(str).append(" -> ").append(createContext).toString());
        this._contexts.addHandler(createContext);
        this._currentDeployments.put(str, createContext);
        if (this._contexts.isStarted()) {
            createContext.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void undeploy(String str) throws Exception {
        ContextHandler contextHandler = (ContextHandler) this._currentDeployments.get(str);
        Log.info(new StringBuffer("Undeploy ").append(str).append(" -> ").append(contextHandler).toString());
        if (contextHandler == null) {
            return;
        }
        contextHandler.stop();
        this._contexts.removeHandler(contextHandler);
        this._currentDeployments.remove(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void redeploy(String str) throws Exception {
        undeploy(str);
        deploy(str);
    }

    @Override // org.mortbay.component.AbstractLifeCycle
    protected void doStart() throws Exception {
        Resource resource = this._configurationDir;
        if (resource == null) {
            throw new IllegalStateException("No configuraition dir specified");
        }
        if (this._contexts == null) {
            throw new IllegalStateException("No context handler collection specified for deployer");
        }
        this._scanner.setScanDir(resource.getFile());
        this._scanner.setScanInterval(getScanInterval());
        this._scanner.setRecursive(this._recursive);
        this._scanner.setFilenameFilter(new FilenameFilter() { // from class: org.mortbay.jetty.deployer.ContextDeployer.1
            @Override // java.io.FilenameFilter
            public boolean accept(File file, String str) {
                try {
                    return str.endsWith(".xml");
                } catch (Exception e) {
                    Log.warn(e);
                    return false;
                }
            }
        });
        ScannerListener scannerListener = new ScannerListener();
        this._scannerListener = scannerListener;
        this._scanner.addListener(scannerListener);
        this._scanner.scan();
        this._scanner.start();
        this._contexts.getServer().getContainer().addBean(this._scanner);
    }

    @Override // org.mortbay.component.AbstractLifeCycle
    protected void doStop() throws Exception {
        this._scanner.removeListener(this._scannerListener);
        this._scanner.stop();
    }

    private ContextHandler createContext(String str) throws Exception {
        Resource newResource = Resource.newResource(str);
        if (!newResource.exists()) {
            return null;
        }
        XmlConfiguration xmlConfiguration = new XmlConfiguration(newResource.getURL());
        HashMap hashMap = new HashMap();
        hashMap.put("Server", this._contexts.getServer());
        ConfigurationManager configurationManager = this._configMgr;
        if (configurationManager != null) {
            hashMap.putAll(configurationManager.getProperties());
        }
        xmlConfiguration.setProperties(hashMap);
        return (ContextHandler) xmlConfiguration.configure();
    }
}
