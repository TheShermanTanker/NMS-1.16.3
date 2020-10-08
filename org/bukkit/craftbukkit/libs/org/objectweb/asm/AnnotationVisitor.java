/*     */ package org.bukkit.craftbukkit.libs.org.objectweb.asm;
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
/*     */ public abstract class AnnotationVisitor
/*     */ {
/*     */   protected final int api;
/*     */   protected AnnotationVisitor av;
/*     */   
/*     */   public AnnotationVisitor(int api) {
/*  59 */     this(api, null);
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
/*     */   public AnnotationVisitor(int api, AnnotationVisitor annotationVisitor) {
/*  71 */     if (api != 524288 && api != 458752 && api != 393216 && api != 327680 && api != 262144 && api != 17367040)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  77 */       throw new IllegalArgumentException("Unsupported api " + api);
/*     */     }
/*  79 */     if (api == 17367040) {
/*  80 */       Constants.checkAsmExperimental(this);
/*     */     }
/*  82 */     this.api = api;
/*  83 */     this.av = annotationVisitor;
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
/*     */   public void visit(String name, Object value) {
/*  98 */     if (this.av != null) {
/*  99 */       this.av.visit(name, value);
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
/*     */   public void visitEnum(String name, String descriptor, String value) {
/* 111 */     if (this.av != null) {
/* 112 */       this.av.visitEnum(name, descriptor, value);
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
/*     */   
/*     */   public AnnotationVisitor visitAnnotation(String name, String descriptor) {
/* 126 */     if (this.av != null) {
/* 127 */       return this.av.visitAnnotation(name, descriptor);
/*     */     }
/* 129 */     return null;
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
/*     */   public AnnotationVisitor visitArray(String name) {
/* 144 */     if (this.av != null) {
/* 145 */       return this.av.visitArray(name);
/*     */     }
/* 147 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitEnd() {
/* 152 */     if (this.av != null)
/* 153 */       this.av.visitEnd(); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\objectweb\asm\AnnotationVisitor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */