package com.m_myr.nuwm.nuwmschedule.ui.activities.repositoryPeople;

import androidx.lifecycle.LifecycleOwner;
import com.m_myr.nuwm.nuwmschedule.data.models.KeyPairValue;
import com.m_myr.nuwm.nuwmschedule.data.models.RepositoryItem;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.BaseStateView;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.IntentProvider;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public interface UserRepositoryView extends LifecycleOwner, BaseStateView, IntentProvider {
    void onDataSet(ArrayList<RepositoryItem> fullData);

    void setFilterRange(int minY, int maxY);

    void setFilterTypes(ArrayList<KeyPairValue<String>> documentTypes);

    void setTitle(String s);

    void showFilter(boolean show);
}
