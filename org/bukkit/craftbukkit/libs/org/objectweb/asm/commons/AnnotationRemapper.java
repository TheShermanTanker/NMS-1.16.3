/*     */ package org.bukkit.craftbukkit.libs.org.objectweb.asm.commons;
/*     */ 
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.AnnotationVisitor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AnnotationRemapper
/*     */   extends AnnotationVisitor
/*     */ {
/*     */   protected final Remapper remapper;
/*     */   
/*     */   public AnnotationRemapper(AnnotationVisitor annotationVisitor, Remapper remapper) {
/*  52 */     this(524288, annotationVisitor, remapper);
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
/*     */   protected AnnotationRemapper(int api, AnnotationVisitor annotationVisitor, Remapper remapper) {
/*  67 */     super(api, annotationVisitor);
/*  68 */     this.remapper = remapper;
/*     */   }
/*     */ 
/*     */   
/*     */   public void visit(String name, Object value) {
/*  73 */     super.visit(name, this.remapper.mapValue(value));
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitEnum(String name, String descriptor, String value) {
/*  78 */     super.visitEnum(name, this.remapper.mapDesc(descriptor), value);
/*     */   }
/*     */ 
/*     */   
/*     */   public AnnotationVisitor visitAnnotation(String name, String descriptor) {
/*  83 */     AnnotationVisitor annotationVisitor = super.visitAnnotation(name, this.remapper.mapDesc(descriptor));
/*  84 */     if (annotationVisitor == null) {
/*  85 */       return null;
/*     */     }
/*  87 */     return (annotationVisitor == this.av) ? this : createAnnotationRemapper(annotationVisitor);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AnnotationVisitor visitArray(String name) {
/*  93 */     AnnotationVisitor annotationVisitor = super.visitArray(name);
/*  94 */     if (annotationVisitor == null) {
/*  95 */       return null;
/*     */     }
/*  97 */     return (annotationVisitor == this.av) ? this : createAnnotationRemapper(annotationVisitor);
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
/*     */   protected AnnotationVisitor createAnnotationRemapper(AnnotationVisitor annotationVisitor) {
/* 109 */     return new AnnotationRemapper(this.api, annotationVisitor, this.remapper);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\objectweb\asm\commons\AnnotationRemapper.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */