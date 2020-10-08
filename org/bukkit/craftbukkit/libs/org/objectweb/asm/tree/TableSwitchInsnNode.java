/*    */ package org.bukkit.craftbukkit.libs.org.objectweb.asm.tree;
/*    */ 
/*    */ import java.util.List;
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
/*    */ public class TableSwitchInsnNode
/*    */   extends AbstractInsnNode
/*    */ {
/*    */   public int min;
/*    */   public int max;
/*    */   public LabelNode dflt;
/*    */   public List<LabelNode> labels;
/*    */   
/*    */   public TableSwitchInsnNode(int min, int max, LabelNode dflt, LabelNode... labels) {
/* 66 */     super(170);
/* 67 */     this.min = min;
/* 68 */     this.max = max;
/* 69 */     this.dflt = dflt;
/* 70 */     this.labels = Util.asArrayList(labels);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getType() {
/* 75 */     return 11;
/*    */   }
/*    */ 
/*    */   
/*    */   public void accept(MethodVisitor methodVisitor) {
/* 80 */     Label[] labelsArray = new Label[this.labels.size()];
/* 81 */     for (int i = 0, n = labelsArray.length; i < n; i++) {
/* 82 */       labelsArray[i] = ((LabelNode)this.labels.get(i)).getLabel();
/*    */     }
/* 84 */     methodVisitor.visitTableSwitchInsn(this.min, this.max, this.dflt.getLabel(), labelsArray);
/* 85 */     acceptAnnotations(methodVisitor);
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractInsnNode clone(Map<LabelNode, LabelNode> clonedLabels) {
/* 90 */     return (new TableSwitchInsnNode(this.min, this.max, clone(this.dflt, clonedLabels), clone(this.labels, clonedLabels)))
/* 91 */       .cloneAnnotations(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\objectweb\asm\tree\TableSwitchInsnNode.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */