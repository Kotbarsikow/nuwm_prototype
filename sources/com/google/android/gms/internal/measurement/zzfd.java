package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzdy;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@22.1.2 */
/* loaded from: classes.dex */
final class zzfd extends zzdy.zza {
    private final /* synthetic */ zzdy.zzb zzc;
    private final /* synthetic */ zzdy zzd;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzfd(zzdy zzdyVar, zzdy.zzb zzbVar) {
        super(zzdyVar);
        this.zzc = zzbVar;
        this.zzd = zzdyVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzdy.zza
    final void zza() throws RemoteException {
        zzdj zzdjVar;
        zzdjVar = this.zzd.zzj;
        ((zzdj) Preconditions.checkNotNull(zzdjVar)).unregisterOnMeasurementEventListener(this.zzc);
    }
}
