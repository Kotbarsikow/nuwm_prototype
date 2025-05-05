package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class FileAttachMaterialBinding implements ViewBinding {
    public final RelativeLayout fileIco;
    public final TextView fileInfo;
    public final LinearLayout fileInfoLayout;
    public final TextView fileName;
    private final RelativeLayout rootView;

    private FileAttachMaterialBinding(RelativeLayout rootView, RelativeLayout fileIco, TextView fileInfo, LinearLayout fileInfoLayout, TextView fileName) {
        this.rootView = rootView;
        this.fileIco = fileIco;
        this.fileInfo = fileInfo;
        this.fileInfoLayout = fileInfoLayout;
        this.fileName = fileName;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static FileAttachMaterialBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FileAttachMaterialBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.file_attach_material, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static FileAttachMaterialBinding bind(View rootView) {
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
                        return new FileAttachMaterialBinding((RelativeLayout) rootView, relativeLayout, textView, linearLayout, textView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
