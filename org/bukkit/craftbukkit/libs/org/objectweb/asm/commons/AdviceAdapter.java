/*     */ package org.bukkit.craftbukkit.libs.org.objectweb.asm.commons;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.ConstantDynamic;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.Handle;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.Label;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.MethodVisitor;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.Opcodes;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.Type;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AdviceAdapter
/*     */   extends GeneratorAdapter
/*     */   implements Opcodes
/*     */ {
/*  54 */   private static final Object UNINITIALIZED_THIS = new Object();
/*     */ 
/*     */   
/*  57 */   private static final Object OTHER = new Object();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String INVALID_OPCODE = "Invalid opcode ";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int methodAccess;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String methodDesc;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final boolean isConstructor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean superClassConstructorCalled;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private List<Object> stackFrame;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Map<Label, List<Object>> forwardJumpStackFrames;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AdviceAdapter(int api, MethodVisitor methodVisitor, int access, String name, String descriptor) {
/* 114 */     super(api, methodVisitor, access, name, descriptor);
/* 115 */     this.methodAccess = access;
/* 116 */     this.methodDesc = descriptor;
/* 117 */     this.isConstructor = "<init>".equals(name);
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitCode() {
/* 122 */     super.visitCode();
/* 123 */     if (this.isConstructor) {
/* 124 */       this.stackFrame = new ArrayList();
/* 125 */       this.forwardJumpStackFrames = new HashMap<Label, List<Object>>();
/*     */     } else {
/* 127 */       onMethodEnter();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitLabel(Label label) {
/* 133 */     super.visitLabel(label);
/* 134 */     if (this.isConstructor && this.forwardJumpStackFrames != null) {
/* 135 */       List<Object> labelStackFrame = this.forwardJumpStackFrames.get(label);
/* 136 */       if (labelStackFrame != null) {
/* 137 */         this.stackFrame = labelStackFrame;
/* 138 */         this.superClassConstructorCalled = false;
/* 139 */         this.forwardJumpStackFrames.remove(label);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitInsn(int opcode) {
/* 146 */     if (this.isConstructor && !this.superClassConstructorCalled) {
/*     */       int stackSize;
/* 148 */       switch (opcode) {
/*     */         case 172:
/*     */         case 173:
/*     */         case 174:
/*     */         case 175:
/*     */         case 176:
/* 154 */           throw new IllegalArgumentException("Invalid return in constructor");
/*     */         case 177:
/* 156 */           onMethodExit(opcode);
/*     */           break;
/*     */         case 191:
/* 159 */           popValue();
/* 160 */           onMethodExit(opcode);
/*     */           break;
/*     */         case 0:
/*     */         case 47:
/*     */         case 49:
/*     */         case 116:
/*     */         case 117:
/*     */         case 118:
/*     */         case 119:
/*     */         case 134:
/*     */         case 138:
/*     */         case 139:
/*     */         case 143:
/*     */         case 145:
/*     */         case 146:
/*     */         case 147:
/*     */         case 190:
/*     */           break;
/*     */         case 1:
/*     */         case 2:
/*     */         case 3:
/*     */         case 4:
/*     */         case 5:
/*     */         case 6:
/*     */         case 7:
/*     */         case 8:
/*     */         case 11:
/*     */         case 12:
/*     */         case 13:
/*     */         case 133:
/*     */         case 135:
/*     */         case 140:
/*     */         case 141:
/* 193 */           pushValue(OTHER);
/*     */           break;
/*     */         case 9:
/*     */         case 10:
/*     */         case 14:
/*     */         case 15:
/* 199 */           pushValue(OTHER);
/* 200 */           pushValue(OTHER);
/*     */           break;
/*     */         case 46:
/*     */         case 48:
/*     */         case 50:
/*     */         case 51:
/*     */         case 52:
/*     */         case 53:
/*     */         case 87:
/*     */         case 96:
/*     */         case 98:
/*     */         case 100:
/*     */         case 102:
/*     */         case 104:
/*     */         case 106:
/*     */         case 108:
/*     */         case 110:
/*     */         case 112:
/*     */         case 114:
/*     */         case 120:
/*     */         case 121:
/*     */         case 122:
/*     */         case 123:
/*     */         case 124:
/*     */         case 125:
/*     */         case 126:
/*     */         case 128:
/*     */         case 130:
/*     */         case 136:
/*     */         case 137:
/*     */         case 142:
/*     */         case 144:
/*     */         case 149:
/*     */         case 150:
/*     */         case 194:
/*     */         case 195:
/* 236 */           popValue();
/*     */           break;
/*     */         case 88:
/*     */         case 97:
/*     */         case 99:
/*     */         case 101:
/*     */         case 103:
/*     */         case 105:
/*     */         case 107:
/*     */         case 109:
/*     */         case 111:
/*     */         case 113:
/*     */         case 115:
/*     */         case 127:
/*     */         case 129:
/*     */         case 131:
/* 252 */           popValue();
/* 253 */           popValue();
/*     */           break;
/*     */         case 79:
/*     */         case 81:
/*     */         case 83:
/*     */         case 84:
/*     */         case 85:
/*     */         case 86:
/*     */         case 148:
/*     */         case 151:
/*     */         case 152:
/* 264 */           popValue();
/* 265 */           popValue();
/* 266 */           popValue();
/*     */           break;
/*     */         case 80:
/*     */         case 82:
/* 270 */           popValue();
/* 271 */           popValue();
/* 272 */           popValue();
/* 273 */           popValue();
/*     */           break;
/*     */         case 89:
/* 276 */           pushValue(peekValue());
/*     */           break;
/*     */         case 90:
/* 279 */           stackSize = this.stackFrame.size();
/* 280 */           this.stackFrame.add(stackSize - 2, this.stackFrame.get(stackSize - 1));
/*     */           break;
/*     */         case 91:
/* 283 */           stackSize = this.stackFrame.size();
/* 284 */           this.stackFrame.add(stackSize - 3, this.stackFrame.get(stackSize - 1));
/*     */           break;
/*     */         case 92:
/* 287 */           stackSize = this.stackFrame.size();
/* 288 */           this.stackFrame.add(stackSize - 2, this.stackFrame.get(stackSize - 1));
/* 289 */           this.stackFrame.add(stackSize - 2, this.stackFrame.get(stackSize - 1));
/*     */           break;
/*     */         case 93:
/* 292 */           stackSize = this.stackFrame.size();
/* 293 */           this.stackFrame.add(stackSize - 3, this.stackFrame.get(stackSize - 1));
/* 294 */           this.stackFrame.add(stackSize - 3, this.stackFrame.get(stackSize - 1));
/*     */           break;
/*     */         case 94:
/* 297 */           stackSize = this.stackFrame.size();
/* 298 */           this.stackFrame.add(stackSize - 4, this.stackFrame.get(stackSize - 1));
/* 299 */           this.stackFrame.add(stackSize - 4, this.stackFrame.get(stackSize - 1));
/*     */           break;
/*     */         case 95:
/* 302 */           stackSize = this.stackFrame.size();
/* 303 */           this.stackFrame.add(stackSize - 2, this.stackFrame.get(stackSize - 1));
/* 304 */           this.stackFrame.remove(stackSize);
/*     */           break;
/*     */         default:
/* 307 */           throw new IllegalArgumentException("Invalid opcode " + opcode);
/*     */       } 
/*     */     } else {
/* 310 */       switch (opcode) {
/*     */         case 172:
/*     */         case 173:
/*     */         case 174:
/*     */         case 175:
/*     */         case 176:
/*     */         case 177:
/*     */         case 191:
/* 318 */           onMethodExit(opcode);
/*     */           break;
/*     */       } 
/*     */ 
/*     */     
/*     */     } 
/* 324 */     super.visitInsn(opcode);
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitVarInsn(int opcode, int var) {
/* 329 */     super.visitVarInsn(opcode, var);
/* 330 */     if (this.isConstructor && !this.superClassConstructorCalled) {
/* 331 */       switch (opcode) {
/*     */         case 21:
/*     */         case 23:
/* 334 */           pushValue(OTHER);
/*     */         
/*     */         case 22:
/*     */         case 24:
/* 338 */           pushValue(OTHER);
/* 339 */           pushValue(OTHER);
/*     */         
/*     */         case 25:
/* 342 */           pushValue((var == 0) ? UNINITIALIZED_THIS : OTHER);
/*     */         
/*     */         case 54:
/*     */         case 56:
/*     */         case 58:
/* 347 */           popValue();
/*     */         
/*     */         case 55:
/*     */         case 57:
/* 351 */           popValue();
/* 352 */           popValue();
/*     */         
/*     */         case 169:
/*     */           return;
/*     */       } 
/* 357 */       throw new IllegalArgumentException("Invalid opcode " + opcode);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitFieldInsn(int opcode, String owner, String name, String descriptor) {
/* 365 */     super.visitFieldInsn(opcode, owner, name, descriptor);
/* 366 */     if (this.isConstructor && !this.superClassConstructorCalled) {
/* 367 */       char firstDescriptorChar = descriptor.charAt(0);
/* 368 */       boolean longOrDouble = (firstDescriptorChar == 'J' || firstDescriptorChar == 'D');
/* 369 */       switch (opcode) {
/*     */         case 178:
/* 371 */           pushValue(OTHER);
/* 372 */           if (longOrDouble) {
/* 373 */             pushValue(OTHER);
/*     */           }
/*     */           return;
/*     */         case 179:
/* 377 */           popValue();
/* 378 */           if (longOrDouble) {
/* 379 */             popValue();
/*     */           }
/*     */           return;
/*     */         case 181:
/* 383 */           popValue();
/* 384 */           popValue();
/* 385 */           if (longOrDouble) {
/* 386 */             popValue();
/*     */           }
/*     */           return;
/*     */         case 180:
/* 390 */           if (longOrDouble) {
/* 391 */             pushValue(OTHER);
/*     */           }
/*     */           return;
/*     */       } 
/* 395 */       throw new IllegalArgumentException("Invalid opcode " + opcode);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitIntInsn(int opcode, int operand) {
/* 402 */     super.visitIntInsn(opcode, operand);
/* 403 */     if (this.isConstructor && !this.superClassConstructorCalled && opcode != 188) {
/* 404 */       pushValue(OTHER);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitLdcInsn(Object value) {
/* 410 */     super.visitLdcInsn(value);
/* 411 */     if (this.isConstructor && !this.superClassConstructorCalled) {
/* 412 */       pushValue(OTHER);
/* 413 */       if (value instanceof Double || value instanceof Long || (value instanceof ConstantDynamic && ((ConstantDynamic)value)
/*     */         
/* 415 */         .getSize() == 2)) {
/* 416 */         pushValue(OTHER);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitMultiANewArrayInsn(String descriptor, int numDimensions) {
/* 423 */     super.visitMultiANewArrayInsn(descriptor, numDimensions);
/* 424 */     if (this.isConstructor && !this.superClassConstructorCalled) {
/* 425 */       for (int i = 0; i < numDimensions; i++) {
/* 426 */         popValue();
/*     */       }
/* 428 */       pushValue(OTHER);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitTypeInsn(int opcode, String type) {
/* 434 */     super.visitTypeInsn(opcode, type);
/*     */     
/* 436 */     if (this.isConstructor && !this.superClassConstructorCalled && opcode == 187) {
/* 437 */       pushValue(OTHER);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitMethodInsn(int opcodeAndSource, String owner, String name, String descriptor, boolean isInterface) {
/* 448 */     if (this.api < 327680 && (opcodeAndSource & 0x100) == 0) {
/*     */       
/* 450 */       super.visitMethodInsn(opcodeAndSource, owner, name, descriptor, isInterface);
/*     */       return;
/*     */     } 
/* 453 */     super.visitMethodInsn(opcodeAndSource, owner, name, descriptor, isInterface);
/* 454 */     int opcode = opcodeAndSource & 0xFFFFFEFF;
/*     */     
/* 456 */     doVisitMethodInsn(opcode, descriptor);
/*     */   }
/*     */   
/*     */   private void doVisitMethodInsn(int opcode, String descriptor) {
/* 460 */     if (this.isConstructor && !this.superClassConstructorCalled) {
/* 461 */       Object value; for (Type argumentType : Type.getArgumentTypes(descriptor)) {
/* 462 */         popValue();
/* 463 */         if (argumentType.getSize() == 2) {
/* 464 */           popValue();
/*     */         }
/*     */       } 
/* 467 */       switch (opcode) {
/*     */         case 182:
/*     */         case 185:
/* 470 */           popValue();
/*     */           break;
/*     */         case 183:
/* 473 */           value = popValue();
/* 474 */           if (value == UNINITIALIZED_THIS && !this.superClassConstructorCalled) {
/* 475 */             this.superClassConstructorCalled = true;
/* 476 */             onMethodEnter();
/*     */           } 
/*     */           break;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 483 */       Type returnType = Type.getReturnType(descriptor);
/* 484 */       if (returnType != Type.VOID_TYPE) {
/* 485 */         pushValue(OTHER);
/* 486 */         if (returnType.getSize() == 2) {
/* 487 */           pushValue(OTHER);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitInvokeDynamicInsn(String name, String descriptor, Handle bootstrapMethodHandle, Object... bootstrapMethodArguments) {
/* 499 */     super.visitInvokeDynamicInsn(name, descriptor, bootstrapMethodHandle, bootstrapMethodArguments);
/* 500 */     doVisitMethodInsn(186, descriptor);
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitJumpInsn(int opcode, Label label) {
/* 505 */     super.visitJumpInsn(opcode, label);
/* 506 */     if (this.isConstructor && !this.superClassConstructorCalled) {
/* 507 */       switch (opcode) {
/*     */         case 153:
/*     */         case 154:
/*     */         case 155:
/*     */         case 156:
/*     */         case 157:
/*     */         case 158:
/*     */         case 198:
/*     */         case 199:
/* 516 */           popValue();
/*     */           break;
/*     */         case 159:
/*     */         case 160:
/*     */         case 161:
/*     */         case 162:
/*     */         case 163:
/*     */         case 164:
/*     */         case 165:
/*     */         case 166:
/* 526 */           popValue();
/* 527 */           popValue();
/*     */           break;
/*     */         case 168:
/* 530 */           pushValue(OTHER);
/*     */           break;
/*     */       } 
/*     */ 
/*     */       
/* 535 */       addForwardJump(label);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitLookupSwitchInsn(Label dflt, int[] keys, Label[] labels) {
/* 541 */     super.visitLookupSwitchInsn(dflt, keys, labels);
/* 542 */     if (this.isConstructor && !this.superClassConstructorCalled) {
/* 543 */       popValue();
/* 544 */       addForwardJumps(dflt, labels);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitTableSwitchInsn(int min, int max, Label dflt, Label... labels) {
/* 551 */     super.visitTableSwitchInsn(min, max, dflt, labels);
/* 552 */     if (this.isConstructor && !this.superClassConstructorCalled) {
/* 553 */       popValue();
/* 554 */       addForwardJumps(dflt, labels);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitTryCatchBlock(Label start, Label end, Label handler, String type) {
/* 561 */     super.visitTryCatchBlock(start, end, handler, type);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 570 */     if (this.isConstructor && !this.forwardJumpStackFrames.containsKey(handler)) {
/* 571 */       List<Object> handlerStackFrame = new ArrayList();
/* 572 */       handlerStackFrame.add(OTHER);
/* 573 */       this.forwardJumpStackFrames.put(handler, handlerStackFrame);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void addForwardJumps(Label dflt, Label[] labels) {
/* 578 */     addForwardJump(dflt);
/* 579 */     for (Label label : labels) {
/* 580 */       addForwardJump(label);
/*     */     }
/*     */   }
/*     */   
/*     */   private void addForwardJump(Label label) {
/* 585 */     if (this.forwardJumpStackFrames.containsKey(label)) {
/*     */       return;
/*     */     }
/* 588 */     this.forwardJumpStackFrames.put(label, new ArrayList(this.stackFrame));
/*     */   }
/*     */   
/*     */   private Object popValue() {
/* 592 */     return this.stackFrame.remove(this.stackFrame.size() - 1);
/*     */   }
/*     */   
/*     */   private Object peekValue() {
/* 596 */     return this.stackFrame.get(this.stackFrame.size() - 1);
/*     */   }
/*     */   
/*     */   private void pushValue(Object value) {
/* 600 */     this.stackFrame.add(value);
/*     */   }
/*     */   
/*     */   protected void onMethodEnter() {}
/*     */   
/*     */   protected void onMethodExit(int opcode) {}
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\objectweb\asm\commons\AdviceAdapter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */