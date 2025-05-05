package com.m_myr.nuwm.nuwmschedule.network.models;

import com.google.gson.annotations.SerializedName;
import com.m_myr.nuwm.nuwmschedule.data.models.EctsScale;
import com.m_myr.nuwm.nuwmschedule.data.models.NationalScale;
import com.m_myr.nuwm.nuwmschedule.data.models.Rating;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class StatEvaluation {

    @SerializedName("all_half")
    ArrayList<MyGroupUser> allHalf;

    @SerializedName("avg")
    private float avg;

    @SerializedName("current_half")
    ArrayList<MyGroupUser> currentHalf;

    @SerializedName("ects")
    EctsScale ects;

    @SerializedName("alltime")
    ArrayList<MyAlltimeRating> myAllStageRating;

    @SerializedName("national")
    NationalScale national;

    @SerializedName("rating")
    Rating rating;

    public static class MyAlltimeRating {
        private float avg;
        private String grp_name;
        private int id_grp;
    }

    public static class MyGroupUser {
        private float avg;
        private int id;
        private String name;
        private float subjects;
        private float totals;

        public int getId() {
            return this.id;
        }

        public float getTotals() {
            return this.totals;
        }

        public float getSubjects() {
            return this.subjects;
        }

        public String getName() {
            return this.name;
        }

        public float getAvg() {
            return this.avg;
        }
    }

    public ArrayList<MyGroupUser> getCurrentHalf() {
        return this.currentHalf;
    }

    public float getAvg() {
        return this.avg;
    }

    public EctsScale getEcts() {
        return this.ects;
    }

    public NationalScale getNational() {
        return this.national;
    }

    public ArrayList<MyGroupUser> getAllHalf() {
        return this.allHalf;
    }

    public ArrayList<MyAlltimeRating> getMyAllStageRating() {
        return this.myAllStageRating;
    }

    public Rating getRating() {
        return this.rating;
    }
}
