package com.m_myr.nuwm.nuwmschedule.data.models;

import android.graphics.Color;
import androidx.core.view.GravityCompat;
import com.google.common.base.Ascii;
import com.google.gson.annotations.SerializedName;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.EntityFlagsInt;
import java.io.Serializable;

/* loaded from: classes2.dex */
public class ProfileCardButton implements Serializable, EntityFlagsInt {
    public static final int CENTER = 3;
    public static final int CHAIN = 32;
    public static final int LEFT = 1;
    public static final int NOWRAP = 64;
    public static final int OUTLINED_BUTTON = 4;
    public static final int RIGHT = 2;
    public static final int TEXT_BUTTON = 8;
    public static final int UNELEVATED_BUTTON = 16;
    public static final int WRAP = 128;

    @SerializedName("action")
    private int action;

    @SerializedName("action_text")
    private String actionText;

    @SerializedName("action_intent")
    private String action_intent;

    @SerializedName("enabled")
    private boolean enabled;

    @SerializedName("button_id")
    private int id;

    @SerializedName("style")
    private int style;

    @SerializedName("text")
    private String text;

    @SerializedName("tint_color")
    private int tintColor;

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

    public int getAction() {
        return this.action;
    }

    public String getActionText() {
        return this.actionText;
    }

    public String getActionIntent() {
        return this.action_intent;
    }

    public int getStyle() {
        return this.style;
    }

    public String getText() {
        return this.text;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public int getBackgroundTintColor() {
        return Color.argb(255, Color.red(this.tintColor), Color.green(this.tintColor), Color.blue(this.tintColor));
    }

    public static int colorFromByte(byte b) {
        return Color.argb(255, (int) Math.round((((b & 224) >>> 5) / 7.0d) * 255.0d), (int) Math.round((((b & Ascii.FS) >>> 2) / 7.0d) * 255.0d), (int) Math.round(((b & 3) / 3.0d) * 255.0d));
    }

    public int getTextTintColor() {
        return colorFromByte((byte) Color.alpha(this.tintColor));
    }

    public int getTheme() {
        if (checkAttribute(16)) {
            return R.layout.single_unelevatedbutton;
        }
        if (checkAttribute(4)) {
            return R.layout.single_outlinedbutton;
        }
        checkAttribute(8);
        return R.layout.single_textbutton;
    }

    public int getGravity() {
        if (checkAttribute(3)) {
            return 17;
        }
        return (!checkAttribute(1) && checkAttribute(2)) ? GravityCompat.END : GravityCompat.START;
    }

    public int getFlexWrap() {
        if (checkAttribute(64)) {
            return 0;
        }
        checkAttribute(128);
        return 1;
    }

    public boolean isChainGravity() {
        return checkAttribute(32);
    }

    public int getTintColor() {
        return this.tintColor;
    }
}
