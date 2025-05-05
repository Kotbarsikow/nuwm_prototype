package com.m_myr.nuwm.nuwmschedule.data.models;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes2.dex */
public class GroupExtended extends Group {

    @SerializedName("form")
    private String form;

    @SerializedName("id_fac")
    private int idFac;

    @SerializedName("prof_shirt")
    private String professionShirt;

    public String getProfessionShirt() {
        return this.professionShirt;
    }

    public String getForm() {
        return this.form;
    }

    public int getIdFac() {
        return this.idFac;
    }
}
