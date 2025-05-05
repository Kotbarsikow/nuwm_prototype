package com.m_myr.nuwm.nuwmschedule.data.database;

import androidx.room.RoomDatabase;
import com.m_myr.nuwm.nuwmschedule.data.dao.NotesDao;

/* loaded from: classes2.dex */
public abstract class AppDatabase extends RoomDatabase {
    public abstract NotesDao notesDao();
}
