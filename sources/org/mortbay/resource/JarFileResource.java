package org.mortbay.resource;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import org.mortbay.log.Log;
import org.mortbay.util.URIUtil;

/* loaded from: classes3.dex */
class JarFileResource extends JarResource {
    transient boolean _directory;
    transient JarEntry _entry;
    transient boolean _exists;
    transient File _file;
    transient JarFile _jarFile;
    transient String _jarUrl;
    transient String[] _list;
    transient String _path;

    @Override // org.mortbay.resource.Resource
    public String encode(String str) {
        return str;
    }

    JarFileResource(URL url) {
        super(url);
    }

    JarFileResource(URL url, boolean z) {
        super(url, z);
    }

    @Override // org.mortbay.resource.JarResource, org.mortbay.resource.URLResource, org.mortbay.resource.Resource
    public synchronized void release() {
        this._list = null;
        this._entry = null;
        this._file = null;
        this._jarFile = null;
        super.release();
    }

    @Override // org.mortbay.resource.JarResource, org.mortbay.resource.URLResource
    protected boolean checkConnection() {
        try {
            super.checkConnection();
            return this._jarFile != null;
        } finally {
            if (this._jarConnection == null) {
                this._entry = null;
                this._file = null;
                this._jarFile = null;
                this._list = null;
            }
        }
    }

    @Override // org.mortbay.resource.JarResource
    protected void newConnection() throws IOException {
        super.newConnection();
        this._entry = null;
        this._file = null;
        this._jarFile = null;
        this._list = null;
        int indexOf = this._urlString.indexOf("!/") + 2;
        this._jarUrl = this._urlString.substring(0, indexOf);
        String substring = this._urlString.substring(indexOf);
        this._path = substring;
        if (substring.length() == 0) {
            this._path = null;
        }
        this._jarFile = this._jarConnection.getJarFile();
        this._file = new File(this._jarFile.getName());
    }

    @Override // org.mortbay.resource.JarResource, org.mortbay.resource.URLResource, org.mortbay.resource.Resource
    public boolean exists() {
        JarFile jarFile;
        boolean z = true;
        if (this._exists) {
            return true;
        }
        if (this._urlString.endsWith("!/")) {
            try {
                return newResource(this._urlString.substring(4, this._urlString.length() - 2)).exists();
            } catch (Exception e) {
                Log.ignore(e);
                return false;
            }
        }
        boolean checkConnection = checkConnection();
        if (this._jarUrl != null && this._path == null) {
            this._directory = checkConnection;
            return true;
        }
        if (checkConnection) {
            jarFile = this._jarFile;
        } else {
            try {
                JarURLConnection jarURLConnection = (JarURLConnection) new URL(this._jarUrl).openConnection();
                JarURLConnection jarURLConnection2 = jarURLConnection;
                jarURLConnection.setUseCaches(getUseCaches());
                jarFile = jarURLConnection.getJarFile();
            } catch (Exception e2) {
                Log.ignore(e2);
                jarFile = null;
            }
        }
        if (jarFile != null && this._entry == null && !this._directory) {
            Enumeration<JarEntry> entries = jarFile.entries();
            while (true) {
                if (!entries.hasMoreElements()) {
                    break;
                }
                JarEntry nextElement = entries.nextElement();
                String replace = nextElement.getName().replace('\\', '/');
                if (replace.equals(this._path)) {
                    this._entry = nextElement;
                    this._directory = this._path.endsWith(URIUtil.SLASH);
                    break;
                }
                if (this._path.endsWith(URIUtil.SLASH)) {
                    if (replace.startsWith(this._path)) {
                        this._directory = true;
                        break;
                    }
                } else if (replace.startsWith(this._path) && replace.length() > this._path.length() && replace.charAt(this._path.length()) == '/') {
                    this._directory = true;
                    break;
                }
            }
        }
        if (!this._directory && this._entry == null) {
            z = false;
        }
        this._exists = z;
        return z;
    }

    @Override // org.mortbay.resource.URLResource, org.mortbay.resource.Resource
    public boolean isDirectory() {
        return this._urlString.endsWith(URIUtil.SLASH) || (exists() && this._directory);
    }

    @Override // org.mortbay.resource.URLResource, org.mortbay.resource.Resource
    public long lastModified() {
        File file;
        if (!checkConnection() || (file = this._file) == null) {
            return -1L;
        }
        return file.lastModified();
    }

    @Override // org.mortbay.resource.URLResource, org.mortbay.resource.Resource
    public synchronized String[] list() {
        if (isDirectory() && this._list == null) {
            ArrayList arrayList = new ArrayList(32);
            checkConnection();
            JarFile jarFile = this._jarFile;
            if (jarFile == null) {
                try {
                    JarURLConnection jarURLConnection = (JarURLConnection) new URL(this._jarUrl).openConnection();
                    JarURLConnection jarURLConnection2 = jarURLConnection;
                    jarURLConnection.setUseCaches(getUseCaches());
                    jarFile = jarURLConnection.getJarFile();
                } catch (Exception e) {
                    Log.ignore(e);
                }
            }
            Enumeration<JarEntry> entries = jarFile.entries();
            String substring = this._urlString.substring(this._urlString.indexOf("!/") + 2);
            while (entries.hasMoreElements()) {
                String replace = entries.nextElement().getName().replace('\\', '/');
                if (replace.startsWith(substring) && replace.length() != substring.length()) {
                    String substring2 = replace.substring(substring.length());
                    int indexOf = substring2.indexOf(47);
                    if (indexOf >= 0) {
                        if (indexOf != 0 || substring2.length() != 1) {
                            if (indexOf == 0) {
                                substring2 = substring2.substring(indexOf + 1, substring2.length());
                            } else {
                                substring2 = substring2.substring(0, indexOf + 1);
                            }
                            if (arrayList.contains(substring2)) {
                            }
                        }
                    }
                    arrayList.add(substring2);
                }
            }
            String[] strArr = new String[arrayList.size()];
            this._list = strArr;
            arrayList.toArray(strArr);
        }
        return this._list;
    }

    @Override // org.mortbay.resource.URLResource, org.mortbay.resource.Resource
    public long length() {
        JarEntry jarEntry;
        if (isDirectory() || (jarEntry = this._entry) == null) {
            return -1L;
        }
        return jarEntry.getSize();
    }

    public static Resource getNonCachingResource(Resource resource) {
        return !(resource instanceof JarFileResource) ? resource : new JarFileResource(((JarFileResource) resource).getURL(), false);
    }
}
