package com.m_myr.nuwm.nuwmschedule.ui.activities.settings;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceFragmentCompat;
import com.google.android.material.color.DynamicColors;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.models.AccountInfo;
import com.m_myr.nuwm.nuwmschedule.data.repositories.AppDataManager;
import com.m_myr.nuwm.nuwmschedule.data.repositories.FastRepository;
import com.m_myr.nuwm.nuwmschedule.domain.App;
import com.m_myr.nuwm.nuwmschedule.domain.AppPreferences;
import com.m_myr.nuwm.nuwmschedule.network.APIMethod;
import com.m_myr.nuwm.nuwmschedule.network.ErrorResponse;
import com.m_myr.nuwm.nuwmschedule.network.api.APIMethods;
import com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback;
import com.m_myr.nuwm.nuwmschedule.ui.activities.main.MainActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.settings.AppMainSettingsFragment;
import com.m_myr.nuwm.nuwmschedule.ui.activities.signout.SignoutActivity;
import com.m_myr.nuwm.nuwmschedule.utils.Constant;
import com.m_myr.nuwm.nuwmschedule.utils.LinksResolver;
import java.util.Iterator;

/* loaded from: classes2.dex */
public class AppMainSettingsFragment extends PreferenceFragmentCompat {
    PreferenceCategory account_category;
    Preference exit;
    Preference mainAccount;

