package com.m_myr.nuwm.nuwmschedule.ui.framents.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.snackbar.Snackbar;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.models.LoggedUser;
import com.m_myr.nuwm.nuwmschedule.data.models.UserNuwm;
import com.m_myr.nuwm.nuwmschedule.data.repositories.AppDataManager;
import com.m_myr.nuwm.nuwmschedule.data.repositories.FastRepository;
import com.m_myr.nuwm.nuwmschedule.domain.App;
import com.m_myr.nuwm.nuwmschedule.network.APIMethod;
import com.m_myr.nuwm.nuwmschedule.network.ErrorResponse;
import com.m_myr.nuwm.nuwmschedule.network.api.APIMethods;
import com.m_myr.nuwm.nuwmschedule.network.api.APIObjectListener;
import com.m_myr.nuwm.nuwmschedule.network.models.ProfileResponse;
import com.m_myr.nuwm.nuwmschedule.ui.activities.about.AboutActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.googleAuth.GoogleAuth;
import com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.HelpdeskActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.main.MainActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.notesList.MyNotesListActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.settings.AppSettingsActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.signout.SignoutActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.students.StudentProfileActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.user.UserProfileActivity;
import com.m_myr.nuwm.nuwmschedule.ui.framents.main.MainMenuFragment;
import com.m_myr.nuwm.nuwmschedule.utils.LinksResolver;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;

