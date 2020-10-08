/*     */ package org.bukkit.craftbukkit.libs.org.objectweb.asm.tree;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.AnnotationVisitor;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.Attribute;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.ClassVisitor;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.FieldVisitor;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FieldNode
/*     */   extends FieldVisitor
/*     */ {
/*     */   public int access;
/*     */   public String name;
/*     */   public String desc;
/*     */   public String signature;
/*     */   public Object value;
/*     */   public List<AnnotationNode> visibleAnnotations;
/*     */   public List<AnnotationNode> invisibleAnnotations;
/*     */   public List<TypeAnnotationNode> visibleTypeAnnotations;
/*     */   public List<TypeAnnotationNode> invisibleTypeAnnotations;
/*     */   public List<Attribute> attrs;
/*     */   
/*     */   public FieldNode(int access, String name, String descriptor, String signature, Object value) {
/* 102 */     this(524288, access, name, descriptor, signature, value);
/* 103 */     if (getClass() != FieldNode.class) {
/* 104 */       throw new IllegalStateException();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FieldNode(int api, int access, String name, String descriptor, String signature, Object value) {
/* 130 */     super(api);
/* 131 */     this.access = access;
/* 132 */     this.name = name;
/* 133 */     this.desc = descriptor;
/* 134 */     this.signature = signature;
/* 135 */     this.value = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
/* 144 */     AnnotationNode annotation = new AnnotationNode(descriptor);
/* 145 */     if (visible) {
/* 146 */       this.visibleAnnotations = Util.add(this.visibleAnnotations, annotation);
/*     */     } else {
/* 148 */       this.invisibleAnnotations = Util.add(this.invisibleAnnotations, annotation);
/*     */     } 
/* 150 */     return annotation;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, String descriptor, boolean visible) {
/* 156 */     TypeAnnotationNode typeAnnotation = new TypeAnnotationNode(typeRef, typePath, descriptor);
/* 157 */     if (visible) {
/* 158 */       this.visibleTypeAnnotations = Util.add(this.visibleTypeAnnotations, typeAnnotation);
/*     */     } else {
/* 160 */       this.invisibleTypeAnnotations = Util.add(this.invisibleTypeAnnotations, typeAnnotation);
/*     */     } 
/* 162 */     return typeAnnotation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitAttribute(Attribute attribute) {
/* 167 */     this.attrs = Util.add(this.attrs, attribute);
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
/* 188 */     if (api == 262144) {
/* 189 */       if (this.visibleTypeAnnotations != null && !this.visibleTypeAnnotations.isEmpty()) {
/* 190 */         throw new UnsupportedClassVersionException();
/*     */       }
/* 192 */       if (this.invisibleTypeAnnotations != null && !this.invisibleTypeAnnotations.isEmpty()) {
/* 193 */         throw new UnsupportedClassVersionException();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void accept(ClassVisitor classVisitor) {
/* 204 */     FieldVisitor fieldVisitor = classVisitor.visitField(this.access, this.name, this.desc, this.signature, this.value);
/* 205 */     if (fieldVisitor == null) {
/*     */       return;
/*     */     }
/*     */     
/* 209 */     if (this.visibleAnnotations != null) {
/* 210 */       for (int i = 0, n = this.visibleAnnotations.size(); i < n; i++) {
/* 211 */         AnnotationNode annotation = this.visibleAnnotations.get(i);
/* 212 */         annotation.accept(fieldVisitor.visitAnnotation(annotation.desc, true));
/*     */       } 
/*     */     }
/* 215 */     if (this.invisibleAnnotations != null) {
/* 216 */       for (int i = 0, n = this.invisibleAnnotations.size(); i < n; i++) {
/* 217 */         AnnotationNode annotation = this.invisibleAnnotations.get(i);
/* 218 */         annotation.accept(fieldVisitor.visitAnnotation(annotation.desc, false));
/*     */       } 
/*     */     }
/* 221 */     if (this.visibleTypeAnnotations != null) {
/* 222 */       for (int i = 0, n = this.visibleTypeAnnotations.size(); i < n; i++) {
/* 223 */         TypeAnnotationNode typeAnnotation = this.visibleTypeAnnotations.get(i);
/* 224 */         typeAnnotation.accept(fieldVisitor
/* 225 */             .visitTypeAnnotation(typeAnnotation.typeRef, typeAnnotation.typePath, typeAnnotation.desc, true));
/*     */       } 
/*     */     }
/*     */     
/* 229 */     if (this.invisibleTypeAnnotations != null) {
/* 230 */       for (int i = 0, n = this.invisibleTypeAnnotations.size(); i < n; i++) {
/* 231 */         TypeAnnotationNode typeAnnotation = this.invisibleTypeAnnotations.get(i);
/* 232 */         typeAnnotation.accept(fieldVisitor
/* 233 */             .visitTypeAnnotation(typeAnnotation.typeRef, typeAnnotation.typePath, typeAnnotation.desc, false));
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 238 */     if (this.attrs != null) {
/* 239 */       for (int i = 0, n = this.attrs.size(); i < n; i++) {
/* 240 */         fieldVisitor.visitAttribute(this.attrs.get(i));
/*     */       }
/*     */     }
/* 243 */     fieldVisitor.visitEnd();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\objectweb\asm\tree\FieldNode.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */