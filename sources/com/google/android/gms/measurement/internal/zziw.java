package com.google.android.gms.measurement.internal;

import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
/* loaded from: classes.dex */
final class zziw implements Callable<byte[]> {
    private final /* synthetic */ zzbf zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ zzic zzc;

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ byte[] call() throws Exception {
        zznv zznvVar;
        zznv zznvVar2;
        zznvVar = this.zzc.zza;
        zznvVar.zzr();
        zznvVar2 = this.zzc.zza;
        return zznvVar2.zzm().zza(this.zza, this.zzb);
    }

    zziw(zzic zzicVar, zzbf zzbfVar, String str) {
        this.zza = zzbfVar;
        this.zzb = str;
        this.zzc = zzicVar;
    }
}
