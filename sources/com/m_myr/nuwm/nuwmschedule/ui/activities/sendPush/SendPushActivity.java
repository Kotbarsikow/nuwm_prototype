package com.m_myr.nuwm.nuwmschedule.ui.activities.sendPush;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.models.Lesson;
import com.m_myr.nuwm.nuwmschedule.data.models.Recipient;
import com.m_myr.nuwm.nuwmschedule.domain.adapter.TimetableAdapter;
import com.m_myr.nuwm.nuwmschedule.network.APIMethod;
import com.m_myr.nuwm.nuwmschedule.network.models.UploadResponse;
import com.m_myr.nuwm.nuwmschedule.ui.view.AlertSender;
import com.m_myr.nuwm.nuwmschedule.ui.view.CircleColorView;
import com.m_myr.nuwm.nuwmschedule.ui.view.DocumentHolderItem;
import com.m_myr.nuwm.nuwmschedule.ui.view.StyleCallback;
import com.m_myr.nuwm.nuwmschedule.utils.UploadRunnable;
import com.theartofdev.edmodo.cropper.CropImage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

/* loaded from: classes2.dex */
public class SendPushActivity extends AppCompatActivity implements SendPushOwner {
    private BottomSheetBehavior bottomSheetBehavior;
    private BottomSheetBehavior bottomSheetBehaviorPush;
    private ImageView mAttachImage;
    private RelativeLayout mAttachLayout;
    private LinearLayout mAttachmentsFile;
    private EditText mEditNoteTitle;
    private EditText mEditNoteView;
    private ProgressBar mProgressBar;
    private ScrollView mScrollView;
    private Toolbar mToolbar;
    private SendPushPresenterCompat pushPresenter = new SendPushPresenterCompat(this);

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_push_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.mToolbar = toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        View findViewById = findViewById(R.id.bottom_sheet_push);
        BottomSheetBehavior from = BottomSheetBehavior.from(findViewById(R.id.bottom_sheet));
        this.bottomSheetBehavior = from;
        from.setState(5);
        BottomSheetBehavior from2 = BottomSheetBehavior.from(findViewById);
        this.bottomSheetBehaviorPush = from2;
        from2.setState(5);
        this.mAttachImage = (ImageView) findViewById(R.id.attach_image);
        this.mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        this.mAttachLayout = (RelativeLayout) findViewById(R.id.attach_layout);
        this.mAttachLayout = (RelativeLayout) findViewById(R.id.attach_layout);
        this.mAttachmentsFile = (LinearLayout) findViewById(R.id.attachments_file);
        this.mScrollView = (ScrollView) findViewById(R.id.scrollView);
        this.mEditNoteTitle = (EditText) findViewById(R.id.edit_note_title);
        EditText editText = (EditText) findViewById(R.id.edit_note_view);
        this.mEditNoteView = editText;
        editText.setCustomSelectionActionModeCallback(new StyleCallback(this.mEditNoteView));
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.push_menu, menu);
        return true;
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.send) {
            this.pushPresenter.setText(this.mEditNoteTitle.getText().toString(), this.mEditNoteView.getText().toString());
            this.pushPresenter.onSendClick();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override // androidx.appcompat.app.AppCompatActivity
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void onClickColor(View v) {
        int color = ((CircleColorView) v).getColor();
        this.pushPresenter.selectColor(color);
        ObjectAnimator.ofObject(findViewById(android.R.id.content), "backgroundColor", new ArgbEvaluator(), Integer.valueOf(color), Integer.valueOf(color)).setDuration(400L).start();
        ObjectAnimator.ofObject(findViewById(R.id.bottom_sheet_push), "backgroundColor", new ArgbEvaluator(), Integer.valueOf(color), Integer.valueOf(color)).setDuration(400L).start();
        ObjectAnimator.ofObject(findViewById(R.id.bottom_sheet), "backgroundColor", new ArgbEvaluator(), Integer.valueOf(color), Integer.valueOf(color)).setDuration(400L).start();
        ObjectAnimator.ofObject(findViewById(R.id.bottom_app_bar), "backgroundColor", new ArgbEvaluator(), Integer.valueOf(color), Integer.valueOf(color)).setDuration(400L).start();
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(color));
        Window window = getWindow();
        window.addFlags(Integer.MIN_VALUE);
        window.setStatusBarColor(color);
        window.setNavigationBarColor(color);
    }

    public void hideSoftKeyboard() {
        try {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception unused) {
        }
    }

    public void onMenuItemClick(View item) {
        hideSoftKeyboard();
        int id = item.getId();
        if (id == R.id.attach) {
            this.bottomSheetBehaviorPush.setState(5);
            if (this.bottomSheetBehavior.getState() == 3) {
                this.bottomSheetBehavior.setState(5);
                return;
            } else {
                this.bottomSheetBehavior.setState(3);
                return;
            }
        }
        if (id != R.id.more) {
            return;
        }
        this.bottomSheetBehavior.setState(5);
        if (this.bottomSheetBehaviorPush.getState() == 3) {
            this.bottomSheetBehaviorPush.setState(5);
        } else {
            this.bottomSheetBehaviorPush.setState(3);
        }
    }

    private void hideSheet() {
        this.bottomSheetBehavior.setState(5);
        this.bottomSheetBehaviorPush.setState(5);
    }

    public void onClickAttachFile(View view) {
        hideSheet();
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("*/*");
        startActivityForResult(Intent.createChooser(intent, "Choose a file"), 52);
    }

    public void onClickAttachImage(View view) {
        hideSheet();
        CropImage.activity().setOutputCompressQuality(80).start(this);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 203) {
            CropImage.ActivityResult activityResult = CropImage.getActivityResult(data);
            if (resultCode == -1) {
                Uri uri = activityResult.getUri();
                this.mAttachImage.setImageURI(uri);
                this.mAttachImage.setAlpha(0.33f);
                this.mAttachLayout.setVisibility(0);
                Executors.newSingleThreadExecutor().execute(new UploadRunnable(this, uri, new UploadRunnable.ProgressListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.sendPush.SendPushActivity.1
                    @Override // com.m_myr.nuwm.nuwmschedule.utils.UploadRunnable.ProgressListener
                    public void progressUpload(long num, float p) {
                        SendPushActivity.this.mProgressBar.setProgress((int) p);
                        Log.e("UploadRunnable", String.format("long %s, float %s", Long.valueOf(num), Float.valueOf(p)));
                    }

                    @Override // com.m_myr.nuwm.nuwmschedule.utils.UploadRunnable.ProgressListener
                    public void failUpload(String error) {
                        Log.e("UploadRunnable", "error " + error);
                    }

                    @Override // com.m_myr.nuwm.nuwmschedule.utils.UploadRunnable.ProgressListener
                    public void finishUpload(UploadResponse result) {
                        Log.e("UploadRunnable", "finishUpload " + result.getUrl());
                        SendPushActivity.this.pushPresenter.setImage(result.getUrl());
                        SendPushActivity.this.mProgressBar.setVisibility(4);
                        SendPushActivity.this.mAttachImage.setAlpha(1.0f);
                        SendPushActivity.this.mScrollView.invalidate();
                    }
                }, APIMethod.Patch.getPatchFileUpload()));
                return;
            }
            return;
        }
        if (requestCode == 52 && resultCode == -1) {
            final Uri data2 = data.getData();
            this.mAttachmentsFile.setVisibility(0);
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setProgressStyle(1);
            progressDialog.setTitle("Завантаження файлу");
            progressDialog.setMax(100);
            progressDialog.show();
            Executors.newSingleThreadExecutor().execute(new UploadRunnable(this, data2, new UploadRunnable.ProgressListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.sendPush.SendPushActivity.2
                @Override // com.m_myr.nuwm.nuwmschedule.utils.UploadRunnable.ProgressListener
                public void progressUpload(long num, float p) {
                    progressDialog.setProgress((int) p);
                    Log.e("UploadRunnable", String.format("long %s, float %s", Long.valueOf(num), Float.valueOf(p)));
                }

                @Override // com.m_myr.nuwm.nuwmschedule.utils.UploadRunnable.ProgressListener
                public void failUpload(String error) {
                    progressDialog.dismiss();
                    Log.e("UploadRunnable", "error " + error);
                }

                @Override // com.m_myr.nuwm.nuwmschedule.utils.UploadRunnable.ProgressListener
                public void finishUpload(UploadResponse result) {
                    progressDialog.dismiss();
                    Log.e("UploadRunnable", "finishUpload " + result);
                    SendPushActivity.this.mAttachmentsFile.addView(new DocumentHolderItem(SendPushActivity.this.getLayoutInflater()).init(result, data2, SendPushActivity.this).getView());
                    SendPushActivity.this.mScrollView.invalidate();
                    SendPushActivity.this.mProgressBar.setVisibility(4);
                    SendPushActivity.this.mAttachImage.setAlpha(1.0f);
                    SendPushActivity.this.pushPresenter.addFile(result);
                }
            }, APIMethod.Patch.getPatchFileUpload()));
        }
    }

    public void onClickEditText(View view) {
        hideSheet();
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.sendPush.SendPushOwner
    public void attachLesson(Lesson lesson) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(lesson);
        TimetableAdapter timetableAdapter = new TimetableAdapter(this, false);
        timetableAdapter.setData(arrayList);
        timetableAdapter.onBindViewHolder((TimetableAdapter.LessonViewHolderItem) timetableAdapter.onCreateViewHolder(this.mAttachmentsFile, 0), 0);
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.sendPush.SendPushOwner
    public void showInfo(String s) {
        Toast.makeText(this, s, 0).show();
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.sendPush.SendPushOwner
    public void showAlertRecipients(List<String> selectedRecipient, ArrayList<Recipient> recipients) {
        AlertSender alertSender = new AlertSender(this);
        alertSender.setRecipient(selectedRecipient, recipients);
        alertSender.show();
    }
}
