package com.google.android.gms.internal.measurement;

import j$.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
/* loaded from: classes.dex */
final class zzlq {
    private static final zzlq zza = new zzlq();
    private final ConcurrentMap<Class<?>, zzlu<?>> zzc = new ConcurrentHashMap();
    private final zzlt zzb = new zzkq();

    public static zzlq zza() {
        return zza;
    }

    public final <T> zzlu<T> zza(Class<T> cls) {
        zzjv.zza(cls, "messageType");
        zzlu<T> zzluVar = (zzlu) this.zzc.get(cls);
        if (zzluVar != null) {
            return zzluVar;
        }
        zzlu<T> zza2 = this.zzb.zza(cls);
        zzjv.zza(cls, "messageType");
        zzjv.zza(zza2, "schema");
        zzlu<T> zzluVar2 = (zzlu) this.zzc.putIfAbsent(cls, zza2);
        return zzluVar2 != null ? zzluVar2 : zza2;
    }

    public final <T> zzlu<T> zza(T t) {
        return zza((Class) t.getClass());
    }

    private zzlq() {
    }
}
