/*     */ package org.bukkit.craftbukkit.libs.org.objectweb.asm.commons;
/*     */ 
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.AnnotationVisitor;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.Handle;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.Label;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.MethodVisitor;
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
/*     */ public class MethodRemapper
/*     */   extends MethodVisitor
/*     */ {
/*     */   protected final Remapper remapper;
/*     */   
/*     */   public MethodRemapper(MethodVisitor methodVisitor, Remapper remapper) {
/*  56 */     this(524288, methodVisitor, remapper);
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
/*     */   protected MethodRemapper(int api, MethodVisitor methodVisitor, Remapper remapper) {
/*  71 */     super(api, methodVisitor);
/*  72 */     this.remapper = remapper;
/*     */   }
/*     */ 
/*     */   
/*     */   public AnnotationVisitor visitAnnotationDefault() {
/*  77 */     AnnotationVisitor annotationVisitor = super.visitAnnotationDefault();
/*  78 */     return (annotationVisitor == null) ? annotationVisitor : 
/*     */       
/*  80 */       createAnnotationRemapper(annotationVisitor);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
/*  86 */     AnnotationVisitor annotationVisitor = super.visitAnnotation(this.remapper.mapDesc(descriptor), visible);
/*  87 */     return (annotationVisitor == null) ? annotationVisitor : 
/*     */       
/*  89 */       createAnnotationRemapper(annotationVisitor);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, String descriptor, boolean visible) {
/*  96 */     AnnotationVisitor annotationVisitor = super.visitTypeAnnotation(typeRef, typePath, this.remapper.mapDesc(descriptor), visible);
/*  97 */     return (annotationVisitor == null) ? annotationVisitor : 
/*     */       
/*  99 */       createAnnotationRemapper(annotationVisitor);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnnotationVisitor visitParameterAnnotation(int parameter, String descriptor, boolean visible) {
/* 106 */     AnnotationVisitor annotationVisitor = super.visitParameterAnnotation(parameter, this.remapper.mapDesc(descriptor), visible);
/* 107 */     return (annotationVisitor == null) ? annotationVisitor : 
/*     */       
/* 109 */       createAnnotationRemapper(annotationVisitor);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitFrame(int type, int numLocal, Object[] local, int numStack, Object[] stack) {
/* 119 */     super.visitFrame(type, numLocal, 
/*     */ 
/*     */         
/* 122 */         remapFrameTypes(numLocal, local), numStack, 
/*     */         
/* 124 */         remapFrameTypes(numStack, stack));
/*     */   }
/*     */   
/*     */   private Object[] remapFrameTypes(int numTypes, Object[] frameTypes) {
/* 128 */     if (frameTypes == null) {
/* 129 */       return frameTypes;
/*     */     }
/* 131 */     Object[] remappedFrameTypes = null;
/* 132 */     for (int i = 0; i < numTypes; i++) {
/* 133 */       if (frameTypes[i] instanceof String) {
/* 134 */         if (remappedFrameTypes == null) {
/* 135 */           remappedFrameTypes = new Object[numTypes];
/* 136 */           System.arraycopy(frameTypes, 0, remappedFrameTypes, 0, numTypes);
/*     */         } 
/* 138 */         remappedFrameTypes[i] = this.remapper.mapType((String)frameTypes[i]);
/*     */       } 
/*     */     } 
/* 141 */     return (remappedFrameTypes == null) ? frameTypes : remappedFrameTypes;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitFieldInsn(int opcode, String owner, String name, String descriptor) {
/* 147 */     super.visitFieldInsn(opcode, this.remapper
/*     */         
/* 149 */         .mapType(owner), this.remapper
/* 150 */         .mapFieldName(owner, name, descriptor), this.remapper
/* 151 */         .mapDesc(descriptor));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitMethodInsn(int opcodeAndSource, String owner, String name, String descriptor, boolean isInterface) {
/* 161 */     if (this.api < 327680 && (opcodeAndSource & 0x100) == 0) {
/*     */       
/* 163 */       super.visitMethodInsn(opcodeAndSource, owner, name, descriptor, isInterface);
/*     */       return;
/*     */     } 
/* 166 */     super.visitMethodInsn(opcodeAndSource, this.remapper
/*     */         
/* 168 */         .mapType(owner), this.remapper
/* 169 */         .mapMethodName(owner, name, descriptor), this.remapper
/* 170 */         .mapMethodDesc(descriptor), isInterface);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitInvokeDynamicInsn(String name, String descriptor, Handle bootstrapMethodHandle, Object... bootstrapMethodArguments) {
/* 180 */     Object[] remappedBootstrapMethodArguments = new Object[bootstrapMethodArguments.length];
/* 181 */     for (int i = 0; i < bootstrapMethodArguments.length; i++) {
/* 182 */       remappedBootstrapMethodArguments[i] = this.remapper.mapValue(bootstrapMethodArguments[i]);
/*     */     }
/* 184 */     super.visitInvokeDynamicInsn(this.remapper
/* 185 */         .mapInvokeDynamicMethodName(name, descriptor), this.remapper
/* 186 */         .mapMethodDesc(descriptor), (Handle)this.remapper
/* 187 */         .mapValue(bootstrapMethodHandle), remappedBootstrapMethodArguments);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitTypeInsn(int opcode, String type) {
/* 193 */     super.visitTypeInsn(opcode, this.remapper.mapType(type));
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitLdcInsn(Object value) {
/* 198 */     super.visitLdcInsn(this.remapper.mapValue(value));
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitMultiANewArrayInsn(String descriptor, int numDimensions) {
/* 203 */     super.visitMultiANewArrayInsn(this.remapper.mapDesc(descriptor), numDimensions);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnnotationVisitor visitInsnAnnotation(int typeRef, TypePath typePath, String descriptor, boolean visible) {
/* 210 */     AnnotationVisitor annotationVisitor = super.visitInsnAnnotation(typeRef, typePath, this.remapper.mapDesc(descriptor), visible);
/* 211 */     return (annotationVisitor == null) ? annotationVisitor : 
/*     */       
/* 213 */       createAnnotationRemapper(annotationVisitor);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitTryCatchBlock(Label start, Label end, Label handler, String type) {
/* 219 */     super.visitTryCatchBlock(start, end, handler, (type == null) ? null : this.remapper.mapType(type));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnnotationVisitor visitTryCatchAnnotation(int typeRef, TypePath typePath, String descriptor, boolean visible) {
/* 226 */     AnnotationVisitor annotationVisitor = super.visitTryCatchAnnotation(typeRef, typePath, this.remapper.mapDesc(descriptor), visible);
/* 227 */     return (annotationVisitor == null) ? annotationVisitor : 
/*     */       
/* 229 */       createAnnotationRemapper(annotationVisitor);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitLocalVariable(String name, String descriptor, String signature, Label start, Label end, int index) {
/* 240 */     super.visitLocalVariable(name, this.remapper
/*     */         
/* 242 */         .mapDesc(descriptor), this.remapper
/* 243 */         .mapSignature(signature, true), start, end, index);
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
/*     */   public AnnotationVisitor visitLocalVariableAnnotation(int typeRef, TypePath typePath, Label[] start, Label[] end, int[] index, String descriptor, boolean visible) {
/* 259 */     AnnotationVisitor annotationVisitor = super.visitLocalVariableAnnotation(typeRef, typePath, start, end, index, this.remapper
/* 260 */         .mapDesc(descriptor), visible);
/* 261 */     return (annotationVisitor == null) ? annotationVisitor : 
/*     */       
/* 263 */       createAnnotationRemapper(annotationVisitor);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AnnotationVisitor createAnnotationRemapper(AnnotationVisitor annotationVisitor) {
/* 274 */     return new AnnotationRemapper(this.api, annotationVisitor, this.remapper);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\objectweb\asm\commons\MethodRemapper.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */