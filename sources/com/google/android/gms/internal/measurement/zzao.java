package com.google.android.gms.internal.measurement;

import com.github.mikephil.charting.utils.Utils;
import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
/* loaded from: classes.dex */
public final class zzao implements zzaq {
    public final int hashCode() {
        return 1;
    }

    @Override // com.google.android.gms.internal.measurement.zzaq
    public final Iterator<zzaq> zzh() {
        return null;
    }

    @Override // com.google.android.gms.internal.measurement.zzaq
    public final zzaq zza(String str, zzh zzhVar, List<zzaq> list) {
        throw new IllegalStateException(String.format("null has no function %s", str));
    }

    @Override // com.google.android.gms.internal.measurement.zzaq
    public final zzaq zzc() {
        return zzaq.zzd;
    }

    @Override // com.google.android.gms.internal.measurement.zzaq
    public final Boolean zzd() {
        return false;
    }

    @Override // com.google.android.gms.internal.measurement.zzaq
    public final Double zze() {
        return Double.valueOf(Utils.DOUBLE_EPSILON);
    }

    @Override // com.google.android.gms.internal.measurement.zzaq
    public final String zzf() {
        return "null";
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return obj instanceof zzao;
    }
}
