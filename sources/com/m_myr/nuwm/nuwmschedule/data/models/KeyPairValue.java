package com.m_myr.nuwm.nuwmschedule.data.models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: classes2.dex */
public final class KeyPairValue<T> implements Serializable {

    @SerializedName("key")
    final String key;

    @SerializedName("value")
    final T value;

    public String getKey() {
        return this.key;
    }

    public T getValue() {
        return this.value;
    }

    public KeyPairValue(String key, T value) {
        this.key = key;
        this.value = value;
    }
}
