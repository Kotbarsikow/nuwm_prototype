package com.m_myr.nuwm.nuwmschedule.domain;

import androidx.lifecycle.LifecycleOwner;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.RepositoryProvider;
import com.m_myr.nuwm.nuwmschedule.network.api.APIRepository;

/* loaded from: classes2.dex */
public abstract class RepositoryPresenter<T extends LifecycleOwner> extends LifecyclePresenter<T> implements RepositoryProvider {
    private APIRepository apiRepository;

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.RepositoryProvider
    public APIRepository getRepository() {
        return this.apiRepository;
    }

    public RepositoryPresenter(T view) {
        this(view, false);
    }

    public RepositoryPresenter(T view, boolean isFragment) {
        super(view, isFragment);
        this.apiRepository = new APIRepository();
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.LifecyclePresenter
    protected void onDestroy() {
        super.onDestroy();
        this.apiRepository.unsubscribe();
    }
}
