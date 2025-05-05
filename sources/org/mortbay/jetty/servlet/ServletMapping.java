package org.mortbay.jetty.servlet;

import java.util.Arrays;

/* loaded from: classes3.dex */
public class ServletMapping {
    private String[] _pathSpecs;
    private String _servletName;

    public String[] getPathSpecs() {
        return this._pathSpecs;
    }

    public String getServletName() {
        return this._servletName;
    }

    public void setPathSpecs(String[] strArr) {
        this._pathSpecs = strArr;
    }

    public void setPathSpec(String str) {
        this._pathSpecs = new String[]{str};
    }

    public void setServletName(String str) {
        this._servletName = str;
    }

    public String toString() {
        StringBuffer append = new StringBuffer("(S=").append(this._servletName).append(",");
        String[] strArr = this._pathSpecs;
        return append.append(strArr == null ? "[]" : Arrays.asList(strArr).toString()).append(")").toString();
    }
}
