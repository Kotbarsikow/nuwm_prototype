package com.m_myr.nuwm.nuwmschedule.data.models.push;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.core.app.NotificationCompat;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public class UnsupportedPush extends SimplePush {
    @Override // com.m_myr.nuwm.nuwmschedule.data.models.push.SimplePush, com.m_myr.nuwm.nuwmschedule.data.models.push.BasePush
    public int getId() {
        return -1;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.push.SimplePush, com.m_myr.nuwm.nuwmschedule.data.models.push.BasePush
    public Notification createNotification(Context context) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.m_myr.nuwm.nuwmschedule"));
        intent.setFlags(268468224);
        return new NotificationCompat.Builder(context, getChanelId()).setSmallIcon(R.drawable.ic_menu).setContentTitle("Оновіть додаток").setContentText("Це сповіщення не підтримується вашою версією додатку. Необхідно оновитися").setStyle(new NotificationCompat.BigTextStyle().bigText("Це сповіщення не підтримується вашою версією додатку. Необхідно оновитися")).setPriority(0).setContentIntent(PendingIntent.getActivity(context, 0, intent, 0)).setAutoCancel(true).build();
    }
}
