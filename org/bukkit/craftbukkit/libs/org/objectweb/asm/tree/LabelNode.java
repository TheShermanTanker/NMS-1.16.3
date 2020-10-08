/*    */ package org.bukkit.craftbukkit.libs.org.objectweb.asm.tree;
/*    */ 
/*    */ import java.util.Map;
/*    */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.Label;
/*    */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.MethodVisitor;
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
/*    */ public class LabelNode
/*    */   extends AbstractInsnNode
/*    */ {
/*    */   private Label value;
/*    */   
/*    */   public LabelNode() {
/* 40 */     super(-1);
/*    */   }
/*    */   
/*    */   public LabelNode(Label label) {
/* 44 */     super(-1);
/* 45 */     this.value = label;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getType() {
/* 50 */     return 8;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Label getLabel() {
/* 60 */     if (this.value == null) {
/* 61 */       this.value = new Label();
/*    */     }
/* 63 */     return this.value;
/*    */   }
/*    */ 
/*    */   
/*    */   public void accept(MethodVisitor methodVisitor) {
/* 68 */     methodVisitor.visitLabel(getLabel());
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractInsnNode clone(Map<LabelNode, LabelNode> clonedLabels) {
/* 73 */     return clonedLabels.get(this);
/*    */   }
/*    */   
/*    */   public void resetLabel() {
/* 77 */     this.value = null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\objectweb\asm\tree\LabelNode.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */