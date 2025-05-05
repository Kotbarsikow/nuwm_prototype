package com.m_myr.nuwm.nuwmschedule.data.models.search;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: classes2.dex */
public class BaseSearchResult implements Serializable {

    @SerializedName("highlight")
    public String highlight;

    @SerializedName("id")
    public int id;

    @SerializedName("text")
    public String text;

    @SerializedName("type")
    public int type;

    String getDescription() {
        return "";
    }

    public int getType() {
        return this.type;
    }

    public String getText() {
        return this.text;
    }

    public int getId() {
        return this.id;
    }

    public String getHighlight() {
        return this.highlight;
    }

    public int hashCode() {
        return this.id;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        return (obj instanceof BaseSearchResult) && ((BaseSearchResult) obj).getId() == this.id;
    }
}
