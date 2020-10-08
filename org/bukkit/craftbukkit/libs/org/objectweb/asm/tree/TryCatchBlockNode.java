/*     */ package org.bukkit.craftbukkit.libs.org.objectweb.asm.tree;
/*     */ 
/*     */ import java.util.List;
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
/*     */ 
/*     */ 
/*     */ public class TryCatchBlockNode
/*     */ {
/*     */   public LabelNode start;
/*     */   public LabelNode end;
/*     */   public LabelNode handler;
/*     */   public String type;
/*     */   public List<TypeAnnotationNode> visibleTypeAnnotations;
/*     */   public List<TypeAnnotationNode> invisibleTypeAnnotations;
/*     */   
/*     */   public TryCatchBlockNode(LabelNode start, LabelNode end, LabelNode handler, String type) {
/*  74 */     this.start = start;
/*  75 */     this.end = end;
/*  76 */     this.handler = handler;
/*  77 */     this.type = type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateIndex(int index) {
/*  88 */     int newTypeRef = 0x42000000 | index << 8;
/*  89 */     if (this.visibleTypeAnnotations != null) {
/*  90 */       for (int i = 0, n = this.visibleTypeAnnotations.size(); i < n; i++) {
/*  91 */         ((TypeAnnotationNode)this.visibleTypeAnnotations.get(i)).typeRef = newTypeRef;
/*     */       }
/*     */     }
/*  94 */     if (this.invisibleTypeAnnotations != null) {
/*  95 */       for (int i = 0, n = this.invisibleTypeAnnotations.size(); i < n; i++) {
/*  96 */         ((TypeAnnotationNode)this.invisibleTypeAnnotations.get(i)).typeRef = newTypeRef;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void accept(MethodVisitor methodVisitor) {
/* 107 */     methodVisitor.visitTryCatchBlock(this.start
/* 108 */         .getLabel(), this.end.getLabel(), (this.handler == null) ? null : this.handler.getLabel(), this.type);
/* 109 */     if (this.visibleTypeAnnotations != null) {
/* 110 */       for (int i = 0, n = this.visibleTypeAnnotations.size(); i < n; i++) {
/* 111 */         TypeAnnotationNode typeAnnotation = this.visibleTypeAnnotations.get(i);
/* 112 */         typeAnnotation.accept(methodVisitor
/* 113 */             .visitTryCatchAnnotation(typeAnnotation.typeRef, typeAnnotation.typePath, typeAnnotation.desc, true));
/*     */       } 
/*     */     }
/*     */     
/* 117 */     if (this.invisibleTypeAnnotations != null)
/* 118 */       for (int i = 0, n = this.invisibleTypeAnnotations.size(); i < n; i++) {
/* 119 */         TypeAnnotationNode typeAnnotation = this.invisibleTypeAnnotations.get(i);
/* 120 */         typeAnnotation.accept(methodVisitor
/* 121 */             .visitTryCatchAnnotation(typeAnnotation.typeRef, typeAnnotation.typePath, typeAnnotation.desc, false));
/*     */       }  
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\objectweb\asm\tree\TryCatchBlockNode.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */