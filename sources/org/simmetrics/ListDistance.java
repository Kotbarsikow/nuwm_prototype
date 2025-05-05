package org.simmetrics;

import java.util.List;

/* loaded from: classes3.dex */
public interface ListDistance<E> extends Distance<List<E>> {
    float distance(List<E> list, List<E> list2);
}
