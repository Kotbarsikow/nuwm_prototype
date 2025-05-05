package com.m_myr.nuwm.nuwmschedule.data.repositories;

import com.m_myr.nuwm.nuwmschedule.data.models.Lesson;
import com.m_myr.nuwm.nuwmschedule.data.models.TimetableDay;
import com.m_myr.nuwm.nuwmschedule.data.models.TimetableIdentifier;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.TimetableStorage;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public class TimetableMemoryStorage implements TimetableStorage {
    private String identifier;
    private HashMap<Integer, TimetableDay> localData = new HashMap<>();

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.TimetableStorage
    public /* synthetic */ Lesson getLesson(Date date, String str) {
        return TimetableStorage.CC.$default$getLesson(this, date, str);
    }

    private TimetableMemoryStorage(String identifier) {
        this.identifier = identifier;
    }

    public static TimetableStorage getInstance(TimetableIdentifier identifier) {
        return new TimetableMemoryStorage(identifier.getValue());
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.TimetableStorage
    public void clear() {
        this.localData.clear();
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.TimetableStorage
    public void put(Map<Integer, TimetableDay> newData) {
        this.localData.putAll(newData);
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.TimetableStorage
    public TimetableDay get(int day) {
        return this.localData.get(Integer.valueOf(day));
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.TimetableStorage
    public HashMap<Integer, TimetableDay> getData() {
        return this.localData;
    }
}
