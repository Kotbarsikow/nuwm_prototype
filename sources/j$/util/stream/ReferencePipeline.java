package j$.util.stream;

import j$.util.Objects;
import j$.util.Optional;
import j$.util.Spliterator;
import j$.util.Spliterators;
import j$.util.function.Predicate$$ExternalSyntheticLambda1;
import j$.util.stream.Collector;
import j$.util.stream.DoublePipeline;
import j$.util.stream.FindOps$FindSink;
import j$.util.stream.ForEachOps$ForEachOp;
import j$.util.stream.IntPipeline;
import j$.util.stream.LongPipeline;
import j$.util.stream.Node;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

/* loaded from: classes4.dex */
abstract class ReferencePipeline extends AbstractPipeline implements Stream {

    /* renamed from: j$.util.stream.ReferencePipeline$1 */
    final class AnonymousClass1 extends StatefulOp {
        @Override // j$.util.stream.AbstractPipeline
        final Sink opWrapSink(int i, Sink sink) {
            return sink;
        }
    }

    @Override // j$.util.stream.Stream
    public final Stream sorted() {
        return new SortedOps$OfRef(this);
    }

    @Override // j$.util.stream.Stream
    public final Stream distinct() {
        return new DistinctOps$1(this, StreamOpFlag.IS_DISTINCT | StreamOpFlag.NOT_SIZED, 0);
    }

    @Override // j$.util.stream.Stream
    public final Optional min(Comparator comparator) {
        Objects.requireNonNull(comparator);
        return reduce(new Predicate$$ExternalSyntheticLambda1(2, comparator));
    }

    @Override // j$.util.stream.Stream
    public final Optional findAny() {
        return (Optional) evaluate(FindOps$FindSink.OfRef.OP_FIND_ANY);
    }

    @Override // j$.util.stream.Stream
    public final Optional findFirst() {
        return (Optional) evaluate(FindOps$FindSink.OfRef.OP_FIND_FIRST);
    }

    @Override // j$.util.stream.Stream
    public final Stream sorted(Comparator comparator) {
        return new SortedOps$OfRef(this, comparator);
    }

    @Override // j$.util.stream.Stream
    public final Object reduce(Object obj, BiFunction biFunction, BinaryOperator binaryOperator) {
        Objects.requireNonNull(biFunction);
        Objects.requireNonNull(binaryOperator);
        return evaluate(new ReduceOps$1(StreamShape.REFERENCE, binaryOperator, biFunction, obj, 0));
    }

    @Override // j$.util.stream.Stream
    public final Object reduce(Object obj, BinaryOperator binaryOperator) {
        Objects.requireNonNull(binaryOperator);
        Objects.requireNonNull(binaryOperator);
        return evaluate(new ReduceOps$1(StreamShape.REFERENCE, binaryOperator, binaryOperator, obj, 0));
    }

    @Override // j$.util.stream.Stream
    public void forEach(Consumer consumer) {
        Objects.requireNonNull(consumer);
        evaluate(new ForEachOps$ForEachOp.OfRef(consumer, false));
    }

    @Override // j$.util.stream.Stream
    public void forEachOrdered(Consumer consumer) {
        Objects.requireNonNull(consumer);
        evaluate(new ForEachOps$ForEachOp.OfRef(consumer, true));
    }

    @Override // j$.util.stream.Stream
    public final Optional max(Comparator comparator) {
        Objects.requireNonNull(comparator);
        return reduce(new Predicate$$ExternalSyntheticLambda1(1, comparator));
    }

    @Override // j$.util.stream.AbstractPipeline
    final StreamShape getOutputShape() {
        return StreamShape.REFERENCE;
    }

    @Override // j$.util.stream.Stream
    public final Optional reduce(BinaryOperator binaryOperator) {
        Objects.requireNonNull(binaryOperator);
        return (Optional) evaluate(new ReduceOps$2(StreamShape.REFERENCE, binaryOperator, 0));
    }

    @Override // j$.util.stream.AbstractPipeline
    final Node evaluateToNode(AbstractPipeline abstractPipeline, Spliterator spliterator, boolean z, IntFunction intFunction) {
        return Node.CC.collect(abstractPipeline, spliterator, z, intFunction);
    }

    @Override // j$.util.stream.AbstractPipeline
    final Spliterator wrap(AbstractPipeline abstractPipeline, Supplier supplier, boolean z) {
        return new StreamSpliterators$WrappingSpliterator(abstractPipeline, supplier, z);
    }

    @Override // j$.util.stream.AbstractPipeline
    final Spliterator lazySpliterator(Supplier supplier) {
        return new StreamSpliterators$DelegatingSpliterator(supplier);
    }

    @Override // j$.util.stream.AbstractPipeline
    final boolean forEachWithCancel(Spliterator spliterator, Sink sink) {
        boolean cancellationRequested;
        do {
            cancellationRequested = sink.cancellationRequested();
            if (cancellationRequested) {
                break;
            }
        } while (spliterator.tryAdvance(sink));
        return cancellationRequested;
    }

    @Override // j$.util.stream.AbstractPipeline
    final Node.Builder makeNodeBuilder(long j, IntFunction intFunction) {
        return Node.CC.builder(j, intFunction);
    }

    @Override // j$.util.stream.BaseStream
    public final Iterator iterator() {
        return Spliterators.iterator(spliterator());
    }

    final class Head extends ReferencePipeline {
        @Override // j$.util.stream.BaseStream
        public final BaseStream unordered() {
            return !isOrdered() ? this : new AnonymousClass1(this, StreamOpFlag.NOT_ORDERED, 1);
        }

        @Override // j$.util.stream.AbstractPipeline
        final boolean opIsStateful() {
            throw new UnsupportedOperationException();
        }

        @Override // j$.util.stream.AbstractPipeline
        final Sink opWrapSink(int i, Sink sink) {
            throw new UnsupportedOperationException();
        }

        @Override // j$.util.stream.ReferencePipeline, j$.util.stream.Stream
        public final void forEach(Consumer consumer) {
            if (!isParallel()) {
                sourceStageSpliterator().forEachRemaining(consumer);
            } else {
                super.forEach(consumer);
            }
        }

        @Override // j$.util.stream.ReferencePipeline, j$.util.stream.Stream
        public final void forEachOrdered(Consumer consumer) {
            if (!isParallel()) {
                sourceStageSpliterator().forEachRemaining(consumer);
            } else {
                super.forEachOrdered(consumer);
            }
        }
    }

    abstract class StatefulOp extends ReferencePipeline {
        public final /* synthetic */ int $r8$classId;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public /* synthetic */ StatefulOp(AbstractPipeline abstractPipeline, int i, int i2) {
            super(abstractPipeline, i);
            this.$r8$classId = i2;
        }

        @Override // j$.util.stream.AbstractPipeline
        final boolean opIsStateful() {
            switch (this.$r8$classId) {
                case 0:
                    return true;
                default:
                    return false;
            }
        }

        @Override // j$.util.stream.BaseStream
        public final BaseStream unordered() {
            switch (this.$r8$classId) {
                case 0:
                    if (!isOrdered()) {
                        break;
                    } else {
                        break;
                    }
                default:
                    if (!isOrdered()) {
                        break;
                    } else {
                        break;
                    }
            }
            return new AnonymousClass1(this, StreamOpFlag.NOT_ORDERED, 1);
        }
    }

    @Override // j$.util.stream.Stream
    public final Stream filter(Predicate predicate) {
        Objects.requireNonNull(predicate);
        return new IntPipeline.AnonymousClass1(this, StreamOpFlag.NOT_SIZED, predicate, 4);
    }

    @Override // j$.util.stream.Stream
    public final Stream map(Function function) {
        Objects.requireNonNull(function);
        return new IntPipeline.AnonymousClass1(this, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT, function, 5);
    }

    @Override // j$.util.stream.Stream
    public final IntStream mapToInt(ToIntFunction toIntFunction) {
        Objects.requireNonNull(toIntFunction);
        return new IntPipeline.AnonymousClass7(this, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT, toIntFunction, 2);
    }

    @Override // j$.util.stream.Stream
    public final Object collect(Supplier supplier, BiConsumer biConsumer, BiConsumer biConsumer2) {
        Objects.requireNonNull(supplier);
        Objects.requireNonNull(biConsumer);
        Objects.requireNonNull(biConsumer2);
        return evaluate(new ReduceOps$1(StreamShape.REFERENCE, biConsumer2, biConsumer, supplier, 3));
    }

