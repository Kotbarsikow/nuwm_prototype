package org.mortbay.util.ajax;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.mortbay.log.Log;
import org.mortbay.util.ajax.JSON;

/* loaded from: classes3.dex */
public class JSONPojoConvertor implements JSON.Convertor {
    public static final NumberType DOUBLE;
    public static final NumberType FLOAT;
    public static final NumberType INTEGER;
    public static final NumberType LONG;
    public static final NumberType SHORT;
    private static final Map __numberTypes;
    static /* synthetic */ Class class$java$lang$Double;
    static /* synthetic */ Class class$java$lang$Float;
    static /* synthetic */ Class class$java$lang$Integer;
    static /* synthetic */ Class class$java$lang$Long;
    static /* synthetic */ Class class$java$lang$Object;
    static /* synthetic */ Class class$java$lang$Short;
    protected Set _excluded;
    protected boolean _fromJSON;
    protected Map _getters;
    protected Class _pojoClass;
    protected Map _setters;
    public static final Object[] GETTER_ARG = new Object[0];
    public static final Object[] NULL_ARG = {null};

    public interface NumberType {
        Object getActualValue(Number number);
    }

    static {
        HashMap hashMap = new HashMap();
        __numberTypes = hashMap;
        NumberType numberType = new NumberType() { // from class: org.mortbay.util.ajax.JSONPojoConvertor.1
            @Override // org.mortbay.util.ajax.JSONPojoConvertor.NumberType
            public Object getActualValue(Number number) {
                return new Short(number.shortValue());
            }
        };
        SHORT = numberType;
        NumberType numberType2 = new NumberType() { // from class: org.mortbay.util.ajax.JSONPojoConvertor.2
            @Override // org.mortbay.util.ajax.JSONPojoConvertor.NumberType
            public Object getActualValue(Number number) {
                return new Integer(number.intValue());
            }
        };
        INTEGER = numberType2;
        NumberType numberType3 = new NumberType() { // from class: org.mortbay.util.ajax.JSONPojoConvertor.3
            @Override // org.mortbay.util.ajax.JSONPojoConvertor.NumberType
            public Object getActualValue(Number number) {
                return new Float(number.floatValue());
            }
        };
        FLOAT = numberType3;
        NumberType numberType4 = new NumberType() { // from class: org.mortbay.util.ajax.JSONPojoConvertor.4
            @Override // org.mortbay.util.ajax.JSONPojoConvertor.NumberType
            public Object getActualValue(Number number) {
                return number instanceof Long ? number : new Long(number.longValue());
            }
        };
        LONG = numberType4;
        NumberType numberType5 = new NumberType() { // from class: org.mortbay.util.ajax.JSONPojoConvertor.5
            @Override // org.mortbay.util.ajax.JSONPojoConvertor.NumberType
            public Object getActualValue(Number number) {
                return number instanceof Double ? number : new Double(number.doubleValue());
            }
        };
        DOUBLE = numberType5;
        Class cls = class$java$lang$Short;
        if (cls == null) {
            cls = class$("java.lang.Short");
            class$java$lang$Short = cls;
        }
        hashMap.put(cls, numberType);
        hashMap.put(Short.TYPE, numberType);
        Class cls2 = class$java$lang$Integer;
        if (cls2 == null) {
            cls2 = class$("java.lang.Integer");
            class$java$lang$Integer = cls2;
        }
        hashMap.put(cls2, numberType2);
        hashMap.put(Integer.TYPE, numberType2);
        Class cls3 = class$java$lang$Long;
        if (cls3 == null) {
            cls3 = class$("java.lang.Long");
            class$java$lang$Long = cls3;
        }
        hashMap.put(cls3, numberType4);
        hashMap.put(Long.TYPE, numberType4);
        Class cls4 = class$java$lang$Float;
        if (cls4 == null) {
            cls4 = class$("java.lang.Float");
            class$java$lang$Float = cls4;
        }
        hashMap.put(cls4, numberType3);
        hashMap.put(Float.TYPE, numberType3);
        Class cls5 = class$java$lang$Double;
        if (cls5 == null) {
            cls5 = class$("java.lang.Double");
            class$java$lang$Double = cls5;
        }
        hashMap.put(cls5, numberType5);
        hashMap.put(Double.TYPE, numberType5);
    }

