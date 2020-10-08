/*    */ package org.yaml.snakeyaml.nodes;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AnchorNode
/*    */   extends Node
/*    */ {
/*    */   private Node realNode;
/*    */   
/*    */   public AnchorNode(Node realNode) {
/* 26 */     super(realNode.getTag(), realNode.getStartMark(), realNode.getEndMark());
/* 27 */     this.realNode = realNode;
/*    */   }
/*    */ 
/*    */   
/*    */   public NodeId getNodeId() {
/* 32 */     return NodeId.anchor;
/*    */   }
/*    */   
/*    */   public Node getRealNode() {
/* 36 */     return this.realNode;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\yaml\snakeyaml\nodes\AnchorNode.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */