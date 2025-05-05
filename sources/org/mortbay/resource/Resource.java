package org.mortbay.resource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;
import org.mortbay.log.Log;
import org.mortbay.util.IO;
import org.mortbay.util.Loader;
import org.mortbay.util.StringUtil;
import org.mortbay.util.URIUtil;

/* loaded from: classes3.dex */
public abstract class Resource implements Serializable {
    public static boolean __defaultUseCaches = true;
    static /* synthetic */ Class class$org$mortbay$resource$Resource;
    Object _associate;

    public abstract Resource addPath(String str) throws IOException, MalformedURLException;

    public abstract boolean delete() throws SecurityException;

    public abstract boolean exists();

    public URL getAlias() {
        return null;
    }

    public abstract File getFile() throws IOException;

    public abstract InputStream getInputStream() throws IOException;

    public abstract String getName();

    public abstract OutputStream getOutputStream() throws IOException, SecurityException;

    public abstract URL getURL();

    public abstract boolean isDirectory();

    public abstract long lastModified();

    public abstract long length();

    public abstract String[] list();

    public abstract void release();

    public abstract boolean renameTo(Resource resource) throws SecurityException;

    public static void setDefaultUseCaches(boolean z) {
        __defaultUseCaches = z;
    }

    public static boolean getDefaultUseCaches() {
        return __defaultUseCaches;
    }

    public static Resource newResource(URL url) throws IOException {
        return newResource(url, __defaultUseCaches);
    }

    public static Resource newResource(URL url, boolean z) {
        if (url == null) {
            return null;
        }
        String externalForm = url.toExternalForm();
        if (externalForm.startsWith("file:")) {
            try {
                return new FileResource(url);
            } catch (Exception e) {
                Log.debug(Log.EXCEPTION, e);
                return new BadResource(url, e.toString());
            }
        }
        if (externalForm.startsWith("jar:file:")) {
            return new JarFileResource(url, z);
        }
        if (externalForm.startsWith("jar:")) {
            return new JarResource(url, z);
        }
        return new URLResource(url, null, z);
    }

    public static Resource newResource(String str) throws MalformedURLException, IOException {
        return newResource(str, __defaultUseCaches);
    }

    public static Resource newResource(String str, boolean z) throws MalformedURLException, IOException {
        try {
            URL url = new URL(str);
            String url2 = url.toString();
            if (url2.length() > 0 && url2.charAt(url2.length() - 1) != str.charAt(str.length() - 1) && ((url2.charAt(url2.length() - 1) != '/' || url2.charAt(url2.length() - 2) != str.charAt(str.length() - 1)) && (str.charAt(str.length() - 1) != '/' || str.charAt(str.length() - 2) != url2.charAt(url2.length() - 1)))) {
                return new BadResource(url, new StringBuffer("Trailing special characters stripped by URL in ").append(str).toString());
            }
            return newResource(url);
        } catch (MalformedURLException e) {
            if (!str.startsWith("ftp:") && !str.startsWith("file:") && !str.startsWith("jar:")) {
                try {
                    if (str.startsWith("./")) {
                        str = str.substring(2);
                    }
                    File canonicalFile = new File(str).getCanonicalFile();
                    URL url3 = new URL(URIUtil.encodePath(canonicalFile.toURL().toString()));
                    URLConnection openConnection = url3.openConnection();
                    openConnection.setUseCaches(z);
                    return new FileResource(url3, openConnection, canonicalFile);
                } catch (Exception e2) {
                    Log.debug(Log.EXCEPTION, e2);
                    throw e;
                }
            }
            Log.warn(new StringBuffer("Bad Resource: ").append(str).toString());
            throw e;
        }
    }

