package com.google.api.client.googleapis.json;

import com.google.api.client.http.HttpResponse;
import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.util.Data;
import com.google.api.client.util.Key;
import com.google.firebase.messaging.Constants;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/* loaded from: classes2.dex */
public class GoogleJsonError extends GenericJson {

    @Key
    private int code;

    @Key
    private List<ErrorInfo> errors;

    @Key
    private String message;

    public static GoogleJsonError parse(JsonFactory jsonFactory, HttpResponse httpResponse) throws IOException {
        return (GoogleJsonError) new JsonObjectParser.Builder(jsonFactory).setWrapperKeys(Collections.singleton(Constants.IPC_BUNDLE_KEY_SEND_ERROR)).build().parseAndClose(httpResponse.getContent(), httpResponse.getContentCharset(), GoogleJsonError.class);
    }

    static {
        Data.nullOf(ErrorInfo.class);
    }

    public static class ErrorInfo extends GenericJson {

        @Key
        private String domain;

        @Key
        private String location;

        @Key
        private String locationType;

        @Key
        private String message;

        @Key
        private String reason;

        public final String getDomain() {
            return this.domain;
        }

        public final void setDomain(String str) {
            this.domain = str;
        }

        public final String getReason() {
            return this.reason;
        }

        public final void setReason(String str) {
            this.reason = str;
        }

        public final String getMessage() {
            return this.message;
        }

        public final void setMessage(String str) {
            this.message = str;
        }

        public final String getLocation() {
            return this.location;
        }

        public final void setLocation(String str) {
            this.location = str;
        }

        public final String getLocationType() {
            return this.locationType;
        }

        public final void setLocationType(String str) {
            this.locationType = str;
        }

        @Override // com.google.api.client.json.GenericJson, com.google.api.client.util.GenericData
        public ErrorInfo set(String str, Object obj) {
            return (ErrorInfo) super.set(str, obj);
        }

        @Override // com.google.api.client.json.GenericJson, com.google.api.client.util.GenericData, java.util.AbstractMap
        public ErrorInfo clone() {
            return (ErrorInfo) super.clone();
        }
    }

    public final List<ErrorInfo> getErrors() {
        return this.errors;
    }

    public final void setErrors(List<ErrorInfo> list) {
        this.errors = list;
    }

    public final int getCode() {
        return this.code;
    }

    public final void setCode(int i) {
        this.code = i;
    }

    public final String getMessage() {
        return this.message;
    }

    public final void setMessage(String str) {
        this.message = str;
    }

    @Override // com.google.api.client.json.GenericJson, com.google.api.client.util.GenericData
    public GoogleJsonError set(String str, Object obj) {
        return (GoogleJsonError) super.set(str, obj);
    }

    @Override // com.google.api.client.json.GenericJson, com.google.api.client.util.GenericData, java.util.AbstractMap
    public GoogleJsonError clone() {
        return (GoogleJsonError) super.clone();
    }
}
