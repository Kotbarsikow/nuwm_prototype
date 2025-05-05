package com.m_myr.nuwm.nuwmschedule.data.models;

import com.m_myr.nuwm.nuwmschedule.domain.interfaces.EntityFlags;
import java.util.Date;
import java.util.GregorianCalendar;

/* loaded from: classes2.dex */
public interface ItemTimetableContract extends EntityFlags {
    String getAttendees();

    int getColor();

    int getColor(int colorDefault);

    String getDescription();

    Date getEndDate();

    int getEndDay();

    String getEndTime();

    String getFakeId();

    String getFastTitle();

    String getLocation();

    String getOrganizer();

    Date getStartDate();

    int getStartDay();

    String getStartTime();

    String getTitle();

    String getType();

    /* renamed from: com.m_myr.nuwm.nuwmschedule.data.models.ItemTimetableContract$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static String $default$getFakeId(ItemTimetableContract _this) {
            return String.valueOf(_this.getFastTitle().hashCode()) + _this.getStartDate().getTime();
        }

        public static int $default$getStartDay(ItemTimetableContract _this) {
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.setTime(_this.getStartDate());
            return gregorianCalendar.get(6);
        }

        public static int $default$getEndDay(ItemTimetableContract _this) {
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.setTime(_this.getEndDate());
            return gregorianCalendar.get(6);
        }

        public static int $default$getColor(ItemTimetableContract _this, int colorDefault) {
            int color = _this.getColor();
            return (color < 0 || color >= 3) ? color : colorDefault;
        }
    }
}
