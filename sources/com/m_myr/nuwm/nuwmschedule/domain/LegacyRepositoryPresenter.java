package com.m_myr.nuwm.nuwmschedule.domain;

import androidx.lifecycle.LifecycleOwner;
import com.m_myr.nuwm.nuwmschedule.data.repositories.OldAPIRepository;

@Deprecated
/* loaded from: classes2.dex */
public abstract class LegacyRepositoryPresenter<T extends LifecycleOwner> extends LifecyclePresenter<T> {
    private OldAPIRepository apiRepository;

    public OldAPIRepository getRepository() {
        return this.apiRepository;
    }

    public LegacyRepositoryPresenter(T view) {
        this(view, false);
    }

    public LegacyRepositoryPresenter(T view, boolean isFragment) {
        super(view, isFragment);
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.LifecyclePresenter
    protected void onDestroy() {
        super.onDestroy();
        this.apiRepository.unsubscribe();
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.LifecyclePresenter
    protected void legacyInit() {
        super.legacyInit();
        this.apiRepository = new OldAPIRepository();
    }
}
