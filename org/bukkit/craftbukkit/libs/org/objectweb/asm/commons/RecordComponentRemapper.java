/*    */ package org.bukkit.craftbukkit.libs.org.objectweb.asm.commons;
/*    */ 
/*    */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.AnnotationVisitor;
/*    */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.RecordComponentVisitor;
/*    */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.TypePath;
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
/*    */ public class RecordComponentRemapper
/*    */   extends RecordComponentVisitor
/*    */ {
/*    */   protected final Remapper remapper;
/*    */   
/*    */   public RecordComponentRemapper(RecordComponentVisitor recordComponentVisitor, Remapper remapper) {
/* 56 */     this(524288, recordComponentVisitor, remapper);
/*    */   }
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
/*    */   protected RecordComponentRemapper(int api, RecordComponentVisitor recordComponentVisitor, Remapper remapper) {
/* 69 */     super(api, recordComponentVisitor);
/* 70 */     this.remapper = remapper;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
/* 76 */     AnnotationVisitor annotationVisitor = super.visitAnnotation(this.remapper.mapDesc(descriptor), visible);
/* 77 */     return (annotationVisitor == null) ? null : createAnnotationRemapper(annotationVisitor);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, String descriptor, boolean visible) {
/* 84 */     AnnotationVisitor annotationVisitor = super.visitTypeAnnotation(typeRef, typePath, this.remapper.mapDesc(descriptor), visible);
/* 85 */     return (annotationVisitor == null) ? null : createAnnotationRemapper(annotationVisitor);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected AnnotationVisitor createAnnotationRemapper(AnnotationVisitor annotationVisitor) {
/* 96 */     return new AnnotationRemapper(this.api, annotationVisitor, this.remapper);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\objectweb\asm\commons\RecordComponentRemapper.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */