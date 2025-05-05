package com.google.android.gms.measurement.internal;

import java.util.List;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
/* loaded from: classes.dex */
final class zzio implements Callable<List<zzop>> {
    private final /* synthetic */ String zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ String zzc;
    private final /* synthetic */ zzic zzd;

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ List<zzop> call() throws Exception {
        zznv zznvVar;
        zznv zznvVar2;
        zznvVar = this.zzd.zza;
        zznvVar.zzr();
        zznvVar2 = this.zzd.zza;
        return zznvVar2.zzf().zzb(this.zza, this.zzb, this.zzc);
    }

    zzio(zzic zzicVar, String str, String str2, String str3) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = str3;
        this.zzd = zzicVar;
    }
}
