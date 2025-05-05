package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes.dex */
public final class zzqa implements Supplier<zzqd> {
    private static zzqa zza = new zzqa();
    private final Supplier<zzqd> zzb = Suppliers.ofInstance(new zzqc());

    @Override // com.google.common.base.Supplier
    public final /* synthetic */ zzqd get() {
        return this.zzb.get();
    }

    @SideEffectFree
    public static boolean zza() {
        return ((zzqd) zza.get()).zza();
    }
}
