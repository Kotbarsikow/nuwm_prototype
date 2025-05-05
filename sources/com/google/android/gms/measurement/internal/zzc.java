package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes.dex */
final class zzc implements Runnable {
    private final /* synthetic */ long zza;
    private final /* synthetic */ zzb zzb;

    zzc(zzb zzbVar, long j) {
        this.zza = j;
        this.zzb = zzbVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zzb(this.zza);
    }
}
