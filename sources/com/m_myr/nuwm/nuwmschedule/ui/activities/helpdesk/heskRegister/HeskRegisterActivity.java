package com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.heskRegister;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.m_myr.nuwm.nuwmschedule.data.models.helpdesk.HelpdeskUserProfile;
import com.m_myr.nuwm.nuwmschedule.data.repositories.FastRepository;
import com.m_myr.nuwm.nuwmschedule.databinding.HeskRegisterBinding;
import com.m_myr.nuwm.nuwmschedule.network.ErrorResponse;
import com.m_myr.nuwm.nuwmschedule.network.api.APIMethods;
import com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HeskRegisterActivity.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0014R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\r"}, d2 = {"Lcom/m_myr/nuwm/nuwmschedule/ui/activities/helpdesk/heskRegister/HeskRegisterActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/m_myr/nuwm/nuwmschedule/databinding/HeskRegisterBinding;", "getBinding", "()Lcom/m_myr/nuwm/nuwmschedule/databinding/HeskRegisterBinding;", "setBinding", "(Lcom/m_myr/nuwm/nuwmschedule/databinding/HeskRegisterBinding;)V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "app_publicReleaseRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class HeskRegisterActivity extends AppCompatActivity {
    public HeskRegisterBinding binding;

    public final HeskRegisterBinding getBinding() {
        HeskRegisterBinding heskRegisterBinding = this.binding;
        if (heskRegisterBinding != null) {
            return heskRegisterBinding;
        }
        Intrinsics.throwUninitializedPropertyAccessException("binding");
        return null;
    }

    public final void setBinding(HeskRegisterBinding heskRegisterBinding) {
        Intrinsics.checkNotNullParameter(heskRegisterBinding, "<set-?>");
        this.binding = heskRegisterBinding;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HeskRegisterBinding inflate = HeskRegisterBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        setBinding(inflate);
        setContentView(getBinding().getRoot());
        getBinding().button.setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.heskRegister.HeskRegisterActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HeskRegisterActivity.onCreate$lambda$0(HeskRegisterActivity.this, view);
            }
        });
        getBinding().skip.setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.heskRegister.HeskRegisterActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HeskRegisterActivity.onCreate$lambda$1(HeskRegisterActivity.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$0(final HeskRegisterActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getBinding().button.setVisibility(4);
        this$0.getBinding().progressCircular.setVisibility(0);
        this$0.getBinding().progressCircular.show();
        FastRepository.call(APIMethods.authHesk()).into(new RequestObjectCallback<HelpdeskUserProfile>() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.helpdesk.heskRegister.HeskRegisterActivity$onCreate$1$1
            @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
            public void onError(ErrorResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                Toast.makeText(HeskRegisterActivity.this, response.getMessage(), 0).show();
                HeskRegisterActivity.this.setResult(0);
                HeskRegisterActivity.this.finish();
            }

            @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
            public void onSuccessData(HelpdeskUserProfile data) {
                Intrinsics.checkNotNullParameter(data, "data");
                Toast.makeText(HeskRegisterActivity.this, "Успішно зареєстровано!", 0).show();
                HeskRegisterActivity.this.getBinding().progressCircular.hide();
                HeskRegisterActivity.this.setResult(-1);
                HeskRegisterActivity.this.finish();
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$1(HeskRegisterActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setResult(0);
        this$0.finish();
    }
}
