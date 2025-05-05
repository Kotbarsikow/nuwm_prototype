package com.google.android.gms.measurement.internal;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import com.github.mikephil.charting.utils.Utils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzov;
import com.google.firebase.messaging.Constants;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import kotlinx.coroutines.DebugKt;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes.dex */
public class zzhy implements zzjc {
    private static volatile zzhy zzb;
    final long zza;
    private Boolean zzaa;
    private long zzab;
    private volatile Boolean zzac;
    private Boolean zzad;
    private Boolean zzae;
    private volatile boolean zzaf;
    private int zzag;
    private int zzah;
    private final Context zzc;
    private final String zzd;
    private final String zze;
    private final String zzf;
    private final boolean zzg;
    private final zzab zzh;
    private final zzag zzi;
    private final zzha zzj;
    private final zzgo zzk;
    private final zzhv zzl;
    private final zznb zzm;
    private final zzos zzn;
    private final zzgh zzo;
    private final Clock zzp;
    private final zzlj zzq;
    private final zzjq zzr;
    private final zzb zzs;
    private final zzle zzt;
    private final String zzu;
    private zzgf zzv;
    private zzls zzw;
    private zzaz zzx;
    private zzgg zzy;
    private boolean zzz = false;
    private AtomicInteger zzai = new AtomicInteger(0);

    public final int zzc() {
        zzl().zzt();
        if (this.zzi.zzx()) {
            return 1;
        }
        Boolean bool = this.zzae;
        if (bool != null && bool.booleanValue()) {
            return 2;
        }
        if (!zzad()) {
            return 8;
        }
        Boolean zzv = zzn().zzv();
        if (zzv != null) {
            return zzv.booleanValue() ? 0 : 3;
        }
        Boolean zze = this.zzi.zze("firebase_analytics_collection_enabled");
        if (zze != null) {
            return zze.booleanValue() ? 0 : 4;
        }
        Boolean bool2 = this.zzad;
        return bool2 != null ? bool2.booleanValue() ? 0 : 5 : (this.zzac == null || this.zzac.booleanValue()) ? 0 : 7;
    }

    @Override // com.google.android.gms.measurement.internal.zzjc
    @Pure
    public final Context zza() {
        return this.zzc;
    }

    @Override // com.google.android.gms.measurement.internal.zzjc
    @Pure
    public final Clock zzb() {
        return this.zzp;
    }

    @Pure
    public final zzb zze() {
        zzb zzbVar = this.zzs;
        if (zzbVar != null) {
            return zzbVar;
        }
        throw new IllegalStateException("Component not created");
    }

    @Override // com.google.android.gms.measurement.internal.zzjc
    @Pure
    public final zzab zzd() {
        return this.zzh;
    }

    @Pure
    public final zzag zzf() {
        return this.zzi;
    }

    @Pure
    public final zzaz zzg() {
        zza((zzjd) this.zzx);
        return this.zzx;
    }

    @Pure
    public final zzgg zzh() {
        zza((zzh) this.zzy);
        return this.zzy;
    }

    @Pure
    public final zzgf zzi() {
        zza((zzh) this.zzv);
        return this.zzv;
    }

    @Pure
    public final zzgh zzk() {
        return this.zzo;
    }

    @Override // com.google.android.gms.measurement.internal.zzjc
    @Pure
    public final zzgo zzj() {
        zza((zzjd) this.zzk);
        return this.zzk;
    }

    public final zzgo zzm() {
        zzgo zzgoVar = this.zzk;
        if (zzgoVar == null || !zzgoVar.zzaf()) {
            return null;
        }
        return this.zzk;
    }

    @Pure
    public final zzha zzn() {
        zza((zzja) this.zzj);
        return this.zzj;
    }

    @Override // com.google.android.gms.measurement.internal.zzjc
    @Pure
    public final zzhv zzl() {
        zza((zzjd) this.zzl);
        return this.zzl;
    }

