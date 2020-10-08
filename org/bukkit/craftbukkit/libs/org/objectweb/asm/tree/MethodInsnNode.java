/*     */ package org.bukkit.craftbukkit.libs.org.objectweb.asm.tree;
/*     */ 
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
/*     */ public class MethodInsnNode
/*     */   extends AbstractInsnNode
/*     */ {
/*     */   public String owner;
/*     */   public String name;
/*     */   public String desc;
/*     */   public boolean itf;
/*     */   
/*     */   public MethodInsnNode(int opcode, String owner, String name, String descriptor) {
/*  71 */     this(opcode, owner, name, descriptor, (opcode == 185));
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
/*     */   public MethodInsnNode(int opcode, String owner, String name, String descriptor, boolean isInterface) {
/*  91 */     super(opcode);
/*  92 */     this.owner = owner;
/*  93 */     this.name = name;
/*  94 */     this.desc = descriptor;
/*  95 */     this.itf = isInterface;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOpcode(int opcode) {
/* 105 */     this.opcode = opcode;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getType() {
/* 110 */     return 5;
/*     */   }
/*     */ 
/*     */   
/*     */   public void accept(MethodVisitor methodVisitor) {
/* 115 */     methodVisitor.visitMethodInsn(this.opcode, this.owner, this.name, this.desc, this.itf);
/* 116 */     acceptAnnotations(methodVisitor);
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractInsnNode clone(Map<LabelNode, LabelNode> clonedLabels) {
/* 121 */     return (new MethodInsnNode(this.opcode, this.owner, this.name, this.desc, this.itf)).cloneAnnotations(this);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\objectweb\asm\tree\MethodInsnNode.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */