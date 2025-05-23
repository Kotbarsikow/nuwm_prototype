package com.google.android.play.core.appupdate;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.play.core.install.InstallException;

/* compiled from: com.google.android.play:app-update@@2.1.0 */
/* loaded from: classes2.dex */
final class zzq extends zzo {
    final /* synthetic */ zzr zzd;
    private final String zze;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzq(zzr zzrVar, TaskCompletionSource taskCompletionSource, String str) {
        super(zzrVar, new com.google.android.play.core.appupdate.internal.zzm("OnRequestInstallCallback"), taskCompletionSource);
        this.zzd = zzrVar;
        this.zze = str;
    }

    @Override // com.google.android.play.core.appupdate.zzo, com.google.android.play.core.appupdate.internal.zzh
    public final void zzc(Bundle bundle) throws RemoteException {
        int i;
        int i2;
        super.zzc(bundle);
        i = bundle.getInt("error.code", -2);
        if (i == 0) {
            this.zzb.trySetResult(zzr.zzf(this.zzd, bundle, this.zze));
            return;
        }
        TaskCompletionSource taskCompletionSource = this.zzb;
        i2 = bundle.getInt("error.code", -2);
        taskCompletionSource.trySetException(new InstallException(i2));
    }
}
