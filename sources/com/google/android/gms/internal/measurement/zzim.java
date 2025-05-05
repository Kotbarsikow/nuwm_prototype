package com.google.android.gms.internal.measurement;

import java.util.Comparator;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
/* loaded from: classes.dex */
final class zzim implements Comparator<zzik> {
    @Override // java.util.Comparator
    public final /* synthetic */ int compare(zzik zzikVar, zzik zzikVar2) {
        zzik zzikVar3 = zzikVar;
        zzik zzikVar4 = zzikVar2;
        zziq zziqVar = (zziq) zzikVar3.iterator();
        zziq zziqVar2 = (zziq) zzikVar4.iterator();
        while (zziqVar.hasNext() && zziqVar2.hasNext()) {
            int compare = Integer.compare(zzik.zza(zziqVar.zza()), zzik.zza(zziqVar2.zza()));
            if (compare != 0) {
                return compare;
            }
        }
        return Integer.compare(zzikVar3.zzb(), zzikVar4.zzb());
    }

    zzim() {
    }
}
