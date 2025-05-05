package com.google.android.gms.internal.measurement;

import j$.util.DesugarCollections;
import java.util.List;
import java.util.Map;

/* JADX INFO: Add missing generic type declarations: [FieldDescriptorT] */
/* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
/* loaded from: classes.dex */
final class zzly<FieldDescriptorT> extends zzlv<FieldDescriptorT, Object> {
    zzly() {
        super();
    }

    @Override // com.google.android.gms.internal.measurement.zzlv
    public final void zzd() {
        if (!zze()) {
            for (int i = 0; i < zza(); i++) {
                Map.Entry<FieldDescriptorT, Object> zza = zza(i);
                if (((zzjo) zza.getKey()).zze()) {
                    zza.setValue(DesugarCollections.unmodifiableList((List) zza.getValue()));
                }
            }
            for (Map.Entry<FieldDescriptorT, Object> entry : zzb()) {
                if (((zzjo) entry.getKey()).zze()) {
                    entry.setValue(DesugarCollections.unmodifiableList((List) entry.getValue()));
                }
            }
        }
        super.zzd();
    }
}
