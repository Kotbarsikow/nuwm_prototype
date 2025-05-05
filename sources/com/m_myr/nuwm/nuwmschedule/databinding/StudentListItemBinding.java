package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class StudentListItemBinding implements ViewBinding {
    public final ImageView online;
    public final ImageView profileImage;
    public final TextView profileImageLetter;
    public final RelativeLayout profileImagePlace;
    private final RelativeLayout rootView;
    public final TextView username;
    public final TextView usertype;

    private StudentListItemBinding(RelativeLayout rootView, ImageView online, ImageView profileImage, TextView profileImageLetter, RelativeLayout profileImagePlace, TextView username, TextView usertype) {
        this.rootView = rootView;
        this.online = online;
        this.profileImage = profileImage;
        this.profileImageLetter = profileImageLetter;
        this.profileImagePlace = profileImagePlace;
        this.username = username;
        this.usertype = usertype;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static StudentListItemBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static StudentListItemBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.student_list_item, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static StudentListItemBinding bind(View rootView) {
        int i = R.id.online;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.online);
        if (imageView != null) {
            i = R.id.profile_image;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.profile_image);
            if (imageView2 != null) {
                i = R.id.profile_image_letter;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.profile_image_letter);
                if (textView != null) {
                    i = R.id.profile_image_place;
                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.profile_image_place);
                    if (relativeLayout != null) {
                        i = R.id.username;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.username);
                        if (textView2 != null) {
                            i = R.id.usertype;
                            TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.usertype);
                            if (textView3 != null) {
                                return new StudentListItemBinding((RelativeLayout) rootView, imageView, imageView2, textView, relativeLayout, textView2, textView3);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
