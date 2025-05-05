package com.m_myr.nuwm.nuwmschedule.ui.activities.individuaCurriculum;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.google.firebase.messaging.Constants;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.models.IndividualCurriculum;
import java.util.Iterator;

/* loaded from: classes2.dex */
public class IndividualCurriculumFragment extends Fragment {
    private LinearLayout mListContainer;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = getLayoutInflater().inflate(R.layout.inplan_fragment, (ViewGroup) null, false);
        this.mListContainer = (LinearLayout) inflate.findViewById(R.id.list_container);
        IndividualCurriculum.Semester semester = (IndividualCurriculum.Semester) getArguments().getSerializable(Constants.ScionAnalytics.MessageType.DATA_MESSAGE);
        if (getArguments().getBoolean("compact")) {
            buildCompact(semester);
        } else {
            buildFullsize(semester);
        }
        if (semester.getArrayList().size() == 0) {
            this.mListContainer.addView(getLayoutInflater().inflate(R.layout.empty_state, (ViewGroup) this.mListContainer, false));
        }
        return inflate;
    }

    private void buildFullsize(IndividualCurriculum.Semester data) {
        Iterator<String[]> it = data.getArrayList().iterator();
        while (it.hasNext()) {
            String[] next = it.next();
            View inflate = getLayoutInflater().inflate(R.layout.text_inplan_line, this.mListContainer, false);
            ((TextView) inflate.findViewById(R.id.main_text)).setText(next[0].trim().replace("   ", ""));
            ((TextView) inflate.findViewById(R.id.sub_text)).setText(next[8]);
            ((TextView) inflate.findViewById(R.id.text1)).setText(getString(R.string.ects_credits, next[1]));
            ((TextView) inflate.findViewById(R.id.text2)).setText(getString(R.string.wtf_inplan, next[7]));
            ((TextView) inflate.findViewById(R.id.text3)).setText(getString(R.string.lab_inplan, next[3]));
            ((TextView) inflate.findViewById(R.id.text4)).setText(getString(R.string.prs_inplan, next[4]));
            ((TextView) inflate.findViewById(R.id.text5)).setText(getString(R.string.srs_inplan, next[5]));
            ((TextView) inflate.findViewById(R.id.text6)).setText(getString(R.string.lect_inplan, next[2]));
            ((TextView) inflate.findViewById(R.id.text7)).setText(getString(R.string.all_inplan, next[6]));
            ((TextView) inflate.findViewById(R.id.other_text)).setText(next[10]);
            this.mListContainer.addView(inflate);
        }
    }

    private void buildCompact(IndividualCurriculum.Semester data) {
        Iterator<String[]> it = data.getArrayList().iterator();
        while (it.hasNext()) {
            String[] next = it.next();
            View inflate = getLayoutInflater().inflate(R.layout.text_inplan_line, this.mListContainer, false);
            ((TextView) inflate.findViewById(R.id.main_text)).setText(next[0].trim().replace("   ", ""));
            ((TextView) inflate.findViewById(R.id.sub_text)).setText(String.format("Півріччя %s", next[1]));
            ((TextView) inflate.findViewById(R.id.text1)).setText(getString(R.string.ects_credits, next[2]));
            inflate.findViewById(R.id.text2).setVisibility(8);
            inflate.findViewById(R.id.text3).setVisibility(8);
            inflate.findViewById(R.id.text4).setVisibility(8);
            inflate.findViewById(R.id.text5).setVisibility(8);
            inflate.findViewById(R.id.text6).setVisibility(8);
            ((TextView) inflate.findViewById(R.id.text7)).setText(getString(R.string.all_inplan, next[3]));
            ((TextView) inflate.findViewById(R.id.other_text)).setText(next[5]);
            this.mListContainer.addView(inflate);
        }
    }
}
