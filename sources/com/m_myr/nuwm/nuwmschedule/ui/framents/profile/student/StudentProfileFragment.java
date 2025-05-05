package com.m_myr.nuwm.nuwmschedule.ui.framents.profile.student;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.repositories.AppDataManager;
import com.m_myr.nuwm.nuwmschedule.ui.activities.academicSuccess.AcademicSuccessActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.individuaCurriculum.IndividualCurriculumActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.semestrEvaluation.SemesterEvaluationActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.simpleScreen.DeskInfoActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.simpleScreen.StudentsListActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.simpleScreen.TeacherGroupActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.statistic.statisticEvaluation.StatisticEvaluationActivity;

/* loaded from: classes2.dex */
public class StudentProfileFragment extends Fragment implements View.OnClickListener, StudentProfileOwner {
    private TextView avgMark;
    private TextView lastMark;
    private StudentProfilePresenterCompat presenter = new StudentProfilePresenterCompat(this);

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_profile, container, false);
        inflate.findViewById(R.id.current_evaluation).setOnClickListener(this);
        inflate.findViewById(R.id.statistic_evaluation).setOnClickListener(this);
        inflate.findViewById(R.id.semestr_evaluation).setOnClickListener(this);
        inflate.findViewById(R.id.individual_curriculum).setOnClickListener(this);
        inflate.findViewById(R.id.info).setOnClickListener(this);
        inflate.findViewById(R.id.my_group).setOnClickListener(this);
        inflate.findViewById(R.id.moodle).setOnClickListener(this);
        this.lastMark = (TextView) inflate.findViewById(R.id.last_mark);
        this.avgMark = (TextView) inflate.findViewById(R.id.avg_mark);
        setUpProfileIcon(inflate);
        return inflate;
    }

    private void setUpProfileIcon(View view) {
        Glide.with(this).load(AppDataManager.getInstance().getUser().getProfileImage()).apply((BaseRequestOptions<?>) RequestOptions.circleCropTransform()).into((ImageView) view.findViewById(R.id.profileIcon));
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (AppDataManager.getInstance().getUserPermission().isIdCard()) {
            view.findViewById(R.id.idCard).setVisibility(0);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.current_evaluation /* 2131362101 */:
                Intent intent = new Intent(getContext(), (Class<?>) AcademicSuccessActivity.class);
                intent.putExtra("lastText", this.lastMark.getText().toString());
                startActivity(intent);
                break;
            case R.id.individual_curriculum /* 2131362286 */:
                startActivity(new Intent(getContext(), (Class<?>) IndividualCurriculumActivity.class));
                break;
            case R.id.info /* 2131362287 */:
                startActivity(new Intent(getContext(), (Class<?>) DeskInfoActivity.class));
                break;
            case R.id.moodle /* 2131362400 */:
                startActivity(new Intent(getContext(), (Class<?>) TeacherGroupActivity.class));
                break;
            case R.id.my_group /* 2131362430 */:
                startActivity(new Intent(getContext(), (Class<?>) StudentsListActivity.class));
                break;
            case R.id.semestr_evaluation /* 2131362612 */:
                startActivity(new Intent(getContext(), (Class<?>) SemesterEvaluationActivity.class));
                break;
            case R.id.statistic_evaluation /* 2131362680 */:
                startActivity(new Intent(getContext(), (Class<?>) StatisticEvaluationActivity.class));
                break;
        }
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.framents.profile.student.StudentProfileOwner
    public void setTextLastMark(String textLastMark) {
        this.lastMark.setText(textLastMark);
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.framents.profile.student.StudentProfileOwner
    public void setTextAvg(String s) {
        this.avgMark.setText(s);
    }
}
