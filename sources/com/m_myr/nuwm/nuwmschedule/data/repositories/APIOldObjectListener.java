package com.m_myr.nuwm.nuwmschedule.data.repositories;

import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.GsonGetter;
import com.m_myr.nuwm.nuwmschedule.network.APIRequestResponse;
import com.m_myr.nuwm.nuwmschedule.network.ErrorResponse;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;
import java.lang.reflect.Type;
import java.util.ArrayList;

@Deprecated
/* loaded from: classes2.dex */
public abstract class APIOldObjectListener<T> extends APIRequestListener implements GsonGetter {

    @Deprecated
    private String fieldName;

    @Deprecated
    private Gson gson;

    @Deprecated
    protected Class<T> tokenDataClass;

    @Deprecated
    private Type type;

    @Deprecated
    protected abstract void onError(ErrorResponse response);

    @Deprecated
    public abstract void onSuccessData(T data);

    @Deprecated
    protected APIOldObjectListener() {
        this.gson = getGson();
    }

    @Deprecated
    protected APIOldObjectListener(String fieldName) {
        this();
        this.fieldName = fieldName;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.GsonGetter
    @Deprecated
    public Gson getGson() {
        return Utils.getGson();
    }

    @Deprecated
    public APIOldObjectListener(Class array, Class arrayType, String fieldName) {
        this(fieldName);
        this.type = TypeToken.getParameterized(array, arrayType).getType();
    }

    @Deprecated
    public APIOldObjectListener(Class array, Class arrayType) {
        this(array, arrayType, null);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @Deprecated
    public APIOldObjectListener(Class<T> tokenDataClass) {
        this(tokenDataClass, (String) null);
    }

    @Deprecated
    public APIOldObjectListener(Class<T> tokenDataClass, String fieldName) {
        this();
        this.tokenDataClass = tokenDataClass;
        this.fieldName = fieldName;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.m_myr.nuwm.nuwmschedule.data.repositories.APIRequestListener
    @Deprecated
    /* renamed from: onError */
    public final void m146xbd98ac6e(Throwable throwable) {
        onError(new ErrorResponse(new Exception(throwable)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.m_myr.nuwm.nuwmschedule.data.repositories.APIRequestListener
    @Deprecated
    protected void onSuccess(APIRequestResponse response) {
        JsonElement response2;
        Object fromJson;
        Log.e("onSuccessData", new GsonBuilder().setPrettyPrinting().create().toJson(response));
        if (response.isSuccessful()) {
            if (this.fieldName != null) {
                response2 = response.getResponse().getAsJsonObject().get(this.fieldName);
            } else {
                response2 = response.getResponse();
            }
            Type type = this.type;
            if (type != null) {
                fromJson = this.gson.fromJson(response2, type);
            } else {
                fromJson = this.gson.fromJson(response2, (Class<Object>) this.tokenDataClass);
            }
            if (fromJson == null) {
                m146xbd98ac6e(new NullPointerException("Parsed object is null"));
            }
            onSuccessData(fromJson);
            return;
        }
        onError(response.getError());
    }

    @Deprecated
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    @Deprecated
    public void setClass(Class<T> tClass) {
        if (tClass == null) {
            return;
        }
        this.tokenDataClass = tClass;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated
    protected <V> ArrayList<V> of(T data, Class<V> vClass) {
        return (ArrayList) data;
    }
}
