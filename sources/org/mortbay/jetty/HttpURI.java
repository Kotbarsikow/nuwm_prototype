package org.mortbay.jetty;

import java.io.UnsupportedEncodingException;
import org.mortbay.util.MultiMap;
import org.mortbay.util.StringUtil;
import org.mortbay.util.TypeUtil;
import org.mortbay.util.URIUtil;
import org.mortbay.util.UrlEncoded;
import org.mortbay.util.Utf8StringBuffer;

/* loaded from: classes3.dex */
public class HttpURI {
    private static final int ASTERISK = 10;
    private static final int AUTH = 4;
    private static final int AUTH_OR_PATH = 1;
    private static final int IPV6 = 5;
    private static final int PARAM = 8;
    private static final int PATH = 7;
    private static final int PORT = 6;
    private static final int QUERY = 9;
    private static final int SCHEME_OR_PATH = 2;
    private static final int START = 0;
    private static byte[] __empty = new byte[0];
    int _authority;
    int _end;
    int _fragment;
    int _host;
    int _param;
    boolean _partial;
    int _path;
    int _port;
    int _query;
    byte[] _raw;
    String _rawString;
    int _scheme;
    Utf8StringBuffer _utf8b;

    public HttpURI() {
        this._partial = false;
        this._raw = __empty;
        this._utf8b = new Utf8StringBuffer(64);
    }

    public HttpURI(boolean z) {
        this._partial = false;
        this._raw = __empty;
        this._utf8b = new Utf8StringBuffer(64);
        this._partial = z;
    }

    public HttpURI(String str) {
        this._partial = false;
        this._raw = __empty;
        this._utf8b = new Utf8StringBuffer(64);
        this._rawString = str;
        byte[] bytes = str.getBytes();
        parse(bytes, 0, bytes.length);
    }

    public HttpURI(byte[] bArr, int i, int i2) {
        this._partial = false;
        this._raw = __empty;
        this._utf8b = new Utf8StringBuffer(64);
        parse2(bArr, i, i2);
    }

    public void parse(String str) {
        byte[] bytes = str.getBytes();
        parse2(bytes, 0, bytes.length);
        this._rawString = str;
    }

    public void parse(byte[] bArr, int i, int i2) {
        this._rawString = null;
        parse2(bArr, i, i2);
    }

    private void parse2(byte[] bArr, int i, int i2) {
        this._raw = bArr;
        int i3 = i + i2;
        this._end = i3;
        this._scheme = i;
        this._authority = i;
        this._host = i;
        this._port = i;
        this._path = i;
        this._param = i3;
        this._query = i3;
        this._fragment = i3;
        char c = 0;
        int i4 = i;
        int i5 = i4;
        while (i4 < i3) {
            byte[] bArr2 = this._raw;
            char c2 = (char) (bArr2[i4] & 255);
            int i6 = i4 + 1;
            switch (c) {
                case 0:
                    if (c2 == '#') {
                        this._param = i4;
                        this._query = i4;
                        this._fragment = i4;
                    } else if (c2 == '*') {
                        this._path = i4;
                        c = '\n';
                    } else if (c2 == '/') {
                        i5 = i4;
                        i4 = i6;
                        c = 1;
                    } else if (c2 == ';') {
                        this._param = i4;
                        i5 = i4;
                        i4 = i6;
                        c = '\b';
                    } else if (c2 == '?') {
                        this._param = i4;
                        this._query = i4;
                        i5 = i4;
                        i4 = i6;
                        c = '\t';
                    } else {
                        if (!Character.isLetterOrDigit(c2)) {
                            throw new IllegalArgumentException(StringUtil.toString(this._raw, i, i2, URIUtil.__CHARSET));
                        }
                        c = 2;
                    }
                    i5 = i4;
                    i4 = i6;
                case 1:
                    if ((this._partial || this._scheme != this._authority) && c2 == '/') {
                        this._host = i6;
                        int i7 = this._end;
                        this._port = i7;
                        this._path = i7;
                        i4 = i6;
                        c = 4;
                    } else {
                        if (c2 != ';' && c2 != '?' && c2 != '#') {
                            this._host = i5;
                            this._port = i5;
                            i4 = i6;
                        }
                        c = 7;
                    }
                    break;
                case 2:
                    if (i2 > 6 && c2 == 't') {
                        int i8 = i + 3;
                        if (bArr2[i8] == 58) {
                            i6 = i + 4;
                        } else {
                            i8 = i + 4;
                            if (bArr2[i8] == 58) {
                                i6 = i + 5;
                            } else {
                                i8 = i + 5;
                                if (bArr2[i8] == 58) {
                                    i6 = i + 6;
                                }
                            }
                        }
                        i4 = i8;
                        c2 = ':';
                    }
                    if (c2 == '#') {
                        this._param = i4;
                        this._query = i4;
                        this._fragment = i4;
                    } else if (c2 == '/') {
                        i4 = i6;
                        c = 7;
                    } else if (c2 == '?') {
                        this._param = i4;
                        this._query = i4;
                        i4 = i6;
                        c = '\t';
                    } else if (c2 == ':') {
                        int i9 = i6 + 1;
                        this._authority = i6;
                        this._path = i6;
                        if (((char) (bArr2[i9] & 255)) == '/') {
                            i4 = i9;
                            i5 = i6;
                            c = 1;
                        } else {
                            this._host = i6;
                            this._port = i6;
                            i4 = i9;
                            i5 = i6;
                            c = 7;
                        }
                    } else if (c2 == ';') {
                        this._param = i4;
                        i4 = i6;
                        c = '\b';
                    }
                    i4 = i6;
                    break;
                case 3:
                default:
                    i4 = i6;
                case 4:
                    if (c2 == '/') {
                        this._path = i4;
                        this._port = i4;
                        i5 = i4;
                        c = 7;
                    } else if (c2 == ':') {
                        this._port = i4;
                        c = 6;
                    } else if (c2 == '@') {
                        this._host = i6;
                    } else if (c2 == '[') {
                        c = 5;
                    }
                    i4 = i6;
                case 5:
                    if (c2 == '/') {
                        throw new IllegalArgumentException(new StringBuffer("No closing ']' for ").append(StringUtil.toString(this._raw, i, i2, URIUtil.__CHARSET)).toString());
                    }
                    if (c2 == ']') {
                        c = 4;
                    }
                    i4 = i6;
                case 6:
                    if (c2 == '/') {
                        this._path = i4;
                        if (this._port <= this._authority) {
                            this._port = i4;
                        }
                        i5 = i4;
                        i4 = i6;
                        c = 7;
                    } else {
                        i4 = i6;
                    }
                case 7:
                    if (c2 == '#') {
                        this._param = i4;
                        this._query = i4;
                        this._fragment = i4;
                    } else if (c2 == ';') {
                        this._param = i4;
                        c = '\b';
                    } else if (c2 == '?') {
                        this._param = i4;
                        this._query = i4;
                        c = '\t';
                    }
                    i4 = i6;
                case '\b':
                    if (c2 == '#') {
                        this._query = i4;
                        this._fragment = i4;
                    } else if (c2 == '?') {
                        this._query = i4;
                        c = '\t';
                    }
                    i4 = i6;
                case '\t':
                    if (c2 == '#') {
                        this._fragment = i4;
                    }
                    i4 = i6;
                case '\n':
                    throw new IllegalArgumentException("only '*'");
            }
        }
    }

    private String toUtf8String(int i, int i2) {
        this._utf8b.reset();
        this._utf8b.append(this._raw, i, i2);
        return this._utf8b.toString();
    }

    public String getScheme() {
        int i = this._scheme;
        int i2 = this._authority;
        if (i == i2) {
            return null;
        }
        int i3 = i2 - i;
        if (i3 == 5) {
            byte[] bArr = this._raw;
            if (bArr[i] == 104 && bArr[i + 1] == 116 && bArr[i + 2] == 116 && bArr[i + 3] == 112) {
                return "http";
            }
        }
        if (i3 == 6) {
            byte[] bArr2 = this._raw;
            if (bArr2[i] == 104 && bArr2[i + 1] == 116 && bArr2[i + 2] == 116 && bArr2[i + 3] == 112 && bArr2[i + 4] == 115) {
                return "https";
            }
        }
        return toUtf8String(i, (i2 - i) - 1);
    }

    public String getAuthority() {
        int i = this._authority;
        int i2 = this._path;
        if (i == i2) {
            return null;
        }
        return toUtf8String(i, i2 - i);
    }

    public String getHost() {
        int i = this._host;
        int i2 = this._port;
        if (i == i2) {
            return null;
        }
        return toUtf8String(i, i2 - i);
    }

    public int getPort() {
        int i = this._port;
        if (i == this._path) {
            return -1;
        }
        return TypeUtil.parseInt(this._raw, i + 1, (r1 - i) - 1, 10);
    }

    public String getPath() {
        int i = this._path;
        int i2 = this._param;
        if (i == i2) {
            return null;
        }
        return toUtf8String(i, i2 - i);
    }

    public String getDecodedPath() {
        int i;
        int i2 = this._path;
        int i3 = this._param;
        byte[] bArr = null;
        if (i2 == i3) {
            return null;
        }
        int i4 = i3 - i2;
        int i5 = 0;
        while (true) {
            int i6 = this._param;
            if (i2 >= i6) {
                break;
            }
            byte[] bArr2 = this._raw;
            byte b = bArr2[i2];
            if (b == 37 && (i = i2 + 2) < i6) {
                b = (byte) (TypeUtil.parseInt(bArr2, i2 + 1, 2, 16) & 255);
                i2 = i;
            } else if (bArr == null) {
                i5++;
                i2++;
            }
            if (bArr == null) {
                bArr = new byte[i4];
                for (int i7 = 0; i7 < i5; i7++) {
                    bArr[i7] = this._raw[this._path + i7];
                }
            }
            bArr[i5] = b;
            i5++;
            i2++;
        }
        if (bArr == null) {
            return toUtf8String(this._path, i4);
        }
        this._utf8b.reset();
        this._utf8b.append(bArr, 0, i5);
        return this._utf8b.toString();
    }

    public String getPathAndParam() {
        int i = this._path;
        int i2 = this._query;
        if (i == i2) {
            return null;
        }
        return toUtf8String(i, i2 - i);
    }

    public String getCompletePath() {
        int i = this._path;
        int i2 = this._end;
        if (i == i2) {
            return null;
        }
        return toUtf8String(i, i2 - i);
    }

    public String getParam() {
        int i = this._param;
        if (i == this._query) {
            return null;
        }
        return toUtf8String(i + 1, (r1 - i) - 1);
    }

    public String getQuery() {
        int i = this._query;
        if (i == this._fragment) {
            return null;
        }
        return toUtf8String(i + 1, (r1 - i) - 1);
    }

    public String getQuery(String str) {
        int i = this._query;
        if (i == this._fragment) {
            return null;
        }
        return StringUtil.toString(this._raw, i + 1, (r1 - i) - 1, str);
    }

    public boolean hasQuery() {
        return this._fragment > this._query;
    }

    public String getFragment() {
        int i = this._fragment;
        if (i == this._end) {
            return null;
        }
        return toUtf8String(i + 1, (r1 - i) - 1);
    }

    public void decodeQueryTo(MultiMap multiMap) {
        if (this._query == this._fragment) {
            return;
        }
        this._utf8b.reset();
        UrlEncoded.decodeUtf8To(this._raw, this._query + 1, (this._fragment - r1) - 1, multiMap, this._utf8b);
    }

    public void decodeQueryTo(MultiMap multiMap, String str) throws UnsupportedEncodingException {
        if (this._query == this._fragment) {
            return;
        }
        if (str == null || StringUtil.isUTF8(str)) {
            UrlEncoded.decodeUtf8To(this._raw, this._query + 1, (this._fragment - r0) - 1, multiMap);
        } else {
            UrlEncoded.decodeTo(toUtf8String(this._query + 1, (this._fragment - r0) - 1), multiMap, str);
        }
    }

    public void clear() {
        this._end = 0;
        this._fragment = 0;
        this._query = 0;
        this._param = 0;
        this._path = 0;
        this._port = 0;
        this._host = 0;
        this._authority = 0;
        this._scheme = 0;
        this._raw = __empty;
        this._rawString = "";
    }

    public String toString() {
        if (this._rawString == null) {
            int i = this._scheme;
            this._rawString = toUtf8String(i, this._end - i);
        }
        return this._rawString;
    }

    public void writeTo(Utf8StringBuffer utf8StringBuffer) {
        byte[] bArr = this._raw;
        int i = this._scheme;
        utf8StringBuffer.append(bArr, i, this._end - i);
    }
}
