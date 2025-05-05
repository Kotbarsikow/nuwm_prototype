package org.mortbay.util;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/* loaded from: classes3.dex */
public class LazyList implements Cloneable, Serializable {
    private static final String[] __EMTPY_STRING_ARRAY = new String[0];

    private LazyList() {
    }

    public static Object add(Object obj, Object obj2) {
        if (obj == null) {
            if (!(obj2 instanceof List) && obj2 != null) {
                return obj2;
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(obj2);
            return arrayList;
        }
        if (obj instanceof List) {
            ((List) obj).add(obj2);
            return obj;
        }
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(obj);
        arrayList2.add(obj2);
        return arrayList2;
    }

    public static Object add(Object obj, int i, Object obj2) {
        if (obj == null) {
            if (i <= 0 && !(obj2 instanceof List) && obj2 != null) {
                return obj2;
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(i, obj2);
            return arrayList;
        }
        if (obj instanceof List) {
            ((List) obj).add(i, obj2);
            return obj;
        }
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(obj);
        arrayList2.add(i, obj2);
        return arrayList2;
    }

    public static Object addCollection(Object obj, Collection collection) {
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            obj = add(obj, it.next());
        }
        return obj;
    }

    public static Object addArray(Object obj, Object[] objArr) {
        for (int i = 0; objArr != null && i < objArr.length; i++) {
            obj = add(obj, objArr[i]);
        }
        return obj;
    }

    public static Object ensureSize(Object obj, int i) {
        if (obj == null) {
            return new ArrayList(i);
        }
        if (obj instanceof ArrayList) {
            ArrayList arrayList = (ArrayList) obj;
            if (arrayList.size() > i) {
                return arrayList;
            }
            ArrayList arrayList2 = new ArrayList(i);
            arrayList2.addAll(arrayList);
            return arrayList2;
        }
        ArrayList arrayList3 = new ArrayList(i);
        arrayList3.add(obj);
        return arrayList3;
    }

    public static Object remove(Object obj, Object obj2) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof List) {
            List list = (List) obj;
            list.remove(obj2);
            if (list.size() == 0) {
                return null;
            }
            return obj;
        }
        if (obj.equals(obj2)) {
            return null;
        }
        return obj;
    }

    public static Object remove(Object obj, int i) {
        if (obj == null) {
            return null;
        }
        if (!(obj instanceof List)) {
            if (i == 0) {
                return null;
            }
            return obj;
        }
        List list = (List) obj;
        list.remove(i);
        if (list.size() == 0) {
            return null;
        }
        return obj;
    }

    public static List getList(Object obj) {
        return getList(obj, false);
    }

    public static List getList(Object obj, boolean z) {
        if (obj == null) {
            if (z) {
                return null;
            }
            return Collections.EMPTY_LIST;
        }
        if (obj instanceof List) {
            return (List) obj;
        }
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(obj);
        return arrayList;
    }

    public static String[] toStringArray(Object obj) {
        if (obj == null) {
            return __EMTPY_STRING_ARRAY;
        }
        if (obj instanceof List) {
            List list = (List) obj;
            String[] strArr = new String[list.size()];
            int size = list.size();
            while (true) {
                int i = size - 1;
                if (size <= 0) {
                    return strArr;
                }
                Object obj2 = list.get(i);
                if (obj2 != null) {
                    strArr[i] = obj2.toString();
                }
                size = i;
            }
        } else {
            return new String[]{obj.toString()};
        }
    }

    public static Object toArray(Object obj, Class cls) {
        if (obj == null) {
            return (Object[]) Array.newInstance((Class<?>) cls, 0);
        }
        if (obj instanceof List) {
            List list = (List) obj;
            if (cls.isPrimitive()) {
                Object newInstance = Array.newInstance((Class<?>) cls, list.size());
                for (int i = 0; i < list.size(); i++) {
                    Array.set(newInstance, i, list.get(i));
                }
                return newInstance;
            }
            return list.toArray((Object[]) Array.newInstance((Class<?>) cls, list.size()));
        }
        Object newInstance2 = Array.newInstance((Class<?>) cls, 1);
        Array.set(newInstance2, 0, obj);
        return newInstance2;
    }

    public static int size(Object obj) {
        if (obj == null) {
            return 0;
        }
        if (obj instanceof List) {
            return ((List) obj).size();
        }
        return 1;
    }

    public static Object get(Object obj, int i) {
        if (obj == null) {
            throw new IndexOutOfBoundsException();
        }
        if (obj instanceof List) {
            return ((List) obj).get(i);
        }
        if (i == 0) {
            return obj;
        }
        throw new IndexOutOfBoundsException();
    }

    public static boolean contains(Object obj, Object obj2) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof List) {
            return ((List) obj).contains(obj2);
        }
        return obj.equals(obj2);
    }

    public static Object clone(Object obj) {
        if (obj == null) {
            return null;
        }
        return obj instanceof List ? new ArrayList((List) obj) : obj;
    }

    public static String toString(Object obj) {
        if (obj == null) {
            return "[]";
        }
        if (obj instanceof List) {
            return ((List) obj).toString();
        }
        return new StringBuffer("[").append(obj).append("]").toString();
    }

    public static Iterator iterator(Object obj) {
        if (obj == null) {
            return Collections.EMPTY_LIST.iterator();
        }
        if (obj instanceof List) {
            return ((List) obj).iterator();
        }
        return getList(obj).iterator();
    }

    public static ListIterator listIterator(Object obj) {
        if (obj == null) {
            return Collections.EMPTY_LIST.listIterator();
        }
        if (obj instanceof List) {
            return ((List) obj).listIterator();
        }
        return getList(obj).listIterator();
    }

    public static List array2List(Object[] objArr) {
        if (objArr == null || objArr.length == 0) {
            return new ArrayList();
        }
        return new ArrayList(Arrays.asList(objArr));
    }

    public static Object[] addToArray(Object[] objArr, Object obj, Class cls) {
        if (objArr == null) {
            if (cls == null && obj != null) {
                cls = obj.getClass();
            }
            Object[] objArr2 = (Object[]) Array.newInstance((Class<?>) cls, 1);
            objArr2[0] = obj;
            return objArr2;
        }
        Object[] objArr3 = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), Array.getLength(objArr) + 1);
        System.arraycopy(objArr, 0, objArr3, 0, objArr.length);
        objArr3[objArr.length] = obj;
        return objArr3;
    }

    public static Object[] removeFromArray(Object[] objArr, Object obj) {
        if (obj != null && objArr != null) {
            int length = objArr.length;
            while (true) {
                int i = length - 1;
                if (length <= 0) {
                    break;
                }
                if (obj.equals(objArr[i])) {
                    Object[] objArr2 = (Object[]) Array.newInstance(objArr == null ? obj.getClass() : objArr.getClass().getComponentType(), Array.getLength(objArr) - 1);
                    if (i > 0) {
                        System.arraycopy(objArr, 0, objArr2, 0, i);
                    }
                    if (length < objArr.length) {
                        System.arraycopy(objArr, length, objArr2, i, objArr.length - length);
                    }
                    return objArr2;
                }
                length = i;
            }
        }
        return objArr;
    }
}