    public static Resource newSystemResource(String str) throws IOException {
        URL url;
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        if (contextClassLoader != null) {
            url = contextClassLoader.getResource(str);
            if (url == null && str.startsWith(URIUtil.SLASH)) {
                url = contextClassLoader.getResource(str.substring(1));
            }
        } else {
            url = null;
        }
        if (url == null) {
            Class cls = class$org$mortbay$resource$Resource;
            if (cls == null) {
                cls = class$("org.mortbay.resource.Resource");
                class$org$mortbay$resource$Resource = cls;
            }
            contextClassLoader = cls.getClassLoader();
            if (contextClassLoader != null && (url = contextClassLoader.getResource(str)) == null && str.startsWith(URIUtil.SLASH)) {
                url = contextClassLoader.getResource(str.substring(1));
            }
        }
        if (url == null && (url = ClassLoader.getSystemResource(str)) == null && str.startsWith(URIUtil.SLASH)) {
            url = contextClassLoader.getResource(str.substring(1));
        }
        if (url == null) {
            return null;
        }
        return newResource(url);
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    public static Resource newClassPathResource(String str) {
        return newClassPathResource(str, true, false);
    }

    public static Resource newClassPathResource(String str, boolean z, boolean z2) {
        Class cls = class$org$mortbay$resource$Resource;
        if (cls == null) {
            cls = class$("org.mortbay.resource.Resource");
            class$org$mortbay$resource$Resource = cls;
        }
        URL resource = cls.getResource(str);
        if (resource == null) {
            try {
                Class cls2 = class$org$mortbay$resource$Resource;
                if (cls2 == null) {
                    cls2 = class$("org.mortbay.resource.Resource");
                    class$org$mortbay$resource$Resource = cls2;
                }
                resource = Loader.getResource(cls2, str, z2);
            } catch (ClassNotFoundException unused) {
                resource = ClassLoader.getSystemResource(str);
            }
        }
        if (resource == null) {
            return null;
        }
        return newResource(resource, z);
    }

    protected void finalize() {
        release();
    }

    public String encode(String str) {
        return URIUtil.encodePath(str);
    }

    public Object getAssociate() {
        return this._associate;
    }

    public void setAssociate(Object obj) {
        this._associate = obj;
    }

    public String getListHTML(String str, boolean z) throws IOException {
        String[] list;
        String canonicalPath = URIUtil.canonicalPath(str);
        if (canonicalPath == null || !isDirectory() || (list = list()) == null) {
            return null;
        }
        Arrays.sort(list);
        String stringBuffer = new StringBuffer("Directory: ").append(deTag(URIUtil.decodePath(canonicalPath))).toString();
        StringBuffer stringBuffer2 = new StringBuffer(4096);
        stringBuffer2.append("<HTML><HEAD><TITLE>");
        stringBuffer2.append(stringBuffer);
        stringBuffer2.append("</TITLE></HEAD><BODY>\n<H1>");
        stringBuffer2.append(stringBuffer);
        stringBuffer2.append("</H1>\n<TABLE BORDER=0>\n");
        if (z) {
            stringBuffer2.append("<TR><TD><A HREF=\"");
            stringBuffer2.append(URIUtil.addPaths(canonicalPath, "../"));
            stringBuffer2.append("\">Parent Directory</A></TD><TD></TD><TD></TD></TR>\n");
        }
        String defangURI = defangURI(canonicalPath);
        DateFormat dateTimeInstance = DateFormat.getDateTimeInstance(2, 2);
        for (int i = 0; i < list.length; i++) {
            Resource addPath = addPath(list[i]);
            stringBuffer2.append("\n<TR><TD><A HREF=\"");
            String addPaths = URIUtil.addPaths(defangURI, URIUtil.encodePath(list[i]));
            stringBuffer2.append(addPaths);
            if (addPath.isDirectory() && !addPaths.endsWith(URIUtil.SLASH)) {
                stringBuffer2.append(URIUtil.SLASH);
            }
            stringBuffer2.append("\">");
            stringBuffer2.append(deTag(list[i]));
            stringBuffer2.append("&nbsp;</TD><TD ALIGN=right>");
            stringBuffer2.append(addPath.length());
            stringBuffer2.append(" bytes&nbsp;</TD><TD>");
            stringBuffer2.append(dateTimeInstance.format(new Date(addPath.lastModified())));
            stringBuffer2.append("</TD></TR>");
        }
        stringBuffer2.append("</TABLE>\n</BODY></HTML>\n");
        return stringBuffer2.toString();
    }

    private static String defangURI(String str) {
        StringBuffer stringBuffer = null;
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt == '\"' || charAt == '\'' || charAt == '<' || charAt == '>') {
                stringBuffer = new StringBuffer(str.length() << 1);
            }
        }
        if (stringBuffer == null) {
            return str;
        }
        for (int i2 = 0; i2 < str.length(); i2++) {
            char charAt2 = str.charAt(i2);
            if (charAt2 == '\"') {
                stringBuffer.append("%22");
            } else if (charAt2 == '\'') {
                stringBuffer.append("%27");
            } else if (charAt2 == '<') {
                stringBuffer.append("%3C");
            } else if (charAt2 == '>') {
                stringBuffer.append("%3E");
            } else {
                stringBuffer.append(charAt2);
            }
        }
        return stringBuffer.toString();
    }

    private static String deTag(String str) {
        return StringUtil.replace(StringUtil.replace(str, "<", "&lt;"), ">", "&gt;");
    }

    public void writeTo(OutputStream outputStream, long j, long j2) throws IOException {
        InputStream inputStream = getInputStream();
        try {
            inputStream.skip(j);
            if (j2 < 0) {
                IO.copy(inputStream, outputStream);
            } else {
                IO.copy(inputStream, outputStream, j2);
            }
        } finally {
            inputStream.close();
        }
    }
}
