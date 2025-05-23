package com.google.android.gms.internal.auth;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import sun.misc.Unsafe;

/* compiled from: com.google.android.gms:play-services-auth-base@@18.0.4 */
/* loaded from: classes.dex */
final class zzfz<T> implements zzgh<T> {
    private static final int[] zza = new int[0];
    private static final Unsafe zzb = zzhi.zzg();
    private final int[] zzc;
    private final Object[] zzd;
    private final int zze;
    private final int zzf;
    private final zzfw zzg;
    private final boolean zzh;
    private final int[] zzi;
    private final int zzj;
    private final int zzk;
    private final zzfk zzl;
    private final zzgy zzm;
    private final zzel zzn;
    private final zzgb zzo;
    private final zzfr zzp;

    private zzfz(int[] iArr, Object[] objArr, int i, int i2, zzfw zzfwVar, boolean z, boolean z2, int[] iArr2, int i3, int i4, zzgb zzgbVar, zzfk zzfkVar, zzgy zzgyVar, zzel zzelVar, zzfr zzfrVar, byte[] bArr) {
        this.zzc = iArr;
        this.zzd = objArr;
        this.zze = i;
        this.zzf = i2;
        this.zzh = z;
        this.zzi = iArr2;
        this.zzj = i3;
        this.zzk = i4;
        this.zzo = zzgbVar;
        this.zzl = zzfkVar;
        this.zzm = zzgyVar;
        this.zzn = zzelVar;
        this.zzg = zzfwVar;
        this.zzp = zzfrVar;
    }

