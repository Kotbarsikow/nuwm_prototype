package com.m_myr.nuwm.nuwmschedule.ui.framents.profile.student;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.m_myr.nuwm.nuwmschedule.data.repositories.APIOldObjectListener;
import com.m_myr.nuwm.nuwmschedule.domain.App;
import com.m_myr.nuwm.nuwmschedule.domain.LegacyRepositoryPresenter;
import com.m_myr.nuwm.nuwmschedule.network.APIMethod;
import com.m_myr.nuwm.nuwmschedule.network.ErrorResponse;
import com.m_myr.nuwm.nuwmschedule.network.models.ElevationUpdate;

/* loaded from: classes2.dex */
public class StudentProfilePresenterCompat extends LegacyRepositoryPresenter<StudentProfileOwner> {
    boolean isRegisteredReceiver;
    private BroadcastReceiver networkStateReceiver;

    public StudentProfilePresenterCompat(StudentProfileOwner view) {
        super(view, true);
        this.networkStateReceiver = new BroadcastReceiver() { // from class: com.m_myr.nuwm.nuwmschedule.ui.framents.profile.student.StudentProfilePresenterCompat.2
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                StudentProfilePresenterCompat.this.onReciveNetwork(((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo());
            }
        };
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.LifecyclePresenter
    public void onInit(final StudentProfileOwner view) {
        super.attachView(view);
        getRepository().call(APIMethod.getElevationUpdate()).async(new APIOldObjectListener<ElevationUpdate>(ElevationUpdate.class, "update") { // from class: com.m_myr.nuwm.nuwmschedule.ui.framents.profile.student.StudentProfilePresenterCompat.1
            @Override // com.m_myr.nuwm.nuwmschedule.data.repositories.APIOldObjectListener
            protected void onError(ErrorResponse response) {
                if (response.getCode() == -8) {
                    StudentProfilePresenterCompat.this.registerInternerReceiver();
                }
            }

            @Override // com.m_myr.nuwm.nuwmschedule.data.repositories.APIOldObjectListener
            public void onSuccessData(ElevationUpdate data) {
                if (data.getTime() == null) {
                    view.setTextLastMark("Немає нових оцінок");
                } else if (data.getValue() == 0.0f) {
                    view.setTextLastMark("Остання зміна " + data.getDate() + "\n" + data.getValueStr() + ", " + data.getSubject());
                } else {
                    view.setTextLastMark("Остання оцінка " + data.getDate() + "\n" + data.getValue() + " бали, " + data.getSubject());
                }
                view.setTextAvg("Середній бал за весь період - " + String.format("%.2f", Float.valueOf(data.getAvg())));
            }
        });
    }

    protected void registerInternerReceiver() {
        this.isRegisteredReceiver = true;
        App.getInstance().registerReceiver(this.networkStateReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onReciveNetwork(NetworkInfo activeNetworkInfo) {
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return;
        }
        this.isRegisteredReceiver = false;
        App.getInstance().unregisterReceiver(this.networkStateReceiver);
        onInit();
    }
}
