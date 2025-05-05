package com.m_myr.nuwm.nuwmschedule.data.models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes2.dex */
public class SubjectEvaluation implements Serializable {

    @SerializedName("subject_id")
    int id;

    @SerializedName("marks")
    ArrayList<Mark> marks;

    @SerializedName("subject_name")
    String name;

    @SerializedName("total")
    float totalMarks;

    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }

    public float getTotalMarks() {
        return this.totalMarks - getTotalLessonDiff();
    }

    public ArrayList<Mark> getMarks() {
        return this.marks;
    }

    public float getTotalLessonDiff() {
        Iterator<Mark> it = this.marks.iterator();
        float f = 0.0f;
        while (it.hasNext()) {
            Mark next = it.next();
            if (next.type == null || !next.type.toLowerCase().contains("модуль")) {
                try {
                    f += Float.parseFloat(next.getFirstMark());
                } catch (NumberFormatException unused) {
                }
                try {
                    f += Float.parseFloat(next.getSecondMark());
                } catch (NumberFormatException unused2) {
                }
            }
        }
        return f - getTotalLessonMark();
    }

    public float getTotalLessonMark() {
        Iterator<Mark> it = this.marks.iterator();
        float f = 0.0f;
        while (it.hasNext()) {
            Mark next = it.next();
            if (next.type == null || !next.type.toLowerCase().contains("модуль")) {
                try {
                    f += Float.parseFloat(next.getFirstMark());
                } catch (NumberFormatException unused) {
                }
                try {
                    f += Float.parseFloat(next.getSecondMark());
                } catch (NumberFormatException unused2) {
                }
            }
        }
        if (f > 60.0f) {
            return 60.0f;
        }
        return f;
    }
}
