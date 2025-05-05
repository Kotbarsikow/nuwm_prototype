package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes.dex */
final class zzlv implements Runnable {
    private final /* synthetic */ AtomicReference zza;
    private final /* synthetic */ zzo zzb;
    private final /* synthetic */ boolean zzc;
    private final /* synthetic */ zzls zzd;

    zzlv(zzls zzlsVar, AtomicReference atomicReference, zzo zzoVar, boolean z) {
        this.zza = atomicReference;
        this.zzb = zzoVar;
        this.zzc = z;
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
                this.zzd.zzj().zzg().zza("Failed to get all user properties; remote exception", e);
            }
            if (zzgbVar == null) {
                this.zzd.zzj().zzg().zza("Failed to get all user properties; not connected to service");
                return;
            }
            Preconditions.checkNotNull(this.zzb);
            this.zza.set(zzgbVar.zza(this.zzb, this.zzc));
            this.zzd.zzar();
            this.zza.notify();
        }
    }
}
