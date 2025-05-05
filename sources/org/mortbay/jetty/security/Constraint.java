package org.mortbay.jetty.security;

import java.io.Serializable;
import java.util.Arrays;

/* loaded from: classes3.dex */
public class Constraint implements Cloneable, Serializable {
    public static final String ANY_ROLE = "*";
    public static final int DC_CONFIDENTIAL = 2;
    public static final int DC_INTEGRAL = 1;
    public static final int DC_NONE = 0;
    public static final int DC_UNSET = -1;
    public static final String NONE = "NONE";
    public static final String __BASIC_AUTH = "BASIC";
    public static final String __CERT_AUTH = "CLIENT_CERT";
    public static final String __CERT_AUTH2 = "CLIENT-CERT";
    public static final String __DIGEST_AUTH = "DIGEST";
    public static final String __FORM_AUTH = "FORM";
    private String _name;
    private String[] _roles;
    private int _dataConstraint = -1;
    private boolean _anyRole = false;
    private boolean _authenticate = false;

    public Constraint() {
    }

    public Constraint(String str, String str2) {
        setName(str);
        setRoles(new String[]{str2});
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void setName(String str) {
        this._name = str;
    }

    public void setRoles(String[] strArr) {
        this._roles = strArr;
        this._anyRole = false;
        if (strArr != null) {
            int length = strArr.length;
            while (!this._anyRole) {
                int i = length - 1;
                if (length <= 0) {
                    return;
                }
                this._anyRole = ANY_ROLE.equals(strArr[i]);
                length = i;
            }
        }
    }

    public boolean isAnyRole() {
        return this._anyRole;
    }

    public String[] getRoles() {
        return this._roles;
    }

    public boolean hasRole(String str) {
        if (this._anyRole) {
            return true;
        }
        String[] strArr = this._roles;
        if (strArr == null) {
            return false;
        }
        int length = strArr.length;
        while (true) {
            int i = length - 1;
            if (length <= 0) {
                return false;
            }
            if (str.equals(this._roles[i])) {
                return true;
            }
            length = i;
        }
    }

    public void setAuthenticate(boolean z) {
        this._authenticate = z;
    }

    public boolean getAuthenticate() {
        return this._authenticate;
    }

    public boolean isForbidden() {
        String[] strArr;
        return this._authenticate && !this._anyRole && ((strArr = this._roles) == null || strArr.length == 0);
    }

    public void setDataConstraint(int i) {
        if (i < 0 || i > 2) {
            throw new IllegalArgumentException("Constraint out of range");
        }
        this._dataConstraint = i;
    }

    public int getDataConstraint() {
        return this._dataConstraint;
    }

    public boolean hasDataConstraint() {
        return this._dataConstraint >= 0;
    }

    public String toString() {
        String obj;
        StringBuffer append = new StringBuffer("SC{").append(this._name).append(",");
        if (this._anyRole) {
            obj = ANY_ROLE;
        } else {
            String[] strArr = this._roles;
            obj = strArr == null ? "-" : Arrays.asList(strArr).toString();
        }
        StringBuffer append2 = append.append(obj).append(",");
        int i = this._dataConstraint;
        return append2.append(i == -1 ? "DC_UNSET}" : i == 0 ? "NONE}" : i == 1 ? "INTEGRAL}" : "CONFIDENTIAL}").toString();
    }
}
