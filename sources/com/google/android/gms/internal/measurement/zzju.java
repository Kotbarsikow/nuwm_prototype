package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzjt;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
/* loaded from: classes.dex */
final class zzju implements zzkz {
    private static final zzju zza = new zzju();

    public static zzju zza() {
        return zza;
    }

    @Override // com.google.android.gms.internal.measurement.zzkz
    public final zzla zza(Class<?> cls) {
        if (!zzjt.class.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Unsupported message type: " + cls.getName());
        }
        try {
            return (zzla) zzjt.zza(cls.asSubclass(zzjt.class)).zza(zzjt.zze.zzc, (Object) null, (Object) null);
        } catch (Exception e) {
            throw new RuntimeException("Unable to get message info for " + cls.getName(), e);
        }
    }

    private zzju() {
    }

    @Override // com.google.android.gms.internal.measurement.zzkz
    public final boolean zzb(Class<?> cls) {
        return zzjt.class.isAssignableFrom(cls);
    }
}
