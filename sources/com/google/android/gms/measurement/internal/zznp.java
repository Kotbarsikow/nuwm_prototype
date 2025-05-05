package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
/* loaded from: classes.dex */
final class zznp extends zzav {
    private final /* synthetic */ zznq zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zznp(zznq zznqVar, zzjc zzjcVar) {
        super(zzjcVar);
        this.zza = zznqVar;
    }

    @Override // com.google.android.gms.measurement.internal.zzav
    public final void zzb() {
        this.zza.zzu();
        this.zza.zzj().zzp().zza("Starting upload from DelayedRunnable");
        this.zza.zzg.zzw();
    }
}
