package org.simmetrics.simplifiers;

import com.google.common.base.Preconditions;
import org.apache.commons.codec.language.MatchRatingApproachEncoder;

@Deprecated
/* loaded from: classes3.dex */
public final class MatchRatingApproach implements Simplifier {
    private final MatchRatingApproachEncoder simplifier = new MatchRatingApproachEncoder();

    @Override // org.simmetrics.simplifiers.Simplifier
    public String simplify(String str) {
        Preconditions.checkNotNull(str);
        return this.simplifier.encode(str);
    }

    public String toString() {
        return "MatchRatingApproach";
    }
}
