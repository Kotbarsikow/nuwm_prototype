package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes.dex */
final class zzkk implements Runnable {
    private final /* synthetic */ AtomicReference zza;
    private final /* synthetic */ boolean zzb;
    private final /* synthetic */ zzjq zzc;

    zzkk(zzjq zzjqVar, AtomicReference atomicReference, boolean z) {
        this.zza = atomicReference;
        this.zzb = z;
        this.zzc = zzjqVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzc.zzo().zza(this.zza, this.zzb);
    }
}
