package com.m_myr.nuwm.nuwmschedule.domain;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

/* loaded from: classes2.dex */
public abstract class LifecyclePresenter<T extends LifecycleOwner> extends BasePresenter<T> implements LifecycleObserver {
    boolean isFragment;
    private final T lifeView;

    protected void legacyInit() {
    }

    protected abstract void onInit(T view);

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    protected void onStop() {
    }

    public LifecyclePresenter(T view) {
        this(view, false);
    }

    public LifecyclePresenter(T view, boolean isFragment) {
        this.lifeView = view;
        this.isFragment = isFragment;
        legacyInit();
        view.getLifecycle().addObserver(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onStart() {
        if (this.isFragment) {
            return;
        }
        attachView(this.lifeView);
        onInit((LifecycleOwner) getView());
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    protected void onStartFragment() {
        if (!this.isFragment || isViewAttached()) {
            return;
        }
        attachView(this.lifeView);
        onInit(this.lifeView);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    protected void onDestroy() {
        detachView();
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void onInit() {
        onInit((LifecycleOwner) getView());
    }
}
