package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.ProcessUtils;
import com.google.android.gms.common.wrappers.Wrappers;
import java.lang.reflect.InvocationTargetException;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.dataflow.qual.Pure;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes.dex */
public final class zzag extends zzja {
    private Boolean zza;
    private String zzb;
    private zzai zzc;
    private Boolean zzd;

    public final double zza(String str, zzfz<Double> zzfzVar) {
        if (TextUtils.isEmpty(str)) {
            return zzfzVar.zza(null).doubleValue();
        }
        String zza = this.zzc.zza(str, zzfzVar.zza());
        if (TextUtils.isEmpty(zza)) {
            return zzfzVar.zza(null).doubleValue();
        }
        try {
            return zzfzVar.zza(Double.valueOf(Double.parseDouble(zza))).doubleValue();
        } catch (NumberFormatException unused) {
            return zzfzVar.zza(null).doubleValue();
        }
    }

    final int zza(String str) {
        return zza(str, zzbh.zzaj, 500, 2000);
    }

    final int zza(String str, boolean z) {
        if (z) {
            return zza(str, zzbh.zzat, 100, 500);
        }
        return 500;
    }

    final int zzb(String str, boolean z) {
        return Math.max(zza(str, z), 256);
    }

    public final int zzc() {
        return zzq().zza(201500000, true) ? 100 : 25;
    }

    public final int zzb(String str) {
        return zza(str, zzbh.zzak, 25, 100);
    }

    public final int zzc(String str) {
        return zzb(str, zzbh.zzo);
    }

    public final int zzb(String str, zzfz<Integer> zzfzVar) {
        if (TextUtils.isEmpty(str)) {
            return zzfzVar.zza(null).intValue();
        }
        String zza = this.zzc.zza(str, zzfzVar.zza());
        if (TextUtils.isEmpty(zza)) {
            return zzfzVar.zza(null).intValue();
        }
        try {
            return zzfzVar.zza(Integer.valueOf(Integer.parseInt(zza))).intValue();
        } catch (NumberFormatException unused) {
            return zzfzVar.zza(null).intValue();
        }
    }

    public final int zza(String str, zzfz<Integer> zzfzVar, int i, int i2) {
        return Math.max(Math.min(zzb(str, zzfzVar), i2), i);
    }

    final long zzd(String str) {
        return zzc(str, zzbh.zza);
    }

    public static long zzg() {
        return zzbh.zzd.zza(null).longValue();
    }

    public static long zzh() {
        return zzbh.zzk.zza(null).intValue();
    }

    public static long zzm() {
        return zzbh.zzae.zza(null).longValue();
    }

    public final long zzc(String str, zzfz<Long> zzfzVar) {
        if (TextUtils.isEmpty(str)) {
            return zzfzVar.zza(null).longValue();
        }
        String zza = this.zzc.zza(str, zzfzVar.zza());
        if (TextUtils.isEmpty(zza)) {
            return zzfzVar.zza(null).longValue();
        }
        try {
            return zzfzVar.zza(Long.valueOf(Long.parseLong(zza))).longValue();
        } catch (NumberFormatException unused) {
            return zzfzVar.zza(null).longValue();
        }
    }

    public static long zzn() {
        return zzbh.zzz.zza(null).longValue();
    }

    @Override // com.google.android.gms.measurement.internal.zzja, com.google.android.gms.measurement.internal.zzjc
    @Pure
    public final /* bridge */ /* synthetic */ Context zza() {
        return super.zza();
    }

