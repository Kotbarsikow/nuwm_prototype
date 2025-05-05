package com.m_myr.nuwm.nuwmschedule.data.repositories;

import com.m_myr.nuwm.nuwmschedule.network.APIRequestResponse;
import io.reactivex.functions.Consumer;

/* loaded from: classes2.dex */
public abstract class APIRequestListener {
    private final Consumer<Throwable> throwableConsumer = new Consumer() { // from class: com.m_myr.nuwm.nuwmschedule.data.repositories.APIRequestListener$$ExternalSyntheticLambda0
        @Override // io.reactivex.functions.Consumer
        public final void accept(Object obj) {
            APIRequestListener.this.m146xbd98ac6e((Throwable) obj);
        }
    };
    private final Consumer<APIRequestResponse> dataConsumer = new Consumer() { // from class: com.m_myr.nuwm.nuwmschedule.data.repositories.APIRequestListener$$ExternalSyntheticLambda1
        @Override // io.reactivex.functions.Consumer
        public final void accept(Object obj) {
            APIRequestListener.this.m147x27c8348d((APIRequestResponse) obj);
        }
    };

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: onError, reason: merged with bridge method [inline-methods] */
    public abstract void m146xbd98ac6e(Throwable throwable);

    protected abstract void onSuccess(APIRequestResponse apiRequestResponse);

    /* renamed from: lambda$new$1$com-m_myr-nuwm-nuwmschedule-data-repositories-APIRequestListener, reason: not valid java name */
    /* synthetic */ void m147x27c8348d(APIRequestResponse aPIRequestResponse) throws Exception {
        try {
            onSuccess(aPIRequestResponse);
        } catch (Exception e) {
            e.printStackTrace();
            m146xbd98ac6e(e);
        }
    }

    public final Consumer<Throwable> getThrowableConsumer() {
        return this.throwableConsumer;
    }

    public final Consumer<APIRequestResponse> getDataConsumer() {
        return this.dataConsumer;
    }
}
