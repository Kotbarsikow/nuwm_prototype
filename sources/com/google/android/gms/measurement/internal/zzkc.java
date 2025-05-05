package com.google.android.gms.measurement.internal;

import android.util.SparseArray;
import com.google.common.util.concurrent.FutureCallback;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes.dex */
final class zzkc implements FutureCallback<Object> {
    private final /* synthetic */ zzno zza;
    private final /* synthetic */ zzjq zzb;

    zzkc(zzjq zzjqVar, zzno zznoVar) {
        this.zza = zznoVar;
        this.zzb = zzjqVar;
    }

    @Override // com.google.common.util.concurrent.FutureCallback
    public final void onFailure(Throwable th) {
        int i;
        int i2;
        int i3;
        int i4;
        this.zzb.zzt();
        this.zzb.zzh = false;
        if (!this.zzb.zze().zza(zzbh.zzcn)) {
            this.zzb.zzas();
            this.zzb.zzj().zzg().zza("registerTriggerAsync failed with throwable", th);
            return;
        }
        int zza = (this.zzb.zze().zza(zzbh.zzcl) ? zzjq.zza(this.zzb, th) : 2) - 1;
        if (zza == 0) {
            this.zzb.zzj().zzu().zza("registerTriggerAsync failed with retriable error. Will try later. App ID, throwable", zzgo.zza(this.zzb.zzg().zzad()), zzgo.zza(th.toString()));
            this.zzb.zzi = 1;
            this.zzb.zzal().add(this.zza);
            return;
        }
        if (zza != 1) {
            if (zza != 2) {
                return;
            }
            this.zzb.zzj().zzg().zza("registerTriggerAsync failed. Dropping URI. App ID, Throwable", zzgo.zza(this.zzb.zzg().zzad()), th);
            zza();
            this.zzb.zzi = 1;
            this.zzb.zzas();
            return;
        }
        this.zzb.zzal().add(this.zza);
        i = this.zzb.zzi;
        if (i > 32) {
            this.zzb.zzi = 1;
            this.zzb.zzj().zzu().zza("registerTriggerAsync failed. May try later. App ID, throwable", zzgo.zza(this.zzb.zzg().zzad()), zzgo.zza(th.toString()));
            return;
        }
        zzgq zzu = this.zzb.zzj().zzu();
        Object zza2 = zzgo.zza(this.zzb.zzg().zzad());
        i2 = this.zzb.zzi;
        zzu.zza("registerTriggerAsync failed. App ID, delay in seconds, throwable", zza2, zzgo.zza(String.valueOf(i2)), zzgo.zza(th.toString()));
        zzjq zzjqVar = this.zzb;
        i3 = zzjqVar.zzi;
        zzjq.zzb(zzjqVar, i3);
        zzjq zzjqVar2 = this.zzb;
        i4 = zzjqVar2.zzi;
        zzjqVar2.zzi = i4 << 1;
    }

    @Override // com.google.common.util.concurrent.FutureCallback
    public final void onSuccess(Object obj) {
        this.zzb.zzt();
        if (this.zzb.zze().zza(zzbh.zzcn)) {
            zza();
            this.zzb.zzh = false;
            this.zzb.zzi = 1;
            this.zzb.zzj().zzc().zza("Successfully registered trigger URI", this.zza.zza);
            this.zzb.zzas();
            return;
        }
        this.zzb.zzh = false;
        this.zzb.zzas();
        this.zzb.zzj().zzc().zza("registerTriggerAsync ran. uri", this.zza.zza);
    }

    private final void zza() {
        SparseArray<Long> zzm = this.zzb.zzk().zzm();
        zzm.put(this.zza.zzc, Long.valueOf(this.zza.zzb));
        this.zzb.zzk().zza(zzm);
    }
}
