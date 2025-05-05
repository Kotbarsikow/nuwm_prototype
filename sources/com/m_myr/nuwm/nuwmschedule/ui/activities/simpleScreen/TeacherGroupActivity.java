package com.m_myr.nuwm.nuwmschedule.ui.activities.simpleScreen;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public class TeacherGroupActivity extends AppCompatActivity {
    private ViewPager mViewpager;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_groups_teacher);
        this.mViewpager = (ViewPager) findViewById(R.id.viewpager);
    }
}
