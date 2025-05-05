package com.google.android.gms.measurement.internal;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes.dex */
public final class zzfz<V> {
    private static final Object zza = new Object();
    private final String zzb;
    private final zzfx<V> zzc;
    private final V zzd;
    private final Object zze;
    private volatile V zzf;
    private volatile V zzg;

    public final V zza(V v) {
        List<zzfz> list;
        synchronized (this.zze) {
        }
        if (v != null) {
            return v;
        }
        if (zzga.zza == null) {
            return this.zzd;
        }
        synchronized (zza) {
            if (zzab.zza()) {
                return this.zzg == null ? this.zzd : this.zzg;
            }
            try {
                list = zzbh.zzdm;
                for (zzfz zzfzVar : list) {
                    if (zzab.zza()) {
                        throw new IllegalStateException("Refreshing flag cache must be done on a worker thread.");
                    }
                    V v2 = null;
                    try {
                        zzfx<V> zzfxVar = zzfzVar.zzc;
                        if (zzfxVar != null) {
                            v2 = zzfxVar.zza();
                        }
                    } catch (IllegalStateException unused) {
                    }
                    synchronized (zza) {
                        zzfzVar.zzg = v2;
                    }
                }
            } catch (SecurityException unused2) {
            }
            zzfx<V> zzfxVar2 = this.zzc;
            if (zzfxVar2 == null) {
                return this.zzd;
            }
            try {
                return zzfxVar2.zza();
            } catch (IllegalStateException unused3) {
                return this.zzd;
            } catch (SecurityException unused4) {
                return this.zzd;
            }
        }
    }

    public final String zza() {
        return this.zzb;
    }

    private zzfz(String str, V v, V v2, zzfx<V> zzfxVar) {
        this.zze = new Object();
        this.zzf = null;
        this.zzg = null;
        this.zzb = str;
        this.zzd = v;
        this.zzc = zzfxVar;
    }
}
