package com.m_myr.nuwm.nuwmschedule.ui.framents;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.messaging.Constants;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.ui.AdapterContract;
import com.m_myr.nuwm.nuwmschedule.ui.GeneralListAdapter;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class BaseListFragment extends Fragment {
    View view;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ArrayList arrayList = (ArrayList) getArguments().getSerializable(Constants.ScionAnalytics.MessageType.DATA_MESSAGE);
        if (arrayList.isEmpty()) {
            this.view = getLayoutInflater().inflate(R.layout.empty_state, (ViewGroup) null, false);
        } else {
            RecyclerView recyclerView = (RecyclerView) getLayoutInflater().inflate(R.layout.base_list_fragment, (ViewGroup) null, false);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(new GeneralListAdapter((AdapterContract) getArguments().getSerializable("adapter"), arrayList));
            this.view = recyclerView;
        }
        return this.view;
    }
}
