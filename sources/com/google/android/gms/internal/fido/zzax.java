package com.google.android.gms.internal.fido;

/* compiled from: com.google.android.gms:play-services-fido@@19.0.0 */
/* loaded from: classes.dex */
final class zzax extends zzau {
    private final zzaz zza;

    zzax(zzaz zzazVar, int i) {
        super(zzazVar.size(), i);
        this.zza = zzazVar;
    }

    @Override // com.google.android.gms.internal.fido.zzau
    protected final Object zza(int i) {
        return this.zza.get(i);
    }
}
