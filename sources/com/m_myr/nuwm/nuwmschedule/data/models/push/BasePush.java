package com.m_myr.nuwm.nuwmschedule.data.models.push;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: classes2.dex */
public abstract class BasePush implements Serializable {

    @SerializedName("subtitle")
    protected String subtitle;

    @SerializedName("title")
    protected String title;

    public abstract Notification createNotification(Context context);

    public abstract void createNotificationChannel(Context context);

    public void executeSilent(Context context) {
    }

    public abstract String getChanelId();

    public abstract int getId();

    public abstract Intent getIntent(Context context);

    public abstract int getType();

    public boolean isHidden() {
        return false;
    }

    protected void createNotificationChannel(Context context, int nameId, int descriptionId) {
        String string = context.getString(nameId);
        String string2 = context.getString(descriptionId);
        NotificationChannel notificationChannel = new NotificationChannel(getChanelId(), string, 3);
        notificationChannel.setDescription(string2);
        ((NotificationManager) context.getSystemService(NotificationManager.class)).createNotificationChannel(notificationChannel);
    }
}
