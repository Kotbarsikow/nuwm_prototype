package com.m_myr.nuwm.nuwmschedule.ui.framents.feed;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.tabs.TabLayout;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.repositories.AppDataManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FeedFragment.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J&\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\u001a\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\b\u0010\u0016\u001a\u00020\u0014H\u0002J\u000e\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/m_myr/nuwm/nuwmschedule/ui/framents/feed/FeedFragment;", "Landroidx/fragment/app/Fragment;", "()V", "showUid", "", "tabLayout", "Lcom/google/android/material/tabs/TabLayout;", "viewPagerFragmentsAdapter", "Lcom/m_myr/nuwm/nuwmschedule/ui/framents/feed/ViewPagerFragmentsAdapter;", "viewpager", "Landroidx/viewpager/widget/ViewPager;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "", "view", "setUpProfileIcon", "showPost", "uid", "app_publicReleaseRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FeedFragment extends Fragment {
    private int showUid = -1;
    private TabLayout tabLayout;
    private ViewPagerFragmentsAdapter viewPagerFragmentsAdapter;
    private ViewPager viewpager;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_notifications, container, false);
    }

    public final void showPost(int uid) {
        this.showUid = uid;
        ViewPager viewPager = this.viewpager;
        if (viewPager != null) {
            Intrinsics.checkNotNull(viewPager);
            viewPager.setCurrentItem(0);
        }
        ViewPagerFragmentsAdapter viewPagerFragmentsAdapter = this.viewPagerFragmentsAdapter;
        if (viewPagerFragmentsAdapter != null) {
            this.showUid = -1;
            Intrinsics.checkNotNull(viewPagerFragmentsAdapter);
            viewPagerFragmentsAdapter.notifyFeedShowNewPost(uid);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        if (AppDataManager.getInstance().getUserPermission().isIdCard()) {
            view.findViewById(R.id.idCard).setVisibility(0);
        }
        this.viewpager = (ViewPager) view.findViewById(R.id.viewpager);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        this.tabLayout = tabLayout;
        if (tabLayout != null) {
            tabLayout.addTab(tabLayout.newTab().setText("Повідомлення"));
            tabLayout.addTab(tabLayout.newTab().setText("Новини"));
            tabLayout.addTab(tabLayout.newTab().setText("Оголошення"));
            tabLayout.addTab(tabLayout.newTab().setText("Наука"));
        }
        this.viewPagerFragmentsAdapter = new ViewPagerFragmentsAdapter(getChildFragmentManager());
        int i = this.showUid;
        if (i > 0) {
            showPost(i);
        }
        ViewPager viewPager = this.viewpager;
        Intrinsics.checkNotNull(viewPager);
        viewPager.setAdapter(this.viewPagerFragmentsAdapter);
        TabLayout tabLayout2 = this.tabLayout;
        Intrinsics.checkNotNull(tabLayout2);
        tabLayout2.setupWithViewPager(this.viewpager);
        ViewPager viewPager2 = this.viewpager;
        Intrinsics.checkNotNull(viewPager2);
        viewPager2.setOffscreenPageLimit(2);
        setUpProfileIcon();
    }

    private final void setUpProfileIcon() {
        Glide.with(this).load(AppDataManager.getInstance().getUser().getProfileImage()).apply((BaseRequestOptions<?>) RequestOptions.circleCropTransform()).into((ImageView) requireView().findViewById(R.id.profileIcon));
    }
}
