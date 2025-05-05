package com.m_myr.nuwm.nuwmschedule.data.models;

import android.content.Intent;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.SerializedName;
import com.m_myr.nuwm.nuwmschedule.data.models.ItemTimetableContract;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.EntityFlags;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.EventData;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.MergeEntity;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.ShareIntent;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;
import j$.util.DesugarTimeZone;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: classes2.dex */
public class Event implements Serializable, ItemTimetableContract, ShareIntent, MergeEntity, EventData {

    @SerializedName("app")
    String app;

    @SerializedName("lesson_uid")
    String attachLessonUid;

    @SerializedName("attendees_count")
    int attendees_count;

    @SerializedName(TypedValues.Custom.S_COLOR)
    int color;

    @SerializedName("creator")
    String creator;

    @SerializedName("description")
    String description;

    @SerializedName("end")
    Date endDate;
    protected byte flags = 0;

    @SerializedName("hangoutLink")
    String hangoutLink;

    @SerializedName("htmlLink")
    String htmlLink;

    @SerializedName("id")
    String id;

    @SerializedName(FirebaseAnalytics.Param.LOCATION)
    String location;

    @SerializedName("out_of_bounds")
    boolean multipleDays;

    @SerializedName("onlineT")
    public String onlineType;

    @SerializedName("organizer")
    String organizer;

    @SerializedName("self")
    boolean self;

    @SerializedName("start")
    Date startDate;

    @SerializedName("title")
    String title;

    @SerializedName("updated")
    Date updated;

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.EntityFlags
    public /* synthetic */ boolean checkAttribute(byte b) {
        return EntityFlags.CC.$default$checkAttribute(this, b);
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.ShareIntent
    public /* synthetic */ Intent createShareIntent() {
        return ShareIntent.CC.$default$createShareIntent(this);
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.ItemTimetableContract
    public /* synthetic */ int getColor(int i) {
        return ItemTimetableContract.CC.$default$getColor(this, i);
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.ItemTimetableContract
    public /* synthetic */ int getEndDay() {
        return ItemTimetableContract.CC.$default$getEndDay(this);
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.ItemTimetableContract
    public /* synthetic */ String getFakeId() {
        return ItemTimetableContract.CC.$default$getFakeId(this);
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.ItemTimetableContract
    public /* synthetic */ int getStartDay() {
        return ItemTimetableContract.CC.$default$getStartDay(this);
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.EntityFlags
    public /* synthetic */ boolean isCollapsed() {
        boolean checkAttribute;
        checkAttribute = checkAttribute((byte) 16);
        return checkAttribute;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.EntityFlags
    public /* synthetic */ boolean isHidden() {
        boolean checkAttribute;
        checkAttribute = checkAttribute((byte) 32);
        return checkAttribute;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.EntityFlags
    public /* synthetic */ boolean isMerge() {
        boolean checkAttribute;
        checkAttribute = checkAttribute((byte) 64);
        return checkAttribute;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.EntityFlags
    public /* synthetic */ void removeAttribute(byte b) {
        setFlag((byte) ((~b) & getFlag()));
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.EntityFlags
    public /* synthetic */ void setAttribute(byte b) {
        setFlag((byte) (b | getFlag()));
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.EntityFlags
    public byte getFlag() {
        return this.flags;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.EntityFlags
    public void setFlag(byte newFlag) {
        this.flags = newFlag;
    }

    public boolean isMultipleDays() {
        return this.multipleDays;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.ItemTimetableContract
    public String getTitle() {
        return this.title;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.ItemTimetableContract
    public String getLocation() {
        return this.location;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.ItemTimetableContract
    public String getOrganizer() {
        String str;
        String str2 = this.organizer;
        return (str2 == null || !str2.contains("@group.calendar.google.com") || (str = this.creator) == null) ? this.organizer : str;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.ItemTimetableContract, com.m_myr.nuwm.nuwmschedule.domain.interfaces.ShareIntent
    public String getType() {
        String str = this.onlineType;
        return str != null ? str : (isMeet() && isAttachLesson()) ? "Онлайн заняття" : isMeet() ? "Онлайн подія" : "Подія календаря";
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.ItemTimetableContract
    public int getColor() {
        return this.color;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.ItemTimetableContract
    public Date getStartDate() {
        return this.startDate;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.ItemTimetableContract
    public Date getEndDate() {
        return this.endDate;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.ItemTimetableContract
    public String getAttendees() {
        int i = this.attendees_count;
        return i >= 9999999 ? "багато учасників" : Utils.StringUtils.unitsFormat("", i, "учасник", "учасника", "учасників");
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.ItemTimetableContract
    public String getDescription() {
        String str = this.description;
        return str == null ? "" : str;
    }

    public int hashCode() {
        return this.id.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj instanceof Event) {
            return this.id.equals(((Event) obj).getId());
        }
        if (obj instanceof String) {
            return this.id.equals(obj);
        }
        return false;
    }

    public String getHangoutLink() {
        return this.hangoutLink;
    }

    public boolean isMeet() {
        return !Utils.StringUtils.isBlank(this.hangoutLink);
    }

    public String getId() {
        return this.id;
    }

    public String getCreator() {
        return this.creator;
    }

    public Date getUpdated() {
        return this.updated;
    }

    public int getAttendees_count() {
        return this.attendees_count;
    }

    public String getApp() {
        return this.app;
    }

    public String getHtmlLink() {
        return this.htmlLink;
    }

    public boolean isSelf() {
        return this.self;
    }

    public String toString() {
        return Utils.getGsonTimetable().toJson(this);
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.ItemTimetableContract
    public String getStartTime() {
        return new SimpleDateFormat("HH:mm").format(this.startDate);
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.ItemTimetableContract
    public String getEndTime() {
        return new SimpleDateFormat("HH:mm").format(this.endDate);
    }

    public String getStartDateFormat() {
        return new SimpleDateFormat("EEEE, d MMMM").format(this.startDate);
    }

    public String getEndDateFormat() {
        return new SimpleDateFormat("EEEE, d MMMM").format(this.endDate);
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.ShareIntent
    public String createShareText() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE, d MMMM");
        simpleDateFormat.setTimeZone(DesugarTimeZone.getTimeZone("Europe/Kiev"));
        return getTitle() + ", " + getEndTime() + "," + getAttendees() + "\nУ " + simpleDateFormat.format(Integer.valueOf(this.startDate.getDay())) + " о " + this.startDate.getTime() + "\nУ такому місці: " + getLocation() + "\n";
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.MergeEntity
    public boolean isAttachLesson() {
        return this.attachLessonUid != null;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.MergeEntity
    public String getAttachLessonUid() {
        return this.attachLessonUid;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.MergeEntity
    public void setAttachLessonUid(String uid) {
        this.attachLessonUid = uid;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.ItemTimetableContract
    public String getFastTitle() {
        return getTitle();
    }
}
