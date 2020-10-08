/*     */ package com.google.common.graph;
/*     */ 
/*     */ import com.google.common.annotations.Beta;
/*     */ import com.google.common.base.Function;
/*     */ import com.google.common.base.Preconditions;
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Beta
/*     */ public final class ImmutableNetwork<N, E>
/*     */   extends ConfigurableNetwork<N, E>
/*     */ {
/*     */   private ImmutableNetwork(Network<N, E> network) {
/*  48 */     super(
/*  49 */         NetworkBuilder.from(network), getNodeConnections(network), getEdgeToReferenceNode(network));
/*     */   }
/*     */ 
/*     */   
/*     */   public static <N, E> ImmutableNetwork<N, E> copyOf(Network<N, E> network) {
/*  54 */     return (network instanceof ImmutableNetwork) ? (ImmutableNetwork<N, E>)network : new ImmutableNetwork<>(network);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static <N, E> ImmutableNetwork<N, E> copyOf(ImmutableNetwork<N, E> network) {
/*  66 */     return (ImmutableNetwork<N, E>)Preconditions.checkNotNull(network);
/*     */   }
/*     */ 
/*     */   
/*     */   public ImmutableGraph<N> asGraph() {
/*  71 */     final Graph<N> asGraph = super.asGraph();
/*  72 */     return new ImmutableGraph<N>()
/*     */       {
/*     */         protected Graph<N> delegate() {
/*  75 */           return asGraph;
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static <N, E> Map<N, NetworkConnections<N, E>> getNodeConnections(Network<N, E> network) {
/*  84 */     ImmutableMap.Builder<N, NetworkConnections<N, E>> nodeConnections = ImmutableMap.builder();
/*  85 */     for (N node : network.nodes()) {
/*  86 */       nodeConnections.put(node, connectionsOf(network, node));
/*     */     }
/*  88 */     return (Map<N, NetworkConnections<N, E>>)nodeConnections.build();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static <N, E> Map<E, N> getEdgeToReferenceNode(Network<N, E> network) {
/*  95 */     ImmutableMap.Builder<E, N> edgeToReferenceNode = ImmutableMap.builder();
/*  96 */     for (E edge : network.edges()) {
/*  97 */       edgeToReferenceNode.put(edge, network.incidentNodes(edge).nodeU());
/*     */     }
/*  99 */     return (Map<E, N>)edgeToReferenceNode.build();
/*     */   }
/*     */   
/*     */   private static <N, E> NetworkConnections<N, E> connectionsOf(Network<N, E> network, N node) {
/* 103 */     if (network.isDirected()) {
/* 104 */       Map<E, N> inEdgeMap = Maps.asMap(network.inEdges(node), sourceNodeFn(network));
/* 105 */       Map<E, N> outEdgeMap = Maps.asMap(network.outEdges(node), targetNodeFn(network));
/* 106 */       int selfLoopCount = network.edgesConnecting(node, node).size();
/* 107 */       return network.allowsParallelEdges() ? 
/* 108 */         DirectedMultiNetworkConnections.<N, E>ofImmutable(inEdgeMap, outEdgeMap, selfLoopCount) : 
/* 109 */         DirectedNetworkConnections.<N, E>ofImmutable(inEdgeMap, outEdgeMap, selfLoopCount);
/*     */     } 
/*     */     
/* 112 */     Map<E, N> incidentEdgeMap = Maps.asMap(network.incidentEdges(node), adjacentNodeFn(network, node));
/* 113 */     return network.allowsParallelEdges() ? 
/* 114 */       UndirectedMultiNetworkConnections.<N, E>ofImmutable(incidentEdgeMap) : 
/* 115 */       UndirectedNetworkConnections.<N, E>ofImmutable(incidentEdgeMap);
/*     */   }
/*     */ 
/*     */   
/*     */   private static <N, E> Function<E, N> sourceNodeFn(final Network<N, E> network) {
/* 120 */     return new Function<E, N>()
/*     */       {
/*     */         public N apply(E edge) {
/* 123 */           return network.incidentNodes(edge).source();
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   private static <N, E> Function<E, N> targetNodeFn(final Network<N, E> network) {
/* 129 */     return new Function<E, N>()
/*     */       {
/*     */         public N apply(E edge) {
/* 132 */           return network.incidentNodes(edge).target();
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   private static <N, E> Function<E, N> adjacentNodeFn(final Network<N, E> network, final N node) {
/* 138 */     return new Function<E, N>()
/*     */       {
/*     */         public N apply(E edge) {
/* 141 */           return network.incidentNodes(edge).adjacentNode(node);
/*     */         }
/*     */       };
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\google\common\graph\ImmutableNetwork.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */