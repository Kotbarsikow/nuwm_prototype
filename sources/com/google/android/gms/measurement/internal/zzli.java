package com.google.android.gms.measurement.internal;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzfy;
import com.google.android.gms.internal.measurement.zzov;
import com.google.android.gms.internal.measurement.zzpo;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import kotlinx.coroutines.DebugKt;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
/* loaded from: classes.dex */
final class zzli extends zznr {
    private static String zza(String str, String str2) {
        throw new SecurityException("This implementation should not be used.");
    }

    @Override // com.google.android.gms.measurement.internal.zznr
    protected final boolean zzc() {
        return false;
    }

    public zzli(zznv zznvVar) {
        super(zznvVar);
    }

    public final byte[] zza(zzbf zzbfVar, String str) {
        zzop zzopVar;
        zzfy.zzk.zza zzaVar;
        Bundle bundle;
        zzfy.zzj.zza zzaVar2;
        zzg zzgVar;
        byte[] bArr;
        long j;
        zzbb zza;
        zzt();
        this.zzu.zzy();
        Preconditions.checkNotNull(zzbfVar);
        Preconditions.checkNotEmpty(str);
        if (!zze().zze(str, zzbh.zzbl)) {
            zzj().zzc().zza("Generating ScionPayload disabled. packageName", str);
            return new byte[0];
        }
        if (!"_iap".equals(zzbfVar.zza) && !"_iapx".equals(zzbfVar.zza)) {
            zzj().zzc().zza("Generating a payload for this event is not available. package_name, event_name", str, zzbfVar.zza);
            return null;
        }
        zzfy.zzj.zza zzb = zzfy.zzj.zzb();
        zzh().zzp();
        try {
            zzg zze = zzh().zze(str);
            if (zze == null) {
                zzj().zzc().zza("Log and bundle not available. package_name", str);
                return new byte[0];
            }
            if (!zze.zzar()) {
                zzj().zzc().zza("Log and bundle disabled. package_name", str);
                return new byte[0];
            }
            zzfy.zzk.zza zzp = zzfy.zzk.zzw().zzh(1).zzp("android");
            if (!TextUtils.isEmpty(zze.zzac())) {
                zzp.zzb(zze.zzac());
            }
            if (!TextUtils.isEmpty(zze.zzae())) {
                zzp.zzd((String) Preconditions.checkNotNull(zze.zzae()));
            }
            if (!TextUtils.isEmpty(zze.zzaf())) {
                zzp.zze((String) Preconditions.checkNotNull(zze.zzaf()));
            }
            if (zze.zze() != -2147483648L) {
                zzp.zze((int) zze.zze());
            }
            zzp.zzf(zze.zzq()).zzd(zze.zzo());
            String zzah = zze.zzah();
            String zzaa = zze.zzaa();
            if (!TextUtils.isEmpty(zzah)) {
                zzp.zzm(zzah);
            } else if (!TextUtils.isEmpty(zzaa)) {
                zzp.zza(zzaa);
            }
            zzp.zzj(zze.zzw());
            zzje zzb2 = this.zzg.zzb(str);
            zzp.zzc(zze.zzn());
            if (this.zzu.zzac() && zze().zzj(zzp.zzt()) && zzb2.zzg() && !TextUtils.isEmpty(null)) {
                zzp.zzj((String) null);
            }
            zzp.zzg(zzb2.zze());
            if (zzb2.zzg() && zze.zzaq()) {
                Pair<String, Boolean> zza2 = zzn().zza(zze.zzac(), zzb2);
                if (zze.zzaq() && zza2 != null && !TextUtils.isEmpty((CharSequence) zza2.first)) {
                    zzp.zzq(zza((String) zza2.first, Long.toString(zzbfVar.zzd)));
                    if (zza2.second != null) {
                        zzp.zzc(((Boolean) zza2.second).booleanValue());
                    }
                }
            }
            zzf().zzac();
            zzfy.zzk.zza zzi = zzp.zzi(Build.MODEL);
            zzf().zzac();
            zzi.zzo(Build.VERSION.RELEASE).zzj((int) zzf().zzc()).zzs(zzf().zzg());
            if (zzb2.zzh() && zze.zzad() != null) {
                zzp.zzc(zza((String) Preconditions.checkNotNull(zze.zzad()), Long.toString(zzbfVar.zzd)));
            }
            if (!TextUtils.isEmpty(zze.zzag())) {
                zzp.zzl((String) Preconditions.checkNotNull(zze.zzag()));
            }
            String zzac = zze.zzac();
            List<zzop> zzl = zzh().zzl(zzac);
            Iterator<zzop> it = zzl.iterator();
            while (true) {
                if (!it.hasNext()) {
                    zzopVar = null;
                    break;
                }
                zzopVar = it.next();
                if ("_lte".equals(zzopVar.zzc)) {
                    break;
                }
            }
            if (zzopVar == null || zzopVar.zze == null) {
                zzop zzopVar2 = new zzop(zzac, DebugKt.DEBUG_PROPERTY_VALUE_AUTO, "_lte", zzb().currentTimeMillis(), 0L);
                zzl.add(zzopVar2);
                zzh().zza(zzopVar2);
            }
            zzfy.zzo[] zzoVarArr = new zzfy.zzo[zzl.size()];
            for (int i = 0; i < zzl.size(); i++) {
                zzfy.zzo.zza zzb3 = zzfy.zzo.zze().zza(zzl.get(i).zzc).zzb(zzl.get(i).zzd);
                g_().zza(zzb3, zzl.get(i).zze);
                zzoVarArr[i] = (zzfy.zzo) ((com.google.android.gms.internal.measurement.zzjt) zzb3.zzai());
            }
            zzp.zze(Arrays.asList(zzoVarArr));
            this.zzg.zza(zze, zzp);
            if (zzov.zza() && zze().zza(zzbh.zzcu)) {
                this.zzg.zzb(zze, zzp);
            }
            zzgs zza3 = zzgs.zza(zzbfVar);
            zzq().zza(zza3.zzc, zzh().zzd(str));
            zzq().zza(zza3, zze().zzb(str));
            Bundle bundle2 = zza3.zzc;
            bundle2.putLong("_c", 1L);
            zzj().zzc().zza("Marking in-app purchase as real-time");
            bundle2.putLong("_r", 1L);
            bundle2.putString("_o", zzbfVar.zzc);
            if (zzq().zzd(zzp.zzt(), zze.zzam())) {
                zzq().zza(bundle2, "_dbg", (Object) 1L);
                zzq().zza(bundle2, "_r", (Object) 1L);
            }
            zzbb zzd = zzh().zzd(str, zzbfVar.zza);
            if (zzd == null) {
                zzaVar = zzp;
                bundle = bundle2;
                zzaVar2 = zzb;
                zzgVar = zze;
                bArr = null;
                zza = new zzbb(str, zzbfVar.zza, 0L, 0L, zzbfVar.zzd, 0L, null, null, null, null);
                j = 0;
            } else {
                zzaVar = zzp;
                bundle = bundle2;
                zzaVar2 = zzb;
                zzgVar = zze;
                bArr = null;
                j = zzd.zzf;
                zza = zzd.zza(zzbfVar.zzd);
            }
            zzh().zza(zza);
            zzbc zzbcVar = new zzbc(this.zzu, zzbfVar.zzc, str, zzbfVar.zza, zzbfVar.zzd, j, bundle);
            zzfy.zzf.zza zza4 = zzfy.zzf.zze().zzb(zzbcVar.zzd).zza(zzbcVar.zzb).zza(zzbcVar.zze);
            Iterator<String> it2 = zzbcVar.zzf.iterator();
            while (it2.hasNext()) {
                String next = it2.next();
                zzfy.zzh.zza zza5 = zzfy.zzh.zze().zza(next);
                Object zzc = zzbcVar.zzf.zzc(next);
                if (zzc != null) {
                    g_().zza(zza5, zzc);
                    zza4.zza(zza5);
                }
            }
            zzfy.zzk.zza zzaVar3 = zzaVar;
            zzaVar3.zza(zza4).zza(zzfy.zzl.zza().zza(zzfy.zzg.zza().zza(zza.zzc).zza(zzbfVar.zza)));
            zzaVar3.zza(zzg().zza(zzgVar.zzac(), Collections.emptyList(), zzaVar3.zzab(), Long.valueOf(zza4.zzc()), Long.valueOf(zza4.zzc())));
            if (zza4.zzg()) {
                zzaVar3.zzi(zza4.zzc()).zze(zza4.zzc());
            }
            long zzs = zzgVar.zzs();
            if (zzs != 0) {
                zzaVar3.zzg(zzs);
            }
            long zzu = zzgVar.zzu();
            if (zzu != 0) {
                zzaVar3.zzh(zzu);
            } else if (zzs != 0) {
                zzaVar3.zzh(zzs);
            }
            String zzal = zzgVar.zzal();
            if (zzpo.zza() && zze().zze(str, zzbh.zzbw) && zzal != null) {
                zzaVar3.zzr(zzal);
            }
            zzgVar.zzap();
            zzaVar3.zzf((int) zzgVar.zzt()).zzl(106000L).zzk(zzb().currentTimeMillis()).zzd(Boolean.TRUE.booleanValue());
            this.zzg.zza(zzaVar3.zzt(), zzaVar3);
            zzfy.zzj.zza zzaVar4 = zzaVar2;
            zzaVar4.zza(zzaVar3);
            zzg zzgVar2 = zzgVar;
            zzgVar2.zzr(zzaVar3.zzf());
            zzgVar2.zzp(zzaVar3.zze());
            zzh().zza(zzgVar2, false, false);
            zzh().zzw();
            try {
                return g_().zzb(((zzfy.zzj) ((com.google.android.gms.internal.measurement.zzjt) zzaVar4.zzai())).zzca());
            } catch (IOException e) {
                zzj().zzg().zza("Data loss. Failed to bundle and serialize. appId", zzgo.zza(str), e);
                return bArr;
            }
        } catch (SecurityException e2) {
            zzj().zzc().zza("Resettable device id encryption failed", e2.getMessage());
            return new byte[0];
        } catch (SecurityException e3) {
            zzj().zzc().zza("app instance id encryption failed", e3.getMessage());
            return new byte[0];
        } finally {
            zzh().zzu();
        }
    }
}
