package com.google.android.gms.internal.p000authapi;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.api.credentials.CredentialRequest;

/* compiled from: com.google.android.gms:play-services-auth@@20.4.0 */
/* loaded from: classes.dex */
public final class zbt extends zba implements IInterface {
    zbt(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.auth.api.credentials.internal.ICredentialsService");
    }

    public final void zbc(zbs zbsVar, zbp zbpVar) throws RemoteException {
        Parcel zba = zba();
        zbc.zbd(zba, zbsVar);
        zbc.zbc(zba, zbpVar);
        zbb(3, zba);
    }

    public final void zbd(zbs zbsVar, CredentialRequest credentialRequest) throws RemoteException {
        Parcel zba = zba();
        zbc.zbd(zba, zbsVar);
        zbc.zbc(zba, credentialRequest);
        zbb(1, zba);
    }

    public final void zbe(zbs zbsVar, zbu zbuVar) throws RemoteException {
        Parcel zba = zba();
        zbc.zbd(zba, zbsVar);
        zbc.zbc(zba, zbuVar);
        zbb(2, zba);
    }

    public final void zbf(zbs zbsVar) throws RemoteException {
        Parcel zba = zba();
        zbc.zbd(zba, zbsVar);
        zbb(4, zba);
    }
}
