package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class BottomSheetHelpdeskTicketBinding implements ViewBinding {
    public final LinearLayout bottomSheet;
    public final MaterialButton closedStatus;
    public final LinearLayout inner;
    public final FrameLayout loadingOverlay;
    public final MaterialButton openedStatus;
    private final LinearLayout rootView;
    public final Chip sortByCategory;
    public final Chip sortByDate;
    public final Chip sortByPriority;
    public final Chip sortByStatus;
    public final Chip sortByStatusAnswerSent;
    public final Chip sortByStatusHold;
    public final Chip sortByStatusNew;
    public final Chip sortByStatusOnReview;
    public final Chip sortByStatusWait;
    public final ChipGroup sortFilters;
    public final ChipGroup statusFilters;
    public final MaterialButtonToggleGroup toggleButtonGroupStatus;

    private BottomSheetHelpdeskTicketBinding(LinearLayout rootView, LinearLayout bottomSheet, MaterialButton closedStatus, LinearLayout inner, FrameLayout loadingOverlay, MaterialButton openedStatus, Chip sortByCategory, Chip sortByDate, Chip sortByPriority, Chip sortByStatus, Chip sortByStatusAnswerSent, Chip sortByStatusHold, Chip sortByStatusNew, Chip sortByStatusOnReview, Chip sortByStatusWait, ChipGroup sortFilters, ChipGroup statusFilters, MaterialButtonToggleGroup toggleButtonGroupStatus) {
        this.rootView = rootView;
        this.bottomSheet = bottomSheet;
        this.closedStatus = closedStatus;
        this.inner = inner;
        this.loadingOverlay = loadingOverlay;
        this.openedStatus = openedStatus;
        this.sortByCategory = sortByCategory;
        this.sortByDate = sortByDate;
        this.sortByPriority = sortByPriority;
        this.sortByStatus = sortByStatus;
        this.sortByStatusAnswerSent = sortByStatusAnswerSent;
        this.sortByStatusHold = sortByStatusHold;
        this.sortByStatusNew = sortByStatusNew;
        this.sortByStatusOnReview = sortByStatusOnReview;
        this.sortByStatusWait = sortByStatusWait;
        this.sortFilters = sortFilters;
        this.statusFilters = statusFilters;
        this.toggleButtonGroupStatus = toggleButtonGroupStatus;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static BottomSheetHelpdeskTicketBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static BottomSheetHelpdeskTicketBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.bottom_sheet_helpdesk_ticket, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static BottomSheetHelpdeskTicketBinding bind(View rootView) {
        LinearLayout linearLayout = (LinearLayout) rootView;
        int i = R.id.closedStatus;
        MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(rootView, R.id.closedStatus);
        if (materialButton != null) {
            i = R.id.inner;
            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.inner);
            if (linearLayout2 != null) {
                i = R.id.loadingOverlay;
                FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(rootView, R.id.loadingOverlay);
                if (frameLayout != null) {
                    i = R.id.openedStatus;
                    MaterialButton materialButton2 = (MaterialButton) ViewBindings.findChildViewById(rootView, R.id.openedStatus);
                    if (materialButton2 != null) {
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
                                                        i = R.id.sortByStatus_Wait;
                                                        Chip chip9 = (Chip) ViewBindings.findChildViewById(rootView, R.id.sortByStatus_Wait);
                                                        if (chip9 != null) {
                                                            i = R.id.sortFilters;
                                                            ChipGroup chipGroup = (ChipGroup) ViewBindings.findChildViewById(rootView, R.id.sortFilters);
                                                            if (chipGroup != null) {
                                                                i = R.id.statusFilters;
                                                                ChipGroup chipGroup2 = (ChipGroup) ViewBindings.findChildViewById(rootView, R.id.statusFilters);
                                                                if (chipGroup2 != null) {
                                                                    i = R.id.toggle_button_group_status;
                                                                    MaterialButtonToggleGroup materialButtonToggleGroup = (MaterialButtonToggleGroup) ViewBindings.findChildViewById(rootView, R.id.toggle_button_group_status);
                                                                    if (materialButtonToggleGroup != null) {
                                                                        return new BottomSheetHelpdeskTicketBinding(linearLayout, linearLayout, materialButton, linearLayout2, frameLayout, materialButton2, chip, chip2, chip3, chip4, chip5, chip6, chip7, chip8, chip9, chipGroup, chipGroup2, materialButtonToggleGroup);
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
