package com.m_myr.nuwm.nuwmschedule.ui.activities.timetable;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.models.TimetableIdentifier;
import com.m_myr.nuwm.nuwmschedule.domain.App;
import com.m_myr.nuwm.nuwmschedule.ui.timetable.TimetableDelegate;
import com.m_myr.nuwm.nuwmschedule.ui.timetable.TimetablePresenter;

/* loaded from: classes2.dex */
public class GeneralTimetableActivity extends AppCompatActivity {
    TimetableDelegate timetableDelegate;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(App.getInstance().getAppTheme());
        setContentView(R.layout.timetable_activity);
        String stringExtra = getIntent().getStringExtra("title");
        if (stringExtra == null) {
            stringExtra = "Розклад";
        }
        TimetableDelegate timetableDelegate = new TimetableDelegate(this, findViewById(R.id.container));
        this.timetableDelegate = timetableDelegate;
        timetableDelegate.setToolbarTitle(stringExtra);
        this.timetableDelegate.replaceRoomToTeacher(getIntent().getBooleanExtra("replaceRoomToTeacher", false));
        new TimetablePresenter(this.timetableDelegate, (TimetableIdentifier) getIntent().getSerializableExtra("identifier")).attachView(this.timetableDelegate);
    }
}
