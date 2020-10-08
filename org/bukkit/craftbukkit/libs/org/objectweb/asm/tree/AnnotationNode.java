/*     */ package org.bukkit.craftbukkit.libs.org.objectweb.asm.tree;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AnnotationNode
/*     */   extends AnnotationVisitor
/*     */ {
/*     */   public String desc;
/*     */   public List<Object> values;
/*     */   
/*     */   public AnnotationNode(String descriptor) {
/*  63 */     this(524288, descriptor);
/*  64 */     if (getClass() != AnnotationNode.class) {
/*  65 */       throw new IllegalStateException();
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
/*     */   public AnnotationNode(int api, String descriptor) {
/*  78 */     super(api);
/*  79 */     this.desc = descriptor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   AnnotationNode(List<Object> values) {
/*  88 */     super(524288);
/*  89 */     this.values = values;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visit(String name, Object value) {
/*  98 */     if (this.values == null) {
/*  99 */       this.values = new ArrayList((this.desc != null) ? 2 : 1);
/*     */     }
/* 101 */     if (this.desc != null) {
/* 102 */       this.values.add(name);
/*     */     }
/* 104 */     if (value instanceof byte[]) {
/* 105 */       this.values.add(Util.asArrayList((byte[])value));
/* 106 */     } else if (value instanceof boolean[]) {
/* 107 */       this.values.add(Util.asArrayList((boolean[])value));
/* 108 */     } else if (value instanceof short[]) {
/* 109 */       this.values.add(Util.asArrayList((short[])value));
/* 110 */     } else if (value instanceof char[]) {
/* 111 */       this.values.add(Util.asArrayList((char[])value));
/* 112 */     } else if (value instanceof int[]) {
/* 113 */       this.values.add(Util.asArrayList((int[])value));
/* 114 */     } else if (value instanceof long[]) {
/* 115 */       this.values.add(Util.asArrayList((long[])value));
/* 116 */     } else if (value instanceof float[]) {
/* 117 */       this.values.add(Util.asArrayList((float[])value));
/* 118 */     } else if (value instanceof double[]) {
/* 119 */       this.values.add(Util.asArrayList((double[])value));
/*     */     } else {
/* 121 */       this.values.add(value);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitEnum(String name, String descriptor, String value) {
/* 127 */     if (this.values == null) {
/* 128 */       this.values = new ArrayList((this.desc != null) ? 2 : 1);
/*     */     }
/* 130 */     if (this.desc != null) {
/* 131 */       this.values.add(name);
/*     */     }
/* 133 */     this.values.add(new String[] { descriptor, value });
/*     */   }
/*     */ 
/*     */   
/*     */   public AnnotationVisitor visitAnnotation(String name, String descriptor) {
/* 138 */     if (this.values == null) {
/* 139 */       this.values = new ArrayList((this.desc != null) ? 2 : 1);
/*     */     }
/* 141 */     if (this.desc != null) {
/* 142 */       this.values.add(name);
/*     */     }
/* 144 */     AnnotationNode annotation = new AnnotationNode(descriptor);
/* 145 */     this.values.add(annotation);
/* 146 */     return annotation;
/*     */   }
/*     */ 
/*     */   
/*     */   public AnnotationVisitor visitArray(String name) {
/* 151 */     if (this.values == null) {
/* 152 */       this.values = new ArrayList((this.desc != null) ? 2 : 1);
/*     */     }
/* 154 */     if (this.desc != null) {
/* 155 */       this.values.add(name);
/*     */     }
/* 157 */     List<Object> array = new ArrayList();
/* 158 */     this.values.add(array);
/* 159 */     return new AnnotationNode(array);
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
/*     */   public void check(int api) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void accept(AnnotationVisitor annotationVisitor) {
/* 189 */     if (annotationVisitor != null) {
/* 190 */       if (this.values != null) {
/* 191 */         for (int i = 0, n = this.values.size(); i < n; i += 2) {
/* 192 */           String name = (String)this.values.get(i);
/* 193 */           Object value = this.values.get(i + 1);
/* 194 */           accept(annotationVisitor, name, value);
/*     */         } 
/*     */       }
/* 197 */       annotationVisitor.visitEnd();
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
/*     */   static void accept(AnnotationVisitor annotationVisitor, String name, Object value) {
/* 210 */     if (annotationVisitor != null)
/* 211 */       if (value instanceof String[]) {
/* 212 */         String[] typeValue = (String[])value;
/* 213 */         annotationVisitor.visitEnum(name, typeValue[0], typeValue[1]);
/* 214 */       } else if (value instanceof AnnotationNode) {
/* 215 */         AnnotationNode annotationValue = (AnnotationNode)value;
/* 216 */         annotationValue.accept(annotationVisitor.visitAnnotation(name, annotationValue.desc));
/* 217 */       } else if (value instanceof List) {
/* 218 */         AnnotationVisitor arrayAnnotationVisitor = annotationVisitor.visitArray(name);
/* 219 */         if (arrayAnnotationVisitor != null) {
/* 220 */           List<?> arrayValue = (List)value;
/* 221 */           for (int i = 0, n = arrayValue.size(); i < n; i++) {
/* 222 */             accept(arrayAnnotationVisitor, null, arrayValue.get(i));
/*     */           }
/* 224 */           arrayAnnotationVisitor.visitEnd();
/*     */         } 
/*     */       } else {
/* 227 */         annotationVisitor.visit(name, value);
/*     */       }  
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\objectweb\asm\tree\AnnotationNode.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */