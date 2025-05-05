package com.m_myr.nuwm.nuwmschedule.ui.framents.profile.employee;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.repositories.AppDataManager;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: EmployeeProfileFragment.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J&\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\u0010\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\nH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/m_myr/nuwm/nuwmschedule/ui/framents/profile/employee/EmployeeProfileFragment;", "Landroidx/fragment/app/Fragment;", "Lcom/m_myr/nuwm/nuwmschedule/ui/framents/profile/employee/EmployeeProfileOwner;", "Landroid/view/View$OnClickListener;", "()V", "presenter", "Lcom/m_myr/nuwm/nuwmschedule/ui/framents/profile/employee/EmployeeProfilePresenterCompat;", "onClick", "", "v", "Landroid/view/View;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "setUpProfileIcon", "view", "app_publicReleaseRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class EmployeeProfileFragment extends Fragment implements EmployeeProfileOwner, View.OnClickListener {
    private final EmployeeProfilePresenterCompat presenter = new EmployeeProfilePresenterCompat(this);

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View inflate = inflater.inflate(R.layout.fragment_profile_employee, container, false);
        EmployeeProfileFragment employeeProfileFragment = this;
        inflate.findViewById(R.id.finance).setOnClickListener(employeeProfileFragment);
        inflate.findViewById(R.id.administration).setOnClickListener(employeeProfileFragment);
        if (AppDataManager.getInstance().getUserPermission().isIdCard()) {
            inflate.findViewById(R.id.idCard).setVisibility(0);
        }
        Intrinsics.checkNotNull(inflate);
        setUpProfileIcon(inflate);
        return inflate;
    }

    private final void setUpProfileIcon(View view) {
        RequestBuilder<Drawable> apply = Glide.with(this).load(AppDataManager.getInstance().getUser().getProfileImage()).apply((BaseRequestOptions<?>) RequestOptions.circleCropTransform());
        View findViewById = view.findViewById(R.id.profileIcon);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.ImageView");
        apply.into((ImageView) findViewById);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        v.getId();
        Utils.showStub(getContext());
    }
}
