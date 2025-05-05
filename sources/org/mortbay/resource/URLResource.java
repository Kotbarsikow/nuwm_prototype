package org.mortbay.resource;

import java.io.File;
import java.io.FilePermission;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.Permission;
import org.mortbay.log.Log;
import org.mortbay.util.URIUtil;

/* loaded from: classes3.dex */
public class URLResource extends Resource {
    protected transient URLConnection _connection;
    protected transient InputStream _in;
    protected URL _url;
    protected String _urlString;
    transient boolean _useCaches;

    @Override // org.mortbay.resource.Resource
    public String[] list() {
        return null;
    }

    protected URLResource(URL url, URLConnection uRLConnection) {
        this._in = null;
        this._useCaches = Resource.__defaultUseCaches;
        this._url = url;
        this._urlString = url.toString();
        this._connection = uRLConnection;
    }

    protected URLResource(URL url, URLConnection uRLConnection, boolean z) {
        this(url, uRLConnection);
        this._useCaches = z;
    }

    protected synchronized boolean checkConnection() {
        if (this._connection == null) {
            try {
                URLConnection openConnection = this._url.openConnection();
                this._connection = openConnection;
                openConnection.setUseCaches(this._useCaches);
            } catch (IOException e) {
                Log.ignore(e);
            }
        }
        return this._connection != null;
    }

    @Override // org.mortbay.resource.Resource
    public synchronized void release() {
        InputStream inputStream = this._in;
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                Log.ignore(e);
            }
            this._in = null;
        }
        if (this._connection != null) {
            this._connection = null;
        }
    }

    @Override // org.mortbay.resource.Resource
    public boolean exists() {
        try {
            synchronized (this) {
                if (checkConnection() && this._in == null) {
                    this._in = this._connection.getInputStream();
                }
            }
        } catch (IOException e) {
            Log.ignore(e);
        }
        return this._in != null;
    }

    @Override // org.mortbay.resource.Resource
    public boolean isDirectory() {
        return exists() && this._url.toString().endsWith(URIUtil.SLASH);
    }

    @Override // org.mortbay.resource.Resource
    public long lastModified() {
        if (checkConnection()) {
            return this._connection.getLastModified();
        }
        return -1L;
    }

    @Override // org.mortbay.resource.Resource
    public long length() {
        if (checkConnection()) {
            return this._connection.getContentLength();
        }
        return -1L;
    }

    @Override // org.mortbay.resource.Resource
    public URL getURL() {
        return this._url;
    }

    @Override // org.mortbay.resource.Resource
    public File getFile() throws IOException {
        if (checkConnection()) {
            Permission permission = this._connection.getPermission();
            if (permission instanceof FilePermission) {
                return new File(permission.getName());
            }
        }
        try {
            return new File(this._url.getFile());
        } catch (Exception e) {
            Log.ignore(e);
            return null;
        }
    }

    @Override // org.mortbay.resource.Resource
    public String getName() {
        return this._url.toExternalForm();
    }

    @Override // org.mortbay.resource.Resource
    public synchronized InputStream getInputStream() throws IOException {
        if (!checkConnection()) {
            throw new IOException("Invalid resource");
        }
        try {
            InputStream inputStream = this._in;
            if (inputStream != null) {
                this._in = null;
                return inputStream;
            }
            return this._connection.getInputStream();
        } finally {
            this._connection = null;
        }
    }

    @Override // org.mortbay.resource.Resource
    public OutputStream getOutputStream() throws IOException, SecurityException {
        throw new IOException("Output not supported");
    }

    @Override // org.mortbay.resource.Resource
    public boolean delete() throws SecurityException {
        throw new SecurityException("Delete not supported");
    }

    @Override // org.mortbay.resource.Resource
    public boolean renameTo(Resource resource) throws SecurityException {
        throw new SecurityException("RenameTo not supported");
    }

    @Override // org.mortbay.resource.Resource
    public Resource addPath(String str) throws IOException, MalformedURLException {
        if (str == null) {
            return null;
        }
        return newResource(URIUtil.addPaths(this._url.toExternalForm(), URIUtil.canonicalPath(str)));
    }

    public String toString() {
        return this._urlString;
    }

    public int hashCode() {
        return this._url.hashCode();
    }

    public boolean equals(Object obj) {
        return (obj instanceof URLResource) && this._url.equals(((URLResource) obj)._url);
    }

    public boolean getUseCaches() {
        return this._useCaches;
    }
}
