package com.m_myr.nuwm.nuwmschedule.network.listener;

import com.m_myr.nuwm.nuwmschedule.network.ErrorResponse;

/* loaded from: classes2.dex */
public interface RequestObjectCallback<T> {
    void onError(ErrorResponse response);

    void onSuccessData(T data);
}
