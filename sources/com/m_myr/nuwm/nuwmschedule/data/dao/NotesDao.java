package com.m_myr.nuwm.nuwmschedule.data.dao;

import com.m_myr.nuwm.nuwmschedule.data.models.Note;
import java.util.List;

/* loaded from: classes2.dex */
public interface NotesDao {
    void delete(Note note);

    List<Note> getAll();

    void insert(Note note);

    void update(Note note);
}
