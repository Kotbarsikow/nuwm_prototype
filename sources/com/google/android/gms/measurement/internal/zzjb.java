package com.google.android.gms.measurement.internal;

import java.util.List;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
/* loaded from: classes.dex */
final class zzjb implements Callable<List<zzop>> {
    private final /* synthetic */ String zza;
    private final /* synthetic */ zzic zzb;

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ List<zzop> call() throws Exception {
        zznv zznvVar;
        zznv zznvVar2;
        zznvVar = this.zzb.zza;
        zznvVar.zzr();
        zznvVar2 = this.zzb.zza;
        return zznvVar2.zzf().zzl(this.zza);
    }

    zzjb(zzic zzicVar, String str) {
        this.zza = str;
        this.zzb = zzicVar;
    }
}
