package com.m_myr.nuwm.nuwmschedule.ui.activities.main;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.messaging.Constants;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.models.UserNuwm;
import com.m_myr.nuwm.nuwmschedule.data.repositories.AppDataManager;
import com.m_myr.nuwm.nuwmschedule.domain.App;
import com.m_myr.nuwm.nuwmschedule.network.ErrorResponse;
import com.m_myr.nuwm.nuwmschedule.network.api.APIObjectListener;
import com.m_myr.nuwm.nuwmschedule.network.models.ProfileResponse;
import com.m_myr.nuwm.nuwmschedule.ui.activities.googleAuth.GoogleAuth;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ProfileMenuAlert.kt */
@Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0002H\u0016¨\u0006\t"}, d2 = {"com/m_myr/nuwm/nuwmschedule/ui/activities/main/ProfileMenuAlert$updateAccount$1", "Lcom/m_myr/nuwm/nuwmschedule/network/api/APIObjectListener;", "Lcom/m_myr/nuwm/nuwmschedule/network/models/ProfileResponse;", "onError", "", "response", "Lcom/m_myr/nuwm/nuwmschedule/network/ErrorResponse;", "onSuccessData", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "app_publicReleaseRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ProfileMenuAlert$updateAccount$1 extends APIObjectListener<ProfileResponse> {
    final /* synthetic */ ProfileMenuAlert this$0;

    ProfileMenuAlert$updateAccount$1(ProfileMenuAlert profileMenuAlert) {
        this.this$0 = profileMenuAlert;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
    public void onError(ErrorResponse response) {
        Intrinsics.checkNotNullParameter(response, "response");
        Toast.makeText(this.this$0.getContext(), "Зараз неможливо виконати цю дію", 0).show();
    }

    @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
    public void onSuccessData(ProfileResponse r4) {
        Intrinsics.checkNotNullParameter(r4, "data");
        AppDataManager appDataManager = AppDataManager.getInstance();
        if (r4.getTypeInt() == appDataManager.getAuthType()) {
            appDataManager.updateUser(UserNuwm.createChildByName(r4.getType(), r4.getProfile())).apply();
            try {
                Snackbar make = Snackbar.make(this.this$0.getActivity().findViewById(R.id.container), "Дані акаунту було оновлено. Додаток буде перезапущено", 10000);
                Intrinsics.checkNotNullExpressionValue(make, "make(...)");
                make.setAction("Перезапустити", new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.main.ProfileMenuAlert$updateAccount$1$$ExternalSyntheticLambda0
                    public /* synthetic */ ProfileMenuAlert$updateAccount$1$$ExternalSyntheticLambda0() {
                    }

                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ProfileMenuAlert$updateAccount$1.onSuccessData$lambda$0(ProfileMenuAlert.this, view);
                    }
                });
                make.show();
                return;
            } catch (Exception unused) {
                return;
            }
        }
        Toast.makeText(App.getInstance(), "Дані акаунту було змінено. Необхідно виконати вхід знову", 1).show();
        App.getInstance().removeCurrentAccount();
        Intent intent = new Intent(this.this$0.getContext(), (Class<?>) GoogleAuth.class);
        intent.addFlags(268435456);
        this.this$0.getActivity().startActivity(intent);
        this.this$0.getActivity().finish();
    }

    public static final void onSuccessData$lambda$0(ProfileMenuAlert this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intent intent = new Intent(this$0.getContext(), (Class<?>) MainActivity.class);
        intent.addFlags(268435456);
        this$0.getActivity().finishAfterTransition();
        this$0.getActivity().startActivity(intent);
    }
}
