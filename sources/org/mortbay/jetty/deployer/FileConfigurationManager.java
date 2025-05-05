package org.mortbay.jetty.deployer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;
import java.util.Properties;
import org.mortbay.resource.Resource;

/* loaded from: classes3.dex */
public class FileConfigurationManager implements ConfigurationManager {
    private Resource _file;
    private Properties _properties = new Properties();

    public void setFile(String str) throws MalformedURLException, IOException {
        this._file = Resource.newResource(str);
    }

    @Override // org.mortbay.jetty.deployer.ConfigurationManager
    public Map getProperties() {
        try {
            loadProperties();
            return this._properties;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void loadProperties() throws FileNotFoundException, IOException {
        if (this._properties.isEmpty()) {
            this._properties.load(this._file.getInputStream());
        }
    }
}
