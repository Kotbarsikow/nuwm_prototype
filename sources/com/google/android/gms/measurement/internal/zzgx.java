package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
/* loaded from: classes.dex */
final class zzgx implements Runnable {
    private final /* synthetic */ boolean zza;
    private final /* synthetic */ zzgy zzb;

    zzgx(zzgy zzgyVar, boolean z) {
        this.zza = z;
        this.zzb = zzgyVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zznv zznvVar;
        zznvVar = this.zzb.zza;
        zznvVar.zza(this.zza);
    }
}
