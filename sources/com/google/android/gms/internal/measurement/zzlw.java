package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
/* loaded from: classes.dex */
final class zzlw {
    private static final zzmk<?, ?> zza = new zzmm();

    static int zza(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzjc.zzb(i, true);
    }

    static int zza(List<?> list) {
        return list.size();
    }

    static int zza(int i, List<zzik> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzi = size * zzjc.zzi(i);
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzi += zzjc.zzb(list.get(i2));
        }
        return zzi;
    }

    static int zzb(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzb(list) + (size * zzjc.zzi(i));
    }

    static int zzb(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzjw) {
            zzjw zzjwVar = (zzjw) list;
            i = 0;
            while (i2 < size) {
                i += zzjc.zzd(zzjwVar.zzb(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzjc.zzd(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzc(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzjc.zzf(i, 0);
    }

    static int zzc(List<?> list) {
        return list.size() << 2;
    }

    static int zzd(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzjc.zzc(i, 0L);
    }

    static int zzd(List<?> list) {
        return list.size() << 3;
    }

    static int zza(int i, List<zzlc> list, zzlu<?> zzluVar) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i2 += zzjc.zzb(i, list.get(i3), zzluVar);
        }
        return i2;
    }

    static int zze(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zze(list) + (size * zzjc.zzi(i));
    }

    static int zze(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzjw) {
            zzjw zzjwVar = (zzjw) list;
            i = 0;
            while (i2 < size) {
                i += zzjc.zzf(zzjwVar.zzb(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzjc.zzf(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzf(int i, List<Long> list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return zzf(list) + (list.size() * zzjc.zzi(i));
    }

    static int zzf(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzkn) {
            zzkn zzknVar = (zzkn) list;
            i = 0;
            while (i2 < size) {
                i += zzjc.zzd(zzknVar.zzb(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzjc.zzd(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    static int zza(int i, Object obj, zzlu<?> zzluVar) {
        if (obj instanceof zzkk) {
            return zzjc.zzb(i, (zzkk) obj);
        }
        return zzjc.zzc(i, (zzlc) obj, zzluVar);
    }

    static int zzb(int i, List<?> list, zzlu<?> zzluVar) {
        int zza2;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzi = zzjc.zzi(i) * size;
        for (int i2 = 0; i2 < size; i2++) {
            Object obj = list.get(i2);
            if (obj instanceof zzkk) {
                zza2 = zzjc.zza((zzkk) obj);
            } else {
                zza2 = zzjc.zza((zzlc) obj, zzluVar);
            }
            zzi += zza2;
        }
        return zzi;
    }

    static int zzg(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzg(list) + (size * zzjc.zzi(i));
    }

    static int zzg(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzjw) {
            zzjw zzjwVar = (zzjw) list;
            i = 0;
            while (i2 < size) {
                i += zzjc.zzh(zzjwVar.zzb(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzjc.zzh(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzh(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzh(list) + (size * zzjc.zzi(i));
    }

    static int zzh(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzkn) {
            zzkn zzknVar = (zzkn) list;
            i = 0;
            while (i2 < size) {
                i += zzjc.zzf(zzknVar.zzb(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzjc.zzf(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    static int zzb(int i, List<?> list) {
        int zzb;
        int zzb2;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        int zzi = zzjc.zzi(i) * size;
        if (list instanceof zzkj) {
            zzkj zzkjVar = (zzkj) list;
            while (i2 < size) {
                Object zza2 = zzkjVar.zza(i2);
                if (zza2 instanceof zzik) {
                    zzb2 = zzjc.zzb((zzik) zza2);
                } else {
                    zzb2 = zzjc.zzb((String) zza2);
                }
                zzi += zzb2;
                i2++;
            }
        } else {
            while (i2 < size) {
                Object obj = list.get(i2);
                if (obj instanceof zzik) {
                    zzb = zzjc.zzb((zzik) obj);
                } else {
                    zzb = zzjc.zzb((String) obj);
                }
                zzi += zzb;
                i2++;
            }
        }
        return zzi;
    }

    static int zzi(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzi(list) + (size * zzjc.zzi(i));
    }

    static int zzi(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzjw) {
            zzjw zzjwVar = (zzjw) list;
            i = 0;
            while (i2 < size) {
                i += zzjc.zzj(zzjwVar.zzb(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzjc.zzj(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzj(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzj(list) + (size * zzjc.zzi(i));
    }

    static int zzj(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzkn) {
            zzkn zzknVar = (zzkn) list;
            i = 0;
            while (i2 < size) {
                i += zzjc.zzg(zzknVar.zzb(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzjc.zzg(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    public static zzmk<?, ?> zza() {
        return zza;
    }

    static <UT, UB> UB zza(Object obj, int i, List<Integer> list, zzjx zzjxVar, UB ub, zzmk<UT, UB> zzmkVar) {
        if (zzjxVar == null) {
            return ub;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                Integer num = list.get(i3);
                int intValue = num.intValue();
                if (zzjxVar.zza(intValue)) {
                    if (i3 != i2) {
                        list.set(i2, num);
                    }
                    i2++;
                } else {
                    ub = (UB) zza(obj, i, intValue, ub, zzmkVar);
                }
            }
            if (i2 != size) {
                list.subList(i2, size).clear();
            }
        } else {
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                int intValue2 = it.next().intValue();
                if (!zzjxVar.zza(intValue2)) {
                    ub = (UB) zza(obj, i, intValue2, ub, zzmkVar);
                    it.remove();
                }
            }
        }
        return ub;
    }

    static <UT, UB> UB zza(Object obj, int i, int i2, UB ub, zzmk<UT, UB> zzmkVar) {
        if (ub == null) {
            ub = zzmkVar.zzc(obj);
        }
        zzmkVar.zzb(ub, i, i2);
        return ub;
    }

    static <T, FT extends zzjo<FT>> void zza(zzji<FT> zzjiVar, T t, T t2) {
        zzjm<FT> zza2 = zzjiVar.zza(t2);
        if (zza2.zza.isEmpty()) {
            return;
        }
        zzjiVar.zzb(t).zza(zza2);
    }

    static <T> void zza(zzkv zzkvVar, T t, T t2, long j) {
        zzml.zza(t, j, zzkvVar.zza(zzml.zze(t, j), zzml.zze(t2, j)));
    }

    static <T, UT, UB> void zza(zzmk<UT, UB> zzmkVar, T t, T t2) {
        zzmkVar.zzc(t, zzmkVar.zza(zzmkVar.zzd(t), zzmkVar.zzd(t2)));
    }

    public static void zza(Class<?> cls) {
        zzjt.class.isAssignableFrom(cls);
    }

    public static void zza(int i, List<Boolean> list, zznb zznbVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznbVar.zza(i, list, z);
    }

    public static void zza(int i, List<zzik> list, zznb zznbVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznbVar.zza(i, list);
    }

    public static void zzb(int i, List<Double> list, zznb zznbVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznbVar.zzb(i, list, z);
    }

    public static void zzc(int i, List<Integer> list, zznb zznbVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznbVar.zzc(i, list, z);
    }

    public static void zzd(int i, List<Integer> list, zznb zznbVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznbVar.zzd(i, list, z);
    }

    public static void zze(int i, List<Long> list, zznb zznbVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznbVar.zze(i, list, z);
    }

    public static void zzf(int i, List<Float> list, zznb zznbVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznbVar.zzf(i, list, z);
    }

    public static void zza(int i, List<?> list, zznb zznbVar, zzlu<?> zzluVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznbVar.zza(i, list, (zzlu) zzluVar);
    }

    public static void zzg(int i, List<Integer> list, zznb zznbVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznbVar.zzg(i, list, z);
    }

    public static void zzh(int i, List<Long> list, zznb zznbVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznbVar.zzh(i, list, z);
    }

    public static void zzb(int i, List<?> list, zznb zznbVar, zzlu<?> zzluVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznbVar.zzb(i, list, (zzlu) zzluVar);
    }

    public static void zzi(int i, List<Integer> list, zznb zznbVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznbVar.zzi(i, list, z);
    }

    public static void zzj(int i, List<Long> list, zznb zznbVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznbVar.zzj(i, list, z);
    }

    public static void zzk(int i, List<Integer> list, zznb zznbVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznbVar.zzk(i, list, z);
    }

    public static void zzl(int i, List<Long> list, zznb zznbVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznbVar.zzl(i, list, z);
    }

    public static void zzb(int i, List<String> list, zznb zznbVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznbVar.zzb(i, list);
    }

    public static void zzm(int i, List<Integer> list, zznb zznbVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznbVar.zzm(i, list, z);
    }

    public static void zzn(int i, List<Long> list, zznb zznbVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznbVar.zzn(i, list, z);
    }

    static boolean zza(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }
}
