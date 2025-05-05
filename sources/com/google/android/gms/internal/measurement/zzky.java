package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
/* loaded from: classes.dex */
final class zzky implements zzkv {
    @Override // com.google.android.gms.internal.measurement.zzkv
    public final int zza(int i, Object obj, Object obj2) {
        zzkw zzkwVar = (zzkw) obj;
        if (zzkwVar.isEmpty()) {
            return 0;
        }
        Iterator it = zzkwVar.entrySet().iterator();
        if (!it.hasNext()) {
            return 0;
        }
        Map.Entry entry = (Map.Entry) it.next();
        entry.getKey();
        entry.getValue();
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.internal.measurement.zzkv
    public final zzkt<?, ?> zza(Object obj) {
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.internal.measurement.zzkv
    public final Object zza(Object obj, Object obj2) {
        zzkw zzkwVar = (zzkw) obj;
        zzkw zzkwVar2 = (zzkw) obj2;
        if (!zzkwVar2.isEmpty()) {
            if (!zzkwVar.zzd()) {
                zzkwVar = zzkwVar.zzb();
            }
            zzkwVar.zza(zzkwVar2);
        }
        return zzkwVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzkv
    public final Object zzb(Object obj) {
        return zzkw.zza().zzb();
    }

    @Override // com.google.android.gms.internal.measurement.zzkv
    public final Object zzc(Object obj) {
        ((zzkw) obj).zzc();
        return obj;
    }

    @Override // com.google.android.gms.internal.measurement.zzkv
    public final Map<?, ?> zzd(Object obj) {
        return (zzkw) obj;
    }

    @Override // com.google.android.gms.internal.measurement.zzkv
    public final Map<?, ?> zze(Object obj) {
        return (zzkw) obj;
    }

    zzky() {
    }

    @Override // com.google.android.gms.internal.measurement.zzkv
    public final boolean zzf(Object obj) {
        return !((zzkw) obj).zzd();
    }
}
