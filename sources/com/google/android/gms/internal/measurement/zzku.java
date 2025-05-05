package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
/* loaded from: classes.dex */
public final class zzku<K, V> {
    static <K, V> int zza(zzkt<K, V> zzktVar, K k, V v) {
        return zzjm.zza(zzktVar.zza, 1, k) + zzjm.zza(zzktVar.zzc, 2, v);
    }

    static <K, V> void zza(zzjc zzjcVar, zzkt<K, V> zzktVar, K k, V v) throws IOException {
        zzjm.zza(zzjcVar, zzktVar.zza, 1, k);
        zzjm.zza(zzjcVar, zzktVar.zzc, 2, v);
    }
}
