package com.fasterxml.jackson.core;

/* loaded from: classes.dex */
public class JsonGenerationException extends JsonProcessingException {
    private static final long serialVersionUID = 123;

    public JsonGenerationException(Throwable th) {
        super(th);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JsonGenerationException(String str) {
        super(str, (JsonLocation) null);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JsonGenerationException(String str, Throwable th) {
        super(str, null, th);
    }
}
