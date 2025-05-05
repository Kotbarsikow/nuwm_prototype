package com.google.android.gms.internal.measurement;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
/* loaded from: classes.dex */
final class zzkl implements zzkm {
    private static <E> zzkc<E> zzc(Object obj, long j) {
        return (zzkc) zzml.zze(obj, j);
    }

    @Override // com.google.android.gms.internal.measurement.zzkm
    public final <L> List<L> zza(Object obj, long j) {
        zzkc zzc = zzc(obj, j);
        if (zzc.zzc()) {
            return zzc;
        }
        int size = zzc.size();
        zzkc zza = zzc.zza(size == 0 ? 10 : size << 1);
        zzml.zza(obj, j, zza);
        return zza;
    }

    zzkl() {
    }

    @Override // com.google.android.gms.internal.measurement.zzkm
    public final void zzb(Object obj, long j) {
        zzc(obj, j).zzb();
    }

    @Override // com.google.android.gms.internal.measurement.zzkm
    public final <E> void zza(Object obj, Object obj2, long j) {
        zzkc zzc = zzc(obj, j);
        zzkc zzc2 = zzc(obj2, j);
        int size = zzc.size();
        int size2 = zzc2.size();
        if (size > 0 && size2 > 0) {
            if (!zzc.zzc()) {
                zzc = zzc.zza(size2 + size);
            }
            zzc.addAll(zzc2);
        }
        if (size > 0) {
            zzc2 = zzc;
        }
        zzml.zza(obj, j, zzc2);
    }
}
