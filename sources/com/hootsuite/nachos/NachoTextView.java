package com.hootsuite.nachos;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Pair;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.MultiAutoCompleteTextView;
import androidx.core.content.ContextCompat;
import com.google.api.client.googleapis.media.MediaHttpDownloader;
import com.hootsuite.nachos.chip.Chip;
import com.hootsuite.nachos.chip.ChipInfo;
import com.hootsuite.nachos.chip.ChipSpan;
import com.hootsuite.nachos.chip.ChipSpanChipCreator;
import com.hootsuite.nachos.terminator.ChipTerminatorHandler;
import com.hootsuite.nachos.terminator.DefaultChipTerminatorHandler;
import com.hootsuite.nachos.tokenizer.ChipTokenizer;
import com.hootsuite.nachos.tokenizer.SpanChipTokenizer;
import com.hootsuite.nachos.validator.NachoValidator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class NachoTextView extends MultiAutoCompleteTextView implements TextWatcher, AdapterView.OnItemClickListener {
    private ColorStateList mChipBackground;
    private int mChipHeight;
    private int mChipSpacing;
    private ChipTerminatorHandler mChipTerminatorHandler;
    private int mChipTextColor;
    private int mChipTextSize;
    private ChipTokenizer mChipTokenizer;
    private int mChipVerticalSpacing;
    private boolean mChipifyUnterminatedTokensOnEdit;
    private List<Chip> mChipsToRemove;
    private int mDefaultPaddingBottom;
    private int mDefaultPaddingTop;
    private boolean mEditChipOnTouchEnabled;
    private boolean mIgnoreTextChangedEvents;
    private char[] mIllegalCharacters;
    private boolean mIsPasteEvent;
    private boolean mLayoutComplete;
    private boolean mMeasured;
    private boolean mMoveChipToEndOnEdit;
    private NachoValidator mNachoValidator;
    private OnChipClickListener mOnChipClickListener;
    private int mTextChangedEnd;
    private int mTextChangedStart;
    private boolean mUsingDefaultPadding;
    private GestureDetector singleTapDetector;

    public interface OnChipClickListener {
        void onChipClick(Chip chip, MotionEvent motionEvent);
    }

    @Override // android.widget.TextView, android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public NachoTextView(Context context) {
        super(context);
        this.mChipSpacing = -1;
        this.mChipBackground = null;
        this.mChipTextColor = -1;
        this.mChipTextSize = -1;
        this.mChipHeight = -1;
        this.mChipVerticalSpacing = -1;
        this.mDefaultPaddingTop = 0;
        this.mDefaultPaddingBottom = 0;
        this.mUsingDefaultPadding = true;
        this.mChipsToRemove = new ArrayList();
        init(null);
    }

    public NachoTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mChipSpacing = -1;
        this.mChipBackground = null;
        this.mChipTextColor = -1;
        this.mChipTextSize = -1;
        this.mChipHeight = -1;
        this.mChipVerticalSpacing = -1;
        this.mDefaultPaddingTop = 0;
        this.mDefaultPaddingBottom = 0;
        this.mUsingDefaultPadding = true;
        this.mChipsToRemove = new ArrayList();
        init(attributeSet);
    }

    public NachoTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mChipSpacing = -1;
        this.mChipBackground = null;
        this.mChipTextColor = -1;
        this.mChipTextSize = -1;
        this.mChipHeight = -1;
        this.mChipVerticalSpacing = -1;
        this.mDefaultPaddingTop = 0;
        this.mDefaultPaddingBottom = 0;
        this.mUsingDefaultPadding = true;
        this.mChipsToRemove = new ArrayList();
        init(attributeSet);
    }

    private void init(AttributeSet attributeSet) {
        Context context = getContext();
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.NachoTextView, 0, R.style.DefaultChipSuggestionTextView);
            try {
                this.mChipSpacing = obtainStyledAttributes.getDimensionPixelSize(R.styleable.NachoTextView_chipSpacing, -1);
                this.mChipBackground = obtainStyledAttributes.getColorStateList(R.styleable.NachoTextView_chipBackground);
                this.mChipTextColor = obtainStyledAttributes.getColor(R.styleable.NachoTextView_chipTextColor, -1);
                this.mChipTextSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.NachoTextView_chipTextSize, -1);
                this.mChipHeight = obtainStyledAttributes.getDimensionPixelSize(R.styleable.NachoTextView_chipHeight, -1);
                this.mChipVerticalSpacing = obtainStyledAttributes.getDimensionPixelSize(R.styleable.NachoTextView_chipVerticalSpacing, -1);
            } finally {
                obtainStyledAttributes.recycle();
            }
        }
        this.mDefaultPaddingTop = getPaddingTop();
        this.mDefaultPaddingBottom = getPaddingBottom();
        this.singleTapDetector = new GestureDetector(getContext(), new SingleTapListener());
        setImeOptions(MediaHttpDownloader.MAXIMUM_CHUNK_SIZE);
        addTextChangedListener(this);
        setChipTokenizer(new SpanChipTokenizer(context, new ChipSpanChipCreator(), ChipSpan.class));
        setChipTerminatorHandler(new DefaultChipTerminatorHandler());
        setOnItemClickListener(this);
        updatePadding();
    }

    @Override // android.widget.TextView, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.mMeasured || getWidth() <= 0) {
            return;
        }
        invalidateChips();
        this.mMeasured = true;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.mLayoutComplete) {
            return;
        }
        invalidateChips();
        this.mLayoutComplete = true;
    }

    private void updatePadding() {
        if (this.mChipHeight != -1) {
            boolean isEmpty = getAllChips().isEmpty();
            if (isEmpty && this.mUsingDefaultPadding) {
                this.mUsingDefaultPadding = false;
                Paint.FontMetricsInt fontMetricsInt = getPaint().getFontMetricsInt();
                int i = fontMetricsInt.descent - fontMetricsInt.ascent;
                int i2 = this.mChipHeight;
                int i3 = this.mChipVerticalSpacing;
                int i4 = ((i2 + (i3 != -1 ? i3 : 0)) - i) / 2;
                super.setPadding(getPaddingLeft(), this.mDefaultPaddingTop + i4, getPaddingRight(), this.mDefaultPaddingBottom + i4);
                return;
            }
            if (isEmpty || this.mUsingDefaultPadding) {
                return;
            }
            this.mUsingDefaultPadding = true;
            super.setPadding(getPaddingLeft(), this.mDefaultPaddingTop, getPaddingRight(), this.mDefaultPaddingBottom);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void setPadding(int i, int i2, int i3, int i4) {
        super.setPadding(i, i2, i3, i4);
        this.mDefaultPaddingTop = i2;
        this.mDefaultPaddingBottom = i4;
        updatePadding();
    }

    public int getChipSpacing() {
        return this.mChipSpacing;
    }

    public void setChipSpacing(int i) {
        this.mChipSpacing = getContext().getResources().getDimensionPixelSize(i);
        invalidateChips();
    }

    public ColorStateList getChipBackground() {
        return this.mChipBackground;
    }

    public void setChipBackgroundResource(int i) {
        setChipBackground(ContextCompat.getColorStateList(getContext(), i));
    }

    public void setChipBackground(ColorStateList colorStateList) {
        this.mChipBackground = colorStateList;
        invalidateChips();
    }

    public int getChipTextColor() {
        return this.mChipTextColor;
    }

    public void setChipTextColorResource(int i) {
        setChipTextColor(ContextCompat.getColor(getContext(), i));
    }

    public void setChipTextColor(int i) {
        this.mChipTextColor = i;
        invalidateChips();
    }

    public int getChipTextSize() {
        return this.mChipTextSize;
    }

    public void setChipTextSize(int i) {
        this.mChipTextSize = getContext().getResources().getDimensionPixelSize(i);
        invalidateChips();
    }

    public int getChipHeight() {
        return this.mChipHeight;
    }

    public void setChipHeight(int i) {
        this.mChipHeight = getContext().getResources().getDimensionPixelSize(i);
        invalidateChips();
    }

    public int getChipVerticalSpacing() {
        return this.mChipVerticalSpacing;
    }

    public void setChipVerticalSpacing(int i) {
        this.mChipVerticalSpacing = getContext().getResources().getDimensionPixelSize(i);
        invalidateChips();
    }

    public ChipTokenizer getChipTokenizer() {
        return this.mChipTokenizer;
    }

    public void setChipTokenizer(ChipTokenizer chipTokenizer) {
        this.mChipTokenizer = chipTokenizer;
        if (chipTokenizer != null) {
            setTokenizer(new ChipTokenizerWrapper(chipTokenizer));
        } else {
            setTokenizer(null);
        }
        invalidateChips();
    }

    public void setOnChipClickListener(OnChipClickListener onChipClickListener) {
        this.mOnChipClickListener = onChipClickListener;
    }

    public void setChipTerminatorHandler(ChipTerminatorHandler chipTerminatorHandler) {
        this.mChipTerminatorHandler = chipTerminatorHandler;
    }

    public void setNachoValidator(NachoValidator nachoValidator) {
        this.mNachoValidator = nachoValidator;
    }

    public void setChipTerminators(Map<Character, Integer> map) {
        ChipTerminatorHandler chipTerminatorHandler = this.mChipTerminatorHandler;
        if (chipTerminatorHandler != null) {
            chipTerminatorHandler.setChipTerminators(map);
        }
    }

    public void addChipTerminator(char c, int i) {
        ChipTerminatorHandler chipTerminatorHandler = this.mChipTerminatorHandler;
        if (chipTerminatorHandler != null) {
            chipTerminatorHandler.addChipTerminator(c, i);
        }
    }

    public void setPasteBehavior(int i) {
        ChipTerminatorHandler chipTerminatorHandler = this.mChipTerminatorHandler;
        if (chipTerminatorHandler != null) {
            chipTerminatorHandler.setPasteBehavior(i);
        }
    }

    public void setIllegalCharacters(char... cArr) {
        this.mIllegalCharacters = cArr;
    }

    public void invalidateChips() {
        beginUnwatchedTextChange();
        if (this.mChipTokenizer != null) {
            this.mChipTokenizer.applyConfiguration(getText(), new ChipConfiguration(this.mChipSpacing, this.mChipBackground, this.mChipTextColor, this.mChipTextSize, this.mChipHeight, this.mChipVerticalSpacing, (getWidth() - getCompoundPaddingLeft()) - getCompoundPaddingRight()));
        }
        endUnwatchedTextChange();
    }

    public void enableEditChipOnTouch(boolean z, boolean z2) {
        this.mEditChipOnTouchEnabled = true;
        this.mMoveChipToEndOnEdit = z;
        this.mChipifyUnterminatedTokensOnEdit = z2;
    }

    public void disableEditChipOnTouch() {
        this.mEditChipOnTouchEnabled = false;
    }

    public void setEditingChip(Chip chip, boolean z) {
        if (this.mChipTokenizer == null) {
            return;
        }
        beginUnwatchedTextChange();
        Editable text = getText();
        if (z) {
            text.append(chip.getText());
            this.mChipTokenizer.deleteChipAndPadding(chip, text);
            setSelection(text.length());
        } else {
            int findChipStart = this.mChipTokenizer.findChipStart(chip, text);
            this.mChipTokenizer.revertChipToToken(chip, text);
            setSelection(this.mChipTokenizer.findTokenEnd(text, findChipStart));
        }
        endUnwatchedTextChange();
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z;
        boolean z2;
        clearChipStates();
        Chip findTouchedChip = findTouchedChip(motionEvent);
        if (findTouchedChip != null && isFocused() && this.singleTapDetector.onTouchEvent(motionEvent)) {
            findTouchedChip.setState(View.PRESSED_SELECTED_STATE_SET);
            z = onChipClicked(findTouchedChip);
            OnChipClickListener onChipClickListener = this.mOnChipClickListener;
            if (onChipClickListener != null) {
                onChipClickListener.onChipClick(findTouchedChip, motionEvent);
            }
        } else {
            z = false;
        }
        try {
            z2 = super.onTouchEvent(motionEvent);
        } catch (NullPointerException e) {
            Log.w("Nacho", String.format("Error during touch event of type [%d]", Integer.valueOf(motionEvent.getAction())), e);
            z2 = false;
        }
        return z || z2;
    }

    private Chip findTouchedChip(MotionEvent motionEvent) {
        if (this.mChipTokenizer == null) {
            return null;
        }
        Editable text = getText();
        int offsetForPosition = getOffsetForPosition(motionEvent.getX(), motionEvent.getY());
        for (Chip chip : getAllChips()) {
            int findChipStart = this.mChipTokenizer.findChipStart(chip, text);
            int findChipEnd = this.mChipTokenizer.findChipEnd(chip, text);
            if (findChipStart <= offsetForPosition && offsetForPosition <= findChipEnd) {
                float xForIndex = getXForIndex(findChipStart);
                float xForIndex2 = getXForIndex(findChipEnd - 1);
                float x = motionEvent.getX();
                if (xForIndex <= x && x <= xForIndex2) {
                    return chip;
                }
            }
        }
        return null;
    }

    public boolean onChipClicked(Chip chip) {
        if (!this.mEditChipOnTouchEnabled) {
            return false;
        }
        if (this.mChipifyUnterminatedTokensOnEdit) {
            chipifyAllUnterminatedTokens();
        }
        setEditingChip(chip, this.mMoveChipToEndOnEdit);
        return true;
    }

    private float getXForIndex(int i) {
        return getLayout().getPrimaryHorizontal(i);
    }

    private void clearChipStates() {
        Iterator<Chip> it = getAllChips().iterator();
        while (it.hasNext()) {
            it.next().setState(View.EMPTY_STATE_SET);
        }
    }

    @Override // android.widget.EditText, android.widget.TextView
    public boolean onTextContextMenuItem(int i) {
        int selectionStart = getSelectionStart();
        int selectionEnd = getSelectionEnd();
        switch (i) {
            case android.R.id.cut:
                try {
                    setClipboardData(ClipData.newPlainText(null, getTextWithPlainTextSpans(selectionStart, selectionEnd)));
                    getText().delete(getSelectionStart(), getSelectionEnd());
                    return true;
                } catch (StringIndexOutOfBoundsException e) {
                    throw new StringIndexOutOfBoundsException(String.format("%s \nError cutting text index [%s, %s] for text [%s] and substring [%s]", e.getMessage(), Integer.valueOf(selectionStart), Integer.valueOf(selectionEnd), getText().toString(), getText().subSequence(selectionStart, selectionEnd)));
                }
            case android.R.id.copy:
                try {
                    setClipboardData(ClipData.newPlainText(null, getTextWithPlainTextSpans(selectionStart, selectionEnd)));
                    return true;
                } catch (StringIndexOutOfBoundsException e2) {
                    throw new StringIndexOutOfBoundsException(String.format("%s \nError copying text index [%s, %s] for text [%s] and substring [%s]", e2.getMessage(), Integer.valueOf(selectionStart), Integer.valueOf(selectionEnd), getText().toString(), getText().subSequence(selectionStart, selectionEnd)));
                }
            case android.R.id.paste:
                this.mIsPasteEvent = true;
                boolean onTextContextMenuItem = super.onTextContextMenuItem(i);
                this.mIsPasteEvent = false;
                return onTextContextMenuItem;
            default:
                return super.onTextContextMenuItem(i);
        }
    }

    private void setClipboardData(ClipData clipData) {
        ((ClipboardManager) getContext().getSystemService("clipboard")).setPrimaryClip(clipData);
    }

    @Override // android.widget.MultiAutoCompleteTextView, android.widget.AutoCompleteTextView
    public void performValidation() {
        if (this.mNachoValidator == null || this.mChipTokenizer == null) {
            super.performValidation();
            return;
        }
        Editable text = getText();
        if (TextUtils.isEmpty(text) || this.mNachoValidator.isValid(this.mChipTokenizer, text)) {
            return;
        }
        setRawText(this.mNachoValidator.fixText(this.mChipTokenizer, text));
    }

    private void beginUnwatchedTextChange() {
        this.mIgnoreTextChangedEvents = true;
    }

    private void endUnwatchedTextChange() {
        updatePadding();
        this.mIgnoreTextChangedEvents = false;
    }

    private void setRawText(CharSequence charSequence) {
        beginUnwatchedTextChange();
        super.setText(charSequence);
        endUnwatchedTextChange();
    }

    public void setText(List<String> list) {
        if (this.mChipTokenizer == null) {
            return;
        }
        beginUnwatchedTextChange();
        Editable text = getText();
        text.clear();
        if (list != null) {
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                text.append(this.mChipTokenizer.terminateToken(it.next(), null));
            }
        }
        setSelection(text.length());
        endUnwatchedTextChange();
    }

    public void setTextWithChips(List<ChipInfo> list) {
        if (this.mChipTokenizer == null) {
            return;
        }
        beginUnwatchedTextChange();
        Editable text = getText();
        text.clear();
        if (list != null) {
            for (ChipInfo chipInfo : list) {
                text.append(this.mChipTokenizer.terminateToken(chipInfo.getText(), chipInfo.getData()));
            }
        }
        setSelection(text.length());
        endUnwatchedTextChange();
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        ListAdapter adapter;
        if (this.mChipTokenizer == null || (adapter = getAdapter()) == null) {
            return;
        }
        beginUnwatchedTextChange();
        Object dataForSuggestion = getDataForSuggestion(adapter, i);
        CharSequence convertResultToString = getFilter().convertResultToString(adapter.getItem(i));
        clearComposingText();
        int selectionEnd = getSelectionEnd();
        Editable text = getText();
        text.replace(this.mChipTokenizer.findTokenStart(text, selectionEnd), selectionEnd, this.mChipTokenizer.terminateToken(convertResultToString, dataForSuggestion));
        endUnwatchedTextChange();
    }

    protected Object getDataForSuggestion(Adapter adapter, int i) {
        return adapter.getItem(i);
    }

    @Override // android.widget.MultiAutoCompleteTextView, android.widget.AutoCompleteTextView
    protected void replaceText(CharSequence charSequence) {
        if (this.mChipTokenizer == null) {
            super.replaceText(charSequence);
        }
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (this.mIgnoreTextChangedEvents) {
            return;
        }
        this.mTextChangedStart = i;
        this.mTextChangedEnd = i + i3;
        if (this.mChipTokenizer == null || i2 <= 0 || i3 >= i2) {
            return;
        }
        int i4 = i2 + i;
        Editable text = getText();
        for (Chip chip : this.mChipTokenizer.findAllChips(i, i4, text)) {
            int findChipStart = this.mChipTokenizer.findChipStart(chip, text);
            int findChipEnd = this.mChipTokenizer.findChipEnd(chip, text);
            if (findChipStart < i4 && findChipEnd > i) {
                this.mChipsToRemove.add(chip);
            }
        }
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
        if (this.mIgnoreTextChangedEvents) {
            return;
        }
        beginUnwatchedTextChange();
        if (this.mChipTokenizer != null) {
            Iterator<Chip> it = this.mChipsToRemove.iterator();
            while (it.hasNext()) {
                Chip next = it.next();
                it.remove();
                this.mChipTokenizer.deleteChip(next, editable);
            }
        }
        handleTextChanged(this.mTextChangedStart, this.mTextChangedEnd);
        endUnwatchedTextChange();
    }

    private void handleTextChanged(int i, int i2) {
        ChipTokenizer chipTokenizer;
        ChipTerminatorHandler chipTerminatorHandler;
        int findAndHandleChipTerminators;
        if (i == i2) {
            return;
        }
        Editable text = getText();
        CharSequence subSequence = text.subSequence(i, i2);
        CharSequence removeIllegalCharacters = removeIllegalCharacters(subSequence);
        if (removeIllegalCharacters.length() < subSequence.length()) {
            text.replace(i, i2, removeIllegalCharacters);
            i2 = removeIllegalCharacters.length() + i;
            clearComposingText();
        }
        int i3 = i2;
        if (i == i3 || (chipTokenizer = this.mChipTokenizer) == null || (chipTerminatorHandler = this.mChipTerminatorHandler) == null || (findAndHandleChipTerminators = chipTerminatorHandler.findAndHandleChipTerminators(chipTokenizer, getText(), i, i3, this.mIsPasteEvent)) <= 0) {
            return;
        }
        setSelection(findAndHandleChipTerminators);
    }

    private CharSequence removeIllegalCharacters(CharSequence charSequence) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            if (!isIllegalCharacter(charAt)) {
                sb.append(charAt);
            }
        }
        return sb;
    }

    private boolean isIllegalCharacter(char c) {
        char[] cArr = this.mIllegalCharacters;
        if (cArr != null) {
            for (char c2 : cArr) {
                if (c2 == c) {
                    return true;
                }
            }
        }
        return false;
    }

    public void chipifyAllUnterminatedTokens() {
        beginUnwatchedTextChange();
        chipifyAllUnterminatedTokens(getText());
        endUnwatchedTextChange();
    }

    private void chipifyAllUnterminatedTokens(Editable editable) {
        ChipTokenizer chipTokenizer = this.mChipTokenizer;
        if (chipTokenizer != null) {
            chipTokenizer.terminateAllTokens(editable);
        }
    }

    public void chipify(int i, int i2) {
        beginUnwatchedTextChange();
        chipify(i, i2, getText(), null);
        endUnwatchedTextChange();
    }

    private void chipify(int i, int i2, Editable editable, Object obj) {
        if (this.mChipTokenizer != null) {
            editable.replace(i, i2, this.mChipTokenizer.terminateToken(editable.subSequence(i, i2), obj));
        }
    }

    private CharSequence getTextWithPlainTextSpans(int i, int i2) {
        Editable text = getText();
        String charSequence = text.subSequence(i, i2).toString();
        ChipTokenizer chipTokenizer = this.mChipTokenizer;
        if (chipTokenizer != null) {
            List<Chip> asList = Arrays.asList(chipTokenizer.findAllChips(i, i2, text));
            Collections.reverse(asList);
            for (Chip chip : asList) {
                charSequence = charSequence.substring(0, this.mChipTokenizer.findChipStart(chip, text) - i) + chip.getText().toString() + charSequence.substring(this.mChipTokenizer.findChipEnd(chip, text) - i, charSequence.length());
            }
        }
        return charSequence;
    }

    public List<Chip> getAllChips() {
        Editable text = getText();
        ChipTokenizer chipTokenizer = this.mChipTokenizer;
        return chipTokenizer != null ? Arrays.asList(chipTokenizer.findAllChips(0, text.length(), text)) : new ArrayList();
    }

    public List<String> getChipValues() {
        ArrayList arrayList = new ArrayList();
        Iterator<Chip> it = getAllChips().iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getText().toString());
        }
        return arrayList;
    }

    public List<String> getTokenValues() {
        ArrayList arrayList = new ArrayList();
        if (this.mChipTokenizer != null) {
            Editable text = getText();
            for (Pair<Integer, Integer> pair : this.mChipTokenizer.findAllTokens(text)) {
                arrayList.add(text.subSequence(((Integer) pair.first).intValue(), ((Integer) pair.second).intValue()).toString());
            }
        }
        return arrayList;
    }

    public List<String> getChipAndTokenValues() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(getChipValues());
        arrayList.addAll(getTokenValues());
        return arrayList;
    }

    @Override // android.view.View
    public String toString() {
        try {
            return getTextWithPlainTextSpans(0, getText().length()).toString();
        } catch (ClassCastException unused) {
            return super.toString();
        } catch (StringIndexOutOfBoundsException e) {
            throw new StringIndexOutOfBoundsException(String.format("%s \nError converting toString() [%s]", e.getMessage(), getText().toString()));
        }
    }

    private class ChipTokenizerWrapper implements MultiAutoCompleteTextView.Tokenizer {
        private ChipTokenizer mChipTokenizer;

        public ChipTokenizerWrapper(ChipTokenizer chipTokenizer) {
            this.mChipTokenizer = chipTokenizer;
        }

        @Override // android.widget.MultiAutoCompleteTextView.Tokenizer
        public int findTokenStart(CharSequence charSequence, int i) {
            return this.mChipTokenizer.findTokenStart(charSequence, i);
        }

        @Override // android.widget.MultiAutoCompleteTextView.Tokenizer
        public int findTokenEnd(CharSequence charSequence, int i) {
            return this.mChipTokenizer.findTokenEnd(charSequence, i);
        }

        @Override // android.widget.MultiAutoCompleteTextView.Tokenizer
        public CharSequence terminateToken(CharSequence charSequence) {
            return this.mChipTokenizer.terminateToken(charSequence, null);
        }
    }

    private class SingleTapListener extends GestureDetector.SimpleOnGestureListener {
        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            return true;
        }

        private SingleTapListener() {
        }
    }
}
