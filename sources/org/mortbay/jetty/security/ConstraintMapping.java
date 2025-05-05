package org.mortbay.jetty.security;

/* loaded from: classes3.dex */
public class ConstraintMapping {
    Constraint constraint;
    String method;
    String pathSpec;

    public Constraint getConstraint() {
        return this.constraint;
    }

    public void setConstraint(Constraint constraint) {
        this.constraint = constraint;
    }

    public String getMethod() {
        return this.method;
    }

    public void setMethod(String str) {
        this.method = str;
    }

    public String getPathSpec() {
        return this.pathSpec;
    }

    public void setPathSpec(String str) {
        this.pathSpec = str;
    }
}
