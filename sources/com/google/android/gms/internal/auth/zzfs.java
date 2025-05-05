package com.google.android.gms.internal.auth;

/* compiled from: com.google.android.gms:play-services-auth-base@@18.0.4 */
/* loaded from: classes.dex */
final class zzfs {
    private static final zzfr zza;
    private static final zzfr zzb;

    static {
        zzfr zzfrVar = null;
        try {
            zzfrVar = (zzfr) Class.forName("com.google.protobuf.MapFieldSchemaFull").getDeclaredConstructor(null).newInstance(null);
        } catch (Exception unused) {
        }
        zza = zzfrVar;
        zzb = new zzfr();
    }

    static zzfr zza() {
        return zza;
    }

    static zzfr zzb() {
        return zzb;
    }
}
