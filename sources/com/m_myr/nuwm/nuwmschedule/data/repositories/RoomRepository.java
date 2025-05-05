package com.m_myr.nuwm.nuwmschedule.data.repositories;

import com.m_myr.nuwm.nuwmschedule.data.dao.NotesDao;
import com.m_myr.nuwm.nuwmschedule.data.database.DataCallback;
import com.m_myr.nuwm.nuwmschedule.domain.App;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.Callable;

/* loaded from: classes2.dex */
public class RoomRepository implements Repository {
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public NotesDao notesDao() {
        return App.getInstance().getDatabase().notesDao();
    }

    protected void cancelTask() {
        this.compositeDisposable.clear();
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.repositories.Repository
    public void unsubscribe() {
        this.compositeDisposable.dispose();
        cancelTask();
    }

    public <T> void call(Callable<T> o, DataCallback<T> callback) {
        this.compositeDisposable.add(newObservable(o).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(callback.getDataConsumer(), callback.getThrowableConsumer()));
    }

    public void call(Callable<Void> o) {
        this.compositeDisposable.add(newObservable(o).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe());
    }

    public void call(Action o) {
        this.compositeDisposable.add(Completable.fromAction(o).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe());
    }

    private <T> Observable<T> newObservable(final Callable<T> o) {
        return Observable.defer(new Callable() { // from class: com.m_myr.nuwm.nuwmschedule.data.repositories.RoomRepository$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                ObservableSource just;
                just = Observable.just(o.call());
                return just;
            }
        });
    }
}
