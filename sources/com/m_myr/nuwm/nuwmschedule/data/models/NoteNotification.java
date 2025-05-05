package com.m_myr.nuwm.nuwmschedule.data.models;

/* loaded from: classes2.dex */
public class NoteNotification {
    private long notificationId;
    private long time;

    public NoteNotification(long time, long notificationId) {
        this.time = time;
        this.notificationId = notificationId;
    }

    public NoteNotification() {
    }

    public long getTime() {
        return this.time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getNotificationId() {
        return this.notificationId;
    }

    public void setNotificationId(long notificationId) {
        this.notificationId = notificationId;
    }
}
