package com.m_myr.nuwm.nuwmschedule.ui.activities.intro;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.repositories.AppDataManager;

/* loaded from: classes2.dex */
public class IntroActivity extends AppCompatActivity {
    private static final int NUM_PAGES = 3;
    private ViewPager mPager;
    private PagerAdapter pagerAdapter;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_slide);
        this.mPager = (ViewPager) findViewById(R.id.pager);
        ScreenSlidePagerAdapter screenSlidePagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        this.pagerAdapter = screenSlidePagerAdapter;
        this.mPager.setAdapter(screenSlidePagerAdapter);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.mPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            this.mPager.setCurrentItem(r0.getCurrentItem() - 1);
        }
    }

    public void next(int i) {
        this.mPager.setCurrentItem(i, true);
    }

    public void finishIntro() {
        AppDataManager.getInstance().apply();
        finish();
        startActivity(new Intent(this, (Class<?>) IntroActivity.class));
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            return 3;
        }

        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override // androidx.fragment.app.FragmentStatePagerAdapter
        public Fragment getItem(int position) {
            ScreenSlideIntroFragment screenSlideIntroFragment = new ScreenSlideIntroFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("position", position);
            screenSlideIntroFragment.setArguments(bundle);
            return screenSlideIntroFragment;
        }
    }
}
