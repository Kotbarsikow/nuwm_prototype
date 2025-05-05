package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;
import java.util.Map;

/* loaded from: classes2.dex */
public final class ConferenceParametersAddOnParameters extends GenericJson {

    @Key
    private Map<String, String> parameters;

    public Map<String, String> getParameters() {
        return this.parameters;
    }

    public ConferenceParametersAddOnParameters setParameters(Map<String, String> map) {
        this.parameters = map;
        return this;
    }

    @Override // com.google.api.client.json.GenericJson, com.google.api.client.util.GenericData
    public ConferenceParametersAddOnParameters set(String str, Object obj) {
        return (ConferenceParametersAddOnParameters) super.set(str, obj);
    }

    @Override // com.google.api.client.json.GenericJson, com.google.api.client.util.GenericData, java.util.AbstractMap
    public ConferenceParametersAddOnParameters clone() {
        return (ConferenceParametersAddOnParameters) super.clone();
    }
}
