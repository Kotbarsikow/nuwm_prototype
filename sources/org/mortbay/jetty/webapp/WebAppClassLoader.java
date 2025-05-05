package org.mortbay.jetty.webapp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.CodeSource;
import java.security.PermissionCollection;
import java.util.HashSet;
import java.util.StringTokenizer;
import org.mortbay.jetty.handler.ContextHandler;
import org.mortbay.log.Log;
import org.mortbay.resource.Resource;
import org.mortbay.util.IO;
import org.mortbay.util.LazyList;
import org.mortbay.util.StringUtil;
import org.mortbay.util.URIUtil;

/* loaded from: classes3.dex */
public class WebAppClassLoader extends URLClassLoader {
    static /* synthetic */ Class class$org$mortbay$jetty$webapp$WebAppClassLoader;
    private WebAppContext _context;
    private HashSet _extensions;
    private String _name;
    private ClassLoader _parent;

    public WebAppClassLoader(WebAppContext webAppContext) throws IOException {
        this(null, webAppContext);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public WebAppClassLoader(java.lang.ClassLoader r3, org.mortbay.jetty.webapp.WebAppContext r4) throws java.io.IOException {
        /*
            r2 = this;
            r0 = 0
            java.net.URL[] r0 = new java.net.URL[r0]
            java.lang.String r1 = "org.mortbay.jetty.webapp.WebAppClassLoader"
            if (r3 == 0) goto L8
            goto L3e
        L8:
            java.lang.Thread r3 = java.lang.Thread.currentThread()
            java.lang.ClassLoader r3 = r3.getContextClassLoader()
            if (r3 == 0) goto L1b
            java.lang.Thread r3 = java.lang.Thread.currentThread()
            java.lang.ClassLoader r3 = r3.getContextClassLoader()
            goto L3e
        L1b:
            java.lang.Class r3 = org.mortbay.jetty.webapp.WebAppClassLoader.class$org$mortbay$jetty$webapp$WebAppClassLoader
            if (r3 != 0) goto L25
            java.lang.Class r3 = class$(r1)
            org.mortbay.jetty.webapp.WebAppClassLoader.class$org$mortbay$jetty$webapp$WebAppClassLoader = r3
        L25:
            java.lang.ClassLoader r3 = r3.getClassLoader()
            if (r3 == 0) goto L3a
            java.lang.Class r3 = org.mortbay.jetty.webapp.WebAppClassLoader.class$org$mortbay$jetty$webapp$WebAppClassLoader
            if (r3 != 0) goto L35
            java.lang.Class r3 = class$(r1)
            org.mortbay.jetty.webapp.WebAppClassLoader.class$org$mortbay$jetty$webapp$WebAppClassLoader = r3
        L35:
            java.lang.ClassLoader r3 = r3.getClassLoader()
            goto L3e
        L3a:
            java.lang.ClassLoader r3 = java.lang.ClassLoader.getSystemClassLoader()
        L3e:
            r2.<init>(r0, r3)
            java.lang.ClassLoader r3 = r2.getParent()
            r2._parent = r3
            r2._context = r4
            if (r3 == 0) goto Lae
            java.util.HashSet r3 = new java.util.HashSet
            r3.<init>()
            r2._extensions = r3
            java.lang.String r0 = ".jar"
            r3.add(r0)
            java.util.HashSet r3 = r2._extensions
            java.lang.String r0 = ".zip"
            r3.add(r0)
            java.lang.StringBuffer r3 = new java.lang.StringBuffer
            r3.<init>()
            java.lang.Class r0 = org.mortbay.jetty.webapp.WebAppClassLoader.class$org$mortbay$jetty$webapp$WebAppClassLoader
            if (r0 != 0) goto L6d
            java.lang.Class r0 = class$(r1)
            org.mortbay.jetty.webapp.WebAppClassLoader.class$org$mortbay$jetty$webapp$WebAppClassLoader = r0
        L6d:
            java.lang.String r0 = r0.getName()
            java.lang.StringBuffer r3 = r3.append(r0)
            java.lang.String r0 = ".extensions"
            java.lang.StringBuffer r3 = r3.append(r0)
            java.lang.String r3 = r3.toString()
            java.lang.String r3 = java.lang.System.getProperty(r3)
            if (r3 == 0) goto La0
            java.util.StringTokenizer r0 = new java.util.StringTokenizer
            java.lang.String r1 = ",;"
            r0.<init>(r3, r1)
        L8c:
            boolean r3 = r0.hasMoreTokens()
            if (r3 == 0) goto La0
            java.util.HashSet r3 = r2._extensions
            java.lang.String r1 = r0.nextToken()
            java.lang.String r1 = r1.trim()
            r3.add(r1)
            goto L8c
        La0:
            java.lang.String r3 = r4.getExtraClasspath()
            if (r3 == 0) goto Lad
            java.lang.String r3 = r4.getExtraClasspath()
            r2.addClassPath(r3)
        Lad:
            return
        Lae:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            java.lang.String r4 = "no parent classloader!"
            r3.<init>(r4)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mortbay.jetty.webapp.WebAppClassLoader.<init>(java.lang.ClassLoader, org.mortbay.jetty.webapp.WebAppContext):void");
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    public String getName() {
        return this._name;
    }

    public void setName(String str) {
        this._name = str;
    }

    public ContextHandler getContext() {
        return this._context;
    }

    public void addClassPath(String str) throws IOException {
        FileOutputStream fileOutputStream;
        if (str == null) {
            return;
        }
        StringTokenizer stringTokenizer = new StringTokenizer(str, ",;");
        while (stringTokenizer.hasMoreTokens()) {
            Resource newResource = Resource.newResource(stringTokenizer.nextToken());
            if (Log.isDebugEnabled()) {
                Log.debug(new StringBuffer("Path resource=").append(newResource).toString());
            }
            File file = newResource.getFile();
            if (file != null) {
                addURL(newResource.getURL());
            } else if (!newResource.isDirectory() && file == null) {
                InputStream inputStream = newResource.getInputStream();
                File tempDirectory = this._context.getTempDirectory();
                FileOutputStream fileOutputStream2 = null;
                if (tempDirectory == null) {
                    tempDirectory = File.createTempFile("jetty.cl.lib", null);
                    tempDirectory.mkdir();
                    tempDirectory.deleteOnExit();
                }
                File file2 = new File(tempDirectory, "lib");
                if (!file2.exists()) {
                    file2.mkdir();
                    file2.deleteOnExit();
                }
                File createTempFile = File.createTempFile("Jetty-", ".jar", file2);
                createTempFile.deleteOnExit();
                if (Log.isDebugEnabled()) {
                    Log.debug(new StringBuffer("Extract ").append(newResource).append(" to ").append(createTempFile).toString());
                }
                try {
                    fileOutputStream = new FileOutputStream(createTempFile);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    IO.copy(inputStream, fileOutputStream);
                    IO.close(fileOutputStream);
                    addURL(createTempFile.toURL());
                } catch (Throwable th2) {
                    th = th2;
                    fileOutputStream2 = fileOutputStream;
                    IO.close(fileOutputStream2);
                    throw th;
                }
            } else {
                addURL(newResource.getURL());
            }
        }
    }

    private boolean isFileSupported(String str) {
        int lastIndexOf = str.lastIndexOf(46);
        return lastIndexOf != -1 && this._extensions.contains(str.substring(lastIndexOf));
    }

    public void addJars(Resource resource) {
        if (resource.exists() && resource.isDirectory()) {
            String[] list = resource.list();
            for (int i = 0; list != null && i < list.length; i++) {
                try {
                    Resource addPath = resource.addPath(list[i]);
                    if (isFileSupported(addPath.getName().toLowerCase())) {
                        addClassPath(StringUtil.replace(StringUtil.replace(addPath.toString(), ",", "%2C"), ";", "%3B"));
                    }
                } catch (Exception e) {
                    Log.warn(Log.EXCEPTION, (Throwable) e);
                }
            }
        }
    }

    public void destroy() {
        this._parent = null;
    }

    @Override // java.net.URLClassLoader, java.security.SecureClassLoader
    public PermissionCollection getPermissions(CodeSource codeSource) {
        PermissionCollection permissions = this._context.getPermissions();
        return permissions == null ? super.getPermissions(codeSource) : permissions;
    }

    @Override // java.lang.ClassLoader
    public URL getResource(String str) {
        boolean z;
        ClassLoader classLoader;
        if (this._context.isParentLoaderPriority() || isSystemPath(str)) {
            ClassLoader classLoader2 = this._parent;
            r2 = classLoader2 != null ? classLoader2.getResource(str) : null;
            z = true;
        } else {
            z = false;
        }
        if (r2 == null && (r2 = findResource(str)) == null && str.startsWith(URIUtil.SLASH)) {
            if (Log.isDebugEnabled()) {
                Log.debug(new StringBuffer("HACK leading / off ").append(str).toString());
            }
            r2 = findResource(str.substring(1));
        }
        if (r2 == null && !z && (classLoader = this._parent) != null) {
            r2 = classLoader.getResource(str);
        }
        if (r2 != null && Log.isDebugEnabled()) {
            Log.debug(new StringBuffer("getResource(").append(str).append(")=").append(r2).toString());
        }
        return r2;
    }

    public boolean isServerPath(String str) {
        boolean z;
        String replace = str.replace('/', '.');
        while (replace.startsWith(".")) {
            replace = replace.substring(1);
        }
        String[] serverClasses = this._context.getServerClasses();
        if (serverClasses != null) {
            for (int i = 0; i < serverClasses.length; i++) {
                String str2 = serverClasses[i];
                if (str2.startsWith("-")) {
                    str2 = str2.substring(1);
                    z = false;
                } else {
                    z = true;
                }
                if (str2.endsWith(".")) {
                    if (replace.startsWith(str2)) {
                        return z;
                    }
                } else if (replace.equals(str2)) {
                    return z;
                }
            }
        }
        return false;
    }

    public boolean isSystemPath(String str) {
        boolean z;
        String replace = str.replace('/', '.');
        while (replace.startsWith(".")) {
            replace = replace.substring(1);
        }
        String[] systemClasses = this._context.getSystemClasses();
        if (systemClasses != null) {
            for (int i = 0; i < systemClasses.length; i++) {
                String str2 = systemClasses[i];
                if (str2.startsWith("-")) {
                    str2 = str2.substring(1);
                    z = false;
                } else {
                    z = true;
                }
                if (str2.endsWith(".")) {
                    if (replace.startsWith(str2)) {
                        return z;
                    }
                } else if (replace.equals(str2)) {
                    return z;
                }
            }
        }
        return false;
    }

    @Override // java.lang.ClassLoader
    public Class loadClass(String str) throws ClassNotFoundException {
        return loadClass(str, false);
    }

    @Override // java.lang.ClassLoader
    protected synchronized Class loadClass(String str, boolean z) throws ClassNotFoundException {
        Class<?> findLoadedClass;
        boolean z2;
        findLoadedClass = findLoadedClass(str);
        ClassNotFoundException e = null;
        if (findLoadedClass == null && this._parent != null && (this._context.isParentLoaderPriority() || isSystemPath(str))) {
            z2 = true;
            try {
                findLoadedClass = this._parent.loadClass(str);
                if (Log.isDebugEnabled()) {
                    Log.debug(new StringBuffer("loaded ").append(findLoadedClass).toString());
                }
            } catch (ClassNotFoundException e2) {
                e = e2;
            }
        } else {
            z2 = false;
        }
        if (findLoadedClass == null) {
            try {
                findLoadedClass = findClass(str);
            } catch (ClassNotFoundException e3) {
                e = e3;
            }
        }
        if (findLoadedClass == null && this._parent != null && !z2 && !isServerPath(str)) {
            findLoadedClass = this._parent.loadClass(str);
        }
        if (findLoadedClass == null) {
            throw e;
        }
        if (z) {
            resolveClass(findLoadedClass);
        }
        if (Log.isDebugEnabled()) {
            Log.debug(new StringBuffer("loaded ").append(findLoadedClass).append(" from ").append(findLoadedClass.getClassLoader()).toString());
        }
        return findLoadedClass;
    }

    public String toString() {
        if (Log.isDebugEnabled()) {
            return new StringBuffer("ContextLoader@").append(this._name).append("(").append(LazyList.array2List(getURLs())).append(") / ").append(this._parent).toString();
        }
        return new StringBuffer("ContextLoader@").append(this._name).toString();
    }
}
