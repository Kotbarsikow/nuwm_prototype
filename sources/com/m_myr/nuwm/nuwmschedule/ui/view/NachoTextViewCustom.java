package com.m_myr.nuwm.nuwmschedule.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import com.hootsuite.nachos.NachoTextView;

/* loaded from: classes2.dex */
public class NachoTextViewCustom extends NachoTextView {
    private ProgressBar mLoadingIndicator;

    public NachoTextViewCustom(Context context) {
        super(context);
    }

    public NachoTextViewCustom(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NachoTextViewCustom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setLoadingIndicator(ProgressBar progressBar) {
        this.mLoadingIndicator = progressBar;
    }

    @Override // android.widget.MultiAutoCompleteTextView, android.widget.AutoCompleteTextView
    protected void performFiltering(CharSequence text, int keyCode) {
        ProgressBar progressBar = this.mLoadingIndicator;
        if (progressBar != null) {
            progressBar.setVisibility(0);
        }
        super.performFiltering(text, keyCode);
    }

    @Override // android.widget.AutoCompleteTextView, android.widget.Filter.FilterListener
    public void onFilterComplete(int count) {
        ProgressBar progressBar = this.mLoadingIndicator;
        if (progressBar != null) {
            progressBar.setVisibility(8);
        }
        super.onFilterComplete(count);
    }
}