    public static NumberType getNumberType(Class cls) {
        return (NumberType) __numberTypes.get(cls);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public JSONPojoConvertor(Class cls) {
        this(cls, null, true);
    }

    public JSONPojoConvertor(Class cls, String[] strArr) {
        this(cls, new HashSet(Arrays.asList(strArr)), true);
    }

    public JSONPojoConvertor(Class cls, Set set) {
        this(cls, set, true);
    }

    public JSONPojoConvertor(Class cls, Set set, boolean z) {
        this._getters = new HashMap();
        this._setters = new HashMap();
        this._pojoClass = cls;
        this._excluded = set;
        this._fromJSON = z;
        init();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public JSONPojoConvertor(Class cls, boolean z) {
        this(cls, null, z);
    }

    protected void init() {
        String stringBuffer;
        for (Method method : this._pojoClass.getMethods()) {
            if (!Modifier.isStatic(method.getModifiers())) {
                Class<?> declaringClass = method.getDeclaringClass();
                Class<?> cls = class$java$lang$Object;
                if (cls == null) {
                    cls = class$("java.lang.Object");
                    class$java$lang$Object = cls;
                }
                if (declaringClass != cls) {
                    String name = method.getName();
                    int length = method.getParameterTypes().length;
                    if (length == 0) {
                        if (method.getReturnType() != null) {
                            if (name.startsWith("is") && name.length() > 2) {
                                stringBuffer = new StringBuffer().append(name.substring(2, 3).toLowerCase()).append(name.substring(3)).toString();
                            } else if (name.startsWith("get") && name.length() > 3) {
                                stringBuffer = new StringBuffer().append(name.substring(3, 4).toLowerCase()).append(name.substring(4)).toString();
                            }
                            if (includeField(stringBuffer, method)) {
                                addGetter(stringBuffer, method);
                            }
                        }
                    } else if (length == 1 && name.startsWith("set") && name.length() > 3) {
                        String stringBuffer2 = new StringBuffer().append(name.substring(3, 4).toLowerCase()).append(name.substring(4)).toString();
                        if (includeField(stringBuffer2, method)) {
                            addSetter(stringBuffer2, method);
                        }
                    }
                }
            }
        }
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    protected void addGetter(String str, Method method) {
        this._getters.put(str, method);
    }

    protected void addSetter(String str, Method method) {
        this._setters.put(str, new Setter(str, method));
    }

    protected Setter getSetter(String str) {
        return (Setter) this._setters.get(str);
    }

    protected boolean includeField(String str, Method method) {
        Set set = this._excluded;
        return set == null || !set.contains(str);
    }

    protected int getExcludedCount() {
        Set set = this._excluded;
        if (set == null) {
            return 0;
        }
        return set.size();
    }

    @Override // org.mortbay.util.ajax.JSON.Convertor
    public Object fromJSON(Map map) {
        try {
            Object newInstance = this._pojoClass.newInstance();
            setProps(newInstance, map);
            return newInstance;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int setProps(Object obj, Map map) {
        int i = 0;
        for (Map.Entry entry : map.entrySet()) {
            Setter setter = getSetter((String) entry.getKey());
            if (setter != null) {
                try {
                    setter.invoke(obj, entry.getValue());
                    i++;
                } catch (Exception e) {
                    Log.warn("{} property '{}' not set. (errors)", this._pojoClass.getName(), setter.getPropertyName());
                    log(e);
                }
            }
        }
        return i;
    }

    @Override // org.mortbay.util.ajax.JSON.Convertor
    public void toJSON(Object obj, JSON.Output output) {
        if (this._fromJSON) {
            output.addClass(this._pojoClass);
        }
        for (Map.Entry entry : this._getters.entrySet()) {
            try {
                output.add((String) entry.getKey(), ((Method) entry.getValue()).invoke(obj, GETTER_ARG));
            } catch (Exception e) {
                Log.warn("{} property '{}' excluded. (errors)", this._pojoClass.getName(), entry.getKey());
                log(e);
            }
        }
    }

    protected void log(Throwable th) {
        Log.ignore(th);
    }

    public static class Setter {
        protected Class _componentType;
        protected Method _method;
        protected NumberType _numberType;
        protected String _propertyName;
        protected Class _type;

        public Setter(String str, Method method) {
            this._propertyName = str;
            this._method = method;
            this._type = method.getParameterTypes()[0];
            NumberType numberType = (NumberType) JSONPojoConvertor.__numberTypes.get(this._type);
            this._numberType = numberType;
            if (numberType == null && this._type.isArray()) {
                this._componentType = this._type.getComponentType();
                this._numberType = (NumberType) JSONPojoConvertor.__numberTypes.get(this._componentType);
            }
        }

        public String getPropertyName() {
            return this._propertyName;
        }

        public Method getMethod() {
            return this._method;
        }

        public NumberType getNumberType() {
            return this._numberType;
        }

        public Class getType() {
            return this._type;
        }

        public Class getComponentType() {
            return this._componentType;
        }

        public boolean isPropertyNumber() {
            return this._numberType != null;
        }

        public void invoke(Object obj, Object obj2) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
            if (obj2 == null) {
                this._method.invoke(obj, JSONPojoConvertor.NULL_ARG);
            } else {
                invokeObject(obj, obj2);
            }
        }

        protected void invokeObject(Object obj, Object obj2) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
            NumberType numberType = this._numberType;
            if (numberType != null && (obj2 instanceof Number)) {
                this._method.invoke(obj, numberType.getActualValue((Number) obj2));
                return;
            }
            if (this._componentType != null && obj2.getClass().isArray()) {
                if (this._numberType == null) {
                    int length = Array.getLength(obj2);
                    Object newInstance = Array.newInstance((Class<?>) this._componentType, length);
                    try {
                        System.arraycopy(obj2, 0, newInstance, 0, length);
                        this._method.invoke(obj, newInstance);
                        return;
                    } catch (Exception e) {
                        Log.ignore(e);
                        this._method.invoke(obj, obj2);
                        return;
                    }
                }
                Object[] objArr = (Object[]) obj2;
                Object newInstance2 = Array.newInstance((Class<?>) this._componentType, objArr.length);
                for (int i = 0; i < objArr.length; i++) {
                    try {
                        Array.set(newInstance2, i, this._numberType.getActualValue((Number) objArr[i]));
                    } catch (Exception e2) {
                        Log.ignore(e2);
                        this._method.invoke(obj, obj2);
                        return;
                    }
                }
                this._method.invoke(obj, newInstance2);
                return;
            }
            this._method.invoke(obj, obj2);
        }
    }
}
