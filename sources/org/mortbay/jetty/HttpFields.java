package org.mortbay.jetty;

import com.hootsuite.nachos.tokenizer.SpanChipTokenizer;
import j$.util.DesugarTimeZone;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TimeZone;
import javax.servlet.http.Cookie;
import org.mortbay.io.Buffer;
import org.mortbay.io.BufferCache;
import org.mortbay.io.BufferDateCache;
import org.mortbay.io.BufferUtil;
import org.mortbay.io.ByteArrayBuffer;
import org.mortbay.io.View;
import org.mortbay.util.LazyList;
import org.mortbay.util.QuotedStringTokenizer;
import org.mortbay.util.StringMap;
import org.mortbay.util.StringUtil;

/* loaded from: classes3.dex */
public class HttpFields {
    public static final String __01Jan1970;
    public static final Buffer __01Jan1970_BUFFER;
    public static final BufferDateCache __dateCache;
    private static SimpleDateFormat[] __dateReceive = null;
    private static final String[] __dateReceiveFmt;
    private static int __dateReceiveInit = 0;
    private static Float __one = null;
    private static StringMap __qualities = null;
    public static final String __separators = ", \t";
    private static Float __zero;
    private Calendar _calendar;
    private StringBuffer _dateBuffer;
    protected int _revision;
    private static String[] DAYS = {"Sat", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
    private static String[] MONTHS = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec", "Jan"};
    private static TimeZone __GMT = DesugarTimeZone.getTimeZone("GMT");
    protected ArrayList _fields = new ArrayList(20);
    protected HashMap _bufferMap = new HashMap(32);
    protected SimpleDateFormat[] _dateReceive = new SimpleDateFormat[__dateReceive.length];

    static {
        BufferDateCache bufferDateCache = new BufferDateCache("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
        __dateCache = bufferDateCache;
        String[] strArr = {"EEE, dd MMM yyyy HH:mm:ss zzz", "EEE, dd-MMM-yy HH:mm:ss", "EEE MMM dd HH:mm:ss yyyy", "EEE, dd MMM yyyy HH:mm:ss", "EEE dd MMM yyyy HH:mm:ss zzz", "EEE dd MMM yyyy HH:mm:ss", "EEE MMM dd yyyy HH:mm:ss zzz", "EEE MMM dd yyyy HH:mm:ss", "EEE MMM-dd-yyyy HH:mm:ss zzz", "EEE MMM-dd-yyyy HH:mm:ss", "dd MMM yyyy HH:mm:ss zzz", "dd MMM yyyy HH:mm:ss", "dd-MMM-yy HH:mm:ss zzz", "dd-MMM-yy HH:mm:ss", "MMM dd HH:mm:ss yyyy zzz", "MMM dd HH:mm:ss yyyy", "EEE MMM dd HH:mm:ss yyyy zzz", "EEE, MMM dd HH:mm:ss yyyy zzz", "EEE, MMM dd HH:mm:ss yyyy", "EEE, dd-MMM-yy HH:mm:ss zzz", "EEE dd-MMM-yy HH:mm:ss zzz", "EEE dd-MMM-yy HH:mm:ss"};
        __dateReceiveFmt = strArr;
        __dateReceiveInit = 3;
        __GMT.setID("GMT");
        bufferDateCache.setTimeZone(__GMT);
        __dateReceive = new SimpleDateFormat[strArr.length];
        for (int i = 0; i < __dateReceiveInit; i++) {
            __dateReceive[i] = new SimpleDateFormat(__dateReceiveFmt[i], Locale.US);
            __dateReceive[i].setTimeZone(__GMT);
        }
        String trim = formatDate(0L, true).trim();
        __01Jan1970 = trim;
        __01Jan1970_BUFFER = new ByteArrayBuffer(trim);
        __one = new Float("1.0");
        __zero = new Float("0.0");
        StringMap stringMap = new StringMap();
        __qualities = stringMap;
        stringMap.put((String) null, (Object) __one);
        __qualities.put("1.0", (Object) __one);
        __qualities.put("1", (Object) __one);
        __qualities.put("0.9", (Object) new Float("0.9"));
        __qualities.put("0.8", (Object) new Float("0.8"));
        __qualities.put("0.7", (Object) new Float("0.7"));
        __qualities.put("0.66", (Object) new Float("0.66"));
        __qualities.put("0.6", (Object) new Float("0.6"));
        __qualities.put("0.5", (Object) new Float("0.5"));
        __qualities.put("0.4", (Object) new Float("0.4"));
        __qualities.put("0.33", (Object) new Float("0.33"));
        __qualities.put("0.3", (Object) new Float("0.3"));
        __qualities.put("0.2", (Object) new Float("0.2"));
        __qualities.put("0.1", (Object) new Float("0.1"));
        __qualities.put("0", (Object) __zero);
        __qualities.put("0.0", (Object) __zero);
    }

    public static String formatDate(long j, boolean z) {
        StringBuffer stringBuffer = new StringBuffer(32);
        GregorianCalendar gregorianCalendar = new GregorianCalendar(__GMT);
        gregorianCalendar.setTimeInMillis(j);
        formatDate(stringBuffer, gregorianCalendar, z);
        return stringBuffer.toString();
    }

    public static String formatDate(Calendar calendar, boolean z) {
        StringBuffer stringBuffer = new StringBuffer(32);
        formatDate(stringBuffer, calendar, z);
        return stringBuffer.toString();
    }

    public static String formatDate(StringBuffer stringBuffer, long j, boolean z) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar(__GMT);
        gregorianCalendar.setTimeInMillis(j);
        formatDate(stringBuffer, gregorianCalendar, z);
        return stringBuffer.toString();
    }

    public static void formatDate(StringBuffer stringBuffer, Calendar calendar, boolean z) {
        int i = calendar.get(7);
        int i2 = calendar.get(5);
        int i3 = calendar.get(2);
        int i4 = calendar.get(1);
        int i5 = i4 / 100;
        int i6 = i4 % 100;
        int timeInMillis = (int) ((calendar.getTimeInMillis() / 1000) % 86400);
        int i7 = timeInMillis % 60;
        int i8 = timeInMillis / 60;
        int i9 = i8 % 60;
        int i10 = i8 / 60;
        stringBuffer.append(DAYS[i]);
        stringBuffer.append(',');
        stringBuffer.append(SpanChipTokenizer.AUTOCORRECT_SEPARATOR);
        StringUtil.append2digits(stringBuffer, i2);
        if (z) {
            stringBuffer.append('-');
            stringBuffer.append(MONTHS[i3]);
            stringBuffer.append('-');
            StringUtil.append2digits(stringBuffer, i5);
            StringUtil.append2digits(stringBuffer, i6);
        } else {
            stringBuffer.append(SpanChipTokenizer.AUTOCORRECT_SEPARATOR);
            stringBuffer.append(MONTHS[i3]);
            stringBuffer.append(SpanChipTokenizer.AUTOCORRECT_SEPARATOR);
            StringUtil.append2digits(stringBuffer, i5);
            StringUtil.append2digits(stringBuffer, i6);
        }
        stringBuffer.append(SpanChipTokenizer.AUTOCORRECT_SEPARATOR);
        StringUtil.append2digits(stringBuffer, i10);
        stringBuffer.append(':');
        StringUtil.append2digits(stringBuffer, i9);
        stringBuffer.append(':');
        StringUtil.append2digits(stringBuffer, i7);
        stringBuffer.append(" GMT");
    }

    public Enumeration getFieldNames() {
        final int i = this._revision;
        return new Enumeration() { // from class: org.mortbay.jetty.HttpFields.1
            int i = 0;
            Field field = null;

            @Override // java.util.Enumeration
            public boolean hasMoreElements() {
                if (this.field != null) {
                    return true;
                }
                while (this.i < HttpFields.this._fields.size()) {
                    ArrayList arrayList = HttpFields.this._fields;
                    int i2 = this.i;
                    this.i = i2 + 1;
                    Field field = (Field) arrayList.get(i2);
                    if (field != null && field._prev == null && field._revision == i) {
                        this.field = field;
                        return true;
                    }
                }
                return false;
            }

            @Override // java.util.Enumeration
            public Object nextElement() throws NoSuchElementException {
                if (this.field == null && !hasMoreElements()) {
                    throw new NoSuchElementException();
                }
                String str = BufferUtil.to8859_1_String(this.field._name);
                this.field = null;
                return str;
            }
        };
    }

    public Iterator getFields() {
        final int i = this._revision;
        return new Iterator() { // from class: org.mortbay.jetty.HttpFields.2
            int i = 0;
            Field field = null;

            @Override // java.util.Iterator
            public boolean hasNext() {
                if (this.field != null) {
                    return true;
                }
                while (this.i < HttpFields.this._fields.size()) {
                    ArrayList arrayList = HttpFields.this._fields;
                    int i2 = this.i;
                    this.i = i2 + 1;
                    Field field = (Field) arrayList.get(i2);
                    if (field != null && field._revision == i) {
                        this.field = field;
                        return true;
                    }
                }
                return false;
            }

            @Override // java.util.Iterator
            public Object next() {
                if (this.field != null || hasNext()) {
                    Field field = this.field;
                    this.field = null;
                    return field;
                }
                throw new NoSuchElementException();
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    private Field getField(String str) {
        return (Field) this._bufferMap.get(HttpHeaders.CACHE.lookup(str));
    }

    private Field getField(Buffer buffer) {
        return (Field) this._bufferMap.get(buffer);
    }

    public boolean containsKey(Buffer buffer) {
        Field field = getField(buffer);
        return field != null && field._revision == this._revision;
    }

    public boolean containsKey(String str) {
        Field field = getField(str);
        return field != null && field._revision == this._revision;
    }

    public String getStringField(String str) {
        Field field = getField(str);
        if (field == null || field._revision != this._revision) {
            return null;
        }
        return field.getValue();
    }

    public String getStringField(Buffer buffer) {
        Field field = getField(buffer);
        if (field == null || field._revision != this._revision) {
            return null;
        }
        return BufferUtil.to8859_1_String(field._value);
    }

    public Buffer get(Buffer buffer) {
        Field field = getField(buffer);
        if (field == null || field._revision != this._revision) {
            return null;
        }
        return field._value;
    }

    public Enumeration getValues(String str) {
        Field field = getField(str);
        if (field == null) {
            return null;
        }
        return new Enumeration(field, this._revision) { // from class: org.mortbay.jetty.HttpFields.3
            Field f;
            private final /* synthetic */ Field val$field;
            private final /* synthetic */ int val$revision;

            {
                this.val$field = field;
                this.val$revision = r3;
                this.f = field;
            }

            @Override // java.util.Enumeration
            public boolean hasMoreElements() {
                while (true) {
                    Field field2 = this.f;
                    if (field2 == null || field2._revision == this.val$revision) {
                        break;
                    }
                    this.f = this.f._next;
                }
                return this.f != null;
            }

            @Override // java.util.Enumeration
            public Object nextElement() throws NoSuchElementException {
                Field field2;
                Field field3 = this.f;
                if (field3 == null) {
                    throw new NoSuchElementException();
                }
                do {
                    field2 = this.f._next;
                    this.f = field2;
                    if (field2 == null) {
                        break;
                    }
                } while (field2._revision != this.val$revision);
                return field3.getValue();
            }
        };
    }

    public Enumeration getValues(Buffer buffer) {
        Field field = getField(buffer);
        if (field == null) {
            return null;
        }
        return new Enumeration(field, this._revision) { // from class: org.mortbay.jetty.HttpFields.4
            Field f;
            private final /* synthetic */ Field val$field;
            private final /* synthetic */ int val$revision;

            {
                this.val$field = field;
                this.val$revision = r3;
                this.f = field;
            }

            @Override // java.util.Enumeration
            public boolean hasMoreElements() {
                while (true) {
                    Field field2 = this.f;
                    if (field2 == null || field2._revision == this.val$revision) {
                        break;
                    }
                    this.f = this.f._next;
                }
                return this.f != null;
            }

            @Override // java.util.Enumeration
            public Object nextElement() throws NoSuchElementException {
                Field field2 = this.f;
                if (field2 == null) {
                    throw new NoSuchElementException();
                }
                this.f = field2._next;
                while (true) {
                    Field field3 = this.f;
                    if (field3 == null || field3._revision == this.val$revision) {
                        break;
                    }
                    this.f = this.f._next;
                }
                return field2.getValue();
            }
        };
    }

    public Enumeration getValues(String str, final String str2) {
        final Enumeration values = getValues(str);
        if (values == null) {
            return null;
        }
        return new Enumeration() { // from class: org.mortbay.jetty.HttpFields.5
            QuotedStringTokenizer tok = null;

            @Override // java.util.Enumeration
            public boolean hasMoreElements() {
                QuotedStringTokenizer quotedStringTokenizer = this.tok;
                if (quotedStringTokenizer != null && quotedStringTokenizer.hasMoreElements()) {
                    return true;
                }
                while (values.hasMoreElements()) {
                    QuotedStringTokenizer quotedStringTokenizer2 = new QuotedStringTokenizer((String) values.nextElement(), str2, false, false);
                    this.tok = quotedStringTokenizer2;
                    if (quotedStringTokenizer2.hasMoreElements()) {
                        return true;
                    }
                }
                this.tok = null;
                return false;
            }

            @Override // java.util.Enumeration
            public Object nextElement() throws NoSuchElementException {
                if (!hasMoreElements()) {
                    throw new NoSuchElementException();
                }
                String str3 = (String) this.tok.nextElement();
                return str3 != null ? str3.trim() : str3;
            }
        };
    }

    public void put(String str, String str2) {
        put(HttpHeaders.CACHE.lookup(str), str2 != null ? HttpHeaderValues.CACHE.lookup(str2) : null, -1L);
    }

    public void put(Buffer buffer, String str) {
        put(buffer, HttpHeaderValues.CACHE.lookup(str), -1L);
    }

    public void put(Buffer buffer, Buffer buffer2) {
        put(buffer, buffer2, -1L);
    }

    public void put(Buffer buffer, Buffer buffer2, long j) {
        if (buffer2 == null) {
            remove(buffer);
            return;
        }
        if (!(buffer instanceof BufferCache.CachedBuffer)) {
            buffer = HttpHeaders.CACHE.lookup(buffer);
        }
        Buffer buffer3 = buffer;
        Field field = (Field) this._bufferMap.get(buffer3);
        if (field == null) {
            Field field2 = new Field(buffer3, buffer2, j, this._revision);
            this._fields.add(field2);
            this._bufferMap.put(field2.getNameBuffer(), field2);
        } else {
            field.reset(buffer2, j, this._revision);
            for (Field field3 = field._next; field3 != null; field3 = field3._next) {
                field3.clear();
            }
        }
    }

    public void put(String str, List list) {
        if (list == null || list.size() == 0) {
            remove(str);
            return;
        }
        Buffer lookup = HttpHeaders.CACHE.lookup(str);
        Object obj = list.get(0);
        if (obj != null) {
            put(lookup, HttpHeaderValues.CACHE.lookup(obj.toString()));
        } else {
            remove(lookup);
        }
        if (list.size() > 1) {
            Iterator it = list.iterator();
            it.next();
            while (it.hasNext()) {
                Object next = it.next();
                if (next != null) {
                    put(lookup, HttpHeaderValues.CACHE.lookup(next.toString()));
                }
            }
        }
    }

    public void add(String str, String str2) throws IllegalArgumentException {
        add(HttpHeaders.CACHE.lookup(str), HttpHeaderValues.CACHE.lookup(str2), -1L);
    }

    public void add(Buffer buffer, Buffer buffer2) throws IllegalArgumentException {
        add(buffer, buffer2, -1L);
    }

    private void add(Buffer buffer, Buffer buffer2, long j) throws IllegalArgumentException {
        if (buffer2 == null) {
            throw new IllegalArgumentException("null value");
        }
        if (!(buffer instanceof BufferCache.CachedBuffer)) {
            buffer = HttpHeaders.CACHE.lookup(buffer);
        }
        Buffer buffer3 = buffer;
        Field field = (Field) this._bufferMap.get(buffer3);
        Field field2 = null;
        if (field != null) {
            while (field != null && field._revision == this._revision) {
                field2 = field;
                field = field._next;
            }
        }
        Field field3 = field2;
        if (field == null) {
            Field field4 = new Field(buffer3, buffer2, j, this._revision);
            if (field3 == null) {
                this._bufferMap.put(field4.getNameBuffer(), field4);
            } else {
                field4._prev = field3;
                field3._next = field4;
            }
            this._fields.add(field4);
            return;
        }
        field.reset(buffer2, j, this._revision);
    }

    public void remove(String str) {
        remove(HttpHeaders.CACHE.lookup(str));
    }

    public void remove(Buffer buffer) {
        Field field = (Field) this._bufferMap.get(buffer);
        if (field != null) {
            while (field != null) {
                field.clear();
                field = field._next;
            }
        }
    }

    public long getLongField(String str) throws NumberFormatException {
        Field field = getField(str);
        if (field == null || field._revision != this._revision) {
            return -1L;
        }
        return field.getLongValue();
    }

    public long getLongField(Buffer buffer) throws NumberFormatException {
        Field field = getField(buffer);
        if (field == null || field._revision != this._revision) {
            return -1L;
        }
        return field.getLongValue();
    }

    /* JADX WARN: Code restructure failed: missing block: B:59:0x00df, code lost:
    
        if (r2.endsWith(" GMT") == false) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00e1, code lost:
    
        r2 = r2.substring(r0, r2.length() - 4);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public long getDateField(java.lang.String r9) {
        /*
            Method dump skipped, instructions count: 287
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mortbay.jetty.HttpFields.getDateField(java.lang.String):long");
    }

    public void putLongField(Buffer buffer, long j) {
        put(buffer, BufferUtil.toBuffer(j), j);
    }

    public void putLongField(String str, long j) {
        put(HttpHeaders.CACHE.lookup(str), BufferUtil.toBuffer(j), j);
    }

    public void addLongField(String str, long j) {
        add(HttpHeaders.CACHE.lookup(str), BufferUtil.toBuffer(j), j);
    }

    public void addLongField(Buffer buffer, long j) {
        add(buffer, BufferUtil.toBuffer(j), j);
    }

    public void putDateField(Buffer buffer, long j) {
        if (this._dateBuffer == null) {
            this._dateBuffer = new StringBuffer(32);
            this._calendar = new GregorianCalendar(__GMT);
        }
        this._dateBuffer.setLength(0);
        this._calendar.setTimeInMillis(j);
        formatDate(this._dateBuffer, this._calendar, false);
        put(buffer, new ByteArrayBuffer(this._dateBuffer.toString()), j);
    }

    public void putDateField(String str, long j) {
        putDateField(HttpHeaders.CACHE.lookup(str), j);
    }

    public void addDateField(String str, long j) {
        if (this._dateBuffer == null) {
            this._dateBuffer = new StringBuffer(32);
            this._calendar = new GregorianCalendar(__GMT);
        }
        this._dateBuffer.setLength(0);
        this._calendar.setTimeInMillis(j);
        formatDate(this._dateBuffer, this._calendar, false);
        add(HttpHeaders.CACHE.lookup(str), new ByteArrayBuffer(this._dateBuffer.toString()), j);
    }

    public void addSetCookie(Cookie cookie) {
        String stringBuffer;
        String name = cookie.getName();
        String value = cookie.getValue();
        int version = cookie.getVersion();
        if (name == null || name.length() == 0) {
            throw new IllegalArgumentException("Bad cookie name");
        }
        StringBuffer stringBuffer2 = new StringBuffer(128);
        synchronized (stringBuffer2) {
            QuotedStringTokenizer.quoteIfNeeded(stringBuffer2, name);
            stringBuffer2.append('=');
            if (value != null && value.length() > 0) {
                QuotedStringTokenizer.quoteIfNeeded(stringBuffer2, value);
            }
            if (version > 0) {
                stringBuffer2.append(";Version=");
                stringBuffer2.append(version);
                String comment = cookie.getComment();
                if (comment != null && comment.length() > 0) {
                    stringBuffer2.append(";Comment=");
                    QuotedStringTokenizer.quoteIfNeeded(stringBuffer2, comment);
                }
            }
            String path = cookie.getPath();
            if (path != null && path.length() > 0) {
                stringBuffer2.append(";Path=");
                if (path.startsWith("\"")) {
                    stringBuffer2.append(path);
                } else {
                    QuotedStringTokenizer.quoteIfNeeded(stringBuffer2, path);
                }
            }
            String domain = cookie.getDomain();
            if (domain != null && domain.length() > 0) {
                stringBuffer2.append(";Domain=");
                QuotedStringTokenizer.quoteIfNeeded(stringBuffer2, domain.toLowerCase());
            }
            long maxAge = cookie.getMaxAge();
            if (maxAge >= 0) {
                if (version == 0) {
                    stringBuffer2.append(";Expires=");
                    if (maxAge == 0) {
                        stringBuffer2.append(__01Jan1970);
                    } else {
                        formatDate(stringBuffer2, System.currentTimeMillis() + (maxAge * 1000), true);
                    }
                } else {
                    stringBuffer2.append(";Max-Age=");
                    stringBuffer2.append(maxAge);
                }
            } else if (version > 0) {
                stringBuffer2.append(";Discard");
            }
            if (cookie.getSecure()) {
                stringBuffer2.append(";Secure");
            }
            if (cookie instanceof HttpOnlyCookie) {
                stringBuffer2.append(";HttpOnly");
            }
            stringBuffer = stringBuffer2.toString();
        }
        put(HttpHeaders.EXPIRES_BUFFER, __01Jan1970_BUFFER);
        add(HttpHeaders.SET_COOKIE_BUFFER, new ByteArrayBuffer(stringBuffer));
    }

    public void put(Buffer buffer) throws IOException {
        for (int i = 0; i < this._fields.size(); i++) {
            Field field = (Field) this._fields.get(i);
            if (field != null && field._revision == this._revision) {
                field.put(buffer);
            }
        }
        BufferUtil.putCRLF(buffer);
    }

    public String toString() {
        try {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < this._fields.size(); i++) {
                Field field = (Field) this._fields.get(i);
                if (field != null && field._revision == this._revision) {
                    String name = field.getName();
                    if (name != null) {
                        stringBuffer.append(name);
                    }
                    stringBuffer.append(": ");
                    String value = field.getValue();
                    if (value != null) {
                        stringBuffer.append(value);
                    }
                    stringBuffer.append("\r\n");
                }
            }
            stringBuffer.append("\r\n");
            return stringBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void clear() {
        int i = this._revision + 1;
        this._revision = i;
        if (i <= 1000000) {
            return;
        }
        this._revision = 0;
        int size = this._fields.size();
        while (true) {
            int i2 = size - 1;
            if (size <= 0) {
                return;
            }
            Field field = (Field) this._fields.get(i2);
            if (field != null) {
                field.clear();
            }
            size = i2;
        }
    }

    public void destroy() {
        ArrayList arrayList = this._fields;
        if (arrayList != null) {
            int size = arrayList.size();
            while (true) {
                int i = size - 1;
                if (size <= 0) {
                    break;
                }
                Field field = (Field) this._fields.get(i);
                if (field != null) {
                    this._bufferMap.remove(field.getNameBuffer());
                    field.destroy();
                }
                size = i;
            }
        }
        this._fields = null;
        this._dateBuffer = null;
        this._calendar = null;
        this._dateReceive = null;
    }

    public void add(HttpFields httpFields) {
        if (httpFields == null) {
            return;
        }
        Enumeration fieldNames = httpFields.getFieldNames();
        while (fieldNames.hasMoreElements()) {
            String str = (String) fieldNames.nextElement();
            Enumeration values = httpFields.getValues(str);
            while (values.hasMoreElements()) {
                add(str, (String) values.nextElement());
            }
        }
    }

    public static String valueParameters(String str, Map map) {
        if (str == null) {
            return null;
        }
        int indexOf = str.indexOf(59);
        if (indexOf < 0) {
            return str;
        }
        if (map == null) {
            return str.substring(0, indexOf).trim();
        }
        QuotedStringTokenizer quotedStringTokenizer = new QuotedStringTokenizer(str.substring(indexOf), ";", false, true);
        while (quotedStringTokenizer.hasMoreTokens()) {
            QuotedStringTokenizer quotedStringTokenizer2 = new QuotedStringTokenizer(quotedStringTokenizer.nextToken(), "= ");
            if (quotedStringTokenizer2.hasMoreTokens()) {
                map.put(quotedStringTokenizer2.nextToken(), quotedStringTokenizer2.hasMoreTokens() ? quotedStringTokenizer2.nextToken() : null);
            }
        }
        return str.substring(0, indexOf).trim();
    }

    public static Float getQuality(String str) {
        if (str == null) {
            return __zero;
        }
        int indexOf = str.indexOf(";");
        int i = indexOf + 1;
        if (indexOf < 0 || i == str.length()) {
            return __one;
        }
        if (str.charAt(i) == 'q') {
            int i2 = indexOf + 3;
            Map.Entry entry = __qualities.getEntry(str, i2, str.length() - i2);
            if (entry != null) {
                return (Float) entry.getValue();
            }
        }
        HashMap hashMap = new HashMap(3);
        valueParameters(str, hashMap);
        String str2 = (String) hashMap.get("q");
        Float f = (Float) __qualities.get(str2);
        if (f != null) {
            return f;
        }
        try {
            return new Float(str2);
        } catch (Exception unused) {
            return __one;
        }
    }

    public static List qualityList(Enumeration enumeration) {
        if (enumeration == null || !enumeration.hasMoreElements()) {
            return Collections.EMPTY_LIST;
        }
        Object obj = null;
        Object obj2 = null;
        while (enumeration.hasMoreElements()) {
            String obj3 = enumeration.nextElement().toString();
            Float quality = getQuality(obj3);
            if (quality.floatValue() >= 0.001d) {
                obj = LazyList.add(obj, obj3);
                obj2 = LazyList.add(obj2, quality);
            }
        }
        List list = LazyList.getList(obj, false);
        if (list.size() < 2) {
            return list;
        }
        List list2 = LazyList.getList(obj2, false);
        Float f = __zero;
        int size = list.size();
        while (true) {
            int i = size - 1;
            if (size > 0) {
                Float f2 = (Float) list2.get(i);
                if (f.compareTo(f2) > 0) {
                    Object obj4 = list.get(i);
                    list.set(i, list.get(size));
                    list.set(size, obj4);
                    list2.set(i, list2.get(size));
                    list2.set(size, f2);
                    f = __zero;
                    size = list.size();
                } else {
                    size = i;
                    f = f2;
                }
            } else {
                list2.clear();
                return list;
            }
        }
    }

    public static final class Field {
        private Buffer _name;
        private Field _next;
        private long _numValue;
        private Field _prev;
        private int _revision;
        private String _stringValue;
        private Buffer _value;

        private Field(Buffer buffer, Buffer buffer2, long j, int i) {
            this._name = buffer.asImmutableBuffer();
            this._value = buffer2.isImmutable() ? buffer2 : new View(buffer2);
            this._next = null;
            this._prev = null;
            this._revision = i;
            this._numValue = j;
            this._stringValue = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clear() {
            this._revision = -1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void destroy() {
            this._name = null;
            this._value = null;
            this._next = null;
            this._prev = null;
            this._stringValue = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void reset(Buffer buffer, long j, int i) {
            this._revision = i;
            if (this._value == null) {
                if (!buffer.isImmutable()) {
                    buffer = new View(buffer);
                }
                this._value = buffer;
                this._numValue = j;
                this._stringValue = null;
                return;
            }
            if (buffer.isImmutable()) {
                this._value = buffer;
                this._numValue = j;
                this._stringValue = null;
                return;
            }
            Buffer buffer2 = this._value;
            if (buffer2 instanceof View) {
                ((View) buffer2).update(buffer);
            } else {
                this._value = new View(buffer);
            }
            this._numValue = j;
            String str = this._stringValue;
            if (str == null) {
                return;
            }
            if (str.length() != buffer.length()) {
                this._stringValue = null;
                return;
            }
            int length = buffer.length();
            while (true) {
                int i2 = length - 1;
                if (length <= 0) {
                    return;
                }
                if (buffer.peek(buffer.getIndex() + i2) != this._stringValue.charAt(i2)) {
                    this._stringValue = null;
                    return;
                }
                length = i2;
            }
        }

        public void put(Buffer buffer) throws IOException {
            Buffer buffer2 = this._name;
            if ((buffer2 instanceof BufferCache.CachedBuffer ? ((BufferCache.CachedBuffer) buffer2).getOrdinal() : -1) >= 0) {
                buffer.put(this._name);
            } else {
                int index = this._name.getIndex();
                int putIndex = this._name.putIndex();
                while (index < putIndex) {
                    int i = index + 1;
                    byte peek = this._name.peek(index);
                    if (peek != 10 && peek != 13 && peek != 58) {
                        buffer.put(peek);
                    }
                    index = i;
                }
            }
            buffer.put(HttpTokens.COLON);
            buffer.put((byte) 32);
            Buffer buffer3 = this._value;
            if ((buffer3 instanceof BufferCache.CachedBuffer ? ((BufferCache.CachedBuffer) buffer3).getOrdinal() : -1) >= 0 || this._numValue >= 0) {
                buffer.put(this._value);
            } else {
                int index2 = this._value.getIndex();
                int putIndex2 = this._value.putIndex();
                while (index2 < putIndex2) {
                    int i2 = index2 + 1;
                    byte peek2 = this._value.peek(index2);
                    if (peek2 != 10 && peek2 != 13) {
                        buffer.put(peek2);
                    }
                    index2 = i2;
                }
            }
            BufferUtil.putCRLF(buffer);
        }

        public String getName() {
            return BufferUtil.to8859_1_String(this._name);
        }

        Buffer getNameBuffer() {
            return this._name;
        }

        public int getNameOrdinal() {
            return HttpHeaders.CACHE.getOrdinal(this._name);
        }

        public String getValue() {
            if (this._stringValue == null) {
                this._stringValue = BufferUtil.to8859_1_String(this._value);
            }
            return this._stringValue;
        }

        public Buffer getValueBuffer() {
            return this._value;
        }

        public int getValueOrdinal() {
            return HttpHeaderValues.CACHE.getOrdinal(this._value);
        }

        public int getIntValue() {
            return (int) getLongValue();
        }

        public long getLongValue() {
            if (this._numValue == -1) {
                this._numValue = BufferUtil.toLong(this._value);
            }
            return this._numValue;
        }

        public String toString() {
            return new StringBuffer("[").append(this._prev == null ? "" : "<-").append(getName()).append("=").append(this._revision).append("=").append(this._value).append(this._next != null ? "->" : "").append("]").toString();
        }
    }
}
