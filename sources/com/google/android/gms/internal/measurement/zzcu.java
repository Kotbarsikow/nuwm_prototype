package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.common.base.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes.dex */
public final class zzcu {
    private static final ThreadLocal<Boolean> zza = new zzct();

    public static SharedPreferences zza(Context context, String str, int i, zzcm zzcmVar) {
        zzcp zzcpVar = zzcf.zza().zza(str, zzcmVar, zzcj.SHARED_PREFS_TYPE).equals("") ? new zzcp() : null;
        if (zzcpVar != null) {
            return zzcpVar;
        }
        ThreadLocal<Boolean> threadLocal = zza;
        Preconditions.checkArgument(threadLocal.get().booleanValue());
        threadLocal.set(Boolean.FALSE);
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences(str, 0);
            threadLocal.set(Boolean.TRUE);
            return sharedPreferences;
        } catch (Throwable th) {
            zza.set(Boolean.TRUE);
            throw th;
        }
    }
}
