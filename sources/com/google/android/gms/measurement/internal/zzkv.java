package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes.dex */
final class zzkv implements Runnable {
    private final /* synthetic */ Boolean zza;
    private final /* synthetic */ zzjq zzb;

    zzkv(zzjq zzjqVar, Boolean bool) {
        this.zza = bool;
        this.zzb = zzjqVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zza(this.zza, true);
    }
}
