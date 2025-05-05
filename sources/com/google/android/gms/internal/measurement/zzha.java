package com.google.android.gms.internal.measurement;

import android.os.Binder;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes.dex */
public final /* synthetic */ class zzha {
    public static <V> V zza(zzhd<V> zzhdVar) {
        try {
            return zzhdVar.zza();
        } catch (SecurityException unused) {
            long clearCallingIdentity = Binder.clearCallingIdentity();
            try {
                return zzhdVar.zza();
            } finally {
                Binder.restoreCallingIdentity(clearCallingIdentity);
            }
        }
    }
}
