package com.m_myr.nuwm.nuwmschedule.network;

import com.m_myr.nuwm.nuwmschedule.data.repositories.AppDataManager;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;
import java.util.ArrayList;
import okhttp3.HttpUrl;

@Deprecated
/* loaded from: classes2.dex */
public class API5RequestBuilder {
    public static final String auth = "auth";
    public static final String profile = "profile";
    private HttpUrl.Builder postBuilder;
    protected boolean sendPost;
    private HttpUrl.Builder urlBuilder;

    private API5RequestBuilder() {
    }

    private API5RequestBuilder(String method, boolean post) {
        this.sendPost = post;
        HttpUrl.Builder newBuilder = HttpUrl.parse(getRoot()).newBuilder();
        this.urlBuilder = newBuilder;
        newBuilder.addPathSegment(method);
        if (this.sendPost) {
            this.postBuilder = HttpUrl.parse(getRoot()).newBuilder();
        }
    }

    protected String getRoot() {
        return "https://app.nuwm.edu.ua/api/v6/";
    }

    public static API5RequestBuilder get(String method) {
        return new API5RequestBuilder(method, false);
    }

    public static API5RequestBuilder post(String method) {
        return new API5RequestBuilder(method, true);
    }

    public API5RequestBuilder add(String q, boolean v) {
        this.urlBuilder.addQueryParameter(q, Boolean.toString(v));
        return this;
    }

    public API5RequestBuilder addForm(String q, Object v) {
        if (v == null) {
            return this;
        }
        if (!this.sendPost) {
            throw new RuntimeException("Invalid args");
        }
        this.postBuilder.addQueryParameter(q, Utils.getGson().toJson(v));
        return this;
    }

    public API5RequestBuilder add(String q, String v) {
        if (v == null) {
            return this;
        }
        this.urlBuilder.addQueryParameter(q, v);
        return this;
    }

    public API5RequestBuilder add(String q, int v) {
        this.urlBuilder.addQueryParameter(q, Integer.toString(v));
        return this;
    }

    public API5RequestBuilder add(String q, double v) {
        this.urlBuilder.addQueryParameter(q, String.valueOf(v));
        return this;
    }

    public API5RequestBuilder addToken() {
        return add("token", AppDataManager.getInstance().getToken());
    }

    public boolean isPost() {
        return this.sendPost;
    }

    public HttpUrl build() {
        addOS();
        return this.urlBuilder.build();
    }

    public API5RequestBuilder useCompact() {
        return add("compact", true);
    }

    public API5RequestBuilder getExtend() {
        return add("extended", true);
    }

    public HttpUrl buildForm() {
        return this.postBuilder.build();
    }

    public API5RequestBuilder addAppVersion() {
        return add("v", String.valueOf(112));
    }

    public API5RequestBuilder addOS() {
        return add("app", "android");
    }

    public API5RequestBuilder add(String q, ArrayList<String> v) {
        return add(q, Utils.StringUtils.joinString((CharSequence) ",", v));
    }
}
