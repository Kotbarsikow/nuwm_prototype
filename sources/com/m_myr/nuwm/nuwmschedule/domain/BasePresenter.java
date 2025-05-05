package com.m_myr.nuwm.nuwmschedule.domain;

/* loaded from: classes2.dex */
public abstract class BasePresenter<V> {
    protected V view;

    public V requireView() {
        V v = this.view;
        if (v != null) {
            return v;
        }
        throw new RuntimeException("View not attached to presenter!");
    }

    public V getView() {
        return this.view;
    }

    public void attachView(V view) {
        this.view = view;
    }

    public void detachView() {
        this.view = null;
    }

    public boolean isViewAttached() {
        return this.view != null;
    }
}