/* loaded from: classes2.dex */
public class MainMenuFragment extends Fragment implements MainMenuOwner, View.OnClickListener {
    private TextView mHint;
    private MaterialButton mHintOk;
    private ImageView mProfileImage;
    private TextView mUsername;
    private TextView mUsertype;
    private MaterialCardView mVerificationHint;
    MainMenuPresenterCompat presenter = new MainMenuPresenterCompat(this);

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.profile_card, container, false);
        this.mProfileImage = (ImageView) inflate.findViewById(R.id.profile_image);
        this.mUsername = (TextView) inflate.findViewById(R.id.username);
        this.mUsertype = (TextView) inflate.findViewById(R.id.usertype);
        this.mVerificationHint = (MaterialCardView) inflate.findViewById(R.id.verification_hint);
        this.mHint = (TextView) inflate.findViewById(R.id.hint);
        MaterialButton materialButton = (MaterialButton) inflate.findViewById(R.id.hint_ok);
        this.mHintOk = materialButton;
        materialButton.setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.framents.main.MainMenuFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                MainMenuFragment.this.presenter.onClickOkHint();
            }
        });
        inflate.findViewById(R.id.note).setOnClickListener(this);
        inflate.findViewById(R.id.about).setOnClickListener(this);
        inflate.findViewById(R.id.terms).setOnClickListener(this);
        inflate.findViewById(R.id.profile_place).setOnClickListener(this);
        inflate.findViewById(R.id.achiv_stub).setOnClickListener(this);
        inflate.findViewById(R.id.settings).setOnClickListener(this);
        inflate.findViewById(R.id.helpdesk).setOnClickListener(this);
        inflate.findViewById(R.id.navigateToDesk).setOnClickListener(this);
        if (AppDataManager.getInstance().getUserPermission().isIdCard()) {
            inflate.findViewById(R.id.idCard).setVisibility(0);
        }
        if (AppDataManager.getInstance().isGuest()) {
            inflate.findViewById(R.id.achiv_stub).setVisibility(8);
        }
        AppDataManager.getInstance().isStudent();
        return inflate;
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.framents.main.MainMenuOwner
    public void setUserInfo(LoggedUser user) {
        Glide.with(this).load(user.getProfileImage()).apply((BaseRequestOptions<?>) RequestOptions.circleCropTransform()).into(this.mProfileImage);
        this.mUsername.setText(user.getSimpleName());
        this.mUsertype.setText(user.getWho());
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.framents.main.MainMenuOwner
    public void showVerifiedHint(boolean show) {
        if (show) {
            this.mVerificationHint.setVisibility(0);
            this.mHint.setText("Ви зможете користуватися своєю ID-карткою після верифікації вашої фотографії");
        } else {
            this.mVerificationHint.setVisibility(8);
        }
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.framents.main.MainMenuOwner
    public void showPhotoHint(boolean show) {
        if (show) {
            this.mVerificationHint.setVisibility(0);
            this.mHint.setText("Ви зможете користуватися своєю ID-карткою після завантаження та  верифікації вашої фотографії");
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.note) {
            startActivityForResult(new Intent(getActivity(), (Class<?>) MyNotesListActivity.class), 50);
            return;
        }
        if (id == R.id.about) {
            startActivity(new Intent(getActivity(), (Class<?>) AboutActivity.class));
            return;
        }
        if (id == R.id.profile_place) {
            showAccountManager(v);
            return;
        }
        if (id == R.id.settings) {
            startActivity(new Intent(getActivity(), (Class<?>) AppSettingsActivity.class));
            return;
        }
        if (id == R.id.helpdesk) {
            startActivity(new Intent(getActivity(), (Class<?>) HelpdeskActivity.class));
            return;
        }
        if (id == R.id.navigateToDesk) {
            LinksResolver.startOnChrome(requireContext(), "https://nuwm.edu.ua/kabinet/privacy-policy");
        } else if (id == R.id.terms) {
            LinksResolver.startOnChrome(requireContext(), APIMethod.Patch.getLinkDesk());
        } else {
            Utils.showStub(getContext(), "Ця функція ще розробляється");
        }
    }

    private void showAccountManager(View v) {
        PopupMenu popupMenu = new PopupMenu(getContext(), v);
        popupMenu.inflate(R.menu.popupmenu_account_manager);
        if (AppDataManager.getInstance().isGuest()) {
            popupMenu.getMenu().findItem(R.id.change_account).setVisible(false);
            popupMenu.getMenu().findItem(R.id.my_profile).setVisible(false);
        }
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.framents.main.MainMenuFragment.2
            @Override // android.widget.PopupMenu.OnMenuItemClickListener
            public boolean onMenuItemClick(MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.sign_out) {
                    if (AppDataManager.getInstance().isGuest()) {
                        SignoutActivity.exit(MainMenuFragment.this.getActivity());
                    } else {
                        SignoutActivity.showExitAlertWarning(MainMenuFragment.this.getActivity());
                    }
                    return true;
                }
                if (itemId == R.id.my_profile) {
                    if (AppDataManager.getInstance().isStudent()) {
                        StudentProfileActivity.startById(MainMenuFragment.this.getContext(), AppDataManager.getInstance().getUser().getId());
                    } else if (AppDataManager.getInstance().isEmployee()) {
                        UserProfileActivity.startById(MainMenuFragment.this.getContext(), AppDataManager.getInstance().getUser().getId());
                    }
                    Utils.sendAnalytic(MainMenuFragment.this.getContext(), "click_my_profile", new Pair[0]);
                    return true;
                }
                if (itemId == R.id.change_account) {
                    SignoutActivity.change(MainMenuFragment.this.getActivity());
                    return true;
                }
                if (itemId != R.id.update_account) {
                    return false;
                }
                MainMenuFragment.this.updateAccount();
                return true;
            }
        });
        popupMenu.show();
    }

    /* renamed from: com.m_myr.nuwm.nuwmschedule.ui.framents.main.MainMenuFragment$3, reason: invalid class name */
    class AnonymousClass3 extends APIObjectListener<ProfileResponse> {
        AnonymousClass3() {
        }

        @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
        public void onError(ErrorResponse response) {
            Toast.makeText(MainMenuFragment.this.getContext(), "Зараз неможливо виконати цю дію", 0).show();
        }

        @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
        public void onSuccessData(ProfileResponse data) {
            AppDataManager appDataManager = AppDataManager.getInstance();
            if (data.getTypeInt() == appDataManager.getAuthType()) {
                appDataManager.updateUser(UserNuwm.createChildByName(data.getType(), data.getProfile())).apply();
                try {
                    Snackbar make = Snackbar.make(MainMenuFragment.this.requireActivity().findViewById(R.id.container), "Дані акаунту було змінено. Додаток буде перезапущено", 10000);
                    make.setAction("Перезапустити", new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.framents.main.MainMenuFragment$3$$ExternalSyntheticLambda0
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            MainMenuFragment.AnonymousClass3.this.m203xb90b5f42(view);
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
            Intent intent = new Intent(MainMenuFragment.this.requireContext(), (Class<?>) GoogleAuth.class);
            intent.addFlags(268435456);
            MainMenuFragment.this.startActivity(intent);
            MainMenuFragment.this.getActivity().finish();
        }

        /* renamed from: lambda$onSuccessData$0$com-m_myr-nuwm-nuwmschedule-ui-framents-main-MainMenuFragment$3, reason: not valid java name */
        /* synthetic */ void m203xb90b5f42(View view) {
            Intent intent = new Intent(MainMenuFragment.this.requireContext(), (Class<?>) MainActivity.class);
            intent.addFlags(268435456);
            MainMenuFragment.this.startActivity(intent);
            MainMenuFragment.this.getActivity().finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateAccount() {
        FastRepository.from(this).call(APIMethods.getProfileJson()).into((APIObjectListener) new AnonymousClass3()).start();
    }
}
