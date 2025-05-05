package com.google.common.graph;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterators;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.graph.DirectedGraphConnections;
import com.google.common.graph.ElementOrder;
import j$.util.DesugarCollections;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
/* loaded from: classes2.dex */
final class DirectedGraphConnections<N, V> implements GraphConnections<N, V> {
    private static final Object PRED = new Object();
    private final Map<N, Object> adjacentNodeValues;

    @CheckForNull
    private final List<NodeConnection<N>> orderedNodeConnections;
    private int predecessorCount;
    private int successorCount;

    private static final class PredAndSucc {
        private final Object successorValue;

        PredAndSucc(Object obj) {
            this.successorValue = obj;
        }
    }

    static abstract class NodeConnection<N> {
        final N node;

        NodeConnection(N n) {
            this.node = (N) Preconditions.checkNotNull(n);
        }

        static final class Pred<N> extends NodeConnection<N> {
            Pred(N n) {
                super(n);
            }

            public boolean equals(@CheckForNull Object obj) {
                if (obj instanceof Pred) {
                    return this.node.equals(((Pred) obj).node);
                }
                return false;
            }

            public int hashCode() {
                return Pred.class.hashCode() + this.node.hashCode();
            }
        }

        static final class Succ<N> extends NodeConnection<N> {
            Succ(N n) {
                super(n);
            }

            public boolean equals(@CheckForNull Object obj) {
                if (obj instanceof Succ) {
                    return this.node.equals(((Succ) obj).node);
                }
                return false;
            }

            public int hashCode() {
                return Succ.class.hashCode() + this.node.hashCode();
            }
        }
    }

    private DirectedGraphConnections(Map<N, Object> map, @CheckForNull List<NodeConnection<N>> list, int i, int i2) {
        this.adjacentNodeValues = (Map) Preconditions.checkNotNull(map);
        this.orderedNodeConnections = list;
        this.predecessorCount = Graphs.checkNonNegative(i);
        this.successorCount = Graphs.checkNonNegative(i2);
        Preconditions.checkState(i <= map.size() && i2 <= map.size());
    }

