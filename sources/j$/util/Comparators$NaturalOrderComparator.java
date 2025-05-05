package j$.util;

import j$.util.Comparator;
import j$.util.Iterator;
import java.util.Collections;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes4.dex */
final class Comparators$NaturalOrderComparator implements java.util.Comparator, Comparator {
    private static final /* synthetic */ Comparators$NaturalOrderComparator[] $VALUES;
    public static final Comparators$NaturalOrderComparator INSTANCE;

    @Override // java.util.Comparator, j$.util.Comparator
    public final /* synthetic */ java.util.Comparator thenComparing(java.util.Comparator comparator) {
        return Comparator.CC.$default$thenComparing(this, comparator);
    }

    @Override // java.util.Comparator, j$.util.Comparator
    public final /* synthetic */ java.util.Comparator thenComparing(Function function) {
        java.util.Comparator thenComparing;
        thenComparing = Iterator.EL.thenComparing(this, Comparator.CC.comparing(function));
        return thenComparing;
    }

    @Override // java.util.Comparator, j$.util.Comparator
    public final /* synthetic */ java.util.Comparator thenComparing(Function function, java.util.Comparator comparator) {
        java.util.Comparator thenComparing;
        thenComparing = Iterator.EL.thenComparing(this, Comparator.CC.comparing(function, comparator));
        return thenComparing;
    }

    @Override // java.util.Comparator, j$.util.Comparator
    public final /* synthetic */ java.util.Comparator thenComparingDouble(ToDoubleFunction toDoubleFunction) {
        return Comparator.CC.$default$thenComparingDouble(this, toDoubleFunction);
    }

    @Override // java.util.Comparator, j$.util.Comparator
    public final /* synthetic */ java.util.Comparator thenComparingInt(ToIntFunction toIntFunction) {
        return Comparator.CC.$default$thenComparingInt(this, toIntFunction);
    }

    @Override // java.util.Comparator, j$.util.Comparator
    public final /* synthetic */ java.util.Comparator thenComparingLong(ToLongFunction toLongFunction) {
        return Comparator.CC.$default$thenComparingLong(this, toLongFunction);
    }

    public static Comparators$NaturalOrderComparator valueOf(String str) {
        return (Comparators$NaturalOrderComparator) Enum.valueOf(Comparators$NaturalOrderComparator.class, str);
    }

    public static Comparators$NaturalOrderComparator[] values() {
        return (Comparators$NaturalOrderComparator[]) $VALUES.clone();
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return ((Comparable) obj).compareTo((Comparable) obj2);
    }

    static {
        Comparators$NaturalOrderComparator comparators$NaturalOrderComparator = new Comparators$NaturalOrderComparator("INSTANCE", 0);
        INSTANCE = comparators$NaturalOrderComparator;
        $VALUES = new Comparators$NaturalOrderComparator[]{comparators$NaturalOrderComparator};
    }

    @Override // java.util.Comparator, j$.util.Comparator
    public final java.util.Comparator reversed() {
        return Collections.reverseOrder();
    }
}
