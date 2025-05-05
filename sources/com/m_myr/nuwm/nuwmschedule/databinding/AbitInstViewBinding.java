package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class AbitInstViewBinding implements ViewBinding {
    public final RelativeLayout fragmentRoot;
    public final LoadingLayoutBinding loadingLayout;
    private final RelativeLayout rootView;
    public final WebView webview;

    private AbitInstViewBinding(RelativeLayout rootView, RelativeLayout fragmentRoot, LoadingLayoutBinding loadingLayout, WebView webview) {
        this.rootView = rootView;
        this.fragmentRoot = fragmentRoot;
        this.loadingLayout = loadingLayout;
        this.webview = webview;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static AbitInstViewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static AbitInstViewBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.abit_inst_view, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static AbitInstViewBinding bind(View rootView) {
        RelativeLayout relativeLayout = (RelativeLayout) rootView;
        int i = R.id.loading_layout;
        View findChildViewById = ViewBindings.findChildViewById(rootView, R.id.loading_layout);
        if (findChildViewById != null) {
            LoadingLayoutBinding bind = LoadingLayoutBinding.bind(findChildViewById);
            WebView webView = (WebView) ViewBindings.findChildViewById(rootView, R.id.webview);
            if (webView != null) {
                return new AbitInstViewBinding(relativeLayout, relativeLayout, bind, webView);
            }
            i = R.id.webview;
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
