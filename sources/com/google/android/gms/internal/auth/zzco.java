package com.google.android.gms.internal.auth;

import android.content.Context;
import android.database.ContentObserver;
import android.util.Log;
import androidx.core.content.PermissionChecker;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-auth-base@@18.0.4 */
/* loaded from: classes.dex */
final class zzco implements zzcl {
    private static zzco zza;

    @Nullable
    private final Context zzb;

    @Nullable
    private final ContentObserver zzc;

    private zzco() {
        this.zzb = null;
        this.zzc = null;
    }

    private zzco(Context context) {
        this.zzb = context;
        zzcn zzcnVar = new zzcn(this, null);
        this.zzc = zzcnVar;
        context.getContentResolver().registerContentObserver(zzcb.zza, true, zzcnVar);
    }

    static zzco zza(Context context) {
        zzco zzcoVar;
        synchronized (zzco.class) {
            if (zza == null) {
                zza = PermissionChecker.checkSelfPermission(context, "com.google.android.providers.gsf.permission.READ_GSERVICES") == 0 ? new zzco(context) : new zzco();
            }
            zzcoVar = zza;
        }
        return zzcoVar;
    }

    static synchronized void zze() {
        Context context;
        synchronized (zzco.class) {
            zzco zzcoVar = zza;
            if (zzcoVar != null && (context = zzcoVar.zzb) != null && zzcoVar.zzc != null) {
                context.getContentResolver().unregisterContentObserver(zza.zzc);
            }
            zza = null;
        }
    }

    @Override // com.google.android.gms.internal.auth.zzcl
    @Nullable
    /* renamed from: zzc */
    public final String zzb(String str) {
        Context context = this.zzb;
        if (context != null && !zzcc.zza(context)) {
            try {
                return (String) zzcj.zza(new zzck() { // from class: com.google.android.gms.internal.auth.zzcm
                    public final /* synthetic */ String zzb;

                    public /* synthetic */ zzcm(String str2) {
                        r2 = str2;
                    }

                    @Override // com.google.android.gms.internal.auth.zzck
                    public final Object zza() {
                        return zzco.this.zzd(r2);
                    }
                });
            } catch (IllegalStateException | NullPointerException | SecurityException e) {
                Log.e("GservicesLoader", "Unable to read GServices for: ".concat(String.valueOf(str2)), e);
            }
        }
        return null;
    }

    final /* synthetic */ String zzd(String str) {
        return zzcb.zza(this.zzb.getContentResolver(), str, null);
    }
}
