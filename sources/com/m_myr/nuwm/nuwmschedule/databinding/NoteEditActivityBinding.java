package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class NoteEditActivityBinding implements ViewBinding {
    public final RelativeLayout bottomAppBar;
    public final LinearLayout bottomSheetMenu;
    public final EditText editNoteTitle;
    public final EditText editNoteView;
    public final ImageView more;
    private final CoordinatorLayout rootView;
    public final ScrollView scrollView;
    public final Toolbar toolbar;

    private NoteEditActivityBinding(CoordinatorLayout rootView, RelativeLayout bottomAppBar, LinearLayout bottomSheetMenu, EditText editNoteTitle, EditText editNoteView, ImageView more, ScrollView scrollView, Toolbar toolbar) {
        this.rootView = rootView;
        this.bottomAppBar = bottomAppBar;
        this.bottomSheetMenu = bottomSheetMenu;
        this.editNoteTitle = editNoteTitle;
        this.editNoteView = editNoteView;
        this.more = more;
        this.scrollView = scrollView;
        this.toolbar = toolbar;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CoordinatorLayout getRoot() {
        return this.rootView;
    }

    public static NoteEditActivityBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static NoteEditActivityBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.note_edit_activity, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static NoteEditActivityBinding bind(View rootView) {
        int i = R.id.bottom_app_bar;
        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.bottom_app_bar);
        if (relativeLayout != null) {
            i = R.id.bottom_sheet_menu;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.bottom_sheet_menu);
            if (linearLayout != null) {
                i = R.id.edit_note_title;
                EditText editText = (EditText) ViewBindings.findChildViewById(rootView, R.id.edit_note_title);
                if (editText != null) {
                    i = R.id.edit_note_view;
                    EditText editText2 = (EditText) ViewBindings.findChildViewById(rootView, R.id.edit_note_view);
                    if (editText2 != null) {
                        i = R.id.more;
                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.more);
                        if (imageView != null) {
                            i = R.id.scrollView;
                            ScrollView scrollView = (ScrollView) ViewBindings.findChildViewById(rootView, R.id.scrollView);
                            if (scrollView != null) {
                                i = R.id.toolbar;
                                Toolbar toolbar = (Toolbar) ViewBindings.findChildViewById(rootView, R.id.toolbar);
                                if (toolbar != null) {
                                    return new NoteEditActivityBinding((CoordinatorLayout) rootView, relativeLayout, linearLayout, editText, editText2, imageView, scrollView, toolbar);
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
