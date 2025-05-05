package com.google.api.client.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.WeakHashMap;

/* loaded from: classes2.dex */
public class FieldInfo {
    private static final Map<Field, FieldInfo> CACHE = new WeakHashMap();
    private final Field field;
    private final boolean isPrimitive;
    private final String name;

    public static FieldInfo of(Enum<?> r5) {
        try {
            FieldInfo of = of(r5.getClass().getField(r5.name()));
            Preconditions.checkArgument(of != null, "enum constant missing @Value or @NullValue annotation: %s", r5);
            return of;
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    public static FieldInfo of(Field field) {
        String str = null;
        if (field == null) {
            return null;
        }
        Map<Field, FieldInfo> map = CACHE;
        synchronized (map) {
            FieldInfo fieldInfo = map.get(field);
            boolean isEnumConstant = field.isEnumConstant();
            if (fieldInfo == null && (isEnumConstant || !Modifier.isStatic(field.getModifiers()))) {
                if (isEnumConstant) {
                    Value value = (Value) field.getAnnotation(Value.class);
                    if (value != null) {
                        str = value.value();
                    } else if (((NullValue) field.getAnnotation(NullValue.class)) == null) {
                        return null;
                    }
                } else {
                    Key key = (Key) field.getAnnotation(Key.class);
                    if (key == null) {
                        return null;
                    }
                    str = key.value();
                    field.setAccessible(true);
                }
                if ("##default".equals(str)) {
                    str = field.getName();
                }
                fieldInfo = new FieldInfo(field, str);
                map.put(field, fieldInfo);
            }
            return fieldInfo;
        }
    }

    FieldInfo(Field field, String str) {
        this.field = field;
        this.name = str == null ? null : str.intern();
        this.isPrimitive = Data.isPrimitive(getType());
    }

    public Field getField() {
        return this.field;
    }

    public String getName() {
        return this.name;
    }

    public Class<?> getType() {
        return this.field.getType();
    }

    public Type getGenericType() {
        return this.field.getGenericType();
    }

    public boolean isFinal() {
        return Modifier.isFinal(this.field.getModifiers());
    }

    public boolean isPrimitive() {
        return this.isPrimitive;
    }

    public Object getValue(Object obj) {
        return getFieldValue(this.field, obj);
    }

    public void setValue(Object obj, Object obj2) {
        setFieldValue(this.field, obj, obj2);
    }

    public ClassInfo getClassInfo() {
        return ClassInfo.of(this.field.getDeclaringClass());
    }

    public <T extends Enum<T>> T enumValue() {
        return (T) Enum.valueOf(this.field.getDeclaringClass(), this.field.getName());
    }

    public static Object getFieldValue(Field field, Object obj) {
        try {
            return field.get(obj);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static void setFieldValue(Field field, Object obj, Object obj2) {
        if (Modifier.isFinal(field.getModifiers())) {
            Object fieldValue = getFieldValue(field, obj);
            if (obj2 == null) {
                if (fieldValue == null) {
                    return;
                }
            } else if (obj2.equals(fieldValue)) {
                return;
            }
            String valueOf = String.valueOf(String.valueOf(fieldValue));
            String valueOf2 = String.valueOf(String.valueOf(obj2));
            String valueOf3 = String.valueOf(String.valueOf(field.getName()));
            String valueOf4 = String.valueOf(String.valueOf(obj.getClass().getName()));
            StringBuilder sb = new StringBuilder(valueOf.length() + 48 + valueOf2.length() + valueOf3.length() + valueOf4.length());
            sb.append("expected final value <");
            sb.append(valueOf);
            sb.append("> but was <");
            sb.append(valueOf2);
            sb.append("> on ");
            sb.append(valueOf3);
            sb.append(" field in ");
            sb.append(valueOf4);
            throw new IllegalArgumentException(sb.toString());
        }
        try {
            field.set(obj, obj2);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        } catch (SecurityException e2) {
            throw new IllegalArgumentException(e2);
        }
    }
}
