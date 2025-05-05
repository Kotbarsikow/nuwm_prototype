package com.google.android.gms.internal.measurement;

import android.content.Context;
import com.google.android.gms.internal.measurement.zzhg;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes.dex */
public abstract class zzhj<T> {
    private static final Object zza = new Object();

    @Nullable
    private static volatile zzhu zzb = null;
    private static volatile boolean zzc = false;
    private static zzhy zzd;
    private static final AtomicInteger zze;
    private final zzhr zzf;
    private final String zzg;
    private Object zzh;
    private volatile int zzi;
    private volatile T zzj;
    private final boolean zzk;
    private volatile boolean zzl;

    /* synthetic */ zzhj(zzhr zzhrVar, String str, Object obj, boolean z, zzht zzhtVar) {
        this(zzhrVar, str, obj, true);
    }

    static /* synthetic */ boolean zzd() {
        return true;
    }

    abstract T zza(Object obj);

    static /* synthetic */ zzhj zza(zzhr zzhrVar, String str, Boolean bool, boolean z) {
        return new zzhq(zzhrVar, str, bool, true);
    }

    static /* synthetic */ zzhj zza(zzhr zzhrVar, String str, Double d, boolean z) {
        return new zzhp(zzhrVar, str, d, true);
    }

    static /* synthetic */ zzhj zza(zzhr zzhrVar, String str, Long l, boolean z) {
        return new zzhn(zzhrVar, str, l, true);
    }

    static /* synthetic */ zzhj zza(zzhr zzhrVar, String str, String str2, boolean z) {
        return new zzhs(zzhrVar, str, str2, true);
    }

    public final T zza() {
        T zzb2;
        if (!this.zzk) {
            Preconditions.checkState(zzd.zza(this.zzg), "Attempt to access PhenotypeFlag not via codegen. All new PhenotypeFlags must be accessed through codegen APIs. If you believe you are seeing this error by mistake, you can add your flag to the exemption list located at //java/com/google/android/libraries/phenotype/client/lockdown/flags.textproto. Send the addition CL to ph-reviews@. See go/phenotype-android-codegen for information about generated code. See go/ph-lockdown for more information about this error.");
        }
        int i = zze.get();
        if (this.zzi < i) {
            synchronized (this) {
                if (this.zzi < i) {
                    zzhu zzhuVar = zzb;
                    Optional<zzhh> absent = Optional.absent();
                    String str = null;
                    if (zzhuVar != null) {
                        absent = zzhuVar.zzb().get();
                        if (absent.isPresent()) {
                            str = absent.get().zza(this.zzf.zzb, this.zzf.zza, this.zzf.zzd, this.zzg);
                        }
                    }
                    Preconditions.checkState(zzhuVar != null, "Must call PhenotypeFlagInitializer.maybeInit() first");
                    if (!this.zzf.zzf ? (zzb2 = zzb(zzhuVar)) == null && (zzb2 = zza(zzhuVar)) == null : (zzb2 = zza(zzhuVar)) == null && (zzb2 = zzb(zzhuVar)) == null) {
                        zzb2 = zze();
                    }
                    if (absent.isPresent()) {
                        zzb2 = str == null ? zze() : zza((Object) str);
                    }
                    this.zzj = zzb2;
                    this.zzi = i;
                }
            }
        }
        return this.zzj;
    }

    private final T zze() {
        return (T) this.zzh;
    }

    @Nullable
    private final T zza(zzhu zzhuVar) {
        if (!this.zzf.zze && (this.zzf.zzh == null || this.zzf.zzh.apply(zzhuVar.zza()).booleanValue())) {
            Object zza2 = zzhc.zza(zzhuVar.zza()).zza(this.zzf.zze ? null : zza(this.zzf.zzc));
            if (zza2 != null) {
                return zza(zza2);
            }
        }
        return null;
    }

    @Nullable
    private final T zzb(zzhu zzhuVar) {
        zzhb zza2;
        Object zza3;
        if (this.zzf.zzb != null) {
            if (!zzhi.zza(zzhuVar.zza(), this.zzf.zzb)) {
                zza2 = null;
            } else if (this.zzf.zzg) {
                zza2 = zzgu.zza(zzhuVar.zza().getContentResolver(), zzhk.zza(zzhk.zza(zzhuVar.zza(), this.zzf.zzb.getLastPathSegment())), new Runnable() { // from class: com.google.android.gms.internal.measurement.zzhm
                    @Override // java.lang.Runnable
                    public final void run() {
                        zzhj.zzc();
                    }
                });
            } else {
                zza2 = zzgu.zza(zzhuVar.zza().getContentResolver(), this.zzf.zzb, new Runnable() { // from class: com.google.android.gms.internal.measurement.zzhm
                    @Override // java.lang.Runnable
                    public final void run() {
                        zzhj.zzc();
                    }
                });
            }
        } else {
            zza2 = zzhw.zza(zzhuVar.zza(), this.zzf.zza, new Runnable() { // from class: com.google.android.gms.internal.measurement.zzhm
                @Override // java.lang.Runnable
                public final void run() {
                    zzhj.zzc();
                }
            });
        }
        if (zza2 == null || (zza3 = zza2.zza(zzb())) == null) {
            return null;
        }
        return zza(zza3);
    }

    public final String zzb() {
        return zza(this.zzf.zzd);
    }

    private final String zza(String str) {
        if (str != null && str.isEmpty()) {
            return this.zzg;
        }
        return str + this.zzg;
    }

    static {
        new AtomicReference();
        zzd = new zzhy(new zzhx() { // from class: com.google.android.gms.internal.measurement.zzho
            @Override // com.google.android.gms.internal.measurement.zzhx
            public final boolean zza() {
                return zzhj.zzd();
            }
        });
        zze = new AtomicInteger();
    }

    /* JADX INFO: Access modifiers changed from: private */
    zzhj(zzhr zzhrVar, String str, T t, boolean z) {
        this.zzi = -1;
        if (zzhrVar.zza == null && zzhrVar.zzb == null) {
            throw new IllegalArgumentException("Must pass a valid SharedPreferences file name or ContentProvider URI");
        }
        if (zzhrVar.zza != null && zzhrVar.zzb != null) {
            throw new IllegalArgumentException("Must pass one of SharedPreferences file name or ContentProvider URI");
        }
        this.zzf = zzhrVar;
        this.zzg = str;
        this.zzh = t;
        this.zzk = z;
        this.zzl = false;
    }

    public static void zzc() {
        zze.incrementAndGet();
    }

    public static void zzb(Context context) {
        if (zzb != null || context == null) {
            return;
        }
        Object obj = zza;
        synchronized (obj) {
            if (zzb == null && context != null) {
                synchronized (obj) {
                    zzhu zzhuVar = zzb;
                    Context applicationContext = context.getApplicationContext();
                    if (applicationContext != null) {
                        context = applicationContext;
                    }
                    if (zzhuVar == null || zzhuVar.zza() != context) {
                        if (zzhuVar != null) {
                            zzgu.zzc();
                            zzhw.zza();
                            zzhc.zza();
                        }
                        zzb = new zzgv(context, Suppliers.memoize(new Supplier() { // from class: com.google.android.gms.internal.measurement.zzhl
                            private /* synthetic */ Context zza;

                            public /* synthetic */ zzhl(Context context2) {
                                r1 = context2;
                            }

                            @Override // com.google.common.base.Supplier
                            public final Object get() {
                                Optional zza2;
                                zza2 = zzhg.zza.zza(r1);
                                return zza2;
                            }
                        }));
                        zze.incrementAndGet();
                    }
                }
            }
        }
    }
}
