package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.Key;

/* loaded from: classes2.dex */
public final class EventDateTime extends GenericJson {

    @Key
    private DateTime date;

    @Key
    private DateTime dateTime;

    @Key
    private String timeZone;

    public DateTime getDate() {
        return this.date;
    }

    public EventDateTime setDate(DateTime dateTime) {
        this.date = dateTime;
        return this;
    }

    public DateTime getDateTime() {
        return this.dateTime;
    }

    public EventDateTime setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public String getTimeZone() {
        return this.timeZone;
    }

    public EventDateTime setTimeZone(String str) {
        this.timeZone = str;
        return this;
    }

    @Override // com.google.api.client.json.GenericJson, com.google.api.client.util.GenericData
    public EventDateTime set(String str, Object obj) {
        return (EventDateTime) super.set(str, obj);
    }

    @Override // com.google.api.client.json.GenericJson, com.google.api.client.util.GenericData, java.util.AbstractMap
    public EventDateTime clone() {
        return (EventDateTime) super.clone();
    }
}
