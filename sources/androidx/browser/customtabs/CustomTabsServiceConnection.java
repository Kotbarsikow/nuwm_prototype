package androidx.browser.customtabs;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.customtabs.ICustomTabsService;

/* loaded from: classes.dex */
public abstract class CustomTabsServiceConnection implements ServiceConnection {
    private Context mApplicationContext;

    public abstract void onCustomTabsServiceConnected(ComponentName name, CustomTabsClient client);

    void setApplicationContext(Context context) {
        this.mApplicationContext = context;
    }

    Context getApplicationContext() {
        return this.mApplicationContext;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName name, IBinder service) {
        if (this.mApplicationContext == null) {
            throw new IllegalStateException("Custom Tabs Service connected before an applicationcontext has been provided.");
        }
        onCustomTabsServiceConnected(name, new CustomTabsClient(ICustomTabsService.Stub.asInterface(service), name, this.mApplicationContext) { // from class: androidx.browser.customtabs.CustomTabsServiceConnection.1
            AnonymousClass1(ICustomTabsService service2, ComponentName name2, Context applicationContext) {
                super(service2, name2, applicationContext);
            }
        });
    }

    /* renamed from: androidx.browser.customtabs.CustomTabsServiceConnection$1 */
    class AnonymousClass1 extends CustomTabsClient {
        AnonymousClass1(ICustomTabsService service2, ComponentName name2, Context applicationContext) {
            super(service2, name2, applicationContext);
        }
    }
}
