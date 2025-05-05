package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

/* loaded from: classes2.dex */
public final class FreeBusyRequestItem extends GenericJson {

    @Key
    private String id;

    public String getId() {
        return this.id;
    }

    public FreeBusyRequestItem setId(String str) {
        this.id = str;
        return this;
    }

    @Override // com.google.api.client.json.GenericJson, com.google.api.client.util.GenericData
    public FreeBusyRequestItem set(String str, Object obj) {
        return (FreeBusyRequestItem) super.set(str, obj);
    }

    @Override // com.google.api.client.json.GenericJson, com.google.api.client.util.GenericData, java.util.AbstractMap
    public FreeBusyRequestItem clone() {
        return (FreeBusyRequestItem) super.clone();
    }
}
