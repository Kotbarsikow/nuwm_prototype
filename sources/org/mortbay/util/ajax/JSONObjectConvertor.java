package org.mortbay.util.ajax;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.mortbay.util.ajax.JSON;

/* loaded from: classes3.dex */
public class JSONObjectConvertor implements JSON.Convertor {
    static /* synthetic */ Class class$java$lang$Object;
    private Set _excluded;
    private boolean _fromJSON;

    public JSONObjectConvertor() {
        this._excluded = null;
        this._fromJSON = false;
    }

    public JSONObjectConvertor(boolean z) {
        this._excluded = null;
        this._fromJSON = z;
    }

    public JSONObjectConvertor(boolean z, String[] strArr) {
        this._excluded = null;
        this._fromJSON = z;
        if (strArr != null) {
            this._excluded = new HashSet(Arrays.asList(strArr));
        }
    }

    @Override // org.mortbay.util.ajax.JSON.Convertor
    public Object fromJSON(Map map) {
        if (this._fromJSON) {
            throw new UnsupportedOperationException();
        }
        return map;
    }

    @Override // org.mortbay.util.ajax.JSON.Convertor
    public void toJSON(Object obj, JSON.Output output) {
        String stringBuffer;
        try {
            obj.getClass();
            if (this._fromJSON) {
                output.addClass(obj.getClass());
            }
            for (Method method : obj.getClass().getMethods()) {
                if (!Modifier.isStatic(method.getModifiers()) && method.getParameterTypes().length == 0 && method.getReturnType() != null) {
                    Class<?> declaringClass = method.getDeclaringClass();
                    Class<?> cls = class$java$lang$Object;
                    if (cls == null) {
                        cls = class$("java.lang.Object");
                        class$java$lang$Object = cls;
                    }
                    if (declaringClass != cls) {
                        String name = method.getName();
                        if (name.startsWith("is")) {
                            stringBuffer = new StringBuffer().append(name.substring(2, 3).toLowerCase()).append(name.substring(3)).toString();
                        } else if (name.startsWith("get")) {
                            stringBuffer = new StringBuffer().append(name.substring(3, 4).toLowerCase()).append(name.substring(4)).toString();
                        }
                        if (includeField(stringBuffer, obj, method)) {
                            output.add(stringBuffer, method.invoke(obj, null));
                        }
                    }
                }
            }
        } catch (Throwable th) {
            throw new RuntimeException("Illegal argument", th);
        }
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    protected boolean includeField(String str, Object obj, Method method) {
        Set set = this._excluded;
        return set == null || !set.contains(str);
    }
}
