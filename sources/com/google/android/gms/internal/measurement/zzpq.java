package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes.dex */
public final class zzpq implements zzpr {
    private static final zzhj<Boolean> zza;

    static {
        zzhr zza2 = new zzhr(zzhk.zza("com.google.android.gms.measurement")).zzb().zza();
        zza2.zza("measurement.collection.enable_session_stitching_token.client.dev", true);
        zza2.zza("measurement.collection.enable_session_stitching_token.first_open_fix", true);
        zza = zza2.zza("measurement.session_stitching_token_enabled", false);
        zza2.zza("measurement.link_sst_to_sid", true);
    }

    @Override // com.google.android.gms.internal.measurement.zzpr
    public final boolean zza() {
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zzpr
    public final boolean zzb() {
        return zza.zza().booleanValue();
    }
}
