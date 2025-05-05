package com.google.android.gms.measurement.internal;

import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes.dex */
final class zzmi implements Runnable {
    private final /* synthetic */ zzbf zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ com.google.android.gms.internal.measurement.zzdo zzc;
    private final /* synthetic */ zzls zzd;

    zzmi(zzls zzlsVar, zzbf zzbfVar, String str, com.google.android.gms.internal.measurement.zzdo zzdoVar) {
        this.zza = zzbfVar;
        this.zzb = str;
        this.zzc = zzdoVar;
        this.zzd = zzlsVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzgb zzgbVar;
        try {
            zzgbVar = this.zzd.zzb;
            if (zzgbVar == null) {
                this.zzd.zzj().zzg().zza("Discarding data. Failed to send event to service to bundle");
                return;
            }
            byte[] zza = zzgbVar.zza(this.zza, this.zzb);
            this.zzd.zzar();
            this.zzd.zzq().zza(this.zzc, zza);
        } catch (RemoteException e) {
            this.zzd.zzj().zzg().zza("Failed to send event to the service to bundle", e);
        } finally {
            this.zzd.zzq().zza(this.zzc, (byte[]) null);
        }
    }
}
