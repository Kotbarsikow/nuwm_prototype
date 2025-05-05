package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.internal.measurement.zzfr;
import com.google.android.gms.internal.measurement.zzpu;
import java.util.HashMap;
import org.checkerframework.dataflow.qual.Pure;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
/* loaded from: classes.dex */
public final class zznu extends zzns {
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

    @Override // com.google.android.gms.measurement.internal.zzns
    public final /* bridge */ /* synthetic */ zzt zzg() {
        return super.zzg();
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

    @Override // com.google.android.gms.measurement.internal.zzns
    public final /* bridge */ /* synthetic */ zzal zzh() {
        return super.zzh();
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

    @Override // com.google.android.gms.measurement.internal.zzns
    public final /* bridge */ /* synthetic */ zzhl zzm() {
        return super.zzm();
    }

    @Override // com.google.android.gms.measurement.internal.zzja, com.google.android.gms.measurement.internal.zzjc
    @Pure
    public final /* bridge */ /* synthetic */ zzhv zzl() {
        return super.zzl();
    }

    @Override // com.google.android.gms.measurement.internal.zzns
    public final /* bridge */ /* synthetic */ zzmw zzn() {
        return super.zzn();
    }

    public final zznw zza(String str) {
        zzg zze;
        if (zzpu.zza() && zze().zza(zzbh.zzbx)) {
            zzq();
            if (zzos.zzf(str)) {
                zzj().zzp().zza("sgtm feature flag enabled.");
                zzg zze2 = zzh().zze(str);
                if (zze2 == null) {
                    return new zznw(zzb(str), zznt.GOOGLE_ANALYTICS);
                }
                String zzad = zze2.zzad();
                zzfr.zzd zzc = zzm().zzc(str);
                if (zzc == null || (zze = zzh().zze(str)) == null || ((!zzc.zzq() || zzc.zzh().zza() != 100) && !zzq().zzd(str, zze.zzam()) && (!zze().zza(zzbh.zzbz) ? TextUtils.isEmpty(zzad) || zzad.hashCode() % 100 >= zzc.zzh().zza() : TextUtils.isEmpty(zzad) || Math.abs(zzad.hashCode() % 100) >= zzc.zzh().zza()))) {
                    return new zznw(zzb(str), zznt.GOOGLE_ANALYTICS);
                }
                zznw zznwVar = null;
                if (zze2.zzat()) {
                    zzj().zzp().zza("sgtm upload enabled in manifest.");
                    zzfr.zzd zzc2 = zzm().zzc(zze2.zzac());
                    if (zzc2 != null && zzc2.zzq()) {
                        String zze3 = zzc2.zzh().zze();
                        if (!TextUtils.isEmpty(zze3)) {
                            String zzd = zzc2.zzh().zzd();
                            zzj().zzp().zza("sgtm configured with upload_url, server_info", zze3, TextUtils.isEmpty(zzd) ? "Y" : "N");
                            if (TextUtils.isEmpty(zzd)) {
                                zznwVar = new zznw(zze3, zznt.SGTM);
                            } else {
                                HashMap hashMap = new HashMap();
                                hashMap.put("x-sgtm-server-info", zzd);
                                if (!TextUtils.isEmpty(zze2.zzam())) {
                                    hashMap.put("x-gtm-server-preview", zze2.zzam());
                                }
                                zznwVar = new zznw(zze3, hashMap, zznt.SGTM);
                            }
                        }
                    }
                }
                if (zznwVar != null) {
                    return zznwVar;
                }
            }
        }
        return new zznw(zzb(str), zznt.GOOGLE_ANALYTICS);
    }

    @Override // com.google.android.gms.measurement.internal.zzns
    public final /* bridge */ /* synthetic */ zznu zzo() {
        return super.zzo();
    }

    @Override // com.google.android.gms.measurement.internal.zzns
    public final /* bridge */ /* synthetic */ zzoo g_() {
        return super.g_();
    }

    @Override // com.google.android.gms.measurement.internal.zzja
    @Pure
    public final /* bridge */ /* synthetic */ zzos zzq() {
        return super.zzq();
    }

    public final String zza(zzg zzgVar) {
        Uri.Builder builder = new Uri.Builder();
        String zzah = zzgVar.zzah();
        if (TextUtils.isEmpty(zzah)) {
            zzah = zzgVar.zzaa();
        }
        builder.scheme(zzbh.zze.zza(null)).encodedAuthority(zzbh.zzf.zza(null)).path("config/app/" + zzah).appendQueryParameter("platform", "android").appendQueryParameter("gmp_version", "106000").appendQueryParameter("runtime_version", "0");
        return builder.build().toString();
    }

    private final String zzb(String str) {
        String zzf = zzm().zzf(str);
        if (!TextUtils.isEmpty(zzf)) {
            Uri parse = Uri.parse(zzbh.zzq.zza(null));
            Uri.Builder buildUpon = parse.buildUpon();
            buildUpon.authority(zzf + "." + parse.getAuthority());
            return buildUpon.build().toString();
        }
        return zzbh.zzq.zza(null);
    }

    zznu(zznv zznvVar) {
        super(zznvVar);
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
}
