package com.m_myr.nuwm.nuwmschedule.network.api;

import com.m_myr.nuwm.nuwmschedule.network.ErrorResponse;

/* loaded from: classes2.dex */
public abstract class APIObjectSuccessListener<T> extends APIObjectListener<T> {
    @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
    public void onError(ErrorResponse response) {
    }
}
