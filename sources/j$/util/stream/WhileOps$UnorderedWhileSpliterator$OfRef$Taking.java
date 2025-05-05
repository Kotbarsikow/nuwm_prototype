package j$.util.stream;

import j$.util.Iterator;
import j$.util.Spliterator;
import j$.util.function.Consumer$CC;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.function.Predicate;

/* loaded from: classes4.dex */
final class WhileOps$UnorderedWhileSpliterator$OfRef$Taking implements Consumer, Spliterator {
    public final /* synthetic */ int $r8$classId;
    final AtomicBoolean cancel;
    int count;
    final Predicate p;
    final Spliterator s;
    Object t;
    boolean takeOrDrop;

    @Override // java.util.function.Consumer
    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        return Consumer$CC.$default$andThen(this, consumer);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ boolean hasCharacteristics(int i) {
        return Iterator.EL.$default$hasCharacteristics(this, i);
    }

    @Override // j$.util.Spliterator
    public final void forEachRemaining(Consumer consumer) {
        while (tryAdvance(consumer)) {
        }
    }

    public WhileOps$UnorderedWhileSpliterator$OfRef$Taking(Spliterator spliterator, Predicate predicate, int i) {
        this.$r8$classId = i;
        this.takeOrDrop = true;
        this.s = spliterator;
        this.cancel = new AtomicBoolean();
        this.p = predicate;
    }

    public WhileOps$UnorderedWhileSpliterator$OfRef$Taking(Spliterator spliterator, WhileOps$UnorderedWhileSpliterator$OfRef$Taking whileOps$UnorderedWhileSpliterator$OfRef$Taking, int i) {
        this.$r8$classId = i;
        this.takeOrDrop = true;
        this.s = spliterator;
        this.cancel = whileOps$UnorderedWhileSpliterator$OfRef$Taking.cancel;
        this.p = whileOps$UnorderedWhileSpliterator$OfRef$Taking.p;
    }

    @Override // j$.util.Spliterator
    public final long estimateSize() {
        return this.s.estimateSize();
    }

    @Override // j$.util.Spliterator
    public final int characteristics() {
        return this.s.characteristics() & (-16449);
    }

    @Override // j$.util.Spliterator
    public final long getExactSizeIfKnown() {
        return -1L;
    }

    @Override // j$.util.Spliterator
    public final Comparator getComparator() {
        return this.s.getComparator();
    }

    public final Spliterator trySplit$j$$util$stream$WhileOps$UnorderedWhileSpliterator$OfRef() {
        Spliterator trySplit = this.s.trySplit();
        if (trySplit == null) {
            return null;
        }
        switch (this.$r8$classId) {
            case 0:
                return new WhileOps$UnorderedWhileSpliterator$OfRef$Taking(trySplit, this, 0);
            default:
                return new WhileOps$UnorderedWhileSpliterator$OfRef$Taking(trySplit, this, 1);
        }
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        this.count = (this.count + 1) & 63;
        this.t = obj;
    }

    @Override // j$.util.Spliterator
    public Spliterator trySplit() {
        switch (this.$r8$classId) {
            case 0:
                if (!this.cancel.get()) {
                    break;
                }
                break;
        }
        return trySplit$j$$util$stream$WhileOps$UnorderedWhileSpliterator$OfRef();
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x002f, code lost:
    
        if (r0 == false) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0031, code lost:
    
        r3.set(true);
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0034, code lost:
    
        r8.accept(r7.t);
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:?, code lost:
    
        return r2;
     */
    @Override // j$.util.Spliterator
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean tryAdvance(java.util.function.Consumer r8) {
        /*
            r7 = this;
            int r0 = r7.$r8$classId
            switch(r0) {
                case 0: goto L3f;
                default: goto L5;
            }
        L5:
            boolean r0 = r7.takeOrDrop
            j$.util.Spliterator r1 = r7.s
            if (r0 == 0) goto L3a
            r0 = 0
            r7.takeOrDrop = r0
        Le:
            boolean r2 = r1.tryAdvance(r7)
            java.util.concurrent.atomic.AtomicBoolean r3 = r7.cancel
            r4 = 1
            if (r2 == 0) goto L2d
            int r5 = r7.count
            if (r5 != 0) goto L21
            boolean r5 = r3.get()
            if (r5 != 0) goto L2d
        L21:
            java.util.function.Predicate r5 = r7.p
            java.lang.Object r6 = r7.t
            boolean r5 = r5.test(r6)
            if (r5 == 0) goto L2d
            r0 = 1
            goto Le
        L2d:
            if (r2 == 0) goto L3e
            if (r0 == 0) goto L34
            r3.set(r4)
        L34:
            java.lang.Object r0 = r7.t
            r8.accept(r0)
            goto L3e
        L3a:
            boolean r2 = r1.tryAdvance(r8)
        L3e:
            return r2
        L3f:
            boolean r0 = r7.takeOrDrop
            java.util.concurrent.atomic.AtomicBoolean r1 = r7.cancel
            r2 = 1
            if (r0 == 0) goto L68
            int r0 = r7.count
            if (r0 != 0) goto L50
            boolean r0 = r1.get()
            if (r0 != 0) goto L68
        L50:
            j$.util.Spliterator r0 = r7.s
            boolean r0 = r0.tryAdvance(r7)
            if (r0 == 0) goto L68
            java.util.function.Predicate r0 = r7.p
            java.lang.Object r3 = r7.t
            boolean r0 = r0.test(r3)
            if (r0 == 0) goto L69
            java.lang.Object r0 = r7.t
            r8.accept(r0)
            goto L72
        L68:
            r0 = 1
        L69:
            r8 = 0
            r7.takeOrDrop = r8
            if (r0 != 0) goto L71
            r1.set(r2)
        L71:
            r2 = 0
        L72:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: j$.util.stream.WhileOps$UnorderedWhileSpliterator$OfRef$Taking.tryAdvance(java.util.function.Consumer):boolean");
    }
}
