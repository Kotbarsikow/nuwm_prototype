package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes.dex */
final class zznd implements Runnable {
    private final /* synthetic */ long zza;
    private final /* synthetic */ zznb zzb;

    zznd(zznb zznbVar, long j) {
        this.zza = j;
        this.zzb = zznbVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zznb.zza(this.zzb, this.zza);
    }
}
