package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes.dex */
public final class zzbi implements Parcelable.Creator<zzbf> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzbf createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String str = null;
        zzbe zzbeVar = null;
        String str2 = null;
        long j = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 2) {
                str = SafeParcelReader.createString(parcel, readHeader);
            } else if (fieldId == 3) {
                zzbeVar = (zzbe) SafeParcelReader.createParcelable(parcel, readHeader, zzbe.CREATOR);
            } else if (fieldId == 4) {
                str2 = SafeParcelReader.createString(parcel, readHeader);
            } else if (fieldId != 5) {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            } else {
                j = SafeParcelReader.readLong(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzbf(str, zzbeVar, str2, j);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzbf[] newArray(int i) {
        return new zzbf[i];
    }
}
