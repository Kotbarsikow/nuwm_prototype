package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

/* loaded from: classes2.dex */
public final class ColorDefinition extends GenericJson {

    @Key
    private String background;

    @Key
    private String foreground;

    public String getBackground() {
        return this.background;
    }

    public ColorDefinition setBackground(String str) {
        this.background = str;
        return this;
    }

    public String getForeground() {
        return this.foreground;
    }

    public ColorDefinition setForeground(String str) {
        this.foreground = str;
        return this;
    }

    @Override // com.google.api.client.json.GenericJson, com.google.api.client.util.GenericData
    public ColorDefinition set(String str, Object obj) {
        return (ColorDefinition) super.set(str, obj);
    }

    @Override // com.google.api.client.json.GenericJson, com.google.api.client.util.GenericData, java.util.AbstractMap
    public ColorDefinition clone() {
        return (ColorDefinition) super.clone();
    }
}
