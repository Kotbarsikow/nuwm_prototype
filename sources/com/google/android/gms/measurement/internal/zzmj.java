package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes.dex */
final class zzmj implements Runnable {
    private final /* synthetic */ boolean zza;
    private final /* synthetic */ zzo zzb;
    private final /* synthetic */ boolean zzc;
    private final /* synthetic */ zzbf zzd;
    private final /* synthetic */ String zze;
    private final /* synthetic */ zzls zzf;

    zzmj(zzls zzlsVar, boolean z, zzo zzoVar, boolean z2, zzbf zzbfVar, String str) {
        this.zza = z;
        this.zzb = zzoVar;
        this.zzc = z2;
        this.zzd = zzbfVar;
        this.zze = str;
        this.zzf = zzlsVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzgb zzgbVar;
        long j;
        long j2;
        long j3;
        zzgbVar = this.zzf.zzb;
        if (zzgbVar == null) {
            this.zzf.zzj().zzg().zza("Discarding data. Failed to send event to service");
            return;
        }
        if (this.zza) {
            Preconditions.checkNotNull(this.zzb);
            this.zzf.zza(zzgbVar, this.zzc ? null : this.zzd, this.zzb);
        } else {
            boolean zza = this.zzf.zze().zza(zzbh.zzce);
            try {
                if (TextUtils.isEmpty(this.zze)) {
                    Preconditions.checkNotNull(this.zzb);
                    if (zza) {
                        j3 = this.zzf.zzu.zzb().currentTimeMillis();
                        try {
                            j = this.zzf.zzu.zzb().elapsedRealtime();
                        } catch (RemoteException e) {
                            e = e;
                            j = 0;
                            j2 = j3;
                            this.zzf.zzj().zzg().zza("Failed to send event to the service", e);
                            if (zza && j2 != 0) {
                                zzgm.zza(this.zzf.zzu).zza(36301, 13, j2, this.zzf.zzu.zzb().currentTimeMillis(), (int) (this.zzf.zzu.zzb().elapsedRealtime() - j));
                            }
                            this.zzf.zzar();
                        }
                    } else {
                        j3 = 0;
                        j = 0;
                    }
                    try {
                        zzgbVar.zza(this.zzd, this.zzb);
                        if (zza) {
                            this.zzf.zzj().zzp().zza("Logging telemetry for logEvent");
                            zzgm.zza(this.zzf.zzu).zza(36301, 0, j3, this.zzf.zzu.zzb().currentTimeMillis(), (int) (this.zzf.zzu.zzb().elapsedRealtime() - j));
                        }
                    } catch (RemoteException e2) {
                        e = e2;
                        j2 = j3;
                        this.zzf.zzj().zzg().zza("Failed to send event to the service", e);
                        if (zza) {
                            zzgm.zza(this.zzf.zzu).zza(36301, 13, j2, this.zzf.zzu.zzb().currentTimeMillis(), (int) (this.zzf.zzu.zzb().elapsedRealtime() - j));
                        }
                        this.zzf.zzar();
                    }
                } else {
                    zzgbVar.zza(this.zzd, this.zze, this.zzf.zzj().zzx());
                }
            } catch (RemoteException e3) {
                e = e3;
                j = 0;
                j2 = 0;
            }
        }
        this.zzf.zzar();
    }
}
