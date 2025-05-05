package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class CompgroupListItemBinding implements ViewBinding {
    public final TextView info;
    public final TextView name;
    private final LinearLayout rootView;
    public final TextView stream;

    private CompgroupListItemBinding(LinearLayout rootView, TextView info, TextView name, TextView stream) {
        this.rootView = rootView;
        this.info = info;
        this.name = name;
        this.stream = stream;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static CompgroupListItemBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static CompgroupListItemBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.compgroup_list_item, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static CompgroupListItemBinding bind(View rootView) {
        int i = R.id.info;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.info);
        if (textView != null) {
            i = R.id.name;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.name);
            if (textView2 != null) {
                i = R.id.stream;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.stream);
                if (textView3 != null) {
                    return new CompgroupListItemBinding((LinearLayout) rootView, textView, textView2, textView3);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
