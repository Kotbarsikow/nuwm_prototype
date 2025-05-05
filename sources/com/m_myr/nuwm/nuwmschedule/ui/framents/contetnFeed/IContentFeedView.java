package com.m_myr.nuwm.nuwmschedule.ui.framents.contetnFeed;

import androidx.lifecycle.LifecycleOwner;
import com.m_myr.nuwm.nuwmschedule.data.models.NewsViewItem;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public interface IContentFeedView extends LifecycleOwner {
    void updateList(ArrayList<NewsViewItem> arrayList);
}
