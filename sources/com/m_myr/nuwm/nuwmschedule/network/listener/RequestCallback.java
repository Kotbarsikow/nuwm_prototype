package com.m_myr.nuwm.nuwmschedule.network.listener;

import com.m_myr.nuwm.nuwmschedule.network.APIRequestResponse;

/* loaded from: classes2.dex */
public interface RequestCallback {
    void onError(Throwable throwable);

    void onSuccess(APIRequestResponse apiRequestResponse);
}
