package com.google.android.gms.internal.measurement;

import com.github.mikephil.charting.utils.Utils;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
/* loaded from: classes.dex */
public enum zzkd {
    VOID(Void.class, Void.class, null),
    INT(Integer.TYPE, Integer.class, 0),
    LONG(Long.TYPE, Long.class, 0L),
    FLOAT(Float.TYPE, Float.class, Float.valueOf(0.0f)),
    DOUBLE(Double.TYPE, Double.class, Double.valueOf(Utils.DOUBLE_EPSILON)),
    BOOLEAN(Boolean.TYPE, Boolean.class, false),
    STRING(String.class, String.class, ""),
    BYTE_STRING(zzik.class, zzik.class, zzik.zza),
    ENUM(Integer.TYPE, Integer.class, null),
    MESSAGE(Object.class, Object.class, null);

    private final Class<?> zzl;

    public final Class<?> zza() {
        return this.zzl;
    }

    zzkd(Class cls, Class cls2, Object obj) {
        this.zzl = cls2;
    }
}