    @SideEffectFree
    final zzhv zzo() {
        return this.zzl;
    }

    public static zzhy zza(Context context, com.google.android.gms.internal.measurement.zzdw zzdwVar, Long l) {
        if (zzdwVar != null && (zzdwVar.zze == null || zzdwVar.zzf == null)) {
            zzdwVar = new com.google.android.gms.internal.measurement.zzdw(zzdwVar.zza, zzdwVar.zzb, zzdwVar.zzc, zzdwVar.zzd, null, null, zzdwVar.zzg, null);
        }
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(context.getApplicationContext());
        if (zzb == null) {
            synchronized (zzhy.class) {
                if (zzb == null) {
                    zzb = new zzhy(new zzjo(context, zzdwVar, l));
                }
            }
        } else if (zzdwVar != null && zzdwVar.zzg != null && zzdwVar.zzg.containsKey("dataCollectionDefaultEnabled")) {
            Preconditions.checkNotNull(zzb);
            zzb.zza(zzdwVar.zzg.getBoolean("dataCollectionDefaultEnabled"));
        }
        Preconditions.checkNotNull(zzb);
        return zzb;
    }

    @Pure
    public final zzjq zzp() {
        zza((zzh) this.zzr);
        return this.zzr;
    }

    @Pure
    private final zzle zzai() {
        zza((zzjd) this.zzt);
        return this.zzt;
    }

    @Pure
    public final zzlj zzq() {
        zza((zzh) this.zzq);
        return this.zzq;
    }

    @Pure
    public final zzls zzr() {
        zza((zzh) this.zzw);
        return this.zzw;
    }

    @Pure
    public final zznb zzs() {
        zza((zzh) this.zzm);
        return this.zzm;
    }

    @Pure
    public final zzos zzt() {
        zza((zzja) this.zzn);
        return this.zzn;
    }

    @Pure
    public final String zzu() {
        return this.zzd;
    }

    @Pure
    public final String zzv() {
        return this.zze;
    }

    @Pure
    public final String zzw() {
        return this.zzf;
    }

    @Pure
    public final String zzx() {
        return this.zzu;
    }

    static /* synthetic */ void zza(zzhy zzhyVar, zzjo zzjoVar) {
        zzhyVar.zzl().zzt();
        zzaz zzazVar = new zzaz(zzhyVar);
        zzazVar.zzad();
        zzhyVar.zzx = zzazVar;
        zzgg zzggVar = new zzgg(zzhyVar, zzjoVar.zzf);
        zzggVar.zzv();
        zzhyVar.zzy = zzggVar;
        zzgf zzgfVar = new zzgf(zzhyVar);
        zzgfVar.zzv();
        zzhyVar.zzv = zzgfVar;
        zzls zzlsVar = new zzls(zzhyVar);
        zzlsVar.zzv();
        zzhyVar.zzw = zzlsVar;
        zzhyVar.zzn.zzae();
        zzhyVar.zzj.zzae();
        zzhyVar.zzy.zzw();
        zzhyVar.zzj().zzo().zza("App measurement initialized, version", 106000L);
        zzhyVar.zzj().zzo().zza("To enable debug logging run: adb shell setprop log.tag.FA VERBOSE");
        String zzad = zzggVar.zzad();
        if (TextUtils.isEmpty(zzhyVar.zzd)) {
            if (zzhyVar.zzt().zzd(zzad, zzhyVar.zzi.zzu())) {
                zzhyVar.zzj().zzo().zza("Faster debug mode event logging enabled. To disable, run:\n  adb shell setprop debug.firebase.analytics.app .none.");
            } else {
                zzhyVar.zzj().zzo().zza("To enable faster debug mode event logging run:\n  adb shell setprop debug.firebase.analytics.app " + zzad);
            }
        }
        zzhyVar.zzj().zzc().zza("Debug-level message logging enabled");
        if (zzhyVar.zzag != zzhyVar.zzai.get()) {
            zzhyVar.zzj().zzg().zza("Not all components initialized", Integer.valueOf(zzhyVar.zzag), Integer.valueOf(zzhyVar.zzai.get()));
        }
        zzhyVar.zzz = true;
    }

