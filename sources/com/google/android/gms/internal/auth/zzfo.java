package com.google.android.gms.internal.auth;

/* compiled from: com.google.android.gms:play-services-auth-base@@18.0.4 */
/* loaded from: classes.dex */
final class zzfo implements zzgi {
    private static final zzfu zza = new zzfm();
    private final zzfu zzb;

    public zzfo() {
        zzfu zzfuVar;
        zzfu[] zzfuVarArr = new zzfu[2];
        zzfuVarArr[0] = zzer.zza();
        try {
            zzfuVar = (zzfu) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", null).invoke(null, null);
        } catch (Exception unused) {
            zzfuVar = zza;
        }
        zzfuVarArr[1] = zzfuVar;
        zzfn zzfnVar = new zzfn(zzfuVarArr);
        zzez.zzf(zzfnVar, "messageInfoFactory");
        this.zzb = zzfnVar;
    }

    private static boolean zzb(zzft zzftVar) {
        return zzftVar.zzc() == 1;
    }

    @Override // com.google.android.gms.internal.auth.zzgi
    public final zzgh zza(Class cls) {
        zzgj.zzg(cls);
        zzft zzb = this.zzb.zzb(cls);
        return zzb.zzb() ? zzeu.class.isAssignableFrom(cls) ? zzga.zzb(zzgj.zzc(), zzen.zzb(), zzb.zza()) : zzga.zzb(zzgj.zza(), zzen.zza(), zzb.zza()) : zzeu.class.isAssignableFrom(cls) ? zzb(zzb) ? zzfz.zzj(cls, zzb, zzgc.zzb(), zzfk.zzd(), zzgj.zzc(), zzen.zzb(), zzfs.zzb()) : zzfz.zzj(cls, zzb, zzgc.zzb(), zzfk.zzd(), zzgj.zzc(), null, zzfs.zzb()) : zzb(zzb) ? zzfz.zzj(cls, zzb, zzgc.zza(), zzfk.zzc(), zzgj.zza(), zzen.zza(), zzfs.zza()) : zzfz.zzj(cls, zzb, zzgc.zza(), zzfk.zzc(), zzgj.zzb(), null, zzfs.zza());
    }
}
