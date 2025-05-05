package com.m_myr.nuwm.nuwmschedule.ui.activities.notes;

import androidx.lifecycle.LifecycleOwner;
import com.m_myr.nuwm.nuwmschedule.data.models.Note;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.IntentProvider;

/* loaded from: classes2.dex */
public interface IMyNotesView extends LifecycleOwner, IntentProvider {
    void attachListener();

    void finish();

    void setChangeResult(int position, Note note);

    void setData(Note note);

    void setInsertResult(Note note);

    void setRemoveResult(int position, Note note);
}
