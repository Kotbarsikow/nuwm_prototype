package com.m_myr.nuwm.nuwmschedule.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public class DashboardFragment extends Fragment {
    private DashboardViewModel dashboardViewModel;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.dashboardViewModel = (DashboardViewModel) ViewModelProviders.of(this).get(DashboardViewModel.class);
        View inflate = inflater.inflate(R.layout.fragment_dashboard, container, false);
        final TextView textView = (TextView) inflate.findViewById(R.id.text_dashboard);
        this.dashboardViewModel.getText().observe(this, new Observer<String>() { // from class: com.m_myr.nuwm.nuwmschedule.ui.dashboard.DashboardFragment.1
            @Override // androidx.lifecycle.Observer
            public void onChanged(String s) {
                textView.setText(s);
            }
        });
        return inflate;
    }
}
