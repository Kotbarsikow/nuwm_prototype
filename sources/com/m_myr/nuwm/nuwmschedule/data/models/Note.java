package com.m_myr.nuwm.nuwmschedule.data.models;

import java.io.Serializable;
import java.util.UUID;

/* loaded from: classes2.dex */
public class Note implements Serializable {
    private String bodyHtml;
    private int colorNote;
    private String id;
    private Lesson lesson;
    private NoteNotification noteNotification;
    private long timeCreate;
    private long timeEdit;
    private String title;

    public Note() {
        this.colorNote = -1;
        this.id = UUID.randomUUID().toString().replace("-", "") + System.currentTimeMillis();
        long currentTimeMillis = System.currentTimeMillis();
        this.timeCreate = currentTimeMillis;
        this.timeEdit = currentTimeMillis;
    }

    public Note(String id) {
        this.id = id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBodyHtml(String bodyHtml) {
        this.bodyHtml = bodyHtml;
    }

    public void setTimeCreate(long timeCreate) {
        this.timeCreate = timeCreate;
    }

    public void setTimeEdit(long timeEdit) {
        this.timeEdit = timeEdit;
    }

    public void setColorNote(int color) {
        this.colorNote = color;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public String getId() {
        return this.id;
    }

    public String getTitle() {
        return this.bodyHtml == null ? "" : this.title;
    }

    public String getBodyHtml() {
        String str = this.bodyHtml;
        return str == null ? "" : str;
    }

    public long getTimeCreate() {
        return this.timeCreate;
    }

    public long getTimeEdit() {
        return this.timeEdit;
    }

    public int getColorNote() {
        return this.colorNote;
    }

    public Lesson getLesson() {
        return this.lesson;
    }

    public NoteNotification getNoteNotification() {
        return this.noteNotification;
    }

    public void setNoteNotification(NoteNotification noteNotification) {
        this.noteNotification = noteNotification;
    }

    public int hashCode() {
        return this.id.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof Note)) {
            return this.id.equals(((Note) obj).getId());
        }
        return false;
    }
}