    @Override // j$.util.stream.Stream
    public final LongStream mapToLong(ToLongFunction toLongFunction) {
        Objects.requireNonNull(toLongFunction);
        return new LongPipeline.AnonymousClass6(this, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT, toLongFunction, 3);
    }

    @Override // j$.util.stream.Stream
    public final DoubleStream mapToDouble(ToDoubleFunction toDoubleFunction) {
        Objects.requireNonNull(toDoubleFunction);
        return new DoublePipeline.AnonymousClass5(this, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT, toDoubleFunction, 2);
    }

    @Override // j$.util.stream.Stream
    public final long count() {
        return ((Long) evaluate(new ReduceOps$5(0))).longValue();
    }

    @Override // j$.util.stream.Stream
    public final Stream flatMap(FlatMapApiFlips$FunctionStreamWrapper flatMapApiFlips$FunctionStreamWrapper) {
        Objects.requireNonNull(flatMapApiFlips$FunctionStreamWrapper);
        return new IntPipeline.AnonymousClass1(this, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT | StreamOpFlag.NOT_SIZED, flatMapApiFlips$FunctionStreamWrapper, 6);
    }

    @Override // j$.util.stream.Stream
    public final IntStream flatMapToInt(FlatMapApiFlips$FunctionStreamWrapper flatMapApiFlips$FunctionStreamWrapper) {
        Objects.requireNonNull(flatMapApiFlips$FunctionStreamWrapper);
        return new IntPipeline.AnonymousClass7(this, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT | StreamOpFlag.NOT_SIZED, flatMapApiFlips$FunctionStreamWrapper, 3);
    }

    @Override // j$.util.stream.Stream
    public final DoubleStream flatMapToDouble(FlatMapApiFlips$FunctionStreamWrapper flatMapApiFlips$FunctionStreamWrapper) {
        Objects.requireNonNull(flatMapApiFlips$FunctionStreamWrapper);
        return new DoublePipeline.AnonymousClass5(this, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT | StreamOpFlag.NOT_SIZED, flatMapApiFlips$FunctionStreamWrapper, 3);
    }

    @Override // j$.util.stream.Stream
    public final LongStream flatMapToLong(FlatMapApiFlips$FunctionStreamWrapper flatMapApiFlips$FunctionStreamWrapper) {
        Objects.requireNonNull(flatMapApiFlips$FunctionStreamWrapper);
        return new LongPipeline.AnonymousClass6(this, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT | StreamOpFlag.NOT_SIZED, flatMapApiFlips$FunctionStreamWrapper, 2);
    }

    @Override // j$.util.stream.Stream
    public final Stream peek(Consumer consumer) {
        Objects.requireNonNull(consumer);
        return new IntPipeline.AnonymousClass1(this, consumer);
    }

    @Override // j$.util.stream.Stream
    public final Stream limit(long j) {
        if (j < 0) {
            throw new IllegalArgumentException(Long.toString(j));
        }
        return Node.CC.makeRef(this, 0L, j);
    }

    @Override // j$.util.stream.Stream
    public final Stream skip(long j) {
        if (j >= 0) {
            return j == 0 ? this : Node.CC.makeRef(this, j, -1L);
        }
        throw new IllegalArgumentException(Long.toString(j));
    }

