package org.mortbay.resource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

/* loaded from: classes3.dex */
class BadResource extends URLResource {
    private String _message;

    @Override // org.mortbay.resource.URLResource, org.mortbay.resource.Resource
    public boolean exists() {
        return false;
    }

    @Override // org.mortbay.resource.URLResource, org.mortbay.resource.Resource
    public File getFile() {
        return null;
    }

    @Override // org.mortbay.resource.URLResource, org.mortbay.resource.Resource
    public boolean isDirectory() {
        return false;
    }

    @Override // org.mortbay.resource.URLResource, org.mortbay.resource.Resource
    public long lastModified() {
        return -1L;
    }

    @Override // org.mortbay.resource.URLResource, org.mortbay.resource.Resource
    public long length() {
        return -1L;
    }

    @Override // org.mortbay.resource.URLResource, org.mortbay.resource.Resource
    public String[] list() {
        return null;
    }

    BadResource(URL url, String str) {
        super(url, null);
        this._message = str;
    }

    @Override // org.mortbay.resource.URLResource, org.mortbay.resource.Resource
    public InputStream getInputStream() throws IOException {
        throw new FileNotFoundException(this._message);
    }

    @Override // org.mortbay.resource.URLResource, org.mortbay.resource.Resource
    public OutputStream getOutputStream() throws IOException, SecurityException {
        throw new FileNotFoundException(this._message);
    }

    @Override // org.mortbay.resource.URLResource, org.mortbay.resource.Resource
    public boolean delete() throws SecurityException {
        throw new SecurityException(this._message);
    }

    @Override // org.mortbay.resource.URLResource, org.mortbay.resource.Resource
    public boolean renameTo(Resource resource) throws SecurityException {
        throw new SecurityException(this._message);
    }

    @Override // org.mortbay.resource.URLResource
    public String toString() {
        return new StringBuffer().append(super.toString()).append("; BadResource=").append(this._message).toString();
    }
}
