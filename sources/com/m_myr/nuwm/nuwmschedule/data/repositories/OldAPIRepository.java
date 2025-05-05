package com.m_myr.nuwm.nuwmschedule.data.repositories;

import com.google.gson.JsonParser;
import com.m_myr.nuwm.nuwmschedule.network.API5RequestBuilder;
import com.m_myr.nuwm.nuwmschedule.network.APIRequestErrorResponse;
import com.m_myr.nuwm.nuwmschedule.network.APIRequestResponse;
import com.m_myr.nuwm.nuwmschedule.network.NetworkClient;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.io.FileNotFoundException;
import java.net.UnknownHostException;
import java.util.concurrent.Callable;
import okhttp3.HttpUrl;

@Deprecated
/* loaded from: classes2.dex */
public class OldAPIRepository implements Repository {
    private API5RequestBuilder api5RequestBuilder;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public OldAPIRepository call(API5RequestBuilder api5RequestBuilder) {
        this.api5RequestBuilder = api5RequestBuilder;
        return this;
    }

    public void async(APIRequestListener repository) {
        this.compositeDisposable.add(newObservable().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(repository.getDataConsumer(), repository.getThrowableConsumer()));
    }

    private Observable<APIRequestResponse> newObservable() {
        return Observable.defer(new Callable() { // from class: com.m_myr.nuwm.nuwmschedule.data.repositories.OldAPIRepository$$ExternalSyntheticLambda0
            public /* synthetic */ OldAPIRepository$$ExternalSyntheticLambda0() {
            }

            @Override // java.util.concurrent.Callable
            public final Object call() {
                return OldAPIRepository.this.m151x4e39f();
            }
        });
    }

    /* renamed from: lambda$newObservable$0$com-m_myr-nuwm-nuwmschedule-data-repositories-OldAPIRepository */
    /* synthetic */ ObservableSource m151x4e39f() throws Exception {
        return Observable.just(launch());
    }

    public APIRequestResponse launch() {
        try {
            HttpUrl build = this.api5RequestBuilder.build();
            return new APIRequestResponse(new JsonParser().parse(this.api5RequestBuilder.isPost() ? NetworkClient.post(build, this.api5RequestBuilder.buildForm(), 16000) : NetworkClient.get(build, 16000)));
        } catch (FileNotFoundException unused) {
            return new APIRequestErrorResponse(90);
        } catch (UnknownHostException unused2) {
            return new APIRequestErrorResponse(-8);
        } catch (Exception unused3) {
            return new APIRequestErrorResponse(-5);
        }
    }

    @Deprecated
    protected void subscribe(Disposable disposable) {
        this.compositeDisposable.add(disposable);
    }

    protected void cancelTask() {
        this.compositeDisposable.clear();
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.repositories.Repository
    public void unsubscribe() {
        this.compositeDisposable.dispose();
        cancelTask();
    }
}
