package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes.dex */
public final class zzof implements zzoc {
    private static final zzhj<Boolean> zza;
    private static final zzhj<Boolean> zzb;

    static {
        zzhr zza2 = new zzhr(zzhk.zza("com.google.android.gms.measurement")).zzb().zza();
        zza2.zza("measurement.client.ad_id_consent_fix", true);
        zza2.zza("measurement.service.consent.aiid_reset_fix", false);
        zza2.zza("measurement.service.consent.aiid_reset_fix2", true);
        zza2.zza("measurement.service.consent.app_start_fix", true);
        zza = zza2.zza("measurement.service.consent.params_on_fx", true);
        zzb = zza2.zza("measurement.service.consent.pfo_on_fx", true);
    }

    @Override // com.google.android.gms.internal.measurement.zzoc
    public final boolean zza() {
        return zza.zza().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzoc
    public final boolean zzb() {
        return zzb.zza().booleanValue();
    }
}
