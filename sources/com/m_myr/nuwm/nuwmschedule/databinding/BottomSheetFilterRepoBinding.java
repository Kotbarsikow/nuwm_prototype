package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.slider.RangeSlider;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class BottomSheetFilterRepoBinding implements ViewBinding {
    public final LinearLayout bottomSheet;
    public final MaterialButton btSearch;
    public final RangeSlider rangeYears;
    private final LinearLayout rootView;
    public final Chip sortByDate;
    public final Chip sortByName;
    public final Chip sortByType;
    public final ChipGroup sortFilters;
    public final TextView toolbarReset;
    public final ChipGroup typeFilters;

    private BottomSheetFilterRepoBinding(LinearLayout rootView, LinearLayout bottomSheet, MaterialButton btSearch, RangeSlider rangeYears, Chip sortByDate, Chip sortByName, Chip sortByType, ChipGroup sortFilters, TextView toolbarReset, ChipGroup typeFilters) {
        this.rootView = rootView;
        this.bottomSheet = bottomSheet;
        this.btSearch = btSearch;
        this.rangeYears = rangeYears;
        this.sortByDate = sortByDate;
        this.sortByName = sortByName;
        this.sortByType = sortByType;
        this.sortFilters = sortFilters;
        this.toolbarReset = toolbarReset;
        this.typeFilters = typeFilters;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static BottomSheetFilterRepoBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static BottomSheetFilterRepoBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.bottom_sheet_filter_repo, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static BottomSheetFilterRepoBinding bind(View rootView) {
        LinearLayout linearLayout = (LinearLayout) rootView;
        int i = R.id.bt_search;
        MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(rootView, R.id.bt_search);
        if (materialButton != null) {
            i = R.id.rangeYears;
            RangeSlider rangeSlider = (RangeSlider) ViewBindings.findChildViewById(rootView, R.id.rangeYears);
            if (rangeSlider != null) {
                i = R.id.sortByDate;
                Chip chip = (Chip) ViewBindings.findChildViewById(rootView, R.id.sortByDate);
                if (chip != null) {
                    i = R.id.sortByName;
                    Chip chip2 = (Chip) ViewBindings.findChildViewById(rootView, R.id.sortByName);
                    if (chip2 != null) {
                        i = R.id.sortByType;
                        Chip chip3 = (Chip) ViewBindings.findChildViewById(rootView, R.id.sortByType);
                        if (chip3 != null) {
                            i = R.id.sortFilters;
                            ChipGroup chipGroup = (ChipGroup) ViewBindings.findChildViewById(rootView, R.id.sortFilters);
                            if (chipGroup != null) {
                                i = R.id.toolbar_reset;
                                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.toolbar_reset);
                                if (textView != null) {
                                    i = R.id.typeFilters;
                                    ChipGroup chipGroup2 = (ChipGroup) ViewBindings.findChildViewById(rootView, R.id.typeFilters);
                                    if (chipGroup2 != null) {
                                        return new BottomSheetFilterRepoBinding(linearLayout, linearLayout, materialButton, rangeSlider, chip, chip2, chip3, chipGroup, textView, chipGroup2);
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
