package com.m_myr.nuwm.nuwmschedule.ui.framents.profile.employee;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.m_myr.nuwm.nuwmschedule.domain.App;
import com.m_myr.nuwm.nuwmschedule.domain.LegacyRepositoryPresenter;

/* loaded from: classes2.dex */
public class EmployeeProfilePresenterCompat extends LegacyRepositoryPresenter<EmployeeProfileOwner> {
    boolean isRegisteredReceiver;
    private BroadcastReceiver networkStateReceiver;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.m_myr.nuwm.nuwmschedule.domain.LifecyclePresenter
    public void onInit(EmployeeProfileOwner view) {
    }

    public EmployeeProfilePresenterCompat(EmployeeProfileOwner view) {
        super(view, true);
        this.networkStateReceiver = new BroadcastReceiver() { // from class: com.m_myr.nuwm.nuwmschedule.ui.framents.profile.employee.EmployeeProfilePresenterCompat.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                EmployeeProfilePresenterCompat.this.onReceiveNetwork(((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo());
            }
        };
    }

    protected void registerInternetReceiver() {
        this.isRegisteredReceiver = true;
        App.getInstance().registerReceiver(this.networkStateReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onReceiveNetwork(NetworkInfo activeNetworkInfo) {
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return;
        }
        this.isRegisteredReceiver = false;
        App.getInstance().unregisterReceiver(this.networkStateReceiver);
        onInit();
    }
}
