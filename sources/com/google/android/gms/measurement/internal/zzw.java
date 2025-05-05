package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzfo;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
/* loaded from: classes.dex */
final /* synthetic */ class zzw {
    static final /* synthetic */ int[] zza;
    static final /* synthetic */ int[] zzb;

    static {
        int[] iArr = new int[zzfo.zzd.zzb.values().length];
        zzb = iArr;
        try {
            iArr[zzfo.zzd.zzb.LESS_THAN.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            zzb[zzfo.zzd.zzb.GREATER_THAN.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            zzb[zzfo.zzd.zzb.EQUAL.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            zzb[zzfo.zzd.zzb.BETWEEN.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        int[] iArr2 = new int[zzfo.zzf.zzb.values().length];
        zza = iArr2;
        try {
            iArr2[zzfo.zzf.zzb.REGEXP.ordinal()] = 1;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            zza[zzfo.zzf.zzb.BEGINS_WITH.ordinal()] = 2;
        } catch (NoSuchFieldError unused6) {
        }
        try {
            zza[zzfo.zzf.zzb.ENDS_WITH.ordinal()] = 3;
        } catch (NoSuchFieldError unused7) {
        }
        try {
            zza[zzfo.zzf.zzb.PARTIAL.ordinal()] = 4;
        } catch (NoSuchFieldError unused8) {
        }
        try {
            zza[zzfo.zzf.zzb.EXACT.ordinal()] = 5;
        } catch (NoSuchFieldError unused9) {
        }
        try {
            zza[zzfo.zzf.zzb.IN_LIST.ordinal()] = 6;
        } catch (NoSuchFieldError unused10) {
        }
    }
}
