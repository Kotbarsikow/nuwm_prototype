package com.m_myr.nuwm.nuwmschedule.data.models;

import android.content.Intent;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.gson.annotations.SerializedName;
import com.m_myr.nuwm.nuwmschedule.data.models.ItemTimetableContract;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.EntityFlags;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.ShareIntent;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;
import j$.util.DesugarTimeZone;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: classes2.dex */
public class Lesson implements Serializable, ItemTimetableContract, ShareIntent {
    private transient String _cacheUid;

    @SerializedName(TypedValues.Custom.S_COLOR)
    protected int color;

    @SerializedName("end")
    protected Date endDate;

    @SerializedName("flags")
    protected byte flags;

    @SerializedName("num_lesson")
    protected int numLesson;

    @SerializedName("room")
    protected String room;

    @SerializedName("start")
    protected Date startDate;

    @SerializedName("stream_consists")
    protected String streamConsists;

    @SerializedName("streams")
    protected String streams;

    @SerializedName("subgroup")
    protected String subgroup;

    @SerializedName("subject")
    protected String subject;

    @SerializedName("teacher")
    protected String teacher;

    @SerializedName("type")
    protected String type;

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

    protected Lesson() {
        this.flags = (byte) 0;
        this.color = 1;
    }

    public String getStreamConsists() {
        return this.streamConsists;
    }

    public Lesson(String room, String subject, String teacher, String subgroup, String streams, Date startDate, Date endDate, String type, byte flags, int color, int numLesson, String streamConsists) {
        this.room = room;
        this.subject = subject;
        this.teacher = teacher;
        this.subgroup = subgroup;
        this.streams = streams;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
        this.flags = flags;
        this.color = color;
        this.numLesson = numLesson;
        this.streamConsists = streamConsists;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.ShareIntent
    public String createShareText() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE, d MMMM");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HH:mm");
        simpleDateFormat.setTimeZone(DesugarTimeZone.getTimeZone("Europe/Kiev"));
        return this.subject + ", " + this.type + "," + this.subgroup + "\nУ " + simpleDateFormat.format(this.startDate) + " о " + simpleDateFormat2.format(this.startDate) + "\nАудиторія " + this.room + "\n";
    }

    public String getRoom() {
        return this.room;
    }

    public String getSubject() {
        return this.subject;
    }

    public String getTeacher() {
        return this.teacher;
    }

    public String getSubgroup() {
        return this.subgroup;
    }

    public String getStreams() {
        return this.streams;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.ItemTimetableContract
    public Date getStartDate() {
        return this.startDate;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.ItemTimetableContract
    public Date getEndDate() {
        return this.endDate;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.ItemTimetableContract, com.m_myr.nuwm.nuwmschedule.domain.interfaces.ShareIntent
    public String getType() {
        return this.type;
    }

    public byte getFlags() {
        return this.flags;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.ItemTimetableContract
    public int getColor() {
        return this.color;
    }

    public int getNumLesson() {
        return this.numLesson;
    }

    public String getStartDateFormat() {
        return new SimpleDateFormat("EEEE, d MMMM").format(this.startDate);
    }

    public String getUriData() {
        return "uid=" + generateInternalUid() + "&les=" + getNumLesson() + "&orgs=" + getStartDate() + "&t" + getType() + "&sub" + getSubgroup();
    }

    public int hashCode() {
        return generateInternalUid().hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof Lesson) && ((Lesson) obj).generateInternalUid().equals(generateInternalUid())) {
            try {
                if (((Lesson) obj).getStartDate().equals(getStartDate()) && ((Lesson) obj).getEndDate().equals(getEndDate()) && ((Lesson) obj).getAttendees().equals(getAttendees()) && ((Lesson) obj).getOrganizer().equals(getOrganizer())) {
                    return ((Lesson) obj).getTitle().equals(getTitle());
                }
                return false;
            } catch (Exception unused) {
            }
        }
        return false;
    }

    public String generateInternalUid() {
        if (this._cacheUid == null) {
            this._cacheUid = Utils.md5(getSubject().trim() + getNumLesson() + getType() + getSubgroup());
        }
        return this._cacheUid;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.ItemTimetableContract
    public String getFastTitle() {
        return getTitle();
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.ItemTimetableContract
    public String getTitle() {
        return this.subject;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.ItemTimetableContract
    public String getLocation() {
        return this.room;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.ItemTimetableContract
    public String getOrganizer() {
        return this.teacher;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.ItemTimetableContract
    public String getAttendees() {
        return this.subgroup;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.ItemTimetableContract
    public String getDescription() {
        return this.streams;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.EntityFlags
    public boolean isHidden() {
        return (this.flags & 32) == 32;
    }
}
