package com.m_myr.nuwm.nuwmschedule.network;

import com.m_myr.nuwm.nuwmschedule.data.repositories.APIOldObjectListener;
import com.m_myr.nuwm.nuwmschedule.network.models.EmptyResult;

@Deprecated
/* loaded from: classes2.dex */
public abstract class APIObjectOkListener extends APIOldObjectListener<EmptyResult> {
    @Override // com.m_myr.nuwm.nuwmschedule.data.repositories.APIOldObjectListener
    protected abstract void onError(ErrorResponse response);

    public abstract void onSuccess();

    public APIObjectOkListener() {
        super(EmptyResult.class);
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.repositories.APIOldObjectListener
    public void onSuccessData(EmptyResult data) {
        onSuccess();
    }
}
