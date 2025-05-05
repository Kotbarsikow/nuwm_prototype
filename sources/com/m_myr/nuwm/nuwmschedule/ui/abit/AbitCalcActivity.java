package com.m_myr.nuwm.nuwmschedule.ui.abit;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.ui.activities.base.BaseStateActivity;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class AbitCalcActivity extends BaseStateActivity {
    private ArrayList<String> History = new ArrayList<>();
    View viewMain;
    WebView webView;

    public boolean goBack() {
        return false;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.abit_inst_view);
        attachToolbar();
        setTitle("Калькулятор ЗНО");
        WebView webView = (WebView) findViewById(R.id.webview);
        this.webView = webView;
        webView.getSettings().setJavaScriptEnabled(true);
        this.webView.getSettings().setAllowContentAccess(true);
        this.webView.getSettings().setAllowFileAccess(true);
        this.webView.getSettings().setDatabaseEnabled(true);
        this.webView.getSettings().setBlockNetworkImage(true);
        this.webView.loadUrl("https://start.nuwm.edu.ua/calc");
        this.webView.setWebViewClient(new WebViewClient() { // from class: com.m_myr.nuwm.nuwmschedule.ui.abit.AbitCalcActivity.1
            @Override // android.webkit.WebViewClient
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                AbitCalcActivity.this.History.add(url);
                if (url.contains(".PDF")) {
                    AbitCalcActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(url)));
                    return;
                }
                if (url.contains("/instytuty") || url.contains("/nni-")) {
                    if (url.contains("/instytuty/")) {
                        return;
                    }
                    url.contains("/nni-");
                } else {
                    if (url.contains("edu.ua/osvitni-prohramy/item") || url.contains("bakalavr/item/") || url.contains("u.ua/bakalavr") || url.contains("http://wiki.nuwm.edu.ua/index.php/")) {
                        return;
                    }
                    super.onPageStarted(view, url, favicon);
                }
            }

            @Override // android.webkit.WebViewClient
            public void onPageFinished(WebView view, String url) {
                view.loadUrl("javascript:(function() { var element = document.getElementById(\"footer\");element.style.display = 'none';var element2 = document.getElementsByClassName('grid-block')[0];element2.style.padding = \"0px 0px 0px 0px\";var element3 = document.getElementsByClassName('zno-calcs_content-inner')[0];element3.style.margin = \"0px 0px 0px 0px\";})()");
            }
        });
    }
}
