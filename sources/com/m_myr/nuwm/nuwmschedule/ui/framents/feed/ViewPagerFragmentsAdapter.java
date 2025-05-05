package com.m_myr.nuwm.nuwmschedule.ui.framents.feed;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.m_myr.nuwm.nuwmschedule.ui.framents.contetnFeed.ContentFeedFragment;
import com.m_myr.nuwm.nuwmschedule.ui.framents.pushFeed.PushFeedFragment;

/* loaded from: classes2.dex */
public class ViewPagerFragmentsAdapter extends FragmentStatePagerAdapter {
    private final int mNumOfTabs;
    private int uid;

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return 4;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getItemPosition(Object object) {
        return -2;
    }

    @Override // androidx.fragment.app.FragmentStatePagerAdapter, androidx.viewpager.widget.PagerAdapter
    public Parcelable saveState() {
        return null;
    }

    public ViewPagerFragmentsAdapter(FragmentManager fm) {
        super(fm, 1);
        this.mNumOfTabs = 4;
    }

    @Override // androidx.fragment.app.FragmentStatePagerAdapter
    public Fragment getItem(int position) {
        if (position == 0) {
            PushFeedFragment pushFeedFragment = new PushFeedFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("uid", this.uid);
            this.uid = -1;
            pushFeedFragment.setArguments(bundle);
            return pushFeedFragment;
        }
        if (position == 1) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("category", "news");
            ContentFeedFragment contentFeedFragment = new ContentFeedFragment();
            contentFeedFragment.setArguments(bundle2);
            return contentFeedFragment;
        }
        if (position == 2) {
            Bundle bundle3 = new Bundle();
            bundle3.putString("category", "ads");
            ContentFeedFragment contentFeedFragment2 = new ContentFeedFragment();
            contentFeedFragment2.setArguments(bundle3);
            return contentFeedFragment2;
        }
        if (position != 3) {
            return null;
        }
        Bundle bundle4 = new Bundle();
        bundle4.putString("category", "science");
        ContentFeedFragment contentFeedFragment3 = new ContentFeedFragment();
        contentFeedFragment3.setArguments(bundle4);
        return contentFeedFragment3;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "Повідомлення";
        }
        if (position == 1) {
            return "Новини";
        }
        if (position == 2) {
            return "Оголошення";
        }
        if (position != 3) {
            return null;
        }
        return "Наука";
    }

    public void notifyFeedShowNewPost(int uid) {
        this.uid = uid;
        notifyDataSetChanged();
    }
}
