package com.m_myr.nuwm.nuwmschedule.network.api;

/* loaded from: classes2.dex */
public class PreparedObject<V> {
    private final V object;
    private final Object serializable;

    public PreparedObject(V object, Object serializable) {
        this.object = object;
        this.serializable = serializable;
    }

    public V getData() {
        return this.object;
    }

    public Object getOriginal() {
        return this.serializable;
    }
}