    @Override // j$.util.stream.Stream
    public final Stream takeWhile(Predicate predicate) {
        int i = WhileOps.TAKE_FLAGS;
        Objects.requireNonNull(predicate);
        return new StatefulOp
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x000c: RETURN 
              (wrap:j$.util.stream.WhileOps$1:0x0009: CONSTRUCTOR 
              (r2v0 'this' j$.util.stream.ReferencePipeline A[IMMUTABLE_TYPE, THIS])
              (wrap:int:0x0007: SGET  A[WRAPPED] j$.util.stream.WhileOps.TAKE_FLAGS int)
              (r3v0 'predicate' java.util.function.Predicate)
             A[MD:(j$.util.stream.ReferencePipeline, int, java.util.function.Predicate):void (m), WRAPPED] (LINE:64) call: j$.util.stream.WhileOps.1.<init>(j$.util.stream.ReferencePipeline, int, java.util.function.Predicate):void type: CONSTRUCTOR)
             (LINE:64) in method: j$.util.stream.ReferencePipeline.takeWhile(java.util.function.Predicate):j$.util.stream.Stream, file: classes4.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:310)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:94)
            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:406)
            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:335)
            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:301)
            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(Unknown Source)
            	at java.base/java.util.ArrayList.forEach(Unknown Source)
            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(Unknown Source)
            	at java.base/java.util.stream.Sink$ChainedReference.end(Unknown Source)
            	at java.base/java.util.stream.ReferencePipeline$7$1FlatMap.end(Unknown Source)
            	at java.base/java.util.stream.AbstractPipeline.copyInto(Unknown Source)
            	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(Unknown Source)
            	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(Unknown Source)
            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(Unknown Source)
            	at java.base/java.util.stream.AbstractPipeline.evaluate(Unknown Source)
            	at java.base/java.util.stream.ReferencePipeline.forEach(Unknown Source)
            	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:297)
            	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:286)
            	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:270)
            	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:161)
            	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:103)
            	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:45)
            	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:34)
            	at jadx.core.codegen.CodeGen.generate(CodeGen.java:22)
            	at jadx.core.ProcessClass.process(ProcessClass.java:79)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
            	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:402)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:390)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:310)
            Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Method arg registers not loaded: j$.util.stream.WhileOps.1.<init>(j$.util.stream.ReferencePipeline, int, java.util.function.Predicate):void, class status: GENERATED_AND_UNLOADED
            	at jadx.core.dex.nodes.MethodNode.getArgRegs(MethodNode.java:290)
            	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:829)
            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:730)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:418)
            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:145)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:121)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:108)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:368)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:303)
            	... 35 more
            */
        /*
            this = this;
            int r0 = j$.util.stream.WhileOps.TAKE_FLAGS
            j$.util.Objects.requireNonNull(r3)
            j$.util.stream.WhileOps$1 r0 = new j$.util.stream.WhileOps$1
            int r1 = j$.util.stream.WhileOps.TAKE_FLAGS
            r0.<init>(r2, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: j$.util.stream.ReferencePipeline.takeWhile(java.util.function.Predicate):j$.util.stream.Stream");
    }

    @Override // j$.util.stream.Stream
    public final Stream dropWhile(Predicate predicate) {
        int i = WhileOps.TAKE_FLAGS;
        Objects.requireNonNull(predicate);
        return new StatefulOp
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x000c: RETURN 
              (wrap:j$.util.stream.WhileOps$1Op:0x0009: CONSTRUCTOR 
              (r2v0 'this' j$.util.stream.ReferencePipeline A[IMMUTABLE_TYPE, THIS])
              (wrap:int:0x0007: SGET  A[WRAPPED] j$.util.stream.WhileOps.DROP_FLAGS int)
              (r3v0 'predicate' java.util.function.Predicate)
             A[MD:(j$.util.stream.ReferencePipeline, int, java.util.function.Predicate):void (m), WRAPPED] (LINE:397) call: j$.util.stream.WhileOps.1Op.<init>(j$.util.stream.ReferencePipeline, int, java.util.function.Predicate):void type: CONSTRUCTOR)
             (LINE:397) in method: j$.util.stream.ReferencePipeline.dropWhile(java.util.function.Predicate):j$.util.stream.Stream, file: classes4.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:310)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:94)
            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:406)
            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:335)
            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:301)
            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(Unknown Source)
            	at java.base/java.util.ArrayList.forEach(Unknown Source)
            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(Unknown Source)
            	at java.base/java.util.stream.Sink$ChainedReference.end(Unknown Source)
            	at java.base/java.util.stream.ReferencePipeline$7$1FlatMap.end(Unknown Source)
            	at java.base/java.util.stream.AbstractPipeline.copyInto(Unknown Source)
            	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(Unknown Source)
            	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(Unknown Source)
            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(Unknown Source)
            	at java.base/java.util.stream.AbstractPipeline.evaluate(Unknown Source)
            	at java.base/java.util.stream.ReferencePipeline.forEach(Unknown Source)
            	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:297)
            	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:286)
            	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:270)
            	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:161)
            	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:103)
            	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:45)
            	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:34)
            	at jadx.core.codegen.CodeGen.generate(CodeGen.java:22)
            	at jadx.core.ProcessClass.process(ProcessClass.java:79)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
            	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:402)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:390)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:310)
            Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Method arg registers not loaded: j$.util.stream.WhileOps.1Op.<init>(j$.util.stream.ReferencePipeline, int, java.util.function.Predicate):void, class status: GENERATED_AND_UNLOADED
            	at jadx.core.dex.nodes.MethodNode.getArgRegs(MethodNode.java:290)
            	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:829)
            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:730)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:418)
            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:145)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:121)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:108)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:368)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:303)
            	... 35 more
            */
        /*
            this = this;
            int r0 = j$.util.stream.WhileOps.TAKE_FLAGS
            j$.util.Objects.requireNonNull(r3)
            j$.util.stream.WhileOps$1Op r0 = new j$.util.stream.WhileOps$1Op
            int r1 = j$.util.stream.WhileOps.DROP_FLAGS
            r0.<init>(r2, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: j$.util.stream.ReferencePipeline.dropWhile(java.util.function.Predicate):j$.util.stream.Stream");
    }

    @Override // j$.util.stream.Stream
    public final Object[] toArray(IntFunction intFunction) {
        return Node.CC.flatten(evaluateToArrayNode(intFunction), intFunction).asArray(intFunction);
    }

    @Override // j$.util.stream.Stream
    public final Object[] toArray() {
        return toArray(new Node$$ExternalSyntheticLambda0(4));
    }

    @Override // j$.util.stream.Stream
    public final boolean anyMatch(Predicate predicate) {
        return ((Boolean) evaluate(Node.CC.makeRef(MatchOps$MatchKind.ANY, predicate))).booleanValue();
    }

    @Override // j$.util.stream.Stream
    public final boolean allMatch(Predicate predicate) {
        return ((Boolean) evaluate(Node.CC.makeRef(MatchOps$MatchKind.ALL, predicate))).booleanValue();
    }

    @Override // j$.util.stream.Stream
    public final boolean noneMatch(Predicate predicate) {
        return ((Boolean) evaluate(Node.CC.makeRef(MatchOps$MatchKind.NONE, predicate))).booleanValue();
    }

    @Override // j$.util.stream.Stream
    public final Object collect(Collector collector) {
        Object evaluate;
        if (!isParallel() || !collector.characteristics().contains(Collector.Characteristics.CONCURRENT) || (isOrdered() && !collector.characteristics().contains(Collector.Characteristics.UNORDERED))) {
            Supplier supplier = ((Collector) Objects.requireNonNull(collector)).supplier();
            evaluate = evaluate(new Node.CC(StreamShape.REFERENCE, collector.combiner(), collector.accumulator(), supplier, collector) { // from class: j$.util.stream.ReduceOps$3
                final /* synthetic */ BiConsumer val$accumulator;
                final /* synthetic */ Collector val$collector;
                final /* synthetic */ BinaryOperator val$combiner;
                final /* synthetic */ Supplier val$supplier;

                @Override // j$.util.stream.Node.CC
                public final ReduceOps$AccumulatingSink makeSink() {
                    return new ReduceOps$3ReducingSink(this.val$supplier, this.val$accumulator, this.val$combiner);
                }

                @Override // j$.util.stream.Node.CC, j$.util.stream.TerminalOp
                public final int getOpFlags() {
                    if (this.val$collector.characteristics().contains(Collector.Characteristics.UNORDERED)) {
                        return StreamOpFlag.NOT_ORDERED;
                    }
                    return 0;
                }

                ReduceOps$3(StreamShape streamShape, BinaryOperator binaryOperator, BiConsumer biConsumer, Supplier supplier2, Collector collector2) {
                    this.val$combiner = binaryOperator;
                    this.val$accumulator = biConsumer;
                    this.val$supplier = supplier2;
                    this.val$collector = collector2;
                }
            });
        } else {
            evaluate = collector2.supplier().get();
            forEach(new MatchOps$$ExternalSyntheticLambda3(3, collector2.accumulator(), evaluate));
        }
        return collector2.characteristics().contains(Collector.Characteristics.IDENTITY_FINISH) ? evaluate : collector2.finisher().apply(evaluate);
    }
}
