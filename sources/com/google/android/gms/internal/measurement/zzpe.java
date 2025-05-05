package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes.dex */
public final class zzpe implements zzpf {
    private static final zzhj<Boolean> zza;

    static {
        zzhr zza2 = new zzhr(zzhk.zza("com.google.android.gms.measurement")).zzb().zza();
        zza2.zza("measurement.sdk.collection.enable_extend_user_property_size", true);
        zza2.zza("measurement.sdk.collection.last_deep_link_referrer2", true);
        zza = zza2.zza("measurement.sdk.collection.last_deep_link_referrer_campaign2", false);
        zza2.zza("measurement.id.sdk.collection.last_deep_link_referrer2", 0L);
    }

    @Override // com.google.android.gms.internal.measurement.zzpf
    public final boolean zza() {
        return zza.zza().booleanValue();
    }
}
