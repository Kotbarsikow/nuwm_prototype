package org.simmetrics.tokenizers;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes3.dex */
public abstract class AbstractTokenizer implements Tokenizer {
    @Override // org.simmetrics.tokenizers.Tokenizer
    public Set<String> tokenizeToSet(String str) {
        return new HashSet(tokenizeToList(str));
    }

    @Override // org.simmetrics.tokenizers.Tokenizer
    public Multiset<String> tokenizeToMultiset(String str) {
        return HashMultiset.create(tokenizeToList(str));
    }
}
