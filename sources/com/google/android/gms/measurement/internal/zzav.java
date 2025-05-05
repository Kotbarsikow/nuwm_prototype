package com.google.android.gms.measurement.internal;

import android.os.Handler;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes.dex */
abstract class zzav {
    private static volatile Handler zza;
    private final zzjc zzb;
    private final Runnable zzc;
    private volatile long zzd;

    private final Handler zzd() {
        Handler handler;
        if (zza != null) {
            return zza;
        }
        synchronized (zzav.class) {
            if (zza == null) {
                zza = new com.google.android.gms.internal.measurement.zzdh(this.zzb.zza().getMainLooper());
            }
            handler = zza;
        }
        return handler;
    }

    public abstract void zzb();

    zzav(zzjc zzjcVar) {
        Preconditions.checkNotNull(zzjcVar);
        this.zzb = zzjcVar;
        this.zzc = new zzay(this, zzjcVar);
    }

    final void zza() {
        this.zzd = 0L;
        zzd().removeCallbacks(this.zzc);
    }

    public final void zza(long j) {
        zza();
        if (j >= 0) {
            this.zzd = this.zzb.zzb().currentTimeMillis();
            if (zzd().postDelayed(this.zzc, j)) {
                return;
            }
            this.zzb.zzj().zzg().zza("Failed to schedule delayed post. time", Long.valueOf(j));
        }
    }

    public final boolean zzc() {
        return this.zzd != 0;
    }
}
