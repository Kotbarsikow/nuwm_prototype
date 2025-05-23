package com.google.android.gms.internal.auth;

/* compiled from: com.google.android.gms:play-services-auth-base@@18.0.4 */
/* loaded from: classes.dex */
final class zzfi extends zzfk {
    private zzfi() {
        super(null);
    }

    /* synthetic */ zzfi(zzfh zzfhVar) {
        super(null);
    }

    @Override // com.google.android.gms.internal.auth.zzfk
    final void zza(Object obj, long j) {
        ((zzey) zzhi.zzf(obj, j)).zzb();
    }

    @Override // com.google.android.gms.internal.auth.zzfk
    final void zzb(Object obj, Object obj2, long j) {
        zzey zzeyVar = (zzey) zzhi.zzf(obj, j);
        zzey zzeyVar2 = (zzey) zzhi.zzf(obj2, j);
        int size = zzeyVar.size();
        int size2 = zzeyVar2.size();
        if (size > 0 && size2 > 0) {
            if (!zzeyVar.zzc()) {
                zzeyVar = zzeyVar.zzd(size2 + size);
            }
            zzeyVar.addAll(zzeyVar2);
        }
        if (size > 0) {
            zzeyVar2 = zzeyVar;
        }
        zzhi.zzp(obj, j, zzeyVar2);
    }
}
