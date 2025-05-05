package com.google.android.gms.internal.measurement;

import android.database.ContentObserver;
import android.os.Handler;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes.dex */
final class zzgp extends ContentObserver {
    private final /* synthetic */ zzgn zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzgp(zzgn zzgnVar, Handler handler) {
        super(null);
        this.zza = zzgnVar;
    }

    @Override // android.database.ContentObserver
    public final void onChange(boolean z) {
        AtomicBoolean atomicBoolean;
        atomicBoolean = this.zza.zza;
        atomicBoolean.set(true);
    }
}
