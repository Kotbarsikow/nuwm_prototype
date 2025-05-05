package org.mortbay.component;

import java.util.EventListener;
import org.mortbay.log.Log;
import org.mortbay.util.LazyList;

/* loaded from: classes3.dex */
public class Container {
    private Object _listeners;

    public interface Listener extends EventListener {
        void add(Relationship relationship);

        void addBean(Object obj);

        void remove(Relationship relationship);

        void removeBean(Object obj);
    }

    public synchronized void addEventListener(Listener listener) {
        this._listeners = LazyList.add(this._listeners, listener);
    }

    public synchronized void removeEventListener(Listener listener) {
        this._listeners = LazyList.remove(this._listeners, listener);
    }

    public synchronized void update(Object obj, Object obj2, Object obj3, String str) {
        if (obj2 != null) {
            try {
                if (!obj2.equals(obj3)) {
                    remove(obj, obj2, str);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        if (obj3 != null && !obj3.equals(obj2)) {
            add(obj, obj3, str);
        }
    }

    public synchronized void update(Object obj, Object obj2, Object obj3, String str, boolean z) {
        if (obj2 != null) {
            try {
                if (!obj2.equals(obj3)) {
                    remove(obj, obj2, str);
                    if (z) {
                        removeBean(obj2);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        if (obj3 != null && !obj3.equals(obj2)) {
            if (z) {
                addBean(obj3);
            }
            add(obj, obj3, str);
        }
    }

    public synchronized void update(Object obj, Object[] objArr, Object[] objArr2, String str) {
        update(obj, objArr, objArr2, str, false);
    }

    public synchronized void update(Object obj, Object[] objArr, Object[] objArr2, String str, boolean z) {
        Object[] objArr3 = null;
        if (objArr2 != null) {
            try {
                Object[] objArr4 = new Object[objArr2.length];
                int length = objArr2.length;
                while (true) {
                    int i = length - 1;
                    if (length <= 0) {
                        break;
                    }
                    boolean z2 = true;
                    if (objArr != null) {
                        int length2 = objArr.length;
                        while (true) {
                            int i2 = length2 - 1;
                            if (length2 <= 0) {
                                break;
                            }
                            Object obj2 = objArr2[i];
                            if (obj2 == null || !obj2.equals(objArr[i2])) {
                                length2 = i2;
                            } else {
                                objArr[i2] = null;
                                length2 = i2;
                                z2 = false;
                            }
                        }
                    }
                    if (z2) {
                        objArr4[i] = objArr2[i];
                    }
                    length = i;
                }
                objArr3 = objArr4;
            } finally {
            }
        }
        if (objArr != null) {
            int length3 = objArr.length;
            while (true) {
                int i3 = length3 - 1;
                if (length3 <= 0) {
                    break;
                }
                Object obj3 = objArr[i3];
                if (obj3 != null) {
                    remove(obj, obj3, str);
                    if (z) {
                        removeBean(objArr[i3]);
                    }
                }
                length3 = i3;
            }
        }
        if (objArr3 != null) {
            for (int i4 = 0; i4 < objArr3.length; i4++) {
                Object obj4 = objArr3[i4];
                if (obj4 != null) {
                    if (z) {
                        addBean(obj4);
                    }
                    add(obj, objArr3[i4], str);
                }
            }
        }
    }

    public void addBean(Object obj) {
        if (this._listeners != null) {
            for (int i = 0; i < LazyList.size(this._listeners); i++) {
                ((Listener) LazyList.get(this._listeners, i)).addBean(obj);
            }
        }
    }

    public void removeBean(Object obj) {
        if (this._listeners != null) {
            for (int i = 0; i < LazyList.size(this._listeners); i++) {
                ((Listener) LazyList.get(this._listeners, i)).removeBean(obj);
            }
        }
    }

    private void add(Object obj, Object obj2, String str) {
        if (Log.isDebugEnabled()) {
            Log.debug(new StringBuffer("Container ").append(obj).append(" + ").append(obj2).append(" as ").append(str).toString());
        }
        if (this._listeners != null) {
            Relationship relationship = new Relationship(obj, obj2, str);
            for (int i = 0; i < LazyList.size(this._listeners); i++) {
                ((Listener) LazyList.get(this._listeners, i)).add(relationship);
            }
        }
    }

    private void remove(Object obj, Object obj2, String str) {
        if (Log.isDebugEnabled()) {
            Log.debug(new StringBuffer("Container ").append(obj).append(" - ").append(obj2).append(" as ").append(str).toString());
        }
        if (this._listeners != null) {
            Relationship relationship = new Relationship(obj, obj2, str);
            for (int i = 0; i < LazyList.size(this._listeners); i++) {
                ((Listener) LazyList.get(this._listeners, i)).remove(relationship);
            }
        }
    }

    public static class Relationship {
        private Object _child;
        private Container _container;
        private Object _parent;
        private String _relationship;

        private Relationship(Container container, Object obj, Object obj2, String str) {
            this._container = container;
            this._parent = obj;
            this._child = obj2;
            this._relationship = str;
        }

        public Container getContainer() {
            return this._container;
        }

        public Object getChild() {
            return this._child;
        }

        public Object getParent() {
            return this._parent;
        }

        public String getRelationship() {
            return this._relationship;
        }

        public String toString() {
            return new StringBuffer().append(this._parent).append("---").append(this._relationship).append("-->").append(this._child).toString();
        }

        public int hashCode() {
            return this._parent.hashCode() + this._child.hashCode() + this._relationship.hashCode();
        }

        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof Relationship)) {
                return false;
            }
            Relationship relationship = (Relationship) obj;
            return relationship._parent == this._parent && relationship._child == this._child && relationship._relationship.equals(this._relationship);
        }
    }
}
