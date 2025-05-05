package com.m_myr.nuwm.nuwmschedule.ui.activities.worload;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.google.firebase.messaging.Constants;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.models.HalfWorkload;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes2.dex */
public class WorkloadHalfFragment extends Fragment implements View.OnClickListener {
    private LinearLayout mListContainer;
    View view;
    private ArrayList<HalfWorkload> workloads;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ArrayList<HalfWorkload> arrayList = (ArrayList) getArguments().getSerializable(Constants.ScionAnalytics.MessageType.DATA_MESSAGE);
        this.workloads = arrayList;
        if (arrayList.size() > 0) {
            View inflate = getLayoutInflater().inflate(R.layout.inplan_fragment, (ViewGroup) null, false);
            this.view = inflate;
            this.mListContainer = (LinearLayout) inflate.findViewById(R.id.list_container);
            buildList();
        } else {
            this.view = getLayoutInflater().inflate(R.layout.empty_state, (ViewGroup) null, false);
        }
        return this.view;
    }

    private void buildList() {
        Iterator<HalfWorkload> it = this.workloads.iterator();
        while (it.hasNext()) {
            HalfWorkload next = it.next();
            View inflate = getLayoutInflater().inflate(R.layout.workload_item, (ViewGroup) this.mListContainer, false);
            ((TextView) inflate.findViewById(R.id.text1)).setText(next.subjectName);
            if (next.subgroup != null && !next.subgroup.isEmpty() && Utils.StringUtils.isBlank(next.lectionsStream)) {
                ((TextView) inflate.findViewById(R.id.text2)).setText(next.groupName + ", " + next.subgroup + " підгрупа");
            } else {
                ((TextView) inflate.findViewById(R.id.text2)).setText(next.groupName);
            }
            ((TextView) inflate.findViewById(R.id.text3)).setText(Utils.StringUtils.unitsFormat("Всього ", next.sumResult, "година", "години", " годин"));
            inflate.setTag(next);
            inflate.setOnClickListener(this);
            this.mListContainer.addView(inflate);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), (Class<?>) WorkloadSubjectActivity.class);
        intent.putExtra("workload", (HalfWorkload) v.getTag());
        startActivity(intent);
    }
}
