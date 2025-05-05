package com.m_myr.nuwm.nuwmschedule.ui.view;

import android.content.Context;
import android.text.Layout;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.AlignmentSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.BulletSpan;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.ParagraphStyle;
import android.text.style.QuoteSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TypefaceSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import androidx.core.text.HtmlCompat;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.hootsuite.nachos.tokenizer.SpanChipTokenizer;
import com.m_myr.nuwm.nuwmschedule.domain.App;
import com.m_myr.nuwm.nuwmschedule.utils.HtmlHttpImageGetter;
import com.m_myr.nuwm.nuwmschedule.utils.MyHtmlTagHandler;
import kotlin.jvm.internal.CharCompanionObject;
import org.xml.sax.helpers.XMLReaderFactory;

/* loaded from: classes2.dex */
public class HtmlImproved {
    private static final int TO_HTML_PARAGRAPH_FLAG = 1;

    public static Spanned fromHtml(String source, HtmlHttpImageGetter imageGetter) {
        return fromHtml(source, imageGetter, null, null);
    }

    public static Spanned fromHtml(String source, Context context) {
        return fromHtml(source, null, null, context);
    }

    public static Spanned fromHtml(String source, HtmlHttpImageGetter imageGetter, MyHtmlTagHandler tagHandler, Context context) {
        try {
            HtmlToSpannedConverter htmlToSpannedConverter = new HtmlToSpannedConverter(source, imageGetter, tagHandler, XMLReaderFactory.createXMLReader("org.ccil.cowan.tagsoup.Parser"), 257);
            htmlToSpannedConverter.withContext(context);
            return htmlToSpannedConverter.convert();
        } catch (Exception unused) {
            return HtmlCompat.fromHtml(source, 0, imageGetter, tagHandler);
        }
    }

    @Deprecated
    public static String toHtml(Spanned text) {
        return toHtml(text, 0);
    }

    public static String toHtml(Spanned text, int option) {
        StringBuilder sb = new StringBuilder();
        withinHtml(sb, text, option);
        return sb.toString();
    }

    public static String escapeHtml(CharSequence text) {
        StringBuilder sb = new StringBuilder();
        withinStyle(sb, text, 0, text.length());
        return sb.toString();
    }

    private static void withinHtml(StringBuilder out, Spanned text, int option) {
        if ((option & 1) == 0) {
            encodeTextAlignmentByDiv(out, text, option);
        } else {
            withinDiv(out, text, 0, text.length(), option);
        }
    }

    private static void encodeTextAlignmentByDiv(StringBuilder out, Spanned text, int option) {
        int length = text.length();
        int i = 0;
        while (i < length) {
            int nextSpanTransition = text.nextSpanTransition(i, length, ParagraphStyle.class);
            ParagraphStyle[] paragraphStyleArr = (ParagraphStyle[]) text.getSpans(i, nextSpanTransition, ParagraphStyle.class);
            String str = MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
            boolean z = false;
            for (ParagraphStyle paragraphStyle : paragraphStyleArr) {
                if (paragraphStyle instanceof AlignmentSpan) {
                    Layout.Alignment alignment = ((AlignmentSpan) paragraphStyle).getAlignment();
                    if (alignment == Layout.Alignment.ALIGN_CENTER) {
                        str = "align=\"center\" " + str;
                    } else if (alignment == Layout.Alignment.ALIGN_OPPOSITE) {
                        str = "align=\"right\" " + str;
                    } else {
                        str = "align=\"left\" " + str;
                    }
                    z = true;
                }
            }
            if (z) {
                out.append("<div ");
                out.append(str);
                out.append(">");
            }
            withinDiv(out, text, i, nextSpanTransition, option);
            if (z) {
                out.append("</div>");
            }
            i = nextSpanTransition;
        }
    }

