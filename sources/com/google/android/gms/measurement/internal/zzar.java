package com.google.android.gms.measurement.internal;

import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzfy;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.messaging.Constants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
/* loaded from: classes.dex */
public final class zzar {
    private final String zza;
    private long zzb = -1;
    private final /* synthetic */ zzal zzc;

    public final List<zzap> zza() {
        Cursor query;
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            try {
                query = this.zzc.e_().query("raw_events", new String[]{"rowid", AppMeasurementSdk.ConditionalUserProperty.NAME, "timestamp", "metadata_fingerprint", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "realtime"}, "app_id = ? and rowid > ?", new String[]{this.zza, String.valueOf(this.zzb)}, null, null, "rowid", "1000");
            } catch (SQLiteException e) {
                this.zzc.zzj().zzg().zza("Data loss. Error querying raw events batch. appId", zzgo.zza(this.zza), e);
                if (0 != 0) {
                    cursor.close();
                }
            }
            if (!query.moveToFirst()) {
                List<zzap> emptyList = Collections.emptyList();
                if (query != null) {
                    query.close();
                }
                return emptyList;
            }
            do {
                long j = query.getLong(0);
                long j2 = query.getLong(3);
                boolean z = query.getLong(5) == 1;
                byte[] blob = query.getBlob(4);
                if (j > this.zzb) {
                    this.zzb = j;
                }
                try {
                    zzfy.zzf.zza zzaVar = (zzfy.zzf.zza) zzoo.zza(zzfy.zzf.zze(), blob);
                    String string = query.getString(1);
                    if (string == null) {
                        string = "";
                    }
                    zzaVar.zza(string).zzb(query.getLong(2));
                    arrayList.add(new zzap(j, j2, z, (zzfy.zzf) ((com.google.android.gms.internal.measurement.zzjt) zzaVar.zzai())));
                } catch (IOException e2) {
                    this.zzc.zzj().zzg().zza("Data loss. Failed to merge raw event. appId", zzgo.zza(this.zza), e2);
                }
            } while (query.moveToNext());
            if (query != null) {
                query.close();
            }
            return arrayList;
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    public zzar(zzal zzalVar, String str) {
        this.zzc = zzalVar;
        Preconditions.checkNotEmpty(str);
        this.zza = str;
    }
}
