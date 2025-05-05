package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes.dex */
public final class zzna implements Supplier<zznd> {
    private static zzna zza = new zzna();
    private final Supplier<zznd> zzb = Suppliers.ofInstance(new zznc());

    @Override // com.google.common.base.Supplier
    public final /* synthetic */ zznd get() {
        return this.zzb.get();
    }

    @SideEffectFree
    public static boolean zza() {
        return ((zznd) zza.get()).zza();
    }
}
