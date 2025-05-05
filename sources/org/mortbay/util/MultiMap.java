package org.mortbay.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class MultiMap extends HashMap implements Cloneable {
    public MultiMap() {
    }

    public MultiMap(int i) {
        super(i);
    }

    public MultiMap(Map map) {
        super((map.size() * 3) / 2);
        putAll(map);
    }

    public List getValues(Object obj) {
        return LazyList.getList(super.get(obj), true);
    }

    public Object getValue(Object obj, int i) {
        Object obj2 = super.get(obj);
        if (i == 0 && LazyList.size(obj2) == 0) {
            return null;
        }
        return LazyList.get(obj2, i);
    }

    public String getString(Object obj) {
        String stringBuffer;
        Object obj2 = super.get(obj);
        int size = LazyList.size(obj2);
        if (size == 0) {
            return null;
        }
        if (size == 1) {
            Object obj3 = LazyList.get(obj2, 0);
            if (obj3 == null) {
                return null;
            }
            return obj3.toString();
        }
        StringBuffer stringBuffer2 = new StringBuffer(128);
        synchronized (stringBuffer2) {
            for (int i = 0; i < LazyList.size(obj2); i++) {
                Object obj4 = LazyList.get(obj2, i);
                if (obj4 != null) {
                    if (stringBuffer2.length() > 0) {
                        stringBuffer2.append(',');
                    }
                    stringBuffer2.append(obj4.toString());
                }
            }
            stringBuffer = stringBuffer2.toString();
        }
        return stringBuffer;
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public Object get(Object obj) {
        Object obj2 = super.get(obj);
        int size = LazyList.size(obj2);
        if (size == 0) {
            return null;
        }
        if (size == 1) {
            return LazyList.get(obj2, 0);
        }
        return LazyList.getList(obj2, true);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public Object put(Object obj, Object obj2) {
        return super.put(obj, LazyList.add(null, obj2));
    }

    public Object putValues(Object obj, List list) {
        return super.put(obj, list);
    }

    public Object putValues(Object obj, String[] strArr) {
        Object obj2 = null;
        for (String str : strArr) {
            obj2 = LazyList.add(obj2, str);
        }
        return put(obj, obj2);
    }

    public void add(Object obj, Object obj2) {
        Object obj3 = super.get(obj);
        Object add = LazyList.add(obj3, obj2);
        if (obj3 != add) {
            super.put(obj, add);
        }
    }

    public void addValues(Object obj, List list) {
        Object obj2 = super.get(obj);
        Object addCollection = LazyList.addCollection(obj2, list);
        if (obj2 != addCollection) {
            super.put(obj, addCollection);
        }
    }

    public void addValues(Object obj, String[] strArr) {
        Object obj2 = super.get(obj);
        Object addCollection = LazyList.addCollection(obj2, Arrays.asList(strArr));
        if (obj2 != addCollection) {
            super.put(obj, addCollection);
        }
    }

    public boolean removeValue(Object obj, Object obj2) {
        Object obj3 = super.get(obj);
        int size = LazyList.size(obj3);
        if (size > 0) {
            obj3 = LazyList.remove(obj3, obj2);
            if (obj3 == null) {
                super.remove(obj);
            } else {
                super.put(obj, obj3);
            }
        }
        return LazyList.size(obj3) != size;
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public void putAll(Map map) {
        boolean z = map instanceof MultiMap;
        for (Map.Entry entry : map.entrySet()) {
            if (z) {
                super.put(entry.getKey(), LazyList.clone(entry.getValue()));
            } else {
                put(entry.getKey(), entry.getValue());
            }
        }
    }

    public Map toStringArrayMap() {
        HashMap hashMap = new HashMap((size() * 3) / 2);
        for (Map.Entry entry : super.entrySet()) {
            hashMap.put(entry.getKey(), LazyList.toStringArray(entry.getValue()));
        }
        return hashMap;
    }

    @Override // java.util.HashMap, java.util.AbstractMap
    public Object clone() {
        MultiMap multiMap = (MultiMap) super.clone();
        for (Map.Entry entry : multiMap.entrySet()) {
            entry.setValue(LazyList.clone(entry.getValue()));
        }
        return multiMap;
    }
}
