package com.m_myr.nuwm.nuwmschedule.ui.activities.academicSuccess;

import androidx.lifecycle.LifecycleOwner;
import com.m_myr.nuwm.nuwmschedule.data.models.SubjectEvaluation;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public interface AcademicSuccessOwner extends LifecycleOwner {
    void showData(ArrayList<SubjectEvaluation> h1, ArrayList<SubjectEvaluation> h2);

    void showError(String toString);
}
