package org.mortbay.jetty.servlet;

import androidx.exifinterface.media.ExifInterface;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mortbay.io.Buffer;
import org.mortbay.io.ByteArrayBuffer;
import org.mortbay.io.WriterOutputStream;
import org.mortbay.io.nio.DirectNIOBuffer;
import org.mortbay.io.nio.IndirectNIOBuffer;
import org.mortbay.jetty.HttpConnection;
import org.mortbay.jetty.HttpContent;
import org.mortbay.jetty.HttpFields;
import org.mortbay.jetty.HttpHeaderValues;
import org.mortbay.jetty.HttpHeaders;
import org.mortbay.jetty.InclusiveByteRange;
import org.mortbay.jetty.MimeTypes;
import org.mortbay.jetty.ResourceCache;
import org.mortbay.jetty.Response;
import org.mortbay.jetty.handler.ContextHandler;
import org.mortbay.jetty.nio.NIOConnector;
import org.mortbay.jetty.servlet.PathMap;
import org.mortbay.log.Log;
import org.mortbay.resource.FileResource;
import org.mortbay.resource.Resource;
import org.mortbay.resource.ResourceFactory;
import org.mortbay.util.IO;
import org.mortbay.util.MultiPartOutputStream;
import org.mortbay.util.TypeUtil;
import org.mortbay.util.URIUtil;

/* loaded from: classes3.dex */
public class DefaultServlet extends HttpServlet implements ResourceFactory {
    static /* synthetic */ Class class$org$mortbay$jetty$servlet$ServletHandler;
    private ResourceCache _bioCache;
    ByteArrayBuffer _cacheControl;
    private ContextHandler.SContext _context;
    private ServletHolder _defaultHolder;
    private MimeTypes _mimeTypes;
    private NIOResourceCache _nioCache;
    private Resource _resourceBase;
    private ServletHandler _servletHandler;
    private String[] _welcomes;
    private boolean _acceptRanges = true;
    private boolean _dirAllowed = true;
    private boolean _welcomeServlets = false;
    private boolean _redirectWelcome = false;
    private boolean _gzip = true;
    private boolean _aliases = false;
    private boolean _useFileMappedBuffer = false;

