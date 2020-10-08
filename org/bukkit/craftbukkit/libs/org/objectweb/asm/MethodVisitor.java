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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class MethodVisitor
/*     */ {
/*     */   private static final String REQUIRES_ASM5 = "This feature requires ASM5";
/*     */   protected final int api;
/*     */   protected MethodVisitor mv;
/*     */   
/*     */   public MethodVisitor(int api) {
/*  71 */     this(api, null);
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
/*     */   public MethodVisitor(int api, MethodVisitor methodVisitor) {
/*  83 */     if (api != 524288 && api != 458752 && api != 393216 && api != 327680 && api != 262144 && api != 17367040)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  89 */       throw new IllegalArgumentException("Unsupported api " + api);
/*     */     }
/*  91 */     if (api == 17367040) {
/*  92 */       Constants.checkAsmExperimental(this);
/*     */     }
/*  94 */     this.api = api;
/*  95 */     this.mv = methodVisitor;
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
/*     */   public void visitParameter(String name, int access) {
/* 110 */     if (this.api < 327680) {
/* 111 */       throw new UnsupportedOperationException("This feature requires ASM5");
/*     */     }
/* 113 */     if (this.mv != null) {
/* 114 */       this.mv.visitParameter(name, access);
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
/*     */   public AnnotationVisitor visitAnnotationDefault() {
/* 127 */     if (this.mv != null) {
/* 128 */       return this.mv.visitAnnotationDefault();
/*     */     }
/* 130 */     return null;
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
/* 142 */     if (this.mv != null) {
/* 143 */       return this.mv.visitAnnotation(descriptor, visible);
/*     */     }
/* 145 */     return null;
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
/*     */   public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, String descriptor, boolean visible) {
/* 166 */     if (this.api < 327680) {
/* 167 */       throw new UnsupportedOperationException("This feature requires ASM5");
/*     */     }
/* 169 */     if (this.mv != null) {
/* 170 */       return this.mv.visitTypeAnnotation(typeRef, typePath, descriptor, visible);
/*     */     }
/* 172 */     return null;
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
/*     */   public void visitAnnotableParameterCount(int parameterCount, boolean visible) {
/* 190 */     if (this.mv != null) {
/* 191 */       this.mv.visitAnnotableParameterCount(parameterCount, visible);
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
/*     */   public AnnotationVisitor visitParameterAnnotation(int parameter, String descriptor, boolean visible) {
/* 211 */     if (this.mv != null) {
/* 212 */       return this.mv.visitParameterAnnotation(parameter, descriptor, visible);
/*     */     }
/* 214 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitAttribute(Attribute attribute) {
/* 223 */     if (this.mv != null) {
/* 224 */       this.mv.visitAttribute(attribute);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitCode() {
/* 230 */     if (this.mv != null) {
/* 231 */       this.mv.visitCode();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitFrame(int type, int numLocal, Object[] local, int numStack, Object[] stack) {
/* 298 */     if (this.mv != null) {
/* 299 */       this.mv.visitFrame(type, numLocal, local, numStack, stack);
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
/*     */   public void visitInsn(int opcode) {
/* 322 */     if (this.mv != null) {
/* 323 */       this.mv.visitInsn(opcode);
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
/*     */   public void visitIntInsn(int opcode, int operand) {
/* 342 */     if (this.mv != null) {
/* 343 */       this.mv.visitIntInsn(opcode, operand);
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
/*     */   public void visitVarInsn(int opcode, int var) {
/* 357 */     if (this.mv != null) {
/* 358 */       this.mv.visitVarInsn(opcode, var);
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
/*     */   public void visitTypeInsn(int opcode, String type) {
/* 372 */     if (this.mv != null) {
/* 373 */       this.mv.visitTypeInsn(opcode, type);
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
/*     */   public void visitFieldInsn(int opcode, String owner, String name, String descriptor) {
/* 389 */     if (this.mv != null) {
/* 390 */       this.mv.visitFieldInsn(opcode, owner, name, descriptor);
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
/*     */   @Deprecated
/*     */   public void visitMethodInsn(int opcode, String owner, String name, String descriptor) {
/* 408 */     int opcodeAndSource = opcode | ((this.api < 327680) ? 256 : 0);
/* 409 */     visitMethodInsn(opcodeAndSource, owner, name, descriptor, (opcode == 185));
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
/*     */   public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
/* 429 */     if (this.api < 327680 && (opcode & 0x100) == 0) {
/* 430 */       if (isInterface != ((opcode == 185))) {
/* 431 */         throw new UnsupportedOperationException("INVOKESPECIAL/STATIC on interfaces requires ASM5");
/*     */       }
/* 433 */       visitMethodInsn(opcode, owner, name, descriptor);
/*     */       return;
/*     */     } 
/* 436 */     if (this.mv != null) {
/* 437 */       this.mv.visitMethodInsn(opcode & 0xFFFFFEFF, owner, name, descriptor, isInterface);
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
/*     */   public void visitInvokeDynamicInsn(String name, String descriptor, Handle bootstrapMethodHandle, Object... bootstrapMethodArguments) {
/* 457 */     if (this.api < 327680) {
/* 458 */       throw new UnsupportedOperationException("This feature requires ASM5");
/*     */     }
/* 460 */     if (this.mv != null) {
/* 461 */       this.mv.visitInvokeDynamicInsn(name, descriptor, bootstrapMethodHandle, bootstrapMethodArguments);
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
/*     */   public void visitJumpInsn(int opcode, Label label) {
/* 476 */     if (this.mv != null) {
/* 477 */       this.mv.visitJumpInsn(opcode, label);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitLabel(Label label) {
/* 487 */     if (this.mv != null) {
/* 488 */       this.mv.visitLabel(label);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitLdcInsn(Object value) {
/* 540 */     if (this.api < 327680 && (value instanceof Handle || (value instanceof Type && ((Type)value)
/*     */       
/* 542 */       .getSort() == 11))) {
/* 543 */       throw new UnsupportedOperationException("This feature requires ASM5");
/*     */     }
/* 545 */     if (this.api < 458752 && value instanceof ConstantDynamic) {
/* 546 */       throw new UnsupportedOperationException("This feature requires ASM7");
/*     */     }
/* 548 */     if (this.mv != null) {
/* 549 */       this.mv.visitLdcInsn(value);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitIincInsn(int var, int increment) {
/* 560 */     if (this.mv != null) {
/* 561 */       this.mv.visitIincInsn(var, increment);
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
/*     */   public void visitTableSwitchInsn(int min, int max, Label dflt, Label... labels) {
/* 576 */     if (this.mv != null) {
/* 577 */       this.mv.visitTableSwitchInsn(min, max, dflt, labels);
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
/*     */   public void visitLookupSwitchInsn(Label dflt, int[] keys, Label[] labels) {
/* 590 */     if (this.mv != null) {
/* 591 */       this.mv.visitLookupSwitchInsn(dflt, keys, labels);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitMultiANewArrayInsn(String descriptor, int numDimensions) {
/* 602 */     if (this.mv != null) {
/* 603 */       this.mv.visitMultiANewArrayInsn(descriptor, numDimensions);
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
/*     */   public AnnotationVisitor visitInsnAnnotation(int typeRef, TypePath typePath, String descriptor, boolean visible) {
/* 628 */     if (this.api < 327680) {
/* 629 */       throw new UnsupportedOperationException("This feature requires ASM5");
/*     */     }
/* 631 */     if (this.mv != null) {
/* 632 */       return this.mv.visitInsnAnnotation(typeRef, typePath, descriptor, visible);
/*     */     }
/* 634 */     return null;
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
/*     */   public void visitTryCatchBlock(Label start, Label end, Label handler, String type) {
/* 654 */     if (this.mv != null) {
/* 655 */       this.mv.visitTryCatchBlock(start, end, handler, type);
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
/*     */   public AnnotationVisitor visitTryCatchAnnotation(int typeRef, TypePath typePath, String descriptor, boolean visible) {
/* 676 */     if (this.api < 327680) {
/* 677 */       throw new UnsupportedOperationException("This feature requires ASM5");
/*     */     }
/* 679 */     if (this.mv != null) {
/* 680 */       return this.mv.visitTryCatchAnnotation(typeRef, typePath, descriptor, visible);
/*     */     }
/* 682 */     return null;
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
/*     */   public void visitLocalVariable(String name, String descriptor, String signature, Label start, Label end, int index) {
/* 706 */     if (this.mv != null) {
/* 707 */       this.mv.visitLocalVariable(name, descriptor, signature, start, end, index);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnnotationVisitor visitLocalVariableAnnotation(int typeRef, TypePath typePath, Label[] start, Label[] end, int[] index, String descriptor, boolean visible) {
/* 739 */     if (this.api < 327680) {
/* 740 */       throw new UnsupportedOperationException("This feature requires ASM5");
/*     */     }
/* 742 */     if (this.mv != null) {
/* 743 */       return this.mv.visitLocalVariableAnnotation(typeRef, typePath, start, end, index, descriptor, visible);
/*     */     }
/*     */     
/* 746 */     return null;
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
/*     */   public void visitLineNumber(int line, Label start) {
/* 759 */     if (this.mv != null) {
/* 760 */       this.mv.visitLineNumber(line, start);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitMaxs(int maxStack, int maxLocals) {
/* 771 */     if (this.mv != null) {
/* 772 */       this.mv.visitMaxs(maxStack, maxLocals);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitEnd() {
/* 781 */     if (this.mv != null)
/* 782 */       this.mv.visitEnd(); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\objectweb\asm\MethodVisitor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */