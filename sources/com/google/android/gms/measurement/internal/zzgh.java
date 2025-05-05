package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes.dex */
public final class zzgh {
    private static final AtomicReference<String[]> zza = new AtomicReference<>();
    private static final AtomicReference<String[]> zzb = new AtomicReference<>();
    private static final AtomicReference<String[]> zzc = new AtomicReference<>();
    private final zzgk zzd;

    private final String zza(Object[] objArr) {
        if (objArr == null) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Object obj : objArr) {
            String zza2 = obj instanceof Bundle ? zza((Bundle) obj) : String.valueOf(obj);
            if (zza2 != null) {
                if (sb.length() != 1) {
                    sb.append(", ");
                }
                sb.append(zza2);
            }
        }
        sb.append("]");
        return sb.toString();
    }

    protected final String zza(Bundle bundle) {
        String valueOf;
        if (bundle == null) {
            return null;
        }
        if (!this.zzd.zza()) {
            return bundle.toString();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Bundle[{");
        for (String str : bundle.keySet()) {
            if (sb.length() != 8) {
                sb.append(", ");
            }
            sb.append(zzb(str));
            sb.append("=");
            Object obj = bundle.get(str);
            if (obj instanceof Bundle) {
                valueOf = zza(new Object[]{obj});
            } else if (obj instanceof Object[]) {
                valueOf = zza((Object[]) obj);
            } else if (obj instanceof ArrayList) {
                valueOf = zza(((ArrayList) obj).toArray());
            } else {
                valueOf = String.valueOf(obj);
            }
            sb.append(valueOf);
        }
        sb.append("}]");
        return sb.toString();
    }

    protected final String zza(zzbf zzbfVar) {
        String str = null;
        if (zzbfVar == null) {
            return null;
        }
        if (!this.zzd.zza()) {
            return zzbfVar.toString();
        }
        StringBuilder sb = new StringBuilder("origin=");
        sb.append(zzbfVar.zzc);
        sb.append(",name=");
        sb.append(zza(zzbfVar.zza));
        sb.append(",params=");
        zzbe zzbeVar = zzbfVar.zzb;
        if (zzbeVar != null) {
            if (!this.zzd.zza()) {
                str = zzbeVar.toString();
            } else {
                str = zza(zzbeVar.zzb());
            }
        }
        sb.append(str);
        return sb.toString();
    }

    protected final String zza(String str) {
        if (str == null) {
            return null;
        }
        return !this.zzd.zza() ? str : zza(str, zzji.zzc, zzji.zza, zza);
    }

    protected final String zzb(String str) {
        if (str == null) {
            return null;
        }
        return !this.zzd.zza() ? str : zza(str, zzjk.zzb, zzjk.zza, zzb);
    }

    protected final String zzc(String str) {
        if (str == null) {
            return null;
        }
        if (!this.zzd.zza()) {
            return str;
        }
        if (str.startsWith("_exp_")) {
            return "experiment_id(" + str + ")";
        }
        return zza(str, zzjj.zzb, zzjj.zza, zzc);
    }

    private static String zza(String str, String[] strArr, String[] strArr2, AtomicReference<String[]> atomicReference) {
        String str2;
        Preconditions.checkNotNull(strArr);
        Preconditions.checkNotNull(strArr2);
        Preconditions.checkNotNull(atomicReference);
        Preconditions.checkArgument(strArr.length == strArr2.length);
        for (int i = 0; i < strArr.length; i++) {
            if (Objects.equals(str, strArr[i])) {
                synchronized (atomicReference) {
                    String[] strArr3 = atomicReference.get();
                    if (strArr3 == null) {
                        strArr3 = new String[strArr2.length];
                        atomicReference.set(strArr3);
                    }
                    if (strArr3[i] == null) {
                        strArr3[i] = strArr2[i] + "(" + strArr[i] + ")";
                    }
                    str2 = strArr3[i];
                }
                return str2;
            }
        }
        return str;
    }

    public zzgh(zzgk zzgkVar) {
        this.zzd = zzgkVar;
    }
}
