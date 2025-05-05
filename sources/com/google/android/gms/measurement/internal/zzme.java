package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes.dex */
final class zzme implements Runnable {
    private final /* synthetic */ zzo zza;
    private final /* synthetic */ zzls zzb;

    zzme(zzls zzlsVar, zzo zzoVar) {
        this.zza = zzoVar;
        this.zzb = zzlsVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzgb zzgbVar;
        zzgbVar = this.zzb.zzb;
        if (zzgbVar == null) {
            this.zzb.zzj().zzu().zza("Failed to send app backgrounded");
            return;
        }
        try {
            Preconditions.checkNotNull(this.zza);
            zzgbVar.zzc(this.zza);
            this.zzb.zzar();
        } catch (RemoteException e) {
            this.zzb.zzj().zzg().zza("Failed to send app backgrounded to the service", e);
        }
    }
}
