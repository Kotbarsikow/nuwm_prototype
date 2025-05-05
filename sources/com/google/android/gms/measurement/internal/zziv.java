package com.google.android.gms.measurement.internal;

import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
/* loaded from: classes.dex */
final class zziv implements Callable<zzaj> {
    private final /* synthetic */ zzo zza;
    private final /* synthetic */ zzic zzb;

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ zzaj call() throws Exception {
        zznv zznvVar;
        zznv zznvVar2;
        zznvVar = this.zzb.zza;
        zznvVar.zzr();
        zznvVar2 = this.zzb.zza;
        return new zzaj(zznvVar2.zza(this.zza.zza));
    }

    zziv(zzic zzicVar, zzo zzoVar) {
        this.zza = zzoVar;
        this.zzb = zzicVar;
    }
}
