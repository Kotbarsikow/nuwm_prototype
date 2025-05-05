package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes.dex */
public final class zzgl {
    private static zzgk zza;

    public static synchronized zzgk zza() {
        zzgk zzgkVar;
        synchronized (zzgl.class) {
            if (zza == null) {
                zza(new zzgn());
            }
            zzgkVar = zza;
        }
        return zzgkVar;
    }

    private static synchronized void zza(zzgk zzgkVar) {
        synchronized (zzgl.class) {
            if (zza != null) {
                throw new IllegalStateException("init() already called");
            }
            zza = zzgkVar;
        }
    }
}