    private zzhy(zzjo zzjoVar) {
        long currentTimeMillis;
        boolean z = false;
        Preconditions.checkNotNull(zzjoVar);
        zzab zzabVar = new zzab(zzjoVar.zza);
        this.zzh = zzabVar;
        zzga.zza = zzabVar;
        Context context = zzjoVar.zza;
        this.zzc = context;
        this.zzd = zzjoVar.zzb;
        this.zze = zzjoVar.zzc;
        this.zzf = zzjoVar.zzd;
        this.zzg = zzjoVar.zzh;
        this.zzac = zzjoVar.zze;
        this.zzu = zzjoVar.zzj;
        this.zzaf = true;
        com.google.android.gms.internal.measurement.zzdw zzdwVar = zzjoVar.zzg;
        if (zzdwVar != null && zzdwVar.zzg != null) {
            Object obj = zzdwVar.zzg.get("measurementEnabled");
            if (obj instanceof Boolean) {
                this.zzad = (Boolean) obj;
            }
            Object obj2 = zzdwVar.zzg.get("measurementDeactivated");
            if (obj2 instanceof Boolean) {
                this.zzae = (Boolean) obj2;
            }
        }
        com.google.android.gms.internal.measurement.zzhj.zzb(context);
        Clock defaultClock = DefaultClock.getInstance();
        this.zzp = defaultClock;
        if (zzjoVar.zzi != null) {
            currentTimeMillis = zzjoVar.zzi.longValue();
        } else {
            currentTimeMillis = defaultClock.currentTimeMillis();
        }
        this.zza = currentTimeMillis;
        this.zzi = new zzag(this);
        zzha zzhaVar = new zzha(this);
        zzhaVar.zzad();
        this.zzj = zzhaVar;
        zzgo zzgoVar = new zzgo(this);
        zzgoVar.zzad();
        this.zzk = zzgoVar;
        zzos zzosVar = new zzos(this);
        zzosVar.zzad();
        this.zzn = zzosVar;
        this.zzo = new zzgh(new zzjn(zzjoVar, this));
        this.zzs = new zzb(this);
        zzlj zzljVar = new zzlj(this);
        zzljVar.zzv();
        this.zzq = zzljVar;
        zzjq zzjqVar = new zzjq(this);
        zzjqVar.zzv();
        this.zzr = zzjqVar;
        zznb zznbVar = new zznb(this);
        zznbVar.zzv();
        this.zzm = zznbVar;
        zzle zzleVar = new zzle(this);
        zzleVar.zzad();
        this.zzt = zzleVar;
        zzhv zzhvVar = new zzhv(this);
        zzhvVar.zzad();
        this.zzl = zzhvVar;
        if (zzjoVar.zzg != null && zzjoVar.zzg.zzb != 0) {
            z = true;
        }
        boolean z2 = !z;
        if (context.getApplicationContext() instanceof Application) {
            zzp().zzb(z2);
        } else {
            zzj().zzu().zza("Application context is not an Application");
        }
        zzhvVar.zzb(new zzid(this, zzjoVar));
    }

    private static void zza(zzja zzjaVar) {
        if (zzjaVar == null) {
            throw new IllegalStateException("Component not created");
        }
    }

    private static void zza(zzh zzhVar) {
        if (zzhVar == null) {
            throw new IllegalStateException("Component not created");
        }
        if (zzhVar.zzy()) {
            return;
        }
        throw new IllegalStateException("Component not initialized: " + String.valueOf(zzhVar.getClass()));
    }

    private static void zza(zzjd zzjdVar) {
        if (zzjdVar == null) {
            throw new IllegalStateException("Component not created");
        }
        if (zzjdVar.zzaf()) {
            return;
        }
        throw new IllegalStateException("Component not initialized: " + String.valueOf(zzjdVar.getClass()));
    }

    final void zzy() {
        throw new IllegalStateException("Unexpected call on client side");
    }

    final void zzz() {
        this.zzai.incrementAndGet();
    }

    final /* synthetic */ void zza(String str, int i, Throwable th, byte[] bArr, Map map) {
        if ((i != 200 && i != 204 && i != 304) || th != null) {
            zzj().zzu().zza("Network Request for Deferred Deep Link failed. response, exception", Integer.valueOf(i), th);
            return;
        }
        zzn().zzo.zza(true);
        if (bArr == null || bArr.length == 0) {
            zzj().zzc().zza("Deferred Deep Link response empty.");
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(new String(bArr));
            String optString = jSONObject.optString("deeplink", "");
            if (TextUtils.isEmpty(optString)) {
                zzj().zzc().zza("Deferred Deep Link is empty.");
                return;
            }
            String optString2 = jSONObject.optString("gclid", "");
            String optString3 = jSONObject.optString("gbraid", "");
            String optString4 = jSONObject.optString("gad_source", "");
            double optDouble = jSONObject.optDouble("timestamp", Utils.DOUBLE_EPSILON);
            Bundle bundle = new Bundle();
            if (zzov.zza() && this.zzi.zza(zzbh.zzct)) {
                if (!zzt().zzi(optString)) {
                    zzj().zzu().zza("Deferred Deep Link validation failed. gclid, gbraid, deep link", optString2, optString3, optString);
                    return;
                }
                if (!TextUtils.isEmpty(optString3)) {
                    bundle.putString("gbraid", optString3);
                }
                if (!TextUtils.isEmpty(optString4)) {
                    bundle.putString("gad_source", optString4);
                }
            } else if (!zzt().zzi(optString)) {
                zzj().zzu().zza("Deferred Deep Link validation failed. gclid, deep link", optString2, optString);
                return;
            }
            if (zzov.zza()) {
                this.zzi.zza(zzbh.zzct);
            }
            bundle.putString("gclid", optString2);
            bundle.putString("_cis", "ddp");
            this.zzr.zzc(DebugKt.DEBUG_PROPERTY_VALUE_AUTO, Constants.ScionAnalytics.EVENT_FIREBASE_CAMPAIGN, bundle);
            zzos zzt = zzt();
            if (TextUtils.isEmpty(optString) || !zzt.zza(optString, optDouble)) {
                return;
            }
            zzt.zza().sendBroadcast(new Intent("android.google.analytics.action.DEEPLINK_ACTION"));
        } catch (JSONException e) {
            zzj().zzg().zza("Failed to parse the Deferred Deep Link response. exception", e);
        }
    }

    final void zzaa() {
        this.zzag++;
    }

    final void zza(boolean z) {
        this.zzac = Boolean.valueOf(z);
    }

    public final void zzb(boolean z) {
        zzl().zzt();
        this.zzaf = z;
    }

