package com.m_myr.nuwm.nuwmschedule.data.models.push;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;
import com.google.gson.annotations.SerializedName;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.ui.activities.main.MainActivity;

/* loaded from: classes2.dex */
public class PushFeedItems extends SimplePush {
    public static final transient int PUSH_FEED_INT = 8;

    @SerializedName("topics")
    protected String topics;

    @SerializedName("uid")
    protected int uid;

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.push.SimplePush, com.m_myr.nuwm.nuwmschedule.data.models.push.BasePush
    public int getType() {
        return 8;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.push.SimplePush, com.m_myr.nuwm.nuwmschedule.data.models.push.BasePush
    public Intent getIntent(Context context) {
        Intent intent = new Intent(context, (Class<?>) MainActivity.class);
        intent.putExtra("uid", this.uid);
        intent.putExtra("topics", this.topics);
        intent.putExtra("action", "feed_push");
        intent.addFlags(805306368);
        return intent;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.push.SimplePush, com.m_myr.nuwm.nuwmschedule.data.models.push.BasePush
    public Notification createNotification(Context context) {
        return new NotificationCompat.Builder(context, getChanelId()).setSmallIcon(R.drawable.ic_nuwm_vector).setContentTitle(this.title).setContentText(this.subtitle).setStyle(new NotificationCompat.BigTextStyle().bigText(this.subtitle)).setPriority(0).setContentIntent(PendingIntent.getActivity(context, this.uid, getIntent(context), 1073741824)).setAutoCancel(true).build();
    }
}
