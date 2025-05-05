package com.m_myr.nuwm.nuwmschedule.ui.activities.sendPush;

import androidx.lifecycle.LifecycleOwner;
import com.m_myr.nuwm.nuwmschedule.data.models.Lesson;
import com.m_myr.nuwm.nuwmschedule.data.models.Recipient;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.IntentProvider;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public interface SendPushOwner extends LifecycleOwner, IntentProvider {
    void attachLesson(Lesson lesson);

    void showAlertRecipients(List<String> selectedRecipient, ArrayList<Recipient> recipients);

    void showInfo(String s);
}
