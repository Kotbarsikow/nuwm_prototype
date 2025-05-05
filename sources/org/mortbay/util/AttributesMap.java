package org.mortbay.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class AttributesMap implements Attributes {
    Map _map;

    public AttributesMap() {
        this._map = new HashMap();
    }

    public AttributesMap(Map map) {
        this._map = map;
    }

    @Override // org.mortbay.util.Attributes
    public void removeAttribute(String str) {
        this._map.remove(str);
    }

    @Override // org.mortbay.util.Attributes
    public void setAttribute(String str, Object obj) {
        if (obj == null) {
            this._map.remove(str);
        } else {
            this._map.put(str, obj);
        }
    }

    @Override // org.mortbay.util.Attributes
    public Object getAttribute(String str) {
        return this._map.get(str);
    }

    @Override // org.mortbay.util.Attributes
    public Enumeration getAttributeNames() {
        return Collections.enumeration(this._map.keySet());
    }

    public static Enumeration getAttributeNamesCopy(Attributes attributes) {
        if (attributes instanceof AttributesMap) {
            return Collections.enumeration(((AttributesMap) attributes)._map.keySet());
        }
        ArrayList arrayList = new ArrayList();
        Enumeration attributeNames = attributes.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            arrayList.add(attributeNames.nextElement());
        }
        return Collections.enumeration(arrayList);
    }

    @Override // org.mortbay.util.Attributes
    public void clearAttributes() {
        this._map.clear();
    }

    public String toString() {
        return this._map.toString();
    }
}
