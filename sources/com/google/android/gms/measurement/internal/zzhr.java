package com.google.android.gms.measurement.internal;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
/* loaded from: classes.dex */
final class zzhr implements com.google.android.gms.internal.measurement.zzv {
    private final /* synthetic */ zzhl zza;

    zzhr(zzhl zzhlVar) {
        this.zza = zzhlVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzv
    public final void zza(com.google.android.gms.internal.measurement.zzs zzsVar, String str, List<String> list, boolean z, boolean z2) {
        int i = zzht.zza[zzsVar.ordinal()];
        zzgq zzo = i != 1 ? i != 2 ? i != 3 ? i != 4 ? this.zza.zzj().zzo() : this.zza.zzj().zzp() : z ? this.zza.zzj().zzw() : !z2 ? this.zza.zzj().zzv() : this.zza.zzj().zzu() : z ? this.zza.zzj().zzn() : !z2 ? this.zza.zzj().zzm() : this.zza.zzj().zzg() : this.zza.zzj().zzc();
        int size = list.size();
        if (size == 1) {
            zzo.zza(str, list.get(0));
            return;
        }
        if (size == 2) {
            zzo.zza(str, list.get(0), list.get(1));
        } else if (size != 3) {
            zzo.zza(str);
        } else {
            zzo.zza(str, list.get(0), list.get(1), list.get(2));
        }
    }
}
