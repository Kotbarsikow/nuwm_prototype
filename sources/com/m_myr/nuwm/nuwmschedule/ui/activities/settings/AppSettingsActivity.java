package com.m_myr.nuwm.nuwmschedule.ui.activities.settings;

import android.content.Intent;
import android.os.Bundle;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.models.AccountInfo;
import com.m_myr.nuwm.nuwmschedule.data.repositories.FastRepository;
import com.m_myr.nuwm.nuwmschedule.domain.App;
import com.m_myr.nuwm.nuwmschedule.domain.AppPreferences;
import com.m_myr.nuwm.nuwmschedule.network.ErrorResponse;
import com.m_myr.nuwm.nuwmschedule.network.api.APIMethods;
import com.m_myr.nuwm.nuwmschedule.network.api.APIObjectListener;
import com.m_myr.nuwm.nuwmschedule.network.api.APIObjectSuccessListener;
import com.m_myr.nuwm.nuwmschedule.ui.activities.BaseToolbarActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.signout.SignoutActivity;

/* loaded from: classes2.dex */
public class AppSettingsActivity extends BaseToolbarActivity {
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        attachToolbar();
        getSupportFragmentManager().beginTransaction().replace(R.id.settings_activity_root, new AppMainSettingsFragment()).commit();
        if (getIntent().getData() != null) {
            FastRepository.call(APIMethods.getMyAccount()).into((APIObjectListener) new APIObjectSuccessListener<AccountInfo>() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.settings.AppSettingsActivity.1
                AnonymousClass1() {
                }

                @Override // com.m_myr.nuwm.nuwmschedule.network.api.APIObjectSuccessListener, com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
                public void onError(ErrorResponse response) {
                    super.onError(response);
                }

                @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
                public void onSuccessData(AccountInfo data) {
                    if (data.getTokenInfo().valid) {
                        return;
                    }
                    Intent intent = new Intent(App.getInstance(), (Class<?>) SignoutActivity.class);
                    intent.addFlags(67108864);
                    intent.putExtra("change", false);
                    AppSettingsActivity.this.startActivity(intent);
                    AppSettingsActivity.this.finish();
                }
            }).start();
        }
    }

    /* renamed from: com.m_myr.nuwm.nuwmschedule.ui.activities.settings.AppSettingsActivity$1 */
    class AnonymousClass1 extends APIObjectSuccessListener<AccountInfo> {
        AnonymousClass1() {
        }

        @Override // com.m_myr.nuwm.nuwmschedule.network.api.APIObjectSuccessListener, com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
        public void onError(ErrorResponse response) {
            super.onError(response);
        }

        @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
        public void onSuccessData(AccountInfo data) {
            if (data.getTokenInfo().valid) {
                return;
            }
            Intent intent = new Intent(App.getInstance(), (Class<?>) SignoutActivity.class);
            intent.addFlags(67108864);
            intent.putExtra("change", false);
            AppSettingsActivity.this.startActivity(intent);
            AppSettingsActivity.this.finish();
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        AppPreferences.reInstance();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        AppPreferences.reInstance();
    }

    @Override // android.app.Activity
    public boolean onNavigateUp() {
        onBackPressed();
        return super.onNavigateUp();
    }
}
