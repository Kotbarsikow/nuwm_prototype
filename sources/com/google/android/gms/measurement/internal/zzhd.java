package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes.dex */
public final class zzhd {
    private final String zza;
    private boolean zzb;
    private String zzc;
    private final /* synthetic */ zzha zzd;

    public final String zza() {
        if (!this.zzb) {
            this.zzb = true;
            this.zzc = this.zzd.zzg().getString(this.zza, null);
        }
        return this.zzc;
    }

    public zzhd(zzha zzhaVar, String str, String str2) {
        this.zzd = zzhaVar;
        Preconditions.checkNotEmpty(str);
        this.zza = str;
    }

    public final void zza(String str) {
        SharedPreferences.Editor edit = this.zzd.zzg().edit();
        edit.putString(this.zza, str);
        edit.apply();
        this.zzc = str;
    }
}
