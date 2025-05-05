package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
/* loaded from: classes.dex */
final class zzjf implements zznb {
    private final zzjc zza;

    public static zzjf zza(zzjc zzjcVar) {
        return zzjcVar.zza != null ? zzjcVar.zza : new zzjf(zzjcVar);
    }

    @Override // com.google.android.gms.internal.measurement.zznb
    public final int zza() {
        return 1;
    }

    private zzjf(zzjc zzjcVar) {
        zzjc zzjcVar2 = (zzjc) zzjv.zza(zzjcVar, "output");
        this.zza = zzjcVar2;
        zzjcVar2.zza = this;
    }

    @Override // com.google.android.gms.internal.measurement.zznb
    public final void zza(int i, boolean z) throws IOException {
        this.zza.zza(i, z);
    }

    @Override // com.google.android.gms.internal.measurement.zznb
    public final void zza(int i, List<Boolean> list, boolean z) throws IOException {
        int i2 = 0;
        if (!(list instanceof zzii)) {
            if (z) {
                this.zza.zzc(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < list.size(); i4++) {
                    i3 += zzjc.zza(list.get(i4).booleanValue());
                }
                this.zza.zzc(i3);
                while (i2 < list.size()) {
                    this.zza.zzb(list.get(i2).booleanValue());
                    i2++;
                }
                return;
            }
            while (i2 < list.size()) {
                this.zza.zza(i, list.get(i2).booleanValue());
                i2++;
            }
            return;
        }
        zzii zziiVar = (zzii) list;
        if (z) {
            this.zza.zzc(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < zziiVar.size(); i6++) {
                i5 += zzjc.zza(zziiVar.zzb(i6));
            }
            this.zza.zzc(i5);
            while (i2 < zziiVar.size()) {
                this.zza.zzb(zziiVar.zzb(i2));
                i2++;
            }
            return;
        }
        while (i2 < zziiVar.size()) {
            this.zza.zza(i, zziiVar.zzb(i2));
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zznb
    public final void zza(int i, zzik zzikVar) throws IOException {
        this.zza.zza(i, zzikVar);
    }

    @Override // com.google.android.gms.internal.measurement.zznb
    public final void zza(int i, List<zzik> list) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.zza.zza(i, list.get(i2));
        }
    }

    @Override // com.google.android.gms.internal.measurement.zznb
    public final void zza(int i, double d) throws IOException {
        this.zza.zzb(i, d);
    }

