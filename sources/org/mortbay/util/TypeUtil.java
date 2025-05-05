package org.mortbay.util;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import org.mortbay.log.Log;

/* loaded from: classes3.dex */
public class TypeUtil {
    public static int CR = 13;
    public static int LF = 10;
    static /* synthetic */ Class class$java$lang$Boolean;
    static /* synthetic */ Class class$java$lang$Byte;
    static /* synthetic */ Class class$java$lang$Character;
    static /* synthetic */ Class class$java$lang$Double;
    static /* synthetic */ Class class$java$lang$Float;
    static /* synthetic */ Class class$java$lang$Integer;
    static /* synthetic */ Class class$java$lang$Long;
    static /* synthetic */ Class class$java$lang$Short;
    static /* synthetic */ Class class$java$lang$String;
    private static final HashMap class2Name;
    private static final HashMap class2Value;
    private static int intCacheSize;
    private static Integer[] integerCache;
    private static String[] integerStrCache;
    private static Long[] longCache;
    private static int longCacheSize;
    private static Integer minusOne;
    private static Long minusOneL;
    private static final HashMap name2Class;
    private static Class[] stringArg;

    public static byte convertHexDigit(byte b) {
        int i;
        if (b >= 48 && b <= 57) {
            i = b - 48;
        } else if (b >= 97 && b <= 102) {
            i = b - 87;
        } else {
            if (b < 65 || b > 70) {
                return (byte) 0;
            }
            i = b - 55;
        }
        return (byte) i;
    }

    static {
        HashMap hashMap = new HashMap();
        name2Class = hashMap;
        hashMap.put(TypedValues.Custom.S_BOOLEAN, Boolean.TYPE);
        hashMap.put("byte", Byte.TYPE);
        hashMap.put("char", Character.TYPE);
        hashMap.put("double", Double.TYPE);
        hashMap.put(TypedValues.Custom.S_FLOAT, Float.TYPE);
        hashMap.put("int", Integer.TYPE);
        hashMap.put("long", Long.TYPE);
        hashMap.put("short", Short.TYPE);
        hashMap.put("void", Void.TYPE);
        hashMap.put("java.lang.Boolean.TYPE", Boolean.TYPE);
        hashMap.put("java.lang.Byte.TYPE", Byte.TYPE);
        hashMap.put("java.lang.Character.TYPE", Character.TYPE);
        hashMap.put("java.lang.Double.TYPE", Double.TYPE);
        hashMap.put("java.lang.Float.TYPE", Float.TYPE);
        hashMap.put("java.lang.Integer.TYPE", Integer.TYPE);
        hashMap.put("java.lang.Long.TYPE", Long.TYPE);
        hashMap.put("java.lang.Short.TYPE", Short.TYPE);
        hashMap.put("java.lang.Void.TYPE", Void.TYPE);
        Class cls = class$java$lang$Boolean;
        if (cls == null) {
            cls = class$("java.lang.Boolean");
            class$java$lang$Boolean = cls;
        }
        hashMap.put("java.lang.Boolean", cls);
        Class cls2 = class$java$lang$Byte;
        if (cls2 == null) {
            cls2 = class$("java.lang.Byte");
            class$java$lang$Byte = cls2;
        }
        hashMap.put("java.lang.Byte", cls2);
        Class cls3 = class$java$lang$Character;
        if (cls3 == null) {
            cls3 = class$("java.lang.Character");
            class$java$lang$Character = cls3;
        }
        hashMap.put("java.lang.Character", cls3);
        Class cls4 = class$java$lang$Double;
        if (cls4 == null) {
            cls4 = class$("java.lang.Double");
            class$java$lang$Double = cls4;
        }
        hashMap.put("java.lang.Double", cls4);
        Class cls5 = class$java$lang$Float;
        if (cls5 == null) {
            cls5 = class$("java.lang.Float");
            class$java$lang$Float = cls5;
        }
        hashMap.put("java.lang.Float", cls5);
        Class cls6 = class$java$lang$Integer;
        if (cls6 == null) {
            cls6 = class$("java.lang.Integer");
            class$java$lang$Integer = cls6;
        }
        hashMap.put("java.lang.Integer", cls6);
        Class cls7 = class$java$lang$Long;
        if (cls7 == null) {
            cls7 = class$("java.lang.Long");
            class$java$lang$Long = cls7;
        }
        hashMap.put("java.lang.Long", cls7);
        Class cls8 = class$java$lang$Short;
        if (cls8 == null) {
            cls8 = class$("java.lang.Short");
            class$java$lang$Short = cls8;
        }
        hashMap.put("java.lang.Short", cls8);
        Class cls9 = class$java$lang$Boolean;
        if (cls9 == null) {
            cls9 = class$("java.lang.Boolean");
            class$java$lang$Boolean = cls9;
        }
        hashMap.put("Boolean", cls9);
        Class cls10 = class$java$lang$Byte;
        if (cls10 == null) {
            cls10 = class$("java.lang.Byte");
            class$java$lang$Byte = cls10;
        }
        hashMap.put("Byte", cls10);
        Class cls11 = class$java$lang$Character;
        if (cls11 == null) {
            cls11 = class$("java.lang.Character");
            class$java$lang$Character = cls11;
        }
        hashMap.put("Character", cls11);
        Class cls12 = class$java$lang$Double;
        if (cls12 == null) {
            cls12 = class$("java.lang.Double");
            class$java$lang$Double = cls12;
        }
        hashMap.put("Double", cls12);
        Class cls13 = class$java$lang$Float;
        if (cls13 == null) {
            cls13 = class$("java.lang.Float");
            class$java$lang$Float = cls13;
        }
        hashMap.put("Float", cls13);
        Class cls14 = class$java$lang$Integer;
        if (cls14 == null) {
            cls14 = class$("java.lang.Integer");
            class$java$lang$Integer = cls14;
        }
        hashMap.put("Integer", cls14);
        Class cls15 = class$java$lang$Long;
        if (cls15 == null) {
            cls15 = class$("java.lang.Long");
            class$java$lang$Long = cls15;
        }
        hashMap.put("Long", cls15);
        Class cls16 = class$java$lang$Short;
        if (cls16 == null) {
            cls16 = class$("java.lang.Short");
            class$java$lang$Short = cls16;
        }
        hashMap.put("Short", cls16);
        hashMap.put(null, Void.TYPE);
        Class cls17 = class$java$lang$String;
        if (cls17 == null) {
            cls17 = class$("java.lang.String");
            class$java$lang$String = cls17;
        }
        hashMap.put(TypedValues.Custom.S_STRING, cls17);
        Class cls18 = class$java$lang$String;
        if (cls18 == null) {
            cls18 = class$("java.lang.String");
            class$java$lang$String = cls18;
        }
        hashMap.put("String", cls18);
        Class cls19 = class$java$lang$String;
        if (cls19 == null) {
            cls19 = class$("java.lang.String");
            class$java$lang$String = cls19;
        }
        hashMap.put("java.lang.String", cls19);
        HashMap hashMap2 = new HashMap();
        class2Name = hashMap2;
        hashMap2.put(Boolean.TYPE, TypedValues.Custom.S_BOOLEAN);
        hashMap2.put(Byte.TYPE, "byte");
        hashMap2.put(Character.TYPE, "char");
        hashMap2.put(Double.TYPE, "double");
        hashMap2.put(Float.TYPE, TypedValues.Custom.S_FLOAT);
        hashMap2.put(Integer.TYPE, "int");
        hashMap2.put(Long.TYPE, "long");
        hashMap2.put(Short.TYPE, "short");
        hashMap2.put(Void.TYPE, "void");
        Class cls20 = class$java$lang$Boolean;
        if (cls20 == null) {
            cls20 = class$("java.lang.Boolean");
            class$java$lang$Boolean = cls20;
        }
        hashMap2.put(cls20, "java.lang.Boolean");
        Class cls21 = class$java$lang$Byte;
        if (cls21 == null) {
            cls21 = class$("java.lang.Byte");
            class$java$lang$Byte = cls21;
        }
        hashMap2.put(cls21, "java.lang.Byte");
        Class cls22 = class$java$lang$Character;
        if (cls22 == null) {
            cls22 = class$("java.lang.Character");
            class$java$lang$Character = cls22;
        }
        hashMap2.put(cls22, "java.lang.Character");
        Class cls23 = class$java$lang$Double;
        if (cls23 == null) {
            cls23 = class$("java.lang.Double");
            class$java$lang$Double = cls23;
        }
        hashMap2.put(cls23, "java.lang.Double");
        Class cls24 = class$java$lang$Float;
        if (cls24 == null) {
            cls24 = class$("java.lang.Float");
            class$java$lang$Float = cls24;
        }
        hashMap2.put(cls24, "java.lang.Float");
        Class cls25 = class$java$lang$Integer;
        if (cls25 == null) {
            cls25 = class$("java.lang.Integer");
            class$java$lang$Integer = cls25;
        }
        hashMap2.put(cls25, "java.lang.Integer");
        Class cls26 = class$java$lang$Long;
        if (cls26 == null) {
            cls26 = class$("java.lang.Long");
            class$java$lang$Long = cls26;
        }
        hashMap2.put(cls26, "java.lang.Long");
        Class cls27 = class$java$lang$Short;
        if (cls27 == null) {
            cls27 = class$("java.lang.Short");
            class$java$lang$Short = cls27;
        }
        hashMap2.put(cls27, "java.lang.Short");
        hashMap2.put(null, "void");
        Class cls28 = class$java$lang$String;
        if (cls28 == null) {
            cls28 = class$("java.lang.String");
            class$java$lang$String = cls28;
        }
        hashMap2.put(cls28, "java.lang.String");
        HashMap hashMap3 = new HashMap();
        class2Value = hashMap3;
        try {
            Class<?> cls29 = class$java$lang$String;
            if (cls29 == null) {
                cls29 = class$("java.lang.String");
                class$java$lang$String = cls29;
            }
            Class<?>[] clsArr = {cls29};
            Class cls30 = Boolean.TYPE;
            Class cls31 = class$java$lang$Boolean;
            if (cls31 == null) {
                cls31 = class$("java.lang.Boolean");
                class$java$lang$Boolean = cls31;
            }
            hashMap3.put(cls30, cls31.getMethod("valueOf", clsArr));
            Class cls32 = Byte.TYPE;
            Class cls33 = class$java$lang$Byte;
            if (cls33 == null) {
                cls33 = class$("java.lang.Byte");
                class$java$lang$Byte = cls33;
            }
            hashMap3.put(cls32, cls33.getMethod("valueOf", clsArr));
            Class cls34 = Double.TYPE;
            Class cls35 = class$java$lang$Double;
            if (cls35 == null) {
                cls35 = class$("java.lang.Double");
                class$java$lang$Double = cls35;
            }
            hashMap3.put(cls34, cls35.getMethod("valueOf", clsArr));
            Class cls36 = Float.TYPE;
            Class cls37 = class$java$lang$Float;
            if (cls37 == null) {
                cls37 = class$("java.lang.Float");
                class$java$lang$Float = cls37;
            }
            hashMap3.put(cls36, cls37.getMethod("valueOf", clsArr));
            Class cls38 = Integer.TYPE;
            Class cls39 = class$java$lang$Integer;
            if (cls39 == null) {
                cls39 = class$("java.lang.Integer");
                class$java$lang$Integer = cls39;
            }
            hashMap3.put(cls38, cls39.getMethod("valueOf", clsArr));
            Class cls40 = Long.TYPE;
            Class cls41 = class$java$lang$Long;
            if (cls41 == null) {
                cls41 = class$("java.lang.Long");
                class$java$lang$Long = cls41;
            }
            hashMap3.put(cls40, cls41.getMethod("valueOf", clsArr));
            Class cls42 = Short.TYPE;
            Class cls43 = class$java$lang$Short;
            if (cls43 == null) {
                cls43 = class$("java.lang.Short");
                class$java$lang$Short = cls43;
            }
            hashMap3.put(cls42, cls43.getMethod("valueOf", clsArr));
            Class cls44 = class$java$lang$Boolean;
            if (cls44 == null) {
                cls44 = class$("java.lang.Boolean");
                class$java$lang$Boolean = cls44;
            }
            Class cls45 = class$java$lang$Boolean;
            if (cls45 == null) {
                cls45 = class$("java.lang.Boolean");
                class$java$lang$Boolean = cls45;
            }
            hashMap3.put(cls44, cls45.getMethod("valueOf", clsArr));
            Class cls46 = class$java$lang$Byte;
            if (cls46 == null) {
                cls46 = class$("java.lang.Byte");
                class$java$lang$Byte = cls46;
            }
            Class cls47 = class$java$lang$Byte;
            if (cls47 == null) {
                cls47 = class$("java.lang.Byte");
                class$java$lang$Byte = cls47;
            }
            hashMap3.put(cls46, cls47.getMethod("valueOf", clsArr));
            Class cls48 = class$java$lang$Double;
            if (cls48 == null) {
                cls48 = class$("java.lang.Double");
                class$java$lang$Double = cls48;
            }
            Class cls49 = class$java$lang$Double;
            if (cls49 == null) {
                cls49 = class$("java.lang.Double");
                class$java$lang$Double = cls49;
            }
            hashMap3.put(cls48, cls49.getMethod("valueOf", clsArr));
            Class cls50 = class$java$lang$Float;
            if (cls50 == null) {
                cls50 = class$("java.lang.Float");
                class$java$lang$Float = cls50;
            }
            Class cls51 = class$java$lang$Float;
            if (cls51 == null) {
                cls51 = class$("java.lang.Float");
                class$java$lang$Float = cls51;
            }
            hashMap3.put(cls50, cls51.getMethod("valueOf", clsArr));
            Class cls52 = class$java$lang$Integer;
            if (cls52 == null) {
                cls52 = class$("java.lang.Integer");
                class$java$lang$Integer = cls52;
            }
            Class cls53 = class$java$lang$Integer;
            if (cls53 == null) {
                cls53 = class$("java.lang.Integer");
                class$java$lang$Integer = cls53;
            }
            hashMap3.put(cls52, cls53.getMethod("valueOf", clsArr));
            Class cls54 = class$java$lang$Long;
            if (cls54 == null) {
                cls54 = class$("java.lang.Long");
                class$java$lang$Long = cls54;
            }
            Class cls55 = class$java$lang$Long;
            if (cls55 == null) {
                cls55 = class$("java.lang.Long");
                class$java$lang$Long = cls55;
            }
            hashMap3.put(cls54, cls55.getMethod("valueOf", clsArr));
            Class cls56 = class$java$lang$Short;
            if (cls56 == null) {
                cls56 = class$("java.lang.Short");
                class$java$lang$Short = cls56;
            }
            Class cls57 = class$java$lang$Short;
            if (cls57 == null) {
                cls57 = class$("java.lang.Short");
                class$java$lang$Short = cls57;
            }
            hashMap3.put(cls56, cls57.getMethod("valueOf", clsArr));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Class cls58 = class$java$lang$String;
        if (cls58 == null) {
            cls58 = class$("java.lang.String");
            class$java$lang$String = cls58;
        }
        stringArg = new Class[]{cls58};
        int intValue = Integer.getInteger("org.mortbay.util.TypeUtil.IntegerCacheSize", 600).intValue();
        intCacheSize = intValue;
        integerCache = new Integer[intValue];
        integerStrCache = new String[intValue];
        minusOne = new Integer(-1);
        int intValue2 = Integer.getInteger("org.mortbay.util.TypeUtil.LongCacheSize", 64).intValue();
        longCacheSize = intValue2;
        longCache = new Long[intValue2];
        minusOneL = new Long(-1L);
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    public static Class fromName(String str) {
        return (Class) name2Class.get(str);
    }

    public static String toName(Class cls) {
        return (String) class2Name.get(cls);
    }

    public static Object valueOf(Class cls, String str) {
        try {
            Class cls2 = class$java$lang$String;
            if (cls2 == null) {
                cls2 = class$("java.lang.String");
                class$java$lang$String = cls2;
            }
            if (cls.equals(cls2)) {
                return str;
            }
            Method method = (Method) class2Value.get(cls);
            if (method != null) {
                return method.invoke(null, str);
            }
            if (!cls.equals(Character.TYPE)) {
                Class cls3 = class$java$lang$Character;
                if (cls3 == null) {
                    cls3 = class$("java.lang.Character");
                    class$java$lang$Character = cls3;
                }
                if (!cls.equals(cls3)) {
                    return cls.getConstructor(stringArg).newInstance(str);
                }
            }
            return new Character(str.charAt(0));
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException unused) {
            return null;
        } catch (InvocationTargetException e) {
            if (e.getTargetException() instanceof Error) {
                throw ((Error) e.getTargetException());
            }
            return null;
        }
    }

    public static Object valueOf(String str, String str2) {
        return valueOf(fromName(str), str2);
    }

    public static Integer newInteger(int i) {
        if (i < 0 || i >= intCacheSize) {
            if (i == -1) {
                return minusOne;
            }
            return new Integer(i);
        }
        Integer[] numArr = integerCache;
        if (numArr[i] == null) {
            numArr[i] = new Integer(i);
        }
        return integerCache[i];
    }

    public static Long newLong(long j) {
        if (j < 0 || j >= longCacheSize) {
            if (j == -1) {
                return minusOneL;
            }
            return new Long(j);
        }
        Long[] lArr = longCache;
        int i = (int) j;
        if (lArr[i] == null) {
            lArr[i] = new Long(j);
        }
        return longCache[i];
    }

    public static String toString(int i) {
        if (i < 0 || i >= intCacheSize) {
            if (i == -1) {
                return "-1";
            }
            return Integer.toString(i);
        }
        String[] strArr = integerStrCache;
        if (strArr[i] == null) {
            strArr[i] = Integer.toString(i);
        }
        return integerStrCache[i];
    }

    public static String toString(long j) {
        if (j < 0 || j >= intCacheSize) {
            if (j == -1) {
                return "-1";
            }
            return Long.toString(j);
        }
        String[] strArr = integerStrCache;
        int i = (int) j;
        if (strArr[i] == null) {
            strArr[i] = Long.toString(j);
        }
        return integerStrCache[i];
    }

    public static int parseInt(String str, int i, int i2, int i3) throws NumberFormatException {
        if (i2 < 0) {
            i2 = str.length() - i;
        }
        int i4 = 0;
        for (int i5 = 0; i5 < i2; i5++) {
            char charAt = str.charAt(i + i5);
            int i6 = charAt - '0';
            if ((i6 < 0 || i6 >= i3 || i6 >= 10) && (charAt - '7' < 10 || i6 >= i3)) {
                i6 = charAt - 'W';
            }
            if (i6 < 0 || i6 >= i3) {
                throw new NumberFormatException(str.substring(i, i2 + i));
            }
            i4 = (i4 * i3) + i6;
        }
        return i4;
    }

    public static int parseInt(byte[] bArr, int i, int i2, int i3) throws NumberFormatException {
        if (i2 < 0) {
            i2 = bArr.length - i;
        }
        int i4 = 0;
        for (int i5 = 0; i5 < i2; i5++) {
            char c = (char) (bArr[i + i5] & 255);
            int i6 = c - '0';
            if ((i6 < 0 || i6 >= i3 || i6 >= 10) && (c - '7' < 10 || i6 >= i3)) {
                i6 = c - 'W';
            }
            if (i6 < 0 || i6 >= i3) {
                throw new NumberFormatException(new String(bArr, i, i2));
            }
            i4 = (i4 * i3) + i6;
        }
        return i4;
    }

    public static byte[] parseBytes(String str, int i) {
        byte[] bArr = new byte[str.length() / 2];
        for (int i2 = 0; i2 < str.length(); i2 += 2) {
            bArr[i2 / 2] = (byte) parseInt(str, i2, 2, i);
        }
        return bArr;
    }

    public static String toString(byte[] bArr, int i) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 : bArr) {
            int i3 = i2 & 255;
            int i4 = (i3 / i) % i;
            int i5 = i4 + 48;
            if (i5 > 57) {
                i5 = i4 + 87;
            }
            stringBuffer.append((char) i5);
            int i6 = i3 % i;
            int i7 = i6 + 48;
            if (i7 > 57) {
                i7 = i6 + 87;
            }
            stringBuffer.append((char) i7);
        }
        return stringBuffer.toString();
    }

    public static String toHexString(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            int i = b & 255;
            int i2 = (i / 16) % 16;
            int i3 = i2 + 48;
            if (i3 > 57) {
                i3 = i2 + 55;
            }
            stringBuffer.append((char) i3);
            int i4 = i % 16;
            int i5 = i4 + 48;
            if (i5 > 57) {
                i5 = i4 + 87;
            }
            stringBuffer.append((char) i5);
        }
        return stringBuffer.toString();
    }

    public static String toHexString(byte[] bArr, int i, int i2) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i3 = i; i3 < i + i2; i3++) {
            int i4 = bArr[i3] & 255;
            int i5 = (i4 / 16) % 16;
            int i6 = i5 + 48;
            if (i6 > 57) {
                i6 = i5 + 55;
            }
            stringBuffer.append((char) i6);
            int i7 = i4 % 16;
            int i8 = i7 + 48;
            if (i8 > 57) {
                i8 = i7 + 87;
            }
            stringBuffer.append((char) i8);
        }
        return stringBuffer.toString();
    }

    public static byte[] fromHexString(String str) {
        if (str.length() % 2 != 0) {
            throw new IllegalArgumentException(str);
        }
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) (Integer.parseInt(str.substring(i2, i2 + 2), 16) & 255);
        }
        return bArr;
    }

    public static void dump(Class cls) {
        System.err.println(new StringBuffer("Dump: ").append(cls).toString());
        dump(cls.getClassLoader());
    }

    public static void dump(ClassLoader classLoader) {
        System.err.println("Dump Loaders:");
        while (classLoader != null) {
            System.err.println(new StringBuffer("  loader ").append(classLoader).toString());
            classLoader = classLoader.getParent();
        }
    }

    public static byte[] readLine(InputStream inputStream) throws IOException {
        int read;
        byte[] bArr = new byte[256];
        int i = 0;
        int i2 = 0;
        while (true) {
            read = inputStream.read();
            if (read < 0) {
                break;
            }
            i++;
            if (i != 1 || read != LF) {
                if (read == CR || read == LF) {
                    break;
                }
                if (i2 >= bArr.length) {
                    byte[] bArr2 = new byte[bArr.length + 256];
                    System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
                    bArr = bArr2;
                }
                bArr[i2] = (byte) read;
                i2++;
            }
        }
        if (read == -1 && i2 == 0) {
            return null;
        }
        if (read == CR && inputStream.available() >= 1 && inputStream.markSupported()) {
            inputStream.mark(1);
            if (inputStream.read() != LF) {
                inputStream.reset();
            }
        }
        byte[] bArr3 = new byte[i2];
        System.arraycopy(bArr, 0, bArr3, 0, i2);
        return bArr3;
    }

    public static URL jarFor(String str) {
        try {
            String url = Loader.getResource(null, new StringBuffer().append(str.replace('.', '/')).append(".class").toString(), false).toString();
            if (url.startsWith("jar:file:")) {
                return new URL(url.substring(4, url.indexOf("!/")));
            }
        } catch (Exception e) {
            Log.ignore(e);
        }
        return null;
    }
}