    private static void withinDiv(StringBuilder out, Spanned text, int start, int end, int option) {
        while (start < end) {
            int nextSpanTransition = text.nextSpanTransition(start, end, QuoteSpan.class);
            QuoteSpan[] quoteSpanArr = (QuoteSpan[]) text.getSpans(start, nextSpanTransition, QuoteSpan.class);
            for (QuoteSpan quoteSpan : quoteSpanArr) {
                out.append("<blockquote>");
            }
            withinBlockquote(out, text, start, nextSpanTransition, option);
            for (QuoteSpan quoteSpan2 : quoteSpanArr) {
                out.append("</blockquote>\n");
            }
            start = nextSpanTransition;
        }
    }

    private static String getTextDirection(Spanned text, int start, int end) {
        return " dir=\"ltr\"";
    }

    private static String getTextStyles(Spanned text, int start, int end, boolean forceNoVerticalMargin, boolean includeTextAlign) {
        String str = null;
        String str2 = forceNoVerticalMargin ? "margin-top:0; margin-bottom:0;" : null;
        if (includeTextAlign) {
            AlignmentSpan[] alignmentSpanArr = (AlignmentSpan[]) text.getSpans(start, end, AlignmentSpan.class);
            int length = alignmentSpanArr.length - 1;
            while (true) {
                if (length < 0) {
                    break;
                }
                AlignmentSpan alignmentSpan = alignmentSpanArr[length];
                if ((text.getSpanFlags(alignmentSpan) & 51) == 51) {
                    Layout.Alignment alignment = alignmentSpan.getAlignment();
                    if (alignment == Layout.Alignment.ALIGN_NORMAL) {
                        str = "text-align:start;";
                    } else if (alignment == Layout.Alignment.ALIGN_CENTER) {
                        str = "text-align:center;";
                    } else if (alignment == Layout.Alignment.ALIGN_OPPOSITE) {
                        str = "text-align:end;";
                    }
                } else {
                    length--;
                }
            }
        }
        if (str2 == null && str == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder(" style=\"");
        if (str2 != null && str != null) {
            sb.append(str2);
            sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            sb.append(str);
        } else if (str2 != null) {
            sb.append(str2);
        } else if (str != null) {
            sb.append(str);
        }
        sb.append("\"");
        return sb.toString();
    }

    private static void withinBlockquote(StringBuilder out, Spanned text, int start, int end, int option) {
        if ((option & 1) == 0) {
            withinBlockquoteConsecutive(out, text, start, end);
        } else {
            withinBlockquoteIndividual(out, text, start, end);
        }
    }

    private static void withinBlockquoteIndividual(StringBuilder out, Spanned text, int start, int end) {
        boolean z;
        boolean z2 = false;
        while (start <= end) {
            int indexOf = TextUtils.indexOf((CharSequence) text, '\n', start, end);
            if (indexOf < 0) {
                indexOf = end;
            }
            if (indexOf == start) {
                if (z2) {
                    out.append("</ul>\n");
                    z2 = false;
                }
                out.append("<br>\n");
            } else {
                ParagraphStyle[] paragraphStyleArr = (ParagraphStyle[]) text.getSpans(start, indexOf, ParagraphStyle.class);
                int length = paragraphStyleArr.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        z = false;
                        break;
                    }
                    ParagraphStyle paragraphStyle = paragraphStyleArr[i];
                    if ((text.getSpanFlags(paragraphStyle) & 51) == 51 && (paragraphStyle instanceof BulletSpan)) {
                        z = true;
                        break;
                    }
                    i++;
                }
                if (z && !z2) {
                    out.append("<ul");
                    out.append(getTextStyles(text, start, indexOf, true, false));
                    out.append(">\n");
                    z2 = true;
                }
                if (z2 && !z) {
                    out.append("</ul>\n");
                    z2 = false;
                }
                String str = z ? "li" : "p";
                out.append("<");
                out.append(str);
                out.append(getTextDirection(text, start, indexOf));
                out.append(getTextStyles(text, start, indexOf, !z, true));
                out.append(">");
                withinParagraph(out, text, start, indexOf);
                out.append("</");
                out.append(str);
                out.append(">\n");
                if (indexOf == end && z2) {
                    out.append("</ul>\n");
                    z2 = false;
                }
            }
            start = indexOf + 1;
        }
    }

    private static void withinBlockquoteConsecutive(StringBuilder out, Spanned text, int start, int end) {
        out.append("<p");
        out.append(getTextDirection(text, start, end));
        out.append(">");
        int i = start;
        while (i < end) {
            int indexOf = TextUtils.indexOf((CharSequence) text, '\n', i, end);
            if (indexOf < 0) {
                indexOf = end;
            }
            int i2 = 0;
            while (indexOf < end && text.charAt(indexOf) == '\n') {
                i2++;
                indexOf++;
            }
            withinParagraph(out, text, i, indexOf - i2);
            if (i2 == 1) {
                out.append("<br>\n");
            } else {
                for (int i3 = 2; i3 < i2; i3++) {
                    out.append("<br>");
                }
                if (indexOf != end) {
                    out.append("</p>\n");
                    out.append("<p");
                    out.append(getTextDirection(text, start, end));
                    out.append(">");
                }
            }
            i = indexOf;
        }
        out.append("</p>\n");
    }

    private static void withinParagraph(StringBuilder out, Spanned text, int start, int end) {
        while (start < end) {
            int nextSpanTransition = text.nextSpanTransition(start, end, CharacterStyle.class);
            CharacterStyle[] characterStyleArr = (CharacterStyle[]) text.getSpans(start, nextSpanTransition, CharacterStyle.class);
            for (int i = 0; i < characterStyleArr.length; i++) {
                CharacterStyle characterStyle = characterStyleArr[i];
                if (characterStyle instanceof StyleSpan) {
                    int style = ((StyleSpan) characterStyle).getStyle();
                    if ((style & 1) != 0) {
                        out.append("<b>");
                    }
                    if ((style & 2) != 0) {
                        out.append("<i>");
                    }
                }
                CharacterStyle characterStyle2 = characterStyleArr[i];
                if ((characterStyle2 instanceof TypefaceSpan) && "monospace".equals(((TypefaceSpan) characterStyle2).getFamily())) {
                    out.append("<tt>");
                }
                if (characterStyleArr[i] instanceof SuperscriptSpan) {
                    out.append("<sup>");
                }
                if (characterStyleArr[i] instanceof SubscriptSpan) {
                    out.append("<sub>");
                }
                if (characterStyleArr[i] instanceof UnderlineSpan) {
                    out.append("<u>");
                }
                if (characterStyleArr[i] instanceof StrikethroughSpan) {
                    out.append("<span style=\"text-decoration:line-through;\">");
                }
                if (characterStyleArr[i] instanceof URLSpan) {
                    out.append("<a href=\"");
                    out.append(((URLSpan) characterStyleArr[i]).getURL());
                    out.append("\">");
                }
                if (characterStyleArr[i] instanceof ImageSpan) {
                    out.append("<img src=\"");
                    out.append(((ImageSpan) characterStyleArr[i]).getSource());
                    out.append("\">");
                    start = nextSpanTransition;
                }
                CharacterStyle characterStyle3 = characterStyleArr[i];
                if (characterStyle3 instanceof AbsoluteSizeSpan) {
                    AbsoluteSizeSpan absoluteSizeSpan = (AbsoluteSizeSpan) characterStyle3;
                    float size = absoluteSizeSpan.getSize();
                    if (!absoluteSizeSpan.getDip()) {
                        size /= App.getInstance().getResources().getDisplayMetrics().density;
                    }
                    out.append(String.format("<span style=\"font-size:%.0fpx\";>", Float.valueOf(size)));
                }
                CharacterStyle characterStyle4 = characterStyleArr[i];
                if (characterStyle4 instanceof RelativeSizeSpan) {
                    out.append(String.format("<span style=\"font-size:%.2fem;\">", Float.valueOf(((RelativeSizeSpan) characterStyle4).getSizeChange())));
                }
                CharacterStyle characterStyle5 = characterStyleArr[i];
                if (characterStyle5 instanceof ForegroundColorSpan) {
                    out.append(String.format("<span style=\"color:#%06X;\">", Integer.valueOf(((ForegroundColorSpan) characterStyle5).getForegroundColor() & 16777215)));
                }
                CharacterStyle characterStyle6 = characterStyleArr[i];
                if (characterStyle6 instanceof BackgroundColorSpan) {
                    out.append(String.format("<span style=\"background-color:#%06X;\">", Integer.valueOf(((BackgroundColorSpan) characterStyle6).getBackgroundColor() & 16777215)));
                }
            }
            withinStyle(out, text, start, nextSpanTransition);
            for (int length = characterStyleArr.length - 1; length >= 0; length--) {
                if (characterStyleArr[length] instanceof BackgroundColorSpan) {
                    out.append("</span>");
                }
                if (characterStyleArr[length] instanceof ForegroundColorSpan) {
                    out.append("</span>");
                }
                if (characterStyleArr[length] instanceof RelativeSizeSpan) {
                    out.append("</span>");
                }
                if (characterStyleArr[length] instanceof AbsoluteSizeSpan) {
                    out.append("</span>");
                }
                if (characterStyleArr[length] instanceof URLSpan) {
                    out.append("</a>");
                }
                if (characterStyleArr[length] instanceof StrikethroughSpan) {
                    out.append("</span>");
                }
                if (characterStyleArr[length] instanceof UnderlineSpan) {
                    out.append("</u>");
                }
                if (characterStyleArr[length] instanceof SubscriptSpan) {
                    out.append("</sub>");
                }
                if (characterStyleArr[length] instanceof SuperscriptSpan) {
                    out.append("</sup>");
                }
                CharacterStyle characterStyle7 = characterStyleArr[length];
                if ((characterStyle7 instanceof TypefaceSpan) && ((TypefaceSpan) characterStyle7).getFamily().equals("monospace")) {
                    out.append("</tt>");
                }
                CharacterStyle characterStyle8 = characterStyleArr[length];
                if (characterStyle8 instanceof StyleSpan) {
                    int style2 = ((StyleSpan) characterStyle8).getStyle();
                    if ((style2 & 1) != 0) {
                        out.append("</b>");
                    }
                    if ((style2 & 2) != 0) {
                        out.append("</i>");
                    }
                }
            }
            start = nextSpanTransition;
        }
    }

    private static void withinStyle(StringBuilder out, CharSequence text, int start, int end) {
        int i;
        char charAt;
        while (start < end) {
            char charAt2 = text.charAt(start);
            if (charAt2 == '<') {
                out.append("&lt;");
            } else if (charAt2 == '>') {
                out.append("&gt;");
            } else if (charAt2 == '&') {
                out.append("&amp;");
            } else if (charAt2 < 55296 || charAt2 > 57343) {
                if (charAt2 > '~' || charAt2 < ' ') {
                    out.append("&#");
                    out.append((int) charAt2);
                    out.append(";");
                } else if (charAt2 == ' ') {
                    while (true) {
                        int i2 = start + 1;
                        if (i2 >= end || text.charAt(i2) != ' ') {
                            break;
                        }
                        out.append("&nbsp;");
                        start = i2;
                    }
                    out.append(SpanChipTokenizer.AUTOCORRECT_SEPARATOR);
                } else {
                    out.append(charAt2);
                }
            } else if (charAt2 < 56320 && (i = start + 1) < end && (charAt = text.charAt(i)) >= 56320 && charAt <= 57343) {
                int i3 = ((charAt2 - 55296) << 10) | 65536 | (charAt - CharCompanionObject.MIN_LOW_SURROGATE);
                out.append("&#");
                out.append(i3);
                out.append(";");
                start = i;
            }
            start++;
        }
    }
}