    /* renamed from: com.google.common.graph.DirectedGraphConnections$5 */
    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] $SwitchMap$com$google$common$graph$ElementOrder$Type;

        static {
            int[] iArr = new int[ElementOrder.Type.values().length];
            $SwitchMap$com$google$common$graph$ElementOrder$Type = iArr;
            try {
                iArr[ElementOrder.Type.UNORDERED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$common$graph$ElementOrder$Type[ElementOrder.Type.STABLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    static <N, V> DirectedGraphConnections<N, V> of(ElementOrder<N> elementOrder) {
        ArrayList arrayList;
        int i = AnonymousClass5.$SwitchMap$com$google$common$graph$ElementOrder$Type[elementOrder.type().ordinal()];
        if (i == 1) {
            arrayList = null;
        } else if (i == 2) {
            arrayList = new ArrayList();
        } else {
            throw new AssertionError(elementOrder.type());
        }
        return new DirectedGraphConnections<>(new HashMap(4, 1.0f), arrayList, 0, 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    static <N, V> DirectedGraphConnections<N, V> ofImmutable(N n, Iterable<EndpointPair<N>> iterable, Function<N, V> function) {
        Preconditions.checkNotNull(n);
        Preconditions.checkNotNull(function);
        HashMap hashMap = new HashMap();
        ImmutableList.Builder builder = ImmutableList.builder();
        int i = 0;
        int i2 = 0;
        for (EndpointPair<N> endpointPair : iterable) {
            if (endpointPair.nodeU().equals(n) && endpointPair.nodeV().equals(n)) {
                hashMap.put(n, new PredAndSucc(function.apply(n)));
                builder.add((ImmutableList.Builder) new NodeConnection.Pred(n));
                builder.add((ImmutableList.Builder) new NodeConnection.Succ(n));
                i++;
            } else if (endpointPair.nodeV().equals(n)) {
                N nodeU = endpointPair.nodeU();
                Object put = hashMap.put(nodeU, PRED);
                if (put != null) {
                    hashMap.put(nodeU, new PredAndSucc(put));
                }
                builder.add((ImmutableList.Builder) new NodeConnection.Pred(nodeU));
                i++;
            } else {
                Preconditions.checkArgument(endpointPair.nodeU().equals(n));
                N nodeV = endpointPair.nodeV();
                V apply = function.apply(nodeV);
                Object put2 = hashMap.put(nodeV, apply);
                if (put2 != null) {
                    Preconditions.checkArgument(put2 == PRED);
                    hashMap.put(nodeV, new PredAndSucc(apply));
                }
                builder.add((ImmutableList.Builder) new NodeConnection.Succ(nodeV));
            }
            i2++;
        }
        return new DirectedGraphConnections<>(hashMap, builder.build(), i, i2);
    }

    @Override // com.google.common.graph.GraphConnections
    public Set<N> adjacentNodes() {
        if (this.orderedNodeConnections == null) {
            return DesugarCollections.unmodifiableSet(this.adjacentNodeValues.keySet());
        }
        return new AbstractSet<N>() { // from class: com.google.common.graph.DirectedGraphConnections.1
            AnonymousClass1() {
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public UnmodifiableIterator<N> iterator() {
                return new AbstractIterator<N>(this) { // from class: com.google.common.graph.DirectedGraphConnections.1.1
                    final /* synthetic */ Iterator val$nodeConnections;
                    final /* synthetic */ Set val$seenNodes;

                    C00371(AnonymousClass1 this, Iterator it, Set set) {
                        r2 = it;
                        r3 = set;
                    }

                    @Override // com.google.common.collect.AbstractIterator
                    @CheckForNull
                    protected N computeNext() {
                        while (r2.hasNext()) {
                            NodeConnection nodeConnection = (NodeConnection) r2.next();
                            if (r3.add(nodeConnection.node)) {
                                return nodeConnection.node;
                            }
                        }
                        return endOfData();
                    }
                };
            }

            /* renamed from: com.google.common.graph.DirectedGraphConnections$1$1 */
            class C00371 extends AbstractIterator<N> {
                final /* synthetic */ Iterator val$nodeConnections;
                final /* synthetic */ Set val$seenNodes;

                C00371(AnonymousClass1 this, Iterator it, Set set) {
                    r2 = it;
                    r3 = set;
                }

                @Override // com.google.common.collect.AbstractIterator
                @CheckForNull
                protected N computeNext() {
                    while (r2.hasNext()) {
                        NodeConnection nodeConnection = (NodeConnection) r2.next();
                        if (r3.add(nodeConnection.node)) {
                            return nodeConnection.node;
                        }
                    }
                    return endOfData();
                }
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                return DirectedGraphConnections.this.adjacentNodeValues.size();
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean contains(@CheckForNull Object obj) {
                return DirectedGraphConnections.this.adjacentNodeValues.containsKey(obj);
            }
        };
    }

    /* renamed from: com.google.common.graph.DirectedGraphConnections$1 */
    class AnonymousClass1 extends AbstractSet<N> {
        AnonymousClass1() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public UnmodifiableIterator<N> iterator() {
            return new AbstractIterator<N>(this) { // from class: com.google.common.graph.DirectedGraphConnections.1.1
                final /* synthetic */ Iterator val$nodeConnections;
                final /* synthetic */ Set val$seenNodes;

                C00371(AnonymousClass1 this, Iterator it, Set set) {
                    r2 = it;
                    r3 = set;
                }

                @Override // com.google.common.collect.AbstractIterator
                @CheckForNull
                protected N computeNext() {
                    while (r2.hasNext()) {
                        NodeConnection nodeConnection = (NodeConnection) r2.next();
                        if (r3.add(nodeConnection.node)) {
                            return nodeConnection.node;
                        }
                    }
                    return endOfData();
                }
            };
        }

        /* renamed from: com.google.common.graph.DirectedGraphConnections$1$1 */
        class C00371 extends AbstractIterator<N> {
            final /* synthetic */ Iterator val$nodeConnections;
            final /* synthetic */ Set val$seenNodes;

            C00371(AnonymousClass1 this, Iterator it, Set set) {
                r2 = it;
                r3 = set;
            }

            @Override // com.google.common.collect.AbstractIterator
            @CheckForNull
            protected N computeNext() {
                while (r2.hasNext()) {
                    NodeConnection nodeConnection = (NodeConnection) r2.next();
                    if (r3.add(nodeConnection.node)) {
                        return nodeConnection.node;
                    }
                }
                return endOfData();
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return DirectedGraphConnections.this.adjacentNodeValues.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(@CheckForNull Object obj) {
            return DirectedGraphConnections.this.adjacentNodeValues.containsKey(obj);
        }
    }

    /* renamed from: com.google.common.graph.DirectedGraphConnections$2 */
    class AnonymousClass2 extends AbstractSet<N> {
        AnonymousClass2() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public UnmodifiableIterator<N> iterator() {
            if (DirectedGraphConnections.this.orderedNodeConnections == null) {
                return new AbstractIterator<N>(this) { // from class: com.google.common.graph.DirectedGraphConnections.2.1
                    final /* synthetic */ Iterator val$entries;

                    AnonymousClass1(AnonymousClass2 this, Iterator it) {
                        r2 = it;
                    }

                    @Override // com.google.common.collect.AbstractIterator
                    @CheckForNull
                    protected N computeNext() {
                        while (r2.hasNext()) {
                            Map.Entry entry = (Map.Entry) r2.next();
                            if (DirectedGraphConnections.isPredecessor(entry.getValue())) {
                                return (N) entry.getKey();
                            }
                        }
                        return endOfData();
                    }
                };
            }
            return new AbstractIterator<N>(this) { // from class: com.google.common.graph.DirectedGraphConnections.2.2
                final /* synthetic */ Iterator val$nodeConnections;

                C00382(AnonymousClass2 this, Iterator it) {
                    r2 = it;
                }

                @Override // com.google.common.collect.AbstractIterator
                @CheckForNull
                protected N computeNext() {
                    while (r2.hasNext()) {
                        NodeConnection nodeConnection = (NodeConnection) r2.next();
                        if (nodeConnection instanceof NodeConnection.Pred) {
                            return nodeConnection.node;
                        }
                    }
                    return endOfData();
                }
            };
        }

        /* renamed from: com.google.common.graph.DirectedGraphConnections$2$1 */
        class AnonymousClass1 extends AbstractIterator<N> {
            final /* synthetic */ Iterator val$entries;

            AnonymousClass1(AnonymousClass2 this, Iterator it) {
                r2 = it;
            }

            @Override // com.google.common.collect.AbstractIterator
            @CheckForNull
            protected N computeNext() {
                while (r2.hasNext()) {
                    Map.Entry entry = (Map.Entry) r2.next();
                    if (DirectedGraphConnections.isPredecessor(entry.getValue())) {
                        return (N) entry.getKey();
                    }
                }
                return endOfData();
            }
        }

        /* renamed from: com.google.common.graph.DirectedGraphConnections$2$2 */
        class C00382 extends AbstractIterator<N> {
            final /* synthetic */ Iterator val$nodeConnections;

            C00382(AnonymousClass2 this, Iterator it) {
                r2 = it;
            }

            @Override // com.google.common.collect.AbstractIterator
            @CheckForNull
            protected N computeNext() {
                while (r2.hasNext()) {
                    NodeConnection nodeConnection = (NodeConnection) r2.next();
                    if (nodeConnection instanceof NodeConnection.Pred) {
                        return nodeConnection.node;
                    }
                }
                return endOfData();
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return DirectedGraphConnections.this.predecessorCount;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(@CheckForNull Object obj) {
            return DirectedGraphConnections.isPredecessor(DirectedGraphConnections.this.adjacentNodeValues.get(obj));
        }
    }

    @Override // com.google.common.graph.GraphConnections
    public Set<N> predecessors() {
        return new AbstractSet<N>() { // from class: com.google.common.graph.DirectedGraphConnections.2
            AnonymousClass2() {
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public UnmodifiableIterator<N> iterator() {
                if (DirectedGraphConnections.this.orderedNodeConnections == null) {
                    return new AbstractIterator<N>(this) { // from class: com.google.common.graph.DirectedGraphConnections.2.1
                        final /* synthetic */ Iterator val$entries;

                        AnonymousClass1(AnonymousClass2 this, Iterator it) {
                            r2 = it;
                        }

                        @Override // com.google.common.collect.AbstractIterator
                        @CheckForNull
                        protected N computeNext() {
                            while (r2.hasNext()) {
                                Map.Entry entry = (Map.Entry) r2.next();
                                if (DirectedGraphConnections.isPredecessor(entry.getValue())) {
                                    return (N) entry.getKey();
                                }
                            }
                            return endOfData();
                        }
                    };
                }
                return new AbstractIterator<N>(this) { // from class: com.google.common.graph.DirectedGraphConnections.2.2
                    final /* synthetic */ Iterator val$nodeConnections;

                    C00382(AnonymousClass2 this, Iterator it) {
                        r2 = it;
                    }

                    @Override // com.google.common.collect.AbstractIterator
                    @CheckForNull
                    protected N computeNext() {
                        while (r2.hasNext()) {
                            NodeConnection nodeConnection = (NodeConnection) r2.next();
                            if (nodeConnection instanceof NodeConnection.Pred) {
                                return nodeConnection.node;
                            }
                        }
                        return endOfData();
                    }
                };
            }

            /* renamed from: com.google.common.graph.DirectedGraphConnections$2$1 */
            class AnonymousClass1 extends AbstractIterator<N> {
                final /* synthetic */ Iterator val$entries;

                AnonymousClass1(AnonymousClass2 this, Iterator it) {
                    r2 = it;
                }

                @Override // com.google.common.collect.AbstractIterator
                @CheckForNull
                protected N computeNext() {
                    while (r2.hasNext()) {
                        Map.Entry entry = (Map.Entry) r2.next();
                        if (DirectedGraphConnections.isPredecessor(entry.getValue())) {
                            return (N) entry.getKey();
                        }
                    }
                    return endOfData();
                }
            }

            /* renamed from: com.google.common.graph.DirectedGraphConnections$2$2 */
            class C00382 extends AbstractIterator<N> {
                final /* synthetic */ Iterator val$nodeConnections;

                C00382(AnonymousClass2 this, Iterator it) {
                    r2 = it;
                }

                @Override // com.google.common.collect.AbstractIterator
                @CheckForNull
                protected N computeNext() {
                    while (r2.hasNext()) {
                        NodeConnection nodeConnection = (NodeConnection) r2.next();
                        if (nodeConnection instanceof NodeConnection.Pred) {
                            return nodeConnection.node;
                        }
                    }
                    return endOfData();
                }
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                return DirectedGraphConnections.this.predecessorCount;
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean contains(@CheckForNull Object obj) {
                return DirectedGraphConnections.isPredecessor(DirectedGraphConnections.this.adjacentNodeValues.get(obj));
            }
        };
    }

    /* renamed from: com.google.common.graph.DirectedGraphConnections$3 */
    class AnonymousClass3 extends AbstractSet<N> {
        AnonymousClass3() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public UnmodifiableIterator<N> iterator() {
            if (DirectedGraphConnections.this.orderedNodeConnections == null) {
                return new AbstractIterator<N>(this) { // from class: com.google.common.graph.DirectedGraphConnections.3.1
                    final /* synthetic */ Iterator val$entries;

                    AnonymousClass1(AnonymousClass3 this, Iterator it) {
                        r2 = it;
                    }

                    @Override // com.google.common.collect.AbstractIterator
                    @CheckForNull
                    protected N computeNext() {
                        while (r2.hasNext()) {
                            Map.Entry entry = (Map.Entry) r2.next();
                            if (DirectedGraphConnections.isSuccessor(entry.getValue())) {
                                return (N) entry.getKey();
                            }
                        }
                        return endOfData();
                    }
                };
            }
            return new AbstractIterator<N>(this) { // from class: com.google.common.graph.DirectedGraphConnections.3.2
                final /* synthetic */ Iterator val$nodeConnections;

                AnonymousClass2(AnonymousClass3 this, Iterator it) {
                    r2 = it;
                }

                @Override // com.google.common.collect.AbstractIterator
                @CheckForNull
                protected N computeNext() {
                    while (r2.hasNext()) {
                        NodeConnection nodeConnection = (NodeConnection) r2.next();
                        if (nodeConnection instanceof NodeConnection.Succ) {
                            return nodeConnection.node;
                        }
                    }
                    return endOfData();
                }
            };
        }

        /* renamed from: com.google.common.graph.DirectedGraphConnections$3$1 */
        class AnonymousClass1 extends AbstractIterator<N> {
            final /* synthetic */ Iterator val$entries;

            AnonymousClass1(AnonymousClass3 this, Iterator it) {
                r2 = it;
            }

            @Override // com.google.common.collect.AbstractIterator
            @CheckForNull
            protected N computeNext() {
                while (r2.hasNext()) {
                    Map.Entry entry = (Map.Entry) r2.next();
                    if (DirectedGraphConnections.isSuccessor(entry.getValue())) {
                        return (N) entry.getKey();
                    }
                }
                return endOfData();
            }
        }

        /* renamed from: com.google.common.graph.DirectedGraphConnections$3$2 */
        class AnonymousClass2 extends AbstractIterator<N> {
            final /* synthetic */ Iterator val$nodeConnections;

            AnonymousClass2(AnonymousClass3 this, Iterator it) {
                r2 = it;
            }

            @Override // com.google.common.collect.AbstractIterator
            @CheckForNull
            protected N computeNext() {
                while (r2.hasNext()) {
                    NodeConnection nodeConnection = (NodeConnection) r2.next();
                    if (nodeConnection instanceof NodeConnection.Succ) {
                        return nodeConnection.node;
                    }
                }
                return endOfData();
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return DirectedGraphConnections.this.successorCount;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(@CheckForNull Object obj) {
            return DirectedGraphConnections.isSuccessor(DirectedGraphConnections.this.adjacentNodeValues.get(obj));
        }
    }

    @Override // com.google.common.graph.GraphConnections
    public Set<N> successors() {
        return new AbstractSet<N>() { // from class: com.google.common.graph.DirectedGraphConnections.3
            AnonymousClass3() {
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public UnmodifiableIterator<N> iterator() {
                if (DirectedGraphConnections.this.orderedNodeConnections == null) {
                    return new AbstractIterator<N>(this) { // from class: com.google.common.graph.DirectedGraphConnections.3.1
                        final /* synthetic */ Iterator val$entries;

                        AnonymousClass1(AnonymousClass3 this, Iterator it) {
                            r2 = it;
                        }

                        @Override // com.google.common.collect.AbstractIterator
                        @CheckForNull
                        protected N computeNext() {
                            while (r2.hasNext()) {
                                Map.Entry entry = (Map.Entry) r2.next();
                                if (DirectedGraphConnections.isSuccessor(entry.getValue())) {
                                    return (N) entry.getKey();
                                }
                            }
                            return endOfData();
                        }
                    };
                }
                return new AbstractIterator<N>(this) { // from class: com.google.common.graph.DirectedGraphConnections.3.2
                    final /* synthetic */ Iterator val$nodeConnections;

                    AnonymousClass2(AnonymousClass3 this, Iterator it) {
                        r2 = it;
                    }

                    @Override // com.google.common.collect.AbstractIterator
                    @CheckForNull
                    protected N computeNext() {
                        while (r2.hasNext()) {
                            NodeConnection nodeConnection = (NodeConnection) r2.next();
                            if (nodeConnection instanceof NodeConnection.Succ) {
                                return nodeConnection.node;
                            }
                        }
                        return endOfData();
                    }
                };
            }

            /* renamed from: com.google.common.graph.DirectedGraphConnections$3$1 */
            class AnonymousClass1 extends AbstractIterator<N> {
                final /* synthetic */ Iterator val$entries;

                AnonymousClass1(AnonymousClass3 this, Iterator it) {
                    r2 = it;
                }

                @Override // com.google.common.collect.AbstractIterator
                @CheckForNull
                protected N computeNext() {
                    while (r2.hasNext()) {
                        Map.Entry entry = (Map.Entry) r2.next();
                        if (DirectedGraphConnections.isSuccessor(entry.getValue())) {
                            return (N) entry.getKey();
                        }
                    }
                    return endOfData();
                }
            }

            /* renamed from: com.google.common.graph.DirectedGraphConnections$3$2 */
            class AnonymousClass2 extends AbstractIterator<N> {
                final /* synthetic */ Iterator val$nodeConnections;

                AnonymousClass2(AnonymousClass3 this, Iterator it) {
                    r2 = it;
                }

                @Override // com.google.common.collect.AbstractIterator
                @CheckForNull
                protected N computeNext() {
                    while (r2.hasNext()) {
                        NodeConnection nodeConnection = (NodeConnection) r2.next();
                        if (nodeConnection instanceof NodeConnection.Succ) {
                            return nodeConnection.node;
                        }
                    }
                    return endOfData();
                }
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                return DirectedGraphConnections.this.successorCount;
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean contains(@CheckForNull Object obj) {
                return DirectedGraphConnections.isSuccessor(DirectedGraphConnections.this.adjacentNodeValues.get(obj));
            }
        };
    }

    @Override // com.google.common.graph.GraphConnections
    public Iterator<EndpointPair<N>> incidentEdgeIterator(N n) {
        Iterator transform;
        Preconditions.checkNotNull(n);
        List<NodeConnection<N>> list = this.orderedNodeConnections;
        if (list == null) {
            transform = Iterators.concat(Iterators.transform(predecessors().iterator(), new Function() { // from class: com.google.common.graph.DirectedGraphConnections$$ExternalSyntheticLambda0
                public final /* synthetic */ Object f$0;

                public /* synthetic */ DirectedGraphConnections$$ExternalSyntheticLambda0(Object n2) {
                    r1 = n2;
                }

                @Override // com.google.common.base.Function
                public final Object apply(Object obj) {
                    EndpointPair ordered;
                    ordered = EndpointPair.ordered(obj, r1);
                    return ordered;
                }
            }), Iterators.transform(successors().iterator(), new Function() { // from class: com.google.common.graph.DirectedGraphConnections$$ExternalSyntheticLambda1
                public final /* synthetic */ Object f$0;

                public /* synthetic */ DirectedGraphConnections$$ExternalSyntheticLambda1(Object n2) {
                    r1 = n2;
                }

                @Override // com.google.common.base.Function
                public final Object apply(Object obj) {
                    EndpointPair ordered;
                    ordered = EndpointPair.ordered(r1, obj);
                    return ordered;
                }
            }));
        } else {
            transform = Iterators.transform(list.iterator(), new Function() { // from class: com.google.common.graph.DirectedGraphConnections$$ExternalSyntheticLambda2
                public final /* synthetic */ Object f$0;

                public /* synthetic */ DirectedGraphConnections$$ExternalSyntheticLambda2(Object n2) {
                    r1 = n2;
                }

                @Override // com.google.common.base.Function
                public final Object apply(Object obj) {
                    return DirectedGraphConnections.lambda$incidentEdgeIterator$2(r1, (DirectedGraphConnections.NodeConnection) obj);
                }
            });
        }
        return new AbstractIterator<EndpointPair<N>>(this) { // from class: com.google.common.graph.DirectedGraphConnections.4
            final /* synthetic */ AtomicBoolean val$alreadySeenSelfLoop;
            final /* synthetic */ Iterator val$resultWithDoubleSelfLoop;

            AnonymousClass4(DirectedGraphConnections this, Iterator transform2, AtomicBoolean atomicBoolean) {
                r2 = transform2;
                r3 = atomicBoolean;
            }

            @Override // com.google.common.collect.AbstractIterator
            @CheckForNull
            public EndpointPair<N> computeNext() {
                while (r2.hasNext()) {
                    EndpointPair<N> endpointPair = (EndpointPair) r2.next();
                    if (!endpointPair.nodeU().equals(endpointPair.nodeV()) || !r3.getAndSet(true)) {
                        return endpointPair;
                    }
                }
                return endOfData();
            }
        };
    }

    static /* synthetic */ EndpointPair lambda$incidentEdgeIterator$2(Object obj, NodeConnection nodeConnection) {
        if (nodeConnection instanceof NodeConnection.Succ) {
            return EndpointPair.ordered(obj, nodeConnection.node);
        }
        return EndpointPair.ordered(nodeConnection.node, obj);
    }

    /* renamed from: com.google.common.graph.DirectedGraphConnections$4 */
    class AnonymousClass4 extends AbstractIterator<EndpointPair<N>> {
        final /* synthetic */ AtomicBoolean val$alreadySeenSelfLoop;
        final /* synthetic */ Iterator val$resultWithDoubleSelfLoop;

        AnonymousClass4(DirectedGraphConnections this, Iterator transform2, AtomicBoolean atomicBoolean) {
            r2 = transform2;
            r3 = atomicBoolean;
        }

        @Override // com.google.common.collect.AbstractIterator
        @CheckForNull
        public EndpointPair<N> computeNext() {
            while (r2.hasNext()) {
                EndpointPair<N> endpointPair = (EndpointPair) r2.next();
                if (!endpointPair.nodeU().equals(endpointPair.nodeV()) || !r3.getAndSet(true)) {
                    return endpointPair;
                }
            }
            return endOfData();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.GraphConnections
    @CheckForNull
    public V value(N n) {
        Preconditions.checkNotNull(n);
        V v = (V) this.adjacentNodeValues.get(n);
        if (v == PRED) {
            return null;
        }
        return v instanceof PredAndSucc ? (V) ((PredAndSucc) v).successorValue : v;
    }

    @Override // com.google.common.graph.GraphConnections
    public void removePredecessor(N n) {
        Preconditions.checkNotNull(n);
        Object obj = this.adjacentNodeValues.get(n);
        if (obj == PRED) {
            this.adjacentNodeValues.remove(n);
        } else if (!(obj instanceof PredAndSucc)) {
            return;
        } else {
            this.adjacentNodeValues.put(n, ((PredAndSucc) obj).successorValue);
        }
        int i = this.predecessorCount - 1;
        this.predecessorCount = i;
        Graphs.checkNonNegative(i);
        List<NodeConnection<N>> list = this.orderedNodeConnections;
        if (list != null) {
            list.remove(new NodeConnection.Pred(n));
        }
    }

    @Override // com.google.common.graph.GraphConnections
    @CheckForNull
    public V removeSuccessor(Object obj) {
        Object obj2;
        Preconditions.checkNotNull(obj);
        Object obj3 = this.adjacentNodeValues.get(obj);
        if (obj3 == null || obj3 == (obj2 = PRED)) {
            obj3 = null;
        } else if (obj3 instanceof PredAndSucc) {
            this.adjacentNodeValues.put(obj, obj2);
            obj3 = ((PredAndSucc) obj3).successorValue;
        } else {
            this.adjacentNodeValues.remove(obj);
        }
        if (obj3 != null) {
            int i = this.successorCount - 1;
            this.successorCount = i;
            Graphs.checkNonNegative(i);
            List<NodeConnection<N>> list = this.orderedNodeConnections;
            if (list != null) {
                list.remove(new NodeConnection.Succ(obj));
            }
        }
        if (obj3 == null) {
            return null;
        }
        return (V) obj3;
    }

    @Override // com.google.common.graph.GraphConnections
    public void addPredecessor(N n, V v) {
        Map<N, Object> map = this.adjacentNodeValues;
        Object obj = PRED;
        Object put = map.put(n, obj);
        if (put != null) {
            if (put instanceof PredAndSucc) {
                this.adjacentNodeValues.put(n, put);
                return;
            } else if (put == obj) {
                return;
            } else {
                this.adjacentNodeValues.put(n, new PredAndSucc(put));
            }
        }
        int i = this.predecessorCount + 1;
        this.predecessorCount = i;
        Graphs.checkPositive(i);
        List<NodeConnection<N>> list = this.orderedNodeConnections;
        if (list != null) {
            list.add(new NodeConnection.Pred(n));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:5:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0049  */
    @Override // com.google.common.graph.GraphConnections
    @javax.annotation.CheckForNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public V addSuccessor(N r5, V r6) {
        /*
            r4 = this;
            java.util.Map<N, java.lang.Object> r0 = r4.adjacentNodeValues
            java.lang.Object r0 = r0.put(r5, r6)
            r1 = 0
            if (r0 != 0) goto Lb
        L9:
            r0 = r1
            goto L2f
        Lb:
            boolean r2 = r0 instanceof com.google.common.graph.DirectedGraphConnections.PredAndSucc
            if (r2 == 0) goto L20
            java.util.Map<N, java.lang.Object> r2 = r4.adjacentNodeValues
            com.google.common.graph.DirectedGraphConnections$PredAndSucc r3 = new com.google.common.graph.DirectedGraphConnections$PredAndSucc
            r3.<init>(r6)
            r2.put(r5, r3)
            com.google.common.graph.DirectedGraphConnections$PredAndSucc r0 = (com.google.common.graph.DirectedGraphConnections.PredAndSucc) r0
            java.lang.Object r0 = com.google.common.graph.DirectedGraphConnections.PredAndSucc.access$600(r0)
            goto L2f
        L20:
            java.lang.Object r2 = com.google.common.graph.DirectedGraphConnections.PRED
            if (r0 != r2) goto L2f
            java.util.Map<N, java.lang.Object> r0 = r4.adjacentNodeValues
            com.google.common.graph.DirectedGraphConnections$PredAndSucc r2 = new com.google.common.graph.DirectedGraphConnections$PredAndSucc
            r2.<init>(r6)
            r0.put(r5, r2)
            goto L9
        L2f:
            if (r0 != 0) goto L46
            int r6 = r4.successorCount
            int r6 = r6 + 1
            r4.successorCount = r6
            com.google.common.graph.Graphs.checkPositive(r6)
            java.util.List<com.google.common.graph.DirectedGraphConnections$NodeConnection<N>> r6 = r4.orderedNodeConnections
            if (r6 == 0) goto L46
            com.google.common.graph.DirectedGraphConnections$NodeConnection$Succ r2 = new com.google.common.graph.DirectedGraphConnections$NodeConnection$Succ
            r2.<init>(r5)
            r6.add(r2)
        L46:
            if (r0 != 0) goto L49
            goto L4a
        L49:
            r1 = r0
        L4a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.graph.DirectedGraphConnections.addSuccessor(java.lang.Object, java.lang.Object):java.lang.Object");
    }

    public static boolean isPredecessor(@CheckForNull Object obj) {
        return obj == PRED || (obj instanceof PredAndSucc);
    }

    public static boolean isSuccessor(@CheckForNull Object obj) {
        return (obj == PRED || obj == null) ? false : true;
    }
}
