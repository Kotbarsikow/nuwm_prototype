package com.m_myr.nuwm.nuwmschedule.network.api;

import com.m_myr.nuwm.nuwmschedule.domain.ApiException;
import com.m_myr.nuwm.nuwmschedule.network.APIRequestResponse;
import com.m_myr.nuwm.nuwmschedule.network.ErrorResponse;
import com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback;
import io.reactivex.functions.Consumer;

/* loaded from: classes2.dex */
public abstract class APIObjectPreparator<T, V> extends APIObjectListener<T> {
    private final Consumer<PreparedObject<V>> objectConsumer = new Consumer() { // from class: com.m_myr.nuwm.nuwmschedule.network.api.APIObjectPreparator$$ExternalSyntheticLambda0
        @Override // io.reactivex.functions.Consumer
        public final void accept(Object obj) {
            APIObjectPreparator.this.m156x259fe878((PreparedObject) obj);
        }
    };
    RequestObjectCallback<PreparedObject<V>> requestObjectCallback;

    @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
    public final void onSuccessData(T data) {
    }

    public abstract V onSuccessDataInThread(T data) throws Exception;

    @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
    public final void onError(ErrorResponse response) {
        this.requestObjectCallback.onError(response);
    }

    /* renamed from: lambda$new$0$com-m_myr-nuwm-nuwmschedule-network-api-APIObjectPreparator, reason: not valid java name */
    /* synthetic */ void m156x259fe878(PreparedObject preparedObject) throws Exception {
        try {
            this.requestObjectCallback.onSuccessData(preparedObject);
        } catch (Exception e) {
            e.printStackTrace();
            onError(e);
        }
    }

    public Consumer<PreparedObject<V>> getObjectConsumer() {
        return this.objectConsumer;
    }

    public PreparedObject<V> prepare(APIRequestResponse response) throws Exception {
        if (response.isSuccessful()) {
            T parseData = parseData(response);
            return new PreparedObject<>(onSuccessDataInThread(parseData), parseData);
        }
        throw new ApiException(response.getError());
    }

    public void setDataListener(RequestObjectCallback<PreparedObject<V>> requestObjectCallback) {
        this.requestObjectCallback = requestObjectCallback;
    }
}
