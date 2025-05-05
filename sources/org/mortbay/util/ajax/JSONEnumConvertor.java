package org.mortbay.util.ajax;

import java.lang.reflect.Method;
import java.util.Map;
import org.mortbay.log.Log;
import org.mortbay.util.Loader;
import org.mortbay.util.ajax.JSON;

/* loaded from: classes3.dex */
public class JSONEnumConvertor implements JSON.Convertor {
    static /* synthetic */ Class class$java$lang$Class;
    static /* synthetic */ Class class$java$lang$String;
    private boolean _fromJSON;
    private Method _valueOf;

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    public JSONEnumConvertor() {
        this(false);
    }

    public JSONEnumConvertor(boolean z) {
        try {
            Class loadClass = Loader.loadClass(getClass(), "java.lang.Enum");
            Class<?> cls = class$java$lang$Class;
            if (cls == null) {
                cls = class$("java.lang.Class");
                class$java$lang$Class = cls;
            }
            Class<?> cls2 = class$java$lang$String;
            if (cls2 == null) {
                cls2 = class$("java.lang.String");
                class$java$lang$String = cls2;
            }
            this._valueOf = loadClass.getMethod("valueOf", cls, cls2);
            this._fromJSON = z;
        } catch (Exception e) {
            throw new RuntimeException("!Enums", e);
        }
    }

    @Override // org.mortbay.util.ajax.JSON.Convertor
    public Object fromJSON(Map map) {
        if (!this._fromJSON) {
            throw new UnsupportedOperationException();
        }
        try {
            return this._valueOf.invoke(null, Loader.loadClass(getClass(), (String) map.get("class")), map.get("value"));
        } catch (Exception e) {
            Log.warn(e);
            return null;
        }
    }

    @Override // org.mortbay.util.ajax.JSON.Convertor
    public void toJSON(Object obj, JSON.Output output) {
        if (this._fromJSON) {
            output.addClass(obj.getClass());
            output.add("value", obj.toString());
        } else {
            output.add(obj.toString());
        }
    }
}
