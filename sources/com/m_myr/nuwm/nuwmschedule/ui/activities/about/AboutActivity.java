package com.m_myr.nuwm.nuwmschedule.ui.activities.about;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.browser.customtabs.CustomTabsIntent;
import com.m_myr.nuwm.nuwmschedule.BuildConfig;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.repositories.AppDataManager;
import com.m_myr.nuwm.nuwmschedule.domain.App;
import com.m_myr.nuwm.nuwmschedule.domain.AppPreferences;
import com.m_myr.nuwm.nuwmschedule.ui.activities.BaseToolbarActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.DeveloperActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.department.DepartmentProfileActivity;

/* loaded from: classes2.dex */
public class AboutActivity extends BaseToolbarActivity implements View.OnClickListener {
    private int Easter_Egg = 0;
    TextView appName;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(App.getInstance().getAppTheme());
        setContentView(R.layout.about_activity);
        attachToolbar();
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.developer);
        LinearLayout linearLayout2 = (LinearLayout) findViewById(R.id.version);
        LinearLayout linearLayout3 = (LinearLayout) findViewById(R.id.privacy_policy);
        this.appName = (TextView) findViewById(R.id.appName);
        ((TextView) findViewById(R.id.version_str)).setText(String.format("%s (%d)", BuildConfig.VERSION_NAME, 112));
        findViewById(R.id.image_done).setOnClickListener(this);
        linearLayout3.setOnClickListener(this);
        linearLayout2.setOnClickListener(this);
        linearLayout.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        if (v.getId() == R.id.privacy_policy) {
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setTitle("Політика конфіденційності");
            WebView webView = new WebView(v.getContext());
            webView.loadUrl("https://nuwm.edu.ua/kabinet/privacy-policy");
            webView.setWebViewClient(new WebViewClient() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.about.AboutActivity.1
                @Override // android.webkit.WebViewClient
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }
            });
            builder.setView(webView);
            builder.setNegativeButton("Закрити", new DialogInterface.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.about.AboutActivity$$ExternalSyntheticLambda0
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            builder.show();
        } else if (v.getId() == R.id.open_sw) {
            AlertDialog.Builder builder2 = new AlertDialog.Builder(v.getContext());
            builder2.setTitle("Ліцензії відкритого ПЗ");
            WebView webView2 = new WebView(v.getContext());
            webView2.loadUrl("http://x1.nuwm.edu.ua/file/open_sw_rv1.html");
            webView2.setWebViewClient(new WebViewClient() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.about.AboutActivity.2
                @Override // android.webkit.WebViewClient
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }
            });
            builder2.setView(webView2);
            builder2.setNegativeButton("Закрити", new DialogInterface.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.about.AboutActivity$$ExternalSyntheticLambda1
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            builder2.show();
        } else if (v.getId() == R.id.image_done) {
            if (AppPreferences.getInstance().isEnableTestFunction()) {
                ((ImageView) findViewById(R.id.image_done)).setImageResource(R.drawable.ic_sea_lion);
                Toast.makeText(v.getContext(), "Приховані функції уже відкрито", 0).show();
                return;
            }
            int i = this.Easter_Egg + 1;
            this.Easter_Egg = i;
            if (i == 10) {
                ((ImageView) findViewById(R.id.image_done)).setImageResource(R.drawable.ic_beta);
                AppPreferences.getInstance().setUseTestFunction(true);
                if (AppPreferences.getInstance().isEnableTestFunction()) {
                    Toast.makeText(v.getContext(), "Приховані функції уже відкрито", 0).show();
                } else {
                    Toast.makeText(v.getContext(), "Відкрито приховані функції", 0).show();
                }
            } else if (i == 4) {
                Toast.makeText(v.getContext(), "Ще трішки..", 0).show();
            } else if (i == 7) {
                Toast.makeText(v.getContext(), "Уже майже..", 0).show();
            }
        } else if (v.getId() == R.id.developer) {
            new CustomTabsIntent.Builder().build().launchUrl(v.getContext(), Uri.parse("https://t.me/m_myro"));
        } else if (v.getId() == R.id.version) {
            AlertDialog.Builder builder3 = new AlertDialog.Builder(v.getContext());
            WebView webView3 = new WebView(v.getContext());
            webView3.getSettings().setCacheMode(2);
            webView3.loadUrl("http://app.nuwm.edu.ua/app/log.html?v=112");
            webView3.getSettings().setJavaScriptEnabled(true);
            webView3.setWebViewClient(new WebViewClient() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.about.AboutActivity.3
                @Override // android.webkit.WebViewClient
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }
            });
            builder3.setView(webView3);
            builder3.setNegativeButton("Закрити", new DialogInterface.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.about.AboutActivity$$ExternalSyntheticLambda2
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i2) {
                    dialogInterface.dismiss();
                }
            });
            builder3.show();
        }
        AppPreferences.getInstance().isEnableTestFunction();
    }

    public void navigateToDebugActivity(View view) {
        startActivity(new Intent(this, (Class<?>) DeveloperActivity.class));
    }

    public void ITOnClick(View view) {
        DepartmentProfileActivity.startById(this, 20000009);
    }

    private int getMyEmoji() {
        if (AppDataManager.getInstance().isNuwmUser()) {
            AppDataManager.getInstance().getNuwmUser().getId();
            return 0;
        }
        Settings.Secure.getString(getContentResolver(), "android_id").toString().hashCode();
        return 0;
    }
}
