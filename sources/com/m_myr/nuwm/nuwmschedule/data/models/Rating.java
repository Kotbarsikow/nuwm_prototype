package com.m_myr.nuwm.nuwmschedule.data.models;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class Rating {

    @SerializedName("all_counter")
    private ArrayList<RoundCount> allCounter;

    @SerializedName("all_global_count")
    private float allGlobalCount;

    @SerializedName("all_institute_count")
    private float allInstituteCount;

    @SerializedName("all_prof_count")
    private float allProfCount;

    @SerializedName("all_course_count")
    private float all_CourseCount;

    @SerializedName("all_grp_count")
    private float all_GrpCount;

    @SerializedName("course_counter")
    private ArrayList<RoundCount> courseCounter;

    @SerializedName("institute_counter")
    private ArrayList<RoundCount> instituteCounter;

    @SerializedName("my_course_score")
    private float myCourseScore;

    @SerializedName("my_global_score")
    private float myGlobalScore;

    @SerializedName("my_grp_score")
    private float myGroupScore;

    @SerializedName("my_institute_score")
    private float myInstituteScore;

    @SerializedName("my_prof_score")
    private float myProfScore;

    @SerializedName("prof_counter")
    private ArrayList<RoundCount> profCounter;

    public ArrayList<RoundCount> getProfCounter() {
        return this.profCounter;
    }

    public ArrayList<RoundCount> getCourseCounter() {
        return this.courseCounter;
    }

    public ArrayList<RoundCount> getAllCounter() {
        return this.allCounter;
    }

    public ArrayList<RoundCount> getInstituteCounter() {
        return this.instituteCounter;
    }

    public static class RoundCount {
        public int count = 0;
        public int round;

        public RoundCount(int round) {
            this.round = round;
        }

        public int hashCode() {
            return this.round;
        }

        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            return this == obj || obj.hashCode() == hashCode();
        }
    }

    public int getMyGlobalScore() {
        return (int) this.myGlobalScore;
    }

    public float getMyGroupScore() {
        return this.myGroupScore;
    }

    public float getMyCourseScore() {
        return this.myCourseScore;
    }

    public float getMyProfScore() {
        return this.myProfScore;
    }

    public float getMyInstituteScore() {
        return this.myInstituteScore;
    }

    public float getAllGlobalCount() {
        return this.allGlobalCount;
    }

    public float getAll_GrpCount() {
        return this.all_GrpCount;
    }

    public float getAll_CourseCount() {
        return this.all_CourseCount;
    }

    public float getAllProfCount() {
        return this.allProfCount;
    }

    public float getAllInstituteCount() {
        return this.allInstituteCount;
    }
}
