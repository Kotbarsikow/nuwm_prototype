package com.m_myr.nuwm.nuwmschedule.ui.activities.department;

import androidx.lifecycle.LifecycleOwner;
import com.google.firebase.messaging.Constants;
import com.m_myr.nuwm.nuwmschedule.data.models.DepartmentProfile;
import com.m_myr.nuwm.nuwmschedule.data.models.NewsViewItem;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.BaseStateView;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.IntentProvider;
import kotlin.Metadata;

/* compiled from: DepartmentProfileView.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u001a\u0010\b\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000bH&¨\u0006\f"}, d2 = {"Lcom/m_myr/nuwm/nuwmschedule/ui/activities/department/DepartmentProfileView;", "Landroidx/lifecycle/LifecycleOwner;", "Lcom/m_myr/nuwm/nuwmschedule/domain/interfaces/BaseStateView;", "Lcom/m_myr/nuwm/nuwmschedule/domain/interfaces/IntentProvider;", "setData", "", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "Lcom/m_myr/nuwm/nuwmschedule/data/models/DepartmentProfile;", "setNewsData", "Lcom/m_myr/nuwm/nuwmschedule/data/models/NewsViewItem;", "categoryId", "", "app_publicReleaseRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface DepartmentProfileView extends LifecycleOwner, BaseStateView, IntentProvider {
    void setData(DepartmentProfile data);

    void setNewsData(NewsViewItem data, int categoryId);
}
