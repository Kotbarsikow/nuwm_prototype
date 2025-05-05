package org.simmetrics;

import com.google.common.collect.Multiset;

/* loaded from: classes3.dex */
public interface MultisetDistance<E> extends Distance<Multiset<E>> {
    float distance(Multiset<E> multiset, Multiset<E> multiset2);
}
