package com.m_myr.nuwm.nuwmschedule.ui.framents.search;

import androidx.lifecycle.LifecycleOwner;
import com.m_myr.nuwm.nuwmschedule.data.models.search.BaseSearchResult;
import com.m_myr.nuwm.nuwmschedule.data.models.search.EmployerSearchItem;
import com.m_myr.nuwm.nuwmschedule.data.models.search.RepositorySearchItem;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public interface ISearchView extends LifecycleOwner {
    void setHistoryDocuments(List<RepositorySearchItem> repositoryItems);

    void setHistoryEmployers(ArrayList<EmployerSearchItem> employerItems);

    void setResult(ArrayList<BaseSearchResult> results);

    void showInto(boolean show);
}
