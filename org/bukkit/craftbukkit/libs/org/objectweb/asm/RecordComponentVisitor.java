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
/*     */ public abstract class RecordComponentVisitor
/*     */ {
/*     */   protected final int api;
/*     */   RecordComponentVisitor delegate;
/*     */   
/*     */   public RecordComponentVisitor(int api) {
/*  56 */     this(api, null);
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
/*     */   public RecordComponentVisitor(int api, RecordComponentVisitor recordComponentVisitor) {
/*  68 */     if (api != 524288 && api != 458752 && api != 393216 && api != 327680 && api != 262144 && api != 17367040)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  74 */       throw new IllegalArgumentException("Unsupported api " + api);
/*     */     }
/*  76 */     if (api == 17367040) {
/*  77 */       Constants.checkAsmExperimental(this);
/*     */     }
/*  79 */     this.api = api;
/*  80 */     this.delegate = recordComponentVisitor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RecordComponentVisitor getDelegate() {
/*  89 */     return this.delegate;
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
/*     */   public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
/* 101 */     if (this.delegate != null) {
/* 102 */       return this.delegate.visitAnnotation(descriptor, visible);
/*     */     }
/* 104 */     return null;
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
/*     */   public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, String descriptor, boolean visible) {
/* 124 */     if (this.delegate != null) {
/* 125 */       return this.delegate.visitTypeAnnotation(typeRef, typePath, descriptor, visible);
/*     */     }
/* 127 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitAttribute(Attribute attribute) {
/* 136 */     if (this.delegate != null) {
/* 137 */       this.delegate.visitAttribute(attribute);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitEnd() {
/* 146 */     if (this.delegate != null)
/* 147 */       this.delegate.visitEnd(); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\objectweb\asm\RecordComponentVisitor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */