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
/*    */ public class LookupSwitchInsnNode
/*    */   extends AbstractInsnNode
/*    */ {
/*    */   public LabelNode dflt;
/*    */   public List<Integer> keys;
/*    */   public List<LabelNode> labels;
/*    */   
/*    */   public LookupSwitchInsnNode(LabelNode dflt, int[] keys, LabelNode[] labels) {
/* 61 */     super(171);
/* 62 */     this.dflt = dflt;
/* 63 */     this.keys = Util.asArrayList(keys);
/* 64 */     this.labels = Util.asArrayList(labels);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getType() {
/* 69 */     return 12;
/*    */   }
/*    */ 
/*    */   
/*    */   public void accept(MethodVisitor methodVisitor) {
/* 74 */     int[] keysArray = new int[this.keys.size()];
/* 75 */     for (int i = 0, n = keysArray.length; i < n; i++) {
/* 76 */       keysArray[i] = ((Integer)this.keys.get(i)).intValue();
/*    */     }
/* 78 */     Label[] labelsArray = new Label[this.labels.size()];
/* 79 */     for (int j = 0, k = labelsArray.length; j < k; j++) {
/* 80 */       labelsArray[j] = ((LabelNode)this.labels.get(j)).getLabel();
/*    */     }
/* 82 */     methodVisitor.visitLookupSwitchInsn(this.dflt.getLabel(), keysArray, labelsArray);
/* 83 */     acceptAnnotations(methodVisitor);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractInsnNode clone(Map<LabelNode, LabelNode> clonedLabels) {
/* 89 */     LookupSwitchInsnNode clone = new LookupSwitchInsnNode(clone(this.dflt, clonedLabels), null, clone(this.labels, clonedLabels));
/* 90 */     clone.keys.addAll(this.keys);
/* 91 */     return clone.cloneAnnotations(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\objectweb\asm\tree\LookupSwitchInsnNode.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */