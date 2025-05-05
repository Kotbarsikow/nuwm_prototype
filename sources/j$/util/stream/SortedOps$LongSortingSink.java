package j$.util.stream;

import j$.util.stream.SpinedBuffer;
import java.util.Arrays;

/* loaded from: classes4.dex */
final class SortedOps$LongSortingSink extends SortedOps$AbstractLongSortingSink {
    private SpinedBuffer.OfLong b;

    /* JADX WARN: Type inference failed for: r0v2, types: [j$.util.stream.SpinedBuffer$OfLong, j$.util.stream.SpinedBuffer$OfPrimitive] */
    /* JADX WARN: Type inference failed for: r0v5, types: [j$.util.stream.SpinedBuffer$OfPrimitive] */
    /* JADX WARN: Type inference failed for: r0v6, types: [j$.util.stream.SpinedBuffer$OfPrimitive] */
    @Override // j$.util.stream.Sink.ChainedLong, j$.util.stream.Sink
    public final void begin(long j) {
        ?? r0;
        if (j >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        if (j <= 0) {
            r0 = new SpinedBuffer.OfPrimitive();
        } else {
            r0 = new SpinedBuffer.OfLong((int) j);
        }
        this.b = r0;
    }

    @Override // j$.util.stream.Sink.ChainedLong, j$.util.stream.Sink
    public final void end() {
        long[] jArr = (long[]) this.b.asPrimitiveArray();
        Arrays.sort(jArr);
        long length = jArr.length;
        Sink sink = this.downstream;
        sink.begin(length);
        int i = 0;
        if (!this.cancellationRequestedCalled) {
            int length2 = jArr.length;
            while (i < length2) {
                sink.accept(jArr[i]);
                i++;
            }
        } else {
            int length3 = jArr.length;
            while (i < length3) {
                long j = jArr[i];
                if (sink.cancellationRequested()) {
                    break;
                }
                sink.accept(j);
                i++;
            }
        }
        sink.end();
    }

    @Override // j$.util.stream.Sink.OfLong, j$.util.stream.Sink
    public final void accept(long j) {
        this.b.accept(j);
    }
}
