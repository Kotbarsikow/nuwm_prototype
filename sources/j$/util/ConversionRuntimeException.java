package j$.util;

import java.io.Serializable;

/* loaded from: classes4.dex */
public final class ConversionRuntimeException extends RuntimeException {
    public static void exception(String str, Serializable serializable) {
        throw new ConversionRuntimeException("Unsupported " + str + " :" + serializable);
    }
}
