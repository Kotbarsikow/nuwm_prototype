package com.google.android.gms.internal.fido;

import android.app.PendingIntent;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

/* compiled from: com.google.android.gms:play-services-fido@@19.0.0 */
/* loaded from: classes.dex */
public interface zzm extends IInterface {
    void zzb(Status status, PendingIntent pendingIntent) throws RemoteException;
}
