package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes.dex */
public final class zzno implements zznp {
    private static final zzhj<Boolean> zza;
    private static final zzhj<Boolean> zzb;
    private static final zzhj<Boolean> zzc;
    private static final zzhj<Boolean> zzd;

    static {
        zzhr zza2 = new zzhr(zzhk.zza("com.google.android.gms.measurement")).zzb().zza();
        zza = zza2.zza("measurement.consent.stop_reset_on_storage_denied.client", true);
        zzb = zza2.zza("measurement.consent.stop_reset_on_storage_denied.service", true);
        zzc = zza2.zza("measurement.consent.scrub_audience_data_analytics_consent", true);
        zzd = zza2.zza("measurement.consent.fix_first_open_count_from_snapshot", true);
    }

    @Override // com.google.android.gms.internal.measurement.zznp
    public final boolean zza() {
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zznp
    public final boolean zzb() {
        return zza.zza().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznp
    public final boolean zzc() {
        return zzb.zza().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznp
    public final boolean zzd() {
        return zzc.zza().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznp
    public final boolean zze() {
        return zzd.zza().booleanValue();
    }
}
