package com.m_myr.nuwm.nuwmschedule.data.models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class IndividualCurriculum {

    @SerializedName("semester_1")
    Semester firstSemester;

    @SerializedName("semester_2")
    Semester secondSemester;

    @SerializedName("select")
    Semester selectSubject;

    public Semester getFirstSemester() {
        return this.firstSemester;
    }

    public Semester getSecondSemester() {
        return this.secondSemester;
    }

    public Semester getSelectSubject() {
        return this.selectSubject;
    }

    public static class Semester implements Serializable {

        @SerializedName("subject")
        ArrayList<String[]> arrayList;

        @SerializedName("totals")
        String[] totals;

        public String[] getTotals() {
            return this.totals;
        }

        public ArrayList<String[]> getArrayList() {
            return this.arrayList;
        }
    }
}
