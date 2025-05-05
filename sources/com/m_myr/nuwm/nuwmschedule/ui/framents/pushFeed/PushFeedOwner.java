package com.m_myr.nuwm.nuwmschedule.ui.framents.pushFeed;

import androidx.lifecycle.LifecycleOwner;
import com.m_myr.nuwm.nuwmschedule.data.models.feed.PostMessage;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public interface PushFeedOwner extends LifecycleOwner {
    void setData(ArrayList<PostMessage> data);
}
