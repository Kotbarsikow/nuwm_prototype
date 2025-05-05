package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes.dex */
public final class zzgd extends com.google.android.gms.internal.measurement.zzbu implements zzgb {
    @Override // com.google.android.gms.measurement.internal.zzgb
    public final zzaj zza(zzo zzoVar) throws RemoteException {
        Parcel a_ = a_();
        com.google.android.gms.internal.measurement.zzbw.zza(a_, zzoVar);
        Parcel zza = zza(21, a_);
        zzaj zzajVar = (zzaj) com.google.android.gms.internal.measurement.zzbw.zza(zza, zzaj.CREATOR);
        zza.recycle();
        return zzajVar;
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final String zzb(zzo zzoVar) throws RemoteException {
        Parcel a_ = a_();
        com.google.android.gms.internal.measurement.zzbw.zza(a_, zzoVar);
        Parcel zza = zza(11, a_);
        String readString = zza.readString();
        zza.recycle();
        return readString;
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final List<zzno> zza(zzo zzoVar, Bundle bundle) throws RemoteException {
        Parcel a_ = a_();
        com.google.android.gms.internal.measurement.zzbw.zza(a_, zzoVar);
        com.google.android.gms.internal.measurement.zzbw.zza(a_, bundle);
        Parcel zza = zza(24, a_);
        ArrayList createTypedArrayList = zza.createTypedArrayList(zzno.CREATOR);
        zza.recycle();
        return createTypedArrayList;
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final List<zzon> zza(zzo zzoVar, boolean z) throws RemoteException {
        Parcel a_ = a_();
        com.google.android.gms.internal.measurement.zzbw.zza(a_, zzoVar);
        com.google.android.gms.internal.measurement.zzbw.zza(a_, z);
        Parcel zza = zza(7, a_);
        ArrayList createTypedArrayList = zza.createTypedArrayList(zzon.CREATOR);
        zza.recycle();
        return createTypedArrayList;
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final List<zzae> zza(String str, String str2, zzo zzoVar) throws RemoteException {
        Parcel a_ = a_();
        a_.writeString(str);
        a_.writeString(str2);
        com.google.android.gms.internal.measurement.zzbw.zza(a_, zzoVar);
        Parcel zza = zza(16, a_);
        ArrayList createTypedArrayList = zza.createTypedArrayList(zzae.CREATOR);
        zza.recycle();
        return createTypedArrayList;
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final List<zzae> zza(String str, String str2, String str3) throws RemoteException {
        Parcel a_ = a_();
        a_.writeString(str);
        a_.writeString(str2);
        a_.writeString(str3);
        Parcel zza = zza(17, a_);
        ArrayList createTypedArrayList = zza.createTypedArrayList(zzae.CREATOR);
        zza.recycle();
        return createTypedArrayList;
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final List<zzon> zza(String str, String str2, boolean z, zzo zzoVar) throws RemoteException {
        Parcel a_ = a_();
        a_.writeString(str);
        a_.writeString(str2);
        com.google.android.gms.internal.measurement.zzbw.zza(a_, z);
        com.google.android.gms.internal.measurement.zzbw.zza(a_, zzoVar);
        Parcel zza = zza(14, a_);
        ArrayList createTypedArrayList = zza.createTypedArrayList(zzon.CREATOR);
        zza.recycle();
        return createTypedArrayList;
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final List<zzon> zza(String str, String str2, String str3, boolean z) throws RemoteException {
        Parcel a_ = a_();
        a_.writeString(str);
        a_.writeString(str2);
        a_.writeString(str3);
        com.google.android.gms.internal.measurement.zzbw.zza(a_, z);
        Parcel zza = zza(15, a_);
        ArrayList createTypedArrayList = zza.createTypedArrayList(zzon.CREATOR);
        zza.recycle();
        return createTypedArrayList;
    }

    zzgd(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.measurement.internal.IMeasurementService");
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zzc(zzo zzoVar) throws RemoteException {
        Parcel a_ = a_();
        com.google.android.gms.internal.measurement.zzbw.zza(a_, zzoVar);
        zzb(27, a_);
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zzd(zzo zzoVar) throws RemoteException {
        Parcel a_ = a_();
        com.google.android.gms.internal.measurement.zzbw.zza(a_, zzoVar);
        zzb(4, a_);
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zza(zzbf zzbfVar, zzo zzoVar) throws RemoteException {
        Parcel a_ = a_();
        com.google.android.gms.internal.measurement.zzbw.zza(a_, zzbfVar);
        com.google.android.gms.internal.measurement.zzbw.zza(a_, zzoVar);
        zzb(1, a_);
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zza(zzbf zzbfVar, String str, String str2) throws RemoteException {
        Parcel a_ = a_();
        com.google.android.gms.internal.measurement.zzbw.zza(a_, zzbfVar);
        a_.writeString(str);
        a_.writeString(str2);
        zzb(5, a_);
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zze(zzo zzoVar) throws RemoteException {
        Parcel a_ = a_();
        com.google.android.gms.internal.measurement.zzbw.zza(a_, zzoVar);
        zzb(18, a_);
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zza(zzae zzaeVar, zzo zzoVar) throws RemoteException {
        Parcel a_ = a_();
        com.google.android.gms.internal.measurement.zzbw.zza(a_, zzaeVar);
        com.google.android.gms.internal.measurement.zzbw.zza(a_, zzoVar);
        zzb(12, a_);
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zza(zzae zzaeVar) throws RemoteException {
        Parcel a_ = a_();
        com.google.android.gms.internal.measurement.zzbw.zza(a_, zzaeVar);
        zzb(13, a_);
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zzf(zzo zzoVar) throws RemoteException {
        Parcel a_ = a_();
        com.google.android.gms.internal.measurement.zzbw.zza(a_, zzoVar);
        zzb(20, a_);
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zza(long j, String str, String str2, String str3) throws RemoteException {
        Parcel a_ = a_();
        a_.writeLong(j);
        a_.writeString(str);
        a_.writeString(str2);
        a_.writeString(str3);
        zzb(10, a_);
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zza(Bundle bundle, zzo zzoVar) throws RemoteException {
        Parcel a_ = a_();
        com.google.android.gms.internal.measurement.zzbw.zza(a_, bundle);
        com.google.android.gms.internal.measurement.zzbw.zza(a_, zzoVar);
        zzb(19, a_);
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zzb(Bundle bundle, zzo zzoVar) throws RemoteException {
        Parcel a_ = a_();
        com.google.android.gms.internal.measurement.zzbw.zza(a_, bundle);
        com.google.android.gms.internal.measurement.zzbw.zza(a_, zzoVar);
        zzb(28, a_);
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zzg(zzo zzoVar) throws RemoteException {
        Parcel a_ = a_();
        com.google.android.gms.internal.measurement.zzbw.zza(a_, zzoVar);
        zzb(26, a_);
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zzh(zzo zzoVar) throws RemoteException {
        Parcel a_ = a_();
        com.google.android.gms.internal.measurement.zzbw.zza(a_, zzoVar);
        zzb(6, a_);
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zzi(zzo zzoVar) throws RemoteException {
        Parcel a_ = a_();
        com.google.android.gms.internal.measurement.zzbw.zza(a_, zzoVar);
        zzb(25, a_);
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final void zza(zzon zzonVar, zzo zzoVar) throws RemoteException {
        Parcel a_ = a_();
        com.google.android.gms.internal.measurement.zzbw.zza(a_, zzonVar);
        com.google.android.gms.internal.measurement.zzbw.zza(a_, zzoVar);
        zzb(2, a_);
    }

    @Override // com.google.android.gms.measurement.internal.zzgb
    public final byte[] zza(zzbf zzbfVar, String str) throws RemoteException {
        Parcel a_ = a_();
        com.google.android.gms.internal.measurement.zzbw.zza(a_, zzbfVar);
        a_.writeString(str);
        Parcel zza = zza(9, a_);
        byte[] createByteArray = zza.createByteArray();
        zza.recycle();
        return createByteArray;
    }
}
