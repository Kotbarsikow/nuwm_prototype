package com.google.android.gms.internal.measurement;

import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
/* loaded from: classes.dex */
final class zzam implements Iterator<zzaq> {
    private final /* synthetic */ Iterator zza;

    @Override // java.util.Iterator
    public final /* synthetic */ zzaq next() {
        return new zzas((String) this.zza.next());
    }

    zzam(Iterator it) {
        this.zza = it;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zza.hasNext();
    }
}
