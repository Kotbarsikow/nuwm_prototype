package com.m_myr.nuwm.nuwmschedule.utils;

import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.util.TypedValue;
import android.widget.Toast;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.browser.customtabs.CustomTabsService;
import androidx.core.net.MailTo;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.domain.App;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class LinksResolver {
    public static void startOnChrome(Context context, String url) {
        new TypedValue();
        int color = context.getResources().getColor(R.color.white);
        try {
            String customTabsPackages = getCustomTabsPackages(context);
            if (customTabsPackages == null) {
                throw new ActivityNotFoundException();
            }
            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
            CustomTabsIntent build = builder.build();
            build.intent.setPackage(customTabsPackages);
            build.intent.addFlags(67108864);
            builder.setShowTitle(true);
            builder.setToolbarColor(color);
            builder.enableUrlBarHiding();
            build.launchUrl(context, Uri.parse(url));
        } catch (ActivityNotFoundException unused) {
            startOnBrowser(null, context, url);
        }
    }

    private static void startOnBrowser(Intent intent, Context context, String url) {
        if (url.contains(MailTo.MAILTO_SCHEME)) {
            Intent intent2 = new Intent("android.intent.action.SENDTO");
            intent2.setData(Uri.parse(url));
            context.startActivity(Intent.createChooser(intent2, "Надіслати листа"));
            return;
        }
        ResolveInfo isIntentAvailable = isIntentAvailable(context, "android.intent.action.VIEW", Uri.parse(url), null);
        if (isIntentAvailable == null) {
            Toast.makeText(context, "Неможливо відкрити посилання.", 0).show();
            return;
        }
        ActivityInfo activityInfo = isIntentAvailable.activityInfo;
        ComponentName componentName = new ComponentName(activityInfo.applicationInfo.packageName, activityInfo.name);
        if (intent == null) {
            intent = new Intent("android.intent.action.VIEW", Uri.parse(url));
        }
        intent.setComponent(componentName);
        context.startActivity(intent);
    }

    private static ResolveInfo isIntentAvailable(Context context, String action, Uri uri, String mimeType) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("http://www.google.com"));
        List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 65536);
        for (int i = 0; i < queryIntentActivities.size(); i++) {
            if (queryIntentActivities.get(i).activityInfo.name.equals("com.m_myr.nuwm.nuwmschedule.utilsLinkOpener")) {
                queryIntentActivities.remove(i);
            }
        }
        if (queryIntentActivities.size() == 0) {
            return null;
        }
        queryIntentActivities.get(0);
        return queryIntentActivities.get(0);
    }

    public static String getCustomTabsPackages(Context context) {
        PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse("http://www.example.com")), 0);
        ArrayList arrayList = new ArrayList();
        for (ResolveInfo resolveInfo : queryIntentActivities) {
            Intent intent = new Intent();
            intent.setAction(CustomTabsService.ACTION_CUSTOM_TABS_CONNECTION);
            intent.setPackage(resolveInfo.activityInfo.packageName);
            if (packageManager.resolveService(intent, 0) != null) {
                arrayList.add(resolveInfo.activityInfo.packageName);
            }
        }
        if (arrayList.size() > 0) {
            return (String) arrayList.get(0);
        }
        return null;
    }

    public static void ActionView(Context context, String link) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(link));
            context.startActivity(intent);
        } catch (Exception unused) {
            startOnChrome(context, link);
        }
    }

    public static void openEmail(Context context, String email) {
        Intent intent = new Intent("android.intent.action.SENDTO");
        intent.setData(Uri.parse(MailTo.MAILTO_SCHEME + email));
        context.startActivity(Intent.createChooser(intent, "Надіслати email..."));
    }

    public static void copyToClipboard(String text) {
        ((ClipboardManager) App.getInstance().getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, text));
        Toast.makeText(App.getInstance(), "Скопійовано", 0).show();
    }

    public static void openInAppOrChrome(Context context, String url) {
        startOnChrome(context, url);
    }

    public static void openById(Context context, int safeToInt) {
        startOnChrome(context, "https://app.nuwm.edu.ua/api/v6/getById.php?id=" + safeToInt + "&extended=true");
    }

    public static Intent getWebIntent(String url) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(url));
        intent.addFlags(268435456);
        return intent;
    }
}
