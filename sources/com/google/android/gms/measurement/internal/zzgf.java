package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteFullException;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import androidx.exifinterface.media.ExifInterface;
import com.google.android.gms.common.util.Clock;
import org.checkerframework.dataflow.qual.Pure;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes.dex */
public final class zzgf extends zzh {
    private final zzgi zza;
    private boolean zzb;

    @Override // com.google.android.gms.measurement.internal.zzh
    protected final boolean zzz() {
        return false;
    }

    private static long zza(SQLiteDatabase sQLiteDatabase) {
        Cursor cursor = null;
        try {
            cursor = sQLiteDatabase.query("messages", new String[]{"rowid"}, "type=?", new String[]{ExifInterface.GPS_MEASUREMENT_3D}, null, null, "rowid desc", "1");
            if (cursor.moveToFirst()) {
                return cursor.getLong(0);
            }
            if (cursor == null) {
                return -1L;
            }
            cursor.close();
            return -1L;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzja, com.google.android.gms.measurement.internal.zzjc
    @Pure
    public final /* bridge */ /* synthetic */ Context zza() {
        return super.zza();
    }

    private final SQLiteDatabase zzad() throws SQLiteException {
        if (this.zzb) {
            return null;
        }
        SQLiteDatabase writableDatabase = this.zza.getWritableDatabase();
        if (writableDatabase != null) {
            return writableDatabase;
        }
        this.zzb = true;
        return null;
    }

    @Override // com.google.android.gms.measurement.internal.zzja, com.google.android.gms.measurement.internal.zzjc
    @Pure
    public final /* bridge */ /* synthetic */ Clock zzb() {
        return super.zzb();
    }

    @Override // com.google.android.gms.measurement.internal.zze
    public final /* bridge */ /* synthetic */ zzb zzc() {
        return super.zzc();
    }

    @Override // com.google.android.gms.measurement.internal.zzja, com.google.android.gms.measurement.internal.zzjc
    @Pure
    public final /* bridge */ /* synthetic */ zzab zzd() {
        return super.zzd();
    }

    @Override // com.google.android.gms.measurement.internal.zzja
    @Pure
    public final /* bridge */ /* synthetic */ zzag zze() {
        return super.zze();
    }

    @Override // com.google.android.gms.measurement.internal.zzja
    @Pure
    public final /* bridge */ /* synthetic */ zzaz zzf() {
        return super.zzf();
    }

    @Override // com.google.android.gms.measurement.internal.zze
    public final /* bridge */ /* synthetic */ zzgg zzg() {
        return super.zzg();
    }

    @Override // com.google.android.gms.measurement.internal.zze
    public final /* bridge */ /* synthetic */ zzgf zzh() {
        return super.zzh();
    }

    @Override // com.google.android.gms.measurement.internal.zzja
    @Pure
    public final /* bridge */ /* synthetic */ zzgh zzi() {
        return super.zzi();
    }

    @Override // com.google.android.gms.measurement.internal.zzja, com.google.android.gms.measurement.internal.zzjc
    @Pure
    public final /* bridge */ /* synthetic */ zzgo zzj() {
        return super.zzj();
    }

    @Override // com.google.android.gms.measurement.internal.zzja
    @Pure
    public final /* bridge */ /* synthetic */ zzha zzk() {
        return super.zzk();
    }

    @Override // com.google.android.gms.measurement.internal.zzja, com.google.android.gms.measurement.internal.zzjc
    @Pure
    public final /* bridge */ /* synthetic */ zzhv zzl() {
        return super.zzl();
    }

    @Override // com.google.android.gms.measurement.internal.zze
    public final /* bridge */ /* synthetic */ zzjq zzm() {
        return super.zzm();
    }

    @Override // com.google.android.gms.measurement.internal.zze
    public final /* bridge */ /* synthetic */ zzlj zzn() {
        return super.zzn();
    }

    @Override // com.google.android.gms.measurement.internal.zze
    public final /* bridge */ /* synthetic */ zzls zzo() {
        return super.zzo();
    }

    @Override // com.google.android.gms.measurement.internal.zze
    public final /* bridge */ /* synthetic */ zznb zzp() {
        return super.zzp();
    }

    @Override // com.google.android.gms.measurement.internal.zzja
    @Pure
    public final /* bridge */ /* synthetic */ zzos zzq() {
        return super.zzq();
    }

    /* JADX WARN: Removed duplicated region for block: B:149:0x01ce  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x01d3  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x01f1 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x01e9  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x01ee  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x01f1 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x01bb  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x01c0  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x01f1 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x01a3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x01fb  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0200  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.util.List<com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable> zza(int r22) {
        /*
            Method dump skipped, instructions count: 530
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzgf.zza(int):java.util.List");
    }

    zzgf(zzhy zzhyVar) {
        super(zzhyVar);
        this.zza = new zzgi(this, zza(), "google_app_measurement_local.db");
    }

    @Override // com.google.android.gms.measurement.internal.zze, com.google.android.gms.measurement.internal.zzja
    public final /* bridge */ /* synthetic */ void zzr() {
        super.zzr();
    }

    @Override // com.google.android.gms.measurement.internal.zze, com.google.android.gms.measurement.internal.zzja
    public final /* bridge */ /* synthetic */ void zzs() {
        super.zzs();
    }

    @Override // com.google.android.gms.measurement.internal.zze, com.google.android.gms.measurement.internal.zzja
    public final /* bridge */ /* synthetic */ void zzt() {
        super.zzt();
    }

    public final void zzaa() {
        int delete;
        zzt();
        try {
            SQLiteDatabase zzad = zzad();
            if (zzad == null || (delete = zzad.delete("messages", null, null)) <= 0) {
                return;
            }
            zzj().zzp().zza("Reset local analytics data. records", Integer.valueOf(delete));
        } catch (SQLiteException e) {
            zzj().zzg().zza("Error resetting local analytics data. error", e);
        }
    }

    public final boolean zzab() {
        return zza(3, new byte[0]);
    }

    private final boolean zzae() {
        return zza().getDatabasePath("google_app_measurement_local.db").exists();
    }

    public final boolean zzac() {
        zzt();
        if (this.zzb || !zzae()) {
            return false;
        }
        int i = 5;
        for (int i2 = 0; i2 < 5; i2++) {
            SQLiteDatabase sQLiteDatabase = null;
            try {
                SQLiteDatabase zzad = zzad();
                if (zzad == null) {
                    this.zzb = true;
                    if (zzad != null) {
                        zzad.close();
                    }
                    return false;
                }
                zzad.beginTransaction();
                zzad.delete("messages", "type == ?", new String[]{Integer.toString(3)});
                zzad.setTransactionSuccessful();
                zzad.endTransaction();
                if (zzad != null) {
                    zzad.close();
                }
                return true;
            } catch (SQLiteDatabaseLockedException unused) {
                SystemClock.sleep(i);
                i += 20;
                if (0 != 0) {
                    sQLiteDatabase.close();
                }
            } catch (SQLiteFullException e) {
                zzj().zzg().zza("Error deleting app launch break from local database", e);
                this.zzb = true;
                if (0 != 0) {
                    sQLiteDatabase.close();
                }
            } catch (SQLiteException e2) {
                if (0 != 0) {
                    try {
                        if (sQLiteDatabase.inTransaction()) {
                            sQLiteDatabase.endTransaction();
                        }
                    } catch (Throwable th) {
                        if (0 != 0) {
                            sQLiteDatabase.close();
                        }
                        throw th;
                    }
                }
                zzj().zzg().zza("Error deleting app launch break from local database", e2);
                this.zzb = true;
                if (0 != 0) {
                    sQLiteDatabase.close();
                }
            }
        }
        zzj().zzu().zza("Error deleting app launch break from local database in reasonable time");
        return false;
    }

    public final boolean zza(zzae zzaeVar) {
        zzq();
        byte[] zza = zzos.zza((Parcelable) zzaeVar);
        if (zza.length > 131072) {
            zzj().zzn().zza("Conditional user property too long for local database. Sending directly to service");
            return false;
        }
        return zza(2, zza);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:17:0x005f A[Catch: all -> 0x004b, SQLiteException -> 0x004e, SQLiteFullException -> 0x0050, SQLiteDatabaseLockedException -> 0x00b2, TRY_ENTER, TryCatch #7 {SQLiteDatabaseLockedException -> 0x00b2, SQLiteFullException -> 0x0050, SQLiteException -> 0x004e, all -> 0x004b, blocks: (B:76:0x0040, B:78:0x0046, B:17:0x005f, B:19:0x0083, B:20:0x009d), top: B:75:0x0040 }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0120  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0125  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:74:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r10v0 */
    /* JADX WARN: Type inference failed for: r10v1 */
    /* JADX WARN: Type inference failed for: r10v2, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r10v4 */
    /* JADX WARN: Type inference failed for: r10v5 */
    /* JADX WARN: Type inference failed for: r10v6, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r10v7 */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r2v11 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final boolean zza(int r17, byte[] r18) {
        /*
            Method dump skipped, instructions count: 312
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzgf.zza(int, byte[]):boolean");
    }

    public final boolean zza(zzbf zzbfVar) {
        Parcel obtain = Parcel.obtain();
        zzbfVar.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        if (marshall.length > 131072) {
            zzj().zzn().zza("Event is too long for local database. Sending event directly to service");
            return false;
        }
        return zza(0, marshall);
    }

    public final boolean zza(zzon zzonVar) {
        Parcel obtain = Parcel.obtain();
        zzonVar.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        if (marshall.length > 131072) {
            zzj().zzn().zza("User property too long for local database. Sending directly to service");
            return false;
        }
        return zza(1, marshall);
    }
}