    /* JADX WARN: Code restructure failed: missing block: B:136:0x00dc, code lost:
    
        if (r1.zzi() != false) goto L179;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected final void zza(com.google.android.gms.internal.measurement.zzdw r12) {
        /*
            Method dump skipped, instructions count: 1090
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzhy.zza(com.google.android.gms.internal.measurement.zzdw):void");
    }

    public final boolean zzab() {
        return this.zzac != null && this.zzac.booleanValue();
    }

    public final boolean zzac() {
        return zzc() == 0;
    }

    public final boolean zzad() {
        zzl().zzt();
        return this.zzaf;
    }

    @Pure
    public final boolean zzae() {
        return TextUtils.isEmpty(this.zzd);
    }

    protected final boolean zzaf() {
        if (!this.zzz) {
            throw new IllegalStateException("AppMeasurement is not initialized");
        }
        zzl().zzt();
        Boolean bool = this.zzaa;
        if (bool == null || this.zzab == 0 || (bool != null && !bool.booleanValue() && Math.abs(this.zzp.elapsedRealtime() - this.zzab) > 1000)) {
            this.zzab = this.zzp.elapsedRealtime();
            boolean z = true;
            Boolean valueOf = Boolean.valueOf(zzt().zze("android.permission.INTERNET") && zzt().zze("android.permission.ACCESS_NETWORK_STATE") && (Wrappers.packageManager(this.zzc).isCallerInstantApp() || this.zzi.zzy() || (zzos.zza(this.zzc) && zzos.zza(this.zzc, false))));
            this.zzaa = valueOf;
            if (valueOf.booleanValue()) {
                if (!zzt().zza(zzh().zzae(), zzh().zzac()) && TextUtils.isEmpty(zzh().zzac())) {
                    z = false;
                }
                this.zzaa = Boolean.valueOf(z);
            }
        }
        return this.zzaa.booleanValue();
    }

    @Pure
    public final boolean zzag() {
        return this.zzg;
    }

    public final boolean zzah() {
        zzl().zzt();
        zza((zzjd) zzai());
        String zzad = zzh().zzad();
        if (!this.zzi.zzv()) {
            zzj().zzp().zza("ADID collection is disabled from Manifest. Skipping");
            return false;
        }
        Pair<String, Boolean> zza = zzn().zza(zzad);
        if (((Boolean) zza.second).booleanValue() || TextUtils.isEmpty((CharSequence) zza.first)) {
            zzj().zzp().zza("ADID unavailable to retrieve Deferred Deep Link. Skipping");
            return false;
        }
        if (!zzai().zzc()) {
            zzj().zzu().zza("Network is not available for Deferred Deep Link request. Skipping");
            return false;
        }
        StringBuilder sb = new StringBuilder();
        zzls zzr = zzr();
        zzr.zzt();
        zzr.zzu();
        if (!zzr.zzap() || zzr.zzq().zzg() >= 234200) {
            zzaj zzab = zzp().zzab();
            Bundle bundle = zzab != null ? zzab.zza : null;
            if (bundle == null) {
                int i = this.zzah;
                this.zzah = i + 1;
                boolean z = i < 10;
                zzj().zzc().zza("Failed to retrieve DMA consent from the service, " + (z ? "Retrying." : "Skipping.") + " retryCount", Integer.valueOf(this.zzah));
                return z;
            }
            zzje zza2 = zzje.zza(bundle, 100);
            sb.append("&gcs=");
            sb.append(zza2.zze());
            zzax zza3 = zzax.zza(bundle, 100);
            sb.append("&dma=");
            sb.append(zza3.zzd() == Boolean.FALSE ? 0 : 1);
            if (!TextUtils.isEmpty(zza3.zze())) {
                sb.append("&dma_cps=");
                sb.append(zza3.zze());
            }
            int i2 = zzax.zza(bundle) == Boolean.TRUE ? 0 : 1;
            sb.append("&npa=");
            sb.append(i2);
            zzj().zzp().zza("Consent query parameters to Bow", sb);
        }
        zzos zzt = zzt();
        zzh();
        URL zza4 = zzt.zza(106000L, zzad, (String) zza.first, zzn().zzp.zza() - 1, sb.toString());
        if (zza4 != null) {
            zzle zzai = zzai();
            zzia zziaVar = new zzld() { // from class: com.google.android.gms.measurement.internal.zzia
                public /* synthetic */ zzia() {
                }

                @Override // com.google.android.gms.measurement.internal.zzld
                public final void zza(String str, int i3, Throwable th, byte[] bArr, Map map) {
                    zzhy.this.zza(str, i3, th, bArr, map);
                }
            };
            zzai.zzt();
            zzai.zzac();
            Preconditions.checkNotNull(zza4);
            Preconditions.checkNotNull(zziaVar);
            zzai.zzl().zza(new zzlg(zzai, zzad, zza4, null, null, zziaVar));
        }
        return false;
    }
}
