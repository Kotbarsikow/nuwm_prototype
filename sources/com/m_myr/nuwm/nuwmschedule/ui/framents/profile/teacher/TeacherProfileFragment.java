package com.m_myr.nuwm.nuwmschedule.ui.framents.profile.teacher;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.repositories.AppDataManager;
import com.m_myr.nuwm.nuwmschedule.ui.activities.teacherElevat.myGroupsElevat.MyGroupsEvaluation;
import com.m_myr.nuwm.nuwmschedule.ui.activities.teacherGroups.MyGroupsActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.worload.WorkloadActivity;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;

/* loaded from: classes2.dex */
public class TeacherProfileFragment extends Fragment implements TeacherProfileOwner, View.OnClickListener {
    private TeacherProfilePresenterCompat presenter = new TeacherProfilePresenterCompat(this);

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_profile_teacher, container, false);
        inflate.findViewById(R.id.workload).setOnClickListener(this);
        inflate.findViewById(R.id.my_group).setOnClickListener(this);
        inflate.findViewById(R.id.current_evaluation).setOnClickListener(this);
        inflate.findViewById(R.id.inplan).setOnClickListener(this);
        inflate.findViewById(R.id.finance).setOnClickListener(this);
        inflate.findViewById(R.id.administration).setOnClickListener(this);
        if (AppDataManager.getInstance().getUserPermission().isIdCard()) {
            inflate.findViewById(R.id.idCard).setVisibility(0);
        }
        setUpProfileIcon(inflate);
        return inflate;
    }

    private void setUpProfileIcon(View view) {
        Glide.with(this).load(AppDataManager.getInstance().getUser().getProfileImage()).apply((BaseRequestOptions<?>) RequestOptions.circleCropTransform()).into((ImageView) view.findViewById(R.id.profileIcon));
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.current_evaluation) {
            startActivity(new Intent(getContext(), (Class<?>) MyGroupsEvaluation.class));
            return;
        }
        if (id == R.id.my_group) {
            startActivity(new Intent(getContext(), (Class<?>) MyGroupsActivity.class));
        } else if (id == R.id.workload) {
            startActivity(new Intent(getContext(), (Class<?>) WorkloadActivity.class));
        } else {
            Utils.showStub(getContext());
        }
    }
}
