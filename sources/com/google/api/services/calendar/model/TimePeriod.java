package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.Key;

/* loaded from: classes2.dex */
public final class TimePeriod extends GenericJson {

    @Key
    private DateTime end;

    @Key
    private DateTime start;

    public DateTime getEnd() {
        return this.end;
    }

    public TimePeriod setEnd(DateTime dateTime) {
        this.end = dateTime;
        return this;
    }

    public DateTime getStart() {
        return this.start;
    }

    public TimePeriod setStart(DateTime dateTime) {
        this.start = dateTime;
        return this;
    }

    @Override // com.google.api.client.json.GenericJson, com.google.api.client.util.GenericData
    public TimePeriod set(String str, Object obj) {
        return (TimePeriod) super.set(str, obj);
    }

    @Override // com.google.api.client.json.GenericJson, com.google.api.client.util.GenericData, java.util.AbstractMap
    public TimePeriod clone() {
        return (TimePeriod) super.clone();
    }
}
