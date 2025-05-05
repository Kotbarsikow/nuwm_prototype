package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.Intent;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
/* loaded from: classes.dex */
public final class zzhj {
    private final zza zza;

    /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
    public interface zza {
        void doStartService(Context context, Intent intent);
    }

    public zzhj(zza zzaVar) {
        Preconditions.checkNotNull(zzaVar);
        this.zza = zzaVar;
    }

    public final void zza(Context context, Intent intent) {
        zzgo zzj = zzhy.zza(context, null, null).zzj();
        if (intent == null) {
            zzj.zzu().zza("Receiver called with null intent");
            return;
        }
        String action = intent.getAction();
        zzj.zzp().zza("Local receiver got", action);
        if ("com.google.android.gms.measurement.UPLOAD".equals(action)) {
            Intent className = new Intent().setClassName(context, "com.google.android.gms.measurement.AppMeasurementService");
            className.setAction("com.google.android.gms.measurement.UPLOAD");
            zzj.zzp().zza("Starting wakeful intent.");
            this.zza.doStartService(context, className);
            return;
        }
        if ("com.android.vending.INSTALL_REFERRER".equals(action)) {
            zzj.zzu().zza("Install Referrer Broadcasts are deprecated");
        }
    }
}
