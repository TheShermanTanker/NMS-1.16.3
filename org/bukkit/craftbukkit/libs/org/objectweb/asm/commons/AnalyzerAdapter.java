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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AnalyzerAdapter
/*     */   extends MethodVisitor
/*     */ {
/*     */   public List<Object> locals;
/*     */   public List<Object> stack;
/*     */   private List<Label> labels;
/*     */   public Map<Object, Object> uninitializedTypes;
/*     */   private int maxStack;
/*     */   private int maxLocals;
/*     */   private String owner;
/*     */   
/*     */   public AnalyzerAdapter(String owner, int access, String name, String descriptor, MethodVisitor methodVisitor) {
/* 119 */     this(524288, owner, access, name, descriptor, methodVisitor);
/* 120 */     if (getClass() != AnalyzerAdapter.class) {
/* 121 */       throw new IllegalStateException();
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
/*     */   protected AnalyzerAdapter(int api, String owner, int access, String name, String descriptor, MethodVisitor methodVisitor) {
/* 145 */     super(api, methodVisitor);
/* 146 */     this.owner = owner;
/* 147 */     this.locals = new ArrayList();
/* 148 */     this.stack = new ArrayList();
/* 149 */     this.uninitializedTypes = new HashMap<Object, Object>();
/*     */     
/* 151 */     if ((access & 0x8) == 0) {
/* 152 */       if ("<init>".equals(name)) {
/* 153 */         this.locals.add(Opcodes.UNINITIALIZED_THIS);
/*     */       } else {
/* 155 */         this.locals.add(owner);
/*     */       } 
/*     */     }
/* 158 */     for (Type argumentType : Type.getArgumentTypes(descriptor)) {
/* 159 */       switch (argumentType.getSort()) {
/*     */         case 1:
/*     */         case 2:
/*     */         case 3:
/*     */         case 4:
/*     */         case 5:
/* 165 */           this.locals.add(Opcodes.INTEGER);
/*     */           break;
/*     */         case 6:
/* 168 */           this.locals.add(Opcodes.FLOAT);
/*     */           break;
/*     */         case 7:
/* 171 */           this.locals.add(Opcodes.LONG);
/* 172 */           this.locals.add(Opcodes.TOP);
/*     */           break;
/*     */         case 8:
/* 175 */           this.locals.add(Opcodes.DOUBLE);
/* 176 */           this.locals.add(Opcodes.TOP);
/*     */           break;
/*     */         case 9:
/* 179 */           this.locals.add(argumentType.getDescriptor());
/*     */           break;
/*     */         case 10:
/* 182 */           this.locals.add(argumentType.getInternalName());
/*     */           break;
/*     */         default:
/* 185 */           throw new AssertionError();
/*     */       } 
/*     */     } 
/* 188 */     this.maxLocals = this.locals.size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitFrame(int type, int numLocal, Object[] local, int numStack, Object[] stack) {
/* 198 */     if (type != -1) {
/* 199 */       throw new IllegalArgumentException("AnalyzerAdapter only accepts expanded frames (see ClassReader.EXPAND_FRAMES)");
/*     */     }
/*     */ 
/*     */     
/* 203 */     super.visitFrame(type, numLocal, local, numStack, stack);
/*     */     
/* 205 */     if (this.locals != null) {
/* 206 */       this.locals.clear();
/* 207 */       this.stack.clear();
/*     */     } else {
/* 209 */       this.locals = new ArrayList();
/* 210 */       this.stack = new ArrayList();
/*     */     } 
/* 212 */     visitFrameTypes(numLocal, local, this.locals);
/* 213 */     visitFrameTypes(numStack, stack, this.stack);
/* 214 */     this.maxLocals = Math.max(this.maxLocals, this.locals.size());
/* 215 */     this.maxStack = Math.max(this.maxStack, this.stack.size());
/*     */   }
/*     */ 
/*     */   
/*     */   private static void visitFrameTypes(int numTypes, Object[] frameTypes, List<Object> result) {
/* 220 */     for (int i = 0; i < numTypes; i++) {
/* 221 */       Object frameType = frameTypes[i];
/* 222 */       result.add(frameType);
/* 223 */       if (frameType == Opcodes.LONG || frameType == Opcodes.DOUBLE) {
/* 224 */         result.add(Opcodes.TOP);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitInsn(int opcode) {
/* 231 */     super.visitInsn(opcode);
/* 232 */     execute(opcode, 0, (String)null);
/* 233 */     if ((opcode >= 172 && opcode <= 177) || opcode == 191) {
/* 234 */       this.locals = null;
/* 235 */       this.stack = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitIntInsn(int opcode, int operand) {
/* 241 */     super.visitIntInsn(opcode, operand);
/* 242 */     execute(opcode, operand, (String)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitVarInsn(int opcode, int var) {
/* 247 */     super.visitVarInsn(opcode, var);
/* 248 */     boolean isLongOrDouble = (opcode == 22 || opcode == 24 || opcode == 55 || opcode == 57);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 253 */     this.maxLocals = Math.max(this.maxLocals, var + (isLongOrDouble ? 2 : 1));
/* 254 */     execute(opcode, var, (String)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitTypeInsn(int opcode, String type) {
/* 259 */     if (opcode == 187) {
/* 260 */       if (this.labels == null) {
/* 261 */         Label label = new Label();
/* 262 */         this.labels = new ArrayList<Label>(3);
/* 263 */         this.labels.add(label);
/* 264 */         if (this.mv != null) {
/* 265 */           this.mv.visitLabel(label);
/*     */         }
/*     */       } 
/* 268 */       for (Label label : this.labels) {
/* 269 */         this.uninitializedTypes.put(label, type);
/*     */       }
/*     */     } 
/* 272 */     super.visitTypeInsn(opcode, type);
/* 273 */     execute(opcode, 0, type);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitFieldInsn(int opcode, String owner, String name, String descriptor) {
/* 279 */     super.visitFieldInsn(opcode, owner, name, descriptor);
/* 280 */     execute(opcode, 0, descriptor);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitMethodInsn(int opcodeAndSource, String owner, String name, String descriptor, boolean isInterface) {
/* 290 */     if (this.api < 327680 && (opcodeAndSource & 0x100) == 0) {
/*     */       
/* 292 */       super.visitMethodInsn(opcodeAndSource, owner, name, descriptor, isInterface);
/*     */       return;
/*     */     } 
/* 295 */     super.visitMethodInsn(opcodeAndSource, owner, name, descriptor, isInterface);
/* 296 */     int opcode = opcodeAndSource & 0xFFFFFEFF;
/*     */     
/* 298 */     if (this.locals == null) {
/* 299 */       this.labels = null;
/*     */       return;
/*     */     } 
/* 302 */     pop(descriptor);
/* 303 */     if (opcode != 184) {
/* 304 */       Object value = pop();
/* 305 */       if (opcode == 183 && name.equals("<init>")) {
/*     */         Object initializedValue;
/* 307 */         if (value == Opcodes.UNINITIALIZED_THIS) {
/* 308 */           initializedValue = this.owner;
/*     */         } else {
/* 310 */           initializedValue = this.uninitializedTypes.get(value);
/*     */         }  int i;
/* 312 */         for (i = 0; i < this.locals.size(); i++) {
/* 313 */           if (this.locals.get(i) == value) {
/* 314 */             this.locals.set(i, initializedValue);
/*     */           }
/*     */         } 
/* 317 */         for (i = 0; i < this.stack.size(); i++) {
/* 318 */           if (this.stack.get(i) == value) {
/* 319 */             this.stack.set(i, initializedValue);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 324 */     pushDescriptor(descriptor);
/* 325 */     this.labels = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitInvokeDynamicInsn(String name, String descriptor, Handle bootstrapMethodHandle, Object... bootstrapMethodArguments) {
/* 334 */     super.visitInvokeDynamicInsn(name, descriptor, bootstrapMethodHandle, bootstrapMethodArguments);
/* 335 */     if (this.locals == null) {
/* 336 */       this.labels = null;
/*     */       return;
/*     */     } 
/* 339 */     pop(descriptor);
/* 340 */     pushDescriptor(descriptor);
/* 341 */     this.labels = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitJumpInsn(int opcode, Label label) {
/* 346 */     super.visitJumpInsn(opcode, label);
/* 347 */     execute(opcode, 0, (String)null);
/* 348 */     if (opcode == 167) {
/* 349 */       this.locals = null;
/* 350 */       this.stack = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitLabel(Label label) {
/* 356 */     super.visitLabel(label);
/* 357 */     if (this.labels == null) {
/* 358 */       this.labels = new ArrayList<Label>(3);
/*     */     }
/* 360 */     this.labels.add(label);
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitLdcInsn(Object value) {
/* 365 */     super.visitLdcInsn(value);
/* 366 */     if (this.locals == null) {
/* 367 */       this.labels = null;
/*     */       return;
/*     */     } 
/* 370 */     if (value instanceof Integer) {
/* 371 */       push(Opcodes.INTEGER);
/* 372 */     } else if (value instanceof Long) {
/* 373 */       push(Opcodes.LONG);
/* 374 */       push(Opcodes.TOP);
/* 375 */     } else if (value instanceof Float) {
/* 376 */       push(Opcodes.FLOAT);
/* 377 */     } else if (value instanceof Double) {
/* 378 */       push(Opcodes.DOUBLE);
/* 379 */       push(Opcodes.TOP);
/* 380 */     } else if (value instanceof String) {
/* 381 */       push("java/lang/String");
/* 382 */     } else if (value instanceof Type) {
/* 383 */       int sort = ((Type)value).getSort();
/* 384 */       if (sort == 10 || sort == 9) {
/* 385 */         push("java/lang/Class");
/* 386 */       } else if (sort == 11) {
/* 387 */         push("java/lang/invoke/MethodType");
/*     */       } else {
/* 389 */         throw new IllegalArgumentException();
/*     */       } 
/* 391 */     } else if (value instanceof Handle) {
/* 392 */       push("java/lang/invoke/MethodHandle");
/* 393 */     } else if (value instanceof ConstantDynamic) {
/* 394 */       pushDescriptor(((ConstantDynamic)value).getDescriptor());
/*     */     } else {
/* 396 */       throw new IllegalArgumentException();
/*     */     } 
/* 398 */     this.labels = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitIincInsn(int var, int increment) {
/* 403 */     super.visitIincInsn(var, increment);
/* 404 */     this.maxLocals = Math.max(this.maxLocals, var + 1);
/* 405 */     execute(132, var, (String)null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitTableSwitchInsn(int min, int max, Label dflt, Label... labels) {
/* 411 */     super.visitTableSwitchInsn(min, max, dflt, labels);
/* 412 */     execute(170, 0, (String)null);
/* 413 */     this.locals = null;
/* 414 */     this.stack = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitLookupSwitchInsn(Label dflt, int[] keys, Label[] labels) {
/* 419 */     super.visitLookupSwitchInsn(dflt, keys, labels);
/* 420 */     execute(171, 0, (String)null);
/* 421 */     this.locals = null;
/* 422 */     this.stack = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitMultiANewArrayInsn(String descriptor, int numDimensions) {
/* 427 */     super.visitMultiANewArrayInsn(descriptor, numDimensions);
/* 428 */     execute(197, numDimensions, descriptor);
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
/* 439 */     char firstDescriptorChar = descriptor.charAt(0);
/* 440 */     this
/* 441 */       .maxLocals = Math.max(this.maxLocals, index + ((firstDescriptorChar == 'J' || firstDescriptorChar == 'D') ? 2 : 1));
/*     */     
/* 443 */     super.visitLocalVariable(name, descriptor, signature, start, end, index);
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitMaxs(int maxStack, int maxLocals) {
/* 448 */     if (this.mv != null) {
/* 449 */       this.maxStack = Math.max(this.maxStack, maxStack);
/* 450 */       this.maxLocals = Math.max(this.maxLocals, maxLocals);
/* 451 */       this.mv.visitMaxs(this.maxStack, this.maxLocals);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private Object get(int local) {
/* 458 */     this.maxLocals = Math.max(this.maxLocals, local + 1);
/* 459 */     return (local < this.locals.size()) ? this.locals.get(local) : Opcodes.TOP;
/*     */   }
/*     */   
/*     */   private void set(int local, Object type) {
/* 463 */     this.maxLocals = Math.max(this.maxLocals, local + 1);
/* 464 */     while (local >= this.locals.size()) {
/* 465 */       this.locals.add(Opcodes.TOP);
/*     */     }
/* 467 */     this.locals.set(local, type);
/*     */   }
/*     */   
/*     */   private void push(Object type) {
/* 471 */     this.stack.add(type);
/* 472 */     this.maxStack = Math.max(this.maxStack, this.stack.size());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void pushDescriptor(String fieldOrMethodDescriptor) {
/* 478 */     String descriptor = (fieldOrMethodDescriptor.charAt(0) == '(') ? Type.getReturnType(fieldOrMethodDescriptor).getDescriptor() : fieldOrMethodDescriptor;
/*     */     
/* 480 */     switch (descriptor.charAt(0)) {
/*     */       case 'V':
/*     */         return;
/*     */       case 'B':
/*     */       case 'C':
/*     */       case 'I':
/*     */       case 'S':
/*     */       case 'Z':
/* 488 */         push(Opcodes.INTEGER);
/*     */         return;
/*     */       case 'F':
/* 491 */         push(Opcodes.FLOAT);
/*     */         return;
/*     */       case 'J':
/* 494 */         push(Opcodes.LONG);
/* 495 */         push(Opcodes.TOP);
/*     */         return;
/*     */       case 'D':
/* 498 */         push(Opcodes.DOUBLE);
/* 499 */         push(Opcodes.TOP);
/*     */         return;
/*     */       case '[':
/* 502 */         push(descriptor);
/*     */         return;
/*     */       case 'L':
/* 505 */         push(descriptor.substring(1, descriptor.length() - 1));
/*     */         return;
/*     */     } 
/* 508 */     throw new AssertionError();
/*     */   }
/*     */ 
/*     */   
/*     */   private Object pop() {
/* 513 */     return this.stack.remove(this.stack.size() - 1);
/*     */   }
/*     */   
/*     */   private void pop(int numSlots) {
/* 517 */     int size = this.stack.size();
/* 518 */     int end = size - numSlots;
/* 519 */     for (int i = size - 1; i >= end; i--) {
/* 520 */       this.stack.remove(i);
/*     */     }
/*     */   }
/*     */   
/*     */   private void pop(String descriptor) {
/* 525 */     char firstDescriptorChar = descriptor.charAt(0);
/* 526 */     if (firstDescriptorChar == '(') {
/* 527 */       int numSlots = 0;
/* 528 */       Type[] types = Type.getArgumentTypes(descriptor);
/* 529 */       for (Type type : types) {
/* 530 */         numSlots += type.getSize();
/*     */       }
/* 532 */       pop(numSlots);
/* 533 */     } else if (firstDescriptorChar == 'J' || firstDescriptorChar == 'D') {
/* 534 */       pop(2);
/*     */     } else {
/* 536 */       pop(1);
/*     */     } 
/*     */   }
/*     */   private void execute(int opcode, int intArg, String stringArg) {
/*     */     Object value1, value2, value3, t4;
/* 541 */     if (opcode == 168 || opcode == 169) {
/* 542 */       throw new IllegalArgumentException("JSR/RET are not supported");
/*     */     }
/* 544 */     if (this.locals == null) {
/* 545 */       this.labels = null;
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */ 
/*     */     
/* 552 */     switch (opcode) {
/*     */       case 0:
/*     */       case 116:
/*     */       case 117:
/*     */       case 118:
/*     */       case 119:
/*     */       case 145:
/*     */       case 146:
/*     */       case 147:
/*     */       case 167:
/*     */       case 177:
/*     */         break;
/*     */       case 1:
/* 565 */         push(Opcodes.NULL);
/*     */         break;
/*     */       case 2:
/*     */       case 3:
/*     */       case 4:
/*     */       case 5:
/*     */       case 6:
/*     */       case 7:
/*     */       case 8:
/*     */       case 16:
/*     */       case 17:
/* 576 */         push(Opcodes.INTEGER);
/*     */         break;
/*     */       case 9:
/*     */       case 10:
/* 580 */         push(Opcodes.LONG);
/* 581 */         push(Opcodes.TOP);
/*     */         break;
/*     */       case 11:
/*     */       case 12:
/*     */       case 13:
/* 586 */         push(Opcodes.FLOAT);
/*     */         break;
/*     */       case 14:
/*     */       case 15:
/* 590 */         push(Opcodes.DOUBLE);
/* 591 */         push(Opcodes.TOP);
/*     */         break;
/*     */       case 21:
/*     */       case 23:
/*     */       case 25:
/* 596 */         push(get(intArg));
/*     */         break;
/*     */       case 22:
/*     */       case 24:
/* 600 */         push(get(intArg));
/* 601 */         push(Opcodes.TOP);
/*     */         break;
/*     */       case 47:
/*     */       case 143:
/* 605 */         pop(2);
/* 606 */         push(Opcodes.LONG);
/* 607 */         push(Opcodes.TOP);
/*     */         break;
/*     */       case 49:
/*     */       case 138:
/* 611 */         pop(2);
/* 612 */         push(Opcodes.DOUBLE);
/* 613 */         push(Opcodes.TOP);
/*     */         break;
/*     */       case 50:
/* 616 */         pop(1);
/* 617 */         value1 = pop();
/* 618 */         if (value1 instanceof String) {
/* 619 */           pushDescriptor(((String)value1).substring(1)); break;
/* 620 */         }  if (value1 == Opcodes.NULL) {
/* 621 */           push(value1); break;
/*     */         } 
/* 623 */         push("java/lang/Object");
/*     */         break;
/*     */       
/*     */       case 54:
/*     */       case 56:
/*     */       case 58:
/* 629 */         value1 = pop();
/* 630 */         set(intArg, value1);
/* 631 */         if (intArg > 0) {
/* 632 */           Object object = get(intArg - 1);
/* 633 */           if (object == Opcodes.LONG || object == Opcodes.DOUBLE) {
/* 634 */             set(intArg - 1, Opcodes.TOP);
/*     */           }
/*     */         } 
/*     */         break;
/*     */       case 55:
/*     */       case 57:
/* 640 */         pop(1);
/* 641 */         value1 = pop();
/* 642 */         set(intArg, value1);
/* 643 */         set(intArg + 1, Opcodes.TOP);
/* 644 */         if (intArg > 0) {
/* 645 */           Object object = get(intArg - 1);
/* 646 */           if (object == Opcodes.LONG || object == Opcodes.DOUBLE) {
/* 647 */             set(intArg - 1, Opcodes.TOP);
/*     */           }
/*     */         } 
/*     */         break;
/*     */       case 79:
/*     */       case 81:
/*     */       case 83:
/*     */       case 84:
/*     */       case 85:
/*     */       case 86:
/* 657 */         pop(3);
/*     */         break;
/*     */       case 80:
/*     */       case 82:
/* 661 */         pop(4);
/*     */         break;
/*     */       case 87:
/*     */       case 153:
/*     */       case 154:
/*     */       case 155:
/*     */       case 156:
/*     */       case 157:
/*     */       case 158:
/*     */       case 170:
/*     */       case 171:
/*     */       case 172:
/*     */       case 174:
/*     */       case 176:
/*     */       case 191:
/*     */       case 194:
/*     */       case 195:
/*     */       case 198:
/*     */       case 199:
/* 680 */         pop(1);
/*     */         break;
/*     */       case 88:
/*     */       case 159:
/*     */       case 160:
/*     */       case 161:
/*     */       case 162:
/*     */       case 163:
/*     */       case 164:
/*     */       case 165:
/*     */       case 166:
/*     */       case 173:
/*     */       case 175:
/* 693 */         pop(2);
/*     */         break;
/*     */       case 89:
/* 696 */         value1 = pop();
/* 697 */         push(value1);
/* 698 */         push(value1);
/*     */         break;
/*     */       case 90:
/* 701 */         value1 = pop();
/* 702 */         value2 = pop();
/* 703 */         push(value1);
/* 704 */         push(value2);
/* 705 */         push(value1);
/*     */         break;
/*     */       case 91:
/* 708 */         value1 = pop();
/* 709 */         value2 = pop();
/* 710 */         value3 = pop();
/* 711 */         push(value1);
/* 712 */         push(value3);
/* 713 */         push(value2);
/* 714 */         push(value1);
/*     */         break;
/*     */       case 92:
/* 717 */         value1 = pop();
/* 718 */         value2 = pop();
/* 719 */         push(value2);
/* 720 */         push(value1);
/* 721 */         push(value2);
/* 722 */         push(value1);
/*     */         break;
/*     */       case 93:
/* 725 */         value1 = pop();
/* 726 */         value2 = pop();
/* 727 */         value3 = pop();
/* 728 */         push(value2);
/* 729 */         push(value1);
/* 730 */         push(value3);
/* 731 */         push(value2);
/* 732 */         push(value1);
/*     */         break;
/*     */       case 94:
/* 735 */         value1 = pop();
/* 736 */         value2 = pop();
/* 737 */         value3 = pop();
/* 738 */         t4 = pop();
/* 739 */         push(value2);
/* 740 */         push(value1);
/* 741 */         push(t4);
/* 742 */         push(value3);
/* 743 */         push(value2);
/* 744 */         push(value1);
/*     */         break;
/*     */       case 95:
/* 747 */         value1 = pop();
/* 748 */         value2 = pop();
/* 749 */         push(value1);
/* 750 */         push(value2);
/*     */         break;
/*     */       case 46:
/*     */       case 51:
/*     */       case 52:
/*     */       case 53:
/*     */       case 96:
/*     */       case 100:
/*     */       case 104:
/*     */       case 108:
/*     */       case 112:
/*     */       case 120:
/*     */       case 122:
/*     */       case 124:
/*     */       case 126:
/*     */       case 128:
/*     */       case 130:
/*     */       case 136:
/*     */       case 142:
/*     */       case 149:
/*     */       case 150:
/* 771 */         pop(2);
/* 772 */         push(Opcodes.INTEGER);
/*     */         break;
/*     */       case 97:
/*     */       case 101:
/*     */       case 105:
/*     */       case 109:
/*     */       case 113:
/*     */       case 127:
/*     */       case 129:
/*     */       case 131:
/* 782 */         pop(4);
/* 783 */         push(Opcodes.LONG);
/* 784 */         push(Opcodes.TOP);
/*     */         break;
/*     */       case 48:
/*     */       case 98:
/*     */       case 102:
/*     */       case 106:
/*     */       case 110:
/*     */       case 114:
/*     */       case 137:
/*     */       case 144:
/* 794 */         pop(2);
/* 795 */         push(Opcodes.FLOAT);
/*     */         break;
/*     */       case 99:
/*     */       case 103:
/*     */       case 107:
/*     */       case 111:
/*     */       case 115:
/* 802 */         pop(4);
/* 803 */         push(Opcodes.DOUBLE);
/* 804 */         push(Opcodes.TOP);
/*     */         break;
/*     */       case 121:
/*     */       case 123:
/*     */       case 125:
/* 809 */         pop(3);
/* 810 */         push(Opcodes.LONG);
/* 811 */         push(Opcodes.TOP);
/*     */         break;
/*     */       case 132:
/* 814 */         set(intArg, Opcodes.INTEGER);
/*     */         break;
/*     */       case 133:
/*     */       case 140:
/* 818 */         pop(1);
/* 819 */         push(Opcodes.LONG);
/* 820 */         push(Opcodes.TOP);
/*     */         break;
/*     */       case 134:
/* 823 */         pop(1);
/* 824 */         push(Opcodes.FLOAT);
/*     */         break;
/*     */       case 135:
/*     */       case 141:
/* 828 */         pop(1);
/* 829 */         push(Opcodes.DOUBLE);
/* 830 */         push(Opcodes.TOP);
/*     */         break;
/*     */       case 139:
/*     */       case 190:
/*     */       case 193:
/* 835 */         pop(1);
/* 836 */         push(Opcodes.INTEGER);
/*     */         break;
/*     */       case 148:
/*     */       case 151:
/*     */       case 152:
/* 841 */         pop(4);
/* 842 */         push(Opcodes.INTEGER);
/*     */         break;
/*     */       case 178:
/* 845 */         pushDescriptor(stringArg);
/*     */         break;
/*     */       case 179:
/* 848 */         pop(stringArg);
/*     */         break;
/*     */       case 180:
/* 851 */         pop(1);
/* 852 */         pushDescriptor(stringArg);
/*     */         break;
/*     */       case 181:
/* 855 */         pop(stringArg);
/* 856 */         pop();
/*     */         break;
/*     */       case 187:
/* 859 */         push(this.labels.get(0));
/*     */         break;
/*     */       case 188:
/* 862 */         pop();
/* 863 */         switch (intArg) {
/*     */           case 4:
/* 865 */             pushDescriptor("[Z");
/*     */             break;
/*     */           case 5:
/* 868 */             pushDescriptor("[C");
/*     */             break;
/*     */           case 8:
/* 871 */             pushDescriptor("[B");
/*     */             break;
/*     */           case 9:
/* 874 */             pushDescriptor("[S");
/*     */             break;
/*     */           case 10:
/* 877 */             pushDescriptor("[I");
/*     */             break;
/*     */           case 6:
/* 880 */             pushDescriptor("[F");
/*     */             break;
/*     */           case 7:
/* 883 */             pushDescriptor("[D");
/*     */             break;
/*     */           case 11:
/* 886 */             pushDescriptor("[J");
/*     */             break;
/*     */         } 
/* 889 */         throw new IllegalArgumentException("Invalid array type " + intArg);
/*     */ 
/*     */       
/*     */       case 189:
/* 893 */         pop();
/* 894 */         pushDescriptor("[" + Type.getObjectType(stringArg));
/*     */         break;
/*     */       case 192:
/* 897 */         pop();
/* 898 */         pushDescriptor(Type.getObjectType(stringArg).getDescriptor());
/*     */         break;
/*     */       case 197:
/* 901 */         pop(intArg);
/* 902 */         pushDescriptor(stringArg);
/*     */         break;
/*     */       default:
/* 905 */         throw new IllegalArgumentException("Invalid opcode " + opcode);
/*     */     } 
/* 907 */     this.labels = null;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\objectweb\asm\commons\AnalyzerAdapter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */