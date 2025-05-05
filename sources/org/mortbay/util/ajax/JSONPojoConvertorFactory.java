package org.mortbay.util.ajax;

import org.mortbay.util.ajax.JSON;

/* loaded from: classes3.dex */
public class JSONPojoConvertorFactory implements JSON.Convertor {
    static /* synthetic */ Class class$java$lang$Object;
    static /* synthetic */ Class class$org$mortbay$util$ajax$JSON;
    private final boolean _fromJSON;
    private final JSON _json;

    public JSONPojoConvertorFactory(JSON json) {
        this._json = json;
        this._fromJSON = true;
        if (json == null) {
            throw new IllegalArgumentException();
        }
    }

    public JSONPojoConvertorFactory(JSON json, boolean z) {
        this._json = json;
        this._fromJSON = z;
        if (json == null) {
            throw new IllegalArgumentException();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:4:0x0037  */
    @Override // org.mortbay.util.ajax.JSON.Convertor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void toJSON(java.lang.Object r6, org.mortbay.util.ajax.JSON.Output r7) {
        /*
            r5 = this;
            java.lang.Class r0 = r6.getClass()
            java.lang.String r0 = r0.getName()
            org.mortbay.util.ajax.JSON r1 = r5._json
            org.mortbay.util.ajax.JSON$Convertor r1 = r1.getConvertorFor(r0)
            if (r1 != 0) goto L35
            java.lang.Class r2 = org.mortbay.util.ajax.JSONPojoConvertorFactory.class$org$mortbay$util$ajax$JSON     // Catch: java.lang.ClassNotFoundException -> L31
            if (r2 != 0) goto L1c
            java.lang.String r2 = "org.mortbay.util.ajax.JSON"
            java.lang.Class r2 = class$(r2)     // Catch: java.lang.ClassNotFoundException -> L31
            org.mortbay.util.ajax.JSONPojoConvertorFactory.class$org$mortbay$util$ajax$JSON = r2     // Catch: java.lang.ClassNotFoundException -> L31
        L1c:
            java.lang.Class r2 = org.mortbay.util.Loader.loadClass(r2, r0)     // Catch: java.lang.ClassNotFoundException -> L31
            org.mortbay.util.ajax.JSONPojoConvertor r3 = new org.mortbay.util.ajax.JSONPojoConvertor     // Catch: java.lang.ClassNotFoundException -> L31
            boolean r4 = r5._fromJSON     // Catch: java.lang.ClassNotFoundException -> L31
            r3.<init>(r2, r4)     // Catch: java.lang.ClassNotFoundException -> L31
            org.mortbay.util.ajax.JSON r1 = r5._json     // Catch: java.lang.ClassNotFoundException -> L2e
            r1.addConvertorFor(r0, r3)     // Catch: java.lang.ClassNotFoundException -> L2e
            r1 = r3
            goto L35
        L2e:
            r0 = move-exception
            r1 = r3
            goto L32
        L31:
            r0 = move-exception
        L32:
            r0.printStackTrace()
        L35:
            if (r1 == 0) goto L4d
            java.lang.Class r0 = r6.getClass()
            java.lang.Class r2 = org.mortbay.util.ajax.JSONPojoConvertorFactory.class$java$lang$Object
            if (r2 != 0) goto L47
            java.lang.String r2 = "java.lang.Object"
            java.lang.Class r2 = class$(r2)
            org.mortbay.util.ajax.JSONPojoConvertorFactory.class$java$lang$Object = r2
        L47:
            if (r0 == r2) goto L4d
            r1.toJSON(r6, r7)
            goto L54
        L4d:
            java.lang.String r6 = r6.toString()
            r7.add(r6)
        L54:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mortbay.util.ajax.JSONPojoConvertorFactory.toJSON(java.lang.Object, org.mortbay.util.ajax.JSON$Output):void");
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:6:0x0037  */
    @Override // org.mortbay.util.ajax.JSON.Convertor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object fromJSON(java.util.Map r5) {
        /*
            r4 = this;
            java.lang.String r0 = "class"
            java.lang.Object r0 = r5.get(r0)
            java.lang.String r0 = (java.lang.String) r0
            if (r0 == 0) goto L51
            org.mortbay.util.ajax.JSON r1 = r4._json
            org.mortbay.util.ajax.JSON$Convertor r1 = r1.getConvertorFor(r0)
            if (r1 != 0) goto L35
            java.lang.Class r2 = org.mortbay.util.ajax.JSONPojoConvertorFactory.class$org$mortbay$util$ajax$JSON     // Catch: java.lang.ClassNotFoundException -> L31
            if (r2 != 0) goto L1e
            java.lang.String r2 = "org.mortbay.util.ajax.JSON"
            java.lang.Class r2 = class$(r2)     // Catch: java.lang.ClassNotFoundException -> L31
            org.mortbay.util.ajax.JSONPojoConvertorFactory.class$org$mortbay$util$ajax$JSON = r2     // Catch: java.lang.ClassNotFoundException -> L31
        L1e:
            java.lang.Class r2 = org.mortbay.util.Loader.loadClass(r2, r0)     // Catch: java.lang.ClassNotFoundException -> L31
            org.mortbay.util.ajax.JSONPojoConvertor r3 = new org.mortbay.util.ajax.JSONPojoConvertor     // Catch: java.lang.ClassNotFoundException -> L31
            r3.<init>(r2)     // Catch: java.lang.ClassNotFoundException -> L31
            org.mortbay.util.ajax.JSON r1 = r4._json     // Catch: java.lang.ClassNotFoundException -> L2e
            r1.addConvertorFor(r0, r3)     // Catch: java.lang.ClassNotFoundException -> L2e
            r1 = r3
            goto L35
        L2e:
            r2 = move-exception
            r1 = r3
            goto L32
        L31:
            r2 = move-exception
        L32:
            r2.printStackTrace()
        L35:
            if (r1 == 0) goto L51
            java.lang.Class r2 = org.mortbay.util.ajax.JSONPojoConvertorFactory.class$java$lang$Object
            if (r2 != 0) goto L43
            java.lang.String r2 = "java.lang.Object"
            java.lang.Class r2 = class$(r2)
            org.mortbay.util.ajax.JSONPojoConvertorFactory.class$java$lang$Object = r2
        L43:
            java.lang.String r2 = r2.getName()
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L51
            java.lang.Object r5 = r1.fromJSON(r5)
        L51:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mortbay.util.ajax.JSONPojoConvertorFactory.fromJSON(java.util.Map):java.lang.Object");
    }
}
