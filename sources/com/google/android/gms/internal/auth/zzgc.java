package com.google.android.gms.internal.auth;

/* compiled from: com.google.android.gms:play-services-auth-base@@18.0.4 */
/* loaded from: classes.dex */
final class zzgc {
    private static final zzgb zza;
    private static final zzgb zzb;

    static {
        zzgb zzgbVar = null;
        try {
            zzgbVar = (zzgb) Class.forName("com.google.protobuf.NewInstanceSchemaFull").getDeclaredConstructor(null).newInstance(null);
        } catch (Exception unused) {
        }
        zza = zzgbVar;
        zzb = new zzgb();
    }

    static zzgb zza() {
        return zza;
    }

    static zzgb zzb() {
        return zzb;
    }
}
