package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-sdk@@22.1.2 */
/* loaded from: classes.dex */
final class zzn implements Runnable {
    private final /* synthetic */ com.google.android.gms.internal.measurement.zzdo zza;
    private final /* synthetic */ AppMeasurementDynamiteService zzb;

    zzn(AppMeasurementDynamiteService appMeasurementDynamiteService, com.google.android.gms.internal.measurement.zzdo zzdoVar) {
        this.zza = zzdoVar;
        this.zzb = appMeasurementDynamiteService;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zza.zzt().zza(this.zza, this.zzb.zza.zzab());
    }
}
