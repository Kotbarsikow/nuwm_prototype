package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class SendPushActivityBinding implements ViewBinding {
    public final ImageView attach;
    public final ImageView attachImage;
    public final RelativeLayout attachLayout;
    public final LinearLayout attachmentsFile;
    public final RelativeLayout bottomAppBar;
    public final LinearLayout bottomSheet;
    public final LinearLayout bottomSheetPush;
    public final EditText editNoteTitle;
    public final EditText editNoteView;
    public final ImageView more;
    public final ProgressBar progressBar;
    private final CoordinatorLayout rootView;
    public final ScrollView scrollView;
    public final Toolbar toolbar;

    private SendPushActivityBinding(CoordinatorLayout rootView, ImageView attach, ImageView attachImage, RelativeLayout attachLayout, LinearLayout attachmentsFile, RelativeLayout bottomAppBar, LinearLayout bottomSheet, LinearLayout bottomSheetPush, EditText editNoteTitle, EditText editNoteView, ImageView more, ProgressBar progressBar, ScrollView scrollView, Toolbar toolbar) {
        this.rootView = rootView;
        this.attach = attach;
        this.attachImage = attachImage;
        this.attachLayout = attachLayout;
        this.attachmentsFile = attachmentsFile;
        this.bottomAppBar = bottomAppBar;
        this.bottomSheet = bottomSheet;
        this.bottomSheetPush = bottomSheetPush;
        this.editNoteTitle = editNoteTitle;
        this.editNoteView = editNoteView;
        this.more = more;
        this.progressBar = progressBar;
        this.scrollView = scrollView;
        this.toolbar = toolbar;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CoordinatorLayout getRoot() {
        return this.rootView;
    }

    public static SendPushActivityBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static SendPushActivityBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.send_push_activity, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static SendPushActivityBinding bind(View rootView) {
        int i = R.id.attach;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.attach);
        if (imageView != null) {
            i = R.id.attach_image;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.attach_image);
            if (imageView2 != null) {
                i = R.id.attach_layout;
                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.attach_layout);
                if (relativeLayout != null) {
                    i = R.id.attachments_file;
                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.attachments_file);
                    if (linearLayout != null) {
                        i = R.id.bottom_app_bar;
                        RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.bottom_app_bar);
                        if (relativeLayout2 != null) {
                            i = R.id.bottom_sheet;
                            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.bottom_sheet);
                            if (linearLayout2 != null) {
                                i = R.id.bottom_sheet_push;
                                LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.bottom_sheet_push);
                                if (linearLayout3 != null) {
                                    i = R.id.edit_note_title;
                                    EditText editText = (EditText) ViewBindings.findChildViewById(rootView, R.id.edit_note_title);
                                    if (editText != null) {
                                        i = R.id.edit_note_view;
                                        EditText editText2 = (EditText) ViewBindings.findChildViewById(rootView, R.id.edit_note_view);
                                        if (editText2 != null) {
                                            i = R.id.more;
                                            ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.more);
                                            if (imageView3 != null) {
                                                i = R.id.progressBar;
                                                ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(rootView, R.id.progressBar);
                                                if (progressBar != null) {
                                                    i = R.id.scrollView;
                                                    ScrollView scrollView = (ScrollView) ViewBindings.findChildViewById(rootView, R.id.scrollView);
                                                    if (scrollView != null) {
                                                        i = R.id.toolbar;
                                                        Toolbar toolbar = (Toolbar) ViewBindings.findChildViewById(rootView, R.id.toolbar);
                                                        if (toolbar != null) {
                                                            return new SendPushActivityBinding((CoordinatorLayout) rootView, imageView, imageView2, relativeLayout, linearLayout, relativeLayout2, linearLayout2, linearLayout3, editText, editText2, imageView3, progressBar, scrollView, toolbar);
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
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
