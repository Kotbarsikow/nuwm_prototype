package java.io;

import j$.util.Objects;

/* loaded from: classes4.dex */
public class UncheckedIOException extends RuntimeException {
    public UncheckedIOException(IOException iOException) {
        super((Throwable) Objects.requireNonNull(iOException));
    }

    @Override // java.lang.Throwable
    public IOException getCause() {
        return (IOException) super.getCause();
    }
}
