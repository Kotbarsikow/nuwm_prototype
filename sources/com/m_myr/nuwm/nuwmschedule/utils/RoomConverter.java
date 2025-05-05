package com.m_myr.nuwm.nuwmschedule.utils;

import java.util.Date;

/* loaded from: classes2.dex */
public class RoomConverter {
    public Long dateToTimestamp(Date date) {
        if (date == null) {
            return null;
        }
        return Long.valueOf(date.getTime());
    }

    public Date dateToTimestamp(Long date) {
        if (date == null) {
            return null;
        }
        return new Date(date.longValue());
    }
}
