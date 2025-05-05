package com.m_myr.nuwm.nuwmschedule.ui.activities.googleAuth;

import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.gson.JsonObject;
import com.m_myr.nuwm.nuwmschedule.data.models.EmployeeNuwm;
import com.m_myr.nuwm.nuwmschedule.data.models.GuestUser;
import com.m_myr.nuwm.nuwmschedule.data.models.OfficeUserNuwm;
import com.m_myr.nuwm.nuwmschedule.data.models.StudentNumw;
import com.m_myr.nuwm.nuwmschedule.data.models.TeacherNuwm;
import com.m_myr.nuwm.nuwmschedule.data.repositories.APIOldObjectListener;
import com.m_myr.nuwm.nuwmschedule.data.repositories.APIRequestListener;
import com.m_myr.nuwm.nuwmschedule.data.repositories.AppDataManager;
import com.m_myr.nuwm.nuwmschedule.data.repositories.OldAPIRepository;
import com.m_myr.nuwm.nuwmschedule.domain.App;
import com.m_myr.nuwm.nuwmschedule.domain.BasePresenter;
import com.m_myr.nuwm.nuwmschedule.network.APIMethod;
import com.m_myr.nuwm.nuwmschedule.network.APIRequestResponse;
import com.m_myr.nuwm.nuwmschedule.network.ErrorResponse;
import com.m_myr.nuwm.nuwmschedule.network.models.AuthResponse;
import com.m_myr.nuwm.nuwmschedule.ui.activities.googleAuth.PresenterGoogleAuth;
import com.m_myr.nuwm.nuwmschedule.utils.Constant;

/* loaded from: classes2.dex */
public class PresenterGoogleAuth extends BasePresenter<IGoogleAuthView> implements DialogInterface.OnClickListener, View.OnClickListener {
    private OldAPIRepository apiRepository = new OldAPIRepository();
    AuthResponse authResponse;

    @Override // com.m_myr.nuwm.nuwmschedule.domain.BasePresenter
    public void attachView(IGoogleAuthView view) {
        super.attachView((PresenterGoogleAuth) view);
        if (view.getIntent().getBooleanExtra("change", false)) {
            signInClick();
        }
    }

    public void signInClick() {
        if (App.getInstance().getAccounts().size() == 0) {
            ((IGoogleAuthView) this.view).showLoading(true);
            ((IGoogleAuthView) this.view).startActivityLogin();
        } else {
            ((IGoogleAuthView) this.view).showAccountSelect(App.getInstance().getAccounts());
        }
    }

    public void onResult(Intent data) {
        Task<GoogleSignInAccount> signedInAccountFromIntent = GoogleSignIn.getSignedInAccountFromIntent(data);
        if (!signedInAccountFromIntent.isSuccessful()) {
            ((IGoogleAuthView) this.view).showLoading(false);
            if (signedInAccountFromIntent.getException() instanceof ApiException) {
                if (((ApiException) signedInAccountFromIntent.getException()).getStatusCode() == 7) {
                    ((IGoogleAuthView) this.view).showError("Перевірте мережу");
                    return;
                }
                ((IGoogleAuthView) this.view).showError("Помилка 0x00" + ((ApiException) signedInAccountFromIntent.getException()).getStatusCode());
                return;
            }
            ((IGoogleAuthView) this.view).showError("Помилка " + signedInAccountFromIntent.getException().getMessage());
            return;
        }
        this.apiRepository.call(APIMethod.auth(signedInAccountFromIntent.getResult().getServerAuthCode())).async(new APIOldObjectListener<AuthResponse>(AuthResponse.class) { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.googleAuth.PresenterGoogleAuth.1
            @Override // com.m_myr.nuwm.nuwmschedule.data.repositories.APIOldObjectListener
            public void onSuccessData(AuthResponse data2) {
                Log.e("apiRepository", "APIMethod.auth onSuccessData ");
                PresenterGoogleAuth.this.authResponse = data2;
                PresenterGoogleAuth.this.getUserDate();
            }

            @Override // com.m_myr.nuwm.nuwmschedule.data.repositories.APIOldObjectListener
            protected void onError(ErrorResponse response) {
                ((IGoogleAuthView) PresenterGoogleAuth.this.view).showError("Помилка 7841 " + response.getMessage());
                ((IGoogleAuthView) PresenterGoogleAuth.this.view).showLoading(false);
            }
        });
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.BasePresenter
    public void detachView() {
        super.detachView();
        this.apiRepository.unsubscribe();
    }

    /* renamed from: com.m_myr.nuwm.nuwmschedule.ui.activities.googleAuth.PresenterGoogleAuth$2, reason: invalid class name */
    class AnonymousClass2 extends APIRequestListener {
        AnonymousClass2() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.m_myr.nuwm.nuwmschedule.data.repositories.APIRequestListener
        /* renamed from: onError */
        public void m146xbd98ac6e(Throwable throwable) {
            ((IGoogleAuthView) PresenterGoogleAuth.this.view).showError(throwable.getMessage());
        }

        @Override // com.m_myr.nuwm.nuwmschedule.data.repositories.APIRequestListener
        protected void onSuccess(APIRequestResponse response) {
            try {
                Log.e("apiRepository", "APIMethod.profile onSuccess ");
                if (!response.isSuccessful()) {
                    ((IGoogleAuthView) PresenterGoogleAuth.this.view).showError("Помилка 54849 " + response.getError().getMessage());
                } else {
                    String asString = response.getResponseObject().getAsJsonPrimitive("type").getAsString();
                    JsonObject asJsonObject = response.getResponseObject().getAsJsonObject("profile");
                    if ("student".equals(asString)) {
                        AppDataManager.createInstance(PresenterGoogleAuth.this.authResponse, 4, StudentNumw.create(asJsonObject)).apply();
                        ((IGoogleAuthView) PresenterGoogleAuth.this.view).showError("З поверненням, " + AppDataManager.getInstance().getUser().getFirstName());
                        ((IGoogleAuthView) PresenterGoogleAuth.this.view).navigateToIntroActivity();
                        App.getInstance().subscribeToTopics();
                    } else if ("employee".equals(asString)) {
                        AppDataManager.createInstance(PresenterGoogleAuth.this.authResponse, 2, EmployeeNuwm.create(asJsonObject)).apply();
                        ((IGoogleAuthView) PresenterGoogleAuth.this.view).showError("З поверненням, " + AppDataManager.getInstance().getUser().getFirstName());
                        ((IGoogleAuthView) PresenterGoogleAuth.this.view).navigateToIntroActivity();
                    } else if ("teacher".equals(asString)) {
                        AppDataManager.createInstance(PresenterGoogleAuth.this.authResponse, 10, TeacherNuwm.create(asJsonObject)).apply();
                        ((IGoogleAuthView) PresenterGoogleAuth.this.view).showError("З поверненням, " + AppDataManager.getInstance().getUser().getFirstName());
                        ((IGoogleAuthView) PresenterGoogleAuth.this.view).navigateToIntroActivity();
                    } else if (!"office".equals(asString)) {
                        ((IGoogleAuthView) PresenterGoogleAuth.this.view).showError("Помилка 42. Зверніться до розробника");
                    } else {
                        final OfficeUserNuwm create = OfficeUserNuwm.create(asJsonObject);
                        ((IGoogleAuthView) PresenterGoogleAuth.this.view).showOfficeAlert(create, new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.googleAuth.PresenterGoogleAuth$2$$ExternalSyntheticLambda0
                            @Override // android.view.View.OnClickListener
                            public final void onClick(View view) {
                                PresenterGoogleAuth.AnonymousClass2.this.m164x60d0f1d1(create, view);
                            }
                        });
                    }
                }
            } catch (IllegalAccessException e) {
                m146xbd98ac6e(e);
            }
            ((IGoogleAuthView) PresenterGoogleAuth.this.view).showLoading(false);
        }

        /* renamed from: lambda$onSuccess$0$com-m_myr-nuwm-nuwmschedule-ui-activities-googleAuth-PresenterGoogleAuth$2, reason: not valid java name */
        /* synthetic */ void m164x60d0f1d1(OfficeUserNuwm officeUserNuwm, View view) {
            try {
                AppDataManager.createInstance(PresenterGoogleAuth.this.authResponse, 32, officeUserNuwm).apply();
                ((IGoogleAuthView) PresenterGoogleAuth.this.view).showError("З поверненням, " + AppDataManager.getInstance().getUser().getFirstName());
                ((IGoogleAuthView) PresenterGoogleAuth.this.view).navigateToIntroActivity();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                ((IGoogleAuthView) PresenterGoogleAuth.this.view).showError("Помилка 42. Зверніться до розробника");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getUserDate() {
        this.apiRepository.call(APIMethod.profile(this.authResponse.getToken())).async(new AnonymousClass2());
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialog, int which) {
        ((IGoogleAuthView) this.view).showLoading(true);
        ((IGoogleAuthView) this.view).startActivityLogin();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        App.getInstance().setCurrentAccount((String) v.getTag());
        AppDataManager.createInstance();
        ((IGoogleAuthView) this.view).navigateToIntroActivity();
    }

    public void loginHowAbit() {
        App.getInstance().setCurrentAccount("abit");
        AppDataManager.createInstance();
        ((IGoogleAuthView) this.view).navigateToAbitActivity();
    }

    public void loginHowGuest() {
        App.getInstance().setCurrentAccount(Constant.getNameOfTypeUser(1));
        try {
            AppDataManager.createInstance(AuthResponse.createForGuest(), 1, GuestUser.createDefault()).apply();
            ((IGoogleAuthView) this.view).showError("З поверненням, " + AppDataManager.getInstance().getUser().getFirstName());
            ((IGoogleAuthView) this.view).navigateToIntroActivity();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            ((IGoogleAuthView) this.view).showError("Не вдалося увійти :(");
        }
    }
}
