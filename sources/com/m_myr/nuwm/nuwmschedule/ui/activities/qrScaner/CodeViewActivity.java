package com.m_myr.nuwm.nuwmschedule.ui.activities.qrScaner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import androidx.appcompat.app.AppCompatActivity;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.databinding.BarcodeViewActivityBinding;
import com.m_myr.nuwm.nuwmschedule.utils.Gs1_128;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CodeViewActivity.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0014J\b\u0010\r\u001a\u00020\nH\u0014R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u000f"}, d2 = {"Lcom/m_myr/nuwm/nuwmschedule/ui/activities/qrScaner/CodeViewActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/m_myr/nuwm/nuwmschedule/databinding/BarcodeViewActivityBinding;", "getBinding", "()Lcom/m_myr/nuwm/nuwmschedule/databinding/BarcodeViewActivityBinding;", "setBinding", "(Lcom/m_myr/nuwm/nuwmschedule/databinding/BarcodeViewActivityBinding;)V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onStop", "Companion", "app_publicReleaseRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CodeViewActivity extends AppCompatActivity {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public BarcodeViewActivityBinding binding;

    @JvmStatic
    public static final void showGS128(Context context, String str, String str2, String str3) {
        INSTANCE.showGS128(context, str, str2, str3);
    }

    public final BarcodeViewActivityBinding getBinding() {
        BarcodeViewActivityBinding barcodeViewActivityBinding = this.binding;
        if (barcodeViewActivityBinding != null) {
            return barcodeViewActivityBinding;
        }
        Intrinsics.throwUninitializedPropertyAccessException("binding");
        return null;
    }

    public final void setBinding(BarcodeViewActivityBinding barcodeViewActivityBinding) {
        Intrinsics.checkNotNullParameter(barcodeViewActivityBinding, "<set-?>");
        this.binding = barcodeViewActivityBinding;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BarcodeViewActivityBinding inflate = BarcodeViewActivityBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        setBinding(inflate);
        setContentView(getBinding().getRoot());
        final Gs1_128 gs1_128 = new Gs1_128(getIntent().getStringExtra("code_128"));
        if (getIntent().hasExtra("fullName")) {
            getBinding().first.setText(getIntent().getStringExtra("fullName"));
        }
        if (getIntent().hasExtra("info")) {
            getBinding().second.setText(getIntent().getStringExtra("info"));
        }
        getBinding().codetext.setText(getIntent().getStringExtra("code_128"));
        getBinding().code.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.qrScaner.CodeViewActivity$onCreate$1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                CodeViewActivity.this.getBinding().code.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                CodeViewActivity.this.getBinding().code.setImageBitmap(gs1_128.ToBitMap(CodeViewActivity.this.getBinding().code.getHeight(), CodeViewActivity.this.getResources().getDimensionPixelSize(R.dimen.code128) * 2));
            }
        });
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
    }

    /* compiled from: CodeViewActivity.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J(\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\bH\u0007¨\u0006\u000b"}, d2 = {"Lcom/m_myr/nuwm/nuwmschedule/ui/activities/qrScaner/CodeViewActivity$Companion;", "", "()V", "showGS128", "", "context", "Landroid/content/Context;", "code", "", "info", "fullName", "app_publicReleaseRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final void showGS128(Context context, String code, String info, String fullName) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(code, "code");
            Intrinsics.checkNotNullParameter(info, "info");
            Intrinsics.checkNotNullParameter(fullName, "fullName");
            Intent intent = new Intent(context, (Class<?>) CodeViewActivity.class);
            intent.putExtra("code_128", code);
            intent.putExtra("info", info);
            intent.putExtra("fullName", fullName);
            context.startActivity(intent);
        }
    }
}
