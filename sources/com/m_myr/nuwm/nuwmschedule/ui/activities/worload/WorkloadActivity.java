package com.m_myr.nuwm.nuwmschedule.ui.activities.worload;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.models.Workload;
import com.m_myr.nuwm.nuwmschedule.data.repositories.FastRepository;
import com.m_myr.nuwm.nuwmschedule.domain.App;
import com.m_myr.nuwm.nuwmschedule.network.ErrorResponse;
import com.m_myr.nuwm.nuwmschedule.network.api.APIMethods;
import com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback;
import com.m_myr.nuwm.nuwmschedule.ui.activities.base.BaseStateActivity;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;

/* loaded from: classes2.dex */
public class WorkloadActivity extends BaseStateActivity implements RequestObjectCallback<Workload> {
    private Workload data;
    private TextView mValueAll;
    private TextView mValueHalf1;
    private TextView mValueHalf2;
    private TabLayout tabLayout;
    private ViewPager viewpager;

    public static void start(Context context, int id, String groupName) {
        Intent intent = new Intent(context, (Class<?>) WorkloadActivity.class);
        intent.putExtra(FirebaseAnalytics.Param.GROUP_ID, id);
        intent.putExtra("group_name", groupName);
        context.startActivity(intent);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(App.getInstance().getAppTheme());
        setContentView(R.layout.workload_activity_layout);
        this.viewpager = (ViewPager) findViewById(R.id.viewpager);
        this.tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        this.mValueHalf1 = (TextView) findViewById(R.id.value_half1);
        this.mValueHalf2 = (TextView) findViewById(R.id.value_half2);
        this.mValueAll = (TextView) findViewById(R.id.value_all);
        setTitle(Utils.StringUtils.elvis(getIntent().getStringExtra("group_name"), "Педагогічне навантаження"));
        loadData();
    }

    private void loadData() {
        showLoading();
        if (getIntent().getIntExtra(FirebaseAnalytics.Param.GROUP_ID, 0) != 0) {
            FastRepository.from(this).call(APIMethods.getWorkload(getIntent().getIntExtra(FirebaseAnalytics.Param.GROUP_ID, 0))).into(this).start();
        } else {
            FastRepository.from(this).call(APIMethods.getWorkload()).into(this).start();
        }
    }

    @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
    public void onError(ErrorResponse response) {
        showError(response.getMessage());
    }

    @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
    public void onSuccessData(Workload data) {
        this.data = data;
        this.viewpager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        this.tabLayout.setupWithViewPager(this.viewpager);
        this.viewpager.setOffscreenPageLimit(2);
        this.viewpager.setCurrentItem(App.currentSemestr - 1);
        this.mValueAll.setText(String.valueOf(data.getFirstHalfHours() + data.getSecondHalfHours()));
        this.mValueHalf1.setText(String.valueOf(data.getFirstHalfHours()));
        this.mValueHalf2.setText(String.valueOf(data.getSecondHalfHours()));
        showContent();
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.base.BaseStateActivity
    public void OnRetry(View view) {
        loadData();
    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {
        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            return 2;
        }

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm, 1);
        }

        @Override // androidx.fragment.app.FragmentStatePagerAdapter
        public Fragment getItem(int position) {
            Bundle bundle = new Bundle();
            if (position == 0) {
                bundle.putSerializable(Constants.ScionAnalytics.MessageType.DATA_MESSAGE, WorkloadActivity.this.data.getFirstHalf());
            }
            if (position == 1) {
                bundle.putSerializable(Constants.ScionAnalytics.MessageType.DATA_MESSAGE, WorkloadActivity.this.data.getSecondHalf());
            }
            WorkloadHalfFragment workloadHalfFragment = new WorkloadHalfFragment();
            workloadHalfFragment.setArguments(bundle);
            return workloadHalfFragment;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public CharSequence getPageTitle(int position) {
            if (position == 0) {
                return "I Півріччя";
            }
            if (position != 1) {
                return null;
            }
            return "II Півріччя";
        }
    }
}
