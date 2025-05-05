package com.google.android.gms.internal.p000authapi;

import android.os.Build;
import com.google.api.client.googleapis.media.MediaHttpDownloader;

/* compiled from: com.google.android.gms:play-services-auth@@20.4.0 */
/* loaded from: classes.dex */
public final class zbbc {
    public static final int zba;

    static {
        zba = Build.VERSION.SDK_INT >= 31 ? MediaHttpDownloader.MAXIMUM_CHUNK_SIZE : 0;
    }
}
