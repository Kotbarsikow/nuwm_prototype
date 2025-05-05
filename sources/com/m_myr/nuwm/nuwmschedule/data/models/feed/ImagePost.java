package com.m_myr.nuwm.nuwmschedule.data.models.feed;

import android.graphics.Point;
import com.google.gson.annotations.SerializedName;
import com.m_myr.nuwm.nuwmschedule.utils.FlagsUtils;

/* loaded from: classes2.dex */
public class ImagePost extends PostMessage {
    public static final int POST_IMAGE_CARD = 4;

    @SerializedName("colors")
    long colors;

    @SerializedName("flags")
    int flags;

    @SerializedName("poster_image")
    String image;

    @SerializedName("link")
    String link;

    @SerializedName("size")
    Point size;

    @SerializedName("gravity")
    short text_params;

    public String getImage() {
        return this.image;
    }

    public String getLink() {
        return this.link;
    }

    public Point getSize() {
        return this.size;
    }

    public int getTextTitleGravity() {
        return this.text_params / 1000;
    }

    public int getTextSubtitleGravity() {
        return this.text_params % 1000;
    }

    public boolean isConstraint() {
        Point point = this.size;
        return (point == null || point.x == 0 || this.size.y == 0) ? false : true;
    }

    public int getFlags() {
        return this.flags;
    }

    public boolean isShadowTextDark() {
        return FlagsUtils.checkAttribute(this.flags, 1);
    }

    public boolean isShadowTextLight() {
        return FlagsUtils.checkAttribute(this.flags, 512);
    }

    public boolean isShowShadowGradient() {
        return FlagsUtils.checkAttribute(this.flags, 2);
    }

    public boolean isExpandSubtitle() {
        return FlagsUtils.checkAttribute(this.flags, 4);
    }

    public boolean isTitleGiant() {
        return !FlagsUtils.checkAttribute(this.flags, 8) && FlagsUtils.checkAttribute(this.flags, 16);
    }

    public boolean isTitleLarge() {
        return FlagsUtils.checkAttribute(this.flags, 8) && FlagsUtils.checkAttribute(this.flags, 16);
    }

    public boolean isTitleMedium() {
        return FlagsUtils.checkAttribute(this.flags, 8) && !FlagsUtils.checkAttribute(this.flags, 16);
    }

    public boolean isTitleSmall() {
        return (FlagsUtils.checkAttribute(this.flags, 8) || FlagsUtils.checkAttribute(this.flags, 16)) ? false : true;
    }

    public boolean isSubtitleLarge() {
        return FlagsUtils.checkAttribute(this.flags, 128) && FlagsUtils.checkAttribute(this.flags, 256);
    }

    public boolean isSubtitleMedium() {
        return FlagsUtils.checkAttribute(this.flags, 128) && !FlagsUtils.checkAttribute(this.flags, 256);
    }

    public boolean isSubtitleSmall() {
        return (FlagsUtils.checkAttribute(this.flags, 128) || FlagsUtils.checkAttribute(this.flags, 256)) ? false : true;
    }

    public boolean isSubtitleAutoSize() {
        return FlagsUtils.checkAttribute(this.flags, 1024);
    }

    public boolean isItalicTitle() {
        return FlagsUtils.checkAttribute(this.flags, 32);
    }

    public boolean isItalicSubtitle() {
        return FlagsUtils.checkAttribute(this.flags, 64);
    }
}
