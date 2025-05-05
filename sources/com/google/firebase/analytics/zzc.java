package com.google.firebase.analytics;

import com.google.android.gms.internal.measurement.zzdy;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-measurement-api@@22.1.2 */
/* loaded from: classes2.dex */
final class zzc implements Callable<String> {
    private final /* synthetic */ FirebaseAnalytics zza;

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ String call() throws Exception {
        zzdy zzdyVar;
        zzdyVar = this.zza.zzb;
        return zzdyVar.zze();
    }

    zzc(FirebaseAnalytics firebaseAnalytics) {
        this.zza = firebaseAnalytics;
    }
}
