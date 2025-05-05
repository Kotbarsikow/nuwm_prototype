package org.mortbay.util.ajax;

import j$.util.DesugarCollections;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.mortbay.log.Log;
import org.mortbay.util.IO;
import org.mortbay.util.Loader;
import org.mortbay.util.QuotedStringTokenizer;
import org.mortbay.util.TypeUtil;

/* loaded from: classes3.dex */
public class JSON {
    private static JSON __default = new JSON();
    static /* synthetic */ Class class$java$lang$Object;
    static /* synthetic */ Class class$org$mortbay$util$ajax$JSON;
    static /* synthetic */ Class class$org$mortbay$util$ajax$JSON$Convertible;
    private Map _convertors = DesugarCollections.synchronizedMap(new HashMap());
    private int _stringBufferSize = 256;

    public interface Convertible {
        void fromJSON(Map map);

        void toJSON(Output output);
    }

    public interface Convertor {
        Object fromJSON(Map map);

        void toJSON(Object obj, Output output);
    }

    public interface Generator {
        void addJSON(StringBuffer stringBuffer);
    }

    public interface Output {
        void add(Object obj);

        void add(String str, double d);

        void add(String str, long j);

        void add(String str, Object obj);

        void add(String str, boolean z);

        void addClass(Class cls);
    }

    public interface Source {
        boolean hasNext();

        char next();

        char peek();

        char[] scratchBuffer();
    }

    protected JSON contextFor(String str) {
        return this;
    }

    protected JSON contextForArray() {
        return this;
    }

    public int getStringBufferSize() {
        return this._stringBufferSize;
    }

    public void setStringBufferSize(int i) {
        this._stringBufferSize = i;
    }

    public static void registerConvertor(Class cls, Convertor convertor) {
        __default.addConvertor(cls, convertor);
    }

    public static JSON getDefault() {
        return __default;
    }

    public static void setDefault(JSON json) {
        __default = json;
    }

    public static String toString(Object obj) {
        String stringBuffer;
        StringBuffer stringBuffer2 = new StringBuffer(__default.getStringBufferSize());
        synchronized (stringBuffer2) {
            __default.append(stringBuffer2, obj);
            stringBuffer = stringBuffer2.toString();
        }
        return stringBuffer;
    }

    public static String toString(Map map) {
        String stringBuffer;
        StringBuffer stringBuffer2 = new StringBuffer(__default.getStringBufferSize());
        synchronized (stringBuffer2) {
            __default.appendMap(stringBuffer2, map);
            stringBuffer = stringBuffer2.toString();
        }
        return stringBuffer;
    }

    public static String toString(Object[] objArr) {
        String stringBuffer;
        StringBuffer stringBuffer2 = new StringBuffer(__default.getStringBufferSize());
        synchronized (stringBuffer2) {
            __default.appendArray(stringBuffer2, objArr);
            stringBuffer = stringBuffer2.toString();
        }
        return stringBuffer;
    }

    public static Object parse(String str) {
        return __default.parse((Source) new StringSource(str), false);
    }

    public static Object parse(String str, boolean z) {
        return __default.parse(new StringSource(str), z);
    }

    public static Object parse(Reader reader) throws IOException {
        return __default.parse((Source) new ReaderSource(reader), false);
    }

    public static Object parse(Reader reader, boolean z) throws IOException {
        return __default.parse(new ReaderSource(reader), z);
    }

    public static Object parse(InputStream inputStream) throws IOException {
        return __default.parse((Source) new StringSource(IO.toString(inputStream)), false);
    }

    public static Object parse(InputStream inputStream, boolean z) throws IOException {
        return __default.parse(new StringSource(IO.toString(inputStream)), z);
    }

    public String toJSON(Object obj) {
        String stringBuffer;
        StringBuffer stringBuffer2 = new StringBuffer(getStringBufferSize());
        synchronized (stringBuffer2) {
            append(stringBuffer2, obj);
            stringBuffer = stringBuffer2.toString();
        }
        return stringBuffer;
    }

    public Object fromJSON(String str) {
        return parse(new StringSource(str));
    }

    public void append(StringBuffer stringBuffer, Object obj) {
        if (obj == null) {
            stringBuffer.append("null");
            return;
        }
        if (obj instanceof Convertible) {
            appendJSON(stringBuffer, (Convertible) obj);
            return;
        }
        if (obj instanceof Generator) {
            appendJSON(stringBuffer, (Generator) obj);
            return;
        }
        if (obj instanceof Map) {
            appendMap(stringBuffer, (Map) obj);
            return;
        }
        if (obj instanceof Collection) {
            appendArray(stringBuffer, (Collection) obj);
            return;
        }
        if (obj.getClass().isArray()) {
            appendArray(stringBuffer, obj);
            return;
        }
        if (obj instanceof Number) {
            appendNumber(stringBuffer, (Number) obj);
            return;
        }
        if (obj instanceof Boolean) {
            appendBoolean(stringBuffer, (Boolean) obj);
            return;
        }
        if (obj instanceof String) {
            appendString(stringBuffer, (String) obj);
            return;
        }
        Convertor convertor = getConvertor(obj.getClass());
        if (convertor != null) {
            appendJSON(stringBuffer, convertor, obj);
        } else {
            appendString(stringBuffer, obj.toString());
        }
    }

    public void appendNull(StringBuffer stringBuffer) {
        stringBuffer.append("null");
    }

    public void appendJSON(StringBuffer stringBuffer, final Convertor convertor, final Object obj) {
        appendJSON(stringBuffer, new Convertible() { // from class: org.mortbay.util.ajax.JSON.1
            @Override // org.mortbay.util.ajax.JSON.Convertible
            public void fromJSON(Map map) {
            }

            @Override // org.mortbay.util.ajax.JSON.Convertible
            public void toJSON(Output output) {
                convertor.toJSON(obj, output);
            }
        });
    }

    public void appendJSON(final StringBuffer stringBuffer, Convertible convertible) {
        final char[] cArr = {'{'};
        convertible.toJSON(new Output() { // from class: org.mortbay.util.ajax.JSON.2
            @Override // org.mortbay.util.ajax.JSON.Output
            public void add(Object obj) {
                if (cArr[0] == 0) {
                    throw new IllegalStateException();
                }
                JSON.this.append(stringBuffer, obj);
                cArr[0] = 0;
            }

            @Override // org.mortbay.util.ajax.JSON.Output
            public void addClass(Class cls) {
                char[] cArr2 = cArr;
                if (cArr2[0] == 0) {
                    throw new IllegalStateException();
                }
                stringBuffer.append(cArr2);
                stringBuffer.append("\"class\":");
                JSON.this.append(stringBuffer, cls.getName());
                cArr[0] = ',';
            }

            @Override // org.mortbay.util.ajax.JSON.Output
            public void add(String str, Object obj) {
                char[] cArr2 = cArr;
                if (cArr2[0] == 0) {
                    throw new IllegalStateException();
                }
                stringBuffer.append(cArr2);
                QuotedStringTokenizer.quote(stringBuffer, str);
                stringBuffer.append(':');
                JSON.this.append(stringBuffer, obj);
                cArr[0] = ',';
            }

            @Override // org.mortbay.util.ajax.JSON.Output
            public void add(String str, double d) {
                char[] cArr2 = cArr;
                if (cArr2[0] == 0) {
                    throw new IllegalStateException();
                }
                stringBuffer.append(cArr2);
                QuotedStringTokenizer.quote(stringBuffer, str);
                stringBuffer.append(':');
                JSON.this.appendNumber(stringBuffer, new Double(d));
                cArr[0] = ',';
            }

            @Override // org.mortbay.util.ajax.JSON.Output
            public void add(String str, long j) {
                char[] cArr2 = cArr;
                if (cArr2[0] == 0) {
                    throw new IllegalStateException();
                }
                stringBuffer.append(cArr2);
                QuotedStringTokenizer.quote(stringBuffer, str);
                stringBuffer.append(':');
                JSON.this.appendNumber(stringBuffer, TypeUtil.newLong(j));
                cArr[0] = ',';
            }

            @Override // org.mortbay.util.ajax.JSON.Output
            public void add(String str, boolean z) {
                char[] cArr2 = cArr;
                if (cArr2[0] == 0) {
                    throw new IllegalStateException();
                }
                stringBuffer.append(cArr2);
                QuotedStringTokenizer.quote(stringBuffer, str);
                stringBuffer.append(':');
                JSON.this.appendBoolean(stringBuffer, z ? Boolean.TRUE : Boolean.FALSE);
                cArr[0] = ',';
            }
        });
        char c = cArr[0];
        if (c == '{') {
            stringBuffer.append("{}");
        } else if (c != 0) {
            stringBuffer.append("}");
        }
    }

    public void appendJSON(StringBuffer stringBuffer, Generator generator) {
        generator.addJSON(stringBuffer);
    }

    public void appendMap(StringBuffer stringBuffer, Map map) {
        if (map == null) {
            appendNull(stringBuffer);
            return;
        }
        stringBuffer.append('{');
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            QuotedStringTokenizer.quote(stringBuffer, entry.getKey().toString());
            stringBuffer.append(':');
            append(stringBuffer, entry.getValue());
            if (it.hasNext()) {
                stringBuffer.append(',');
            }
        }
        stringBuffer.append('}');
    }

    public void appendArray(StringBuffer stringBuffer, Collection collection) {
        if (collection == null) {
            appendNull(stringBuffer);
            return;
        }
        stringBuffer.append('[');
        Iterator it = collection.iterator();
        boolean z = true;
        while (it.hasNext()) {
            if (!z) {
                stringBuffer.append(',');
            }
            append(stringBuffer, it.next());
            z = false;
        }
        stringBuffer.append(']');
    }

    public void appendArray(StringBuffer stringBuffer, Object obj) {
        if (obj == null) {
            appendNull(stringBuffer);
            return;
        }
        stringBuffer.append('[');
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            if (i != 0) {
                stringBuffer.append(',');
            }
            append(stringBuffer, Array.get(obj, i));
        }
        stringBuffer.append(']');
    }

    public void appendBoolean(StringBuffer stringBuffer, Boolean bool) {
        if (bool == null) {
            appendNull(stringBuffer);
        } else {
            stringBuffer.append(bool.booleanValue() ? "true" : "false");
        }
    }

    public void appendNumber(StringBuffer stringBuffer, Number number) {
        if (number == null) {
            appendNull(stringBuffer);
        } else {
            stringBuffer.append(number);
        }
    }

    public void appendString(StringBuffer stringBuffer, String str) {
        if (str == null) {
            appendNull(stringBuffer);
        } else {
            QuotedStringTokenizer.quote(stringBuffer, str);
        }
    }

    protected String toString(char[] cArr, int i, int i2) {
        return new String(cArr, i, i2);
    }

    protected Map newMap() {
        return new HashMap();
    }

    protected Object[] newArray(int i) {
        return new Object[i];
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    protected Object convertTo(Class cls, Map map) {
        if (cls != null) {
            Class cls2 = class$org$mortbay$util$ajax$JSON$Convertible;
            if (cls2 == null) {
                cls2 = class$("org.mortbay.util.ajax.JSON$Convertible");
                class$org$mortbay$util$ajax$JSON$Convertible = cls2;
            }
            if (cls2.isAssignableFrom(cls)) {
                try {
                    Convertible convertible = (Convertible) cls.newInstance();
                    convertible.fromJSON(map);
                    return convertible;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
        Convertor convertor = getConvertor(cls);
        return convertor != null ? convertor.fromJSON(map) : map;
    }

    public void addConvertor(Class cls, Convertor convertor) {
        this._convertors.put(cls.getName(), convertor);
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0048, code lost:
    
        r5 = r5.getSuperclass();
        r0 = (org.mortbay.util.ajax.JSON.Convertor) r4._convertors.get(r5.getName());
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected org.mortbay.util.ajax.JSON.Convertor getConvertor(java.lang.Class r5) {
        /*
            r4 = this;
            java.util.Map r0 = r4._convertors
            java.lang.String r1 = r5.getName()
            java.lang.Object r0 = r0.get(r1)
            org.mortbay.util.ajax.JSON$Convertor r0 = (org.mortbay.util.ajax.JSON.Convertor) r0
            if (r0 != 0) goto L16
            org.mortbay.util.ajax.JSON r1 = org.mortbay.util.ajax.JSON.__default
            if (r4 == r1) goto L16
            org.mortbay.util.ajax.JSON$Convertor r0 = r1.getConvertor(r5)
        L16:
            if (r0 != 0) goto L59
            if (r5 == 0) goto L59
            java.lang.Class r1 = org.mortbay.util.ajax.JSON.class$java$lang$Object
            if (r1 != 0) goto L26
            java.lang.String r1 = "java.lang.Object"
            java.lang.Class r1 = class$(r1)
            org.mortbay.util.ajax.JSON.class$java$lang$Object = r1
        L26:
            if (r5 == r1) goto L59
            java.lang.Class[] r1 = r5.getInterfaces()
            r2 = 0
        L2d:
            if (r0 != 0) goto L46
            if (r1 == 0) goto L46
            int r3 = r1.length
            if (r2 >= r3) goto L46
            java.util.Map r0 = r4._convertors
            int r3 = r2 + 1
            r2 = r1[r2]
            java.lang.String r2 = r2.getName()
            java.lang.Object r0 = r0.get(r2)
            org.mortbay.util.ajax.JSON$Convertor r0 = (org.mortbay.util.ajax.JSON.Convertor) r0
            r2 = r3
            goto L2d
        L46:
            if (r0 != 0) goto L16
            java.lang.Class r5 = r5.getSuperclass()
            java.util.Map r0 = r4._convertors
            java.lang.String r1 = r5.getName()
            java.lang.Object r0 = r0.get(r1)
            org.mortbay.util.ajax.JSON$Convertor r0 = (org.mortbay.util.ajax.JSON.Convertor) r0
            goto L16
        L59:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mortbay.util.ajax.JSON.getConvertor(java.lang.Class):org.mortbay.util.ajax.JSON$Convertor");
    }

    public void addConvertorFor(String str, Convertor convertor) {
        this._convertors.put(str, convertor);
    }

    public Convertor getConvertorFor(String str) {
        JSON json;
        Convertor convertor = (Convertor) this._convertors.get(str);
        return (convertor != null || this == (json = __default)) ? convertor : json.getConvertorFor(str);
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x0043, code lost:
    
        if (r4 != '\r') goto L40;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object parse(org.mortbay.util.ajax.JSON.Source r10, boolean r11) {
        /*
            r9 = this;
            if (r11 != 0) goto L7
            java.lang.Object r10 = r9.parse(r10)
            return r10
        L7:
            r11 = 1
            r0 = 0
            r1 = 0
            r2 = 0
            r3 = 1
        Lc:
            boolean r4 = r10.hasNext()
            if (r4 == 0) goto L5e
            char r4 = r10.peek()
            r5 = 47
            r6 = 42
            r7 = 2
            if (r2 != r11) goto L29
            if (r4 == r6) goto L24
            if (r4 == r5) goto L22
            goto L5a
        L22:
            r2 = -1
            goto L5a
        L24:
            if (r3 != r11) goto L30
            r2 = 0
            r3 = 2
            goto L5a
        L29:
            r8 = 3
            if (r2 <= r11) goto L3b
            if (r4 == r6) goto L39
            if (r4 == r5) goto L32
        L30:
            r2 = 2
            goto L5a
        L32:
            if (r2 != r8) goto L30
            if (r3 != r7) goto L37
            return r1
        L37:
            r2 = 0
            goto L5a
        L39:
            r2 = 3
            goto L5a
        L3b:
            if (r2 >= 0) goto L46
            r5 = 10
            if (r4 == r5) goto L37
            r5 = 13
            if (r4 == r5) goto L37
            goto L5a
        L46:
            boolean r7 = java.lang.Character.isWhitespace(r4)
            if (r7 != 0) goto L5a
            if (r4 != r5) goto L50
            r2 = 1
            goto L5a
        L50:
            if (r4 != r6) goto L53
            goto L39
        L53:
            if (r1 != 0) goto L5a
            java.lang.Object r1 = r9.parse(r10)
            goto Lc
        L5a:
            r10.next()
            goto Lc
        L5e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mortbay.util.ajax.JSON.parse(org.mortbay.util.ajax.JSON$Source, boolean):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0028, code lost:
    
        if (r1 != 3) goto L15;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object parse(org.mortbay.util.ajax.JSON.Source r9) {
        /*
            r8 = this;
            r0 = 0
            r1 = 0
        L2:
            boolean r2 = r9.hasNext()
            r3 = 0
            if (r2 == 0) goto Lb4
            char r2 = r9.peek()
            r4 = 42
            r5 = 2
            r6 = 47
            r7 = 1
            if (r1 != r7) goto L1e
            if (r2 == r4) goto L25
            if (r2 == r6) goto L1b
            goto La5
        L1b:
            r1 = -1
            goto La5
        L1e:
            if (r1 <= r7) goto L2e
            r3 = 3
            if (r2 == r4) goto L2b
            if (r2 == r6) goto L28
        L25:
            r1 = 2
            goto La5
        L28:
            if (r1 != r3) goto L25
            goto L39
        L2b:
            r1 = 3
            goto La5
        L2e:
            if (r1 >= 0) goto L3b
            r3 = 10
            if (r2 == r3) goto L39
            r3 = 13
            if (r2 == r3) goto L39
            goto La5
        L39:
            r1 = 0
            goto La5
        L3b:
            r4 = 34
            if (r2 == r4) goto Laf
            r4 = 45
            if (r2 == r4) goto Laa
            if (r2 == r6) goto La4
            r4 = 78
            if (r2 == r4) goto L9e
            r4 = 91
            if (r2 == r4) goto L99
            r4 = 102(0x66, float:1.43E-43)
            if (r2 == r4) goto L91
            r4 = 110(0x6e, float:1.54E-43)
            if (r2 == r4) goto L8b
            r4 = 123(0x7b, float:1.72E-43)
            if (r2 == r4) goto L86
            r4 = 116(0x74, float:1.63E-43)
            if (r2 == r4) goto L7e
            r4 = 117(0x75, float:1.64E-43)
            if (r2 == r4) goto L78
            boolean r3 = java.lang.Character.isDigit(r2)
            if (r3 == 0) goto L6c
            java.lang.Number r9 = r8.parseNumber(r9)
            return r9
        L6c:
            boolean r3 = java.lang.Character.isWhitespace(r2)
            if (r3 == 0) goto L73
            goto La5
        L73:
            java.lang.Object r9 = r8.handleUnknown(r9, r2)
            return r9
        L78:
            java.lang.String r0 = "undefined"
            complete(r0, r9)
            return r3
        L7e:
            java.lang.String r0 = "true"
            complete(r0, r9)
            java.lang.Boolean r9 = java.lang.Boolean.TRUE
            return r9
        L86:
            java.lang.Object r9 = r8.parseObject(r9)
            return r9
        L8b:
            java.lang.String r0 = "null"
            complete(r0, r9)
            return r3
        L91:
            java.lang.String r0 = "false"
            complete(r0, r9)
            java.lang.Boolean r9 = java.lang.Boolean.FALSE
            return r9
        L99:
            java.lang.Object r9 = r8.parseArray(r9)
            return r9
        L9e:
            java.lang.String r0 = "NaN"
            complete(r0, r9)
            return r3
        La4:
            r1 = 1
        La5:
            r9.next()
            goto L2
        Laa:
            java.lang.Number r9 = r8.parseNumber(r9)
            return r9
        Laf:
            java.lang.String r9 = r8.parseString(r9)
            return r9
        Lb4:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mortbay.util.ajax.JSON.parse(org.mortbay.util.ajax.JSON$Source):java.lang.Object");
    }

    protected Object handleUnknown(Source source, char c) {
        throw new IllegalStateException(new StringBuffer("unknown char '").append(c).append("'(").append((int) c).append(") in ").append(source).toString());
    }

    protected Object parseObject(Source source) {
        if (source.next() != '{') {
            throw new IllegalStateException();
        }
        Map newMap = newMap();
        char seekTo = seekTo("\"}", source);
        while (true) {
            if (!source.hasNext()) {
                break;
            }
            if (seekTo == '}') {
                source.next();
                break;
            }
            String parseString = parseString(source);
            seekTo(':', source);
            source.next();
            newMap.put(parseString, contextFor(parseString).parse(source));
            seekTo(",}", source);
            if (source.next() == '}') {
                break;
            }
            seekTo = seekTo("\"}", source);
        }
        String str = (String) newMap.get("class");
        if (str != null) {
            try {
                Class cls = class$org$mortbay$util$ajax$JSON;
                if (cls == null) {
                    cls = class$("org.mortbay.util.ajax.JSON");
                    class$org$mortbay$util$ajax$JSON = cls;
                }
                return convertTo(Loader.loadClass(cls, str), newMap);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return newMap;
    }

    protected Object parseArray(Source source) {
        if (source.next() != '[') {
            throw new IllegalStateException();
        }
        Object obj = null;
        ArrayList arrayList = null;
        boolean z = true;
        int i = 0;
        while (source.hasNext()) {
            char peek = source.peek();
            if (peek != ',') {
                if (peek == ']') {
                    source.next();
                    if (i == 0) {
                        return newArray(0);
                    }
                    if (i == 1) {
                        Object[] newArray = newArray(1);
                        Array.set(newArray, 0, obj);
                        return newArray;
                    }
                    return arrayList.toArray(newArray(arrayList.size()));
                }
                if (Character.isWhitespace(peek)) {
                    source.next();
                } else {
                    int i2 = i + 1;
                    if (i == 0) {
                        obj = contextForArray().parse(source);
                    } else {
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                            arrayList.add(obj);
                            arrayList.add(contextForArray().parse(source));
                        } else {
                            arrayList.add(contextForArray().parse(source));
                        }
                        obj = null;
                    }
                    i = i2;
                    z = false;
                }
            } else {
                if (z) {
                    throw new IllegalStateException();
                }
                source.next();
                z = true;
            }
        }
        throw new IllegalStateException("unexpected end of array");
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x0044, code lost:
    
        if (r3 == '\"') goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0046, code lost:
    
        if (r3 == '/') goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0048, code lost:
    
        if (r3 == '\\') goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x004a, code lost:
    
        if (r3 == 'b') goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x004c, code lost:
    
        if (r3 == 'f') goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x004e, code lost:
    
        if (r3 == 'n') goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0050, code lost:
    
        if (r3 == 'r') goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0052, code lost:
    
        if (r3 == 't') goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0054, code lost:
    
        if (r3 == 'u') goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0056, code lost:
    
        r13 = r4 + 1;
        r0[r4] = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x005b, code lost:
    
        r13 = r4 + 1;
        r0[r4] = (char) ((((org.mortbay.util.TypeUtil.convertHexDigit((byte) r21.next()) << com.google.common.base.Ascii.FF) + (org.mortbay.util.TypeUtil.convertHexDigit((byte) r21.next()) << 8)) + (org.mortbay.util.TypeUtil.convertHexDigit((byte) r21.next()) << 4)) + org.mortbay.util.TypeUtil.convertHexDigit((byte) r21.next()));
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x008f, code lost:
    
        r13 = r4 + 1;
        r0[r4] = '\t';
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0096, code lost:
    
        r13 = r4 + 1;
        r0[r4] = '\r';
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x009d, code lost:
    
        r13 = r4 + 1;
        r0[r4] = '\n';
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00a4, code lost:
    
        r13 = r4 + 1;
        r0[r4] = '\f';
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00a9, code lost:
    
        r13 = r4 + 1;
        r0[r4] = '\b';
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00b0, code lost:
    
        r13 = r4 + 1;
        r0[r4] = '\\';
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00b5, code lost:
    
        r13 = r4 + 1;
        r0[r4] = '/';
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00ba, code lost:
    
        r13 = r4 + 1;
        r0[r4] = kotlin.text.Typography.quote;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected java.lang.String parseString(org.mortbay.util.ajax.JSON.Source r21) {
        /*
            Method dump skipped, instructions count: 441
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mortbay.util.ajax.JSON.parseString(org.mortbay.util.ajax.JSON$Source):java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0067  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Number parseNumber(org.mortbay.util.ajax.JSON.Source r12) {
        /*
            r11 = this;
            r0 = 0
            r1 = 0
            r3 = r1
        L4:
            boolean r5 = r12.hasNext()
            r6 = 46
            r7 = 101(0x65, float:1.42E-43)
            r8 = 69
            r9 = 43
            r10 = 45
            if (r5 == 0) goto L59
            char r5 = r12.peek()
            if (r5 == r9) goto L48
            if (r5 == r8) goto L32
            if (r5 == r7) goto L32
            if (r5 == r10) goto L48
            if (r5 == r6) goto L32
            switch(r5) {
                case 48: goto L26;
                case 49: goto L26;
                case 50: goto L26;
                case 51: goto L26;
                case 52: goto L26;
                case 53: goto L26;
                case 54: goto L26;
                case 55: goto L26;
                case 56: goto L26;
                case 57: goto L26;
                default: goto L25;
            }
        L25:
            goto L59
        L26:
            r6 = 10
            long r3 = r3 * r6
            int r5 = r5 + (-48)
            long r5 = (long) r5
            long r3 = r3 + r5
            r12.next()
            goto L4
        L32:
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r2 = 16
            r1.<init>(r2)
            if (r0 == 0) goto L3e
            r1.append(r10)
        L3e:
            r1.append(r3)
            r1.append(r5)
            r12.next()
            goto L5a
        L48:
            int r0 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r0 != 0) goto L51
            r12.next()
            r0 = 1
            goto L4
        L51:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r0 = "bad number"
            r12.<init>(r0)
            throw r12
        L59:
            r1 = 0
        L5a:
            if (r1 != 0) goto L67
            if (r0 == 0) goto L62
            r0 = -1
            long r3 = r3 * r0
        L62:
            java.lang.Long r12 = org.mortbay.util.TypeUtil.newLong(r3)
            return r12
        L67:
            monitor-enter(r1)
        L68:
            boolean r0 = r12.hasNext()     // Catch: java.lang.Throwable -> L92
            if (r0 == 0) goto L87
            char r0 = r12.peek()     // Catch: java.lang.Throwable -> L92
            if (r0 == r9) goto L80
            if (r0 == r8) goto L80
            if (r0 == r7) goto L80
            if (r0 == r10) goto L80
            if (r0 == r6) goto L80
            switch(r0) {
                case 48: goto L80;
                case 49: goto L80;
                case 50: goto L80;
                case 51: goto L80;
                case 52: goto L80;
                case 53: goto L80;
                case 54: goto L80;
                case 55: goto L80;
                case 56: goto L80;
                case 57: goto L80;
                default: goto L7f;
            }     // Catch: java.lang.Throwable -> L92
        L7f:
            goto L87
        L80:
            r1.append(r0)     // Catch: java.lang.Throwable -> L92
            r12.next()     // Catch: java.lang.Throwable -> L92
            goto L68
        L87:
            java.lang.Double r12 = new java.lang.Double     // Catch: java.lang.Throwable -> L92
            java.lang.String r0 = r1.toString()     // Catch: java.lang.Throwable -> L92
            r12.<init>(r0)     // Catch: java.lang.Throwable -> L92
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L92
            return r12
        L92:
            r12 = move-exception
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L92
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mortbay.util.ajax.JSON.parseNumber(org.mortbay.util.ajax.JSON$Source):java.lang.Number");
    }

    protected void seekTo(char c, Source source) {
        while (source.hasNext()) {
            char peek = source.peek();
            if (peek == c) {
                return;
            }
            if (!Character.isWhitespace(peek)) {
                throw new IllegalStateException(new StringBuffer("Unexpected '").append(peek).append(" while seeking '").append(c).append("'").toString());
            }
            source.next();
        }
        throw new IllegalStateException(new StringBuffer("Expected '").append(c).append("'").toString());
    }

    protected char seekTo(String str, Source source) {
        while (source.hasNext()) {
            char peek = source.peek();
            if (str.indexOf(peek) >= 0) {
                return peek;
            }
            if (!Character.isWhitespace(peek)) {
                throw new IllegalStateException(new StringBuffer("Unexpected '").append(peek).append("' while seeking one of '").append(str).append("'").toString());
            }
            source.next();
        }
        throw new IllegalStateException(new StringBuffer("Expected one of '").append(str).append("'").toString());
    }

    protected static void complete(String str, Source source) {
        int i = 0;
        while (source.hasNext() && i < str.length()) {
            char next = source.next();
            int i2 = i + 1;
            if (next != str.charAt(i)) {
                throw new IllegalStateException(new StringBuffer("Unexpected '").append(next).append(" while seeking  \"").append(str).append("\"").toString());
            }
            i = i2;
        }
        if (i < str.length()) {
            throw new IllegalStateException(new StringBuffer("Expected \"").append(str).append("\"").toString());
        }
    }

    public static class StringSource implements Source {
        private int index;
        private char[] scratch;
        private final String string;

        public StringSource(String str) {
            this.string = str;
        }

        @Override // org.mortbay.util.ajax.JSON.Source
        public boolean hasNext() {
            if (this.index < this.string.length()) {
                return true;
            }
            this.scratch = null;
            return false;
        }

        @Override // org.mortbay.util.ajax.JSON.Source
        public char next() {
            String str = this.string;
            int i = this.index;
            this.index = i + 1;
            return str.charAt(i);
        }

        @Override // org.mortbay.util.ajax.JSON.Source
        public char peek() {
            return this.string.charAt(this.index);
        }

        public String toString() {
            return new StringBuffer().append(this.string.substring(0, this.index)).append("|||").append(this.string.substring(this.index)).toString();
        }

        @Override // org.mortbay.util.ajax.JSON.Source
        public char[] scratchBuffer() {
            if (this.scratch == null) {
                this.scratch = new char[this.string.length()];
            }
            return this.scratch;
        }
    }

    public static class ReaderSource implements Source {
        private int _next = -1;
        private Reader _reader;
        private char[] scratch;

        public ReaderSource(Reader reader) {
            this._reader = reader;
        }

        public void setReader(Reader reader) {
            this._reader = reader;
            this._next = -1;
        }

        @Override // org.mortbay.util.ajax.JSON.Source
        public boolean hasNext() {
            getNext();
            if (this._next >= 0) {
                return true;
            }
            this.scratch = null;
            return false;
        }

        @Override // org.mortbay.util.ajax.JSON.Source
        public char next() {
            getNext();
            char c = (char) this._next;
            this._next = -1;
            return c;
        }

        @Override // org.mortbay.util.ajax.JSON.Source
        public char peek() {
            getNext();
            return (char) this._next;
        }

        private void getNext() {
            if (this._next < 0) {
                try {
                    this._next = this._reader.read();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        @Override // org.mortbay.util.ajax.JSON.Source
        public char[] scratchBuffer() {
            if (this.scratch == null) {
                this.scratch = new char[1024];
            }
            return this.scratch;
        }
    }

    public static class Literal implements Generator {
        private String _json;

        public Literal(String str) {
            if (Log.isDebugEnabled()) {
                JSON.parse(str);
            }
            this._json = str;
        }

        public String toString() {
            return this._json;
        }

        @Override // org.mortbay.util.ajax.JSON.Generator
        public void addJSON(StringBuffer stringBuffer) {
            stringBuffer.append(this._json);
        }
    }
}
