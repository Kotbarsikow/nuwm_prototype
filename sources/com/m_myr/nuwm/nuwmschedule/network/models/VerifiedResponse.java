package com.m_myr.nuwm.nuwmschedule.network.models;

import com.google.gson.annotations.SerializedName;
import java.util.Date;

/* loaded from: classes2.dex */
public class VerifiedResponse {

    @SerializedName("code")
    String barcode;

    @SerializedName("last_edit_date")
    Date lastEditDate;

    @SerializedName("picture")
    String picture;

    @SerializedName("verified")
    boolean verified;

    public boolean isVerified() {
        return this.verified;
    }

    public String getPicture() {
        return this.picture;
    }

    public String getBarcode() {
        return this.barcode;
    }

    public Date getLastEditDate() {
        return this.lastEditDate;
    }
}
