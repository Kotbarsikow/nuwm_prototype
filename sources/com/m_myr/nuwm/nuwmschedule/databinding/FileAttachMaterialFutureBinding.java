package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class FileAttachMaterialFutureBinding implements ViewBinding {
    public final RelativeLayout fileIco;
    public final TextView fileInfo;
    public final LinearLayout fileInfoLayout;
    public final TextView fileName;
    public final ImageView iconInside;
    public final LinearProgressIndicator progressBar;
    private final RelativeLayout rootView;

    private FileAttachMaterialFutureBinding(RelativeLayout rootView, RelativeLayout fileIco, TextView fileInfo, LinearLayout fileInfoLayout, TextView fileName, ImageView iconInside, LinearProgressIndicator progressBar) {
        this.rootView = rootView;
        this.fileIco = fileIco;
        this.fileInfo = fileInfo;
        this.fileInfoLayout = fileInfoLayout;
        this.fileName = fileName;
        this.iconInside = iconInside;
        this.progressBar = progressBar;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static FileAttachMaterialFutureBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FileAttachMaterialFutureBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.file_attach_material_future, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static FileAttachMaterialFutureBinding bind(View rootView) {
        int i = R.id.file_ico;
        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.file_ico);
        if (relativeLayout != null) {
            i = R.id.file_info;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.file_info);
            if (textView != null) {
                i = R.id.file_info_layout;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.file_info_layout);
                if (linearLayout != null) {
                    i = R.id.file_name;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.file_name);
                    if (textView2 != null) {
                        i = R.id.icon_inside;
                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.icon_inside);
                        if (imageView != null) {
                            i = R.id.progressBar;
                            LinearProgressIndicator linearProgressIndicator = (LinearProgressIndicator) ViewBindings.findChildViewById(rootView, R.id.progressBar);
                            if (linearProgressIndicator != null) {
                                return new FileAttachMaterialFutureBinding((RelativeLayout) rootView, relativeLayout, textView, linearLayout, textView2, imageView, linearProgressIndicator);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
