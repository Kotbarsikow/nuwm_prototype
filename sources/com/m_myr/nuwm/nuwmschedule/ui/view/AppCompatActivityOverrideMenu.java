package com.m_myr.nuwm.nuwmschedule.ui.view;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;

/* loaded from: classes2.dex */
public class AppCompatActivityOverrideMenu extends AppCompatActivity implements View.OnClickListener {
    public boolean onOptionsItemSelected(View v) {
        return false;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public final void createMenu(int... vars) {
        for (int i : vars) {
            findViewById(i).setOnClickListener(this);
            findViewById(i).setVisibility(0);
        }
    }

    public final void createMenuFromView(int idView) {
        ViewGroup viewGroup = (ViewGroup) findViewById(idView);
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            viewGroup.getChildAt(i).setOnClickListener(this);
        }
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View v) {
        onOptionsItemSelected(v);
    }
}
