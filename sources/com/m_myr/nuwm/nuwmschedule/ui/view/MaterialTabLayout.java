package com.m_myr.nuwm.nuwmschedule.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public class MaterialTabLayout extends TabLayout {
    public MaterialTabLayout(Context context) {
        super(context);
    }

    public MaterialTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MaterialTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override // com.google.android.material.tabs.TabLayout
    public void setupWithViewPager(ViewPager viewPager) {
        super.setupWithViewPager(viewPager);
        setCustomIndicator();
    }

    public final void setCustomIndicator() {
        for (int i = 0; i < getTabCount(); i++) {
            getTabAt(i).setCustomView(LayoutInflater.from(getContext()).inflate(R.layout.tab_textview, (ViewGroup) null));
        }
    }
}
