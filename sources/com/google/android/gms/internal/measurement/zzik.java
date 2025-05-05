package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Locale;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
/* loaded from: classes.dex */
public abstract class zzik implements Serializable, Iterable<Byte> {
    public static final zzik zza = new zziv(zzjv.zzb);
    private static final zzir zzb = new zziu();
    private int zzc = 0;

    static /* synthetic */ int zza(byte b) {
        return b & 255;
    }

    public abstract boolean equals(Object obj);

    public abstract byte zza(int i);

    public abstract zzik zza(int i, int i2);

    abstract void zza(zzil zzilVar) throws IOException;

    abstract byte zzb(int i);

    public abstract int zzb();

    protected abstract int zzb(int i, int i2, int i3);

    static int zza(int i, int i2, int i3) {
        int i4 = i2 - i;
        if ((i | i2 | i4 | (i3 - i2)) >= 0) {
            return i4;
        }
        if (i < 0) {
            throw new IndexOutOfBoundsException("Beginning index: " + i + " < 0");
        }
        if (i2 < i) {
            throw new IndexOutOfBoundsException("Beginning index larger than ending index: " + i + ", " + i2);
        }
        throw new IndexOutOfBoundsException("End index: " + i2 + " >= " + i3);
    }

    public final int hashCode() {
        int i = this.zzc;
        if (i == 0) {
            int zzb2 = zzb();
            i = zzb(zzb2, 0, zzb2);
            if (i == 0) {
                i = 1;
            }
            this.zzc = i;
        }
        return i;
    }

    protected final int zza() {
        return this.zzc;
    }

    static zzit zzc(int i) {
        return new zzit(i);
    }

    public static zzik zza(byte[] bArr) {
        return zza(bArr, 0, bArr.length);
    }

    public static zzik zza(byte[] bArr, int i, int i2) {
        zza(i, i + i2, bArr.length);
        return new zziv(zzb.zza(bArr, i, i2));
    }

    public static zzik zza(String str) {
        return new zziv(str.getBytes(zzjv.zza));
    }

    static zzik zzb(byte[] bArr) {
        return new zziv(bArr);
    }

    public final String toString() {
        String str;
        Locale locale = Locale.ROOT;
        String hexString = Integer.toHexString(System.identityHashCode(this));
        Integer valueOf = Integer.valueOf(zzb());
        if (zzb() <= 50) {
            str = zzmg.zza(this);
        } else {
            str = zzmg.zza(zza(0, 47)) + "...";
        }
        return String.format(locale, "<ByteString@%s size=%d contents=\"%s\">", hexString, valueOf, str);
    }

    @Override // java.lang.Iterable
    public /* synthetic */ Iterator<Byte> iterator() {
        return new zzin(this);
    }

    static {
        new zzim();
    }

    zzik() {
    }
}
