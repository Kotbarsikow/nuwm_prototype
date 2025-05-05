package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.database.sqlite.SQLiteException;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzfo;
import com.google.android.gms.internal.measurement.zzfy;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
/* loaded from: classes.dex */
final class zzt extends zznr {
    private String zza;
    private Set<Integer> zzb;
    private Map<Integer, zzv> zzc;
    private Long zzd;
    private Long zze;

    private final zzv zza(Integer num) {
        if (this.zzc.containsKey(num)) {
            return this.zzc.get(num);
        }
        zzv zzvVar = new zzv(this, this.zza);
        this.zzc.put(num, zzvVar);
        return zzvVar;
    }

    @Override // com.google.android.gms.measurement.internal.zznr
    protected final boolean zzc() {
        return false;
    }

    final List<zzfy.zzd> zza(String str, List<zzfy.zzf> list, List<zzfy.zzo> list2, Long l, Long l2) {
        return zza(str, list, list2, l, l2, false);
    }

    /*  JADX ERROR: JadxRuntimeException in pass: ProcessVariables
        jadx.core.utils.exceptions.JadxRuntimeException: Method arg registers not loaded: com.google.android.gms.measurement.internal.zzv.<init>(com.google.android.gms.measurement.internal.zzt, java.lang.String, com.google.android.gms.internal.measurement.zzfy$zzm, java.util.BitSet, java.util.BitSet, java.util.Map, java.util.Map, com.google.android.gms.measurement.internal.zzac):void, class status: GENERATED_AND_UNLOADED
        	at jadx.core.dex.nodes.MethodNode.getArgRegs(MethodNode.java:290)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables$1.isArgUnused(ProcessVariables.java:146)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables$1.lambda$isVarUnused$0(ProcessVariables.java:131)
        	at jadx.core.utils.ListUtils.allMatch(ListUtils.java:193)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables$1.isVarUnused(ProcessVariables.java:131)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables$1.processBlock(ProcessVariables.java:82)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:64)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Unknown Source)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:19)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables.removeUnusedResults(ProcessVariables.java:73)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables.visit(ProcessVariables.java:48)
        */
    final java.util.List<com.google.android.gms.internal.measurement.zzfy.zzd> zza(java.lang.String r25, java.util.List<com.google.android.gms.internal.measurement.zzfy.zzf> r26, java.util.List<com.google.android.gms.internal.measurement.zzfy.zzo> r27, java.lang.Long r28, java.lang.Long r29, boolean r30) {
        /*
            Method dump skipped, instructions count: 978
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzt.zza(java.lang.String, java.util.List, java.util.List, java.lang.Long, java.lang.Long, boolean):java.util.List");
    }

    private final List<zzfy.zzd> zzu() {
        ArrayList arrayList = new ArrayList();
        Set<Integer> keySet = this.zzc.keySet();
        keySet.removeAll(this.zzb);
        for (Integer num : keySet) {
            int intValue = num.intValue();
            zzv zzvVar = this.zzc.get(num);
            Preconditions.checkNotNull(zzvVar);
            zzfy.zzd zza = zzvVar.zza(intValue);
            arrayList.add(zza);
            zzal zzh = zzh();
            String str = this.zza;
            zzfy.zzm zzd = zza.zzd();
            zzh.zzal();
            zzh.zzt();
            Preconditions.checkNotEmpty(str);
            Preconditions.checkNotNull(zzd);
            byte[] zzca = zzd.zzca();
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", str);
            contentValues.put("audience_id", num);
            contentValues.put("current_results", zzca);
            try {
                if (zzh.e_().insertWithOnConflict("audience_filter_values", null, contentValues, 5) == -1) {
                    zzh.zzj().zzg().zza("Failed to insert filter results (got -1). appId", zzgo.zza(str));
                }
            } catch (SQLiteException e) {
                zzh.zzj().zzg().zza("Error storing filter results. appId", zzgo.zza(str), e);
            }
        }
        return arrayList;
    }

    zzt(zznv zznvVar) {
        super(zznvVar);
    }

    private final void zza(List<zzfy.zzf> list, boolean z) {
        zzbb zzbbVar;
        zzy zzyVar;
        Integer num;
        Map<Integer, List<zzfo.zzb>> map;
        long j;
        if (list.isEmpty()) {
            return;
        }
        String str = null;
        zzy zzyVar2 = new zzy(this);
        ArrayMap arrayMap = new ArrayMap();
        for (zzfy.zzf zzfVar : list) {
            zzfy.zzf zza = zzyVar2.zza(this.zza, zzfVar);
            if (zza != null) {
                zzal zzh = zzh();
                String str2 = this.zza;
                String zzg = zza.zzg();
                zzbb zzd = zzh.zzd(str2, zzfVar.zzg());
                if (zzd == null) {
                    zzh.zzj().zzu().zza("Event aggregate wasn't created during raw event logging. appId, event", zzgo.zza(str2), zzh.zzi().zza(zzg));
                    zzbbVar = new zzbb(str2, zzfVar.zzg(), 1L, 1L, 1L, zzfVar.zzd(), 0L, null, null, null, null);
                } else {
                    zzbbVar = new zzbb(zzd.zza, zzd.zzb, zzd.zzc + 1, zzd.zzd + 1, zzd.zze + 1, zzd.zzf, zzd.zzg, zzd.zzh, zzd.zzi, zzd.zzj, zzd.zzk);
                }
                zzbb zzbbVar2 = zzbbVar;
                zzh().zza(zzbbVar2);
                if (!com.google.android.gms.internal.measurement.zznm.zza() || !zze().zzf(str, zzbh.zzcy) || !z) {
                    long j2 = zzbbVar2.zzc;
                    String zzg2 = zza.zzg();
                    Map<Integer, List<zzfo.zzb>> map2 = (Map) arrayMap.get(zzg2);
                    if (map2 == null) {
                        map2 = zzh().zzf(this.zza, zzg2);
                        arrayMap.put(zzg2, map2);
                    }
                    Map<Integer, List<zzfo.zzb>> map3 = map2;
                    Iterator<Integer> it = map3.keySet().iterator();
                    while (it.hasNext()) {
                        Integer next = it.next();
                        int intValue = next.intValue();
                        if (this.zzb.contains(next)) {
                            zzj().zzp().zza("Skipping failed audience ID", next);
                        } else {
                            Iterator<zzfo.zzb> it2 = map3.get(next).iterator();
                            boolean z2 = true;
                            while (true) {
                                if (!it2.hasNext()) {
                                    zzyVar = zzyVar2;
                                    num = next;
                                    map = map3;
                                    j = j2;
                                    break;
                                }
                                zzfo.zzb next2 = it2.next();
                                zzx zzxVar = new zzx(this, this.zza, intValue, next2);
                                zzyVar = zzyVar2;
                                num = next;
                                int i = intValue;
                                map = map3;
                                j = j2;
                                z2 = zzxVar.zza(this.zzd, this.zze, zza, j2, zzbbVar2, zza(intValue, next2.zzb()));
                                if (z2) {
                                    zza(num).zza(zzxVar);
                                    next = num;
                                    zzyVar2 = zzyVar;
                                    intValue = i;
                                    map3 = map;
                                    j2 = j;
                                } else {
                                    this.zzb.add(num);
                                    break;
                                }
                            }
                            if (!z2) {
                                this.zzb.add(num);
                            }
                            zzyVar2 = zzyVar;
                            map3 = map;
                            j2 = j;
                            str = null;
                        }
                    }
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:42:0x00fb, code lost:
    
        r5 = zzj().zzu();
        r6 = com.google.android.gms.measurement.internal.zzgo.zza(r13.zza);
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x010d, code lost:
    
        if (r7.zzi() == false) goto L95;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x010f, code lost:
    
        r9 = java.lang.Integer.valueOf(r7.zza());
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0117, code lost:
    
        r5.zza("Invalid property filter ID. appId, id", r6, java.lang.String.valueOf(r9));
        r7 = false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void zza(java.util.List<com.google.android.gms.internal.measurement.zzfy.zzo> r14) {
        /*
            Method dump skipped, instructions count: 299
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzt.zza(java.util.List):void");
    }

    private final boolean zza(int i, int i2) {
        BitSet bitSet;
        zzv zzvVar = this.zzc.get(Integer.valueOf(i));
        if (zzvVar == null) {
            return false;
        }
        bitSet = zzvVar.zzd;
        return bitSet.get(i2);
    }
}
