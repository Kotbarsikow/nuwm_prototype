package org.mortbay.jetty.servlet;

import java.util.Arrays;

/* loaded from: classes3.dex */
public class FilterMapping {
    private int _dispatches = 1;
    private String _filterName;
    private transient FilterHolder _holder;
    private String[] _pathSpecs;
    private String[] _servletNames;

    boolean appliesTo(String str, int i) {
        int i2 = this._dispatches;
        if (((i2 & i) != 0 || (i2 == 0 && i == 1)) && this._pathSpecs != null) {
            int i3 = 0;
            while (true) {
                String[] strArr = this._pathSpecs;
                if (i3 >= strArr.length) {
                    break;
                }
                String str2 = strArr[i3];
                if (str2 != null && PathMap.match(str2, str, true)) {
                    return true;
                }
                i3++;
            }
        }
        return false;
    }

    boolean appliesTo(int i) {
        int i2 = this._dispatches;
        return (i2 & i) != 0 || (i2 == 0 && i == 1);
    }

    public int getDispatches() {
        return this._dispatches;
    }

    public String getFilterName() {
        return this._filterName;
    }

    FilterHolder getFilterHolder() {
        return this._holder;
    }

    public String[] getPathSpecs() {
        return this._pathSpecs;
    }

    public void setDispatches(int i) {
        this._dispatches = i;
    }

    public void setFilterName(String str) {
        this._filterName = str;
    }

    void setFilterHolder(FilterHolder filterHolder) {
        this._holder = filterHolder;
    }

    public void setPathSpecs(String[] strArr) {
        this._pathSpecs = strArr;
    }

    public void setPathSpec(String str) {
        this._pathSpecs = new String[]{str};
    }

    public String[] getServletNames() {
        return this._servletNames;
    }

    public void setServletNames(String[] strArr) {
        this._servletNames = strArr;
    }

    public void setServletName(String str) {
        this._servletNames = new String[]{str};
    }

    public String toString() {
        StringBuffer append = new StringBuffer("(F=").append(this._filterName).append(",");
        String[] strArr = this._pathSpecs;
        StringBuffer append2 = append.append(strArr == null ? "[]" : Arrays.asList(strArr).toString()).append(",");
        String[] strArr2 = this._servletNames;
        return append2.append(strArr2 != null ? Arrays.asList(strArr2).toString() : "[]").append(",").append(this._dispatches).append(")").toString();
    }
}
