package com.m_myr.nuwm.nuwmschedule.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.messaging.Constants;
import com.m_myr.nuwm.nuwmschedule.BuildConfig;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.ui.activities.DeveloperActivity;
import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CrashScreen.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u0012\u0010\r\u001a\u00020\n2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0014J\u000e\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u0012J\u0018\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u001a\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0019H\u0002J\u0018\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0019H\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u001b"}, d2 = {"Lcom/m_myr/nuwm/nuwmschedule/utils/CrashScreen;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "_container", "Landroid/view/ViewGroup;", "get_container", "()Landroid/view/ViewGroup;", "set_container", "(Landroid/view/ViewGroup;)V", "copy", "", "id", "Landroid/widget/TextView;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "openDeveloperActivity", "view", "Landroid/view/View;", "openSetting", "setText", "title", "", "info", "Landroid/text/Spanned;", "", "setTextNonNull", "app_publicReleaseRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CrashScreen extends AppCompatActivity {
    public ViewGroup _container;

    public final ViewGroup get_container() {
        ViewGroup viewGroup = this._container;
        if (viewGroup != null) {
            return viewGroup;
        }
        Intrinsics.throwUninitializedPropertyAccessException("_container");
        return null;
    }

    public final void set_container(ViewGroup viewGroup) {
        Intrinsics.checkNotNullParameter(viewGroup, "<set-?>");
        this._container = viewGroup;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crash_screen);
        View findViewById = findViewById(R.id._container);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        set_container((ViewGroup) findViewById);
        get_container().addView(setText("ApplicationId", BuildConfig.APPLICATION_ID));
        get_container().addView(setText("Build type", "release"));
        get_container().addView(setText("Flavor", BuildConfig.FLAVOR));
        get_container().addView(setText("Version code", (Object) 112));
        get_container().addView(setText("Version name", BuildConfig.VERSION_NAME));
        get_container().addView(setText("Time", new Date().toLocaleString()));
        View text = setText("StackTrace", getIntent().getStringExtra(Constants.IPC_BUNDLE_KEY_SEND_ERROR));
        View findViewById2 = text.findViewById(android.R.id.text2);
        Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type android.widget.TextView");
        ((TextView) findViewById2).setTextColor(getResources().getColor(R.color.red_normal));
        View findViewById3 = text.findViewById(android.R.id.text2);
        Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type android.widget.TextView");
        ((TextView) findViewById3).setTextSize(8.4f);
        get_container().addView(text);
    }

    private final View setText(String title, Object info) {
        if (info == null) {
            return setTextNonNull(title, "null");
        }
        return setTextNonNull(title, info);
    }

    private final View setTextNonNull(String title, Object info) {
        return setText("<b>" + title + "</b>\t[" + info.getClass().getSimpleName() + ']', (Spanned) new SpannableString(info.toString()));
    }

    private final View setText(String title, Spanned info) {
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(android.R.attr.selectableItemBackground, typedValue, true);
        View inflate = getLayoutInflater().inflate(android.R.layout.simple_list_item_activated_2, get_container(), false);
        inflate.setBackgroundResource(typedValue.resourceId);
        View findViewById = inflate.findViewById(android.R.id.text1);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.TextView");
        ((TextView) findViewById).setText(Html.fromHtml(title));
        View findViewById2 = inflate.findViewById(android.R.id.text2);
        Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type android.widget.TextView");
        ((TextView) findViewById2).setText(info);
        inflate.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.utils.CrashScreen$setText$1
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View v) {
                Intrinsics.checkNotNullParameter(v, "v");
                CrashScreen crashScreen = CrashScreen.this;
                View findViewById3 = v.findViewById(android.R.id.text2);
                Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type android.widget.TextView");
                crashScreen.copy((TextView) findViewById3);
                return true;
            }
        });
        Intrinsics.checkNotNull(inflate);
        return inflate;
    }

    public final void copy(TextView id) {
        Intrinsics.checkNotNullParameter(id, "id");
        Object systemService = getSystemService("clipboard");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.content.ClipboardManager");
        ClipData newPlainText = ClipData.newPlainText("", id.getText().toString());
        Intrinsics.checkNotNullExpressionValue(newPlainText, "newPlainText(...)");
        ((ClipboardManager) systemService).setPrimaryClip(newPlainText);
        Toast.makeText(this, "Скопійовано", 0).show();
    }

    public final void openSetting(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        startActivity(new Intent(this, (Class<?>) DeveloperActivity.class));
    }

    public final void openDeveloperActivity(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        startActivity(new Intent(this, (Class<?>) DeveloperActivity.class));
    }
}
