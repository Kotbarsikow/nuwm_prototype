package com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.createTicket;

import android.R;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.core.view.ViewGroupKt;
import com.google.firebase.messaging.Constants;
import com.m_myr.nuwm.nuwmschedule.data.models.DocumentInfo;
import com.m_myr.nuwm.nuwmschedule.data.models.HelpdeskCategory;
import com.m_myr.nuwm.nuwmschedule.databinding.CreateNewTicketActivityBinding;
import com.m_myr.nuwm.nuwmschedule.network.APIMethod;
import com.m_myr.nuwm.nuwmschedule.network.models.UploadResponse;
import com.m_myr.nuwm.nuwmschedule.ui.activities.BaseToolbarActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.createTicket.CreateTicketView;
import com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.tisket.TicketActivity;
import com.m_myr.nuwm.nuwmschedule.ui.view.MaterialFutureDocumentHolderItem;
import com.m_myr.nuwm.nuwmschedule.ui.view.StyleCallback;
import com.m_myr.nuwm.nuwmschedule.utils.UploadRunnable;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;
import com.theartofdev.edmodo.cropper.CropImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.Executors;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;

/* compiled from: CreateTicketActivity.kt */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\"\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u00152\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0014J\u0012\u0010\u001b\u001a\u00020\u00132\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0014J\u0016\u0010\u001e\u001a\u00020\u00132\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 H\u0016J\u0012\u0010\"\u001a\u00020\u00132\b\u0010#\u001a\u0004\u0018\u00010$H\u0016J\u0010\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020&H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006("}, d2 = {"Lcom/m_myr/nuwm/nuwmschedule/ui/activities/helpdesk/createTicket/CreateTicketActivity;", "Lcom/m_myr/nuwm/nuwmschedule/ui/activities/BaseToolbarActivity;", "Lcom/m_myr/nuwm/nuwmschedule/ui/activities/helpdesk/createTicket/CreateTicketView;", "()V", "presenter", "Lcom/m_myr/nuwm/nuwmschedule/ui/activities/helpdesk/createTicket/CreateTicketPresenter;", "getPresenter", "()Lcom/m_myr/nuwm/nuwmschedule/ui/activities/helpdesk/createTicket/CreateTicketPresenter;", "textWatcher", "Landroid/text/TextWatcher;", "getTextWatcher", "()Landroid/text/TextWatcher;", "view", "Lcom/m_myr/nuwm/nuwmschedule/databinding/CreateNewTicketActivityBinding;", "getView", "()Lcom/m_myr/nuwm/nuwmschedule/databinding/CreateNewTicketActivityBinding;", "setView", "(Lcom/m_myr/nuwm/nuwmschedule/databinding/CreateNewTicketActivityBinding;)V", "created", "", "id", "", "onActivityResult", "requestCode", "resultCode", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setCategories", "list", "Ljava/util/ArrayList;", "Lcom/m_myr/nuwm/nuwmschedule/data/models/HelpdeskCategory;", "showError", "message", "", "validate", "", "finally", "app_publicReleaseRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CreateTicketActivity extends BaseToolbarActivity implements CreateTicketView {
    private final CreateTicketPresenter presenter = new CreateTicketPresenter(this);
    private final TextWatcher textWatcher = new TextWatcher() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.createTicket.CreateTicketActivity$textWatcher$1
        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable s) {
            CreateTicketView.DefaultImpls.validate$default(CreateTicketActivity.this, false, 1, null);
        }
    };
    public CreateNewTicketActivityBinding view;

    public final CreateNewTicketActivityBinding getView() {
        CreateNewTicketActivityBinding createNewTicketActivityBinding = this.view;
        if (createNewTicketActivityBinding != null) {
            return createNewTicketActivityBinding;
        }
        Intrinsics.throwUninitializedPropertyAccessException("view");
        return null;
    }

    public final void setView(CreateNewTicketActivityBinding createNewTicketActivityBinding) {
        Intrinsics.checkNotNullParameter(createNewTicketActivityBinding, "<set-?>");
        this.view = createNewTicketActivityBinding;
    }

    public final CreateTicketPresenter getPresenter() {
        return this.presenter;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.createTicket.CreateTicketView
    public void showError(String message) {
        Toast.makeText(this, message, 0).show();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CreateNewTicketActivityBinding inflate = CreateNewTicketActivityBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        setView(inflate);
        setContentView(getView().getRoot());
        attachToolbar();
        getView().ticketMessage.setCustomSelectionActionModeCallback(new StyleCallback(getView().ticketMessage, getView().textFormat));
        getView().attach.setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.createTicket.CreateTicketActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CreateTicketActivity.onCreate$lambda$0(CreateTicketActivity.this, view);
            }
        });
        getView().send.setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.createTicket.CreateTicketActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CreateTicketActivity.onCreate$lambda$1(CreateTicketActivity.this, view);
            }
        });
        if (StringsKt.equals("xiaomi", Build.MANUFACTURER, true)) {
            getView().textFormat.setVisibility(0);
        }
        EditText editText = getView().textField.getEditText();
        Intrinsics.checkNotNull(editText, "null cannot be cast to non-null type android.widget.AutoCompleteTextView");
        ((AutoCompleteTextView) editText).setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.createTicket.CreateTicketActivity$$ExternalSyntheticLambda2
            @Override // android.widget.AdapterView.OnItemClickListener
            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                CreateTicketActivity.onCreate$lambda$3(CreateTicketActivity.this, adapterView, view, i, j);
            }
        });
        getView().ticketMessage.addTextChangedListener(this.textWatcher);
        EditText editText2 = getView().ticketTheme.getEditText();
        Intrinsics.checkNotNull(editText2);
        editText2.addTextChangedListener(this.textWatcher);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$0(CreateTicketActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.getView().attachmentsFile.getChildCount() >= 4) {
            this$0.getView().attach.setVisibility(8);
            Toast.makeText(this$0, "Неможливо додати більше файлів", 0).show();
            return;
        }
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("*/*");
        Intent createChooser = Intent.createChooser(intent, "Оберіть файл");
        Intrinsics.checkNotNullExpressionValue(createChooser, "createChooser(...)");
        this$0.startActivityForResult(createChooser, 52);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$1(CreateTicketActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        CreateTicketPresenter createTicketPresenter = this$0.presenter;
        Editable text = this$0.getView().ticketMessage.getText();
        EditText editText = this$0.getView().ticketTheme.getEditText();
        Intrinsics.checkNotNull(editText);
        createTicketPresenter.send(text, editText.getText());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$3(CreateTicketActivity this$0, AdapterView adapterView, View view, int i, long j) {
        View view2;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        EditText editText = this$0.getView().textField.getEditText();
        Intrinsics.checkNotNull(editText, "null cannot be cast to non-null type android.widget.AutoCompleteTextView");
        Object item = ((AutoCompleteTextView) editText).getAdapter().getItem(i);
        Intrinsics.checkNotNull(item, "null cannot be cast to non-null type com.m_myr.nuwm.nuwmschedule.data.models.HelpdeskCategory");
        HelpdeskCategory helpdeskCategory = (HelpdeskCategory) item;
        this$0.getView().textField.setErrorEnabled(false);
        this$0.presenter.selectedCategory(helpdeskCategory);
        LinearLayout attachmentsFile = this$0.getView().attachmentsFile;
        Intrinsics.checkNotNullExpressionValue(attachmentsFile, "attachmentsFile");
        Iterator<View> it = ViewGroupKt.getChildren(attachmentsFile).iterator();
        while (true) {
            if (!it.hasNext()) {
                view2 = null;
                break;
            } else {
                view2 = it.next();
                if (view2 instanceof CheckBox) {
                    break;
                }
            }
        }
        View view3 = view2;
        if (view3 != null) {
            this$0.getView().attachmentsFile.removeView(view3);
        }
        if (helpdeskCategory.id == 44) {
            this$0.getView().attachmentsCard.setVisibility(0);
            CheckBox checkBox = new CheckBox(this$0);
            checkBox.setText("Звіти додатку та інформація про вас");
            checkBox.setChecked(true);
            checkBox.setPadding(Utils.dpToPx(10), Utils.dpToPx(12), Utils.dpToPx(8), Utils.dpToPx(14));
            checkBox.setEnabled(false);
            checkBox.setClickable(true);
            this$0.getView().attachmentsFile.addView(checkBox);
        }
        if (this$0.getView().attachmentsFile.getChildCount() < 2) {
            this$0.getView().attachmentsCard.setVisibility(8);
        }
    }

    public final TextWatcher getTextWatcher() {
        return this.textWatcher;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.createTicket.CreateTicketView
    public boolean validate(boolean r3) {
        EditText editText = getView().textField.getEditText();
        Intrinsics.checkNotNull(editText);
        Editable text = editText.getText();
        if (text == null || StringsKt.isBlank(text) || this.presenter.getSeletedHelpdeskCategory() == null) {
            if (r3) {
                getView().textField.setError("Виберіть катеорію");
            }
            return false;
        }
        getView().textField.setErrorEnabled(false);
        Editable text2 = getView().ticketMessage.getText();
        if (text2 == null || StringsKt.isBlank(text2)) {
            if (r3) {
                getView().ticketMessageLayout.setError("Детально поясніть свою проблему");
            }
            return false;
        }
        getView().ticketMessageLayout.setErrorEnabled(false);
        EditText editText2 = getView().ticketTheme.getEditText();
        Intrinsics.checkNotNull(editText2);
        Editable text3 = editText2.getText();
        if (text3 != null && !StringsKt.isBlank(text3)) {
            getView().ticketTheme.setErrorEnabled(false);
            return true;
        }
        if (r3) {
            getView().ticketTheme.setError("Коротко опишіть проблему");
        }
        return false;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.createTicket.CreateTicketView
    public void created(int id) {
        Intent intent = new Intent(this, (Class<?>) TicketActivity.class);
        intent.putExtra("ticket_id", id);
        setResult(4);
        startActivity(intent);
        finishAfterTransition();
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.createTicket.CreateTicketView
    public void setCategories(ArrayList<HelpdeskCategory> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.simple_dropdown_item_1line, list);
        EditText editText = getView().textField.getEditText();
        Intrinsics.checkNotNull(editText, "null cannot be cast to non-null type android.widget.AutoCompleteTextView");
        ((AutoCompleteTextView) editText).setAdapter(arrayAdapter);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 203) {
            CropImage.ActivityResult activityResult = CropImage.getActivityResult(data);
            if (resultCode == -1) {
                Executors.newSingleThreadExecutor().execute(new UploadRunnable(this, activityResult.getUri(), new UploadRunnable.ProgressListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.createTicket.CreateTicketActivity$onActivityResult$uploadRunnable$1
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
                }, APIMethod.Patch.PATCH_HESK_FILE_UPLOAD));
                return;
            }
            return;
        }
        if (requestCode == 52 && resultCode == -1) {
            Intrinsics.checkNotNull(data);
            Uri data2 = data.getData();
            getView().attachmentsCard.setVisibility(0);
            LayoutInflater layoutInflater = getLayoutInflater();
            Intrinsics.checkNotNullExpressionValue(layoutInflater, "getLayoutInflater(...)");
            LinearLayout attachmentsFile = getView().attachmentsFile;
            Intrinsics.checkNotNullExpressionValue(attachmentsFile, "attachmentsFile");
            MaterialFutureDocumentHolderItem materialFutureDocumentHolderItem = new MaterialFutureDocumentHolderItem(layoutInflater, attachmentsFile);
            CreateTicketActivity createTicketActivity = this;
            String fileName = UploadRunnable.getFileName(createTicketActivity, data2);
            Intrinsics.checkNotNullExpressionValue(fileName, "getFileName(...)");
            final MaterialFutureDocumentHolderItem future = materialFutureDocumentHolderItem.future(fileName);
            getView().attachmentsFile.addView(future.getView());
            Executors.newSingleThreadExecutor().execute(new UploadRunnable(createTicketActivity, data2, new UploadRunnable.ProgressListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.createTicket.CreateTicketActivity$onActivityResult$uploadRunnable$2
                @Override // com.m_myr.nuwm.nuwmschedule.utils.UploadRunnable.ProgressListener
                public void progressUpload(long num, float p) {
                    MaterialFutureDocumentHolderItem.this.setProgress((int) p);
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
                    this.getPresenter().addFile(result);
                    MaterialFutureDocumentHolderItem.this.initDocument((DocumentInfo) result);
                    Log.e("UploadRunnable", "finishUpload " + result);
                }
            }, APIMethod.Patch.PATCH_HESK_FILE_UPLOAD));
        }
    }
}
