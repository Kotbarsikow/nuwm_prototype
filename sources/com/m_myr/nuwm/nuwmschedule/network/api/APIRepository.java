package com.m_myr.nuwm.nuwmschedule.network.api;

import com.google.firebase.messaging.Constants;
import com.google.gson.JsonParser;
import com.m_myr.nuwm.nuwmschedule.data.repositories.Repository;
import com.m_myr.nuwm.nuwmschedule.domain.App;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.StorageModel;
import com.m_myr.nuwm.nuwmschedule.network.APIFileResponse;
import com.m_myr.nuwm.nuwmschedule.network.APIRequestErrorResponse;
import com.m_myr.nuwm.nuwmschedule.network.APIRequestOKResponse;
import com.m_myr.nuwm.nuwmschedule.network.APIRequestResponse;
import com.m_myr.nuwm.nuwmschedule.network.StorageModelName;
import com.m_myr.nuwm.nuwmschedule.network.api.APIRepository;
import com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;

/* loaded from: classes2.dex */
public class APIRepository implements Repository {
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public interface APIRepositoryExecutor<T> {
        void async(APIObjectListener<T> consumer);

        APIRepository getApiRepository();

        APIRequestResponse load();

        <V> APIRepositoryPrepareCallable<T, V> prepareInThread(APIObjectPreparator<T, V> consumer);

        void sync(APIRequestConsumer consumer);
    }

    public <T> APIRepositoryExecutor<T> call(ApiRequestBuilder<T> requestBuilder) {
        return new APIRepositoryCallable(this, requestBuilder);
    }

    public <T> APIRepositoryCallableKtx<T> callKts(ApiRequestBuilder<T> requestBuilder) {
        return new APIRepositoryCallableKtx<>(this, requestBuilder);
    }

    public void cancelTask() {
        this.compositeDisposable.clear();
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.repositories.Repository
    public void unsubscribe() {
        this.compositeDisposable.dispose();
        cancelTask();
    }

    void addDisposable(Disposable disposable) {
        this.compositeDisposable.add(disposable);
    }

    public <T extends StorageModel> FileRepositoryLoader<T> loadStorage(@StorageModelName Class<T> requestModel) {
        return new FileRepositoryLoader<>(this, requestModel);
    }

    public static class FileRepositoryLoader<T extends StorageModel> implements APIRepositoryExecutor<T> {
        private final APIRepository apiRepository;
        private final Class<T> requestModel;

        public FileRepositoryLoader(APIRepository apiRepository, Class<T> requestModel) {
            this.apiRepository = apiRepository;
            this.requestModel = requestModel;
        }

        private void async(APIRequestConsumer consumer) {
            this.apiRepository.addDisposable(newObservable().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(consumer.getDataConsumer(), consumer.getThrowableConsumer()));
        }

        @Override // com.m_myr.nuwm.nuwmschedule.network.api.APIRepository.APIRepositoryExecutor
        public void async(APIObjectListener<T> consumer) {
            consumer.setType(this.requestModel, null);
            async((APIRequestConsumer) consumer);
        }

        @Override // com.m_myr.nuwm.nuwmschedule.network.api.APIRepository.APIRepositoryExecutor
        public void sync(APIRequestConsumer consumer) {
            this.apiRepository.addDisposable(newObservable().subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(consumer.getDataConsumer(), consumer.getThrowableConsumer()));
        }

        @Override // com.m_myr.nuwm.nuwmschedule.network.api.APIRepository.APIRepositoryExecutor
        public <V> APIRepositoryPrepareCallable<T, V> prepareInThread(APIObjectPreparator<T, V> consumer) {
            consumer.setType(this.requestModel.getComponentType(), Constants.ScionAnalytics.MessageType.DATA_MESSAGE);
            return new APIRepositoryPrepareCallable<>(consumer, this);
        }

        @Override // com.m_myr.nuwm.nuwmschedule.network.api.APIRepository.APIRepositoryExecutor
        public APIRepository getApiRepository() {
            return this.apiRepository;
        }

        private Observable<APIRequestResponse> newObservable() {
            return Observable.defer(new Callable() { // from class: com.m_myr.nuwm.nuwmschedule.network.api.APIRepository$FileRepositoryLoader$$ExternalSyntheticLambda0
                public /* synthetic */ APIRepository$FileRepositoryLoader$$ExternalSyntheticLambda0() {
                }

                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return APIRepository.FileRepositoryLoader.this.m158x943b190b();
                }
            });
        }

        /* renamed from: lambda$newObservable$0$com-m_myr-nuwm-nuwmschedule-network-api-APIRepository$FileRepositoryLoader */
        /* synthetic */ ObservableSource m158x943b190b() throws Exception {
            return Observable.just(load());
        }

        private String getFileName() {
            return ((StorageModelName) this.requestModel.getAnnotation(StorageModelName.class)).value() + "_storage.json";
        }

        public APIRequestResponse save(T object) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(new File(App.getInstance().getCacheDir(), App.getInstance().getCurrentFileName(getFileName())));
                synchronized (object) {
                    fileOutputStream.write(Utils.getGson().toJson(object).getBytes());
                }
                fileOutputStream.close();
                return new APIRequestOKResponse();
            } catch (Exception e) {
                e.printStackTrace();
                return new APIRequestErrorResponse(e);
            }
        }

        @Override // com.m_myr.nuwm.nuwmschedule.network.api.APIRepository.APIRepositoryExecutor
        public APIRequestResponse load() {
            try {
                return new APIFileResponse(new JsonParser().parse(new BufferedReader(new InputStreamReader(new FileInputStream(new File(App.getInstance().getCacheDir(), App.getInstance().getCurrentFileName(getFileName())))))));
            } catch (FileNotFoundException unused) {
                return new APIRequestErrorResponse(33);
            } catch (Exception unused2) {
                return new APIRequestErrorResponse(-5);
            }
        }
    }

    public static class APIRepositoryPrepareCallable<T, V> {
        private APIObjectPreparator<T, V> preparator;
        private APIRepositoryExecutor<T> repositoryCallable;

        public APIRepositoryPrepareCallable(APIObjectPreparator<T, V> preparator, APIRepositoryExecutor<T> repositoryCallable) {
            this.preparator = preparator;
            this.repositoryCallable = repositoryCallable;
        }

        public void async(RequestObjectCallback<PreparedObject<V>> requestObjectCallback) {
            this.preparator.setDataListener(requestObjectCallback);
            this.repositoryCallable.getApiRepository().addDisposable(newObservable().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(this.preparator.getObjectConsumer(), this.preparator.getThrowableConsumer()));
        }

        private Observable<PreparedObject<V>> newObservable() {
            return Observable.defer(new Callable() { // from class: com.m_myr.nuwm.nuwmschedule.network.api.APIRepository$APIRepositoryPrepareCallable$$ExternalSyntheticLambda0
                public /* synthetic */ APIRepository$APIRepositoryPrepareCallable$$ExternalSyntheticLambda0() {
                }

                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return APIRepository.APIRepositoryPrepareCallable.this.m157xda30d2ad();
                }
            });
        }

        /* renamed from: lambda$newObservable$0$com-m_myr-nuwm-nuwmschedule-network-api-APIRepository$APIRepositoryPrepareCallable */
        /* synthetic */ ObservableSource m157xda30d2ad() throws Exception {
            return Observable.just(this.preparator.prepare(this.repositoryCallable.load()));
        }
    }
}
