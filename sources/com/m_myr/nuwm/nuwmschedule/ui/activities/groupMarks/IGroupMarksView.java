package com.m_myr.nuwm.nuwmschedule.ui.activities.groupMarks;

import com.m_myr.nuwm.nuwmschedule.domain.interfaces.ActivityView;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.DefaultErrorToast;
import com.m_myr.nuwm.nuwmschedule.network.models.ElevationUpdate;
import com.m_myr.nuwm.nuwmschedule.network.models.GroupMarksResponse;

/* loaded from: classes2.dex */
public interface IGroupMarksView extends ActivityView, DefaultErrorToast {
    void createTable(GroupMarksResponse data);

    void showLoading();

    void showUpdate(ElevationUpdate data);
}
