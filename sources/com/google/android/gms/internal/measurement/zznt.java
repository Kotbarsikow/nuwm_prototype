package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes.dex */
public final class zznt implements zznq {
    private static final zzhj<Boolean> zza;
    private static final zzhj<Boolean> zzb;
    private static final zzhj<Boolean> zzc;

    static {
        zzhr zza2 = new zzhr(zzhk.zza("com.google.android.gms.measurement")).zzb().zza();
        zza = zza2.zza("measurement.set_default_event_parameters_with_backfill.client.dev", false);
        zzb = zza2.zza("measurement.defensively_copy_bundles_validate_default_params", true);
        zzc = zza2.zza("measurement.set_default_event_parameters_with_backfill.service", false);
    }

    @Override // com.google.android.gms.internal.measurement.zznq
    public final boolean zza() {
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zznq
    public final boolean zzb() {
        return zza.zza().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznq
    public final boolean zzc() {
        return zzb.zza().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznq
    public final boolean zzd() {
        return zzc.zza().booleanValue();
    }
}
