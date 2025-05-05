package org.mortbay.resource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;
import org.mortbay.util.URIUtil;

/* loaded from: classes3.dex */
public class ResourceCollection extends Resource {
    private Resource[] _resources;

    @Override // org.mortbay.resource.Resource
    public long length() {
        return -1L;
    }

    public ResourceCollection() {
    }

    public ResourceCollection(Resource[] resourceArr) {
        setResources(resourceArr);
    }

    public ResourceCollection(String[] strArr) {
        setResources(strArr);
    }

    public ResourceCollection(String str) {
        setResources(str);
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x003a, code lost:
    
        throw new java.lang.IllegalArgumentException(new java.lang.StringBuffer().append(r0).append(" is not an existing directory.").toString());
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setResources(org.mortbay.resource.Resource[] r3) {
        /*
            r2 = this;
            org.mortbay.resource.Resource[] r0 = r2._resources
            if (r0 != 0) goto L4c
            if (r3 == 0) goto L44
            int r0 = r3.length
            if (r0 == 0) goto L3c
            r2._resources = r3
            r3 = 0
        Lc:
            org.mortbay.resource.Resource[] r0 = r2._resources
            int r1 = r0.length
            if (r3 >= r1) goto L3b
            r0 = r0[r3]
            boolean r1 = r0.exists()
            if (r1 == 0) goto L22
            boolean r1 = r0.isDirectory()
            if (r1 == 0) goto L22
            int r3 = r3 + 1
            goto Lc
        L22:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            java.lang.StringBuffer r0 = r1.append(r0)
            java.lang.String r1 = " is not an existing directory."
            java.lang.StringBuffer r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            r3.<init>(r0)
            throw r3
        L3b:
            return
        L3c:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "arg *resources* must be one or more resources."
            r3.<init>(r0)
            throw r3
        L44:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "*resources* must not be null."
            r3.<init>(r0)
            throw r3
        L4c:
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException
            java.lang.String r0 = "*resources* already set."
            r3.<init>(r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mortbay.resource.ResourceCollection.setResources(org.mortbay.resource.Resource[]):void");
    }

    public void setResources(String[] strArr) {
        if (this._resources != null) {
            throw new IllegalStateException("*resources* already set.");
        }
        if (strArr == null) {
            throw new IllegalArgumentException("*resources* must not be null.");
        }
        if (strArr.length == 0) {
            throw new IllegalArgumentException("arg *resources* must be one or more resources.");
        }
        this._resources = new Resource[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            try {
                this._resources[i] = Resource.newResource(strArr[i]);
                if (!this._resources[i].exists() || !this._resources[i].isDirectory()) {
                    throw new IllegalArgumentException(new StringBuffer().append(this._resources[i]).append(" is not an existing directory.").toString());
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void setResources(String str) {
        if (this._resources != null) {
            throw new IllegalStateException("*resources* already set.");
        }
        if (str == null) {
            throw new IllegalArgumentException("*csvResources* must not be null.");
        }
        StringTokenizer stringTokenizer = new StringTokenizer(str, ",;");
        int countTokens = stringTokenizer.countTokens();
        if (countTokens == 0) {
            throw new IllegalArgumentException("arg *resources* must be one or more resources.");
        }
        this._resources = new Resource[countTokens];
        int i = 0;
        while (stringTokenizer.hasMoreTokens()) {
            try {
                this._resources[i] = Resource.newResource(stringTokenizer.nextToken().trim());
                if (!this._resources[i].exists() || !this._resources[i].isDirectory()) {
                    throw new IllegalArgumentException(new StringBuffer().append(this._resources[i]).append(" is not an existing directory.").toString());
                }
                i++;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void setResourcesAsCSV(String str) {
        setResources(str);
    }

    public Resource[] getResources() {
        return this._resources;
    }

    @Override // org.mortbay.resource.Resource
    public Resource addPath(String str) throws IOException, MalformedURLException {
        if (this._resources == null) {
            throw new IllegalStateException("*resources* not set.");
        }
        if (str == null) {
            throw new MalformedURLException();
        }
        if (str.length() == 0 || URIUtil.SLASH.equals(str)) {
            return this;
        }
        int i = 0;
        Resource resource = null;
        while (true) {
            Resource[] resourceArr = this._resources;
            if (i >= resourceArr.length) {
                break;
            }
            resource = resourceArr[i].addPath(str);
            if (!resource.exists()) {
                i++;
            } else if (!resource.isDirectory()) {
                return resource;
            }
        }
        int i2 = i + 1;
        ArrayList arrayList = null;
        while (true) {
            Resource[] resourceArr2 = this._resources;
            if (i2 >= resourceArr2.length) {
                break;
            }
            Resource addPath = resourceArr2[i2].addPath(str);
            if (addPath.exists() && addPath.isDirectory()) {
                if (resource != null) {
                    arrayList = new ArrayList();
                    arrayList.add(resource);
                    resource = null;
                }
                arrayList.add(addPath);
            }
            i2++;
        }
        if (resource != null) {
            return resource;
        }
        if (arrayList != null) {
            return new ResourceCollection((Resource[]) arrayList.toArray(new Resource[arrayList.size()]));
        }
        return null;
    }

    protected Object findResource(String str) throws IOException, MalformedURLException {
        int i = 0;
        Resource resource = null;
        while (true) {
            Resource[] resourceArr = this._resources;
            if (i >= resourceArr.length) {
                break;
            }
            resource = resourceArr[i].addPath(str);
            if (!resource.exists()) {
                i++;
            } else if (!resource.isDirectory()) {
                return resource;
            }
        }
        int i2 = i + 1;
        ArrayList arrayList = null;
        while (true) {
            Resource[] resourceArr2 = this._resources;
            if (i2 >= resourceArr2.length) {
                break;
            }
            Resource addPath = resourceArr2[i2].addPath(str);
            if (addPath.exists() && addPath.isDirectory()) {
                if (resource != null) {
                    arrayList = new ArrayList();
                    arrayList.add(resource);
                }
                arrayList.add(addPath);
            }
            i2++;
        }
        if (resource != null) {
            return resource;
        }
        if (arrayList != null) {
            return arrayList;
        }
        return null;
    }

    @Override // org.mortbay.resource.Resource
    public boolean delete() throws SecurityException {
        throw new UnsupportedOperationException();
    }

    @Override // org.mortbay.resource.Resource
    public boolean exists() {
        if (this._resources != null) {
            return true;
        }
        throw new IllegalStateException("*resources* not set.");
    }

    @Override // org.mortbay.resource.Resource
    public File getFile() throws IOException {
        if (this._resources == null) {
            throw new IllegalStateException("*resources* not set.");
        }
        int i = 0;
        while (true) {
            Resource[] resourceArr = this._resources;
            if (i >= resourceArr.length) {
                return null;
            }
            File file = resourceArr[i].getFile();
            if (file != null) {
                return file;
            }
            i++;
        }
    }

    @Override // org.mortbay.resource.Resource
    public InputStream getInputStream() throws IOException {
        if (this._resources == null) {
            throw new IllegalStateException("*resources* not set.");
        }
        int i = 0;
        while (true) {
            Resource[] resourceArr = this._resources;
            if (i >= resourceArr.length) {
                return null;
            }
            InputStream inputStream = resourceArr[i].getInputStream();
            if (inputStream != null) {
                return inputStream;
            }
            i++;
        }
    }

    @Override // org.mortbay.resource.Resource
    public String getName() {
        if (this._resources == null) {
            throw new IllegalStateException("*resources* not set.");
        }
        int i = 0;
        while (true) {
            Resource[] resourceArr = this._resources;
            if (i >= resourceArr.length) {
                return null;
            }
            String name = resourceArr[i].getName();
            if (name != null) {
                return name;
            }
            i++;
        }
    }

    @Override // org.mortbay.resource.Resource
    public OutputStream getOutputStream() throws IOException, SecurityException {
        if (this._resources == null) {
            throw new IllegalStateException("*resources* not set.");
        }
        int i = 0;
        while (true) {
            Resource[] resourceArr = this._resources;
            if (i >= resourceArr.length) {
                return null;
            }
            OutputStream outputStream = resourceArr[i].getOutputStream();
            if (outputStream != null) {
                return outputStream;
            }
            i++;
        }
    }

    @Override // org.mortbay.resource.Resource
    public URL getURL() {
        if (this._resources == null) {
            throw new IllegalStateException("*resources* not set.");
        }
        int i = 0;
        while (true) {
            Resource[] resourceArr = this._resources;
            if (i >= resourceArr.length) {
                return null;
            }
            URL url = resourceArr[i].getURL();
            if (url != null) {
                return url;
            }
            i++;
        }
    }

    @Override // org.mortbay.resource.Resource
    public boolean isDirectory() {
        if (this._resources != null) {
            return true;
        }
        throw new IllegalStateException("*resources* not set.");
    }

    @Override // org.mortbay.resource.Resource
    public long lastModified() {
        if (this._resources == null) {
            throw new IllegalStateException("*resources* not set.");
        }
        int i = 0;
        while (true) {
            Resource[] resourceArr = this._resources;
            if (i >= resourceArr.length) {
                return -1L;
            }
            long lastModified = resourceArr[i].lastModified();
            if (lastModified != -1) {
                return lastModified;
            }
            i++;
        }
    }

    @Override // org.mortbay.resource.Resource
    public String[] list() {
        if (this._resources == null) {
            throw new IllegalStateException("*resources* not set.");
        }
        HashSet hashSet = new HashSet();
        int i = 0;
        while (true) {
            Resource[] resourceArr = this._resources;
            if (i < resourceArr.length) {
                for (String str : resourceArr[i].list()) {
                    hashSet.add(str);
                }
                i++;
            } else {
                return (String[]) hashSet.toArray(new String[hashSet.size()]);
            }
        }
    }

    @Override // org.mortbay.resource.Resource
    public void release() {
        if (this._resources == null) {
            throw new IllegalStateException("*resources* not set.");
        }
        int i = 0;
        while (true) {
            Resource[] resourceArr = this._resources;
            if (i >= resourceArr.length) {
                return;
            }
            resourceArr[i].release();
            i++;
        }
    }

    @Override // org.mortbay.resource.Resource
    public boolean renameTo(Resource resource) throws SecurityException {
        throw new UnsupportedOperationException();
    }

    public String toString() {
        if (this._resources == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        int i = 0;
        while (true) {
            Resource[] resourceArr = this._resources;
            if (i < resourceArr.length) {
                stringBuffer.append(resourceArr[i].toString()).append(';');
                i++;
            } else {
                return stringBuffer.toString();
            }
        }
    }
}
