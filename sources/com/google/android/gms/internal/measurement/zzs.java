package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
/* loaded from: classes.dex */
public enum zzs {
    DEBUG(3),
    ERROR(6),
    INFO(4),
    VERBOSE(2),
    WARN(5);

    public static zzs zza(int i) {
        return i != 2 ? i != 3 ? i != 5 ? i != 6 ? INFO : ERROR : WARN : DEBUG : VERBOSE;
    }

    zzs(int i) {
    }
}
