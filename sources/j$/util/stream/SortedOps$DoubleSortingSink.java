package j$.util.stream;

import j$.util.stream.SpinedBuffer;
import java.util.Arrays;

/* loaded from: classes4.dex */
final class SortedOps$DoubleSortingSink extends SortedOps$AbstractDoubleSortingSink {
    private SpinedBuffer.OfDouble b;

    /* JADX WARN: Type inference failed for: r0v2, types: [j$.util.stream.SpinedBuffer$OfDouble, j$.util.stream.SpinedBuffer$OfPrimitive] */
    /* JADX WARN: Type inference failed for: r0v5, types: [j$.util.stream.SpinedBuffer$OfPrimitive] */
    /* JADX WARN: Type inference failed for: r0v6, types: [j$.util.stream.SpinedBuffer$OfPrimitive] */
    @Override // j$.util.stream.Sink.ChainedDouble, j$.util.stream.Sink
    public final void begin(long j) {
        ?? r0;
        if (j >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        if (j <= 0) {
            r0 = new SpinedBuffer.OfPrimitive();
        } else {
            r0 = new SpinedBuffer.OfDouble((int) j);
        }
        this.b = r0;
    }

    @Override // j$.util.stream.Sink.ChainedDouble, j$.util.stream.Sink
    public final void end() {
        double[] dArr = (double[]) this.b.asPrimitiveArray();
        Arrays.sort(dArr);
        long length = dArr.length;
        Sink sink = this.downstream;
        sink.begin(length);
        int i = 0;
        if (!this.cancellationRequestedCalled) {
            int length2 = dArr.length;
            while (i < length2) {
                sink.accept(dArr[i]);
                i++;
            }
        } else {
            int length3 = dArr.length;
            while (i < length3) {
                double d = dArr[i];
                if (sink.cancellationRequested()) {
                    break;
                }
                sink.accept(d);
                i++;
            }
        }
        sink.end();
    }

    @Override // j$.util.stream.Sink.OfDouble, j$.util.stream.Sink, java.util.function.DoubleConsumer
    public final void accept(double d) {
        this.b.accept(d);
    }
}
