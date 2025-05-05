package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes.dex */
public final class zznz implements zznw {
    private static final zzhj<Boolean> zza;
    private static final zzhj<Boolean> zzb;
    private static final zzhj<Boolean> zzc;

    static {
        zzhr zza2 = new zzhr(zzhk.zza("com.google.android.gms.measurement")).zzb().zza();
        zza2.zza("measurement.dma_consent.client", true);
        zza2.zza("measurement.dma_consent.client_bow_check2", true);
        zza2.zza("measurement.dma_consent.separate_service_calls_fix", true);
        zza2.zza("measurement.dma_consent.service", true);
        zza = zza2.zza("measurement.dma_consent.service_database_update_fix", true);
        zza2.zza("measurement.dma_consent.service_dcu_event", true);
        zzb = zza2.zza("measurement.dma_consent.service_dcu_event2", true);
        zza2.zza("measurement.dma_consent.service_npa_remote_default", true);
        zza2.zza("measurement.dma_consent.service_split_batch_on_consent", true);
        zza2.zza("measurement.dma_consent.set_consent_inline_on_worker", true);
        zzc = zza2.zza("measurement.dma_consent.setting_npa_inline_fix", true);
    }

    @Override // com.google.android.gms.internal.measurement.zznw
    public final boolean zza() {
        return zza.zza().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznw
    public final boolean zzb() {
        return zzb.zza().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznw
    public final boolean zzc() {
        return zzc.zza().booleanValue();
    }
}
