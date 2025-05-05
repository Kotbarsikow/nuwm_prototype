package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes.dex */
public final class zznu implements zznv {
    private static final zzhj<Boolean> zza;
    private static final zzhj<Boolean> zzb;

    static {
        zzhr zza2 = new zzhr(zzhk.zza("com.google.android.gms.measurement")).zzb().zza();
        zza = zza2.zza("measurement.set_default_event_parameters_propagate_clear.client.dev", false);
        zzb = zza2.zza("measurement.set_default_event_parameters_propagate_clear.service", false);
        zza2.zza("measurement.id.set_default_event_parameters_propagate_clear.experiment_id", 0L);
    }

    @Override // com.google.android.gms.internal.measurement.zznv
    public final boolean zza() {
        return zza.zza().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznv
    public final boolean zzb() {
        return zzb.zza().booleanValue();
    }
}
