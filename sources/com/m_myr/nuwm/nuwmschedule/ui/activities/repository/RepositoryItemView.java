package com.m_myr.nuwm.nuwmschedule.ui.activities.repository;

import androidx.lifecycle.LifecycleOwner;
import com.m_myr.nuwm.nuwmschedule.data.models.RepositoryDocument;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.BaseStateView;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.IntentProvider;

/* loaded from: classes2.dex */
public interface RepositoryItemView extends LifecycleOwner, BaseStateView, IntentProvider {
    void showContent(RepositoryDocument data);
}
