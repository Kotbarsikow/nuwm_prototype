package com.google.android.gms.measurement.internal;

import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.internal.measurement.zzov;
import com.google.firebase.messaging.Constants;
import kotlinx.coroutines.DebugKt;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
/* loaded from: classes.dex */
final class zzhh implements Runnable {
    private final /* synthetic */ com.google.android.gms.internal.measurement.zzbz zza;
    private final /* synthetic */ ServiceConnection zzb;
    private final /* synthetic */ zzhi zzc;

    zzhh(zzhi zzhiVar, com.google.android.gms.internal.measurement.zzbz zzbzVar, ServiceConnection serviceConnection) {
        this.zza = zzbzVar;
        this.zzb = serviceConnection;
        this.zzc = zzhiVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        String str;
        zzhf zzhfVar = this.zzc.zza;
        str = this.zzc.zzb;
        com.google.android.gms.internal.measurement.zzbz zzbzVar = this.zza;
        ServiceConnection serviceConnection = this.zzb;
        Bundle zza = zzhfVar.zza(str, zzbzVar);
        zzhfVar.zza.zzl().zzt();
        zzhfVar.zza.zzy();
        if (zza != null) {
            long j = zza.getLong("install_begin_timestamp_seconds", 0L) * 1000;
            if (j == 0) {
                zzhfVar.zza.zzj().zzu().zza("Service response is missing Install Referrer install timestamp");
            } else {
                String string = zza.getString("install_referrer");
                if (string == null || string.isEmpty()) {
                    zzhfVar.zza.zzj().zzg().zza("No referrer defined in Install Referrer response");
                } else {
                    zzhfVar.zza.zzj().zzp().zza("InstallReferrer API result", string);
                    boolean z = zzov.zza() && zzhfVar.zza.zzf().zza(zzbh.zzcu);
                    Bundle zza2 = zzhfVar.zza.zzt().zza(Uri.parse("?" + string), z);
                    if (zza2 == null) {
                        zzhfVar.zza.zzj().zzg().zza("No campaign params defined in Install Referrer result");
                    } else {
                        if (!z) {
                            String string2 = zza2.getString("medium");
                            if (string2 != null && !"(not set)".equalsIgnoreCase(string2) && !"organic".equalsIgnoreCase(string2)) {
                                long j2 = zza.getLong("referrer_click_timestamp_seconds", 0L) * 1000;
                                if (j2 == 0) {
                                    zzhfVar.zza.zzj().zzg().zza("Install Referrer is missing click timestamp for ad campaign");
                                } else {
                                    zza2.putLong("click_timestamp", j2);
                                }
                            }
                        } else if (zza2.containsKey("gclid") || zza2.containsKey("gbraid")) {
                            long j3 = zza.getLong("referrer_click_timestamp_server_seconds", 0L) * 1000;
                            if (j3 > 0) {
                                zza2.putLong("click_timestamp", j3);
                            }
                        }
                        if (j == zzhfVar.zza.zzn().zzd.zza()) {
                            zzhfVar.zza.zzj().zzp().zza("Logging Install Referrer campaign from module while it may have already been logged.");
                        }
                        if (zzhfVar.zza.zzac()) {
                            zzhfVar.zza.zzn().zzd.zza(j);
                            zzhfVar.zza.zzj().zzp().zza("Logging Install Referrer campaign from gmscore with ", "referrer API v2");
                            zza2.putString("_cis", "referrer API v2");
                            zzhfVar.zza.zzp().zza(DebugKt.DEBUG_PROPERTY_VALUE_AUTO, Constants.ScionAnalytics.EVENT_FIREBASE_CAMPAIGN, zza2, str);
                        }
                    }
                }
            }
        }
        if (serviceConnection != null) {
            ConnectionTracker.getInstance().unbindService(zzhfVar.zza.zza(), serviceConnection);
        }
    }
}
