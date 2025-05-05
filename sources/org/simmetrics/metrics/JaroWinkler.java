package org.simmetrics.metrics;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import org.simmetrics.StringDistance;
import org.simmetrics.StringMetric;

/* loaded from: classes3.dex */
public final class JaroWinkler implements StringMetric, StringDistance {
    private static final float BOOST_THRESHOLD = 0.0f;
    private static final int MAX_PREFIX_LENGTH = 4;
    private static final float PREFIX_SCALE = 0.1f;
    private static final float WINKLER_BOOST_THRESHOLD = 0.7f;
    private final float boostThreshold;
    private final Jaro jaro;
    private final int maxPrefixLength;
    private final float prefixScale;

    public JaroWinkler() {
        this(0.0f, 0.1f, 4);
    }

    public static JaroWinkler createWithBoostThreshold() {
        return new JaroWinkler(WINKLER_BOOST_THRESHOLD, 0.1f, 4);
    }

    public JaroWinkler(float f, float f2, int i) {
        this.jaro = new Jaro();
        Preconditions.checkArgument(f >= 0.0f);
        Preconditions.checkArgument(0.0f <= f2 && f2 <= 1.0f);
        Preconditions.checkArgument(i >= 0);
        this.boostThreshold = f;
        this.prefixScale = f2;
        this.maxPrefixLength = i;
    }

    @Override // org.simmetrics.Distance
    public float distance(String str, String str2) {
        return 1.0f - compare(str, str2);
    }

    @Override // org.simmetrics.Metric
    public float compare(String str, String str2) {
        float compare = this.jaro.compare(str, str2);
        return compare < this.boostThreshold ? compare : compare + (java.lang.Math.min(Strings.commonPrefix(str, str2).length(), this.maxPrefixLength) * this.prefixScale * (1.0f - compare));
    }

    public String toString() {
        return "JaroWinkler [boostThreshold=" + this.boostThreshold + ", prefixScale=" + this.prefixScale + ", maxPrefixLength=" + this.maxPrefixLength + "]";
    }
}
