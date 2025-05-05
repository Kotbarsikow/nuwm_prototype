package com.google.api.client.util;

import j$.util.DesugarCollections;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.WeakHashMap;

/* loaded from: classes2.dex */
public final class ClassInfo {
    private static final Map<Class<?>, ClassInfo> CACHE = new WeakHashMap();
    private static final Map<Class<?>, ClassInfo> CACHE_IGNORE_CASE = new WeakHashMap();
    private final Class<?> clazz;
    private final boolean ignoreCase;
    private final IdentityHashMap<String, FieldInfo> nameToFieldInfoMap = new IdentityHashMap<>();
    final List<String> names;

    public static ClassInfo of(Class<?> cls) {
        return of(cls, false);
    }

    public static ClassInfo of(Class<?> cls, boolean z) {
        ClassInfo classInfo;
        if (cls == null) {
            return null;
        }
        Map<Class<?>, ClassInfo> map = z ? CACHE_IGNORE_CASE : CACHE;
        synchronized (map) {
            classInfo = map.get(cls);
            if (classInfo == null) {
                classInfo = new ClassInfo(cls, z);
                map.put(cls, classInfo);
            }
        }
        return classInfo;
    }

    public Class<?> getUnderlyingClass() {
        return this.clazz;
    }

    public final boolean getIgnoreCase() {
        return this.ignoreCase;
    }

    public FieldInfo getFieldInfo(String str) {
        if (str != null) {
            if (this.ignoreCase) {
                str = str.toLowerCase();
            }
            str = str.intern();
        }
        return this.nameToFieldInfoMap.get(str);
    }

    public Field getField(String str) {
        FieldInfo fieldInfo = getFieldInfo(str);
        if (fieldInfo == null) {
            return null;
        }
        return fieldInfo.getField();
    }

    public boolean isEnum() {
        return this.clazz.isEnum();
    }

    public Collection<String> getNames() {
        return this.names;
    }

    private ClassInfo(Class<?> cls, boolean z) {
        this.clazz = cls;
        this.ignoreCase = z;
        boolean z2 = (z && cls.isEnum()) ? false : true;
        String valueOf = String.valueOf(String.valueOf(cls));
        StringBuilder sb = new StringBuilder(valueOf.length() + 31);
        sb.append("cannot ignore case on an enum: ");
        sb.append(valueOf);
        Preconditions.checkArgument(z2, sb.toString());
        TreeSet treeSet = new TreeSet(new Comparator<String>() { // from class: com.google.api.client.util.ClassInfo.1
            AnonymousClass1() {
            }

            @Override // java.util.Comparator
            public int compare(String str, String str2) {
                if (str == str2) {
                    return 0;
                }
                if (str == null) {
                    return -1;
                }
                if (str2 == null) {
                    return 1;
                }
                return str.compareTo(str2);
            }
        });
        for (Field field : cls.getDeclaredFields()) {
            FieldInfo of = FieldInfo.of(field);
            if (of != null) {
                String name = of.getName();
                name = z ? name.toLowerCase().intern() : name;
                FieldInfo fieldInfo = this.nameToFieldInfoMap.get(name);
                Preconditions.checkArgument(fieldInfo == null, "two fields have the same %sname <%s>: %s and %s", z ? "case-insensitive " : "", name, field, fieldInfo == null ? null : fieldInfo.getField());
                this.nameToFieldInfoMap.put(name, of);
                treeSet.add(name);
            }
        }
        Class<? super Object> superclass = cls.getSuperclass();
        if (superclass != null) {
            ClassInfo of2 = of(superclass, z);
            treeSet.addAll(of2.names);
            for (Map.Entry<String, FieldInfo> entry : of2.nameToFieldInfoMap.entrySet()) {
                String key = entry.getKey();
                if (!this.nameToFieldInfoMap.containsKey(key)) {
                    this.nameToFieldInfoMap.put(key, entry.getValue());
                }
            }
        }
        this.names = treeSet.isEmpty() ? Collections.emptyList() : DesugarCollections.unmodifiableList(new ArrayList(treeSet));
    }

    /* renamed from: com.google.api.client.util.ClassInfo$1 */
    class AnonymousClass1 implements Comparator<String> {
        AnonymousClass1() {
        }

        @Override // java.util.Comparator
        public int compare(String str, String str2) {
            if (str == str2) {
                return 0;
            }
            if (str == null) {
                return -1;
            }
            if (str2 == null) {
                return 1;
            }
            return str.compareTo(str2);
        }
    }

    public Collection<FieldInfo> getFieldInfos() {
        return DesugarCollections.unmodifiableCollection(this.nameToFieldInfoMap.values());
    }
}
