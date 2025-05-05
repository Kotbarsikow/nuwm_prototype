package j$.util.stream;

import java.util.Arrays;

/* loaded from: classes4.dex */
final class SortedOps$SizedRefSortingSink extends SortedOps$AbstractRefSortingSink {
    private Object[] array;
    private int offset;

    @Override // j$.util.stream.Sink.ChainedReference, j$.util.stream.Sink
    public final void begin(long j) {
        if (j >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        this.array = new Object[(int) j];
    }

    @Override // j$.util.stream.Sink.ChainedReference, j$.util.stream.Sink
    public final void end() {
        int i = 0;
        Arrays.sort(this.array, 0, this.offset, this.comparator);
        long j = this.offset;
        Sink sink = this.downstream;
        sink.begin(j);
        if (!this.cancellationRequestedCalled) {
            while (i < this.offset) {
                sink.accept((Sink) this.array[i]);
                i++;
            }
        } else {
            while (i < this.offset && !sink.cancellationRequested()) {
                sink.accept((Sink) this.array[i]);
                i++;
            }
        }
        sink.end();
        this.array = null;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        Object[] objArr = this.array;
        int i = this.offset;
        this.offset = i + 1;
        objArr[i] = obj;
    }
}
