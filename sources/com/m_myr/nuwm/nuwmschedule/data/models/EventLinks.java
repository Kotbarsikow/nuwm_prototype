package com.m_myr.nuwm.nuwmschedule.data.models;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.SerializedName;
import com.m_myr.nuwm.nuwmschedule.data.models.ItemTimetableContract;
import com.m_myr.nuwm.nuwmschedule.data.repositories.EventRepository;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.EntityFlags;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.EventData;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.MergeEntity;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: classes2.dex */
public class EventLinks implements Serializable, ItemTimetableContract, MergeEntity, EventData {

    @SerializedName("lesson_uid")
    String attachLessonUid;

    @SerializedName("attendees_count")
    int attendees_count;

    @SerializedName(TypedValues.Custom.S_COLOR)
    int color;

    @SerializedName("end")
    Date endDate;
    private transient Event event;
    protected byte flags = 0;

    @SerializedName("hangout")
    boolean haveHangoutLink;

    @SerializedName("id")
    String id;

    @SerializedName(FirebaseAnalytics.Param.LOCATION)
    String location;

    @SerializedName("out_of_bounds")
    boolean multipleDays;

    @SerializedName("onlineT")
    public String onlineType;

    @SerializedName("start")
    Date startDate;

    @SerializedName("title")
    String title;

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.EntityFlags
    public /* synthetic */ boolean checkAttribute(byte b) {
        return EntityFlags.CC.$default$checkAttribute(this, b);
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
    public /* synthetic */ String getEndTime() {
        String format;
        format = new SimpleDateFormat("HH:mm").format(getEndDate());
        return format;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.ItemTimetableContract
    public /* synthetic */ String getFakeId() {
        return ItemTimetableContract.CC.$default$getFakeId(this);
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.ItemTimetableContract
    public /* synthetic */ int getStartDay() {
        return ItemTimetableContract.CC.$default$getStartDay(this);
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.ItemTimetableContract
    public /* synthetic */ String getStartTime() {
        String format;
        format = new SimpleDateFormat("HH:mm").format(getStartDate());
        return format;
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

    public Event resolve() {
        if (this.event == null) {
            Event eventById = EventRepository.getEventById(this.id);
            this.event = eventById;
            if (eventById == null) {
                throw new RuntimeException("EventRepository Corrupted");
            }
            String str = this.attachLessonUid;
            if (str != null) {
                eventById.setAttachLessonUid(str);
            }
        }
        String str2 = this.attachLessonUid;
        if (str2 != null) {
            this.event.setAttachLessonUid(str2);
        }
        return this.event;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.ItemTimetableContract
    public String getDescription() {
        return "";
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.EntityFlags
    public byte getFlag() {
        return this.flags;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.EntityFlags
    public void setFlag(byte newFlag) {
        this.flags = newFlag;
    }

    public boolean isHaveHangoutLink() {
        return this.haveHangoutLink;
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
        throw new RuntimeException("Organizer Corrupted");
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
        if (i == 0) {
            return "лише ви";
        }
        if (i >= 9999999) {
            return "багато учасників";
        }
        return Utils.StringUtils.unitsFormat("", i, "учасник", "учасника", "учасників");
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
        if (obj instanceof EventLinks) {
            return this.id.equals(((EventLinks) obj).getId());
        }
        if (obj instanceof String) {
            return this.id.equals(obj);
        }
        return false;
    }

    public boolean isMeet() {
        return this.haveHangoutLink;
    }

    public String getId() {
        return this.id;
    }

    public String toString() {
        return Utils.getGsonTimetable().toJson(this);
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
