package com.google.android.gms.internal.p000authapi;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-auth@@20.4.0 */
/* loaded from: classes.dex */
final class zbaw extends zbac {
    final /* synthetic */ TaskCompletionSource zba;

    zbaw(zbay zbayVar, TaskCompletionSource taskCompletionSource) {
        this.zba = taskCompletionSource;
    }

    @Override // com.google.android.gms.internal.p000authapi.zbad
    public final void zbb(Status status, PendingIntent pendingIntent) throws RemoteException {
        TaskUtil.setResultOrApiException(status, pendingIntent, this.zba);
    }
}
