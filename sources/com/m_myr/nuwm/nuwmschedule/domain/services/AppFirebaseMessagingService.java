package com.m_myr.nuwm.nuwmschedule.domain.services;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import android.util.Log;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.models.push.BasePush;
import com.m_myr.nuwm.nuwmschedule.data.models.push.HelpdeskPush;
import com.m_myr.nuwm.nuwmschedule.data.models.push.LinkPush;
import com.m_myr.nuwm.nuwmschedule.data.models.push.NewMarksPush;
import com.m_myr.nuwm.nuwmschedule.data.models.push.PushFeedItems;
import com.m_myr.nuwm.nuwmschedule.data.models.push.SimplePush;
import com.m_myr.nuwm.nuwmschedule.data.models.push.UnsupportedPush;
import com.m_myr.nuwm.nuwmschedule.data.repositories.FastRepository;
import com.m_myr.nuwm.nuwmschedule.domain.App;
import com.m_myr.nuwm.nuwmschedule.network.api.APIMethods;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;

/* loaded from: classes2.dex */
public class AppFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "FBMService";

    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "onMessageReceived: " + new Gson().toJson(remoteMessage));
        if (remoteMessage.getData().size() > 0) {
            parseAction(Utils.StringUtils.safeToInt(remoteMessage.getData().get("type"), 0), Utils.StringUtils.safeToInt(remoteMessage.getData().get("priority"), 0), Utils.StringUtils.safeToInt(remoteMessage.getData().get(TypedValues.Attributes.S_TARGET), 0), remoteMessage.getData().get("body"));
        }
    }

    private void parseAction(int type, int priority, int targetApp, String data) {
        BasePush basePush;
        if (targetApp >= 1000000000) {
            int i = targetApp % 1000;
            int i2 = (targetApp / 10000) - 10000;
            if (112 > i || 112 <= i2) {
                return;
            }
        } else if (112 < targetApp) {
            return;
        }
        Log.d(TAG, "type: " + type + "\tdata: " + data);
        if (type == 0) {
            basePush = (BasePush) new Gson().fromJson(data, SimplePush.class);
        } else if (type == 2) {
            basePush = (BasePush) new Gson().fromJson(data, NewMarksPush.class);
        } else if (type == 3) {
            basePush = (BasePush) new Gson().fromJson(data, HelpdeskPush.class);
        } else if (type == 7) {
            basePush = (BasePush) new Gson().fromJson(data, LinkPush.class);
        } else if (type == 8) {
            basePush = (BasePush) new Gson().fromJson(data, PushFeedItems.class);
        } else {
            basePush = new UnsupportedPush();
        }
        NotificationManagerCompat from = NotificationManagerCompat.from(this);
        if (priority != -1 && !basePush.isHidden()) {
            if (Build.VERSION.SDK_INT >= 26) {
                basePush.createNotificationChannel(this);
            }
            from.notify(basePush.getId(), basePush.createNotification(this));
            if (type == 2) {
                from.notify(2, new NotificationCompat.Builder(this, basePush.getChanelId()).setContentTitle("Нові оцінки").setContentText("Нові оцінки").setSmallIcon(R.drawable.ic_nuwm_vector).setStyle(new NotificationCompat.InboxStyle().setSummaryText("нові оцінки")).setGroup("marks").setGroupSummary(true).build());
                return;
            }
            if (type != 3) {
                if (type == 7) {
                    from.notify(basePush.getId(), basePush.createNotification(this));
                    return;
                } else {
                    from.notify(basePush.getId(), basePush.createNotification(this));
                    return;
                }
            }
            HelpdeskPush helpdeskPush = (HelpdeskPush) basePush;
            Intent intent = new Intent("HelpdeskPush.MESSAGE_RECEIVE");
            intent.putExtra("push", helpdeskPush);
            intent.putExtra("ticket_id", helpdeskPush.ticketId);
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
            PendingIntent.getActivity(this, (int) SystemClock.uptimeMillis(), helpdeskPush.getIntent(this), 1073741824);
            from.notify(helpdeskPush.ticketId, new NotificationCompat.Builder(this, basePush.getChanelId()).setContentTitle(helpdeskPush.trackid).setSmallIcon(R.drawable.ic_helpdesk).setSubText("Запит #" + helpdeskPush.trackid).setColorized(true).setColor(getResources().getColor(R.color.colorPrimaryMaterialDark)).setAutoCancel(true).setGroup(helpdeskPush.trackid).setGroupSummary(true).build());
            return;
        }
        basePush.executeSilent(this);
    }

    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public void onNewToken(String s) {
        super.onNewToken(s);
        FastRepository.call(APIMethods.sendMessagingToken(s, App.getDeviceIdLegacy()).setTimeout(3000)).detach().start();
    }
}
