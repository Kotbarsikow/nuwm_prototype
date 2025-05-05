package j$.util.stream;

import j$.util.Iterator;
import j$.util.Spliterator;
import j$.util.stream.Node;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.IntFunction;

/* loaded from: classes4.dex */
final class Nodes$CollectionNode implements Node {
    private final Collection c;

    @Override // j$.util.stream.Node
    public final /* synthetic */ int getChildCount() {
        return 0;
    }

    @Override // j$.util.stream.Node
    public final /* synthetic */ Node truncate(long j, long j2, IntFunction intFunction) {
        return Node.CC.$default$truncate(this, j, j2, intFunction);
    }

    @Override // j$.util.stream.Node
    public final Node getChild(int i) {
        throw new IndexOutOfBoundsException();
    }

    Nodes$CollectionNode(Collection collection) {
        this.c = collection;
    }

    @Override // j$.util.stream.Node
    public final Spliterator spliterator() {
        return Iterator.EL.stream(this.c).spliterator();
    }

    @Override // j$.util.stream.Node
    public final void copyInto(Object[] objArr, int i) {
        java.util.Iterator it = this.c.iterator();
        while (it.hasNext()) {
            objArr[i] = it.next();
            i++;
        }
    }

    @Override // j$.util.stream.Node
    public final Object[] asArray(IntFunction intFunction) {
        Collection collection = this.c;
        return collection.toArray((Object[]) intFunction.apply(collection.size()));
    }

    @Override // j$.util.stream.Node
    public final long count() {
        return this.c.size();
    }

    @Override // j$.util.stream.Node
    public final void forEach(Consumer consumer) {
        Iterator.EL.forEach(this.c, consumer);
    }

    public final String toString() {
        Collection collection = this.c;
        return String.format("CollectionNode[%d][%s]", Integer.valueOf(collection.size()), collection);
    }
}
