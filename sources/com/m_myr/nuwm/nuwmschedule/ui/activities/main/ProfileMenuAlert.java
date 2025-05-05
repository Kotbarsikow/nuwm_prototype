package com.m_myr.nuwm.nuwmschedule.ui.activities.main;

import android.content.Intent;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.models.LoggedUser;
import com.m_myr.nuwm.nuwmschedule.data.repositories.AppDataManager;
import com.m_myr.nuwm.nuwmschedule.data.repositories.FastRepository;
import com.m_myr.nuwm.nuwmschedule.databinding.ProfileMenuPopupBinding;
import com.m_myr.nuwm.nuwmschedule.network.APIMethod;
import com.m_myr.nuwm.nuwmschedule.network.api.APIMethods;
import com.m_myr.nuwm.nuwmschedule.network.api.APIObjectListener;
import com.m_myr.nuwm.nuwmschedule.ui.activities.about.AboutActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.department.DepartmentProfileActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.HelpdeskActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.notesList.MyNotesListActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.qrScaner.QrCodeScannerActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.settings.AppSettingsActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.signout.SignoutActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.students.StudentProfileActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.user.UserProfileActivity;
import com.m_myr.nuwm.nuwmschedule.ui.framents.main.MainMenuOwner;
import com.m_myr.nuwm.nuwmschedule.ui.framents.main.MainMenuPresenterCompat;
import com.m_myr.nuwm.nuwmschedule.utils.LinksResolver;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ProfileMenuAlert.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0002J\u0010\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0013H\u0016J\b\u0010\u0018\u001a\u00020\u0013H\u0002J\u000e\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u001bJ\u0010\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u001dH\u0016J\u0010\u0010\u001e\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u001dH\u0016J\u000e\u0010\u001f\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u001bR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006 "}, d2 = {"Lcom/m_myr/nuwm/nuwmschedule/ui/activities/main/ProfileMenuAlert;", "Landroidx/appcompat/app/AlertDialog;", "Lcom/m_myr/nuwm/nuwmschedule/ui/framents/main/MainMenuOwner;", "activity", "Landroidx/appcompat/app/AppCompatActivity;", "(Landroidx/appcompat/app/AppCompatActivity;)V", "getActivity", "()Landroidx/appcompat/app/AppCompatActivity;", "binding", "Lcom/m_myr/nuwm/nuwmschedule/databinding/ProfileMenuPopupBinding;", "getBinding", "()Lcom/m_myr/nuwm/nuwmschedule/databinding/ProfileMenuPopupBinding;", "presenter", "Lcom/m_myr/nuwm/nuwmschedule/ui/framents/main/MainMenuPresenterCompat;", "getPresenter", "()Lcom/m_myr/nuwm/nuwmschedule/ui/framents/main/MainMenuPresenterCompat;", "getLifecycle", "Landroidx/lifecycle/Lifecycle;", "hideAccountAction", "", "setUserInfo", "user", "Lcom/m_myr/nuwm/nuwmschedule/data/models/LoggedUser;", "show", "showAccountAction", "showMe", "view", "Landroid/view/View;", "showPhotoHint", "", "showVerifiedHint", "updateAccount", "app_publicReleaseRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ProfileMenuAlert extends AlertDialog implements MainMenuOwner {
    private final AppCompatActivity activity;
    private final ProfileMenuPopupBinding binding;
    private final MainMenuPresenterCompat presenter;

    @Override // com.m_myr.nuwm.nuwmschedule.ui.framents.main.MainMenuOwner
    public void showPhotoHint(boolean show) {
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.framents.main.MainMenuOwner
    public void showVerifiedHint(boolean show) {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProfileMenuAlert(AppCompatActivity activity) {
        super(activity, R.style.RoundedCornersDialog);
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.activity = activity;
        ProfileMenuPopupBinding inflate = ProfileMenuPopupBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        this.binding = inflate;
        setView(inflate.getRoot());
        this.presenter = new MainMenuPresenterCompat(this);
        inflate.profilePlace.setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.main.ProfileMenuAlert$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ProfileMenuAlert._init_$lambda$0(ProfileMenuAlert.this, view);
            }
        });
    }

    public final AppCompatActivity getActivity() {
        return this.activity;
    }

    public final ProfileMenuPopupBinding getBinding() {
        return this.binding;
    }

    public final MainMenuPresenterCompat getPresenter() {
        return this.presenter;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$0(ProfileMenuAlert this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (Intrinsics.areEqual(this$0.binding.profilePlace.getTag(), (Object) true)) {
            this$0.hideAccountAction();
        } else {
            this$0.showAccountAction();
        }
    }

    private final void showAccountAction() {
        this.binding.profilePlace.setTag(true);
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, -180.0f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setDuration(150L);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setFillAfter(true);
        this.binding.arrow.startAnimation(rotateAnimation);
        this.binding.accountAction.setVisibility(0);
    }

    private final void hideAccountAction() {
        this.binding.profilePlace.setTag(false);
        RotateAnimation rotateAnimation = new RotateAnimation(-180.0f, 0.0f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setDuration(150L);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setFillAfter(true);
        this.binding.arrow.startAnimation(rotateAnimation);
        this.binding.accountAction.setVisibility(8);
    }

    @Override // androidx.lifecycle.LifecycleOwner
    public Lifecycle getLifecycle() {
        Lifecycle lifecycle = this.activity.getLifecycle();
        Intrinsics.checkNotNullExpressionValue(lifecycle, "getLifecycle(...)");
        return lifecycle;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.framents.main.MainMenuOwner
    public void setUserInfo(LoggedUser user) {
        Intrinsics.checkNotNullParameter(user, "user");
        Glide.with(getContext()).load(user.getProfileImage()).apply((BaseRequestOptions<?>) RequestOptions.circleCropTransform()).into(this.binding.profileImage);
        this.binding.username.setText(user.getSimpleName());
        this.binding.usertype.setText(user.getWho());
        this.binding.updateAccount.setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.main.ProfileMenuAlert$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ProfileMenuAlert.this.updateAccount(view);
            }
        });
        this.binding.showProfile.setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.main.ProfileMenuAlert$$ExternalSyntheticLambda9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ProfileMenuAlert.this.showMe(view);
            }
        });
        this.binding.btnClose.setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.main.ProfileMenuAlert$$ExternalSyntheticLambda10
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ProfileMenuAlert.setUserInfo$lambda$1(ProfileMenuAlert.this, view);
            }
        });
        this.binding.note.setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.main.ProfileMenuAlert$$ExternalSyntheticLambda11
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ProfileMenuAlert.setUserInfo$lambda$2(ProfileMenuAlert.this, view);
            }
        });
        this.binding.settings.setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.main.ProfileMenuAlert$$ExternalSyntheticLambda12
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ProfileMenuAlert.setUserInfo$lambda$3(ProfileMenuAlert.this, view);
            }
        });
        this.binding.about.setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.main.ProfileMenuAlert$$ExternalSyntheticLambda13
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ProfileMenuAlert.setUserInfo$lambda$4(ProfileMenuAlert.this, view);
            }
        });
        this.binding.navigateToDesk.setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.main.ProfileMenuAlert$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ProfileMenuAlert.setUserInfo$lambda$5(ProfileMenuAlert.this, view);
            }
        });
        this.binding.report.setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.main.ProfileMenuAlert$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ProfileMenuAlert.setUserInfo$lambda$6(ProfileMenuAlert.this, view);
            }
        });
        this.binding.helpdesk.setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.main.ProfileMenuAlert$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ProfileMenuAlert.setUserInfo$lambda$7(ProfileMenuAlert.this, view);
            }
        });
        this.binding.logout.setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.main.ProfileMenuAlert$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ProfileMenuAlert.setUserInfo$lambda$8(ProfileMenuAlert.this, view);
            }
        });
        this.binding.changeAccount.setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.main.ProfileMenuAlert$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ProfileMenuAlert.setUserInfo$lambda$9(ProfileMenuAlert.this, view);
            }
        });
        this.binding.terms.setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.main.ProfileMenuAlert$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ProfileMenuAlert.setUserInfo$lambda$10(ProfileMenuAlert.this, view);
            }
        });
        if (AppDataManager.getInstance().isStudent()) {
            this.binding.navigateToDesk.setVisibility(0);
            this.binding.navigateToDesk.setText("Увійти у ПС Студент");
        }
        if (AppDataManager.getInstance().isTeacher()) {
            this.binding.navigateToDesk.setVisibility(0);
            this.binding.navigateToDesk.setText("Увійти у ПС-журнал");
        }
        this.binding.scan.setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.main.ProfileMenuAlert$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ProfileMenuAlert.setUserInfo$lambda$11(ProfileMenuAlert.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setUserInfo$lambda$1(ProfileMenuAlert this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setUserInfo$lambda$2(ProfileMenuAlert this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.activity.startActivityForResult(new Intent(this$0.activity, (Class<?>) MyNotesListActivity.class), 50);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setUserInfo$lambda$3(ProfileMenuAlert this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.activity.startActivity(new Intent(this$0.activity, (Class<?>) AppSettingsActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setUserInfo$lambda$4(ProfileMenuAlert this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.activity.startActivity(new Intent(this$0.activity, (Class<?>) AboutActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setUserInfo$lambda$5(ProfileMenuAlert this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LinksResolver.startOnChrome(this$0.activity, APIMethod.Patch.getLinkDesk());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setUserInfo$lambda$6(ProfileMenuAlert this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DepartmentProfileActivity.INSTANCE.startById(this$0.activity, 20000077);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setUserInfo$lambda$7(ProfileMenuAlert this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.activity.startActivity(new Intent(this$0.activity, (Class<?>) HelpdeskActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setUserInfo$lambda$8(ProfileMenuAlert this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SignoutActivity.exit(this$0.activity);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setUserInfo$lambda$9(ProfileMenuAlert this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SignoutActivity.change(this$0.activity);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setUserInfo$lambda$10(ProfileMenuAlert this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LinksResolver.startOnChrome(this$0.activity, APIMethod.Patch.getLinkDesk());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setUserInfo$lambda$11(ProfileMenuAlert this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.activity.startActivity(new Intent(this$0.activity, (Class<?>) QrCodeScannerActivity.class));
    }

    public final void showMe(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (AppDataManager.getInstance().isStudent()) {
            StudentProfileActivity.startById(getContext(), AppDataManager.getInstance().getUser().getId());
        } else if (AppDataManager.getInstance().isEmployee()) {
            UserProfileActivity.startById(getContext(), AppDataManager.getInstance().getUser().getId());
        }
        Utils.sendAnalytic(getContext(), "click_my_profile", new Pair[0]);
    }

    @Override // android.app.Dialog
    public void show() {
        super.show();
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        Window window = getWindow();
        Intrinsics.checkNotNull(window);
        layoutParams.copyFrom(window.getAttributes());
        layoutParams.width = -1;
        layoutParams.height = -2;
        Window window2 = getWindow();
        Intrinsics.checkNotNull(window2);
        window2.setAttributes(layoutParams);
    }

    public final void updateAccount(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        dismiss();
        FastRepository.from(this).call(APIMethods.getProfileJson()).into((APIObjectListener) new ProfileMenuAlert$updateAccount$1(this)).start();
    }
}
