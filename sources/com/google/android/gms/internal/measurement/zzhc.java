package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.database.ContentObserver;
import android.util.Log;
import androidx.core.content.PermissionChecker;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes.dex */
final class zzhc implements zzhb {
    private static zzhc zza;

    @Nullable
    private final Context zzb;

    @Nullable
    private final ContentObserver zzc;

    static zzhc zza(Context context) {
        zzhc zzhcVar;
        synchronized (zzhc.class) {
            if (zza == null) {
                zza = PermissionChecker.checkSelfPermission(context, "com.google.android.providers.gsf.permission.READ_GSERVICES") == 0 ? new zzhc(context) : new zzhc();
            }
            zzhcVar = zza;
        }
        return zzhcVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzhb
    @Nullable
    /* renamed from: zzc */
    public final String zza(String str) {
        Context context = this.zzb;
        if (context != null && !zzgs.zza(context)) {
            try {
                return (String) zzha.zza(new zzhd() { // from class: com.google.android.gms.internal.measurement.zzhf
                    private /* synthetic */ String zzb;

                    public /* synthetic */ zzhf(String str2) {
                        r2 = str2;
                    }

                    @Override // com.google.android.gms.internal.measurement.zzhd
                    public final Object zza() {
                        return zzhc.this.zzb(r2);
                    }
                });
            } catch (IllegalStateException | NullPointerException | SecurityException e) {
                Log.e("GservicesLoader", "Unable to read GServices for: " + str2, e);
            }
        }
        return null;
    }

    final /* synthetic */ String zzb(String str) {
        return zzgj.zza(this.zzb.getContentResolver(), str, null);
    }

    private zzhc() {
        this.zzb = null;
        this.zzc = null;
    }

    private zzhc(Context context) {
        this.zzb = context;
        zzhe zzheVar = new zzhe(this, null);
        this.zzc = zzheVar;
        context.getContentResolver().registerContentObserver(zzgi.zza, true, zzheVar);
    }

    static synchronized void zza() {
        Context context;
        synchronized (zzhc.class) {
            zzhc zzhcVar = zza;
            if (zzhcVar != null && (context = zzhcVar.zzb) != null && zzhcVar.zzc != null) {
                context.getContentResolver().unregisterContentObserver(zza.zzc);
            }
            zza = null;
        }
    }
}
