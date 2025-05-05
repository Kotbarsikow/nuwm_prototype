package j$.util.stream;

import j$.util.stream.Node;
import java.util.concurrent.CountedCompleter;

/* loaded from: classes4.dex */
class Nodes$ToArrayTask$OfRef extends CountedCompleter {
    public final /* synthetic */ int $r8$classId;
    private final Object array;
    protected final Node node;
    protected final int offset;

    public Nodes$ToArrayTask$OfRef(Node node, Object obj, int i) {
        this.$r8$classId = i;
        this.node = node;
        this.offset = 0;
        this.array = obj;
    }

    Nodes$ToArrayTask$OfRef(Nodes$ToArrayTask$OfRef nodes$ToArrayTask$OfRef, Node node, int i, byte b) {
        super(nodes$ToArrayTask$OfRef);
        this.node = node;
        this.offset = i;
    }

    @Override // java.util.concurrent.CountedCompleter
    public final void compute() {
        Nodes$ToArrayTask$OfRef nodes$ToArrayTask$OfRef = this;
        while (nodes$ToArrayTask$OfRef.node.getChildCount() != 0) {
            nodes$ToArrayTask$OfRef.setPendingCount(nodes$ToArrayTask$OfRef.node.getChildCount() - 1);
            int i = 0;
            int i2 = 0;
            while (i < nodes$ToArrayTask$OfRef.node.getChildCount() - 1) {
                Nodes$ToArrayTask$OfRef makeChild = nodes$ToArrayTask$OfRef.makeChild(i, nodes$ToArrayTask$OfRef.offset + i2);
                i2 = (int) (i2 + makeChild.node.count());
                makeChild.fork();
                i++;
            }
            nodes$ToArrayTask$OfRef = nodes$ToArrayTask$OfRef.makeChild(i, nodes$ToArrayTask$OfRef.offset + i2);
        }
        switch (nodes$ToArrayTask$OfRef.$r8$classId) {
            case 0:
                nodes$ToArrayTask$OfRef.node.copyInto((Object[]) nodes$ToArrayTask$OfRef.array, nodes$ToArrayTask$OfRef.offset);
                break;
            default:
                ((Node.OfPrimitive) nodes$ToArrayTask$OfRef.node).copyInto(nodes$ToArrayTask$OfRef.offset, nodes$ToArrayTask$OfRef.array);
                break;
        }
        nodes$ToArrayTask$OfRef.propagateCompletion();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Nodes$ToArrayTask$OfRef(Nodes$ToArrayTask$OfRef nodes$ToArrayTask$OfRef, Node node, int i) {
        this(nodes$ToArrayTask$OfRef, node, i, (byte) 0);
        this.$r8$classId = 0;
        this.array = (Object[]) nodes$ToArrayTask$OfRef.array;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Nodes$ToArrayTask$OfRef(Nodes$ToArrayTask$OfRef nodes$ToArrayTask$OfRef, Node.OfPrimitive ofPrimitive, int i) {
        this(nodes$ToArrayTask$OfRef, ofPrimitive, i, (byte) 0);
        this.$r8$classId = 1;
        this.array = nodes$ToArrayTask$OfRef.array;
    }

    final Nodes$ToArrayTask$OfRef makeChild(int i, int i2) {
        switch (this.$r8$classId) {
            case 0:
                return new Nodes$ToArrayTask$OfRef(this, this.node.getChild(i), i2);
            default:
                return new Nodes$ToArrayTask$OfRef(this, ((Node.OfPrimitive) this.node).getChild(i), i2);
        }
    }
}
