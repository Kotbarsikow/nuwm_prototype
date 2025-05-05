package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
/* loaded from: classes.dex */
final class zziz implements Runnable {
    private final /* synthetic */ zzon zza;
    private final /* synthetic */ zzo zzb;
    private final /* synthetic */ zzic zzc;

    zziz(zzic zzicVar, zzon zzonVar, zzo zzoVar) {
        this.zza = zzonVar;
        this.zzb = zzoVar;
        this.zzc = zzicVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zznv zznvVar;
        zznv zznvVar2;
        zznv zznvVar3;
        zznvVar = this.zzc.zza;
        zznvVar.zzr();
        if (this.zza.zza() == null) {
            zznvVar3 = this.zzc.zza;
            zznvVar3.zza(this.zza.zza, this.zzb);
        } else {
            zznvVar2 = this.zzc.zza;
            zznvVar2.zza(this.zza, this.zzb);
        }
    }
}
