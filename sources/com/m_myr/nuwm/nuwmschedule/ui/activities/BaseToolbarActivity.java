package com.m_myr.nuwm.nuwmschedule.ui.activities;

import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public abstract class BaseToolbarActivity extends AppCompatActivity {
    final void attachToolbar(int id) {
        try {
            setSupportActionBar((Toolbar) super.findViewById(id));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (Exception unused) {
            Log.e("ActivityHelper", "Can't attach toolbar");
        }
    }

    public final void setTitle(String title) {
        setTitle((CharSequence) title);
    }

    @Override // android.app.Activity
    public final void setTitle(CharSequence title) {
        getSupportActionBar().setTitle(title);
    }

    public void attachToolbar() {
        attachToolbar(R.id.toolbar);
    }

    public Toolbar getMaterialToolbar() {
        return (Toolbar) findViewById(R.id.toolbar);
    }

    @Override // androidx.appcompat.app.AppCompatActivity
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
