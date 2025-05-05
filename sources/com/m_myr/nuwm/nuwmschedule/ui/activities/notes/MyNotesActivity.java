package com.m_myr.nuwm.nuwmschedule.ui.activities.notes;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.models.Note;
import com.m_myr.nuwm.nuwmschedule.ui.view.CircleColorView;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;

/* loaded from: classes2.dex */
public class MyNotesActivity extends AppCompatActivity implements TextWatcher, IMyNotesView {
    public static final int NOTE_CREATED = 10;
    public static final int NOTE_DELETE = 12;
    public static final int NOTE_EDITED = 11;
    private BottomSheetBehavior bottomSheetBehavior;
    private EditText mEditNoteTitle;
    private EditText mEditNoteView;
    MyNotesPresenter myNotesPresenter = new MyNotesPresenter(this);

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_edit_activity);
        this.mEditNoteTitle = (EditText) findViewById(R.id.edit_note_title);
        this.mEditNoteView = (EditText) findViewById(R.id.edit_note_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        BottomSheetBehavior from = BottomSheetBehavior.from(findViewById(R.id.bottom_sheet_menu));
        this.bottomSheetBehavior = from;
        from.setState(5);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note, menu);
        return true;
    }

    private void hideSheet() {
        this.bottomSheetBehavior.setState(5);
    }

    public void onClickEditText(View view) {
        hideSheet();
    }

    @Override // androidx.appcompat.app.AppCompatActivity
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        this.myNotesPresenter.onBackPressed();
        super.onBackPressed();
    }

    public void onMenuItemClick(View item) {
        hideSoftKeyboard();
        if (item.getId() != R.id.more) {
            return;
        }
        if (this.bottomSheetBehavior.getState() == 3) {
            this.bottomSheetBehavior.setState(5);
        } else {
            this.bottomSheetBehavior.setState(3);
        }
    }

    public void onClickColor(View v) {
        int color = ((CircleColorView) v).getColor();
        ObjectAnimator.ofObject(findViewById(android.R.id.content), "backgroundColor", new ArgbEvaluator(), Integer.valueOf(this.myNotesPresenter.getColorNote()), Integer.valueOf(color)).setDuration(400L).start();
        ObjectAnimator.ofObject(findViewById(R.id.bottom_sheet_menu), "backgroundColor", new ArgbEvaluator(), Integer.valueOf(this.myNotesPresenter.getColorNote()), Integer.valueOf(color)).setDuration(400L).start();
        ObjectAnimator.ofObject(findViewById(R.id.bottom_app_bar), "backgroundColor", new ArgbEvaluator(), Integer.valueOf(this.myNotesPresenter.getColorNote()), Integer.valueOf(color)).setDuration(400L).start();
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(color));
        Window window = getWindow();
        window.addFlags(Integer.MIN_VALUE);
        window.setStatusBarColor(color);
        window.setNavigationBarColor(color);
        this.myNotesPresenter.setColorNote(color);
        this.myNotesPresenter.onBackPressed();
    }

    public void hideSoftKeyboard() {
        try {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception unused) {
        }
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable s) {
        this.myNotesPresenter.updateText(this.mEditNoteTitle.getText().toString(), this.mEditNoteView.getText().toString());
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.notes.IMyNotesView
    public void attachListener() {
        this.mEditNoteTitle.addTextChangedListener(this);
        this.mEditNoteView.addTextChangedListener(this);
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.notes.IMyNotesView
    public void setData(Note note) {
        this.mEditNoteTitle.setText(note.getTitle());
        this.mEditNoteView.setText(note.getBodyHtml());
        findViewById(android.R.id.content).setBackgroundColor(note.getColorNote());
        findViewById(R.id.bottom_sheet_menu).setBackgroundColor(note.getColorNote());
        findViewById(R.id.bottom_app_bar).setBackgroundColor(note.getColorNote());
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(note.getColorNote()));
        Window window = getWindow();
        window.addFlags(Integer.MIN_VALUE);
        window.setStatusBarColor(note.getColorNote());
        window.setNavigationBarColor(note.getColorNote());
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.notes.IMyNotesView
    public void setChangeResult(int position, Note note) {
        Intent intent = new Intent();
        intent.putExtra("note", note);
        intent.putExtra("position", position);
        setResult(11, intent);
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.notes.IMyNotesView
    public void setInsertResult(Note note) {
        Intent intent = new Intent();
        intent.putExtra("note", note);
        setResult(10, intent);
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, (Class<?>) MyNotesActivity.class);
    }

    public static Intent getIntent(Context context, int position, Note note) {
        Intent intent = new Intent(context, (Class<?>) MyNotesActivity.class);
        intent.putExtra("position", position);
        intent.putExtra("note", note);
        intent.putExtra("new", false);
        return intent;
    }

    public void onClickShare(View view) {
        Utils.showStub(this);
    }

    public void onClickNotification(View view) {
        Utils.showStub(this);
    }

    public void onClickDelete(View view) {
        this.myNotesPresenter.onClickDelete();
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.notes.IMyNotesView
    public void setRemoveResult(int position, Note note) {
        Intent intent = new Intent();
        intent.putExtra("note", note);
        intent.putExtra("position", position);
        setResult(12, intent);
    }

    public void onPin(MenuItem item) {
        Utils.showStub(this);
    }

    public void onClickNotification(MenuItem item) {
        onClickNotification(item.getActionView());
    }
}
