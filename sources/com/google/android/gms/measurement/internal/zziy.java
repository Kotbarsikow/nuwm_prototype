package com.google.android.gms.measurement.internal;

import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzpn;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
/* loaded from: classes.dex */
final class zziy implements Callable<List<zzno>> {
    private final /* synthetic */ zzo zza;
    private final /* synthetic */ Bundle zzb;
    private final /* synthetic */ zzic zzc;

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ List<zzno> call() throws Exception {
        zznv zznvVar;
        zznv zznvVar2;
        zznvVar = this.zzc.zza;
        zznvVar.zzr();
        zznvVar2 = this.zzc.zza;
        zzo zzoVar = this.zza;
        Bundle bundle = this.zzb;
        zznvVar2.zzl().zzt();
        if (!zzpn.zza() || !zznvVar2.zze().zze(zzoVar.zza, zzbh.zzch) || zzoVar.zza == null) {
            return new ArrayList();
        }
        if (bundle != null) {
            int[] intArray = bundle.getIntArray("uriSources");
            long[] longArray = bundle.getLongArray("uriTimestamps");
            if (intArray != null) {
                if (longArray == null || longArray.length != intArray.length) {
                    zznvVar2.zzj().zzg().zza("Uri sources and timestamps do not match");
                } else {
                    for (int i = 0; i < intArray.length; i++) {
                        zzal zzf = zznvVar2.zzf();
                        String str = zzoVar.zza;
                        int i2 = intArray[i];
                        long j = longArray[i];
                        Preconditions.checkNotEmpty(str);
                        zzf.zzt();
                        zzf.zzal();
                        try {
                            int delete = zzf.e_().delete("trigger_uris", "app_id=? and source=? and timestamp_millis<=?", new String[]{str, String.valueOf(i2), String.valueOf(j)});
                            zzf.zzj().zzp().zza("Pruned " + delete + " trigger URIs. appId, source, timestamp", str, Integer.valueOf(i2), Long.valueOf(j));
                        } catch (SQLiteException e) {
                            zzf.zzj().zzg().zza("Error pruning trigger URIs. appId", zzgo.zza(str), e);
                        }
                    }
                }
            }
        }
        return zznvVar2.zzf().zzk(zzoVar.zza);
    }

    zziy(zzic zzicVar, zzo zzoVar, Bundle bundle) {
        this.zza = zzoVar;
        this.zzb = bundle;
        this.zzc = zzicVar;
    }
}
