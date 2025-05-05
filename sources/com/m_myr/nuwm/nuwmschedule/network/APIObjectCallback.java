package com.m_myr.nuwm.nuwmschedule.network;

import com.m_myr.nuwm.nuwmschedule.data.repositories.APIOldObjectListener;

@Deprecated
/* loaded from: classes2.dex */
public class APIObjectCallback<T> extends APIOldObjectListener<T> {
    ObjectCallback<T> callback;

    public interface ObjectCallback<T> {
        void onError(ErrorResponse response);

        void onSuccessData(T data);
    }

    public APIObjectCallback(ObjectCallback<T> callback) {
        this.callback = callback;
    }

    public APIObjectCallback(ObjectCallback<T> callback, String fieldName) {
        super(fieldName);
        this.callback = callback;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.repositories.APIOldObjectListener
    protected void onError(ErrorResponse response) {
        this.callback.onError(response);
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.repositories.APIOldObjectListener
    public void onSuccessData(T data) {
        this.callback.onSuccessData(data);
    }
}
