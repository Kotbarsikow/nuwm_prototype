package com.m_myr.nuwm.nuwmschedule.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.webkit.WebView;

/* loaded from: classes2.dex */
public class NestedScrollWebView extends WebView {
    public NestedScrollWebView(Context context) {
        super(context);
    }

    public NestedScrollWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NestedScrollWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override // android.webkit.WebView, android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        requestDisallowInterceptTouchEvent(true);
        return super.onTouchEvent(event);
    }
}
