package j$.util.stream;

import j$.util.Spliterator;
import j$.util.stream.Node;
import java.util.concurrent.CountedCompleter;
import java.util.function.BinaryOperator;
import java.util.function.LongFunction;

/* loaded from: classes4.dex */
class Nodes$CollectorTask extends AbstractTask {
    protected final LongFunction builderFactory;
    protected final BinaryOperator concFactory;
    protected final AbstractPipeline helper;

    @Override // j$.util.stream.AbstractTask, java.util.concurrent.CountedCompleter
    public final void onCompletion(CountedCompleter countedCompleter) {
        AbstractTask abstractTask = this.leftChild;
        if (abstractTask != null) {
            setLocalResult((Node) this.concFactory.apply((Node) ((Nodes$CollectorTask) abstractTask).getLocalResult(), (Node) ((Nodes$CollectorTask) this.rightChild).getLocalResult()));
        }
        super.onCompletion(countedCompleter);
    }

    Nodes$CollectorTask(AbstractPipeline abstractPipeline, Spliterator spliterator, LongFunction longFunction, BinaryOperator binaryOperator) {
        super(abstractPipeline, spliterator);
        this.helper = abstractPipeline;
        this.builderFactory = longFunction;
        this.concFactory = binaryOperator;
    }

    Nodes$CollectorTask(Nodes$CollectorTask nodes$CollectorTask, Spliterator spliterator) {
        super(nodes$CollectorTask, spliterator);
        this.helper = nodes$CollectorTask.helper;
        this.builderFactory = nodes$CollectorTask.builderFactory;
        this.concFactory = nodes$CollectorTask.concFactory;
    }

    final class OfInt extends Nodes$CollectorTask {
        public final /* synthetic */ int $r8$classId;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public /* synthetic */ OfInt(AbstractPipeline abstractPipeline, Spliterator spliterator, LongFunction longFunction, BinaryOperator binaryOperator, int i) {
            super(abstractPipeline, spliterator, longFunction, binaryOperator);
            this.$r8$classId = i;
        }

        @Override // j$.util.stream.Nodes$CollectorTask, j$.util.stream.AbstractTask
        protected final AbstractTask makeChild(Spliterator spliterator) {
            switch (this.$r8$classId) {
            }
            return new Nodes$CollectorTask(this, spliterator);
        }

        @Override // j$.util.stream.Nodes$CollectorTask, j$.util.stream.AbstractTask
        protected final /* bridge */ /* synthetic */ Object doLeaf() {
            switch (this.$r8$classId) {
            }
            return doLeaf();
        }
    }

    @Override // j$.util.stream.AbstractTask
    protected AbstractTask makeChild(Spliterator spliterator) {
        return new Nodes$CollectorTask(this, spliterator);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // j$.util.stream.AbstractTask
    public final Node doLeaf() {
        Node.Builder builder = (Node.Builder) this.builderFactory.apply(this.helper.exactOutputSizeIfKnown(this.spliterator));
        this.helper.wrapAndCopyInto(this.spliterator, builder);
        return builder.build();
    }
}
