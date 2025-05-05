package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzpn;
import java.util.Arrays;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes.dex */
public final class zzhc {
    private final String zza;
    private final Bundle zzb;
    private Bundle zzc;
    private final /* synthetic */ zzha zzd;

    /* JADX WARN: Removed duplicated region for block: B:27:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0120 A[Catch: NumberFormatException | JSONException -> 0x0128, NumberFormatException | JSONException -> 0x0128, TRY_LEAVE, TryCatch #1 {NumberFormatException | JSONException -> 0x0128, blocks: (B:13:0x0025, B:31:0x0091, B:31:0x0091, B:34:0x00a2, B:34:0x00a2, B:36:0x00a8, B:36:0x00a8, B:38:0x00b6, B:38:0x00b6, B:40:0x00c8, B:40:0x00c8, B:42:0x00d1, B:42:0x00d1, B:46:0x00d5, B:46:0x00d5, B:48:0x00db, B:48:0x00db, B:50:0x00e9, B:50:0x00e9, B:52:0x00fb, B:52:0x00fb, B:54:0x0104, B:54:0x0104, B:58:0x0108, B:58:0x0108, B:60:0x0114, B:60:0x0114, B:62:0x0120, B:62:0x0120, B:64:0x0052, B:67:0x005c, B:70:0x0066, B:73:0x0070, B:76:0x007a), top: B:12:0x0025, outer: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.os.Bundle zza() {
        /*
            Method dump skipped, instructions count: 378
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzhc.zza():android.os.Bundle");
    }

    private final String zzb(Bundle bundle) {
        JSONArray jSONArray = new JSONArray();
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            if (obj != null) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("n", str);
                    if (zzpn.zza() && this.zzd.zze().zza(zzbh.zzci)) {
                        if (obj instanceof String) {
                            jSONObject.put("v", String.valueOf(obj));
                            jSONObject.put("t", "s");
                        } else if (obj instanceof Long) {
                            jSONObject.put("v", String.valueOf(obj));
                            jSONObject.put("t", "l");
                        } else if (obj instanceof int[]) {
                            jSONObject.put("v", Arrays.toString((int[]) obj));
                            jSONObject.put("t", "ia");
                        } else if (obj instanceof long[]) {
                            jSONObject.put("v", Arrays.toString((long[]) obj));
                            jSONObject.put("t", "la");
                        } else if (obj instanceof Double) {
                            jSONObject.put("v", String.valueOf(obj));
                            jSONObject.put("t", "d");
                        } else {
                            this.zzd.zzj().zzg().zza("Cannot serialize bundle value to SharedPreferences. Type", obj.getClass());
                        }
                    } else {
                        jSONObject.put("v", String.valueOf(obj));
                        if (obj instanceof String) {
                            jSONObject.put("t", "s");
                        } else if (obj instanceof Long) {
                            jSONObject.put("t", "l");
                        } else if (obj instanceof Double) {
                            jSONObject.put("t", "d");
                        } else {
                            this.zzd.zzj().zzg().zza("Cannot serialize bundle value to SharedPreferences. Type", obj.getClass());
                        }
                    }
                    jSONArray.put(jSONObject);
                } catch (JSONException e) {
                    this.zzd.zzj().zzg().zza("Cannot serialize bundle value to SharedPreferences", e);
                }
            }
        }
        return jSONArray.toString();
    }

    public zzhc(zzha zzhaVar, String str, Bundle bundle) {
        this.zzd = zzhaVar;
        Preconditions.checkNotEmpty(str);
        this.zza = str;
        if (zzhaVar.zze().zza(zzbh.zzdk)) {
            this.zzb = new Bundle();
        } else {
            this.zzb = new Bundle();
        }
    }

    public final void zza(Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        } else if (this.zzd.zze().zza(zzbh.zzdk)) {
            bundle = new Bundle(bundle);
        }
        SharedPreferences.Editor edit = this.zzd.zzg().edit();
        if (bundle.size() == 0) {
            edit.remove(this.zza);
        } else {
            edit.putString(this.zza, zzb(bundle));
        }
        edit.apply();
        this.zzc = bundle;
    }
}
