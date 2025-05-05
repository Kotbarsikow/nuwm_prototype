package com.m_myr.nuwm.nuwmschedule.network.api;

import com.m_myr.nuwm.nuwmschedule.network.APIRequestResponse;

/* loaded from: classes2.dex */
public abstract class APIOnCompleteListener extends APIRequestConsumer {
    public abstract void onComplete(boolean successful);

    @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestCallback
    public final void onSuccess(APIRequestResponse apiRequestResponse) {
        onComplete(apiRequestResponse.isSuccessful());
    }

    @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestCallback
    public final void onError(Throwable throwable) {
        onComplete(false);
    }
}
