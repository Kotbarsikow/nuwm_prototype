package com.google.android.gms.measurement.internal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes.dex */
public final class zzaw {
    private static Set<String> zza(SQLiteDatabase sQLiteDatabase, String str) {
        HashSet hashSet = new HashSet();
        Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT * FROM " + str + " LIMIT 0", null);
        try {
            Collections.addAll(hashSet, rawQuery.getColumnNames());
            return hashSet;
        } finally {
            rawQuery.close();
        }
    }

    static void zza(zzgo zzgoVar, SQLiteDatabase sQLiteDatabase, String str, String str2, String str3, String[] strArr) throws SQLiteException {
        if (zzgoVar == null) {
            throw new IllegalArgumentException("Monitor must not be null");
        }
        if (!zza(zzgoVar, sQLiteDatabase, str)) {
            sQLiteDatabase.execSQL(str2);
        }
        try {
            if (zzgoVar == null) {
                throw new IllegalArgumentException("Monitor must not be null");
            }
            Set<String> zza = zza(sQLiteDatabase, str);
            for (String str4 : str3.split(",")) {
                if (!zza.remove(str4)) {
                    throw new SQLiteException("Table " + str + " is missing required column: " + str4);
                }
            }
            if (strArr != null) {
                for (int i = 0; i < strArr.length; i += 2) {
                    if (!zza.remove(strArr[i])) {
                        sQLiteDatabase.execSQL(strArr[i + 1]);
                    }
                }
            }
            if (zza.isEmpty()) {
                return;
            }
            zzgoVar.zzu().zza("Table has extra columns. table, columns", str, TextUtils.join(", ", zza));
        } catch (SQLiteException e) {
            zzgoVar.zzg().zza("Failed to verify columns on table that was just created", str);
            throw e;
        }
    }

    static void zza(zzgo zzgoVar, SQLiteDatabase sQLiteDatabase) {
        if (zzgoVar == null) {
            throw new IllegalArgumentException("Monitor must not be null");
        }
        File file = new File(com.google.android.gms.internal.measurement.zzcf.zza().zza(sQLiteDatabase.getPath()));
        if (!file.setReadable(false, false)) {
            zzgoVar.zzu().zza("Failed to turn off database read permission");
        }
        if (!file.setWritable(false, false)) {
            zzgoVar.zzu().zza("Failed to turn off database write permission");
        }
        if (!file.setReadable(true, true)) {
            zzgoVar.zzu().zza("Failed to turn on database read permission for owner");
        }
        if (file.setWritable(true, true)) {
            return;
        }
        zzgoVar.zzu().zza("Failed to turn on database write permission for owner");
    }

    private static boolean zza(zzgo zzgoVar, SQLiteDatabase sQLiteDatabase, String str) {
        if (zzgoVar == null) {
            throw new IllegalArgumentException("Monitor must not be null");
        }
        Cursor cursor = null;
        try {
            try {
                cursor = sQLiteDatabase.query("SQLITE_MASTER", new String[]{AppMeasurementSdk.ConditionalUserProperty.NAME}, "name=?", new String[]{str}, null, null, null);
                boolean moveToFirst = cursor.moveToFirst();
                if (cursor != null) {
                    cursor.close();
                }
                return moveToFirst;
            } catch (SQLiteException e) {
                zzgoVar.zzu().zza("Error querying for table", str, e);
                if (cursor == null) {
                    return false;
                }
                cursor.close();
                return false;
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }
}
