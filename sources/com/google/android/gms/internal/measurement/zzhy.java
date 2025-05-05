package com.google.android.gms.internal.measurement;

import com.google.common.base.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes.dex */
public final class zzhy {
    private final boolean zza;

    public zzhy(zzhx zzhxVar) {
        Preconditions.checkNotNull(zzhxVar, "BuildInfo must be non-null");
        this.zza = !zzhxVar.zza();
    }

    public final boolean zza(String str) {
        Preconditions.checkNotNull(str, "flagName must not be null");
        if (this.zza) {
            return zzia.zza.get().containsValue(str);
        }
        return true;
    }
}
