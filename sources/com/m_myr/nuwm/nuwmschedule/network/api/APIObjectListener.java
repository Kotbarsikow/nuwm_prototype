package com.m_myr.nuwm.nuwmschedule.network.api;

import com.google.gson.Gson;
import com.m_myr.nuwm.nuwmschedule.domain.ApiException;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.GsonGetter;
import com.m_myr.nuwm.nuwmschedule.network.APIRequestResponse;
import com.m_myr.nuwm.nuwmschedule.network.ErrorResponse;
import com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;
import java.lang.reflect.Type;

/* loaded from: classes2.dex */
public abstract class APIObjectListener<T> extends APIRequestConsumer implements GsonGetter, RequestObjectCallback<T> {
    private String filed;
    private Gson gson = getGson();
    private Type type;

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.GsonGetter
    public Gson getGson() {
        return Utils.getGson();
    }

    @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestCallback
    public final void onSuccess(APIRequestResponse response) {
        if (response.isSuccessful()) {
            T parseData = parseData(response);
            if (parseData == null) {
                onError(new NullPointerException("Parsed object is null"));
            }
            onSuccessData(parseData);
            return;
        }
        onError(response.getError());
    }

    T parseData(APIRequestResponse aPIRequestResponse) {
        return (T) this.gson.fromJson(this.filed == null ? aPIRequestResponse.getResponse() : aPIRequestResponse.getResponse().getAsJsonObject().get(this.filed), this.type);
    }

    @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestCallback
    public final void onError(Throwable throwable) {
        if (throwable instanceof ApiException) {
            onError(((ApiException) throwable).getResponse());
        } else {
            onError(new ErrorResponse(throwable));
        }
        throwable.printStackTrace();
    }

    public final void setType(Type type, String filed) {
        this.type = type;
        this.filed = filed;
    }
}
