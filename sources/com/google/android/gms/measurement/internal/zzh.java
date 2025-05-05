package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes.dex */
abstract class zzh extends zze {
    private boolean zza;

    zzh(zzhy zzhyVar) {
        super(zzhyVar);
        this.zzu.zzaa();
    }

    protected void zzx() {
    }

    protected abstract boolean zzz();

    protected final void zzu() {
        if (!zzy()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    public final void zzv() {
        if (this.zza) {
            throw new IllegalStateException("Can't initialize twice");
        }
        if (zzz()) {
            return;
        }
        this.zzu.zzz();
        this.zza = true;
    }

    public final void zzw() {
        if (this.zza) {
            throw new IllegalStateException("Can't initialize twice");
        }
        zzx();
        this.zzu.zzz();
        this.zza = true;
    }

    final boolean zzy() {
        return this.zza;
    }
}
