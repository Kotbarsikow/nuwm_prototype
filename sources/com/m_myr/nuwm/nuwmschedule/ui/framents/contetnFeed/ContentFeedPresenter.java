package com.m_myr.nuwm.nuwmschedule.ui.framents.contetnFeed;

import android.os.Bundle;
import com.m_myr.nuwm.nuwmschedule.data.models.NewsViewItem;
import com.m_myr.nuwm.nuwmschedule.domain.RepositoryPresenter;
import com.m_myr.nuwm.nuwmschedule.network.ErrorResponse;
import com.m_myr.nuwm.nuwmschedule.network.api.APIMethods;
import com.m_myr.nuwm.nuwmschedule.network.api.APIObjectListener;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class ContentFeedPresenter extends RepositoryPresenter<IContentFeedView> {
    private String category;
    private int categoryId;
    private boolean loadingMore;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.m_myr.nuwm.nuwmschedule.domain.LifecyclePresenter
    public void onInit(IContentFeedView view) {
    }

    public ContentFeedPresenter(IContentFeedView view) {
        super(view);
    }

    public void get(Bundle arguments) {
        this.category = arguments.getString("category");
        this.categoryId = arguments.getInt("categoryId");
        load(0);
    }

    public void onScroll(int visibleItemCount, int pastVisibleItems, int totalItemCount) {
        if (this.loadingMore || visibleItemCount + pastVisibleItems < totalItemCount) {
            return;
        }
        load(totalItemCount - 1);
    }

    private void load(int offset) {
        this.loadingMore = true;
        getRepository().call(APIMethods.getNewsFeed(this.category, this.categoryId, offset)).async(new APIObjectListener<ArrayList<NewsViewItem>>() { // from class: com.m_myr.nuwm.nuwmschedule.ui.framents.contetnFeed.ContentFeedPresenter.1
            @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
            public void onError(ErrorResponse response) {
            }

            @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
            public void onSuccessData(ArrayList<NewsViewItem> data) {
                ((IContentFeedView) ContentFeedPresenter.this.view).updateList(data);
                ContentFeedPresenter.this.loadingMore = false;
            }
        });
    }
}
