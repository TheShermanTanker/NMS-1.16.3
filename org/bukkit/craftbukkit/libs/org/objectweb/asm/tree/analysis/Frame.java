/*     */ package org.bukkit.craftbukkit.libs.org.objectweb.asm.tree.analysis;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.Type;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.tree.AbstractInsnNode;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.tree.IincInsnNode;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.tree.InvokeDynamicInsnNode;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.tree.LabelNode;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.tree.MethodInsnNode;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.tree.MultiANewArrayInsnNode;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.tree.VarInsnNode;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Frame<V extends Value>
/*     */ {
/*     */   private V returnValue;
/*     */   private V[] values;
/*     */   private int numLocals;
/*     */   private int numStack;
/*     */   
/*     */   public Frame(int numLocals, int numStack) {
/*  78 */     this.values = (V[])new Value[numLocals + numStack];
/*  79 */     this.numLocals = numLocals;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Frame(Frame<? extends V> frame) {
/*  88 */     this(frame.numLocals, frame.values.length - frame.numLocals);
/*  89 */     init(frame);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Frame<V> init(Frame<? extends V> frame) {
/*  99 */     this.returnValue = frame.returnValue;
/* 100 */     System.arraycopy(frame.values, 0, this.values, 0, this.values.length);
/* 101 */     this.numStack = frame.numStack;
/* 102 */     return this;
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
/*     */   public void initJumpTarget(int opcode, LabelNode target) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReturn(V v) {
/* 134 */     this.returnValue = v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLocals() {
/* 143 */     return this.numLocals;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxStackSize() {
/* 152 */     return this.values.length - this.numLocals;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public V getLocal(int index) {
/* 163 */     if (index >= this.numLocals) {
/* 164 */       throw new IndexOutOfBoundsException("Trying to get an inexistant local variable " + index);
/*     */     }
/* 166 */     return this.values[index];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLocal(int index, V value) {
/* 177 */     if (index >= this.numLocals) {
/* 178 */       throw new IndexOutOfBoundsException("Trying to set an inexistant local variable " + index);
/*     */     }
/* 180 */     this.values[index] = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStackSize() {
/* 190 */     return this.numStack;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public V getStack(int index) {
/* 201 */     return this.values[this.numLocals + index];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStack(int index, V value) {
/* 212 */     this.values[this.numLocals + index] = value;
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearStack() {
/* 217 */     this.numStack = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public V pop() {
/* 227 */     if (this.numStack == 0) {
/* 228 */       throw new IndexOutOfBoundsException("Cannot pop operand off an empty stack.");
/*     */     }
/* 230 */     return this.values[this.numLocals + --this.numStack];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void push(V value) {
/* 240 */     if (this.numLocals + this.numStack >= this.values.length) {
/* 241 */       throw new IndexOutOfBoundsException("Insufficient maximum stack size.");
/*     */     }
/* 243 */     this.values[this.numLocals + this.numStack++] = value;
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
/*     */   public void execute(AbstractInsnNode insn, Interpreter<V> interpreter) throws AnalyzerException {
/*     */     V value1;
/*     */     V value2;
/*     */     V value3;
/*     */     int var;
/*     */     List<V> valueList;
/*     */     int i;
/* 262 */     switch (insn.getOpcode()) {
/*     */       case 0:
/*     */         return;
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/*     */       case 4:
/*     */       case 5:
/*     */       case 6:
/*     */       case 7:
/*     */       case 8:
/*     */       case 9:
/*     */       case 10:
/*     */       case 11:
/*     */       case 12:
/*     */       case 13:
/*     */       case 14:
/*     */       case 15:
/*     */       case 16:
/*     */       case 17:
/*     */       case 18:
/* 283 */         push(interpreter.newOperation(insn));
/*     */       
/*     */       case 21:
/*     */       case 22:
/*     */       case 23:
/*     */       case 24:
/*     */       case 25:
/* 290 */         push(interpreter.copyOperation(insn, getLocal(((VarInsnNode)insn).var)));
/*     */       
/*     */       case 54:
/*     */       case 55:
/*     */       case 56:
/*     */       case 57:
/*     */       case 58:
/* 297 */         value1 = interpreter.copyOperation(insn, pop());
/* 298 */         var = ((VarInsnNode)insn).var;
/* 299 */         setLocal(var, value1);
/* 300 */         if (value1.getSize() == 2) {
/* 301 */           setLocal(var + 1, interpreter.newEmptyValue(var + 1));
/*     */         }
/* 303 */         if (var > 0) {
/* 304 */           Value local = (Value)getLocal(var - 1);
/* 305 */           if (local != null && local.getSize() == 2) {
/* 306 */             setLocal(var - 1, interpreter.newEmptyValue(var - 1));
/*     */           }
/*     */         } 
/*     */       
/*     */       case 79:
/*     */       case 80:
/*     */       case 81:
/*     */       case 82:
/*     */       case 83:
/*     */       case 84:
/*     */       case 85:
/*     */       case 86:
/* 318 */         value3 = pop();
/* 319 */         value2 = pop();
/* 320 */         value1 = pop();
/* 321 */         interpreter.ternaryOperation(insn, value1, value2, value3);
/*     */       
/*     */       case 87:
/* 324 */         if (pop().getSize() == 2) {
/* 325 */           throw new AnalyzerException(insn, "Illegal use of POP");
/*     */         }
/*     */       
/*     */       case 88:
/* 329 */         if (pop().getSize() == 1 && pop().getSize() != 1) {
/* 330 */           throw new AnalyzerException(insn, "Illegal use of POP2");
/*     */         }
/*     */       
/*     */       case 89:
/* 334 */         value1 = pop();
/* 335 */         if (value1.getSize() != 1) {
/* 336 */           throw new AnalyzerException(insn, "Illegal use of DUP");
/*     */         }
/* 338 */         push(value1);
/* 339 */         push(interpreter.copyOperation(insn, value1));
/*     */       
/*     */       case 90:
/* 342 */         value1 = pop();
/* 343 */         value2 = pop();
/* 344 */         if (value1.getSize() != 1 || value2.getSize() != 1) {
/* 345 */           throw new AnalyzerException(insn, "Illegal use of DUP_X1");
/*     */         }
/* 347 */         push(interpreter.copyOperation(insn, value1));
/* 348 */         push(value2);
/* 349 */         push(value1);
/*     */       
/*     */       case 91:
/* 352 */         value1 = pop();
/* 353 */         if (value1.getSize() != 1 || !executeDupX2(insn, value1, interpreter))
/*     */         {
/*     */           
/* 356 */           throw new AnalyzerException(insn, "Illegal use of DUP_X2"); } 
/*     */       case 92:
/* 358 */         value1 = pop();
/* 359 */         if (value1.getSize() == 1)
/* 360 */         { value2 = pop();
/* 361 */           if (value2.getSize() == 1)
/* 362 */           { push(value2);
/* 363 */             push(value1);
/* 364 */             push(interpreter.copyOperation(insn, value2));
/* 365 */             push(interpreter.copyOperation(insn, value1));
/*     */             
/*     */              }
/*     */           
/*     */           else
/*     */           
/*     */           { 
/*     */             
/* 373 */             throw new AnalyzerException(insn, "Illegal use of DUP2"); }  } else { push(value1); push(interpreter.copyOperation(insn, value1)); } 
/*     */       case 93:
/* 375 */         value1 = pop();
/* 376 */         if (value1.getSize() == 1)
/* 377 */         { value2 = pop();
/* 378 */           if (value2.getSize() == 1)
/* 379 */           { value3 = pop();
/* 380 */             if (value3.getSize() == 1)
/* 381 */             { push(interpreter.copyOperation(insn, value2));
/* 382 */               push(interpreter.copyOperation(insn, value1));
/* 383 */               push(value3);
/* 384 */               push(value2);
/* 385 */               push(value1);
/*     */ 
/*     */ 
/*     */ 
/*     */               
/*     */                }
/*     */             
/*     */             else
/*     */             
/*     */             { 
/*     */ 
/*     */ 
/*     */               
/* 398 */               throw new AnalyzerException(insn, "Illegal use of DUP2_X1"); }  } else { throw new AnalyzerException(insn, "Illegal use of DUP2_X1"); }  } else { value2 = pop(); if (value2.getSize() == 1) { push(interpreter.copyOperation(insn, value1)); push(value2); push(value1); } else { throw new AnalyzerException(insn, "Illegal use of DUP2_X1"); }  } 
/*     */       case 94:
/* 400 */         value1 = pop();
/* 401 */         if (value1.getSize() == 1)
/* 402 */         { value2 = pop();
/* 403 */           if (value2.getSize() == 1)
/* 404 */           { value3 = pop();
/* 405 */             if (value3.getSize() == 1)
/* 406 */             { V value4 = pop();
/* 407 */               if (value4.getSize() == 1)
/* 408 */               { push(interpreter.copyOperation(insn, value2));
/* 409 */                 push(interpreter.copyOperation(insn, value1));
/* 410 */                 push(value4);
/* 411 */                 push(value3);
/* 412 */                 push(value2);
/* 413 */                 push(value1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/*     */                  }
/*     */               
/*     */               else
/*     */               
/*     */               { 
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/* 428 */                 throw new AnalyzerException(insn, "Illegal use of DUP2_X2"); }  } else { push(interpreter.copyOperation(insn, value2)); push(interpreter.copyOperation(insn, value1)); push(value3); push(value2); push(value1); }  } else { throw new AnalyzerException(insn, "Illegal use of DUP2_X2"); }  } else { if (executeDupX2(insn, value1, interpreter)) return;  throw new AnalyzerException(insn, "Illegal use of DUP2_X2"); } 
/*     */       case 95:
/* 430 */         value2 = pop();
/* 431 */         value1 = pop();
/* 432 */         if (value1.getSize() != 1 || value2.getSize() != 1) {
/* 433 */           throw new AnalyzerException(insn, "Illegal use of SWAP");
/*     */         }
/* 435 */         push(interpreter.copyOperation(insn, value2));
/* 436 */         push(interpreter.copyOperation(insn, value1));
/*     */       
/*     */       case 46:
/*     */       case 47:
/*     */       case 48:
/*     */       case 49:
/*     */       case 50:
/*     */       case 51:
/*     */       case 52:
/*     */       case 53:
/*     */       case 96:
/*     */       case 97:
/*     */       case 98:
/*     */       case 99:
/*     */       case 100:
/*     */       case 101:
/*     */       case 102:
/*     */       case 103:
/*     */       case 104:
/*     */       case 105:
/*     */       case 106:
/*     */       case 107:
/*     */       case 108:
/*     */       case 109:
/*     */       case 110:
/*     */       case 111:
/*     */       case 112:
/*     */       case 113:
/*     */       case 114:
/*     */       case 115:
/*     */       case 120:
/*     */       case 121:
/*     */       case 122:
/*     */       case 123:
/*     */       case 124:
/*     */       case 125:
/*     */       case 126:
/*     */       case 127:
/*     */       case 128:
/*     */       case 129:
/*     */       case 130:
/*     */       case 131:
/*     */       case 148:
/*     */       case 149:
/*     */       case 150:
/*     */       case 151:
/*     */       case 152:
/* 483 */         value2 = pop();
/* 484 */         value1 = pop();
/* 485 */         push(interpreter.binaryOperation(insn, value1, value2));
/*     */       
/*     */       case 116:
/*     */       case 117:
/*     */       case 118:
/*     */       case 119:
/* 491 */         push(interpreter.unaryOperation(insn, pop()));
/*     */       
/*     */       case 132:
/* 494 */         var = ((IincInsnNode)insn).var;
/* 495 */         setLocal(var, interpreter.unaryOperation(insn, getLocal(var)));
/*     */       
/*     */       case 133:
/*     */       case 134:
/*     */       case 135:
/*     */       case 136:
/*     */       case 137:
/*     */       case 138:
/*     */       case 139:
/*     */       case 140:
/*     */       case 141:
/*     */       case 142:
/*     */       case 143:
/*     */       case 144:
/*     */       case 145:
/*     */       case 146:
/*     */       case 147:
/* 512 */         push(interpreter.unaryOperation(insn, pop()));
/*     */       
/*     */       case 153:
/*     */       case 154:
/*     */       case 155:
/*     */       case 156:
/*     */       case 157:
/*     */       case 158:
/* 520 */         interpreter.unaryOperation(insn, pop());
/*     */       
/*     */       case 159:
/*     */       case 160:
/*     */       case 161:
/*     */       case 162:
/*     */       case 163:
/*     */       case 164:
/*     */       case 165:
/*     */       case 166:
/*     */       case 181:
/* 531 */         value2 = pop();
/* 532 */         value1 = pop();
/* 533 */         interpreter.binaryOperation(insn, value1, value2);
/*     */       
/*     */       case 167:
/*     */         return;
/*     */       case 168:
/* 538 */         push(interpreter.newOperation(insn));
/*     */       
/*     */       case 169:
/*     */         return;
/*     */       case 170:
/*     */       case 171:
/* 544 */         interpreter.unaryOperation(insn, pop());
/*     */       
/*     */       case 172:
/*     */       case 173:
/*     */       case 174:
/*     */       case 175:
/*     */       case 176:
/* 551 */         value1 = pop();
/* 552 */         interpreter.unaryOperation(insn, value1);
/* 553 */         interpreter.returnOperation(insn, value1, this.returnValue);
/*     */       
/*     */       case 177:
/* 556 */         if (this.returnValue != null) {
/* 557 */           throw new AnalyzerException(insn, "Incompatible return type");
/*     */         }
/*     */       
/*     */       case 178:
/* 561 */         push(interpreter.newOperation(insn));
/*     */       
/*     */       case 179:
/* 564 */         interpreter.unaryOperation(insn, pop());
/*     */       
/*     */       case 180:
/* 567 */         push(interpreter.unaryOperation(insn, pop()));
/*     */       
/*     */       case 182:
/*     */       case 183:
/*     */       case 184:
/*     */       case 185:
/* 573 */         executeInvokeInsn(insn, ((MethodInsnNode)insn).desc, interpreter);
/*     */       
/*     */       case 186:
/* 576 */         executeInvokeInsn(insn, ((InvokeDynamicInsnNode)insn).desc, interpreter);
/*     */       
/*     */       case 187:
/* 579 */         push(interpreter.newOperation(insn));
/*     */       
/*     */       case 188:
/*     */       case 189:
/*     */       case 190:
/* 584 */         push(interpreter.unaryOperation(insn, pop()));
/*     */       
/*     */       case 191:
/* 587 */         interpreter.unaryOperation(insn, pop());
/*     */       
/*     */       case 192:
/*     */       case 193:
/* 591 */         push(interpreter.unaryOperation(insn, pop()));
/*     */       
/*     */       case 194:
/*     */       case 195:
/* 595 */         interpreter.unaryOperation(insn, pop());
/*     */       
/*     */       case 197:
/* 598 */         valueList = new ArrayList<V>();
/* 599 */         for (i = ((MultiANewArrayInsnNode)insn).dims; i > 0; i--) {
/* 600 */           valueList.add(0, pop());
/*     */         }
/* 602 */         push(interpreter.naryOperation(insn, valueList));
/*     */       
/*     */       case 198:
/*     */       case 199:
/* 606 */         interpreter.unaryOperation(insn, pop());
/*     */     } 
/*     */     
/* 609 */     throw new AnalyzerException(insn, "Illegal opcode " + insn.getOpcode());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean executeDupX2(AbstractInsnNode insn, V value1, Interpreter<V> interpreter) throws AnalyzerException {
/* 616 */     V value2 = pop();
/* 617 */     if (value2.getSize() == 1) {
/* 618 */       V value3 = pop();
/* 619 */       if (value3.getSize() == 1) {
/* 620 */         push(interpreter.copyOperation(insn, value1));
/* 621 */         push(value3);
/* 622 */         push(value2);
/* 623 */         push(value1);
/* 624 */         return true;
/*     */       } 
/*     */     } else {
/* 627 */       push(interpreter.copyOperation(insn, value1));
/* 628 */       push(value2);
/* 629 */       push(value1);
/* 630 */       return true;
/*     */     } 
/* 632 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void executeInvokeInsn(AbstractInsnNode insn, String methodDescriptor, Interpreter<V> interpreter) throws AnalyzerException {
/* 638 */     ArrayList<V> valueList = new ArrayList<V>();
/* 639 */     for (int i = (Type.getArgumentTypes(methodDescriptor)).length; i > 0; i--) {
/* 640 */       valueList.add(0, pop());
/*     */     }
/* 642 */     if (insn.getOpcode() != 184 && insn.getOpcode() != 186) {
/* 643 */       valueList.add(0, pop());
/*     */     }
/* 645 */     if (Type.getReturnType(methodDescriptor) == Type.VOID_TYPE) {
/* 646 */       interpreter.naryOperation(insn, valueList);
/*     */     } else {
/* 648 */       push(interpreter.naryOperation(insn, valueList));
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
/*     */   public boolean merge(Frame<? extends V> frame, Interpreter<V> interpreter) throws AnalyzerException {
/* 663 */     if (this.numStack != frame.numStack) {
/* 664 */       throw new AnalyzerException(null, "Incompatible stack heights");
/*     */     }
/* 666 */     boolean changed = false;
/* 667 */     for (int i = 0; i < this.numLocals + this.numStack; i++) {
/* 668 */       V v = interpreter.merge(this.values[i], frame.values[i]);
/* 669 */       if (!v.equals(this.values[i])) {
/* 670 */         this.values[i] = v;
/* 671 */         changed = true;
/*     */       } 
/*     */     } 
/* 674 */     return changed;
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
/*     */   public boolean merge(Frame<? extends V> frame, boolean[] localsUsed) {
/* 689 */     boolean changed = false;
/* 690 */     for (int i = 0; i < this.numLocals; i++) {
/* 691 */       if (!localsUsed[i] && !this.values[i].equals(frame.values[i])) {
/* 692 */         this.values[i] = frame.values[i];
/* 693 */         changed = true;
/*     */       } 
/*     */     } 
/* 696 */     return changed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 706 */     StringBuilder stringBuilder = new StringBuilder(); int i;
/* 707 */     for (i = 0; i < getLocals(); i++) {
/* 708 */       stringBuilder.append(getLocal(i));
/*     */     }
/* 710 */     stringBuilder.append(' ');
/* 711 */     for (i = 0; i < getStackSize(); i++) {
/* 712 */       stringBuilder.append(getStack(i).toString());
/*     */     }
/* 714 */     return stringBuilder.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\objectweb\asm\tree\analysis\Frame.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */