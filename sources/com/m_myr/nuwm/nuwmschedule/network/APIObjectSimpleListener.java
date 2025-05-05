package com.m_myr.nuwm.nuwmschedule.network;

import com.m_myr.nuwm.nuwmschedule.data.repositories.APIOldObjectListener;

@Deprecated
/* loaded from: classes2.dex */
public abstract class APIObjectSimpleListener<T> extends APIOldObjectListener<T> {
    @Override // com.m_myr.nuwm.nuwmschedule.data.repositories.APIOldObjectListener
    @Deprecated
    protected void onError(ErrorResponse response) {
    }

    @Deprecated
    public APIObjectSimpleListener() {
    }

    @Deprecated
    public APIObjectSimpleListener(Class<T> tokenDataClass) {
        super(tokenDataClass);
    }

    @Deprecated
    public APIObjectSimpleListener(Class<T> tokenDataClass, String fieldName) {
        super(tokenDataClass, fieldName);
    }
}
