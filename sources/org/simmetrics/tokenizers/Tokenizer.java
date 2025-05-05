package org.simmetrics.tokenizers;

import com.google.common.collect.Multiset;
import java.util.List;
import java.util.Set;

/* loaded from: classes3.dex */
public interface Tokenizer {
    List<String> tokenizeToList(String str);

    Multiset<String> tokenizeToMultiset(String str);

    Set<String> tokenizeToSet(String str);
}
