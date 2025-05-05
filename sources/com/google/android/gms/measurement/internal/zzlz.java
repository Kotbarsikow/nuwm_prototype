package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes.dex */
final class zzlz implements Runnable {
    private final /* synthetic */ AtomicReference zza;
    private final /* synthetic */ zzo zzb;
    private final /* synthetic */ zzls zzc;

    zzlz(zzls zzlsVar, AtomicReference atomicReference, zzo zzoVar) {
        this.zza = atomicReference;
        this.zzb = zzoVar;
        this.zzc = zzlsVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzgb zzgbVar;
        synchronized (this.zza) {
            try {
                try {
                } finally {
                    this.zza.notify();
                }
            } catch (RemoteException e) {
                this.zzc.zzj().zzg().zza("Failed to get app instance id", e);
            }
            if (!this.zzc.zzk().zzo().zzh()) {
                this.zzc.zzj().zzv().zza("Analytics storage consent denied; will not get app instance id");
                this.zzc.zzm().zzc((String) null);
                this.zzc.zzk().zze.zza(null);
                this.zza.set(null);
                return;
            }
            zzgbVar = this.zzc.zzb;
            if (zzgbVar == null) {
                this.zzc.zzj().zzg().zza("Failed to get app instance id");
                return;
            }
            Preconditions.checkNotNull(this.zzb);
            this.zza.set(zzgbVar.zzb(this.zzb));
            String str = (String) this.zza.get();
            if (str != null) {
                this.zzc.zzm().zzc(str);
                this.zzc.zzk().zze.zza(str);
            }
            this.zzc.zzar();
            this.zza.notify();
        }
    }
}
