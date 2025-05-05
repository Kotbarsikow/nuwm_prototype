package j$.time.format;

import j$.time.format.DateTimeFormatterBuilder;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
final class DateTimeTextProvider$LocaleStore {
    private final Map valueTextMap;

    DateTimeTextProvider$LocaleStore(Map map) {
        Comparator comparator;
        Comparator comparator2;
        this.valueTextMap = map;
        HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        for (Map.Entry entry : map.entrySet()) {
            HashMap hashMap2 = new HashMap();
            for (Map.Entry entry2 : ((Map) entry.getValue()).entrySet()) {
                String str = (String) entry2.getValue();
                String str2 = (String) entry2.getValue();
                Long l = (Long) entry2.getKey();
                int i = DateTimeFormatterBuilder.AnonymousClass1.$r8$clinit;
                hashMap2.put(str, new AbstractMap.SimpleImmutableEntry(str2, l));
            }
            ArrayList arrayList2 = new ArrayList(hashMap2.values());
            comparator2 = DateTimeFormatterBuilder.AnonymousClass1.COMPARATOR;
            Collections.sort(arrayList2, comparator2);
            hashMap.put((TextStyle) entry.getKey(), arrayList2);
            arrayList.addAll(arrayList2);
            hashMap.put(null, arrayList);
        }
        comparator = DateTimeFormatterBuilder.AnonymousClass1.COMPARATOR;
        Collections.sort(arrayList, comparator);
    }

    final String getText(long j, TextStyle textStyle) {
        Map map = (Map) this.valueTextMap.get(textStyle);
        if (map != null) {
            return (String) map.get(Long.valueOf(j));
        }
        return null;
    }
}
