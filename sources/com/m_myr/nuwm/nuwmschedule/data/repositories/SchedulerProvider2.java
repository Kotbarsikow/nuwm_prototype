package com.m_myr.nuwm.nuwmschedule.data.repositories;

import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.google.firebase.messaging.Constants;
import com.hootsuite.nachos.tokenizer.SpanChipTokenizer;
import com.m_myr.nuwm.nuwmschedule.data.models.EventLinks;
import com.m_myr.nuwm.nuwmschedule.data.models.ItemTimetableContract;
import com.m_myr.nuwm.nuwmschedule.data.models.Lesson;
import com.m_myr.nuwm.nuwmschedule.data.models.TimetableDay;
import com.m_myr.nuwm.nuwmschedule.data.models.TimetableIdentifier;
import com.m_myr.nuwm.nuwmschedule.domain.AppPreferences;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.RepositoryProvider;
import com.m_myr.nuwm.nuwmschedule.network.models.ScheduleResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.simmetrics.StringMetric;
import org.simmetrics.metrics.StringMetrics;

/* compiled from: SchedulerProvider2.kt */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J \u0010\u0012\u001a\u0004\u0018\u00010\u00182\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00150\u000e2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J$\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00150\u000e2\u0006\u0010\u0016\u001a\u00020\u00172\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00150\u000eH\u0002J8\u0010\u001c\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00150\u001d2\u0006\u0010\u001e\u001a\u00020\b2\u000e\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020 \u0018\u00010\u001d2\u000e\u0010!\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\"H\u0002J \u0010#\u001a\u0012\u0012\u0004\u0012\u00020\b\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001d0$2\u0006\u0010%\u001a\u00020&H\u0014R\u000e\u0010\u0007\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006'"}, d2 = {"Lcom/m_myr/nuwm/nuwmschedule/data/repositories/SchedulerProvider2;", "Lcom/m_myr/nuwm/nuwmschedule/data/repositories/SchedulerProvider;", "provider", "Lcom/m_myr/nuwm/nuwmschedule/domain/interfaces/RepositoryProvider;", "identifier", "Lcom/m_myr/nuwm/nuwmschedule/data/models/TimetableIdentifier;", "(Lcom/m_myr/nuwm/nuwmschedule/domain/interfaces/RepositoryProvider;Lcom/m_myr/nuwm/nuwmschedule/data/models/TimetableIdentifier;)V", "TIME_10SECONDS_DEVIATION", "", "TIME_1MINUTES_DEVIATION", "TIME_MAX_DEVIATION", "TIME_MIN_DEVIATION", "TIME_SECONDS_DEVIATION", "keyWords", "", "", "getKeyWords", "()Ljava/util/List;", "checkMatch", "", "pair", "Lcom/m_myr/nuwm/nuwmschedule/data/models/ItemTimetableContract;", NotificationCompat.CATEGORY_EVENT, "Lcom/m_myr/nuwm/nuwmschedule/data/models/EventLinks;", "Lcom/m_myr/nuwm/nuwmschedule/data/repositories/Collapse;", "pairs", "findPair", "lessons", "prepareDay", "Lcom/m_myr/nuwm/nuwmschedule/data/models/TimetableDay;", "day", "lessonsTimetable", "Lcom/m_myr/nuwm/nuwmschedule/data/models/Lesson;", "events", "Ljava/util/ArrayList;", "prepareMergeLesson", "Ljava/util/HashMap;", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "Lcom/m_myr/nuwm/nuwmschedule/network/models/ScheduleResponse;", "app_publicReleaseRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class SchedulerProvider2 extends SchedulerProvider {
    private final int TIME_10SECONDS_DEVIATION;
    private final int TIME_1MINUTES_DEVIATION;
    private final int TIME_MAX_DEVIATION;
    private final int TIME_MIN_DEVIATION;
    private final int TIME_SECONDS_DEVIATION;
    private final List<String> keyWords;

    public SchedulerProvider2(RepositoryProvider repositoryProvider, TimetableIdentifier timetableIdentifier) {
        super(repositoryProvider, timetableIdentifier);
        this.TIME_MIN_DEVIATION = 360150;
        this.TIME_MAX_DEVIATION = 660150;
        this.TIME_SECONDS_DEVIATION = 1150;
        this.TIME_10SECONDS_DEVIATION = 11050;
        this.TIME_1MINUTES_DEVIATION = 61050;
        this.keyWords = CollectionsKt.listOfNotNull((Object[]) new String[]{"лекція", "лаб.роб", "лаб.", "(лаб)", "(л)", "(прс)", "(підгр", "практичне заняття", "практ.", "потік", "практичн", ",", "підгр."});
    }

    @Override // com.m_myr.nuwm.nuwmschedule.data.repositories.SchedulerProvider
    protected HashMap<Integer, TimetableDay<?>> prepareMergeLesson(ScheduleResponse data) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (!AppPreferences.getInstance().isTimetableMergesEnabled()) {
            HashMap<Integer, TimetableDay<?>> prepareMergeLesson = super.prepareMergeLesson(data);
            Intrinsics.checkNotNullExpressionValue(prepareMergeLesson, "prepareMergeLesson(...)");
            return prepareMergeLesson;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        HashMap<Integer, TimetableDay<?>> hashMap = new HashMap<>();
        HashMap<Integer, TimetableDay<Lesson>> timetable = data.timetable;
        Intrinsics.checkNotNullExpressionValue(timetable, "timetable");
        Iterator<Map.Entry<Integer, TimetableDay<Lesson>>> it = timetable.entrySet().iterator();
        while (it.hasNext()) {
            Integer key = it.next().getKey();
            Intrinsics.checkNotNull(key);
            linkedHashSet.add(key);
        }
        if (data.mapLinks != null) {
            Map<Integer, ArrayList<EventLinks>> mapLinks = data.mapLinks;
            Intrinsics.checkNotNullExpressionValue(mapLinks, "mapLinks");
            Iterator<Map.Entry<Integer, ArrayList<EventLinks>>> it2 = mapLinks.entrySet().iterator();
            while (it2.hasNext()) {
                Integer key2 = it2.next().getKey();
                Intrinsics.checkNotNull(key2);
                linkedHashSet.add(key2);
            }
        }
        if (data.mapLinks != null) {
            Iterator it3 = linkedHashSet.iterator();
            while (it3.hasNext()) {
                int intValue = ((Number) it3.next()).intValue();
                hashMap.put(Integer.valueOf(intValue), prepareDay(intValue, data.timetable.get(Integer.valueOf(intValue)), data.mapLinks.get(Integer.valueOf(intValue))));
            }
            return hashMap;
        }
        HashMap<Integer, TimetableDay<Lesson>> hashMap2 = data.timetable;
        Intrinsics.checkNotNull(hashMap2, "null cannot be cast to non-null type java.util.HashMap<kotlin.Int, com.m_myr.nuwm.nuwmschedule.data.models.TimetableDay<*>>");
        return hashMap2;
    }

    private final TimetableDay<? extends ItemTimetableContract> prepareDay(int day, TimetableDay<Lesson> lessonsTimetable, ArrayList<EventLinks> events) {
        Collapse checkMatch;
        ArrayList<EventLinks> arrayList = events;
        if (arrayList == null || arrayList.isEmpty()) {
            Intrinsics.checkNotNull(lessonsTimetable);
            return lessonsTimetable;
        }
        if (lessonsTimetable == null || lessonsTimetable.isEmpty()) {
            return new TimetableDay<>(events, day);
        }
        Iterator<EventLinks> it = events.iterator();
        Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
        while (it.hasNext()) {
            EventLinks next = it.next();
            Intrinsics.checkNotNullExpressionValue(next, "next(...)");
            EventLinks eventLinks = next;
            List<? extends ItemTimetableContract> items = lessonsTimetable.getItems();
            Intrinsics.checkNotNullExpressionValue(items, "getItems(...)");
            List<ItemTimetableContract> findPair = findPair(eventLinks, items);
            if (!findPair.isEmpty() && (checkMatch = checkMatch(findPair, eventLinks)) != null) {
                mergeEntity(checkMatch.getEvent(), checkMatch.getItemTimetableContract());
            }
        }
        lessonsTimetable.addAllKtx(events);
        Collections.sort(lessonsTimetable.getItems(), this.dateComparator);
        return lessonsTimetable;
    }

    private final Collapse checkMatch(List<? extends ItemTimetableContract> pairs, EventLinks event) {
        if (pairs.size() == 1) {
            float checkMatch = checkMatch(pairs.get(0), event);
            if (checkMatch > 0.55d) {
                return new Collapse(pairs.get(0), event, checkMatch);
            }
        } else {
            Collapse collapse = null;
            for (ItemTimetableContract itemTimetableContract : pairs) {
                float checkMatch2 = checkMatch(itemTimetableContract, event);
                if (collapse == null || collapse.getCheckMatch() < checkMatch2) {
                    collapse = new Collapse(itemTimetableContract, event, checkMatch(itemTimetableContract, event));
                }
            }
            Intrinsics.checkNotNull(collapse);
            if (collapse.getCheckMatch() > 0.55d) {
                return collapse;
            }
        }
        return null;
    }

    public final List<String> getKeyWords() {
        return this.keyWords;
    }

    private final float checkMatch(ItemTimetableContract pair, EventLinks event) {
        String str;
        String str2;
        String str3;
        float f;
        StringMetric cosineSimilarity = StringMetrics.cosineSimilarity();
        String fastTitle = pair.getFastTitle();
        Intrinsics.checkNotNullExpressionValue(fastTitle, "getFastTitle(...)");
        String lowerCase = fastTitle.toLowerCase();
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
        String fastTitle2 = event.getFastTitle();
        Intrinsics.checkNotNullExpressionValue(fastTitle2, "getFastTitle(...)");
        String lowerCase2 = fastTitle2.toLowerCase();
        Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase()");
        float compare = cosineSimilarity.compare(lowerCase, lowerCase2);
        if (!event.isMeet()) {
            return 0.0f;
        }
        Iterator<T> it = this.keyWords.iterator();
        String str4 = lowerCase2;
        while (it.hasNext()) {
            str4 = StringsKt.replace$default(str4, (String) it.next(), "", false, 4, (Object) null);
        }
        String replace = new Regex("([а-яА-ЯіІїЇєЄ]){1,5}-([\\d]{1,2}[а-яіїє]?)[,]?[ ]?").replace(str4, "");
        float compare2 = StringMetrics.cosineSimilarity().compare(lowerCase, replace);
        if (compare2 < 0.2d) {
            str = "this as java.lang.String).toLowerCase()";
            str2 = lowerCase2;
            if (Math.abs(pair.getStartDate().getTime() - event.getStartDate().getTime()) < this.TIME_10SECONDS_DEVIATION && Math.abs(pair.getEndDate().getTime() - event.getEndDate().getTime()) < this.TIME_10SECONDS_DEVIATION) {
                String fastTitle3 = pair.getFastTitle();
                Intrinsics.checkNotNullExpressionValue(fastTitle3, "getFastTitle(...)");
                List split$default = StringsKt.split$default((CharSequence) fastTitle3, new char[]{SpanChipTokenizer.AUTOCORRECT_SEPARATOR}, false, 0, 6, (Object) null);
                ArrayList arrayList = new ArrayList();
                Iterator it2 = split$default.iterator();
                while (it2.hasNext()) {
                    Character firstOrNull = StringsKt.firstOrNull((String) it2.next());
                    String ch = firstOrNull != null ? firstOrNull.toString() : null;
                    if (ch != null) {
                        arrayList.add(ch);
                    }
                }
                Iterator it3 = arrayList.iterator();
                if (!it3.hasNext()) {
                    throw new UnsupportedOperationException("Empty collection can't be reduced.");
                }
                Object next = it3.next();
                while (it3.hasNext()) {
                    next = ((String) next) + ((String) it3.next());
                }
                String str5 = (String) next;
                if (str5.length() > 2) {
                    String replace2 = new Regex("[^A-Za-z0-9 ]").replace(str5, "");
                    String fastTitle4 = event.getFastTitle();
                    Intrinsics.checkNotNullExpressionValue(fastTitle4, "getFastTitle(...)");
                    String upperCase = replace2.toUpperCase();
                    Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase()");
                    if (StringsKt.contains$default((CharSequence) fastTitle4, (CharSequence) upperCase, false, 2, (Object) null)) {
                        compare2 += 0.25f;
                    }
                }
            } else {
                Log.e("CANCELE", "" + event.getStartDate().getDate() + ", " + compare + '\t' + compare2 + "\t[" + pair.getFastTitle() + "]\t[" + event.getFastTitle() + "]\t[" + replace + ']');
                return compare;
            }
        } else {
            str = "this as java.lang.String).toLowerCase()";
            str2 = lowerCase2;
        }
        double d = compare2;
        if (d > 1.5d) {
            Log.e("CANCELE", "" + event.getStartDate().getDate() + ", " + compare + '\t' + compare2 + "\t[" + pair.getFastTitle() + "]\t[" + event.getFastTitle() + "]\t[" + replace + ']');
            return compare2;
        }
        if (d > 0.4d && Math.abs(pair.getStartDate().getTime() - event.getStartDate().getTime()) < this.TIME_10SECONDS_DEVIATION) {
            compare2 += 0.2f;
        }
        if (Math.abs(pair.getEndDate().getTime() - event.getEndDate().getTime()) < this.TIME_1MINUTES_DEVIATION) {
            compare2 *= 1.31f;
        }
        if (compare2 < 0.6d) {
            if (AppDataManager.getInstance().isStudent()) {
                String groupName = AppDataManager.getInstance().getStudent().getGroupName();
                Intrinsics.checkNotNullExpressionValue(groupName, "getGroupName(...)");
                String lowerCase3 = groupName.toLowerCase();
                str3 = str;
                Intrinsics.checkNotNullExpressionValue(lowerCase3, str3);
                if (StringsKt.contains$default((CharSequence) str2, (CharSequence) lowerCase3, false, 2, (Object) null)) {
                    compare2 += 0.4f;
                }
            } else {
                str3 = str;
            }
            String str6 = str2;
            String type = pair.getType();
            Intrinsics.checkNotNullExpressionValue(type, "getType(...)");
            if (StringsKt.contains$default((CharSequence) str6, (CharSequence) type, false, 2, (Object) null)) {
                compare2 *= 1.56f;
            } else {
                String type2 = pair.getType();
                Intrinsics.checkNotNullExpressionValue(type2, "getType(...)");
                String lowerCase4 = type2.toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase4, str3);
                if (StringsKt.contains$default((CharSequence) lowerCase4, (CharSequence) "лабораторна", false, 2, (Object) null)) {
                    if (!StringsKt.contains$default((CharSequence) str6, (CharSequence) "лаб.", false, 2, (Object) null)) {
                        f = StringsKt.contains$default((CharSequence) str6, (CharSequence) "лабораторна", false, 2, (Object) null) ? 1.76f : 1.56f;
                    }
                    compare2 *= f;
                }
            }
            if (event.getStartDate().getDate() == 26 && compare2 < 0.6d) {
                int indexOf$default = StringsKt.indexOf$default((CharSequence) lowerCase, SpanChipTokenizer.AUTOCORRECT_SEPARATOR, 0, false, 6, (Object) null);
                if (indexOf$default == -1) {
                    indexOf$default = lowerCase.length() - 1;
                }
                if (indexOf$default > 0) {
                    String substring = lowerCase.substring(0, indexOf$default);
                    Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
                    if (substring.length() > 3 && StringsKt.contains$default((CharSequence) str6, (CharSequence) substring, false, 2, (Object) null)) {
                        compare2 *= 1.8f;
                    }
                }
            }
        }
        Log.e("COMPARE", "" + event.getStartDate().getDate() + ", " + compare + '\t' + compare2 + "\t[" + pair.getFastTitle() + "]\t[" + event.getFastTitle() + "]\t[" + replace + ']');
        return compare2;
    }

    private final List<ItemTimetableContract> findPair(EventLinks event, List<? extends ItemTimetableContract> lessons) {
        long time = event.getStartDate().getTime() - this.TIME_MAX_DEVIATION;
        long time2 = event.getStartDate().getTime() + this.TIME_MIN_DEVIATION;
        ArrayList arrayList = new ArrayList();
        for (Object obj : lessons) {
            ItemTimetableContract itemTimetableContract = (ItemTimetableContract) obj;
            if (itemTimetableContract.getStartDate().getTime() > time && itemTimetableContract.getStartDate().getTime() < time2) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }
}
