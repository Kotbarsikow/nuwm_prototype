package com.m_myr.nuwm.nuwmschedule.ui.framents.main;

import com.m_myr.nuwm.nuwmschedule.data.repositories.APIOldObjectListener;
import com.m_myr.nuwm.nuwmschedule.data.repositories.AppDataManager;
import com.m_myr.nuwm.nuwmschedule.domain.LegacyRepositoryPresenter;
import com.m_myr.nuwm.nuwmschedule.network.APIMethod;
import com.m_myr.nuwm.nuwmschedule.network.ErrorResponse;
import com.m_myr.nuwm.nuwmschedule.network.models.VerifiedResponse;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;

/* loaded from: classes2.dex */
public class MainMenuPresenterCompat extends LegacyRepositoryPresenter<MainMenuOwner> {
    public MainMenuPresenterCompat(MainMenuOwner view) {
        super(view, true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.m_myr.nuwm.nuwmschedule.domain.LifecyclePresenter
    public void onInit(final MainMenuOwner view) {
        view.setUserInfo(AppDataManager.getInstance().getUser());
        if (AppDataManager.getInstance().isNuwmPerson() && AppDataManager.getInstance().getHint(AppDataManager.HINT_idAlert) && !AppDataManager.getInstance().getPersonNuwm().isVerified()) {
            view.showVerifiedHint(true);
            if (Utils.StringUtils.isBlank(AppDataManager.getInstance().getPersonNuwm().getProfileImage())) {
                view.showPhotoHint(true);
            }
            getRepository().call(APIMethod.getVerified()).async(new APIOldObjectListener<VerifiedResponse>(VerifiedResponse.class) { // from class: com.m_myr.nuwm.nuwmschedule.ui.framents.main.MainMenuPresenterCompat.1
                @Override // com.m_myr.nuwm.nuwmschedule.data.repositories.APIOldObjectListener
                protected void onError(ErrorResponse response) {
                }

                @Override // com.m_myr.nuwm.nuwmschedule.data.repositories.APIOldObjectListener
                public void onSuccessData(VerifiedResponse data) {
                    if (data.isVerified()) {
                        AppDataManager.getInstance().updateUserState(data).apply();
                        view.showVerifiedHint(false);
                    } else {
                        view.showVerifiedHint(true);
                    }
                }
            });
        }
    }

    public void onClickOkHint() {
        ((MainMenuOwner) this.view).showVerifiedHint(false);
        AppDataManager.getInstance().setHideHint(AppDataManager.HINT_idAlert);
    }
}
