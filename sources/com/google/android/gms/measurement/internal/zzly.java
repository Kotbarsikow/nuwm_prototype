package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes.dex */
final class zzly implements Runnable {
    private final /* synthetic */ AtomicReference zza;
    private final /* synthetic */ zzo zzb;
    private final /* synthetic */ Bundle zzc;
    private final /* synthetic */ zzls zzd;

    zzly(zzls zzlsVar, AtomicReference atomicReference, zzo zzoVar, Bundle bundle) {
        this.zza = atomicReference;
        this.zzb = zzoVar;
        this.zzc = bundle;
        this.zzd = zzlsVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzgb zzgbVar;
        synchronized (this.zza) {
            try {
                try {
                    zzgbVar = this.zzd.zzb;
                } finally {
                    this.zza.notify();
                }
            } catch (RemoteException e) {
                this.zzd.zzj().zzg().zza("Failed to get trigger URIs; remote exception", e);
            }
            if (zzgbVar == null) {
                this.zzd.zzj().zzg().zza("Failed to get trigger URIs; not connected to service");
                return;
            }
            Preconditions.checkNotNull(this.zzb);
            this.zza.set(zzgbVar.zza(this.zzb, this.zzc));
            this.zzd.zzar();
            this.zza.notify();
        }
    }
}
