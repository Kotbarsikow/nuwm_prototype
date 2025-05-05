package org.mortbay.jetty.handler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mortbay.jetty.Request;
import org.mortbay.jetty.servlet.PathMap;
import org.mortbay.util.URIUtil;

/* loaded from: classes3.dex */
public class RewriteHandler extends HandlerWrapper {
    private String _originalPathAttribute;
    private boolean _rewriteRequestURI = true;
    private boolean _rewritePathInfo = true;
    private PathMap _rewrite = new PathMap(true);

    public boolean isRewriteRequestURI() {
        return this._rewriteRequestURI;
    }

    public void setRewriteRequestURI(boolean z) {
        this._rewriteRequestURI = z;
    }

    public boolean isRewritePathInfo() {
        return this._rewritePathInfo;
    }

    public void setRewritePathInfo(boolean z) {
        this._rewritePathInfo = z;
    }

    public String getOriginalPathAttribute() {
        return this._originalPathAttribute;
    }

    public void setOriginalPathAttribute(String str) {
        this._originalPathAttribute = str;
    }

    public PathMap getRewrite() {
        return this._rewrite;
    }

    public void setRewrite(PathMap pathMap) {
        this._rewrite = pathMap;
    }

    public void addRewriteRule(String str, String str2) {
        if (str == null || str.length() == 0 || !str.startsWith(URIUtil.SLASH)) {
            throw new IllegalArgumentException();
        }
        if (this._rewrite == null) {
            this._rewrite = new PathMap(true);
        }
        this._rewrite.put(str, str2);
    }

    @Override // org.mortbay.jetty.handler.HandlerWrapper, org.mortbay.jetty.Handler
    public void handle(String str, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, int i) throws IOException, ServletException {
        PathMap pathMap;
        PathMap.Entry match;
        if (isStarted() && (pathMap = this._rewrite) != null && (match = pathMap.getMatch(str)) != null && match.getValue() != null) {
            String str2 = this._originalPathAttribute;
            if (str2 != null) {
                httpServletRequest.setAttribute(str2, str);
            }
            str = URIUtil.addPaths(match.getValue().toString(), PathMap.pathInfo(match.getKey().toString(), str));
            if (this._rewriteRequestURI) {
                ((Request) httpServletRequest).setRequestURI(str);
            }
            if (this._rewritePathInfo) {
                ((Request) httpServletRequest).setPathInfo(str);
            }
        }
        super.handle(str, httpServletRequest, httpServletResponse, i);
    }
}
