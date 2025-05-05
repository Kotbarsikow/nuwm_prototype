package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.base.ParserBase;
import com.fasterxml.jackson.core.io.CharTypes;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.sym.BytesToNameCanonicalizer;
import com.fasterxml.jackson.core.sym.Name;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: classes.dex */
public final class UTF8StreamJsonParser extends ParserBase {
    static final byte BYTE_LF = 10;
    protected boolean _bufferRecyclable;
    protected byte[] _inputBuffer;
    protected InputStream _inputStream;
    protected ObjectCodec _objectCodec;
    private int _quad1;
    protected int[] _quadBuffer;
    protected final BytesToNameCanonicalizer _symbols;
    protected boolean _tokenIncomplete;
    private static final int[] sInputCodesUtf8 = CharTypes.getInputCodeUtf8();
    private static final int[] sInputCodesLatin1 = CharTypes.getInputCodeLatin1();

    @Override // com.fasterxml.jackson.core.JsonParser
    public Object getEmbeddedObject() throws IOException, JsonParseException {
        return null;
    }

    public UTF8StreamJsonParser(IOContext iOContext, int i, InputStream inputStream, ObjectCodec objectCodec, BytesToNameCanonicalizer bytesToNameCanonicalizer, byte[] bArr, int i2, int i3, boolean z) {
        super(iOContext, i);
        this._quadBuffer = new int[16];
        this._tokenIncomplete = false;
        this._inputStream = inputStream;
        this._objectCodec = objectCodec;
        this._symbols = bytesToNameCanonicalizer;
        this._inputBuffer = bArr;
        this._inputPtr = i2;
        this._inputEnd = i3;
        this._bufferRecyclable = z;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public ObjectCodec getCodec() {
        return this._objectCodec;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public void setCodec(ObjectCodec objectCodec) {
        this._objectCodec = objectCodec;
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.core.Versioned
    public Version version() {
        return CoreVersion.instance.version();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public int releaseBuffered(OutputStream outputStream) throws IOException {
        int i = this._inputEnd - this._inputPtr;
        if (i < 1) {
            return 0;
        }
        outputStream.write(this._inputBuffer, this._inputPtr, i);
        return i;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public Object getInputSource() {
        return this._inputStream;
    }

    @Override // com.fasterxml.jackson.core.base.ParserBase
    protected boolean loadMore() throws IOException {
        this._currInputProcessed += this._inputEnd;
        this._currInputRowStart -= this._inputEnd;
        InputStream inputStream = this._inputStream;
        if (inputStream != null) {
            byte[] bArr = this._inputBuffer;
            int read = inputStream.read(bArr, 0, bArr.length);
            if (read > 0) {
                this._inputPtr = 0;
                this._inputEnd = read;
                return true;
            }
            _closeInput();
            if (read == 0) {
                throw new IOException("InputStream.read() returned 0 characters when trying to read " + this._inputBuffer.length + " bytes");
            }
        }
        return false;
    }

    protected boolean _loadToHaveAtLeast(int i) throws IOException {
        if (this._inputStream == null) {
            return false;
        }
        int i2 = this._inputEnd - this._inputPtr;
        if (i2 > 0 && this._inputPtr > 0) {
            this._currInputProcessed += this._inputPtr;
            this._currInputRowStart -= this._inputPtr;
            System.arraycopy(this._inputBuffer, this._inputPtr, this._inputBuffer, 0, i2);
            this._inputEnd = i2;
        } else {
            this._inputEnd = 0;
        }
        this._inputPtr = 0;
        while (this._inputEnd < i) {
            int read = this._inputStream.read(this._inputBuffer, this._inputEnd, this._inputBuffer.length - this._inputEnd);
            if (read < 1) {
                _closeInput();
                if (read != 0) {
                    return false;
                }
                throw new IOException("InputStream.read() returned 0 characters when trying to read " + i2 + " bytes");
            }
            this._inputEnd += read;
        }
        return true;
    }

    @Override // com.fasterxml.jackson.core.base.ParserBase
    protected void _closeInput() throws IOException {
        if (this._inputStream != null) {
            if (this._ioContext.isResourceManaged() || isEnabled(JsonParser.Feature.AUTO_CLOSE_SOURCE)) {
                this._inputStream.close();
            }
            this._inputStream = null;
        }
    }

    @Override // com.fasterxml.jackson.core.base.ParserBase
    protected void _releaseBuffers() throws IOException {
        byte[] bArr;
        super._releaseBuffers();
        if (!this._bufferRecyclable || (bArr = this._inputBuffer) == null) {
            return;
        }
        this._inputBuffer = null;
        this._ioContext.releaseReadIOBuffer(bArr);
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public String getText() throws IOException, JsonParseException {
        if (this._currToken == JsonToken.VALUE_STRING) {
            if (this._tokenIncomplete) {
                this._tokenIncomplete = false;
                _finishString();
            }
            return this._textBuffer.contentsAsString();
        }
        return _getText2(this._currToken);
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public String getValueAsString() throws IOException, JsonParseException {
        if (this._currToken == JsonToken.VALUE_STRING) {
            if (this._tokenIncomplete) {
                this._tokenIncomplete = false;
                _finishString();
            }
            return this._textBuffer.contentsAsString();
        }
        return super.getValueAsString(null);
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public String getValueAsString(String str) throws IOException, JsonParseException {
        if (this._currToken == JsonToken.VALUE_STRING) {
            if (this._tokenIncomplete) {
                this._tokenIncomplete = false;
                _finishString();
            }
            return this._textBuffer.contentsAsString();
        }
        return super.getValueAsString(str);
    }

    /* renamed from: com.fasterxml.jackson.core.json.UTF8StreamJsonParser$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$fasterxml$jackson$core$JsonToken;

        static {
            int[] iArr = new int[JsonToken.values().length];
            $SwitchMap$com$fasterxml$jackson$core$JsonToken = iArr;
            try {
                iArr[JsonToken.FIELD_NAME.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_STRING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_NUMBER_INT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_NUMBER_FLOAT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_TRUE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_FALSE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    protected String _getText2(JsonToken jsonToken) {
        if (jsonToken == null) {
            return null;
        }
        int i = AnonymousClass1.$SwitchMap$com$fasterxml$jackson$core$JsonToken[jsonToken.ordinal()];
        if (i == 1) {
            return this._parsingContext.getCurrentName();
        }
        if (i == 2 || i == 3 || i == 4) {
            return this._textBuffer.contentsAsString();
        }
        return jsonToken.asString();
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public char[] getTextCharacters() throws IOException, JsonParseException {
        if (this._currToken == null) {
            return null;
        }
        int i = AnonymousClass1.$SwitchMap$com$fasterxml$jackson$core$JsonToken[this._currToken.ordinal()];
        if (i == 1) {
            if (!this._nameCopied) {
                String currentName = this._parsingContext.getCurrentName();
                int length = currentName.length();
                if (this._nameCopyBuffer == null) {
                    this._nameCopyBuffer = this._ioContext.allocNameCopyBuffer(length);
                } else if (this._nameCopyBuffer.length < length) {
                    this._nameCopyBuffer = new char[length];
                }
                currentName.getChars(0, length, this._nameCopyBuffer, 0);
                this._nameCopied = true;
            }
            return this._nameCopyBuffer;
        }
        if (i != 2) {
            if (i != 3 && i != 4) {
                return this._currToken.asCharArray();
            }
        } else if (this._tokenIncomplete) {
            this._tokenIncomplete = false;
            _finishString();
        }
        return this._textBuffer.getTextBuffer();
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public int getTextLength() throws IOException, JsonParseException {
        if (this._currToken == null) {
            return 0;
        }
        int i = AnonymousClass1.$SwitchMap$com$fasterxml$jackson$core$JsonToken[this._currToken.ordinal()];
        if (i == 1) {
            return this._parsingContext.getCurrentName().length();
        }
        if (i != 2) {
            if (i != 3 && i != 4) {
                return this._currToken.asCharArray().length;
            }
        } else if (this._tokenIncomplete) {
            this._tokenIncomplete = false;
            _finishString();
        }
        return this._textBuffer.size();
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x0016, code lost:
    
        if (r0 != 4) goto L16;
     */
    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int getTextOffset() throws java.io.IOException, com.fasterxml.jackson.core.JsonParseException {
        /*
            r3 = this;
            com.fasterxml.jackson.core.JsonToken r0 = r3._currToken
            r1 = 0
            if (r0 == 0) goto L29
            int[] r0 = com.fasterxml.jackson.core.json.UTF8StreamJsonParser.AnonymousClass1.$SwitchMap$com$fasterxml$jackson$core$JsonToken
            com.fasterxml.jackson.core.JsonToken r2 = r3._currToken
            int r2 = r2.ordinal()
            r0 = r0[r2]
            r2 = 2
            if (r0 == r2) goto L19
            r2 = 3
            if (r0 == r2) goto L22
            r2 = 4
            if (r0 == r2) goto L22
            goto L29
        L19:
            boolean r0 = r3._tokenIncomplete
            if (r0 == 0) goto L22
            r3._tokenIncomplete = r1
            r3._finishString()
        L22:
            com.fasterxml.jackson.core.util.TextBuffer r0 = r3._textBuffer
            int r0 = r0.getTextOffset()
            return r0
        L29:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.UTF8StreamJsonParser.getTextOffset():int");
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public byte[] getBinaryValue(Base64Variant base64Variant) throws IOException, JsonParseException {
        if (this._currToken != JsonToken.VALUE_STRING && (this._currToken != JsonToken.VALUE_EMBEDDED_OBJECT || this._binaryValue == null)) {
            _reportError("Current token (" + this._currToken + ") not VALUE_STRING or VALUE_EMBEDDED_OBJECT, can not access as binary");
        }
        if (this._tokenIncomplete) {
            try {
                this._binaryValue = _decodeBase64(base64Variant);
                this._tokenIncomplete = false;
            } catch (IllegalArgumentException e) {
                throw _constructError("Failed to decode VALUE_STRING as base64 (" + base64Variant + "): " + e.getMessage());
            }
        } else if (this._binaryValue == null) {
            ByteArrayBuilder _getByteArrayBuilder = _getByteArrayBuilder();
            _decodeBase64(getText(), _getByteArrayBuilder, base64Variant);
            this._binaryValue = _getByteArrayBuilder.toByteArray();
        }
        return this._binaryValue;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public int readBinaryValue(Base64Variant base64Variant, OutputStream outputStream) throws IOException, JsonParseException {
        if (!this._tokenIncomplete || this._currToken != JsonToken.VALUE_STRING) {
            byte[] binaryValue = getBinaryValue(base64Variant);
            outputStream.write(binaryValue);
            return binaryValue.length;
        }
        byte[] allocBase64Buffer = this._ioContext.allocBase64Buffer();
        try {
            return _readBinary(base64Variant, outputStream, allocBase64Buffer);
        } finally {
            this._ioContext.releaseBase64Buffer(allocBase64Buffer);
        }
    }

    protected int _readBinary(Base64Variant base64Variant, OutputStream outputStream, byte[] bArr) throws IOException, JsonParseException {
        int length = bArr.length - 3;
        int i = 0;
        int i2 = 0;
        while (true) {
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            byte[] bArr2 = this._inputBuffer;
            int i3 = this._inputPtr;
            this._inputPtr = i3 + 1;
            int i4 = bArr2[i3] & 255;
            if (i4 > 32) {
                int decodeBase64Char = base64Variant.decodeBase64Char(i4);
                if (decodeBase64Char < 0) {
                    if (i4 == 34) {
                        break;
                    }
                    decodeBase64Char = _decodeBase64Escape(base64Variant, i4, 0);
                    if (decodeBase64Char < 0) {
                        continue;
                    }
                }
                if (i > length) {
                    i2 += i;
                    outputStream.write(bArr, 0, i);
                    i = 0;
                }
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                byte[] bArr3 = this._inputBuffer;
                int i5 = this._inputPtr;
                this._inputPtr = i5 + 1;
                int i6 = bArr3[i5] & 255;
                int decodeBase64Char2 = base64Variant.decodeBase64Char(i6);
                if (decodeBase64Char2 < 0) {
                    decodeBase64Char2 = _decodeBase64Escape(base64Variant, i6, 1);
                }
                int i7 = (decodeBase64Char << 6) | decodeBase64Char2;
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                byte[] bArr4 = this._inputBuffer;
                int i8 = this._inputPtr;
                this._inputPtr = i8 + 1;
                int i9 = bArr4[i8] & 255;
                int decodeBase64Char3 = base64Variant.decodeBase64Char(i9);
                if (decodeBase64Char3 < 0) {
                    if (decodeBase64Char3 != -2) {
                        if (i9 == 34 && !base64Variant.usesPadding()) {
                            bArr[i] = (byte) (i7 >> 4);
                            i++;
                            break;
                        }
                        decodeBase64Char3 = _decodeBase64Escape(base64Variant, i9, 2);
                    }
                    if (decodeBase64Char3 == -2) {
                        if (this._inputPtr >= this._inputEnd) {
                            loadMoreGuaranteed();
                        }
                        byte[] bArr5 = this._inputBuffer;
                        int i10 = this._inputPtr;
                        this._inputPtr = i10 + 1;
                        int i11 = bArr5[i10] & 255;
                        if (!base64Variant.usesPaddingChar(i11)) {
                            throw reportInvalidBase64Char(base64Variant, i11, 3, "expected padding character '" + base64Variant.getPaddingChar() + "'");
                        }
                        bArr[i] = (byte) (i7 >> 4);
                        i++;
                    }
                }
                int i12 = (i7 << 6) | decodeBase64Char3;
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                byte[] bArr6 = this._inputBuffer;
                int i13 = this._inputPtr;
                this._inputPtr = i13 + 1;
                int i14 = bArr6[i13] & 255;
                int decodeBase64Char4 = base64Variant.decodeBase64Char(i14);
                if (decodeBase64Char4 < 0) {
                    if (decodeBase64Char4 != -2) {
                        if (i14 == 34 && !base64Variant.usesPadding()) {
                            int i15 = i + 1;
                            bArr[i] = (byte) (i12 >> 10);
                            i += 2;
                            bArr[i15] = (byte) (i12 >> 2);
                            break;
                        }
                        decodeBase64Char4 = _decodeBase64Escape(base64Variant, i14, 3);
                    }
                    if (decodeBase64Char4 == -2) {
                        int i16 = i + 1;
                        bArr[i] = (byte) (i12 >> 10);
                        i += 2;
                        bArr[i16] = (byte) (i12 >> 2);
                    }
                }
                int i17 = (i12 << 6) | decodeBase64Char4;
                bArr[i] = (byte) (i17 >> 16);
                int i18 = i + 2;
                bArr[i + 1] = (byte) (i17 >> 8);
                i += 3;
                bArr[i18] = (byte) i17;
            }
        }
        this._tokenIncomplete = false;
        if (i <= 0) {
            return i2;
        }
        int i19 = i2 + i;
        outputStream.write(bArr, 0, i);
        return i19;
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public JsonToken nextToken() throws IOException, JsonParseException {
        JsonToken parseNumberText;
        this._numTypesValid = 0;
        if (this._currToken == JsonToken.FIELD_NAME) {
            return _nextAfterName();
        }
        if (this._tokenIncomplete) {
            _skipString();
        }
        int _skipWSOrEnd = _skipWSOrEnd();
        if (_skipWSOrEnd < 0) {
            close();
            this._currToken = null;
            return null;
        }
        this._tokenInputTotal = (this._currInputProcessed + this._inputPtr) - 1;
        this._tokenInputRow = this._currInputRow;
        this._tokenInputCol = (this._inputPtr - this._currInputRowStart) - 1;
        this._binaryValue = null;
        if (_skipWSOrEnd == 93) {
            if (!this._parsingContext.inArray()) {
                _reportMismatchedEndMarker(_skipWSOrEnd, '}');
            }
            this._parsingContext = this._parsingContext.getParent();
            JsonToken jsonToken = JsonToken.END_ARRAY;
            this._currToken = jsonToken;
            return jsonToken;
        }
        if (_skipWSOrEnd == 125) {
            if (!this._parsingContext.inObject()) {
                _reportMismatchedEndMarker(_skipWSOrEnd, ']');
            }
            this._parsingContext = this._parsingContext.getParent();
            JsonToken jsonToken2 = JsonToken.END_OBJECT;
            this._currToken = jsonToken2;
            return jsonToken2;
        }
        if (this._parsingContext.expectComma()) {
            if (_skipWSOrEnd != 44) {
                _reportUnexpectedChar(_skipWSOrEnd, "was expecting comma to separate " + this._parsingContext.getTypeDesc() + " entries");
            }
            _skipWSOrEnd = _skipWS();
        }
        if (!this._parsingContext.inObject()) {
            return _nextTokenNotInObject(_skipWSOrEnd);
        }
        this._parsingContext.setCurrentName(_parseFieldName(_skipWSOrEnd).getName());
        this._currToken = JsonToken.FIELD_NAME;
        int _skipWS = _skipWS();
        if (_skipWS != 58) {
            _reportUnexpectedChar(_skipWS, "was expecting a colon to separate field name and value");
        }
        int _skipWS2 = _skipWS();
        if (_skipWS2 == 34) {
            this._tokenIncomplete = true;
            this._nextToken = JsonToken.VALUE_STRING;
            return this._currToken;
        }
        if (_skipWS2 != 45) {
            if (_skipWS2 == 91) {
                parseNumberText = JsonToken.START_ARRAY;
            } else {
                if (_skipWS2 != 93) {
                    if (_skipWS2 == 102) {
                        _matchToken("false", 1);
                        parseNumberText = JsonToken.VALUE_FALSE;
                    } else if (_skipWS2 != 110) {
                        if (_skipWS2 != 116) {
                            if (_skipWS2 == 123) {
                                parseNumberText = JsonToken.START_OBJECT;
                            } else if (_skipWS2 != 125) {
                                switch (_skipWS2) {
                                    case 48:
                                    case 49:
                                    case 50:
                                    case 51:
                                    case 52:
                                    case 53:
                                    case 54:
                                    case 55:
                                    case 56:
                                    case 57:
                                        break;
                                    default:
                                        parseNumberText = _handleUnexpectedValue(_skipWS2);
                                        break;
                                }
                            }
                        }
                        _matchToken("true", 1);
                        parseNumberText = JsonToken.VALUE_TRUE;
                    } else {
                        _matchToken("null", 1);
                        parseNumberText = JsonToken.VALUE_NULL;
                    }
                }
                _reportUnexpectedChar(_skipWS2, "expected a value");
                _matchToken("true", 1);
                parseNumberText = JsonToken.VALUE_TRUE;
            }
            this._nextToken = parseNumberText;
            return this._currToken;
        }
        parseNumberText = parseNumberText(_skipWS2);
        this._nextToken = parseNumberText;
        return this._currToken;
    }

    private JsonToken _nextTokenNotInObject(int i) throws IOException, JsonParseException {
        if (i == 34) {
            this._tokenIncomplete = true;
            JsonToken jsonToken = JsonToken.VALUE_STRING;
            this._currToken = jsonToken;
            return jsonToken;
        }
        if (i != 45) {
            if (i == 91) {
                this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
                JsonToken jsonToken2 = JsonToken.START_ARRAY;
                this._currToken = jsonToken2;
                return jsonToken2;
            }
            if (i != 93) {
                if (i == 102) {
                    _matchToken("false", 1);
                    JsonToken jsonToken3 = JsonToken.VALUE_FALSE;
                    this._currToken = jsonToken3;
                    return jsonToken3;
                }
                if (i != 110) {
                    if (i != 116) {
                        if (i == 123) {
                            this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
                            JsonToken jsonToken4 = JsonToken.START_OBJECT;
                            this._currToken = jsonToken4;
                            return jsonToken4;
                        }
                        if (i != 125) {
                            switch (i) {
                                case 48:
                                case 49:
                                case 50:
                                case 51:
                                case 52:
                                case 53:
                                case 54:
                                case 55:
                                case 56:
                                case 57:
                                    break;
                                default:
                                    JsonToken _handleUnexpectedValue = _handleUnexpectedValue(i);
                                    this._currToken = _handleUnexpectedValue;
                                    return _handleUnexpectedValue;
                            }
                        }
                    }
                    _matchToken("true", 1);
                    JsonToken jsonToken5 = JsonToken.VALUE_TRUE;
                    this._currToken = jsonToken5;
                    return jsonToken5;
                }
                _matchToken("null", 1);
                JsonToken jsonToken6 = JsonToken.VALUE_NULL;
                this._currToken = jsonToken6;
                return jsonToken6;
            }
            _reportUnexpectedChar(i, "expected a value");
            _matchToken("true", 1);
            JsonToken jsonToken52 = JsonToken.VALUE_TRUE;
            this._currToken = jsonToken52;
            return jsonToken52;
        }
        JsonToken parseNumberText = parseNumberText(i);
        this._currToken = parseNumberText;
        return parseNumberText;
    }

    private JsonToken _nextAfterName() {
        this._nameCopied = false;
        JsonToken jsonToken = this._nextToken;
        this._nextToken = null;
        if (jsonToken == JsonToken.START_ARRAY) {
            this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
        } else if (jsonToken == JsonToken.START_OBJECT) {
            this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
        }
        this._currToken = jsonToken;
        return jsonToken;
    }

    @Override // com.fasterxml.jackson.core.base.ParserBase, com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        super.close();
        this._symbols.release();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public boolean nextFieldName(SerializableString serializableString) throws IOException, JsonParseException {
        this._numTypesValid = 0;
        if (this._currToken == JsonToken.FIELD_NAME) {
            _nextAfterName();
            return false;
        }
        if (this._tokenIncomplete) {
            _skipString();
        }
        int _skipWSOrEnd = _skipWSOrEnd();
        if (_skipWSOrEnd < 0) {
            close();
            this._currToken = null;
            return false;
        }
        this._tokenInputTotal = (this._currInputProcessed + this._inputPtr) - 1;
        this._tokenInputRow = this._currInputRow;
        this._tokenInputCol = (this._inputPtr - this._currInputRowStart) - 1;
        this._binaryValue = null;
        if (_skipWSOrEnd == 93) {
            if (!this._parsingContext.inArray()) {
                _reportMismatchedEndMarker(_skipWSOrEnd, '}');
            }
            this._parsingContext = this._parsingContext.getParent();
            this._currToken = JsonToken.END_ARRAY;
            return false;
        }
        if (_skipWSOrEnd == 125) {
            if (!this._parsingContext.inObject()) {
                _reportMismatchedEndMarker(_skipWSOrEnd, ']');
            }
            this._parsingContext = this._parsingContext.getParent();
            this._currToken = JsonToken.END_OBJECT;
            return false;
        }
        if (this._parsingContext.expectComma()) {
            if (_skipWSOrEnd != 44) {
                _reportUnexpectedChar(_skipWSOrEnd, "was expecting comma to separate " + this._parsingContext.getTypeDesc() + " entries");
            }
            _skipWSOrEnd = _skipWS();
        }
        if (!this._parsingContext.inObject()) {
            _nextTokenNotInObject(_skipWSOrEnd);
            return false;
        }
        if (_skipWSOrEnd == 34) {
            byte[] asQuotedUTF8 = serializableString.asQuotedUTF8();
            int length = asQuotedUTF8.length;
            if (this._inputPtr + length < this._inputEnd) {
                int i = this._inputPtr + length;
                if (this._inputBuffer[i] == 34) {
                    int i2 = this._inputPtr;
                    for (int i3 = 0; i3 != length; i3++) {
                        if (asQuotedUTF8[i3] == this._inputBuffer[i2 + i3]) {
                        }
                    }
                    this._inputPtr = i + 1;
                    this._parsingContext.setCurrentName(serializableString.getValue());
                    this._currToken = JsonToken.FIELD_NAME;
                    _isNextTokenNameYes();
                    return true;
                }
            }
        }
        return _isNextTokenNameMaybe(_skipWSOrEnd, serializableString);
    }

    private void _isNextTokenNameYes() throws IOException, JsonParseException {
        int _skipColon;
        if (this._inputPtr < this._inputEnd && this._inputBuffer[this._inputPtr] == 58) {
            this._inputPtr++;
            byte[] bArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            byte b = bArr[i];
            if (b == 34) {
                this._tokenIncomplete = true;
                this._nextToken = JsonToken.VALUE_STRING;
                return;
            } else {
                if (b == 123) {
                    this._nextToken = JsonToken.START_OBJECT;
                    return;
                }
                if (b == 91) {
                    this._nextToken = JsonToken.START_ARRAY;
                    return;
                }
                _skipColon = b & 255;
                if (_skipColon <= 32 || _skipColon == 47) {
                    this._inputPtr--;
                    _skipColon = _skipWS();
                }
            }
        } else {
            _skipColon = _skipColon();
        }
        if (_skipColon == 34) {
            this._tokenIncomplete = true;
            this._nextToken = JsonToken.VALUE_STRING;
            return;
        }
        if (_skipColon != 45) {
            if (_skipColon == 91) {
                this._nextToken = JsonToken.START_ARRAY;
                return;
            }
            if (_skipColon != 93) {
                if (_skipColon == 102) {
                    _matchToken("false", 1);
                    this._nextToken = JsonToken.VALUE_FALSE;
                    return;
                }
                if (_skipColon != 110) {
                    if (_skipColon != 116) {
                        if (_skipColon == 123) {
                            this._nextToken = JsonToken.START_OBJECT;
                            return;
                        } else if (_skipColon != 125) {
                            switch (_skipColon) {
                                case 48:
                                case 49:
                                case 50:
                                case 51:
                                case 52:
                                case 53:
                                case 54:
                                case 55:
                                case 56:
                                case 57:
                                    break;
                                default:
                                    this._nextToken = _handleUnexpectedValue(_skipColon);
                                    break;
                            }
                            return;
                        }
                    }
                    _matchToken("true", 1);
                    this._nextToken = JsonToken.VALUE_TRUE;
                    return;
                }
                _matchToken("null", 1);
                this._nextToken = JsonToken.VALUE_NULL;
                return;
            }
            _reportUnexpectedChar(_skipColon, "expected a value");
            _matchToken("true", 1);
            this._nextToken = JsonToken.VALUE_TRUE;
            return;
        }
        this._nextToken = parseNumberText(_skipColon);
    }

    private boolean _isNextTokenNameMaybe(int i, SerializableString serializableString) throws IOException, JsonParseException {
        JsonToken parseNumberText;
        String name = _parseFieldName(i).getName();
        this._parsingContext.setCurrentName(name);
        boolean equals = name.equals(serializableString.getValue());
        this._currToken = JsonToken.FIELD_NAME;
        int _skipWS = _skipWS();
        if (_skipWS != 58) {
            _reportUnexpectedChar(_skipWS, "was expecting a colon to separate field name and value");
        }
        int _skipWS2 = _skipWS();
        if (_skipWS2 == 34) {
            this._tokenIncomplete = true;
            this._nextToken = JsonToken.VALUE_STRING;
            return equals;
        }
        if (_skipWS2 != 45) {
            if (_skipWS2 == 91) {
                parseNumberText = JsonToken.START_ARRAY;
            } else {
                if (_skipWS2 != 93) {
                    if (_skipWS2 == 102) {
                        _matchToken("false", 1);
                        parseNumberText = JsonToken.VALUE_FALSE;
                    } else if (_skipWS2 != 110) {
                        if (_skipWS2 != 116) {
                            if (_skipWS2 == 123) {
                                parseNumberText = JsonToken.START_OBJECT;
                            } else if (_skipWS2 != 125) {
                                switch (_skipWS2) {
                                    case 48:
                                    case 49:
                                    case 50:
                                    case 51:
                                    case 52:
                                    case 53:
                                    case 54:
                                    case 55:
                                    case 56:
                                    case 57:
                                        break;
                                    default:
                                        parseNumberText = _handleUnexpectedValue(_skipWS2);
                                        break;
                                }
                            }
                        }
                        _matchToken("true", 1);
                        parseNumberText = JsonToken.VALUE_TRUE;
                    } else {
                        _matchToken("null", 1);
                        parseNumberText = JsonToken.VALUE_NULL;
                    }
                }
                _reportUnexpectedChar(_skipWS2, "expected a value");
                _matchToken("true", 1);
                parseNumberText = JsonToken.VALUE_TRUE;
            }
            this._nextToken = parseNumberText;
            return equals;
        }
        parseNumberText = parseNumberText(_skipWS2);
        this._nextToken = parseNumberText;
        return equals;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public String nextTextValue() throws IOException, JsonParseException {
        if (this._currToken == JsonToken.FIELD_NAME) {
            this._nameCopied = false;
            JsonToken jsonToken = this._nextToken;
            this._nextToken = null;
            this._currToken = jsonToken;
            if (jsonToken == JsonToken.VALUE_STRING) {
                if (this._tokenIncomplete) {
                    this._tokenIncomplete = false;
                    _finishString();
                }
                return this._textBuffer.contentsAsString();
            }
            if (jsonToken == JsonToken.START_ARRAY) {
                this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
            } else if (jsonToken == JsonToken.START_OBJECT) {
                this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
            }
            return null;
        }
        if (nextToken() == JsonToken.VALUE_STRING) {
            return getText();
        }
        return null;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public int nextIntValue(int i) throws IOException, JsonParseException {
        if (this._currToken != JsonToken.FIELD_NAME) {
            return nextToken() == JsonToken.VALUE_NUMBER_INT ? getIntValue() : i;
        }
        this._nameCopied = false;
        JsonToken jsonToken = this._nextToken;
        this._nextToken = null;
        this._currToken = jsonToken;
        if (jsonToken == JsonToken.VALUE_NUMBER_INT) {
            return getIntValue();
        }
        if (jsonToken == JsonToken.START_ARRAY) {
            this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
        } else if (jsonToken == JsonToken.START_OBJECT) {
            this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
        }
        return i;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public long nextLongValue(long j) throws IOException, JsonParseException {
        if (this._currToken != JsonToken.FIELD_NAME) {
            return nextToken() == JsonToken.VALUE_NUMBER_INT ? getLongValue() : j;
        }
        this._nameCopied = false;
        JsonToken jsonToken = this._nextToken;
        this._nextToken = null;
        this._currToken = jsonToken;
        if (jsonToken == JsonToken.VALUE_NUMBER_INT) {
            return getLongValue();
        }
        if (jsonToken == JsonToken.START_ARRAY) {
            this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
        } else if (jsonToken == JsonToken.START_OBJECT) {
            this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
        }
        return j;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public Boolean nextBooleanValue() throws IOException, JsonParseException {
        if (this._currToken == JsonToken.FIELD_NAME) {
            this._nameCopied = false;
            JsonToken jsonToken = this._nextToken;
            this._nextToken = null;
            this._currToken = jsonToken;
            if (jsonToken == JsonToken.VALUE_TRUE) {
                return Boolean.TRUE;
            }
            if (jsonToken == JsonToken.VALUE_FALSE) {
                return Boolean.FALSE;
            }
            if (jsonToken == JsonToken.START_ARRAY) {
                this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
            } else if (jsonToken == JsonToken.START_OBJECT) {
                this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
            }
            return null;
        }
        int i = AnonymousClass1.$SwitchMap$com$fasterxml$jackson$core$JsonToken[nextToken().ordinal()];
        if (i == 5) {
            return Boolean.TRUE;
        }
        if (i != 6) {
            return null;
        }
        return Boolean.FALSE;
    }

    protected JsonToken parseNumberText(int i) throws IOException, JsonParseException {
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int i2 = 0;
        boolean z = i == 45;
        if (z) {
            emptyAndGetCurrentSegment[0] = '-';
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            byte[] bArr = this._inputBuffer;
            int i3 = this._inputPtr;
            this._inputPtr = i3 + 1;
            i = bArr[i3] & 255;
            if (i < 48 || i > 57) {
                return _handleInvalidNumberStart(i, true);
            }
            i2 = 1;
        }
        if (i == 48) {
            i = _verifyNoLeadingZeroes();
        }
        int i4 = i2 + 1;
        emptyAndGetCurrentSegment[i2] = (char) i;
        int length = this._inputPtr + emptyAndGetCurrentSegment.length;
        if (length > this._inputEnd) {
            length = this._inputEnd;
        }
        int i5 = 1;
        while (this._inputPtr < length) {
            byte[] bArr2 = this._inputBuffer;
            int i6 = this._inputPtr;
            this._inputPtr = i6 + 1;
            int i7 = bArr2[i6] & 255;
            if (i7 < 48 || i7 > 57) {
                if (i7 == 46 || i7 == 101 || i7 == 69) {
                    return _parseFloatText(emptyAndGetCurrentSegment, i4, i7, z, i5);
                }
                this._inputPtr--;
                this._textBuffer.setCurrentLength(i4);
                return resetInt(z, i5);
            }
            i5++;
            emptyAndGetCurrentSegment[i4] = (char) i7;
            i4++;
        }
        return _parserNumber2(emptyAndGetCurrentSegment, i4, z, i5);
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0044, code lost:
    
        if (r3 == 46) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0048, code lost:
    
        if (r3 == 101) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x004c, code lost:
    
        if (r3 != 69) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x004f, code lost:
    
        r6._inputPtr--;
        r6._textBuffer.setCurrentLength(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x005e, code lost:
    
        return resetInt(r9, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0065, code lost:
    
        return _parseFloatText(r1, r2, r3, r9, r5);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.fasterxml.jackson.core.JsonToken _parserNumber2(char[] r7, int r8, boolean r9, int r10) throws java.io.IOException, com.fasterxml.jackson.core.JsonParseException {
        /*
            r6 = this;
            r1 = r7
            r2 = r8
            r5 = r10
        L3:
            int r7 = r6._inputPtr
            int r8 = r6._inputEnd
            if (r7 < r8) goto L19
            boolean r7 = r6.loadMore()
            if (r7 != 0) goto L19
            com.fasterxml.jackson.core.util.TextBuffer r7 = r6._textBuffer
            r7.setCurrentLength(r2)
            com.fasterxml.jackson.core.JsonToken r7 = r6.resetInt(r9, r5)
            return r7
        L19:
            byte[] r7 = r6._inputBuffer
            int r8 = r6._inputPtr
            int r10 = r8 + 1
            r6._inputPtr = r10
            r7 = r7[r8]
            r3 = r7 & 255(0xff, float:3.57E-43)
            r7 = 57
            if (r3 > r7) goto L42
            r7 = 48
            if (r3 >= r7) goto L2e
            goto L42
        L2e:
            int r7 = r1.length
            if (r2 < r7) goto L39
            com.fasterxml.jackson.core.util.TextBuffer r7 = r6._textBuffer
            char[] r7 = r7.finishCurrentSegment()
            r2 = 0
            r1 = r7
        L39:
            int r7 = r2 + 1
            char r8 = (char) r3
            r1[r2] = r8
            int r5 = r5 + 1
            r2 = r7
            goto L3
        L42:
            r7 = 46
            if (r3 == r7) goto L5f
            r7 = 101(0x65, float:1.42E-43)
            if (r3 == r7) goto L5f
            r7 = 69
            if (r3 != r7) goto L4f
            goto L5f
        L4f:
            int r7 = r6._inputPtr
            int r7 = r7 + (-1)
            r6._inputPtr = r7
            com.fasterxml.jackson.core.util.TextBuffer r7 = r6._textBuffer
            r7.setCurrentLength(r2)
            com.fasterxml.jackson.core.JsonToken r7 = r6.resetInt(r9, r5)
            return r7
        L5f:
            r0 = r6
            r4 = r9
            com.fasterxml.jackson.core.JsonToken r7 = r0._parseFloatText(r1, r2, r3, r4, r5)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.UTF8StreamJsonParser._parserNumber2(char[], int, boolean, int):com.fasterxml.jackson.core.JsonToken");
    }

    private int _verifyNoLeadingZeroes() throws IOException, JsonParseException {
        int i;
        if ((this._inputPtr >= this._inputEnd && !loadMore()) || (i = this._inputBuffer[this._inputPtr] & 255) < 48 || i > 57) {
            return 48;
        }
        if (!isEnabled(JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS)) {
            reportInvalidNumber("Leading zeroes not allowed");
        }
        this._inputPtr++;
        if (i == 48) {
            do {
                if (this._inputPtr >= this._inputEnd && !loadMore()) {
                    break;
                }
                i = this._inputBuffer[this._inputPtr] & 255;
                if (i < 48 || i > 57) {
                    return 48;
                }
                this._inputPtr++;
            } while (i == 48);
        }
        return i;
    }

    /* JADX WARN: Code restructure failed: missing block: B:69:0x0043, code lost:
    
        r5 = false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.fasterxml.jackson.core.JsonToken _parseFloatText(char[] r10, int r11, int r12, boolean r13, int r14) throws java.io.IOException, com.fasterxml.jackson.core.JsonParseException {
        /*
            Method dump skipped, instructions count: 248
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.UTF8StreamJsonParser._parseFloatText(char[], int, int, boolean, int):com.fasterxml.jackson.core.JsonToken");
    }

    protected Name _parseFieldName(int i) throws IOException, JsonParseException {
        if (i != 34) {
            return _handleUnusualFieldName(i);
        }
        if (this._inputPtr + 9 > this._inputEnd) {
            return slowParseFieldName();
        }
        byte[] bArr = this._inputBuffer;
        int[] iArr = sInputCodesLatin1;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        int i3 = bArr[i2] & 255;
        if (iArr[i3] != 0) {
            if (i3 == 34) {
                return BytesToNameCanonicalizer.getEmptyName();
            }
            return parseFieldName(0, i3, 0);
        }
        int i4 = this._inputPtr;
        this._inputPtr = i4 + 1;
        int i5 = bArr[i4] & 255;
        if (iArr[i5] != 0) {
            if (i5 == 34) {
                return findName(i3, 1);
            }
            return parseFieldName(i3, i5, 1);
        }
        int i6 = (i3 << 8) | i5;
        int i7 = this._inputPtr;
        this._inputPtr = i7 + 1;
        int i8 = bArr[i7] & 255;
        if (iArr[i8] != 0) {
            if (i8 == 34) {
                return findName(i6, 2);
            }
            return parseFieldName(i6, i8, 2);
        }
        int i9 = (i6 << 8) | i8;
        int i10 = this._inputPtr;
        this._inputPtr = i10 + 1;
        int i11 = bArr[i10] & 255;
        if (iArr[i11] != 0) {
            if (i11 == 34) {
                return findName(i9, 3);
            }
            return parseFieldName(i9, i11, 3);
        }
        int i12 = (i9 << 8) | i11;
        int i13 = this._inputPtr;
        this._inputPtr = i13 + 1;
        int i14 = bArr[i13] & 255;
        if (iArr[i14] == 0) {
            this._quad1 = i12;
            return parseMediumFieldName(i14, iArr);
        }
        if (i14 == 34) {
            return findName(i12, 4);
        }
        return parseFieldName(i12, i14, 4);
    }

    protected Name parseMediumFieldName(int i, int[] iArr) throws IOException, JsonParseException {
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        int i3 = bArr[i2] & 255;
        if (iArr[i3] != 0) {
            if (i3 == 34) {
                return findName(this._quad1, i, 1);
            }
            return parseFieldName(this._quad1, i, i3, 1);
        }
        int i4 = (i << 8) | i3;
        byte[] bArr2 = this._inputBuffer;
        int i5 = this._inputPtr;
        this._inputPtr = i5 + 1;
        int i6 = bArr2[i5] & 255;
        if (iArr[i6] != 0) {
            if (i6 == 34) {
                return findName(this._quad1, i4, 2);
            }
            return parseFieldName(this._quad1, i4, i6, 2);
        }
        int i7 = (i4 << 8) | i6;
        byte[] bArr3 = this._inputBuffer;
        int i8 = this._inputPtr;
        this._inputPtr = i8 + 1;
        int i9 = bArr3[i8] & 255;
        if (iArr[i9] != 0) {
            if (i9 == 34) {
                return findName(this._quad1, i7, 3);
            }
            return parseFieldName(this._quad1, i7, i9, 3);
        }
        int i10 = (i7 << 8) | i9;
        byte[] bArr4 = this._inputBuffer;
        int i11 = this._inputPtr;
        this._inputPtr = i11 + 1;
        int i12 = bArr4[i11] & 255;
        if (iArr[i12] != 0) {
            if (i12 == 34) {
                return findName(this._quad1, i10, 4);
            }
            return parseFieldName(this._quad1, i10, i12, 4);
        }
        int[] iArr2 = this._quadBuffer;
        iArr2[0] = this._quad1;
        iArr2[1] = i10;
        return parseLongFieldName(i12);
    }

    protected Name parseLongFieldName(int i) throws IOException, JsonParseException {
        int[] iArr = sInputCodesLatin1;
        int i2 = 2;
        while (this._inputEnd - this._inputPtr >= 4) {
            byte[] bArr = this._inputBuffer;
            int i3 = this._inputPtr;
            this._inputPtr = i3 + 1;
            int i4 = bArr[i3] & 255;
            if (iArr[i4] != 0) {
                if (i4 == 34) {
                    return findName(this._quadBuffer, i2, i, 1);
                }
                return parseEscapedFieldName(this._quadBuffer, i2, i, i4, 1);
            }
            int i5 = (i << 8) | i4;
            byte[] bArr2 = this._inputBuffer;
            int i6 = this._inputPtr;
            this._inputPtr = i6 + 1;
            int i7 = bArr2[i6] & 255;
            if (iArr[i7] != 0) {
                if (i7 == 34) {
                    return findName(this._quadBuffer, i2, i5, 2);
                }
                return parseEscapedFieldName(this._quadBuffer, i2, i5, i7, 2);
            }
            int i8 = (i5 << 8) | i7;
            byte[] bArr3 = this._inputBuffer;
            int i9 = this._inputPtr;
            this._inputPtr = i9 + 1;
            int i10 = bArr3[i9] & 255;
            if (iArr[i10] != 0) {
                if (i10 == 34) {
                    return findName(this._quadBuffer, i2, i8, 3);
                }
                return parseEscapedFieldName(this._quadBuffer, i2, i8, i10, 3);
            }
            int i11 = (i8 << 8) | i10;
            byte[] bArr4 = this._inputBuffer;
            int i12 = this._inputPtr;
            this._inputPtr = i12 + 1;
            int i13 = bArr4[i12] & 255;
            if (iArr[i13] != 0) {
                if (i13 == 34) {
                    return findName(this._quadBuffer, i2, i11, 4);
                }
                return parseEscapedFieldName(this._quadBuffer, i2, i11, i13, 4);
            }
            int[] iArr2 = this._quadBuffer;
            if (i2 >= iArr2.length) {
                this._quadBuffer = growArrayBy(iArr2, i2);
            }
            this._quadBuffer[i2] = i11;
            i2++;
            i = i13;
        }
        return parseEscapedFieldName(this._quadBuffer, i2, 0, i, 0);
    }

    protected Name slowParseFieldName() throws IOException, JsonParseException {
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            _reportInvalidEOF(": was expecting closing '\"' for name");
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        int i2 = bArr[i] & 255;
        if (i2 == 34) {
            return BytesToNameCanonicalizer.getEmptyName();
        }
        return parseEscapedFieldName(this._quadBuffer, 0, 0, i2, 0);
    }

    private Name parseFieldName(int i, int i2, int i3) throws IOException, JsonParseException {
        return parseEscapedFieldName(this._quadBuffer, 0, i, i2, i3);
    }

    private Name parseFieldName(int i, int i2, int i3, int i4) throws IOException, JsonParseException {
        int[] iArr = this._quadBuffer;
        iArr[0] = i;
        return parseEscapedFieldName(iArr, 1, i2, i3, i4);
    }

    protected Name parseEscapedFieldName(int[] iArr, int i, int i2, int i3, int i4) throws IOException, JsonParseException {
        int[] iArr2 = sInputCodesLatin1;
        while (true) {
            if (iArr2[i3] != 0) {
                if (i3 == 34) {
                    break;
                }
                if (i3 != 92) {
                    _throwUnquotedSpace(i3, AppMeasurementSdk.ConditionalUserProperty.NAME);
                } else {
                    i3 = _decodeEscaped();
                }
                if (i3 > 127) {
                    int i5 = 0;
                    if (i4 >= 4) {
                        if (i >= iArr.length) {
                            iArr = growArrayBy(iArr, iArr.length);
                            this._quadBuffer = iArr;
                        }
                        iArr[i] = i2;
                        i++;
                        i2 = 0;
                        i4 = 0;
                    }
                    if (i3 < 2048) {
                        i2 = (i2 << 8) | (i3 >> 6) | 192;
                        i4++;
                    } else {
                        int i6 = (i2 << 8) | (i3 >> 12) | 224;
                        int i7 = i4 + 1;
                        if (i7 >= 4) {
                            if (i >= iArr.length) {
                                iArr = growArrayBy(iArr, iArr.length);
                                this._quadBuffer = iArr;
                            }
                            iArr[i] = i6;
                            i++;
                            i7 = 0;
                        } else {
                            i5 = i6;
                        }
                        i2 = (i5 << 8) | ((i3 >> 6) & 63) | 128;
                        i4 = i7 + 1;
                    }
                    i3 = (i3 & 63) | 128;
                }
            }
            if (i4 < 4) {
                i4++;
                i2 = (i2 << 8) | i3;
            } else {
                if (i >= iArr.length) {
                    iArr = growArrayBy(iArr, iArr.length);
                    this._quadBuffer = iArr;
                }
                iArr[i] = i2;
                i2 = i3;
                i++;
                i4 = 1;
            }
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                _reportInvalidEOF(" in field name");
            }
            byte[] bArr = this._inputBuffer;
            int i8 = this._inputPtr;
            this._inputPtr = i8 + 1;
            i3 = bArr[i8] & 255;
        }
        if (i4 > 0) {
            if (i >= iArr.length) {
                iArr = growArrayBy(iArr, iArr.length);
                this._quadBuffer = iArr;
            }
            iArr[i] = i2;
            i++;
        }
        Name findName = this._symbols.findName(iArr, i);
        return findName == null ? addName(iArr, i, i4) : findName;
    }

    protected Name _handleUnusualFieldName(int i) throws IOException, JsonParseException {
        if (i == 39 && isEnabled(JsonParser.Feature.ALLOW_SINGLE_QUOTES)) {
            return _parseApostropheFieldName();
        }
        if (!isEnabled(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES)) {
            _reportUnexpectedChar(i, "was expecting double-quote to start field name");
        }
        int[] inputCodeUtf8JsNames = CharTypes.getInputCodeUtf8JsNames();
        if (inputCodeUtf8JsNames[i] != 0) {
            _reportUnexpectedChar(i, "was expecting either valid name character (for unquoted name) or double-quote (for quoted) to start field name");
        }
        int[] iArr = this._quadBuffer;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            if (i2 < 4) {
                i2++;
                i4 = i | (i4 << 8);
            } else {
                if (i3 >= iArr.length) {
                    iArr = growArrayBy(iArr, iArr.length);
                    this._quadBuffer = iArr;
                }
                iArr[i3] = i4;
                i4 = i;
                i3++;
                i2 = 1;
            }
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                _reportInvalidEOF(" in field name");
            }
            i = this._inputBuffer[this._inputPtr] & 255;
            if (inputCodeUtf8JsNames[i] != 0) {
                break;
            }
            this._inputPtr++;
        }
        if (i2 > 0) {
            if (i3 >= iArr.length) {
                int[] growArrayBy = growArrayBy(iArr, iArr.length);
                this._quadBuffer = growArrayBy;
                iArr = growArrayBy;
            }
            iArr[i3] = i4;
            i3++;
        }
        Name findName = this._symbols.findName(iArr, i3);
        return findName == null ? addName(iArr, i3, i2) : findName;
    }

    protected Name _parseApostropheFieldName() throws IOException, JsonParseException {
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            _reportInvalidEOF(": was expecting closing ''' for name");
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        int i2 = bArr[i] & 255;
        if (i2 == 39) {
            return BytesToNameCanonicalizer.getEmptyName();
        }
        int[] iArr = this._quadBuffer;
        int[] iArr2 = sInputCodesLatin1;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (i2 != 39) {
            if (i2 != 34 && iArr2[i2] != 0) {
                if (i2 != 92) {
                    _throwUnquotedSpace(i2, AppMeasurementSdk.ConditionalUserProperty.NAME);
                } else {
                    i2 = _decodeEscaped();
                }
                if (i2 > 127) {
                    if (i3 >= 4) {
                        if (i4 >= iArr.length) {
                            iArr = growArrayBy(iArr, iArr.length);
                            this._quadBuffer = iArr;
                        }
                        iArr[i4] = i5;
                        i4++;
                        i3 = 0;
                        i5 = 0;
                    }
                    if (i2 < 2048) {
                        i5 = (i5 << 8) | (i2 >> 6) | 192;
                        i3++;
                    } else {
                        int i6 = (i5 << 8) | (i2 >> 12) | 224;
                        int i7 = i3 + 1;
                        if (i7 >= 4) {
                            if (i4 >= iArr.length) {
                                iArr = growArrayBy(iArr, iArr.length);
                                this._quadBuffer = iArr;
                            }
                            iArr[i4] = i6;
                            i4++;
                            i7 = 0;
                            i6 = 0;
                        }
                        i5 = (i6 << 8) | ((i2 >> 6) & 63) | 128;
                        i3 = i7 + 1;
                    }
                    i2 = (i2 & 63) | 128;
                }
            }
            if (i3 < 4) {
                i3++;
                i5 = i2 | (i5 << 8);
            } else {
                if (i4 >= iArr.length) {
                    iArr = growArrayBy(iArr, iArr.length);
                    this._quadBuffer = iArr;
                }
                iArr[i4] = i5;
                i5 = i2;
                i4++;
                i3 = 1;
            }
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                _reportInvalidEOF(" in field name");
            }
            byte[] bArr2 = this._inputBuffer;
            int i8 = this._inputPtr;
            this._inputPtr = i8 + 1;
            i2 = bArr2[i8] & 255;
        }
        if (i3 > 0) {
            if (i4 >= iArr.length) {
                int[] growArrayBy = growArrayBy(iArr, iArr.length);
                this._quadBuffer = growArrayBy;
                iArr = growArrayBy;
            }
            iArr[i4] = i5;
            i4++;
        }
        Name findName = this._symbols.findName(iArr, i4);
        return findName == null ? addName(iArr, i4, i3) : findName;
    }

    private Name findName(int i, int i2) throws JsonParseException {
        Name findName = this._symbols.findName(i);
        if (findName != null) {
            return findName;
        }
        int[] iArr = this._quadBuffer;
        iArr[0] = i;
        return addName(iArr, 1, i2);
    }

    private Name findName(int i, int i2, int i3) throws JsonParseException {
        Name findName = this._symbols.findName(i, i2);
        if (findName != null) {
            return findName;
        }
        int[] iArr = this._quadBuffer;
        iArr[0] = i;
        iArr[1] = i2;
        return addName(iArr, 2, i3);
    }

    private Name findName(int[] iArr, int i, int i2, int i3) throws JsonParseException {
        if (i >= iArr.length) {
            iArr = growArrayBy(iArr, iArr.length);
            this._quadBuffer = iArr;
        }
        int i4 = i + 1;
        iArr[i] = i2;
        Name findName = this._symbols.findName(iArr, i4);
        return findName == null ? addName(iArr, i4, i3) : findName;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00bb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.fasterxml.jackson.core.sym.Name addName(int[] r17, int r18, int r19) throws com.fasterxml.jackson.core.JsonParseException {
        /*
            Method dump skipped, instructions count: 259
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.UTF8StreamJsonParser.addName(int[], int, int):com.fasterxml.jackson.core.sym.Name");
    }

    @Override // com.fasterxml.jackson.core.base.ParserBase
    protected void _finishString() throws IOException, JsonParseException {
        int i = this._inputPtr;
        if (i >= this._inputEnd) {
            loadMoreGuaranteed();
            i = this._inputPtr;
        }
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int[] iArr = sInputCodesUtf8;
        int min = Math.min(this._inputEnd, emptyAndGetCurrentSegment.length + i);
        byte[] bArr = this._inputBuffer;
        int i2 = 0;
        while (true) {
            if (i >= min) {
                break;
            }
            int i3 = bArr[i] & 255;
            if (iArr[i3] == 0) {
                i++;
                emptyAndGetCurrentSegment[i2] = (char) i3;
                i2++;
            } else if (i3 == 34) {
                this._inputPtr = i + 1;
                this._textBuffer.setCurrentLength(i2);
                return;
            }
        }
        this._inputPtr = i;
        _finishString2(emptyAndGetCurrentSegment, i2);
    }

    private void _finishString2(char[] cArr, int i) throws IOException, JsonParseException {
        int[] iArr = sInputCodesUtf8;
        byte[] bArr = this._inputBuffer;
        while (true) {
            int i2 = this._inputPtr;
            if (i2 >= this._inputEnd) {
                loadMoreGuaranteed();
                i2 = this._inputPtr;
            }
            int i3 = 0;
            if (i >= cArr.length) {
                cArr = this._textBuffer.finishCurrentSegment();
                i = 0;
            }
            int min = Math.min(this._inputEnd, (cArr.length - i) + i2);
            while (true) {
                if (i2 < min) {
                    int i4 = i2 + 1;
                    int i5 = bArr[i2] & 255;
                    if (iArr[i5] != 0) {
                        this._inputPtr = i4;
                        if (i5 != 34) {
                            int i6 = iArr[i5];
                            if (i6 == 1) {
                                i5 = _decodeEscaped();
                            } else if (i6 == 2) {
                                i5 = _decodeUtf8_2(i5);
                            } else if (i6 == 3) {
                                i5 = this._inputEnd - this._inputPtr >= 2 ? _decodeUtf8_3fast(i5) : _decodeUtf8_3(i5);
                            } else if (i6 == 4) {
                                int _decodeUtf8_4 = _decodeUtf8_4(i5);
                                int i7 = i + 1;
                                cArr[i] = (char) ((_decodeUtf8_4 >> 10) | 55296);
                                if (i7 >= cArr.length) {
                                    cArr = this._textBuffer.finishCurrentSegment();
                                    i = 0;
                                } else {
                                    i = i7;
                                }
                                i5 = (_decodeUtf8_4 & 1023) | 56320;
                            } else if (i5 < 32) {
                                _throwUnquotedSpace(i5, "string value");
                            } else {
                                _reportInvalidChar(i5);
                            }
                            if (i >= cArr.length) {
                                cArr = this._textBuffer.finishCurrentSegment();
                            } else {
                                i3 = i;
                            }
                            i = i3 + 1;
                            cArr[i3] = (char) i5;
                        } else {
                            this._textBuffer.setCurrentLength(i);
                            return;
                        }
                    } else {
                        cArr[i] = (char) i5;
                        i2 = i4;
                        i++;
                    }
                } else {
                    this._inputPtr = i2;
                    break;
                }
            }
        }
    }

    protected void _skipString() throws IOException, JsonParseException {
        this._tokenIncomplete = false;
        int[] iArr = sInputCodesUtf8;
        byte[] bArr = this._inputBuffer;
        while (true) {
            int i = this._inputPtr;
            int i2 = this._inputEnd;
            if (i >= i2) {
                loadMoreGuaranteed();
                i = this._inputPtr;
                i2 = this._inputEnd;
            }
            while (true) {
                if (i < i2) {
                    int i3 = i + 1;
                    int i4 = bArr[i] & 255;
                    if (iArr[i4] != 0) {
                        this._inputPtr = i3;
                        if (i4 == 34) {
                            return;
                        }
                        int i5 = iArr[i4];
                        if (i5 == 1) {
                            _decodeEscaped();
                        } else if (i5 == 2) {
                            _skipUtf8_2(i4);
                        } else if (i5 == 3) {
                            _skipUtf8_3(i4);
                        } else if (i5 == 4) {
                            _skipUtf8_4(i4);
                        } else if (i4 < 32) {
                            _throwUnquotedSpace(i4, "string value");
                        } else {
                            _reportInvalidChar(i4);
                        }
                    } else {
                        i = i3;
                    }
                } else {
                    this._inputPtr = i;
                    break;
                }
            }
        }
    }

    protected JsonToken _handleUnexpectedValue(int i) throws IOException, JsonParseException {
        if (i != 39) {
            if (i == 43) {
                if (this._inputPtr >= this._inputEnd && !loadMore()) {
                    _reportInvalidEOFInValue();
                }
                byte[] bArr = this._inputBuffer;
                int i2 = this._inputPtr;
                this._inputPtr = i2 + 1;
                return _handleInvalidNumberStart(bArr[i2] & 255, false);
            }
            if (i == 78) {
                _matchToken("NaN", 1);
                if (isEnabled(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
                    return resetAsNaN("NaN", Double.NaN);
                }
                _reportError("Non-standard token 'NaN': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
            }
        } else if (isEnabled(JsonParser.Feature.ALLOW_SINGLE_QUOTES)) {
            return _handleApostropheValue();
        }
        _reportUnexpectedChar(i, "expected a valid value (number, String, array, object, 'true', 'false' or 'null')");
        return null;
    }

    protected JsonToken _handleApostropheValue() throws IOException, JsonParseException {
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int[] iArr = sInputCodesUtf8;
        byte[] bArr = this._inputBuffer;
        int i = 0;
        while (true) {
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            if (i >= emptyAndGetCurrentSegment.length) {
                emptyAndGetCurrentSegment = this._textBuffer.finishCurrentSegment();
                i = 0;
            }
            int i2 = this._inputEnd;
            int length = this._inputPtr + (emptyAndGetCurrentSegment.length - i);
            if (length < i2) {
                i2 = length;
            }
            while (this._inputPtr < i2) {
                int i3 = this._inputPtr;
                this._inputPtr = i3 + 1;
                int i4 = bArr[i3] & 255;
                if (i4 != 39 && iArr[i4] == 0) {
                    emptyAndGetCurrentSegment[i] = (char) i4;
                    i++;
                } else if (i4 != 39) {
                    int i5 = iArr[i4];
                    if (i5 != 1) {
                        if (i5 == 2) {
                            i4 = _decodeUtf8_2(i4);
                        } else if (i5 != 3) {
                            if (i5 == 4) {
                                int _decodeUtf8_4 = _decodeUtf8_4(i4);
                                int i6 = i + 1;
                                emptyAndGetCurrentSegment[i] = (char) ((_decodeUtf8_4 >> 10) | 55296);
                                if (i6 >= emptyAndGetCurrentSegment.length) {
                                    emptyAndGetCurrentSegment = this._textBuffer.finishCurrentSegment();
                                    i = 0;
                                } else {
                                    i = i6;
                                }
                                i4 = 56320 | (_decodeUtf8_4 & 1023);
                            } else {
                                if (i4 < 32) {
                                    _throwUnquotedSpace(i4, "string value");
                                }
                                _reportInvalidChar(i4);
                            }
                        } else if (this._inputEnd - this._inputPtr >= 2) {
                            i4 = _decodeUtf8_3fast(i4);
                        } else {
                            i4 = _decodeUtf8_3(i4);
                        }
                    } else if (i4 != 34) {
                        i4 = _decodeEscaped();
                    }
                    if (i >= emptyAndGetCurrentSegment.length) {
                        emptyAndGetCurrentSegment = this._textBuffer.finishCurrentSegment();
                        i = 0;
                    }
                    emptyAndGetCurrentSegment[i] = (char) i4;
                    i++;
                } else {
                    this._textBuffer.setCurrentLength(i);
                    return JsonToken.VALUE_STRING;
                }
            }
        }
    }

    protected JsonToken _handleInvalidNumberStart(int i, boolean z) throws IOException, JsonParseException {
        if (i == 73) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                _reportInvalidEOFInValue();
            }
            byte[] bArr = this._inputBuffer;
            int i2 = this._inputPtr;
            this._inputPtr = i2 + 1;
            i = bArr[i2];
            if (i == 78) {
                String str = z ? "-INF" : "+INF";
                _matchToken(str, 3);
                if (isEnabled(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
                    return resetAsNaN(str, z ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY);
                }
                _reportError("Non-standard token '" + str + "': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
            } else if (i == 110) {
                String str2 = z ? "-Infinity" : "+Infinity";
                _matchToken(str2, 3);
                if (isEnabled(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
                    return resetAsNaN(str2, z ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY);
                }
                _reportError("Non-standard token '" + str2 + "': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
            }
        }
        reportUnexpectedNumberChar(i, "expected digit (0-9) to follow minus sign, for valid numeric value");
        return null;
    }

    protected void _matchToken(String str, int i) throws IOException, JsonParseException {
        int i2;
        int length = str.length();
        do {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                _reportInvalidEOF(" in a value");
            }
            if (this._inputBuffer[this._inputPtr] != str.charAt(i)) {
                _reportInvalidToken(str.substring(0, i), "'null', 'true', 'false' or NaN");
            }
            this._inputPtr++;
            i++;
        } while (i < length);
        if ((this._inputPtr < this._inputEnd || loadMore()) && (i2 = this._inputBuffer[this._inputPtr] & 255) >= 48 && i2 != 93 && i2 != 125 && Character.isJavaIdentifierPart((char) _decodeCharForError(i2))) {
            this._inputPtr++;
            _reportInvalidToken(str.substring(0, i), "'null', 'true', 'false' or NaN");
        }
    }

    protected void _reportInvalidToken(String str, String str2) throws IOException, JsonParseException {
        StringBuilder sb = new StringBuilder(str);
        while (true) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                break;
            }
            byte[] bArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            char _decodeCharForError = (char) _decodeCharForError(bArr[i]);
            if (!Character.isJavaIdentifierPart(_decodeCharForError)) {
                break;
            } else {
                sb.append(_decodeCharForError);
            }
        }
        _reportError("Unrecognized token '" + sb.toString() + "': was expecting " + str2);
    }

    private int _skipWS() throws IOException, JsonParseException {
        while (true) {
            if (this._inputPtr < this._inputEnd || loadMore()) {
                byte[] bArr = this._inputBuffer;
                int i = this._inputPtr;
                this._inputPtr = i + 1;
                int i2 = bArr[i] & 255;
                if (i2 > 32) {
                    if (i2 != 47) {
                        return i2;
                    }
                    _skipComment();
                } else if (i2 != 32) {
                    if (i2 == 10) {
                        _skipLF();
                    } else if (i2 == 13) {
                        _skipCR();
                    } else if (i2 != 9) {
                        _throwInvalidSpace(i2);
                    }
                }
            } else {
                throw _constructError("Unexpected end-of-input within/between " + this._parsingContext.getTypeDesc() + " entries");
            }
        }
    }

    private int _skipWSOrEnd() throws IOException, JsonParseException {
        while (true) {
            if (this._inputPtr < this._inputEnd || loadMore()) {
                byte[] bArr = this._inputBuffer;
                int i = this._inputPtr;
                this._inputPtr = i + 1;
                int i2 = bArr[i] & 255;
                if (i2 > 32) {
                    if (i2 != 47) {
                        return i2;
                    }
                    _skipComment();
                } else if (i2 != 32) {
                    if (i2 == 10) {
                        _skipLF();
                    } else if (i2 == 13) {
                        _skipCR();
                    } else if (i2 != 9) {
                        _throwInvalidSpace(i2);
                    }
                }
            } else {
                _handleEOF();
                return -1;
            }
        }
    }

    private int _skipColon() throws IOException, JsonParseException {
        int i;
        int i2;
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i3 = this._inputPtr;
        this._inputPtr = i3 + 1;
        byte b = bArr[i3];
        if (b != 58) {
            while (true) {
                i = b & 255;
                if (i != 9) {
                    if (i == 10) {
                        _skipLF();
                    } else if (i == 13) {
                        _skipCR();
                    } else if (i != 32) {
                        if (i != 47) {
                            break;
                        }
                        _skipComment();
                    }
                }
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                byte[] bArr2 = this._inputBuffer;
                int i4 = this._inputPtr;
                this._inputPtr = i4 + 1;
                b = bArr2[i4];
            }
            if (i < 32) {
                _throwInvalidSpace(i);
            }
            if (i != 58) {
                _reportUnexpectedChar(i, "was expecting a colon to separate field name and value");
            }
        } else if (this._inputPtr < this._inputEnd && (i2 = this._inputBuffer[this._inputPtr] & 255) > 32 && i2 != 47) {
            this._inputPtr++;
            return i2;
        }
        while (true) {
            if (this._inputPtr < this._inputEnd || loadMore()) {
                byte[] bArr3 = this._inputBuffer;
                int i5 = this._inputPtr;
                this._inputPtr = i5 + 1;
                int i6 = bArr3[i5] & 255;
                if (i6 > 32) {
                    if (i6 != 47) {
                        return i6;
                    }
                    _skipComment();
                } else if (i6 != 32) {
                    if (i6 == 10) {
                        _skipLF();
                    } else if (i6 == 13) {
                        _skipCR();
                    } else if (i6 != 9) {
                        _throwInvalidSpace(i6);
                    }
                }
            } else {
                throw _constructError("Unexpected end-of-input within/between " + this._parsingContext.getTypeDesc() + " entries");
            }
        }
    }

    private void _skipComment() throws IOException, JsonParseException {
        if (!isEnabled(JsonParser.Feature.ALLOW_COMMENTS)) {
            _reportUnexpectedChar(47, "maybe a (non-standard) comment? (not recognized as one since Feature 'ALLOW_COMMENTS' not enabled for parser)");
        }
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            _reportInvalidEOF(" in a comment");
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        int i2 = bArr[i] & 255;
        if (i2 == 47) {
            _skipCppComment();
        } else if (i2 == 42) {
            _skipCComment();
        } else {
            _reportUnexpectedChar(i2, "was expecting either '*' or '/' for a comment");
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x0045, code lost:
    
        _reportInvalidEOF(" in a comment");
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x004a, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void _skipCComment() throws java.io.IOException, com.fasterxml.jackson.core.JsonParseException {
        /*
            r4 = this;
            int[] r0 = com.fasterxml.jackson.core.io.CharTypes.getInputCodeComment()
        L4:
            int r1 = r4._inputPtr
            int r2 = r4._inputEnd
            if (r1 < r2) goto L10
            boolean r1 = r4.loadMore()
            if (r1 == 0) goto L45
        L10:
            byte[] r1 = r4._inputBuffer
            int r2 = r4._inputPtr
            int r3 = r2 + 1
            r4._inputPtr = r3
            r1 = r1[r2]
            r1 = r1 & 255(0xff, float:3.57E-43)
            r2 = r0[r1]
            if (r2 == 0) goto L4
            r3 = 2
            if (r2 == r3) goto L6c
            r3 = 3
            if (r2 == r3) goto L68
            r3 = 4
            if (r2 == r3) goto L64
            r3 = 10
            if (r2 == r3) goto L60
            r3 = 13
            if (r2 == r3) goto L5c
            r3 = 42
            if (r2 == r3) goto L39
            r4._reportInvalidChar(r1)
            goto L4
        L39:
            int r1 = r4._inputPtr
            int r2 = r4._inputEnd
            if (r1 < r2) goto L4b
            boolean r1 = r4.loadMore()
            if (r1 != 0) goto L4b
        L45:
            java.lang.String r0 = " in a comment"
            r4._reportInvalidEOF(r0)
            return
        L4b:
            byte[] r1 = r4._inputBuffer
            int r2 = r4._inputPtr
            r1 = r1[r2]
            r2 = 47
            if (r1 != r2) goto L4
            int r0 = r4._inputPtr
            int r0 = r0 + 1
            r4._inputPtr = r0
            return
        L5c:
            r4._skipCR()
            goto L4
        L60:
            r4._skipLF()
            goto L4
        L64:
            r4._skipUtf8_4(r1)
            goto L4
        L68:
            r4._skipUtf8_3(r1)
            goto L4
        L6c:
            r4._skipUtf8_2(r1)
            goto L4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.UTF8StreamJsonParser._skipCComment():void");
    }

    private void _skipCppComment() throws IOException, JsonParseException {
        int[] inputCodeComment = CharTypes.getInputCodeComment();
        while (true) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                return;
            }
            byte[] bArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            int i2 = bArr[i] & 255;
            int i3 = inputCodeComment[i2];
            if (i3 != 0) {
                if (i3 == 2) {
                    _skipUtf8_2(i2);
                } else if (i3 == 3) {
                    _skipUtf8_3(i2);
                } else if (i3 == 4) {
                    _skipUtf8_4(i2);
                } else if (i3 == 10) {
                    _skipLF();
                    return;
                } else if (i3 == 13) {
                    _skipCR();
                    return;
                } else if (i3 != 42) {
                    _reportInvalidChar(i2);
                }
            }
        }
    }

    @Override // com.fasterxml.jackson.core.base.ParserBase
    protected char _decodeEscaped() throws IOException, JsonParseException {
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            _reportInvalidEOF(" in character escape sequence");
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        byte b = bArr[i];
        if (b == 34 || b == 47 || b == 92) {
            return (char) b;
        }
        if (b == 98) {
            return '\b';
        }
        if (b == 102) {
            return '\f';
        }
        if (b == 110) {
            return '\n';
        }
        if (b == 114) {
            return '\r';
        }
        if (b == 116) {
            return '\t';
        }
        if (b != 117) {
            return _handleUnrecognizedCharacterEscape((char) _decodeCharForError(b));
        }
        int i2 = 0;
        for (int i3 = 0; i3 < 4; i3++) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                _reportInvalidEOF(" in character escape sequence");
            }
            byte[] bArr2 = this._inputBuffer;
            int i4 = this._inputPtr;
            this._inputPtr = i4 + 1;
            byte b2 = bArr2[i4];
            int charToHex = CharTypes.charToHex(b2);
            if (charToHex < 0) {
                _reportUnexpectedChar(b2, "expected a hex-digit for character escape sequence");
            }
            i2 = (i2 << 4) | charToHex;
        }
        return (char) i2;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:23:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0032  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected int _decodeCharForError(int r7) throws java.io.IOException, com.fasterxml.jackson.core.JsonParseException {
        /*
            r6 = this;
            if (r7 >= 0) goto L64
            r0 = r7 & 224(0xe0, float:3.14E-43)
            r1 = 2
            r2 = 1
            r3 = 192(0xc0, float:2.69E-43)
            if (r0 != r3) goto Le
            r7 = r7 & 31
        Lc:
            r0 = 1
            goto L28
        Le:
            r0 = r7 & 240(0xf0, float:3.36E-43)
            r3 = 224(0xe0, float:3.14E-43)
            if (r0 != r3) goto L18
            r7 = r7 & 15
            r0 = 2
            goto L28
        L18:
            r0 = r7 & 248(0xf8, float:3.48E-43)
            r3 = 240(0xf0, float:3.36E-43)
            if (r0 != r3) goto L22
            r7 = r7 & 7
            r0 = 3
            goto L28
        L22:
            r0 = r7 & 255(0xff, float:3.57E-43)
            r6._reportInvalidInitial(r0)
            goto Lc
        L28:
            int r3 = r6.nextByte()
            r4 = r3 & 192(0xc0, float:2.69E-43)
            r5 = 128(0x80, float:1.8E-43)
            if (r4 == r5) goto L37
            r4 = r3 & 255(0xff, float:3.57E-43)
            r6._reportInvalidOther(r4)
        L37:
            int r7 = r7 << 6
            r3 = r3 & 63
            r7 = r7 | r3
            if (r0 <= r2) goto L64
            int r2 = r6.nextByte()
            r3 = r2 & 192(0xc0, float:2.69E-43)
            if (r3 == r5) goto L4b
            r3 = r2 & 255(0xff, float:3.57E-43)
            r6._reportInvalidOther(r3)
        L4b:
            int r7 = r7 << 6
            r2 = r2 & 63
            r7 = r7 | r2
            if (r0 <= r1) goto L64
            int r0 = r6.nextByte()
            r1 = r0 & 192(0xc0, float:2.69E-43)
            if (r1 == r5) goto L5f
            r1 = r0 & 255(0xff, float:3.57E-43)
            r6._reportInvalidOther(r1)
        L5f:
            int r7 = r7 << 6
            r0 = r0 & 63
            r7 = r7 | r0
        L64:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.UTF8StreamJsonParser._decodeCharForError(int):int");
    }

    private int _decodeUtf8_2(int i) throws IOException, JsonParseException {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        byte b = bArr[i2];
        if ((b & 192) != 128) {
            _reportInvalidOther(b & 255, this._inputPtr);
        }
        return ((i & 31) << 6) | (b & 63);
    }

    private int _decodeUtf8_3(int i) throws IOException, JsonParseException {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        int i2 = i & 15;
        byte[] bArr = this._inputBuffer;
        int i3 = this._inputPtr;
        this._inputPtr = i3 + 1;
        byte b = bArr[i3];
        if ((b & 192) != 128) {
            _reportInvalidOther(b & 255, this._inputPtr);
        }
        int i4 = (i2 << 6) | (b & 63);
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr2 = this._inputBuffer;
        int i5 = this._inputPtr;
        this._inputPtr = i5 + 1;
        byte b2 = bArr2[i5];
        if ((b2 & 192) != 128) {
            _reportInvalidOther(b2 & 255, this._inputPtr);
        }
        return (i4 << 6) | (b2 & 63);
    }

    private int _decodeUtf8_3fast(int i) throws IOException, JsonParseException {
        int i2 = i & 15;
        byte[] bArr = this._inputBuffer;
        int i3 = this._inputPtr;
        this._inputPtr = i3 + 1;
        byte b = bArr[i3];
        if ((b & 192) != 128) {
            _reportInvalidOther(b & 255, this._inputPtr);
        }
        int i4 = (i2 << 6) | (b & 63);
        byte[] bArr2 = this._inputBuffer;
        int i5 = this._inputPtr;
        this._inputPtr = i5 + 1;
        byte b2 = bArr2[i5];
        if ((b2 & 192) != 128) {
            _reportInvalidOther(b2 & 255, this._inputPtr);
        }
        return (i4 << 6) | (b2 & 63);
    }

    private int _decodeUtf8_4(int i) throws IOException, JsonParseException {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        byte b = bArr[i2];
        if ((b & 192) != 128) {
            _reportInvalidOther(b & 255, this._inputPtr);
        }
        int i3 = ((i & 7) << 6) | (b & 63);
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr2 = this._inputBuffer;
        int i4 = this._inputPtr;
        this._inputPtr = i4 + 1;
        byte b2 = bArr2[i4];
        if ((b2 & 192) != 128) {
            _reportInvalidOther(b2 & 255, this._inputPtr);
        }
        int i5 = (i3 << 6) | (b2 & 63);
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr3 = this._inputBuffer;
        int i6 = this._inputPtr;
        this._inputPtr = i6 + 1;
        byte b3 = bArr3[i6];
        if ((b3 & 192) != 128) {
            _reportInvalidOther(b3 & 255, this._inputPtr);
        }
        return ((i5 << 6) | (b3 & 63)) - 65536;
    }

    private void _skipUtf8_2(int i) throws IOException, JsonParseException {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        byte b = bArr[i2];
        if ((b & 192) != 128) {
            _reportInvalidOther(b & 255, this._inputPtr);
        }
    }

    private void _skipUtf8_3(int i) throws IOException, JsonParseException {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        byte b = bArr[i2];
        if ((b & 192) != 128) {
            _reportInvalidOther(b & 255, this._inputPtr);
        }
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr2 = this._inputBuffer;
        int i3 = this._inputPtr;
        this._inputPtr = i3 + 1;
        byte b2 = bArr2[i3];
        if ((b2 & 192) != 128) {
            _reportInvalidOther(b2 & 255, this._inputPtr);
        }
    }

    private void _skipUtf8_4(int i) throws IOException, JsonParseException {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        byte b = bArr[i2];
        if ((b & 192) != 128) {
            _reportInvalidOther(b & 255, this._inputPtr);
        }
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr2 = this._inputBuffer;
        int i3 = this._inputPtr;
        this._inputPtr = i3 + 1;
        byte b2 = bArr2[i3];
        if ((b2 & 192) != 128) {
            _reportInvalidOther(b2 & 255, this._inputPtr);
        }
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr3 = this._inputBuffer;
        int i4 = this._inputPtr;
        this._inputPtr = i4 + 1;
        byte b3 = bArr3[i4];
        if ((b3 & 192) != 128) {
            _reportInvalidOther(b3 & 255, this._inputPtr);
        }
    }

    protected void _skipCR() throws IOException {
        if ((this._inputPtr < this._inputEnd || loadMore()) && this._inputBuffer[this._inputPtr] == 10) {
            this._inputPtr++;
        }
        this._currInputRow++;
        this._currInputRowStart = this._inputPtr;
    }

    protected void _skipLF() throws IOException {
        this._currInputRow++;
        this._currInputRowStart = this._inputPtr;
    }

    private int nextByte() throws IOException, JsonParseException {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        return bArr[i] & 255;
    }

    protected void _reportInvalidChar(int i) throws JsonParseException {
        if (i < 32) {
            _throwInvalidSpace(i);
        }
        _reportInvalidInitial(i);
    }

    protected void _reportInvalidInitial(int i) throws JsonParseException {
        _reportError("Invalid UTF-8 start byte 0x" + Integer.toHexString(i));
    }

    protected void _reportInvalidOther(int i) throws JsonParseException {
        _reportError("Invalid UTF-8 middle byte 0x" + Integer.toHexString(i));
    }

    protected void _reportInvalidOther(int i, int i2) throws JsonParseException {
        this._inputPtr = i2;
        _reportInvalidOther(i);
    }

    public static int[] growArrayBy(int[] iArr, int i) {
        if (iArr == null) {
            return new int[i];
        }
        int length = iArr.length;
        int[] iArr2 = new int[i + length];
        System.arraycopy(iArr, 0, iArr2, 0, length);
        return iArr2;
    }

    protected byte[] _decodeBase64(Base64Variant base64Variant) throws IOException, JsonParseException {
        ByteArrayBuilder _getByteArrayBuilder = _getByteArrayBuilder();
        while (true) {
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            byte[] bArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            int i2 = bArr[i] & 255;
            if (i2 > 32) {
                int decodeBase64Char = base64Variant.decodeBase64Char(i2);
                if (decodeBase64Char < 0) {
                    if (i2 == 34) {
                        return _getByteArrayBuilder.toByteArray();
                    }
                    decodeBase64Char = _decodeBase64Escape(base64Variant, i2, 0);
                    if (decodeBase64Char < 0) {
                        continue;
                    }
                }
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                byte[] bArr2 = this._inputBuffer;
                int i3 = this._inputPtr;
                this._inputPtr = i3 + 1;
                int i4 = bArr2[i3] & 255;
                int decodeBase64Char2 = base64Variant.decodeBase64Char(i4);
                if (decodeBase64Char2 < 0) {
                    decodeBase64Char2 = _decodeBase64Escape(base64Variant, i4, 1);
                }
                int i5 = (decodeBase64Char << 6) | decodeBase64Char2;
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                byte[] bArr3 = this._inputBuffer;
                int i6 = this._inputPtr;
                this._inputPtr = i6 + 1;
                int i7 = bArr3[i6] & 255;
                int decodeBase64Char3 = base64Variant.decodeBase64Char(i7);
                if (decodeBase64Char3 < 0) {
                    if (decodeBase64Char3 != -2) {
                        if (i7 == 34 && !base64Variant.usesPadding()) {
                            _getByteArrayBuilder.append(i5 >> 4);
                            return _getByteArrayBuilder.toByteArray();
                        }
                        decodeBase64Char3 = _decodeBase64Escape(base64Variant, i7, 2);
                    }
                    if (decodeBase64Char3 == -2) {
                        if (this._inputPtr >= this._inputEnd) {
                            loadMoreGuaranteed();
                        }
                        byte[] bArr4 = this._inputBuffer;
                        int i8 = this._inputPtr;
                        this._inputPtr = i8 + 1;
                        int i9 = bArr4[i8] & 255;
                        if (!base64Variant.usesPaddingChar(i9)) {
                            throw reportInvalidBase64Char(base64Variant, i9, 3, "expected padding character '" + base64Variant.getPaddingChar() + "'");
                        }
                        _getByteArrayBuilder.append(i5 >> 4);
                    }
                }
                int i10 = (i5 << 6) | decodeBase64Char3;
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                byte[] bArr5 = this._inputBuffer;
                int i11 = this._inputPtr;
                this._inputPtr = i11 + 1;
                int i12 = bArr5[i11] & 255;
                int decodeBase64Char4 = base64Variant.decodeBase64Char(i12);
                if (decodeBase64Char4 < 0) {
                    if (decodeBase64Char4 != -2) {
                        if (i12 == 34 && !base64Variant.usesPadding()) {
                            _getByteArrayBuilder.appendTwoBytes(i10 >> 2);
                            return _getByteArrayBuilder.toByteArray();
                        }
                        decodeBase64Char4 = _decodeBase64Escape(base64Variant, i12, 3);
                    }
                    if (decodeBase64Char4 == -2) {
                        _getByteArrayBuilder.appendTwoBytes(i10 >> 2);
                    }
                }
                _getByteArrayBuilder.appendThreeBytes((i10 << 6) | decodeBase64Char4);
            }
        }
    }
}
