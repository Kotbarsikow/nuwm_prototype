package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
/* loaded from: classes.dex */
public final class zzmj {
    private static final zzmj zza = new zzmj(0, new int[0], new Object[0], false);
    private int zzb;
    private int[] zzc;
    private Object[] zzd;
    private int zze;
    private boolean zzf;

    public final int zza() {
        int zzg;
        int i = this.zze;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.zzb; i3++) {
            int i4 = this.zzc[i3];
            int i5 = i4 >>> 3;
            int i6 = i4 & 7;
            if (i6 == 0) {
                zzg = zzjc.zzg(i5, ((Long) this.zzd[i3]).longValue());
            } else if (i6 == 1) {
                zzg = zzjc.zzc(i5, ((Long) this.zzd[i3]).longValue());
            } else if (i6 == 2) {
                zzg = zzjc.zzc(i5, (zzik) this.zzd[i3]);
            } else if (i6 == 3) {
                zzg = (zzjc.zzi(i5) << 1) + ((zzmj) this.zzd[i3]).zza();
            } else {
                if (i6 != 5) {
                    throw new IllegalStateException(zzkb.zza());
                }
                zzg = zzjc.zzf(i5, ((Integer) this.zzd[i3]).intValue());
            }
            i2 += zzg;
        }
        this.zze = i2;
        return i2;
    }

    public final int zzb() {
        int i = this.zze;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.zzb; i3++) {
            i2 += zzjc.zzd(this.zzc[i3] >>> 3, (zzik) this.zzd[i3]);
        }
        this.zze = i2;
        return i2;
    }

    public final int hashCode() {
        int i = this.zzb;
        int i2 = (i + 527) * 31;
        int[] iArr = this.zzc;
        int i3 = 17;
        int i4 = 17;
        for (int i5 = 0; i5 < i; i5++) {
            i4 = (i4 * 31) + iArr[i5];
        }
        int i6 = (i2 + i4) * 31;
        Object[] objArr = this.zzd;
        int i7 = this.zzb;
        for (int i8 = 0; i8 < i7; i8++) {
            i3 = (i3 * 31) + objArr[i8].hashCode();
        }
        return i6 + i3;
    }

    public static zzmj zzc() {
        return zza;
    }

    final zzmj zza(zzmj zzmjVar) {
        if (zzmjVar.equals(zza)) {
            return this;
        }
        zzf();
        int i = this.zzb + zzmjVar.zzb;
        zza(i);
        System.arraycopy(zzmjVar.zzc, 0, this.zzc, this.zzb, zzmjVar.zzb);
        System.arraycopy(zzmjVar.zzd, 0, this.zzd, this.zzb, zzmjVar.zzb);
        this.zzb = i;
        return this;
    }

    static zzmj zza(zzmj zzmjVar, zzmj zzmjVar2) {
        int i = zzmjVar.zzb + zzmjVar2.zzb;
        int[] copyOf = Arrays.copyOf(zzmjVar.zzc, i);
        System.arraycopy(zzmjVar2.zzc, 0, copyOf, zzmjVar.zzb, zzmjVar2.zzb);
        Object[] copyOf2 = Arrays.copyOf(zzmjVar.zzd, i);
        System.arraycopy(zzmjVar2.zzd, 0, copyOf2, zzmjVar.zzb, zzmjVar2.zzb);
        return new zzmj(i, copyOf, copyOf2, true);
    }

    static zzmj zzd() {
        return new zzmj();
    }

    private zzmj() {
        this(0, new int[8], new Object[8], true);
    }

    private zzmj(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zze = -1;
        this.zzb = i;
        this.zzc = iArr;
        this.zzd = objArr;
        this.zzf = z;
    }

    private final void zzf() {
        if (!this.zzf) {
            throw new UnsupportedOperationException();
        }
    }

    private final void zza(int i) {
        int[] iArr = this.zzc;
        if (i > iArr.length) {
            int i2 = this.zzb;
            int i3 = i2 + (i2 / 2);
            if (i3 >= i) {
                i = i3;
            }
            if (i < 8) {
                i = 8;
            }
            this.zzc = Arrays.copyOf(iArr, i);
            this.zzd = Arrays.copyOf(this.zzd, i);
        }
    }

    public final void zze() {
        if (this.zzf) {
            this.zzf = false;
        }
    }

    final void zza(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < this.zzb; i2++) {
            zzld.zza(sb, i, String.valueOf(this.zzc[i2] >>> 3), this.zzd[i2]);
        }
    }

    final void zza(int i, Object obj) {
        zzf();
        zza(this.zzb + 1);
        int[] iArr = this.zzc;
        int i2 = this.zzb;
        iArr[i2] = i;
        this.zzd[i2] = obj;
        this.zzb = i2 + 1;
    }

    final void zza(zznb zznbVar) throws IOException {
        if (zznbVar.zza() == 2) {
            for (int i = this.zzb - 1; i >= 0; i--) {
                zznbVar.zza(this.zzc[i] >>> 3, this.zzd[i]);
            }
            return;
        }
        for (int i2 = 0; i2 < this.zzb; i2++) {
            zznbVar.zza(this.zzc[i2] >>> 3, this.zzd[i2]);
        }
    }

    private static void zza(int i, Object obj, zznb zznbVar) throws IOException {
        int i2 = i >>> 3;
        int i3 = i & 7;
        if (i3 == 0) {
            zznbVar.zzb(i2, ((Long) obj).longValue());
            return;
        }
        if (i3 == 1) {
            zznbVar.zza(i2, ((Long) obj).longValue());
            return;
        }
        if (i3 == 2) {
            zznbVar.zza(i2, (zzik) obj);
            return;
        }
        if (i3 != 3) {
            if (i3 == 5) {
                zznbVar.zzb(i2, ((Integer) obj).intValue());
                return;
            }
            throw new RuntimeException(zzkb.zza());
        }
        if (zznbVar.zza() == 1) {
            zznbVar.zzb(i2);
            ((zzmj) obj).zzb(zznbVar);
            zznbVar.zza(i2);
        } else {
            zznbVar.zza(i2);
            ((zzmj) obj).zzb(zznbVar);
            zznbVar.zzb(i2);
        }
    }

    public final void zzb(zznb zznbVar) throws IOException {
        if (this.zzb == 0) {
            return;
        }
        if (zznbVar.zza() == 1) {
            for (int i = 0; i < this.zzb; i++) {
                zza(this.zzc[i], this.zzd[i], zznbVar);
            }
            return;
        }
        for (int i2 = this.zzb - 1; i2 >= 0; i2--) {
            zza(this.zzc[i2], this.zzd[i2], zznbVar);
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof zzmj)) {
            return false;
        }
        zzmj zzmjVar = (zzmj) obj;
        int i = this.zzb;
        if (i == zzmjVar.zzb) {
            int[] iArr = this.zzc;
            int[] iArr2 = zzmjVar.zzc;
            int i2 = 0;
            while (true) {
                if (i2 < i) {
                    if (iArr[i2] != iArr2[i2]) {
                        break;
                    }
                    i2++;
                } else {
                    Object[] objArr = this.zzd;
                    Object[] objArr2 = zzmjVar.zzd;
                    int i3 = this.zzb;
                    for (int i4 = 0; i4 < i3; i4++) {
                        if (objArr[i4].equals(objArr2[i4])) {
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }
}
