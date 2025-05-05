package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

/* loaded from: classes2.dex */
public final class TypeAdapters {
    public static final TypeAdapter<AtomicBoolean> ATOMIC_BOOLEAN;
    public static final TypeAdapterFactory ATOMIC_BOOLEAN_FACTORY;
    public static final TypeAdapter<AtomicInteger> ATOMIC_INTEGER;
    public static final TypeAdapter<AtomicIntegerArray> ATOMIC_INTEGER_ARRAY;
    public static final TypeAdapterFactory ATOMIC_INTEGER_ARRAY_FACTORY;
    public static final TypeAdapterFactory ATOMIC_INTEGER_FACTORY;
    public static final TypeAdapter<BigDecimal> BIG_DECIMAL;
    public static final TypeAdapter<BigInteger> BIG_INTEGER;
    public static final TypeAdapter<BitSet> BIT_SET;
    public static final TypeAdapterFactory BIT_SET_FACTORY;
    public static final TypeAdapter<Boolean> BOOLEAN;
    public static final TypeAdapter<Boolean> BOOLEAN_AS_STRING;
    public static final TypeAdapterFactory BOOLEAN_FACTORY;
    public static final TypeAdapter<Number> BYTE;
    public static final TypeAdapterFactory BYTE_FACTORY;
    public static final TypeAdapter<Calendar> CALENDAR;
    public static final TypeAdapterFactory CALENDAR_FACTORY;
    public static final TypeAdapter<Character> CHARACTER;
    public static final TypeAdapterFactory CHARACTER_FACTORY;
    public static final TypeAdapter<Class> CLASS;
    public static final TypeAdapterFactory CLASS_FACTORY;
    public static final TypeAdapter<Currency> CURRENCY;
    public static final TypeAdapterFactory CURRENCY_FACTORY;
    public static final TypeAdapter<Number> DOUBLE;
    public static final TypeAdapterFactory ENUM_FACTORY;
    public static final TypeAdapter<Number> FLOAT;
    public static final TypeAdapter<InetAddress> INET_ADDRESS;
    public static final TypeAdapterFactory INET_ADDRESS_FACTORY;
    public static final TypeAdapter<Number> INTEGER;
    public static final TypeAdapterFactory INTEGER_FACTORY;
    public static final TypeAdapter<JsonElement> JSON_ELEMENT;
    public static final TypeAdapterFactory JSON_ELEMENT_FACTORY;
    public static final TypeAdapter<Locale> LOCALE;
    public static final TypeAdapterFactory LOCALE_FACTORY;
    public static final TypeAdapter<Number> LONG;
    public static final TypeAdapter<Number> NUMBER;
    public static final TypeAdapterFactory NUMBER_FACTORY;
    public static final TypeAdapter<Number> SHORT;
    public static final TypeAdapterFactory SHORT_FACTORY;
    public static final TypeAdapter<String> STRING;
    public static final TypeAdapter<StringBuffer> STRING_BUFFER;
    public static final TypeAdapterFactory STRING_BUFFER_FACTORY;
    public static final TypeAdapter<StringBuilder> STRING_BUILDER;
    public static final TypeAdapterFactory STRING_BUILDER_FACTORY;
    public static final TypeAdapterFactory STRING_FACTORY;
    public static final TypeAdapterFactory TIMESTAMP_FACTORY;
    public static final TypeAdapter<URI> URI;
    public static final TypeAdapterFactory URI_FACTORY;
    public static final TypeAdapter<URL> URL;
    public static final TypeAdapterFactory URL_FACTORY;
    public static final TypeAdapter<UUID> UUID;
    public static final TypeAdapterFactory UUID_FACTORY;

    private TypeAdapters() {
        throw new UnsupportedOperationException();
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$1 */
    class AnonymousClass1 extends TypeAdapter<Class> {
        AnonymousClass1() {
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Class cls) throws IOException {
            throw new UnsupportedOperationException("Attempted to serialize java.lang.Class: " + cls.getName() + ". Forgot to register a type adapter?");
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public Class read2(JsonReader jsonReader) throws IOException {
            throw new UnsupportedOperationException("Attempted to deserialize a java.lang.Class. Forgot to register a type adapter?");
        }
    }

    static {
        TypeAdapter<Class> nullSafe = new TypeAdapter<Class>() { // from class: com.google.gson.internal.bind.TypeAdapters.1
            AnonymousClass1() {
            }

            @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter, Class cls) throws IOException {
                throw new UnsupportedOperationException("Attempted to serialize java.lang.Class: " + cls.getName() + ". Forgot to register a type adapter?");
            }

            @Override // com.google.gson.TypeAdapter
            /* renamed from: read */
            public Class read2(JsonReader jsonReader) throws IOException {
                throw new UnsupportedOperationException("Attempted to deserialize a java.lang.Class. Forgot to register a type adapter?");
            }
        }.nullSafe();
        CLASS = nullSafe;
        CLASS_FACTORY = newFactory(Class.class, nullSafe);
        TypeAdapter<BitSet> nullSafe2 = new TypeAdapter<BitSet>() { // from class: com.google.gson.internal.bind.TypeAdapters.2
            AnonymousClass2() {
            }

            /* JADX WARN: Code restructure failed: missing block: B:13:0x002b, code lost:
            
                if (java.lang.Integer.parseInt(r1) != 0) goto L57;
             */
            /* JADX WARN: Code restructure failed: missing block: B:14:0x002e, code lost:
            
                r5 = false;
             */
            /* JADX WARN: Code restructure failed: missing block: B:29:0x0061, code lost:
            
                if (r8.nextInt() != 0) goto L57;
             */
            @Override // com.google.gson.TypeAdapter
            /* renamed from: read */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public java.util.BitSet read2(com.google.gson.stream.JsonReader r8) throws java.io.IOException {
                /*
                    r7 = this;
                    java.util.BitSet r0 = new java.util.BitSet
                    r0.<init>()
                    r8.beginArray()
                    com.google.gson.stream.JsonToken r1 = r8.peek()
                    r2 = 0
                    r3 = 0
                Le:
                    com.google.gson.stream.JsonToken r4 = com.google.gson.stream.JsonToken.END_ARRAY
                    if (r1 == r4) goto L6f
                    int[] r4 = com.google.gson.internal.bind.TypeAdapters.AnonymousClass36.$SwitchMap$com$google$gson$stream$JsonToken
                    int r5 = r1.ordinal()
                    r4 = r4[r5]
                    r5 = 1
                    if (r4 == r5) goto L5d
                    r6 = 2
                    if (r4 == r6) goto L58
                    r6 = 3
                    if (r4 != r6) goto L44
                    java.lang.String r1 = r8.nextString()
                    int r1 = java.lang.Integer.parseInt(r1)     // Catch: java.lang.NumberFormatException -> L30
                    if (r1 == 0) goto L2e
                    goto L63
                L2e:
                    r5 = 0
                    goto L63
                L30:
                    com.google.gson.JsonSyntaxException r8 = new com.google.gson.JsonSyntaxException
                    java.lang.StringBuilder r0 = new java.lang.StringBuilder
                    java.lang.String r2 = "Error: Expecting: bitset number value (1, 0), Found: "
                    r0.<init>(r2)
                    r0.append(r1)
                    java.lang.String r0 = r0.toString()
                    r8.<init>(r0)
                    throw r8
                L44:
                    com.google.gson.JsonSyntaxException r8 = new com.google.gson.JsonSyntaxException
                    java.lang.StringBuilder r0 = new java.lang.StringBuilder
                    java.lang.String r2 = "Invalid bitset value type: "
                    r0.<init>(r2)
                    r0.append(r1)
                    java.lang.String r0 = r0.toString()
                    r8.<init>(r0)
                    throw r8
                L58:
                    boolean r5 = r8.nextBoolean()
                    goto L63
                L5d:
                    int r1 = r8.nextInt()
                    if (r1 == 0) goto L2e
                L63:
                    if (r5 == 0) goto L68
                    r0.set(r3)
                L68:
                    int r3 = r3 + 1
                    com.google.gson.stream.JsonToken r1 = r8.peek()
                    goto Le
                L6f:
                    r8.endArray()
                    return r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.bind.TypeAdapters.AnonymousClass2.read2(com.google.gson.stream.JsonReader):java.util.BitSet");
            }

            @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter, BitSet bitSet) throws IOException {
                jsonWriter.beginArray();
                int length = bitSet.length();
                for (int i = 0; i < length; i++) {
                    jsonWriter.value(bitSet.get(i) ? 1L : 0L);
                }
                jsonWriter.endArray();
            }
        }.nullSafe();
        BIT_SET = nullSafe2;
        BIT_SET_FACTORY = newFactory(BitSet.class, nullSafe2);
        AnonymousClass3 anonymousClass3 = new TypeAdapter<Boolean>() { // from class: com.google.gson.internal.bind.TypeAdapters.3
            AnonymousClass3() {
            }

            @Override // com.google.gson.TypeAdapter
            /* renamed from: read */
            public Boolean read2(JsonReader jsonReader) throws IOException {
                JsonToken peek = jsonReader.peek();
                if (peek == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
                if (peek == JsonToken.STRING) {
                    return Boolean.valueOf(Boolean.parseBoolean(jsonReader.nextString()));
                }
                return Boolean.valueOf(jsonReader.nextBoolean());
            }

            @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter, Boolean bool) throws IOException {
                jsonWriter.value(bool);
            }
        };
        BOOLEAN = anonymousClass3;
        BOOLEAN_AS_STRING = new TypeAdapter<Boolean>() { // from class: com.google.gson.internal.bind.TypeAdapters.4
            AnonymousClass4() {
            }

            @Override // com.google.gson.TypeAdapter
            /* renamed from: read */
            public Boolean read2(JsonReader jsonReader) throws IOException {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
                return Boolean.valueOf(jsonReader.nextString());
            }

            @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter, Boolean bool) throws IOException {
                jsonWriter.value(bool == null ? "null" : bool.toString());
            }
        };
        BOOLEAN_FACTORY = newFactory(Boolean.TYPE, Boolean.class, anonymousClass3);
        AnonymousClass5 anonymousClass5 = new TypeAdapter<Number>() { // from class: com.google.gson.internal.bind.TypeAdapters.5
            AnonymousClass5() {
            }

            @Override // com.google.gson.TypeAdapter
            /* renamed from: read */
            public Number read2(JsonReader jsonReader) throws IOException {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
                try {
                    return Byte.valueOf((byte) jsonReader.nextInt());
                } catch (NumberFormatException e) {
                    throw new JsonSyntaxException(e);
                }
            }

            @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter, Number number) throws IOException {
                jsonWriter.value(number);
            }
        };
        BYTE = anonymousClass5;
        BYTE_FACTORY = newFactory(Byte.TYPE, Byte.class, anonymousClass5);
        AnonymousClass6 anonymousClass6 = new TypeAdapter<Number>() { // from class: com.google.gson.internal.bind.TypeAdapters.6
            AnonymousClass6() {
            }

            @Override // com.google.gson.TypeAdapter
            /* renamed from: read */
            public Number read2(JsonReader jsonReader) throws IOException {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
                try {
                    return Short.valueOf((short) jsonReader.nextInt());
                } catch (NumberFormatException e) {
                    throw new JsonSyntaxException(e);
                }
            }

            @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter, Number number) throws IOException {
                jsonWriter.value(number);
            }
        };
        SHORT = anonymousClass6;
        SHORT_FACTORY = newFactory(Short.TYPE, Short.class, anonymousClass6);
        AnonymousClass7 anonymousClass7 = new TypeAdapter<Number>() { // from class: com.google.gson.internal.bind.TypeAdapters.7
            AnonymousClass7() {
            }

            @Override // com.google.gson.TypeAdapter
            /* renamed from: read */
            public Number read2(JsonReader jsonReader) throws IOException {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
                try {
                    return Integer.valueOf(jsonReader.nextInt());
                } catch (NumberFormatException e) {
                    throw new JsonSyntaxException(e);
                }
            }

            @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter, Number number) throws IOException {
                jsonWriter.value(number);
            }
        };
        INTEGER = anonymousClass7;
        INTEGER_FACTORY = newFactory(Integer.TYPE, Integer.class, anonymousClass7);
        TypeAdapter<AtomicInteger> nullSafe3 = new TypeAdapter<AtomicInteger>() { // from class: com.google.gson.internal.bind.TypeAdapters.8
            AnonymousClass8() {
            }

            @Override // com.google.gson.TypeAdapter
            /* renamed from: read */
            public AtomicInteger read2(JsonReader jsonReader) throws IOException {
                try {
                    return new AtomicInteger(jsonReader.nextInt());
                } catch (NumberFormatException e) {
                    throw new JsonSyntaxException(e);
                }
            }

            @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter, AtomicInteger atomicInteger) throws IOException {
                jsonWriter.value(atomicInteger.get());
            }
        }.nullSafe();
        ATOMIC_INTEGER = nullSafe3;
        ATOMIC_INTEGER_FACTORY = newFactory(AtomicInteger.class, nullSafe3);
        TypeAdapter<AtomicBoolean> nullSafe4 = new TypeAdapter<AtomicBoolean>() { // from class: com.google.gson.internal.bind.TypeAdapters.9
            AnonymousClass9() {
            }

            @Override // com.google.gson.TypeAdapter
            /* renamed from: read */
            public AtomicBoolean read2(JsonReader jsonReader) throws IOException {
                return new AtomicBoolean(jsonReader.nextBoolean());
            }

            @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter, AtomicBoolean atomicBoolean) throws IOException {
                jsonWriter.value(atomicBoolean.get());
            }
        }.nullSafe();
        ATOMIC_BOOLEAN = nullSafe4;
        ATOMIC_BOOLEAN_FACTORY = newFactory(AtomicBoolean.class, nullSafe4);
        TypeAdapter<AtomicIntegerArray> nullSafe5 = new TypeAdapter<AtomicIntegerArray>() { // from class: com.google.gson.internal.bind.TypeAdapters.10
            AnonymousClass10() {
            }

            @Override // com.google.gson.TypeAdapter
            /* renamed from: read */
            public AtomicIntegerArray read2(JsonReader jsonReader) throws IOException {
                ArrayList arrayList = new ArrayList();
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    try {
                        arrayList.add(Integer.valueOf(jsonReader.nextInt()));
                    } catch (NumberFormatException e) {
                        throw new JsonSyntaxException(e);
                    }
                }
                jsonReader.endArray();
                int size = arrayList.size();
                AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(size);
                for (int i = 0; i < size; i++) {
                    atomicIntegerArray.set(i, ((Integer) arrayList.get(i)).intValue());
                }
                return atomicIntegerArray;
            }

            @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter, AtomicIntegerArray atomicIntegerArray) throws IOException {
                jsonWriter.beginArray();
                int length = atomicIntegerArray.length();
                for (int i = 0; i < length; i++) {
                    jsonWriter.value(atomicIntegerArray.get(i));
                }
                jsonWriter.endArray();
            }
        }.nullSafe();
        ATOMIC_INTEGER_ARRAY = nullSafe5;
        ATOMIC_INTEGER_ARRAY_FACTORY = newFactory(AtomicIntegerArray.class, nullSafe5);
        LONG = new TypeAdapter<Number>() { // from class: com.google.gson.internal.bind.TypeAdapters.11
            AnonymousClass11() {
            }

            @Override // com.google.gson.TypeAdapter
            /* renamed from: read */
            public Number read2(JsonReader jsonReader) throws IOException {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
                try {
                    return Long.valueOf(jsonReader.nextLong());
                } catch (NumberFormatException e) {
                    throw new JsonSyntaxException(e);
                }
            }

            @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter, Number number) throws IOException {
                jsonWriter.value(number);
            }
        };
        FLOAT = new TypeAdapter<Number>() { // from class: com.google.gson.internal.bind.TypeAdapters.12
            AnonymousClass12() {
            }

            @Override // com.google.gson.TypeAdapter
            /* renamed from: read */
            public Number read2(JsonReader jsonReader) throws IOException {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
                return Float.valueOf((float) jsonReader.nextDouble());
            }

            @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter, Number number) throws IOException {
                jsonWriter.value(number);
            }
        };
        DOUBLE = new TypeAdapter<Number>() { // from class: com.google.gson.internal.bind.TypeAdapters.13
            AnonymousClass13() {
            }

            @Override // com.google.gson.TypeAdapter
            /* renamed from: read */
            public Number read2(JsonReader jsonReader) throws IOException {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
                return Double.valueOf(jsonReader.nextDouble());
            }

            @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter, Number number) throws IOException {
                jsonWriter.value(number);
            }
        };
        AnonymousClass14 anonymousClass14 = new TypeAdapter<Number>() { // from class: com.google.gson.internal.bind.TypeAdapters.14
            AnonymousClass14() {
            }

            @Override // com.google.gson.TypeAdapter
            /* renamed from: read */
            public Number read2(JsonReader jsonReader) throws IOException {
                JsonToken peek = jsonReader.peek();
                int i = AnonymousClass36.$SwitchMap$com$google$gson$stream$JsonToken[peek.ordinal()];
                if (i == 1 || i == 3) {
                    return new LazilyParsedNumber(jsonReader.nextString());
                }
                if (i == 4) {
                    jsonReader.nextNull();
                    return null;
                }
                throw new JsonSyntaxException("Expecting number, got: " + peek);
            }

            @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter, Number number) throws IOException {
                jsonWriter.value(number);
            }
        };
        NUMBER = anonymousClass14;
        NUMBER_FACTORY = newFactory(Number.class, anonymousClass14);
        AnonymousClass15 anonymousClass15 = new TypeAdapter<Character>() { // from class: com.google.gson.internal.bind.TypeAdapters.15
            AnonymousClass15() {
            }

            @Override // com.google.gson.TypeAdapter
            /* renamed from: read */
            public Character read2(JsonReader jsonReader) throws IOException {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
                String nextString = jsonReader.nextString();
                if (nextString.length() != 1) {
                    throw new JsonSyntaxException("Expecting character, got: " + nextString);
                }
                return Character.valueOf(nextString.charAt(0));
            }

            @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter, Character ch) throws IOException {
                jsonWriter.value(ch == null ? null : String.valueOf(ch));
            }
        };
        CHARACTER = anonymousClass15;
        CHARACTER_FACTORY = newFactory(Character.TYPE, Character.class, anonymousClass15);
        AnonymousClass16 anonymousClass16 = new TypeAdapter<String>() { // from class: com.google.gson.internal.bind.TypeAdapters.16
            AnonymousClass16() {
            }

            @Override // com.google.gson.TypeAdapter
            /* renamed from: read */
            public String read2(JsonReader jsonReader) throws IOException {
                JsonToken peek = jsonReader.peek();
                if (peek == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
                if (peek == JsonToken.BOOLEAN) {
                    return Boolean.toString(jsonReader.nextBoolean());
                }
                return jsonReader.nextString();
            }

            @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter, String str) throws IOException {
                jsonWriter.value(str);
            }
        };
        STRING = anonymousClass16;
        BIG_DECIMAL = new TypeAdapter<BigDecimal>() { // from class: com.google.gson.internal.bind.TypeAdapters.17
            AnonymousClass17() {
            }

            @Override // com.google.gson.TypeAdapter
            /* renamed from: read */
            public BigDecimal read2(JsonReader jsonReader) throws IOException {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
                try {
                    return new BigDecimal(jsonReader.nextString());
                } catch (NumberFormatException e) {
                    throw new JsonSyntaxException(e);
                }
            }

            @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter, BigDecimal bigDecimal) throws IOException {
                jsonWriter.value(bigDecimal);
            }
        };
        BIG_INTEGER = new TypeAdapter<BigInteger>() { // from class: com.google.gson.internal.bind.TypeAdapters.18
            AnonymousClass18() {
            }

            @Override // com.google.gson.TypeAdapter
            /* renamed from: read */
            public BigInteger read2(JsonReader jsonReader) throws IOException {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
                try {
                    return new BigInteger(jsonReader.nextString());
                } catch (NumberFormatException e) {
                    throw new JsonSyntaxException(e);
                }
            }

            @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter, BigInteger bigInteger) throws IOException {
                jsonWriter.value(bigInteger);
            }
        };
        STRING_FACTORY = newFactory(String.class, anonymousClass16);
        AnonymousClass19 anonymousClass19 = new TypeAdapter<StringBuilder>() { // from class: com.google.gson.internal.bind.TypeAdapters.19
            AnonymousClass19() {
            }

            @Override // com.google.gson.TypeAdapter
            /* renamed from: read */
            public StringBuilder read2(JsonReader jsonReader) throws IOException {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
                return new StringBuilder(jsonReader.nextString());
            }

            @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter, StringBuilder sb) throws IOException {
                jsonWriter.value(sb == null ? null : sb.toString());
            }
        };
        STRING_BUILDER = anonymousClass19;
        STRING_BUILDER_FACTORY = newFactory(StringBuilder.class, anonymousClass19);
        AnonymousClass20 anonymousClass20 = new TypeAdapter<StringBuffer>() { // from class: com.google.gson.internal.bind.TypeAdapters.20
            AnonymousClass20() {
            }

            @Override // com.google.gson.TypeAdapter
            /* renamed from: read */
            public StringBuffer read2(JsonReader jsonReader) throws IOException {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
                return new StringBuffer(jsonReader.nextString());
            }

            @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter, StringBuffer stringBuffer) throws IOException {
                jsonWriter.value(stringBuffer == null ? null : stringBuffer.toString());
            }
        };
        STRING_BUFFER = anonymousClass20;
        STRING_BUFFER_FACTORY = newFactory(StringBuffer.class, anonymousClass20);
        AnonymousClass21 anonymousClass21 = new TypeAdapter<URL>() { // from class: com.google.gson.internal.bind.TypeAdapters.21
            AnonymousClass21() {
            }

            @Override // com.google.gson.TypeAdapter
            /* renamed from: read */
            public URL read2(JsonReader jsonReader) throws IOException {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
                String nextString = jsonReader.nextString();
                if ("null".equals(nextString)) {
                    return null;
                }
                return new URL(nextString);
            }

            @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter, URL url) throws IOException {
                jsonWriter.value(url == null ? null : url.toExternalForm());
            }
        };
        URL = anonymousClass21;
        URL_FACTORY = newFactory(URL.class, anonymousClass21);
        AnonymousClass22 anonymousClass22 = new TypeAdapter<URI>() { // from class: com.google.gson.internal.bind.TypeAdapters.22
            AnonymousClass22() {
            }

            @Override // com.google.gson.TypeAdapter
            /* renamed from: read */
            public URI read2(JsonReader jsonReader) throws IOException {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
                try {
                    String nextString = jsonReader.nextString();
                    if ("null".equals(nextString)) {
                        return null;
                    }
                    return new URI(nextString);
                } catch (URISyntaxException e) {
                    throw new JsonIOException(e);
                }
            }

            @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter, URI uri) throws IOException {
                jsonWriter.value(uri == null ? null : uri.toASCIIString());
            }
        };
        URI = anonymousClass22;
        URI_FACTORY = newFactory(URI.class, anonymousClass22);
        AnonymousClass23 anonymousClass23 = new TypeAdapter<InetAddress>() { // from class: com.google.gson.internal.bind.TypeAdapters.23
            AnonymousClass23() {
            }

            @Override // com.google.gson.TypeAdapter
            /* renamed from: read */
            public InetAddress read2(JsonReader jsonReader) throws IOException {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
                return InetAddress.getByName(jsonReader.nextString());
            }

            @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter, InetAddress inetAddress) throws IOException {
                jsonWriter.value(inetAddress == null ? null : inetAddress.getHostAddress());
            }
        };
        INET_ADDRESS = anonymousClass23;
        INET_ADDRESS_FACTORY = newTypeHierarchyFactory(InetAddress.class, anonymousClass23);
        AnonymousClass24 anonymousClass24 = new TypeAdapter<UUID>() { // from class: com.google.gson.internal.bind.TypeAdapters.24
            AnonymousClass24() {
            }

            @Override // com.google.gson.TypeAdapter
            /* renamed from: read */
            public UUID read2(JsonReader jsonReader) throws IOException {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
                return UUID.fromString(jsonReader.nextString());
            }

            @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter, UUID uuid) throws IOException {
                jsonWriter.value(uuid == null ? null : uuid.toString());
            }
        };
        UUID = anonymousClass24;
        UUID_FACTORY = newFactory(UUID.class, anonymousClass24);
        TypeAdapter<Currency> nullSafe6 = new TypeAdapter<Currency>() { // from class: com.google.gson.internal.bind.TypeAdapters.25
            AnonymousClass25() {
            }

            @Override // com.google.gson.TypeAdapter
            /* renamed from: read */
            public Currency read2(JsonReader jsonReader) throws IOException {
                return Currency.getInstance(jsonReader.nextString());
            }

            @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter, Currency currency) throws IOException {
                jsonWriter.value(currency.getCurrencyCode());
            }
        }.nullSafe();
        CURRENCY = nullSafe6;
        CURRENCY_FACTORY = newFactory(Currency.class, nullSafe6);
        TIMESTAMP_FACTORY = new TypeAdapterFactory() { // from class: com.google.gson.internal.bind.TypeAdapters.26
            AnonymousClass26() {
            }

            @Override // com.google.gson.TypeAdapterFactory
            public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
                if (typeToken.getRawType() != Timestamp.class) {
                    return null;
                }
                return new TypeAdapter<Timestamp>() { // from class: com.google.gson.internal.bind.TypeAdapters.26.1
                    final /* synthetic */ TypeAdapter val$dateTypeAdapter;

                    AnonymousClass1(TypeAdapter typeAdapter) {
                        r2 = typeAdapter;
                    }

                    @Override // com.google.gson.TypeAdapter
                    /* renamed from: read */
                    public Timestamp read2(JsonReader jsonReader) throws IOException {
                        Date date = (Date) r2.read2(jsonReader);
                        if (date != null) {
                            return new Timestamp(date.getTime());
                        }
                        return null;
                    }

                    @Override // com.google.gson.TypeAdapter
                    public void write(JsonWriter jsonWriter, Timestamp timestamp) throws IOException {
                        r2.write(jsonWriter, timestamp);
                    }
                };
            }

            /* renamed from: com.google.gson.internal.bind.TypeAdapters$26$1 */
            class AnonymousClass1 extends TypeAdapter<Timestamp> {
                final /* synthetic */ TypeAdapter val$dateTypeAdapter;

                AnonymousClass1(TypeAdapter typeAdapter) {
                    r2 = typeAdapter;
                }

                @Override // com.google.gson.TypeAdapter
                /* renamed from: read */
                public Timestamp read2(JsonReader jsonReader) throws IOException {
                    Date date = (Date) r2.read2(jsonReader);
                    if (date != null) {
                        return new Timestamp(date.getTime());
                    }
                    return null;
                }

                @Override // com.google.gson.TypeAdapter
                public void write(JsonWriter jsonWriter, Timestamp timestamp) throws IOException {
                    r2.write(jsonWriter, timestamp);
                }
            }
        };
        AnonymousClass27 anonymousClass27 = new TypeAdapter<Calendar>() { // from class: com.google.gson.internal.bind.TypeAdapters.27
            private static final String DAY_OF_MONTH = "dayOfMonth";
            private static final String HOUR_OF_DAY = "hourOfDay";
            private static final String MINUTE = "minute";
            private static final String MONTH = "month";
            private static final String SECOND = "second";
            private static final String YEAR = "year";

            AnonymousClass27() {
            }

            @Override // com.google.gson.TypeAdapter
            /* renamed from: read */
            public Calendar read2(JsonReader jsonReader) throws IOException {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
                jsonReader.beginObject();
                int i = 0;
                int i2 = 0;
                int i3 = 0;
                int i4 = 0;
                int i5 = 0;
                int i6 = 0;
                while (jsonReader.peek() != JsonToken.END_OBJECT) {
                    String nextName = jsonReader.nextName();
                    int nextInt = jsonReader.nextInt();
                    if (YEAR.equals(nextName)) {
                        i = nextInt;
                    } else if (MONTH.equals(nextName)) {
                        i2 = nextInt;
                    } else if (DAY_OF_MONTH.equals(nextName)) {
                        i3 = nextInt;
                    } else if (HOUR_OF_DAY.equals(nextName)) {
                        i4 = nextInt;
                    } else if (MINUTE.equals(nextName)) {
                        i5 = nextInt;
                    } else if (SECOND.equals(nextName)) {
                        i6 = nextInt;
                    }
                }
                jsonReader.endObject();
                return new GregorianCalendar(i, i2, i3, i4, i5, i6);
            }

            @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter, Calendar calendar) throws IOException {
                if (calendar == null) {
                    jsonWriter.nullValue();
                    return;
                }
                jsonWriter.beginObject();
                jsonWriter.name(YEAR);
                jsonWriter.value(calendar.get(1));
                jsonWriter.name(MONTH);
                jsonWriter.value(calendar.get(2));
                jsonWriter.name(DAY_OF_MONTH);
                jsonWriter.value(calendar.get(5));
                jsonWriter.name(HOUR_OF_DAY);
                jsonWriter.value(calendar.get(11));
                jsonWriter.name(MINUTE);
                jsonWriter.value(calendar.get(12));
                jsonWriter.name(SECOND);
                jsonWriter.value(calendar.get(13));
                jsonWriter.endObject();
            }
        };
        CALENDAR = anonymousClass27;
        CALENDAR_FACTORY = newFactoryForMultipleTypes(Calendar.class, GregorianCalendar.class, anonymousClass27);
        AnonymousClass28 anonymousClass28 = new TypeAdapter<Locale>() { // from class: com.google.gson.internal.bind.TypeAdapters.28
            AnonymousClass28() {
            }

            @Override // com.google.gson.TypeAdapter
            /* renamed from: read */
            public Locale read2(JsonReader jsonReader) throws IOException {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
                StringTokenizer stringTokenizer = new StringTokenizer(jsonReader.nextString(), "_");
                String nextToken = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
                String nextToken2 = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
                String nextToken3 = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
                if (nextToken2 == null && nextToken3 == null) {
                    return new Locale(nextToken);
                }
                if (nextToken3 == null) {
                    return new Locale(nextToken, nextToken2);
                }
                return new Locale(nextToken, nextToken2, nextToken3);
            }

            @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter, Locale locale) throws IOException {
                jsonWriter.value(locale == null ? null : locale.toString());
            }
        };
        LOCALE = anonymousClass28;
        LOCALE_FACTORY = newFactory(Locale.class, anonymousClass28);
        AnonymousClass29 anonymousClass29 = new TypeAdapter<JsonElement>() { // from class: com.google.gson.internal.bind.TypeAdapters.29
            AnonymousClass29() {
            }

            @Override // com.google.gson.TypeAdapter
            /* renamed from: read */
            public JsonElement read2(JsonReader jsonReader) throws IOException {
                switch (AnonymousClass36.$SwitchMap$com$google$gson$stream$JsonToken[jsonReader.peek().ordinal()]) {
                    case 1:
                        return new JsonPrimitive(new LazilyParsedNumber(jsonReader.nextString()));
                    case 2:
                        return new JsonPrimitive(Boolean.valueOf(jsonReader.nextBoolean()));
                    case 3:
                        return new JsonPrimitive(jsonReader.nextString());
                    case 4:
                        jsonReader.nextNull();
                        return JsonNull.INSTANCE;
                    case 5:
                        JsonArray jsonArray = new JsonArray();
                        jsonReader.beginArray();
                        while (jsonReader.hasNext()) {
                            jsonArray.add(read2(jsonReader));
                        }
                        jsonReader.endArray();
                        return jsonArray;
                    case 6:
                        JsonObject jsonObject = new JsonObject();
                        jsonReader.beginObject();
                        while (jsonReader.hasNext()) {
                            jsonObject.add(jsonReader.nextName(), read2(jsonReader));
                        }
                        jsonReader.endObject();
                        return jsonObject;
                    default:
                        throw new IllegalArgumentException();
                }
            }

            @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter, JsonElement jsonElement) throws IOException {
                if (jsonElement == null || jsonElement.isJsonNull()) {
                    jsonWriter.nullValue();
                    return;
                }
                if (jsonElement.isJsonPrimitive()) {
                    JsonPrimitive asJsonPrimitive = jsonElement.getAsJsonPrimitive();
                    if (asJsonPrimitive.isNumber()) {
                        jsonWriter.value(asJsonPrimitive.getAsNumber());
                        return;
                    } else if (asJsonPrimitive.isBoolean()) {
                        jsonWriter.value(asJsonPrimitive.getAsBoolean());
                        return;
                    } else {
                        jsonWriter.value(asJsonPrimitive.getAsString());
                        return;
                    }
                }
                if (jsonElement.isJsonArray()) {
                    jsonWriter.beginArray();
                    Iterator<JsonElement> it = jsonElement.getAsJsonArray().iterator();
                    while (it.hasNext()) {
                        write(jsonWriter, it.next());
                    }
                    jsonWriter.endArray();
                    return;
                }
                if (jsonElement.isJsonObject()) {
                    jsonWriter.beginObject();
                    for (Map.Entry<String, JsonElement> entry : jsonElement.getAsJsonObject().entrySet()) {
                        jsonWriter.name(entry.getKey());
                        write(jsonWriter, entry.getValue());
                    }
                    jsonWriter.endObject();
                    return;
                }
                throw new IllegalArgumentException("Couldn't write " + jsonElement.getClass());
            }
        };
        JSON_ELEMENT = anonymousClass29;
        JSON_ELEMENT_FACTORY = newTypeHierarchyFactory(JsonElement.class, anonymousClass29);
        ENUM_FACTORY = new TypeAdapterFactory() { // from class: com.google.gson.internal.bind.TypeAdapters.30
            AnonymousClass30() {
            }

            @Override // com.google.gson.TypeAdapterFactory
            public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
                Class<? super T> rawType = typeToken.getRawType();
                if (!Enum.class.isAssignableFrom(rawType) || rawType == Enum.class) {
                    return null;
                }
                if (!rawType.isEnum()) {
                    rawType = rawType.getSuperclass();
                }
                return new EnumTypeAdapter(rawType);
            }
        };
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$2 */
    class AnonymousClass2 extends TypeAdapter<BitSet> {
        AnonymousClass2() {
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public BitSet read2(JsonReader jsonReader) throws IOException {
            /*
                this = this;
                java.util.BitSet r0 = new java.util.BitSet
                r0.<init>()
                r8.beginArray()
                com.google.gson.stream.JsonToken r1 = r8.peek()
                r2 = 0
                r3 = 0
            Le:
                com.google.gson.stream.JsonToken r4 = com.google.gson.stream.JsonToken.END_ARRAY
                if (r1 == r4) goto L6f
                int[] r4 = com.google.gson.internal.bind.TypeAdapters.AnonymousClass36.$SwitchMap$com$google$gson$stream$JsonToken
                int r5 = r1.ordinal()
                r4 = r4[r5]
                r5 = 1
                if (r4 == r5) goto L5d
                r6 = 2
                if (r4 == r6) goto L58
                r6 = 3
                if (r4 != r6) goto L44
                java.lang.String r1 = r8.nextString()
                int r1 = java.lang.Integer.parseInt(r1)     // Catch: java.lang.NumberFormatException -> L30
                if (r1 == 0) goto L2e
                goto L63
            L2e:
                r5 = 0
                goto L63
            L30:
                com.google.gson.JsonSyntaxException r8 = new com.google.gson.JsonSyntaxException
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                java.lang.String r2 = "Error: Expecting: bitset number value (1, 0), Found: "
                r0.<init>(r2)
                r0.append(r1)
                java.lang.String r0 = r0.toString()
                r8.<init>(r0)
                throw r8
            L44:
                com.google.gson.JsonSyntaxException r8 = new com.google.gson.JsonSyntaxException
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                java.lang.String r2 = "Invalid bitset value type: "
                r0.<init>(r2)
                r0.append(r1)
                java.lang.String r0 = r0.toString()
                r8.<init>(r0)
                throw r8
            L58:
                boolean r5 = r8.nextBoolean()
                goto L63
            L5d:
                int r1 = r8.nextInt()
                if (r1 == 0) goto L2e
            L63:
                if (r5 == 0) goto L68
                r0.set(r3)
            L68:
                int r3 = r3 + 1
                com.google.gson.stream.JsonToken r1 = r8.peek()
                goto Le
            L6f:
                r8.endArray()
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.bind.TypeAdapters.AnonymousClass2.read2(com.google.gson.stream.JsonReader):java.util.BitSet");
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, BitSet bitSet) throws IOException {
            jsonWriter.beginArray();
            int length = bitSet.length();
            for (int i = 0; i < length; i++) {
                jsonWriter.value(bitSet.get(i) ? 1L : 0L);
            }
            jsonWriter.endArray();
        }
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$36 */
    static /* synthetic */ class AnonymousClass36 {
        static final /* synthetic */ int[] $SwitchMap$com$google$gson$stream$JsonToken;

        static {
            int[] iArr = new int[JsonToken.values().length];
            $SwitchMap$com$google$gson$stream$JsonToken = iArr;
            try {
                iArr[JsonToken.NUMBER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.BOOLEAN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.STRING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.NULL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.BEGIN_ARRAY.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.BEGIN_OBJECT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.END_DOCUMENT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.NAME.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.END_OBJECT.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.END_ARRAY.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$3 */
    class AnonymousClass3 extends TypeAdapter<Boolean> {
        AnonymousClass3() {
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public Boolean read2(JsonReader jsonReader) throws IOException {
            JsonToken peek = jsonReader.peek();
            if (peek == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            if (peek == JsonToken.STRING) {
                return Boolean.valueOf(Boolean.parseBoolean(jsonReader.nextString()));
            }
            return Boolean.valueOf(jsonReader.nextBoolean());
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Boolean bool) throws IOException {
            jsonWriter.value(bool);
        }
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$4 */
    class AnonymousClass4 extends TypeAdapter<Boolean> {
        AnonymousClass4() {
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public Boolean read2(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            return Boolean.valueOf(jsonReader.nextString());
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Boolean bool) throws IOException {
            jsonWriter.value(bool == null ? "null" : bool.toString());
        }
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$5 */
    class AnonymousClass5 extends TypeAdapter<Number> {
        AnonymousClass5() {
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public Number read2(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            try {
                return Byte.valueOf((byte) jsonReader.nextInt());
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Number number) throws IOException {
            jsonWriter.value(number);
        }
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$6 */
    class AnonymousClass6 extends TypeAdapter<Number> {
        AnonymousClass6() {
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public Number read2(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            try {
                return Short.valueOf((short) jsonReader.nextInt());
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Number number) throws IOException {
            jsonWriter.value(number);
        }
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$7 */
    class AnonymousClass7 extends TypeAdapter<Number> {
        AnonymousClass7() {
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public Number read2(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            try {
                return Integer.valueOf(jsonReader.nextInt());
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Number number) throws IOException {
            jsonWriter.value(number);
        }
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$8 */
    class AnonymousClass8 extends TypeAdapter<AtomicInteger> {
        AnonymousClass8() {
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public AtomicInteger read2(JsonReader jsonReader) throws IOException {
            try {
                return new AtomicInteger(jsonReader.nextInt());
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, AtomicInteger atomicInteger) throws IOException {
            jsonWriter.value(atomicInteger.get());
        }
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$9 */
    class AnonymousClass9 extends TypeAdapter<AtomicBoolean> {
        AnonymousClass9() {
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public AtomicBoolean read2(JsonReader jsonReader) throws IOException {
            return new AtomicBoolean(jsonReader.nextBoolean());
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, AtomicBoolean atomicBoolean) throws IOException {
            jsonWriter.value(atomicBoolean.get());
        }
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$10 */
    class AnonymousClass10 extends TypeAdapter<AtomicIntegerArray> {
        AnonymousClass10() {
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public AtomicIntegerArray read2(JsonReader jsonReader) throws IOException {
            ArrayList arrayList = new ArrayList();
            jsonReader.beginArray();
            while (jsonReader.hasNext()) {
                try {
                    arrayList.add(Integer.valueOf(jsonReader.nextInt()));
                } catch (NumberFormatException e) {
                    throw new JsonSyntaxException(e);
                }
            }
            jsonReader.endArray();
            int size = arrayList.size();
            AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(size);
            for (int i = 0; i < size; i++) {
                atomicIntegerArray.set(i, ((Integer) arrayList.get(i)).intValue());
            }
            return atomicIntegerArray;
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, AtomicIntegerArray atomicIntegerArray) throws IOException {
            jsonWriter.beginArray();
            int length = atomicIntegerArray.length();
            for (int i = 0; i < length; i++) {
                jsonWriter.value(atomicIntegerArray.get(i));
            }
            jsonWriter.endArray();
        }
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$11 */
    class AnonymousClass11 extends TypeAdapter<Number> {
        AnonymousClass11() {
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public Number read2(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            try {
                return Long.valueOf(jsonReader.nextLong());
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Number number) throws IOException {
            jsonWriter.value(number);
        }
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$12 */
    class AnonymousClass12 extends TypeAdapter<Number> {
        AnonymousClass12() {
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public Number read2(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            return Float.valueOf((float) jsonReader.nextDouble());
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Number number) throws IOException {
            jsonWriter.value(number);
        }
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$13 */
    class AnonymousClass13 extends TypeAdapter<Number> {
        AnonymousClass13() {
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public Number read2(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            return Double.valueOf(jsonReader.nextDouble());
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Number number) throws IOException {
            jsonWriter.value(number);
        }
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$14 */
    class AnonymousClass14 extends TypeAdapter<Number> {
        AnonymousClass14() {
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public Number read2(JsonReader jsonReader) throws IOException {
            JsonToken peek = jsonReader.peek();
            int i = AnonymousClass36.$SwitchMap$com$google$gson$stream$JsonToken[peek.ordinal()];
            if (i == 1 || i == 3) {
                return new LazilyParsedNumber(jsonReader.nextString());
            }
            if (i == 4) {
                jsonReader.nextNull();
                return null;
            }
            throw new JsonSyntaxException("Expecting number, got: " + peek);
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Number number) throws IOException {
            jsonWriter.value(number);
        }
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$15 */
    class AnonymousClass15 extends TypeAdapter<Character> {
        AnonymousClass15() {
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public Character read2(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            String nextString = jsonReader.nextString();
            if (nextString.length() != 1) {
                throw new JsonSyntaxException("Expecting character, got: " + nextString);
            }
            return Character.valueOf(nextString.charAt(0));
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Character ch) throws IOException {
            jsonWriter.value(ch == null ? null : String.valueOf(ch));
        }
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$16 */
    class AnonymousClass16 extends TypeAdapter<String> {
        AnonymousClass16() {
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public String read2(JsonReader jsonReader) throws IOException {
            JsonToken peek = jsonReader.peek();
            if (peek == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            if (peek == JsonToken.BOOLEAN) {
                return Boolean.toString(jsonReader.nextBoolean());
            }
            return jsonReader.nextString();
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, String str) throws IOException {
            jsonWriter.value(str);
        }
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$17 */
    class AnonymousClass17 extends TypeAdapter<BigDecimal> {
        AnonymousClass17() {
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public BigDecimal read2(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            try {
                return new BigDecimal(jsonReader.nextString());
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, BigDecimal bigDecimal) throws IOException {
            jsonWriter.value(bigDecimal);
        }
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$18 */
    class AnonymousClass18 extends TypeAdapter<BigInteger> {
        AnonymousClass18() {
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public BigInteger read2(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            try {
                return new BigInteger(jsonReader.nextString());
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, BigInteger bigInteger) throws IOException {
            jsonWriter.value(bigInteger);
        }
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$19 */
    class AnonymousClass19 extends TypeAdapter<StringBuilder> {
        AnonymousClass19() {
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public StringBuilder read2(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            return new StringBuilder(jsonReader.nextString());
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, StringBuilder sb) throws IOException {
            jsonWriter.value(sb == null ? null : sb.toString());
        }
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$20 */
    class AnonymousClass20 extends TypeAdapter<StringBuffer> {
        AnonymousClass20() {
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public StringBuffer read2(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            return new StringBuffer(jsonReader.nextString());
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, StringBuffer stringBuffer) throws IOException {
            jsonWriter.value(stringBuffer == null ? null : stringBuffer.toString());
        }
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$21 */
    class AnonymousClass21 extends TypeAdapter<URL> {
        AnonymousClass21() {
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public URL read2(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            String nextString = jsonReader.nextString();
            if ("null".equals(nextString)) {
                return null;
            }
            return new URL(nextString);
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, URL url) throws IOException {
            jsonWriter.value(url == null ? null : url.toExternalForm());
        }
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$22 */
    class AnonymousClass22 extends TypeAdapter<URI> {
        AnonymousClass22() {
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public URI read2(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            try {
                String nextString = jsonReader.nextString();
                if ("null".equals(nextString)) {
                    return null;
                }
                return new URI(nextString);
            } catch (URISyntaxException e) {
                throw new JsonIOException(e);
            }
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, URI uri) throws IOException {
            jsonWriter.value(uri == null ? null : uri.toASCIIString());
        }
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$23 */
    class AnonymousClass23 extends TypeAdapter<InetAddress> {
        AnonymousClass23() {
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public InetAddress read2(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            return InetAddress.getByName(jsonReader.nextString());
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, InetAddress inetAddress) throws IOException {
            jsonWriter.value(inetAddress == null ? null : inetAddress.getHostAddress());
        }
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$24 */
    class AnonymousClass24 extends TypeAdapter<UUID> {
        AnonymousClass24() {
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public UUID read2(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            return UUID.fromString(jsonReader.nextString());
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, UUID uuid) throws IOException {
            jsonWriter.value(uuid == null ? null : uuid.toString());
        }
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$25 */
    class AnonymousClass25 extends TypeAdapter<Currency> {
        AnonymousClass25() {
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public Currency read2(JsonReader jsonReader) throws IOException {
            return Currency.getInstance(jsonReader.nextString());
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Currency currency) throws IOException {
            jsonWriter.value(currency.getCurrencyCode());
        }
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$26 */
    class AnonymousClass26 implements TypeAdapterFactory {
        AnonymousClass26() {
        }

        @Override // com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            if (typeToken.getRawType() != Timestamp.class) {
                return null;
            }
            return new TypeAdapter<Timestamp>() { // from class: com.google.gson.internal.bind.TypeAdapters.26.1
                final /* synthetic */ TypeAdapter val$dateTypeAdapter;

                AnonymousClass1(TypeAdapter typeAdapter) {
                    r2 = typeAdapter;
                }

                @Override // com.google.gson.TypeAdapter
                /* renamed from: read */
                public Timestamp read2(JsonReader jsonReader) throws IOException {
                    Date date = (Date) r2.read2(jsonReader);
                    if (date != null) {
                        return new Timestamp(date.getTime());
                    }
                    return null;
                }

                @Override // com.google.gson.TypeAdapter
                public void write(JsonWriter jsonWriter, Timestamp timestamp) throws IOException {
                    r2.write(jsonWriter, timestamp);
                }
            };
        }

        /* renamed from: com.google.gson.internal.bind.TypeAdapters$26$1 */
        class AnonymousClass1 extends TypeAdapter<Timestamp> {
            final /* synthetic */ TypeAdapter val$dateTypeAdapter;

            AnonymousClass1(TypeAdapter typeAdapter) {
                r2 = typeAdapter;
            }

            @Override // com.google.gson.TypeAdapter
            /* renamed from: read */
            public Timestamp read2(JsonReader jsonReader) throws IOException {
                Date date = (Date) r2.read2(jsonReader);
                if (date != null) {
                    return new Timestamp(date.getTime());
                }
                return null;
            }

            @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter, Timestamp timestamp) throws IOException {
                r2.write(jsonWriter, timestamp);
            }
        }
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$27 */
    class AnonymousClass27 extends TypeAdapter<Calendar> {
        private static final String DAY_OF_MONTH = "dayOfMonth";
        private static final String HOUR_OF_DAY = "hourOfDay";
        private static final String MINUTE = "minute";
        private static final String MONTH = "month";
        private static final String SECOND = "second";
        private static final String YEAR = "year";

        AnonymousClass27() {
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public Calendar read2(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            while (jsonReader.peek() != JsonToken.END_OBJECT) {
                String nextName = jsonReader.nextName();
                int nextInt = jsonReader.nextInt();
                if (YEAR.equals(nextName)) {
                    i = nextInt;
                } else if (MONTH.equals(nextName)) {
                    i2 = nextInt;
                } else if (DAY_OF_MONTH.equals(nextName)) {
                    i3 = nextInt;
                } else if (HOUR_OF_DAY.equals(nextName)) {
                    i4 = nextInt;
                } else if (MINUTE.equals(nextName)) {
                    i5 = nextInt;
                } else if (SECOND.equals(nextName)) {
                    i6 = nextInt;
                }
            }
            jsonReader.endObject();
            return new GregorianCalendar(i, i2, i3, i4, i5, i6);
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Calendar calendar) throws IOException {
            if (calendar == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(YEAR);
            jsonWriter.value(calendar.get(1));
            jsonWriter.name(MONTH);
            jsonWriter.value(calendar.get(2));
            jsonWriter.name(DAY_OF_MONTH);
            jsonWriter.value(calendar.get(5));
            jsonWriter.name(HOUR_OF_DAY);
            jsonWriter.value(calendar.get(11));
            jsonWriter.name(MINUTE);
            jsonWriter.value(calendar.get(12));
            jsonWriter.name(SECOND);
            jsonWriter.value(calendar.get(13));
            jsonWriter.endObject();
        }
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$28 */
    class AnonymousClass28 extends TypeAdapter<Locale> {
        AnonymousClass28() {
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public Locale read2(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            StringTokenizer stringTokenizer = new StringTokenizer(jsonReader.nextString(), "_");
            String nextToken = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            String nextToken2 = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            String nextToken3 = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            if (nextToken2 == null && nextToken3 == null) {
                return new Locale(nextToken);
            }
            if (nextToken3 == null) {
                return new Locale(nextToken, nextToken2);
            }
            return new Locale(nextToken, nextToken2, nextToken3);
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Locale locale) throws IOException {
            jsonWriter.value(locale == null ? null : locale.toString());
        }
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$29 */
    class AnonymousClass29 extends TypeAdapter<JsonElement> {
        AnonymousClass29() {
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public JsonElement read2(JsonReader jsonReader) throws IOException {
            switch (AnonymousClass36.$SwitchMap$com$google$gson$stream$JsonToken[jsonReader.peek().ordinal()]) {
                case 1:
                    return new JsonPrimitive(new LazilyParsedNumber(jsonReader.nextString()));
                case 2:
                    return new JsonPrimitive(Boolean.valueOf(jsonReader.nextBoolean()));
                case 3:
                    return new JsonPrimitive(jsonReader.nextString());
                case 4:
                    jsonReader.nextNull();
                    return JsonNull.INSTANCE;
                case 5:
                    JsonArray jsonArray = new JsonArray();
                    jsonReader.beginArray();
                    while (jsonReader.hasNext()) {
                        jsonArray.add(read2(jsonReader));
                    }
                    jsonReader.endArray();
                    return jsonArray;
                case 6:
                    JsonObject jsonObject = new JsonObject();
                    jsonReader.beginObject();
                    while (jsonReader.hasNext()) {
                        jsonObject.add(jsonReader.nextName(), read2(jsonReader));
                    }
                    jsonReader.endObject();
                    return jsonObject;
                default:
                    throw new IllegalArgumentException();
            }
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, JsonElement jsonElement) throws IOException {
            if (jsonElement == null || jsonElement.isJsonNull()) {
                jsonWriter.nullValue();
                return;
            }
            if (jsonElement.isJsonPrimitive()) {
                JsonPrimitive asJsonPrimitive = jsonElement.getAsJsonPrimitive();
                if (asJsonPrimitive.isNumber()) {
                    jsonWriter.value(asJsonPrimitive.getAsNumber());
                    return;
                } else if (asJsonPrimitive.isBoolean()) {
                    jsonWriter.value(asJsonPrimitive.getAsBoolean());
                    return;
                } else {
                    jsonWriter.value(asJsonPrimitive.getAsString());
                    return;
                }
            }
            if (jsonElement.isJsonArray()) {
                jsonWriter.beginArray();
                Iterator<JsonElement> it = jsonElement.getAsJsonArray().iterator();
                while (it.hasNext()) {
                    write(jsonWriter, it.next());
                }
                jsonWriter.endArray();
                return;
            }
            if (jsonElement.isJsonObject()) {
                jsonWriter.beginObject();
                for (Map.Entry<String, JsonElement> entry : jsonElement.getAsJsonObject().entrySet()) {
                    jsonWriter.name(entry.getKey());
                    write(jsonWriter, entry.getValue());
                }
                jsonWriter.endObject();
                return;
            }
            throw new IllegalArgumentException("Couldn't write " + jsonElement.getClass());
        }
    }

    private static final class EnumTypeAdapter<T extends Enum<T>> extends TypeAdapter<T> {
        private final Map<String, T> nameToConstant = new HashMap();
        private final Map<T, String> constantToName = new HashMap();

        public EnumTypeAdapter(Class<T> cls) {
            try {
                for (T t : cls.getEnumConstants()) {
                    String name = t.name();
                    SerializedName serializedName = (SerializedName) cls.getField(name).getAnnotation(SerializedName.class);
                    if (serializedName != null) {
                        name = serializedName.value();
                        for (String str : serializedName.alternate()) {
                            this.nameToConstant.put(str, t);
                        }
                    }
                    this.nameToConstant.put(name, t);
                    this.constantToName.put(t, name);
                }
            } catch (NoSuchFieldException e) {
                throw new AssertionError(e);
            }
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public T read2(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            return this.nameToConstant.get(jsonReader.nextString());
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, T t) throws IOException {
            jsonWriter.value(t == null ? null : this.constantToName.get(t));
        }
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$30 */
    class AnonymousClass30 implements TypeAdapterFactory {
        AnonymousClass30() {
        }

        @Override // com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            Class<? super T> rawType = typeToken.getRawType();
            if (!Enum.class.isAssignableFrom(rawType) || rawType == Enum.class) {
                return null;
            }
            if (!rawType.isEnum()) {
                rawType = rawType.getSuperclass();
            }
            return new EnumTypeAdapter(rawType);
        }
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$31 */
    class AnonymousClass31 implements TypeAdapterFactory {
        final /* synthetic */ TypeAdapter val$typeAdapter;

        AnonymousClass31(TypeAdapter typeAdapter) {
            r2 = typeAdapter;
        }

        @Override // com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            if (typeToken.equals(TypeToken.this)) {
                return r2;
            }
            return null;
        }
    }

    public static <TT> TypeAdapterFactory newFactory(TypeToken<TT> typeToken, TypeAdapter<TT> typeAdapter) {
        return new TypeAdapterFactory() { // from class: com.google.gson.internal.bind.TypeAdapters.31
            final /* synthetic */ TypeAdapter val$typeAdapter;

            AnonymousClass31(TypeAdapter typeAdapter2) {
                r2 = typeAdapter2;
            }

            @Override // com.google.gson.TypeAdapterFactory
            public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken2) {
                if (typeToken2.equals(TypeToken.this)) {
                    return r2;
                }
                return null;
            }
        };
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$32 */
    class AnonymousClass32 implements TypeAdapterFactory {
        final /* synthetic */ Class val$type;
        final /* synthetic */ TypeAdapter val$typeAdapter;

        AnonymousClass32(Class cls, TypeAdapter typeAdapter) {
            r1 = cls;
            r2 = typeAdapter;
        }

        @Override // com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            if (typeToken.getRawType() == r1) {
                return r2;
            }
            return null;
        }

        public String toString() {
            return "Factory[type=" + r1.getName() + ",adapter=" + r2 + "]";
        }
    }

    public static <TT> TypeAdapterFactory newFactory(Class<TT> cls, TypeAdapter<TT> typeAdapter) {
        return new TypeAdapterFactory() { // from class: com.google.gson.internal.bind.TypeAdapters.32
            final /* synthetic */ Class val$type;
            final /* synthetic */ TypeAdapter val$typeAdapter;

            AnonymousClass32(Class cls2, TypeAdapter typeAdapter2) {
                r1 = cls2;
                r2 = typeAdapter2;
            }

            @Override // com.google.gson.TypeAdapterFactory
            public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
                if (typeToken.getRawType() == r1) {
                    return r2;
                }
                return null;
            }

            public String toString() {
                return "Factory[type=" + r1.getName() + ",adapter=" + r2 + "]";
            }
        };
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$33 */
    class AnonymousClass33 implements TypeAdapterFactory {
        final /* synthetic */ Class val$boxed;
        final /* synthetic */ TypeAdapter val$typeAdapter;
        final /* synthetic */ Class val$unboxed;

        AnonymousClass33(Class cls, Class cls2, TypeAdapter typeAdapter) {
            r1 = cls;
            r2 = cls2;
            r3 = typeAdapter;
        }

        @Override // com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            Class<? super T> rawType = typeToken.getRawType();
            if (rawType == r1 || rawType == r2) {
                return r3;
            }
            return null;
        }

        public String toString() {
            return "Factory[type=" + r2.getName() + "+" + r1.getName() + ",adapter=" + r3 + "]";
        }
    }

    public static <TT> TypeAdapterFactory newFactory(Class<TT> cls, Class<TT> cls2, TypeAdapter<? super TT> typeAdapter) {
        return new TypeAdapterFactory() { // from class: com.google.gson.internal.bind.TypeAdapters.33
            final /* synthetic */ Class val$boxed;
            final /* synthetic */ TypeAdapter val$typeAdapter;
            final /* synthetic */ Class val$unboxed;

            AnonymousClass33(Class cls3, Class cls22, TypeAdapter typeAdapter2) {
                r1 = cls3;
                r2 = cls22;
                r3 = typeAdapter2;
            }

            @Override // com.google.gson.TypeAdapterFactory
            public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
                Class<? super T> rawType = typeToken.getRawType();
                if (rawType == r1 || rawType == r2) {
                    return r3;
                }
                return null;
            }

            public String toString() {
                return "Factory[type=" + r2.getName() + "+" + r1.getName() + ",adapter=" + r3 + "]";
            }
        };
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$34 */
    class AnonymousClass34 implements TypeAdapterFactory {
        final /* synthetic */ Class val$base;
        final /* synthetic */ Class val$sub;
        final /* synthetic */ TypeAdapter val$typeAdapter;

        AnonymousClass34(Class cls, Class cls2, TypeAdapter typeAdapter) {
            r1 = cls;
            r2 = cls2;
            r3 = typeAdapter;
        }

        @Override // com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            Class<? super T> rawType = typeToken.getRawType();
            if (rawType == r1 || rawType == r2) {
                return r3;
            }
            return null;
        }

        public String toString() {
            return "Factory[type=" + r1.getName() + "+" + r2.getName() + ",adapter=" + r3 + "]";
        }
    }

    public static <TT> TypeAdapterFactory newFactoryForMultipleTypes(Class<TT> cls, Class<? extends TT> cls2, TypeAdapter<? super TT> typeAdapter) {
        return new TypeAdapterFactory() { // from class: com.google.gson.internal.bind.TypeAdapters.34
            final /* synthetic */ Class val$base;
            final /* synthetic */ Class val$sub;
            final /* synthetic */ TypeAdapter val$typeAdapter;

            AnonymousClass34(Class cls3, Class cls22, TypeAdapter typeAdapter2) {
                r1 = cls3;
                r2 = cls22;
                r3 = typeAdapter2;
            }

            @Override // com.google.gson.TypeAdapterFactory
            public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
                Class<? super T> rawType = typeToken.getRawType();
                if (rawType == r1 || rawType == r2) {
                    return r3;
                }
                return null;
            }

            public String toString() {
                return "Factory[type=" + r1.getName() + "+" + r2.getName() + ",adapter=" + r3 + "]";
            }
        };
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$35 */
    class AnonymousClass35 implements TypeAdapterFactory {
        final /* synthetic */ Class val$clazz;
        final /* synthetic */ TypeAdapter val$typeAdapter;

        AnonymousClass35(Class cls, TypeAdapter typeAdapter) {
            r1 = cls;
            r2 = typeAdapter;
        }

        @Override // com.google.gson.TypeAdapterFactory
        public <T2> TypeAdapter<T2> create(Gson gson, TypeToken<T2> typeToken) {
            Class<? super T2> rawType = typeToken.getRawType();
            if (r1.isAssignableFrom(rawType)) {
                return new TypeAdapter<T1>() { // from class: com.google.gson.internal.bind.TypeAdapters.35.1
                    final /* synthetic */ Class val$requestedType;

                    AnonymousClass1(Class rawType2) {
                        r2 = rawType2;
                    }

                    @Override // com.google.gson.TypeAdapter
                    public void write(JsonWriter jsonWriter, T1 t1) throws IOException {
                        r2.write(jsonWriter, t1);
                    }

                    @Override // com.google.gson.TypeAdapter
                    /* renamed from: read */
                    public T1 read2(JsonReader jsonReader) throws IOException {
                        T1 t1 = (T1) r2.read2(jsonReader);
                        if (t1 == null || r2.isInstance(t1)) {
                            return t1;
                        }
                        throw new JsonSyntaxException("Expected a " + r2.getName() + " but was " + t1.getClass().getName());
                    }
                };
            }
            return null;
        }

        /* renamed from: com.google.gson.internal.bind.TypeAdapters$35$1 */
        class AnonymousClass1<T1> extends TypeAdapter<T1> {
            final /* synthetic */ Class val$requestedType;

            AnonymousClass1(Class rawType2) {
                r2 = rawType2;
            }

            @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter, T1 t1) throws IOException {
                r2.write(jsonWriter, t1);
            }

            @Override // com.google.gson.TypeAdapter
            /* renamed from: read */
            public T1 read2(JsonReader jsonReader) throws IOException {
                T1 t1 = (T1) r2.read2(jsonReader);
                if (t1 == null || r2.isInstance(t1)) {
                    return t1;
                }
                throw new JsonSyntaxException("Expected a " + r2.getName() + " but was " + t1.getClass().getName());
            }
        }

        public String toString() {
            return "Factory[typeHierarchy=" + r1.getName() + ",adapter=" + r2 + "]";
        }
    }

    public static <T1> TypeAdapterFactory newTypeHierarchyFactory(Class<T1> cls, TypeAdapter<T1> typeAdapter) {
        return new TypeAdapterFactory() { // from class: com.google.gson.internal.bind.TypeAdapters.35
            final /* synthetic */ Class val$clazz;
            final /* synthetic */ TypeAdapter val$typeAdapter;

            AnonymousClass35(Class cls2, TypeAdapter typeAdapter2) {
                r1 = cls2;
                r2 = typeAdapter2;
            }

            @Override // com.google.gson.TypeAdapterFactory
            public <T2> TypeAdapter<T2> create(Gson gson, TypeToken<T2> typeToken) {
                Class rawType2 = typeToken.getRawType();
                if (r1.isAssignableFrom(rawType2)) {
                    return new TypeAdapter<T1>() { // from class: com.google.gson.internal.bind.TypeAdapters.35.1
                        final /* synthetic */ Class val$requestedType;

                        AnonymousClass1(Class rawType22) {
                            r2 = rawType22;
                        }

                        @Override // com.google.gson.TypeAdapter
                        public void write(JsonWriter jsonWriter, T1 t1) throws IOException {
                            r2.write(jsonWriter, t1);
                        }

                        @Override // com.google.gson.TypeAdapter
                        /* renamed from: read */
                        public T1 read2(JsonReader jsonReader) throws IOException {
                            T1 t1 = (T1) r2.read2(jsonReader);
                            if (t1 == null || r2.isInstance(t1)) {
                                return t1;
                            }
                            throw new JsonSyntaxException("Expected a " + r2.getName() + " but was " + t1.getClass().getName());
                        }
                    };
                }
                return null;
            }

            /* renamed from: com.google.gson.internal.bind.TypeAdapters$35$1 */
            class AnonymousClass1<T1> extends TypeAdapter<T1> {
                final /* synthetic */ Class val$requestedType;

                AnonymousClass1(Class rawType22) {
                    r2 = rawType22;
                }

                @Override // com.google.gson.TypeAdapter
                public void write(JsonWriter jsonWriter, T1 t1) throws IOException {
                    r2.write(jsonWriter, t1);
                }

                @Override // com.google.gson.TypeAdapter
                /* renamed from: read */
                public T1 read2(JsonReader jsonReader) throws IOException {
                    T1 t1 = (T1) r2.read2(jsonReader);
                    if (t1 == null || r2.isInstance(t1)) {
                        return t1;
                    }
                    throw new JsonSyntaxException("Expected a " + r2.getName() + " but was " + t1.getClass().getName());
                }
            }

            public String toString() {
                return "Factory[typeHierarchy=" + r1.getName() + ",adapter=" + r2 + "]";
            }
        };
    }
}
