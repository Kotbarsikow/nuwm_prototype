package org.mortbay.util;

/* loaded from: classes3.dex */
public class Loader {
    /* JADX WARN: Removed duplicated region for block: B:20:0x0027 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:14:0x002d -> B:10:0x001f). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x002f -> B:10:0x001f). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.net.URL getResource(java.lang.Class r3, java.lang.String r4, boolean r5) throws java.lang.ClassNotFoundException {
        /*
            java.lang.Thread r0 = java.lang.Thread.currentThread()
            java.lang.ClassLoader r0 = r0.getContextClassLoader()
            r1 = 0
            r2 = r1
        La:
            if (r2 != 0) goto L1d
            if (r0 == 0) goto L1d
            java.net.URL r2 = r0.getResource(r4)
            if (r2 != 0) goto L1b
            if (r5 == 0) goto L1b
            java.lang.ClassLoader r0 = r0.getParent()
            goto La
        L1b:
            r0 = r1
            goto La
        L1d:
            if (r3 != 0) goto L21
        L1f:
            r3 = r1
            goto L25
        L21:
            java.lang.ClassLoader r3 = r3.getClassLoader()
        L25:
            if (r2 != 0) goto L36
            if (r3 == 0) goto L36
            java.net.URL r2 = r3.getResource(r4)
            if (r2 != 0) goto L1f
            if (r5 == 0) goto L1f
            java.lang.ClassLoader r3 = r3.getParent()
            goto L25
        L36:
            if (r2 != 0) goto L3c
            java.net.URL r2 = java.lang.ClassLoader.getSystemResource(r4)
        L3c:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mortbay.util.Loader.getResource(java.lang.Class, java.lang.String, boolean):java.net.URL");
    }

    public static Class loadClass(Class cls, String str) throws ClassNotFoundException {
        return loadClass(cls, str, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x002d A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x004f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0044 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x0039 -> B:15:0x0025). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x003b -> B:15:0x0025). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Class loadClass(java.lang.Class r5, java.lang.String r6, boolean r7) throws java.lang.ClassNotFoundException {
        /*
            java.lang.Thread r0 = java.lang.Thread.currentThread()
            java.lang.ClassLoader r0 = r0.getContextClassLoader()
            r1 = 0
            r2 = r1
            r3 = r2
        Lb:
            if (r2 != 0) goto L23
            if (r0 == 0) goto L23
            java.lang.Class r2 = r0.loadClass(r6)     // Catch: java.lang.ClassNotFoundException -> L14
            goto L18
        L14:
            r4 = move-exception
            if (r3 != 0) goto L18
            r3 = r4
        L18:
            if (r2 != 0) goto L21
            if (r7 == 0) goto L21
            java.lang.ClassLoader r0 = r0.getParent()
            goto Lb
        L21:
            r0 = r1
            goto Lb
        L23:
            if (r5 != 0) goto L27
        L25:
            r5 = r1
            goto L2b
        L27:
            java.lang.ClassLoader r5 = r5.getClassLoader()
        L2b:
            if (r2 != 0) goto L42
            if (r5 == 0) goto L42
            java.lang.Class r0 = r5.loadClass(r6)     // Catch: java.lang.ClassNotFoundException -> L35
            r2 = r0
            goto L39
        L35:
            r0 = move-exception
            if (r3 != 0) goto L39
            r3 = r0
        L39:
            if (r2 != 0) goto L25
            if (r7 == 0) goto L25
            java.lang.ClassLoader r5 = r5.getParent()
            goto L2b
        L42:
            if (r2 != 0) goto L4d
            java.lang.Class r2 = java.lang.Class.forName(r6)     // Catch: java.lang.ClassNotFoundException -> L49
            goto L4d
        L49:
            r5 = move-exception
            if (r3 != 0) goto L4d
            r3 = r5
        L4d:
            if (r2 == 0) goto L50
            return r2
        L50:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mortbay.util.Loader.loadClass(java.lang.Class, java.lang.String, boolean):java.lang.Class");
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x002d A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x004f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0044 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x0039 -> B:15:0x0025). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x003b -> B:15:0x0025). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.ResourceBundle getResourceBundle(java.lang.Class r5, java.lang.String r6, boolean r7, java.util.Locale r8) throws java.util.MissingResourceException {
        /*
            java.lang.Thread r0 = java.lang.Thread.currentThread()
            java.lang.ClassLoader r0 = r0.getContextClassLoader()
            r1 = 0
            r2 = r1
            r3 = r2
        Lb:
            if (r2 != 0) goto L23
            if (r0 == 0) goto L23
            java.util.ResourceBundle r2 = java.util.ResourceBundle.getBundle(r6, r8, r0)     // Catch: java.util.MissingResourceException -> L14
            goto L18
        L14:
            r4 = move-exception
            if (r3 != 0) goto L18
            r3 = r4
        L18:
            if (r2 != 0) goto L21
            if (r7 == 0) goto L21
            java.lang.ClassLoader r0 = r0.getParent()
            goto Lb
        L21:
            r0 = r1
            goto Lb
        L23:
            if (r5 != 0) goto L27
        L25:
            r5 = r1
            goto L2b
        L27:
            java.lang.ClassLoader r5 = r5.getClassLoader()
        L2b:
            if (r2 != 0) goto L42
            if (r5 == 0) goto L42
            java.util.ResourceBundle r0 = java.util.ResourceBundle.getBundle(r6, r8, r5)     // Catch: java.util.MissingResourceException -> L35
            r2 = r0
            goto L39
        L35:
            r0 = move-exception
            if (r3 != 0) goto L39
            r3 = r0
        L39:
            if (r2 != 0) goto L25
            if (r7 == 0) goto L25
            java.lang.ClassLoader r5 = r5.getParent()
            goto L2b
        L42:
            if (r2 != 0) goto L4d
            java.util.ResourceBundle r2 = java.util.ResourceBundle.getBundle(r6, r8)     // Catch: java.util.MissingResourceException -> L49
            goto L4d
        L49:
            r5 = move-exception
            if (r3 != 0) goto L4d
            r3 = r5
        L4d:
            if (r2 == 0) goto L50
            return r2
        L50:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mortbay.util.Loader.getResourceBundle(java.lang.Class, java.lang.String, boolean, java.util.Locale):java.util.ResourceBundle");
    }
}
