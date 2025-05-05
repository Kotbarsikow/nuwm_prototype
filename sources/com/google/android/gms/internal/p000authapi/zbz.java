package com.google.android.gms.internal.p000authapi;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.api.identity.SaveAccountLinkingTokenRequest;
import com.google.android.gms.auth.api.identity.SavePasswordRequest;

/* compiled from: com.google.android.gms:play-services-auth@@20.4.0 */
/* loaded from: classes.dex */
public final class zbz extends zba implements IInterface {
    zbz(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.auth.api.identity.internal.ICredentialSavingService");
    }

    public final void zbc(zbaf zbafVar, SaveAccountLinkingTokenRequest saveAccountLinkingTokenRequest) throws RemoteException {
        Parcel zba = zba();
        zbc.zbd(zba, zbafVar);
        zbc.zbc(zba, saveAccountLinkingTokenRequest);
        zbb(1, zba);
    }

    public final void zbd(zbah zbahVar, SavePasswordRequest savePasswordRequest) throws RemoteException {
        Parcel zba = zba();
        zbc.zbd(zba, zbahVar);
        zbc.zbc(zba, savePasswordRequest);
        zbb(2, zba);
    }
}
