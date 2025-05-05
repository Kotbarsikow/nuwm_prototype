package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes.dex */
public enum zzjh {
    UNINITIALIZED("uninitialized"),
    POLICY("eu_consent_policy"),
    DENIED("denied"),
    GRANTED("granted");

    private final String zzf;

    @Override // java.lang.Enum
    public final String toString() {
        return this.zzf;
    }

    zzjh(String str) {
        this.zzf = str;
    }
}
