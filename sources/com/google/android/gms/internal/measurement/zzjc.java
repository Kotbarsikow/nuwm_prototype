package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
/* loaded from: classes.dex */
public abstract class zzjc extends zzil {
    private static final Logger zzb = Logger.getLogger(zzjc.class.getName());
    private static final boolean zzc = zzml.zzc();
    zzjf zza;

    /* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
    private static class zza extends zzjc {
        private final byte[] zzb;
        private final int zzc;
        private int zzd;

        @Override // com.google.android.gms.internal.measurement.zzjc
        public final int zza() {
            return this.zzc - this.zzd;
        }

        zza(byte[] bArr, int i, int i2) {
            super();
            if (bArr == null) {
                throw new NullPointerException("buffer");
            }
            if (((bArr.length - i2) | i2) < 0) {
                throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", Integer.valueOf(bArr.length), 0, Integer.valueOf(i2)));
            }
            this.zzb = bArr;
            this.zzd = 0;
            this.zzc = i2;
        }

        @Override // com.google.android.gms.internal.measurement.zzjc
        public final void zza(byte b) throws IOException {
            int i;
            int i2 = this.zzd;
            try {
                i = i2 + 1;
            } catch (IndexOutOfBoundsException e) {
                e = e;
            }
            try {
                this.zzb[i2] = b;
                this.zzd = i;
            } catch (IndexOutOfBoundsException e2) {
                e = e2;
                i2 = i;
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(i2), Integer.valueOf(this.zzc), 1), e);
            }
        }

        private final void zzc(byte[] bArr, int i, int i2) throws IOException {
            try {
                System.arraycopy(bArr, i, this.zzb, this.zzd, i2);
                this.zzd += i2;
            } catch (IndexOutOfBoundsException e) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), Integer.valueOf(i2)), e);
            }
        }

        @Override // com.google.android.gms.internal.measurement.zzjc
        public final void zza(int i, boolean z) throws IOException {
            zzc(i, 0);
            zza(z ? (byte) 1 : (byte) 0);
        }

        @Override // com.google.android.gms.internal.measurement.zzjc
        public final void zzb(byte[] bArr, int i, int i2) throws IOException {
            zzc(i2);
            zzc(bArr, 0, i2);
        }

        @Override // com.google.android.gms.internal.measurement.zzjc
        public final void zza(int i, zzik zzikVar) throws IOException {
            zzc(i, 2);
            zza(zzikVar);
        }

        @Override // com.google.android.gms.internal.measurement.zzjc
        public final void zza(zzik zzikVar) throws IOException {
            zzc(zzikVar.zzb());
            zzikVar.zza(this);
        }

        @Override // com.google.android.gms.internal.measurement.zzjc
        public final void zza(int i, int i2) throws IOException {
            zzc(i, 5);
            zza(i2);
        }

        @Override // com.google.android.gms.internal.measurement.zzjc
        public final void zza(int i) throws IOException {
            try {
                byte[] bArr = this.zzb;
                int i2 = this.zzd;
                int i3 = i2 + 1;
                this.zzd = i3;
                bArr[i2] = (byte) i;
                int i4 = i2 + 2;
                this.zzd = i4;
                bArr[i3] = (byte) (i >> 8);
                int i5 = i2 + 3;
                this.zzd = i5;
                bArr[i4] = (byte) (i >> 16);
                this.zzd = i2 + 4;
                bArr[i5] = (byte) (i >>> 24);
            } catch (IndexOutOfBoundsException e) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), 1), e);
            }
        }

        @Override // com.google.android.gms.internal.measurement.zzjc
        public final void zza(int i, long j) throws IOException {
            zzc(i, 1);
            zza(j);
        }

        @Override // com.google.android.gms.internal.measurement.zzjc
        public final void zza(long j) throws IOException {
            try {
                byte[] bArr = this.zzb;
                int i = this.zzd;
                int i2 = i + 1;
                this.zzd = i2;
                bArr[i] = (byte) j;
                int i3 = i + 2;
                this.zzd = i3;
                bArr[i2] = (byte) (j >> 8);
                int i4 = i + 3;
                this.zzd = i4;
                bArr[i3] = (byte) (j >> 16);
                int i5 = i + 4;
                this.zzd = i5;
                bArr[i4] = (byte) (j >> 24);
                int i6 = i + 5;
                this.zzd = i6;
                bArr[i5] = (byte) (j >> 32);
                int i7 = i + 6;
                this.zzd = i7;
                bArr[i6] = (byte) (j >> 40);
                int i8 = i + 7;
                this.zzd = i8;
                bArr[i7] = (byte) (j >> 48);
                this.zzd = i + 8;
                bArr[i8] = (byte) (j >> 56);
            } catch (IndexOutOfBoundsException e) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), 1), e);
            }
        }

        @Override // com.google.android.gms.internal.measurement.zzjc
        public final void zzb(int i, int i2) throws IOException {
            zzc(i, 0);
            zzb(i2);
        }

        @Override // com.google.android.gms.internal.measurement.zzjc
        public final void zzb(int i) throws IOException {
            if (i >= 0) {
                zzc(i);
            } else {
                zzb(i);
            }
        }

        @Override // com.google.android.gms.internal.measurement.zzil
        public final void zza(byte[] bArr, int i, int i2) throws IOException {
            zzc(bArr, i, i2);
        }

        @Override // com.google.android.gms.internal.measurement.zzjc
        final void zza(int i, zzlc zzlcVar, zzlu zzluVar) throws IOException {
            zzc(i, 2);
            zzc(((zzib) zzlcVar).zza(zzluVar));
            zzluVar.zza((zzlu) zzlcVar, (zznb) this.zza);
        }

        @Override // com.google.android.gms.internal.measurement.zzjc
        public final void zza(zzlc zzlcVar) throws IOException {
            zzc(zzlcVar.zzcb());
            zzlcVar.zza(this);
        }

        @Override // com.google.android.gms.internal.measurement.zzjc
        public final void zza(int i, zzlc zzlcVar) throws IOException {
            zzc(1, 3);
            zzd(2, i);
            zzc(3, 2);
            zza(zzlcVar);
            zzc(1, 4);
        }

        @Override // com.google.android.gms.internal.measurement.zzjc
        public final void zzb(int i, zzik zzikVar) throws IOException {
            zzc(1, 3);
            zzd(2, i);
            zza(3, zzikVar);
            zzc(1, 4);
        }

        @Override // com.google.android.gms.internal.measurement.zzjc
        public final void zza(int i, String str) throws IOException {
            zzc(i, 2);
            zza(str);
        }

        @Override // com.google.android.gms.internal.measurement.zzjc
        public final void zza(String str) throws IOException {
            int i = this.zzd;
            try {
                int zzj = zzj(str.length() * 3);
                int zzj2 = zzj(str.length());
                if (zzj2 == zzj) {
                    int i2 = i + zzj2;
                    this.zzd = i2;
                    int zza = zzmp.zza(str, this.zzb, i2, zza());
                    this.zzd = i;
                    zzc((zza - i) - zzj2);
                    this.zzd = zza;
                    return;
                }
                zzc(zzmp.zza(str));
                this.zzd = zzmp.zza(str, this.zzb, this.zzd, zza());
            } catch (zzmt e) {
                this.zzd = i;
                zza(str, e);
            } catch (IndexOutOfBoundsException e2) {
                throw new zzb(e2);
            }
        }

        @Override // com.google.android.gms.internal.measurement.zzjc
        public final void zzc(int i, int i2) throws IOException {
            zzc((i << 3) | i2);
        }

        @Override // com.google.android.gms.internal.measurement.zzjc
        public final void zzd(int i, int i2) throws IOException {
            zzc(i, 0);
            zzc(i2);
        }

        @Override // com.google.android.gms.internal.measurement.zzjc
        public final void zzc(int i) throws IOException {
            while ((i & (-128)) != 0) {
                try {
                    byte[] bArr = this.zzb;
                    int i2 = this.zzd;
                    this.zzd = i2 + 1;
                    bArr[i2] = (byte) (i | 128);
                    i >>>= 7;
                } catch (IndexOutOfBoundsException e) {
                    throw new zzb(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), 1), e);
                }
            }
            byte[] bArr2 = this.zzb;
            int i3 = this.zzd;
            this.zzd = i3 + 1;
            bArr2[i3] = (byte) i;
        }

        @Override // com.google.android.gms.internal.measurement.zzjc
        public final void zzb(int i, long j) throws IOException {
            zzc(i, 0);
            zzb(j);
        }

        @Override // com.google.android.gms.internal.measurement.zzjc
        public final void zzb(long j) throws IOException {
            if (zzjc.zzc && zza() >= 10) {
                while ((j & (-128)) != 0) {
                    byte[] bArr = this.zzb;
                    int i = this.zzd;
                    this.zzd = i + 1;
                    zzml.zza(bArr, i, (byte) (((int) j) | 128));
                    j >>>= 7;
                }
                byte[] bArr2 = this.zzb;
                int i2 = this.zzd;
                this.zzd = i2 + 1;
                zzml.zza(bArr2, i2, (byte) j);
                return;
            }
            while ((j & (-128)) != 0) {
                try {
                    byte[] bArr3 = this.zzb;
                    int i3 = this.zzd;
                    this.zzd = i3 + 1;
                    bArr3[i3] = (byte) (((int) j) | 128);
                    j >>>= 7;
                } catch (IndexOutOfBoundsException e) {
                    throw new zzb(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), 1), e);
                }
            }
            byte[] bArr4 = this.zzb;
            int i4 = this.zzd;
            this.zzd = i4 + 1;
            bArr4[i4] = (byte) j;
        }
    }

    public static int zza(double d) {
        return 8;
    }

    public static int zza(float f) {
        return 4;
    }

    public static int zza(boolean z) {
        return 1;
    }

    public static int zzc(long j) {
        return 8;
    }

    public static int zze(int i) {
        return 4;
    }

    public static int zze(long j) {
        return 8;
    }

    public static int zzg(int i) {
        return 4;
    }

    private static long zzi(long j) {
        return (j >> 63) ^ (j << 1);
    }

    private static int zzl(int i) {
        return (i >> 31) ^ (i << 1);
    }

    public abstract int zza();

    public abstract void zza(byte b) throws IOException;

    public abstract void zza(int i) throws IOException;

    public abstract void zza(int i, int i2) throws IOException;

    public abstract void zza(int i, long j) throws IOException;

    public abstract void zza(int i, zzik zzikVar) throws IOException;

    public abstract void zza(int i, zzlc zzlcVar) throws IOException;

    abstract void zza(int i, zzlc zzlcVar, zzlu zzluVar) throws IOException;

    public abstract void zza(int i, String str) throws IOException;

    public abstract void zza(int i, boolean z) throws IOException;

    public abstract void zza(long j) throws IOException;

    public abstract void zza(zzik zzikVar) throws IOException;

    public abstract void zza(zzlc zzlcVar) throws IOException;

    public abstract void zza(String str) throws IOException;

    public abstract void zzb(int i) throws IOException;

    public abstract void zzb(int i, int i2) throws IOException;

    public abstract void zzb(int i, long j) throws IOException;

    public abstract void zzb(int i, zzik zzikVar) throws IOException;

    public abstract void zzb(long j) throws IOException;

    abstract void zzb(byte[] bArr, int i, int i2) throws IOException;

    public abstract void zzc(int i) throws IOException;

    public abstract void zzc(int i, int i2) throws IOException;

    public abstract void zzd(int i, int i2) throws IOException;

    /* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
    public static class zzb extends IOException {
        zzb() {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }

        zzb(Throwable th) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.", th);
        }

        zzb(String str, Throwable th) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.: " + str, th);
        }
    }

    public static int zzb(int i, boolean z) {
        return zzj(i << 3) + 1;
    }

    public static int zza(byte[] bArr) {
        int length = bArr.length;
        return zzj(length) + length;
    }

    public static int zzc(int i, zzik zzikVar) {
        int zzj = zzj(i << 3);
        int zzb2 = zzikVar.zzb();
        return zzj + zzj(zzb2) + zzb2;
    }

    public static int zzb(zzik zzikVar) {
        int zzb2 = zzikVar.zzb();
        return zzj(zzb2) + zzb2;
    }

    public static int zza(int i, double d) {
        return zzj(i << 3) + 8;
    }

    public static int zze(int i, int i2) {
        return zzj(i << 3) + zzg(i2);
    }

    public static int zzd(int i) {
        return zzg(i);
    }

    public static int zzf(int i, int i2) {
        return zzj(i << 3) + 4;
    }

    public static int zzc(int i, long j) {
        return zzj(i << 3) + 8;
    }

    public static int zza(int i, float f) {
        return zzj(i << 3) + 4;
    }

    @Deprecated
    static int zzb(int i, zzlc zzlcVar, zzlu zzluVar) {
        return (zzj(i << 3) << 1) + ((zzib) zzlcVar).zza(zzluVar);
    }

    @Deprecated
    public static int zzb(zzlc zzlcVar) {
        return zzlcVar.zzcb();
    }

    public static int zzg(int i, int i2) {
        return zzj(i << 3) + zzg(i2);
    }

    public static int zzf(int i) {
        return zzg(i);
    }

    public static int zzd(int i, long j) {
        return zzj(i << 3) + zzg(j);
    }

    public static int zzd(long j) {
        return zzg(j);
    }

    public static int zza(int i, zzkk zzkkVar) {
        return (zzj(8) << 1) + zzj(2, i) + zzb(3, zzkkVar);
    }

    public static int zzb(int i, zzkk zzkkVar) {
        int zzj = zzj(i << 3);
        int zza2 = zzkkVar.zza();
        return zzj + zzj(zza2) + zza2;
    }

    public static int zza(zzkk zzkkVar) {
        int zza2 = zzkkVar.zza();
        return zzj(zza2) + zza2;
    }

    public static int zzb(int i, zzlc zzlcVar) {
        return (zzj(8) << 1) + zzj(2, i) + zzj(24) + zzc(zzlcVar);
    }

    static int zzc(int i, zzlc zzlcVar, zzlu zzluVar) {
        return zzj(i << 3) + zza(zzlcVar, zzluVar);
    }

    public static int zzc(zzlc zzlcVar) {
        int zzcb = zzlcVar.zzcb();
        return zzj(zzcb) + zzcb;
    }

    static int zza(zzlc zzlcVar, zzlu zzluVar) {
        int zza2 = ((zzib) zzlcVar).zza(zzluVar);
        return zzj(zza2) + zza2;
    }

    public static int zzd(int i, zzik zzikVar) {
        return (zzj(8) << 1) + zzj(2, i) + zzc(3, zzikVar);
    }

    public static int zzh(int i, int i2) {
        return zzj(i << 3) + 4;
    }

    public static int zze(int i, long j) {
        return zzj(i << 3) + 8;
    }

    public static int zzi(int i, int i2) {
        return zzj(i << 3) + zzj(zzl(i2));
    }

    public static int zzh(int i) {
        return zzj(zzl(i));
    }

    public static int zzf(int i, long j) {
        return zzj(i << 3) + zzg(zzi(j));
    }

    public static int zzf(long j) {
        return zzg(zzi(j));
    }

    public static int zzb(int i, String str) {
        return zzj(i << 3) + zzb(str);
    }

    public static int zzb(String str) {
        int length;
        try {
            length = zzmp.zza(str);
        } catch (zzmt unused) {
            length = str.getBytes(zzjv.zza).length;
        }
        return zzj(length) + length;
    }

    public static int zzi(int i) {
        return zzj(i << 3);
    }

    public static int zzj(int i, int i2) {
        return zzj(i << 3) + zzj(i2);
    }

    public static int zzj(int i) {
        return (352 - (Integer.numberOfLeadingZeros(i) * 9)) >>> 6;
    }

    public static int zzg(int i, long j) {
        return zzj(i << 3) + zzg(j);
    }

    public static int zzg(long j) {
        return (640 - (Long.numberOfLeadingZeros(j) * 9)) >>> 6;
    }

    public static zzjc zzb(byte[] bArr) {
        return new zza(bArr, 0, bArr.length);
    }

    private zzjc() {
    }

    public final void zzb() {
        if (zza() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    final void zza(String str, zzmt zzmtVar) throws IOException {
        zzb.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", (Throwable) zzmtVar);
        byte[] bytes = str.getBytes(zzjv.zza);
        try {
            zzc(bytes.length);
            zza(bytes, 0, bytes.length);
        } catch (IndexOutOfBoundsException e) {
            throw new zzb(e);
        }
    }

    public final void zzb(boolean z) throws IOException {
        zza(z ? (byte) 1 : (byte) 0);
    }

    public final void zzb(int i, double d) throws IOException {
        zza(i, Double.doubleToRawLongBits(d));
    }

    public final void zzb(double d) throws IOException {
        zza(Double.doubleToRawLongBits(d));
    }

    public final void zzb(int i, float f) throws IOException {
        zza(i, Float.floatToRawIntBits(f));
    }

    public final void zzb(float f) throws IOException {
        zza(Float.floatToRawIntBits(f));
    }

    public final void zzk(int i, int i2) throws IOException {
        zzd(i, zzl(i2));
    }

    public final void zzk(int i) throws IOException {
        zzc(zzl(i));
    }

    public final void zzh(int i, long j) throws IOException {
        zzb(i, zzi(j));
    }

    public final void zzh(long j) throws IOException {
        zzb(zzi(j));
    }
}
