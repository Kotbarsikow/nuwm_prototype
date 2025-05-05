package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-sdk@@22.1.2 */
/* loaded from: classes.dex */
final class zzk implements Runnable {
    private final /* synthetic */ com.google.android.gms.internal.measurement.zzdo zza;
    private final /* synthetic */ zzbf zzb;
    private final /* synthetic */ String zzc;
    private final /* synthetic */ AppMeasurementDynamiteService zzd;

    zzk(AppMeasurementDynamiteService appMeasurementDynamiteService, com.google.android.gms.internal.measurement.zzdo zzdoVar, zzbf zzbfVar, String str) {
        this.zza = zzdoVar;
        this.zzb = zzbfVar;
        this.zzc = str;
        this.zzd = appMeasurementDynamiteService;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzd.zza.zzr().zza(this.zza, this.zzb, this.zzc);
    }
}
