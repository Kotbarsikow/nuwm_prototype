package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class BottomSheetHelpdeskFilterBinding implements ViewBinding {
    public final MaterialButton apply;
    public final LinearLayout bottomSheet;
    public final CheckBox pinCriticalTicket;
    private final LinearLayout rootView;
    public final CheckBox showLastReplies;
    public final Chip sortByCategory;
    public final Chip sortByDate;
    public final Chip sortByPriority;
    public final Chip sortByStatus;
    public final Chip sortByStatusAnswerSent;
    public final Chip sortByStatusHold;
    public final Chip sortByStatusNew;
    public final Chip sortByStatusOnReview;
    public final Chip sortByStatusReviewed;
    public final Chip sortByStatusWait;
    public final ChipGroup sortFilters;
    public final ChipGroup statusFilters;
    public final TextView toolbarReset;

    private BottomSheetHelpdeskFilterBinding(LinearLayout rootView, MaterialButton apply, LinearLayout bottomSheet, CheckBox pinCriticalTicket, CheckBox showLastReplies, Chip sortByCategory, Chip sortByDate, Chip sortByPriority, Chip sortByStatus, Chip sortByStatusAnswerSent, Chip sortByStatusHold, Chip sortByStatusNew, Chip sortByStatusOnReview, Chip sortByStatusReviewed, Chip sortByStatusWait, ChipGroup sortFilters, ChipGroup statusFilters, TextView toolbarReset) {
        this.rootView = rootView;
        this.apply = apply;
        this.bottomSheet = bottomSheet;
        this.pinCriticalTicket = pinCriticalTicket;
        this.showLastReplies = showLastReplies;
        this.sortByCategory = sortByCategory;
        this.sortByDate = sortByDate;
        this.sortByPriority = sortByPriority;
        this.sortByStatus = sortByStatus;
        this.sortByStatusAnswerSent = sortByStatusAnswerSent;
        this.sortByStatusHold = sortByStatusHold;
        this.sortByStatusNew = sortByStatusNew;
        this.sortByStatusOnReview = sortByStatusOnReview;
        this.sortByStatusReviewed = sortByStatusReviewed;
        this.sortByStatusWait = sortByStatusWait;
        this.sortFilters = sortFilters;
        this.statusFilters = statusFilters;
        this.toolbarReset = toolbarReset;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static BottomSheetHelpdeskFilterBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static BottomSheetHelpdeskFilterBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.bottom_sheet_helpdesk_filter, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static BottomSheetHelpdeskFilterBinding bind(View rootView) {
        int i = R.id.apply;
        MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(rootView, R.id.apply);
        if (materialButton != null) {
            LinearLayout linearLayout = (LinearLayout) rootView;
            i = R.id.pinCriticalTicket;
            CheckBox checkBox = (CheckBox) ViewBindings.findChildViewById(rootView, R.id.pinCriticalTicket);
            if (checkBox != null) {
                i = R.id.showLastReplies;
                CheckBox checkBox2 = (CheckBox) ViewBindings.findChildViewById(rootView, R.id.showLastReplies);
                if (checkBox2 != null) {
                    i = R.id.sortByCategory;
                    Chip chip = (Chip) ViewBindings.findChildViewById(rootView, R.id.sortByCategory);
                    if (chip != null) {
                        i = R.id.sortByDate;
                        Chip chip2 = (Chip) ViewBindings.findChildViewById(rootView, R.id.sortByDate);
                        if (chip2 != null) {
                            i = R.id.sortByPriority;
                            Chip chip3 = (Chip) ViewBindings.findChildViewById(rootView, R.id.sortByPriority);
                            if (chip3 != null) {
                                i = R.id.sortByStatus;
                                Chip chip4 = (Chip) ViewBindings.findChildViewById(rootView, R.id.sortByStatus);
                                if (chip4 != null) {
                                    i = R.id.sortByStatus_AnswerSent;
                                    Chip chip5 = (Chip) ViewBindings.findChildViewById(rootView, R.id.sortByStatus_AnswerSent);
                                    if (chip5 != null) {
                                        i = R.id.sortByStatus_Hold;
                                        Chip chip6 = (Chip) ViewBindings.findChildViewById(rootView, R.id.sortByStatus_Hold);
                                        if (chip6 != null) {
                                            i = R.id.sortByStatus_New;
                                            Chip chip7 = (Chip) ViewBindings.findChildViewById(rootView, R.id.sortByStatus_New);
                                            if (chip7 != null) {
                                                i = R.id.sortByStatus_OnReview;
                                                Chip chip8 = (Chip) ViewBindings.findChildViewById(rootView, R.id.sortByStatus_OnReview);
                                                if (chip8 != null) {
                                                    i = R.id.sortByStatus_Reviewed;
                                                    Chip chip9 = (Chip) ViewBindings.findChildViewById(rootView, R.id.sortByStatus_Reviewed);
                                                    if (chip9 != null) {
                                                        i = R.id.sortByStatus_Wait;
                                                        Chip chip10 = (Chip) ViewBindings.findChildViewById(rootView, R.id.sortByStatus_Wait);
                                                        if (chip10 != null) {
                                                            i = R.id.sortFilters;
                                                            ChipGroup chipGroup = (ChipGroup) ViewBindings.findChildViewById(rootView, R.id.sortFilters);
                                                            if (chipGroup != null) {
                                                                i = R.id.statusFilters;
                                                                ChipGroup chipGroup2 = (ChipGroup) ViewBindings.findChildViewById(rootView, R.id.statusFilters);
                                                                if (chipGroup2 != null) {
                                                                    i = R.id.toolbar_reset;
                                                                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.toolbar_reset);
                                                                    if (textView != null) {
                                                                        return new BottomSheetHelpdeskFilterBinding(linearLayout, materialButton, linearLayout, checkBox, checkBox2, chip, chip2, chip3, chip4, chip5, chip6, chip7, chip8, chip9, chip10, chipGroup, chipGroup2, textView);
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
