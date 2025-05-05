package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes.dex */
public final class zzox implements zzou {
    private static final zzhj<Boolean> zza;
    private static final zzhj<Boolean> zzb;

    static {
        zzhr zza2 = new zzhr(zzhk.zza("com.google.android.gms.measurement")).zzb().zza();
        zza = zza2.zza("measurement.gbraid_campaign.gbraid.client", true);
        zzb = zza2.zza("measurement.gbraid_campaign.gbraid.service", true);
    }

    @Override // com.google.android.gms.internal.measurement.zzou
    public final boolean zza() {
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zzou
    public final boolean zzb() {
        return zza.zza().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzou
    public final boolean zzc() {
        return zzb.zza().booleanValue();
    }
}
