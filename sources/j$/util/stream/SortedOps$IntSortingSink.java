package j$.util.stream;

import j$.util.stream.SpinedBuffer;
import java.util.Arrays;

/* loaded from: classes4.dex */
final class SortedOps$IntSortingSink extends SortedOps$AbstractIntSortingSink {
    private SpinedBuffer.OfInt b;

    /* JADX WARN: Type inference failed for: r0v2, types: [j$.util.stream.SpinedBuffer$OfInt, j$.util.stream.SpinedBuffer$OfPrimitive] */
    /* JADX WARN: Type inference failed for: r0v5, types: [j$.util.stream.SpinedBuffer$OfPrimitive] */
    /* JADX WARN: Type inference failed for: r0v6, types: [j$.util.stream.SpinedBuffer$OfPrimitive] */
    @Override // j$.util.stream.Sink.ChainedInt, j$.util.stream.Sink
    public final void begin(long j) {
        ?? r0;
        if (j >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        if (j <= 0) {
            r0 = new SpinedBuffer.OfPrimitive();
        } else {
            r0 = new SpinedBuffer.OfInt((int) j);
        }
        this.b = r0;
    }

    @Override // j$.util.stream.Sink.ChainedInt, j$.util.stream.Sink
    public final void end() {
        int[] iArr = (int[]) this.b.asPrimitiveArray();
        Arrays.sort(iArr);
        long length = iArr.length;
        Sink sink = this.downstream;
        sink.begin(length);
        int i = 0;
        if (!this.cancellationRequestedCalled) {
            int length2 = iArr.length;
            while (i < length2) {
                sink.accept(iArr[i]);
                i++;
            }
        } else {
            int length3 = iArr.length;
            while (i < length3) {
                int i2 = iArr[i];
                if (sink.cancellationRequested()) {
                    break;
                }
                sink.accept(i2);
                i++;
            }
        }
        sink.end();
    }

    @Override // j$.util.stream.Sink.OfInt, j$.util.stream.Sink
    public final void accept(int i) {
        this.b.accept(i);
    }
}
