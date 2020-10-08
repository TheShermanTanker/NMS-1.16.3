/*     */ package org.bukkit.craftbukkit.libs.org.objectweb.asm.commons;
/*     */ 
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.ConstantDynamic;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.Handle;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.Label;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.MethodVisitor;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.Opcodes;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CodeSizeEvaluator
/*     */   extends MethodVisitor
/*     */   implements Opcodes
/*     */ {
/*     */   private int minSize;
/*     */   private int maxSize;
/*     */   
/*     */   public CodeSizeEvaluator(MethodVisitor methodVisitor) {
/*  50 */     this(524288, methodVisitor);
/*     */   }
/*     */   
/*     */   protected CodeSizeEvaluator(int api, MethodVisitor methodVisitor) {
/*  54 */     super(api, methodVisitor);
/*     */   }
/*     */   
/*     */   public int getMinSize() {
/*  58 */     return this.minSize;
/*     */   }
/*     */   
/*     */   public int getMaxSize() {
/*  62 */     return this.maxSize;
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitInsn(int opcode) {
/*  67 */     this.minSize++;
/*  68 */     this.maxSize++;
/*  69 */     super.visitInsn(opcode);
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitIntInsn(int opcode, int operand) {
/*  74 */     if (opcode == 17) {
/*  75 */       this.minSize += 3;
/*  76 */       this.maxSize += 3;
/*     */     } else {
/*  78 */       this.minSize += 2;
/*  79 */       this.maxSize += 2;
/*     */     } 
/*  81 */     super.visitIntInsn(opcode, operand);
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitVarInsn(int opcode, int var) {
/*  86 */     if (var < 4 && opcode != 169) {
/*  87 */       this.minSize++;
/*  88 */       this.maxSize++;
/*  89 */     } else if (var >= 256) {
/*  90 */       this.minSize += 4;
/*  91 */       this.maxSize += 4;
/*     */     } else {
/*  93 */       this.minSize += 2;
/*  94 */       this.maxSize += 2;
/*     */     } 
/*  96 */     super.visitVarInsn(opcode, var);
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitTypeInsn(int opcode, String type) {
/* 101 */     this.minSize += 3;
/* 102 */     this.maxSize += 3;
/* 103 */     super.visitTypeInsn(opcode, type);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitFieldInsn(int opcode, String owner, String name, String descriptor) {
/* 109 */     this.minSize += 3;
/* 110 */     this.maxSize += 3;
/* 111 */     super.visitFieldInsn(opcode, owner, name, descriptor);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitMethodInsn(int opcodeAndSource, String owner, String name, String descriptor, boolean isInterface) {
/* 121 */     if (this.api < 327680 && (opcodeAndSource & 0x100) == 0) {
/*     */       
/* 123 */       super.visitMethodInsn(opcodeAndSource, owner, name, descriptor, isInterface);
/*     */       return;
/*     */     } 
/* 126 */     int opcode = opcodeAndSource & 0xFFFFFEFF;
/*     */     
/* 128 */     if (opcode == 185) {
/* 129 */       this.minSize += 5;
/* 130 */       this.maxSize += 5;
/*     */     } else {
/* 132 */       this.minSize += 3;
/* 133 */       this.maxSize += 3;
/*     */     } 
/* 135 */     super.visitMethodInsn(opcodeAndSource, owner, name, descriptor, isInterface);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitInvokeDynamicInsn(String name, String descriptor, Handle bootstrapMethodHandle, Object... bootstrapMethodArguments) {
/* 144 */     this.minSize += 5;
/* 145 */     this.maxSize += 5;
/* 146 */     super.visitInvokeDynamicInsn(name, descriptor, bootstrapMethodHandle, bootstrapMethodArguments);
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitJumpInsn(int opcode, Label label) {
/* 151 */     this.minSize += 3;
/* 152 */     if (opcode == 167 || opcode == 168) {
/* 153 */       this.maxSize += 5;
/*     */     } else {
/* 155 */       this.maxSize += 8;
/*     */     } 
/* 157 */     super.visitJumpInsn(opcode, label);
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitLdcInsn(Object value) {
/* 162 */     if (value instanceof Long || value instanceof Double || (value instanceof ConstantDynamic && ((ConstantDynamic)value)
/*     */       
/* 164 */       .getSize() == 2)) {
/* 165 */       this.minSize += 3;
/* 166 */       this.maxSize += 3;
/*     */     } else {
/* 168 */       this.minSize += 2;
/* 169 */       this.maxSize += 3;
/*     */     } 
/* 171 */     super.visitLdcInsn(value);
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitIincInsn(int var, int increment) {
/* 176 */     if (var > 255 || increment > 127 || increment < -128) {
/* 177 */       this.minSize += 6;
/* 178 */       this.maxSize += 6;
/*     */     } else {
/* 180 */       this.minSize += 3;
/* 181 */       this.maxSize += 3;
/*     */     } 
/* 183 */     super.visitIincInsn(var, increment);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitTableSwitchInsn(int min, int max, Label dflt, Label... labels) {
/* 189 */     this.minSize += 13 + labels.length * 4;
/* 190 */     this.maxSize += 16 + labels.length * 4;
/* 191 */     super.visitTableSwitchInsn(min, max, dflt, labels);
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitLookupSwitchInsn(Label dflt, int[] keys, Label[] labels) {
/* 196 */     this.minSize += 9 + keys.length * 8;
/* 197 */     this.maxSize += 12 + keys.length * 8;
/* 198 */     super.visitLookupSwitchInsn(dflt, keys, labels);
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitMultiANewArrayInsn(String descriptor, int numDimensions) {
/* 203 */     this.minSize += 4;
/* 204 */     this.maxSize += 4;
/* 205 */     super.visitMultiANewArrayInsn(descriptor, numDimensions);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\objectweb\asm\commons\CodeSizeEvaluator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */