package com.google.api.services.calendar;

import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClientRequest;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.util.Key;

/* loaded from: classes2.dex */
public abstract class CalendarRequest<T> extends AbstractGoogleJsonClientRequest<T> {

    @Key
    private String alt;

    @Key
    private String fields;

    @Key
    private String key;

    @Key("oauth_token")
    private String oauthToken;

    @Key
    private Boolean prettyPrint;

    @Key
    private String quotaUser;

    @Key
    private String userIp;

    public CalendarRequest(Calendar calendar, String str, String str2, Object obj, Class<T> cls) {
        super(calendar, str, str2, obj, cls);
    }

    public String getAlt() {
        return this.alt;
    }

    /* renamed from: setAlt */
    public CalendarRequest<T> setAlt2(String str) {
        this.alt = str;
        return this;
    }

    public String getFields() {
        return this.fields;
    }

    /* renamed from: setFields */
    public CalendarRequest<T> setFields2(String str) {
        this.fields = str;
        return this;
    }

    public String getKey() {
        return this.key;
    }

    /* renamed from: setKey */
    public CalendarRequest<T> setKey2(String str) {
        this.key = str;
        return this;
    }

    public String getOauthToken() {
        return this.oauthToken;
    }

    /* renamed from: setOauthToken */
    public CalendarRequest<T> setOauthToken2(String str) {
        this.oauthToken = str;
        return this;
    }

    public Boolean getPrettyPrint() {
        return this.prettyPrint;
    }

    /* renamed from: setPrettyPrint */
    public CalendarRequest<T> setPrettyPrint2(Boolean bool) {
        this.prettyPrint = bool;
        return this;
    }

    public String getQuotaUser() {
        return this.quotaUser;
    }

    /* renamed from: setQuotaUser */
    public CalendarRequest<T> setQuotaUser2(String str) {
        this.quotaUser = str;
        return this;
    }

    public String getUserIp() {
        return this.userIp;
    }

    /* renamed from: setUserIp */
    public CalendarRequest<T> setUserIp2(String str) {
        this.userIp = str;
        return this;
    }

    @Override // com.google.api.client.googleapis.services.json.AbstractGoogleJsonClientRequest, com.google.api.client.googleapis.services.AbstractGoogleClientRequest
    public final Calendar getAbstractGoogleClient() {
        return (Calendar) super.getAbstractGoogleClient();
    }

    @Override // com.google.api.client.googleapis.services.json.AbstractGoogleJsonClientRequest, com.google.api.client.googleapis.services.AbstractGoogleClientRequest
    public CalendarRequest<T> setDisableGZipContent(boolean z) {
        return (CalendarRequest) super.setDisableGZipContent(z);
    }

    @Override // com.google.api.client.googleapis.services.json.AbstractGoogleJsonClientRequest, com.google.api.client.googleapis.services.AbstractGoogleClientRequest
    public CalendarRequest<T> setRequestHeaders(HttpHeaders httpHeaders) {
        return (CalendarRequest) super.setRequestHeaders(httpHeaders);
    }

    @Override // com.google.api.client.googleapis.services.json.AbstractGoogleJsonClientRequest, com.google.api.client.googleapis.services.AbstractGoogleClientRequest, com.google.api.client.util.GenericData
    public CalendarRequest<T> set(String str, Object obj) {
        return (CalendarRequest) super.set(str, obj);
    }
}
