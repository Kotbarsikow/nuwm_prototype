package org.mortbay.jetty.handler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mortbay.io.Buffer;
import org.mortbay.io.ByteArrayBuffer;
import org.mortbay.io.WriterOutputStream;
import org.mortbay.jetty.HttpConnection;
import org.mortbay.jetty.HttpFields;
import org.mortbay.jetty.HttpHeaders;
import org.mortbay.jetty.MimeTypes;
import org.mortbay.jetty.Request;
import org.mortbay.jetty.Response;
import org.mortbay.jetty.handler.ContextHandler;
import org.mortbay.log.Log;
import org.mortbay.resource.FileResource;
import org.mortbay.resource.Resource;
import org.mortbay.util.TypeUtil;
import org.mortbay.util.URIUtil;

/* loaded from: classes3.dex */
public class ResourceHandler extends AbstractHandler {
    boolean _aliases;
    Resource _baseResource;
    ByteArrayBuffer _cacheControl;
    ContextHandler _context;
    String[] _welcomeFiles = {"index.html"};
    MimeTypes _mimeTypes = new MimeTypes();

    public MimeTypes getMimeTypes() {
        return this._mimeTypes;
    }

    public void setMimeTypes(MimeTypes mimeTypes) {
        this._mimeTypes = mimeTypes;
    }

    public boolean isAliases() {
        return this._aliases;
    }

    public void setAliases(boolean z) {
        this._aliases = z;
    }

    @Override // org.mortbay.jetty.handler.AbstractHandler, org.mortbay.component.AbstractLifeCycle
    public void doStart() throws Exception {
        ContextHandler.SContext currentContext = ContextHandler.getCurrentContext();
        this._context = currentContext == null ? null : currentContext.getContextHandler();
        if (!this._aliases && !FileResource.getCheckAliases()) {
            throw new IllegalStateException("Alias checking disabled");
        }
        super.doStart();
    }

    public Resource getBaseResource() {
        Resource resource = this._baseResource;
        if (resource == null) {
            return null;
        }
        return resource;
    }

    public String getResourceBase() {
        Resource resource = this._baseResource;
        if (resource == null) {
            return null;
        }
        return resource.toString();
    }

    public void setBaseResource(Resource resource) {
        this._baseResource = resource;
    }

    public void setResourceBase(String str) {
        try {
            setBaseResource(Resource.newResource(str));
        } catch (Exception e) {
            Log.warn(e.toString());
            Log.debug(e);
            throw new IllegalArgumentException(str);
        }
    }

    public String getCacheControl() {
        return this._cacheControl.toString();
    }

    public void setCacheControl(String str) {
        this._cacheControl = str == null ? null : new ByteArrayBuffer(str);
    }

    public Resource getResource(String str) throws MalformedURLException {
        ContextHandler contextHandler;
        if (str == null || !str.startsWith(URIUtil.SLASH)) {
            throw new MalformedURLException(str);
        }
        Resource resource = this._baseResource;
        if (resource == null && ((contextHandler = this._context) == null || (resource = contextHandler.getBaseResource()) == null)) {
            return null;
        }
        try {
            return resource.addPath(URIUtil.canonicalPath(str));
        } catch (Exception e) {
            Log.ignore(e);
            return null;
        }
    }

    protected Resource getResource(HttpServletRequest httpServletRequest) throws MalformedURLException {
        String pathInfo = httpServletRequest.getPathInfo();
        if (pathInfo == null) {
            return null;
        }
        return getResource(pathInfo);
    }

    public String[] getWelcomeFiles() {
        return this._welcomeFiles;
    }

    public void setWelcomeFiles(String[] strArr) {
        this._welcomeFiles = strArr;
    }

    protected Resource getWelcome(Resource resource) throws MalformedURLException, IOException {
        int i = 0;
        while (true) {
            String[] strArr = this._welcomeFiles;
            if (i >= strArr.length) {
                return null;
            }
            Resource addPath = resource.addPath(strArr[i]);
            if (addPath.exists() && !addPath.isDirectory()) {
                return addPath;
            }
            i++;
        }
    }