    @Override // javax.servlet.GenericServlet
    public void init() throws UnavailableException {
        ServletContext servletContext = getServletContext();
        ContextHandler.SContext sContext = (ContextHandler.SContext) servletContext;
        this._context = sContext;
        this._mimeTypes = sContext.getContextHandler().getMimeTypes();
        String[] welcomeFiles = this._context.getContextHandler().getWelcomeFiles();
        this._welcomes = welcomeFiles;
        if (welcomeFiles == null) {
            this._welcomes = new String[]{"index.jsp", "index.html"};
        }
        this._acceptRanges = getInitBoolean("acceptRanges", this._acceptRanges);
        this._dirAllowed = getInitBoolean("dirAllowed", this._dirAllowed);
        this._welcomeServlets = getInitBoolean("welcomeServlets", this._welcomeServlets);
        this._redirectWelcome = getInitBoolean("redirectWelcome", this._redirectWelcome);
        this._gzip = getInitBoolean(HttpHeaderValues.GZIP, this._gzip);
        boolean initBoolean = getInitBoolean("aliases", this._aliases);
        this._aliases = initBoolean;
        if (!initBoolean && !FileResource.getCheckAliases()) {
            throw new IllegalStateException("Alias checking disabled");
        }
        if (this._aliases) {
            servletContext.log("Aliases are enabled");
        }
        this._useFileMappedBuffer = getInitBoolean("useFileMappedBuffer", this._useFileMappedBuffer);
        String initParameter = getInitParameter("relativeResourceBase");
        if (initParameter != null) {
            try {
                Resource resource = this._context.getContextHandler().getResource(URIUtil.SLASH);
                if (resource == null) {
                    throw new UnavailableException(new StringBuffer("No base resourceBase for relativeResourceBase in").append(this._context.getContextPath()).toString());
                }
                this._resourceBase = resource.addPath(initParameter);
            } catch (Exception e) {
                Log.warn(Log.EXCEPTION, (Throwable) e);
                throw new UnavailableException(e.toString());
            }
        }
        String initParameter2 = getInitParameter("resourceBase");
        if (initParameter != null && initParameter2 != null) {
            throw new UnavailableException("resourceBase & relativeResourceBase");
        }
        if (initParameter2 != null) {
            try {
                this._resourceBase = Resource.newResource(initParameter2);
            } catch (Exception e2) {
                Log.warn(Log.EXCEPTION, (Throwable) e2);
                throw new UnavailableException(e2.toString());
            }
        }
        String initParameter3 = getInitParameter("cacheControl");
        if (initParameter3 != null) {
            this._cacheControl = new ByteArrayBuffer(initParameter3);
        }
        try {
            if (this._resourceBase == null) {
                this._resourceBase = this._context.getContextHandler().getResource(URIUtil.SLASH);
            }
            String initParameter4 = getInitParameter("cacheType");
            int initInt = getInitInt("maxCacheSize", -2);
            int initInt2 = getInitInt("maxCachedFileSize", -2);
            int initInt3 = getInitInt("maxCachedFiles", -2);
            if ((initParameter4 == null || "nio".equals(initParameter4) || "both".equals(initParameter4)) && (initInt == -2 || initInt > 0)) {
                NIOResourceCache nIOResourceCache = new NIOResourceCache(this._mimeTypes);
                this._nioCache = nIOResourceCache;
                if (initInt > 0) {
                    nIOResourceCache.setMaxCacheSize(initInt);
                }
                if (initInt2 >= -1) {
                    this._nioCache.setMaxCachedFileSize(initInt2);
                }
                if (initInt3 >= -1) {
                    this._nioCache.setMaxCachedFiles(initInt3);
                }
                this._nioCache.start();
            }
            if (("bio".equals(initParameter4) || "both".equals(initParameter4)) && (initInt == -2 || initInt > 0)) {
                ResourceCache resourceCache = new ResourceCache(this._mimeTypes);
                this._bioCache = resourceCache;
                if (initInt > 0) {
                    resourceCache.setMaxCacheSize(initInt);
                }
                if (initInt2 >= -1) {
                    this._bioCache.setMaxCachedFileSize(initInt2);
                }
                if (initInt3 >= -1) {
                    this._bioCache.setMaxCachedFiles(initInt3);
                }
                this._bioCache.start();
            }
            if (this._nioCache == null) {
                this._bioCache = null;
            }
            ContextHandler contextHandler = this._context.getContextHandler();
            Class cls = class$org$mortbay$jetty$servlet$ServletHandler;
            if (cls == null) {
                cls = class$("org.mortbay.jetty.servlet.ServletHandler");
                class$org$mortbay$jetty$servlet$ServletHandler = cls;
            }
            ServletHandler servletHandler = (ServletHandler) contextHandler.getChildHandlerByClass(cls);
            this._servletHandler = servletHandler;
            ServletHolder[] servlets = servletHandler.getServlets();
            int length = servlets.length;
            while (true) {
                int i = length - 1;
                if (length <= 0) {
                    break;
                }
                if (servlets[i].getServletInstance() == this) {
                    this._defaultHolder = servlets[i];
                }
                length = i;
            }
            if (Log.isDebugEnabled()) {
                Log.debug(new StringBuffer("resource base = ").append(this._resourceBase).toString());
            }
        } catch (Exception e3) {
            Log.warn(Log.EXCEPTION, (Throwable) e3);
            throw new UnavailableException(e3.toString());
        }
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    @Override // javax.servlet.GenericServlet, javax.servlet.ServletConfig
    public String getInitParameter(String str) {
        String initParameter = getServletContext().getInitParameter(new StringBuffer("org.mortbay.jetty.servlet.Default.").append(str).toString());
        return initParameter == null ? super.getInitParameter(str) : initParameter;
    }

    private boolean getInitBoolean(String str, boolean z) {
        String initParameter = getInitParameter(str);
        return (initParameter == null || initParameter.length() == 0) ? z : initParameter.startsWith("t") || initParameter.startsWith(ExifInterface.GPS_DIRECTION_TRUE) || initParameter.startsWith("y") || initParameter.startsWith("Y") || initParameter.startsWith("1");
    }

    private int getInitInt(String str, int i) {
        String initParameter = getInitParameter(str);
        if (initParameter == null) {
            initParameter = getInitParameter(str);
        }
        return (initParameter == null || initParameter.length() <= 0) ? i : Integer.parseInt(initParameter);
    }

    @Override // org.mortbay.resource.ResourceFactory
    public Resource getResource(String str) {
        Resource resource = this._resourceBase;
        Resource resource2 = null;
        if (resource == null) {
            return null;
        }
        try {
            Resource addPath = resource.addPath(str);
            try {
                if (!this._aliases && addPath.getAlias() != null) {
                    if (addPath.exists()) {
                        Log.warn(new StringBuffer("Aliased resource: ").append(addPath).append("==").append(addPath.getAlias()).toString());
                    }
                    return null;
                }
                if (!Log.isDebugEnabled()) {
                    return addPath;
                }
                Log.debug(new StringBuffer("RESOURCE=").append(addPath).toString());
                return addPath;
            } catch (IOException e) {
                e = e;
                resource2 = addPath;
                Log.ignore(e);
                return resource2;
            }
        } catch (IOException e2) {
            e = e2;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:169:0x00e4, code lost:
    
        if (r3.isDirectory() == false) goto L62;
     */
    /* JADX WARN: Removed duplicated region for block: B:100:0x02ea  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x02f0  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x01d2  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x008f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:165:0x00da A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:196:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x011c A[Catch: all -> 0x02c0, IllegalArgumentException -> 0x02c2, TryCatch #13 {IllegalArgumentException -> 0x02c2, all -> 0x02c0, blocks: (B:34:0x0116, B:36:0x011c, B:39:0x012c, B:42:0x0139, B:45:0x0141, B:49:0x014b, B:51:0x014f, B:53:0x0155, B:55:0x0164, B:57:0x016a, B:58:0x017f, B:68:0x0194, B:107:0x01d4, B:109:0x01da, B:112:0x01e4, B:114:0x01ea, B:116:0x01ee, B:118:0x01f7, B:120:0x01fd, B:121:0x0225, B:122:0x0238, B:124:0x023e, B:126:0x0244, B:127:0x0249, B:128:0x0252, B:145:0x0277, B:147:0x0285, B:148:0x028c, B:150:0x0292, B:152:0x0298, B:153:0x02a0, B:154:0x0289, B:155:0x02af), top: B:33:0x0116 }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x02b6  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x02ba  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x02d0 A[Catch: all -> 0x02e5, TRY_LEAVE, TryCatch #2 {all -> 0x02e5, blocks: (B:85:0x02c5, B:87:0x02d0), top: B:84:0x02c5 }] */
    /* JADX WARN: Removed duplicated region for block: B:90:0x02db  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x02df  */
    @Override // javax.servlet.http.HttpServlet
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void doGet(javax.servlet.http.HttpServletRequest r19, javax.servlet.http.HttpServletResponse r20) throws javax.servlet.ServletException, java.io.IOException {
        /*
            Method dump skipped, instructions count: 756
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mortbay.jetty.servlet.DefaultServlet.doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse):void");
    }

    @Override // javax.servlet.http.HttpServlet
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        doGet(httpServletRequest, httpServletResponse);
    }

    @Override // javax.servlet.http.HttpServlet
    protected void doTrace(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletResponse.sendError(405);
    }

    private String getWelcomeFile(String str) throws MalformedURLException, IOException {
        PathMap.Entry holderEntry;
        String str2 = null;
        if (this._welcomes == null) {
            return null;
        }
        int i = 0;
        while (true) {
            String[] strArr = this._welcomes;
            if (i >= strArr.length) {
                return str2;
            }
            String addPaths = URIUtil.addPaths(str, strArr[i]);
            Resource resource = getResource(addPaths);
            if (resource != null && resource.exists()) {
                return this._welcomes[i];
            }
            if (this._welcomeServlets && str2 == null && (holderEntry = this._servletHandler.getHolderEntry(addPaths)) != null && holderEntry.getValue() != this._defaultHolder) {
                str2 = addPaths;
            }
            i++;
        }
    }

    protected boolean passConditionalHeaders(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Resource resource, HttpContent httpContent) throws IOException {
        Buffer lastModified;
        try {
            if (httpServletRequest.getMethod().equals("HEAD")) {
                return true;
            }
            String header = httpServletRequest.getHeader("If-Modified-Since");
            if (header != null) {
                if (httpContent != null && (lastModified = httpContent.getLastModified()) != null && header.equals(lastModified.toString())) {
                    httpServletResponse.reset();
                    httpServletResponse.setStatus(304);
                    httpServletResponse.flushBuffer();
                    return false;
                }
                long dateHeader = httpServletRequest.getDateHeader("If-Modified-Since");
                if (dateHeader != -1 && resource.lastModified() / 1000 <= dateHeader / 1000) {
                    httpServletResponse.reset();
                    httpServletResponse.setStatus(304);
                    httpServletResponse.flushBuffer();
                    return false;
                }
            }
            long dateHeader2 = httpServletRequest.getDateHeader("If-Unmodified-Since");
            if (dateHeader2 == -1 || resource.lastModified() / 1000 <= dateHeader2 / 1000) {
                return true;
            }
            httpServletResponse.sendError(412);
            return false;
        } catch (IllegalArgumentException e) {
            if (!httpServletResponse.isCommitted()) {
                httpServletResponse.sendError(400, e.getMessage());
            }
            throw e;
        }
    }

    protected void sendDirectory(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Resource resource, boolean z) throws IOException {
        if (!this._dirAllowed) {
            httpServletResponse.sendError(403);
            return;
        }
        String listHTML = resource.getListHTML(URIUtil.addPaths(httpServletRequest.getRequestURI(), URIUtil.SLASH), z);
        if (listHTML == null) {
            httpServletResponse.sendError(403, "No directory");
            return;
        }
        byte[] bytes = listHTML.getBytes("UTF-8");
        httpServletResponse.setContentType("text/html; charset=UTF-8");
        httpServletResponse.setContentLength(bytes.length);
        httpServletResponse.getOutputStream().write(bytes);
    }

    protected void sendData(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, boolean z, Resource resource, HttpContent httpContent, Enumeration enumeration) throws IOException {
        OutputStream writerOutputStream;
        String str;
        long length = httpContent == null ? resource.length() : httpContent.getContentLength();
        try {
            writerOutputStream = httpServletResponse.getOutputStream();
        } catch (IllegalStateException unused) {
            writerOutputStream = new WriterOutputStream(httpServletResponse.getWriter());
        }
        if (enumeration == null || !enumeration.hasMoreElements() || length < 0) {
            if (z) {
                resource.writeTo(writerOutputStream, 0L, length);
                return;
            }
            if (writerOutputStream instanceof HttpConnection.Output) {
                if (httpServletResponse instanceof Response) {
                    writeOptionHeaders(((Response) httpServletResponse).getHttpFields());
                    ((HttpConnection.Output) writerOutputStream).sendContent(httpContent);
                    return;
                } else if (httpContent.getBuffer() != null) {
                    writeHeaders(httpServletResponse, httpContent, length);
                    ((HttpConnection.Output) writerOutputStream).sendContent(httpContent.getBuffer());
                    return;
                } else {
                    writeHeaders(httpServletResponse, httpContent, length);
                    resource.writeTo(writerOutputStream, 0L, length);
                    return;
                }
            }
            writeHeaders(httpServletResponse, httpContent, length);
            resource.writeTo(writerOutputStream, 0L, length);
            return;
        }
        List satisfiableRanges = InclusiveByteRange.satisfiableRanges(enumeration, length);
        if (satisfiableRanges == null || satisfiableRanges.size() == 0) {
            writeHeaders(httpServletResponse, httpContent, length);
            httpServletResponse.setStatus(416);
            httpServletResponse.setHeader("Content-Range", InclusiveByteRange.to416HeaderRangeString(length));
            resource.writeTo(writerOutputStream, 0L, length);
            return;
        }
        if (satisfiableRanges.size() == 1) {
            InclusiveByteRange inclusiveByteRange = (InclusiveByteRange) satisfiableRanges.get(0);
            long size = inclusiveByteRange.getSize(length);
            writeHeaders(httpServletResponse, httpContent, size);
            httpServletResponse.setStatus(206);
            httpServletResponse.setHeader("Content-Range", inclusiveByteRange.toHeaderRangeString(length));
            resource.writeTo(writerOutputStream, inclusiveByteRange.getFirst(length), size);
            return;
        }
        writeHeaders(httpServletResponse, httpContent, -1L);
        String obj = httpContent.getContentType().toString();
        MultiPartOutputStream multiPartOutputStream = new MultiPartOutputStream(writerOutputStream);
        httpServletResponse.setStatus(206);
        if (httpServletRequest.getHeader(HttpHeaders.REQUEST_RANGE) != null) {
            str = "multipart/x-byteranges; boundary=";
        } else {
            str = "multipart/byteranges; boundary=";
        }
        httpServletResponse.setContentType(new StringBuffer().append(str).append(multiPartOutputStream.getBoundary()).toString());
        InputStream inputStream = resource.getInputStream();
        String[] strArr = new String[satisfiableRanges.size()];
        int i = 0;
        int i2 = 0;
        while (i < satisfiableRanges.size()) {
            InclusiveByteRange inclusiveByteRange2 = (InclusiveByteRange) satisfiableRanges.get(i);
            strArr[i] = inclusiveByteRange2.toHeaderRangeString(length);
            i2 = (int) (i2 + (i > 0 ? 2 : 0) + 2 + multiPartOutputStream.getBoundary().length() + 16 + obj.length() + 17 + strArr[i].length() + 4 + (inclusiveByteRange2.getLast(length) - inclusiveByteRange2.getFirst(length)) + 1);
            i++;
        }
        httpServletResponse.setContentLength(i2 + multiPartOutputStream.getBoundary().length() + 8);
        long j = 0;
        for (int i3 = 0; i3 < satisfiableRanges.size(); i3++) {
            InclusiveByteRange inclusiveByteRange3 = (InclusiveByteRange) satisfiableRanges.get(i3);
            multiPartOutputStream.startPart(obj, new String[]{new StringBuffer("Content-Range: ").append(strArr[i3]).toString()});
            long first = inclusiveByteRange3.getFirst(length);
            long size2 = inclusiveByteRange3.getSize(length);
            if (inputStream != null) {
                if (first < j) {
                    inputStream.close();
                    inputStream = resource.getInputStream();
                    j = 0;
                }
                if (j < first) {
                    inputStream.skip(first - j);
                } else {
                    first = j;
                }
                IO.copy(inputStream, multiPartOutputStream, size2);
                j = first + size2;
            } else {
                resource.writeTo(multiPartOutputStream, first, size2);
            }
        }
        if (inputStream != null) {
            inputStream.close();
        }
        multiPartOutputStream.close();
    }

    protected void writeHeaders(HttpServletResponse httpServletResponse, HttpContent httpContent, long j) throws IOException {
        if (httpContent.getContentType() != null && httpServletResponse.getContentType() == null) {
            httpServletResponse.setContentType(httpContent.getContentType().toString());
        }
        if (httpServletResponse instanceof Response) {
            Response response = (Response) httpServletResponse;
            HttpFields httpFields = response.getHttpFields();
            if (httpContent.getLastModified() != null) {
                httpFields.put(HttpHeaders.LAST_MODIFIED_BUFFER, httpContent.getLastModified(), httpContent.getResource().lastModified());
            } else if (httpContent.getResource() != null) {
                long lastModified = httpContent.getResource().lastModified();
                if (lastModified != -1) {
                    httpFields.putDateField(HttpHeaders.LAST_MODIFIED_BUFFER, lastModified);
                }
            }
            if (j != -1) {
                response.setLongContentLength(j);
            }
            writeOptionHeaders(httpFields);
            return;
        }
        long lastModified2 = httpContent.getResource().lastModified();
        if (lastModified2 >= 0) {
            httpServletResponse.setDateHeader("Last-Modified", lastModified2);
        }
        if (j != -1) {
            if (j < 2147483647L) {
                httpServletResponse.setContentLength((int) j);
            } else {
                httpServletResponse.setHeader("Content-Length", TypeUtil.toString(j));
            }
        }
        writeOptionHeaders(httpServletResponse);
    }

    protected void writeOptionHeaders(HttpFields httpFields) throws IOException {
        if (this._acceptRanges) {
            httpFields.put(HttpHeaders.ACCEPT_RANGES_BUFFER, HttpHeaderValues.BYTES_BUFFER);
        }
        if (this._cacheControl != null) {
            httpFields.put(HttpHeaders.CACHE_CONTROL_BUFFER, this._cacheControl);
        }
    }

    protected void writeOptionHeaders(HttpServletResponse httpServletResponse) throws IOException {
        if (this._acceptRanges) {
            httpServletResponse.setHeader("Accept-Ranges", HttpHeaderValues.BYTES);
        }
        ByteArrayBuffer byteArrayBuffer = this._cacheControl;
        if (byteArrayBuffer != null) {
            httpServletResponse.setHeader("Cache-Control", byteArrayBuffer.toString());
        }
    }

    @Override // javax.servlet.GenericServlet, javax.servlet.Servlet
    public void destroy() {
        try {
            try {
                NIOResourceCache nIOResourceCache = this._nioCache;
                if (nIOResourceCache != null) {
                    nIOResourceCache.stop();
                }
            } catch (Exception e) {
                Log.warn(Log.EXCEPTION, (Throwable) e);
                try {
                    try {
                        ResourceCache resourceCache = this._bioCache;
                        if (resourceCache != null) {
                            resourceCache.stop();
                        }
                    } finally {
                    }
                } catch (Exception e2) {
                    Log.warn(Log.EXCEPTION, (Throwable) e2);
                }
            }
            try {
                try {
                    ResourceCache resourceCache2 = this._bioCache;
                    if (resourceCache2 != null) {
                        resourceCache2.stop();
                    }
                } catch (Exception e3) {
                    Log.warn(Log.EXCEPTION, (Throwable) e3);
                }
            } finally {
            }
        } catch (Throwable th) {
            try {
                try {
                    ResourceCache resourceCache3 = this._bioCache;
                    if (resourceCache3 != null) {
                        resourceCache3.stop();
                    }
                } catch (Exception e4) {
                    Log.warn(Log.EXCEPTION, (Throwable) e4);
                }
                throw th;
            } finally {
            }
        }
    }

    private class UnCachedContent implements HttpContent {
        Resource _resource;

        @Override // org.mortbay.jetty.HttpContent
        public Buffer getBuffer() {
            return null;
        }

        @Override // org.mortbay.jetty.HttpContent
        public Buffer getLastModified() {
            return null;
        }

        UnCachedContent(Resource resource) {
            this._resource = resource;
        }

        @Override // org.mortbay.jetty.HttpContent
        public Buffer getContentType() {
            return DefaultServlet.this._mimeTypes.getMimeByExtension(this._resource.toString());
        }

        @Override // org.mortbay.jetty.HttpContent
        public long getContentLength() {
            return this._resource.length();
        }

        @Override // org.mortbay.jetty.HttpContent
        public InputStream getInputStream() throws IOException {
            return this._resource.getInputStream();
        }

        @Override // org.mortbay.jetty.HttpContent
        public Resource getResource() {
            return this._resource;
        }

        @Override // org.mortbay.jetty.HttpContent
        public void release() {
            this._resource.release();
            this._resource = null;
        }
    }

    class NIOResourceCache extends ResourceCache {
        public NIOResourceCache(MimeTypes mimeTypes) {
            super(mimeTypes);
        }

        @Override // org.mortbay.jetty.ResourceCache
        protected void fill(ResourceCache.Content content) throws IOException {
            Buffer indirectNIOBuffer;
            Buffer buffer;
            Resource resource = content.getResource();
            long length = resource.length();
            if (DefaultServlet.this._useFileMappedBuffer && resource.getFile() != null) {
                buffer = new DirectNIOBuffer(resource.getFile());
            } else {
                InputStream inputStream = resource.getInputStream();
                try {
                    indirectNIOBuffer = ((NIOConnector) HttpConnection.getCurrentConnection().getConnector()).getUseDirectBuffers() ? new DirectNIOBuffer((int) length) : new IndirectNIOBuffer((int) length);
                } catch (OutOfMemoryError e) {
                    Log.warn(e.toString());
                    Log.debug(e);
                    indirectNIOBuffer = new IndirectNIOBuffer((int) length);
                }
                indirectNIOBuffer.readFrom(inputStream, (int) length);
                inputStream.close();
                buffer = indirectNIOBuffer;
            }
            content.setBuffer(buffer);
        }
    }
}
