package com.fasterxml.jackson.core.sym;

/* loaded from: classes.dex */
public abstract class Name {
    protected final int _hashCode;
    protected final String _name;

    public abstract boolean equals(int i);

    public abstract boolean equals(int i, int i2);

    public boolean equals(Object obj) {
        return obj == this;
    }

    public abstract boolean equals(int[] iArr, int i);

    protected Name(String str, int i) {
        this._name = str;
        this._hashCode = i;
    }

    public String getName() {
        return this._name;
    }

    public String toString() {
        return this._name;
    }

    public final int hashCode() {
        return this._hashCode;
    }
}
