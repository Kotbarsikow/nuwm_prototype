package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes.dex */
final class zzkt implements Runnable {
    private final /* synthetic */ AtomicReference zza;
    private final /* synthetic */ zzjq zzb;

    zzkt(zzjq zzjqVar, AtomicReference atomicReference) {
        this.zza = atomicReference;
        this.zzb = zzjqVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.zza) {
            try {
                this.zza.set(Integer.valueOf(this.zzb.zze().zzb(this.zzb.zzg().zzad(), zzbh.zzap)));
            } finally {
                this.zza.notify();
            }
        }
    }
}
