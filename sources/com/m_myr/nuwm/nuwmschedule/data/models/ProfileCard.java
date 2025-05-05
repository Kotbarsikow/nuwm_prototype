package com.m_myr.nuwm.nuwmschedule.data.models;

import com.google.gson.annotations.SerializedName;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.EntityFlagsInt;
import java.io.Serializable;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class ProfileCard implements Serializable, EntityFlagsInt {
    public static final int MARGIN_TITLE_BOTTOM = 2;
    public static final int MARGIN_TITLE_TOP = 1;

    @SerializedName("body")
    private String body;

    @SerializedName("buttons")
    ArrayList<ProfileCardButton> cardButtons;

    @SerializedName("divider")
    private boolean divider;

    @SerializedName("id")
    private int id;

    @SerializedName("style")
    private int style;

    @SerializedName("title")
    private String title;

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.EntityFlagsInt
    public /* synthetic */ boolean checkAttribute(int i) {
        return EntityFlagsInt.CC.$default$checkAttribute(this, i);
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.EntityFlagsInt
    public /* synthetic */ void removeAttribute(int i) {
        setFlag((~i) & getFlag());
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.EntityFlagsInt
    public /* synthetic */ void setAttribute(int i) {
        setFlag(i | getFlag());
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.EntityFlagsInt
    public int getFlag() {
        return this.style;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.EntityFlagsInt
    public void setFlag(int newFlag) {
        this.style = newFlag;
    }

    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getBody() {
        return this.body;
    }

    public boolean isDivider() {
        return this.divider;
    }

    public ArrayList<ProfileCardButton> getCardButtons() {
        return this.cardButtons;
    }

    public int getStyle() {
        return this.style;
    }
}
