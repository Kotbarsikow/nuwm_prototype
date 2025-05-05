package j$.io;

import j$.util.Spliterators;
import j$.util.stream.Stream;
import j$.util.stream.StreamSupport;
import java.io.BufferedReader;

/* loaded from: classes4.dex */
public final /* synthetic */ class DesugarBufferedReader {
    public static Stream lines(BufferedReader bufferedReader) {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(new DesugarBufferedReaderLinesIterator(bufferedReader), 272), false);
    }
}
