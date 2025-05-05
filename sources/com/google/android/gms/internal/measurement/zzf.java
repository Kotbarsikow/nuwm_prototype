package com.google.android.gms.internal.measurement;

import com.github.mikephil.charting.utils.Utils;
import com.google.android.gms.internal.measurement.zzgd;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
/* loaded from: classes.dex */
public final class zzf {
    final zzh zza;
    final zzh zzb;
    private final zzbb zzc;
    private final zzl zzd;

    public final zzaq zza(zzh zzhVar, zzgd.zzd... zzdVarArr) {
        zzaq zzaqVar = zzaq.zzc;
        for (zzgd.zzd zzdVar : zzdVarArr) {
            zzaqVar = zzj.zza(zzdVar);
            zzg.zza(this.zzb);
            if ((zzaqVar instanceof zzat) || (zzaqVar instanceof zzar)) {
                zzaqVar = this.zzc.zza(zzhVar, zzaqVar);
            }
        }
        return zzaqVar;
    }

    public zzf() {
        zzbb zzbbVar = new zzbb();
        this.zzc = zzbbVar;
        zzh zzhVar = new zzh(null, zzbbVar);
        this.zzb = zzhVar;
        this.zza = zzhVar.zza();
        zzl zzlVar = new zzl();
        this.zzd = zzlVar;
        zzhVar.zzc("require", new zzz(zzlVar));
        zzlVar.zza("internal.platform", new Callable() { // from class: com.google.android.gms.internal.measurement.zze
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return new zzy();
            }
        });
        zzhVar.zzc("runtime.counter", new zzai(Double.valueOf(Utils.DOUBLE_EPSILON)));
    }

    public final void zza(String str, Callable<? extends zzal> callable) {
        this.zzd.zza(str, callable);
    }
}
