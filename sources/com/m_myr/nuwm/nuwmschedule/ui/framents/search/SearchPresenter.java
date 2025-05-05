package com.m_myr.nuwm.nuwmschedule.ui.framents.search;

import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.m_myr.nuwm.nuwmschedule.data.models.LastSearch;
import com.m_myr.nuwm.nuwmschedule.data.models.search.BaseSearchResult;
import com.m_myr.nuwm.nuwmschedule.data.models.search.EmployerSearchItem;
import com.m_myr.nuwm.nuwmschedule.data.models.search.RepositorySearchItem;
import com.m_myr.nuwm.nuwmschedule.domain.RepositoryPresenter;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.ItemClick;
import com.m_myr.nuwm.nuwmschedule.network.ErrorResponse;
import com.m_myr.nuwm.nuwmschedule.network.api.APIMethods;
import com.m_myr.nuwm.nuwmschedule.network.api.APIObjectListener;
import com.m_myr.nuwm.nuwmschedule.network.api.APIObjectSuccessListener;
import com.m_myr.nuwm.nuwmschedule.network.models.SearchResponse;
import com.m_myr.nuwm.nuwmschedule.utils.gson.JsonSearchResponseDeserialize;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class SearchPresenter extends RepositoryPresenter<ISearchView> implements ItemClick<BaseSearchResult> {
    private LastSearch lastSearch;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.m_myr.nuwm.nuwmschedule.domain.LifecyclePresenter
    public void onInit(ISearchView view) {
    }

    public SearchPresenter(ISearchView view) {
        super(view, true);
    }

    public void loadHistory() {
        if (this.lastSearch == null) {
            Log.e("SearchPresenter", "lastSearch == null");
            getRepository().loadStorage(LastSearch.class).async((APIObjectListener) new APIObjectListener<LastSearch>() { // from class: com.m_myr.nuwm.nuwmschedule.ui.framents.search.SearchPresenter.1
                @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
                public void onError(ErrorResponse response) {
                    Log.e("SearchPresenter", "onError");
                    SearchPresenter.this.lastSearch = new LastSearch();
                    SearchPresenter.this.updateHistory();
                }

                @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
                public void onSuccessData(LastSearch data) {
                    Log.e("SearchPresenter", "onSuccessData");
                    SearchPresenter.this.lastSearch = data;
                    SearchPresenter.this.updateHistory();
                }
            });
        } else {
            updateHistory();
        }
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.ItemClick
    public void onClick(int position, BaseSearchResult object) {
        this.lastSearch.addSearch(object);
        updateHistory();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateHistory() {
        List<RepositorySearchItem> repositoryItems = this.lastSearch.getRepositoryItems();
        ((ISearchView) this.view).setHistoryDocuments(repositoryItems);
        ArrayList<EmployerSearchItem> employerItems = this.lastSearch.getEmployerItems();
        ((ISearchView) this.view).setHistoryEmployers(employerItems);
        if (repositoryItems.isEmpty() && employerItems.isEmpty()) {
            ((ISearchView) this.view).showInto(true);
        } else {
            ((ISearchView) this.view).showInto(false);
        }
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.RepositoryPresenter, com.m_myr.nuwm.nuwmschedule.domain.LifecyclePresenter
    protected void onDestroy() {
        super.onDestroy();
        getRepository().loadStorage(LastSearch.class).save(this.lastSearch);
    }

    public boolean textChanged(CharSequence s) {
        if (s.length() == 0) {
            return false;
        }
        ((ISearchView) this.view).showInto(false);
        getRepository().call(APIMethods.search(s.toString())).async(new APIObjectSuccessListener<SearchResponse>() { // from class: com.m_myr.nuwm.nuwmschedule.ui.framents.search.SearchPresenter.2
            @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
            public void onSuccessData(SearchResponse data) {
                ((ISearchView) SearchPresenter.this.view).setResult(data.getResults());
            }

            @Override // com.m_myr.nuwm.nuwmschedule.network.api.APIObjectListener, com.m_myr.nuwm.nuwmschedule.domain.interfaces.GsonGetter
            public Gson getGson() {
                Type type = new TypeToken<ArrayList<BaseSearchResult>>() { // from class: com.m_myr.nuwm.nuwmschedule.ui.framents.search.SearchPresenter.2.1
                }.getType();
                GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder.registerTypeAdapter(type, new JsonSearchResponseDeserialize());
                return gsonBuilder.create();
            }
        });
        return true;
    }
}
