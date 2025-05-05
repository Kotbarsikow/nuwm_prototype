package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Space;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class InfoDialogBinding implements ViewBinding {
    public final ImageView btnClose;
    public final ImageView ivImage;
    private final FrameLayout rootView;
    public final Space spacer;
    public final TextView tvAction;
    public final TextView tvAction2;
    public final TextView tvAction3;
    public final TextView tvAction4;
    public final TextView tvCancel;
    public final TextView tvText;
    public final TextView tvTitle;

    private InfoDialogBinding(FrameLayout rootView, ImageView btnClose, ImageView ivImage, Space spacer, TextView tvAction, TextView tvAction2, TextView tvAction3, TextView tvAction4, TextView tvCancel, TextView tvText, TextView tvTitle) {
        this.rootView = rootView;
        this.btnClose = btnClose;
        this.ivImage = ivImage;
        this.spacer = spacer;
        this.tvAction = tvAction;
        this.tvAction2 = tvAction2;
        this.tvAction3 = tvAction3;
        this.tvAction4 = tvAction4;
        this.tvCancel = tvCancel;
        this.tvText = tvText;
        this.tvTitle = tvTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static InfoDialogBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static InfoDialogBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.info_dialog, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static InfoDialogBinding bind(View rootView) {
        int i = R.id.btnClose;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.btnClose);
        if (imageView != null) {
            i = R.id.ivImage;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.ivImage);
            if (imageView2 != null) {
                i = R.id.spacer;
                Space space = (Space) ViewBindings.findChildViewById(rootView, R.id.spacer);
                if (space != null) {
                    i = R.id.tvAction;
                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tvAction);
                    if (textView != null) {
                        i = R.id.tvAction2;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tvAction2);
                        if (textView2 != null) {
                            i = R.id.tvAction3;
                            TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tvAction3);
                            if (textView3 != null) {
                                i = R.id.tvAction4;
                                TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tvAction4);
                                if (textView4 != null) {
                                    i = R.id.tvCancel;
                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tvCancel);
                                    if (textView5 != null) {
                                        i = R.id.tvText;
                                        TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tvText);
                                        if (textView6 != null) {
                                            i = R.id.tvTitle;
                                            TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tvTitle);
                                            if (textView7 != null) {
                                                return new InfoDialogBinding((FrameLayout) rootView, imageView, imageView2, space, textView, textView2, textView3, textView4, textView5, textView6, textView7);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
