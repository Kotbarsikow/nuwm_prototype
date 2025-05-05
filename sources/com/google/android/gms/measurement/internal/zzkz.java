package com.google.android.gms.measurement.internal;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import kotlinx.coroutines.DebugKt;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes.dex */
final class zzkz implements Application.ActivityLifecycleCallbacks {
    private final /* synthetic */ zzjq zza;

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x00ad A[Catch: RuntimeException -> 0x019f, TRY_ENTER, TryCatch #0 {RuntimeException -> 0x019f, blocks: (B:3:0x000b, B:5:0x0019, B:8:0x002a, B:14:0x00ad, B:16:0x00b9, B:19:0x00ce, B:21:0x00d4, B:24:0x00e1, B:26:0x00e7, B:27:0x00ff, B:28:0x010e, B:32:0x0115, B:36:0x0138, B:37:0x0154, B:39:0x0145, B:40:0x015b, B:42:0x0161, B:44:0x0167, B:46:0x016d, B:48:0x0173, B:50:0x017b, B:52:0x0183, B:54:0x0189, B:57:0x018f, B:62:0x003e, B:65:0x0046, B:67:0x004e, B:69:0x0054, B:71:0x005a, B:73:0x0060, B:75:0x0068, B:77:0x0070, B:79:0x0078, B:81:0x0080, B:82:0x008c, B:84:0x00a4), top: B:2:0x000b }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0114 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0115 A[Catch: RuntimeException -> 0x019f, TRY_LEAVE, TryCatch #0 {RuntimeException -> 0x019f, blocks: (B:3:0x000b, B:5:0x0019, B:8:0x002a, B:14:0x00ad, B:16:0x00b9, B:19:0x00ce, B:21:0x00d4, B:24:0x00e1, B:26:0x00e7, B:27:0x00ff, B:28:0x010e, B:32:0x0115, B:36:0x0138, B:37:0x0154, B:39:0x0145, B:40:0x015b, B:42:0x0161, B:44:0x0167, B:46:0x016d, B:48:0x0173, B:50:0x017b, B:52:0x0183, B:54:0x0189, B:57:0x018f, B:62:0x003e, B:65:0x0046, B:67:0x004e, B:69:0x0054, B:71:0x005a, B:73:0x0060, B:75:0x0068, B:77:0x0070, B:79:0x0078, B:81:0x0080, B:82:0x008c, B:84:0x00a4), top: B:2:0x000b }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static /* synthetic */ void zza(com.google.android.gms.measurement.internal.zzkz r17, boolean r18, android.net.Uri r19, java.lang.String r20, java.lang.String r21) {
        /*
            Method dump skipped, instructions count: 432
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzkz.zza(com.google.android.gms.measurement.internal.zzkz, boolean, android.net.Uri, java.lang.String, java.lang.String):void");
    }

    zzkz(zzjq zzjqVar) {
        this.zza = zzjqVar;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
        String str;
        try {
            this.zza.zzj().zzp().zza("onActivityCreated");
            Intent intent = activity.getIntent();
            if (intent == null) {
                return;
            }
            Uri data = intent.getData();
            if (data == null || !data.isHierarchical()) {
                Bundle extras = intent.getExtras();
                if (extras != null) {
                    String string = extras.getString("com.android.vending.referral_url");
                    if (!TextUtils.isEmpty(string)) {
                        data = Uri.parse(string);
                    }
                }
                data = null;
            }
            Uri uri = data;
            if (uri != null && uri.isHierarchical()) {
                this.zza.zzq();
                if (zzos.zza(intent)) {
                    str = "gs";
                } else {
                    str = DebugKt.DEBUG_PROPERTY_VALUE_AUTO;
                }
                this.zza.zzl().zzb(new zzlc(this, bundle == null, uri, str, uri.getQueryParameter("referrer")));
            }
        } catch (RuntimeException e) {
            this.zza.zzj().zzg().zza("Throwable caught in onActivityCreated", e);
        } finally {
            this.zza.zzn().zza(activity, bundle);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
        this.zza.zzn().zza(activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) {
        this.zza.zzn().zzb(activity);
        zznb zzp = this.zza.zzp();
        zzp.zzl().zzb(new zznd(zzp, zzp.zzb().elapsedRealtime()));
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity) {
        zznb zzp = this.zza.zzp();
        zzp.zzl().zzb(new zzne(zzp, zzp.zzb().elapsedRealtime()));
        this.zza.zzn().zzc(activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        this.zza.zzn().zzb(activity, bundle);
    }
}
