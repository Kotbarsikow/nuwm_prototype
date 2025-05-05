package com.google.android.gms.cloudmessaging;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Log;
import androidx.collection.SimpleArrayMap;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.messaging.Constants;
import com.google.zxing.client.android.Intents;
import j$.util.Objects;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: com.google.android.gms:play-services-cloud-messaging@@17.2.0 */
/* loaded from: classes.dex */
public class Rpc {
    private static int zza;
    private static PendingIntent zzb;
    private static final Executor zzc = new Executor() { // from class: com.google.android.gms.cloudmessaging.zzy
        @Override // java.util.concurrent.Executor
        public final void execute(Runnable runnable) {
            runnable.run();
        }
    };
    private static final Pattern zzd = Pattern.compile("\\|ID\\|([^|]+)\\|:?+(.*)");
    private final Context zzf;
    private final zzw zzg;
    private final ScheduledExecutorService zzh;
    private Messenger zzj;
    private zzd zzk;
    private final SimpleArrayMap zze = new SimpleArrayMap();
    private final Messenger zzi = new Messenger(new zzae(this, Looper.getMainLooper()));

    public Rpc(Context context) {
        this.zzf = context;
        this.zzg = new zzw(context);
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);
        scheduledThreadPoolExecutor.setKeepAliveTime(60L, TimeUnit.SECONDS);
        scheduledThreadPoolExecutor.allowCoreThreadTimeOut(true);
        this.zzh = scheduledThreadPoolExecutor;
    }

    static /* synthetic */ Task zza(Bundle bundle) throws Exception {
        return zzi(bundle) ? Tasks.forResult(null) : Tasks.forResult(bundle);
    }

    static /* bridge */ /* synthetic */ void zzc(Rpc rpc, Message message) {
        if (message == null || !(message.obj instanceof Intent)) {
            Log.w("Rpc", "Dropping invalid message");
            return;
        }
        Intent intent = (Intent) message.obj;
        intent.setExtrasClassLoader(new zzc());
        if (intent.hasExtra("google.messenger")) {
            Parcelable parcelableExtra = intent.getParcelableExtra("google.messenger");
            if (parcelableExtra instanceof zzd) {
                rpc.zzk = (zzd) parcelableExtra;
            }
            if (parcelableExtra instanceof Messenger) {
                rpc.zzj = (Messenger) parcelableExtra;
            }
        }
        Intent intent2 = (Intent) message.obj;
        String action = intent2.getAction();
        if (!Objects.equals(action, "com.google.android.c2dm.intent.REGISTRATION")) {
            if (Log.isLoggable("Rpc", 3)) {
                Log.d("Rpc", "Unexpected response action: ".concat(String.valueOf(action)));
                return;
            }
            return;
        }
        String stringExtra = intent2.getStringExtra("registration_id");
        if (stringExtra == null) {
            stringExtra = intent2.getStringExtra("unregistered");
        }
        if (stringExtra != null) {
            Matcher matcher = zzd.matcher(stringExtra);
            if (!matcher.matches()) {
                if (Log.isLoggable("Rpc", 3)) {
                    Log.d("Rpc", "Unexpected response string: ".concat(stringExtra));
                    return;
                }
                return;
            }
            String group = matcher.group(1);
            String group2 = matcher.group(2);
            if (group != null) {
                Bundle extras = intent2.getExtras();
                extras.putString("registration_id", group2);
                rpc.zzh(group, extras);
                return;
            }
            return;
        }
        String stringExtra2 = intent2.getStringExtra(Constants.IPC_BUNDLE_KEY_SEND_ERROR);
        if (stringExtra2 == null) {
            Log.w("Rpc", "Unexpected response, no error or registration id ".concat(String.valueOf(String.valueOf(intent2.getExtras()))));
            return;
        }
        if (Log.isLoggable("Rpc", 3)) {
            Log.d("Rpc", "Received InstanceID error ".concat(stringExtra2));
        }
        if (!stringExtra2.startsWith("|")) {
            synchronized (rpc.zze) {
                for (int i = 0; i < rpc.zze.size(); i++) {
                    rpc.zzh((String) rpc.zze.keyAt(i), intent2.getExtras());
                }
            }
            return;
        }
        String[] split = stringExtra2.split("\\|");
        if (split.length <= 2 || !Objects.equals(split[1], "ID")) {
            Log.w("Rpc", "Unexpected structured response ".concat(stringExtra2));
            return;
        }
        String str = split[2];
        String str2 = split[3];
        if (str2.startsWith(":")) {
            str2 = str2.substring(1);
        }
        rpc.zzh(str, intent2.putExtra(Constants.IPC_BUNDLE_KEY_SEND_ERROR, str2).getExtras());
    }

    private final Task zze(Bundle bundle) {
        final String zzf = zzf();
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        synchronized (this.zze) {
            this.zze.put(zzf, taskCompletionSource);
        }
        Intent intent = new Intent();
        intent.setPackage("com.google.android.gms");
        if (this.zzg.zzb() == 2) {
            intent.setAction("com.google.iid.TOKEN_REQUEST");
        } else {
            intent.setAction("com.google.android.c2dm.intent.REGISTER");
        }
        intent.putExtras(bundle);
        zzg(this.zzf, intent);
        intent.putExtra("kid", "|ID|" + zzf + "|");
        if (Log.isLoggable("Rpc", 3)) {
            Log.d("Rpc", "Sending ".concat(String.valueOf(String.valueOf(intent.getExtras()))));
        }
        intent.putExtra("google.messenger", this.zzi);
        if (this.zzj != null || this.zzk != null) {
            Message obtain = Message.obtain();
            obtain.obj = intent;
            try {
                Messenger messenger = this.zzj;
                if (messenger != null) {
                    messenger.send(obtain);
                } else {
                    this.zzk.zzb(obtain);
                }
            } catch (RemoteException unused) {
                if (Log.isLoggable("Rpc", 3)) {
                    Log.d("Rpc", "Messenger failed, fallback to startService");
                }
            }
            final ScheduledFuture<?> schedule = this.zzh.schedule(new Runnable() { // from class: com.google.android.gms.cloudmessaging.zzac
                @Override // java.lang.Runnable
                public final void run() {
                    if (TaskCompletionSource.this.trySetException(new IOException(Intents.Scan.TIMEOUT))) {
                        Log.w("Rpc", "No response");
                    }
                }
            }, 30L, TimeUnit.SECONDS);
            taskCompletionSource.getTask().addOnCompleteListener(zzc, new OnCompleteListener() { // from class: com.google.android.gms.cloudmessaging.zzad
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task) {
                    Rpc.this.zzd(zzf, schedule, task);
                }
            });
            return taskCompletionSource.getTask();
        }
        if (this.zzg.zzb() == 2) {
            this.zzf.sendBroadcast(intent);
        } else {
            this.zzf.startService(intent);
        }
        final ScheduledFuture schedule2 = this.zzh.schedule(new Runnable() { // from class: com.google.android.gms.cloudmessaging.zzac
            @Override // java.lang.Runnable
            public final void run() {
                if (TaskCompletionSource.this.trySetException(new IOException(Intents.Scan.TIMEOUT))) {
                    Log.w("Rpc", "No response");
                }
            }
        }, 30L, TimeUnit.SECONDS);
        taskCompletionSource.getTask().addOnCompleteListener(zzc, new OnCompleteListener() { // from class: com.google.android.gms.cloudmessaging.zzad
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                Rpc.this.zzd(zzf, schedule2, task);
            }
        });
        return taskCompletionSource.getTask();
    }

    private static synchronized String zzf() {
        String num;
        synchronized (Rpc.class) {
            int i = zza;
            zza = i + 1;
            num = Integer.toString(i);
        }
        return num;
    }

    private static synchronized void zzg(Context context, Intent intent) {
        synchronized (Rpc.class) {
            if (zzb == null) {
                Intent intent2 = new Intent();
                intent2.setPackage("com.google.example.invalidpackage");
                zzb = PendingIntent.getBroadcast(context, 0, intent2, com.google.android.gms.internal.cloudmessaging.zza.zza);
            }
            intent.putExtra("app", zzb);
        }
    }

    private final void zzh(String str, Bundle bundle) {
        synchronized (this.zze) {
            TaskCompletionSource taskCompletionSource = (TaskCompletionSource) this.zze.remove(str);
            if (taskCompletionSource != null) {
                taskCompletionSource.setResult(bundle);
                return;
            }
            Log.w("Rpc", "Missing callback for " + str);
        }
    }

    private static boolean zzi(Bundle bundle) {
        return bundle != null && bundle.containsKey("google.messenger");
    }

    public Task<CloudMessage> getProxiedNotificationData() {
        return this.zzg.zza() >= 241100000 ? zzv.zzb(this.zzf).zzd(5, Bundle.EMPTY).continueWith(zzc, new Continuation() { // from class: com.google.android.gms.cloudmessaging.zzab
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                Intent intent = (Intent) ((Bundle) task.getResult()).getParcelable("notification_data");
                if (intent != null) {
                    return new CloudMessage(intent);
                }
                return null;
            }
        }) : Tasks.forException(new IOException("SERVICE_NOT_AVAILABLE"));
    }

    public Task<Void> messageHandled(CloudMessage cloudMessage) {
        if (this.zzg.zza() < 233700000) {
            return Tasks.forException(new IOException("SERVICE_NOT_AVAILABLE"));
        }
        Bundle bundle = new Bundle();
        bundle.putString(Constants.MessagePayloadKeys.MSGID, cloudMessage.getMessageId());
        Integer zza2 = cloudMessage.zza();
        if (zza2 != null) {
            bundle.putInt(Constants.MessagePayloadKeys.PRODUCT_ID, zza2.intValue());
        }
        return zzv.zzb(this.zzf).zzc(3, bundle);
    }

    public Task<Bundle> send(final Bundle bundle) {
        return this.zzg.zza() < 12000000 ? this.zzg.zzb() != 0 ? zze(bundle).continueWithTask(zzc, new Continuation() { // from class: com.google.android.gms.cloudmessaging.zzz
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return Rpc.this.zzb(bundle, task);
            }
        }) : Tasks.forException(new IOException("MISSING_INSTANCEID_SERVICE")) : zzv.zzb(this.zzf).zzd(1, bundle).continueWith(zzc, new Continuation() { // from class: com.google.android.gms.cloudmessaging.zzaa
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                if (task.isSuccessful()) {
                    return (Bundle) task.getResult();
                }
                if (Log.isLoggable("Rpc", 3)) {
                    Log.d("Rpc", "Error making request: ".concat(String.valueOf(String.valueOf(task.getException()))));
                }
                throw new IOException("SERVICE_NOT_AVAILABLE", task.getException());
            }
        });
    }

    public Task<Void> setRetainProxiedNotifications(boolean z) {
        if (this.zzg.zza() < 241100000) {
            return Tasks.forException(new IOException("SERVICE_NOT_AVAILABLE"));
        }
        Bundle bundle = new Bundle();
        bundle.putBoolean("proxy_retention", z);
        return zzv.zzb(this.zzf).zzc(4, bundle);
    }

    final /* synthetic */ Task zzb(Bundle bundle, Task task) throws Exception {
        return (task.isSuccessful() && zzi((Bundle) task.getResult())) ? zze(bundle).onSuccessTask(zzc, new SuccessContinuation() { // from class: com.google.android.gms.cloudmessaging.zzx
            @Override // com.google.android.gms.tasks.SuccessContinuation
            public final Task then(Object obj) {
                return Rpc.zza((Bundle) obj);
            }
        }) : task;
    }

    final /* synthetic */ void zzd(String str, ScheduledFuture scheduledFuture, Task task) {
        synchronized (this.zze) {
            this.zze.remove(str);
        }
        scheduledFuture.cancel(false);
    }
}
