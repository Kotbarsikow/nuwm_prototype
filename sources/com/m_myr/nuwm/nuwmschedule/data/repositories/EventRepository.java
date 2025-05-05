package com.m_myr.nuwm.nuwmschedule.data.repositories;

import android.util.Log;
import com.google.gson.reflect.TypeToken;
import com.m_myr.nuwm.nuwmschedule.data.models.Event;
import com.m_myr.nuwm.nuwmschedule.domain.App;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes2.dex */
public class EventRepository {
    private static HashMap<String, Event> eventMap;
    static final ExecutorService tpe = Executors.newSingleThreadExecutor();

    public static Event getEventById(String uid) {
        return safeGet().get(uid);
    }

    public static boolean loadFromCache() {
        if (eventMap != null) {
            return true;
        }
        try {
            Log.e("EventRepository", "loadFromCache start ");
            HashMap<String, Event> hashMap = (HashMap) Utils.getGsonTimetable().fromJson(new BufferedReader(new InputStreamReader(new FileInputStream(new File(App.getInstance().getCacheDir(), App.getInstance().getCurrentFileName("EventCache.json"))))), new TypeToken<HashMap<String, Event>>() { // from class: com.m_myr.nuwm.nuwmschedule.data.repositories.EventRepository.1
                AnonymousClass1() {
                }
            }.getType());
            eventMap = hashMap;
            if (hashMap == null) {
                throw new NullPointerException();
            }
            Log.e("EventRepository", "loadFromCache end " + eventMap.size());
            return true;
        } catch (Exception unused) {
            Log.e("EventRepository", "loadFromCache fail ");
            return false;
        }
    }

    /* renamed from: com.m_myr.nuwm.nuwmschedule.data.repositories.EventRepository$1 */
    class AnonymousClass1 extends TypeToken<HashMap<String, Event>> {
        AnonymousClass1() {
        }
    }

    public static synchronized HashMap<String, Event> safeGet() {
        HashMap<String, Event> hashMap;
        synchronized (EventRepository.class) {
            if (!loadFromCache()) {
                eventMap = new HashMap<>();
            }
            hashMap = eventMap;
        }
        return hashMap;
    }

    public static HashMap<String, Event> getEventMap() {
        return safeGet();
    }

    public static synchronized void put(HashMap<String, Event> newEvents) {
        synchronized (EventRepository.class) {
            if (newEvents == null) {
                return;
            }
            safeGet().putAll(newEvents);
            saveCacheInBackground();
        }
    }

    /* renamed from: com.m_myr.nuwm.nuwmschedule.data.repositories.EventRepository$2 */
    class AnonymousClass2 implements Runnable {
        AnonymousClass2() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                Log.e("EventRepository", "saveCacheInBackground start " + EventRepository.safeGet().size());
                FileOutputStream fileOutputStream = new FileOutputStream(new File(App.getInstance().getCacheDir(), App.getInstance().getCurrentFileName("EventCache.json")));
                fileOutputStream.write(Utils.getGsonTimetable().toJson(EventRepository.safeGet()).getBytes());
                fileOutputStream.close();
                Log.e("EventRepository", "saveCacheInBackground saved " + EventRepository.safeGet().size());
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("EventRepository", "saveCacheInBackground fail  ");
            }
        }
    }

    private static void saveCacheInBackground() {
        tpe.submit(new Runnable() { // from class: com.m_myr.nuwm.nuwmschedule.data.repositories.EventRepository.2
            AnonymousClass2() {
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    Log.e("EventRepository", "saveCacheInBackground start " + EventRepository.safeGet().size());
                    FileOutputStream fileOutputStream = new FileOutputStream(new File(App.getInstance().getCacheDir(), App.getInstance().getCurrentFileName("EventCache.json")));
                    fileOutputStream.write(Utils.getGsonTimetable().toJson(EventRepository.safeGet()).getBytes());
                    fileOutputStream.close();
                    Log.e("EventRepository", "saveCacheInBackground saved " + EventRepository.safeGet().size());
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("EventRepository", "saveCacheInBackground fail  ");
                }
            }
        });
    }
}
