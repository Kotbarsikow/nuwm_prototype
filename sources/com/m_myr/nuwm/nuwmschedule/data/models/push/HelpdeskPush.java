package com.m_myr.nuwm.nuwmschedule.data.models.push;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import androidx.core.app.TaskStackBuilder;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.api.client.googleapis.media.MediaHttpDownloader;
import com.google.gson.annotations.SerializedName;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.tisket.TicketActivity;
import com.m_myr.nuwm.nuwmschedule.utils.Constant;

/* loaded from: classes2.dex */
public class HelpdeskPush extends BasePush {
    public static final transient int PUSH_HESK_NEW_REPLY_INT = 3;

    @SerializedName("from_me")
    boolean fromMe;

    @SerializedName("reply_id")
    int id;

    @SerializedName("message")
    String message;

    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
    String name;

    @SerializedName("priority")
    int priority;

    @SerializedName("ticket_id")
    public int ticketId;

    @SerializedName("trackid")
    public String trackid;

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.push.BasePush
    public int getType() {
        return 3;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.push.BasePush
    public Intent getIntent(Context context) {
        Intent intent = new Intent(context, (Class<?>) TicketActivity.class);
        intent.putExtra("ticket_id", this.ticketId);
        return intent;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.push.BasePush
    public boolean isHidden() {
        return this.fromMe;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.push.BasePush
    public Notification createNotification(Context context) {
        PendingIntent pendingIntent;
        Intent intent = new Intent(context, (Class<?>) TicketActivity.class);
        intent.putExtra("ticket_id", this.ticketId);
        intent.setFlags(268435456);
        TaskStackBuilder create = TaskStackBuilder.create(context);
        create.addNextIntentWithParentStack(intent);
        if (Build.VERSION.SDK_INT >= 31) {
            pendingIntent = create.getPendingIntent(1, MediaHttpDownloader.MAXIMUM_CHUNK_SIZE);
        } else {
            pendingIntent = create.getPendingIntent(1, 134217728);
        }
        return new NotificationCompat.Builder(context, getChanelId()).setSmallIcon(R.drawable.ic_helpdesk).setContentTitle(this.name).setSubText("Запит #" + this.trackid).setContentText(this.subtitle).setGroup(this.trackid).setColorized(true).setColor(context.getResources().getColor(R.color.colorPrimaryMaterialDark)).setPriority(0).setContentIntent(pendingIntent).setAutoCancel(true).build();
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.push.BasePush
    public int getId() {
        return this.id;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.push.BasePush
    public String getChanelId() {
        return Constant.CHANEL_ID_3;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.push.BasePush
    public void createNotificationChannel(Context context) {
        createNotificationChannel(context, R.string.channel_id3_name, R.string.channel_id3_description);
    }
}
