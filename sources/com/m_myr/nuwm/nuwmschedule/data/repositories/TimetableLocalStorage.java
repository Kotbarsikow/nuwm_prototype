package com.m_myr.nuwm.nuwmschedule.data.repositories;

import android.util.Log;
import com.google.gson.reflect.TypeToken;
import com.m_myr.nuwm.nuwmschedule.data.models.Lesson;
import com.m_myr.nuwm.nuwmschedule.data.models.TimetableDay;
import com.m_myr.nuwm.nuwmschedule.data.models.TimetableIdentifier;
import com.m_myr.nuwm.nuwmschedule.domain.App;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.TimetableStorage;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;
import j$.util.DesugarCollections;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes2.dex */
public class TimetableLocalStorage implements TimetableStorage {
    private static TimetableLocalStorage instance;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private String identifier;
    private HashMap<Integer, TimetableDay> localData;

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.TimetableStorage
    public /* synthetic */ Lesson getLesson(Date date, String str) {
        return TimetableStorage.CC.$default$getLesson(this, date, str);
    }

    private TimetableLocalStorage(String identifier) {
        this.identifier = identifier;
        instance = this;
        getSelfSafe();
    }

    public static TimetableLocalStorage getInstance(TimetableIdentifier identifier) {
        if (instance == null) {
            createInstance(identifier.getValue());
        }
        if (!instance.identifier.equals(identifier.getValue())) {
            createInstance(identifier.getValue());
        }
        return instance;
    }

    private static void createInstance(String identifier) {
        instance = new TimetableLocalStorage(identifier);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized HashMap<Integer, TimetableDay> getSelfSafe() {
        if (!loadFromCache()) {
            this.localData = new HashMap<>();
        }
        return this.localData;
    }

    private synchronized void saveCacheInBackground() {
        this.executorService.submit(new Runnable() { // from class: com.m_myr.nuwm.nuwmschedule.data.repositories.TimetableLocalStorage.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Log.e("SchedulerRepository", "saveCacheInBackground start " + TimetableLocalStorage.this.getSelfSafe().size());
                    Map synchronizedMap = DesugarCollections.synchronizedMap(TimetableLocalStorage.this.getSelfSafe());
                    synchronized (synchronizedMap) {
                        FileOutputStream fileOutputStream = new FileOutputStream(new File(App.getInstance().getCacheDir(), App.getInstance().getCurrentFileName("SchedulerCache.json")));
                        fileOutputStream.write(Utils.getGsonTimetable().toJson(synchronizedMap).getBytes());
                        fileOutputStream.close();
                    }
                    Log.e("SchedulerRepository", "saveCacheInBackground saved " + TimetableLocalStorage.this.getSelfSafe().size());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public synchronized boolean loadFromCache() {
        if (this.localData != null) {
            return true;
        }
        Log.e("SchedulerRepository", " loadFromCache");
        try {
            Log.e("SchedulerRepository", "loadFromCache start");
            HashMap<Integer, TimetableDay> hashMap = (HashMap) Utils.getGsonTimetable().fromJson(new BufferedReader(new InputStreamReader(new FileInputStream(new File(App.getInstance().getCacheDir(), App.getInstance().getCurrentFileName("SchedulerCache.json"))))), new TypeToken<HashMap<Integer, TimetableDay>>() { // from class: com.m_myr.nuwm.nuwmschedule.data.repositories.TimetableLocalStorage.2
            }.getType());
            this.localData = hashMap;
            if (hashMap == null) {
                throw new NullPointerException();
            }
            Log.e("SchedulerRepository", "loadFromCache end " + this.localData.size());
            return true;
        } catch (Exception unused) {
            Log.e("SchedulerRepository ", "loadFromCache fail ");
            return false;
        }
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.TimetableStorage
    public void clear() {
        getSelfSafe().clear();
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.TimetableStorage
    public void put(Map<Integer, TimetableDay> newData) {
        getSelfSafe().putAll(newData);
        saveCacheInBackground();
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.TimetableStorage
    public TimetableDay get(int day) {
        return getSelfSafe().get(Integer.valueOf(day));
    }

    @Override // com.m_myr.nuwm.nuwmschedule.domain.interfaces.TimetableStorage
    public HashMap<Integer, TimetableDay> getData() {
        return getSelfSafe();
    }
}
