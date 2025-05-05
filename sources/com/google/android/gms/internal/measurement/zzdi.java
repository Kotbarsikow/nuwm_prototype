package com.google.android.gms.internal.measurement;

import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
/* loaded from: classes.dex */
final class zzdi implements zzdd {
    @Override // com.google.android.gms.internal.measurement.zzdd
    public final Runnable zza(Runnable runnable) {
        return runnable;
    }

    @Override // com.google.android.gms.internal.measurement.zzdd
    public final <V> Callable<V> zza(Callable<V> callable) {
        return callable;
    }

    zzdi() {
    }
}
