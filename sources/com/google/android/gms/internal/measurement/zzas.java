package com.google.android.gms.internal.measurement;

import com.github.mikephil.charting.utils.Utils;
import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
/* loaded from: classes.dex */
public final class zzas implements zzaq, Iterable<zzaq> {
    private final String zza;

    public final int hashCode() {
        return this.zza.hashCode();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:111:0x042d  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x04be  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0508  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x051c  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x0532  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x0594  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x05a8  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x05f9  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0610  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x0649  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x068f  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x06a5  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x06af  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x0103  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x0115  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x0127  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x0162  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x0189  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x019c  */
    /* JADX WARN: Removed duplicated region for block: B:247:0x01af  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x01c2  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x01d3  */
    /* JADX WARN: Removed duplicated region for block: B:256:0x01e5  */
    /* JADX WARN: Removed duplicated region for block: B:259:0x01f7  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x020d  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0215  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x026e  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0302  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x037c  */
    @Override // com.google.android.gms.internal.measurement.zzaq
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.google.android.gms.internal.measurement.zzaq zza(java.lang.String r22, com.google.android.gms.internal.measurement.zzh r23, java.util.List<com.google.android.gms.internal.measurement.zzaq> r24) {
        /*
            Method dump skipped, instructions count: 1888
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzas.zza(java.lang.String, com.google.android.gms.internal.measurement.zzh, java.util.List):com.google.android.gms.internal.measurement.zzaq");
    }

    @Override // com.google.android.gms.internal.measurement.zzaq
    public final zzaq zzc() {
        return new zzas(this.zza);
    }

    @Override // com.google.android.gms.internal.measurement.zzaq
    public final Boolean zzd() {
        return Boolean.valueOf(!this.zza.isEmpty());
    }

    @Override // com.google.android.gms.internal.measurement.zzaq
    public final Double zze() {
        if (this.zza.isEmpty()) {
            return Double.valueOf(Utils.DOUBLE_EPSILON);
        }
        try {
            return Double.valueOf(this.zza);
        } catch (NumberFormatException unused) {
            return Double.valueOf(Double.NaN);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzaq
    public final String zzf() {
        return this.zza;
    }

    public final String toString() {
        return "\"" + this.zza + "\"";
    }

    @Override // com.google.android.gms.internal.measurement.zzaq
    public final Iterator<zzaq> zzh() {
        return new zzav(this);
    }

    @Override // java.lang.Iterable
    public final Iterator<zzaq> iterator() {
        return new zzau(this);
    }

    public zzas(String str) {
        if (str == null) {
            throw new IllegalArgumentException("StringValue cannot be null.");
        }
        this.zza = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzas) {
            return this.zza.equals(((zzas) obj).zza);
        }
        return false;
    }
}
