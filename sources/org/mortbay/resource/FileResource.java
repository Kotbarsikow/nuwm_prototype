package org.mortbay.resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.security.Permission;
import org.mortbay.log.Log;
import org.mortbay.util.URIUtil;

/* loaded from: classes3.dex */
public class FileResource extends URLResource {
    private static boolean __checkAliases;
    private transient URL _alias;
    private transient boolean _aliasChecked;
    private File _file;

    @Override // org.mortbay.resource.Resource
    public String encode(String str) {
        return str;
    }

    static {
        boolean equalsIgnoreCase = "true".equalsIgnoreCase(System.getProperty("org.mortbay.util.FileResource.checkAliases", "true"));
        __checkAliases = equalsIgnoreCase;
        if (equalsIgnoreCase) {
            Log.debug("Checking Resource aliases");
        } else {
            Log.warn("Resource alias checking is disabled");
        }
    }

    public static void setCheckAliases(boolean z) {
        __checkAliases = z;
    }

    public static boolean getCheckAliases() {
        return __checkAliases;
    }

    public FileResource(URL url) throws IOException, URISyntaxException {
        super(url, null);
        this._alias = null;
        this._aliasChecked = false;
        try {
            this._file = new File(new URI(url.toString()));
        } catch (Exception e) {
            Log.ignore(e);
            try {
                URI uri = new URI(new StringBuffer("file:").append(URIUtil.encodePath(url.toString().substring(5))).toString());
                if (uri.getAuthority() == null) {
                    this._file = new File(uri);
                } else {
                    this._file = new File(new StringBuffer("//").append(uri.getAuthority()).append(URIUtil.decodePath(url.getFile())).toString());
                }
            } catch (Exception e2) {
                Log.ignore(e2);
                checkConnection();
                Permission permission = this._connection.getPermission();
                this._file = new File(permission == null ? url.getFile() : permission.getName());
            }
        }
        if (this._file.isDirectory()) {
            if (this._urlString.endsWith(URIUtil.SLASH)) {
                return;
            }
            this._urlString = new StringBuffer().append(this._urlString).append(URIUtil.SLASH).toString();
        } else if (this._urlString.endsWith(URIUtil.SLASH)) {
            this._urlString = this._urlString.substring(0, this._urlString.length() - 1);
        }
    }

    FileResource(URL url, URLConnection uRLConnection, File file) {
        super(url, uRLConnection);
        this._alias = null;
        this._aliasChecked = false;
        this._file = file;
        if (!file.isDirectory() || this._urlString.endsWith(URIUtil.SLASH)) {
            return;
        }
        this._urlString = new StringBuffer().append(this._urlString).append(URIUtil.SLASH).toString();
    }

    @Override // org.mortbay.resource.URLResource, org.mortbay.resource.Resource
    public Resource addPath(String str) throws IOException, MalformedURLException {
        String addPaths;
        URLResource uRLResource;
        String canonicalPath = URIUtil.canonicalPath(str);
        if (!isDirectory()) {
            uRLResource = (FileResource) super.addPath(canonicalPath);
            addPaths = uRLResource._urlString;
        } else {
            if (canonicalPath == null) {
                throw new MalformedURLException();
            }
            addPaths = URIUtil.addPaths(this._urlString, URIUtil.encodePath(canonicalPath.startsWith(URIUtil.SLASH) ? canonicalPath.substring(1) : canonicalPath));
            uRLResource = (URLResource) Resource.newResource(addPaths);
        }
        String encodePath = URIUtil.encodePath(canonicalPath);
        int length = uRLResource.toString().length() - encodePath.length();
        int lastIndexOf = uRLResource._urlString.lastIndexOf(encodePath, length);
        if (length != lastIndexOf && ((length - 1 != lastIndexOf || canonicalPath.endsWith(URIUtil.SLASH) || !uRLResource.isDirectory()) && !(uRLResource instanceof BadResource))) {
            FileResource fileResource = (FileResource) uRLResource;
            fileResource._alias = new URL(addPaths);
            fileResource._aliasChecked = true;
        }
        return uRLResource;
    }

    @Override // org.mortbay.resource.Resource
    public URL getAlias() {
        if (__checkAliases && !this._aliasChecked) {
            try {
                String absolutePath = this._file.getAbsolutePath();
                String canonicalPath = this._file.getCanonicalPath();
                if (absolutePath.length() != canonicalPath.length() || !absolutePath.equals(canonicalPath)) {
                    this._alias = new File(canonicalPath).toURI().toURL();
                }
                this._aliasChecked = true;
                if (this._alias != null && Log.isDebugEnabled()) {
                    Log.debug(new StringBuffer("ALIAS abs=").append(absolutePath).toString());
                    Log.debug(new StringBuffer("ALIAS can=").append(canonicalPath).toString());
                }
            } catch (Exception e) {
                Log.warn(Log.EXCEPTION, (Throwable) e);
                return getURL();
            }
        }
        return this._alias;
    }

    @Override // org.mortbay.resource.URLResource, org.mortbay.resource.Resource
    public boolean exists() {
        return this._file.exists();
    }

    @Override // org.mortbay.resource.URLResource, org.mortbay.resource.Resource
    public long lastModified() {
        return this._file.lastModified();
    }

    @Override // org.mortbay.resource.URLResource, org.mortbay.resource.Resource
    public boolean isDirectory() {
        return this._file.isDirectory();
    }

    @Override // org.mortbay.resource.URLResource, org.mortbay.resource.Resource
    public long length() {
        return this._file.length();
    }

    @Override // org.mortbay.resource.URLResource, org.mortbay.resource.Resource
    public String getName() {
        return this._file.getAbsolutePath();
    }

    @Override // org.mortbay.resource.URLResource, org.mortbay.resource.Resource
    public File getFile() {
        return this._file;
    }

    @Override // org.mortbay.resource.URLResource, org.mortbay.resource.Resource
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(this._file);
    }

    @Override // org.mortbay.resource.URLResource, org.mortbay.resource.Resource
    public OutputStream getOutputStream() throws IOException, SecurityException {
        return new FileOutputStream(this._file);
    }

    @Override // org.mortbay.resource.URLResource, org.mortbay.resource.Resource
    public boolean delete() throws SecurityException {
        return this._file.delete();
    }

    @Override // org.mortbay.resource.URLResource, org.mortbay.resource.Resource
    public boolean renameTo(Resource resource) throws SecurityException {
        if (resource instanceof FileResource) {
            return this._file.renameTo(((FileResource) resource)._file);
        }
        return false;
    }

    @Override // org.mortbay.resource.URLResource, org.mortbay.resource.Resource
    public String[] list() {
        String[] list = this._file.list();
        if (list == null) {
            return null;
        }
        int length = list.length;
        while (true) {
            int i = length - 1;
            if (length <= 0) {
                return list;
            }
            if (new File(this._file, list[i]).isDirectory() && !list[i].endsWith(URIUtil.SLASH)) {
                list[i] = new StringBuffer().append(list[i]).append(URIUtil.SLASH).toString();
            }
            length = i;
        }
    }

    @Override // org.mortbay.resource.URLResource
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof FileResource)) {
            return false;
        }
        Object obj2 = ((FileResource) obj)._file;
        File file = this._file;
        if (obj2 != file) {
            return file != null && file.equals(obj2);
        }
        return true;
    }

    @Override // org.mortbay.resource.URLResource
    public int hashCode() {
        File file = this._file;
        return file == null ? super.hashCode() : file.hashCode();
    }
}
