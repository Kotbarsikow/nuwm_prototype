package com.google.android.gms.internal.fido;

import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-fido@@19.0.0 */
/* loaded from: classes.dex */
public final class zzbp extends zzbq {
    private final String zza;

    zzbp(String str) {
        this.zza = str;
    }

    @Override // java.lang.Comparable
    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        zzbq zzbqVar = (zzbq) obj;
        zzbqVar.zza();
        String str = this.zza;
        int length = str.length();
        String str2 = ((zzbp) zzbqVar).zza;
        return length != str2.length() ? str.length() - str2.length() : str.compareTo(str2);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return this.zza.equals(((zzbp) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{3, this.zza});
    }

    public final String toString() {
        return "\"" + this.zza + "\"";
    }

    @Override // com.google.android.gms.internal.fido.zzbq
    protected final int zza() {
        return 3;
    }
}
