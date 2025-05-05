package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes.dex */
public final class zzqg implements zzqe {
    private static final zzhj<Boolean> zza;

    static {
        zzhr zza2 = new zzhr(zzhk.zza("com.google.android.gms.measurement")).zzb().zza();
        zza = zza2.zza("measurement.currency.escape_underscore_fix", true);
        zza2.zza("measurement.validation.value_and_currency_params", true);
    }

    @Override // com.google.android.gms.internal.measurement.zzqe
    public final boolean zza() {
        return zza.zza().booleanValue();
    }
}
