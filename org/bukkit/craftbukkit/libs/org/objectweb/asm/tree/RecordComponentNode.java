/*     */ package org.bukkit.craftbukkit.libs.org.objectweb.asm.tree;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.AnnotationVisitor;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.Attribute;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.ClassVisitor;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.RecordComponentVisitor;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.TypePath;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RecordComponentNode
/*     */   extends RecordComponentVisitor
/*     */ {
/*     */   public String name;
/*     */   public String descriptor;
/*     */   public String signature;
/*     */   public List<AnnotationNode> visibleAnnotations;
/*     */   public List<AnnotationNode> invisibleAnnotations;
/*     */   public List<TypeAnnotationNode> visibleTypeAnnotations;
/*     */   public List<TypeAnnotationNode> invisibleTypeAnnotations;
/*     */   public List<Attribute> attrs;
/*     */   
/*     */   public RecordComponentNode(String name, String descriptor, String signature) {
/*  79 */     this(524288, name, descriptor, signature);
/*  80 */     if (getClass() != RecordComponentNode.class) {
/*  81 */       throw new IllegalStateException();
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
/*     */   public RecordComponentNode(int api, String name, String descriptor, String signature) {
/*  95 */     super(api);
/*  96 */     this.name = name;
/*  97 */     this.descriptor = descriptor;
/*  98 */     this.signature = signature;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
/* 107 */     AnnotationNode annotation = new AnnotationNode(descriptor);
/* 108 */     if (visible) {
/* 109 */       this.visibleAnnotations = Util.add(this.visibleAnnotations, annotation);
/*     */     } else {
/* 111 */       this.invisibleAnnotations = Util.add(this.invisibleAnnotations, annotation);
/*     */     } 
/* 113 */     return annotation;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, String descriptor, boolean visible) {
/* 119 */     TypeAnnotationNode typeAnnotation = new TypeAnnotationNode(typeRef, typePath, descriptor);
/* 120 */     if (visible) {
/* 121 */       this.visibleTypeAnnotations = Util.add(this.visibleTypeAnnotations, typeAnnotation);
/*     */     } else {
/* 123 */       this.invisibleTypeAnnotations = Util.add(this.invisibleTypeAnnotations, typeAnnotation);
/*     */     } 
/* 125 */     return typeAnnotation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitAttribute(Attribute attribute) {
/* 130 */     this.attrs = Util.add(this.attrs, attribute);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitEnd() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void check(int api) {
/* 150 */     if (api < 524288) {
/* 151 */       throw new UnsupportedClassVersionException();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void accept(ClassVisitor classVisitor) {
/* 162 */     RecordComponentVisitor recordComponentVisitor = classVisitor.visitRecordComponent(this.name, this.descriptor, this.signature);
/* 163 */     if (recordComponentVisitor == null) {
/*     */       return;
/*     */     }
/*     */     
/* 167 */     if (this.visibleAnnotations != null) {
/* 168 */       for (int i = 0, n = this.visibleAnnotations.size(); i < n; i++) {
/* 169 */         AnnotationNode annotation = this.visibleAnnotations.get(i);
/* 170 */         annotation.accept(recordComponentVisitor.visitAnnotation(annotation.desc, true));
/*     */       } 
/*     */     }
/* 173 */     if (this.invisibleAnnotations != null) {
/* 174 */       for (int i = 0, n = this.invisibleAnnotations.size(); i < n; i++) {
/* 175 */         AnnotationNode annotation = this.invisibleAnnotations.get(i);
/* 176 */         annotation.accept(recordComponentVisitor.visitAnnotation(annotation.desc, false));
/*     */       } 
/*     */     }
/* 179 */     if (this.visibleTypeAnnotations != null) {
/* 180 */       for (int i = 0, n = this.visibleTypeAnnotations.size(); i < n; i++) {
/* 181 */         TypeAnnotationNode typeAnnotation = this.visibleTypeAnnotations.get(i);
/* 182 */         typeAnnotation.accept(recordComponentVisitor
/* 183 */             .visitTypeAnnotation(typeAnnotation.typeRef, typeAnnotation.typePath, typeAnnotation.desc, true));
/*     */       } 
/*     */     }
/*     */     
/* 187 */     if (this.invisibleTypeAnnotations != null) {
/* 188 */       for (int i = 0, n = this.invisibleTypeAnnotations.size(); i < n; i++) {
/* 189 */         TypeAnnotationNode typeAnnotation = this.invisibleTypeAnnotations.get(i);
/* 190 */         typeAnnotation.accept(recordComponentVisitor
/* 191 */             .visitTypeAnnotation(typeAnnotation.typeRef, typeAnnotation.typePath, typeAnnotation.desc, false));
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 196 */     if (this.attrs != null) {
/* 197 */       for (int i = 0, n = this.attrs.size(); i < n; i++) {
/* 198 */         recordComponentVisitor.visitAttribute(this.attrs.get(i));
/*     */       }
/*     */     }
/* 201 */     recordComponentVisitor.visitEnd();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\objectweb\asm\tree\RecordComponentNode.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */