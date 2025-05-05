package com.m_myr.nuwm.nuwmschedule.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Editable;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AlignmentSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.BulletSpan;
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
import android.util.Log;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import com.hootsuite.nachos.tokenizer.SpanChipTokenizer;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.domain.App;
import com.m_myr.nuwm.nuwmschedule.utils.HtmlHttpImageGetter;
import com.m_myr.nuwm.nuwmschedule.utils.MyHtmlTagHandler;
import com.m_myr.nuwm.nuwmschedule.utils.ResourcesHelper;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

/* compiled from: HtmlImproved.java */
/* loaded from: classes2.dex */
class HtmlToSpannedConverter implements ContentHandler {
    private static final float[] HEADING_SIZES = {1.5f, 1.4f, 1.3f, 1.2f, 1.1f, 1.0f};
    private static Pattern sBackgroundColorPattern;
    private static final Map<String, Integer> sColorMap;
    private static Pattern sForegroundColorPattern;
    private static Pattern sTextAlignPattern;
    private static Pattern sTextDecorationPattern;
    private static Pattern sTextSizePattern;
    private Context context;
    private int mFlags;
    private HtmlHttpImageGetter mImageGetter;
    private XMLReader mReader;
    private String mSource;
    private SpannableStringBuilder mSpannableStringBuilder = new SpannableStringBuilder();
    private MyHtmlTagHandler mTagHandler;

    @Override // org.xml.sax.ContentHandler
    public void endDocument() throws SAXException {
    }

    @Override // org.xml.sax.ContentHandler
    public void endPrefixMapping(String prefix) throws SAXException {
    }

    @Override // org.xml.sax.ContentHandler
    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
    }

    @Override // org.xml.sax.ContentHandler
    public void processingInstruction(String target, String data) throws SAXException {
    }

    @Override // org.xml.sax.ContentHandler
    public void setDocumentLocator(Locator locator) {
    }

    @Override // org.xml.sax.ContentHandler
    public void skippedEntity(String name) throws SAXException {
    }

    @Override // org.xml.sax.ContentHandler
    public void startDocument() throws SAXException {
    }

    @Override // org.xml.sax.ContentHandler
    public void startPrefixMapping(String prefix, String uri) throws SAXException {
    }

    static {
        HashMap hashMap = new HashMap();
        sColorMap = hashMap;
        hashMap.put("darkgray", -5658199);
        hashMap.put("gray", -8355712);
        hashMap.put("lightgray", -2894893);
        hashMap.put("darkgrey", -5658199);
        hashMap.put("grey", -8355712);
        hashMap.put("lightgrey", -2894893);
        hashMap.put("green", -16744448);
    }

    private static Pattern getTextAlignPattern() {
        if (sTextAlignPattern == null) {
            sTextAlignPattern = Pattern.compile("(?:\\s+|\\A)text-align\\s*:\\s*(\\S*)\\b");
        }
        return sTextAlignPattern;
    }

    private static Pattern getForegroundColorPattern() {
        if (sForegroundColorPattern == null) {
            sForegroundColorPattern = Pattern.compile("(?:\\s+|\\A)color\\s*:\\s*(\\S*)\\b");
        }
        return sForegroundColorPattern;
    }

    private static Pattern getTextSize() {
        if (sTextSizePattern == null) {
            sTextSizePattern = Pattern.compile("(?:\\s+|\\A)font-size\\s*:\\s*([0-9.]{1,})([a-z%]{1,3})\\b");
        }
        return sTextSizePattern;
    }

    private static Pattern getBackgroundColorPattern() {
        if (sBackgroundColorPattern == null) {
            sBackgroundColorPattern = Pattern.compile("(?:\\s+|\\A)background(?:-color)?\\s*:\\s*(\\S*)\\b");
        }
        return sBackgroundColorPattern;
    }

    private static Pattern getTextDecorationPattern() {
        if (sTextDecorationPattern == null) {
            sTextDecorationPattern = Pattern.compile("(?:\\s+|\\A)text-decoration\\s*:\\s*(\\S*)\\b");
        }
        return sTextDecorationPattern;
    }

    public HtmlToSpannedConverter(String source, HtmlHttpImageGetter imageGetter, MyHtmlTagHandler tagHandler, XMLReader parser, int flags) {
        this.mSource = source;
        this.mImageGetter = imageGetter;
        this.mTagHandler = tagHandler;
        this.mReader = parser;
        this.mFlags = flags;
    }

    public Spanned convert() {
        this.mReader.setContentHandler(this);
        try {
            this.mReader.parse(new InputSource(new StringReader(this.mSource)));
            SpannableStringBuilder spannableStringBuilder = this.mSpannableStringBuilder;
            Object[] spans = spannableStringBuilder.getSpans(0, spannableStringBuilder.length(), ParagraphStyle.class);
            for (int i = 0; i < spans.length; i++) {
                int spanStart = this.mSpannableStringBuilder.getSpanStart(spans[i]);
                int spanEnd = this.mSpannableStringBuilder.getSpanEnd(spans[i]);
                int i2 = spanEnd - 2;
                if (i2 >= 0 && this.mSpannableStringBuilder.charAt(spanEnd - 1) == '\n' && this.mSpannableStringBuilder.charAt(i2) == '\n') {
                    spanEnd--;
                }
                if (spanEnd == spanStart) {
                    this.mSpannableStringBuilder.removeSpan(spans[i]);
                } else {
                    this.mSpannableStringBuilder.setSpan(spans[i], spanStart, spanEnd, 51);
                }
            }
            this.context = null;
            return this.mSpannableStringBuilder;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e2) {
            throw new RuntimeException(e2);
        }
    }

    private void handleStartTag(String tag, Attributes attributes) {
        if (tag.equalsIgnoreCase("br")) {
            return;
        }
        if (tag.equalsIgnoreCase("p")) {
            startBlockElement(this.mSpannableStringBuilder, attributes, getMarginParagraph());
            startCssStyle(this.mSpannableStringBuilder, attributes);
            return;
        }
        if (tag.equalsIgnoreCase("ul")) {
            startBlockElement(this.mSpannableStringBuilder, attributes, getMarginList());
            return;
        }
        if (tag.equalsIgnoreCase("li")) {
            startLi(this.mSpannableStringBuilder, attributes);
            return;
        }
        if (tag.equalsIgnoreCase("div")) {
            startBlockElement(this.mSpannableStringBuilder, attributes, getMarginDiv());
            return;
        }
        if (tag.equalsIgnoreCase("span")) {
            startCssStyle(this.mSpannableStringBuilder, attributes);
            return;
        }
        if (tag.equalsIgnoreCase("strong")) {
            start(this.mSpannableStringBuilder, new Bold());
            return;
        }
        if (tag.equalsIgnoreCase("b")) {
            start(this.mSpannableStringBuilder, new Bold());
            return;
        }
        if (tag.equalsIgnoreCase("em")) {
            start(this.mSpannableStringBuilder, new Italic());
            return;
        }
        if (tag.equalsIgnoreCase("cite")) {
            start(this.mSpannableStringBuilder, new Italic());
            return;
        }
        if (tag.equalsIgnoreCase("dfn")) {
            start(this.mSpannableStringBuilder, new Italic());
            return;
        }
        if (tag.equalsIgnoreCase("i")) {
            start(this.mSpannableStringBuilder, new Italic());
            return;
        }
        if (tag.equalsIgnoreCase("big")) {
            start(this.mSpannableStringBuilder, new Big());
            return;
        }
        if (tag.equalsIgnoreCase("small")) {
            start(this.mSpannableStringBuilder, new Small());
            return;
        }
        if (tag.equalsIgnoreCase("font")) {
            startFont(this.mSpannableStringBuilder, attributes);
            return;
        }
        if (tag.equalsIgnoreCase("blockquote")) {
            startBlockquote(this.mSpannableStringBuilder, attributes);
            return;
        }
        if (tag.equalsIgnoreCase("tt")) {
            start(this.mSpannableStringBuilder, new Monospace());
            return;
        }
        if (tag.equalsIgnoreCase("a")) {
            startA(this.mSpannableStringBuilder, attributes);
            return;
        }
        if (tag.equalsIgnoreCase("u")) {
            start(this.mSpannableStringBuilder, new Underline());
            return;
        }
        if (tag.equalsIgnoreCase("del")) {
            start(this.mSpannableStringBuilder, new Strikethrough());
            return;
        }
        if (tag.equalsIgnoreCase("s")) {
            start(this.mSpannableStringBuilder, new Strikethrough());
            return;
        }
        if (tag.equalsIgnoreCase("strike")) {
            start(this.mSpannableStringBuilder, new Strikethrough());
            return;
        }
        if (tag.equalsIgnoreCase("sup")) {
            start(this.mSpannableStringBuilder, new Super());
            return;
        }
        if (tag.equalsIgnoreCase("sub")) {
            start(this.mSpannableStringBuilder, new Sub());
            return;
        }
        if (tag.length() == 2 && Character.toLowerCase(tag.charAt(0)) == 'h' && tag.charAt(1) >= '1' && tag.charAt(1) <= '6') {
            startHeading(this.mSpannableStringBuilder, attributes, tag.charAt(1) - '1');
            return;
        }
        if (tag.equalsIgnoreCase("img")) {
            HtmlHttpImageGetter htmlHttpImageGetter = this.mImageGetter;
            if (htmlHttpImageGetter != null) {
                startImg(this.mSpannableStringBuilder, attributes, htmlHttpImageGetter);
                return;
            }
            return;
        }
        MyHtmlTagHandler myHtmlTagHandler = this.mTagHandler;
        if (myHtmlTagHandler != null) {
            myHtmlTagHandler.handleTag(true, tag, this.mSpannableStringBuilder, this.mReader, attributes);
        }
    }

    private void handleEndTag(String tag) {
        if (tag.equalsIgnoreCase("br")) {
            handleBr(this.mSpannableStringBuilder);
            return;
        }
        if (tag.equalsIgnoreCase("p")) {
            endCssStyle(this.mSpannableStringBuilder);
            endBlockElement(this.mSpannableStringBuilder);
            return;
        }
        if (tag.equalsIgnoreCase("ul")) {
            endBlockElement(this.mSpannableStringBuilder);
            return;
        }
        if (tag.equalsIgnoreCase("li")) {
            endLi(this.mSpannableStringBuilder);
            return;
        }
        if (tag.equalsIgnoreCase("div")) {
            endBlockElement(this.mSpannableStringBuilder);
            return;
        }
        if (tag.equalsIgnoreCase("span")) {
            endCssStyle(this.mSpannableStringBuilder);
            return;
        }
        if (tag.equalsIgnoreCase("strong")) {
            end(this.mSpannableStringBuilder, Bold.class, new StyleSpan(1));
            return;
        }
        if (tag.equalsIgnoreCase("b")) {
            end(this.mSpannableStringBuilder, Bold.class, new StyleSpan(1));
            return;
        }
        if (tag.equalsIgnoreCase("em")) {
            end(this.mSpannableStringBuilder, Italic.class, new StyleSpan(2));
            return;
        }
        if (tag.equalsIgnoreCase("cite")) {
            end(this.mSpannableStringBuilder, Italic.class, new StyleSpan(2));
            return;
        }
        if (tag.equalsIgnoreCase("dfn")) {
            end(this.mSpannableStringBuilder, Italic.class, new StyleSpan(2));
            return;
        }
        if (tag.equalsIgnoreCase("i")) {
            end(this.mSpannableStringBuilder, Italic.class, new StyleSpan(2));
            return;
        }
        if (tag.equalsIgnoreCase("big")) {
            end(this.mSpannableStringBuilder, Big.class, new RelativeSizeSpan(1.25f));
            return;
        }
        if (tag.equalsIgnoreCase("small")) {
            end(this.mSpannableStringBuilder, Small.class, new RelativeSizeSpan(0.8f));
            return;
        }
        if (tag.equalsIgnoreCase("font")) {
            endFont(this.mSpannableStringBuilder);
            return;
        }
        if (tag.equalsIgnoreCase("blockquote")) {
            endBlockquote(this.mSpannableStringBuilder, this.context);
            return;
        }
        if (tag.equalsIgnoreCase("tt")) {
            end(this.mSpannableStringBuilder, Monospace.class, new TypefaceSpan("monospace"));
            return;
        }
        if (tag.equalsIgnoreCase("a")) {
            endA(this.mSpannableStringBuilder);
            return;
        }
        if (tag.equalsIgnoreCase("u")) {
            end(this.mSpannableStringBuilder, Underline.class, new UnderlineSpan());
            return;
        }
        if (tag.equalsIgnoreCase("del")) {
            end(this.mSpannableStringBuilder, Strikethrough.class, new StrikethroughSpan());
            return;
        }
        if (tag.equalsIgnoreCase("s")) {
            end(this.mSpannableStringBuilder, Strikethrough.class, new StrikethroughSpan());
            return;
        }
        if (tag.equalsIgnoreCase("strike")) {
            end(this.mSpannableStringBuilder, Strikethrough.class, new StrikethroughSpan());
            return;
        }
        if (tag.equalsIgnoreCase("sup")) {
            end(this.mSpannableStringBuilder, Super.class, new SuperscriptSpan());
            return;
        }
        if (tag.equalsIgnoreCase("sub")) {
            end(this.mSpannableStringBuilder, Sub.class, new SubscriptSpan());
            return;
        }
        if (tag.length() == 2 && Character.toLowerCase(tag.charAt(0)) == 'h' && tag.charAt(1) >= '1' && tag.charAt(1) <= '6') {
            endHeading(this.mSpannableStringBuilder);
            return;
        }
        MyHtmlTagHandler myHtmlTagHandler = this.mTagHandler;
        if (myHtmlTagHandler != null) {
            myHtmlTagHandler.handleTag(false, tag, this.mSpannableStringBuilder, this.mReader);
        }
    }

    private int getMarginParagraph() {
        return getMargin(1);
    }

    private int getMarginHeading() {
        return getMargin(2);
    }

    private int getMarginListItem() {
        return getMargin(4);
    }

    private int getMarginList() {
        return getMargin(8);
    }

    private int getMarginDiv() {
        return getMargin(16);
    }

    private int getMarginBlockquote() {
        return getMargin(32);
    }

    private int getMargin(int flag) {
        return (flag & this.mFlags) != 0 ? 1 : 2;
    }

    private static void appendNewlines(Editable text, int minNewline) {
        int length = text.length();
        if (length == 0) {
            return;
        }
        int i = 0;
        for (int i2 = length - 1; i2 >= 0 && text.charAt(i2) == '\n'; i2--) {
            i++;
        }
        while (i < minNewline) {
            text.append("\n");
            i++;
        }
    }

    private static void startBlockElement(Editable text, Attributes attributes, int margin) {
        text.length();
        if (margin > 0) {
            appendNewlines(text, margin);
            start(text, new Newline(margin));
        }
        String value = attributes.getValue("", "style");
        if (value != null) {
            Matcher matcher = getTextAlignPattern().matcher(value);
            if (matcher.find()) {
                String group = matcher.group(1);
                if (group.equalsIgnoreCase("start")) {
                    start(text, new Alignment(Layout.Alignment.ALIGN_NORMAL));
                    return;
                }
                if (group.equalsIgnoreCase("center")) {
                    start(text, new Alignment(Layout.Alignment.ALIGN_CENTER));
                } else if (group.equalsIgnoreCase("end") || group.equalsIgnoreCase("right")) {
                    start(text, new Alignment(Layout.Alignment.ALIGN_OPPOSITE));
                }
            }
        }
    }

    private static void endBlockElement(Editable text) {
        Newline newline = (Newline) getLast(text, Newline.class);
        if (newline != null) {
            appendNewlines(text, newline.mNumNewlines);
            text.removeSpan(newline);
        }
        Alignment alignment = (Alignment) getLast(text, Alignment.class);
        if (alignment != null) {
            setSpanFromMark(text, alignment, new AlignmentSpan.Standard(alignment.mAlignment));
        }
    }

    private static void handleBr(Editable text) {
        text.append('\n');
    }

    private void startLi(Editable text, Attributes attributes) {
        startBlockElement(text, attributes, getMarginListItem());
        start(text, new Bullet());
        startCssStyle(text, attributes);
    }

    private static void endLi(Editable text) {
        endCssStyle(text);
        endBlockElement(text);
        end(text, Bullet.class, new BulletSpan());
    }

    private void startBlockquote(Editable text, Attributes attributes) {
        startBlockElement(text, attributes, getMarginBlockquote());
        start(text, new Blockquote());
    }

    private static void endBlockquote(Editable text, Context context) {
        endBlockElement(text);
        if (Build.VERSION.SDK_INT >= 28) {
            end(text, Blockquote.class, new QuoteSpan(ResourcesHelper.getAttrColor(context, R.attr.colorPrimary), Utils.dpToPx(4), Utils.dpToPx(8)));
        } else {
            end(text, Blockquote.class, new QuoteSpan(ResourcesHelper.getAttrColor(context, R.attr.colorPrimary)));
        }
    }

    private void startHeading(Editable text, Attributes attributes, int level) {
        startBlockElement(text, attributes, getMarginHeading());
        start(text, new Heading(level));
    }

    private static void endHeading(Editable text) {
        Heading heading = (Heading) getLast(text, Heading.class);
        if (heading != null) {
            setSpanFromMark(text, heading, new RelativeSizeSpan(HEADING_SIZES[heading.mLevel]), new StyleSpan(1));
        }
        endBlockElement(text);
    }

    private static <T> T getLast(Spanned spanned, Class<T> cls) {
        Object[] spans = spanned.getSpans(0, spanned.length(), cls);
        if (spans.length == 0) {
            return null;
        }
        return (T) spans[spans.length - 1];
    }

    private static void setSpanFromMark(Spannable text, Object mark, Object... spans) {
        int spanStart = text.getSpanStart(mark);
        text.removeSpan(mark);
        int length = text.length();
        if (spanStart != length) {
            for (Object obj : spans) {
                text.setSpan(obj, spanStart, length, 33);
            }
        }
    }

    private static void start(Editable text, Object mark) {
        int length = text.length();
        text.setSpan(mark, length, length, 17);
    }

    private static void end(Editable text, Class kind, Object repl) {
        text.length();
        Object last = getLast(text, kind);
        if (last != null) {
            setSpanFromMark(text, last, repl);
        }
    }

    private void startCssStyle(Editable text, Attributes attributes) {
        int htmlColor;
        int htmlColor2;
        String value = attributes.getValue("", "style");
        if (value != null) {
            for (String str : value.split(";")) {
                Log.e("XXXXXXXXXXXXXX", "startBlockElement attr " + str);
                if (!str.isEmpty()) {
                    Matcher matcher = getForegroundColorPattern().matcher(str);
                    if (matcher.find() && (htmlColor2 = getHtmlColor(matcher.group(1))) != -1) {
                        start(text, new Foreground(htmlColor2 | ViewCompat.MEASURED_STATE_MASK));
                    }
                    Matcher matcher2 = getBackgroundColorPattern().matcher(str);
                    if (matcher2.find() && (htmlColor = getHtmlColor(matcher2.group(1))) != -1) {
                        start(text, new Background(htmlColor | ViewCompat.MEASURED_STATE_MASK));
                    }
                    Matcher matcher3 = getTextDecorationPattern().matcher(str);
                    if (matcher3.find() && matcher3.group(1).equalsIgnoreCase("line-through")) {
                        start(text, new Strikethrough());
                    }
                    Matcher matcher4 = getTextSize().matcher(str);
                    if (matcher4.find()) {
                        float parseFloat = Float.parseFloat(matcher4.group(1));
                        if (parseFloat != 0.0f) {
                            start(text, new TextSize(parseFloat));
                        }
                    }
                }
            }
        }
    }

    private static void endCssStyle(Editable text) {
        Strikethrough strikethrough = (Strikethrough) getLast(text, Strikethrough.class);
        if (strikethrough != null) {
            setSpanFromMark(text, strikethrough, new StrikethroughSpan());
        }
        Background background = (Background) getLast(text, Background.class);
        if (background != null) {
            setSpanFromMark(text, background, new BackgroundColorSpan(background.mBackgroundColor));
        }
        Foreground foreground = (Foreground) getLast(text, Foreground.class);
        if (foreground != null) {
            setSpanFromMark(text, foreground, new ForegroundColorSpan(foreground.mForegroundColor));
        }
        TextSize textSize = (TextSize) getLast(text, TextSize.class);
        if (textSize != null) {
            setSpanFromMark(text, textSize, new RelativeSizeSpan(textSize.mTextSize));
        }
    }

    private static void startImg(Editable text, Attributes attributes, HtmlHttpImageGetter img) {
        String value = attributes.getValue("", "src");
        Drawable drawable = img != null ? img.getDrawable(value, attributes) : null;
        if (drawable == null) {
            drawable = ContextCompat.getDrawable(App.getInstance(), R.drawable.ic_numw);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        }
        int length = text.length();
        text.append("￼");
        text.setSpan(new ImageSpan(drawable, value), length, text.length(), 33);
    }

    private void startFont(Editable text, Attributes attributes) {
        int htmlColor;
        String value = attributes.getValue("", TypedValues.Custom.S_COLOR);
        String value2 = attributes.getValue("", "face");
        String value3 = attributes.getValue("", "size");
        if (!TextUtils.isEmpty(value) && (htmlColor = getHtmlColor(value)) != -1) {
            start(text, new Foreground(htmlColor | ViewCompat.MEASURED_STATE_MASK));
        }
        if (!TextUtils.isEmpty(value2)) {
            start(text, new Font(value2));
        }
        if (TextUtils.isEmpty(value3)) {
            return;
        }
        float parseFloat = Float.parseFloat(value3);
        if (parseFloat != 0.0f) {
            start(text, new TextSize(parseFloat));
        }
    }

    private static void endFont(Editable text) {
        Font font = (Font) getLast(text, Font.class);
        if (font != null) {
            setSpanFromMark(text, font, new TypefaceSpan(font.mFace));
        }
        Foreground foreground = (Foreground) getLast(text, Foreground.class);
        if (foreground != null) {
            setSpanFromMark(text, foreground, new ForegroundColorSpan(foreground.mForegroundColor));
        }
        TextSize textSize = (TextSize) getLast(text, TextSize.class);
        if (textSize != null) {
            setSpanFromMark(text, textSize, new RelativeSizeSpan(textSize.mTextSize));
        }
    }

    private static void startA(Editable text, Attributes attributes) {
        start(text, new Href(attributes.getValue("", "href")));
    }

    private static void endA(Editable text) {
        Href href = (Href) getLast(text, Href.class);
        if (href == null || href.mHref == null) {
            return;
        }
        setSpanFromMark(text, href, new URLSpan(href.mHref));
    }

    private int getHtmlColor(String color) {
        Integer num;
        Integer colorByName;
        if (color.contains("@") && (colorByName = ResourcesHelper.getColorByName(this.context, color.substring(1))) != null) {
            return colorByName.intValue();
        }
        if ((this.mFlags & 256) == 256 && (num = sColorMap.get(color.toLowerCase(Locale.US))) != null) {
            return num.intValue();
        }
        try {
            return Color.parseColor(color);
        } catch (Exception unused) {
            return -3355444;
        }
    }

    @Override // org.xml.sax.ContentHandler
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        handleStartTag(localName, attributes);
    }

    @Override // org.xml.sax.ContentHandler
    public void endElement(String uri, String localName, String qName) throws SAXException {
        handleEndTag(localName);
    }

    @Override // org.xml.sax.ContentHandler
    public void characters(char[] ch, int start, int length) throws SAXException {
        char charAt;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char c = ch[i + start];
            if (c == ' ' || c == '\n') {
                int length2 = sb.length();
                if (length2 == 0) {
                    int length3 = this.mSpannableStringBuilder.length();
                    charAt = length3 == 0 ? '\n' : this.mSpannableStringBuilder.charAt(length3 - 1);
                } else {
                    charAt = sb.charAt(length2 - 1);
                }
                if (charAt != ' ' && charAt != '\n') {
                    sb.append(SpanChipTokenizer.AUTOCORRECT_SEPARATOR);
                }
            } else {
                sb.append(c);
            }
        }
        this.mSpannableStringBuilder.append((CharSequence) sb);
    }

    public void withContext(Context context) {
        this.context = context;
    }

    /* compiled from: HtmlImproved.java */
    private static class Bold {
        private Bold() {
        }

        /* synthetic */ Bold(AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    /* compiled from: HtmlImproved.java */
    private static class Italic {
        private Italic() {
        }

        /* synthetic */ Italic(AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    /* compiled from: HtmlImproved.java */
    private static class Underline {
        private Underline() {
        }

        /* synthetic */ Underline(AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    /* compiled from: HtmlImproved.java */
    private static class Strikethrough {
        private Strikethrough() {
        }

        /* synthetic */ Strikethrough(AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    /* compiled from: HtmlImproved.java */
    private static class Big {
        private Big() {
        }

        /* synthetic */ Big(AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    /* compiled from: HtmlImproved.java */
    private static class Small {
        private Small() {
        }

        /* synthetic */ Small(AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    /* compiled from: HtmlImproved.java */
    private static class Monospace {
        private Monospace() {
        }

        /* synthetic */ Monospace(AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    /* compiled from: HtmlImproved.java */
    private static class Blockquote {
        private Blockquote() {
        }

        /* synthetic */ Blockquote(AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    /* compiled from: HtmlImproved.java */
    private static class Super {
        private Super() {
        }

        /* synthetic */ Super(AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    /* compiled from: HtmlImproved.java */
    private static class Sub {
        private Sub() {
        }

        /* synthetic */ Sub(AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    /* compiled from: HtmlImproved.java */
    private static class Bullet {
        private Bullet() {
        }

        /* synthetic */ Bullet(AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    /* compiled from: HtmlImproved.java */
    private static class Font {
        public String mFace;

        public Font(String face) {
            this.mFace = face;
        }
    }

    /* compiled from: HtmlImproved.java */
    private static class Href {
        public String mHref;

        public Href(String href) {
            this.mHref = href;
        }
    }

    /* compiled from: HtmlImproved.java */
    private static class Foreground {
        private int mForegroundColor;

        public Foreground(int foregroundColor) {
            this.mForegroundColor = foregroundColor;
        }
    }

    /* compiled from: HtmlImproved.java */
    private static class TextSize {
        private float mTextSize;

        public TextSize(float textSize) {
            this.mTextSize = Utils.dpToPx((int) textSize) / Utils.dpToPxRaw(12.0f);
        }
    }

    /* compiled from: HtmlImproved.java */
    private static class Background {
        private int mBackgroundColor;

        public Background(int backgroundColor) {
            this.mBackgroundColor = backgroundColor;
        }
    }

    /* compiled from: HtmlImproved.java */
    private static class Heading {
        private int mLevel;

        public Heading(int level) {
            this.mLevel = level;
        }
    }

    /* compiled from: HtmlImproved.java */
    private static class Newline {
        private int mNumNewlines;

        public Newline(int numNewlines) {
            this.mNumNewlines = numNewlines;
        }
    }

    /* compiled from: HtmlImproved.java */
    private static class Alignment {
        private Layout.Alignment mAlignment;

        public Alignment(Layout.Alignment alignment) {
            this.mAlignment = alignment;
        }
    }
}
