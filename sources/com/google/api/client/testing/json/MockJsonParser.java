package com.google.api.client.testing.json;

import com.github.mikephil.charting.utils.Utils;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonParser;
import com.google.api.client.json.JsonToken;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

/* loaded from: classes2.dex */
public class MockJsonParser extends JsonParser {
    private final JsonFactory factory;
    private boolean isClosed;

    @Override // com.google.api.client.json.JsonParser
    public BigInteger getBigIntegerValue() throws IOException {
        return null;
    }

    @Override // com.google.api.client.json.JsonParser
    public byte getByteValue() throws IOException {
        return (byte) 0;
    }

    @Override // com.google.api.client.json.JsonParser
    public String getCurrentName() throws IOException {
        return null;
    }

    @Override // com.google.api.client.json.JsonParser
    public JsonToken getCurrentToken() {
        return null;
    }

    @Override // com.google.api.client.json.JsonParser
    public BigDecimal getDecimalValue() throws IOException {
        return null;
    }

    @Override // com.google.api.client.json.JsonParser
    public double getDoubleValue() throws IOException {
        return Utils.DOUBLE_EPSILON;
    }

    @Override // com.google.api.client.json.JsonParser
    public float getFloatValue() throws IOException {
        return 0.0f;
    }

    @Override // com.google.api.client.json.JsonParser
    public int getIntValue() throws IOException {
        return 0;
    }

    @Override // com.google.api.client.json.JsonParser
    public long getLongValue() throws IOException {
        return 0L;
    }

    @Override // com.google.api.client.json.JsonParser
    public short getShortValue() throws IOException {
        return (short) 0;
    }

    @Override // com.google.api.client.json.JsonParser
    public String getText() throws IOException {
        return null;
    }

    @Override // com.google.api.client.json.JsonParser
    public JsonToken nextToken() throws IOException {
        return null;
    }

    @Override // com.google.api.client.json.JsonParser
    public JsonParser skipChildren() throws IOException {
        return null;
    }

    MockJsonParser(JsonFactory jsonFactory) {
        this.factory = jsonFactory;
    }

    @Override // com.google.api.client.json.JsonParser
    public JsonFactory getFactory() {
        return this.factory;
    }

    @Override // com.google.api.client.json.JsonParser
    public void close() throws IOException {
        this.isClosed = true;
    }

    public boolean isClosed() {
        return this.isClosed;
    }
}
