package com.m_myr.nuwm.nuwmschedule.network.api;

import com.google.gson.JsonParser;
import com.m_myr.nuwm.nuwmschedule.network.APIRequestErrorResponse;
import com.m_myr.nuwm.nuwmschedule.network.APIRequestResponse;
import com.m_myr.nuwm.nuwmschedule.network.NetworkClient;
import com.m_myr.nuwm.nuwmschedule.network.api.APIRepository;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.io.FileNotFoundException;
import java.net.UnknownHostException;
import java.util.concurrent.Callable;
import okhttp3.HttpUrl;

/* loaded from: classes2.dex */
public class APIRepositoryCallable<T> implements APIRepository.APIRepositoryExecutor<T> {
    private final APIRepository apiRepository;
    private final ApiRequestBuilder<T> requestBuilder;

    public APIRepositoryCallable(APIRepository apiRepository, ApiRequestBuilder<T> requestBuilder) {
        this.apiRepository = apiRepository;
        this.requestBuilder = requestBuilder;
    }

    private void async(APIRequestConsumer consumer) {
        this.apiRepository.addDisposable(newObservable().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(consumer.getDataConsumer(), consumer.getThrowableConsumer()));
    }

    @Override // com.m_myr.nuwm.nuwmschedule.network.api.APIRepository.APIRepositoryExecutor
    public void sync(APIRequestConsumer consumer) {
        this.apiRepository.addDisposable(newObservable().subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(consumer.getDataConsumer(), consumer.getThrowableConsumer()));
    }

    @Override // com.m_myr.nuwm.nuwmschedule.network.api.APIRepository.APIRepositoryExecutor
    public void async(APIObjectListener<T> consumer) {
        consumer.setType(this.requestBuilder.type, this.requestBuilder.field);
        async((APIRequestConsumer) consumer);
    }

    @Override // com.m_myr.nuwm.nuwmschedule.network.api.APIRepository.APIRepositoryExecutor
    public <V> APIRepository.APIRepositoryPrepareCallable<T, V> prepareInThread(APIObjectPreparator<T, V> consumer) {
        consumer.setType(this.requestBuilder.type, this.requestBuilder.field);
        return new APIRepository.APIRepositoryPrepareCallable<>(consumer, this);
    }

    private Observable<APIRequestResponse> newObservable() {
        return Observable.defer(new Callable() { // from class: com.m_myr.nuwm.nuwmschedule.network.api.APIRepositoryCallable$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return APIRepositoryCallable.this.m159x53f37cea();
            }
        });
    }

    /* renamed from: lambda$newObservable$0$com-m_myr-nuwm-nuwmschedule-network-api-APIRepositoryCallable, reason: not valid java name */
    /* synthetic */ ObservableSource m159x53f37cea() throws Exception {
        return Observable.just(load());
    }

    @Override // com.m_myr.nuwm.nuwmschedule.network.api.APIRepository.APIRepositoryExecutor
    public APIRepository getApiRepository() {
        return this.apiRepository;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.network.api.APIRepository.APIRepositoryExecutor
    public APIRequestResponse load() {
        try {
            HttpUrl build = this.requestBuilder.build();
            return new APIRequestResponse(new JsonParser().parse(this.requestBuilder.isPost() ? NetworkClient.post(build, this.requestBuilder.buildForm(), this.requestBuilder.getTimeout()) : NetworkClient.get(build, this.requestBuilder.getTimeout())));
        } catch (FileNotFoundException unused) {
            return new APIRequestErrorResponse(90);
        } catch (UnknownHostException unused2) {
            return new APIRequestErrorResponse(-8);
        } catch (Exception unused3) {
            return new APIRequestErrorResponse(-5);
        }
    }
}
