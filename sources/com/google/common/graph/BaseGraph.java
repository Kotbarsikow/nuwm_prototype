package com.google.common.graph;

import java.util.Set;

@ElementTypesAreNonnullByDefault
/* loaded from: classes2.dex */
interface BaseGraph<N> extends SuccessorsFunction<N>, PredecessorsFunction<N> {
    Set<N> adjacentNodes(N n);

    boolean allowsSelfLoops();

    int degree(N n);

    Set<EndpointPair<N>> edges();

    boolean hasEdgeConnecting(EndpointPair<N> endpointPair);

    boolean hasEdgeConnecting(N n, N n2);

    int inDegree(N n);

    ElementOrder<N> incidentEdgeOrder();

    Set<EndpointPair<N>> incidentEdges(N n);

    boolean isDirected();

    ElementOrder<N> nodeOrder();

    Set<N> nodes();

    int outDegree(N n);

    @Override // com.google.common.graph.PredecessorsFunction
    Set<N> predecessors(N n);

    @Override // com.google.common.graph.SuccessorsFunction
    Set<N> successors(N n);

    /* renamed from: com.google.common.graph.BaseGraph$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
    }
}
