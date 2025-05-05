package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzjt;
import java.util.List;
import kotlin.text.Typography;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
/* loaded from: classes.dex */
public final class zzgd {

    /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
    public static final class zza extends zzjt<zza, C0014zza> implements zzle {
        private static final zza zzc;
        private static volatile zzll<zza> zzd;
        private zzkc<zzb> zze = zzch();

        /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
        /* renamed from: com.google.android.gms.internal.measurement.zzgd$zza$zza, reason: collision with other inner class name */
        public static final class C0014zza extends zzjt.zzb<zza, C0014zza> implements zzle {
            private C0014zza() {
                super(zza.zzc);
            }
        }

        public final int zza() {
            return this.zze.size();
        }

        public static zza zzc() {
            return zzc;
        }

        @Override // com.google.android.gms.internal.measurement.zzjt
        protected final Object zza(int i, Object obj, Object obj2) {
            switch (zzgf.zza[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C0014zza();
                case 3:
                    return zza(zzc, "\u0004\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"zze", zzb.class});
                case 4:
                    return zzc;
                case 5:
                    zzll<zza> zzllVar = zzd;
                    if (zzllVar == null) {
                        synchronized (zza.class) {
                            zzllVar = zzd;
                            if (zzllVar == null) {
                                zzllVar = new zzjt.zza<>(zzc);
                                zzd = zzllVar;
                            }
                        }
                    }
                    return zzllVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public final List<zzb> zzd() {
            return this.zze;
        }

        static {
            zza zzaVar = new zza();
            zzc = zzaVar;
            zzjt.zza((Class<zza>) zza.class, zzaVar);
        }

        private zza() {
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
    public static final class zzc extends zzjt<zzc, zza> implements zzle {
        private static final zzc zzc;
        private static volatile zzll<zzc> zzd;
        private int zze;
        private zzkc<zzd> zzf = zzch();
        private zza zzg;

        /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
        public static final class zza extends zzjt.zzb<zzc, zza> implements zzle {
            private zza() {
                super(zzc.zzc);
            }
        }

        public final zza zza() {
            zza zzaVar = this.zzg;
            return zzaVar == null ? zza.zzc() : zzaVar;
        }

        @Override // com.google.android.gms.internal.measurement.zzjt
        protected final Object zza(int i, Object obj, Object obj2) {
            switch (zzgf.zza[i - 1]) {
                case 1:
                    return new zzc();
                case 2:
                    return new zza();
                case 3:
                    return zza(zzc, "\u0004\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u001b\u0002ဉ\u0000", new Object[]{"zze", "zzf", zzd.class, "zzg"});
                case 4:
                    return zzc;
                case 5:
                    zzll<zzc> zzllVar = zzd;
                    if (zzllVar == null) {
                        synchronized (zzc.class) {
                            zzllVar = zzd;
                            if (zzllVar == null) {
                                zzllVar = new zzjt.zza<>(zzc);
                                zzd = zzllVar;
                            }
                        }
                    }
                    return zzllVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public final List<zzd> zzc() {
            return this.zzf;
        }

        static {
            zzc zzcVar = new zzc();
            zzc = zzcVar;
            zzjt.zza((Class<zzc>) zzc.class, zzcVar);
        }

        private zzc() {
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
    public static final class zzd extends zzjt<zzd, zzb> implements zzle {
        private static final zzd zzc;
        private static volatile zzll<zzd> zzd;
        private int zze;
        private int zzf;
        private zzkc<zzd> zzg = zzch();
        private String zzh = "";
        private String zzi = "";
        private boolean zzj;
        private double zzk;

        /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
        public static final class zzb extends zzjt.zzb<zzd, zzb> implements zzle {
            private zzb() {
                super(zzd.zzc);
            }
        }

        public final double zza() {
            return this.zzk;
        }

        public final zza zzb() {
            zza zza2 = zza.zza(this.zzf);
            return zza2 == null ? zza.UNKNOWN : zza2;
        }

        @Override // com.google.android.gms.internal.measurement.zzjt
        protected final Object zza(int i, Object obj, Object obj2) {
            switch (zzgf.zza[i - 1]) {
                case 1:
                    return new zzd();
                case 2:
                    return new zzb();
                case 3:
                    return zza(zzc, "\u0004\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0001\u0000\u0001᠌\u0000\u0002\u001b\u0003ဈ\u0001\u0004ဈ\u0002\u0005ဇ\u0003\u0006က\u0004", new Object[]{"zze", "zzf", zza.zzb(), "zzg", zzd.class, "zzh", "zzi", "zzj", "zzk"});
                case 4:
                    return zzc;
                case 5:
                    zzll<zzd> zzllVar = zzd;
                    if (zzllVar == null) {
                        synchronized (zzd.class) {
                            zzllVar = zzd;
                            if (zzllVar == null) {
                                zzllVar = new zzjt.zza<>(zzc);
                                zzd = zzllVar;
                            }
                        }
                    }
                    return zzllVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
        public enum zza implements zzjy {
            UNKNOWN(0),
            STRING(1),
            NUMBER(2),
            BOOLEAN(3),
            STATEMENT(4);

            private final int zzg;

            @Override // com.google.android.gms.internal.measurement.zzjy
            public final int zza() {
                return this.zzg;
            }

            public static zza zza(int i) {
                if (i == 0) {
                    return UNKNOWN;
                }
                if (i == 1) {
                    return STRING;
                }
                if (i == 2) {
                    return NUMBER;
                }
                if (i == 3) {
                    return BOOLEAN;
                }
                if (i != 4) {
                    return null;
                }
                return STATEMENT;
            }

            public static zzjx zzb() {
                return zzgh.zza;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.zzg + " name=" + name() + Typography.greater;
            }

            zza(int i) {
                this.zzg = i;
            }
        }

        public final String zzd() {
            return this.zzh;
        }

        public final String zze() {
            return this.zzi;
        }

        public final List<zzd> zzf() {
            return this.zzg;
        }

        static {
            zzd zzdVar = new zzd();
            zzc = zzdVar;
            zzjt.zza((Class<zzd>) zzd.class, zzdVar);
        }

        private zzd() {
        }

        public final boolean zzg() {
            return this.zzj;
        }

        public final boolean zzh() {
            return (this.zze & 8) != 0;
        }

        public final boolean zzi() {
            return (this.zze & 16) != 0;
        }

        public final boolean zzj() {
            return (this.zze & 4) != 0;
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
    public static final class zzb extends zzjt<zzb, zza> implements zzle {
        private static final zzb zzc;
        private static volatile zzll<zzb> zzd;
        private int zze;
        private String zzf = "";
        private zzkc<zzd> zzg = zzch();

        /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
        public static final class zza extends zzjt.zzb<zzb, zza> implements zzle {
            private zza() {
                super(zzb.zzc);
            }
        }

        @Override // com.google.android.gms.internal.measurement.zzjt
        protected final Object zza(int i, Object obj, Object obj2) {
            switch (zzgf.zza[i - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new zza();
                case 3:
                    return zza(zzc, "\u0004\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001ဈ\u0000\u0002\u001b", new Object[]{"zze", "zzf", "zzg", zzd.class});
                case 4:
                    return zzc;
                case 5:
                    zzll<zzb> zzllVar = zzd;
                    if (zzllVar == null) {
                        synchronized (zzb.class) {
                            zzllVar = zzd;
                            if (zzllVar == null) {
                                zzllVar = new zzjt.zza<>(zzc);
                                zzd = zzllVar;
                            }
                        }
                    }
                    return zzllVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public final String zzb() {
            return this.zzf;
        }

        public final List<zzd> zzc() {
            return this.zzg;
        }

        static {
            zzb zzbVar = new zzb();
            zzc = zzbVar;
            zzjt.zza((Class<zzb>) zzb.class, zzbVar);
        }

        private zzb() {
        }
    }
}
