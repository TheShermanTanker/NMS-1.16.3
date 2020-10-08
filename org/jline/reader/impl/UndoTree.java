/*    */ package org.jline.reader.impl;
/*    */ 
/*    */ import java.util.function.Consumer;
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
/*    */ public class UndoTree<T>
/*    */ {
/*    */   private final Consumer<T> state;
/*    */   private final Node parent;
/*    */   private Node current;
/*    */   
/*    */   public UndoTree(Consumer<T> s) {
/* 24 */     this.state = s;
/* 25 */     this.parent = new Node(null);
/* 26 */     this.parent.left = this.parent;
/* 27 */     clear();
/*    */   }
/*    */   
/*    */   public void clear() {
/* 31 */     this.current = this.parent;
/*    */   }
/*    */   
/*    */   public void newState(T state) {
/* 35 */     Node node = new Node(state);
/* 36 */     this.current.right = node;
/* 37 */     node.left = this.current;
/* 38 */     this.current = node;
/*    */   }
/*    */   
/*    */   public boolean canUndo() {
/* 42 */     return (this.current.left != this.parent);
/*    */   }
/*    */   
/*    */   public boolean canRedo() {
/* 46 */     return (this.current.right != null);
/*    */   }
/*    */   
/*    */   public void undo() {
/* 50 */     if (!canUndo()) {
/* 51 */       throw new IllegalStateException("Cannot undo.");
/*    */     }
/* 53 */     this.current = this.current.left;
/* 54 */     this.state.accept(this.current.state);
/*    */   }
/*    */   
/*    */   public void redo() {
/* 58 */     if (!canRedo()) {
/* 59 */       throw new IllegalStateException("Cannot redo.");
/*    */     }
/* 61 */     this.current = this.current.right;
/* 62 */     this.state.accept(this.current.state);
/*    */   }
/*    */   
/*    */   private class Node {
/*    */     private final T state;
/* 67 */     private Node left = null;
/* 68 */     private Node right = null;
/*    */     
/*    */     public Node(T s) {
/* 71 */       this.state = s;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jline\reader\impl\UndoTree.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */