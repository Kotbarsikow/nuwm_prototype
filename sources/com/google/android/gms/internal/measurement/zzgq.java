package com.google.android.gms.internal.measurement;

import android.content.ContentResolver;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes.dex */
public interface zzgq {
    String zza(ContentResolver contentResolver, String str) throws zzgt;

    <T extends Map<String, String>> T zza(ContentResolver contentResolver, String[] strArr, zzgr<T> zzgrVar) throws zzgt;
}
