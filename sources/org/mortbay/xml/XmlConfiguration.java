package org.mortbay.xml;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import org.mortbay.component.LifeCycle;
import org.mortbay.jetty.servlet.ServletHandler;
import org.mortbay.log.Log;
import org.mortbay.resource.Resource;
import org.mortbay.util.LazyList;
import org.mortbay.util.Loader;
import org.mortbay.util.TypeUtil;
import org.mortbay.xml.XmlParser;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/* loaded from: classes3.dex */
public class XmlConfiguration {
    private static final Integer ZERO;
    private static XmlParser __parser;
    private static Class[] __primitiveHolders;
    private static Class[] __primitives = {Boolean.TYPE, Character.TYPE, Byte.TYPE, Short.TYPE, Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE, Void.TYPE};
    static /* synthetic */ Class class$java$lang$Boolean;
    static /* synthetic */ Class class$java$lang$Byte;
    static /* synthetic */ Class class$java$lang$Character;
    static /* synthetic */ Class class$java$lang$Double;
    static /* synthetic */ Class class$java$lang$Float;
    static /* synthetic */ Class class$java$lang$Integer;
    static /* synthetic */ Class class$java$lang$Long;
    static /* synthetic */ Class class$java$lang$Object;
    static /* synthetic */ Class class$java$lang$Short;
    static /* synthetic */ Class class$java$lang$String;
    static /* synthetic */ Class class$java$lang$Void;
    static /* synthetic */ Class class$java$net$InetAddress;
    static /* synthetic */ Class class$java$net$URL;
    static /* synthetic */ Class class$org$mortbay$xml$XmlConfiguration;
    private XmlParser.Node _config;
    private Map _idMap = new HashMap();
    private Map _propertyMap = new HashMap();

    static {
        Class cls = class$java$lang$Boolean;
        if (cls == null) {
            cls = class$("java.lang.Boolean");
            class$java$lang$Boolean = cls;
        }
        Class cls2 = class$java$lang$Character;
        if (cls2 == null) {
            cls2 = class$("java.lang.Character");
            class$java$lang$Character = cls2;
        }
        Class cls3 = class$java$lang$Byte;
        if (cls3 == null) {
            cls3 = class$("java.lang.Byte");
            class$java$lang$Byte = cls3;
        }
        Class cls4 = class$java$lang$Short;
        if (cls4 == null) {
            cls4 = class$("java.lang.Short");
            class$java$lang$Short = cls4;
        }
        Class cls5 = class$java$lang$Integer;
        if (cls5 == null) {
            cls5 = class$("java.lang.Integer");
            class$java$lang$Integer = cls5;
        }
        Class cls6 = class$java$lang$Long;
        if (cls6 == null) {
            cls6 = class$("java.lang.Long");
            class$java$lang$Long = cls6;
        }
        Class cls7 = class$java$lang$Float;
        if (cls7 == null) {
            cls7 = class$("java.lang.Float");
            class$java$lang$Float = cls7;
        }
        Class cls8 = class$java$lang$Double;
        if (cls8 == null) {
            cls8 = class$("java.lang.Double");
            class$java$lang$Double = cls8;
        }
        Class cls9 = class$java$lang$Void;
        if (cls9 == null) {
            cls9 = class$("java.lang.Void");
            class$java$lang$Void = cls9;
        }
        __primitiveHolders = new Class[]{cls, cls2, cls3, cls4, cls5, cls6, cls7, cls8, cls9};
        ZERO = new Integer(0);
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    private static synchronized void initParser() throws IOException {
        synchronized (XmlConfiguration.class) {
            if (__parser != null) {
                return;
            }
            __parser = new XmlParser();
            try {
                Class cls = class$org$mortbay$xml$XmlConfiguration;
                if (cls == null) {
                    cls = class$("org.mortbay.xml.XmlConfiguration");
                    class$org$mortbay$xml$XmlConfiguration = cls;
                }
                URL resource = Loader.getResource(cls, "org/mortbay/xml/configure_6_0.dtd", true);
                __parser.redirectEntity("configure.dtd", resource);
                __parser.redirectEntity("configure_1_3.dtd", resource);
                __parser.redirectEntity("http://jetty.mortbay.org/configure.dtd", resource);
                __parser.redirectEntity("-//Mort Bay Consulting//DTD Configure//EN", resource);
                __parser.redirectEntity("http://jetty.mortbay.org/configure_1_3.dtd", resource);
                __parser.redirectEntity("-//Mort Bay Consulting//DTD Configure 1.3//EN", resource);
                __parser.redirectEntity("configure_1_2.dtd", resource);
                __parser.redirectEntity("http://jetty.mortbay.org/configure_1_2.dtd", resource);
                __parser.redirectEntity("-//Mort Bay Consulting//DTD Configure 1.2//EN", resource);
                __parser.redirectEntity("configure_1_1.dtd", resource);
                __parser.redirectEntity("http://jetty.mortbay.org/configure_1_1.dtd", resource);
                __parser.redirectEntity("-//Mort Bay Consulting//DTD Configure 1.1//EN", resource);
                __parser.redirectEntity("configure_1_0.dtd", resource);
                __parser.redirectEntity("http://jetty.mortbay.org/configure_1_0.dtd", resource);
                __parser.redirectEntity("-//Mort Bay Consulting//DTD Configure 1.0//EN", resource);
            } catch (ClassNotFoundException e) {
                Log.warn(e.toString());
                Log.debug(e);
            }
        }
    }

    public XmlConfiguration(URL url) throws SAXException, IOException {
        initParser();
        synchronized (__parser) {
            this._config = __parser.parse(url.toString());
        }
    }

    public XmlConfiguration(String str) throws SAXException, IOException {
        initParser();
        InputSource inputSource = new InputSource(new StringReader(new StringBuffer("<?xml version=\"1.0\"  encoding=\"ISO-8859-1\"?>\n<!DOCTYPE Configure PUBLIC \"-//Mort Bay Consulting//DTD Configure 1.2//EN\" \"http://jetty.mortbay.org/configure_1_2.dtd\">").append(str).toString()));
        synchronized (__parser) {
            this._config = __parser.parse(inputSource);
        }
    }

    public XmlConfiguration(InputStream inputStream) throws SAXException, IOException {
        initParser();
        InputSource inputSource = new InputSource(inputStream);
        synchronized (__parser) {
            this._config = __parser.parse(inputSource);
        }
    }

    public Map getIdMap() {
        return this._idMap;
    }

    public void setIdMap(Map map) {
        this._idMap = map;
    }

    public void setProperties(Map map) {
        this._propertyMap = map;
    }

    public Map getProperties() {
        return this._propertyMap;
    }

    public void configure(Object obj) throws Exception {
        Class nodeClass = nodeClass(this._config);
        if (!nodeClass.isInstance(obj)) {
            throw new IllegalArgumentException(new StringBuffer("Object is not of type ").append(nodeClass).toString());
        }
        configure(obj, this._config, 0);
    }

    public Object configure() throws Exception {
        Class nodeClass = nodeClass(this._config);
        String attribute = this._config.getAttribute("id");
        Object obj = attribute == null ? null : this._idMap.get(attribute);
        if (obj == null && nodeClass != null) {
            obj = nodeClass.newInstance();
        }
        if (nodeClass != null && !nodeClass.isInstance(obj)) {
            throw new ClassCastException(nodeClass.toString());
        }
        configure(obj, this._config, 0);
        return obj;
    }

    private Class nodeClass(XmlParser.Node node) throws ClassNotFoundException {
        String attribute = node.getAttribute("class");
        if (attribute == null) {
            return null;
        }
        Class cls = class$org$mortbay$xml$XmlConfiguration;
        if (cls == null) {
            cls = class$("org.mortbay.xml.XmlConfiguration");
            class$org$mortbay$xml$XmlConfiguration = cls;
        }
        return Loader.loadClass(cls, attribute, true);
    }

    private void configure(Object obj, XmlParser.Node node, int i) throws Exception {
        String attribute = node.getAttribute("id");
        if (attribute != null) {
            this._idMap.put(attribute, obj);
        }
        while (i < node.size()) {
            Object obj2 = node.get(i);
            if (!(obj2 instanceof String)) {
                XmlParser.Node node2 = (XmlParser.Node) obj2;
                try {
                    String tag = node2.getTag();
                    if ("Set".equals(tag)) {
                        set(obj, node2);
                    } else if ("Put".equals(tag)) {
                        put(obj, node2);
                    } else if ("Call".equals(tag)) {
                        call(obj, node2);
                    } else if ("Get".equals(tag)) {
                        get(obj, node2);
                    } else if ("New".equals(tag)) {
                        newObj(obj, node2);
                    } else if ("Array".equals(tag)) {
                        newArray(obj, node2);
                    } else if ("Ref".equals(tag)) {
                        refObj(obj, node2);
                    } else if ("Property".equals(tag)) {
                        propertyObj(obj, node2);
                    } else {
                        throw new IllegalStateException(new StringBuffer().append("Unknown tag: ").append(tag).toString());
                    }
                } catch (Exception e) {
                    Log.warn(new StringBuffer("Config error at ").append(node2).toString(), e.toString());
                    throw e;
                }
            }
            i++;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x00e0 A[Catch: NoSuchFieldException -> 0x00e4, TRY_LEAVE, TryCatch #7 {NoSuchFieldException -> 0x00e4, blocks: (B:31:0x00d2, B:33:0x00e0), top: B:30:0x00d2 }] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x011b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void set(java.lang.Object r14, org.mortbay.xml.XmlParser.Node r15) throws java.lang.Exception {
        /*
            Method dump skipped, instructions count: 386
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mortbay.xml.XmlConfiguration.set(java.lang.Object, org.mortbay.xml.XmlParser$Node):void");
    }

    private void put(Object obj, XmlParser.Node node) throws Exception {
        if (!(obj instanceof Map)) {
            throw new IllegalArgumentException(new StringBuffer("Object for put is not a Map: ").append(obj).toString());
        }
        String attribute = node.getAttribute(AppMeasurementSdk.ConditionalUserProperty.NAME);
        Object value = value(obj, node);
        ((Map) obj).put(attribute, value);
        if (Log.isDebugEnabled()) {
            Log.debug(new StringBuffer("XML ").append(obj).append(".put(").append(attribute).append(",").append(value).append(")").toString());
        }
    }

    private Object get(Object obj, XmlParser.Node node) throws Exception {
        Class<?> nodeClass = nodeClass(node);
        if (nodeClass != null) {
            obj = null;
        } else {
            nodeClass = obj.getClass();
        }
        String attribute = node.getAttribute(AppMeasurementSdk.ConditionalUserProperty.NAME);
        String attribute2 = node.getAttribute("id");
        if (Log.isDebugEnabled()) {
            Log.debug(new StringBuffer("XML get ").append(attribute).toString());
        }
        try {
            obj = nodeClass.getMethod(new StringBuffer("get").append(attribute.substring(0, 1).toUpperCase()).append(attribute.substring(1)).toString(), null).invoke(obj, null);
            configure(obj, node, 0);
        } catch (NoSuchMethodException e) {
            try {
                obj = nodeClass.getField(attribute).get(obj);
                configure(obj, node, 0);
            } catch (NoSuchFieldException unused) {
                throw e;
            }
        }
        if (attribute2 != null) {
            this._idMap.put(attribute2, obj);
        }
        return obj;
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x00d0 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00db A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.Object call(java.lang.Object r14, org.mortbay.xml.XmlParser.Node r15) throws java.lang.Exception {
        /*
            Method dump skipped, instructions count: 263
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mortbay.xml.XmlConfiguration.call(java.lang.Object, org.mortbay.xml.XmlParser$Node):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x0098 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00a3 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.Object newObj(java.lang.Object r11, org.mortbay.xml.XmlParser.Node r12) throws java.lang.Exception {
        /*
            r10 = this;
            java.lang.Class r0 = r10.nodeClass(r12)
            java.lang.String r1 = "id"
            java.lang.String r1 = r12.getAttribute(r1)
            int r2 = r12.size()
            r3 = 0
            r4 = 0
            r5 = 0
        L11:
            int r6 = r12.size()
            if (r4 >= r6) goto L35
            java.lang.Object r6 = r12.get(r4)
            boolean r7 = r6 instanceof java.lang.String
            if (r7 == 0) goto L20
            goto L32
        L20:
            org.mortbay.xml.XmlParser$Node r6 = (org.mortbay.xml.XmlParser.Node) r6
            java.lang.String r6 = r6.getTag()
            java.lang.String r7 = "Arg"
            boolean r6 = r6.equals(r7)
            if (r6 != 0) goto L30
            r2 = r4
            goto L35
        L30:
            int r5 = r5 + 1
        L32:
            int r4 = r4 + 1
            goto L11
        L35:
            java.lang.Object[] r4 = new java.lang.Object[r5]
            r6 = 0
            r7 = 0
        L39:
            if (r6 >= r5) goto L52
            java.lang.Object r8 = r12.get(r7)
            boolean r9 = r8 instanceof java.lang.String
            if (r9 == 0) goto L44
            goto L4f
        L44:
            int r9 = r6 + 1
            org.mortbay.xml.XmlParser$Node r8 = (org.mortbay.xml.XmlParser.Node) r8
            java.lang.Object r8 = r10.value(r11, r8)
            r4[r6] = r8
            r6 = r9
        L4f:
            int r7 = r7 + 1
            goto L39
        L52:
            boolean r6 = org.mortbay.log.Log.isDebugEnabled()
            if (r6 == 0) goto L6a
            java.lang.StringBuffer r6 = new java.lang.StringBuffer
            java.lang.String r7 = "XML new "
            r6.<init>(r7)
            java.lang.StringBuffer r6 = r6.append(r0)
            java.lang.String r6 = r6.toString()
            org.mortbay.log.Log.debug(r6)
        L6a:
            java.lang.reflect.Constructor[] r0 = r0.getConstructors()
            r6 = 0
        L6f:
            if (r0 == 0) goto La6
            int r7 = r0.length
            if (r6 >= r7) goto La6
            r7 = r0[r6]
            java.lang.Class[] r7 = r7.getParameterTypes()
            int r7 = r7.length
            if (r7 == r5) goto L7e
            goto La3
        L7e:
            r7 = r0[r6]     // Catch: java.lang.IllegalArgumentException -> L86 java.lang.InstantiationException -> L8b java.lang.IllegalAccessException -> L90
            java.lang.Object r7 = r7.newInstance(r4)     // Catch: java.lang.IllegalArgumentException -> L86 java.lang.InstantiationException -> L8b java.lang.IllegalAccessException -> L90
            r8 = 1
            goto L96
        L86:
            r7 = move-exception
            org.mortbay.log.Log.ignore(r7)
            goto L94
        L8b:
            r7 = move-exception
            org.mortbay.log.Log.ignore(r7)
            goto L94
        L90:
            r7 = move-exception
            org.mortbay.log.Log.ignore(r7)
        L94:
            r7 = 0
            r8 = 0
        L96:
            if (r8 == 0) goto La3
            if (r1 == 0) goto L9f
            java.util.Map r11 = r10._idMap
            r11.put(r1, r7)
        L9f:
            r10.configure(r7, r12, r2)
            return r7
        La3:
            int r6 = r6 + 1
            goto L6f
        La6:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            java.lang.String r2 = "No Constructor: "
            r1.<init>(r2)
            java.lang.StringBuffer r12 = r1.append(r12)
            java.lang.String r1 = " on "
            java.lang.StringBuffer r12 = r12.append(r1)
            java.lang.StringBuffer r11 = r12.append(r11)
            java.lang.String r11 = r11.toString()
            r0.<init>(r11)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mortbay.xml.XmlConfiguration.newObj(java.lang.Object, org.mortbay.xml.XmlParser$Node):java.lang.Object");
    }

    private Object refObj(Object obj, XmlParser.Node node) throws Exception {
        String attribute = node.getAttribute("id");
        Object obj2 = this._idMap.get(attribute);
        if (obj2 == null) {
            throw new IllegalStateException(new StringBuffer("No object for id=").append(attribute).toString());
        }
        configure(obj2, node, 0);
        return obj2;
    }

    private Object newArray(Object obj, XmlParser.Node node) throws Exception {
        Class cls = class$java$lang$Object;
        if (cls == null) {
            cls = class$("java.lang.Object");
            class$java$lang$Object = cls;
        }
        String attribute = node.getAttribute("type");
        String attribute2 = node.getAttribute("id");
        if (attribute != null && (cls = TypeUtil.fromName(attribute)) == null) {
            if ("String".equals(attribute)) {
                cls = class$java$lang$String;
                if (cls == null) {
                    cls = class$("java.lang.String");
                    class$java$lang$String = cls;
                }
            } else if ("URL".equals(attribute)) {
                cls = class$java$net$URL;
                if (cls == null) {
                    cls = class$("java.net.URL");
                    class$java$net$URL = cls;
                }
            } else if ("InetAddress".equals(attribute)) {
                cls = class$java$net$InetAddress;
                if (cls == null) {
                    cls = class$("java.net.InetAddress");
                    class$java$net$InetAddress = cls;
                }
            } else {
                Class cls2 = class$org$mortbay$xml$XmlConfiguration;
                if (cls2 == null) {
                    cls2 = class$("org.mortbay.xml.XmlConfiguration");
                    class$org$mortbay$xml$XmlConfiguration = cls2;
                }
                cls = Loader.loadClass(cls2, attribute, true);
            }
        }
        Iterator it = node.iterator("Item");
        Object obj2 = null;
        while (it.hasNext()) {
            XmlParser.Node node2 = (XmlParser.Node) it.next();
            String attribute3 = node2.getAttribute("id");
            Object value = value(obj, node2);
            obj2 = LazyList.add(obj2, (value == null && cls.isPrimitive()) ? ZERO : value);
            if (attribute3 != null) {
                this._idMap.put(attribute3, value);
            }
        }
        Object array = LazyList.toArray(obj2, cls);
        if (attribute2 != null) {
            this._idMap.put(attribute2, array);
        }
        return array;
    }

    private Object newMap(Object obj, XmlParser.Node node) throws Exception {
        String attribute = node.getAttribute("id");
        HashMap hashMap = new HashMap();
        if (attribute != null) {
            this._idMap.put(attribute, hashMap);
        }
        for (int i = 0; i < node.size(); i++) {
            Object obj2 = node.get(i);
            if (!(obj2 instanceof String)) {
                XmlParser.Node node2 = (XmlParser.Node) obj2;
                if (!node2.getTag().equals("Entry")) {
                    throw new IllegalStateException("Not an Entry");
                }
                XmlParser.Node node3 = null;
                XmlParser.Node node4 = null;
                for (int i2 = 0; i2 < node2.size(); i2++) {
                    Object obj3 = node2.get(i2);
                    if (!(obj3 instanceof String)) {
                        XmlParser.Node node5 = (XmlParser.Node) obj3;
                        if (!node5.getTag().equals("Item")) {
                            throw new IllegalStateException("Not an Item");
                        }
                        if (node3 == null) {
                            node3 = node5;
                        } else {
                            node4 = node5;
                        }
                    }
                }
                if (node3 == null || node4 == null) {
                    throw new IllegalStateException("Missing Item in Entry");
                }
                String attribute2 = node3.getAttribute("id");
                String attribute3 = node4.getAttribute("id");
                Object value = value(obj, node3);
                Object value2 = value(obj, node4);
                hashMap.put(value, value2);
                if (attribute2 != null) {
                    this._idMap.put(attribute2, value);
                }
                if (attribute3 != null) {
                    this._idMap.put(attribute3, value2);
                }
            }
        }
        return hashMap;
    }

    private Object propertyObj(Object obj, XmlParser.Node node) throws Exception {
        String attribute = node.getAttribute("id");
        String attribute2 = node.getAttribute(AppMeasurementSdk.ConditionalUserProperty.NAME);
        Object attribute3 = node.getAttribute(ServletHandler.__DEFAULT_SERVLET);
        Map map = this._propertyMap;
        if (map != null && map.containsKey(attribute2)) {
            attribute3 = this._propertyMap.get(attribute2);
        } else if (attribute3 == null) {
            attribute3 = null;
        }
        if (attribute != null) {
            this._idMap.put(attribute, attribute3);
        }
        if (attribute3 != null) {
            configure(attribute3, node, 0);
        }
        return attribute3;
    }

    private Object value(Object obj, XmlParser.Node node) throws Exception {
        Object stringBuffer;
        String attribute = node.getAttribute("type");
        String attribute2 = node.getAttribute("ref");
        if (attribute2 != null) {
            stringBuffer = this._idMap.get(attribute2);
        } else {
            if (node.size() == 0) {
                if ("String".equals(attribute)) {
                    return "";
                }
                return null;
            }
            int size = node.size() - 1;
            int i = 0;
            if (attribute == null || !"String".equals(attribute)) {
                while (i <= size) {
                    Object obj2 = node.get(i);
                    if (!(obj2 instanceof String) || ((String) obj2).trim().length() > 0) {
                        break;
                    }
                    i++;
                }
                while (i < size) {
                    Object obj3 = node.get(size);
                    if (!(obj3 instanceof String) || ((String) obj3).trim().length() > 0) {
                        break;
                    }
                    size--;
                }
                if (i > size) {
                    return null;
                }
            }
            if (i == size) {
                stringBuffer = itemValue(obj, node.get(i));
            } else {
                StringBuffer stringBuffer2 = new StringBuffer();
                synchronized (stringBuffer2) {
                    while (i <= size) {
                        stringBuffer2.append(itemValue(obj, node.get(i)));
                        i++;
                    }
                    stringBuffer = stringBuffer2.toString();
                }
            }
        }
        if (stringBuffer == null) {
            if ("String".equals(attribute)) {
                return "";
            }
            return null;
        }
        if (attribute == null) {
            return (stringBuffer == null || !(stringBuffer instanceof String)) ? stringBuffer : ((String) stringBuffer).trim();
        }
        if ("String".equals(attribute) || "java.lang.String".equals(attribute)) {
            return stringBuffer.toString();
        }
        Class fromName = TypeUtil.fromName(attribute);
        if (fromName != null) {
            return TypeUtil.valueOf(fromName, stringBuffer.toString());
        }
        if ("URL".equals(attribute) || "java.net.URL".equals(attribute)) {
            if (stringBuffer instanceof URL) {
                return stringBuffer;
            }
            try {
                return new URL(stringBuffer.toString());
            } catch (MalformedURLException e) {
                throw new InvocationTargetException(e);
            }
        }
        if ("InetAddress".equals(attribute) || "java.net.InetAddress".equals(attribute)) {
            if (stringBuffer instanceof InetAddress) {
                return stringBuffer;
            }
            try {
                return InetAddress.getByName(stringBuffer.toString());
            } catch (UnknownHostException e2) {
                throw new InvocationTargetException(e2);
            }
        }
        throw new IllegalStateException(new StringBuffer("Unknown type ").append(attribute).toString());
    }

    private Object itemValue(Object obj, Object obj2) throws Exception {
        if (obj2 instanceof String) {
            return obj2;
        }
        XmlParser.Node node = (XmlParser.Node) obj2;
        String tag = node.getTag();
        if ("Call".equals(tag)) {
            return call(obj, node);
        }
        if ("Get".equals(tag)) {
            return get(obj, node);
        }
        if ("New".equals(tag)) {
            return newObj(obj, node);
        }
        if ("Ref".equals(tag)) {
            return refObj(obj, node);
        }
        if ("Array".equals(tag)) {
            return newArray(obj, node);
        }
        if ("Map".equals(tag)) {
            return newMap(obj, node);
        }
        if ("Property".equals(tag)) {
            return propertyObj(obj, node);
        }
        if ("SystemProperty".equals(tag)) {
            return System.getProperty(node.getAttribute(AppMeasurementSdk.ConditionalUserProperty.NAME), node.getAttribute(ServletHandler.__DEFAULT_SERVLET));
        }
        Log.warn(new StringBuffer("Unknown value tag: ").append(node).toString(), new Throwable());
        return null;
    }

    public static void main(String[] strArr) {
        try {
            Properties properties = new Properties();
            Object[] objArr = new Object[strArr.length];
            XmlConfiguration xmlConfiguration = null;
            for (int i = 0; i < strArr.length; i++) {
                if (strArr[i].toLowerCase().endsWith(".properties")) {
                    properties.load(Resource.newResource(strArr[i]).getInputStream());
                } else {
                    XmlConfiguration xmlConfiguration2 = new XmlConfiguration(Resource.newResource(strArr[i]).getURL());
                    if (xmlConfiguration != null) {
                        xmlConfiguration2.getIdMap().putAll(xmlConfiguration.getIdMap());
                    }
                    if (properties.size() > 0) {
                        xmlConfiguration2.setProperties(properties);
                    }
                    objArr[i] = xmlConfiguration2.configure();
                    xmlConfiguration = xmlConfiguration2;
                }
            }
            for (int i2 = 0; i2 < strArr.length; i2++) {
                Object obj = objArr[i2];
                if (obj instanceof LifeCycle) {
                    LifeCycle lifeCycle = (LifeCycle) obj;
                    if (!lifeCycle.isRunning()) {
                        lifeCycle.start();
                    }
                }
            }
        } catch (Exception e) {
            Log.warn(Log.EXCEPTION, (Throwable) e);
        }
    }
}
