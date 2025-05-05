package com.m_myr.nuwm.nuwmschedule.ui.activities.googleAuth;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.api.services.calendar.CalendarScopes;
import com.m_myr.nuwm.nuwmschedule.BuildConfig;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.models.OfficeUserNuwm;
import com.m_myr.nuwm.nuwmschedule.data.repositories.AppDataManager;
import com.m_myr.nuwm.nuwmschedule.ui.abit.AbitCalcActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.main.MainActivity;
import com.m_myr.nuwm.nuwmschedule.utils.LinksResolver;
import java.util.Set;

/* loaded from: classes2.dex */
public class GoogleAuth extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, IGoogleAuthView {
    private int RC_SIGN_IN = 1000;
    private TextView mAppV;
    private GoogleSignInClient mSignInClient;
    PresenterGoogleAuth presenter;

    public static GoogleSignInOptions getGoogleSignInOptions() {
        return new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestServerAuthCode("575714876605-nls3jfaa56g7h7asbdsiija6fgd0hbtl.apps.googleusercontent.com", true).requestEmail().requestScopes(new Scope("email"), new Scope("profile"), new Scope(CalendarScopes.CALENDAR_READONLY), new Scope(CalendarScopes.CALENDAR), new Scope("https://www.googleapis.com/auth/calendar.events")).build();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_google);
        PresenterGoogleAuth presenterGoogleAuth = new PresenterGoogleAuth();
        this.presenter = presenterGoogleAuth;
        presenterGoogleAuth.attachView((IGoogleAuthView) this);
        this.mSignInClient = GoogleSignIn.getClient((Activity) this, getGoogleSignInOptions());
        findViewById(R.id.auth_sign_in_button).setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.googleAuth.GoogleAuth$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GoogleAuth.this.signIn(view);
            }
        });
        TextView textView = (TextView) findViewById(R.id.app_v);
        this.mAppV = textView;
        textView.setText(BuildConfig.VERSION_NAME);
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.googleAuth.IGoogleAuthView
    public void showOfficeAlert(OfficeUserNuwm officeUserNuwm, final View.OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Продовжити?");
        builder.setMessage(Html.fromHtml("Ви увійшли у акаунт від імені <b>" + officeUserNuwm.getWho() + ".</b> <br>Якщо ви працівник чи студент, увійдіть зі свого основного акаунту.<br> Продовжити вхід? Деякі функції додатку будуть <b>недоступні</b>"));
        builder.setPositiveButton("Продовжити", new DialogInterface.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.googleAuth.GoogleAuth$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                GoogleAuth.lambda$showOfficeAlert$0(onClickListener, dialogInterface, i);
            }
        });
        builder.setNegativeButton("Скасувати", new DialogInterface.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.googleAuth.GoogleAuth$$ExternalSyntheticLambda1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    static /* synthetic */ void lambda$showOfficeAlert$0(View.OnClickListener onClickListener, DialogInterface dialogInterface, int i) {
        onClickListener.onClick(null);
        dialogInterface.dismiss();
    }

    @Override // com.google.android.gms.common.api.internal.OnConnectionFailedListener
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if (connectionResult.hasResolution()) {
            try {
                connectionResult.startResolutionForResult(this, 10);
                return;
            } catch (IntentSender.SendIntentException unused) {
                Toast.makeText(this, "Unable to resolve, message user appropriately", 1).show();
                return;
            }
        }
        GooglePlayServicesUtil.getErrorDialog(connectionResult.getErrorCode(), this, 0).show();
    }

    public void signIn(View view) {
        this.presenter.signInClick();
    }

    public void onSignSkip(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Оберіть профіль який більше підходить для Вас");
        builder.setItems(new String[]{"Я абітурієнт", "Гість", "Дізнатися свою пошту"}, new DialogInterface.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.googleAuth.GoogleAuth.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int position) {
                if (position == 2) {
                    LinksResolver.startOnChrome(GoogleAuth.this, "https://app.nuwm.edu.ua/email/getMyEmail.php");
                } else if (position == 1) {
                    GoogleAuth.this.presenter.loginHowGuest();
                } else if (position == 0) {
                    GoogleAuth.this.presenter.loginHowAbit();
                }
            }
        });
        builder.show();
    }

    public void onSignCalc(View view) {
        this.presenter.loginHowAbit();
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.googleAuth.IGoogleAuthView
    public void navigateToAbitActivity() {
        finish();
        startActivity(new Intent(this, (Class<?>) AbitCalcActivity.class));
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.googleAuth.IGoogleAuthView
    public void startActivityLogin() {
        startActivityForResult(this.mSignInClient.getSignInIntent(), this.RC_SIGN_IN);
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.googleAuth.IGoogleAuthView
    public void showLoading(boolean visibility) {
        findViewById(R.id.progressBar).setVisibility(visibility ? 0 : 8);
        findViewById(R.id.button_panel).setVisibility(visibility ? 8 : 0);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == this.RC_SIGN_IN) {
            this.presenter.onResult(data);
        }
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.googleAuth.IGoogleAuthView
    public void showError(String status) {
        Toast.makeText(this, status, 1).show();
        try {
            this.mSignInClient.signOut();
        } catch (Exception unused) {
        }
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.googleAuth.IGoogleAuthView
    public void navigateToIntroActivity() {
        finish();
        startActivity(new Intent(this, (Class<?>) MainActivity.class));
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.googleAuth.IGoogleAuthView
    public void showAccountSelect(Set<String> accounts) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater from = LayoutInflater.from(this);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        linearLayout.setOrientation(1);
        for (String str : accounts) {
            AppDataManager instanceOf = AppDataManager.getInstanceOf(str);
            ViewGroup viewGroup = (ViewGroup) from.inflate(R.layout.student_list_item, (ViewGroup) linearLayout, false);
            ((TextView) viewGroup.findViewById(R.id.usertype)).setText(instanceOf.getNuwmUser().getEmail());
            ((TextView) viewGroup.findViewById(R.id.username)).setText(instanceOf.getNuwmUser().getFullName());
            Glide.with((FragmentActivity) this).load(instanceOf.getNuwmUser().getProfileImage()).apply((BaseRequestOptions<?>) RequestOptions.circleCropTransform()).into((ImageView) viewGroup.findViewById(R.id.profile_image));
            viewGroup.setTag(str);
            viewGroup.setOnClickListener(this.presenter);
            linearLayout.addView(viewGroup);
        }
        builder.setTitle("Оберіть акаунт");
        builder.setNegativeButton("Скасувати", (DialogInterface.OnClickListener) null);
        builder.setPositiveButton("Додати новий", this.presenter);
        builder.setView(linearLayout);
        builder.show();
    }
}
