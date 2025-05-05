package com.m_myr.nuwm.nuwmschedule.data.models;

import com.google.gson.annotations.SerializedName;
import com.m_myr.nuwm.nuwmschedule.data.models.ItemTimetableContract;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: classes2.dex */
public class TimetableDay<T extends ItemTimetableContract> {

    @SerializedName("dayOfYear")
    int dayOfYear;

    @SerializedName("lessons")
    ArrayList<T> lessons = new ArrayList<>();

    public TimetableDay(ArrayList<T> value, int dayOfYear) {
        this.dayOfYear = dayOfYear;
        addAll(value);
    }

    public int getDayOfYear() {
        return this.dayOfYear;
    }

    public List<? extends ItemTimetableContract> getItems() {
        if (this.lessons == null) {
            this.lessons = new ArrayList<>();
        }
        return this.lessons;
    }

    public void remove(ItemTimetableContract lesson) {
        getItems().remove(lesson);
    }

    public void add(T lesson) {
        if (this.lessons == null) {
            this.lessons = new ArrayList<>();
        }
        this.lessons.add(lesson);
    }

    public void addAll(ArrayList<T> value) {
        if (this.lessons == null) {
            this.lessons = new ArrayList<>();
        }
        if (value != null) {
            this.lessons.addAll(value);
        }
    }

    public void addAllKtx(ArrayList<? extends ItemTimetableContract> value) {
        if (this.lessons == null) {
            this.lessons = new ArrayList<>();
        }
        if (value != null) {
            this.lessons.addAll(value);
        }
    }

    public Set<Integer> getUniqueColors(int color) {
        HashSet hashSet = new HashSet();
        Iterator<T> it = this.lessons.iterator();
        while (it.hasNext()) {
            hashSet.add(Integer.valueOf(it.next().getColor(color)));
        }
        return hashSet;
    }

    public Lesson findLessonByUid(String attachLessonUid) {
        Iterator<T> it = this.lessons.iterator();
        while (it.hasNext()) {
            T next = it.next();
            if (next instanceof Lesson) {
                Lesson lesson = (Lesson) next;
                if (lesson.generateInternalUid().equals(attachLessonUid)) {
                    return lesson;
                }
            }
        }
        return null;
    }

    public boolean isEmpty() {
        ArrayList<T> arrayList = this.lessons;
        return arrayList == null || arrayList.isEmpty();
    }
}
