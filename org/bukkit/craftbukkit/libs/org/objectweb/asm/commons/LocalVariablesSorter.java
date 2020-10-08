/*     */ package org.bukkit.craftbukkit.libs.org.objectweb.asm.commons;
/*     */ 
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.AnnotationVisitor;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.Label;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.MethodVisitor;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.Opcodes;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.Type;
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
/*     */ public class LocalVariablesSorter
/*     */   extends MethodVisitor
/*     */ {
/*  51 */   private static final Type OBJECT_TYPE = Type.getObjectType("java/lang/Object");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  58 */   private int[] remappedVariableIndices = new int[40];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  64 */   private Object[] remappedLocalTypes = new Object[20];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final int firstLocal;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int nextLocal;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LocalVariablesSorter(int access, String descriptor, MethodVisitor methodVisitor) {
/*  84 */     this(524288, access, descriptor, methodVisitor);
/*  85 */     if (getClass() != LocalVariablesSorter.class) {
/*  86 */       throw new IllegalStateException();
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
/*     */   protected LocalVariablesSorter(int api, int access, String descriptor, MethodVisitor methodVisitor) {
/* 102 */     super(api, methodVisitor);
/* 103 */     this.nextLocal = ((0x8 & access) == 0) ? 1 : 0;
/* 104 */     for (Type argumentType : Type.getArgumentTypes(descriptor)) {
/* 105 */       this.nextLocal += argumentType.getSize();
/*     */     }
/* 107 */     this.firstLocal = this.nextLocal;
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitVarInsn(int opcode, int var) {
/*     */     Type varType;
/* 113 */     switch (opcode) {
/*     */       case 22:
/*     */       case 55:
/* 116 */         varType = Type.LONG_TYPE;
/*     */         break;
/*     */       case 24:
/*     */       case 57:
/* 120 */         varType = Type.DOUBLE_TYPE;
/*     */         break;
/*     */       case 23:
/*     */       case 56:
/* 124 */         varType = Type.FLOAT_TYPE;
/*     */         break;
/*     */       case 21:
/*     */       case 54:
/* 128 */         varType = Type.INT_TYPE;
/*     */         break;
/*     */       case 25:
/*     */       case 58:
/*     */       case 169:
/* 133 */         varType = OBJECT_TYPE;
/*     */         break;
/*     */       default:
/* 136 */         throw new IllegalArgumentException("Invalid opcode " + opcode);
/*     */     } 
/* 138 */     super.visitVarInsn(opcode, remap(var, varType));
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitIincInsn(int var, int increment) {
/* 143 */     super.visitIincInsn(remap(var, Type.INT_TYPE), increment);
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitMaxs(int maxStack, int maxLocals) {
/* 148 */     super.visitMaxs(maxStack, this.nextLocal);
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
/* 159 */     int remappedIndex = remap(index, Type.getType(descriptor));
/* 160 */     super.visitLocalVariable(name, descriptor, signature, start, end, remappedIndex);
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
/*     */   public AnnotationVisitor visitLocalVariableAnnotation(int typeRef, TypePath typePath, Label[] start, Label[] end, int[] index, String descriptor, boolean visible) {
/* 172 */     Type type = Type.getType(descriptor);
/* 173 */     int[] remappedIndex = new int[index.length];
/* 174 */     for (int i = 0; i < remappedIndex.length; i++) {
/* 175 */       remappedIndex[i] = remap(index[i], type);
/*     */     }
/* 177 */     return super.visitLocalVariableAnnotation(typeRef, typePath, start, end, remappedIndex, descriptor, visible);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitFrame(int type, int numLocal, Object[] local, int numStack, Object[] stack) {
/* 188 */     if (type != -1) {
/* 189 */       throw new IllegalArgumentException("LocalVariablesSorter only accepts expanded frames (see ClassReader.EXPAND_FRAMES)");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 194 */     Object[] oldRemappedLocals = new Object[this.remappedLocalTypes.length];
/* 195 */     System.arraycopy(this.remappedLocalTypes, 0, oldRemappedLocals, 0, oldRemappedLocals.length);
/*     */     
/* 197 */     updateNewLocals(this.remappedLocalTypes);
/*     */ 
/*     */ 
/*     */     
/* 201 */     int oldVar = 0;
/* 202 */     for (int i = 0; i < numLocal; i++) {
/* 203 */       Object localType = local[i];
/* 204 */       if (localType != Opcodes.TOP) {
/* 205 */         Type varType = OBJECT_TYPE;
/* 206 */         if (localType == Opcodes.INTEGER) {
/* 207 */           varType = Type.INT_TYPE;
/* 208 */         } else if (localType == Opcodes.FLOAT) {
/* 209 */           varType = Type.FLOAT_TYPE;
/* 210 */         } else if (localType == Opcodes.LONG) {
/* 211 */           varType = Type.LONG_TYPE;
/* 212 */         } else if (localType == Opcodes.DOUBLE) {
/* 213 */           varType = Type.DOUBLE_TYPE;
/* 214 */         } else if (localType instanceof String) {
/* 215 */           varType = Type.getObjectType((String)localType);
/*     */         } 
/* 217 */         setFrameLocal(remap(oldVar, varType), localType);
/*     */       } 
/* 219 */       oldVar += (localType == Opcodes.LONG || localType == Opcodes.DOUBLE) ? 2 : 1;
/*     */     } 
/*     */ 
/*     */     
/* 223 */     oldVar = 0;
/* 224 */     int newVar = 0;
/* 225 */     int remappedNumLocal = 0;
/* 226 */     while (oldVar < this.remappedLocalTypes.length) {
/* 227 */       Object localType = this.remappedLocalTypes[oldVar];
/* 228 */       oldVar += (localType == Opcodes.LONG || localType == Opcodes.DOUBLE) ? 2 : 1;
/* 229 */       if (localType != null && localType != Opcodes.TOP) {
/* 230 */         this.remappedLocalTypes[newVar++] = localType;
/* 231 */         remappedNumLocal = newVar; continue;
/*     */       } 
/* 233 */       this.remappedLocalTypes[newVar++] = Opcodes.TOP;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 238 */     super.visitFrame(type, remappedNumLocal, this.remappedLocalTypes, numStack, stack);
/*     */ 
/*     */     
/* 241 */     this.remappedLocalTypes = oldRemappedLocals;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int newLocal(Type type) {
/*     */     Object localType;
/*     */     int local;
/* 254 */     switch (type.getSort()) {
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/*     */       case 4:
/*     */       case 5:
/* 260 */         localType = Opcodes.INTEGER;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 280 */         local = newLocalMapping(type);
/* 281 */         setLocalType(local, type);
/* 282 */         setFrameLocal(local, localType);
/* 283 */         return local;case 6: localType = Opcodes.FLOAT; local = newLocalMapping(type); setLocalType(local, type); setFrameLocal(local, localType); return local;case 7: localType = Opcodes.LONG; local = newLocalMapping(type); setLocalType(local, type); setFrameLocal(local, localType); return local;case 8: localType = Opcodes.DOUBLE; local = newLocalMapping(type); setLocalType(local, type); setFrameLocal(local, localType); return local;case 9: localType = type.getDescriptor(); local = newLocalMapping(type); setLocalType(local, type); setFrameLocal(local, localType); return local;case 10: localType = type.getInternalName(); local = newLocalMapping(type); setLocalType(local, type); setFrameLocal(local, localType); return local;
/*     */     } 
/*     */     throw new AssertionError();
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
/*     */   protected void updateNewLocals(Object[] newLocals) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setLocalType(int local, Type type) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setFrameLocal(int local, Object type) {
/* 316 */     int numLocals = this.remappedLocalTypes.length;
/* 317 */     if (local >= numLocals) {
/* 318 */       Object[] newRemappedLocalTypes = new Object[Math.max(2 * numLocals, local + 1)];
/* 319 */       System.arraycopy(this.remappedLocalTypes, 0, newRemappedLocalTypes, 0, numLocals);
/* 320 */       this.remappedLocalTypes = newRemappedLocalTypes;
/*     */     } 
/* 322 */     this.remappedLocalTypes[local] = type;
/*     */   }
/*     */   
/*     */   private int remap(int var, Type type) {
/* 326 */     if (var + type.getSize() <= this.firstLocal) {
/* 327 */       return var;
/*     */     }
/* 329 */     int key = 2 * var + type.getSize() - 1;
/* 330 */     int size = this.remappedVariableIndices.length;
/* 331 */     if (key >= size) {
/* 332 */       int[] newRemappedVariableIndices = new int[Math.max(2 * size, key + 1)];
/* 333 */       System.arraycopy(this.remappedVariableIndices, 0, newRemappedVariableIndices, 0, size);
/* 334 */       this.remappedVariableIndices = newRemappedVariableIndices;
/*     */     } 
/* 336 */     int value = this.remappedVariableIndices[key];
/* 337 */     if (value == 0) {
/* 338 */       value = newLocalMapping(type);
/* 339 */       setLocalType(value, type);
/* 340 */       this.remappedVariableIndices[key] = value + 1;
/*     */     } else {
/* 342 */       value--;
/*     */     } 
/* 344 */     return value;
/*     */   }
/*     */   
/*     */   protected int newLocalMapping(Type type) {
/* 348 */     int local = this.nextLocal;
/* 349 */     this.nextLocal += type.getSize();
/* 350 */     return local;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\objectweb\asm\commons\LocalVariablesSorter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */