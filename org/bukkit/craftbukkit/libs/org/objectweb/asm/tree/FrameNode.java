/*     */ package org.bukkit.craftbukkit.libs.org.objectweb.asm.tree;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.MethodVisitor;
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
/*     */ public class FrameNode
/*     */   extends AbstractInsnNode
/*     */ {
/*     */   public int type;
/*     */   public List<Object> local;
/*     */   public List<Object> stack;
/*     */   
/*     */   private FrameNode() {
/*  73 */     super(-1);
/*     */   }
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
/*     */   public FrameNode(int type, int numLocal, Object[] local, int numStack, Object[] stack) {
/*  97 */     super(-1);
/*  98 */     this.type = type;
/*  99 */     switch (type) {
/*     */       case -1:
/*     */       case 0:
/* 102 */         this.local = Util.asArrayList(numLocal, local);
/* 103 */         this.stack = Util.asArrayList(numStack, stack);
/*     */       
/*     */       case 1:
/* 106 */         this.local = Util.asArrayList(numLocal, local);
/*     */       
/*     */       case 2:
/* 109 */         this.local = Util.asArrayList(numLocal);
/*     */       
/*     */       case 3:
/*     */         return;
/*     */       case 4:
/* 114 */         this.stack = Util.asArrayList(1, stack);
/*     */     } 
/*     */     
/* 117 */     throw new IllegalArgumentException();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getType() {
/* 123 */     return 14;
/*     */   }
/*     */ 
/*     */   
/*     */   public void accept(MethodVisitor methodVisitor) {
/* 128 */     switch (this.type) {
/*     */       case -1:
/*     */       case 0:
/* 131 */         methodVisitor.visitFrame(this.type, this.local.size(), asArray(this.local), this.stack.size(), asArray(this.stack));
/*     */         return;
/*     */       case 1:
/* 134 */         methodVisitor.visitFrame(this.type, this.local.size(), asArray(this.local), 0, null);
/*     */         return;
/*     */       case 2:
/* 137 */         methodVisitor.visitFrame(this.type, this.local.size(), null, 0, null);
/*     */         return;
/*     */       case 3:
/* 140 */         methodVisitor.visitFrame(this.type, 0, null, 0, null);
/*     */         return;
/*     */       case 4:
/* 143 */         methodVisitor.visitFrame(this.type, 0, null, 1, asArray(this.stack));
/*     */         return;
/*     */     } 
/* 146 */     throw new IllegalArgumentException();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractInsnNode clone(Map<LabelNode, LabelNode> clonedLabels) {
/* 152 */     FrameNode clone = new FrameNode();
/* 153 */     clone.type = this.type;
/* 154 */     if (this.local != null) {
/* 155 */       clone.local = new ArrayList();
/* 156 */       for (int i = 0, n = this.local.size(); i < n; i++) {
/* 157 */         Object localElement = this.local.get(i);
/* 158 */         if (localElement instanceof LabelNode) {
/* 159 */           localElement = clonedLabels.get(localElement);
/*     */         }
/* 161 */         clone.local.add(localElement);
/*     */       } 
/*     */     } 
/* 164 */     if (this.stack != null) {
/* 165 */       clone.stack = new ArrayList();
/* 166 */       for (int i = 0, n = this.stack.size(); i < n; i++) {
/* 167 */         Object stackElement = this.stack.get(i);
/* 168 */         if (stackElement instanceof LabelNode) {
/* 169 */           stackElement = clonedLabels.get(stackElement);
/*     */         }
/* 171 */         clone.stack.add(stackElement);
/*     */       } 
/*     */     } 
/* 174 */     return clone;
/*     */   }
/*     */   
/*     */   private static Object[] asArray(List<Object> list) {
/* 178 */     Object[] array = new Object[list.size()];
/* 179 */     for (int i = 0, n = array.length; i < n; i++) {
/* 180 */       Object o = list.get(i);
/* 181 */       if (o instanceof LabelNode) {
/* 182 */         o = ((LabelNode)o).getLabel();
/*     */       }
/* 184 */       array[i] = o;
/*     */     } 
/* 186 */     return array;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\objectweb\asm\tree\FrameNode.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */