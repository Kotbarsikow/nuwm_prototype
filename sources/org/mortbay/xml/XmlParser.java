package org.mortbay.xml;

import com.hootsuite.nachos.tokenizer.SpanChipTokenizer;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Stack;
import java.util.StringTokenizer;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.mortbay.log.Log;
import org.mortbay.util.LazyList;
import org.mortbay.util.URIUtil;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

/* loaded from: classes3.dex */
public class XmlParser {
    private String _dtd;
    private Map _observerMap;
    private SAXParser _parser;
    private String _xpath;
    private Object _xpaths;
    private Map _redirectMap = new HashMap();
    private Stack _observers = new Stack();

    public XmlParser() {
        setValidating(!Boolean.getBoolean("org.mortbay.xml.XmlParser.NotValidating") && Boolean.valueOf(System.getProperty("org.mortbay.xml.XmlParser.Validating", SAXParserFactory.newInstance().getClass().toString().startsWith("org.apache.xerces.") ? "true" : "false")).booleanValue());
    }

    public XmlParser(boolean z) {
        setValidating(z);
    }

    public void setValidating(boolean z) {
        try {
            SAXParserFactory newInstance = SAXParserFactory.newInstance();
            newInstance.setValidating(z);
            SAXParser newSAXParser = newInstance.newSAXParser();
            this._parser = newSAXParser;
            if (z) {
                try {
                    newSAXParser.getXMLReader().setFeature("http://apache.org/xml/features/validation/schema", z);
                } catch (Exception e) {
                    if (z) {
                        Log.warn("Schema validation may not be supported: ", (Throwable) e);
                    } else {
                        Log.ignore(e);
                    }
                }
            }
            this._parser.getXMLReader().setFeature("http://xml.org/sax/features/validation", z);
            this._parser.getXMLReader().setFeature("http://xml.org/sax/features/namespaces", true);
            this._parser.getXMLReader().setFeature("http://xml.org/sax/features/namespace-prefixes", false);
        } catch (Exception e2) {
            Log.warn(Log.EXCEPTION, (Throwable) e2);
            throw new Error(e2.toString());
        }
    }

    public synchronized void redirectEntity(String str, URL url) {
        if (url != null) {
            this._redirectMap.put(str, url);
        }
    }

    public String getXpath() {
        return this._xpath;
    }

    public void setXpath(String str) {
        this._xpath = str;
        StringTokenizer stringTokenizer = new StringTokenizer(str, "| ");
        while (stringTokenizer.hasMoreTokens()) {
            this._xpaths = LazyList.add(this._xpaths, stringTokenizer.nextToken());
        }
    }

    public String getDTD() {
        return this._dtd;
    }

    public synchronized void addContentHandler(String str, ContentHandler contentHandler) {
        if (this._observerMap == null) {
            this._observerMap = new HashMap();
        }
        this._observerMap.put(str, contentHandler);
    }

    public synchronized Node parse(InputSource inputSource) throws IOException, SAXException {
        Node node;
        this._dtd = null;
        Handler handler = new Handler();
        XMLReader xMLReader = this._parser.getXMLReader();
        xMLReader.setContentHandler(handler);
        xMLReader.setErrorHandler(handler);
        xMLReader.setEntityResolver(handler);
        if (Log.isDebugEnabled()) {
            Log.debug(new StringBuffer("parsing: sid=").append(inputSource.getSystemId()).append(",pid=").append(inputSource.getPublicId()).toString());
        }
        this._parser.parse(inputSource, handler);
        if (handler._error != null) {
            throw handler._error;
        }
        node = (Node) handler._top.get(0);
        handler.clear();
        return node;
    }

    public synchronized Node parse(String str) throws IOException, SAXException {
        if (Log.isDebugEnabled()) {
            Log.debug(new StringBuffer("parse: ").append(str).toString());
        }
        return parse(new InputSource(str));
    }

    public synchronized Node parse(File file) throws IOException, SAXException {
        if (Log.isDebugEnabled()) {
            Log.debug(new StringBuffer("parse: ").append(file).toString());
        }
        return parse(new InputSource(file.toURL().toString()));
    }

    public synchronized Node parse(InputStream inputStream) throws IOException, SAXException {
        Node node;
        this._dtd = null;
        Handler handler = new Handler();
        XMLReader xMLReader = this._parser.getXMLReader();
        xMLReader.setContentHandler(handler);
        xMLReader.setErrorHandler(handler);
        xMLReader.setEntityResolver(handler);
        this._parser.parse(new InputSource(inputStream), handler);
        if (handler._error != null) {
            throw handler._error;
        }
        node = (Node) handler._top.get(0);
        handler.clear();
        return node;
    }

    private class NoopHandler extends DefaultHandler {
        int _depth;
        Handler _next;

        NoopHandler(Handler handler) {
            this._next = handler;
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
            this._depth++;
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void endElement(String str, String str2, String str3) throws SAXException {
            int i = this._depth;
            if (i == 0) {
                XmlParser.this._parser.getXMLReader().setContentHandler(this._next);
            } else {
                this._depth = i - 1;
            }
        }
    }

    private class Handler extends DefaultHandler {
        private Node _context;
        SAXParseException _error;
        private NoopHandler _noop;
        Node _top;

        Handler() {
            Node node = new Node(null, null, null);
            this._top = node;
            this._context = node;
            this._noop = XmlParser.this.new NoopHandler(this);
        }

        void clear() {
            this._top = null;
            this._error = null;
            this._context = null;
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
            String str4 = (str == null || str.equals("")) ? str3 : str2;
            Node node = new Node(this._context, str4, attributes);
            if (XmlParser.this._xpaths != null) {
                String path = node.getPath();
                int size = LazyList.size(XmlParser.this._xpaths);
                boolean z = false;
                while (!z) {
                    int i = size - 1;
                    if (size <= 0) {
                        break;
                    }
                    String str5 = (String) LazyList.get(XmlParser.this._xpaths, i);
                    z = path.equals(str5) || (str5.startsWith(path) && str5.length() > path.length() && str5.charAt(path.length()) == '/');
                    size = i;
                }
                if (!z) {
                    XmlParser.this._parser.getXMLReader().setContentHandler(this._noop);
                } else {
                    this._context.add(node);
                    this._context = node;
                }
            } else {
                this._context.add(node);
                this._context = node;
            }
            XmlParser.this._observers.push(XmlParser.this._observerMap != null ? (ContentHandler) XmlParser.this._observerMap.get(str4) : null);
            for (int i2 = 0; i2 < XmlParser.this._observers.size(); i2++) {
                if (XmlParser.this._observers.get(i2) != null) {
                    ((ContentHandler) XmlParser.this._observers.get(i2)).startElement(str, str2, str3, attributes);
                }
            }
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void endElement(String str, String str2, String str3) throws SAXException {
            this._context = this._context._parent;
            for (int i = 0; i < XmlParser.this._observers.size(); i++) {
                if (XmlParser.this._observers.get(i) != null) {
                    ((ContentHandler) XmlParser.this._observers.get(i)).endElement(str, str2, str3);
                }
            }
            XmlParser.this._observers.pop();
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void ignorableWhitespace(char[] cArr, int i, int i2) throws SAXException {
            for (int i3 = 0; i3 < XmlParser.this._observers.size(); i3++) {
                if (XmlParser.this._observers.get(i3) != null) {
                    ((ContentHandler) XmlParser.this._observers.get(i3)).ignorableWhitespace(cArr, i, i2);
                }
            }
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void characters(char[] cArr, int i, int i2) throws SAXException {
            this._context.add(new String(cArr, i, i2));
            for (int i3 = 0; i3 < XmlParser.this._observers.size(); i3++) {
                if (XmlParser.this._observers.get(i3) != null) {
                    ((ContentHandler) XmlParser.this._observers.get(i3)).characters(cArr, i, i2);
                }
            }
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ErrorHandler
        public void warning(SAXParseException sAXParseException) {
            Log.debug(Log.EXCEPTION, sAXParseException);
            Log.warn(new StringBuffer("WARNING@").append(getLocationString(sAXParseException)).append(" : ").append(sAXParseException.toString()).toString());
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ErrorHandler
        public void error(SAXParseException sAXParseException) throws SAXException {
            if (this._error == null) {
                this._error = sAXParseException;
            }
            Log.debug(Log.EXCEPTION, sAXParseException);
            Log.warn(new StringBuffer("ERROR@").append(getLocationString(sAXParseException)).append(" : ").append(sAXParseException.toString()).toString());
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ErrorHandler
        public void fatalError(SAXParseException sAXParseException) throws SAXException {
            this._error = sAXParseException;
            Log.debug(Log.EXCEPTION, sAXParseException);
            Log.warn(new StringBuffer("FATAL@").append(getLocationString(sAXParseException)).append(" : ").append(sAXParseException.toString()).toString());
            throw sAXParseException;
        }

        private String getLocationString(SAXParseException sAXParseException) {
            return new StringBuffer().append(sAXParseException.getSystemId()).append(" line:").append(sAXParseException.getLineNumber()).append(" col:").append(sAXParseException.getColumnNumber()).toString();
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.EntityResolver
        public InputSource resolveEntity(String str, String str2) {
            if (Log.isDebugEnabled()) {
                Log.debug(new StringBuffer("resolveEntity(").append(str).append(", ").append(str2).append(")").toString());
            }
            if (str2 != null && str2.endsWith(".dtd")) {
                XmlParser.this._dtd = str2;
            }
            URL url = str != null ? (URL) XmlParser.this._redirectMap.get(str) : null;
            if (url == null) {
                url = (URL) XmlParser.this._redirectMap.get(str2);
            }
            if (url == null) {
                String substring = str2.lastIndexOf(47) >= 0 ? str2.substring(str2.lastIndexOf(47) + 1) : str2;
                if (Log.isDebugEnabled()) {
                    Log.debug(new StringBuffer("Can't exact match entity in redirect map, trying ").append(substring).toString());
                }
                url = (URL) XmlParser.this._redirectMap.get(substring);
            }
            if (url != null) {
                try {
                    InputStream openStream = url.openStream();
                    if (Log.isDebugEnabled()) {
                        Log.debug(new StringBuffer("Redirected entity ").append(str2).append(" --> ").append(url).toString());
                    }
                    InputSource inputSource = new InputSource(openStream);
                    inputSource.setSystemId(str2);
                    return inputSource;
                } catch (IOException e) {
                    Log.ignore(e);
                }
            }
            return null;
        }
    }

    public static class Attribute {
        private String _name;
        private String _value;

        Attribute(String str, String str2) {
            this._name = str;
            this._value = str2;
        }

        public String getName() {
            return this._name;
        }

        public String getValue() {
            return this._value;
        }
    }

    public static class Node extends AbstractList {
        private Attribute[] _attrs;
        private boolean _lastString = false;
        private ArrayList _list;
        Node _parent;
        private String _path;
        private String _tag;

        Node(Node node, String str, Attributes attributes) {
            this._parent = node;
            this._tag = str;
            if (attributes != null) {
                this._attrs = new Attribute[attributes.getLength()];
                for (int i = 0; i < attributes.getLength(); i++) {
                    String localName = attributes.getLocalName(i);
                    if (localName == null || localName.equals("")) {
                        localName = attributes.getQName(i);
                    }
                    this._attrs[i] = new Attribute(localName, attributes.getValue(i));
                }
            }
        }

        public Node getParent() {
            return this._parent;
        }

        public String getTag() {
            return this._tag;
        }

        public String getPath() {
            if (this._path == null) {
                if (getParent() != null && getParent().getTag() != null) {
                    this._path = new StringBuffer().append(getParent().getPath()).append(URIUtil.SLASH).append(this._tag).toString();
                } else {
                    this._path = new StringBuffer(URIUtil.SLASH).append(this._tag).toString();
                }
            }
            return this._path;
        }

        public Attribute[] getAttributes() {
            return this._attrs;
        }

        public String getAttribute(String str) {
            return getAttribute(str, null);
        }

        public String getAttribute(String str, String str2) {
            if (this._attrs != null && str != null) {
                int i = 0;
                while (true) {
                    Attribute[] attributeArr = this._attrs;
                    if (i >= attributeArr.length) {
                        break;
                    }
                    if (str.equals(attributeArr[i].getName())) {
                        return this._attrs[i].getValue();
                    }
                    i++;
                }
            }
            return str2;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            ArrayList arrayList = this._list;
            if (arrayList != null) {
                return arrayList.size();
            }
            return 0;
        }

        @Override // java.util.AbstractList, java.util.List
        public Object get(int i) {
            ArrayList arrayList = this._list;
            if (arrayList != null) {
                return arrayList.get(i);
            }
            return null;
        }

        public Node get(String str) {
            if (this._list == null) {
                return null;
            }
            for (int i = 0; i < this._list.size(); i++) {
                Object obj = this._list.get(i);
                if (obj instanceof Node) {
                    Node node = (Node) obj;
                    if (str.equals(node._tag)) {
                        return node;
                    }
                }
            }
            return null;
        }

        @Override // java.util.AbstractList, java.util.List
        public void add(int i, Object obj) {
            if (this._list == null) {
                this._list = new ArrayList();
            }
            if (obj instanceof String) {
                if (this._lastString) {
                    int size = this._list.size() - 1;
                    this._list.set(size, new StringBuffer().append((String) this._list.get(size)).append(obj).toString());
                } else {
                    this._list.add(i, obj);
                }
                this._lastString = true;
                return;
            }
            this._lastString = false;
            this._list.add(i, obj);
        }

        @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public void clear() {
            ArrayList arrayList = this._list;
            if (arrayList != null) {
                arrayList.clear();
            }
            this._list = null;
        }

        public String getString(String str, boolean z, boolean z2) {
            Node node = get(str);
            if (node == null) {
                return null;
            }
            String node2 = node.toString(z);
            return (node2 == null || !z2) ? node2 : node2.trim();
        }

        @Override // java.util.AbstractCollection
        public synchronized String toString() {
            return toString(true);
        }

        public synchronized String toString(boolean z) {
            String stringBuffer;
            StringBuffer stringBuffer2 = new StringBuffer();
            synchronized (stringBuffer2) {
                toString(stringBuffer2, z);
                stringBuffer = stringBuffer2.toString();
            }
            return stringBuffer;
        }

        public synchronized String toString(boolean z, boolean z2) {
            String node;
            node = toString(z);
            if (node != null && z2) {
                node = node.trim();
            }
            return node;
        }

        private synchronized void toString(StringBuffer stringBuffer, boolean z) {
            if (z) {
                stringBuffer.append("<");
                stringBuffer.append(this._tag);
                if (this._attrs != null) {
                    for (int i = 0; i < this._attrs.length; i++) {
                        stringBuffer.append(SpanChipTokenizer.AUTOCORRECT_SEPARATOR);
                        stringBuffer.append(this._attrs[i].getName());
                        stringBuffer.append("=\"");
                        stringBuffer.append(this._attrs[i].getValue());
                        stringBuffer.append("\"");
                    }
                }
            }
            if (this._list != null) {
                if (z) {
                    stringBuffer.append(">");
                }
                for (int i2 = 0; i2 < this._list.size(); i2++) {
                    Object obj = this._list.get(i2);
                    if (obj != null) {
                        if (obj instanceof Node) {
                            ((Node) obj).toString(stringBuffer, z);
                        } else {
                            stringBuffer.append(obj.toString());
                        }
                    }
                }
                if (z) {
                    stringBuffer.append("</");
                    stringBuffer.append(this._tag);
                    stringBuffer.append(">");
                }
            } else if (z) {
                stringBuffer.append("/>");
            }
        }

        public Iterator iterator(final String str) {
            return new Iterator() { // from class: org.mortbay.xml.XmlParser.Node.1
                Node _node;
                int c = 0;

                @Override // java.util.Iterator
                public boolean hasNext() {
                    if (this._node != null) {
                        return true;
                    }
                    while (Node.this._list != null && this.c < Node.this._list.size()) {
                        Object obj = Node.this._list.get(this.c);
                        if (obj instanceof Node) {
                            Node node = (Node) obj;
                            if (str.equals(node._tag)) {
                                this._node = node;
                                return true;
                            }
                        }
                        this.c++;
                    }
                    return false;
                }

                @Override // java.util.Iterator
                public Object next() {
                    try {
                        if (hasNext()) {
                            return this._node;
                        }
                        throw new NoSuchElementException();
                    } finally {
                        this._node = null;
                        this.c++;
                    }
                }

                @Override // java.util.Iterator
                public void remove() {
                    throw new UnsupportedOperationException("Not supported");
                }
            };
        }
    }
}
