package com.google.android.gms.internal.fido;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-fido@@19.0.0 */
/* loaded from: classes.dex */
final class zzbi extends zzbk {
    final char[] zza;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    zzbi(java.lang.String r4, java.lang.String r5) {
        /*
            r3 = this;
            com.google.android.gms.internal.fido.zzbh r4 = new com.google.android.gms.internal.fido.zzbh
            java.lang.String r5 = "base16()"
            java.lang.String r0 = "0123456789ABCDEF"
            char[] r0 = r0.toCharArray()
            r4.<init>(r5, r0)
            r5 = 0
            r3.<init>(r4, r5)
            r5 = 512(0x200, float:7.17E-43)
            char[] r5 = new char[r5]
            r3.zza = r5
            char[] r5 = com.google.android.gms.internal.fido.zzbh.zzc(r4)
            int r5 = r5.length
            r0 = 16
            r1 = 0
            if (r5 != r0) goto L23
            r5 = 1
            goto L24
        L23:
            r5 = 0
        L24:
            com.google.android.gms.internal.fido.zzas.zzc(r5)
        L27:
            r5 = 256(0x100, float:3.59E-43)
            if (r1 >= r5) goto L44
            char[] r5 = r3.zza
            int r0 = r1 >>> 4
            char r0 = r4.zza(r0)
            r5[r1] = r0
            char[] r5 = r3.zza
            r0 = r1 | 256(0x100, float:3.59E-43)
            r2 = r1 & 15
            char r2 = r4.zza(r2)
            r5[r0] = r2
            int r1 = r1 + 1
            goto L27
        L44:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.fido.zzbi.<init>(java.lang.String, java.lang.String):void");
    }

    @Override // com.google.android.gms.internal.fido.zzbk, com.google.android.gms.internal.fido.zzbl
    final void zza(Appendable appendable, byte[] bArr, int i, int i2) throws IOException {
        zzas.zze(0, i2, bArr.length);
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = bArr[i3] & 255;
            appendable.append(this.zza[i4]);
            appendable.append(this.zza[i4 | 256]);
        }
    }
}
