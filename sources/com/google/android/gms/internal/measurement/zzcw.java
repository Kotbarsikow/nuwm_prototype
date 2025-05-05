package com.google.android.gms.internal.measurement;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import com.google.api.client.googleapis.media.MediaHttpDownloader;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
/* loaded from: classes.dex */
public final class zzcw {
    public static final int zza;

    public static PendingIntent zza(Context context, int i, Intent intent, int i2) {
        return PendingIntent.getBroadcast(context, 0, intent, i2);
    }

    static {
        zza = Build.VERSION.SDK_INT >= 31 ? MediaHttpDownloader.MAXIMUM_CHUNK_SIZE : 0;
    }
}
