package com.m_myr.nuwm.nuwmschedule.ui.activities.academicSuccess;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import com.elyeproj.loaderviewlibrary.LoaderTextView;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.models.SubjectEvaluation;
import com.m_myr.nuwm.nuwmschedule.domain.adapter.LessonsAdapter;
import com.m_myr.nuwm.nuwmschedule.ui.activities.base.BaseStateActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.lastMarks.LastMarksActivity;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class AcademicSuccessActivity extends BaseStateActivity implements AcademicSuccessOwner {
    View errorTxt1;
    View errorTxt2;
    ViewGroup listView1;
    ViewGroup listView2;
    private LoaderTextView mLastMark;
    AcademicSuccessPresenterCompat presenter = new AcademicSuccessPresenterCompat(this);

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setScrollContentView(R.layout.academic_success_layout);
        setTitle("Моя успішність");
        this.listView1 = (ViewGroup) findViewById(R.id.half1_place);
        this.listView2 = (ViewGroup) findViewById(R.id.half2_place);
        this.errorTxt1 = findViewById(R.id.error_text_1);
        this.errorTxt2 = findViewById(R.id.error_text_2);
        findViewById(R.id.lastUpdate).setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.academicSuccess.AcademicSuccessActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                AcademicSuccessActivity.this.startActivity(new Intent(AcademicSuccessActivity.this, (Class<?>) LastMarksActivity.class));
            }
        });
        showLoading();
        initView();
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.academicSuccess.AcademicSuccessOwner
    public void showData(ArrayList<SubjectEvaluation> h1, ArrayList<SubjectEvaluation> h2) {
        showContent();
        if (Utils.isEmpty(h1)) {
            this.errorTxt1.setVisibility(0);
        } else {
            LessonsAdapter lessonsAdapter = new LessonsAdapter(this, h1, true, 1);
            for (int i = 0; i < h1.size(); i++) {
                ViewGroup viewGroup = this.listView1;
                viewGroup.addView(lessonsAdapter.getView(i, viewGroup));
            }
        }
        if (Utils.isEmpty(h2)) {
            this.errorTxt2.setVisibility(0);
            return;
        }
        LessonsAdapter lessonsAdapter2 = new LessonsAdapter(this, h2, false, 2);
        for (int i2 = 0; i2 < h2.size(); i2++) {
            this.listView2.addView(lessonsAdapter2.getView(i2, this.listView1));
        }
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.base.BaseStateActivity, com.m_myr.nuwm.nuwmschedule.domain.interfaces.BaseStateView, com.m_myr.nuwm.nuwmschedule.ui.activities.academicSuccess.AcademicSuccessOwner
    public void showError(String text) {
        super.showError(text);
    }

    private void initView() {
        LoaderTextView loaderTextView = (LoaderTextView) findViewById(R.id.last_mark);
        this.mLastMark = loaderTextView;
        loaderTextView.setText(getIntent().getStringExtra("lastText"));
    }
}
