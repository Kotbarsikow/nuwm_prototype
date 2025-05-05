package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

/* loaded from: classes2.dex */
public final class AclRule extends GenericJson {

    @Key
    private String etag;

    @Key
    private String id;

    @Key
    private String kind;

    @Key
    private String role;

    @Key
    private Scope scope;

    public String getEtag() {
        return this.etag;
    }

    public AclRule setEtag(String str) {
        this.etag = str;
        return this;
    }

    public String getId() {
        return this.id;
    }

    public AclRule setId(String str) {
        this.id = str;
        return this;
    }

    public String getKind() {
        return this.kind;
    }

    public AclRule setKind(String str) {
        this.kind = str;
        return this;
    }

    public String getRole() {
        return this.role;
    }

    public AclRule setRole(String str) {
        this.role = str;
        return this;
    }

    public Scope getScope() {
        return this.scope;
    }

    public AclRule setScope(Scope scope) {
        this.scope = scope;
        return this;
    }

    @Override // com.google.api.client.json.GenericJson, com.google.api.client.util.GenericData
    public AclRule set(String str, Object obj) {
        return (AclRule) super.set(str, obj);
    }

    @Override // com.google.api.client.json.GenericJson, com.google.api.client.util.GenericData, java.util.AbstractMap
    public AclRule clone() {
        return (AclRule) super.clone();
    }

    public static final class Scope extends GenericJson {

        @Key
        private String type;

        @Key
        private String value;

        public String getType() {
            return this.type;
        }

        public Scope setType(String str) {
            this.type = str;
            return this;
        }

        public String getValue() {
            return this.value;
        }

        public Scope setValue(String str) {
            this.value = str;
            return this;
        }

        @Override // com.google.api.client.json.GenericJson, com.google.api.client.util.GenericData
        public Scope set(String str, Object obj) {
            return (Scope) super.set(str, obj);
        }

        @Override // com.google.api.client.json.GenericJson, com.google.api.client.util.GenericData, java.util.AbstractMap
        public Scope clone() {
            return (Scope) super.clone();
        }
    }
}
