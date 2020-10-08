/*    */ package org.bukkit.craftbukkit.libs.org.objectweb.asm.tree;
/*    */ 
/*    */ import java.util.Map;
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
/*    */ public class MultiANewArrayInsnNode
/*    */   extends AbstractInsnNode
/*    */ {
/*    */   public String desc;
/*    */   public int dims;
/*    */   
/*    */   public MultiANewArrayInsnNode(String descriptor, int numDimensions) {
/* 54 */     super(197);
/* 55 */     this.desc = descriptor;
/* 56 */     this.dims = numDimensions;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getType() {
/* 61 */     return 13;
/*    */   }
/*    */ 
/*    */   
/*    */   public void accept(MethodVisitor methodVisitor) {
/* 66 */     methodVisitor.visitMultiANewArrayInsn(this.desc, this.dims);
/* 67 */     acceptAnnotations(methodVisitor);
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractInsnNode clone(Map<LabelNode, LabelNode> clonedLabels) {
/* 72 */     return (new MultiANewArrayInsnNode(this.desc, this.dims)).cloneAnnotations(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\objectweb\asm\tree\MultiANewArrayInsnNode.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */