package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

/* loaded from: classes2.dex */
public final class ConferenceRequestStatus extends GenericJson {

    @Key
    private String statusCode;

    public String getStatusCode() {
        return this.statusCode;
    }

    public ConferenceRequestStatus setStatusCode(String str) {
        this.statusCode = str;
        return this;
    }

    @Override // com.google.api.client.json.GenericJson, com.google.api.client.util.GenericData
    public ConferenceRequestStatus set(String str, Object obj) {
        return (ConferenceRequestStatus) super.set(str, obj);
    }

    @Override // com.google.api.client.json.GenericJson, com.google.api.client.util.GenericData, java.util.AbstractMap
    public ConferenceRequestStatus clone() {
        return (ConferenceRequestStatus) super.clone();
    }
}
