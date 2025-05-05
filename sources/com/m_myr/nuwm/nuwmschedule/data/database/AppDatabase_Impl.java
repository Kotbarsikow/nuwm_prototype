package com.m_myr.nuwm.nuwmschedule.data.database;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.m_myr.nuwm.nuwmschedule.data.dao.NotesDao;
import com.m_myr.nuwm.nuwmschedule.data.dao.NotesDao_Impl;
import java.util.HashMap;
import java.util.HashSet;

/* loaded from: classes2.dex */
public final class AppDatabase_Impl extends AppDatabase {
    private volatile NotesDao _notesDao;

    @Override // androidx.room.RoomDatabase
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
        return configuration.sqliteOpenHelperFactory.create(SupportSQLiteOpenHelper.Configuration.builder(configuration.context).name(configuration.name).callback(new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) { // from class: com.m_myr.nuwm.nuwmschedule.data.database.AppDatabase_Impl.1
            @Override // androidx.room.RoomOpenHelper.Delegate
            public void createAllTables(SupportSQLiteDatabase _db) {
                _db.execSQL("CREATE TABLE IF NOT EXISTS `Note` (`id` TEXT NOT NULL, `title` TEXT, `bodyHtml` TEXT, `timeCreate` INTEGER NOT NULL, `timeEdit` INTEGER NOT NULL, `colorNote` INTEGER NOT NULL, `time` INTEGER, `notificationId` INTEGER, `room` TEXT, `streamConsists` TEXT, `subject` TEXT, `teacher` TEXT, `subgroup` TEXT, `streams` TEXT, `startDate` INTEGER, `endDate` INTEGER, `type` TEXT, `flags` INTEGER, `color` INTEGER, `numLesson` INTEGER, PRIMARY KEY(`id`))");
                _db.execSQL(RoomMasterTable.CREATE_QUERY);
                _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"39dd8d9e5afccfb02769c694f92f8588\")");
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void dropAllTables(SupportSQLiteDatabase _db) {
                _db.execSQL("DROP TABLE IF EXISTS `Note`");
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            protected void onCreate(SupportSQLiteDatabase _db) {
                if (AppDatabase_Impl.this.mCallbacks != null) {
                    int size = AppDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) AppDatabase_Impl.this.mCallbacks.get(i)).onCreate(_db);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onOpen(SupportSQLiteDatabase _db) {
                AppDatabase_Impl.this.mDatabase = _db;
                AppDatabase_Impl.this.internalInitInvalidationTracker(_db);
                if (AppDatabase_Impl.this.mCallbacks != null) {
                    int size = AppDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) AppDatabase_Impl.this.mCallbacks.get(i)).onOpen(_db);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            protected void validateMigration(SupportSQLiteDatabase _db) {
                HashMap hashMap = new HashMap(20);
                hashMap.put("id", new TableInfo.Column("id", "TEXT", true, 1));
                hashMap.put("title", new TableInfo.Column("title", "TEXT", false, 0));
                hashMap.put("bodyHtml", new TableInfo.Column("bodyHtml", "TEXT", false, 0));
                hashMap.put("timeCreate", new TableInfo.Column("timeCreate", "INTEGER", true, 0));
                hashMap.put("timeEdit", new TableInfo.Column("timeEdit", "INTEGER", true, 0));
                hashMap.put("colorNote", new TableInfo.Column("colorNote", "INTEGER", true, 0));
                hashMap.put("time", new TableInfo.Column("time", "INTEGER", false, 0));
                hashMap.put("notificationId", new TableInfo.Column("notificationId", "INTEGER", false, 0));
                hashMap.put("room", new TableInfo.Column("room", "TEXT", false, 0));
                hashMap.put("streamConsists", new TableInfo.Column("streamConsists", "TEXT", false, 0));
                hashMap.put("subject", new TableInfo.Column("subject", "TEXT", false, 0));
                hashMap.put("teacher", new TableInfo.Column("teacher", "TEXT", false, 0));
                hashMap.put("subgroup", new TableInfo.Column("subgroup", "TEXT", false, 0));
                hashMap.put("streams", new TableInfo.Column("streams", "TEXT", false, 0));
                hashMap.put("startDate", new TableInfo.Column("startDate", "INTEGER", false, 0));
                hashMap.put("endDate", new TableInfo.Column("endDate", "INTEGER", false, 0));
                hashMap.put("type", new TableInfo.Column("type", "TEXT", false, 0));
                hashMap.put("flags", new TableInfo.Column("flags", "INTEGER", false, 0));
                hashMap.put(TypedValues.Custom.S_COLOR, new TableInfo.Column(TypedValues.Custom.S_COLOR, "INTEGER", false, 0));
                hashMap.put("numLesson", new TableInfo.Column("numLesson", "INTEGER", false, 0));
                TableInfo tableInfo = new TableInfo("Note", hashMap, new HashSet(0), new HashSet(0));
                TableInfo read = TableInfo.read(_db, "Note");
                if (tableInfo.equals(read)) {
                    return;
                }
                throw new IllegalStateException("Migration didn't properly handle Note(com.m_myr.nuwm.nuwmschedule.data.models.Note).\n Expected:\n" + tableInfo + "\n Found:\n" + read);
            }
        }, "39dd8d9e5afccfb02769c694f92f8588", "a1a6e2e1063d08b77bbe71df37d37b4e")).build());
    }

    @Override // androidx.room.RoomDatabase
    protected InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, "Note");
    }

    @Override // androidx.room.RoomDatabase
    public void clearAllTables() {
        super.assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = super.getOpenHelper().getWritableDatabase();
        try {
            super.beginTransaction();
            writableDatabase.execSQL("DELETE FROM `Note`");
            super.setTransactionSuccessful();
        } finally {
            super.endTransaction();
            writableDatabase.query("PRAGMA wal_checkpoint(FULL)").close();
            if (!writableDatabase.inTransaction()) {
                writableDatabase.execSQL("VACUUM");
            }
        }
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.database.AppDatabase
    public NotesDao notesDao() {
        NotesDao notesDao;
        if (this._notesDao != null) {
            return this._notesDao;
        }
        synchronized (this) {
            if (this._notesDao == null) {
                this._notesDao = new NotesDao_Impl(this);
            }
            notesDao = this._notesDao;
        }
        return notesDao;
    }
}
