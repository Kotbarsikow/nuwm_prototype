package com.google.android.gms.measurement.internal;

import android.os.Binder;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.GoogleSignatureVerifier;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.UidVerifier;
import com.google.firebase.messaging.Constants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
/* loaded from: classes.dex */
public final class zzic extends zzge {
    private final zznv zza;
    private Boolean zzb;
    private String zzc;

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final zzaj zza(zzo zzoVar) {
        zzb(zzoVar, false);
        Preconditions.checkNotEmpty(zzoVar.zza);
        try {
            return (zzaj) this.zza.zzl().zzb(new zziv(this, zzoVar)).get(10000L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            this.zza.zzj().zzg().zza("Failed to get consent. appId", zzgo.zza(zzoVar.zza), e);
            return new zzaj(null);
        }
    }

    final zzbf zzb(zzbf zzbfVar, zzo zzoVar) {
        if (Constants.ScionAnalytics.EVENT_FIREBASE_CAMPAIGN.equals(zzbfVar.zza) && zzbfVar.zzb != null && zzbfVar.zzb.zza() != 0) {
            String zzd = zzbfVar.zzb.zzd("_cis");
            if ("referrer broadcast".equals(zzd) || "referrer API".equals(zzd)) {
                this.zza.zzj().zzo().zza("Event has been filtered ", zzbfVar.toString());
                return new zzbf("_cmpx", zzbfVar.zzb, zzbfVar.zzc, zzbfVar.zzd);
            }
        }
        return zzbfVar;
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final String zzb(zzo zzoVar) {
        zzb(zzoVar, false);
        return this.zza.zzb(zzoVar);
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final List<zzno> zza(zzo zzoVar, Bundle bundle) {
        zzb(zzoVar, false);
        Preconditions.checkNotNull(zzoVar.zza);
        try {
            return (List) this.zza.zzl().zza(new zziy(this, zzoVar, bundle)).get();
        } catch (InterruptedException | ExecutionException e) {
            this.zza.zzj().zzg().zza("Failed to get trigger URIs. appId", zzgo.zza(zzoVar.zza), e);
            return Collections.emptyList();
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final List<zzon> zza(zzo zzoVar, boolean z) {
        zzb(zzoVar, false);
        String str = zzoVar.zza;
        Preconditions.checkNotNull(str);
        try {
            List<zzop> list = (List) this.zza.zzl().zza(new zzjb(this, str)).get();
            ArrayList arrayList = new ArrayList(list.size());
            for (zzop zzopVar : list) {
                if (z || !zzos.zzg(zzopVar.zzc)) {
                    arrayList.add(new zzon(zzopVar));
                }
            }
            return arrayList;
        } catch (InterruptedException | ExecutionException e) {
            this.zza.zzj().zzg().zza("Failed to get user properties. appId", zzgo.zza(zzoVar.zza), e);
            return null;
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final List<zzae> zza(String str, String str2, zzo zzoVar) {
        zzb(zzoVar, false);
        String str3 = zzoVar.zza;
        Preconditions.checkNotNull(str3);
        try {
            return (List) this.zza.zzl().zza(new zzir(this, str3, str, str2)).get();
        } catch (InterruptedException | ExecutionException e) {
            this.zza.zzj().zzg().zza("Failed to get conditional user properties", e);
            return Collections.emptyList();
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final List<zzae> zza(String str, String str2, String str3) {
        zza(str, true);
        try {
            return (List) this.zza.zzl().zza(new zziq(this, str, str2, str3)).get();
        } catch (InterruptedException | ExecutionException e) {
            this.zza.zzj().zzg().zza("Failed to get conditional user properties as", e);
            return Collections.emptyList();
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final List<zzon> zza(String str, String str2, boolean z, zzo zzoVar) {
        zzb(zzoVar, false);
        String str3 = zzoVar.zza;
        Preconditions.checkNotNull(str3);
        try {
            List<zzop> list = (List) this.zza.zzl().zza(new zzip(this, str3, str, str2)).get();
            ArrayList arrayList = new ArrayList(list.size());
            for (zzop zzopVar : list) {
                if (z || !zzos.zzg(zzopVar.zzc)) {
                    arrayList.add(new zzon(zzopVar));
                }
            }
            return arrayList;
        } catch (InterruptedException | ExecutionException e) {
            this.zza.zzj().zzg().zza("Failed to query user properties. appId", zzgo.zza(zzoVar.zza), e);
            return Collections.emptyList();
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final List<zzon> zza(String str, String str2, String str3, boolean z) {
        zza(str, true);
        try {
            List<zzop> list = (List) this.zza.zzl().zza(new zzio(this, str, str2, str3)).get();
            ArrayList arrayList = new ArrayList(list.size());
            for (zzop zzopVar : list) {
                if (z || !zzos.zzg(zzopVar.zzc)) {
                    arrayList.add(new zzon(zzopVar));
                }
            }
            return arrayList;
        } catch (InterruptedException | ExecutionException e) {
            this.zza.zzj().zzg().zza("Failed to get user properties as. appId", zzgo.zza(str), e);
            return Collections.emptyList();
        }
    }

    public zzic(zznv zznvVar) {
        this(zznvVar, null);
    }

    private zzic(zznv zznvVar, String str) {
        Preconditions.checkNotNull(zznvVar);
        this.zza = zznvVar;
        this.zzc = null;
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zzc(zzo zzoVar) {
        zzb(zzoVar, false);
        zzb(new zzil(this, zzoVar));
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zzd(zzo zzoVar) {
        zzb(zzoVar, false);
        zzb(new zzii(this, zzoVar));
    }

    private final void zzb(zzo zzoVar, boolean z) {
        Preconditions.checkNotNull(zzoVar);
        Preconditions.checkNotEmpty(zzoVar.zza);
        zza(zzoVar.zza, false);
        this.zza.zzq().zza(zzoVar.zzb, zzoVar.zzp);
    }

    private final void zza(String str, boolean z) {
        boolean z2;
        if (TextUtils.isEmpty(str)) {
            this.zza.zzj().zzg().zza("Measurement Service called without app package");
            throw new SecurityException("Measurement Service called without app package");
        }
        if (z) {
            try {
                if (this.zzb == null) {
                    if (!"com.google.android.gms".equals(this.zzc) && !UidVerifier.isGooglePlayServicesUid(this.zza.zza(), Binder.getCallingUid()) && !GoogleSignatureVerifier.getInstance(this.zza.zza()).isUidGoogleSigned(Binder.getCallingUid())) {
                        z2 = false;
                        this.zzb = Boolean.valueOf(z2);
                    }
                    z2 = true;
                    this.zzb = Boolean.valueOf(z2);
                }
                if (this.zzb.booleanValue()) {
                    return;
                }
            } catch (SecurityException e) {
                this.zza.zzj().zzg().zza("Measurement Service called with invalid calling package. appId", zzgo.zza(str));
                throw e;
            }
        }
        if (this.zzc == null && GooglePlayServicesUtilLight.uidHasPackageName(this.zza.zza(), Binder.getCallingUid(), str)) {
            this.zzc = str;
        }
        if (str.equals(this.zzc)) {
        } else {
            throw new SecurityException(String.format("Unknown calling package name '%s'.", str));
        }
    }

    final void zzc(zzbf zzbfVar, zzo zzoVar) {
        boolean z;
        if (!this.zza.zzi().zzk(zzoVar.zza)) {
            zzd(zzbfVar, zzoVar);
            return;
        }
        this.zza.zzj().zzp().zza("EES config found for", zzoVar.zza);
        zzhl zzi = this.zza.zzi();
        String str = zzoVar.zza;
        com.google.android.gms.internal.measurement.zzb zzbVar = TextUtils.isEmpty(str) ? null : zzi.zza.get(str);
        if (zzbVar == null) {
            this.zza.zzj().zzp().zza("EES not loaded for", zzoVar.zza);
            zzd(zzbfVar, zzoVar);
            return;
        }
        try {
            Map<String, Object> zza = this.zza.zzp().zza(zzbfVar.zzb.zzb(), true);
            String zza2 = zzji.zza(zzbfVar.zza);
            if (zza2 == null) {
                zza2 = zzbfVar.zza;
            }
            z = zzbVar.zza(new com.google.android.gms.internal.measurement.zzad(zza2, zzbfVar.zzd, zza));
        } catch (com.google.android.gms.internal.measurement.zzc unused) {
            this.zza.zzj().zzg().zza("EES error. appId, eventName", zzoVar.zzb, zzbfVar.zza);
            z = false;
        }
        if (!z) {
            this.zza.zzj().zzp().zza("EES was not applied to event", zzbfVar.zza);
            zzd(zzbfVar, zzoVar);
            return;
        }
        if (zzbVar.zzd()) {
            this.zza.zzj().zzp().zza("EES edited event", zzbfVar.zza);
            zzd(this.zza.zzp().zza(zzbVar.zza().zzb()), zzoVar);
        } else {
            zzd(zzbfVar, zzoVar);
        }
        if (zzbVar.zzc()) {
            for (com.google.android.gms.internal.measurement.zzad zzadVar : zzbVar.zza().zzc()) {
                this.zza.zzj().zzp().zza("EES logging created event", zzadVar.zzb());
                zzd(this.zza.zzp().zza(zzadVar), zzoVar);
            }
        }
    }

    final /* synthetic */ void zza(Bundle bundle, String str) {
        boolean zza = this.zza.zze().zza(zzbh.zzde);
        boolean zza2 = this.zza.zze().zza(zzbh.zzdg);
        if (bundle.isEmpty() && zza && zza2) {
            this.zza.zzf().zzp(str);
            return;
        }
        this.zza.zzf().zzb(str, bundle);
        if (zza2 && this.zza.zzf().zzt(str)) {
            this.zza.zzf().zza(str, bundle);
        }
    }

    final /* synthetic */ void zzb(Bundle bundle, String str) {
        if (bundle.isEmpty()) {
            this.zza.zzf().zzp(str);
        } else {
            this.zza.zzf().zzb(str, bundle);
            this.zza.zzf().zza(str, bundle);
        }
    }

    final /* synthetic */ void zzj(zzo zzoVar) {
        this.zza.zzr();
        this.zza.zzf(zzoVar);
    }

    final /* synthetic */ void zzk(zzo zzoVar) {
        this.zza.zzr();
        this.zza.zzg(zzoVar);
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zza(zzbf zzbfVar, zzo zzoVar) {
        Preconditions.checkNotNull(zzbfVar);
        zzb(zzoVar, false);
        zzb(new zziu(this, zzbfVar, zzoVar));
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zza(zzbf zzbfVar, String str, String str2) {
        Preconditions.checkNotNull(zzbfVar);
        Preconditions.checkNotEmpty(str);
        zza(str, true);
        zzb(new zzix(this, zzbfVar, str));
    }

    private final void zzd(zzbf zzbfVar, zzo zzoVar) {
        this.zza.zzr();
        this.zza.zza(zzbfVar, zzoVar);
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zze(zzo zzoVar) {
        Preconditions.checkNotEmpty(zzoVar.zza);
        zza(zzoVar.zza, false);
        zzb(new zzit(this, zzoVar));
    }

    private final void zza(Runnable runnable) {
        Preconditions.checkNotNull(runnable);
        if (this.zza.zzl().zzg()) {
            runnable.run();
        } else {
            this.zza.zzl().zzc(runnable);
        }
    }

    private final void zzb(Runnable runnable) {
        Preconditions.checkNotNull(runnable);
        if (this.zza.zzl().zzg()) {
            runnable.run();
        } else {
            this.zza.zzl().zzb(runnable);
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zza(zzae zzaeVar, zzo zzoVar) {
        Preconditions.checkNotNull(zzaeVar);
        Preconditions.checkNotNull(zzaeVar.zzc);
        zzb(zzoVar, false);
        zzae zzaeVar2 = new zzae(zzaeVar);
        zzaeVar2.zza = zzoVar.zza;
        zzb(new zzin(this, zzaeVar2, zzoVar));
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zza(zzae zzaeVar) {
        Preconditions.checkNotNull(zzaeVar);
        Preconditions.checkNotNull(zzaeVar.zzc);
        Preconditions.checkNotEmpty(zzaeVar.zza);
        zza(zzaeVar.zza, true);
        zzb(new zzim(this, new zzae(zzaeVar)));
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zzf(zzo zzoVar) {
        Preconditions.checkNotEmpty(zzoVar.zza);
        Preconditions.checkNotNull(zzoVar.zzt);
        zza(new zzis(this, zzoVar));
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zza(long j, String str, String str2, String str3) {
        zzb(new zzik(this, str2, str3, str, j));
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zza(Bundle bundle, zzo zzoVar) {
        zzb(zzoVar, false);
        String str = zzoVar.zza;
        Preconditions.checkNotNull(str);
        zzb(new Runnable() { // from class: com.google.android.gms.measurement.internal.zzig
            private /* synthetic */ Bundle zzb;
            private /* synthetic */ String zzc;

            public /* synthetic */ zzig(Bundle bundle2, String str2) {
                r2 = bundle2;
                r3 = str2;
            }

            @Override // java.lang.Runnable
            public final void run() {
                zzic.this.zza(r2, r3);
            }
        });
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zzb(Bundle bundle, zzo zzoVar) {
        if (com.google.android.gms.internal.measurement.zznr.zza() && this.zza.zze().zza(zzbh.zzdg)) {
            zzb(zzoVar, false);
            String str = zzoVar.zza;
            Preconditions.checkNotNull(str);
            zzb(new Runnable() { // from class: com.google.android.gms.measurement.internal.zzie
                private /* synthetic */ Bundle zzb;
                private /* synthetic */ String zzc;

                public /* synthetic */ zzie(Bundle bundle2, String str2) {
                    r2 = bundle2;
                    r3 = str2;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    zzic.this.zzb(r2, r3);
                }
            });
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zzg(zzo zzoVar) {
        Preconditions.checkNotEmpty(zzoVar.zza);
        Preconditions.checkNotNull(zzoVar.zzt);
        zza(new Runnable() { // from class: com.google.android.gms.measurement.internal.zzih
            private /* synthetic */ zzo zzb;

            public /* synthetic */ zzih(zzo zzoVar2) {
                r2 = zzoVar2;
            }

            @Override // java.lang.Runnable
            public final void run() {
                zzic.this.zzj(r2);
            }
        });
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zzh(zzo zzoVar) {
        zzb(zzoVar, false);
        zzb(new zzij(this, zzoVar));
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zzi(zzo zzoVar) {
        Preconditions.checkNotEmpty(zzoVar.zza);
        Preconditions.checkNotNull(zzoVar.zzt);
        zza(new Runnable() { // from class: com.google.android.gms.measurement.internal.zzif
            private /* synthetic */ zzo zzb;

            public /* synthetic */ zzif(zzo zzoVar2) {
                r2 = zzoVar2;
            }

            @Override // java.lang.Runnable
            public final void run() {
                zzic.this.zzk(r2);
            }
        });
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zza(zzon zzonVar, zzo zzoVar) {
        Preconditions.checkNotNull(zzonVar);
        zzb(zzoVar, false);
        zzb(new zziz(this, zzonVar, zzoVar));
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final byte[] zza(zzbf zzbfVar, String str) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzbfVar);
        zza(str, true);
        this.zza.zzj().zzc().zza("Log and bundle. event", this.zza.zzg().zza(zzbfVar.zza));
        long nanoTime = this.zza.zzb().nanoTime() / 1000000;
        try {
            byte[] bArr = (byte[]) this.zza.zzl().zzb(new zziw(this, zzbfVar, str)).get();
            if (bArr == null) {
                this.zza.zzj().zzg().zza("Log and bundle returned null. appId", zzgo.zza(str));
                bArr = new byte[0];
            }
            this.zza.zzj().zzc().zza("Log and bundle processed. event, size, time_ms", this.zza.zzg().zza(zzbfVar.zza), Integer.valueOf(bArr.length), Long.valueOf((this.zza.zzb().nanoTime() / 1000000) - nanoTime));
            return bArr;
        } catch (InterruptedException | ExecutionException e) {
            this.zza.zzj().zzg().zza("Failed to log and bundle. appId, event, error", zzgo.zza(str), this.zza.zzg().zza(zzbfVar.zza), e);
            return null;
        }
    }
}
