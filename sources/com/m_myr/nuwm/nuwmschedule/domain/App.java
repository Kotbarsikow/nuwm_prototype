package com.m_myr.nuwm.nuwmschedule.domain;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.provider.Settings;
import androidx.room.Room;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.security.ProviderInstaller;
import com.google.android.material.color.DynamicColors;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;
import com.m_myr.nuwm.nuwmschedule.data.database.AppDatabase;
import com.m_myr.nuwm.nuwmschedule.data.repositories.AppDataManager;
import com.m_myr.nuwm.nuwmschedule.utils.Constant;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.cert.X509Certificate;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/* loaded from: classes2.dex */
public class App extends Application {
    public static int appThemeResource = Constant.getThemeResource(-1);
    public static int currentSemestr = 2;
    private static App instance;
    public static final boolean nextYear;
    Set<String> accounts;
    String currentAccount;
    private AppDatabase database;
    int prevVersion;
    TrustManager[] trustAllCerts = {new X509TrustManager() { // from class: com.m_myr.nuwm.nuwmschedule.domain.App.1
        @Override // javax.net.ssl.X509TrustManager
        public void checkClientTrusted(X509Certificate[] certs, String authType) {
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkServerTrusted(X509Certificate[] certs, String authType) {
        }

        @Override // javax.net.ssl.X509TrustManager
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        AnonymousClass1() {
        }
    }};

    static {
        nextYear = Calendar.getInstance().get(1) == 2025;
    }

    public AppDatabase getDatabase() {
        return this.database;
    }

    public App() {
        instance = this;
    }

    public int getPrevVersion() {
        return this.prevVersion;
    }

    public boolean wasUpdate() {
        return this.prevVersion < 112;
    }

    /* renamed from: com.m_myr.nuwm.nuwmschedule.domain.App$1 */
    class AnonymousClass1 implements X509TrustManager {
        @Override // javax.net.ssl.X509TrustManager
        public void checkClientTrusted(X509Certificate[] certs, String authType) {
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkServerTrusted(X509Certificate[] certs, String authType) {
        }

        @Override // javax.net.ssl.X509TrustManager
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        AnonymousClass1() {
        }
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
        if (DynamicColors.isDynamicColorAvailable()) {
            DynamicColors.applyToActivitiesIfAvailable(this);
        }
        instance = this;
        if (Build.VERSION.SDK_INT < 23) {
            try {
                ProviderInstaller.installIfNeeded(getApplicationContext());
            } catch (GooglePlayServicesNotAvailableException e) {
                e.printStackTrace();
            } catch (GooglePlayServicesRepairableException e2) {
                e2.printStackTrace();
            }
        }
        appThemeResource = Constant.getThemeResource(AppDataManager.getIndependentInstance().getInt("theme", -1));
        SharedPreferences sharedPreferences = getSharedPreferences("account_storage", 0);
        this.currentAccount = sharedPreferences.getString("current", null);
        this.accounts = sharedPreferences.getStringSet("accounts", new HashSet());
        int i = sharedPreferences.getInt("v", 0);
        this.prevVersion = i;
        if (i < 112) {
            migrate(i);
            sharedPreferences.edit().putInt("v", 112).apply();
        }
        if (this.currentAccount != null) {
            this.database = (AppDatabase) Room.databaseBuilder(this, AppDatabase.class, "database_" + this.currentAccount).build();
        }
    }

    private void checkIfLogExist() {
        String str = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openFileInput("stack.trace")));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                str = str + readLine + "\n";
            }
        } catch (FileNotFoundException | IOException unused) {
        }
        deleteFile("stack.trace");
        Intent intent = new Intent("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.EMAIL", new String[]{"mynuwee@nuwm.edu.ua"});
        intent.putExtra("android.intent.extra.TEXT", "\n" + str + "\n");
        intent.putExtra("android.intent.extra.SUBJECT", "Error report");
        intent.addFlags(268435456);
        intent.setType("message/rfc822");
        Intent createChooser = Intent.createChooser(intent, "Title:");
        createChooser.addFlags(268435456);
        startActivity(createChooser);
    }

    public static String getDeviceIdLegacy() {
        return Settings.Secure.getString(getInstance().getContentResolver(), "android_id");
    }

    private void migrate(int prevVersion) {
        if (prevVersion < 54) {
            trimCache();
        }
        if (prevVersion < 64) {
            trimCache();
        }
        if (prevVersion < 67) {
            reLoginAllPersonal();
        }
        if (prevVersion < 104) {
            trimCache();
        }
    }

    private void reLoginAllPersonal() {
        trimCache();
        AppDataManager currentInstance = AppDataManager.getCurrentInstance();
        if (currentInstance != null) {
            currentInstance.forceRequestToUpdates().apply();
        }
    }

    public void trimCache() {
        try {
            File cacheDir = getCacheDir();
            if (cacheDir == null || !cacheDir.isDirectory()) {
                return;
            }
            deleteDir(cacheDir);
        } catch (Exception unused) {
        }
    }

    public boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            for (String str : dir.list()) {
                if (!deleteDir(new File(dir, str))) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

    @Override // android.app.Application, android.content.ComponentCallbacks
    public void onLowMemory() {
        super.onLowMemory();
    }

    public static App getInstance() {
        return instance;
    }

    public String getCurrentAccount() {
        return this.currentAccount;
    }

    public Set<String> getAccounts() {
        return this.accounts;
    }

    public String getCurrentFileName(String file) {
        return this.currentAccount + "_" + file;
    }

    public void subscribeToTopics() {
        Iterator<String> it = AppDataManager.getInstance().getTopics().iterator();
        while (it.hasNext()) {
            FirebaseMessaging.getInstance().subscribeToTopic(it.next());
        }
    }

    public void setCurrentAccount(String email) {
        this.currentAccount = email;
        getSharedPreferences("account_storage", 0).edit().putString("current", this.currentAccount).apply();
        if (this.currentAccount != null) {
            this.database = (AppDatabase) Room.databaseBuilder(this, AppDatabase.class, "database_" + this.currentAccount).build();
            return;
        }
        this.database = null;
    }

    public boolean addAccount(String email) {
        SharedPreferences sharedPreferences = getSharedPreferences("account_storage", 0);
        Set<String> stringSet = sharedPreferences.getStringSet("accounts", new HashSet());
        this.accounts = stringSet;
        if (!stringSet.add(email)) {
            return false;
        }
        sharedPreferences.edit().putStringSet("accounts", this.accounts).apply();
        return true;
    }

    public void removeCurrentAccount() {
        this.accounts.remove(this.currentAccount);
        this.currentAccount = null;
        SharedPreferences sharedPreferences = getSharedPreferences("account_storage", 0);
        sharedPreferences.edit().putStringSet("accounts", this.accounts);
        sharedPreferences.edit().putString("current", null).apply();
        AppDataManager.getInstance().remove();
    }

    public void skipUpdate() {
        this.prevVersion = 112;
    }

    public boolean getCurrentIsAbit() {
        return "abit".equals(this.currentAccount);
    }

    public int getAppTheme() {
        return appThemeResource;
    }
}
