package com.google.android.gms.internal.measurement;

import android.content.Intent;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzdy;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@22.1.2 */
/* loaded from: classes.dex */
final class zzfb extends zzdy.zza {
    private final /* synthetic */ Intent zzc;
    private final /* synthetic */ zzdy zzd;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzfb(zzdy zzdyVar, Intent intent) {
        super(zzdyVar);
        this.zzc = intent;
        this.zzd = zzdyVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzdy.zza
    final void zza() throws RemoteException {
        zzdj zzdjVar;
        zzdjVar = this.zzd.zzj;
        ((zzdj) Preconditions.checkNotNull(zzdjVar)).setSgtmDebugInfo(this.zzc);
    }
}
