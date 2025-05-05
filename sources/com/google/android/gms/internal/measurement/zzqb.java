package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes.dex */
public final class zzqb implements zzpy {
    private static final zzhj<Boolean> zza = new zzhr(zzhk.zza("com.google.android.gms.measurement")).zzb().zza().zza("measurement.integration.disable_firebase_instance_id", false);

    @Override // com.google.android.gms.internal.measurement.zzpy
    public final boolean zza() {
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zzpy
    public final boolean zzb() {
        return zza.zza().booleanValue();
    }
}
