package com.hootsuite.nachos.terminator;

import android.text.Editable;
import com.hootsuite.nachos.tokenizer.ChipTokenizer;
import java.util.Map;

/* loaded from: classes2.dex */
public interface ChipTerminatorHandler {
    public static final int BEHAVIOR_CHIPIFY_ALL = 0;
    public static final int BEHAVIOR_CHIPIFY_CURRENT_TOKEN = 1;
    public static final int BEHAVIOR_CHIPIFY_TO_TERMINATOR = 2;
    public static final int PASTE_BEHAVIOR_USE_DEFAULT = -1;

    void addChipTerminator(char c, int i);

    int findAndHandleChipTerminators(ChipTokenizer chipTokenizer, Editable editable, int i, int i2, boolean z);

    void setChipTerminators(Map<Character, Integer> map);

    void setPasteBehavior(int i);
}
