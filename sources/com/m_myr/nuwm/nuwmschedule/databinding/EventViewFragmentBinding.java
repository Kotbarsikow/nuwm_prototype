package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class EventViewFragmentBinding implements ViewBinding {
    public final BottomAppBar bottomAppBar;
    public final ImageButton cancel;
    public final View colorMark;
    public final TextInputEditText editNoteView;
    public final FloatingActionButton fab;
    public final LinearLayout groupLayout;
    public final LinearLayout lineTypeName;
    public final LinearLayout listDetals;
    public final LinearLayout locationLayout;
    public final TextView locationName;
    public final TextView locationNameDesc;
    public final CoordinatorLayout mainContent;
    public final LinearLayout mainFrameLesson;
    public final LinearLayout meetLayout;
    public final TextView meetLink;
    public final LinearLayout mergeLayout;
    public final LinearLayout noteEdit;
    public final RelativeLayout noteLayout;
    public final AppCompatTextView noteTextView;
    public final LinearLayout noteView;
    public final LinearLayout otherLayout;
    public final TextView otherName;
    private final LinearLayout rootView;
    public final ImageButton save;
    public final TextView staticTeacherField;
    public final TextView strSubject;
    public final TextView subgroupName;
    public final TextView subgroupStatic;
    public final LinearLayout teacherLayout;
    public final TextView teacherName;
    public final TextView timeName;
    public final Toolbar toolbar;
    public final TextView typeName;

    private EventViewFragmentBinding(LinearLayout rootView, BottomAppBar bottomAppBar, ImageButton cancel, View colorMark, TextInputEditText editNoteView, FloatingActionButton fab, LinearLayout groupLayout, LinearLayout lineTypeName, LinearLayout listDetals, LinearLayout locationLayout, TextView locationName, TextView locationNameDesc, CoordinatorLayout mainContent, LinearLayout mainFrameLesson, LinearLayout meetLayout, TextView meetLink, LinearLayout mergeLayout, LinearLayout noteEdit, RelativeLayout noteLayout, AppCompatTextView noteTextView, LinearLayout noteView, LinearLayout otherLayout, TextView otherName, ImageButton save, TextView staticTeacherField, TextView strSubject, TextView subgroupName, TextView subgroupStatic, LinearLayout teacherLayout, TextView teacherName, TextView timeName, Toolbar toolbar, TextView typeName) {
        this.rootView = rootView;
        this.bottomAppBar = bottomAppBar;
        this.cancel = cancel;
        this.colorMark = colorMark;
        this.editNoteView = editNoteView;
        this.fab = fab;
        this.groupLayout = groupLayout;
        this.lineTypeName = lineTypeName;
        this.listDetals = listDetals;
        this.locationLayout = locationLayout;
        this.locationName = locationName;
        this.locationNameDesc = locationNameDesc;
        this.mainContent = mainContent;
        this.mainFrameLesson = mainFrameLesson;
        this.meetLayout = meetLayout;
        this.meetLink = meetLink;
        this.mergeLayout = mergeLayout;
        this.noteEdit = noteEdit;
        this.noteLayout = noteLayout;
        this.noteTextView = noteTextView;
        this.noteView = noteView;
        this.otherLayout = otherLayout;
        this.otherName = otherName;
        this.save = save;
        this.staticTeacherField = staticTeacherField;
        this.strSubject = strSubject;
        this.subgroupName = subgroupName;
        this.subgroupStatic = subgroupStatic;
        this.teacherLayout = teacherLayout;
        this.teacherName = teacherName;
        this.timeName = timeName;
        this.toolbar = toolbar;
        this.typeName = typeName;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static EventViewFragmentBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static EventViewFragmentBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.event_view_fragment, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static EventViewFragmentBinding bind(View rootView) {
        int i = R.id.bottom_app_bar;
        BottomAppBar bottomAppBar = (BottomAppBar) ViewBindings.findChildViewById(rootView, R.id.bottom_app_bar);
        if (bottomAppBar != null) {
            i = R.id.cancel;
            ImageButton imageButton = (ImageButton) ViewBindings.findChildViewById(rootView, R.id.cancel);
            if (imageButton != null) {
                i = R.id.color_mark;
                View findChildViewById = ViewBindings.findChildViewById(rootView, R.id.color_mark);
                if (findChildViewById != null) {
                    i = R.id.edit_note_view;
                    TextInputEditText textInputEditText = (TextInputEditText) ViewBindings.findChildViewById(rootView, R.id.edit_note_view);
                    if (textInputEditText != null) {
                        i = R.id.fab;
                        FloatingActionButton floatingActionButton = (FloatingActionButton) ViewBindings.findChildViewById(rootView, R.id.fab);
                        if (floatingActionButton != null) {
                            i = R.id.group_layout;
                            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.group_layout);
                            if (linearLayout != null) {
                                i = R.id.line_type_name;
                                LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.line_type_name);
                                if (linearLayout2 != null) {
                                    i = R.id.list_detals;
                                    LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.list_detals);
                                    if (linearLayout3 != null) {
                                        i = R.id.location_layout;
                                        LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.location_layout);
                                        if (linearLayout4 != null) {
                                            i = R.id.location_name;
                                            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.location_name);
                                            if (textView != null) {
                                                i = R.id.location_name_desc;
                                                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.location_name_desc);
                                                if (textView2 != null) {
                                                    i = R.id.main_content;
                                                    CoordinatorLayout coordinatorLayout = (CoordinatorLayout) ViewBindings.findChildViewById(rootView, R.id.main_content);
                                                    if (coordinatorLayout != null) {
                                                        LinearLayout linearLayout5 = (LinearLayout) rootView;
                                                        i = R.id.meet_layout;
                                                        LinearLayout linearLayout6 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.meet_layout);
                                                        if (linearLayout6 != null) {
                                                            i = R.id.meet_link;
                                                            TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.meet_link);
                                                            if (textView3 != null) {
                                                                i = R.id.merge_layout;
                                                                LinearLayout linearLayout7 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.merge_layout);
                                                                if (linearLayout7 != null) {
                                                                    i = R.id.note_edit;
                                                                    LinearLayout linearLayout8 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.note_edit);
                                                                    if (linearLayout8 != null) {
                                                                        i = R.id.note_layout;
                                                                        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.note_layout);
                                                                        if (relativeLayout != null) {
                                                                            i = R.id.note_text_view;
                                                                            AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(rootView, R.id.note_text_view);
                                                                            if (appCompatTextView != null) {
                                                                                i = R.id.note_view;
                                                                                LinearLayout linearLayout9 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.note_view);
                                                                                if (linearLayout9 != null) {
                                                                                    i = R.id.other_layout;
                                                                                    LinearLayout linearLayout10 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.other_layout);
                                                                                    if (linearLayout10 != null) {
                                                                                        i = R.id.other_name;
                                                                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.other_name);
                                                                                        if (textView4 != null) {
                                                                                            i = R.id.save;
                                                                                            ImageButton imageButton2 = (ImageButton) ViewBindings.findChildViewById(rootView, R.id.save);
                                                                                            if (imageButton2 != null) {
                                                                                                i = R.id.static_teacher_field;
                                                                                                TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, R.id.static_teacher_field);
                                                                                                if (textView5 != null) {
                                                                                                    i = R.id.str_subject;
                                                                                                    TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, R.id.str_subject);
                                                                                                    if (textView6 != null) {
                                                                                                        i = R.id.subgroup_name;
                                                                                                        TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, R.id.subgroup_name);
                                                                                                        if (textView7 != null) {
                                                                                                            i = R.id.subgroup_static;
                                                                                                            TextView textView8 = (TextView) ViewBindings.findChildViewById(rootView, R.id.subgroup_static);
                                                                                                            if (textView8 != null) {
                                                                                                                i = R.id.teacher_layout;
                                                                                                                LinearLayout linearLayout11 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.teacher_layout);
                                                                                                                if (linearLayout11 != null) {
                                                                                                                    i = R.id.teacher_name;
                                                                                                                    TextView textView9 = (TextView) ViewBindings.findChildViewById(rootView, R.id.teacher_name);
                                                                                                                    if (textView9 != null) {
                                                                                                                        i = R.id.time_name;
                                                                                                                        TextView textView10 = (TextView) ViewBindings.findChildViewById(rootView, R.id.time_name);
                                                                                                                        if (textView10 != null) {
                                                                                                                            i = R.id.toolbar;
                                                                                                                            Toolbar toolbar = (Toolbar) ViewBindings.findChildViewById(rootView, R.id.toolbar);
                                                                                                                            if (toolbar != null) {
                                                                                                                                i = R.id.type_name;
                                                                                                                                TextView textView11 = (TextView) ViewBindings.findChildViewById(rootView, R.id.type_name);
                                                                                                                                if (textView11 != null) {
                                                                                                                                    return new EventViewFragmentBinding(linearLayout5, bottomAppBar, imageButton, findChildViewById, textInputEditText, floatingActionButton, linearLayout, linearLayout2, linearLayout3, linearLayout4, textView, textView2, coordinatorLayout, linearLayout5, linearLayout6, textView3, linearLayout7, linearLayout8, relativeLayout, appCompatTextView, linearLayout9, linearLayout10, textView4, imageButton2, textView5, textView6, textView7, textView8, linearLayout11, textView9, textView10, toolbar, textView11);
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
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
