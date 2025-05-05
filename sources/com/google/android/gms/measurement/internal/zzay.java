package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes.dex */
final class zzay implements Runnable {
    private final /* synthetic */ zzjc zza;
    private final /* synthetic */ zzav zzb;

    zzay(zzav zzavVar, zzjc zzjcVar) {
        this.zza = zzjcVar;
        this.zzb = zzavVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zzd();
        if (zzab.zza()) {
            this.zza.zzl().zzb(this);
            return;
        }
        boolean zzc = this.zzb.zzc();
        this.zzb.zzd = 0L;
        if (zzc) {
            this.zzb.zzb();
        }
    }
}
