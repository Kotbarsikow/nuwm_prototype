package com.google.android.play.core.appupdate;

import android.content.Context;
import java.io.File;

/* compiled from: com.google.android.play:app-update@@2.1.0 */
/* loaded from: classes2.dex */
final class zzt {
    private final Context zza;

    zzt(Context context) {
        this.zza = context;
    }

    private static long zzb(File file) {
        if (!file.isDirectory()) {
            return file.length();
        }
        File[] listFiles = file.listFiles();
        long j = 0;
        if (listFiles != null) {
            for (File file2 : listFiles) {
                j += zzb(file2);
            }
        }
        return j;
    }

    final long zza() {
        return zzb(new File(this.zza.getFilesDir(), "assetpacks"));
    }
}
