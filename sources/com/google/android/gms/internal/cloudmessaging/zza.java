package com.google.android.gms.internal.cloudmessaging;

import android.os.Build;
import com.google.api.client.googleapis.media.MediaHttpDownloader;

/* compiled from: com.google.android.gms:play-services-cloud-messaging@@17.2.0 */
/* loaded from: classes.dex */
public final class zza {
    public static final int zza;

    static {
        zza = Build.VERSION.SDK_INT >= 31 ? MediaHttpDownloader.MAXIMUM_CHUNK_SIZE : 0;
    }
}
