package j$.util.stream;

import j$.util.stream.Node;

/* loaded from: classes4.dex */
abstract class Nodes$AbstractConcNode implements Node {
    protected final Node left;
    protected final Node right;
    private final long size;

    @Override // j$.util.stream.Node
    public final int getChildCount() {
        return 2;
    }

    Nodes$AbstractConcNode(Node node, Node node2) {
        this.left = node;
        this.right = node2;
        this.size = node.count() + node2.count();
    }

    @Override // j$.util.stream.Node
    public final Node getChild(int i) {
        if (i == 0) {
            return this.left;
        }
        if (i == 1) {
            return this.right;
        }
        throw new IndexOutOfBoundsException();
    }

    @Override // j$.util.stream.Node
    public final long count() {
        return this.size;
    }

    @Override // j$.util.stream.Node
    public /* bridge */ /* synthetic */ Node.OfPrimitive getChild(int i) {
        return (Node.OfPrimitive) getChild(i);
    }
}
