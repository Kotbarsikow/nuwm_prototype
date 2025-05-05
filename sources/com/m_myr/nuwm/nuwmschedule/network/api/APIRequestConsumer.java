package com.m_myr.nuwm.nuwmschedule.network.api;

import com.m_myr.nuwm.nuwmschedule.network.APIRequestResponse;
import com.m_myr.nuwm.nuwmschedule.network.listener.RequestCallback;
import io.reactivex.functions.Consumer;

/* loaded from: classes2.dex */
public abstract class APIRequestConsumer implements RequestCallback {
    private final Consumer<Throwable> throwableConsumer = new Consumer() { // from class: com.m_myr.nuwm.nuwmschedule.network.api.APIRequestConsumer$$ExternalSyntheticLambda0
        @Override // io.reactivex.functions.Consumer
        public final void accept(Object obj) {
            APIRequestConsumer.this.m160x659740a0((Throwable) obj);
        }
    };
    private final Consumer<APIRequestResponse> dataConsumer = new Consumer() { // from class: com.m_myr.nuwm.nuwmschedule.network.api.APIRequestConsumer$$ExternalSyntheticLambda1
        @Override // io.reactivex.functions.Consumer
        public final void accept(Object obj) {
            APIRequestConsumer.this.m161x6b9b0bff((APIRequestResponse) obj);
        }
    };

    /* renamed from: lambda$new$0$com-m_myr-nuwm-nuwmschedule-network-api-APIRequestConsumer, reason: not valid java name */
    /* synthetic */ void m160x659740a0(Throwable th) throws Exception {
        onError(th);
    }

    /* renamed from: lambda$new$1$com-m_myr-nuwm-nuwmschedule-network-api-APIRequestConsumer, reason: not valid java name */
    /* synthetic */ void m161x6b9b0bff(APIRequestResponse aPIRequestResponse) throws Exception {
        try {
            onSuccess(aPIRequestResponse);
        } catch (Exception e) {
            e.printStackTrace();
            onError(e);
        }
    }

    public final Consumer<Throwable> getThrowableConsumer() {
        return this.throwableConsumer;
    }

    public final Consumer<APIRequestResponse> getDataConsumer() {
        return this.dataConsumer;
    }
}
