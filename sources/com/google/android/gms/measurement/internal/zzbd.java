package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes.dex */
final class zzbd implements Iterator<String> {
    private Iterator<String> zza;
    private final /* synthetic */ zzbe zzb;

    @Override // java.util.Iterator
    public final /* synthetic */ String next() {
        return this.zza.next();
    }

    zzbd(zzbe zzbeVar) {
        Bundle bundle;
        this.zzb = zzbeVar;
        bundle = zzbeVar.zza;
        this.zza = bundle.keySet().iterator();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Remove not supported");
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zza.hasNext();
    }
}