    @Override // androidx.preference.PreferenceFragmentCompat
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.main_preference, rootKey);
        this.mainAccount = findPreference("main_account");
        this.account_category = (PreferenceCategory) findPreference("account_category");
        this.exit = findPreference("exit");
        final Preference findPreference = findPreference("test_function");
        Preference findPreference2 = findPreference("material3_theme");
        findPreference.setVisible(AppPreferences.getInstance().isEnableTestFunction());
        if (DynamicColors.isDynamicColorAvailable()) {
            findPreference2.setVisible(AppPreferences.getInstance().isEnableTestFunction());
        } else {
            findPreference2.setVisible(false);
        }
        findPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.settings.AppMainSettingsFragment$$ExternalSyntheticLambda0
            @Override // androidx.preference.Preference.OnPreferenceChangeListener
            public final boolean onPreferenceChange(Preference preference, Object obj) {
                return AppMainSettingsFragment.lambda$onCreatePreferences$0(Preference.this, preference, obj);
            }
        });
        Log.e("dewfwevfc", "rewf" + AppPreferences.getInstance().isEnableMaterial3Theme());
        findPreference2.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.settings.AppMainSettingsFragment$$ExternalSyntheticLambda1
            @Override // androidx.preference.Preference.OnPreferenceChangeListener
            public final boolean onPreferenceChange(Preference preference, Object obj) {
                return AppMainSettingsFragment.this.m192xc95b46af(preference, obj);
            }
        });
        this.exit.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.settings.AppMainSettingsFragment$$ExternalSyntheticLambda2
            @Override // androidx.preference.Preference.OnPreferenceClickListener
            public final boolean onPreferenceClick(Preference preference) {
                return AppMainSettingsFragment.this.m193xb72740e(preference);
            }
        });
        FastRepository.from(this).call(APIMethods.getMyAccount()).into(new AnonymousClass2()).start();
    }

    static /* synthetic */ boolean lambda$onCreatePreferences$0(Preference preference, Preference preference2, Object obj) {
        if ((obj instanceof Boolean) && !((Boolean) obj).booleanValue()) {
            preference.setVisible(false);
        }
        AppPreferences.reInstance();
        return true;
    }

    /* renamed from: lambda$onCreatePreferences$1$com-m_myr-nuwm-nuwmschedule-ui-activities-settings-AppMainSettingsFragment, reason: not valid java name */
    /* synthetic */ boolean m192xc95b46af(Preference preference, Object obj) {
        Toast.makeText(requireContext(), "Додаток буде перезапущено", 0).show();
        Boolean bool = (Boolean) obj;
        AppPreferences.getInstance().setEnableMaterial3Theme(bool.booleanValue());
        if (bool.booleanValue()) {
            App.appThemeResource = R.style.AppTheme_Dynamic;
        } else {
            App.appThemeResource = Constant.getThemeResource(AppDataManager.getIndependentInstance().getInt("theme", -1));
        }
        new Handler().postDelayed(new Runnable() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.settings.AppMainSettingsFragment.1
            @Override // java.lang.Runnable
            public void run() {
                AppMainSettingsFragment.this.requireActivity().finishAffinity();
                AppMainSettingsFragment.this.startActivity(new Intent(AppMainSettingsFragment.this.requireActivity(), (Class<?>) MainActivity.class));
            }
        }, 200L);
        return true;
    }

    /* renamed from: lambda$onCreatePreferences$2$com-m_myr-nuwm-nuwmschedule-ui-activities-settings-AppMainSettingsFragment, reason: not valid java name */
    /* synthetic */ boolean m193xb72740e(Preference preference) {
        if (AppDataManager.getInstance().isGuest()) {
            SignoutActivity.exit(getActivity());
            return true;
        }
        SignoutActivity.showExitAlertWarning(getActivity());
        return true;
    }

    /* renamed from: com.m_myr.nuwm.nuwmschedule.ui.activities.settings.AppMainSettingsFragment$2, reason: invalid class name */
    class AnonymousClass2 implements RequestObjectCallback<AccountInfo> {
        @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
        public void onError(ErrorResponse response) {
        }

        AnonymousClass2() {
        }

        @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
        public void onSuccessData(AccountInfo data) {
            Iterator<AccountInfo.SimpleAccount> it = data.getAccounts().iterator();
            while (it.hasNext()) {
                AccountInfo.SimpleAccount next = it.next();
                if (next.main) {
                    AppMainSettingsFragment.this.mainAccount.setTitle(next.email);
                } else {
                    Preference preference = new Preference(AppMainSettingsFragment.this.getActivity());
                    preference.setTitle(next.email);
                    preference.setSummary("Додаткова пошта");
                    preference.setKey(next.email);
                    preference.setPersistent(false);
                    preference.setIcon(R.drawable.ic_mode_edit);
                    preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.settings.AppMainSettingsFragment$2$$ExternalSyntheticLambda0
                        @Override // androidx.preference.Preference.OnPreferenceClickListener
                        public final boolean onPreferenceClick(Preference preference2) {
                            return AppMainSettingsFragment.AnonymousClass2.this.m194xba7644cd(preference2);
                        }
                    });
                    AppMainSettingsFragment.this.account_category.addPreference(preference);
                }
            }
            Preference preference2 = new Preference(AppMainSettingsFragment.this.getActivity());
            preference2.setTitle("Додати");
            preference2.setKey("add_email");
            preference2.setPersistent(false);
            preference2.setIcon(R.drawable.ic_add);
            final AppMainSettingsFragment appMainSettingsFragment = AppMainSettingsFragment.this;
            preference2.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.settings.AppMainSettingsFragment$2$$ExternalSyntheticLambda1
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference3) {
                    boolean addEmail;
                    addEmail = AppMainSettingsFragment.this.addEmail(preference3);
                    return addEmail;
                }
            });
            AppMainSettingsFragment.this.account_category.addPreference(preference2);
        }

        /* renamed from: lambda$onSuccessData$0$com-m_myr-nuwm-nuwmschedule-ui-activities-settings-AppMainSettingsFragment$2, reason: not valid java name */
        /* synthetic */ boolean m194xba7644cd(Preference preference) {
            LinksResolver.startOnChrome(AppMainSettingsFragment.this.getActivity(), APIMethod.Patch.getAccountManagerDetachEmail(preference.getKey()));
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean addEmail(Preference preference) {
        LinksResolver.startOnChrome(getActivity(), APIMethod.Patch.getAccountManager());
        return true;
    }
}
