package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

/* loaded from: classes2.dex */
public final class EventReminder extends GenericJson {

    @Key
    private String method;

    @Key
    private Integer minutes;

    public String getMethod() {
        return this.method;
    }

    public EventReminder setMethod(String str) {
        this.method = str;
        return this;
    }

    public Integer getMinutes() {
        return this.minutes;
    }

    public EventReminder setMinutes(Integer num) {
        this.minutes = num;
        return this;
    }

    @Override // com.google.api.client.json.GenericJson, com.google.api.client.util.GenericData
    public EventReminder set(String str, Object obj) {
        return (EventReminder) super.set(str, obj);
    }

    @Override // com.google.api.client.json.GenericJson, com.google.api.client.util.GenericData, java.util.AbstractMap
    public EventReminder clone() {
        return (EventReminder) super.clone();
    }
}
