package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.jsibbold.zoomage.ZoomageView;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class ImageViewActivityBinding implements ViewBinding {
    public final ZoomageView myZoomageView;
    private final FrameLayout rootView;
    public final Toolbar toolbar;

    private ImageViewActivityBinding(FrameLayout rootView, ZoomageView myZoomageView, Toolbar toolbar) {
        this.rootView = rootView;
        this.myZoomageView = myZoomageView;
        this.toolbar = toolbar;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static ImageViewActivityBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ImageViewActivityBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.image_view_activity, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static ImageViewActivityBinding bind(View rootView) {
        int i = R.id.myZoomageView;
        ZoomageView zoomageView = (ZoomageView) ViewBindings.findChildViewById(rootView, R.id.myZoomageView);
        if (zoomageView != null) {
            i = R.id.toolbar;
            Toolbar toolbar = (Toolbar) ViewBindings.findChildViewById(rootView, R.id.toolbar);
            if (toolbar != null) {
                return new ImageViewActivityBinding((FrameLayout) rootView, zoomageView, toolbar);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