    @Override // org.mortbay.jetty.Handler
    public void handle(String str, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, int i) throws IOException, ServletException {
        boolean z;
        OutputStream writerOutputStream;
        Request request = httpServletRequest instanceof Request ? (Request) httpServletRequest : HttpConnection.getCurrentConnection().getRequest();
        if (request.isHandled()) {
            return;
        }
        if ("GET".equals(httpServletRequest.getMethod())) {
            z = false;
        } else if (!"HEAD".equals(httpServletRequest.getMethod())) {
            return;
        } else {
            z = true;
        }
        Resource resource = getResource(httpServletRequest);
        if (resource == null || !resource.exists()) {
            return;
        }
        if (!this._aliases && resource.getAlias() != null) {
            Log.info(new StringBuffer().append(resource).append(" aliased to ").append(resource.getAlias()).toString());
            return;
        }
        request.setHandled(true);
        if (resource.isDirectory()) {
            if (!httpServletRequest.getPathInfo().endsWith(URIUtil.SLASH)) {
                httpServletResponse.sendRedirect(httpServletResponse.encodeRedirectURL(URIUtil.addPaths(httpServletRequest.getRequestURI(), URIUtil.SLASH)));
                return;
            }
            resource = getWelcome(resource);
            if (resource == null || !resource.exists() || resource.isDirectory()) {
                httpServletResponse.sendError(403);
                return;
            }
        }
        Resource resource2 = resource;
        long lastModified = resource2.lastModified();
        if (lastModified > 0) {
            long dateHeader = httpServletRequest.getDateHeader("If-Modified-Since");
            if (dateHeader > 0 && lastModified / 1000 <= dateHeader / 1000) {
                httpServletResponse.setStatus(304);
                return;
            }
        }
        Buffer mimeByExtension = this._mimeTypes.getMimeByExtension(resource2.toString());
        if (mimeByExtension == null) {
            mimeByExtension = this._mimeTypes.getMimeByExtension(httpServletRequest.getPathInfo());
        }
        doResponseHeaders(httpServletResponse, resource2, mimeByExtension != null ? mimeByExtension.toString() : null);
        httpServletResponse.setDateHeader("Last-Modified", lastModified);
        if (z) {
            return;
        }
        try {
            writerOutputStream = httpServletResponse.getOutputStream();
        } catch (IllegalStateException unused) {
            writerOutputStream = new WriterOutputStream(httpServletResponse.getWriter());
        }
        OutputStream outputStream = writerOutputStream;
        if (outputStream instanceof HttpConnection.Output) {
            ((HttpConnection.Output) outputStream).sendContent(resource2.getInputStream());
        } else {
            resource2.writeTo(outputStream, 0L, resource2.length());
        }
    }

    protected void doResponseHeaders(HttpServletResponse httpServletResponse, Resource resource, String str) {
        if (str != null) {
            httpServletResponse.setContentType(str);
        }
        long length = resource.length();
        if (httpServletResponse instanceof Response) {
            HttpFields httpFields = ((Response) httpServletResponse).getHttpFields();
            if (length > 0) {
                httpFields.putLongField(HttpHeaders.CONTENT_LENGTH_BUFFER, length);
            }
            if (this._cacheControl != null) {
                httpFields.put(HttpHeaders.CACHE_CONTROL_BUFFER, this._cacheControl);
                return;
            }
            return;
        }
        if (length > 0) {
            httpServletResponse.setHeader("Content-Length", TypeUtil.toString(length));
        }
        ByteArrayBuffer byteArrayBuffer = this._cacheControl;
        if (byteArrayBuffer != null) {
            httpServletResponse.setHeader("Cache-Control", byteArrayBuffer.toString());
        }
    }
}
