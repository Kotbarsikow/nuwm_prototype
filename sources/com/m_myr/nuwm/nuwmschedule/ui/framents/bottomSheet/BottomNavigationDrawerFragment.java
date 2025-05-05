package com.m_myr.nuwm.nuwmschedule.ui.framents.bottomSheet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public class BottomNavigationDrawerFragment extends BottomSheetDialogFragment {
    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bottomsheet_push, container, false);
    }
}
