package com.m_myr.nuwm.nuwmschedule.data.models;

import android.content.Intent;
import android.util.Log;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;
import j$.util.DesugarTimeZone;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.mortbay.jetty.MimeTypes;

@Deprecated
/* loaded from: classes2.dex */
public class LessonEvent extends Lesson implements Serializable {
    protected String description;
    protected String eventId;
    private byte isVideoCall = 0;

    public LessonEvent(String subject, Date startDate, Date endDate, String eventId, int color, String description, String organizer, String eventLocation) {
        this.description = description;
        this.type = "Подія каленаря";
        this.subject = subject;
        this.endDate = endDate;
        this.startDate = startDate;
        this.eventId = eventId;
        this.color = color;
        this.numLesson = -1;
        if (Utils.StringUtils.isBlank(subject)) {
            this.subject = Utils.StringUtils.substrElips(description, 20);
        }
        this.teacher = organizer;
        this.room = eventLocation;
    }

    public String getEventId() {
        return this.eventId;
    }

    public boolean isVideoCall() {
        if (this.isVideoCall == 0) {
            if (Utils.StringUtils.isBlank(getVideoCall())) {
                this.isVideoCall = (byte) 1;
            } else {
                this.isVideoCall = (byte) 2;
            }
        }
        return this.isVideoCall == 2;
    }

    public String getVideoCall() {
        try {
            if (this.description.indexOf("-::~:~::~:~:~:") < 0) {
                return null;
            }
            int indexOf = this.description.indexOf("https://meet.google.com");
            return this.description.substring(indexOf, this.description.indexOf("\n", indexOf)).trim();
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.Lesson, com.m_myr.nuwm.nuwmschedule.data.models.ItemTimetableContract
    public String getDescription() {
        Log.d("getDescription", this.description);
        String str = this.description;
        if (str == null) {
            return null;
        }
        int indexOf = str.indexOf("-::~:~::~:~:~:");
        if (indexOf >= 0) {
            return this.description.substring(0, indexOf);
        }
        return this.description;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.Lesson, com.m_myr.nuwm.nuwmschedule.domain.interfaces.ShareIntent
    public String createShareText() {
        new SimpleDateFormat("EEEE, d MMMM").setTimeZone(DesugarTimeZone.getTimeZone("Europe/Kiev"));
        return this.subject + "\n Коли: " + String.format("%s - %s, %s", getStartTime(), getEndTime(), getStartDateFormat()) + "\nДе: " + this.room;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.models.Lesson, com.m_myr.nuwm.nuwmschedule.domain.interfaces.ShareIntent
    public Intent createShareIntent() {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType(MimeTypes.TEXT_PLAIN);
        intent.putExtra("android.intent.extra.SUBJECT", "Подія");
        intent.putExtra("android.intent.extra.TEXT", createShareText());
        return Intent.createChooser(intent, "Поділитися подією");
    }
}
