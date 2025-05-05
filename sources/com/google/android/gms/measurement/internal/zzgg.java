package com.google.android.gms.measurement.internal;

import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.internal.measurement.zzpz;
import com.google.android.gms.measurement.internal.zzje;
import java.math.BigInteger;
import java.util.List;
import java.util.Locale;
import org.checkerframework.dataflow.qual.Pure;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes.dex */
public final class zzgg extends zzh {
    private String zza;
    private String zzb;
    private int zzc;
    private String zzd;
    private long zze;
    private long zzf;
    private List<String> zzg;
    private String zzh;
    private int zzi;
    private String zzj;
    private String zzk;
    private String zzl;
    private long zzm;
    private String zzn;

    final int zzaa() {
        zzu();
        return this.zzi;
    }

    @Override // com.google.android.gms.measurement.internal.zzh
    protected final boolean zzz() {
        return true;
    }

    final int zzab() {
        zzu();
        return this.zzc;
    }

    @Override // com.google.android.gms.measurement.internal.zzja, com.google.android.gms.measurement.internal.zzjc
    @Pure
    public final /* bridge */ /* synthetic */ Context zza() {
        return super.zza();
    }

    @Override // com.google.android.gms.measurement.internal.zzja, com.google.android.gms.measurement.internal.zzjc
    @Pure
    public final /* bridge */ /* synthetic */ Clock zzb() {
        return super.zzb();
    }

    @Override // com.google.android.gms.measurement.internal.zze
    public final /* bridge */ /* synthetic */ zzb zzc() {
        return super.zzc();
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0165  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0168  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final com.google.android.gms.measurement.internal.zzo zza(java.lang.String r49) {
        /*
            Method dump skipped, instructions count: 543
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzgg.zza(java.lang.String):com.google.android.gms.measurement.internal.zzo");
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

    @Override // com.google.android.gms.measurement.internal.zze
    public final /* bridge */ /* synthetic */ zzgg zzg() {
        return super.zzg();
    }

    @Override // com.google.android.gms.measurement.internal.zze
    public final /* bridge */ /* synthetic */ zzgf zzh() {
        return super.zzh();
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

    @Override // com.google.android.gms.measurement.internal.zze
    public final /* bridge */ /* synthetic */ zzjq zzm() {
        return super.zzm();
    }

    @Override // com.google.android.gms.measurement.internal.zze
    public final /* bridge */ /* synthetic */ zzlj zzn() {
        return super.zzn();
    }

    @Override // com.google.android.gms.measurement.internal.zze
    public final /* bridge */ /* synthetic */ zzls zzo() {
        return super.zzo();
    }

    @Override // com.google.android.gms.measurement.internal.zze
    public final /* bridge */ /* synthetic */ zznb zzp() {
        return super.zzp();
    }

    @Override // com.google.android.gms.measurement.internal.zzja
    @Pure
    public final /* bridge */ /* synthetic */ zzos zzq() {
        return super.zzq();
    }

    final String zzac() {
        zzu();
        return this.zzk;
    }

    final String zzad() {
        zzu();
        Preconditions.checkNotNull(this.zza);
        return this.zza;
    }

    private final String zzah() {
        if (zzpz.zza() && zze().zza(zzbh.zzbr)) {
            zzj().zzp().zza("Disabled IID for tests.");
            return null;
        }
        try {
            Class<?> loadClass = zza().getClassLoader().loadClass("com.google.firebase.analytics.FirebaseAnalytics");
            if (loadClass == null) {
                return null;
            }
            try {
                Object invoke = loadClass.getDeclaredMethod("getInstance", Context.class).invoke(null, zza());
                if (invoke == null) {
                    return null;
                }
                try {
                    return (String) loadClass.getDeclaredMethod("getFirebaseInstanceId", null).invoke(invoke, null);
                } catch (Exception unused) {
                    zzj().zzv().zza("Failed to retrieve Firebase Instance Id");
                    return null;
                }
            } catch (Exception unused2) {
                zzj().zzw().zza("Failed to obtain Firebase Analytics instance");
                return null;
            }
        } catch (ClassNotFoundException unused3) {
        }
    }

    final String zzae() {
        zzt();
        zzu();
        Preconditions.checkNotNull(this.zzj);
        return this.zzj;
    }

    final List<String> zzaf() {
        return this.zzg;
    }

    zzgg(zzhy zzhyVar, long j) {
        super(zzhyVar);
        this.zzm = 0L;
        this.zzn = null;
        this.zzf = j;
    }

    @Override // com.google.android.gms.measurement.internal.zze, com.google.android.gms.measurement.internal.zzja
    public final /* bridge */ /* synthetic */ void zzr() {
        super.zzr();
    }

    @Override // com.google.android.gms.measurement.internal.zze, com.google.android.gms.measurement.internal.zzja
    public final /* bridge */ /* synthetic */ void zzs() {
        super.zzs();
    }

    @Override // com.google.android.gms.measurement.internal.zze, com.google.android.gms.measurement.internal.zzja
    public final /* bridge */ /* synthetic */ void zzt() {
        super.zzt();
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x015c  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0163  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0186  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0190 A[Catch: IllegalStateException -> 0x01c6, TryCatch #3 {IllegalStateException -> 0x01c6, blocks: (B:18:0x016b, B:21:0x0188, B:23:0x0190, B:25:0x01a9, B:27:0x01bd, B:28:0x01c2, B:30:0x01c0), top: B:17:0x016b }] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x01a9 A[Catch: IllegalStateException -> 0x01c6, TryCatch #3 {IllegalStateException -> 0x01c6, blocks: (B:18:0x016b, B:21:0x0188, B:23:0x0190, B:25:0x01a9, B:27:0x01bd, B:28:0x01c2, B:30:0x01c0), top: B:17:0x016b }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x01e7  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x021c  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0227  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0187  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0114  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0122  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x013e  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x014c  */
    @Override // com.google.android.gms.measurement.internal.zzh
    @org.checkerframework.checker.nullness.qual.EnsuresNonNull({"appId", "appStore", "appName", "gmpAppId", "gaAppId"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected final void zzx() {
        /*
            Method dump skipped, instructions count: 576
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzgg.zzx():void");
    }

    final void zzag() {
        String format;
        zzt();
        if (!zzk().zzo().zza(zzje.zza.ANALYTICS_STORAGE)) {
            zzj().zzc().zza("Analytics Storage consent is not granted");
            format = null;
        } else {
            byte[] bArr = new byte[16];
            zzq().zzv().nextBytes(bArr);
            format = String.format(Locale.US, "%032x", new BigInteger(1, bArr));
        }
        zzj().zzc().zza(String.format("Resetting session stitching token to %s", format == null ? "null" : "not null"));
        this.zzl = format;
        this.zzm = zzb().currentTimeMillis();
    }

    final boolean zzb(String str) {
        String str2 = this.zzn;
        boolean z = (str2 == null || str2.equals(str)) ? false : true;
        this.zzn = str;
        return z;
    }
}
