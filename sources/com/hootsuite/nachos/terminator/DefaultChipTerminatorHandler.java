package com.hootsuite.nachos.terminator;

import android.text.Editable;
import com.hootsuite.nachos.tokenizer.ChipTokenizer;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public class DefaultChipTerminatorHandler implements ChipTerminatorHandler {
    private Map<Character, Integer> mChipTerminators;
    private int mPasteBehavior = 2;

    @Override // com.hootsuite.nachos.terminator.ChipTerminatorHandler
    public void setChipTerminators(Map<Character, Integer> map) {
        this.mChipTerminators = map;
    }

    @Override // com.hootsuite.nachos.terminator.ChipTerminatorHandler
    public void addChipTerminator(char c, int i) {
        if (this.mChipTerminators == null) {
            this.mChipTerminators = new HashMap();
        }
        this.mChipTerminators.put(Character.valueOf(c), Integer.valueOf(i));
    }

    @Override // com.hootsuite.nachos.terminator.ChipTerminatorHandler
    public void setPasteBehavior(int i) {
        this.mPasteBehavior = i;
    }

    @Override // com.hootsuite.nachos.terminator.ChipTerminatorHandler
    public int findAndHandleChipTerminators(ChipTokenizer chipTokenizer, Editable editable, int i, int i2, boolean z) {
        int intValue;
        int handleChipifyCurrentToken;
        if (this.mChipTerminators == null) {
            return -1;
        }
        TextIterator textIterator = new TextIterator(editable, i, i2);
        int i3 = -1;
        while (textIterator.hasNextCharacter()) {
            char nextCharacter = textIterator.nextCharacter();
            if (isChipTerminator(nextCharacter)) {
                if (!z || (intValue = this.mPasteBehavior) == -1) {
                    intValue = this.mChipTerminators.get(Character.valueOf(nextCharacter)).intValue();
                }
                if (intValue == 0) {
                    return handleChipifyAll(textIterator, chipTokenizer);
                }
                if (intValue == 1) {
                    handleChipifyCurrentToken = handleChipifyCurrentToken(textIterator, chipTokenizer);
                } else {
                    handleChipifyCurrentToken = intValue != 2 ? -1 : handleChipifyToTerminator(textIterator, chipTokenizer);
                }
                if (handleChipifyCurrentToken != -1) {
                    i3 = handleChipifyCurrentToken;
                }
            }
        }
        return i3;
    }

    private int handleChipifyAll(TextIterator textIterator, ChipTokenizer chipTokenizer) {
        textIterator.deleteCharacter(true);
        chipTokenizer.terminateAllTokens(textIterator.getText());
        return textIterator.totalLength();
    }

    private int handleChipifyCurrentToken(TextIterator textIterator, ChipTokenizer chipTokenizer) {
        textIterator.deleteCharacter(true);
        Editable text = textIterator.getText();
        int index = textIterator.getIndex();
        int findTokenStart = chipTokenizer.findTokenStart(text, index);
        int findTokenEnd = chipTokenizer.findTokenEnd(text, index);
        if (findTokenStart >= findTokenEnd) {
            return -1;
        }
        CharSequence terminateToken = chipTokenizer.terminateToken(text.subSequence(findTokenStart, findTokenEnd), null);
        textIterator.replace(findTokenStart, findTokenEnd, terminateToken);
        return findTokenStart + terminateToken.length();
    }

    private int handleChipifyToTerminator(TextIterator textIterator, ChipTokenizer chipTokenizer) {
        Editable text = textIterator.getText();
        int index = textIterator.getIndex();
        if (index > 0) {
            int findTokenStart = chipTokenizer.findTokenStart(text, index);
            if (findTokenStart < index) {
                textIterator.replace(findTokenStart, index + 1, chipTokenizer.terminateToken(text.subSequence(findTokenStart, index), null));
                return -1;
            }
            textIterator.deleteCharacter(false);
            return -1;
        }
        textIterator.deleteCharacter(false);
        return -1;
    }

    private boolean isChipTerminator(char c) {
        Map<Character, Integer> map = this.mChipTerminators;
        return map != null && map.keySet().contains(Character.valueOf(c));
    }
}
