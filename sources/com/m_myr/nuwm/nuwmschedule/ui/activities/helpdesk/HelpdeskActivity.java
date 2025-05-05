package com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.firebase.messaging.Constants;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.models.helpdesk.SortBy;
import com.m_myr.nuwm.nuwmschedule.data.models.helpdesk.TicketFilters;
import com.m_myr.nuwm.nuwmschedule.data.models.helpdesk.TicketStatus;
import com.m_myr.nuwm.nuwmschedule.data.repositories.AppDataManager;
import com.m_myr.nuwm.nuwmschedule.databinding.ActivityHelpdeskBinding;
import com.m_myr.nuwm.nuwmschedule.databinding.BottomSheetHelpdeskFilterBinding;
import com.m_myr.nuwm.nuwmschedule.ui.activities.base.BaseStateActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.createTicket.CreateTicketActivity;
import com.m_myr.nuwm.nuwmschedule.ui.view.MaterialTabLayout;
import java.util.LinkedHashSet;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HelpdeskActivity.kt */
@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002:\u0001)B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u000f\u001a\u00020\u0010H\u0002J\f\u0010\u0011\u001a\u00060\u0012R\u00020\u0013H\u0016J\"\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0014J\u0018\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0012\u0010\u001f\u001a\u00020\u00102\b\u0010 \u001a\u0004\u0018\u00010!H\u0014J\u0010\u0010\"\u001a\u00020\u001e2\u0006\u0010#\u001a\u00020$H\u0016J\u0010\u0010%\u001a\u00020\u001e2\u0006\u0010&\u001a\u00020'H\u0016J\b\u0010(\u001a\u00020\u0010H\u0002R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\r\u001a\u00060\u000eR\u00020\u0000X\u0082.¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lcom/m_myr/nuwm/nuwmschedule/ui/activities/helpdesk/HelpdeskActivity;", "Lcom/m_myr/nuwm/nuwmschedule/ui/activities/base/BaseStateActivity;", "Landroid/widget/CompoundButton$OnCheckedChangeListener;", "()V", "behavior", "Lcom/google/android/material/bottomsheet/BottomSheetBehavior;", "Landroid/widget/LinearLayout;", "binding", "Lcom/m_myr/nuwm/nuwmschedule/databinding/ActivityHelpdeskBinding;", "bindingSheet", "Lcom/m_myr/nuwm/nuwmschedule/databinding/BottomSheetHelpdeskFilterBinding;", "settings", "Lcom/m_myr/nuwm/nuwmschedule/data/models/helpdesk/TicketFilters;", "viewPagerAdapter", "Lcom/m_myr/nuwm/nuwmschedule/ui/activities/helpdesk/HelpdeskActivity$ViewPagerAdapter;", "applySettingChange", "", "getTheme", "Landroid/content/res/Resources$Theme;", "Landroid/content/res/Resources;", "onActivityResult", "requestCode", "", "resultCode", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "Landroid/content/Intent;", "onCheckedChanged", "buttonView", "Landroid/widget/CompoundButton;", "isChecked", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "menu", "Landroid/view/Menu;", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "setState", "ViewPagerAdapter", "app_publicReleaseRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class HelpdeskActivity extends BaseStateActivity implements CompoundButton.OnCheckedChangeListener {
    private BottomSheetBehavior<LinearLayout> behavior;
    private ActivityHelpdeskBinding binding;
    private BottomSheetHelpdeskFilterBinding bindingSheet;
    private TicketFilters settings;
    private ViewPagerAdapter viewPagerAdapter;

    public HelpdeskActivity() {
        TicketFilters readHelpdeskSetting = AppDataManager.getInstance().readHelpdeskSetting();
        Intrinsics.checkNotNullExpressionValue(readHelpdeskSetting, "readHelpdeskSetting(...)");
        this.settings = readHelpdeskSetting;
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Resources.Theme getTheme() {
        Resources.Theme theme = super.getTheme();
        theme.applyStyle(R.style.AppTheme_White, true);
        Intrinsics.checkNotNull(theme);
        return theme;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_White);
        super.onCreate(savedInstanceState);
        ActivityHelpdeskBinding inflate = ActivityHelpdeskBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        this.binding = inflate;
        ActivityHelpdeskBinding activityHelpdeskBinding = null;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate);
        attachToolbar();
        setTitle("Сервіс Надання Інформаційних Послуг");
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "getSupportFragmentManager(...)");
        this.viewPagerAdapter = new ViewPagerAdapter(this, supportFragmentManager);
        ActivityHelpdeskBinding activityHelpdeskBinding2 = this.binding;
        if (activityHelpdeskBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityHelpdeskBinding2 = null;
        }
        ViewPager viewPager = activityHelpdeskBinding2.viewpager;
        ViewPagerAdapter viewPagerAdapter = this.viewPagerAdapter;
        if (viewPagerAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewPagerAdapter");
            viewPagerAdapter = null;
        }
        viewPager.setAdapter(viewPagerAdapter);
        ActivityHelpdeskBinding activityHelpdeskBinding3 = this.binding;
        if (activityHelpdeskBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityHelpdeskBinding3 = null;
        }
        MaterialTabLayout materialTabLayout = activityHelpdeskBinding3.tabLayout;
        ActivityHelpdeskBinding activityHelpdeskBinding4 = this.binding;
        if (activityHelpdeskBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityHelpdeskBinding4 = null;
        }
        materialTabLayout.setupWithViewPager(activityHelpdeskBinding4.viewpager);
        ActivityHelpdeskBinding activityHelpdeskBinding5 = this.binding;
        if (activityHelpdeskBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityHelpdeskBinding5 = null;
        }
        activityHelpdeskBinding5.viewpager.setOffscreenPageLimit(2);
        ActivityHelpdeskBinding activityHelpdeskBinding6 = this.binding;
        if (activityHelpdeskBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityHelpdeskBinding6 = null;
        }
        BottomSheetHelpdeskFilterBinding bind = BottomSheetHelpdeskFilterBinding.bind(activityHelpdeskBinding6.bottomSheet.getRoot());
        Intrinsics.checkNotNullExpressionValue(bind, "bind(...)");
        this.bindingSheet = bind;
        if (bind == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bindingSheet");
            bind = null;
        }
        BottomSheetBehavior<LinearLayout> from = BottomSheetBehavior.from(bind.getRoot());
        Intrinsics.checkNotNullExpressionValue(from, "from(...)");
        this.behavior = from;
        if (from == null) {
            Intrinsics.throwUninitializedPropertyAccessException("behavior");
            from = null;
        }
        from.setState(5);
        setState();
        BottomSheetHelpdeskFilterBinding bottomSheetHelpdeskFilterBinding = this.bindingSheet;
        if (bottomSheetHelpdeskFilterBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bindingSheet");
            bottomSheetHelpdeskFilterBinding = null;
        }
        bottomSheetHelpdeskFilterBinding.apply.setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.HelpdeskActivity$$ExternalSyntheticLambda0
            public /* synthetic */ HelpdeskActivity$$ExternalSyntheticLambda0() {
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HelpdeskActivity.onCreate$lambda$0(HelpdeskActivity.this, view);
            }
        });
        BottomSheetHelpdeskFilterBinding bottomSheetHelpdeskFilterBinding2 = this.bindingSheet;
        if (bottomSheetHelpdeskFilterBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bindingSheet");
            bottomSheetHelpdeskFilterBinding2 = null;
        }
        bottomSheetHelpdeskFilterBinding2.toolbarReset.setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.HelpdeskActivity$$ExternalSyntheticLambda1
            public /* synthetic */ HelpdeskActivity$$ExternalSyntheticLambda1() {
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HelpdeskActivity.onCreate$lambda$1(HelpdeskActivity.this, view);
            }
        });
        ActivityHelpdeskBinding activityHelpdeskBinding7 = this.binding;
        if (activityHelpdeskBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityHelpdeskBinding = activityHelpdeskBinding7;
        }
        activityHelpdeskBinding.fab.setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.HelpdeskActivity$$ExternalSyntheticLambda2
            public /* synthetic */ HelpdeskActivity$$ExternalSyntheticLambda2() {
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HelpdeskActivity.onCreate$lambda$2(HelpdeskActivity.this, view);
            }
        });
    }

    public static final void onCreate$lambda$0(HelpdeskActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.applySettingChange();
    }

    public static final void onCreate$lambda$1(HelpdeskActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.settings = new TicketFilters(false, null, null, false, 15, null);
        this$0.setState();
        this$0.applySettingChange();
    }

    public static final void onCreate$lambda$2(HelpdeskActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivityForResult(new Intent(this$0, (Class<?>) CreateTicketActivity.class), 100);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent r3) {
        super.onActivityResult(requestCode, resultCode, r3);
        if (resultCode == 4) {
            applySettingChange();
        }
    }

    private final void setState() {
        BottomSheetHelpdeskFilterBinding bottomSheetHelpdeskFilterBinding = this.bindingSheet;
        BottomSheetHelpdeskFilterBinding bottomSheetHelpdeskFilterBinding2 = null;
        if (bottomSheetHelpdeskFilterBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bindingSheet");
            bottomSheetHelpdeskFilterBinding = null;
        }
        bottomSheetHelpdeskFilterBinding.pinCriticalTicket.setOnCheckedChangeListener(null);
        BottomSheetHelpdeskFilterBinding bottomSheetHelpdeskFilterBinding3 = this.bindingSheet;
        if (bottomSheetHelpdeskFilterBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bindingSheet");
            bottomSheetHelpdeskFilterBinding3 = null;
        }
        bottomSheetHelpdeskFilterBinding3.showLastReplies.setOnCheckedChangeListener(null);
        BottomSheetHelpdeskFilterBinding bottomSheetHelpdeskFilterBinding4 = this.bindingSheet;
        if (bottomSheetHelpdeskFilterBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bindingSheet");
            bottomSheetHelpdeskFilterBinding4 = null;
        }
        bottomSheetHelpdeskFilterBinding4.sortByStatusNew.setOnCheckedChangeListener(null);
        BottomSheetHelpdeskFilterBinding bottomSheetHelpdeskFilterBinding5 = this.bindingSheet;
        if (bottomSheetHelpdeskFilterBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bindingSheet");
            bottomSheetHelpdeskFilterBinding5 = null;
        }
        bottomSheetHelpdeskFilterBinding5.sortByStatusWait.setOnCheckedChangeListener(null);
        BottomSheetHelpdeskFilterBinding bottomSheetHelpdeskFilterBinding6 = this.bindingSheet;
        if (bottomSheetHelpdeskFilterBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bindingSheet");
            bottomSheetHelpdeskFilterBinding6 = null;
        }
        bottomSheetHelpdeskFilterBinding6.sortByStatusAnswerSent.setOnCheckedChangeListener(null);
        BottomSheetHelpdeskFilterBinding bottomSheetHelpdeskFilterBinding7 = this.bindingSheet;
        if (bottomSheetHelpdeskFilterBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bindingSheet");
            bottomSheetHelpdeskFilterBinding7 = null;
        }
        bottomSheetHelpdeskFilterBinding7.sortByStatusOnReview.setOnCheckedChangeListener(null);
        BottomSheetHelpdeskFilterBinding bottomSheetHelpdeskFilterBinding8 = this.bindingSheet;
        if (bottomSheetHelpdeskFilterBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bindingSheet");
            bottomSheetHelpdeskFilterBinding8 = null;
        }
        bottomSheetHelpdeskFilterBinding8.sortByStatusReviewed.setOnCheckedChangeListener(null);
        BottomSheetHelpdeskFilterBinding bottomSheetHelpdeskFilterBinding9 = this.bindingSheet;
        if (bottomSheetHelpdeskFilterBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bindingSheet");
            bottomSheetHelpdeskFilterBinding9 = null;
        }
        bottomSheetHelpdeskFilterBinding9.sortByStatusHold.setOnCheckedChangeListener(null);
        BottomSheetHelpdeskFilterBinding bottomSheetHelpdeskFilterBinding10 = this.bindingSheet;
        if (bottomSheetHelpdeskFilterBinding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bindingSheet");
            bottomSheetHelpdeskFilterBinding10 = null;
        }
        bottomSheetHelpdeskFilterBinding10.pinCriticalTicket.setChecked(this.settings.getPinCriticalTicket());
        BottomSheetHelpdeskFilterBinding bottomSheetHelpdeskFilterBinding11 = this.bindingSheet;
        if (bottomSheetHelpdeskFilterBinding11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bindingSheet");
            bottomSheetHelpdeskFilterBinding11 = null;
        }
        bottomSheetHelpdeskFilterBinding11.showLastReplies.setChecked(this.settings.getShowLastReplies());
        BottomSheetHelpdeskFilterBinding bottomSheetHelpdeskFilterBinding12 = this.bindingSheet;
        if (bottomSheetHelpdeskFilterBinding12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bindingSheet");
            bottomSheetHelpdeskFilterBinding12 = null;
        }
        bottomSheetHelpdeskFilterBinding12.sortByStatusNew.setChecked(this.settings.getSelectCategory().contains(TicketStatus.NEW));
        BottomSheetHelpdeskFilterBinding bottomSheetHelpdeskFilterBinding13 = this.bindingSheet;
        if (bottomSheetHelpdeskFilterBinding13 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bindingSheet");
            bottomSheetHelpdeskFilterBinding13 = null;
        }
        bottomSheetHelpdeskFilterBinding13.sortByStatusWait.setChecked(this.settings.getSelectCategory().contains(TicketStatus.WAIT));
        BottomSheetHelpdeskFilterBinding bottomSheetHelpdeskFilterBinding14 = this.bindingSheet;
        if (bottomSheetHelpdeskFilterBinding14 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bindingSheet");
            bottomSheetHelpdeskFilterBinding14 = null;
        }
        bottomSheetHelpdeskFilterBinding14.sortByStatusAnswerSent.setChecked(this.settings.getSelectCategory().contains(TicketStatus.ANSWER_SENT));
        BottomSheetHelpdeskFilterBinding bottomSheetHelpdeskFilterBinding15 = this.bindingSheet;
        if (bottomSheetHelpdeskFilterBinding15 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bindingSheet");
            bottomSheetHelpdeskFilterBinding15 = null;
        }
        bottomSheetHelpdeskFilterBinding15.sortByStatusOnReview.setChecked(this.settings.getSelectCategory().contains(TicketStatus.ON_REVIEW));
        BottomSheetHelpdeskFilterBinding bottomSheetHelpdeskFilterBinding16 = this.bindingSheet;
        if (bottomSheetHelpdeskFilterBinding16 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bindingSheet");
            bottomSheetHelpdeskFilterBinding16 = null;
        }
        bottomSheetHelpdeskFilterBinding16.sortByStatusReviewed.setChecked(this.settings.getSelectCategory().contains(TicketStatus.REVIEWED));
        BottomSheetHelpdeskFilterBinding bottomSheetHelpdeskFilterBinding17 = this.bindingSheet;
        if (bottomSheetHelpdeskFilterBinding17 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bindingSheet");
            bottomSheetHelpdeskFilterBinding17 = null;
        }
        bottomSheetHelpdeskFilterBinding17.sortByStatusHold.setChecked(this.settings.getSelectCategory().contains(TicketStatus.HOLD));
        BottomSheetHelpdeskFilterBinding bottomSheetHelpdeskFilterBinding18 = this.bindingSheet;
        if (bottomSheetHelpdeskFilterBinding18 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bindingSheet");
            bottomSheetHelpdeskFilterBinding18 = null;
        }
        bottomSheetHelpdeskFilterBinding18.pinCriticalTicket.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.HelpdeskActivity$$ExternalSyntheticLambda3
            public /* synthetic */ HelpdeskActivity$$ExternalSyntheticLambda3() {
            }

            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                HelpdeskActivity.setState$lambda$3(HelpdeskActivity.this, compoundButton, z);
            }
        });
        BottomSheetHelpdeskFilterBinding bottomSheetHelpdeskFilterBinding19 = this.bindingSheet;
        if (bottomSheetHelpdeskFilterBinding19 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bindingSheet");
            bottomSheetHelpdeskFilterBinding19 = null;
        }
        bottomSheetHelpdeskFilterBinding19.showLastReplies.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.HelpdeskActivity$$ExternalSyntheticLambda4
            public /* synthetic */ HelpdeskActivity$$ExternalSyntheticLambda4() {
            }

            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                HelpdeskActivity.setState$lambda$4(HelpdeskActivity.this, compoundButton, z);
            }
        });
        BottomSheetHelpdeskFilterBinding bottomSheetHelpdeskFilterBinding20 = this.bindingSheet;
        if (bottomSheetHelpdeskFilterBinding20 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bindingSheet");
            bottomSheetHelpdeskFilterBinding20 = null;
        }
        HelpdeskActivity helpdeskActivity = this;
        bottomSheetHelpdeskFilterBinding20.sortByStatusNew.setOnCheckedChangeListener(helpdeskActivity);
        BottomSheetHelpdeskFilterBinding bottomSheetHelpdeskFilterBinding21 = this.bindingSheet;
        if (bottomSheetHelpdeskFilterBinding21 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bindingSheet");
            bottomSheetHelpdeskFilterBinding21 = null;
        }
        bottomSheetHelpdeskFilterBinding21.sortByStatusWait.setOnCheckedChangeListener(helpdeskActivity);
        BottomSheetHelpdeskFilterBinding bottomSheetHelpdeskFilterBinding22 = this.bindingSheet;
        if (bottomSheetHelpdeskFilterBinding22 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bindingSheet");
            bottomSheetHelpdeskFilterBinding22 = null;
        }
        bottomSheetHelpdeskFilterBinding22.sortByStatusAnswerSent.setOnCheckedChangeListener(helpdeskActivity);
        BottomSheetHelpdeskFilterBinding bottomSheetHelpdeskFilterBinding23 = this.bindingSheet;
        if (bottomSheetHelpdeskFilterBinding23 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bindingSheet");
            bottomSheetHelpdeskFilterBinding23 = null;
        }
        bottomSheetHelpdeskFilterBinding23.sortByStatusOnReview.setOnCheckedChangeListener(helpdeskActivity);
        BottomSheetHelpdeskFilterBinding bottomSheetHelpdeskFilterBinding24 = this.bindingSheet;
        if (bottomSheetHelpdeskFilterBinding24 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bindingSheet");
            bottomSheetHelpdeskFilterBinding24 = null;
        }
        bottomSheetHelpdeskFilterBinding24.sortByStatusReviewed.setOnCheckedChangeListener(helpdeskActivity);
        BottomSheetHelpdeskFilterBinding bottomSheetHelpdeskFilterBinding25 = this.bindingSheet;
        if (bottomSheetHelpdeskFilterBinding25 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bindingSheet");
        } else {
            bottomSheetHelpdeskFilterBinding2 = bottomSheetHelpdeskFilterBinding25;
        }
        bottomSheetHelpdeskFilterBinding2.sortByStatusHold.setOnCheckedChangeListener(helpdeskActivity);
    }

    public static final void setState$lambda$3(HelpdeskActivity this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.settings.setPinCriticalTicket(z);
        this$0.applySettingChange();
    }

    public static final void setState$lambda$4(HelpdeskActivity this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.settings.setShowLastReplies(z);
        this$0.applySettingChange();
    }

    private final void applySettingChange() {
        ActivityHelpdeskBinding activityHelpdeskBinding = this.binding;
        ActivityHelpdeskBinding activityHelpdeskBinding2 = null;
        if (activityHelpdeskBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityHelpdeskBinding = null;
        }
        int currentItem = activityHelpdeskBinding.viewpager.getCurrentItem();
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "getSupportFragmentManager(...)");
        this.viewPagerAdapter = new ViewPagerAdapter(this, supportFragmentManager);
        ActivityHelpdeskBinding activityHelpdeskBinding3 = this.binding;
        if (activityHelpdeskBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityHelpdeskBinding3 = null;
        }
        ViewPager viewPager = activityHelpdeskBinding3.viewpager;
        ViewPagerAdapter viewPagerAdapter = this.viewPagerAdapter;
        if (viewPagerAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewPagerAdapter");
            viewPagerAdapter = null;
        }
        viewPager.setAdapter(viewPagerAdapter);
        ActivityHelpdeskBinding activityHelpdeskBinding4 = this.binding;
        if (activityHelpdeskBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityHelpdeskBinding4 = null;
        }
        MaterialTabLayout materialTabLayout = activityHelpdeskBinding4.tabLayout;
        ActivityHelpdeskBinding activityHelpdeskBinding5 = this.binding;
        if (activityHelpdeskBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityHelpdeskBinding5 = null;
        }
        materialTabLayout.setupWithViewPager(activityHelpdeskBinding5.viewpager);
        ActivityHelpdeskBinding activityHelpdeskBinding6 = this.binding;
        if (activityHelpdeskBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityHelpdeskBinding2 = activityHelpdeskBinding6;
        }
        activityHelpdeskBinding2.viewpager.setCurrentItem(currentItem);
        AppDataManager.getInstance().saveHelpdeskSetting(this.settings);
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Intrinsics.checkNotNullParameter(buttonView, "buttonView");
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        BottomSheetHelpdeskFilterBinding bottomSheetHelpdeskFilterBinding = this.bindingSheet;
        BottomSheetHelpdeskFilterBinding bottomSheetHelpdeskFilterBinding2 = null;
        if (bottomSheetHelpdeskFilterBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bindingSheet");
            bottomSheetHelpdeskFilterBinding = null;
        }
        if (bottomSheetHelpdeskFilterBinding.sortByStatusNew.isChecked()) {
            linkedHashSet.add(TicketStatus.NEW);
        }
        BottomSheetHelpdeskFilterBinding bottomSheetHelpdeskFilterBinding3 = this.bindingSheet;
        if (bottomSheetHelpdeskFilterBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bindingSheet");
            bottomSheetHelpdeskFilterBinding3 = null;
        }
        if (bottomSheetHelpdeskFilterBinding3.sortByStatusWait.isChecked()) {
            linkedHashSet.add(TicketStatus.WAIT);
        }
        BottomSheetHelpdeskFilterBinding bottomSheetHelpdeskFilterBinding4 = this.bindingSheet;
        if (bottomSheetHelpdeskFilterBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bindingSheet");
            bottomSheetHelpdeskFilterBinding4 = null;
        }
        if (bottomSheetHelpdeskFilterBinding4.sortByStatusAnswerSent.isChecked()) {
            linkedHashSet.add(TicketStatus.ANSWER_SENT);
        }
        BottomSheetHelpdeskFilterBinding bottomSheetHelpdeskFilterBinding5 = this.bindingSheet;
        if (bottomSheetHelpdeskFilterBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bindingSheet");
            bottomSheetHelpdeskFilterBinding5 = null;
        }
        if (bottomSheetHelpdeskFilterBinding5.sortByStatusOnReview.isChecked()) {
            linkedHashSet.add(TicketStatus.ON_REVIEW);
        }
        BottomSheetHelpdeskFilterBinding bottomSheetHelpdeskFilterBinding6 = this.bindingSheet;
        if (bottomSheetHelpdeskFilterBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bindingSheet");
            bottomSheetHelpdeskFilterBinding6 = null;
        }
        if (bottomSheetHelpdeskFilterBinding6.sortByStatusReviewed.isChecked()) {
            linkedHashSet.add(TicketStatus.REVIEWED);
        }
        BottomSheetHelpdeskFilterBinding bottomSheetHelpdeskFilterBinding7 = this.bindingSheet;
        if (bottomSheetHelpdeskFilterBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bindingSheet");
        } else {
            bottomSheetHelpdeskFilterBinding2 = bottomSheetHelpdeskFilterBinding7;
        }
        if (bottomSheetHelpdeskFilterBinding2.sortByStatusHold.isChecked()) {
            linkedHashSet.add(TicketStatus.HOLD);
        }
        this.settings.setSelectCategory(linkedHashSet);
        if (buttonView.getId() == R.id.sortByDate) {
            this.settings.setSortBy(SortBy.TIME);
        } else if (buttonView.getId() == R.id.sortByPriority) {
            this.settings.setSortBy(SortBy.PRIORITY);
        } else if (buttonView.getId() == R.id.sortByStatus) {
            this.settings.setSortBy(SortBy.STATUS);
        } else if (buttonView.getId() == R.id.sortByCategory) {
            this.settings.setSortBy(SortBy.CATEGORY);
        }
        applySettingChange();
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        getMenuInflater().inflate(R.menu.helpdesk_menu, menu);
        return true;
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (item.getItemId() == R.id.filter) {
            BottomSheetBehavior<LinearLayout> bottomSheetBehavior = this.behavior;
            BottomSheetBehavior<LinearLayout> bottomSheetBehavior2 = null;
            if (bottomSheetBehavior == null) {
                Intrinsics.throwUninitializedPropertyAccessException("behavior");
                bottomSheetBehavior = null;
            }
            if (bottomSheetBehavior.getState() != 3) {
                BottomSheetBehavior<LinearLayout> bottomSheetBehavior3 = this.behavior;
                if (bottomSheetBehavior3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("behavior");
                } else {
                    bottomSheetBehavior2 = bottomSheetBehavior3;
                }
                bottomSheetBehavior2.setState(3);
            } else {
                BottomSheetBehavior<LinearLayout> bottomSheetBehavior4 = this.behavior;
                if (bottomSheetBehavior4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("behavior");
                } else {
                    bottomSheetBehavior2 = bottomSheetBehavior4;
                }
                bottomSheetBehavior2.setState(5);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    /* compiled from: HelpdeskActivity.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0006H\u0016J\u0012\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\t\u001a\u00020\u0006H\u0016¨\u0006\f"}, d2 = {"Lcom/m_myr/nuwm/nuwmschedule/ui/activities/helpdesk/HelpdeskActivity$ViewPagerAdapter;", "Landroidx/fragment/app/FragmentStatePagerAdapter;", "fm", "Landroidx/fragment/app/FragmentManager;", "(Lcom/m_myr/nuwm/nuwmschedule/ui/activities/helpdesk/HelpdeskActivity;Landroidx/fragment/app/FragmentManager;)V", "getCount", "", "getItem", "Landroidx/fragment/app/Fragment;", "position", "getPageTitle", "", "app_publicReleaseRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private final class ViewPagerAdapter extends FragmentStatePagerAdapter {
        final /* synthetic */ HelpdeskActivity this$0;

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            return 2;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewPagerAdapter(HelpdeskActivity helpdeskActivity, FragmentManager fm) {
            super(fm, 1);
            Intrinsics.checkNotNullParameter(fm, "fm");
            this.this$0 = helpdeskActivity;
        }

        @Override // androidx.fragment.app.FragmentStatePagerAdapter
        public Fragment getItem(int position) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("settings", this.this$0.settings);
            bundle.putSerializable("position", Integer.valueOf(position));
            if (position == 0) {
                bundle.putSerializable("assigned", "from_me");
            } else {
                bundle.putSerializable("assigned", "for_me");
            }
            TicketFragment ticketFragment = new TicketFragment();
            ticketFragment.setArguments(bundle);
            return ticketFragment;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public CharSequence getPageTitle(int position) {
            if (position == 0) {
                return "Мої запити";
            }
            if (position != 1) {
                return null;
            }
            return "Призначені мені";
        }
    }
}
