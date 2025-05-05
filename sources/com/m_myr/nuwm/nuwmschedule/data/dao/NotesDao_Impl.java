package com.m_myr.nuwm.nuwmschedule.data.dao;

import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.m_myr.nuwm.nuwmschedule.data.models.Lesson;
import com.m_myr.nuwm.nuwmschedule.data.models.Note;
import com.m_myr.nuwm.nuwmschedule.data.models.NoteNotification;
import com.m_myr.nuwm.nuwmschedule.utils.RoomConverter;

/* loaded from: classes2.dex */
public final class NotesDao_Impl implements NotesDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter __deletionAdapterOfNote;
    private final EntityInsertionAdapter __insertionAdapterOfNote;
    private final RoomConverter __roomConverter = new RoomConverter();
    private final EntityDeletionOrUpdateAdapter __updateAdapterOfNote;

    public NotesDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfNote = new EntityInsertionAdapter<Note>(__db) { // from class: com.m_myr.nuwm.nuwmschedule.data.dao.NotesDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR ABORT INTO `Note`(`id`,`title`,`bodyHtml`,`timeCreate`,`timeEdit`,`colorNote`,`time`,`notificationId`,`room`,`streamConsists`,`subject`,`teacher`,`subgroup`,`streams`,`startDate`,`endDate`,`type`,`flags`,`color`,`numLesson`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, Note value) {
                if (value.getId() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getId());
                }
                if (value.getTitle() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getTitle());
                }
                if (value.getBodyHtml() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getBodyHtml());
                }
                stmt.bindLong(4, value.getTimeCreate());
                stmt.bindLong(5, value.getTimeEdit());
                stmt.bindLong(6, value.getColorNote());
                NoteNotification noteNotification = value.getNoteNotification();
                if (noteNotification != null) {
                    stmt.bindLong(7, noteNotification.getTime());
                    stmt.bindLong(8, noteNotification.getNotificationId());
                } else {
                    stmt.bindNull(7);
                    stmt.bindNull(8);
                }
                Lesson lesson = value.getLesson();
                if (lesson != null) {
                    if (lesson.getRoom() == null) {
                        stmt.bindNull(9);
                    } else {
                        stmt.bindString(9, lesson.getRoom());
                    }
                    if (lesson.getStreamConsists() == null) {
                        stmt.bindNull(10);
                    } else {
                        stmt.bindString(10, lesson.getStreamConsists());
                    }
                    if (lesson.getSubject() == null) {
                        stmt.bindNull(11);
                    } else {
                        stmt.bindString(11, lesson.getSubject());
                    }
                    if (lesson.getTeacher() == null) {
                        stmt.bindNull(12);
                    } else {
                        stmt.bindString(12, lesson.getTeacher());
                    }
                    if (lesson.getSubgroup() == null) {
                        stmt.bindNull(13);
                    } else {
                        stmt.bindString(13, lesson.getSubgroup());
                    }
                    if (lesson.getStreams() == null) {
                        stmt.bindNull(14);
                    } else {
                        stmt.bindString(14, lesson.getStreams());
                    }
                    Long dateToTimestamp = NotesDao_Impl.this.__roomConverter.dateToTimestamp(lesson.getStartDate());
                    if (dateToTimestamp == null) {
                        stmt.bindNull(15);
                    } else {
                        stmt.bindLong(15, dateToTimestamp.longValue());
                    }
                    Long dateToTimestamp2 = NotesDao_Impl.this.__roomConverter.dateToTimestamp(lesson.getEndDate());
                    if (dateToTimestamp2 == null) {
                        stmt.bindNull(16);
                    } else {
                        stmt.bindLong(16, dateToTimestamp2.longValue());
                    }
                    if (lesson.getType() == null) {
                        stmt.bindNull(17);
                    } else {
                        stmt.bindString(17, lesson.getType());
                    }
                    stmt.bindLong(18, lesson.getFlags());
                    stmt.bindLong(19, lesson.getColor());
                    stmt.bindLong(20, lesson.getNumLesson());
                    return;
                }
                stmt.bindNull(9);
                stmt.bindNull(10);
                stmt.bindNull(11);
                stmt.bindNull(12);
                stmt.bindNull(13);
                stmt.bindNull(14);
                stmt.bindNull(15);
                stmt.bindNull(16);
                stmt.bindNull(17);
                stmt.bindNull(18);
                stmt.bindNull(19);
                stmt.bindNull(20);
            }
        };
        this.__deletionAdapterOfNote = new EntityDeletionOrUpdateAdapter<Note>(__db) { // from class: com.m_myr.nuwm.nuwmschedule.data.dao.NotesDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `Note` WHERE `id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, Note value) {
                if (value.getId() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getId());
                }
            }
        };
        this.__updateAdapterOfNote = new EntityDeletionOrUpdateAdapter<Note>(__db) { // from class: com.m_myr.nuwm.nuwmschedule.data.dao.NotesDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `Note` SET `id` = ?,`title` = ?,`bodyHtml` = ?,`timeCreate` = ?,`timeEdit` = ?,`colorNote` = ?,`time` = ?,`notificationId` = ?,`room` = ?,`streamConsists` = ?,`subject` = ?,`teacher` = ?,`subgroup` = ?,`streams` = ?,`startDate` = ?,`endDate` = ?,`type` = ?,`flags` = ?,`color` = ?,`numLesson` = ? WHERE `id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, Note value) {
                if (value.getId() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getId());
                }
                if (value.getTitle() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getTitle());
                }
                if (value.getBodyHtml() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getBodyHtml());
                }
                stmt.bindLong(4, value.getTimeCreate());
                stmt.bindLong(5, value.getTimeEdit());
                stmt.bindLong(6, value.getColorNote());
                NoteNotification noteNotification = value.getNoteNotification();
                if (noteNotification != null) {
                    stmt.bindLong(7, noteNotification.getTime());
                    stmt.bindLong(8, noteNotification.getNotificationId());
                } else {
                    stmt.bindNull(7);
                    stmt.bindNull(8);
                }
                Lesson lesson = value.getLesson();
                if (lesson != null) {
                    if (lesson.getRoom() == null) {
                        stmt.bindNull(9);
                    } else {
                        stmt.bindString(9, lesson.getRoom());
                    }
                    if (lesson.getStreamConsists() == null) {
                        stmt.bindNull(10);
                    } else {
                        stmt.bindString(10, lesson.getStreamConsists());
                    }
                    if (lesson.getSubject() == null) {
                        stmt.bindNull(11);
                    } else {
                        stmt.bindString(11, lesson.getSubject());
                    }
                    if (lesson.getTeacher() == null) {
                        stmt.bindNull(12);
                    } else {
                        stmt.bindString(12, lesson.getTeacher());
                    }
                    if (lesson.getSubgroup() == null) {
                        stmt.bindNull(13);
                    } else {
                        stmt.bindString(13, lesson.getSubgroup());
                    }
                    if (lesson.getStreams() == null) {
                        stmt.bindNull(14);
                    } else {
                        stmt.bindString(14, lesson.getStreams());
                    }
                    Long dateToTimestamp = NotesDao_Impl.this.__roomConverter.dateToTimestamp(lesson.getStartDate());
                    if (dateToTimestamp == null) {
                        stmt.bindNull(15);
                    } else {
                        stmt.bindLong(15, dateToTimestamp.longValue());
                    }
                    Long dateToTimestamp2 = NotesDao_Impl.this.__roomConverter.dateToTimestamp(lesson.getEndDate());
                    if (dateToTimestamp2 == null) {
                        stmt.bindNull(16);
                    } else {
                        stmt.bindLong(16, dateToTimestamp2.longValue());
                    }
                    if (lesson.getType() == null) {
                        stmt.bindNull(17);
                    } else {
                        stmt.bindString(17, lesson.getType());
                    }
                    stmt.bindLong(18, lesson.getFlags());
                    stmt.bindLong(19, lesson.getColor());
                    stmt.bindLong(20, lesson.getNumLesson());
                } else {
                    stmt.bindNull(9);
                    stmt.bindNull(10);
                    stmt.bindNull(11);
                    stmt.bindNull(12);
                    stmt.bindNull(13);
                    stmt.bindNull(14);
                    stmt.bindNull(15);
                    stmt.bindNull(16);
                    stmt.bindNull(17);
                    stmt.bindNull(18);
                    stmt.bindNull(19);
                    stmt.bindNull(20);
                }
                if (value.getId() == null) {
                    stmt.bindNull(21);
                } else {
                    stmt.bindString(21, value.getId());
                }
            }
        };
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.dao.NotesDao
    public void insert(Note note) {
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfNote.insert((EntityInsertionAdapter) note);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.dao.NotesDao
    public void delete(Note note) {
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfNote.handle(note);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.dao.NotesDao
    public void update(Note note) {
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfNote.handle(note);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x0162  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0187 A[Catch: all -> 0x0222, TryCatch #0 {all -> 0x0222, blocks: (B:6:0x005f, B:7:0x00a0, B:9:0x00a6, B:11:0x00b2, B:15:0x00dd, B:17:0x00e3, B:19:0x00e9, B:21:0x00ef, B:23:0x00f5, B:25:0x00fb, B:27:0x0101, B:29:0x0107, B:31:0x010d, B:33:0x0113, B:35:0x011b, B:37:0x0123, B:40:0x0144, B:44:0x0178, B:47:0x0190, B:48:0x01b2, B:50:0x0187, B:51:0x016b, B:56:0x00c6), top: B:5:0x005f }] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x016b A[Catch: all -> 0x0222, TryCatch #0 {all -> 0x0222, blocks: (B:6:0x005f, B:7:0x00a0, B:9:0x00a6, B:11:0x00b2, B:15:0x00dd, B:17:0x00e3, B:19:0x00e9, B:21:0x00ef, B:23:0x00f5, B:25:0x00fb, B:27:0x0101, B:29:0x0107, B:31:0x010d, B:33:0x0113, B:35:0x011b, B:37:0x0123, B:40:0x0144, B:44:0x0178, B:47:0x0190, B:48:0x01b2, B:50:0x0187, B:51:0x016b, B:56:0x00c6), top: B:5:0x005f }] */
    @Override // com.m_myr.nuwm.nuwmschedule.data.dao.NotesDao
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.List<com.m_myr.nuwm.nuwmschedule.data.models.Note> getAll() {
        /*
            Method dump skipped, instructions count: 558
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.m_myr.nuwm.nuwmschedule.data.dao.NotesDao_Impl.getAll():java.util.List");
    }
}
