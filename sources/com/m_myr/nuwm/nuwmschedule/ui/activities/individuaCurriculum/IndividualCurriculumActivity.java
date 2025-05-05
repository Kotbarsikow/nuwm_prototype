package com.m_myr.nuwm.nuwmschedule.ui.activities.individuaCurriculum;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.messaging.Constants;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.models.IndividualCurriculum;
import com.m_myr.nuwm.nuwmschedule.data.repositories.FastRepository;
import com.m_myr.nuwm.nuwmschedule.network.ErrorResponse;
import com.m_myr.nuwm.nuwmschedule.network.api.APIMethods;
import com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback;
import com.m_myr.nuwm.nuwmschedule.ui.activities.base.BaseStateActivity;

/* loaded from: classes2.dex */
public class IndividualCurriculumActivity extends BaseStateActivity implements RequestObjectCallback<IndividualCurriculum> {
    private IndividualCurriculum data;
    private LinearLayout mListContainer;
    private TabLayout tabLayout;
    private ViewPager viewpager;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager_activity_layout);
        this.viewpager = (ViewPager) findViewById(R.id.viewpager);
        this.tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        setTitle("Індивідуальний навчальний план");
        this.mListContainer = (LinearLayout) findViewById(R.id.list_container);
        loadData();
    }

    private void loadData() {
        setActivityState(1);
        FastRepository.from(this).call(APIMethods.getCurriculum()).into(this).start();
    }

    @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
    public void onError(ErrorResponse response) {
        setActivityState(-1, response.getMessage());
    }

    @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
    public void onSuccessData(IndividualCurriculum data) {
        this.data = data;
        this.viewpager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        this.tabLayout.setupWithViewPager(this.viewpager);
        this.viewpager.setOffscreenPageLimit(3);
        setActivityState(0);
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.base.BaseStateActivity
    public void OnRetry(View view) {
        loadData();
    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {
        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            return 3;
        }

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm, 1);
        }

        @Override // androidx.fragment.app.FragmentStatePagerAdapter
        public Fragment getItem(int position) {
            Bundle bundle = new Bundle();
            if (position == 0) {
                bundle.putSerializable(Constants.ScionAnalytics.MessageType.DATA_MESSAGE, IndividualCurriculumActivity.this.data.getFirstSemester());
            }
            if (position == 1) {
                bundle.putSerializable(Constants.ScionAnalytics.MessageType.DATA_MESSAGE, IndividualCurriculumActivity.this.data.getSecondSemester());
            }
            if (position == 2) {
                bundle.putSerializable(Constants.ScionAnalytics.MessageType.DATA_MESSAGE, IndividualCurriculumActivity.this.data.getSelectSubject());
                bundle.putSerializable("compact", true);
            }
            IndividualCurriculumFragment individualCurriculumFragment = new IndividualCurriculumFragment();
            individualCurriculumFragment.setArguments(bundle);
            return individualCurriculumFragment;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public CharSequence getPageTitle(int position) {
            if (position == 0) {
                return "I Півріччя";
            }
            if (position == 1) {
                return "II Півріччя";
            }
            if (position != 2) {
                return null;
            }
            return "Вибіркові дисципліни";
        }
    }
}