    @Override // com.google.android.gms.internal.measurement.zznb
    public final void zzb(int i, List<Double> list, boolean z) throws IOException {
        int i2 = 0;
        if (!(list instanceof zzje)) {
            if (z) {
                this.zza.zzc(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < list.size(); i4++) {
                    i3 += zzjc.zza(list.get(i4).doubleValue());
                }
                this.zza.zzc(i3);
                while (i2 < list.size()) {
                    this.zza.zzb(list.get(i2).doubleValue());
                    i2++;
                }
                return;
            }
            while (i2 < list.size()) {
                this.zza.zzb(i, list.get(i2).doubleValue());
                i2++;
            }
            return;
        }
        zzje zzjeVar = (zzje) list;
        if (z) {
            this.zza.zzc(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < zzjeVar.size(); i6++) {
                i5 += zzjc.zza(zzjeVar.zzb(i6));
            }
            this.zza.zzc(i5);
            while (i2 < zzjeVar.size()) {
                this.zza.zzb(zzjeVar.zzb(i2));
                i2++;
            }
            return;
        }
        while (i2 < zzjeVar.size()) {
            this.zza.zzb(i, zzjeVar.zzb(i2));
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zznb
    @Deprecated
    public final void zza(int i) throws IOException {
        this.zza.zzc(i, 4);
    }

    @Override // com.google.android.gms.internal.measurement.zznb
    public final void zza(int i, int i2) throws IOException {
        this.zza.zzb(i, i2);
    }

    @Override // com.google.android.gms.internal.measurement.zznb
    public final void zzc(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (!(list instanceof zzjw)) {
            if (z) {
                this.zza.zzc(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < list.size(); i4++) {
                    i3 += zzjc.zzd(list.get(i4).intValue());
                }
                this.zza.zzc(i3);
                while (i2 < list.size()) {
                    this.zza.zzb(list.get(i2).intValue());
                    i2++;
                }
                return;
            }
            while (i2 < list.size()) {
                this.zza.zzb(i, list.get(i2).intValue());
                i2++;
            }
            return;
        }
        zzjw zzjwVar = (zzjw) list;
        if (z) {
            this.zza.zzc(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < zzjwVar.size(); i6++) {
                i5 += zzjc.zzd(zzjwVar.zzb(i6));
            }
            this.zza.zzc(i5);
            while (i2 < zzjwVar.size()) {
                this.zza.zzb(zzjwVar.zzb(i2));
                i2++;
            }
            return;
        }
        while (i2 < zzjwVar.size()) {
            this.zza.zzb(i, zzjwVar.zzb(i2));
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zznb
    public final void zzb(int i, int i2) throws IOException {
        this.zza.zza(i, i2);
    }

    @Override // com.google.android.gms.internal.measurement.zznb
    public final void zzd(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (!(list instanceof zzjw)) {
            if (z) {
                this.zza.zzc(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < list.size(); i4++) {
                    i3 += zzjc.zze(list.get(i4).intValue());
                }
                this.zza.zzc(i3);
                while (i2 < list.size()) {
                    this.zza.zza(list.get(i2).intValue());
                    i2++;
                }
                return;
            }
            while (i2 < list.size()) {
                this.zza.zza(i, list.get(i2).intValue());
                i2++;
            }
            return;
        }
        zzjw zzjwVar = (zzjw) list;
        if (z) {
            this.zza.zzc(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < zzjwVar.size(); i6++) {
                i5 += zzjc.zze(zzjwVar.zzb(i6));
            }
            this.zza.zzc(i5);
            while (i2 < zzjwVar.size()) {
                this.zza.zza(zzjwVar.zzb(i2));
                i2++;
            }
            return;
        }
        while (i2 < zzjwVar.size()) {
            this.zza.zza(i, zzjwVar.zzb(i2));
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zznb
    public final void zza(int i, long j) throws IOException {
        this.zza.zza(i, j);
    }

    @Override // com.google.android.gms.internal.measurement.zznb
    public final void zze(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (!(list instanceof zzkn)) {
            if (z) {
                this.zza.zzc(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < list.size(); i4++) {
                    i3 += zzjc.zzc(list.get(i4).longValue());
                }
                this.zza.zzc(i3);
                while (i2 < list.size()) {
                    this.zza.zza(list.get(i2).longValue());
                    i2++;
                }
                return;
            }
            while (i2 < list.size()) {
                this.zza.zza(i, list.get(i2).longValue());
                i2++;
            }
            return;
        }
        zzkn zzknVar = (zzkn) list;
        if (z) {
            this.zza.zzc(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < zzknVar.size(); i6++) {
                i5 += zzjc.zzc(zzknVar.zzb(i6));
            }
            this.zza.zzc(i5);
            while (i2 < zzknVar.size()) {
                this.zza.zza(zzknVar.zzb(i2));
                i2++;
            }
            return;
        }
        while (i2 < zzknVar.size()) {
            this.zza.zza(i, zzknVar.zzb(i2));
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zznb
    public final void zza(int i, float f) throws IOException {
        this.zza.zzb(i, f);
    }

    @Override // com.google.android.gms.internal.measurement.zznb
    public final void zzf(int i, List<Float> list, boolean z) throws IOException {
        int i2 = 0;
        if (!(list instanceof zzjs)) {
            if (z) {
                this.zza.zzc(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < list.size(); i4++) {
                    i3 += zzjc.zza(list.get(i4).floatValue());
                }
                this.zza.zzc(i3);
                while (i2 < list.size()) {
                    this.zza.zzb(list.get(i2).floatValue());
                    i2++;
                }
                return;
            }
            while (i2 < list.size()) {
                this.zza.zzb(i, list.get(i2).floatValue());
                i2++;
            }
            return;
        }
        zzjs zzjsVar = (zzjs) list;
        if (z) {
            this.zza.zzc(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < zzjsVar.size(); i6++) {
                i5 += zzjc.zza(zzjsVar.zzb(i6));
            }
            this.zza.zzc(i5);
            while (i2 < zzjsVar.size()) {
                this.zza.zzb(zzjsVar.zzb(i2));
                i2++;
            }
            return;
        }
        while (i2 < zzjsVar.size()) {
            this.zza.zzb(i, zzjsVar.zzb(i2));
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zznb
    public final void zza(int i, Object obj, zzlu zzluVar) throws IOException {
        zzjc zzjcVar = this.zza;
        zzjcVar.zzc(i, 3);
        zzluVar.zza((zzlu) obj, (zznb) zzjcVar.zza);
        zzjcVar.zzc(i, 4);
    }

    @Override // com.google.android.gms.internal.measurement.zznb
    public final void zza(int i, List<?> list, zzlu zzluVar) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            zza(i, list.get(i2), zzluVar);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zznb
    public final void zzc(int i, int i2) throws IOException {
        this.zza.zzb(i, i2);
    }

    @Override // com.google.android.gms.internal.measurement.zznb
    public final void zzg(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (!(list instanceof zzjw)) {
            if (z) {
                this.zza.zzc(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < list.size(); i4++) {
                    i3 += zzjc.zzf(list.get(i4).intValue());
                }
                this.zza.zzc(i3);
                while (i2 < list.size()) {
                    this.zza.zzb(list.get(i2).intValue());
                    i2++;
                }
                return;
            }
            while (i2 < list.size()) {
                this.zza.zzb(i, list.get(i2).intValue());
                i2++;
            }
            return;
        }
        zzjw zzjwVar = (zzjw) list;
        if (z) {
            this.zza.zzc(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < zzjwVar.size(); i6++) {
                i5 += zzjc.zzf(zzjwVar.zzb(i6));
            }
            this.zza.zzc(i5);
            while (i2 < zzjwVar.size()) {
                this.zza.zzb(zzjwVar.zzb(i2));
                i2++;
            }
            return;
        }
        while (i2 < zzjwVar.size()) {
            this.zza.zzb(i, zzjwVar.zzb(i2));
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zznb
    public final void zzb(int i, long j) throws IOException {
        this.zza.zzb(i, j);
    }

    @Override // com.google.android.gms.internal.measurement.zznb
    public final void zzh(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (!(list instanceof zzkn)) {
            if (z) {
                this.zza.zzc(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < list.size(); i4++) {
                    i3 += zzjc.zzd(list.get(i4).longValue());
                }
                this.zza.zzc(i3);
                while (i2 < list.size()) {
                    this.zza.zzb(list.get(i2).longValue());
                    i2++;
                }
                return;
            }
            while (i2 < list.size()) {
                this.zza.zzb(i, list.get(i2).longValue());
                i2++;
            }
            return;
        }
        zzkn zzknVar = (zzkn) list;
        if (z) {
            this.zza.zzc(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < zzknVar.size(); i6++) {
                i5 += zzjc.zzd(zzknVar.zzb(i6));
            }
            this.zza.zzc(i5);
            while (i2 < zzknVar.size()) {
                this.zza.zzb(zzknVar.zzb(i2));
                i2++;
            }
            return;
        }
        while (i2 < zzknVar.size()) {
            this.zza.zzb(i, zzknVar.zzb(i2));
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zznb
    public final <K, V> void zza(int i, zzkt<K, V> zzktVar, Map<K, V> map) throws IOException {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            this.zza.zzc(i, 2);
            this.zza.zzc(zzku.zza(zzktVar, entry.getKey(), entry.getValue()));
            zzku.zza(this.zza, zzktVar, entry.getKey(), entry.getValue());
        }
    }

    @Override // com.google.android.gms.internal.measurement.zznb
    public final void zzb(int i, Object obj, zzlu zzluVar) throws IOException {
        this.zza.zza(i, (zzlc) obj, zzluVar);
    }

    @Override // com.google.android.gms.internal.measurement.zznb
    public final void zzb(int i, List<?> list, zzlu zzluVar) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzb(i, list.get(i2), zzluVar);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zznb
    public final void zza(int i, Object obj) throws IOException {
        if (obj instanceof zzik) {
            this.zza.zzb(i, (zzik) obj);
        } else {
            this.zza.zza(i, (zzlc) obj);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zznb
    public final void zzd(int i, int i2) throws IOException {
        this.zza.zza(i, i2);
    }

    @Override // com.google.android.gms.internal.measurement.zznb
    public final void zzi(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (!(list instanceof zzjw)) {
            if (z) {
                this.zza.zzc(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < list.size(); i4++) {
                    i3 += zzjc.zzg(list.get(i4).intValue());
                }
                this.zza.zzc(i3);
                while (i2 < list.size()) {
                    this.zza.zza(list.get(i2).intValue());
                    i2++;
                }
                return;
            }
            while (i2 < list.size()) {
                this.zza.zza(i, list.get(i2).intValue());
                i2++;
            }
            return;
        }
        zzjw zzjwVar = (zzjw) list;
        if (z) {
            this.zza.zzc(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < zzjwVar.size(); i6++) {
                i5 += zzjc.zzg(zzjwVar.zzb(i6));
            }
            this.zza.zzc(i5);
            while (i2 < zzjwVar.size()) {
                this.zza.zza(zzjwVar.zzb(i2));
                i2++;
            }
            return;
        }
        while (i2 < zzjwVar.size()) {
            this.zza.zza(i, zzjwVar.zzb(i2));
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zznb
    public final void zzc(int i, long j) throws IOException {
        this.zza.zza(i, j);
    }

    @Override // com.google.android.gms.internal.measurement.zznb
    public final void zzj(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (!(list instanceof zzkn)) {
            if (z) {
                this.zza.zzc(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < list.size(); i4++) {
                    i3 += zzjc.zze(list.get(i4).longValue());
                }
                this.zza.zzc(i3);
                while (i2 < list.size()) {
                    this.zza.zza(list.get(i2).longValue());
                    i2++;
                }
                return;
            }
            while (i2 < list.size()) {
                this.zza.zza(i, list.get(i2).longValue());
                i2++;
            }
            return;
        }
        zzkn zzknVar = (zzkn) list;
        if (z) {
            this.zza.zzc(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < zzknVar.size(); i6++) {
                i5 += zzjc.zze(zzknVar.zzb(i6));
            }
            this.zza.zzc(i5);
            while (i2 < zzknVar.size()) {
                this.zza.zza(zzknVar.zzb(i2));
                i2++;
            }
            return;
        }
        while (i2 < zzknVar.size()) {
            this.zza.zza(i, zzknVar.zzb(i2));
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zznb
    public final void zze(int i, int i2) throws IOException {
        this.zza.zzk(i, i2);
    }

    @Override // com.google.android.gms.internal.measurement.zznb
    public final void zzk(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (!(list instanceof zzjw)) {
            if (z) {
                this.zza.zzc(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < list.size(); i4++) {
                    i3 += zzjc.zzh(list.get(i4).intValue());
                }
                this.zza.zzc(i3);
                while (i2 < list.size()) {
                    this.zza.zzk(list.get(i2).intValue());
                    i2++;
                }
                return;
            }
            while (i2 < list.size()) {
                this.zza.zzk(i, list.get(i2).intValue());
                i2++;
            }
            return;
        }
        zzjw zzjwVar = (zzjw) list;
        if (z) {
            this.zza.zzc(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < zzjwVar.size(); i6++) {
                i5 += zzjc.zzh(zzjwVar.zzb(i6));
            }
            this.zza.zzc(i5);
            while (i2 < zzjwVar.size()) {
                this.zza.zzk(zzjwVar.zzb(i2));
                i2++;
            }
            return;
        }
        while (i2 < zzjwVar.size()) {
            this.zza.zzk(i, zzjwVar.zzb(i2));
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zznb
    public final void zzd(int i, long j) throws IOException {
        this.zza.zzh(i, j);
    }

    @Override // com.google.android.gms.internal.measurement.zznb
    public final void zzl(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (!(list instanceof zzkn)) {
            if (z) {
                this.zza.zzc(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < list.size(); i4++) {
                    i3 += zzjc.zzf(list.get(i4).longValue());
                }
                this.zza.zzc(i3);
                while (i2 < list.size()) {
                    this.zza.zzh(list.get(i2).longValue());
                    i2++;
                }
                return;
            }
            while (i2 < list.size()) {
                this.zza.zzh(i, list.get(i2).longValue());
                i2++;
            }
            return;
        }
        zzkn zzknVar = (zzkn) list;
        if (z) {
            this.zza.zzc(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < zzknVar.size(); i6++) {
                i5 += zzjc.zzf(zzknVar.zzb(i6));
            }
            this.zza.zzc(i5);
            while (i2 < zzknVar.size()) {
                this.zza.zzh(zzknVar.zzb(i2));
                i2++;
            }
            return;
        }
        while (i2 < zzknVar.size()) {
            this.zza.zzh(i, zzknVar.zzb(i2));
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zznb
    @Deprecated
    public final void zzb(int i) throws IOException {
        this.zza.zzc(i, 3);
    }

    @Override // com.google.android.gms.internal.measurement.zznb
    public final void zza(int i, String str) throws IOException {
        this.zza.zza(i, str);
    }

    @Override // com.google.android.gms.internal.measurement.zznb
    public final void zzb(int i, List<String> list) throws IOException {
        int i2 = 0;
        if (list instanceof zzkj) {
            zzkj zzkjVar = (zzkj) list;
            while (i2 < list.size()) {
                Object zza = zzkjVar.zza(i2);
                if (zza instanceof String) {
                    this.zza.zza(i, (String) zza);
                } else {
                    this.zza.zza(i, (zzik) zza);
                }
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zza(i, list.get(i2));
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zznb
    public final void zzf(int i, int i2) throws IOException {
        this.zza.zzd(i, i2);
    }

    @Override // com.google.android.gms.internal.measurement.zznb
    public final void zzm(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (!(list instanceof zzjw)) {
            if (z) {
                this.zza.zzc(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < list.size(); i4++) {
                    i3 += zzjc.zzj(list.get(i4).intValue());
                }
                this.zza.zzc(i3);
                while (i2 < list.size()) {
                    this.zza.zzc(list.get(i2).intValue());
                    i2++;
                }
                return;
            }
            while (i2 < list.size()) {
                this.zza.zzd(i, list.get(i2).intValue());
                i2++;
            }
            return;
        }
        zzjw zzjwVar = (zzjw) list;
        if (z) {
            this.zza.zzc(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < zzjwVar.size(); i6++) {
                i5 += zzjc.zzj(zzjwVar.zzb(i6));
            }
            this.zza.zzc(i5);
            while (i2 < zzjwVar.size()) {
                this.zza.zzc(zzjwVar.zzb(i2));
                i2++;
            }
            return;
        }
        while (i2 < zzjwVar.size()) {
            this.zza.zzd(i, zzjwVar.zzb(i2));
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zznb
    public final void zze(int i, long j) throws IOException {
        this.zza.zzb(i, j);
    }

    @Override // com.google.android.gms.internal.measurement.zznb
    public final void zzn(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (!(list instanceof zzkn)) {
            if (z) {
                this.zza.zzc(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < list.size(); i4++) {
                    i3 += zzjc.zzg(list.get(i4).longValue());
                }
                this.zza.zzc(i3);
                while (i2 < list.size()) {
                    this.zza.zzb(list.get(i2).longValue());
                    i2++;
                }
                return;
            }
            while (i2 < list.size()) {
                this.zza.zzb(i, list.get(i2).longValue());
                i2++;
            }
            return;
        }
        zzkn zzknVar = (zzkn) list;
        if (z) {
            this.zza.zzc(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < zzknVar.size(); i6++) {
                i5 += zzjc.zzg(zzknVar.zzb(i6));
            }
            this.zza.zzc(i5);
            while (i2 < zzknVar.size()) {
                this.zza.zzb(zzknVar.zzb(i2));
                i2++;
            }
            return;
        }
        while (i2 < zzknVar.size()) {
            this.zza.zzb(i, zzknVar.zzb(i2));
            i2++;
        }
    }
}
