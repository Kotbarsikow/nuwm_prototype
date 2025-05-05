package com.fasterxml.jackson.core.base;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.core.util.VersionUtil;
import com.github.mikephil.charting.utils.Utils;
import java.io.IOException;

/* loaded from: classes.dex */
public abstract class ParserMinimalBase extends JsonParser {
    protected static final int INT_APOSTROPHE = 39;
    protected static final int INT_ASTERISK = 42;
    protected static final int INT_BACKSLASH = 92;
    protected static final int INT_COLON = 58;
    protected static final int INT_COMMA = 44;
    protected static final int INT_CR = 13;
    protected static final int INT_LBRACKET = 91;
    protected static final int INT_LCURLY = 123;
    protected static final int INT_LF = 10;
    protected static final int INT_QUOTE = 34;
    protected static final int INT_RBRACKET = 93;
    protected static final int INT_RCURLY = 125;
    protected static final int INT_SLASH = 47;
    protected static final int INT_SPACE = 32;
    protected static final int INT_TAB = 9;
    protected static final int INT_b = 98;
    protected static final int INT_f = 102;
    protected static final int INT_n = 110;
    protected static final int INT_r = 114;
    protected static final int INT_t = 116;
    protected static final int INT_u = 117;
    protected JsonToken _currToken;
    protected JsonToken _lastClearedToken;

    protected abstract void _handleEOF() throws JsonParseException;

    @Override // com.fasterxml.jackson.core.JsonParser, java.io.Closeable, java.lang.AutoCloseable
    public abstract void close() throws IOException;

    @Override // com.fasterxml.jackson.core.JsonParser
    public abstract byte[] getBinaryValue(Base64Variant base64Variant) throws IOException, JsonParseException;

    @Override // com.fasterxml.jackson.core.JsonParser
    public abstract String getCurrentName() throws IOException, JsonParseException;

    @Override // com.fasterxml.jackson.core.JsonParser
    public abstract JsonStreamContext getParsingContext();

    @Override // com.fasterxml.jackson.core.JsonParser
    public abstract String getText() throws IOException, JsonParseException;

    @Override // com.fasterxml.jackson.core.JsonParser
    public abstract char[] getTextCharacters() throws IOException, JsonParseException;

    @Override // com.fasterxml.jackson.core.JsonParser
    public abstract int getTextLength() throws IOException, JsonParseException;

    @Override // com.fasterxml.jackson.core.JsonParser
    public abstract int getTextOffset() throws IOException, JsonParseException;

    @Override // com.fasterxml.jackson.core.JsonParser
    public abstract boolean hasTextCharacters();

    @Override // com.fasterxml.jackson.core.JsonParser
    public abstract boolean isClosed();

    @Override // com.fasterxml.jackson.core.JsonParser
    public abstract JsonToken nextToken() throws IOException, JsonParseException;

    @Override // com.fasterxml.jackson.core.JsonParser
    public abstract void overrideCurrentName(String str);

    protected ParserMinimalBase() {
    }

    protected ParserMinimalBase(int i) {
        super(i);
    }

    @Override // com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.core.Versioned
    public Version version() {
        return VersionUtil.versionFor(getClass());
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public JsonToken getCurrentToken() {
        return this._currToken;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public boolean hasCurrentToken() {
        return this._currToken != null;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public JsonToken nextValue() throws IOException, JsonParseException {
        JsonToken nextToken = nextToken();
        return nextToken == JsonToken.FIELD_NAME ? nextToken() : nextToken;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public JsonParser skipChildren() throws IOException, JsonParseException {
        if (this._currToken != JsonToken.START_OBJECT && this._currToken != JsonToken.START_ARRAY) {
            return this;
        }
        int i = 1;
        while (true) {
            JsonToken nextToken = nextToken();
            if (nextToken == null) {
                _handleEOF();
                return this;
            }
            int i2 = AnonymousClass1.$SwitchMap$com$fasterxml$jackson$core$JsonToken[nextToken.ordinal()];
            if (i2 == 1 || i2 == 2) {
                i++;
            } else if (i2 == 3 || i2 == 4) {
                i--;
                if (i == 0) {
                    return this;
                }
            }
        }
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public void clearCurrentToken() {
        JsonToken jsonToken = this._currToken;
        if (jsonToken != null) {
            this._lastClearedToken = jsonToken;
            this._currToken = null;
        }
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public JsonToken getLastClearedToken() {
        return this._lastClearedToken;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public boolean getValueAsBoolean(boolean z) throws IOException, JsonParseException {
        if (this._currToken != null) {
            switch (this._currToken) {
                case VALUE_NUMBER_INT:
                    return getIntValue() != 0;
                case VALUE_TRUE:
                    return true;
                case VALUE_FALSE:
                case VALUE_NULL:
                    return false;
                case VALUE_EMBEDDED_OBJECT:
                    Object embeddedObject = getEmbeddedObject();
                    if (embeddedObject instanceof Boolean) {
                        return ((Boolean) embeddedObject).booleanValue();
                    }
                case VALUE_STRING:
                    if ("true".equals(getText().trim())) {
                        return true;
                    }
                default:
                    return z;
            }
        }
        return z;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public int getValueAsInt(int i) throws IOException, JsonParseException {
        if (this._currToken == null) {
            return i;
        }
        switch (this._currToken) {
            case VALUE_NUMBER_INT:
            case VALUE_NUMBER_FLOAT:
                return getIntValue();
            case VALUE_TRUE:
                return 1;
            case VALUE_FALSE:
            case VALUE_NULL:
                return 0;
            case VALUE_EMBEDDED_OBJECT:
                Object embeddedObject = getEmbeddedObject();
                return embeddedObject instanceof Number ? ((Number) embeddedObject).intValue() : i;
            case VALUE_STRING:
                return NumberInput.parseAsInt(getText(), i);
            default:
                return i;
        }
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public long getValueAsLong(long j) throws IOException, JsonParseException {
        if (this._currToken == null) {
            return j;
        }
        switch (this._currToken) {
            case VALUE_NUMBER_INT:
            case VALUE_NUMBER_FLOAT:
                return getLongValue();
            case VALUE_TRUE:
                return 1L;
            case VALUE_FALSE:
            case VALUE_NULL:
                return 0L;
            case VALUE_EMBEDDED_OBJECT:
                Object embeddedObject = getEmbeddedObject();
                return embeddedObject instanceof Number ? ((Number) embeddedObject).longValue() : j;
            case VALUE_STRING:
                return NumberInput.parseAsLong(getText(), j);
            default:
                return j;
        }
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public double getValueAsDouble(double d) throws IOException, JsonParseException {
        if (this._currToken == null) {
            return d;
        }
        switch (this._currToken) {
            case VALUE_NUMBER_INT:
            case VALUE_NUMBER_FLOAT:
                return getDoubleValue();
            case VALUE_TRUE:
                return 1.0d;
            case VALUE_FALSE:
            case VALUE_NULL:
                return Utils.DOUBLE_EPSILON;
            case VALUE_EMBEDDED_OBJECT:
                Object embeddedObject = getEmbeddedObject();
                return embeddedObject instanceof Number ? ((Number) embeddedObject).doubleValue() : d;
            case VALUE_STRING:
                return NumberInput.parseAsDouble(getText(), d);
            default:
                return d;
        }
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public String getValueAsString(String str) throws IOException, JsonParseException {
        JsonToken jsonToken;
        return (this._currToken == JsonToken.VALUE_STRING || !((jsonToken = this._currToken) == null || jsonToken == JsonToken.VALUE_NULL || !this._currToken.isScalarValue())) ? getText() : str;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x001b, code lost:
    
        if (r5 >= 0) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x001d, code lost:
    
        _reportInvalidBase64(r14, r4, 0, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0020, code lost:
    
        if (r3 < r0) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0022, code lost:
    
        _reportBase64EOF();
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0025, code lost:
    
        r4 = r2 + 2;
        r3 = r12.charAt(r3);
        r7 = r14.decodeBase64Char(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x002f, code lost:
    
        if (r7 >= 0) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0031, code lost:
    
        _reportInvalidBase64(r14, r3, 1, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0035, code lost:
    
        r3 = (r5 << 6) | r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0038, code lost:
    
        if (r4 < r0) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x003e, code lost:
    
        if (r14.usesPadding() != false) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0047, code lost:
    
        _reportBase64EOF();
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0040, code lost:
    
        r13.append(r3 >> 4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x00c7, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x004a, code lost:
    
        r5 = r2 + 3;
        r4 = r12.charAt(r4);
        r7 = r14.decodeBase64Char(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0057, code lost:
    
        if (r7 >= 0) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0090, code lost:
    
        r3 = (r3 << 6) | r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0093, code lost:
    
        if (r5 < r0) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0099, code lost:
    
        if (r14.usesPadding() != false) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00a1, code lost:
    
        _reportBase64EOF();
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x009b, code lost:
    
        r13.appendTwoBytes(r3 >> 2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00a4, code lost:
    
        r2 = r2 + 4;
        r4 = r12.charAt(r5);
        r5 = r14.decodeBase64Char(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00ae, code lost:
    
        if (r5 >= 0) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00bc, code lost:
    
        r13.appendThreeBytes((r3 << 6) | r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00b0, code lost:
    
        if (r5 == (-2)) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00b2, code lost:
    
        _reportInvalidBase64(r14, r4, 3, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00b5, code lost:
    
        r13.appendTwoBytes(r3 >> 2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0059, code lost:
    
        if (r7 == (-2)) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x005b, code lost:
    
        _reportInvalidBase64(r14, r4, 2, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x005e, code lost:
    
        if (r5 < r0) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0060, code lost:
    
        _reportBase64EOF();
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0063, code lost:
    
        r2 = r2 + 4;
        r4 = r12.charAt(r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x006d, code lost:
    
        if (r14.usesPaddingChar(r4) != false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x006f, code lost:
    
        _reportInvalidBase64(r14, r4, 3, "expected padding character '" + r14.getPaddingChar() + "'");
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0089, code lost:
    
        r13.append(r3 >> 4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0016, code lost:
    
        r5 = r14.decodeBase64Char(r4);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void _decodeBase64(java.lang.String r12, com.fasterxml.jackson.core.util.ByteArrayBuilder r13, com.fasterxml.jackson.core.Base64Variant r14) throws java.io.IOException, com.fasterxml.jackson.core.JsonParseException {
        /*
            r11 = this;
            int r0 = r12.length()
            r1 = 0
            r2 = 0
        L6:
            if (r2 >= r0) goto Lc7
        L8:
            int r3 = r2 + 1
            char r4 = r12.charAt(r2)
            if (r3 < r0) goto L12
            goto Lc7
        L12:
            r5 = 32
            if (r4 <= r5) goto Lc4
            int r5 = r14.decodeBase64Char(r4)
            r6 = 0
            if (r5 >= 0) goto L20
            r11._reportInvalidBase64(r14, r4, r1, r6)
        L20:
            if (r3 < r0) goto L25
            r11._reportBase64EOF()
        L25:
            int r4 = r2 + 2
            char r3 = r12.charAt(r3)
            int r7 = r14.decodeBase64Char(r3)
            if (r7 >= 0) goto L35
            r8 = 1
            r11._reportInvalidBase64(r14, r3, r8, r6)
        L35:
            int r3 = r5 << 6
            r3 = r3 | r7
            if (r4 < r0) goto L4a
            boolean r5 = r14.usesPadding()
            if (r5 != 0) goto L47
            int r12 = r3 >> 4
            r13.append(r12)
            goto Lc7
        L47:
            r11._reportBase64EOF()
        L4a:
            int r5 = r2 + 3
            char r4 = r12.charAt(r4)
            int r7 = r14.decodeBase64Char(r4)
            r8 = -2
            r9 = 3
            r10 = 2
            if (r7 >= 0) goto L90
            if (r7 == r8) goto L5e
            r11._reportInvalidBase64(r14, r4, r10, r6)
        L5e:
            if (r5 < r0) goto L63
            r11._reportBase64EOF()
        L63:
            int r2 = r2 + 4
            char r4 = r12.charAt(r5)
            boolean r5 = r14.usesPaddingChar(r4)
            if (r5 != 0) goto L89
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = "expected padding character '"
            r5.<init>(r6)
            char r6 = r14.getPaddingChar()
            r5.append(r6)
            java.lang.String r6 = "'"
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            r11._reportInvalidBase64(r14, r4, r9, r5)
        L89:
            int r3 = r3 >> 4
            r13.append(r3)
            goto L6
        L90:
            int r3 = r3 << 6
            r3 = r3 | r7
            if (r5 < r0) goto La4
            boolean r4 = r14.usesPadding()
            if (r4 != 0) goto La1
            int r12 = r3 >> 2
            r13.appendTwoBytes(r12)
            goto Lc7
        La1:
            r11._reportBase64EOF()
        La4:
            int r2 = r2 + 4
            char r4 = r12.charAt(r5)
            int r5 = r14.decodeBase64Char(r4)
            if (r5 >= 0) goto Lbc
            if (r5 == r8) goto Lb5
            r11._reportInvalidBase64(r14, r4, r9, r6)
        Lb5:
            int r3 = r3 >> 2
            r13.appendTwoBytes(r3)
            goto L6
        Lbc:
            int r3 = r3 << 6
            r3 = r3 | r5
            r13.appendThreeBytes(r3)
            goto L6
        Lc4:
            r2 = r3
            goto L8
        Lc7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.base.ParserMinimalBase._decodeBase64(java.lang.String, com.fasterxml.jackson.core.util.ByteArrayBuilder, com.fasterxml.jackson.core.Base64Variant):void");
    }

    protected void _reportInvalidBase64(Base64Variant base64Variant, char c, int i, String str) throws JsonParseException {
        String str2;
        if (c <= ' ') {
            str2 = "Illegal white space character (code 0x" + Integer.toHexString(c) + ") as character #" + (i + 1) + " of 4-char base64 unit: can only used between units";
        } else if (base64Variant.usesPaddingChar(c)) {
            str2 = "Unexpected padding character ('" + base64Variant.getPaddingChar() + "') as character #" + (i + 1) + " of 4-char base64 unit: padding only legal as 3rd or 4th character";
        } else if (!Character.isDefined(c) || Character.isISOControl(c)) {
            str2 = "Illegal character (code 0x" + Integer.toHexString(c) + ") in base64 content";
        } else {
            str2 = "Illegal character '" + c + "' (code 0x" + Integer.toHexString(c) + ") in base64 content";
        }
        if (str != null) {
            str2 = str2 + ": " + str;
        }
        throw _constructError(str2);
    }

    protected void _reportBase64EOF() throws JsonParseException {
        throw _constructError("Unexpected end-of-String in base64 content");
    }

    protected void _reportUnexpectedChar(int i, String str) throws JsonParseException {
        String str2 = "Unexpected character (" + _getCharDesc(i) + ")";
        if (str != null) {
            str2 = str2 + ": " + str;
        }
        _reportError(str2);
    }

    protected void _reportInvalidEOF() throws JsonParseException {
        _reportInvalidEOF(" in " + this._currToken);
    }

    protected void _reportInvalidEOF(String str) throws JsonParseException {
        _reportError("Unexpected end-of-input" + str);
    }

    protected void _reportInvalidEOFInValue() throws JsonParseException {
        _reportInvalidEOF(" in a value");
    }

    protected void _throwInvalidSpace(int i) throws JsonParseException {
        _reportError("Illegal character (" + _getCharDesc((char) i) + "): only regular white space (\\r, \\n, \\t) is allowed between tokens");
    }

    protected void _throwUnquotedSpace(int i, String str) throws JsonParseException {
        if (!isEnabled(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS) || i >= 32) {
            _reportError("Illegal unquoted character (" + _getCharDesc((char) i) + "): has to be escaped using backslash to be included in " + str);
        }
    }

    protected char _handleUnrecognizedCharacterEscape(char c) throws JsonProcessingException {
        if (isEnabled(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER)) {
            return c;
        }
        if (c == '\'' && isEnabled(JsonParser.Feature.ALLOW_SINGLE_QUOTES)) {
            return c;
        }
        _reportError("Unrecognized character escape " + _getCharDesc(c));
        return c;
    }

    protected static final String _getCharDesc(int i) {
        char c = (char) i;
        if (Character.isISOControl(c)) {
            return "(CTRL-CHAR, code " + i + ")";
        }
        if (i > 255) {
            return "'" + c + "' (code " + i + " / 0x" + Integer.toHexString(i) + ")";
        }
        return "'" + c + "' (code " + i + ")";
    }

    protected final void _reportError(String str) throws JsonParseException {
        throw _constructError(str);
    }

    protected final void _wrapError(String str, Throwable th) throws JsonParseException {
        throw _constructError(str, th);
    }

    protected final void _throwInternal() {
        throw new RuntimeException("Internal error: this code path should never get executed");
    }

    protected final JsonParseException _constructError(String str, Throwable th) {
        return new JsonParseException(str, getCurrentLocation(), th);
    }
}
