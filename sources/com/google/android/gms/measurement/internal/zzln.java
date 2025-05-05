package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes.dex */
final class zzln implements Runnable {
    private final /* synthetic */ long zza;
    private final /* synthetic */ zzlj zzb;

    zzln(zzlj zzljVar, long j) {
        this.zza = j;
        this.zzb = zzljVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zzc().zza(this.zza);
        this.zzb.zza = null;
    }
}
