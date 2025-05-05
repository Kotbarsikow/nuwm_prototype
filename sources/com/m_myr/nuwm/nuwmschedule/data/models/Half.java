package com.m_myr.nuwm.nuwmschedule.data.models;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class Half {

    @SerializedName("num")
    int halfNum;

    @SerializedName("subjects")
    ArrayList<SubjectEvaluation> subjectEvaluations;

    public int getHalfNum() {
        return this.halfNum;
    }

    public ArrayList<SubjectEvaluation> getSubjectEvaluations() {
        return this.subjectEvaluations;
    }
}
