package com.google.android.gms.internal.measurement;

import android.util.Log;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes.dex */
final class zzhn extends zzhj<Long> {
    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.google.android.gms.internal.measurement.zzhj
    @Nullable
    /* renamed from: zzb, reason: merged with bridge method [inline-methods] */
    public final Long zza(Object obj) {
        if (obj instanceof Long) {
            return (Long) obj;
        }
        if (obj instanceof String) {
            try {
                return Long.valueOf(Long.parseLong((String) obj));
            } catch (NumberFormatException unused) {
            }
        }
        Log.e("PhenotypeFlag", "Invalid long value for " + super.zzb() + ": " + String.valueOf(obj));
        return null;
    }

    zzhn(zzhr zzhrVar, String str, Long l, boolean z) {
        super(zzhrVar, str, l);
    }
}
