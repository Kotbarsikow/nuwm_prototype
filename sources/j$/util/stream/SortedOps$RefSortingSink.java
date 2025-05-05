package j$.util.stream;

import j$.util.Iterator;
import j$.util.List;
import j$.util.Objects;
import java.util.ArrayList;

/* loaded from: classes4.dex */
final class SortedOps$RefSortingSink extends SortedOps$AbstractRefSortingSink {
    private ArrayList list;

    @Override // j$.util.stream.Sink.ChainedReference, j$.util.stream.Sink
    public final void begin(long j) {
        if (j >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        this.list = j >= 0 ? new ArrayList((int) j) : new ArrayList();
    }

    @Override // j$.util.stream.Sink.ChainedReference, j$.util.stream.Sink
    public final void end() {
        List.EL.sort(this.list, this.comparator);
        long size = this.list.size();
        Sink sink = this.downstream;
        sink.begin(size);
        if (!this.cancellationRequestedCalled) {
            ArrayList arrayList = this.list;
            Objects.requireNonNull(sink);
            Iterator.EL.forEach(arrayList, new FlatMapApiFlips$FunctionStreamWrapper(2, sink));
        } else {
            java.util.Iterator it = this.list.iterator();
            while (it.hasNext()) {
                Object next = it.next();
                if (sink.cancellationRequested()) {
                    break;
                } else {
                    sink.accept((Sink) next);
                }
            }
        }
        sink.end();
        this.list = null;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        this.list.add(obj);
    }
}
