package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

/* loaded from: classes2.dex */
public final class CreateConferenceRequest extends GenericJson {

    @Key
    private ConferenceSolutionKey conferenceSolutionKey;

    @Key
    private String requestId;

    @Key
    private ConferenceRequestStatus status;

    public ConferenceSolutionKey getConferenceSolutionKey() {
        return this.conferenceSolutionKey;
    }

    public CreateConferenceRequest setConferenceSolutionKey(ConferenceSolutionKey conferenceSolutionKey) {
        this.conferenceSolutionKey = conferenceSolutionKey;
        return this;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public CreateConferenceRequest setRequestId(String str) {
        this.requestId = str;
        return this;
    }

    public ConferenceRequestStatus getStatus() {
        return this.status;
    }

    public CreateConferenceRequest setStatus(ConferenceRequestStatus conferenceRequestStatus) {
        this.status = conferenceRequestStatus;
        return this;
    }

    @Override // com.google.api.client.json.GenericJson, com.google.api.client.util.GenericData
    public CreateConferenceRequest set(String str, Object obj) {
        return (CreateConferenceRequest) super.set(str, obj);
    }

    @Override // com.google.api.client.json.GenericJson, com.google.api.client.util.GenericData, java.util.AbstractMap
    public CreateConferenceRequest clone() {
        return (CreateConferenceRequest) super.clone();
    }
}
