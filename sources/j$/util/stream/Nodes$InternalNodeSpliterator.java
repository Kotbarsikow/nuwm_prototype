package j$.util.stream;

import j$.util.Iterator;
import j$.util.Spliterator;
import j$.util.stream.Node;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;

/* loaded from: classes4.dex */
abstract class Nodes$InternalNodeSpliterator implements Spliterator {
    int curChildIndex;
    Node curNode;
    Spliterator lastNodeSpliterator;
    Spliterator tryAdvanceSpliterator;
    ArrayDeque tryAdvanceStack;

    final class OfDouble extends OfPrimitive implements Spliterator.OfDouble {
        @Override // j$.util.Spliterator
        public final /* synthetic */ void forEachRemaining(Consumer consumer) {
            Iterator.EL.$default$forEachRemaining(this, consumer);
        }

        @Override // j$.util.Spliterator
        public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
            return Iterator.EL.$default$tryAdvance(this, consumer);
        }
    }

    final class OfInt extends OfPrimitive implements Spliterator.OfInt {
        @Override // j$.util.Spliterator
        public final /* synthetic */ void forEachRemaining(Consumer consumer) {
            Iterator.EL.$default$forEachRemaining(this, consumer);
        }

        @Override // j$.util.Spliterator
        public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
            return Iterator.EL.$default$tryAdvance(this, consumer);
        }
    }

    final class OfLong extends OfPrimitive implements Spliterator.OfLong {
        @Override // j$.util.Spliterator
        public final /* synthetic */ void forEachRemaining(Consumer consumer) {
            Iterator.EL.$default$forEachRemaining(this, consumer);
        }

        @Override // j$.util.Spliterator
        public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
            return Iterator.EL.$default$tryAdvance(this, consumer);
        }
    }

    @Override // j$.util.Spliterator
    public final int characteristics() {
        return 64;
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ long getExactSizeIfKnown() {
        return Iterator.EL.$default$getExactSizeIfKnown(this);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ boolean hasCharacteristics(int i) {
        return Iterator.EL.$default$hasCharacteristics(this, i);
    }

    @Override // j$.util.Spliterator
    public final Comparator getComparator() {
        throw new IllegalStateException();
    }

    Nodes$InternalNodeSpliterator(Node node) {
        this.curNode = node;
    }

    protected final ArrayDeque initStack() {
        ArrayDeque arrayDeque = new ArrayDeque(8);
        int childCount = this.curNode.getChildCount();
        while (true) {
            childCount--;
            if (childCount < this.curChildIndex) {
                return arrayDeque;
            }
            arrayDeque.addFirst(this.curNode.getChild(childCount));
        }
    }

    protected static Node findNextLeafNode(ArrayDeque arrayDeque) {
        while (true) {
            Node node = (Node) arrayDeque.pollFirst();
            if (node == null) {
                return null;
            }
            if (node.getChildCount() != 0) {
                for (int childCount = node.getChildCount() - 1; childCount >= 0; childCount--) {
                    arrayDeque.addFirst(node.getChild(childCount));
                }
            } else if (node.count() > 0) {
                return node;
            }
        }
    }

    protected final boolean initTryAdvance() {
        if (this.curNode == null) {
            return false;
        }
        if (this.tryAdvanceSpliterator != null) {
            return true;
        }
        Spliterator spliterator = this.lastNodeSpliterator;
        if (spliterator == null) {
            ArrayDeque initStack = initStack();
            this.tryAdvanceStack = initStack;
            Node findNextLeafNode = findNextLeafNode(initStack);
            if (findNextLeafNode != null) {
                this.tryAdvanceSpliterator = findNextLeafNode.spliterator();
                return true;
            }
            this.curNode = null;
            return false;
        }
        this.tryAdvanceSpliterator = spliterator;
        return true;
    }

    @Override // j$.util.Spliterator
    public final Spliterator trySplit() {
        Node node = this.curNode;
        if (node == null || this.tryAdvanceSpliterator != null) {
            return null;
        }
        Spliterator spliterator = this.lastNodeSpliterator;
        if (spliterator != null) {
            return spliterator.trySplit();
        }
        if (this.curChildIndex < node.getChildCount() - 1) {
            Node node2 = this.curNode;
            int i = this.curChildIndex;
            this.curChildIndex = i + 1;
            return node2.getChild(i).spliterator();
        }
        Node child = this.curNode.getChild(this.curChildIndex);
        this.curNode = child;
        if (child.getChildCount() == 0) {
            Spliterator spliterator2 = this.curNode.spliterator();
            this.lastNodeSpliterator = spliterator2;
            return spliterator2.trySplit();
        }
        Node node3 = this.curNode;
        this.curChildIndex = 1;
        return node3.getChild(0).spliterator();
    }

    @Override // j$.util.Spliterator
    public final long estimateSize() {
        long j = 0;
        if (this.curNode == null) {
            return 0L;
        }
        Spliterator spliterator = this.lastNodeSpliterator;
        if (spliterator != null) {
            return spliterator.estimateSize();
        }
        for (int i = this.curChildIndex; i < this.curNode.getChildCount(); i++) {
            j += this.curNode.getChild(i).count();
        }
        return j;
    }

    final class OfRef extends Nodes$InternalNodeSpliterator {
        @Override // j$.util.Spliterator
        public final boolean tryAdvance(Consumer consumer) {
            Node findNextLeafNode;
            if (!initTryAdvance()) {
                return false;
            }
            boolean tryAdvance = this.tryAdvanceSpliterator.tryAdvance(consumer);
            if (!tryAdvance) {
                if (this.lastNodeSpliterator == null && (findNextLeafNode = Nodes$InternalNodeSpliterator.findNextLeafNode(this.tryAdvanceStack)) != null) {
                    Spliterator spliterator = findNextLeafNode.spliterator();
                    this.tryAdvanceSpliterator = spliterator;
                    return spliterator.tryAdvance(consumer);
                }
                this.curNode = null;
            }
            return tryAdvance;
        }

        @Override // j$.util.Spliterator
        public final void forEachRemaining(Consumer consumer) {
            if (this.curNode == null) {
                return;
            }
            if (this.tryAdvanceSpliterator == null) {
                Spliterator spliterator = this.lastNodeSpliterator;
                if (spliterator == null) {
                    ArrayDeque initStack = initStack();
                    while (true) {
                        Node findNextLeafNode = Nodes$InternalNodeSpliterator.findNextLeafNode(initStack);
                        if (findNextLeafNode != null) {
                            findNextLeafNode.forEach(consumer);
                        } else {
                            this.curNode = null;
                            return;
                        }
                    }
                } else {
                    spliterator.forEachRemaining(consumer);
                }
            } else {
                while (tryAdvance(consumer)) {
                }
            }
        }
    }

    @Override // j$.util.Spliterator
    public /* bridge */ /* synthetic */ Spliterator.OfPrimitive trySplit() {
        return (Spliterator.OfPrimitive) trySplit();
    }

    abstract class OfPrimitive extends Nodes$InternalNodeSpliterator implements Spliterator.OfPrimitive {
        @Override // j$.util.Spliterator.OfPrimitive
        public final boolean tryAdvance(Object obj) {
            Node.OfPrimitive ofPrimitive;
            if (!initTryAdvance()) {
                return false;
            }
            boolean tryAdvance = ((Spliterator.OfPrimitive) this.tryAdvanceSpliterator).tryAdvance(obj);
            if (!tryAdvance) {
                if (this.lastNodeSpliterator == null && (ofPrimitive = (Node.OfPrimitive) Nodes$InternalNodeSpliterator.findNextLeafNode(this.tryAdvanceStack)) != null) {
                    Spliterator.OfPrimitive spliterator = ofPrimitive.spliterator();
                    this.tryAdvanceSpliterator = spliterator;
                    return spliterator.tryAdvance(obj);
                }
                this.curNode = null;
            }
            return tryAdvance;
        }

        @Override // j$.util.Spliterator.OfPrimitive
        public final void forEachRemaining(Object obj) {
            if (this.curNode == null) {
                return;
            }
            if (this.tryAdvanceSpliterator == null) {
                Spliterator spliterator = this.lastNodeSpliterator;
                if (spliterator == null) {
                    ArrayDeque initStack = initStack();
                    while (true) {
                        Node.OfPrimitive ofPrimitive = (Node.OfPrimitive) Nodes$InternalNodeSpliterator.findNextLeafNode(initStack);
                        if (ofPrimitive != null) {
                            ofPrimitive.forEach(obj);
                        } else {
                            this.curNode = null;
                            return;
                        }
                    }
                } else {
                    ((Spliterator.OfPrimitive) spliterator).forEachRemaining(obj);
                }
            } else {
                while (tryAdvance(obj)) {
                }
            }
        }

        public /* bridge */ /* synthetic */ void forEachRemaining(IntConsumer intConsumer) {
            forEachRemaining((Object) intConsumer);
        }

        public /* bridge */ /* synthetic */ boolean tryAdvance(IntConsumer intConsumer) {
            return tryAdvance((Object) intConsumer);
        }

        public /* bridge */ /* synthetic */ void forEachRemaining(LongConsumer longConsumer) {
            forEachRemaining((Object) longConsumer);
        }

        public /* bridge */ /* synthetic */ boolean tryAdvance(LongConsumer longConsumer) {
            return tryAdvance((Object) longConsumer);
        }

        public /* bridge */ /* synthetic */ void forEachRemaining(DoubleConsumer doubleConsumer) {
            forEachRemaining((Object) doubleConsumer);
        }

        public /* bridge */ /* synthetic */ boolean tryAdvance(DoubleConsumer doubleConsumer) {
            return tryAdvance((Object) doubleConsumer);
        }
    }

    @Override // j$.util.Spliterator
    public /* bridge */ /* synthetic */ Spliterator.OfInt trySplit() {
        return (Spliterator.OfInt) trySplit();
    }

    @Override // j$.util.Spliterator
    public /* bridge */ /* synthetic */ Spliterator.OfLong trySplit() {
        return (Spliterator.OfLong) trySplit();
    }

    @Override // j$.util.Spliterator
    public /* bridge */ /* synthetic */ Spliterator.OfDouble trySplit() {
        return (Spliterator.OfDouble) trySplit();
    }
}
