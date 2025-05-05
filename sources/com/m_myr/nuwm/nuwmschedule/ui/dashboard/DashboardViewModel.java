package com.m_myr.nuwm.nuwmschedule.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/* loaded from: classes2.dex */
public class DashboardViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public DashboardViewModel() {
        MutableLiveData<String> mutableLiveData = new MutableLiveData<>();
        this.mText = mutableLiveData;
        mutableLiveData.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return this.mText;
    }
}
