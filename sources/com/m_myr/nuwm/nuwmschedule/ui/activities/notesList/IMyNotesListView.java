package com.m_myr.nuwm.nuwmschedule.ui.activities.notesList;

import androidx.lifecycle.LifecycleOwner;
import com.m_myr.nuwm.nuwmschedule.data.models.Note;
import java.util.List;

/* loaded from: classes2.dex */
public interface IMyNotesListView extends LifecycleOwner {
    void updateAll(List<Note> notes);
}
