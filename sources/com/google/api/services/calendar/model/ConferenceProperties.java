package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;
import java.util.List;

/* loaded from: classes2.dex */
public final class ConferenceProperties extends GenericJson {

    @Key
    private List<String> allowedConferenceSolutionTypes;

    public List<String> getAllowedConferenceSolutionTypes() {
        return this.allowedConferenceSolutionTypes;
    }

    public ConferenceProperties setAllowedConferenceSolutionTypes(List<String> list) {
        this.allowedConferenceSolutionTypes = list;
        return this;
    }

    @Override // com.google.api.client.json.GenericJson, com.google.api.client.util.GenericData
    public ConferenceProperties set(String str, Object obj) {
        return (ConferenceProperties) super.set(str, obj);
    }

    @Override // com.google.api.client.json.GenericJson, com.google.api.client.util.GenericData, java.util.AbstractMap
    public ConferenceProperties clone() {
        return (ConferenceProperties) super.clone();
    }
}
