package com.m_myr.nuwm.nuwmschedule.ui.activities.signout;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.installations.FirebaseInstallations;
import com.google.firebase.messaging.FirebaseMessaging;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.domain.App;
import com.m_myr.nuwm.nuwmschedule.ui.activities.base.BaseStateActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.googleAuth.GoogleAuth;
import com.m_myr.nuwm.nuwmschedule.ui.activities.signout.SignoutActivity;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;

/* loaded from: classes2.dex */
public class SignoutActivity extends BaseStateActivity implements GoogleApiClient.OnConnectionFailedListener {
    boolean change;
    GoogleApiClient mApiClient;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_push_empty);
        setTitle("Виходимо...");
        showLoading();
        new Thread(new Runnable() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.signout.SignoutActivity$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                FirebaseMessaging.getInstance().deleteToken();
            }
        }).start();
        this.change = getIntent().getBooleanExtra("change", false);
        this.mApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this).addApi(Auth.GOOGLE_SIGN_IN_API, GoogleAuth.getGoogleSignInOptions()).build();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        this.mApiClient.connect();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPostResume() {
        super.onPostResume();
        this.mApiClient.registerConnectionCallbacks(new AnonymousClass1());
    }

    /* renamed from: com.m_myr.nuwm.nuwmschedule.ui.activities.signout.SignoutActivity$1 */
    class AnonymousClass1 implements GoogleApiClient.ConnectionCallbacks {
        AnonymousClass1() {
        }

        @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
        public void onConnected(Bundle bundle) {
            if (SignoutActivity.this.mApiClient.isConnected()) {
                Auth.GoogleSignInApi.signOut(SignoutActivity.this.mApiClient).setResultCallback(new ResultCallback() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.signout.SignoutActivity$1$$ExternalSyntheticLambda0
                    public /* synthetic */ SignoutActivity$1$$ExternalSyntheticLambda0() {
                    }

                    @Override // com.google.android.gms.common.api.ResultCallback
                    public final void onResult(Result result) {
                        SignoutActivity.AnonymousClass1.this.m196x51a7b451((Status) result);
                    }
                });
            }
        }

        /* renamed from: lambda$onConnected$0$com-m_myr-nuwm-nuwmschedule-ui-activities-signout-SignoutActivity$1 */
        /* synthetic */ void m196x51a7b451(Status status) {
            if (status.isSuccess()) {
                Log.d("onResult", "User Logged out");
                FirebaseInstallations.getInstance().delete().addOnCompleteListener(new OnCompleteListener<Void>() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.signout.SignoutActivity.1.1
                    C00421() {
                    }

                    @Override // com.google.android.gms.tasks.OnCompleteListener
                    public void onComplete(Task<Void> task) {
                        if (task.isComplete()) {
                            SignoutActivity.this.finish();
                        } else {
                            Toast.makeText(SignoutActivity.this, "Схоже щось пішло не так", 0).show();
                            SignoutActivity.this.finish();
                        }
                    }
                });
            }
        }

        /* renamed from: com.m_myr.nuwm.nuwmschedule.ui.activities.signout.SignoutActivity$1$1 */
        class C00421 implements OnCompleteListener<Void> {
            C00421() {
            }

            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<Void> task) {
                if (task.isComplete()) {
                    SignoutActivity.this.finish();
                } else {
                    Toast.makeText(SignoutActivity.this, "Схоже щось пішло не так", 0).show();
                    SignoutActivity.this.finish();
                }
            }
        }

        @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
        public void onConnectionSuspended(int i) {
            Log.d("onConnectionSuspended", "Google API Client Connection Suspended");
        }
    }

    @Override // com.google.android.gms.common.api.internal.OnConnectionFailedListener
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if (connectionResult.hasResolution()) {
            try {
                connectionResult.startResolutionForResult(this, 10);
            } catch (IntentSender.SendIntentException unused) {
            }
        } else {
            GooglePlayServicesUtil.getErrorDialog(connectionResult.getErrorCode(), this, 0).show();
        }
    }

    @Override // android.app.Activity
    public void finish() {
        Utils.sendAnalytic(this, "logout", Pair.create("action", this.change ? "change" : "exit"));
        Intent intent = new Intent(getApplicationContext(), (Class<?>) GoogleAuth.class);
        intent.putExtra("change", this.change);
        if (this.change) {
            App.getInstance().setCurrentAccount(null);
        } else {
            App.getInstance().removeCurrentAccount();
            FirebaseAnalytics.getInstance(this).setUserId(null);
            new Thread(new Runnable() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.signout.SignoutActivity$$ExternalSyntheticLambda0
                public /* synthetic */ SignoutActivity$$ExternalSyntheticLambda0() {
                }

                @Override // java.lang.Runnable
                public final void run() {
                    SignoutActivity.this.m195xb8a8f888();
                }
            }).start();
        }
        Glide.get(this).clearMemory();
        startActivity(intent);
        super.finish();
    }

    /* renamed from: lambda$finish$1$com-m_myr-nuwm-nuwmschedule-ui-activities-signout-SignoutActivity */
    /* synthetic */ void m195xb8a8f888() {
        try {
            App.getInstance().getDatabase().clearAllTables();
        } catch (Exception unused) {
        }
        Glide.get(getApplicationContext()).clearDiskCache();
    }

    public static void showExitAlertWarning(Activity context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Ви впевнені?");
        builder.setMessage(Html.fromHtml("Усі локальні дані (закладки, нотатки, нагадування тощо) будуть <b>видалені назавжди.</b> <br><br>Ви <b>можете зберегти дані</b> для цього оберіть \"Змінити акаунт\", після цього можна в будь-який момент повернутися до поточного акаунту."));
        builder.setPositiveButton("Змінити акаунт", new DialogInterface.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.signout.SignoutActivity$$ExternalSyntheticLambda2
            public final /* synthetic */ Activity f$0;

            public /* synthetic */ SignoutActivity$$ExternalSyntheticLambda2(Activity context2) {
                r1 = context2;
            }

            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                SignoutActivity.change(r1);
            }
        });
        builder.setNegativeButton("Вийти", new DialogInterface.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.signout.SignoutActivity$$ExternalSyntheticLambda3
            public final /* synthetic */ Activity f$0;

            public /* synthetic */ SignoutActivity$$ExternalSyntheticLambda3(Activity context2) {
                r1 = context2;
            }

            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                SignoutActivity.exit(r1);
            }
        });
        builder.show();
    }

    public static void change(Activity context) {
        Intent intent = new Intent(context, (Class<?>) SignoutActivity.class);
        intent.addFlags(268468224);
        context.finish();
        intent.putExtra("change", true);
        context.startActivity(intent);
    }

    public static void exit(Activity context) {
        Intent intent = new Intent(context, (Class<?>) SignoutActivity.class);
        intent.addFlags(268468224);
        intent.putExtra("change", false);
        context.finish();
        context.startActivity(intent);
    }
}
