package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Data;
import com.google.api.client.util.Key;
import java.util.List;

/* loaded from: classes2.dex */
public final class FreeBusyGroup extends GenericJson {

    @Key
    private List<String> calendars;

    @Key
    private List<Error> errors;

    static {
        Data.nullOf(Error.class);
    }

    public List<String> getCalendars() {
        return this.calendars;
    }

    public FreeBusyGroup setCalendars(List<String> list) {
        this.calendars = list;
        return this;
    }

    public List<Error> getErrors() {
        return this.errors;
    }

    public FreeBusyGroup setErrors(List<Error> list) {
        this.errors = list;
        return this;
    }

    @Override // com.google.api.client.json.GenericJson, com.google.api.client.util.GenericData
    public FreeBusyGroup set(String str, Object obj) {
        return (FreeBusyGroup) super.set(str, obj);
    }

    @Override // com.google.api.client.json.GenericJson, com.google.api.client.util.GenericData, java.util.AbstractMap
    public FreeBusyGroup clone() {
        return (FreeBusyGroup) super.clone();
    }
}
