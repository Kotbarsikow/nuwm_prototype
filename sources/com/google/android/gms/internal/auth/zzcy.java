package com.google.android.gms.internal.auth;

import android.util.Base64;
import android.util.Log;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-auth-base@@18.0.4 */
/* loaded from: classes.dex */
final class zzcy extends zzdc {
    final /* synthetic */ zzhu zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzcy(zzcz zzczVar, String str, Object obj, boolean z, zzhu zzhuVar, byte[] bArr) {
        super(zzczVar, "getTokenRefactor__blocked_packages", obj, true, null);
        this.zza = zzhuVar;
    }

    @Override // com.google.android.gms.internal.auth.zzdc
    final Object zza(Object obj) {
        try {
            return zzhr.zzk(Base64.decode((String) obj, 3));
        } catch (IOException | IllegalArgumentException unused) {
            Log.e("PhenotypeFlag", "Invalid byte[] value for " + super.zzc() + ": " + ((String) obj));
            return null;
        }
    }
}
