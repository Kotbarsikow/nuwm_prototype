package com.google.android.gms.internal.measurement;

import com.github.mikephil.charting.utils.Utils;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import sun.misc.Unsafe;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
/* loaded from: classes.dex */
final class zzlg<T> implements zzlu<T> {
    private static final int[] zza = new int[0];
    private static final Unsafe zzb = zzml.zzb();
    private final int[] zzc;
    private final Object[] zzd;
    private final int zze;
    private final int zzf;
    private final zzlc zzg;
    private final boolean zzh;
    private final boolean zzi;
    private final boolean zzj;
    private final int[] zzk;
    private final int zzl;
    private final int zzm;
    private final zzlk zzn;
    private final zzkm zzo;
    private final zzmk<?, ?> zzp;
    private final zzji<?> zzq;
    private final zzkv zzr;

    private static <T> double zza(T t, long j) {
        return ((Double) zzml.zze(t, j)).doubleValue();
    }

    private static boolean zzg(int i) {
        return (i & 536870912) != 0;
    }

    private static <T> float zzb(T t, long j) {
        return ((Float) zzml.zze(t, j)).floatValue();
    }

    private static int zza(byte[] bArr, int i, int i2, zzms zzmsVar, Class<?> cls, zzij zzijVar) throws IOException {
        switch (zzlf.zza[zzmsVar.ordinal()]) {
            case 1:
                int zzd = zzig.zzd(bArr, i, zzijVar);
                zzijVar.zzc = Boolean.valueOf(zzijVar.zzb != 0);
                return zzd;
            case 2:
                return zzig.zza(bArr, i, zzijVar);
            case 3:
                zzijVar.zzc = Double.valueOf(zzig.zza(bArr, i));
                return i + 8;
            case 4:
            case 5:
                zzijVar.zzc = Integer.valueOf(zzig.zzc(bArr, i));
                return i + 4;
            case 6:
            case 7:
                zzijVar.zzc = Long.valueOf(zzig.zzd(bArr, i));
                return i + 8;
            case 8:
                zzijVar.zzc = Float.valueOf(zzig.zzb(bArr, i));
                return i + 4;
            case 9:
            case 10:
            case 11:
                int zzc = zzig.zzc(bArr, i, zzijVar);
                zzijVar.zzc = Integer.valueOf(zzijVar.zza);
                return zzc;
            case 12:
            case 13:
                int zzd2 = zzig.zzd(bArr, i, zzijVar);
                zzijVar.zzc = Long.valueOf(zzijVar.zzb);
                return zzd2;
            case 14:
                return zzig.zza(zzlq.zza().zza((Class) cls), bArr, i, i2, zzijVar);
            case 15:
                int zzc2 = zzig.zzc(bArr, i, zzijVar);
                zzijVar.zzc = Integer.valueOf(zziw.zza(zzijVar.zza));
                return zzc2;
            case 16:
                int zzd3 = zzig.zzd(bArr, i, zzijVar);
                zzijVar.zzc = Long.valueOf(zziw.zza(zzijVar.zzb));
                return zzd3;
            case 17:
                return zzig.zzb(bArr, i, zzijVar);
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Type inference failed for: r9v0 */
    /* JADX WARN: Type inference failed for: r9v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r9v11 */
    @Override // com.google.android.gms.internal.measurement.zzlu
    public final int zza(T t) {
        int i;
        int i2;
        int i3;
        int zza2;
        int zzb2;
        int zzh;
        int zzd;
        int zzi;
        int zzj;
        Unsafe unsafe = zzb;
        ?? r9 = 0;
        int i4 = 1048575;
        int i5 = 1048575;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        while (i7 < this.zzc.length) {
            int zzc = zzc(i7);
            int i9 = (267386880 & zzc) >>> 20;
            int[] iArr = this.zzc;
            int i10 = iArr[i7];
            int i11 = iArr[i7 + 2];
            int i12 = i11 & i4;
            if (i9 <= 17) {
                if (i12 != i5) {
                    i6 = i12 == i4 ? 0 : unsafe.getInt(t, i12);
                    i5 = i12;
                }
                i = i5;
                i2 = i6;
                i3 = 1 << (i11 >>> 20);
            } else {
                i = i5;
                i2 = i6;
                i3 = 0;
            }
            long j = zzc & i4;
            if (i9 >= zzjn.DOUBLE_LIST_PACKED.zza()) {
                zzjn.SINT64_LIST_PACKED.zza();
            }
            int i13 = i3;
            switch (i9) {
                case 0:
                    if (zza((zzlg<T>) t, i7, i, i2, i13)) {
                        zza2 = zzjc.zza(i10, Utils.DOUBLE_EPSILON);
                        i8 += zza2;
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (zza((zzlg<T>) t, i7, i, i2, i13)) {
                        zza2 = zzjc.zza(i10, 0.0f);
                        i8 += zza2;
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (zza((zzlg<T>) t, i7, i, i2, i13)) {
                        zza2 = zzjc.zzd(i10, unsafe.getLong(t, j));
                        i8 += zza2;
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (zza((zzlg<T>) t, i7, i, i2, i13)) {
                        zza2 = zzjc.zzg(i10, unsafe.getLong(t, j));
                        i8 += zza2;
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (zza((zzlg<T>) t, i7, i, i2, i13)) {
                        zza2 = zzjc.zzg(i10, unsafe.getInt(t, j));
                        i8 += zza2;
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (zza((zzlg<T>) t, i7, i, i2, i13)) {
                        zza2 = zzjc.zzc(i10, 0L);
                        i8 += zza2;
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (zza((zzlg<T>) t, i7, i, i2, i13)) {
                        zza2 = zzjc.zzf(i10, 0);
                        i8 += zza2;
                        break;
                    }
                    break;
                case 7:
                    if (zza((zzlg<T>) t, i7, i, i2, i13)) {
                        zzb2 = zzjc.zzb(i10, true);
                        i8 += zzb2;
                    }
                    break;
                case 8:
                    if (zza((zzlg<T>) t, i7, i, i2, i13)) {
                        Object object = unsafe.getObject(t, j);
                        if (object instanceof zzik) {
                            zzb2 = zzjc.zzc(i10, (zzik) object);
                        } else {
                            zzb2 = zzjc.zzb(i10, (String) object);
                        }
                        i8 += zzb2;
                    }
                    break;
                case 9:
                    if (zza((zzlg<T>) t, i7, i, i2, i13)) {
                        zzb2 = zzlw.zza(i10, unsafe.getObject(t, j), (zzlu<?>) zze(i7));
                        i8 += zzb2;
                    }
                    break;
                case 10:
                    if (zza((zzlg<T>) t, i7, i, i2, i13)) {
                        zzb2 = zzjc.zzc(i10, (zzik) unsafe.getObject(t, j));
                        i8 += zzb2;
                    }
                    break;
                case 11:
                    if (zza((zzlg<T>) t, i7, i, i2, i13)) {
                        zzb2 = zzjc.zzj(i10, unsafe.getInt(t, j));
                        i8 += zzb2;
                    }
                    break;
                case 12:
                    if (zza((zzlg<T>) t, i7, i, i2, i13)) {
                        zzb2 = zzjc.zze(i10, unsafe.getInt(t, j));
                        i8 += zzb2;
                    }
                    break;
                case 13:
                    if (zza((zzlg<T>) t, i7, i, i2, i13)) {
                        zzh = zzjc.zzh(i10, 0);
                        i8 += zzh;
                    }
                    break;
                case 14:
                    if (zza((zzlg<T>) t, i7, i, i2, i13)) {
                        zzb2 = zzjc.zze(i10, 0L);
                        i8 += zzb2;
                    }
                    break;
                case 15:
                    if (zza((zzlg<T>) t, i7, i, i2, i13)) {
                        zzb2 = zzjc.zzi(i10, unsafe.getInt(t, j));
                        i8 += zzb2;
                    }
                    break;
                case 16:
                    if (zza((zzlg<T>) t, i7, i, i2, i13)) {
                        zzb2 = zzjc.zzf(i10, unsafe.getLong(t, j));
                        i8 += zzb2;
                    }
                    break;
                case 17:
                    if (zza((zzlg<T>) t, i7, i, i2, i13)) {
                        zzb2 = zzjc.zzb(i10, (zzlc) unsafe.getObject(t, j), zze(i7));
                        i8 += zzb2;
                    }
                    break;
                case 18:
                    zzb2 = zzlw.zzd(i10, (List) unsafe.getObject(t, j), r9);
                    i8 += zzb2;
                    break;
                case 19:
                    zzb2 = zzlw.zzc(i10, (List) unsafe.getObject(t, j), r9);
                    i8 += zzb2;
                    break;
                case 20:
                    zzb2 = zzlw.zzf(i10, (List) unsafe.getObject(t, j), r9);
                    i8 += zzb2;
                    break;
                case 21:
                    zzb2 = zzlw.zzj(i10, (List) unsafe.getObject(t, j), r9);
                    i8 += zzb2;
                    break;
                case 22:
                    zzb2 = zzlw.zze(i10, (List) unsafe.getObject(t, j), r9);
                    i8 += zzb2;
                    break;
                case 23:
                    zzb2 = zzlw.zzd(i10, (List) unsafe.getObject(t, j), r9);
                    i8 += zzb2;
                    break;
                case 24:
                    zzb2 = zzlw.zzc(i10, (List) unsafe.getObject(t, j), r9);
                    i8 += zzb2;
                    break;
                case 25:
                    zzb2 = zzlw.zza(i10, (List<?>) unsafe.getObject(t, j), (boolean) r9);
                    i8 += zzb2;
                    break;
                case 26:
                    zzb2 = zzlw.zzb(i10, (List) unsafe.getObject(t, j));
                    i8 += zzb2;
                    break;
                case 27:
                    zzb2 = zzlw.zzb(i10, (List<?>) unsafe.getObject(t, j), (zzlu<?>) zze(i7));
                    i8 += zzb2;
                    break;
                case 28:
                    zzb2 = zzlw.zza(i10, (List<zzik>) unsafe.getObject(t, j));
                    i8 += zzb2;
                    break;
                case 29:
                    zzb2 = zzlw.zzi(i10, (List) unsafe.getObject(t, j), r9);
                    i8 += zzb2;
                    break;
                case 30:
                    zzb2 = zzlw.zzb(i10, (List<Integer>) unsafe.getObject(t, j), (boolean) r9);
                    i8 += zzb2;
                    break;
                case 31:
                    zzb2 = zzlw.zzc(i10, (List) unsafe.getObject(t, j), r9);
                    i8 += zzb2;
                    break;
                case 32:
                    zzb2 = zzlw.zzd(i10, (List) unsafe.getObject(t, j), r9);
                    i8 += zzb2;
                    break;
                case 33:
                    zzb2 = zzlw.zzg(i10, (List) unsafe.getObject(t, j), r9);
                    i8 += zzb2;
                    break;
                case 34:
                    zzb2 = zzlw.zzh(i10, (List) unsafe.getObject(t, j), r9);
                    i8 += zzb2;
                    break;
                case 35:
                    zzd = zzlw.zzd((List) unsafe.getObject(t, j));
                    if (zzd > 0) {
                        zzi = zzjc.zzi(i10);
                        zzj = zzjc.zzj(zzd);
                        zzh = zzi + zzj + zzd;
                        i8 += zzh;
                    }
                    break;
                case 36:
                    zzd = zzlw.zzc((List) unsafe.getObject(t, j));
                    if (zzd > 0) {
                        zzi = zzjc.zzi(i10);
                        zzj = zzjc.zzj(zzd);
                        zzh = zzi + zzj + zzd;
                        i8 += zzh;
                    }
                    break;
                case 37:
                    zzd = zzlw.zzf((List) unsafe.getObject(t, j));
                    if (zzd > 0) {
                        zzi = zzjc.zzi(i10);
                        zzj = zzjc.zzj(zzd);
                        zzh = zzi + zzj + zzd;
                        i8 += zzh;
                    }
                    break;
                case 38:
                    zzd = zzlw.zzj((List) unsafe.getObject(t, j));
                    if (zzd > 0) {
                        zzi = zzjc.zzi(i10);
                        zzj = zzjc.zzj(zzd);
                        zzh = zzi + zzj + zzd;
                        i8 += zzh;
                    }
                    break;
                case 39:
                    zzd = zzlw.zze((List) unsafe.getObject(t, j));
                    if (zzd > 0) {
                        zzi = zzjc.zzi(i10);
                        zzj = zzjc.zzj(zzd);
                        zzh = zzi + zzj + zzd;
                        i8 += zzh;
                    }
                    break;
                case 40:
                    zzd = zzlw.zzd((List) unsafe.getObject(t, j));
                    if (zzd > 0) {
                        zzi = zzjc.zzi(i10);
                        zzj = zzjc.zzj(zzd);
                        zzh = zzi + zzj + zzd;
                        i8 += zzh;
                    }
                    break;
                case 41:
                    zzd = zzlw.zzc((List) unsafe.getObject(t, j));
                    if (zzd > 0) {
                        zzi = zzjc.zzi(i10);
                        zzj = zzjc.zzj(zzd);
                        zzh = zzi + zzj + zzd;
                        i8 += zzh;
                    }
                    break;
                case 42:
                    zzd = zzlw.zza((List<?>) unsafe.getObject(t, j));
                    if (zzd > 0) {
                        zzi = zzjc.zzi(i10);
                        zzj = zzjc.zzj(zzd);
                        zzh = zzi + zzj + zzd;
                        i8 += zzh;
                    }
                    break;
                case 43:
                    zzd = zzlw.zzi((List) unsafe.getObject(t, j));
                    if (zzd > 0) {
                        zzi = zzjc.zzi(i10);
                        zzj = zzjc.zzj(zzd);
                        zzh = zzi + zzj + zzd;
                        i8 += zzh;
                    }
                    break;
                case 44:
                    zzd = zzlw.zzb((List) unsafe.getObject(t, j));
                    if (zzd > 0) {
                        zzi = zzjc.zzi(i10);
                        zzj = zzjc.zzj(zzd);
                        zzh = zzi + zzj + zzd;
                        i8 += zzh;
                    }
                    break;
                case 45:
                    zzd = zzlw.zzc((List) unsafe.getObject(t, j));
                    if (zzd > 0) {
                        zzi = zzjc.zzi(i10);
                        zzj = zzjc.zzj(zzd);
                        zzh = zzi + zzj + zzd;
                        i8 += zzh;
                    }
                    break;
                case 46:
                    zzd = zzlw.zzd((List) unsafe.getObject(t, j));
                    if (zzd > 0) {
                        zzi = zzjc.zzi(i10);
                        zzj = zzjc.zzj(zzd);
                        zzh = zzi + zzj + zzd;
                        i8 += zzh;
                    }
                    break;
                case 47:
                    zzd = zzlw.zzg((List) unsafe.getObject(t, j));
                    if (zzd > 0) {
                        zzi = zzjc.zzi(i10);
                        zzj = zzjc.zzj(zzd);
                        zzh = zzi + zzj + zzd;
                        i8 += zzh;
                    }
                    break;
                case 48:
                    zzd = zzlw.zzh((List) unsafe.getObject(t, j));
                    if (zzd > 0) {
                        zzi = zzjc.zzi(i10);
                        zzj = zzjc.zzj(zzd);
                        zzh = zzi + zzj + zzd;
                        i8 += zzh;
                    }
                    break;
                case 49:
                    zzb2 = zzlw.zza(i10, (List<zzlc>) unsafe.getObject(t, j), (zzlu<?>) zze(i7));
                    i8 += zzb2;
                    break;
                case 50:
                    zzb2 = this.zzr.zza(i10, unsafe.getObject(t, j), zzf(i7));
                    i8 += zzb2;
                    break;
                case 51:
                    if (zzc((zzlg<T>) t, i10, i7)) {
                        zzb2 = zzjc.zza(i10, Utils.DOUBLE_EPSILON);
                        i8 += zzb2;
                    }
                    break;
                case 52:
                    if (zzc((zzlg<T>) t, i10, i7)) {
                        zzb2 = zzjc.zza(i10, 0.0f);
                        i8 += zzb2;
                    }
                    break;
                case 53:
                    if (zzc((zzlg<T>) t, i10, i7)) {
                        zzb2 = zzjc.zzd(i10, zzd(t, j));
                        i8 += zzb2;
                    }
                    break;
                case 54:
                    if (zzc((zzlg<T>) t, i10, i7)) {
                        zzb2 = zzjc.zzg(i10, zzd(t, j));
                        i8 += zzb2;
                    }
                    break;
                case 55:
                    if (zzc((zzlg<T>) t, i10, i7)) {
                        zzb2 = zzjc.zzg(i10, zzc(t, j));
                        i8 += zzb2;
                    }
                    break;
                case 56:
                    if (zzc((zzlg<T>) t, i10, i7)) {
                        zzb2 = zzjc.zzc(i10, 0L);
                        i8 += zzb2;
                    }
                    break;
                case 57:
                    if (zzc((zzlg<T>) t, i10, i7)) {
                        zzb2 = zzjc.zzf(i10, (int) r9);
                        i8 += zzb2;
                    }
                    break;
                case 58:
                    if (zzc((zzlg<T>) t, i10, i7)) {
                        zzb2 = zzjc.zzb(i10, true);
                        i8 += zzb2;
                    }
                    break;
                case 59:
                    if (zzc((zzlg<T>) t, i10, i7)) {
                        Object object2 = unsafe.getObject(t, j);
                        if (object2 instanceof zzik) {
                            zzb2 = zzjc.zzc(i10, (zzik) object2);
                        } else {
                            zzb2 = zzjc.zzb(i10, (String) object2);
                        }
                        i8 += zzb2;
                    }
                    break;
                case 60:
                    if (zzc((zzlg<T>) t, i10, i7)) {
                        zzb2 = zzlw.zza(i10, unsafe.getObject(t, j), (zzlu<?>) zze(i7));
                        i8 += zzb2;
                    }
                    break;
                case 61:
                    if (zzc((zzlg<T>) t, i10, i7)) {
                        zzb2 = zzjc.zzc(i10, (zzik) unsafe.getObject(t, j));
                        i8 += zzb2;
                    }
                    break;
                case 62:
                    if (zzc((zzlg<T>) t, i10, i7)) {
                        zzb2 = zzjc.zzj(i10, zzc(t, j));
                        i8 += zzb2;
                    }
                    break;
                case 63:
                    if (zzc((zzlg<T>) t, i10, i7)) {
                        zzb2 = zzjc.zze(i10, zzc(t, j));
                        i8 += zzb2;
                    }
                    break;
                case 64:
                    if (zzc((zzlg<T>) t, i10, i7)) {
                        zzb2 = zzjc.zzh(i10, (int) r9);
                        i8 += zzb2;
                    }
                    break;
                case 65:
                    if (zzc((zzlg<T>) t, i10, i7)) {
                        zzb2 = zzjc.zze(i10, 0L);
                        i8 += zzb2;
                    }
                    break;
                case 66:
                    if (zzc((zzlg<T>) t, i10, i7)) {
                        zzb2 = zzjc.zzi(i10, zzc(t, j));
                        i8 += zzb2;
                    }
                    break;
                case 67:
                    if (zzc((zzlg<T>) t, i10, i7)) {
                        zzb2 = zzjc.zzf(i10, zzd(t, j));
                        i8 += zzb2;
                    }
                    break;
                case 68:
                    if (zzc((zzlg<T>) t, i10, i7)) {
                        zzb2 = zzjc.zzb(i10, (zzlc) unsafe.getObject(t, j), zze(i7));
                        i8 += zzb2;
                    }
                    break;
            }
            i7 += 3;
            i5 = i;
            i6 = i2;
            r9 = 0;
            i4 = 1048575;
        }
        int i14 = 0;
        zzmk<?, ?> zzmkVar = this.zzp;
        int zza3 = i8 + zzmkVar.zza((zzmk<?, ?>) zzmkVar.zzd(t));
        if (!this.zzh) {
            return zza3;
        }
        zzjm<?> zza4 = this.zzq.zza(t);
        int zza5 = zza4.zza.zza();
        for (int i15 = 0; i15 < zza5; i15++) {
            Map.Entry<?, Object> zza6 = zza4.zza.zza(i15);
            i14 += zzjm.zza((zzjo<?>) zza6.getKey(), zza6.getValue());
        }
        for (Map.Entry<?, Object> entry : zza4.zza.zzb()) {
            i14 += zzjm.zza((zzjo<?>) entry.getKey(), entry.getValue());
        }
        return zza3 + i14;
    }

    @Override // com.google.android.gms.internal.measurement.zzlu
    public final int zzb(T t) {
        int i;
        int zza2;
        int length = this.zzc.length;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3 += 3) {
            int zzc = zzc(i3);
            int i4 = this.zzc[i3];
            long j = 1048575 & zzc;
            int i5 = 37;
            switch ((zzc & 267386880) >>> 20) {
                case 0:
                    i = i2 * 53;
                    zza2 = zzjv.zza(Double.doubleToLongBits(zzml.zza(t, j)));
                    i2 = i + zza2;
                    break;
                case 1:
                    i = i2 * 53;
                    zza2 = Float.floatToIntBits(zzml.zzb(t, j));
                    i2 = i + zza2;
                    break;
                case 2:
                    i = i2 * 53;
                    zza2 = zzjv.zza(zzml.zzd(t, j));
                    i2 = i + zza2;
                    break;
                case 3:
                    i = i2 * 53;
                    zza2 = zzjv.zza(zzml.zzd(t, j));
                    i2 = i + zza2;
                    break;
                case 4:
                    i = i2 * 53;
                    zza2 = zzml.zzc(t, j);
                    i2 = i + zza2;
                    break;
                case 5:
                    i = i2 * 53;
                    zza2 = zzjv.zza(zzml.zzd(t, j));
                    i2 = i + zza2;
                    break;
                case 6:
                    i = i2 * 53;
                    zza2 = zzml.zzc(t, j);
                    i2 = i + zza2;
                    break;
                case 7:
                    i = i2 * 53;
                    zza2 = zzjv.zza(zzml.zzh(t, j));
                    i2 = i + zza2;
                    break;
                case 8:
                    i = i2 * 53;
                    zza2 = ((String) zzml.zze(t, j)).hashCode();
                    i2 = i + zza2;
                    break;
                case 9:
                    Object zze = zzml.zze(t, j);
                    if (zze != null) {
                        i5 = zze.hashCode();
                    }
                    i2 = (i2 * 53) + i5;
                    break;
                case 10:
                    i = i2 * 53;
                    zza2 = zzml.zze(t, j).hashCode();
                    i2 = i + zza2;
                    break;
                case 11:
                    i = i2 * 53;
                    zza2 = zzml.zzc(t, j);
                    i2 = i + zza2;
                    break;
                case 12:
                    i = i2 * 53;
                    zza2 = zzml.zzc(t, j);
                    i2 = i + zza2;
                    break;
                case 13:
                    i = i2 * 53;
                    zza2 = zzml.zzc(t, j);
                    i2 = i + zza2;
                    break;
                case 14:
                    i = i2 * 53;
                    zza2 = zzjv.zza(zzml.zzd(t, j));
                    i2 = i + zza2;
                    break;
                case 15:
                    i = i2 * 53;
                    zza2 = zzml.zzc(t, j);
                    i2 = i + zza2;
                    break;
                case 16:
                    i = i2 * 53;
                    zza2 = zzjv.zza(zzml.zzd(t, j));
                    i2 = i + zza2;
                    break;
                case 17:
                    Object zze2 = zzml.zze(t, j);
                    if (zze2 != null) {
                        i5 = zze2.hashCode();
                    }
                    i2 = (i2 * 53) + i5;
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    i = i2 * 53;
                    zza2 = zzml.zze(t, j).hashCode();
                    i2 = i + zza2;
                    break;
                case 50:
                    i = i2 * 53;
                    zza2 = zzml.zze(t, j).hashCode();
                    i2 = i + zza2;
                    break;
                case 51:
                    if (zzc((zzlg<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zza2 = zzjv.zza(Double.doubleToLongBits(zza(t, j)));
                        i2 = i + zza2;
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (zzc((zzlg<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zza2 = Float.floatToIntBits(zzb(t, j));
                        i2 = i + zza2;
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (zzc((zzlg<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zza2 = zzjv.zza(zzd(t, j));
                        i2 = i + zza2;
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (zzc((zzlg<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zza2 = zzjv.zza(zzd(t, j));
                        i2 = i + zza2;
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (zzc((zzlg<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zza2 = zzc(t, j);
                        i2 = i + zza2;
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (zzc((zzlg<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zza2 = zzjv.zza(zzd(t, j));
                        i2 = i + zza2;
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (zzc((zzlg<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zza2 = zzc(t, j);
                        i2 = i + zza2;
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (zzc((zzlg<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zza2 = zzjv.zza(zze(t, j));
                        i2 = i + zza2;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (zzc((zzlg<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zza2 = ((String) zzml.zze(t, j)).hashCode();
                        i2 = i + zza2;
                        break;
                    } else {
                        break;
                    }
                case 60:
                    if (zzc((zzlg<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zza2 = zzml.zze(t, j).hashCode();
                        i2 = i + zza2;
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (zzc((zzlg<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zza2 = zzml.zze(t, j).hashCode();
                        i2 = i + zza2;
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (zzc((zzlg<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zza2 = zzc(t, j);
                        i2 = i + zza2;
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (zzc((zzlg<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zza2 = zzc(t, j);
                        i2 = i + zza2;
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (zzc((zzlg<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zza2 = zzc(t, j);
                        i2 = i + zza2;
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (zzc((zzlg<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zza2 = zzjv.zza(zzd(t, j));
                        i2 = i + zza2;
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (zzc((zzlg<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zza2 = zzc(t, j);
                        i2 = i + zza2;
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (zzc((zzlg<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zza2 = zzjv.zza(zzd(t, j));
                        i2 = i + zza2;
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (zzc((zzlg<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zza2 = zzml.zze(t, j).hashCode();
                        i2 = i + zza2;
                        break;
                    } else {
                        break;
                    }
            }
        }
        int hashCode = (i2 * 53) + this.zzp.zzd(t).hashCode();
        return this.zzh ? (hashCode * 53) + this.zzq.zza(t).hashCode() : hashCode;
    }

    private static <T> int zzc(T t, long j) {
        return ((Integer) zzml.zze(t, j)).intValue();
    }

    /* JADX WARN: Code restructure failed: missing block: B:42:0x0d40, code lost:
    
        if (r13 == 1048575) goto L1232;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0d42, code lost:
    
        r28.putInt(r15, r13, r12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0d48, code lost:
    
        r6 = r11.zzl;
        r3 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:451:0x0a73, code lost:
    
        throw com.google.android.gms.internal.measurement.zzkb.zzi();
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0d4f, code lost:
    
        if (r6 >= r11.zzm) goto L1350;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0d51, code lost:
    
        r3 = (com.google.android.gms.internal.measurement.zzmj) zza((java.lang.Object) r32, r11.zzk[r6], (int) r3, (com.google.android.gms.internal.measurement.zzmk<UT, int>) r11.zzp, (java.lang.Object) r32);
        r6 = r6 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0d67, code lost:
    
        if (r3 == null) goto L1238;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0d69, code lost:
    
        r11.zzp.zzb((java.lang.Object) r15, (T) r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0d6e, code lost:
    
        if (r8 != 0) goto L1244;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0d72, code lost:
    
        if (r7 != r35) goto L1242;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0d79, code lost:
    
        throw com.google.android.gms.internal.measurement.zzkb.zzg();
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0d80, code lost:
    
        return r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0d7c, code lost:
    
        if (r7 > r35) goto L1248;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0d7e, code lost:
    
        if (r10 != r8) goto L1248;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0d85, code lost:
    
        throw com.google.android.gms.internal.measurement.zzkb.zzg();
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0948 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0939 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:462:0x0d1f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:466:0x0cb4 A[SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r1v57, types: [int] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final int zza(T r32, byte[] r33, int r34, int r35, int r36, com.google.android.gms.internal.measurement.zzij r37) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 3610
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzlg.zza(java.lang.Object, byte[], int, int, int, com.google.android.gms.internal.measurement.zzij):int");
    }

    private final int zza(int i) {
        if (i < this.zze || i > this.zzf) {
            return -1;
        }
        return zza(i, 0);
    }

    private final int zzb(int i) {
        return this.zzc[i + 2];
    }

    private final int zza(int i, int i2) {
        int length = (this.zzc.length / 3) - 1;
        while (i2 <= length) {
            int i3 = (length + i2) >>> 1;
            int i4 = i3 * 3;
            int i5 = this.zzc[i4];
            if (i == i5) {
                return i4;
            }
            if (i < i5) {
                length = i3 - 1;
            } else {
                i2 = i3 + 1;
            }
        }
        return -1;
    }

    private final int zzc(int i) {
        return this.zzc[i + 1];
    }

    private static <T> long zzd(T t, long j) {
        return ((Long) zzml.zze(t, j)).longValue();
    }

    private final zzjx zzd(int i) {
        return (zzjx) this.zzd[((i / 3) << 1) + 1];
    }

    /* JADX WARN: Removed duplicated region for block: B:111:0x033a  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0395  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0263  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x027d  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0280  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0266  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static <T> com.google.android.gms.internal.measurement.zzlg<T> zza(java.lang.Class<T> r32, com.google.android.gms.internal.measurement.zzla r33, com.google.android.gms.internal.measurement.zzlk r34, com.google.android.gms.internal.measurement.zzkm r35, com.google.android.gms.internal.measurement.zzmk<?, ?> r36, com.google.android.gms.internal.measurement.zzji<?> r37, com.google.android.gms.internal.measurement.zzkv r38) {
        /*
            Method dump skipped, instructions count: 1038
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzlg.zza(java.lang.Class, com.google.android.gms.internal.measurement.zzla, com.google.android.gms.internal.measurement.zzlk, com.google.android.gms.internal.measurement.zzkm, com.google.android.gms.internal.measurement.zzmk, com.google.android.gms.internal.measurement.zzji, com.google.android.gms.internal.measurement.zzkv):com.google.android.gms.internal.measurement.zzlg");
    }

    private final zzlu zze(int i) {
        int i2 = (i / 3) << 1;
        zzlu zzluVar = (zzlu) this.zzd[i2];
        if (zzluVar != null) {
            return zzluVar;
        }
        zzlu<T> zza2 = zzlq.zza().zza((Class) this.zzd[i2 + 1]);
        this.zzd[i2] = zza2;
        return zza2;
    }

    static zzmj zzc(Object obj) {
        zzjt zzjtVar = (zzjt) obj;
        zzmj zzmjVar = zzjtVar.zzb;
        if (zzmjVar != zzmj.zzc()) {
            return zzmjVar;
        }
        zzmj zzd = zzmj.zzd();
        zzjtVar.zzb = zzd;
        return zzd;
    }

    private final <UT, UB> UB zza(Object obj, int i, UB ub, zzmk<UT, UB> zzmkVar, Object obj2) {
        zzjx zzd;
        int i2 = this.zzc[i];
        Object zze = zzml.zze(obj, zzc(i) & 1048575);
        return (zze == null || (zzd = zzd(i)) == null) ? ub : (UB) zza(i, i2, this.zzr.zze(zze), zzd, (zzjx) ub, (zzmk<UT, zzjx>) zzmkVar, obj2);
    }

    private final <K, V, UT, UB> UB zza(int i, int i2, Map<K, V> map, zzjx zzjxVar, UB ub, zzmk<UT, UB> zzmkVar, Object obj) {
        zzkt<?, ?> zza2 = this.zzr.zza(zzf(i));
        Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<K, V> next = it.next();
            if (!zzjxVar.zza(((Integer) next.getValue()).intValue())) {
                if (ub == null) {
                    ub = zzmkVar.zzc(obj);
                }
                zzit zzc = zzik.zzc(zzku.zza(zza2, next.getKey(), next.getValue()));
                try {
                    zzku.zza(zzc.zzb(), zza2, next.getKey(), next.getValue());
                    zzmkVar.zza((zzmk<UT, UB>) ub, i2, zzc.zza());
                    it.remove();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return ub;
    }

    private final Object zzf(int i) {
        return this.zzd[(i / 3) << 1];
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final Object zza(T t, int i) {
        zzlu zze = zze(i);
        long zzc = zzc(i) & 1048575;
        if (!zzc((zzlg<T>) t, i)) {
            return zze.zza();
        }
        Object object = zzb.getObject(t, zzc);
        if (zzg(object)) {
            return object;
        }
        Object zza2 = zze.zza();
        if (object != null) {
            zze.zza(zza2, object);
        }
        return zza2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final Object zza(T t, int i, int i2) {
        zzlu zze = zze(i2);
        if (!zzc((zzlg<T>) t, i, i2)) {
            return zze.zza();
        }
        Object object = zzb.getObject(t, zzc(i2) & 1048575);
        if (zzg(object)) {
            return object;
        }
        Object zza2 = zze.zza();
        if (object != null) {
            zze.zza(zza2, object);
        }
        return zza2;
    }

    @Override // com.google.android.gms.internal.measurement.zzlu
    public final T zza() {
        return (T) this.zzn.zza(this.zzg);
    }

    private static Field zza(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            throw new RuntimeException("Field " + str + " for " + cls.getName() + " not found. Known fields are " + Arrays.toString(declaredFields));
        }
    }

    private zzlg(int[] iArr, Object[] objArr, int i, int i2, zzlc zzlcVar, boolean z, int[] iArr2, int i3, int i4, zzlk zzlkVar, zzkm zzkmVar, zzmk<?, ?> zzmkVar, zzji<?> zzjiVar, zzkv zzkvVar) {
        this.zzc = iArr;
        this.zzd = objArr;
        this.zze = i;
        this.zzf = i2;
        this.zzi = zzlcVar instanceof zzjt;
        this.zzh = zzjiVar != null && zzjiVar.zza(zzlcVar);
        this.zzj = false;
        this.zzk = iArr2;
        this.zzl = i3;
        this.zzm = i4;
        this.zzn = zzlkVar;
        this.zzo = zzkmVar;
        this.zzp = zzmkVar;
        this.zzq = zzjiVar;
        this.zzg = zzlcVar;
        this.zzr = zzkvVar;
    }

    private static void zzf(Object obj) {
        if (zzg(obj)) {
            return;
        }
        throw new IllegalArgumentException("Mutating immutable message: " + String.valueOf(obj));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.measurement.zzlu
    public final void zzd(T t) {
        if (zzg(t)) {
            if (t instanceof zzjt) {
                zzjt zzjtVar = (zzjt) t;
                zzjtVar.zzc(Integer.MAX_VALUE);
                zzjtVar.zza = 0;
                zzjtVar.zzcm();
            }
            int length = this.zzc.length;
            for (int i = 0; i < length; i += 3) {
                int zzc = zzc(i);
                long j = 1048575 & zzc;
                int i2 = (zzc & 267386880) >>> 20;
                if (i2 != 9) {
                    if (i2 == 60 || i2 == 68) {
                        if (zzc((zzlg<T>) t, this.zzc[i], i)) {
                            zze(i).zzd(zzb.getObject(t, j));
                        }
                    } else {
                        switch (i2) {
                            case 18:
                            case 19:
                            case 20:
                            case 21:
                            case 22:
                            case 23:
                            case 24:
                            case 25:
                            case 26:
                            case 27:
                            case 28:
                            case 29:
                            case 30:
                            case 31:
                            case 32:
                            case 33:
                            case 34:
                            case 35:
                            case 36:
                            case 37:
                            case 38:
                            case 39:
                            case 40:
                            case 41:
                            case 42:
                            case 43:
                            case 44:
                            case 45:
                            case 46:
                            case 47:
                            case 48:
                            case 49:
                                this.zzo.zzb(t, j);
                                break;
                            case 50:
                                Unsafe unsafe = zzb;
                                Object object = unsafe.getObject(t, j);
                                if (object != null) {
                                    unsafe.putObject(t, j, this.zzr.zzc(object));
                                    break;
                                } else {
                                    break;
                                }
                        }
                    }
                }
                if (zzc((zzlg<T>) t, i)) {
                    zze(i).zzd(zzb.getObject(t, j));
                }
            }
            this.zzp.zzf(t);
            if (this.zzh) {
                this.zzq.zzc(t);
            }
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzlu
    public final void zza(T t, T t2) {
        zzf(t);
        t2.getClass();
        for (int i = 0; i < this.zzc.length; i += 3) {
            int zzc = zzc(i);
            long j = 1048575 & zzc;
            int i2 = this.zzc[i];
            switch ((zzc & 267386880) >>> 20) {
                case 0:
                    if (zzc((zzlg<T>) t2, i)) {
                        zzml.zza(t, j, zzml.zza(t2, j));
                        zzb((zzlg<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (zzc((zzlg<T>) t2, i)) {
                        zzml.zza((Object) t, j, zzml.zzb(t2, j));
                        zzb((zzlg<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (zzc((zzlg<T>) t2, i)) {
                        zzml.zza((Object) t, j, zzml.zzd(t2, j));
                        zzb((zzlg<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (zzc((zzlg<T>) t2, i)) {
                        zzml.zza((Object) t, j, zzml.zzd(t2, j));
                        zzb((zzlg<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (zzc((zzlg<T>) t2, i)) {
                        zzml.zza((Object) t, j, zzml.zzc(t2, j));
                        zzb((zzlg<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (zzc((zzlg<T>) t2, i)) {
                        zzml.zza((Object) t, j, zzml.zzd(t2, j));
                        zzb((zzlg<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (zzc((zzlg<T>) t2, i)) {
                        zzml.zza((Object) t, j, zzml.zzc(t2, j));
                        zzb((zzlg<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (zzc((zzlg<T>) t2, i)) {
                        zzml.zzc(t, j, zzml.zzh(t2, j));
                        zzb((zzlg<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (zzc((zzlg<T>) t2, i)) {
                        zzml.zza(t, j, zzml.zze(t2, j));
                        zzb((zzlg<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 9:
                    zza(t, t2, i);
                    break;
                case 10:
                    if (zzc((zzlg<T>) t2, i)) {
                        zzml.zza(t, j, zzml.zze(t2, j));
                        zzb((zzlg<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (zzc((zzlg<T>) t2, i)) {
                        zzml.zza((Object) t, j, zzml.zzc(t2, j));
                        zzb((zzlg<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (zzc((zzlg<T>) t2, i)) {
                        zzml.zza((Object) t, j, zzml.zzc(t2, j));
                        zzb((zzlg<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (zzc((zzlg<T>) t2, i)) {
                        zzml.zza((Object) t, j, zzml.zzc(t2, j));
                        zzb((zzlg<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (zzc((zzlg<T>) t2, i)) {
                        zzml.zza((Object) t, j, zzml.zzd(t2, j));
                        zzb((zzlg<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (zzc((zzlg<T>) t2, i)) {
                        zzml.zza((Object) t, j, zzml.zzc(t2, j));
                        zzb((zzlg<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (zzc((zzlg<T>) t2, i)) {
                        zzml.zza((Object) t, j, zzml.zzd(t2, j));
                        zzb((zzlg<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 17:
                    zza(t, t2, i);
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    this.zzo.zza(t, t2, j);
                    break;
                case 50:
                    zzlw.zza(this.zzr, t, t2, j);
                    break;
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                    if (zzc((zzlg<T>) t2, i2, i)) {
                        zzml.zza(t, j, zzml.zze(t2, j));
                        zzb((zzlg<T>) t, i2, i);
                        break;
                    } else {
                        break;
                    }
                case 60:
                    zzb(t, t2, i);
                    break;
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                    if (zzc((zzlg<T>) t2, i2, i)) {
                        zzml.zza(t, j, zzml.zze(t2, j));
                        zzb((zzlg<T>) t, i2, i);
                        break;
                    } else {
                        break;
                    }
                case 68:
                    zzb(t, t2, i);
                    break;
            }
        }
        zzlw.zza(this.zzp, t, t2);
        if (this.zzh) {
            zzlw.zza(this.zzq, t, t2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:219:0x065b A[LOOP:3: B:217:0x0657->B:219:0x065b, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:222:0x066f  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0625 A[Catch: all -> 0x0297, TryCatch #3 {all -> 0x0297, blocks: (B:201:0x05f6, B:35:0x0620, B:37:0x0625, B:38:0x062a, B:29:0x00cc, B:52:0x00de, B:54:0x00f0, B:56:0x0102, B:58:0x0113, B:60:0x0124, B:62:0x012e, B:65:0x0135, B:66:0x013b, B:68:0x0148, B:70:0x0159, B:72:0x0166, B:74:0x0177, B:76:0x0182, B:78:0x0193, B:80:0x01a4, B:82:0x01b5, B:84:0x01c6, B:86:0x01d7, B:88:0x01e8, B:90:0x01f9, B:92:0x020b, B:94:0x021b, B:95:0x023c, B:96:0x0225, B:98:0x022d, B:100:0x024d, B:102:0x025f, B:104:0x026d, B:106:0x027b, B:108:0x0289), top: B:200:0x05f6 }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0630 A[SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r19v0, types: [com.google.android.gms.internal.measurement.zzlr] */
    @Override // com.google.android.gms.internal.measurement.zzlu
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zza(T r18, com.google.android.gms.internal.measurement.zzlr r19, com.google.android.gms.internal.measurement.zzjg r20) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1794
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzlg.zza(java.lang.Object, com.google.android.gms.internal.measurement.zzlr, com.google.android.gms.internal.measurement.zzjg):void");
    }

    @Override // com.google.android.gms.internal.measurement.zzlu
    public final void zza(T t, byte[] bArr, int i, int i2, zzij zzijVar) throws IOException {
        zza((zzlg<T>) t, bArr, i, i2, 0, zzijVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void zza(T t, T t2, int i) {
        if (zzc((zzlg<T>) t2, i)) {
            long zzc = zzc(i) & 1048575;
            Unsafe unsafe = zzb;
            Object object = unsafe.getObject(t2, zzc);
            if (object == null) {
                throw new IllegalStateException("Source subfield " + this.zzc[i] + " is present but null: " + String.valueOf(t2));
            }
            zzlu zze = zze(i);
            if (!zzc((zzlg<T>) t, i)) {
                if (!zzg(object)) {
                    unsafe.putObject(t, zzc, object);
                } else {
                    Object zza2 = zze.zza();
                    zze.zza(zza2, object);
                    unsafe.putObject(t, zzc, zza2);
                }
                zzb((zzlg<T>) t, i);
                return;
            }
            Object object2 = unsafe.getObject(t, zzc);
            if (!zzg(object2)) {
                Object zza3 = zze.zza();
                zze.zza(zza3, object2);
                unsafe.putObject(t, zzc, zza3);
                object2 = zza3;
            }
            zze.zza(object2, object);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void zzb(T t, T t2, int i) {
        int i2 = this.zzc[i];
        if (zzc((zzlg<T>) t2, i2, i)) {
            long zzc = zzc(i) & 1048575;
            Unsafe unsafe = zzb;
            Object object = unsafe.getObject(t2, zzc);
            if (object == null) {
                throw new IllegalStateException("Source subfield " + this.zzc[i] + " is present but null: " + String.valueOf(t2));
            }
            zzlu zze = zze(i);
            if (!zzc((zzlg<T>) t, i2, i)) {
                if (!zzg(object)) {
                    unsafe.putObject(t, zzc, object);
                } else {
                    Object zza2 = zze.zza();
                    zze.zza(zza2, object);
                    unsafe.putObject(t, zzc, zza2);
                }
                zzb((zzlg<T>) t, i2, i);
                return;
            }
            Object object2 = unsafe.getObject(t, zzc);
            if (!zzg(object2)) {
                Object zza3 = zze.zza();
                zze.zza(zza3, object2);
                unsafe.putObject(t, zzc, zza3);
                object2 = zza3;
            }
            zze.zza(object2, object);
        }
    }

    private final void zza(Object obj, int i, zzlr zzlrVar) throws IOException {
        if (zzg(i)) {
            zzml.zza(obj, i & 1048575, zzlrVar.zzr());
        } else if (this.zzi) {
            zzml.zza(obj, i & 1048575, zzlrVar.zzq());
        } else {
            zzml.zza(obj, i & 1048575, zzlrVar.zzp());
        }
    }

    private final void zzb(T t, int i) {
        int zzb2 = zzb(i);
        long j = 1048575 & zzb2;
        if (j == 1048575) {
            return;
        }
        zzml.zza((Object) t, j, (1 << (zzb2 >>> 20)) | zzml.zzc(t, j));
    }

    private final void zzb(T t, int i, int i2) {
        zzml.zza((Object) t, zzb(i2) & 1048575, i);
    }

    private final void zza(T t, int i, Object obj) {
        zzb.putObject(t, zzc(i) & 1048575, obj);
        zzb((zzlg<T>) t, i);
    }

    private final void zza(T t, int i, int i2, Object obj) {
        zzb.putObject(t, zzc(i2) & 1048575, obj);
        zzb((zzlg<T>) t, i, i2);
    }

    private final <K, V> void zza(zznb zznbVar, int i, Object obj, int i2) throws IOException {
        if (obj != null) {
            zznbVar.zza(i, this.zzr.zza(zzf(i2)), this.zzr.zzd(obj));
        }
    }

    private static void zza(int i, Object obj, zznb zznbVar) throws IOException {
        if (obj instanceof String) {
            zznbVar.zza(i, (String) obj);
        } else {
            zznbVar.zza(i, (zzik) obj);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:277:0x0517  */
    /* JADX WARN: Removed duplicated region for block: B:297:0x0558  */
    /* JADX WARN: Removed duplicated region for block: B:509:0x0b8f  */
    @Override // com.google.android.gms.internal.measurement.zzlu
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zza(T r24, com.google.android.gms.internal.measurement.zznb r25) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 3270
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzlg.zza(java.lang.Object, com.google.android.gms.internal.measurement.zznb):void");
    }

    private static <UT, UB> void zza(zzmk<UT, UB> zzmkVar, T t, zznb zznbVar) throws IOException {
        zzmkVar.zzb((zzmk<UT, UB>) zzmkVar.zzd(t), zznbVar);
    }

    private final boolean zzc(T t, T t2, int i) {
        return zzc((zzlg<T>) t, i) == zzc((zzlg<T>) t2, i);
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x006a, code lost:
    
        if (com.google.android.gms.internal.measurement.zzlw.zza(com.google.android.gms.internal.measurement.zzml.zze(r10, r6), com.google.android.gms.internal.measurement.zzml.zze(r11, r6)) != false) goto L223;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x007e, code lost:
    
        if (com.google.android.gms.internal.measurement.zzml.zzd(r10, r6) == com.google.android.gms.internal.measurement.zzml.zzd(r11, r6)) goto L223;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0090, code lost:
    
        if (com.google.android.gms.internal.measurement.zzml.zzc(r10, r6) == com.google.android.gms.internal.measurement.zzml.zzc(r11, r6)) goto L223;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00a4, code lost:
    
        if (com.google.android.gms.internal.measurement.zzml.zzd(r10, r6) == com.google.android.gms.internal.measurement.zzml.zzd(r11, r6)) goto L223;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00b6, code lost:
    
        if (com.google.android.gms.internal.measurement.zzml.zzc(r10, r6) == com.google.android.gms.internal.measurement.zzml.zzc(r11, r6)) goto L223;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00c8, code lost:
    
        if (com.google.android.gms.internal.measurement.zzml.zzc(r10, r6) == com.google.android.gms.internal.measurement.zzml.zzc(r11, r6)) goto L223;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00da, code lost:
    
        if (com.google.android.gms.internal.measurement.zzml.zzc(r10, r6) == com.google.android.gms.internal.measurement.zzml.zzc(r11, r6)) goto L223;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00f0, code lost:
    
        if (com.google.android.gms.internal.measurement.zzlw.zza(com.google.android.gms.internal.measurement.zzml.zze(r10, r6), com.google.android.gms.internal.measurement.zzml.zze(r11, r6)) != false) goto L223;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0106, code lost:
    
        if (com.google.android.gms.internal.measurement.zzlw.zza(com.google.android.gms.internal.measurement.zzml.zze(r10, r6), com.google.android.gms.internal.measurement.zzml.zze(r11, r6)) != false) goto L223;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x011c, code lost:
    
        if (com.google.android.gms.internal.measurement.zzlw.zza(com.google.android.gms.internal.measurement.zzml.zze(r10, r6), com.google.android.gms.internal.measurement.zzml.zze(r11, r6)) != false) goto L223;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x012e, code lost:
    
        if (com.google.android.gms.internal.measurement.zzml.zzh(r10, r6) == com.google.android.gms.internal.measurement.zzml.zzh(r11, r6)) goto L223;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0140, code lost:
    
        if (com.google.android.gms.internal.measurement.zzml.zzc(r10, r6) == com.google.android.gms.internal.measurement.zzml.zzc(r11, r6)) goto L223;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0154, code lost:
    
        if (com.google.android.gms.internal.measurement.zzml.zzd(r10, r6) == com.google.android.gms.internal.measurement.zzml.zzd(r11, r6)) goto L223;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0165, code lost:
    
        if (com.google.android.gms.internal.measurement.zzml.zzc(r10, r6) == com.google.android.gms.internal.measurement.zzml.zzc(r11, r6)) goto L223;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0178, code lost:
    
        if (com.google.android.gms.internal.measurement.zzml.zzd(r10, r6) == com.google.android.gms.internal.measurement.zzml.zzd(r11, r6)) goto L223;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x018b, code lost:
    
        if (com.google.android.gms.internal.measurement.zzml.zzd(r10, r6) == com.google.android.gms.internal.measurement.zzml.zzd(r11, r6)) goto L223;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x01a4, code lost:
    
        if (java.lang.Float.floatToIntBits(com.google.android.gms.internal.measurement.zzml.zzb(r10, r6)) == java.lang.Float.floatToIntBits(com.google.android.gms.internal.measurement.zzml.zzb(r11, r6))) goto L223;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x01bf, code lost:
    
        if (java.lang.Double.doubleToLongBits(com.google.android.gms.internal.measurement.zzml.zza(r10, r6)) == java.lang.Double.doubleToLongBits(com.google.android.gms.internal.measurement.zzml.zza(r11, r6))) goto L223;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0038, code lost:
    
        if (com.google.android.gms.internal.measurement.zzlw.zza(com.google.android.gms.internal.measurement.zzml.zze(r10, r6), com.google.android.gms.internal.measurement.zzml.zze(r11, r6)) != false) goto L223;
     */
    @Override // com.google.android.gms.internal.measurement.zzlu
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean zzb(T r10, T r11) {
        /*
            Method dump skipped, instructions count: 640
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzlg.zzb(java.lang.Object, java.lang.Object):boolean");
    }

    private final boolean zzc(T t, int i) {
        int zzb2 = zzb(i);
        long j = zzb2 & 1048575;
        if (j != 1048575) {
            return (zzml.zzc(t, j) & (1 << (zzb2 >>> 20))) != 0;
        }
        int zzc = zzc(i);
        long j2 = zzc & 1048575;
        switch ((zzc & 267386880) >>> 20) {
            case 0:
                return Double.doubleToRawLongBits(zzml.zza(t, j2)) != 0;
            case 1:
                return Float.floatToRawIntBits(zzml.zzb(t, j2)) != 0;
            case 2:
                return zzml.zzd(t, j2) != 0;
            case 3:
                return zzml.zzd(t, j2) != 0;
            case 4:
                return zzml.zzc(t, j2) != 0;
            case 5:
                return zzml.zzd(t, j2) != 0;
            case 6:
                return zzml.zzc(t, j2) != 0;
            case 7:
                return zzml.zzh(t, j2);
            case 8:
                Object zze = zzml.zze(t, j2);
                if (zze instanceof String) {
                    return !((String) zze).isEmpty();
                }
                if (zze instanceof zzik) {
                    return !zzik.zza.equals(zze);
                }
                throw new IllegalArgumentException();
            case 9:
                return zzml.zze(t, j2) != null;
            case 10:
                return !zzik.zza.equals(zzml.zze(t, j2));
            case 11:
                return zzml.zzc(t, j2) != 0;
            case 12:
                return zzml.zzc(t, j2) != 0;
            case 13:
                return zzml.zzc(t, j2) != 0;
            case 14:
                return zzml.zzd(t, j2) != 0;
            case 15:
                return zzml.zzc(t, j2) != 0;
            case 16:
                return zzml.zzd(t, j2) != 0;
            case 17:
                return zzml.zze(t, j2) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    private final boolean zza(T t, int i, int i2, int i3, int i4) {
        if (i2 == 1048575) {
            return zzc((zzlg<T>) t, i);
        }
        return (i3 & i4) != 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v21 */
    /* JADX WARN: Type inference failed for: r1v22 */
    /* JADX WARN: Type inference failed for: r1v23, types: [com.google.android.gms.internal.measurement.zzlu] */
    /* JADX WARN: Type inference failed for: r1v30 */
    /* JADX WARN: Type inference failed for: r1v31 */
    /* JADX WARN: Type inference failed for: r1v8, types: [com.google.android.gms.internal.measurement.zzlu] */
    @Override // com.google.android.gms.internal.measurement.zzlu
    public final boolean zze(T t) {
        int i;
        int i2;
        int i3 = 1048575;
        int i4 = 0;
        int i5 = 0;
        while (i5 < this.zzl) {
            int i6 = this.zzk[i5];
            int i7 = this.zzc[i6];
            int zzc = zzc(i6);
            int i8 = this.zzc[i6 + 2];
            int i9 = i8 & 1048575;
            int i10 = 1 << (i8 >>> 20);
            if (i9 != i3) {
                if (i9 != 1048575) {
                    i4 = zzb.getInt(t, i9);
                }
                i2 = i4;
                i = i9;
            } else {
                i = i3;
                i2 = i4;
            }
            if ((268435456 & zzc) != 0 && !zza((zzlg<T>) t, i6, i, i2, i10)) {
                return false;
            }
            int i11 = (267386880 & zzc) >>> 20;
            if (i11 == 9 || i11 == 17) {
                if (zza((zzlg<T>) t, i6, i, i2, i10) && !zza((Object) t, zzc, zze(i6))) {
                    return false;
                }
            } else {
                if (i11 != 27) {
                    if (i11 == 60 || i11 == 68) {
                        if (zzc((zzlg<T>) t, i7, i6) && !zza((Object) t, zzc, zze(i6))) {
                            return false;
                        }
                    } else if (i11 != 49) {
                        if (i11 != 50) {
                            continue;
                        } else {
                            Map<?, ?> zzd = this.zzr.zzd(zzml.zze(t, zzc & 1048575));
                            if (zzd.isEmpty()) {
                                continue;
                            } else if (this.zzr.zza(zzf(i6)).zzc.zzb() == zzmz.MESSAGE) {
                                ?? r1 = 0;
                                for (Object obj : zzd.values()) {
                                    r1 = r1;
                                    if (r1 == 0) {
                                        r1 = zzlq.zza().zza((Class) obj.getClass());
                                    }
                                    if (!r1.zze(obj)) {
                                        return false;
                                    }
                                }
                            } else {
                                continue;
                            }
                        }
                    }
                }
                List list = (List) zzml.zze(t, zzc & 1048575);
                if (list.isEmpty()) {
                    continue;
                } else {
                    ?? zze = zze(i6);
                    for (int i12 = 0; i12 < list.size(); i12++) {
                        if (!zze.zze(list.get(i12))) {
                            return false;
                        }
                    }
                }
            }
            i5++;
            i3 = i;
            i4 = i2;
        }
        return !this.zzh || this.zzq.zza(t).zzg();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static boolean zza(Object obj, int i, zzlu zzluVar) {
        return zzluVar.zze(zzml.zze(obj, i & 1048575));
    }

    private static boolean zzg(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof zzjt) {
            return ((zzjt) obj).zzco();
        }
        return true;
    }

    private final boolean zzc(T t, int i, int i2) {
        return zzml.zzc(t, (long) (zzb(i2) & 1048575)) == i;
    }

    private static <T> boolean zze(T t, long j) {
        return ((Boolean) zzml.zze(t, j)).booleanValue();
    }
}
