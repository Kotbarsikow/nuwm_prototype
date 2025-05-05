package j$.util.stream;

import java.util.Arrays;

/* loaded from: classes4.dex */
final class SortedOps$SizedIntSortingSink extends SortedOps$AbstractIntSortingSink {
    private int[] array;
    private int offset;

    @Override // j$.util.stream.Sink.ChainedInt, j$.util.stream.Sink
    public final void begin(long j) {
        if (j >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        this.array = new int[(int) j];
    }

    @Override // j$.util.stream.Sink.ChainedInt, j$.util.stream.Sink
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

    @Override // j$.util.stream.Sink.OfInt, j$.util.stream.Sink
    public final void accept(int i) {
        int[] iArr = this.array;
        int i2 = this.offset;
        this.offset = i2 + 1;
        iArr[i2] = i;
    }
}
