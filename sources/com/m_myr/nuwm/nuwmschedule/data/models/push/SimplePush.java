package com.m_myr.nuwm.nuwmschedule.data.models.push;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import com.google.api.client.googleapis.media.MediaHttpDownloader;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.ui.activities.main.MainActivity;
import com.m_myr.nuwm.nuwmschedule.utils.Constant;

/* loaded from: classes2.dex */
public class SimplePush extends BasePush {
    public static final transient int PUSH_SIMPLE_INT = 0;

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.push.BasePush
    public int getId() {
        return 0;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.push.BasePush
    public int getType() {
        return 0;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.push.BasePush
    public Intent getIntent(Context context) {
        Intent intent = new Intent(context, (Class<?>) MainActivity.class);
        intent.setFlags(268468224);
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
        return new NotificationCompat.Builder(context, getChanelId()).setSmallIcon(R.drawable.ic_nuwm_vector).setContentTitle(this.title).setContentText(this.subtitle).setStyle(new NotificationCompat.BigTextStyle().bigText(this.subtitle)).setPriority(0).setContentIntent(activity).setAutoCancel(true).build();
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.push.BasePush
    public String getChanelId() {
        return Constant.CHANEL_ID_1;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.push.BasePush
    public void createNotificationChannel(Context context) {
        createNotificationChannel(context, R.string.channel_id1_name, R.string.channel_id1_description);
    }
}
