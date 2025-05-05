package com.m_myr.nuwm.nuwmschedule.ui.activities.worload;

import android.os.Bundle;
import android.widget.TextView;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.models.HalfWorkload;
import com.m_myr.nuwm.nuwmschedule.domain.App;
import com.m_myr.nuwm.nuwmschedule.ui.activities.BaseToolbarActivity;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;

/* loaded from: classes2.dex */
public class WorkloadSubjectActivity extends BaseToolbarActivity {
    HalfWorkload halfWorkload;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(App.getInstance().getAppTheme());
        setContentView(R.layout.workload_subject_activity);
        attachToolbar();
        this.halfWorkload = (HalfWorkload) getIntent().getSerializableExtra("workload");
        ((TextView) findViewById(R.id.faculty_name)).setText(this.halfWorkload.facultyName);
        ((TextView) findViewById(R.id.chair)).setText(this.halfWorkload.chair);
        ((TextView) findViewById(R.id.prof_name)).setText(this.halfWorkload.profName);
        ((TextView) findViewById(R.id.form_navch)).setText(this.halfWorkload.formNavch);
        ((TextView) findViewById(R.id.formfin)).setText(this.halfWorkload.formfin);
        ((TextView) findViewById(R.id.group_name)).setText(Utils.StringUtils.safeTrim(this.halfWorkload.groupName) + ", " + this.halfWorkload.subgroup + " підгрупа");
        ((TextView) findViewById(R.id.countstud)).setText(String.valueOf(this.halfWorkload.countstud));
        String safeTrim = Utils.StringUtils.safeTrim(this.halfWorkload.lectionsStream);
        String safeTrim2 = Utils.StringUtils.safeTrim(this.halfWorkload.partsCompGroup);
        if (!safeTrim.isEmpty()) {
            ((TextView) findViewById(R.id.lections_stream)).setText(safeTrim);
        }
        if (!safeTrim2.isEmpty()) {
            ((TextView) findViewById(R.id.lections_stream)).setText(safeTrim2);
        }
        ((TextView) findViewById(R.id.lect)).setText(String.valueOf(this.halfWorkload.lect));
        ((TextView) findViewById(R.id.independ)).setText(String.valueOf(this.halfWorkload.independ));
        ((TextView) findViewById(R.id.pract)).setText(String.valueOf(this.halfWorkload.pract));
        ((TextView) findViewById(R.id.lab)).setText(String.valueOf(this.halfWorkload.lab));
        ((TextView) findViewById(R.id.exam)).setText(String.valueOf(this.halfWorkload.exam));
        ((TextView) findViewById(R.id.pftest)).setText(String.valueOf(this.halfWorkload.pftest));
        ((TextView) findViewById(R.id.consult)).setText(String.valueOf(this.halfWorkload.consult));
        ((TextView) findViewById(R.id.consult_exam)).setText(String.valueOf(this.halfWorkload.consultExam));
        ((TextView) findViewById(R.id.control)).setText(String.valueOf(this.halfWorkload.control));
        ((TextView) findViewById(R.id.course_prj)).setText(String.valueOf(this.halfWorkload.coursePrj));
        ((TextView) findViewById(R.id.rgr)).setText(String.valueOf(this.halfWorkload.rgr));
        ((TextView) findViewById(R.id.course_work)).setText(String.valueOf(this.halfWorkload.courseWork));
        ((TextView) findViewById(R.id.ind_contr)).setText(String.valueOf(this.halfWorkload.indContr));
        ((TextView) findViewById(R.id.pract_contr)).setText(String.valueOf(this.halfWorkload.practContr));
    }
}
