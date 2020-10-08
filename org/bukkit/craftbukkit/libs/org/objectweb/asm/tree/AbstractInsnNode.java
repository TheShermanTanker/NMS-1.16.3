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
/*     */ public abstract class AbstractInsnNode
/*     */ {
/*     */   public static final int INSN = 0;
/*     */   public static final int INT_INSN = 1;
/*     */   public static final int VAR_INSN = 2;
/*     */   public static final int TYPE_INSN = 3;
/*     */   public static final int FIELD_INSN = 4;
/*     */   public static final int METHOD_INSN = 5;
/*     */   public static final int INVOKE_DYNAMIC_INSN = 6;
/*     */   public static final int JUMP_INSN = 7;
/*     */   public static final int LABEL = 8;
/*     */   public static final int LDC_INSN = 9;
/*     */   public static final int IINC_INSN = 10;
/*     */   public static final int TABLESWITCH_INSN = 11;
/*     */   public static final int LOOKUPSWITCH_INSN = 12;
/*     */   public static final int MULTIANEWARRAY_INSN = 13;
/*     */   public static final int FRAME = 14;
/*     */   public static final int LINE = 15;
/*     */   protected int opcode;
/*     */   public List<TypeAnnotationNode> visibleTypeAnnotations;
/*     */   public List<TypeAnnotationNode> invisibleTypeAnnotations;
/*     */   AbstractInsnNode previousInsn;
/*     */   AbstractInsnNode nextInsn;
/*     */   int index;
/*     */   
/*     */   protected AbstractInsnNode(int opcode) {
/* 127 */     this.opcode = opcode;
/* 128 */     this.index = -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getOpcode() {
/* 137 */     return this.opcode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract int getType();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractInsnNode getPrevious() {
/* 154 */     return this.previousInsn;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractInsnNode getNext() {
/* 164 */     return this.nextInsn;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void accept(MethodVisitor paramMethodVisitor);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final void acceptAnnotations(MethodVisitor methodVisitor) {
/* 180 */     if (this.visibleTypeAnnotations != null) {
/* 181 */       for (int i = 0, n = this.visibleTypeAnnotations.size(); i < n; i++) {
/* 182 */         TypeAnnotationNode typeAnnotation = this.visibleTypeAnnotations.get(i);
/* 183 */         typeAnnotation.accept(methodVisitor
/* 184 */             .visitInsnAnnotation(typeAnnotation.typeRef, typeAnnotation.typePath, typeAnnotation.desc, true));
/*     */       } 
/*     */     }
/*     */     
/* 188 */     if (this.invisibleTypeAnnotations != null) {
/* 189 */       for (int i = 0, n = this.invisibleTypeAnnotations.size(); i < n; i++) {
/* 190 */         TypeAnnotationNode typeAnnotation = this.invisibleTypeAnnotations.get(i);
/* 191 */         typeAnnotation.accept(methodVisitor
/* 192 */             .visitInsnAnnotation(typeAnnotation.typeRef, typeAnnotation.typePath, typeAnnotation.desc, false));
/*     */       } 
/*     */     }
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
/*     */   public abstract AbstractInsnNode clone(Map<LabelNode, LabelNode> paramMap);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static LabelNode clone(LabelNode label, Map<LabelNode, LabelNode> clonedLabels) {
/* 215 */     return clonedLabels.get(label);
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
/*     */   static LabelNode[] clone(List<LabelNode> labels, Map<LabelNode, LabelNode> clonedLabels) {
/* 227 */     LabelNode[] clones = new LabelNode[labels.size()];
/* 228 */     for (int i = 0, n = clones.length; i < n; i++) {
/* 229 */       clones[i] = clonedLabels.get(labels.get(i));
/*     */     }
/* 231 */     return clones;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final AbstractInsnNode cloneAnnotations(AbstractInsnNode insnNode) {
/* 241 */     if (insnNode.visibleTypeAnnotations != null) {
/* 242 */       this.visibleTypeAnnotations = new ArrayList<TypeAnnotationNode>();
/* 243 */       for (int i = 0, n = insnNode.visibleTypeAnnotations.size(); i < n; i++) {
/* 244 */         TypeAnnotationNode sourceAnnotation = insnNode.visibleTypeAnnotations.get(i);
/* 245 */         TypeAnnotationNode cloneAnnotation = new TypeAnnotationNode(sourceAnnotation.typeRef, sourceAnnotation.typePath, sourceAnnotation.desc);
/*     */ 
/*     */         
/* 248 */         sourceAnnotation.accept(cloneAnnotation);
/* 249 */         this.visibleTypeAnnotations.add(cloneAnnotation);
/*     */       } 
/*     */     } 
/* 252 */     if (insnNode.invisibleTypeAnnotations != null) {
/* 253 */       this.invisibleTypeAnnotations = new ArrayList<TypeAnnotationNode>();
/* 254 */       for (int i = 0, n = insnNode.invisibleTypeAnnotations.size(); i < n; i++) {
/* 255 */         TypeAnnotationNode sourceAnnotation = insnNode.invisibleTypeAnnotations.get(i);
/* 256 */         TypeAnnotationNode cloneAnnotation = new TypeAnnotationNode(sourceAnnotation.typeRef, sourceAnnotation.typePath, sourceAnnotation.desc);
/*     */ 
/*     */         
/* 259 */         sourceAnnotation.accept(cloneAnnotation);
/* 260 */         this.invisibleTypeAnnotations.add(cloneAnnotation);
/*     */       } 
/*     */     } 
/* 263 */     return this;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\objectweb\asm\tree\AbstractInsnNode.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */