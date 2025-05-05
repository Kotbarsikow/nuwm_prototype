package com.google.android.gms.internal.measurement;

import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.RemoteException;
import android.os.StrictMode;
import android.util.Log;
import androidx.collection.ArrayMap;
import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes.dex */
public final class zzgu implements zzhb {
    private static final Map<Uri, zzgu> zza = new ArrayMap();
    private static final String[] zzb = {"key", "value"};
    private final ContentResolver zzc;
    private final Uri zzd;
    private final Runnable zze;
    private final ContentObserver zzf;
    private final Object zzg;
    private volatile Map<String, String> zzh;
    private final List<zzgz> zzi;

    public static zzgu zza(ContentResolver contentResolver, Uri uri, Runnable runnable) {
        zzgu zzguVar;
        synchronized (zzgu.class) {
            Map<Uri, zzgu> map = zza;
            zzguVar = map.get(uri);
            if (zzguVar == null) {
                try {
                    zzgu zzguVar2 = new zzgu(contentResolver, uri, runnable);
                    try {
                        map.put(uri, zzguVar2);
                    } catch (SecurityException unused) {
                    }
                    zzguVar = zzguVar2;
                } catch (SecurityException unused2) {
                }
            }
        }
        return zzguVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzhb
    public final /* synthetic */ Object zza(String str) {
        return zza().get(str);
    }

    public final Map<String, String> zza() {
        Map<String, String> map = this.zzh;
        if (map == null) {
            synchronized (this.zzg) {
                map = this.zzh;
                if (map == null) {
                    map = zze();
                    this.zzh = map;
                }
            }
        }
        return map != null ? map : Collections.emptyMap();
    }

    final /* synthetic */ Map zzb() {
        Map hashMap;
        ContentProviderClient acquireUnstableContentProviderClient = this.zzc.acquireUnstableContentProviderClient(this.zzd);
        try {
            if (acquireUnstableContentProviderClient == null) {
                Log.w("ConfigurationContentLdr", "Unable to acquire ContentProviderClient, using default values");
                return Collections.emptyMap();
            }
            Cursor query = acquireUnstableContentProviderClient.query(this.zzd, zzb, null, null, null);
            try {
                if (query == null) {
                    Log.w("ConfigurationContentLdr", "ContentProvider query returned null cursor, using default values");
                    Map emptyMap = Collections.emptyMap();
                    if (query != null) {
                        query.close();
                    }
                    return emptyMap;
                }
                int count = query.getCount();
                if (count == 0) {
                    Map emptyMap2 = Collections.emptyMap();
                    if (query != null) {
                        query.close();
                    }
                    return emptyMap2;
                }
                if (count <= 256) {
                    hashMap = new ArrayMap(count);
                } else {
                    hashMap = new HashMap(count, 1.0f);
                }
                while (query.moveToNext()) {
                    hashMap.put(query.getString(0), query.getString(1));
                }
                if (query.isAfterLast()) {
                    if (query != null) {
                        query.close();
                    }
                    return hashMap;
                }
                Log.w("ConfigurationContentLdr", "Cursor read incomplete (ContentProvider dead?), using default values");
                Map emptyMap3 = Collections.emptyMap();
                if (query != null) {
                    query.close();
                }
                return emptyMap3;
            } catch (Throwable th) {
                if (query != null) {
                    try {
                        query.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        } catch (RemoteException e) {
            Log.w("ConfigurationContentLdr", "ContentProvider query failed, using default values", e);
            return Collections.emptyMap();
        } finally {
            acquireUnstableContentProviderClient.release();
        }
    }

    private final Map<String, String> zze() {
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            try {
                return (Map) zzha.zza(new zzhd() { // from class: com.google.android.gms.internal.measurement.zzgx
                    @Override // com.google.android.gms.internal.measurement.zzhd
                    public final Object zza() {
                        return zzgu.this.zzb();
                    }
                });
            } catch (SQLiteException | IllegalStateException | SecurityException e) {
                Log.w("ConfigurationContentLdr", "Unable to query ContentProvider, using default values", e);
                return Collections.emptyMap();
            }
        } finally {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
        }
    }

    private zzgu(ContentResolver contentResolver, Uri uri, Runnable runnable) {
        zzgw zzgwVar = new zzgw(this, null);
        this.zzf = zzgwVar;
        this.zzg = new Object();
        this.zzi = new ArrayList();
        Preconditions.checkNotNull(contentResolver);
        Preconditions.checkNotNull(uri);
        this.zzc = contentResolver;
        this.zzd = uri;
        this.zze = runnable;
        contentResolver.registerContentObserver(uri, false, zzgwVar);
    }

    static synchronized void zzc() {
        synchronized (zzgu.class) {
            for (zzgu zzguVar : zza.values()) {
                zzguVar.zzc.unregisterContentObserver(zzguVar.zzf);
            }
            zza.clear();
        }
    }

    public final void zzd() {
        synchronized (this.zzg) {
            this.zzh = null;
            this.zze.run();
        }
        synchronized (this) {
            Iterator<zzgz> it = this.zzi.iterator();
            while (it.hasNext()) {
                it.next().zza();
            }
        }
    }
}
