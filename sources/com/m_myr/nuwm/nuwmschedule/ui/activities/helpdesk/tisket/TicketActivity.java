package com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.tisket;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.app.NotificationManagerCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.firebase.messaging.Constants;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.models.helpdesk.TicketReply;
import com.m_myr.nuwm.nuwmschedule.databinding.BottomSheetHelpdeskTicketBinding;
import com.m_myr.nuwm.nuwmschedule.databinding.TicketChatLayoutBinding;
import com.m_myr.nuwm.nuwmschedule.network.APIMethod;
import com.m_myr.nuwm.nuwmschedule.network.models.UploadResponse;
import com.m_myr.nuwm.nuwmschedule.ui.activities.base.BaseStateActivity;
import com.m_myr.nuwm.nuwmschedule.utils.UploadRunnable;
import com.theartofdev.edmodo.cropper.CropImage;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;

/* compiled from: TicketActivity.kt */
@Metadata(d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010 \n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0016\u0010\u001f\u001a\u00020 2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"H\u0016J\u0010\u0010$\u001a\u00020 2\u0006\u0010%\u001a\u00020&H\u0016J\b\u0010'\u001a\u00020 H\u0016J\f\u0010(\u001a\u00060)R\u00020*H\u0016J\"\u0010+\u001a\u00020 2\u0006\u0010,\u001a\u00020&2\u0006\u0010-\u001a\u00020&2\b\u0010.\u001a\u0004\u0018\u00010/H\u0014J\u0012\u00100\u001a\u00020 2\b\u00101\u001a\u0004\u0018\u000102H\u0014J\u0010\u00103\u001a\u0002042\u0006\u00105\u001a\u000206H\u0016J\b\u00107\u001a\u00020 H\u0014J\u0010\u00108\u001a\u0002042\u0006\u00109\u001a\u00020\u0018H\u0016J\u0010\u0010:\u001a\u00020 2\u0006\u0010;\u001a\u000204H\u0016J\u0018\u0010<\u001a\u00020 2\u0006\u0010=\u001a\u0002042\u0006\u0010>\u001a\u000204H\u0016J\u0016\u0010?\u001a\u00020 2\f\u0010@\u001a\b\u0012\u0004\u0012\u00020#0AH\u0016J\u0010\u0010B\u001a\u00020 2\u0006\u0010C\u001a\u000204H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\fX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u001b\u001a\u00020\u001c¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001e¨\u0006D"}, d2 = {"Lcom/m_myr/nuwm/nuwmschedule/ui/activities/helpdesk/tisket/TicketActivity;", "Lcom/m_myr/nuwm/nuwmschedule/ui/activities/base/BaseStateActivity;", "Lcom/m_myr/nuwm/nuwmschedule/ui/activities/helpdesk/tisket/TicketView;", "()V", "adapter", "Lcom/m_myr/nuwm/nuwmschedule/ui/activities/helpdesk/tisket/HelpdeskChatAdapter;", "getAdapter", "()Lcom/m_myr/nuwm/nuwmschedule/ui/activities/helpdesk/tisket/HelpdeskChatAdapter;", "behavior", "Lcom/google/android/material/bottomsheet/BottomSheetBehavior;", "Landroid/widget/LinearLayout;", "binding", "Lcom/m_myr/nuwm/nuwmschedule/databinding/TicketChatLayoutBinding;", "getBinding", "()Lcom/m_myr/nuwm/nuwmschedule/databinding/TicketChatLayoutBinding;", "setBinding", "(Lcom/m_myr/nuwm/nuwmschedule/databinding/TicketChatLayoutBinding;)V", "bindingSheet", "Lcom/m_myr/nuwm/nuwmschedule/databinding/BottomSheetHelpdeskTicketBinding;", "getBindingSheet", "()Lcom/m_myr/nuwm/nuwmschedule/databinding/BottomSheetHelpdeskTicketBinding;", "setBindingSheet", "(Lcom/m_myr/nuwm/nuwmschedule/databinding/BottomSheetHelpdeskTicketBinding;)V", "itemSetting", "Landroid/view/MenuItem;", "mMessageReceiver", "Landroid/content/BroadcastReceiver;", "presenter", "Lcom/m_myr/nuwm/nuwmschedule/ui/activities/helpdesk/tisket/TicketPresenter;", "getPresenter", "()Lcom/m_myr/nuwm/nuwmschedule/ui/activities/helpdesk/tisket/TicketPresenter;", "addUpdate", "", "mutableList", "", "Lcom/m_myr/nuwm/nuwmschedule/data/models/helpdesk/TicketReply;", "cancelNotiffication", "ticketId", "", "clearField", "getTheme", "Landroid/content/res/Resources$Theme;", "Landroid/content/res/Resources;", "onActivityResult", "requestCode", "resultCode", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "", "menu", "Landroid/view/Menu;", "onDestroy", "onOptionsItemSelected", "item", "showButtonLoader", "b", "showButtonStatusChange", "canResolve", "closed", "showList", "replies", "", "showTicketSetting", "isVisible", "app_publicReleaseRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class TicketActivity extends BaseStateActivity implements TicketView {
    private BottomSheetBehavior<LinearLayout> behavior;
    public TicketChatLayoutBinding binding;
    public BottomSheetHelpdeskTicketBinding bindingSheet;
    private MenuItem itemSetting;
    private final TicketPresenter presenter = new TicketPresenter(this);
    private final HelpdeskChatAdapter adapter = new HelpdeskChatAdapter(this);
    private final BroadcastReceiver mMessageReceiver = new BroadcastReceiver() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.tisket.TicketActivity$mMessageReceiver$1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Intrinsics.checkNotNullParameter(intent, "intent");
            TicketActivity.this.getPresenter().receive();
        }
    };

    public final TicketPresenter getPresenter() {
        return this.presenter;
    }

    public final HelpdeskChatAdapter getAdapter() {
        return this.adapter;
    }

    public final TicketChatLayoutBinding getBinding() {
        TicketChatLayoutBinding ticketChatLayoutBinding = this.binding;
        if (ticketChatLayoutBinding != null) {
            return ticketChatLayoutBinding;
        }
        Intrinsics.throwUninitializedPropertyAccessException("binding");
        return null;
    }

    public final void setBinding(TicketChatLayoutBinding ticketChatLayoutBinding) {
        Intrinsics.checkNotNullParameter(ticketChatLayoutBinding, "<set-?>");
        this.binding = ticketChatLayoutBinding;
    }

    public final BottomSheetHelpdeskTicketBinding getBindingSheet() {
        BottomSheetHelpdeskTicketBinding bottomSheetHelpdeskTicketBinding = this.bindingSheet;
        if (bottomSheetHelpdeskTicketBinding != null) {
            return bottomSheetHelpdeskTicketBinding;
        }
        Intrinsics.throwUninitializedPropertyAccessException("bindingSheet");
        return null;
    }

    public final void setBindingSheet(BottomSheetHelpdeskTicketBinding bottomSheetHelpdeskTicketBinding) {
        Intrinsics.checkNotNullParameter(bottomSheetHelpdeskTicketBinding, "<set-?>");
        this.bindingSheet = bottomSheetHelpdeskTicketBinding;
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
        TicketChatLayoutBinding inflate = TicketChatLayoutBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        setBinding(inflate);
        setContentView(getBinding());
        getBinding().getRoot().setBackgroundResource(R.drawable.background_pattern_clean);
        BottomSheetHelpdeskTicketBinding bind = BottomSheetHelpdeskTicketBinding.bind(getBinding().bottomSheet.getRoot());
        Intrinsics.checkNotNullExpressionValue(bind, "bind(...)");
        setBindingSheet(bind);
        BottomSheetBehavior<LinearLayout> from = BottomSheetBehavior.from(getBindingSheet().getRoot());
        Intrinsics.checkNotNullExpressionValue(from, "from(...)");
        this.behavior = from;
        if (from == null) {
            Intrinsics.throwUninitializedPropertyAccessException("behavior");
            from = null;
        }
        from.setState(5);
        getBinding().recyclerView.setAdapter(this.adapter);
        getBinding().send.setEnabled(false);
        getBinding().send.setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.tisket.TicketActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TicketActivity.onCreate$lambda$0(TicketActivity.this, view);
            }
        });
        getBinding().message.addTextChangedListener(new TextWatcher() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.tisket.TicketActivity$onCreate$2
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ImageView imageView = TicketActivity.this.getBinding().send;
                Editable text = TicketActivity.this.getBinding().message.getText();
                imageView.setEnabled(!(text == null || StringsKt.isBlank(text)));
            }
        });
        getBinding().attachFile.setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.tisket.TicketActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TicketActivity.onCreate$lambda$1(TicketActivity.this, view);
            }
        });
        LocalBroadcastManager.getInstance(this).registerReceiver(this.mMessageReceiver, new IntentFilter("HelpdeskPush.MESSAGE_RECEIVE"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$0(TicketActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.presenter.sendMessage(this$0.getBinding().message.getText().toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$1(TicketActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("*/*");
        Intent createChooser = Intent.createChooser(intent, "Choose a file");
        Intrinsics.checkNotNullExpressionValue(createChooser, "createChooser(...)");
        this$0.startActivityForResult(createChooser, 52);
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.tisket.TicketView
    public void showButtonStatusChange(boolean canResolve, boolean closed) {
        getBindingSheet().toggleButtonGroupStatus.setVisibility(canResolve ? 0 : 8);
        getBindingSheet().toggleButtonGroupStatus.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.tisket.TicketActivity$$ExternalSyntheticLambda0
            @Override // com.google.android.material.button.MaterialButtonToggleGroup.OnButtonCheckedListener
            public final void onButtonChecked(MaterialButtonToggleGroup materialButtonToggleGroup, int i, boolean z) {
                TicketActivity.showButtonStatusChange$lambda$2(TicketActivity.this, materialButtonToggleGroup, i, z);
            }
        });
        if (closed) {
            getBindingSheet().toggleButtonGroupStatus.check(R.id.closedStatus);
        } else {
            getBindingSheet().toggleButtonGroupStatus.check(R.id.openedStatus);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showButtonStatusChange$lambda$2(TicketActivity this$0, MaterialButtonToggleGroup materialButtonToggleGroup, int i, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getBindingSheet().loadingOverlay.setVisibility(0);
        if (i == R.id.openedStatus) {
            this$0.getBindingSheet().closedStatus.setBackgroundColor(this$0.getResources().getColor(R.color.grey_20));
            this$0.getBindingSheet().closedStatus.setStrokeColorResource(R.color.grey_300);
            this$0.getBindingSheet().closedStatus.setTextColor(this$0.getResources().getColor(R.color.grey_650));
        }
        if (i == R.id.closedStatus) {
            this$0.getBindingSheet().closedStatus.setBackgroundColor(this$0.getResources().getColor(R.color.greenLight));
            this$0.getBindingSheet().closedStatus.setStrokeColorResource(R.color.greenDark);
            this$0.getBindingSheet().closedStatus.setTextColor(this$0.getResources().getColor(R.color.greenDark));
        }
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        getMenuInflater().inflate(R.menu.helpdesk_ticket_menu, menu);
        MenuItem findItem = menu.findItem(R.id.settings_ticket);
        Intrinsics.checkNotNullExpressionValue(findItem, "findItem(...)");
        this.itemSetting = findItem;
        return true;
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (item.getItemId() == R.id.settings_ticket) {
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

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.tisket.TicketView
    public void clearField() {
        getBinding().progressCard.setVisibility(8);
        getBinding().fileIco.setVisibility(8);
        getBinding().attachFile.setVisibility(0);
        getBinding().message.getText().clear();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.mMessageReceiver);
        super.onDestroy();
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.tisket.TicketView
    public void cancelNotiffication(int ticketId) {
        NotificationManagerCompat from = NotificationManagerCompat.from(this);
        Intrinsics.checkNotNullExpressionValue(from, "from(...)");
        from.cancel(ticketId);
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.tisket.TicketView
    public void showButtonLoader(boolean b) {
        if (b) {
            getBinding().messageSending.setVisibility(0);
            getBinding().send.setVisibility(4);
            getBinding().messageSending.show();
        } else {
            getBinding().messageSending.setVisibility(8);
            getBinding().send.setVisibility(0);
            getBinding().messageSending.hide();
        }
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.tisket.TicketView
    public void addUpdate(final List<TicketReply> mutableList) {
        Intrinsics.checkNotNullParameter(mutableList, "mutableList");
        RecyclerView.LayoutManager layoutManager = getBinding().recyclerView.getLayoutManager();
        Intrinsics.checkNotNull(layoutManager, "null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
        getBinding().recyclerView.postDelayed(new Runnable() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.tisket.TicketActivity$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                TicketActivity.addUpdate$lambda$3(TicketActivity.this, mutableList, linearLayoutManager);
            }
        }, 150L);
        linearLayoutManager.smoothScrollToPosition(getBinding().recyclerView, null, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void addUpdate$lambda$3(TicketActivity this$0, List mutableList, LinearLayoutManager linearLayoutManager) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mutableList, "$mutableList");
        Intrinsics.checkNotNullParameter(linearLayoutManager, "$linearLayoutManager");
        this$0.adapter.addUpdate(mutableList);
        if (linearLayoutManager.findFirstCompletelyVisibleItemPosition() == 0) {
            linearLayoutManager.smoothScrollToPosition(this$0.getBinding().recyclerView, null, 0);
        }
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.tisket.TicketView
    public void showList(List<? extends TicketReply> replies) {
        Intrinsics.checkNotNullParameter(replies, "replies");
        this.adapter.addAll(replies);
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.tisket.TicketView
    public void showTicketSetting(boolean isVisible) {
        MenuItem menuItem = this.itemSetting;
        if (menuItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("itemSetting");
            menuItem = null;
        }
        menuItem.setVisible(isVisible);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 203) {
            CropImage.ActivityResult activityResult = CropImage.getActivityResult(data);
            if (resultCode == -1) {
                UploadRunnable uploadRunnable = new UploadRunnable(this, activityResult.getUri(), new UploadRunnable.ProgressListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.tisket.TicketActivity$onActivityResult$uploadRunnable$1
                    @Override // com.m_myr.nuwm.nuwmschedule.utils.UploadRunnable.ProgressListener
                    public void progressUpload(long num, float p) {
                        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                        String format = String.format("long %s, float %s", Arrays.copyOf(new Object[]{Long.valueOf(num), Float.valueOf(p)}, 2));
                        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                        Log.e("UploadRunnable", format);
                    }

                    @Override // com.m_myr.nuwm.nuwmschedule.utils.UploadRunnable.ProgressListener
                    public void failUpload(String error) {
                        Intrinsics.checkNotNullParameter(error, "error");
                        Log.e("UploadRunnable", "error " + error);
                    }

                    @Override // com.m_myr.nuwm.nuwmschedule.utils.UploadRunnable.ProgressListener
                    public void finishUpload(UploadResponse result) {
                        Intrinsics.checkNotNullParameter(result, "result");
                        Log.e("UploadRunnable", "finishUpload " + result.getUrl());
                    }
                }, APIMethod.Patch.PATCH_HESK_FILE_UPLOAD);
                uploadRunnable.addForm("trackId", this.presenter.getTicketData().ticket.trackId);
                Executors.newSingleThreadExecutor().execute(uploadRunnable);
                return;
            }
            return;
        }
        if (requestCode == 52 && resultCode == -1) {
            Intrinsics.checkNotNull(data);
            Uri data2 = data.getData();
            getBinding().progressCard.setVisibility(0);
            UploadRunnable uploadRunnable2 = new UploadRunnable(this, data2, new UploadRunnable.ProgressListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.tisket.TicketActivity$onActivityResult$uploadRunnable$2
                @Override // com.m_myr.nuwm.nuwmschedule.utils.UploadRunnable.ProgressListener
                public void progressUpload(long num, float p) {
                    int i = (int) p;
                    TicketActivity.this.getBinding().progressBar.setProgress(i);
                    TextView textView = TicketActivity.this.getBinding().progress;
                    StringBuilder sb = new StringBuilder();
                    sb.append(i);
                    sb.append('%');
                    textView.setText(sb.toString());
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    String format = String.format("long %s, float %s", Arrays.copyOf(new Object[]{Long.valueOf(num), Float.valueOf(p)}, 2));
                    Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                    Log.e("UploadRunnable", format);
                }

                @Override // com.m_myr.nuwm.nuwmschedule.utils.UploadRunnable.ProgressListener
                public void failUpload(String error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    Log.e("UploadRunnable", "error " + error);
                }

                @Override // com.m_myr.nuwm.nuwmschedule.utils.UploadRunnable.ProgressListener
                public void finishUpload(UploadResponse result) {
                    Intrinsics.checkNotNullParameter(result, "result");
                    TicketActivity.this.getBinding().progressCard.setVisibility(8);
                    TicketActivity.this.getBinding().fileIco.setVisibility(0);
                    TicketActivity.this.getBinding().attachFile.setVisibility(4);
                    TicketActivity.this.getPresenter().addFile(result.getId() + '#' + result.getName() + result.getExtension());
                    StringBuilder sb = new StringBuilder("finishUpload ");
                    sb.append(result);
                    Log.e("UploadRunnable", sb.toString());
                }
            }, APIMethod.Patch.PATCH_HESK_FILE_UPLOAD);
            uploadRunnable2.addForm("trackId", this.presenter.getTicketData().ticket.trackId);
            Executors.newSingleThreadExecutor().execute(uploadRunnable2);
        }
    }
}
