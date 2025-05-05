package com.google.api.client.json;

import com.google.api.client.json.JsonPolymorphicTypeMap;
import com.google.api.client.util.ClassInfo;
import com.google.api.client.util.Data;
import com.google.api.client.util.FieldInfo;
import com.google.api.client.util.GenericData;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Sets;
import com.google.api.client.util.Types;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: classes2.dex */
public abstract class JsonParser {
    private static WeakHashMap<Class<?>, Field> cachedTypemapFields = new WeakHashMap<>();
    private static final Lock lock = new ReentrantLock();

    public abstract void close() throws IOException;

    public abstract BigInteger getBigIntegerValue() throws IOException;

    public abstract byte getByteValue() throws IOException;

    public abstract String getCurrentName() throws IOException;

    public abstract JsonToken getCurrentToken();

    public abstract BigDecimal getDecimalValue() throws IOException;

    public abstract double getDoubleValue() throws IOException;

    public abstract JsonFactory getFactory();

    public abstract float getFloatValue() throws IOException;

    public abstract int getIntValue() throws IOException;

    public abstract long getLongValue() throws IOException;

    public abstract short getShortValue() throws IOException;

    public abstract String getText() throws IOException;

    public abstract JsonToken nextToken() throws IOException;

    public abstract JsonParser skipChildren() throws IOException;

    public final <T> T parseAndClose(Class<T> cls) throws IOException {
        return (T) parseAndClose((Class) cls, (CustomizeJsonParser) null);
    }

    public final <T> T parseAndClose(Class<T> cls, CustomizeJsonParser customizeJsonParser) throws IOException {
        try {
            return (T) parse((Class) cls, customizeJsonParser);
        } finally {
            close();
        }
    }

    public final void skipToKey(String str) throws IOException {
        skipToKey(Collections.singleton(str));
    }

    public final String skipToKey(Set<String> set) throws IOException {
        JsonToken startParsingObjectOrArray = startParsingObjectOrArray();
        while (startParsingObjectOrArray == JsonToken.FIELD_NAME) {
            String text = getText();
            nextToken();
            if (set.contains(text)) {
                return text;
            }
            skipChildren();
            startParsingObjectOrArray = nextToken();
        }
        return null;
    }

    private JsonToken startParsing() throws IOException {
        JsonToken currentToken = getCurrentToken();
        if (currentToken == null) {
            currentToken = nextToken();
        }
        Preconditions.checkArgument(currentToken != null, "no JSON input found");
        return currentToken;
    }

    /* renamed from: com.google.api.client.json.JsonParser$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$api$client$json$JsonToken;

        static {
            int[] iArr = new int[JsonToken.values().length];
            $SwitchMap$com$google$api$client$json$JsonToken = iArr;
            try {
                iArr[JsonToken.START_OBJECT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$api$client$json$JsonToken[JsonToken.START_ARRAY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$api$client$json$JsonToken[JsonToken.END_ARRAY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$api$client$json$JsonToken[JsonToken.FIELD_NAME.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$api$client$json$JsonToken[JsonToken.END_OBJECT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$api$client$json$JsonToken[JsonToken.VALUE_TRUE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$api$client$json$JsonToken[JsonToken.VALUE_FALSE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$google$api$client$json$JsonToken[JsonToken.VALUE_NUMBER_FLOAT.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$google$api$client$json$JsonToken[JsonToken.VALUE_NUMBER_INT.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$google$api$client$json$JsonToken[JsonToken.VALUE_STRING.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$google$api$client$json$JsonToken[JsonToken.VALUE_NULL.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
        }
    }

    private JsonToken startParsingObjectOrArray() throws IOException {
        JsonToken startParsing = startParsing();
        int i = AnonymousClass1.$SwitchMap$com$google$api$client$json$JsonToken[startParsing.ordinal()];
        boolean z = true;
        if (i != 1) {
            return i != 2 ? startParsing : nextToken();
        }
        JsonToken nextToken = nextToken();
        if (nextToken != JsonToken.FIELD_NAME && nextToken != JsonToken.END_OBJECT) {
            z = false;
        }
        Preconditions.checkArgument(z, nextToken);
        return nextToken;
    }

    public final void parseAndClose(Object obj) throws IOException {
        parseAndClose(obj, (CustomizeJsonParser) null);
    }

    public final void parseAndClose(Object obj, CustomizeJsonParser customizeJsonParser) throws IOException {
        try {
            parse(obj, customizeJsonParser);
        } finally {
            close();
        }
    }

    public final <T> T parse(Class<T> cls) throws IOException {
        return (T) parse((Class) cls, (CustomizeJsonParser) null);
    }

    public final <T> T parse(Class<T> cls, CustomizeJsonParser customizeJsonParser) throws IOException {
        return (T) parse((Type) cls, false, customizeJsonParser);
    }

    public Object parse(Type type, boolean z) throws IOException {
        return parse(type, z, (CustomizeJsonParser) null);
    }

    public Object parse(Type type, boolean z, CustomizeJsonParser customizeJsonParser) throws IOException {
        try {
            if (!Void.class.equals(type)) {
                startParsing();
            }
            return parseValue(null, type, new ArrayList<>(), null, customizeJsonParser, true);
        } finally {
            if (z) {
                close();
            }
        }
    }

    public final void parse(Object obj) throws IOException {
        parse(obj, (CustomizeJsonParser) null);
    }

    public final void parse(Object obj, CustomizeJsonParser customizeJsonParser) throws IOException {
        ArrayList<Type> arrayList = new ArrayList<>();
        arrayList.add(obj.getClass());
        parse(arrayList, obj, customizeJsonParser);
    }

    private void parse(ArrayList<Type> arrayList, Object obj, CustomizeJsonParser customizeJsonParser) throws IOException {
        if (obj instanceof GenericJson) {
            ((GenericJson) obj).setFactory(getFactory());
        }
        JsonToken startParsingObjectOrArray = startParsingObjectOrArray();
        Class<?> cls = obj.getClass();
        ClassInfo of = ClassInfo.of(cls);
        boolean isAssignableFrom = GenericData.class.isAssignableFrom(cls);
        if (!isAssignableFrom && Map.class.isAssignableFrom(cls)) {
            parseMap(null, (Map) obj, Types.getMapValueParameter(cls), arrayList, customizeJsonParser);
            return;
        }
        while (startParsingObjectOrArray == JsonToken.FIELD_NAME) {
            String text = getText();
            nextToken();
            if (customizeJsonParser != null && customizeJsonParser.stopAt(obj, text)) {
                return;
            }
            FieldInfo fieldInfo = of.getFieldInfo(text);
            if (fieldInfo != null) {
                if (fieldInfo.isFinal() && !fieldInfo.isPrimitive()) {
                    throw new IllegalArgumentException("final array/object fields are not supported");
                }
                Field field = fieldInfo.getField();
                int size = arrayList.size();
                arrayList.add(field.getGenericType());
                Object parseValue = parseValue(field, fieldInfo.getGenericType(), arrayList, obj, customizeJsonParser, true);
                arrayList.remove(size);
                fieldInfo.setValue(obj, parseValue);
            } else if (isAssignableFrom) {
                ((GenericData) obj).set(text, parseValue(null, null, arrayList, obj, customizeJsonParser, true));
            } else {
                if (customizeJsonParser != null) {
                    customizeJsonParser.handleUnrecognizedKey(obj, text);
                }
                skipChildren();
            }
            startParsingObjectOrArray = nextToken();
        }
    }

    public final <T> Collection<T> parseArrayAndClose(Class<?> cls, Class<T> cls2) throws IOException {
        return parseArrayAndClose(cls, cls2, (CustomizeJsonParser) null);
    }

    public final <T> Collection<T> parseArrayAndClose(Class<?> cls, Class<T> cls2, CustomizeJsonParser customizeJsonParser) throws IOException {
        try {
            return parseArray(cls, cls2, customizeJsonParser);
        } finally {
            close();
        }
    }

    public final <T> void parseArrayAndClose(Collection<? super T> collection, Class<T> cls) throws IOException {
        parseArrayAndClose(collection, cls, (CustomizeJsonParser) null);
    }

    public final <T> void parseArrayAndClose(Collection<? super T> collection, Class<T> cls, CustomizeJsonParser customizeJsonParser) throws IOException {
        try {
            parseArray(collection, cls, customizeJsonParser);
        } finally {
            close();
        }
    }

    public final <T> Collection<T> parseArray(Class<?> cls, Class<T> cls2) throws IOException {
        return parseArray(cls, cls2, (CustomizeJsonParser) null);
    }

    public final <T> Collection<T> parseArray(Class<?> cls, Class<T> cls2, CustomizeJsonParser customizeJsonParser) throws IOException {
        Collection<T> collection = (Collection<T>) Data.newCollectionInstance(cls);
        parseArray(collection, cls2, customizeJsonParser);
        return collection;
    }

    public final <T> void parseArray(Collection<? super T> collection, Class<T> cls) throws IOException {
        parseArray(collection, cls, (CustomizeJsonParser) null);
    }

    public final <T> void parseArray(Collection<? super T> collection, Class<T> cls, CustomizeJsonParser customizeJsonParser) throws IOException {
        parseArray(null, collection, cls, new ArrayList<>(), customizeJsonParser);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <T> void parseArray(Field field, Collection<T> collection, Type type, ArrayList<Type> arrayList, CustomizeJsonParser customizeJsonParser) throws IOException {
        JsonToken startParsingObjectOrArray = startParsingObjectOrArray();
        while (startParsingObjectOrArray != JsonToken.END_ARRAY) {
            collection.add(parseValue(field, type, arrayList, collection, customizeJsonParser, true));
            startParsingObjectOrArray = nextToken();
        }
    }

    private void parseMap(Field field, Map<String, Object> map, Type type, ArrayList<Type> arrayList, CustomizeJsonParser customizeJsonParser) throws IOException {
        JsonToken startParsingObjectOrArray = startParsingObjectOrArray();
        while (startParsingObjectOrArray == JsonToken.FIELD_NAME) {
            String text = getText();
            nextToken();
            if (customizeJsonParser != null && customizeJsonParser.stopAt(map, text)) {
                return;
            }
            map.put(text, parseValue(field, type, arrayList, map, customizeJsonParser, true));
            startParsingObjectOrArray = nextToken();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:130:0x01bc A[Catch: IllegalArgumentException -> 0x0335, TryCatch #0 {IllegalArgumentException -> 0x0335, blocks: (B:14:0x0025, B:15:0x0033, B:16:0x0036, B:17:0x0312, B:18:0x0334, B:20:0x003c, B:22:0x0043, B:24:0x004a, B:26:0x0052, B:28:0x005a, B:30:0x0067, B:32:0x006f, B:34:0x007c, B:37:0x0085, B:41:0x009b, B:46:0x00bd, B:49:0x00c7, B:51:0x00d0, B:52:0x00d5, B:55:0x00a3, B:57:0x00ab, B:59:0x00b3, B:62:0x00e0, B:64:0x00e9, B:66:0x00f0, B:71:0x00fe, B:75:0x0107, B:80:0x0111, B:85:0x011b, B:90:0x0124, B:95:0x012d, B:100:0x0136, B:103:0x013b, B:104:0x015f, B:105:0x0160, B:107:0x0169, B:109:0x0172, B:111:0x017b, B:113:0x0184, B:115:0x018d, B:117:0x0196, B:121:0x019d, B:124:0x01a3, B:128:0x01af, B:130:0x01bc, B:132:0x01bf, B:135:0x01c2, B:139:0x01cc, B:143:0x01d8, B:146:0x01e5, B:148:0x01ed, B:150:0x01f3, B:151:0x0206, B:153:0x0215, B:157:0x01fa, B:159:0x0202, B:162:0x021f, B:164:0x022f, B:167:0x0239, B:169:0x0241, B:173:0x024e, B:174:0x0264, B:176:0x026a, B:178:0x026f, B:180:0x0277, B:182:0x027f, B:184:0x0288, B:187:0x0294, B:189:0x0299, B:192:0x029f, B:195:0x02af, B:197:0x02c8, B:201:0x02d4, B:199:0x02d9, B:205:0x02e0, B:207:0x02ec, B:208:0x02f6, B:210:0x02f1, B:217:0x025b, B:218:0x0260), top: B:13:0x0025 }] */
    /* JADX WARN: Removed duplicated region for block: B:132:0x01bf A[Catch: IllegalArgumentException -> 0x0335, TryCatch #0 {IllegalArgumentException -> 0x0335, blocks: (B:14:0x0025, B:15:0x0033, B:16:0x0036, B:17:0x0312, B:18:0x0334, B:20:0x003c, B:22:0x0043, B:24:0x004a, B:26:0x0052, B:28:0x005a, B:30:0x0067, B:32:0x006f, B:34:0x007c, B:37:0x0085, B:41:0x009b, B:46:0x00bd, B:49:0x00c7, B:51:0x00d0, B:52:0x00d5, B:55:0x00a3, B:57:0x00ab, B:59:0x00b3, B:62:0x00e0, B:64:0x00e9, B:66:0x00f0, B:71:0x00fe, B:75:0x0107, B:80:0x0111, B:85:0x011b, B:90:0x0124, B:95:0x012d, B:100:0x0136, B:103:0x013b, B:104:0x015f, B:105:0x0160, B:107:0x0169, B:109:0x0172, B:111:0x017b, B:113:0x0184, B:115:0x018d, B:117:0x0196, B:121:0x019d, B:124:0x01a3, B:128:0x01af, B:130:0x01bc, B:132:0x01bf, B:135:0x01c2, B:139:0x01cc, B:143:0x01d8, B:146:0x01e5, B:148:0x01ed, B:150:0x01f3, B:151:0x0206, B:153:0x0215, B:157:0x01fa, B:159:0x0202, B:162:0x021f, B:164:0x022f, B:167:0x0239, B:169:0x0241, B:173:0x024e, B:174:0x0264, B:176:0x026a, B:178:0x026f, B:180:0x0277, B:182:0x027f, B:184:0x0288, B:187:0x0294, B:189:0x0299, B:192:0x029f, B:195:0x02af, B:197:0x02c8, B:201:0x02d4, B:199:0x02d9, B:205:0x02e0, B:207:0x02ec, B:208:0x02f6, B:210:0x02f1, B:217:0x025b, B:218:0x0260), top: B:13:0x0025 }] */
    /* JADX WARN: Removed duplicated region for block: B:148:0x01ed A[Catch: IllegalArgumentException -> 0x0335, TryCatch #0 {IllegalArgumentException -> 0x0335, blocks: (B:14:0x0025, B:15:0x0033, B:16:0x0036, B:17:0x0312, B:18:0x0334, B:20:0x003c, B:22:0x0043, B:24:0x004a, B:26:0x0052, B:28:0x005a, B:30:0x0067, B:32:0x006f, B:34:0x007c, B:37:0x0085, B:41:0x009b, B:46:0x00bd, B:49:0x00c7, B:51:0x00d0, B:52:0x00d5, B:55:0x00a3, B:57:0x00ab, B:59:0x00b3, B:62:0x00e0, B:64:0x00e9, B:66:0x00f0, B:71:0x00fe, B:75:0x0107, B:80:0x0111, B:85:0x011b, B:90:0x0124, B:95:0x012d, B:100:0x0136, B:103:0x013b, B:104:0x015f, B:105:0x0160, B:107:0x0169, B:109:0x0172, B:111:0x017b, B:113:0x0184, B:115:0x018d, B:117:0x0196, B:121:0x019d, B:124:0x01a3, B:128:0x01af, B:130:0x01bc, B:132:0x01bf, B:135:0x01c2, B:139:0x01cc, B:143:0x01d8, B:146:0x01e5, B:148:0x01ed, B:150:0x01f3, B:151:0x0206, B:153:0x0215, B:157:0x01fa, B:159:0x0202, B:162:0x021f, B:164:0x022f, B:167:0x0239, B:169:0x0241, B:173:0x024e, B:174:0x0264, B:176:0x026a, B:178:0x026f, B:180:0x0277, B:182:0x027f, B:184:0x0288, B:187:0x0294, B:189:0x0299, B:192:0x029f, B:195:0x02af, B:197:0x02c8, B:201:0x02d4, B:199:0x02d9, B:205:0x02e0, B:207:0x02ec, B:208:0x02f6, B:210:0x02f1, B:217:0x025b, B:218:0x0260), top: B:13:0x0025 }] */
    /* JADX WARN: Removed duplicated region for block: B:150:0x01f3 A[Catch: IllegalArgumentException -> 0x0335, TryCatch #0 {IllegalArgumentException -> 0x0335, blocks: (B:14:0x0025, B:15:0x0033, B:16:0x0036, B:17:0x0312, B:18:0x0334, B:20:0x003c, B:22:0x0043, B:24:0x004a, B:26:0x0052, B:28:0x005a, B:30:0x0067, B:32:0x006f, B:34:0x007c, B:37:0x0085, B:41:0x009b, B:46:0x00bd, B:49:0x00c7, B:51:0x00d0, B:52:0x00d5, B:55:0x00a3, B:57:0x00ab, B:59:0x00b3, B:62:0x00e0, B:64:0x00e9, B:66:0x00f0, B:71:0x00fe, B:75:0x0107, B:80:0x0111, B:85:0x011b, B:90:0x0124, B:95:0x012d, B:100:0x0136, B:103:0x013b, B:104:0x015f, B:105:0x0160, B:107:0x0169, B:109:0x0172, B:111:0x017b, B:113:0x0184, B:115:0x018d, B:117:0x0196, B:121:0x019d, B:124:0x01a3, B:128:0x01af, B:130:0x01bc, B:132:0x01bf, B:135:0x01c2, B:139:0x01cc, B:143:0x01d8, B:146:0x01e5, B:148:0x01ed, B:150:0x01f3, B:151:0x0206, B:153:0x0215, B:157:0x01fa, B:159:0x0202, B:162:0x021f, B:164:0x022f, B:167:0x0239, B:169:0x0241, B:173:0x024e, B:174:0x0264, B:176:0x026a, B:178:0x026f, B:180:0x0277, B:182:0x027f, B:184:0x0288, B:187:0x0294, B:189:0x0299, B:192:0x029f, B:195:0x02af, B:197:0x02c8, B:201:0x02d4, B:199:0x02d9, B:205:0x02e0, B:207:0x02ec, B:208:0x02f6, B:210:0x02f1, B:217:0x025b, B:218:0x0260), top: B:13:0x0025 }] */
    /* JADX WARN: Removed duplicated region for block: B:153:0x0215 A[Catch: IllegalArgumentException -> 0x0335, TryCatch #0 {IllegalArgumentException -> 0x0335, blocks: (B:14:0x0025, B:15:0x0033, B:16:0x0036, B:17:0x0312, B:18:0x0334, B:20:0x003c, B:22:0x0043, B:24:0x004a, B:26:0x0052, B:28:0x005a, B:30:0x0067, B:32:0x006f, B:34:0x007c, B:37:0x0085, B:41:0x009b, B:46:0x00bd, B:49:0x00c7, B:51:0x00d0, B:52:0x00d5, B:55:0x00a3, B:57:0x00ab, B:59:0x00b3, B:62:0x00e0, B:64:0x00e9, B:66:0x00f0, B:71:0x00fe, B:75:0x0107, B:80:0x0111, B:85:0x011b, B:90:0x0124, B:95:0x012d, B:100:0x0136, B:103:0x013b, B:104:0x015f, B:105:0x0160, B:107:0x0169, B:109:0x0172, B:111:0x017b, B:113:0x0184, B:115:0x018d, B:117:0x0196, B:121:0x019d, B:124:0x01a3, B:128:0x01af, B:130:0x01bc, B:132:0x01bf, B:135:0x01c2, B:139:0x01cc, B:143:0x01d8, B:146:0x01e5, B:148:0x01ed, B:150:0x01f3, B:151:0x0206, B:153:0x0215, B:157:0x01fa, B:159:0x0202, B:162:0x021f, B:164:0x022f, B:167:0x0239, B:169:0x0241, B:173:0x024e, B:174:0x0264, B:176:0x026a, B:178:0x026f, B:180:0x0277, B:182:0x027f, B:184:0x0288, B:187:0x0294, B:189:0x0299, B:192:0x029f, B:195:0x02af, B:197:0x02c8, B:201:0x02d4, B:199:0x02d9, B:205:0x02e0, B:207:0x02ec, B:208:0x02f6, B:210:0x02f1, B:217:0x025b, B:218:0x0260), top: B:13:0x0025 }] */
    /* JADX WARN: Removed duplicated region for block: B:155:0x021e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:156:0x01f8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final java.lang.Object parseValue(java.lang.reflect.Field r8, java.lang.reflect.Type r9, java.util.ArrayList<java.lang.reflect.Type> r10, java.lang.Object r11, com.google.api.client.json.CustomizeJsonParser r12, boolean r13) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 894
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.api.client.json.JsonParser.parseValue(java.lang.reflect.Field, java.lang.reflect.Type, java.util.ArrayList, java.lang.Object, com.google.api.client.json.CustomizeJsonParser, boolean):java.lang.Object");
    }

    private static Field getCachedTypemapFieldFor(Class<?> cls) {
        Field field = null;
        if (cls == null) {
            return null;
        }
        Lock lock2 = lock;
        lock2.lock();
        try {
            if (cachedTypemapFields.containsKey(cls)) {
                Field field2 = cachedTypemapFields.get(cls);
                lock2.unlock();
                return field2;
            }
            Iterator<FieldInfo> it = ClassInfo.of(cls).getFieldInfos().iterator();
            while (it.hasNext()) {
                Field field3 = it.next().getField();
                JsonPolymorphicTypeMap jsonPolymorphicTypeMap = (JsonPolymorphicTypeMap) field3.getAnnotation(JsonPolymorphicTypeMap.class);
                if (jsonPolymorphicTypeMap != null) {
                    Preconditions.checkArgument(field == null, "Class contains more than one field with @JsonPolymorphicTypeMap annotation: %s", cls);
                    Preconditions.checkArgument(Data.isPrimitive(field3.getType()), "Field which has the @JsonPolymorphicTypeMap, %s, is not a supported type: %s", cls, field3.getType());
                    JsonPolymorphicTypeMap.TypeDef[] typeDefinitions = jsonPolymorphicTypeMap.typeDefinitions();
                    HashSet newHashSet = Sets.newHashSet();
                    Preconditions.checkArgument(typeDefinitions.length > 0, "@JsonPolymorphicTypeMap must have at least one @TypeDef");
                    for (JsonPolymorphicTypeMap.TypeDef typeDef : typeDefinitions) {
                        Preconditions.checkArgument(newHashSet.add(typeDef.key()), "Class contains two @TypeDef annotations with identical key: %s", typeDef.key());
                    }
                    field = field3;
                }
            }
            cachedTypemapFields.put(cls, field);
            return field;
        } finally {
            lock.unlock();
        }
    }
}
