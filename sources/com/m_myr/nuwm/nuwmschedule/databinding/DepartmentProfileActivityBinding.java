package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class DepartmentProfileActivityBinding implements ViewBinding {
    public final LinearLayout cardsContainer;
    public final LinearLayout contentScrollable;
    public final LinearLayout departments;
    public final ImageView image;
    public final ImageView imagephone;
    public final TextView location;
    public final AppCompatTextView name;
    public final TextView parent;
    public final TextView phone;
    public final ImageView photo;
    public final RelativeLayout relativeLayoutLocation;
    public final RelativeLayout relativeLayoutPhone;
    private final LinearLayout rootView;
    public final RecyclerView workers;

    private DepartmentProfileActivityBinding(LinearLayout rootView, LinearLayout cardsContainer, LinearLayout contentScrollable, LinearLayout departments, ImageView image, ImageView imagephone, TextView location, AppCompatTextView name, TextView parent, TextView phone, ImageView photo, RelativeLayout relativeLayoutLocation, RelativeLayout relativeLayoutPhone, RecyclerView workers) {
        this.rootView = rootView;
        this.cardsContainer = cardsContainer;
        this.contentScrollable = contentScrollable;
        this.departments = departments;
        this.image = image;
        this.imagephone = imagephone;
        this.location = location;
        this.name = name;
        this.parent = parent;
        this.phone = phone;
        this.photo = photo;
        this.relativeLayoutLocation = relativeLayoutLocation;
        this.relativeLayoutPhone = relativeLayoutPhone;
        this.workers = workers;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static DepartmentProfileActivityBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static DepartmentProfileActivityBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.department_profile_activity, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static DepartmentProfileActivityBinding bind(View rootView) {
        int i = R.id.cards_container;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.cards_container);
        if (linearLayout != null) {
            LinearLayout linearLayout2 = (LinearLayout) rootView;
            i = R.id.departments;
            LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.departments);
            if (linearLayout3 != null) {
                i = R.id.image;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image);
                if (imageView != null) {
                    i = R.id.imagephone;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.imagephone);
                    if (imageView2 != null) {
                        i = R.id.location;
                        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.location);
                        if (textView != null) {
                            i = R.id.name;
                            AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(rootView, R.id.name);
                            if (appCompatTextView != null) {
                                i = R.id.parent;
                                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.parent);
                                if (textView2 != null) {
                                    i = R.id.phone;
                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.phone);
                                    if (textView3 != null) {
                                        i = R.id.photo;
                                        ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.photo);
                                        if (imageView3 != null) {
                                            i = R.id.relativeLayoutLocation;
                                            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.relativeLayoutLocation);
                                            if (relativeLayout != null) {
                                                i = R.id.relativeLayoutPhone;
                                                RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.relativeLayoutPhone);
                                                if (relativeLayout2 != null) {
                                                    i = R.id.workers;
                                                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.workers);
                                                    if (recyclerView != null) {
                                                        return new DepartmentProfileActivityBinding(linearLayout2, linearLayout, linearLayout2, linearLayout3, imageView, imageView2, textView, appCompatTextView, textView2, textView3, imageView3, relativeLayout, relativeLayout2, recyclerView);
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
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
