package com.google.android.gms.internal.base;

import android.os.Build;
import com.google.api.client.googleapis.media.MediaHttpDownloader;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
/* loaded from: classes.dex */
public final class zap {
    public static final int zaa;

    static {
        zaa = Build.VERSION.SDK_INT >= 31 ? MediaHttpDownloader.MAXIMUM_CHUNK_SIZE : 0;
    }
}
