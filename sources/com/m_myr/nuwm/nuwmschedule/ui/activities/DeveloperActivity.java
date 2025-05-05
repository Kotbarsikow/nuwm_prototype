package com.m_myr.nuwm.nuwmschedule.ui.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.m_myr.nuwm.nuwmschedule.BuildConfig;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.models.UserNuwm;
import com.m_myr.nuwm.nuwmschedule.data.repositories.AppDataManager;
import com.m_myr.nuwm.nuwmschedule.data.repositories.FastRepository;
import com.m_myr.nuwm.nuwmschedule.domain.App;
import com.m_myr.nuwm.nuwmschedule.network.ErrorResponse;
import com.m_myr.nuwm.nuwmschedule.network.api.APIMethods;
import com.m_myr.nuwm.nuwmschedule.network.api.APIObjectListener;
import com.m_myr.nuwm.nuwmschedule.network.models.ProfileResponse;
import com.m_myr.nuwm.nuwmschedule.ui.activities.DeveloperActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.googleAuth.GoogleAuth;
import com.m_myr.nuwm.nuwmschedule.ui.activities.main.MainActivity;
import com.m_myr.nuwm.nuwmschedule.utils.LinksResolver;
import java.util.Collection;
import java.util.Date;

/* loaded from: classes2.dex */
public class DeveloperActivity extends AppCompatActivity {
    private LinearLayout mTextList;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.debug_activity);
        this.mTextList = (LinearLayout) findViewById(R.id.textList);
        Gson create = new GsonBuilder().setPrettyPrinting().create();
        findViewById(R.id.changeToken).setOnClickListener(new AnonymousClass1());
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.DeveloperActivity.2
            AnonymousClass2() {
            }

            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<String> task) {
                if (task.isSuccessful()) {
                    DeveloperActivity.this.mTextList.addView(DeveloperActivity.this.setText("FirebaseInstance Token", task.getResult()));
                    DeveloperActivity.this.mTextList.addView(DeveloperActivity.this.setText("DeviceId", App.getDeviceIdLegacy()));
                }
            }
        });
        this.mTextList.addView(setText("ApplicationId", BuildConfig.APPLICATION_ID));
        this.mTextList.addView(setText("Build_type", "release"));
        this.mTextList.addView(setText("Flavor", BuildConfig.FLAVOR));
        this.mTextList.addView(setText("Version code", 112));
        this.mTextList.addView(setText("Version name", BuildConfig.VERSION_NAME));
        this.mTextList.addView(setText("Accounts", App.getInstance().getAccounts()));
        this.mTextList.addView(setText("Current Account", App.getInstance().getCurrentAccount()));
        this.mTextList.addView(setText("Auth Type", AppDataManager.getInstance().getAuthType()));
        this.mTextList.addView(setTime("Last Update", AppDataManager.getInstance().getLastUpdate()));
        this.mTextList.addView(setText("API Token", AppDataManager.getInstance().getToken()));
        this.mTextList.addView(setText("Subscribe topics", AppDataManager.getInstance().getTopics()));
        this.mTextList.addView(setText("User.Who", AppDataManager.getInstance().getUser().getWho()));
        this.mTextList.addView(setText("User.Id", AppDataManager.getInstance().getUser().getId()));
        this.mTextList.addView(setText("User.ProfileImage", AppDataManager.getInstance().getUser().getProfileImage()));
        this.mTextList.addView(setText("User.FirstName", AppDataManager.getInstance().getUser().getFirstName()));
        this.mTextList.addView(setText("User.LastName", AppDataManager.getInstance().getUser().getLastName()));
        this.mTextList.addView(setText("User.Patronymic", AppDataManager.getInstance().getUser().getPatronymic()));
        this.mTextList.addView(setText("User.Permission.Service", AppDataManager.getInstance().getUser().getPermission().getService()));
        this.mTextList.addView(setText("User.Permission.Profile", AppDataManager.getInstance().getUser().getPermission().isCabinet()));
        this.mTextList.addView(setText("User.Permission.PushGeneral", AppDataManager.getInstance().getUser().getPermission().isPushGeneral()));
        this.mTextList.addView(setText("User.Permission.PushTeacher", AppDataManager.getInstance().getUser().getPermission().isPushTeacher()));
        this.mTextList.addView(setText("User.Permission.Vote", AppDataManager.getInstance().getUser().getPermission().isVote()));
        this.mTextList.addView(setText("User.Permission.IDCard", AppDataManager.getInstance().getUser().getPermission().isIdCard()));
        this.mTextList.addView(setText("User.Permission.Schedule", AppDataManager.getInstance().getUser().getPermission().isSchedule()));
        this.mTextList.addView(setText("User", create.toJson(AppDataManager.getInstance().getUser())));
        this.mTextList.addView(setText("AppDataManager", create.toJson(AppDataManager.getInstance())));
    }

    /* renamed from: com.m_myr.nuwm.nuwmschedule.ui.activities.DeveloperActivity$1 */
    class AnonymousClass1 implements View.OnClickListener {
        AnonymousClass1() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(DeveloperActivity.this);
            builder.setTitle("Title");
            EditText editText = new EditText(DeveloperActivity.this);
            builder.setView(editText);
            builder.setPositiveButton("Застосувати", new DialogInterface.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.DeveloperActivity$1$$ExternalSyntheticLambda0
                public final /* synthetic */ EditText f$1;

                public /* synthetic */ DeveloperActivity$1$$ExternalSyntheticLambda0(EditText editText2) {
                    r2 = editText2;
                }

                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    DeveloperActivity.AnonymousClass1.this.m162x98a45f45(r2, dialogInterface, i);
                }
            });
            builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.DeveloperActivity$1$$ExternalSyntheticLambda1
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            builder.show();
        }

        /* renamed from: lambda$onClick$0$com-m_myr-nuwm-nuwmschedule-ui-activities-DeveloperActivity$1 */
        /* synthetic */ void m162x98a45f45(EditText editText, DialogInterface dialogInterface, int i) {
            DeveloperActivity.this.updateAccount(editText.getText().toString());
            dialogInterface.dismiss();
        }
    }

    /* renamed from: com.m_myr.nuwm.nuwmschedule.ui.activities.DeveloperActivity$2 */
    class AnonymousClass2 implements OnCompleteListener<String> {
        AnonymousClass2() {
        }

        @Override // com.google.android.gms.tasks.OnCompleteListener
        public void onComplete(Task<String> task) {
            if (task.isSuccessful()) {
                DeveloperActivity.this.mTextList.addView(DeveloperActivity.this.setText("FirebaseInstance Token", task.getResult()));
                DeveloperActivity.this.mTextList.addView(DeveloperActivity.this.setText("DeviceId", App.getDeviceIdLegacy()));
            }
        }
    }

    private View setTime(String title, long info) {
        return setText(title, new Date(info).toString() + "    [unix time]");
    }

    private View setText(String title, Collection<? extends Object> info) {
        if (info == null) {
            return setText(title, "@Collection empty");
        }
        String str = "";
        for (Object obj : info) {
            str = str + obj.toString() + " [<i>" + obj.getClass().getSimpleName() + "</i>]<br>";
        }
        return setText(title, Html.fromHtml(str));
    }

    private View setText(String title, int info) {
        return setText(title, info + "    [integer]");
    }

    private View setText(String title, boolean info) {
        return setText(title, info + "    [boolean]");
    }

    private View setText(String title, float info) {
        return setText(title, info + "    [float]");
    }

    public View setText(String title, String info) {
        return setText(title, new SpannableString(info));
    }

    private View setText(String title, Spanned info) {
        if (title == null) {
            title = "@null";
        }
        String str = info != null ? title : "@null";
        View inflate = getLayoutInflater().inflate(R.layout.info_text_and_subtext, (ViewGroup) this.mTextList, false);
        ((TextView) inflate.findViewById(R.id.text)).setText(str);
        ((TextView) inflate.findViewById(R.id.info)).setText(info);
        inflate.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.DeveloperActivity.3
            AnonymousClass3() {
            }

            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View v) {
                Vibrator vibrator = (Vibrator) DeveloperActivity.this.getSystemService("vibrator");
                if (Build.VERSION.SDK_INT >= 26) {
                    vibrator.vibrate(VibrationEffect.createOneShot(20L, 5));
                } else {
                    vibrator.vibrate(20L);
                }
                LinksResolver.copyToClipboard(((TextView) v.findViewById(R.id.info)).getText().toString());
                return false;
            }
        });
        return inflate;
    }

    /* renamed from: com.m_myr.nuwm.nuwmschedule.ui.activities.DeveloperActivity$3 */
    class AnonymousClass3 implements View.OnLongClickListener {
        AnonymousClass3() {
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View v) {
            Vibrator vibrator = (Vibrator) DeveloperActivity.this.getSystemService("vibrator");
            if (Build.VERSION.SDK_INT >= 26) {
                vibrator.vibrate(VibrationEffect.createOneShot(20L, 5));
            } else {
                vibrator.vibrate(20L);
            }
            LinksResolver.copyToClipboard(((TextView) v.findViewById(R.id.info)).getText().toString());
            return false;
        }
    }

    public void updateAccount(String newToken) {
        AppDataManager.getInstance().updateToken(newToken).apply();
        FastRepository.from(this).call(APIMethods.getProfileJson()).into((APIObjectListener) new APIObjectListener<ProfileResponse>() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.DeveloperActivity.4
            AnonymousClass4() {
            }

            @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
            public void onError(ErrorResponse response) {
                Toast.makeText(DeveloperActivity.this, "Зараз неможливо виконати цю дію", 0).show();
            }

            @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
            public void onSuccessData(ProfileResponse data) {
                AppDataManager appDataManager = AppDataManager.getInstance();
                if (data.getTypeInt() == appDataManager.getAuthType()) {
                    appDataManager.updateUser(UserNuwm.createChildByName(data.getType(), data.getProfile())).apply();
                    Toast.makeText(DeveloperActivity.this, "Оновлено токен. Необхідно перезапустити додаток", 0).show();
                    Intent intent = new Intent(DeveloperActivity.this, (Class<?>) MainActivity.class);
                    intent.addFlags(268435456);
                    DeveloperActivity.this.startActivity(intent);
                    DeveloperActivity.this.finishAffinity();
                    return;
                }
                Toast.makeText(App.getInstance(), "Дані акаунту було змінено. Необхідно виконати вхід знову", 1).show();
                App.getInstance().removeCurrentAccount();
                Intent intent2 = new Intent(DeveloperActivity.this, (Class<?>) GoogleAuth.class);
                intent2.addFlags(268435456);
                DeveloperActivity.this.startActivity(intent2);
                DeveloperActivity.this.finish();
            }
        }).start();
    }

    /* renamed from: com.m_myr.nuwm.nuwmschedule.ui.activities.DeveloperActivity$4 */
    class AnonymousClass4 extends APIObjectListener<ProfileResponse> {
        AnonymousClass4() {
        }

        @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
        public void onError(ErrorResponse response) {
            Toast.makeText(DeveloperActivity.this, "Зараз неможливо виконати цю дію", 0).show();
        }

        @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
        public void onSuccessData(ProfileResponse data) {
            AppDataManager appDataManager = AppDataManager.getInstance();
            if (data.getTypeInt() == appDataManager.getAuthType()) {
                appDataManager.updateUser(UserNuwm.createChildByName(data.getType(), data.getProfile())).apply();
                Toast.makeText(DeveloperActivity.this, "Оновлено токен. Необхідно перезапустити додаток", 0).show();
                Intent intent = new Intent(DeveloperActivity.this, (Class<?>) MainActivity.class);
                intent.addFlags(268435456);
                DeveloperActivity.this.startActivity(intent);
                DeveloperActivity.this.finishAffinity();
                return;
            }
            Toast.makeText(App.getInstance(), "Дані акаунту було змінено. Необхідно виконати вхід знову", 1).show();
            App.getInstance().removeCurrentAccount();
            Intent intent2 = new Intent(DeveloperActivity.this, (Class<?>) GoogleAuth.class);
            intent2.addFlags(268435456);
            DeveloperActivity.this.startActivity(intent2);
            DeveloperActivity.this.finish();
        }
    }
}
