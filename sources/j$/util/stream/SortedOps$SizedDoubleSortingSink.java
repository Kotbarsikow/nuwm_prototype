package j$.util.stream;

import java.util.Arrays;

/* loaded from: classes4.dex */
final class SortedOps$SizedDoubleSortingSink extends SortedOps$AbstractDoubleSortingSink {
    private double[] array;
    private int offset;

    @Override // j$.util.stream.Sink.ChainedDouble, j$.util.stream.Sink
    public final void begin(long j) {
        if (j >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        this.array = new double[(int) j];
    }

    @Override // j$.util.stream.Sink.ChainedDouble, j$.util.stream.Sink
    public final void end() {
        int i = 0;
        Arrays.sort(this.array, 0, this.offset);
        long j = this.offset;
        Sink sink = this.downstream;
        sink.begin(j);
        if (!this.cancellationRequestedCalled) {
            while (i < this.offset) {
                sink.accept(this.array[i]);
                i++;
            }
        } else {
            while (i < this.offset && !sink.cancellationRequested()) {
                sink.accept(this.array[i]);
                i++;
            }
        }
        sink.end();
        this.array = null;
    }

    @Override // j$.util.stream.Sink.OfDouble, j$.util.stream.Sink, java.util.function.DoubleConsumer
    public final void accept(double d) {
        double[] dArr = this.array;
        int i = this.offset;
        this.offset = i + 1;
        dArr[i] = d;
    }
}
