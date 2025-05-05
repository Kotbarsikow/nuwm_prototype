package com.m_myr.nuwm.nuwmschedule.utils;

import android.text.Editable;
import android.text.Html;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.style.AlignmentSpan;
import android.text.style.RelativeSizeSpan;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;

/* loaded from: classes2.dex */
public class MyHtmlTagHandler implements Html.TagHandler {
    public void handleTag(boolean b, String tag, SpannableStringBuilder mSpannableStringBuilder, XMLReader mReader, Attributes attributes) {
    }

    @Override // android.text.Html.TagHandler
    public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader) {
        if (tag.contains("size")) {
            try {
                processStrike(opening, output, Float.parseFloat(tag.substring(4)));
            } catch (Exception unused) {
            }
        }
    }

    private void processRight(boolean opening, Editable output) {
        int length = output.length();
        if (opening) {
            output.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_OPPOSITE), length, length, 33);
            return;
        }
        Object last = getLast(output, AlignmentSpan.Standard.class);
        output.getSpanStart(last);
        output.removeSpan(last);
    }

    private void processStrike(boolean opening, Editable output, float size) {
        int length = output.length();
        if (opening) {
            output.setSpan(new RelativeSizeSpan(size), length, length, 17);
            return;
        }
        Object last = getLast(output, RelativeSizeSpan.class);
        int spanStart = output.getSpanStart(last);
        output.removeSpan(last);
        if (spanStart != length) {
            output.setSpan(new RelativeSizeSpan(size), spanStart, length, 33);
        }
    }

    private Object getLast(Editable text, Class kind) {
        Object[] spans = text.getSpans(0, text.length(), kind);
        if (spans.length == 0) {
            return null;
        }
        for (int length = spans.length; length > 0; length--) {
            int i = length - 1;
            if (text.getSpanFlags(spans[i]) == 17) {
                return spans[i];
            }
        }
        return null;
    }
}
