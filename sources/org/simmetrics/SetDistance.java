package org.simmetrics;

import java.util.Set;

/* loaded from: classes3.dex */
public interface SetDistance<E> extends Distance<Set<E>> {
    float distance(Set<E> set, Set<E> set2);
}
