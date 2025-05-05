package org.simmetrics.metrics;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.util.Iterator;
import java.util.List;
import org.simmetrics.ListDistance;
import org.simmetrics.StringDistance;

/* loaded from: classes3.dex */
public final class HammingDistance {
    private HammingDistance() {
    }

    private static final class HammingListDistance<T> implements ListDistance<T> {
        HammingListDistance() {
        }

        @Override // org.simmetrics.Distance
        public float distance(List<T> list, List<T> list2) {
            int i = 0;
            Preconditions.checkArgument(list.size() == list2.size());
            if (list.isEmpty()) {
                return 0.0f;
            }
            Iterator<T> it = list.iterator();
            Iterator<T> it2 = list2.iterator();
            while (it.hasNext()) {
                if (!Objects.equal(it.next(), it2.next())) {
                    i++;
                }
            }
            return i;
        }

        public String toString() {
            return "HammingListDistance";
        }
    }

    private static final class HammingStringDistance implements StringDistance {
        HammingStringDistance() {
        }

        @Override // org.simmetrics.Distance
        public float distance(String str, String str2) {
            Preconditions.checkArgument(str.length() == str2.length());
            if (str.isEmpty()) {
                return 0.0f;
            }
            int length = str.length();
            int i = 0;
            for (int i2 = 0; i2 < length; i2++) {
                if (str.charAt(i2) != str2.charAt(i2)) {
                    i++;
                }
            }
            return i;
        }

        public String toString() {
            return "HammingStringDistance";
        }
    }

    public static <T> ListDistance<T> forList() {
        return new HammingListDistance();
    }

    public static StringDistance forString() {
        return new HammingStringDistance();
    }
}
