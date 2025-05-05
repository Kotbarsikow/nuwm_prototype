package com.m_myr.nuwm.nuwmschedule.data.models.push;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import com.google.api.client.googleapis.media.MediaHttpDownloader;
import com.google.gson.annotations.SerializedName;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.ui.activities.marks.MyMarksActivity;
import com.m_myr.nuwm.nuwmschedule.utils.Constant;

/* loaded from: classes2.dex */
public class NewMarksPush extends BasePush {
    public static final transient int PUSH_NEW_MARKS_INT = 2;

    @SerializedName("half")
    int half;

    @SerializedName("id")
    int id;

    @SerializedName("lesson_id")
    int lessonId;

    @SerializedName("subject_id")
    int subjectId;

    @SerializedName("subject_name")
    String subject_name;

    @SerializedName("time")
    long time;

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.push.BasePush
    public int getType() {
        return 2;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.push.BasePush
    public Intent getIntent(Context context) {
        Intent intent = new Intent(context, (Class<?>) MyMarksActivity.class);
        intent.setFlags(268435456);
        intent.putExtra("subject_id", this.subjectId);
        intent.putExtra("subject_name", this.subject_name);
        intent.putExtra("lesson_id", this.lessonId);
        intent.putExtra("half", this.half);
        return intent;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.push.BasePush
    public Notification createNotification(Context context) {
        PendingIntent activity;
        if (Build.VERSION.SDK_INT >= 31) {
            activity = PendingIntent.getActivity(context, 0, getIntent(context), MediaHttpDownloader.MAXIMUM_CHUNK_SIZE);
        } else {
            activity = PendingIntent.getActivity(context, 0, getIntent(context), 134217728);
        }
        return new NotificationCompat.Builder(context, getChanelId()).setSmallIcon(R.drawable.ic_nuwm_vector).setContentTitle(this.title).setContentText(this.subtitle).setGroup("marks").setShowWhen(true).setWhen(this.time).setStyle(new NotificationCompat.BigTextStyle().bigText(this.subtitle)).setPriority(2).setContentIntent(activity).setAutoCancel(true).build();
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.push.BasePush
    public int getId() {
        return this.id;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.push.BasePush
    public String getChanelId() {
        return Constant.CHANEL_ID_2;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.push.BasePush
    public void createNotificationChannel(Context context) {
        createNotificationChannel(context, R.string.channel_id2_name, R.string.channel_id2_description);
    }
}
