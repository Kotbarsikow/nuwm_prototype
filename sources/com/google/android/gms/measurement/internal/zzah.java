package com.google.android.gms.measurement.internal;

import com.google.android.gms.measurement.internal.zzje;
import java.util.EnumMap;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
/* loaded from: classes.dex */
final class zzah {
    private final EnumMap<zzje.zza, zzak> zza;

    public final zzak zza(zzje.zza zzaVar) {
        zzak zzakVar = this.zza.get(zzaVar);
        return zzakVar == null ? zzak.UNSET : zzakVar;
    }

    public static zzah zza(String str) {
        EnumMap enumMap = new EnumMap(zzje.zza.class);
        if (str.length() >= zzje.zza.values().length) {
            int i = 0;
            if (str.charAt(0) == '1') {
                zzje.zza[] values = zzje.zza.values();
                int length = values.length;
                int i2 = 1;
                while (i < length) {
                    enumMap.put((EnumMap) values[i], (zzje.zza) zzak.zza(str.charAt(i2)));
                    i++;
                    i2++;
                }
                return new zzah(enumMap);
            }
        }
        return new zzah();
    }

    public final String toString() {
        char c;
        StringBuilder sb = new StringBuilder("1");
        for (zzje.zza zzaVar : zzje.zza.values()) {
            zzak zzakVar = this.zza.get(zzaVar);
            if (zzakVar == null) {
                zzakVar = zzak.UNSET;
            }
            c = zzakVar.zzl;
            sb.append(c);
        }
        return sb.toString();
    }

    zzah() {
        this.zza = new EnumMap<>(zzje.zza.class);
    }

    private zzah(EnumMap<zzje.zza, zzak> enumMap) {
        EnumMap<zzje.zza, zzak> enumMap2 = new EnumMap<>((Class<zzje.zza>) zzje.zza.class);
        this.zza = enumMap2;
        enumMap2.putAll(enumMap);
    }

    public final void zza(zzje.zza zzaVar, int i) {
        zzak zzakVar = zzak.UNSET;
        if (i == -30) {
            zzakVar = zzak.TCF;
        } else {
            if (i != -20) {
                if (i == -10) {
                    zzakVar = zzak.MANIFEST;
                } else if (i != 0) {
                    if (i == 30) {
                        zzakVar = zzak.INITIALIZATION;
                    }
                }
            }
            zzakVar = zzak.API;
        }
        this.zza.put((EnumMap<zzje.zza, zzak>) zzaVar, (zzje.zza) zzakVar);
    }

    public final void zza(zzje.zza zzaVar, zzak zzakVar) {
        this.zza.put((EnumMap<zzje.zza, zzak>) zzaVar, (zzje.zza) zzakVar);
    }
}
