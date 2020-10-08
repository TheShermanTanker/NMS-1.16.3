/*     */ package org.bukkit.craftbukkit.libs.org.objectweb.asm.commons;
/*     */ 
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.ClassVisitor;
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
/*     */ public class StaticInitMerger
/*     */   extends ClassVisitor
/*     */ {
/*     */   private String owner;
/*     */   private final String renamedClinitMethodPrefix;
/*     */   private int numClinitMethods;
/*     */   private MethodVisitor mergedClinitVisitor;
/*     */   
/*     */   public StaticInitMerger(String prefix, ClassVisitor classVisitor) {
/*  64 */     this(524288, prefix, classVisitor);
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
/*     */   protected StaticInitMerger(int api, String prefix, ClassVisitor classVisitor) {
/*  78 */     super(api, classVisitor);
/*  79 */     this.renamedClinitMethodPrefix = prefix;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
/*  90 */     super.visit(version, access, name, signature, superName, interfaces);
/*  91 */     this.owner = name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
/*     */     MethodVisitor methodVisitor;
/* 102 */     if ("<clinit>".equals(name)) {
/* 103 */       int newAccess = 10;
/* 104 */       String newName = this.renamedClinitMethodPrefix + this.numClinitMethods++;
/* 105 */       methodVisitor = super.visitMethod(newAccess, newName, descriptor, signature, exceptions);
/*     */       
/* 107 */       if (this.mergedClinitVisitor == null) {
/* 108 */         this.mergedClinitVisitor = super.visitMethod(newAccess, name, descriptor, null, null);
/*     */       }
/* 110 */       this.mergedClinitVisitor.visitMethodInsn(184, this.owner, newName, descriptor, false);
/*     */     } else {
/* 112 */       methodVisitor = super.visitMethod(access, name, descriptor, signature, exceptions);
/*     */     } 
/* 114 */     return methodVisitor;
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitEnd() {
/* 119 */     if (this.mergedClinitVisitor != null) {
/* 120 */       this.mergedClinitVisitor.visitInsn(177);
/* 121 */       this.mergedClinitVisitor.visitMaxs(0, 0);
/*     */     } 
/* 123 */     super.visitEnd();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\objectweb\asm\commons\StaticInitMerger.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */