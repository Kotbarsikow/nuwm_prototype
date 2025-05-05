package com.m_myr.nuwm.nuwmschedule.ui.activities.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.InstallState;
import com.google.android.play.core.install.InstallStateUpdatedListener;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.models.AppUserInfoUpdateData;
import com.m_myr.nuwm.nuwmschedule.data.repositories.AppDataManager;
import com.m_myr.nuwm.nuwmschedule.data.repositories.FastRepository;
import com.m_myr.nuwm.nuwmschedule.domain.App;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.TimetableActivityContract;
import com.m_myr.nuwm.nuwmschedule.network.ErrorResponse;
import com.m_myr.nuwm.nuwmschedule.network.api.APIMethods;
import com.m_myr.nuwm.nuwmschedule.network.api.APIObjectListener;
import com.m_myr.nuwm.nuwmschedule.ui.activities.NotificationIntroActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.googleAuth.GoogleAuth;
import com.m_myr.nuwm.nuwmschedule.ui.activities.signout.SignoutActivity;
import com.m_myr.nuwm.nuwmschedule.ui.framents.feed.FeedFragment;
import com.m_myr.nuwm.nuwmschedule.ui.framents.main.MainMenuFragment;
import com.m_myr.nuwm.nuwmschedule.ui.framents.profile.employee.EmployeeProfileFragment;
import com.m_myr.nuwm.nuwmschedule.ui.framents.profile.student.StudentProfileFragment;
import com.m_myr.nuwm.nuwmschedule.ui.framents.profile.teacher.TeacherProfileFragment;
import com.m_myr.nuwm.nuwmschedule.ui.framents.search.SearchFragment;
import com.m_myr.nuwm.nuwmschedule.ui.timetable.TimetableDelegate;
import com.m_myr.nuwm.nuwmschedule.ui.timetable.TimetablePresenter;
import com.m_myr.nuwm.nuwmschedule.ui.view.AppCompatActivityOverrideMenu;
import com.m_myr.nuwm.nuwmschedule.utils.Constant;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;
import com.theartofdev.edmodo.cropper.CropImage;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MainActivity.kt */
@Metadata(d1 = {"\u0000\u0094\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J\u0006\u0010:\u001a\u00020;J\b\u0010<\u001a\u00020;H\u0002J\u0012\u0010=\u001a\u0004\u0018\u0001032\u0006\u0010>\u001a\u00020?H\u0002J\u0010\u0010@\u001a\u00020A2\u0006\u0010>\u001a\u00020?H\u0002J\"\u0010B\u001a\u00020;2\u0006\u0010C\u001a\u00020\u00072\u0006\u0010D\u001a\u00020\u00072\b\u0010E\u001a\u0004\u0018\u00010?H\u0016J\u0010\u0010F\u001a\u00020;2\u0006\u0010G\u001a\u00020\u001fH\u0016J\b\u0010H\u001a\u00020;H\u0016J\u000e\u0010I\u001a\u00020;2\u0006\u0010J\u001a\u00020KJ\u000e\u0010L\u001a\u00020;2\u0006\u0010J\u001a\u00020KJ\u0012\u0010M\u001a\u00020;2\b\u0010N\u001a\u0004\u0018\u00010OH\u0014J\u0010\u0010P\u001a\u00020A2\u0006\u0010Q\u001a\u00020RH\u0016J\u0010\u0010S\u001a\u00020;2\u0006\u0010>\u001a\u00020?H\u0014J\u0010\u0010T\u001a\u00020A2\u0006\u0010U\u001a\u00020KH\u0016J\b\u0010V\u001a\u00020;H\u0016J\u0010\u0010W\u001a\u00020;2\u0006\u0010N\u001a\u00020OH\u0016J\u0010\u0010X\u001a\u00020;2\u0006\u0010N\u001a\u00020OH\u0016J\b\u0010Y\u001a\u00020;H\u0002J\b\u0010Z\u001a\u00020;H\u0014J\u0010\u0010[\u001a\u00020;2\u0006\u0010\\\u001a\u00020]H\u0016J\u0010\u0010^\u001a\u00020;2\b\u0010J\u001a\u0004\u0018\u00010KJ\b\u0010_\u001a\u00020;H\u0003J\u0006\u0010`\u001a\u00020;J\b\u0010a\u001a\u00020;H\u0002J\b\u0010b\u001a\u00020;H\u0002J\b\u0010c\u001a\u00020;H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0011\"\u0004\b\u0016\u0010\u0013R\u001a\u0010\u0017\u001a\u00020\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR$\u0010\u001d\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001f0\u001eX\u0086\u000e¢\u0006\u0010\n\u0002\u0010$\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001c\u0010%\u001a\u0004\u0018\u00010&X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u001a\u0010+\u001a\u00020,X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u0014\u00101\u001a\b\u0012\u0004\u0012\u00020302X\u0082.¢\u0006\u0002\n\u0000R\u001c\u00104\u001a\u0004\u0018\u000105X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u00107\"\u0004\b8\u00109¨\u0006d"}, d2 = {"Lcom/m_myr/nuwm/nuwmschedule/ui/activities/main/MainActivity;", "Lcom/m_myr/nuwm/nuwmschedule/ui/view/AppCompatActivityOverrideMenu;", "Lcom/google/android/material/bottomnavigation/BottomNavigationView$OnNavigationItemSelectedListener;", "Lcom/m_myr/nuwm/nuwmschedule/domain/interfaces/TimetableActivityContract;", "Lcom/google/android/play/core/install/InstallStateUpdatedListener;", "()V", "UPDATE_INSTALL_REQUEST_CODE_FLEXIBLE", "", "UPDATE_INSTALL_REQUEST_CODE_IMMEDIATE", "appUpdateManager", "Lcom/google/android/play/core/appupdate/AppUpdateManager;", "getAppUpdateManager", "()Lcom/google/android/play/core/appupdate/AppUpdateManager;", "setAppUpdateManager", "(Lcom/google/android/play/core/appupdate/AppUpdateManager;)V", "availabilityVerCode", "getAvailabilityVerCode", "()I", "setAvailabilityVerCode", "(I)V", "currentFragment", "getCurrentFragment", "setCurrentFragment", "fm", "Landroidx/fragment/app/FragmentManager;", "getFm", "()Landroidx/fragment/app/FragmentManager;", "setFm", "(Landroidx/fragment/app/FragmentManager;)V", "fragments", "", "Landroidx/fragment/app/Fragment;", "getFragments", "()[Landroidx/fragment/app/Fragment;", "setFragments", "([Landroidx/fragment/app/Fragment;)V", "[Landroidx/fragment/app/Fragment;", "idCardDelegate", "Lcom/m_myr/nuwm/nuwmschedule/ui/activities/main/IdCardDelegate;", "getIdCardDelegate", "()Lcom/m_myr/nuwm/nuwmschedule/ui/activities/main/IdCardDelegate;", "setIdCardDelegate", "(Lcom/m_myr/nuwm/nuwmschedule/ui/activities/main/IdCardDelegate;)V", "navView", "Lcom/google/android/material/bottomnavigation/BottomNavigationView;", "getNavView", "()Lcom/google/android/material/bottomnavigation/BottomNavigationView;", "setNavView", "(Lcom/google/android/material/bottomnavigation/BottomNavigationView;)V", "requestPermissionLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "", "timetableDelegate", "Lcom/m_myr/nuwm/nuwmschedule/ui/timetable/TimetableDelegate;", "getTimetableDelegate", "()Lcom/m_myr/nuwm/nuwmschedule/ui/timetable/TimetableDelegate;", "setTimetableDelegate", "(Lcom/m_myr/nuwm/nuwmschedule/ui/timetable/TimetableDelegate;)V", "appUpdateCheck", "", "checkUnread", "getAction", "intent", "Landroid/content/Intent;", "hasAction", "", "onActivityResult", "requestCode", "resultCode", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "onAttachFragment", "fragment", "onBackPressed", "onClickMenu", "view", "Landroid/view/View;", "onClickProfile", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onNavigationItemSelected", "menuItem", "Landroid/view/MenuItem;", "onNewIntent", "onOptionsItemSelected", "v", "onRefresh", "onRestoreInstanceState", "onSaveInstanceState", "onShowSnackbarUpdate", "onStart", "onStateUpdate", "state", "Lcom/google/android/play/core/install/InstallState;", "onStub", "openNotificationSettingsForApp", "postCreate", "setUpProfileIcon", "showChangeLog", "showMenu", "app_publicReleaseRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class MainActivity extends AppCompatActivityOverrideMenu implements BottomNavigationView.OnNavigationItemSelectedListener, TimetableActivityContract, InstallStateUpdatedListener {
    private final int UPDATE_INSTALL_REQUEST_CODE_FLEXIBLE;
    private final int UPDATE_INSTALL_REQUEST_CODE_IMMEDIATE;
    private AppUpdateManager appUpdateManager;
    private int availabilityVerCode;
    private int currentFragment;
    private FragmentManager fm;
    private Fragment[] fragments = new Fragment[4];
    private IdCardDelegate idCardDelegate;
    public BottomNavigationView navView;
    private ActivityResultLauncher<String> requestPermissionLauncher;
    private TimetableDelegate timetableDelegate;

    private final void showMenu() {
    }

    public MainActivity() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "getSupportFragmentManager(...)");
        this.fm = supportFragmentManager;
        this.currentFragment = -1;
        this.UPDATE_INSTALL_REQUEST_CODE_IMMEDIATE = 1777;
        this.UPDATE_INSTALL_REQUEST_CODE_FLEXIBLE = 1778;
    }

    public final TimetableDelegate getTimetableDelegate() {
        return this.timetableDelegate;
    }

    public final void setTimetableDelegate(TimetableDelegate timetableDelegate) {
        this.timetableDelegate = timetableDelegate;
    }

    public final Fragment[] getFragments() {
        return this.fragments;
    }

    public final void setFragments(Fragment[] fragmentArr) {
        Intrinsics.checkNotNullParameter(fragmentArr, "<set-?>");
        this.fragments = fragmentArr;
    }

    public final BottomNavigationView getNavView() {
        BottomNavigationView bottomNavigationView = this.navView;
        if (bottomNavigationView != null) {
            return bottomNavigationView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("navView");
        return null;
    }

    public final void setNavView(BottomNavigationView bottomNavigationView) {
        Intrinsics.checkNotNullParameter(bottomNavigationView, "<set-?>");
        this.navView = bottomNavigationView;
    }

    public final FragmentManager getFm() {
        return this.fm;
    }

    public final void setFm(FragmentManager fragmentManager) {
        Intrinsics.checkNotNullParameter(fragmentManager, "<set-?>");
        this.fm = fragmentManager;
    }

    public final int getCurrentFragment() {
        return this.currentFragment;
    }

    public final void setCurrentFragment(int i) {
        this.currentFragment = i;
    }

    public final IdCardDelegate getIdCardDelegate() {
        return this.idCardDelegate;
    }

    public final void setIdCardDelegate(IdCardDelegate idCardDelegate) {
        this.idCardDelegate = idCardDelegate;
    }

    public final AppUpdateManager getAppUpdateManager() {
        return this.appUpdateManager;
    }

    public final void setAppUpdateManager(AppUpdateManager appUpdateManager) {
        this.appUpdateManager = appUpdateManager;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.view.AppCompatActivityOverrideMenu, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(13);
        setExitSharedElementCallback(new MaterialContainerTransformSharedElementCallback());
        getWindow().setSharedElementsUseOverlay(false);
        setTheme(App.getInstance().getAppTheme());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View findViewById = findViewById(R.id.nav_view);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        setNavView((BottomNavigationView) findViewById);
        if (App.getInstance().getCurrentIsAbit()) {
            startActivity(new Intent(this, (Class<?>) GoogleAuth.class));
            finish();
            return;
        }
        if (App.getInstance().getCurrentAccount() == null) {
            startActivity(new Intent(this, (Class<?>) GoogleAuth.class));
            finish();
            return;
        }
        MainActivity mainActivity = this;
        FirebaseAnalytics.getInstance(mainActivity).setUserId(String.valueOf(AppDataManager.getInstance().getUser().getId()));
        FirebaseAnalytics.getInstance(mainActivity).setUserProperty("user_type", Constant.getNameOfTypeUser(AppDataManager.getInstance().getAuthType()));
        if (AppDataManager.getInstance().getUserPermission().isIdCard()) {
            createMenu(R.id.idCard);
        }
        if (!AppDataManager.getInstance().getUserPermission().isCabinet()) {
            getNavView().getMenu().findItem(R.id.navigation_profile).setVisible(false);
        }
        this.timetableDelegate = new TimetableDelegate(this, findViewById(R.id.container));
        new TimetablePresenter(this.timetableDelegate, AppDataManager.getInstance().getTimetableIdentifier()).attachView(this.timetableDelegate);
        getNavView().setOnNavigationItemSelectedListener(this);
        getNavView().postDelayed(new Runnable() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.main.MainActivity$$ExternalSyntheticLambda3
            public /* synthetic */ MainActivity$$ExternalSyntheticLambda3() {
            }

            @Override // java.lang.Runnable
            public final void run() {
                MainActivity.onCreate$lambda$0(MainActivity.this);
            }
        }, 400L);
        setUpProfileIcon();
        Intent intent = getIntent();
        Intrinsics.checkNotNullExpressionValue(intent, "getIntent(...)");
        ActivityResultLauncher<String> activityResultLauncher = null;
        if (hasAction(intent)) {
            Intent intent2 = getIntent();
            Intrinsics.checkNotNullExpressionValue(intent2, "getIntent(...)");
            if (Intrinsics.areEqual(getAction(intent2), "feed_push")) {
                getNavView().setSelectedItemId(R.id.navigation_notifications);
                try {
                    FeedFragment feedFragment = (FeedFragment) this.fragments[this.currentFragment];
                    if (feedFragment != null) {
                        feedFragment.showPost(getIntent().getIntExtra("uid", -1));
                    }
                } catch (Exception unused) {
                }
            }
            getIntent().setAction(null);
        }
        ActivityResultLauncher<String> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.main.MainActivity$$ExternalSyntheticLambda4
            public /* synthetic */ MainActivity$$ExternalSyntheticLambda4() {
            }

            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                MainActivity.onCreate$lambda$3(MainActivity.this, (Boolean) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResult(...)");
        this.requestPermissionLauncher = registerForActivityResult;
        if (Build.VERSION.SDK_INT < 33 || ContextCompat.checkSelfPermission(mainActivity, "android.permission.POST_NOTIFICATIONS") == 0) {
            return;
        }
        startActivity(new Intent(mainActivity, (Class<?>) NotificationIntroActivity.class));
        ActivityResultLauncher<String> activityResultLauncher2 = this.requestPermissionLauncher;
        if (activityResultLauncher2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("requestPermissionLauncher");
        } else {
            activityResultLauncher = activityResultLauncher2;
        }
        activityResultLauncher.launch("android.permission.POST_NOTIFICATIONS");
    }

    public static final void onCreate$lambda$0(MainActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.postCreate();
    }

    public static final void onCreate$lambda$3(MainActivity this$0, Boolean bool) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNull(bool);
        if (bool.booleanValue()) {
            Snackbar.make(this$0.findViewById(R.id.container), "Ви отримуватимете сповіщення", 0).show();
            return;
        }
        Snackbar make = Snackbar.make(this$0.findViewById(R.id.container), "Будь ласка, увімкніть сповіщення в налаштуваннях додатку", 0);
        Intrinsics.checkNotNullExpressionValue(make, "make(...)");
        if (Build.VERSION.SDK_INT >= 26) {
            make.setAction("Налаштування", new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.main.MainActivity$$ExternalSyntheticLambda6
                public /* synthetic */ MainActivity$$ExternalSyntheticLambda6() {
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MainActivity.onCreate$lambda$3$lambda$2(MainActivity.this, view);
                }
            });
        }
        make.show();
    }

    public static final void onCreate$lambda$3$lambda$2(MainActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.openNotificationSettingsForApp();
    }

    private final void openNotificationSettingsForApp() {
        Intent intent = new Intent();
        intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
        intent.putExtra("android.provider.extra.APP_PACKAGE", getPackageName());
        intent.addFlags(268435456);
        startActivity(intent);
    }

    private final void setUpProfileIcon() {
        Glide.with((FragmentActivity) this).load(AppDataManager.getInstance().getUser().getProfileImage()).apply((BaseRequestOptions<?>) RequestOptions.circleCropTransform()).into((ImageView) findViewById(R.id.profileIcon));
    }

    public final void onClickMenu(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        showMenu();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        super.onNewIntent(intent);
        if (hasAction(intent)) {
            if (Intrinsics.areEqual(getAction(intent), "feed_push")) {
                getNavView().setSelectedItemId(R.id.navigation_notifications);
                try {
                    FeedFragment feedFragment = (FeedFragment) this.fragments[this.currentFragment];
                    if (feedFragment != null) {
                        feedFragment.showPost(intent.getIntExtra("uid", -1));
                    }
                } catch (Exception unused) {
                }
            }
            intent.setAction(null);
        }
    }

    private final String getAction(Intent intent) {
        return intent.getStringExtra("action");
    }

    private final boolean hasAction(Intent intent) {
        return intent.hasExtra("action");
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.view.AppCompatActivityOverrideMenu
    public boolean onOptionsItemSelected(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        if (v.getId() != R.id.idCard) {
            return false;
        }
        if (this.idCardDelegate == null) {
            this.idCardDelegate = new IdCardDelegate(this);
        }
        IdCardDelegate idCardDelegate = this.idCardDelegate;
        Intrinsics.checkNotNull(idCardDelegate);
        idCardDelegate.show();
        return true;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.TimetableActivityContract
    public void onRefresh() {
        TimetableDelegate timetableDelegate = this.timetableDelegate;
        Intrinsics.checkNotNull(timetableDelegate);
        timetableDelegate.onRefresh();
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(savedInstanceState, "savedInstanceState");
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("currentFragment", this.currentFragment);
        Log.e("Instance", "onSaveInstanceState " + this.currentFragment);
    }

    @Override // android.app.Activity
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(savedInstanceState, "savedInstanceState");
        super.onRestoreInstanceState(savedInstanceState);
        this.currentFragment = savedInstanceState.getInt("currentFragment", -1);
        Log.e("Instance", "onRestoreInstanceState " + this.currentFragment);
    }

    @Override // com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        Fragment fragment;
        Fragment fragment2;
        Fragment fragment3;
        Fragment fragment4;
        Intrinsics.checkNotNullParameter(menuItem, "menuItem");
        BottomNavigationView navView = getNavView();
        Intrinsics.checkNotNull(navView);
        if (navView.getSelectedItemId() == menuItem.getItemId()) {
            return false;
        }
        FragmentTransaction beginTransaction = this.fm.beginTransaction();
        Intrinsics.checkNotNullExpressionValue(beginTransaction, "beginTransaction(...)");
        if (menuItem.getItemId() == R.id.navigation_home) {
            beginTransaction.setCustomAnimations(R.anim.fragment_open_animation, 0);
            TimetableDelegate timetableDelegate = this.timetableDelegate;
            Intrinsics.checkNotNull(timetableDelegate);
            timetableDelegate.setVisible(true);
            TimetableDelegate timetableDelegate2 = this.timetableDelegate;
            Intrinsics.checkNotNull(timetableDelegate2);
            timetableDelegate2.getRoot().startAnimation(AnimationUtils.loadAnimation(this, R.anim.fragment_open_animation));
        } else {
            beginTransaction.setCustomAnimations(R.anim.fragment_open_animation, 0);
            TimetableDelegate timetableDelegate3 = this.timetableDelegate;
            Intrinsics.checkNotNull(timetableDelegate3);
            timetableDelegate3.setVisible(false);
        }
        switch (menuItem.getItemId()) {
            case R.id.navigation_home /* 2131362444 */:
                int i = this.currentFragment;
                if (i != -1) {
                    Fragment fragment5 = this.fragments[i];
                    if (fragment5 != null) {
                        Intrinsics.checkNotNull(fragment5);
                        beginTransaction.hide(fragment5);
                    }
                    this.currentFragment = -1;
                    break;
                } else {
                    return false;
                }
            case R.id.navigation_menu /* 2131362445 */:
                int i2 = this.currentFragment;
                if (i2 != -1 && (fragment = this.fragments[i2]) != null) {
                    Intrinsics.checkNotNull(fragment);
                    beginTransaction.hide(fragment);
                }
                this.currentFragment = 3;
                Fragment[] fragmentArr = this.fragments;
                if (fragmentArr[3] == null) {
                    fragmentArr[3] = new MainMenuFragment();
                }
                Fragment findFragmentByTag = this.fm.findFragmentByTag("MainMenuFragment");
                if (findFragmentByTag == null) {
                    Fragment fragment6 = this.fragments[this.currentFragment];
                    Intrinsics.checkNotNull(fragment6);
                    beginTransaction.add(R.id.container, fragment6, "MainMenuFragment");
                    break;
                } else {
                    beginTransaction.show(findFragmentByTag);
                    break;
                }
            case R.id.navigation_notifications /* 2131362446 */:
                int i3 = this.currentFragment;
                if (i3 != -1 && (fragment2 = this.fragments[i3]) != null) {
                    Intrinsics.checkNotNull(fragment2);
                    beginTransaction.hide(fragment2);
                }
                this.currentFragment = 0;
                Fragment[] fragmentArr2 = this.fragments;
                if (fragmentArr2[0] == null) {
                    fragmentArr2[0] = new FeedFragment();
                }
                Fragment findFragmentByTag2 = this.fm.findFragmentByTag("FeedFragment");
                if (findFragmentByTag2 == null) {
                    Fragment fragment7 = this.fragments[this.currentFragment];
                    Intrinsics.checkNotNull(fragment7);
                    beginTransaction.add(R.id.container, fragment7, "FeedFragment");
                    break;
                } else {
                    beginTransaction.show(findFragmentByTag2);
                    break;
                }
                break;
            case R.id.navigation_profile /* 2131362447 */:
                int i4 = this.currentFragment;
                if (i4 != -1 && (fragment3 = this.fragments[i4]) != null) {
                    Intrinsics.checkNotNull(fragment3);
                    beginTransaction.hide(fragment3);
                }
                this.currentFragment = 2;
                if (this.fragments[2] == null) {
                    if (AppDataManager.getInstance().isStudent()) {
                        this.fragments[this.currentFragment] = new StudentProfileFragment();
                    } else if (AppDataManager.getInstance().isTeacher()) {
                        this.fragments[this.currentFragment] = new TeacherProfileFragment();
                    } else {
                        this.fragments[this.currentFragment] = new EmployeeProfileFragment();
                    }
                }
                Fragment findFragmentByTag3 = this.fm.findFragmentByTag("ProfileFragment");
                if (findFragmentByTag3 == null) {
                    Fragment fragment8 = this.fragments[this.currentFragment];
                    Intrinsics.checkNotNull(fragment8);
                    beginTransaction.add(R.id.container, fragment8, "ProfileFragment");
                    break;
                } else {
                    beginTransaction.show(findFragmentByTag3);
                    break;
                }
                break;
            case R.id.navigation_search /* 2131362448 */:
                int i5 = this.currentFragment;
                if (i5 != -1 && (fragment4 = this.fragments[i5]) != null) {
                    Intrinsics.checkNotNull(fragment4);
                    beginTransaction.hide(fragment4);
                }
                this.currentFragment = 1;
                Fragment[] fragmentArr3 = this.fragments;
                if (fragmentArr3[1] == null) {
                    fragmentArr3[1] = new SearchFragment();
                }
                Fragment findFragmentByTag4 = this.fm.findFragmentByTag("SearchFragment");
                if (findFragmentByTag4 == null) {
                    Fragment fragment9 = this.fragments[this.currentFragment];
                    Intrinsics.checkNotNull(fragment9);
                    beginTransaction.add(R.id.container, fragment9, "SearchFragment");
                    break;
                } else {
                    beginTransaction.show(findFragmentByTag4);
                    break;
                }
                break;
            default:
                return false;
        }
        beginTransaction.commit();
        return true;
    }

    @Override // androidx.fragment.app.FragmentActivity
    public void onAttachFragment(Fragment fragment) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        super.onAttachFragment(fragment);
        if (fragment instanceof FeedFragment) {
            this.currentFragment = 0;
            this.fragments[0] = fragment;
            return;
        }
        if (fragment instanceof SearchFragment) {
            this.currentFragment = 1;
            this.fragments[1] = fragment;
        } else if ((fragment instanceof StudentProfileFragment) || (fragment instanceof TeacherProfileFragment)) {
            this.currentFragment = 2;
            this.fragments[2] = fragment;
        } else if (fragment instanceof MainMenuFragment) {
            this.currentFragment = 3;
            this.fragments[3] = fragment;
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent r4) {
        super.onActivityResult(requestCode, resultCode, r4);
        if (requestCode == 203) {
            CropImage.ActivityResult activityResult = CropImage.getActivityResult(r4);
            if (resultCode != -1) {
                if (resultCode != 204) {
                    return;
                }
                Exception error = activityResult.getError();
                IdCardDelegate idCardDelegate = this.idCardDelegate;
                if (idCardDelegate != null) {
                    Intrinsics.checkNotNull(idCardDelegate);
                    if (idCardDelegate.isShowing()) {
                        Toast.makeText(this, error.getMessage(), 0).show();
                        return;
                    }
                    return;
                }
                return;
            }
            Uri uri = activityResult.getUri();
            IdCardDelegate idCardDelegate2 = this.idCardDelegate;
            if (idCardDelegate2 != null) {
                Intrinsics.checkNotNull(idCardDelegate2);
                if (idCardDelegate2.isShowing()) {
                    IdCardDelegate idCardDelegate3 = this.idCardDelegate;
                    Intrinsics.checkNotNull(idCardDelegate3);
                    idCardDelegate3.uploadImage(uri);
                    return;
                }
                return;
            }
            return;
        }
        if (requestCode == this.UPDATE_INSTALL_REQUEST_CODE_FLEXIBLE) {
            if (resultCode == 0) {
                AppDataManager.getIndependentInstance().edit().putInt("skipUpdate", this.availabilityVerCode).apply();
            }
        } else if (requestCode == this.UPDATE_INSTALL_REQUEST_CODE_IMMEDIATE) {
            if (resultCode == 0) {
                finish();
            }
        } else if (requestCode == 54 && resultCode == 4) {
            onRefresh();
        }
    }

    public final int getAvailabilityVerCode() {
        return this.availabilityVerCode;
    }

    public final void setAvailabilityVerCode(int i) {
        this.availabilityVerCode = i;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: ProcessVariables
        jadx.core.utils.exceptions.JadxRuntimeException: Method arg registers not loaded: com.m_myr.nuwm.nuwmschedule.ui.activities.main.MainActivity$postCreate$1.<init>(com.m_myr.nuwm.nuwmschedule.data.repositories.AppDataManager, com.m_myr.nuwm.nuwmschedule.ui.activities.main.MainActivity):void, class status: GENERATED_AND_UNLOADED
        	at jadx.core.dex.nodes.MethodNode.getArgRegs(MethodNode.java:290)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables$1.isArgUnused(ProcessVariables.java:146)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables$1.lambda$isVarUnused$0(ProcessVariables.java:131)
        	at jadx.core.utils.ListUtils.allMatch(ListUtils.java:193)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables$1.isVarUnused(ProcessVariables.java:131)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables$1.processBlock(ProcessVariables.java:82)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:64)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:19)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables.removeUnusedResults(ProcessVariables.java:73)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables.visit(ProcessVariables.java:48)
        */
    public final void postCreate() {
        /*
            r6 = this;
            com.m_myr.nuwm.nuwmschedule.domain.App r0 = com.m_myr.nuwm.nuwmschedule.domain.App.getInstance()
            r0.subscribeToTopics()
            com.m_myr.nuwm.nuwmschedule.domain.App r0 = com.m_myr.nuwm.nuwmschedule.domain.App.getInstance()
            boolean r0 = r0.wasUpdate()
            if (r0 == 0) goto L14
            r6.showChangeLog()
        L14:
            r6.appUpdateCheck()
            com.m_myr.nuwm.nuwmschedule.data.repositories.AppDataManager r0 = com.m_myr.nuwm.nuwmschedule.data.repositories.AppDataManager.getInstance()
            long r1 = java.lang.System.currentTimeMillis()
            long r3 = r0.getLastUpdate()
            long r1 = r1 - r3
            r3 = 43200000(0x2932e00, double:2.1343636E-316)
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 <= 0) goto L48
            r1 = r6
            androidx.lifecycle.LifecycleOwner r1 = (androidx.lifecycle.LifecycleOwner) r1
            com.m_myr.nuwm.nuwmschedule.data.repositories.FastRepository$FastRepositoryOwner r1 = com.m_myr.nuwm.nuwmschedule.data.repositories.FastRepository.from(r1)
            com.m_myr.nuwm.nuwmschedule.network.api.ApiRequestBuilder r2 = com.m_myr.nuwm.nuwmschedule.network.api.APIMethods.getProfileJson()
            com.m_myr.nuwm.nuwmschedule.data.repositories.FastRepository r1 = r1.call(r2)
            com.m_myr.nuwm.nuwmschedule.ui.activities.main.MainActivity$postCreate$1 r2 = new com.m_myr.nuwm.nuwmschedule.ui.activities.main.MainActivity$postCreate$1
            r2.<init>()
            com.m_myr.nuwm.nuwmschedule.network.api.APIObjectListener r2 = (com.m_myr.nuwm.nuwmschedule.network.api.APIObjectListener) r2
            java.lang.Thread r0 = r1.into(r2)
            r0.start()
        L48:
            com.google.firebase.messaging.FirebaseMessaging r0 = com.google.firebase.messaging.FirebaseMessaging.getInstance()     // Catch: java.lang.Exception -> L59
            com.google.android.gms.tasks.Task r0 = r0.getToken()     // Catch: java.lang.Exception -> L59
            com.m_myr.nuwm.nuwmschedule.ui.activities.main.MainActivity$$ExternalSyntheticLambda5 r1 = new com.m_myr.nuwm.nuwmschedule.ui.activities.main.MainActivity$$ExternalSyntheticLambda5     // Catch: java.lang.Exception -> L59
            r1.<init>()     // Catch: java.lang.Exception -> L59
            r0.addOnCompleteListener(r1)     // Catch: java.lang.Exception -> L59
            goto L6b
        L59:
            com.m_myr.nuwm.nuwmschedule.domain.App r0 = com.m_myr.nuwm.nuwmschedule.domain.App.getInstance()
            android.content.Context r0 = (android.content.Context) r0
            java.lang.String r1 = "Помилка оновлення FCM токену. Зверніться до розробника"
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            r2 = 1
            android.widget.Toast r0 = android.widget.Toast.makeText(r0, r1, r2)
            r0.show()
        L6b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.m_myr.nuwm.nuwmschedule.ui.activities.main.MainActivity.postCreate():void");
    }

    public static final void postCreate$lambda$5(Task it) {
        Intrinsics.checkNotNullParameter(it, "it");
        if (it.isComplete() && it.isSuccessful()) {
            String str = ((String) it.getResult()).toString();
            String deviceIdLegacy = App.getDeviceIdLegacy();
            Log.d(Constants.TAG, "token " + str);
            FastRepository.call(APIMethods.sendMessagingToken(str, deviceIdLegacy).setTimeout(5000)).detach().start();
        }
    }

    private final void checkUnread() {
        FastRepository.from(this).call(APIMethods.checkUnread(AppDataManager.getInstance().getTopics())).into((APIObjectListener) new APIObjectListener<AppUserInfoUpdateData>() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.main.MainActivity$checkUnread$1
            @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
            public void onSuccessData(AppUserInfoUpdateData r2) {
                Intrinsics.checkNotNullParameter(r2, "data");
            }

            MainActivity$checkUnread$1() {
            }

            @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
            public void onError(ErrorResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                if (response.getCode() == 61 || response.getCode() == 63) {
                    Toast.makeText(App.getInstance(), response.getMessage(), 1).show();
                    SignoutActivity.exit(MainActivity.this);
                }
            }
        }).start();
    }

    public final void appUpdateCheck() {
        AppUpdateManager create = AppUpdateManagerFactory.create(this);
        this.appUpdateManager = create;
        Intrinsics.checkNotNull(create);
        create.registerListener(this);
        AppUpdateManager appUpdateManager = this.appUpdateManager;
        Intrinsics.checkNotNull(appUpdateManager);
        Task<AppUpdateInfo> appUpdateInfo = appUpdateManager.getAppUpdateInfo();
        Intrinsics.checkNotNullExpressionValue(appUpdateInfo, "getAppUpdateInfo(...)");
        appUpdateInfo.addOnSuccessListener(new OnSuccessListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.main.MainActivity$$ExternalSyntheticLambda0
            public /* synthetic */ MainActivity$$ExternalSyntheticLambda0() {
            }

            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                MainActivity.appUpdateCheck$lambda$6(Function1.this, obj);
            }
        });
    }

    public static final void appUpdateCheck$lambda$6(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public final void onStub(View view) {
        Utils.showStub(this);
    }

    private final void showChangeLog() {
        new AlertDialog.Builder(this).setTitle("Що нового у версії 5.4.5").setMessage(Html.fromHtml(" \n• Виправлення помилки відображення дат;<br>\n• Випарвлено завантаження файлів репозиторію;<br>\n• Деякі інші виправлення;<br>\n<br>\n<br>\n<i>Це останнє оновлення для Android 6.0 та старіше <i>\n")).setPositiveButton("Добре", (DialogInterface.OnClickListener) null).setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.main.MainActivity$$ExternalSyntheticLambda1
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                MainActivity.showChangeLog$lambda$7(dialogInterface);
            }
        }).setCancelable(false).show();
    }

    public static final void showChangeLog$lambda$7(DialogInterface dialogInterface) {
        App.getInstance().skipUpdate();
    }

    @Override // com.google.android.play.core.listener.StateUpdatedListener
    public void onStateUpdate(InstallState state) {
        Intrinsics.checkNotNullParameter(state, "state");
        if (state.installStatus() == 11) {
            onShowSnackbarUpdate();
        } else if (state.installStatus() == 4) {
            AppUpdateManager appUpdateManager = this.appUpdateManager;
            Intrinsics.checkNotNull(appUpdateManager);
            appUpdateManager.unregisterListener(this);
        }
    }

    public final void onShowSnackbarUpdate() {
        Snackbar make = Snackbar.make(findViewById(R.id.container), "Оновлення завантажено.", 10000);
        Intrinsics.checkNotNullExpressionValue(make, "make(...)");
        make.setAction("Встановити", new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.main.MainActivity$$ExternalSyntheticLambda2
            public /* synthetic */ MainActivity$$ExternalSyntheticLambda2() {
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.onShowSnackbarUpdate$lambda$8(MainActivity.this, view);
            }
        });
        make.setActionTextColor(ContextCompat.getColor(this, R.color.colorAccent));
        make.show();
    }

    public static final void onShowSnackbarUpdate$lambda$8(MainActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AppUpdateManager appUpdateManager = this$0.appUpdateManager;
        Intrinsics.checkNotNull(appUpdateManager);
        appUpdateManager.completeUpdate();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        Fragment fragment;
        int i = this.currentFragment;
        if (i == 1 && (fragment = this.fragments[i]) != null) {
            SearchFragment searchFragment = (SearchFragment) fragment;
            Intrinsics.checkNotNull(searchFragment);
            if (searchFragment.onBackPressed()) {
                return;
            }
            super.onBackPressed();
            return;
        }
        super.onBackPressed();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        checkUnread();
    }

    public final void onClickProfile(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        new ProfileMenuAlert(this).show();
    }
}
