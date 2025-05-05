package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

/* loaded from: classes2.dex */
public final class ConferenceSolution extends GenericJson {

    @Key
    private String iconUri;

    @Key
    private ConferenceSolutionKey key;

    @Key
    private String name;

    public String getIconUri() {
        return this.iconUri;
    }

    public ConferenceSolution setIconUri(String str) {
        this.iconUri = str;
        return this;
    }

    public ConferenceSolutionKey getKey() {
        return this.key;
    }

    public ConferenceSolution setKey(ConferenceSolutionKey conferenceSolutionKey) {
        this.key = conferenceSolutionKey;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public ConferenceSolution setName(String str) {
        this.name = str;
        return this;
    }

    @Override // com.google.api.client.json.GenericJson, com.google.api.client.util.GenericData
    public ConferenceSolution set(String str, Object obj) {
        return (ConferenceSolution) super.set(str, obj);
    }

    @Override // com.google.api.client.json.GenericJson, com.google.api.client.util.GenericData, java.util.AbstractMap
    public ConferenceSolution clone() {
        return (ConferenceSolution) super.clone();
    }
}
