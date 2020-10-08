/*    */ package org.bukkit.craftbukkit.libs.org.objectweb.asm.commons;
/*    */ 
/*    */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.AnnotationVisitor;
/*    */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.FieldVisitor;
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
/*    */ public class FieldRemapper
/*    */   extends FieldVisitor
/*    */ {
/*    */   protected final Remapper remapper;
/*    */   
/*    */   public FieldRemapper(FieldVisitor fieldVisitor, Remapper remapper) {
/* 54 */     this(524288, fieldVisitor, remapper);
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
/*    */   protected FieldRemapper(int api, FieldVisitor fieldVisitor, Remapper remapper) {
/* 67 */     super(api, fieldVisitor);
/* 68 */     this.remapper = remapper;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
/* 74 */     AnnotationVisitor annotationVisitor = super.visitAnnotation(this.remapper.mapDesc(descriptor), visible);
/* 75 */     return (annotationVisitor == null) ? null : createAnnotationRemapper(annotationVisitor);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, String descriptor, boolean visible) {
/* 82 */     AnnotationVisitor annotationVisitor = super.visitTypeAnnotation(typeRef, typePath, this.remapper.mapDesc(descriptor), visible);
/* 83 */     return (annotationVisitor == null) ? null : createAnnotationRemapper(annotationVisitor);
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
/* 94 */     return new AnnotationRemapper(this.api, annotationVisitor, this.remapper);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\objectweb\asm\commons\FieldRemapper.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */