package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes.dex */
public final class zzpw implements zzpx {
    private static final zzhj<Boolean> zza;
    private static final zzhj<Boolean> zzb;
    private static final zzhj<Boolean> zzc;
    private static final zzhj<Boolean> zzd;
    private static final zzhj<Boolean> zze;

    static {
        zzhr zza2 = new zzhr(zzhk.zza("com.google.android.gms.measurement")).zzb().zza();
        zza = zza2.zza("measurement.sgtm.google_signal.enable", false);
        zzb = zza2.zza("measurement.sgtm.preview_mode_enabled", true);
        zzc = zza2.zza("measurement.sgtm.rollout_percentage_fix", false);
        zzd = zza2.zza("measurement.sgtm.service", true);
        zze = zza2.zza("measurement.sgtm.upload_queue", false);
        zza2.zza("measurement.id.sgtm", 0L);
    }

    @Override // com.google.android.gms.internal.measurement.zzpx
    public final boolean zza() {
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zzpx
    public final boolean zzb() {
        return zza.zza().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpx
    public final boolean zzc() {
        return zzb.zza().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpx
    public final boolean zzd() {
        return zzc.zza().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpx
    public final boolean zze() {
        return zzd.zza().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpx
    public final boolean zzf() {
        return zze.zza().booleanValue();
    }
}
