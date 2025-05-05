package com.m_myr.nuwm.nuwmschedule.network.api;

import com.google.gson.reflect.TypeToken;
import com.m_myr.nuwm.nuwmschedule.data.repositories.AppDataManager;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;
import java.lang.reflect.Type;
import java.util.ArrayList;
import okhttp3.HttpUrl;

/* loaded from: classes2.dex */
public class ApiRequestBuilder<T> {
    final String field;
    private HttpUrl.Builder postBuilder;
    protected boolean sendPost;
    private int timeout = 16000;
    final Type type;
    private HttpUrl.Builder urlBuilder;

    public static ApiRequestBuilder<Void> getVoid(String s) {
        return new ApiRequestBuilder<>(s, Void.class, null, false);
    }

    public static ApiRequestBuilder<Void> buildPatch(String path) {
        return new ApiRequestBuilder<>(path, Void.class, null, false);
    }

    public int getTimeout() {
        return this.timeout;
    }

    private <V> ApiRequestBuilder(String method, Class<ArrayList> arrayListClass, Class<V> tClass, String field, boolean sendPost) {
        this.field = field;
        this.sendPost = sendPost;
        this.type = TypeToken.getParameterized(arrayListClass, tClass).getType();
        HttpUrl.Builder newBuilder = HttpUrl.parse(getRoot()).newBuilder();
        this.urlBuilder = newBuilder;
        newBuilder.addPathSegment(method);
        this.urlBuilder.addQueryParameter("v", String.valueOf(112));
        this.urlBuilder.addQueryParameter("app", "android");
        if (sendPost) {
            this.postBuilder = HttpUrl.parse(getRoot()).newBuilder();
        }
    }

    private <V> ApiRequestBuilder(String method, Class<V> tClass, String field, boolean sendPost) {
        this.type = TypeToken.getParameterized(tClass, new Type[0]).getType();
        this.field = field;
        this.sendPost = sendPost;
        HttpUrl.Builder newBuilder = HttpUrl.parse(getRoot()).newBuilder();
        this.urlBuilder = newBuilder;
        newBuilder.addPathSegment(method);
        this.urlBuilder.addQueryParameter("v", String.valueOf(112));
        this.urlBuilder.addQueryParameter("app", "android");
        if (sendPost) {
            this.postBuilder = HttpUrl.parse(getRoot()).newBuilder();
        }
    }

    public static String getRoot() {
        return "https://app.nuwm.edu.ua/api/v6/";
    }

    public ApiRequestBuilder<T> addToken() {
        return add("token", AppDataManager.getInstance().getToken());
    }

    private void addQueryParameter(String q, String v) {
        if (v == null) {
            return;
        }
        if (isPost()) {
            this.postBuilder.addQueryParameter(q, v);
        } else {
            this.urlBuilder.addQueryParameter(q, v);
        }
    }

    public ApiRequestBuilder<T> addPatch(String patch) {
        this.urlBuilder.addPathSegment(patch);
        return this;
    }

    public ApiRequestBuilder<T> add(String q, String v) {
        if (v == null) {
            return this;
        }
        addQueryParameter(q, v);
        return this;
    }

    public ApiRequestBuilder<T> add(String q, ArrayList<String> v) {
        if (v == null) {
            return this;
        }
        addQueryParameter(q, Utils.StringUtils.joinString((CharSequence) ",", v));
        return this;
    }

    public ApiRequestBuilder<T> add(String q, boolean v) {
        addQueryParameter(q, Boolean.toString(v));
        return this;
    }

    public ApiRequestBuilder<T> add(String q, int v) {
        addQueryParameter(q, Integer.toString(v));
        return this;
    }

    public ApiRequestBuilder<T> add(String q, double v) {
        addQueryParameter(q, String.valueOf(v));
        return this;
    }

    public HttpUrl build() {
        return this.urlBuilder.build();
    }

    public boolean isPost() {
        return this.sendPost;
    }

    public HttpUrl buildForm() {
        return this.postBuilder.build();
    }

    public static <V> ApiRequestBuilder<ArrayList<V>> getArray(String method, Class<V> tClass) {
        return new ApiRequestBuilder<>(method, ArrayList.class, tClass, null, false);
    }

    public static <V> ApiRequestBuilder<ArrayList<V>> getArray(String method, Class<V> tClass, String filed) {
        return new ApiRequestBuilder<>(method, ArrayList.class, tClass, filed, false);
    }

    public static <V> ApiRequestBuilder<V> getObject(String method, Class<V> tClass) {
        return new ApiRequestBuilder<>(method, tClass, null, false);
    }

    public static <V> ApiRequestBuilder<V> postObject(String method, Class<V> tClass) {
        return new ApiRequestBuilder<>(method, tClass, null, true);
    }

    public static <V> ApiRequestBuilder<V> postObject(String method, Class<V> tClass, String filed) {
        return new ApiRequestBuilder<>(method, tClass, filed, true);
    }

    public static <V> ApiRequestBuilder<V> getObject(String method, Class<V> tClass, String filed) {
        return new ApiRequestBuilder<>(method, tClass, filed, false);
    }

    public String getFieldName() {
        return this.field;
    }

    public Type getType() {
        return this.type;
    }

    public ApiRequestBuilder<T> setTimeout(int timeout) {
        this.timeout = timeout;
        return this;
    }

    public ApiRequestBuilder<T> isExtended(boolean b) {
        add("extend", b);
        return this;
    }
}
