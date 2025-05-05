package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class MenuNotesBinding implements ViewBinding {
    public final ImageView attach;
    private final View rootView;
    public final TextView typeName;

    private MenuNotesBinding(View rootView, ImageView attach, TextView typeName) {
        this.rootView = rootView;
        this.attach = attach;
        this.typeName = typeName;
    }

    @Override // androidx.viewbinding.ViewBinding
    public View getRoot() {
        return this.rootView;
    }

    public static MenuNotesBinding inflate(LayoutInflater inflater, ViewGroup parent) {
        if (parent == null) {
            throw new NullPointerException("parent");
        }
        inflater.inflate(R.layout.menu_notes, parent);
        return bind(parent);
    }

    public static MenuNotesBinding bind(View rootView) {
        int i = R.id.attach;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.attach);
        if (imageView != null) {
            i = R.id.type_name;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.type_name);
            if (textView != null) {
                return new MenuNotesBinding(rootView, imageView, textView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
