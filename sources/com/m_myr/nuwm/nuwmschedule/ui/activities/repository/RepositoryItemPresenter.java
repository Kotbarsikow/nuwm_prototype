package com.m_myr.nuwm.nuwmschedule.ui.activities.repository;

import android.net.Uri;
import com.m_myr.nuwm.nuwmschedule.data.models.RepositoryDocument;
import com.m_myr.nuwm.nuwmschedule.domain.RepositoryPresenter;
import com.m_myr.nuwm.nuwmschedule.network.ErrorResponse;
import com.m_myr.nuwm.nuwmschedule.network.api.APIMethods;
import com.m_myr.nuwm.nuwmschedule.network.api.APIObjectListener;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;
import java.util.Iterator;

/* loaded from: classes2.dex */
public class RepositoryItemPresenter extends RepositoryPresenter<RepositoryItemView> {
    int eprintid;

    public RepositoryItemPresenter(RepositoryItemView view) {
        super(view);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.m_myr.nuwm.nuwmschedule.domain.LifecyclePresenter
    public void onInit(final RepositoryItemView view) {
        view.showLoading();
        if (view.getIntent().getData() != null) {
            Uri data = view.getIntent().getData();
            this.eprintid = 0;
            Iterator<String> it = data.getPathSegments().iterator();
            while (it.hasNext()) {
                int safeToInt = Utils.StringUtils.safeToInt(it.next(), 0);
                this.eprintid = safeToInt;
                if (safeToInt != 0) {
                    break;
                }
            }
            if (this.eprintid == 0) {
                view.showError("Не вдалося відкрити посилання " + data.toString());
                return;
            }
        } else {
            this.eprintid = view.getIntent().getIntExtra("eprintid", -1);
        }
        getRepository().call(APIMethods.getRepositoryDocument(this.eprintid)).async(new APIObjectListener<RepositoryDocument>() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.repository.RepositoryItemPresenter.1
            @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
            public void onError(ErrorResponse response) {
                view.showError(response.getMessage());
            }

            @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
            public void onSuccessData(RepositoryDocument data2) {
                view.showContent(data2);
            }
        });
    }
}