    private final Bundle zzaa() {
        try {
            if (zza().getPackageManager() == null) {
                zzj().zzg().zza("Failed to load metadata: PackageManager is null");
                return null;
            }
            ApplicationInfo applicationInfo = Wrappers.packageManager(zza()).getApplicationInfo(zza().getPackageName(), 128);
            if (applicationInfo == null) {
                zzj().zzg().zza("Failed to load metadata: ApplicationInfo is null");
                return null;
            }
            return applicationInfo.metaData;
        } catch (PackageManager.NameNotFoundException e) {
            zzj().zzg().zza("Failed to load metadata: Package name not found", e);
            return null;
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzja, com.google.android.gms.measurement.internal.zzjc
    @Pure
    public final /* bridge */ /* synthetic */ Clock zzb() {
        return super.zzb();
    }

    @Override // com.google.android.gms.measurement.internal.zzja, com.google.android.gms.measurement.internal.zzjc
    @Pure
    public final /* bridge */ /* synthetic */ zzab zzd() {
        return super.zzd();
    }

    @Override // com.google.android.gms.measurement.internal.zzja
    @Pure
    public final /* bridge */ /* synthetic */ zzag zze() {
        return super.zze();
    }

    @Override // com.google.android.gms.measurement.internal.zzja
    @Pure
    public final /* bridge */ /* synthetic */ zzaz zzf() {
        return super.zzf();
    }

    @Override // com.google.android.gms.measurement.internal.zzja
    @Pure
    public final /* bridge */ /* synthetic */ zzgh zzi() {
        return super.zzi();
    }

    @Override // com.google.android.gms.measurement.internal.zzja, com.google.android.gms.measurement.internal.zzjc
    @Pure
    public final /* bridge */ /* synthetic */ zzgo zzj() {
        return super.zzj();
    }

    @Override // com.google.android.gms.measurement.internal.zzja
    @Pure
    public final /* bridge */ /* synthetic */ zzha zzk() {
        return super.zzk();
    }

    @Override // com.google.android.gms.measurement.internal.zzja, com.google.android.gms.measurement.internal.zzjc
    @Pure
    public final /* bridge */ /* synthetic */ zzhv zzl() {
        return super.zzl();
    }

    public final zzjh zzc(String str, boolean z) {
        Object obj;
        Preconditions.checkNotEmpty(str);
        Bundle zzaa = zzaa();
        if (zzaa == null) {
            zzj().zzg().zza("Failed to load metadata: Metadata bundle is null");
            obj = null;
        } else {
            obj = zzaa.get(str);
        }
        if (obj == null) {
            return zzjh.UNINITIALIZED;
        }
        if (Boolean.TRUE.equals(obj)) {
            return zzjh.GRANTED;
        }
        if (Boolean.FALSE.equals(obj)) {
            return zzjh.DENIED;
        }
        if (z && "eu_consent_policy".equals(obj)) {
            return zzjh.POLICY;
        }
        zzj().zzu().zza("Invalid manifest metadata for", str);
        return zzjh.UNINITIALIZED;
    }

    @Override // com.google.android.gms.measurement.internal.zzja
    @Pure
    public final /* bridge */ /* synthetic */ zzos zzq() {
        return super.zzq();
    }

    final Boolean zze(String str) {
        Preconditions.checkNotEmpty(str);
        Bundle zzaa = zzaa();
        if (zzaa == null) {
            zzj().zzg().zza("Failed to load metadata: Metadata bundle is null");
            return null;
        }
        if (zzaa.containsKey(str)) {
            return Boolean.valueOf(zzaa.getBoolean(str));
        }
        return null;
    }

    public final String zzo() {
        return zza("debug.firebase.analytics.app", "");
    }

    public final String zzp() {
        return zza("debug.deferred.deeplink", "");
    }

    public final String zzd(String str, zzfz<String> zzfzVar) {
        if (TextUtils.isEmpty(str)) {
            return zzfzVar.zza(null);
        }
        return zzfzVar.zza(this.zzc.zza(str, zzfzVar.zza()));
    }

    public final String zzu() {
        return this.zzb;
    }

    final String zzf(String str) {
        return zzd(str, zzbh.zzan);
    }

    private final String zza(String str, String str2) {
        try {
            String str3 = (String) Class.forName("android.os.SystemProperties").getMethod("get", String.class, String.class).invoke(null, str, str2);
            Preconditions.checkNotNull(str3);
            return str3;
        } catch (ClassNotFoundException e) {
            zzj().zzg().zza("Could not find SystemProperties class", e);
            return str2;
        } catch (IllegalAccessException e2) {
            zzj().zzg().zza("Could not access SystemProperties.get()", e2);
            return str2;
        } catch (NoSuchMethodException e3) {
            zzj().zzg().zza("Could not find SystemProperties.get() method", e3);
            return str2;
        } catch (InvocationTargetException e4) {
            zzj().zzg().zza("SystemProperties.get() threw an exception", e4);
            return str2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x002a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final java.util.List<java.lang.String> zzg(java.lang.String r4) {
        /*
            r3 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r4)
            android.os.Bundle r0 = r3.zzaa()
            r1 = 0
            if (r0 != 0) goto L19
            com.google.android.gms.measurement.internal.zzgo r4 = r3.zzj()
            com.google.android.gms.measurement.internal.zzgq r4 = r4.zzg()
            java.lang.String r0 = "Failed to load metadata: Metadata bundle is null"
            r4.zza(r0)
        L17:
            r4 = r1
            goto L28
        L19:
            boolean r2 = r0.containsKey(r4)
            if (r2 != 0) goto L20
            goto L17
        L20:
            int r4 = r0.getInt(r4)
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
        L28:
            if (r4 != 0) goto L2b
            return r1
        L2b:
            android.content.Context r0 = r3.zza()     // Catch: android.content.res.Resources.NotFoundException -> L43
            android.content.res.Resources r0 = r0.getResources()     // Catch: android.content.res.Resources.NotFoundException -> L43
            int r4 = r4.intValue()     // Catch: android.content.res.Resources.NotFoundException -> L43
            java.lang.String[] r4 = r0.getStringArray(r4)     // Catch: android.content.res.Resources.NotFoundException -> L43
            if (r4 != 0) goto L3e
            return r1
        L3e:
            java.util.List r4 = java.util.Arrays.asList(r4)     // Catch: android.content.res.Resources.NotFoundException -> L43
            return r4
        L43:
            r4 = move-exception
            com.google.android.gms.measurement.internal.zzgo r0 = r3.zzj()
            com.google.android.gms.measurement.internal.zzgq r0 = r0.zzg()
            java.lang.String r2 = "Failed to load string array from metadata: resource not found"
            r0.zza(r2, r4)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzag.zzg(java.lang.String):java.util.List");
    }

    zzag(zzhy zzhyVar) {
        super(zzhyVar);
        this.zzc = new zzai() { // from class: com.google.android.gms.measurement.internal.zzaf
            @Override // com.google.android.gms.measurement.internal.zzai
            public final String zza(String str, String str2) {
                return null;
            }
        };
    }

    @Override // com.google.android.gms.measurement.internal.zzja
    public final /* bridge */ /* synthetic */ void zzr() {
        super.zzr();
    }

    @Override // com.google.android.gms.measurement.internal.zzja
    public final /* bridge */ /* synthetic */ void zzs() {
        super.zzs();
    }

    @Override // com.google.android.gms.measurement.internal.zzja
    public final /* bridge */ /* synthetic */ void zzt() {
        super.zzt();
    }

    final void zza(zzai zzaiVar) {
        this.zzc = zzaiVar;
    }

    public final void zzh(String str) {
        this.zzb = str;
    }

    public final boolean zzv() {
        Boolean zze = zze("google_analytics_adid_collection_enabled");
        return zze == null || zze.booleanValue();
    }

    final boolean zzi(String str) {
        return zzf(str, zzbh.zzam);
    }

    public final boolean zza(zzfz<Boolean> zzfzVar) {
        return zzf(null, zzfzVar);
    }

    public final boolean zze(String str, zzfz<Boolean> zzfzVar) {
        return zzf(str, zzfzVar);
    }

    public final boolean zzf(String str, zzfz<Boolean> zzfzVar) {
        if (TextUtils.isEmpty(str)) {
            return zzfzVar.zza(null).booleanValue();
        }
        String zza = this.zzc.zza(str, zzfzVar.zza());
        if (TextUtils.isEmpty(zza)) {
            return zzfzVar.zza(null).booleanValue();
        }
        return zzfzVar.zza(Boolean.valueOf("1".equals(zza))).booleanValue();
    }

    public final boolean zzj(String str) {
        return "1".equals(this.zzc.zza(str, "gaia_collection_enabled"));
    }

    public final boolean zzw() {
        Boolean zze = zze("google_analytics_automatic_screen_reporting_enabled");
        return zze == null || zze.booleanValue();
    }

    public final boolean zzx() {
        Boolean zze = zze("firebase_analytics_collection_deactivated");
        return zze != null && zze.booleanValue();
    }

    public final boolean zzk(String str) {
        return "1".equals(this.zzc.zza(str, "measurement.event_sampling_enabled"));
    }

    final boolean zzy() {
        if (this.zza == null) {
            Boolean zze = zze("app_measurement_lite");
            this.zza = zze;
            if (zze == null) {
                this.zza = false;
            }
        }
        return this.zza.booleanValue() || !this.zzu.zzag();
    }

    @EnsuresNonNull({"this.isMainProcess"})
    public final boolean zzz() {
        if (this.zzd == null) {
            synchronized (this) {
                if (this.zzd == null) {
                    ApplicationInfo applicationInfo = zza().getApplicationInfo();
                    String myProcessName = ProcessUtils.getMyProcessName();
                    if (applicationInfo != null) {
                        String str = applicationInfo.processName;
                        this.zzd = Boolean.valueOf(str != null && str.equals(myProcessName));
                    }
                    if (this.zzd == null) {
                        this.zzd = Boolean.TRUE;
                        zzj().zzg().zza("My process not in the list of running processes");
                    }
                }
            }
        }
        return this.zzd.booleanValue();
    }
}
