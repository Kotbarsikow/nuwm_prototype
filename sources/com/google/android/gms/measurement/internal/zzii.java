package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
/* loaded from: classes.dex */
final class zzii implements Runnable {
    private final /* synthetic */ zzo zza;
    private final /* synthetic */ zzic zzb;

    zzii(zzic zzicVar, zzo zzoVar) {
        this.zza = zzoVar;
        this.zzb = zzicVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zznv zznvVar;
        zznv zznvVar2;
        zznvVar = this.zzb.zza;
        zznvVar.zzr();
        zznvVar2 = this.zzb.zza;
        zznvVar2.zzd(this.zza);
    }
}
