package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
/* loaded from: classes.dex */
final class zznx implements Runnable {
    private final /* synthetic */ zzok zza;
    private final /* synthetic */ zznv zzb;

    zznx(zznv zznvVar, zzok zzokVar) {
        this.zza = zzokVar;
        this.zzb = zznvVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zznv.zza(this.zzb, this.zza);
        this.zzb.zzv();
    }
}
