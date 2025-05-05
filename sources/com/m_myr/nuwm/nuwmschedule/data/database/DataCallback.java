package com.m_myr.nuwm.nuwmschedule.data.database;

import io.reactivex.functions.Consumer;

/* loaded from: classes2.dex */
public abstract class DataCallback<T> {
    private final Consumer<Throwable> throwableConsumer = new Consumer() { // from class: com.m_myr.nuwm.nuwmschedule.data.database.DataCallback$$ExternalSyntheticLambda0
        @Override // io.reactivex.functions.Consumer
        public final void accept(Object obj) {
            DataCallback.this.onError((Throwable) obj);
        }
    };
    private final Consumer<T> dataConsumer = new Consumer() { // from class: com.m_myr.nuwm.nuwmschedule.data.database.DataCallback$$ExternalSyntheticLambda1
        @Override // io.reactivex.functions.Consumer
        public final void accept(Object obj) {
            DataCallback.this.onSuccess(obj);
        }
    };

    protected abstract void onError(Throwable throwable);

    protected abstract void onSuccess(T apiRequestResponse);

    public final Consumer<Throwable> getThrowableConsumer() {
        return this.throwableConsumer;
    }

    public final Consumer<T> getDataConsumer() {
        return this.dataConsumer;
    }
}
