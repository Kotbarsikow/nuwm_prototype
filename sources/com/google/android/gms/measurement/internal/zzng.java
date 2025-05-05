package com.google.android.gms.measurement.internal;

import android.os.Handler;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes.dex */
final class zzng {
    final /* synthetic */ zznb zza;
    private zznf zzb;

    zzng(zznb zznbVar) {
        this.zza = zznbVar;
    }

    final void zza(long j) {
        Handler handler;
        this.zzb = new zznf(this, this.zza.zzb().currentTimeMillis(), j);
        handler = this.zza.zzc;
        handler.postDelayed(this.zzb, 2000L);
    }

    final void zza() {
        Handler handler;
        this.zza.zzt();
        if (this.zzb != null) {
            handler = this.zza.zzc;
            handler.removeCallbacks(this.zzb);
        }
        this.zza.zzk().zzn.zza(false);
        this.zza.zza(false);
        if (this.zza.zze().zza(zzbh.zzcl) && this.zza.zzm().zzau()) {
            this.zza.zzj().zzp().zza("Retrying trigger URI registration in foreground");
            this.zza.zzm().zzas();
        }
    }
}
