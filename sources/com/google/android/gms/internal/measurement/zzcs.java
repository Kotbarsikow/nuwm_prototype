package com.google.android.gms.internal.measurement;

import android.content.SharedPreferences;
import com.google.common.collect.Sets;
import com.google.common.collect.UnmodifiableIterator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes.dex */
final class zzcs implements SharedPreferences.Editor {
    private boolean zza;
    private Set<String> zzb;
    private Map<String, Object> zzc;
    private final /* synthetic */ zzcp zzd;

    /* synthetic */ zzcs(zzcp zzcpVar, zzcr zzcrVar) {
        this(zzcpVar);
    }

    @Override // android.content.SharedPreferences.Editor
    public final SharedPreferences.Editor clear() {
        this.zza = true;
        return this;
    }

    @Override // android.content.SharedPreferences.Editor
    public final SharedPreferences.Editor putBoolean(String str, boolean z) {
        zza(str, Boolean.valueOf(z));
        return this;
    }

    @Override // android.content.SharedPreferences.Editor
    public final SharedPreferences.Editor putFloat(String str, float f) {
        zza(str, Float.valueOf(f));
        return this;
    }

    @Override // android.content.SharedPreferences.Editor
    public final SharedPreferences.Editor putInt(String str, int i) {
        zza(str, Integer.valueOf(i));
        return this;
    }

    @Override // android.content.SharedPreferences.Editor
    public final SharedPreferences.Editor putLong(String str, long j) {
        zza(str, Long.valueOf(j));
        return this;
    }

    @Override // android.content.SharedPreferences.Editor
    public final SharedPreferences.Editor putString(String str, String str2) {
        zza(str, str2);
        return this;
    }

    @Override // android.content.SharedPreferences.Editor
    public final SharedPreferences.Editor putStringSet(String str, Set<String> set) {
        zza(str, set);
        return this;
    }

    @Override // android.content.SharedPreferences.Editor
    public final SharedPreferences.Editor remove(String str) {
        this.zzb.add(str);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    zzcs(zzcp zzcpVar) {
        this.zzd = zzcpVar;
        this.zza = false;
        this.zzb = new HashSet();
        this.zzc = new HashMap();
    }

    @Override // android.content.SharedPreferences.Editor
    public final void apply() {
        commit();
    }

    private final void zza(String str, Object obj) {
        if (obj != null) {
            this.zzc.put(str, obj);
        } else {
            remove(str);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.content.SharedPreferences.Editor
    public final boolean commit() {
        Map map;
        Set<SharedPreferences.OnSharedPreferenceChangeListener> set;
        Map map2;
        Map map3;
        if (this.zza) {
            map3 = this.zzd.zza;
            map3.clear();
        }
        map = this.zzd.zza;
        map.keySet().removeAll(this.zzb);
        for (Map.Entry<String, Object> entry : this.zzc.entrySet()) {
            map2 = this.zzd.zza;
            map2.put(entry.getKey(), entry.getValue());
        }
        set = this.zzd.zzb;
        for (SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener : set) {
            UnmodifiableIterator it = Sets.union(this.zzb, this.zzc.keySet()).iterator();
            while (it.hasNext()) {
                onSharedPreferenceChangeListener.onSharedPreferenceChanged(this.zzd, (String) it.next());
            }
        }
        return (!this.zza && this.zzb.isEmpty() && this.zzc.isEmpty()) ? false : true;
    }
}