    private static Field zzA(Class cls, String str) {
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

    private final void zzB(Object obj, Object obj2, int i) {
        long zzv = zzv(i) & 1048575;
        if (zzG(obj2, i)) {
            Object zzf = zzhi.zzf(obj, zzv);
            Object zzf2 = zzhi.zzf(obj2, zzv);
            if (zzf != null && zzf2 != null) {
                zzhi.zzp(obj, zzv, zzez.zzg(zzf, zzf2));
                zzD(obj, i);
            } else if (zzf2 != null) {
                zzhi.zzp(obj, zzv, zzf2);
                zzD(obj, i);
            }
        }
    }

    private final void zzC(Object obj, Object obj2, int i) {
        int zzv = zzv(i);
        int i2 = this.zzc[i];
        long j = zzv & 1048575;
        if (zzJ(obj2, i2, i)) {
            Object zzf = zzJ(obj, i2, i) ? zzhi.zzf(obj, j) : null;
            Object zzf2 = zzhi.zzf(obj2, j);
            if (zzf != null && zzf2 != null) {
                zzhi.zzp(obj, j, zzez.zzg(zzf, zzf2));
                zzE(obj, i2, i);
            } else if (zzf2 != null) {
                zzhi.zzp(obj, j, zzf2);
                zzE(obj, i2, i);
            }
        }
    }

    private final void zzD(Object obj, int i) {
        int zzs = zzs(i);
        long j = 1048575 & zzs;
        if (j == 1048575) {
            return;
        }
        zzhi.zzn(obj, j, (1 << (zzs >>> 20)) | zzhi.zzc(obj, j));
    }

    private final void zzE(Object obj, int i, int i2) {
        zzhi.zzn(obj, zzs(i2) & 1048575, i);
    }

    private final boolean zzF(Object obj, Object obj2, int i) {
        return zzG(obj, i) == zzG(obj2, i);
    }

    private final boolean zzG(Object obj, int i) {
        int zzs = zzs(i);
        long j = zzs & 1048575;
        if (j != 1048575) {
            return (zzhi.zzc(obj, j) & (1 << (zzs >>> 20))) != 0;
        }
        int zzv = zzv(i);
        long j2 = zzv & 1048575;
        switch (zzu(zzv)) {
            case 0:
                return Double.doubleToRawLongBits(zzhi.zza(obj, j2)) != 0;
            case 1:
                return Float.floatToRawIntBits(zzhi.zzb(obj, j2)) != 0;
            case 2:
                return zzhi.zzd(obj, j2) != 0;
            case 3:
                return zzhi.zzd(obj, j2) != 0;
            case 4:
                return zzhi.zzc(obj, j2) != 0;
            case 5:
                return zzhi.zzd(obj, j2) != 0;
            case 6:
                return zzhi.zzc(obj, j2) != 0;
            case 7:
                return zzhi.zzt(obj, j2);
            case 8:
                Object zzf = zzhi.zzf(obj, j2);
                if (zzf instanceof String) {
                    return !((String) zzf).isEmpty();
                }
                if (zzf instanceof zzee) {
                    return !zzee.zzb.equals(zzf);
                }
                throw new IllegalArgumentException();
            case 9:
                return zzhi.zzf(obj, j2) != null;
            case 10:
                return !zzee.zzb.equals(zzhi.zzf(obj, j2));
            case 11:
                return zzhi.zzc(obj, j2) != 0;
            case 12:
                return zzhi.zzc(obj, j2) != 0;
            case 13:
                return zzhi.zzc(obj, j2) != 0;
            case 14:
                return zzhi.zzd(obj, j2) != 0;
            case 15:
                return zzhi.zzc(obj, j2) != 0;
            case 16:
                return zzhi.zzd(obj, j2) != 0;
            case 17:
                return zzhi.zzf(obj, j2) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    private final boolean zzH(Object obj, int i, int i2, int i3, int i4) {
        return i2 == 1048575 ? zzG(obj, i) : (i3 & i4) != 0;
    }

    private static boolean zzI(Object obj, int i, zzgh zzghVar) {
        return zzghVar.zzi(zzhi.zzf(obj, i & 1048575));
    }

    private final boolean zzJ(Object obj, int i, int i2) {
        return zzhi.zzc(obj, (long) (zzs(i2) & 1048575)) == i;
    }

    static zzgz zzc(Object obj) {
        zzeu zzeuVar = (zzeu) obj;
        zzgz zzgzVar = zzeuVar.zzc;
        if (zzgzVar != zzgz.zza()) {
            return zzgzVar;
        }
        zzgz zzc = zzgz.zzc();
        zzeuVar.zzc = zzc;
        return zzc;
    }

    static zzfz zzj(Class cls, zzft zzftVar, zzgb zzgbVar, zzfk zzfkVar, zzgy zzgyVar, zzel zzelVar, zzfr zzfrVar) {
        if (zzftVar instanceof zzgg) {
            return zzk((zzgg) zzftVar, zzgbVar, zzfkVar, zzgyVar, zzelVar, zzfrVar);
        }
        throw null;
    }

    /* JADX WARN: Removed duplicated region for block: B:105:0x0330  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0389  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x025d  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0278  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x027b  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0260  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static com.google.android.gms.internal.auth.zzfz zzk(com.google.android.gms.internal.auth.zzgg r34, com.google.android.gms.internal.auth.zzgb r35, com.google.android.gms.internal.auth.zzfk r36, com.google.android.gms.internal.auth.zzgy r37, com.google.android.gms.internal.auth.zzel r38, com.google.android.gms.internal.auth.zzfr r39) {
        /*
            Method dump skipped, instructions count: 1021
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.auth.zzfz.zzk(com.google.android.gms.internal.auth.zzgg, com.google.android.gms.internal.auth.zzgb, com.google.android.gms.internal.auth.zzfk, com.google.android.gms.internal.auth.zzgy, com.google.android.gms.internal.auth.zzel, com.google.android.gms.internal.auth.zzfr):com.google.android.gms.internal.auth.zzfz");
    }

    private static int zzl(Object obj, long j) {
        return ((Integer) zzhi.zzf(obj, j)).intValue();
    }

    private final int zzm(Object obj, byte[] bArr, int i, int i2, int i3, long j, zzds zzdsVar) throws IOException {
        Unsafe unsafe = zzb;
        Object zzz = zzz(i3);
        Object object = unsafe.getObject(obj, j);
        if (!((zzfq) object).zze()) {
            zzfq zzb2 = zzfq.zza().zzb();
            zzfr.zza(zzb2, object);
            unsafe.putObject(obj, j, zzb2);
        }
        throw null;
    }

    private final int zzn(Object obj, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, long j, int i8, zzds zzdsVar) throws IOException {
        Unsafe unsafe = zzb;
        long j2 = this.zzc[i8 + 2] & 1048575;
        switch (i7) {
            case 51:
                if (i5 == 1) {
                    unsafe.putObject(obj, j, Double.valueOf(Double.longBitsToDouble(zzdt.zzn(bArr, i))));
                    unsafe.putInt(obj, j2, i4);
                    return i + 8;
                }
                break;
            case 52:
                if (i5 == 5) {
                    unsafe.putObject(obj, j, Float.valueOf(Float.intBitsToFloat(zzdt.zzb(bArr, i))));
                    unsafe.putInt(obj, j2, i4);
                    return i + 4;
                }
                break;
            case 53:
            case 54:
                if (i5 == 0) {
                    int zzm = zzdt.zzm(bArr, i, zzdsVar);
                    unsafe.putObject(obj, j, Long.valueOf(zzdsVar.zzb));
                    unsafe.putInt(obj, j2, i4);
                    return zzm;
                }
                break;
            case 55:
            case 62:
                if (i5 == 0) {
                    int zzj = zzdt.zzj(bArr, i, zzdsVar);
                    unsafe.putObject(obj, j, Integer.valueOf(zzdsVar.zza));
                    unsafe.putInt(obj, j2, i4);
                    return zzj;
                }
                break;
            case 56:
            case 65:
                if (i5 == 1) {
                    unsafe.putObject(obj, j, Long.valueOf(zzdt.zzn(bArr, i)));
                    unsafe.putInt(obj, j2, i4);
                    return i + 8;
                }
                break;
            case 57:
            case 64:
                if (i5 == 5) {
                    unsafe.putObject(obj, j, Integer.valueOf(zzdt.zzb(bArr, i)));
                    unsafe.putInt(obj, j2, i4);
                    return i + 4;
                }
                break;
            case 58:
                if (i5 == 0) {
                    int zzm2 = zzdt.zzm(bArr, i, zzdsVar);
                    unsafe.putObject(obj, j, Boolean.valueOf(zzdsVar.zzb != 0));
                    unsafe.putInt(obj, j2, i4);
                    return zzm2;
                }
                break;
            case 59:
                if (i5 == 2) {
                    int zzj2 = zzdt.zzj(bArr, i, zzdsVar);
                    int i9 = zzdsVar.zza;
                    if (i9 == 0) {
                        unsafe.putObject(obj, j, "");
                    } else {
                        if ((i6 & 536870912) != 0 && !zzhm.zzd(bArr, zzj2, zzj2 + i9)) {
                            throw zzfa.zzb();
                        }
                        unsafe.putObject(obj, j, new String(bArr, zzj2, i9, zzez.zzb));
                        zzj2 += i9;
                    }
                    unsafe.putInt(obj, j2, i4);
                    return zzj2;
                }
                break;
            case 60:
                if (i5 == 2) {
                    int zzd = zzdt.zzd(zzy(i8), bArr, i, i2, zzdsVar);
                    Object object = unsafe.getInt(obj, j2) == i4 ? unsafe.getObject(obj, j) : null;
                    if (object == null) {
                        unsafe.putObject(obj, j, zzdsVar.zzc);
                    } else {
                        unsafe.putObject(obj, j, zzez.zzg(object, zzdsVar.zzc));
                    }
                    unsafe.putInt(obj, j2, i4);
                    return zzd;
                }
                break;
            case 61:
                if (i5 == 2) {
                    int zza2 = zzdt.zza(bArr, i, zzdsVar);
                    unsafe.putObject(obj, j, zzdsVar.zzc);
                    unsafe.putInt(obj, j2, i4);
                    return zza2;
                }
                break;
            case 63:
                if (i5 == 0) {
                    int zzj3 = zzdt.zzj(bArr, i, zzdsVar);
                    int i10 = zzdsVar.zza;
                    zzex zzx = zzx(i8);
                    if (zzx == null || zzx.zza()) {
                        unsafe.putObject(obj, j, Integer.valueOf(i10));
                        unsafe.putInt(obj, j2, i4);
                    } else {
                        zzc(obj).zzf(i3, Long.valueOf(i10));
                    }
                    return zzj3;
                }
                break;
            case 66:
                if (i5 == 0) {
                    int zzj4 = zzdt.zzj(bArr, i, zzdsVar);
                    unsafe.putObject(obj, j, Integer.valueOf(zzei.zzb(zzdsVar.zza)));
                    unsafe.putInt(obj, j2, i4);
                    return zzj4;
                }
                break;
            case 67:
                if (i5 == 0) {
                    int zzm3 = zzdt.zzm(bArr, i, zzdsVar);
                    unsafe.putObject(obj, j, Long.valueOf(zzei.zzc(zzdsVar.zzb)));
                    unsafe.putInt(obj, j2, i4);
                    return zzm3;
                }
                break;
            case 68:
                if (i5 == 3) {
                    int zzc = zzdt.zzc(zzy(i8), bArr, i, i2, (i3 & (-8)) | 4, zzdsVar);
                    Object object2 = unsafe.getInt(obj, j2) == i4 ? unsafe.getObject(obj, j) : null;
                    if (object2 == null) {
                        unsafe.putObject(obj, j, zzdsVar.zzc);
                    } else {
                        unsafe.putObject(obj, j, zzez.zzg(object2, zzdsVar.zzc));
                    }
                    unsafe.putInt(obj, j2, i4);
                    return zzc;
                }
                break;
        }
        return i;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:65:0x0081. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v21, types: [int] */
    private final int zzo(Object obj, byte[] bArr, int i, int i2, zzds zzdsVar) throws IOException {
        byte b;
        int i3;
        int i4;
        int i5;
        Unsafe unsafe;
        int i6;
        int i7;
        int i8;
        int zzm;
        int zzd;
        int i9;
        int i10;
        int i11;
        zzfz<T> zzfzVar = this;
        Object obj2 = obj;
        byte[] bArr2 = bArr;
        int i12 = i2;
        zzds zzdsVar2 = zzdsVar;
        Unsafe unsafe2 = zzb;
        int i13 = 1048575;
        int i14 = -1;
        int i15 = i;
        int i16 = -1;
        int i17 = 0;
        int i18 = 0;
        int i19 = 1048575;
        while (i15 < i12) {
            int i20 = i15 + 1;
            byte b2 = bArr2[i15];
            if (b2 < 0) {
                i3 = zzdt.zzk(b2, bArr2, i20, zzdsVar2);
                b = zzdsVar2.zza;
            } else {
                b = b2;
                i3 = i20;
            }
            int i21 = b >>> 3;
            int i22 = b & 7;
            int zzr = i21 > i16 ? zzfzVar.zzr(i21, i17 / 3) : zzfzVar.zzq(i21);
            if (zzr == i14) {
                i4 = i3;
                i5 = i21;
                unsafe = unsafe2;
                i6 = 0;
            } else {
                int[] iArr = zzfzVar.zzc;
                int i23 = iArr[zzr + 1];
                int zzu = zzu(i23);
                long j = i23 & i13;
                if (zzu <= 17) {
                    int i24 = iArr[zzr + 2];
                    int i25 = 1 << (i24 >>> 20);
                    int i26 = i24 & 1048575;
                    if (i26 != i19) {
                        if (i19 != 1048575) {
                            unsafe2.putInt(obj2, i19, i18);
                        }
                        if (i26 != 1048575) {
                            i18 = unsafe2.getInt(obj2, i26);
                        }
                        i19 = i26;
                    }
                    switch (zzu) {
                        case 0:
                            zzdsVar2 = zzdsVar;
                            i7 = zzr;
                            i8 = i3;
                            i5 = i21;
                            if (i22 == 1) {
                                zzhi.zzl(obj2, j, Double.longBitsToDouble(zzdt.zzn(bArr2, i8)));
                                i15 = i8 + 8;
                                i18 |= i25;
                                i17 = i7;
                                i16 = i5;
                                break;
                            }
                            i4 = i8;
                            unsafe = unsafe2;
                            i6 = i7;
                            break;
                        case 1:
                            zzdsVar2 = zzdsVar;
                            i7 = zzr;
                            i8 = i3;
                            i5 = i21;
                            if (i22 == 5) {
                                zzhi.zzm(obj2, j, Float.intBitsToFloat(zzdt.zzb(bArr2, i8)));
                                i15 = i8 + 4;
                                i18 |= i25;
                                i17 = i7;
                                i16 = i5;
                                break;
                            }
                            i4 = i8;
                            unsafe = unsafe2;
                            i6 = i7;
                            break;
                        case 2:
                        case 3:
                            zzdsVar2 = zzdsVar;
                            i7 = zzr;
                            i8 = i3;
                            i5 = i21;
                            if (i22 == 0) {
                                zzm = zzdt.zzm(bArr2, i8, zzdsVar2);
                                unsafe2.putLong(obj, j, zzdsVar2.zzb);
                                i18 |= i25;
                                i15 = zzm;
                                i17 = i7;
                                i16 = i5;
                                break;
                            }
                            i4 = i8;
                            unsafe = unsafe2;
                            i6 = i7;
                            break;
                        case 4:
                        case 11:
                            zzdsVar2 = zzdsVar;
                            i7 = zzr;
                            i8 = i3;
                            i5 = i21;
                            if (i22 == 0) {
                                i15 = zzdt.zzj(bArr2, i8, zzdsVar2);
                                unsafe2.putInt(obj2, j, zzdsVar2.zza);
                                i18 |= i25;
                                i17 = i7;
                                i16 = i5;
                                break;
                            }
                            i4 = i8;
                            unsafe = unsafe2;
                            i6 = i7;
                            break;
                        case 5:
                        case 14:
                            zzdsVar2 = zzdsVar;
                            i7 = zzr;
                            i5 = i21;
                            if (i22 == 1) {
                                i8 = i3;
                                unsafe2.putLong(obj, j, zzdt.zzn(bArr2, i3));
                                i15 = i8 + 8;
                                i18 |= i25;
                                i17 = i7;
                                i16 = i5;
                                break;
                            }
                            i8 = i3;
                            i4 = i8;
                            unsafe = unsafe2;
                            i6 = i7;
                            break;
                        case 6:
                        case 13:
                            zzdsVar2 = zzdsVar;
                            i7 = zzr;
                            i5 = i21;
                            if (i22 == 5) {
                                unsafe2.putInt(obj2, j, zzdt.zzb(bArr2, i3));
                                i15 = i3 + 4;
                                i18 |= i25;
                                i17 = i7;
                                i16 = i5;
                                break;
                            }
                            i8 = i3;
                            i4 = i8;
                            unsafe = unsafe2;
                            i6 = i7;
                            break;
                        case 7:
                            zzdsVar2 = zzdsVar;
                            i7 = zzr;
                            i5 = i21;
                            if (i22 == 0) {
                                i15 = zzdt.zzm(bArr2, i3, zzdsVar2);
                                zzhi.zzk(obj2, j, zzdsVar2.zzb != 0);
                                i18 |= i25;
                                i17 = i7;
                                i16 = i5;
                                break;
                            }
                            i8 = i3;
                            i4 = i8;
                            unsafe = unsafe2;
                            i6 = i7;
                            break;
                        case 8:
                            zzdsVar2 = zzdsVar;
                            i7 = zzr;
                            i5 = i21;
                            if (i22 == 2) {
                                i15 = (536870912 & i23) == 0 ? zzdt.zzg(bArr2, i3, zzdsVar2) : zzdt.zzh(bArr2, i3, zzdsVar2);
                                unsafe2.putObject(obj2, j, zzdsVar2.zzc);
                                i18 |= i25;
                                i17 = i7;
                                i16 = i5;
                                break;
                            }
                            i8 = i3;
                            i4 = i8;
                            unsafe = unsafe2;
                            i6 = i7;
                            break;
                        case 9:
                            zzdsVar2 = zzdsVar;
                            i7 = zzr;
                            i5 = i21;
                            if (i22 == 2) {
                                zzd = zzdt.zzd(zzfzVar.zzy(i7), bArr2, i3, i12, zzdsVar2);
                                Object object = unsafe2.getObject(obj2, j);
                                if (object == null) {
                                    unsafe2.putObject(obj2, j, zzdsVar2.zzc);
                                } else {
                                    unsafe2.putObject(obj2, j, zzez.zzg(object, zzdsVar2.zzc));
                                }
                                i18 |= i25;
                                i15 = zzd;
                                i17 = i7;
                                i16 = i5;
                                break;
                            }
                            i8 = i3;
                            i4 = i8;
                            unsafe = unsafe2;
                            i6 = i7;
                            break;
                        case 10:
                            zzdsVar2 = zzdsVar;
                            i7 = zzr;
                            i5 = i21;
                            if (i22 == 2) {
                                zzd = zzdt.zza(bArr2, i3, zzdsVar2);
                                unsafe2.putObject(obj2, j, zzdsVar2.zzc);
                                i18 |= i25;
                                i15 = zzd;
                                i17 = i7;
                                i16 = i5;
                                break;
                            }
                            i8 = i3;
                            i4 = i8;
                            unsafe = unsafe2;
                            i6 = i7;
                            break;
                        case 12:
                            zzdsVar2 = zzdsVar;
                            i7 = zzr;
                            i5 = i21;
                            if (i22 == 0) {
                                zzd = zzdt.zzj(bArr2, i3, zzdsVar2);
                                unsafe2.putInt(obj2, j, zzdsVar2.zza);
                                i18 |= i25;
                                i15 = zzd;
                                i17 = i7;
                                i16 = i5;
                                break;
                            }
                            i8 = i3;
                            i4 = i8;
                            unsafe = unsafe2;
                            i6 = i7;
                            break;
                        case 15:
                            zzdsVar2 = zzdsVar;
                            i7 = zzr;
                            i5 = i21;
                            if (i22 == 0) {
                                zzd = zzdt.zzj(bArr2, i3, zzdsVar2);
                                unsafe2.putInt(obj2, j, zzei.zzb(zzdsVar2.zza));
                                i18 |= i25;
                                i15 = zzd;
                                i17 = i7;
                                i16 = i5;
                                break;
                            }
                            i8 = i3;
                            i4 = i8;
                            unsafe = unsafe2;
                            i6 = i7;
                            break;
                        case 16:
                            if (i22 != 0) {
                                i5 = i21;
                                i7 = zzr;
                                i8 = i3;
                                i4 = i8;
                                unsafe = unsafe2;
                                i6 = i7;
                                break;
                            } else {
                                zzdsVar2 = zzdsVar;
                                zzm = zzdt.zzm(bArr2, i3, zzdsVar2);
                                i7 = zzr;
                                i5 = i21;
                                unsafe2.putLong(obj, j, zzei.zzc(zzdsVar2.zzb));
                                i18 |= i25;
                                i15 = zzm;
                                i17 = i7;
                                i16 = i5;
                                break;
                            }
                        default:
                            i5 = i21;
                            i7 = zzr;
                            i8 = i3;
                            i4 = i8;
                            unsafe = unsafe2;
                            i6 = i7;
                            break;
                    }
                } else {
                    zzdsVar2 = zzdsVar;
                    i7 = zzr;
                    int i27 = i3;
                    i5 = i21;
                    if (zzu == 27) {
                        if (i22 == 2) {
                            zzey zzeyVar = (zzey) unsafe2.getObject(obj2, j);
                            if (!zzeyVar.zzc()) {
                                int size = zzeyVar.size();
                                zzeyVar = zzeyVar.zzd(size == 0 ? 10 : size + size);
                                unsafe2.putObject(obj2, j, zzeyVar);
                            }
                            i15 = zzdt.zze(zzfzVar.zzy(i7), b, bArr, i27, i2, zzeyVar, zzdsVar);
                            i18 = i18;
                            i17 = i7;
                            i16 = i5;
                        } else {
                            i9 = i27;
                            i10 = i18;
                            i11 = i19;
                            unsafe = unsafe2;
                            i6 = i7;
                        }
                    } else if (zzu <= 49) {
                        i10 = i18;
                        i11 = i19;
                        unsafe = unsafe2;
                        i6 = i7;
                        i15 = zzp(obj, bArr, i27, i2, b, i5, i22, i7, i23, zzu, j, zzdsVar);
                        if (i15 != i27) {
                            obj2 = obj;
                            bArr2 = bArr;
                            i12 = i2;
                            zzdsVar2 = zzdsVar;
                            i19 = i11;
                            i16 = i5;
                            i18 = i10;
                            i17 = i6;
                            unsafe2 = unsafe;
                            i14 = -1;
                            i13 = 1048575;
                            zzfzVar = this;
                        } else {
                            i4 = i15;
                            i19 = i11;
                            i18 = i10;
                        }
                    } else {
                        i9 = i27;
                        i10 = i18;
                        i11 = i19;
                        unsafe = unsafe2;
                        i6 = i7;
                        if (zzu != 50) {
                            i15 = zzn(obj, bArr, i9, i2, b, i5, i22, i23, zzu, j, i6, zzdsVar);
                            if (i15 != i9) {
                                obj2 = obj;
                                bArr2 = bArr;
                                i12 = i2;
                                zzdsVar2 = zzdsVar;
                                i19 = i11;
                                i16 = i5;
                                i18 = i10;
                                i17 = i6;
                                unsafe2 = unsafe;
                                i14 = -1;
                                i13 = 1048575;
                                zzfzVar = this;
                            } else {
                                i4 = i15;
                                i19 = i11;
                                i18 = i10;
                            }
                        } else if (i22 == 2) {
                            i15 = zzm(obj, bArr, i9, i2, i6, j, zzdsVar);
                            if (i15 != i9) {
                                obj2 = obj;
                                bArr2 = bArr;
                                i12 = i2;
                                zzdsVar2 = zzdsVar;
                                i19 = i11;
                                i16 = i5;
                                i18 = i10;
                                i17 = i6;
                                unsafe2 = unsafe;
                                i14 = -1;
                                i13 = 1048575;
                                zzfzVar = this;
                            } else {
                                i4 = i15;
                                i19 = i11;
                                i18 = i10;
                            }
                        }
                    }
                    i4 = i9;
                    i19 = i11;
                    i18 = i10;
                }
                i14 = -1;
                i13 = 1048575;
            }
            i15 = zzdt.zzi(b, bArr, i4, i2, zzc(obj), zzdsVar);
            zzfzVar = this;
            obj2 = obj;
            bArr2 = bArr;
            i12 = i2;
            zzdsVar2 = zzdsVar;
            i16 = i5;
            i17 = i6;
            unsafe2 = unsafe;
            i14 = -1;
            i13 = 1048575;
        }
        int i28 = i18;
        int i29 = i19;
        Unsafe unsafe3 = unsafe2;
        if (i29 != 1048575) {
            unsafe3.putInt(obj, i29, i28);
        }
        if (i15 == i2) {
            return i15;
        }
        throw zzfa.zzd();
    }

    private final int zzp(Object obj, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, long j, int i7, long j2, zzds zzdsVar) throws IOException {
        int i8;
        int i9;
        int i10;
        int i11;
        int zzj;
        int i12 = i;
        Unsafe unsafe = zzb;
        zzey zzeyVar = (zzey) unsafe.getObject(obj, j2);
        if (!zzeyVar.zzc()) {
            int size = zzeyVar.size();
            zzeyVar = zzeyVar.zzd(size == 0 ? 10 : size + size);
            unsafe.putObject(obj, j2, zzeyVar);
        }
        switch (i7) {
            case 18:
            case 35:
                if (i5 == 2) {
                    zzej zzejVar = (zzej) zzeyVar;
                    int zzj2 = zzdt.zzj(bArr, i12, zzdsVar);
                    int i13 = zzdsVar.zza + zzj2;
                    while (zzj2 < i13) {
                        zzejVar.zze(Double.longBitsToDouble(zzdt.zzn(bArr, zzj2)));
                        zzj2 += 8;
                    }
                    if (zzj2 == i13) {
                        return zzj2;
                    }
                    throw zzfa.zzf();
                }
                if (i5 == 1) {
                    zzej zzejVar2 = (zzej) zzeyVar;
                    zzejVar2.zze(Double.longBitsToDouble(zzdt.zzn(bArr, i)));
                    while (true) {
                        i8 = i12 + 8;
                        if (i8 < i2) {
                            i12 = zzdt.zzj(bArr, i8, zzdsVar);
                            if (i3 == zzdsVar.zza) {
                                zzejVar2.zze(Double.longBitsToDouble(zzdt.zzn(bArr, i12)));
                            }
                        }
                    }
                    return i8;
                }
                return i12;
            case 19:
            case 36:
                if (i5 == 2) {
                    zzeq zzeqVar = (zzeq) zzeyVar;
                    int zzj3 = zzdt.zzj(bArr, i12, zzdsVar);
                    int i14 = zzdsVar.zza + zzj3;
                    while (zzj3 < i14) {
                        zzeqVar.zze(Float.intBitsToFloat(zzdt.zzb(bArr, zzj3)));
                        zzj3 += 4;
                    }
                    if (zzj3 == i14) {
                        return zzj3;
                    }
                    throw zzfa.zzf();
                }
                if (i5 == 5) {
                    zzeq zzeqVar2 = (zzeq) zzeyVar;
                    zzeqVar2.zze(Float.intBitsToFloat(zzdt.zzb(bArr, i)));
                    while (true) {
                        i9 = i12 + 4;
                        if (i9 < i2) {
                            i12 = zzdt.zzj(bArr, i9, zzdsVar);
                            if (i3 == zzdsVar.zza) {
                                zzeqVar2.zze(Float.intBitsToFloat(zzdt.zzb(bArr, i12)));
                            }
                        }
                    }
                    return i9;
                }
                return i12;
            case 20:
            case 21:
            case 37:
            case 38:
                if (i5 == 2) {
                    zzfl zzflVar = (zzfl) zzeyVar;
                    int zzj4 = zzdt.zzj(bArr, i12, zzdsVar);
                    int i15 = zzdsVar.zza + zzj4;
                    while (zzj4 < i15) {
                        zzj4 = zzdt.zzm(bArr, zzj4, zzdsVar);
                        zzflVar.zze(zzdsVar.zzb);
                    }
                    if (zzj4 == i15) {
                        return zzj4;
                    }
                    throw zzfa.zzf();
                }
                if (i5 == 0) {
                    zzfl zzflVar2 = (zzfl) zzeyVar;
                    int zzm = zzdt.zzm(bArr, i12, zzdsVar);
                    zzflVar2.zze(zzdsVar.zzb);
                    while (zzm < i2) {
                        int zzj5 = zzdt.zzj(bArr, zzm, zzdsVar);
                        if (i3 != zzdsVar.zza) {
                            return zzm;
                        }
                        zzm = zzdt.zzm(bArr, zzj5, zzdsVar);
                        zzflVar2.zze(zzdsVar.zzb);
                    }
                    return zzm;
                }
                return i12;
            case 22:
            case 29:
            case 39:
            case 43:
                if (i5 == 2) {
                    return zzdt.zzf(bArr, i12, zzeyVar, zzdsVar);
                }
                if (i5 == 0) {
                    return zzdt.zzl(i3, bArr, i, i2, zzeyVar, zzdsVar);
                }
                return i12;
            case 23:
            case 32:
            case 40:
            case 46:
                if (i5 == 2) {
                    zzfl zzflVar3 = (zzfl) zzeyVar;
                    int zzj6 = zzdt.zzj(bArr, i12, zzdsVar);
                    int i16 = zzdsVar.zza + zzj6;
                    while (zzj6 < i16) {
                        zzflVar3.zze(zzdt.zzn(bArr, zzj6));
                        zzj6 += 8;
                    }
                    if (zzj6 == i16) {
                        return zzj6;
                    }
                    throw zzfa.zzf();
                }
                if (i5 == 1) {
                    zzfl zzflVar4 = (zzfl) zzeyVar;
                    zzflVar4.zze(zzdt.zzn(bArr, i));
                    while (true) {
                        i10 = i12 + 8;
                        if (i10 < i2) {
                            i12 = zzdt.zzj(bArr, i10, zzdsVar);
                            if (i3 == zzdsVar.zza) {
                                zzflVar4.zze(zzdt.zzn(bArr, i12));
                            }
                        }
                    }
                    return i10;
                }
                return i12;
            case 24:
            case 31:
            case 41:
            case 45:
                if (i5 == 2) {
                    zzev zzevVar = (zzev) zzeyVar;
                    int zzj7 = zzdt.zzj(bArr, i12, zzdsVar);
                    int i17 = zzdsVar.zza + zzj7;
                    while (zzj7 < i17) {
                        zzevVar.zze(zzdt.zzb(bArr, zzj7));
                        zzj7 += 4;
                    }
                    if (zzj7 == i17) {
                        return zzj7;
                    }
                    throw zzfa.zzf();
                }
                if (i5 == 5) {
                    zzev zzevVar2 = (zzev) zzeyVar;
                    zzevVar2.zze(zzdt.zzb(bArr, i));
                    while (true) {
                        i11 = i12 + 4;
                        if (i11 < i2) {
                            i12 = zzdt.zzj(bArr, i11, zzdsVar);
                            if (i3 == zzdsVar.zza) {
                                zzevVar2.zze(zzdt.zzb(bArr, i12));
                            }
                        }
                    }
                    return i11;
                }
                return i12;
            case 25:
            case 42:
                if (i5 == 2) {
                    zzdu zzduVar = (zzdu) zzeyVar;
                    zzj = zzdt.zzj(bArr, i12, zzdsVar);
                    int i18 = zzdsVar.zza + zzj;
                    while (zzj < i18) {
                        zzj = zzdt.zzm(bArr, zzj, zzdsVar);
                        zzduVar.zze(zzdsVar.zzb != 0);
                    }
                    if (zzj != i18) {
                        throw zzfa.zzf();
                    }
                    return zzj;
                }
                if (i5 == 0) {
                    zzdu zzduVar2 = (zzdu) zzeyVar;
                    int zzm2 = zzdt.zzm(bArr, i12, zzdsVar);
                    zzduVar2.zze(zzdsVar.zzb != 0);
                    while (zzm2 < i2) {
                        int zzj8 = zzdt.zzj(bArr, zzm2, zzdsVar);
                        if (i3 != zzdsVar.zza) {
                            return zzm2;
                        }
                        zzm2 = zzdt.zzm(bArr, zzj8, zzdsVar);
                        zzduVar2.zze(zzdsVar.zzb != 0);
                    }
                    return zzm2;
                }
                return i12;
            case 26:
                if (i5 == 2) {
                    if ((j & 536870912) == 0) {
                        i12 = zzdt.zzj(bArr, i12, zzdsVar);
                        int i19 = zzdsVar.zza;
                        if (i19 < 0) {
                            throw zzfa.zzc();
                        }
                        if (i19 == 0) {
                            zzeyVar.add("");
                        } else {
                            zzeyVar.add(new String(bArr, i12, i19, zzez.zzb));
                            i12 += i19;
                        }
                        while (i12 < i2) {
                            int zzj9 = zzdt.zzj(bArr, i12, zzdsVar);
                            if (i3 == zzdsVar.zza) {
                                i12 = zzdt.zzj(bArr, zzj9, zzdsVar);
                                int i20 = zzdsVar.zza;
                                if (i20 < 0) {
                                    throw zzfa.zzc();
                                }
                                if (i20 == 0) {
                                    zzeyVar.add("");
                                } else {
                                    zzeyVar.add(new String(bArr, i12, i20, zzez.zzb));
                                    i12 += i20;
                                }
                            }
                        }
                    } else {
                        i12 = zzdt.zzj(bArr, i12, zzdsVar);
                        int i21 = zzdsVar.zza;
                        if (i21 < 0) {
                            throw zzfa.zzc();
                        }
                        if (i21 == 0) {
                            zzeyVar.add("");
                        } else {
                            int i22 = i12 + i21;
                            if (!zzhm.zzd(bArr, i12, i22)) {
                                throw zzfa.zzb();
                            }
                            zzeyVar.add(new String(bArr, i12, i21, zzez.zzb));
                            i12 = i22;
                        }
                        while (i12 < i2) {
                            int zzj10 = zzdt.zzj(bArr, i12, zzdsVar);
                            if (i3 == zzdsVar.zza) {
                                i12 = zzdt.zzj(bArr, zzj10, zzdsVar);
                                int i23 = zzdsVar.zza;
                                if (i23 < 0) {
                                    throw zzfa.zzc();
                                }
                                if (i23 == 0) {
                                    zzeyVar.add("");
                                } else {
                                    int i24 = i12 + i23;
                                    if (!zzhm.zzd(bArr, i12, i24)) {
                                        throw zzfa.zzb();
                                    }
                                    zzeyVar.add(new String(bArr, i12, i23, zzez.zzb));
                                    i12 = i24;
                                }
                            }
                        }
                    }
                }
                return i12;
            case 27:
                if (i5 == 2) {
                    return zzdt.zze(zzy(i6), i3, bArr, i, i2, zzeyVar, zzdsVar);
                }
                return i12;
            case 28:
                if (i5 == 2) {
                    int zzj11 = zzdt.zzj(bArr, i12, zzdsVar);
                    int i25 = zzdsVar.zza;
                    if (i25 < 0) {
                        throw zzfa.zzc();
                    }
                    if (i25 > bArr.length - zzj11) {
                        throw zzfa.zzf();
                    }
                    if (i25 == 0) {
                        zzeyVar.add(zzee.zzb);
                    } else {
                        zzeyVar.add(zzee.zzk(bArr, zzj11, i25));
                        zzj11 += i25;
                    }
                    while (zzj11 < i2) {
                        int zzj12 = zzdt.zzj(bArr, zzj11, zzdsVar);
                        if (i3 != zzdsVar.zza) {
                            return zzj11;
                        }
                        zzj11 = zzdt.zzj(bArr, zzj12, zzdsVar);
                        int i26 = zzdsVar.zza;
                        if (i26 < 0) {
                            throw zzfa.zzc();
                        }
                        if (i26 > bArr.length - zzj11) {
                            throw zzfa.zzf();
                        }
                        if (i26 == 0) {
                            zzeyVar.add(zzee.zzb);
                        } else {
                            zzeyVar.add(zzee.zzk(bArr, zzj11, i26));
                            zzj11 += i26;
                        }
                    }
                    return zzj11;
                }
                return i12;
            case 30:
            case 44:
                if (i5 != 2) {
                    if (i5 == 0) {
                        zzj = zzdt.zzl(i3, bArr, i, i2, zzeyVar, zzdsVar);
                    }
                    return i12;
                }
                zzj = zzdt.zzf(bArr, i12, zzeyVar, zzdsVar);
                zzeu zzeuVar = (zzeu) obj;
                zzgz zzgzVar = zzeuVar.zzc;
                if (zzgzVar == zzgz.zza()) {
                    zzgzVar = null;
                }
                Object zzd = zzgj.zzd(i4, zzeyVar, zzx(i6), zzgzVar, this.zzm);
                if (zzd != null) {
                    zzeuVar.zzc = (zzgz) zzd;
                    return zzj;
                }
                return zzj;
            case 33:
            case 47:
                if (i5 == 2) {
                    zzev zzevVar3 = (zzev) zzeyVar;
                    int zzj13 = zzdt.zzj(bArr, i12, zzdsVar);
                    int i27 = zzdsVar.zza + zzj13;
                    while (zzj13 < i27) {
                        zzj13 = zzdt.zzj(bArr, zzj13, zzdsVar);
                        zzevVar3.zze(zzei.zzb(zzdsVar.zza));
                    }
                    if (zzj13 == i27) {
                        return zzj13;
                    }
                    throw zzfa.zzf();
                }
                if (i5 == 0) {
                    zzev zzevVar4 = (zzev) zzeyVar;
                    int zzj14 = zzdt.zzj(bArr, i12, zzdsVar);
                    zzevVar4.zze(zzei.zzb(zzdsVar.zza));
                    while (zzj14 < i2) {
                        int zzj15 = zzdt.zzj(bArr, zzj14, zzdsVar);
                        if (i3 != zzdsVar.zza) {
                            return zzj14;
                        }
                        zzj14 = zzdt.zzj(bArr, zzj15, zzdsVar);
                        zzevVar4.zze(zzei.zzb(zzdsVar.zza));
                    }
                    return zzj14;
                }
                return i12;
            case 34:
            case 48:
                if (i5 == 2) {
                    zzfl zzflVar5 = (zzfl) zzeyVar;
                    int zzj16 = zzdt.zzj(bArr, i12, zzdsVar);
                    int i28 = zzdsVar.zza + zzj16;
                    while (zzj16 < i28) {
                        zzj16 = zzdt.zzm(bArr, zzj16, zzdsVar);
                        zzflVar5.zze(zzei.zzc(zzdsVar.zzb));
                    }
                    if (zzj16 == i28) {
                        return zzj16;
                    }
                    throw zzfa.zzf();
                }
                if (i5 == 0) {
                    zzfl zzflVar6 = (zzfl) zzeyVar;
                    int zzm3 = zzdt.zzm(bArr, i12, zzdsVar);
                    zzflVar6.zze(zzei.zzc(zzdsVar.zzb));
                    while (zzm3 < i2) {
                        int zzj17 = zzdt.zzj(bArr, zzm3, zzdsVar);
                        if (i3 != zzdsVar.zza) {
                            return zzm3;
                        }
                        zzm3 = zzdt.zzm(bArr, zzj17, zzdsVar);
                        zzflVar6.zze(zzei.zzc(zzdsVar.zzb));
                    }
                    return zzm3;
                }
                return i12;
            default:
                if (i5 == 3) {
                    zzgh zzy = zzy(i6);
                    int i29 = (i3 & (-8)) | 4;
                    int zzc = zzdt.zzc(zzy, bArr, i, i2, i29, zzdsVar);
                    zzeyVar.add(zzdsVar.zzc);
                    while (zzc < i2) {
                        int zzj18 = zzdt.zzj(bArr, zzc, zzdsVar);
                        if (i3 != zzdsVar.zza) {
                            return zzc;
                        }
                        zzc = zzdt.zzc(zzy, bArr, zzj18, i2, i29, zzdsVar);
                        zzeyVar.add(zzdsVar.zzc);
                    }
                    return zzc;
                }
                return i12;
        }
    }

    private final int zzq(int i) {
        if (i < this.zze || i > this.zzf) {
            return -1;
        }
        return zzt(i, 0);
    }

    private final int zzr(int i, int i2) {
        if (i < this.zze || i > this.zzf) {
            return -1;
        }
        return zzt(i, i2);
    }

    private final int zzs(int i) {
        return this.zzc[i + 2];
    }

    private final int zzt(int i, int i2) {
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

    private static int zzu(int i) {
        return (i >>> 20) & 255;
    }

    private final int zzv(int i) {
        return this.zzc[i + 1];
    }

    private static long zzw(Object obj, long j) {
        return ((Long) zzhi.zzf(obj, j)).longValue();
    }

    private final zzex zzx(int i) {
        int i2 = i / 3;
        return (zzex) this.zzd[i2 + i2 + 1];
    }

    private final zzgh zzy(int i) {
        int i2 = i / 3;
        int i3 = i2 + i2;
        zzgh zzghVar = (zzgh) this.zzd[i3];
        if (zzghVar != null) {
            return zzghVar;
        }
        zzgh zzb2 = zzge.zza().zzb((Class) this.zzd[i3 + 1]);
        this.zzd[i3] = zzb2;
        return zzb2;
    }

    private final Object zzz(int i) {
        int i2 = i / 3;
        return this.zzd[i2 + i2];
    }

    @Override // com.google.android.gms.internal.auth.zzgh
    public final int zza(Object obj) {
        int i;
        int zzc;
        int length = this.zzc.length;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3 += 3) {
            int zzv = zzv(i3);
            int i4 = this.zzc[i3];
            long j = 1048575 & zzv;
            int i5 = 37;
            switch (zzu(zzv)) {
                case 0:
                    i = i2 * 53;
                    zzc = zzez.zzc(Double.doubleToLongBits(zzhi.zza(obj, j)));
                    i2 = i + zzc;
                    break;
                case 1:
                    i = i2 * 53;
                    zzc = Float.floatToIntBits(zzhi.zzb(obj, j));
                    i2 = i + zzc;
                    break;
                case 2:
                    i = i2 * 53;
                    zzc = zzez.zzc(zzhi.zzd(obj, j));
                    i2 = i + zzc;
                    break;
                case 3:
                    i = i2 * 53;
                    zzc = zzez.zzc(zzhi.zzd(obj, j));
                    i2 = i + zzc;
                    break;
                case 4:
                    i = i2 * 53;
                    zzc = zzhi.zzc(obj, j);
                    i2 = i + zzc;
                    break;
                case 5:
                    i = i2 * 53;
                    zzc = zzez.zzc(zzhi.zzd(obj, j));
                    i2 = i + zzc;
                    break;
                case 6:
                    i = i2 * 53;
                    zzc = zzhi.zzc(obj, j);
                    i2 = i + zzc;
                    break;
                case 7:
                    i = i2 * 53;
                    zzc = zzez.zza(zzhi.zzt(obj, j));
                    i2 = i + zzc;
                    break;
                case 8:
                    i = i2 * 53;
                    zzc = ((String) zzhi.zzf(obj, j)).hashCode();
                    i2 = i + zzc;
                    break;
                case 9:
                    Object zzf = zzhi.zzf(obj, j);
                    if (zzf != null) {
                        i5 = zzf.hashCode();
                    }
                    i2 = (i2 * 53) + i5;
                    break;
                case 10:
                    i = i2 * 53;
                    zzc = zzhi.zzf(obj, j).hashCode();
                    i2 = i + zzc;
                    break;
                case 11:
                    i = i2 * 53;
                    zzc = zzhi.zzc(obj, j);
                    i2 = i + zzc;
                    break;
                case 12:
                    i = i2 * 53;
                    zzc = zzhi.zzc(obj, j);
                    i2 = i + zzc;
                    break;
                case 13:
                    i = i2 * 53;
                    zzc = zzhi.zzc(obj, j);
                    i2 = i + zzc;
                    break;
                case 14:
                    i = i2 * 53;
                    zzc = zzez.zzc(zzhi.zzd(obj, j));
                    i2 = i + zzc;
                    break;
                case 15:
                    i = i2 * 53;
                    zzc = zzhi.zzc(obj, j);
                    i2 = i + zzc;
                    break;
                case 16:
                    i = i2 * 53;
                    zzc = zzez.zzc(zzhi.zzd(obj, j));
                    i2 = i + zzc;
                    break;
                case 17:
                    Object zzf2 = zzhi.zzf(obj, j);
                    if (zzf2 != null) {
                        i5 = zzf2.hashCode();
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
                    zzc = zzhi.zzf(obj, j).hashCode();
                    i2 = i + zzc;
                    break;
                case 50:
                    i = i2 * 53;
                    zzc = zzhi.zzf(obj, j).hashCode();
                    i2 = i + zzc;
                    break;
                case 51:
                    if (zzJ(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzez.zzc(Double.doubleToLongBits(((Double) zzhi.zzf(obj, j)).doubleValue()));
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (zzJ(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = Float.floatToIntBits(((Float) zzhi.zzf(obj, j)).floatValue());
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (zzJ(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzez.zzc(zzw(obj, j));
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (zzJ(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzez.zzc(zzw(obj, j));
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (zzJ(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzl(obj, j);
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (zzJ(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzez.zzc(zzw(obj, j));
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (zzJ(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzl(obj, j);
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (zzJ(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzez.zza(((Boolean) zzhi.zzf(obj, j)).booleanValue());
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (zzJ(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = ((String) zzhi.zzf(obj, j)).hashCode();
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 60:
                    if (zzJ(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzhi.zzf(obj, j).hashCode();
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (zzJ(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzhi.zzf(obj, j).hashCode();
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (zzJ(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzl(obj, j);
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (zzJ(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzl(obj, j);
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (zzJ(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzl(obj, j);
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (zzJ(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzez.zzc(zzw(obj, j));
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (zzJ(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzl(obj, j);
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (zzJ(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzez.zzc(zzw(obj, j));
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (zzJ(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzhi.zzf(obj, j).hashCode();
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
            }
        }
        return (i2 * 53) + this.zzm.zza(obj).hashCode();
    }

    /* JADX WARN: Code restructure failed: missing block: B:74:0x0334, code lost:
    
        if (r0 != r20) goto L321;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x0336, code lost:
    
        r15 = r29;
        r14 = r30;
        r12 = r31;
        r13 = r33;
        r11 = r34;
        r9 = r35;
        r1 = r17;
        r2 = r19;
        r3 = r21;
        r5 = r22;
        r6 = r25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x034e, code lost:
    
        r7 = r34;
        r2 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x0371, code lost:
    
        if (r0 != r15) goto L321;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x0397, code lost:
    
        if (r0 != r15) goto L321;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:93:0x008d. Please report as an issue. */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final int zzb(java.lang.Object r30, byte[] r31, int r32, int r33, int r34, com.google.android.gms.internal.auth.zzds r35) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1104
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.auth.zzfz.zzb(java.lang.Object, byte[], int, int, int, com.google.android.gms.internal.auth.zzds):int");
    }

    @Override // com.google.android.gms.internal.auth.zzgh
    public final Object zzd() {
        return ((zzeu) this.zzg).zzi(4, null, null);
    }

    @Override // com.google.android.gms.internal.auth.zzgh
    public final void zze(Object obj) {
        int i;
        int i2 = this.zzj;
        while (true) {
            i = this.zzk;
            if (i2 >= i) {
                break;
            }
            long zzv = zzv(this.zzi[i2]) & 1048575;
            Object zzf = zzhi.zzf(obj, zzv);
            if (zzf != null) {
                ((zzfq) zzf).zzc();
                zzhi.zzp(obj, zzv, zzf);
            }
            i2++;
        }
        int length = this.zzi.length;
        while (i < length) {
            this.zzl.zza(obj, this.zzi[i]);
            i++;
        }
        this.zzm.zze(obj);
    }

    @Override // com.google.android.gms.internal.auth.zzgh
    public final void zzg(Object obj, byte[] bArr, int i, int i2, zzds zzdsVar) throws IOException {
        if (this.zzh) {
            zzo(obj, bArr, i, i2, zzdsVar);
        } else {
            zzb(obj, bArr, i, i2, 0, zzdsVar);
        }
    }

    @Override // com.google.android.gms.internal.auth.zzgh
    public final boolean zzh(Object obj, Object obj2) {
        boolean zzh;
        int length = this.zzc.length;
        for (int i = 0; i < length; i += 3) {
            int zzv = zzv(i);
            long j = zzv & 1048575;
            switch (zzu(zzv)) {
                case 0:
                    if (zzF(obj, obj2, i) && Double.doubleToLongBits(zzhi.zza(obj, j)) == Double.doubleToLongBits(zzhi.zza(obj2, j))) {
                        continue;
                    }
                    return false;
                case 1:
                    if (zzF(obj, obj2, i) && Float.floatToIntBits(zzhi.zzb(obj, j)) == Float.floatToIntBits(zzhi.zzb(obj2, j))) {
                        continue;
                    }
                    return false;
                case 2:
                    if (zzF(obj, obj2, i) && zzhi.zzd(obj, j) == zzhi.zzd(obj2, j)) {
                        continue;
                    }
                    return false;
                case 3:
                    if (zzF(obj, obj2, i) && zzhi.zzd(obj, j) == zzhi.zzd(obj2, j)) {
                        continue;
                    }
                    return false;
                case 4:
                    if (zzF(obj, obj2, i) && zzhi.zzc(obj, j) == zzhi.zzc(obj2, j)) {
                        continue;
                    }
                    return false;
                case 5:
                    if (zzF(obj, obj2, i) && zzhi.zzd(obj, j) == zzhi.zzd(obj2, j)) {
                        continue;
                    }
                    return false;
                case 6:
                    if (zzF(obj, obj2, i) && zzhi.zzc(obj, j) == zzhi.zzc(obj2, j)) {
                        continue;
                    }
                    return false;
                case 7:
                    if (zzF(obj, obj2, i) && zzhi.zzt(obj, j) == zzhi.zzt(obj2, j)) {
                        continue;
                    }
                    return false;
                case 8:
                    if (zzF(obj, obj2, i) && zzgj.zzh(zzhi.zzf(obj, j), zzhi.zzf(obj2, j))) {
                        continue;
                    }
                    return false;
                case 9:
                    if (zzF(obj, obj2, i) && zzgj.zzh(zzhi.zzf(obj, j), zzhi.zzf(obj2, j))) {
                        continue;
                    }
                    return false;
                case 10:
                    if (zzF(obj, obj2, i) && zzgj.zzh(zzhi.zzf(obj, j), zzhi.zzf(obj2, j))) {
                        continue;
                    }
                    return false;
                case 11:
                    if (zzF(obj, obj2, i) && zzhi.zzc(obj, j) == zzhi.zzc(obj2, j)) {
                        continue;
                    }
                    return false;
                case 12:
                    if (zzF(obj, obj2, i) && zzhi.zzc(obj, j) == zzhi.zzc(obj2, j)) {
                        continue;
                    }
                    return false;
                case 13:
                    if (zzF(obj, obj2, i) && zzhi.zzc(obj, j) == zzhi.zzc(obj2, j)) {
                        continue;
                    }
                    return false;
                case 14:
                    if (zzF(obj, obj2, i) && zzhi.zzd(obj, j) == zzhi.zzd(obj2, j)) {
                        continue;
                    }
                    return false;
                case 15:
                    if (zzF(obj, obj2, i) && zzhi.zzc(obj, j) == zzhi.zzc(obj2, j)) {
                        continue;
                    }
                    return false;
                case 16:
                    if (zzF(obj, obj2, i) && zzhi.zzd(obj, j) == zzhi.zzd(obj2, j)) {
                        continue;
                    }
                    return false;
                case 17:
                    if (zzF(obj, obj2, i) && zzgj.zzh(zzhi.zzf(obj, j), zzhi.zzf(obj2, j))) {
                        continue;
                    }
                    return false;
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
                    zzh = zzgj.zzh(zzhi.zzf(obj, j), zzhi.zzf(obj2, j));
                    break;
                case 50:
                    zzh = zzgj.zzh(zzhi.zzf(obj, j), zzhi.zzf(obj2, j));
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
                case 60:
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                case 68:
                    long zzs = zzs(i) & 1048575;
                    if (zzhi.zzc(obj, zzs) == zzhi.zzc(obj2, zzs) && zzgj.zzh(zzhi.zzf(obj, j), zzhi.zzf(obj2, j))) {
                        continue;
                    }
                    return false;
                default:
            }
            if (!zzh) {
                return false;
            }
        }
        return this.zzm.zza(obj).equals(this.zzm.zza(obj2));
    }

    @Override // com.google.android.gms.internal.auth.zzgh
    public final boolean zzi(Object obj) {
        int i;
        int i2;
        int i3 = 1048575;
        int i4 = 0;
        int i5 = 0;
        while (i5 < this.zzj) {
            int i6 = this.zzi[i5];
            int i7 = this.zzc[i6];
            int zzv = zzv(i6);
            int i8 = this.zzc[i6 + 2];
            int i9 = i8 & 1048575;
            int i10 = 1 << (i8 >>> 20);
            if (i9 != i3) {
                if (i9 != 1048575) {
                    i4 = zzb.getInt(obj, i9);
                }
                i2 = i4;
                i = i9;
            } else {
                i = i3;
                i2 = i4;
            }
            if ((268435456 & zzv) != 0 && !zzH(obj, i6, i, i2, i10)) {
                return false;
            }
            int zzu = zzu(zzv);
            if (zzu != 9 && zzu != 17) {
                if (zzu != 27) {
                    if (zzu == 60 || zzu == 68) {
                        if (zzJ(obj, i7, i6) && !zzI(obj, zzv, zzy(i6))) {
                            return false;
                        }
                    } else if (zzu != 49) {
                        if (zzu == 50 && !((zzfq) zzhi.zzf(obj, zzv & 1048575)).isEmpty()) {
                            throw null;
                        }
                    }
                }
                List list = (List) zzhi.zzf(obj, zzv & 1048575);
                if (list.isEmpty()) {
                    continue;
                } else {
                    zzgh zzy = zzy(i6);
                    for (int i11 = 0; i11 < list.size(); i11++) {
                        if (!zzy.zzi(list.get(i11))) {
                            return false;
                        }
                    }
                }
            } else if (zzH(obj, i6, i, i2, i10) && !zzI(obj, zzv, zzy(i6))) {
                return false;
            }
            i5++;
            i3 = i;
            i4 = i2;
        }
        return true;
    }

    @Override // com.google.android.gms.internal.auth.zzgh
    public final void zzf(Object obj, Object obj2) {
        obj2.getClass();
        for (int i = 0; i < this.zzc.length; i += 3) {
            int zzv = zzv(i);
            long j = 1048575 & zzv;
            int i2 = this.zzc[i];
            switch (zzu(zzv)) {
                case 0:
                    if (zzG(obj2, i)) {
                        zzhi.zzl(obj, j, zzhi.zza(obj2, j));
                        zzD(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (zzG(obj2, i)) {
                        zzhi.zzm(obj, j, zzhi.zzb(obj2, j));
                        zzD(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (zzG(obj2, i)) {
                        zzhi.zzo(obj, j, zzhi.zzd(obj2, j));
                        zzD(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (zzG(obj2, i)) {
                        zzhi.zzo(obj, j, zzhi.zzd(obj2, j));
                        zzD(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (zzG(obj2, i)) {
                        zzhi.zzn(obj, j, zzhi.zzc(obj2, j));
                        zzD(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (zzG(obj2, i)) {
                        zzhi.zzo(obj, j, zzhi.zzd(obj2, j));
                        zzD(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (zzG(obj2, i)) {
                        zzhi.zzn(obj, j, zzhi.zzc(obj2, j));
                        zzD(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (zzG(obj2, i)) {
                        zzhi.zzk(obj, j, zzhi.zzt(obj2, j));
                        zzD(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (zzG(obj2, i)) {
                        zzhi.zzp(obj, j, zzhi.zzf(obj2, j));
                        zzD(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 9:
                    zzB(obj, obj2, i);
                    break;
                case 10:
                    if (zzG(obj2, i)) {
                        zzhi.zzp(obj, j, zzhi.zzf(obj2, j));
                        zzD(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (zzG(obj2, i)) {
                        zzhi.zzn(obj, j, zzhi.zzc(obj2, j));
                        zzD(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (zzG(obj2, i)) {
                        zzhi.zzn(obj, j, zzhi.zzc(obj2, j));
                        zzD(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (zzG(obj2, i)) {
                        zzhi.zzn(obj, j, zzhi.zzc(obj2, j));
                        zzD(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (zzG(obj2, i)) {
                        zzhi.zzo(obj, j, zzhi.zzd(obj2, j));
                        zzD(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (zzG(obj2, i)) {
                        zzhi.zzn(obj, j, zzhi.zzc(obj2, j));
                        zzD(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (zzG(obj2, i)) {
                        zzhi.zzo(obj, j, zzhi.zzd(obj2, j));
                        zzD(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 17:
                    zzB(obj, obj2, i);
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
                    this.zzl.zzb(obj, obj2, j);
                    break;
                case 50:
                    zzgj.zzi(this.zzp, obj, obj2, j);
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
                    if (zzJ(obj2, i2, i)) {
                        zzhi.zzp(obj, j, zzhi.zzf(obj2, j));
                        zzE(obj, i2, i);
                        break;
                    } else {
                        break;
                    }
                case 60:
                    zzC(obj, obj2, i);
                    break;
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                    if (zzJ(obj2, i2, i)) {
                        zzhi.zzp(obj, j, zzhi.zzf(obj2, j));
                        zzE(obj, i2, i);
                        break;
                    } else {
                        break;
                    }
                case 68:
                    zzC(obj, obj2, i);
                    break;
            }
        }
        zzgj.zzf(this.zzm, obj, obj2);
    }
}
