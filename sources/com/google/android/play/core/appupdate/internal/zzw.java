package com.google.android.play.core.appupdate.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

/* compiled from: com.google.android.play:app-update@@2.1.0 */
/* loaded from: classes2.dex */
final class zzw implements ServiceConnection {
    final /* synthetic */ zzx zza;

    /* synthetic */ zzw(zzx zzxVar, zzv zzvVar) {
        this.zza = zzxVar;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        zzm zzmVar;
        zzmVar = this.zza.zzc;
        zzmVar.zzd("ServiceConnectionImpl.onServiceConnected(%s)", componentName);
        this.zza.zzc().post(new zzt(this, iBinder));
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        zzm zzmVar;
        zzmVar = this.zza.zzc;
        zzmVar.zzd("ServiceConnectionImpl.onServiceDisconnected(%s)", componentName);
        this.zza.zzc().post(new zzu(this));
    }
}
