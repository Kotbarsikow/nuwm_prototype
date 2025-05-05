package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
/* loaded from: classes.dex */
public abstract class zzay {
    final List<zzbv> zza = new ArrayList();

    final zzaq zza(String str) {
        if (!this.zza.contains(zzg.zza(str))) {
            throw new IllegalArgumentException("Command not supported");
        }
        throw new UnsupportedOperationException("Command not implemented: " + str);
    }

    public abstract zzaq zza(String str, zzh zzhVar, List<zzaq> list);

    protected zzay() {
    }
}
