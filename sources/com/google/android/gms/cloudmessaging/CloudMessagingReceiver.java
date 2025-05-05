package com.google.android.gms.cloudmessaging;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.util.concurrent.NamedThreadFactory;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.messaging.Constants;
import j$.util.Objects;
import java.lang.ref.SoftReference;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-cloud-messaging@@17.2.0 */
/* loaded from: classes.dex */
public abstract class CloudMessagingReceiver extends BroadcastReceiver {
    private static SoftReference zza;
    private static SoftReference zzb;

    /* compiled from: com.google.android.gms:play-services-cloud-messaging@@17.2.0 */
    public static final class IntentActionKeys {
        public static final String NOTIFICATION_DISMISS = "com.google.firebase.messaging.NOTIFICATION_DISMISS";
        public static final String NOTIFICATION_OPEN = "com.google.firebase.messaging.NOTIFICATION_OPEN";

        private IntentActionKeys() {
        }
    }

    /* compiled from: com.google.android.gms:play-services-cloud-messaging@@17.2.0 */
    public static final class IntentKeys {
        public static final String PENDING_INTENT = "pending_intent";
        public static final String WRAPPED_INTENT = "wrapped_intent";

        private IntentKeys() {
        }
    }

    private final int zzb(Context context, Intent intent) {
        PendingIntent pendingIntent = (PendingIntent) intent.getParcelableExtra(IntentKeys.PENDING_INTENT);
        if (pendingIntent != null) {
            try {
                pendingIntent.send();
            } catch (PendingIntent.CanceledException unused) {
                Log.e("CloudMessagingReceiver", "Notification pending intent canceled");
            }
        }
        Bundle extras = intent.getExtras();
        if (extras != null) {
            extras.remove(IntentKeys.PENDING_INTENT);
        } else {
            extras = new Bundle();
        }
        if (Objects.equals(intent.getAction(), IntentActionKeys.NOTIFICATION_DISMISS)) {
            onNotificationDismissed(context, extras);
            return -1;
        }
        Log.e("CloudMessagingReceiver", "Unknown notification action");
        return 500;
    }

    protected Executor getBroadcastExecutor() {
        ExecutorService executorService;
        synchronized (CloudMessagingReceiver.class) {
            SoftReference softReference = zza;
            executorService = softReference != null ? (ExecutorService) softReference.get() : null;
            if (executorService == null) {
                com.google.android.gms.internal.cloudmessaging.zze.zza();
                executorService = Executors.unconfigurableExecutorService(Executors.newCachedThreadPool(new NamedThreadFactory("firebase-iid-executor")));
                zza = new SoftReference(executorService);
            }
        }
        return executorService;
    }

    protected abstract int onMessageReceive(Context context, CloudMessage cloudMessage);

    protected void onNotificationDismissed(Context context, Bundle bundle) {
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(final Context context, final Intent intent) {
        if (intent == null) {
            return;
        }
        final boolean isOrderedBroadcast = isOrderedBroadcast();
        final BroadcastReceiver.PendingResult goAsync = goAsync();
        getBroadcastExecutor().execute(new Runnable() { // from class: com.google.android.gms.cloudmessaging.zzh
            @Override // java.lang.Runnable
            public final void run() {
                CloudMessagingReceiver.this.zza(intent, context, isOrderedBroadcast, goAsync);
            }
        });
    }

    final /* synthetic */ void zza(Intent intent, final Context context, boolean z, BroadcastReceiver.PendingResult pendingResult) {
        Executor executor;
        int i;
        try {
            Parcelable parcelableExtra = intent.getParcelableExtra(IntentKeys.WRAPPED_INTENT);
            Intent intent2 = parcelableExtra instanceof Intent ? (Intent) parcelableExtra : null;
            if (intent2 != null) {
                i = zzb(context, intent2);
            } else if (intent.getExtras() == null) {
                i = 500;
            } else {
                final CloudMessage cloudMessage = new CloudMessage(intent);
                final CountDownLatch countDownLatch = new CountDownLatch(1);
                synchronized (CloudMessagingReceiver.class) {
                    SoftReference softReference = zzb;
                    executor = softReference != null ? (Executor) softReference.get() : null;
                    if (executor == null) {
                        com.google.android.gms.internal.cloudmessaging.zze.zza();
                        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new NamedThreadFactory("pscm-ack-executor"));
                        threadPoolExecutor.allowCoreThreadTimeOut(true);
                        executor = Executors.unconfigurableExecutorService(threadPoolExecutor);
                        zzb = new SoftReference(executor);
                    }
                }
                executor.execute(new Runnable() { // from class: com.google.android.gms.cloudmessaging.zzg
                    @Override // java.lang.Runnable
                    public final void run() {
                        Task zzc;
                        CloudMessage cloudMessage2 = cloudMessage;
                        if (TextUtils.isEmpty(cloudMessage2.getMessageId())) {
                            zzc = Tasks.forResult(null);
                        } else {
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.MessagePayloadKeys.MSGID, cloudMessage2.getMessageId());
                            Integer zza2 = cloudMessage2.zza();
                            if (zza2 != null) {
                                bundle.putInt(Constants.MessagePayloadKeys.PRODUCT_ID, zza2.intValue());
                            }
                            Context context2 = context;
                            bundle.putBoolean("supports_message_handled", true);
                            zzc = zzv.zzb(context2).zzc(2, bundle);
                        }
                        final CountDownLatch countDownLatch2 = countDownLatch;
                        zzc.addOnCompleteListener(new Executor() { // from class: com.google.android.gms.cloudmessaging.zze
                            @Override // java.util.concurrent.Executor
                            public final void execute(Runnable runnable) {
                                runnable.run();
                            }
                        }, new OnCompleteListener() { // from class: com.google.android.gms.cloudmessaging.zzf
                            @Override // com.google.android.gms.tasks.OnCompleteListener
                            public final void onComplete(Task task) {
                                countDownLatch2.countDown();
                            }
                        });
                    }
                });
                int onMessageReceive = onMessageReceive(context, cloudMessage);
                try {
                    if (!countDownLatch.await(TimeUnit.SECONDS.toMillis(1L), TimeUnit.MILLISECONDS)) {
                        Log.w("CloudMessagingReceiver", "Message ack timed out");
                    }
                } catch (InterruptedException e) {
                    Log.w("CloudMessagingReceiver", "Message ack failed: ".concat(e.toString()));
                }
                i = onMessageReceive;
            }
            if (z && pendingResult != null) {
                pendingResult.setResultCode(i);
            }
        } finally {
            if (pendingResult != null) {
                pendingResult.finish();
            }
        }
    }
}
