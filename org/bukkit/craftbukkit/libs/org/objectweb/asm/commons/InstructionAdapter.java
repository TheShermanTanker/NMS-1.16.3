/*      */ package org.bukkit.craftbukkit.libs.org.objectweb.asm.commons;
/*      */ 
/*      */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.ConstantDynamic;
/*      */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.Handle;
/*      */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.Label;
/*      */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.MethodVisitor;
/*      */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.Type;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class InstructionAdapter
/*      */   extends MethodVisitor
/*      */ {
/*   46 */   public static final Type OBJECT_TYPE = Type.getType("Ljava/lang/Object;");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public InstructionAdapter(MethodVisitor methodVisitor) {
/*   56 */     this(524288, methodVisitor);
/*   57 */     if (getClass() != InstructionAdapter.class) {
/*   58 */       throw new IllegalStateException();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected InstructionAdapter(int api, MethodVisitor methodVisitor) {
/*   71 */     super(api, methodVisitor);
/*      */   }
/*      */ 
/*      */   
/*      */   public void visitInsn(int opcode) {
/*   76 */     switch (opcode) {
/*      */       case 0:
/*   78 */         nop();
/*      */         return;
/*      */       case 1:
/*   81 */         aconst((Object)null);
/*      */         return;
/*      */       case 2:
/*      */       case 3:
/*      */       case 4:
/*      */       case 5:
/*      */       case 6:
/*      */       case 7:
/*      */       case 8:
/*   90 */         iconst(opcode - 3);
/*      */         return;
/*      */       case 9:
/*      */       case 10:
/*   94 */         lconst((opcode - 9));
/*      */         return;
/*      */       case 11:
/*      */       case 12:
/*      */       case 13:
/*   99 */         fconst((opcode - 11));
/*      */         return;
/*      */       case 14:
/*      */       case 15:
/*  103 */         dconst((opcode - 14));
/*      */         return;
/*      */       case 46:
/*  106 */         aload(Type.INT_TYPE);
/*      */         return;
/*      */       case 47:
/*  109 */         aload(Type.LONG_TYPE);
/*      */         return;
/*      */       case 48:
/*  112 */         aload(Type.FLOAT_TYPE);
/*      */         return;
/*      */       case 49:
/*  115 */         aload(Type.DOUBLE_TYPE);
/*      */         return;
/*      */       case 50:
/*  118 */         aload(OBJECT_TYPE);
/*      */         return;
/*      */       case 51:
/*  121 */         aload(Type.BYTE_TYPE);
/*      */         return;
/*      */       case 52:
/*  124 */         aload(Type.CHAR_TYPE);
/*      */         return;
/*      */       case 53:
/*  127 */         aload(Type.SHORT_TYPE);
/*      */         return;
/*      */       case 79:
/*  130 */         astore(Type.INT_TYPE);
/*      */         return;
/*      */       case 80:
/*  133 */         astore(Type.LONG_TYPE);
/*      */         return;
/*      */       case 81:
/*  136 */         astore(Type.FLOAT_TYPE);
/*      */         return;
/*      */       case 82:
/*  139 */         astore(Type.DOUBLE_TYPE);
/*      */         return;
/*      */       case 83:
/*  142 */         astore(OBJECT_TYPE);
/*      */         return;
/*      */       case 84:
/*  145 */         astore(Type.BYTE_TYPE);
/*      */         return;
/*      */       case 85:
/*  148 */         astore(Type.CHAR_TYPE);
/*      */         return;
/*      */       case 86:
/*  151 */         astore(Type.SHORT_TYPE);
/*      */         return;
/*      */       case 87:
/*  154 */         pop();
/*      */         return;
/*      */       case 88:
/*  157 */         pop2();
/*      */         return;
/*      */       case 89:
/*  160 */         dup();
/*      */         return;
/*      */       case 90:
/*  163 */         dupX1();
/*      */         return;
/*      */       case 91:
/*  166 */         dupX2();
/*      */         return;
/*      */       case 92:
/*  169 */         dup2();
/*      */         return;
/*      */       case 93:
/*  172 */         dup2X1();
/*      */         return;
/*      */       case 94:
/*  175 */         dup2X2();
/*      */         return;
/*      */       case 95:
/*  178 */         swap();
/*      */         return;
/*      */       case 96:
/*  181 */         add(Type.INT_TYPE);
/*      */         return;
/*      */       case 97:
/*  184 */         add(Type.LONG_TYPE);
/*      */         return;
/*      */       case 98:
/*  187 */         add(Type.FLOAT_TYPE);
/*      */         return;
/*      */       case 99:
/*  190 */         add(Type.DOUBLE_TYPE);
/*      */         return;
/*      */       case 100:
/*  193 */         sub(Type.INT_TYPE);
/*      */         return;
/*      */       case 101:
/*  196 */         sub(Type.LONG_TYPE);
/*      */         return;
/*      */       case 102:
/*  199 */         sub(Type.FLOAT_TYPE);
/*      */         return;
/*      */       case 103:
/*  202 */         sub(Type.DOUBLE_TYPE);
/*      */         return;
/*      */       case 104:
/*  205 */         mul(Type.INT_TYPE);
/*      */         return;
/*      */       case 105:
/*  208 */         mul(Type.LONG_TYPE);
/*      */         return;
/*      */       case 106:
/*  211 */         mul(Type.FLOAT_TYPE);
/*      */         return;
/*      */       case 107:
/*  214 */         mul(Type.DOUBLE_TYPE);
/*      */         return;
/*      */       case 108:
/*  217 */         div(Type.INT_TYPE);
/*      */         return;
/*      */       case 109:
/*  220 */         div(Type.LONG_TYPE);
/*      */         return;
/*      */       case 110:
/*  223 */         div(Type.FLOAT_TYPE);
/*      */         return;
/*      */       case 111:
/*  226 */         div(Type.DOUBLE_TYPE);
/*      */         return;
/*      */       case 112:
/*  229 */         rem(Type.INT_TYPE);
/*      */         return;
/*      */       case 113:
/*  232 */         rem(Type.LONG_TYPE);
/*      */         return;
/*      */       case 114:
/*  235 */         rem(Type.FLOAT_TYPE);
/*      */         return;
/*      */       case 115:
/*  238 */         rem(Type.DOUBLE_TYPE);
/*      */         return;
/*      */       case 116:
/*  241 */         neg(Type.INT_TYPE);
/*      */         return;
/*      */       case 117:
/*  244 */         neg(Type.LONG_TYPE);
/*      */         return;
/*      */       case 118:
/*  247 */         neg(Type.FLOAT_TYPE);
/*      */         return;
/*      */       case 119:
/*  250 */         neg(Type.DOUBLE_TYPE);
/*      */         return;
/*      */       case 120:
/*  253 */         shl(Type.INT_TYPE);
/*      */         return;
/*      */       case 121:
/*  256 */         shl(Type.LONG_TYPE);
/*      */         return;
/*      */       case 122:
/*  259 */         shr(Type.INT_TYPE);
/*      */         return;
/*      */       case 123:
/*  262 */         shr(Type.LONG_TYPE);
/*      */         return;
/*      */       case 124:
/*  265 */         ushr(Type.INT_TYPE);
/*      */         return;
/*      */       case 125:
/*  268 */         ushr(Type.LONG_TYPE);
/*      */         return;
/*      */       case 126:
/*  271 */         and(Type.INT_TYPE);
/*      */         return;
/*      */       case 127:
/*  274 */         and(Type.LONG_TYPE);
/*      */         return;
/*      */       case 128:
/*  277 */         or(Type.INT_TYPE);
/*      */         return;
/*      */       case 129:
/*  280 */         or(Type.LONG_TYPE);
/*      */         return;
/*      */       case 130:
/*  283 */         xor(Type.INT_TYPE);
/*      */         return;
/*      */       case 131:
/*  286 */         xor(Type.LONG_TYPE);
/*      */         return;
/*      */       case 133:
/*  289 */         cast(Type.INT_TYPE, Type.LONG_TYPE);
/*      */         return;
/*      */       case 134:
/*  292 */         cast(Type.INT_TYPE, Type.FLOAT_TYPE);
/*      */         return;
/*      */       case 135:
/*  295 */         cast(Type.INT_TYPE, Type.DOUBLE_TYPE);
/*      */         return;
/*      */       case 136:
/*  298 */         cast(Type.LONG_TYPE, Type.INT_TYPE);
/*      */         return;
/*      */       case 137:
/*  301 */         cast(Type.LONG_TYPE, Type.FLOAT_TYPE);
/*      */         return;
/*      */       case 138:
/*  304 */         cast(Type.LONG_TYPE, Type.DOUBLE_TYPE);
/*      */         return;
/*      */       case 139:
/*  307 */         cast(Type.FLOAT_TYPE, Type.INT_TYPE);
/*      */         return;
/*      */       case 140:
/*  310 */         cast(Type.FLOAT_TYPE, Type.LONG_TYPE);
/*      */         return;
/*      */       case 141:
/*  313 */         cast(Type.FLOAT_TYPE, Type.DOUBLE_TYPE);
/*      */         return;
/*      */       case 142:
/*  316 */         cast(Type.DOUBLE_TYPE, Type.INT_TYPE);
/*      */         return;
/*      */       case 143:
/*  319 */         cast(Type.DOUBLE_TYPE, Type.LONG_TYPE);
/*      */         return;
/*      */       case 144:
/*  322 */         cast(Type.DOUBLE_TYPE, Type.FLOAT_TYPE);
/*      */         return;
/*      */       case 145:
/*  325 */         cast(Type.INT_TYPE, Type.BYTE_TYPE);
/*      */         return;
/*      */       case 146:
/*  328 */         cast(Type.INT_TYPE, Type.CHAR_TYPE);
/*      */         return;
/*      */       case 147:
/*  331 */         cast(Type.INT_TYPE, Type.SHORT_TYPE);
/*      */         return;
/*      */       case 148:
/*  334 */         lcmp();
/*      */         return;
/*      */       case 149:
/*  337 */         cmpl(Type.FLOAT_TYPE);
/*      */         return;
/*      */       case 150:
/*  340 */         cmpg(Type.FLOAT_TYPE);
/*      */         return;
/*      */       case 151:
/*  343 */         cmpl(Type.DOUBLE_TYPE);
/*      */         return;
/*      */       case 152:
/*  346 */         cmpg(Type.DOUBLE_TYPE);
/*      */         return;
/*      */       case 172:
/*  349 */         areturn(Type.INT_TYPE);
/*      */         return;
/*      */       case 173:
/*  352 */         areturn(Type.LONG_TYPE);
/*      */         return;
/*      */       case 174:
/*  355 */         areturn(Type.FLOAT_TYPE);
/*      */         return;
/*      */       case 175:
/*  358 */         areturn(Type.DOUBLE_TYPE);
/*      */         return;
/*      */       case 176:
/*  361 */         areturn(OBJECT_TYPE);
/*      */         return;
/*      */       case 177:
/*  364 */         areturn(Type.VOID_TYPE);
/*      */         return;
/*      */       case 190:
/*  367 */         arraylength();
/*      */         return;
/*      */       case 191:
/*  370 */         athrow();
/*      */         return;
/*      */       case 194:
/*  373 */         monitorenter();
/*      */         return;
/*      */       case 195:
/*  376 */         monitorexit();
/*      */         return;
/*      */     } 
/*  379 */     throw new IllegalArgumentException();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitIntInsn(int opcode, int operand) {
/*  385 */     switch (opcode) {
/*      */       case 16:
/*  387 */         iconst(operand);
/*      */         return;
/*      */       case 17:
/*  390 */         iconst(operand);
/*      */         return;
/*      */       case 188:
/*  393 */         switch (operand) {
/*      */           case 4:
/*  395 */             newarray(Type.BOOLEAN_TYPE);
/*      */             return;
/*      */           case 5:
/*  398 */             newarray(Type.CHAR_TYPE);
/*      */             return;
/*      */           case 8:
/*  401 */             newarray(Type.BYTE_TYPE);
/*      */             return;
/*      */           case 9:
/*  404 */             newarray(Type.SHORT_TYPE);
/*      */             return;
/*      */           case 10:
/*  407 */             newarray(Type.INT_TYPE);
/*      */             return;
/*      */           case 6:
/*  410 */             newarray(Type.FLOAT_TYPE);
/*      */             return;
/*      */           case 11:
/*  413 */             newarray(Type.LONG_TYPE);
/*      */             return;
/*      */           case 7:
/*  416 */             newarray(Type.DOUBLE_TYPE);
/*      */             return;
/*      */         } 
/*  419 */         throw new IllegalArgumentException();
/*      */     } 
/*      */ 
/*      */     
/*  423 */     throw new IllegalArgumentException();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitVarInsn(int opcode, int var) {
/*  429 */     switch (opcode) {
/*      */       case 21:
/*  431 */         load(var, Type.INT_TYPE);
/*      */         return;
/*      */       case 22:
/*  434 */         load(var, Type.LONG_TYPE);
/*      */         return;
/*      */       case 23:
/*  437 */         load(var, Type.FLOAT_TYPE);
/*      */         return;
/*      */       case 24:
/*  440 */         load(var, Type.DOUBLE_TYPE);
/*      */         return;
/*      */       case 25:
/*  443 */         load(var, OBJECT_TYPE);
/*      */         return;
/*      */       case 54:
/*  446 */         store(var, Type.INT_TYPE);
/*      */         return;
/*      */       case 55:
/*  449 */         store(var, Type.LONG_TYPE);
/*      */         return;
/*      */       case 56:
/*  452 */         store(var, Type.FLOAT_TYPE);
/*      */         return;
/*      */       case 57:
/*  455 */         store(var, Type.DOUBLE_TYPE);
/*      */         return;
/*      */       case 58:
/*  458 */         store(var, OBJECT_TYPE);
/*      */         return;
/*      */       case 169:
/*  461 */         ret(var);
/*      */         return;
/*      */     } 
/*  464 */     throw new IllegalArgumentException();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitTypeInsn(int opcode, String type) {
/*  470 */     Type objectType = Type.getObjectType(type);
/*  471 */     switch (opcode) {
/*      */       case 187:
/*  473 */         anew(objectType);
/*      */         return;
/*      */       case 189:
/*  476 */         newarray(objectType);
/*      */         return;
/*      */       case 192:
/*  479 */         checkcast(objectType);
/*      */         return;
/*      */       case 193:
/*  482 */         instanceOf(objectType);
/*      */         return;
/*      */     } 
/*  485 */     throw new IllegalArgumentException();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitFieldInsn(int opcode, String owner, String name, String descriptor) {
/*  492 */     switch (opcode) {
/*      */       case 178:
/*  494 */         getstatic(owner, name, descriptor);
/*      */         return;
/*      */       case 179:
/*  497 */         putstatic(owner, name, descriptor);
/*      */         return;
/*      */       case 180:
/*  500 */         getfield(owner, name, descriptor);
/*      */         return;
/*      */       case 181:
/*  503 */         putfield(owner, name, descriptor);
/*      */         return;
/*      */     } 
/*  506 */     throw new IllegalArgumentException();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitMethodInsn(int opcodeAndSource, String owner, String name, String descriptor, boolean isInterface) {
/*  517 */     if (this.api < 327680 && (opcodeAndSource & 0x100) == 0) {
/*      */       
/*  519 */       super.visitMethodInsn(opcodeAndSource, owner, name, descriptor, isInterface);
/*      */       return;
/*      */     } 
/*  522 */     int opcode = opcodeAndSource & 0xFFFFFEFF;
/*      */     
/*  524 */     switch (opcode) {
/*      */       case 183:
/*  526 */         invokespecial(owner, name, descriptor, isInterface);
/*      */         return;
/*      */       case 182:
/*  529 */         invokevirtual(owner, name, descriptor, isInterface);
/*      */         return;
/*      */       case 184:
/*  532 */         invokestatic(owner, name, descriptor, isInterface);
/*      */         return;
/*      */       case 185:
/*  535 */         invokeinterface(owner, name, descriptor);
/*      */         return;
/*      */     } 
/*  538 */     throw new IllegalArgumentException();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitInvokeDynamicInsn(String name, String descriptor, Handle bootstrapMethodHandle, Object... bootstrapMethodArguments) {
/*  548 */     invokedynamic(name, descriptor, bootstrapMethodHandle, bootstrapMethodArguments);
/*      */   }
/*      */ 
/*      */   
/*      */   public void visitJumpInsn(int opcode, Label label) {
/*  553 */     switch (opcode) {
/*      */       case 153:
/*  555 */         ifeq(label);
/*      */         return;
/*      */       case 154:
/*  558 */         ifne(label);
/*      */         return;
/*      */       case 155:
/*  561 */         iflt(label);
/*      */         return;
/*      */       case 156:
/*  564 */         ifge(label);
/*      */         return;
/*      */       case 157:
/*  567 */         ifgt(label);
/*      */         return;
/*      */       case 158:
/*  570 */         ifle(label);
/*      */         return;
/*      */       case 159:
/*  573 */         ificmpeq(label);
/*      */         return;
/*      */       case 160:
/*  576 */         ificmpne(label);
/*      */         return;
/*      */       case 161:
/*  579 */         ificmplt(label);
/*      */         return;
/*      */       case 162:
/*  582 */         ificmpge(label);
/*      */         return;
/*      */       case 163:
/*  585 */         ificmpgt(label);
/*      */         return;
/*      */       case 164:
/*  588 */         ificmple(label);
/*      */         return;
/*      */       case 165:
/*  591 */         ifacmpeq(label);
/*      */         return;
/*      */       case 166:
/*  594 */         ifacmpne(label);
/*      */         return;
/*      */       case 167:
/*  597 */         goTo(label);
/*      */         return;
/*      */       case 168:
/*  600 */         jsr(label);
/*      */         return;
/*      */       case 198:
/*  603 */         ifnull(label);
/*      */         return;
/*      */       case 199:
/*  606 */         ifnonnull(label);
/*      */         return;
/*      */     } 
/*  609 */     throw new IllegalArgumentException();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitLabel(Label label) {
/*  615 */     mark(label);
/*      */   }
/*      */ 
/*      */   
/*      */   public void visitLdcInsn(Object value) {
/*  620 */     if (this.api < 327680 && (value instanceof Handle || (value instanceof Type && ((Type)value)
/*      */       
/*  622 */       .getSort() == 11))) {
/*  623 */       throw new UnsupportedOperationException("This feature requires ASM5");
/*      */     }
/*  625 */     if (this.api < 458752 && value instanceof ConstantDynamic) {
/*  626 */       throw new UnsupportedOperationException("This feature requires ASM7");
/*      */     }
/*  628 */     if (value instanceof Integer) {
/*  629 */       iconst(((Integer)value).intValue());
/*  630 */     } else if (value instanceof Byte) {
/*  631 */       iconst(((Byte)value).intValue());
/*  632 */     } else if (value instanceof Character) {
/*  633 */       iconst(((Character)value).charValue());
/*  634 */     } else if (value instanceof Short) {
/*  635 */       iconst(((Short)value).intValue());
/*  636 */     } else if (value instanceof Boolean) {
/*  637 */       iconst(((Boolean)value).booleanValue() ? 1 : 0);
/*  638 */     } else if (value instanceof Float) {
/*  639 */       fconst(((Float)value).floatValue());
/*  640 */     } else if (value instanceof Long) {
/*  641 */       lconst(((Long)value).longValue());
/*  642 */     } else if (value instanceof Double) {
/*  643 */       dconst(((Double)value).doubleValue());
/*  644 */     } else if (value instanceof String) {
/*  645 */       aconst(value);
/*  646 */     } else if (value instanceof Type) {
/*  647 */       tconst((Type)value);
/*  648 */     } else if (value instanceof Handle) {
/*  649 */       hconst((Handle)value);
/*  650 */     } else if (value instanceof ConstantDynamic) {
/*  651 */       cconst((ConstantDynamic)value);
/*      */     } else {
/*  653 */       throw new IllegalArgumentException();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void visitIincInsn(int var, int increment) {
/*  659 */     iinc(var, increment);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitTableSwitchInsn(int min, int max, Label dflt, Label... labels) {
/*  665 */     tableswitch(min, max, dflt, labels);
/*      */   }
/*      */ 
/*      */   
/*      */   public void visitLookupSwitchInsn(Label dflt, int[] keys, Label[] labels) {
/*  670 */     lookupswitch(dflt, keys, labels);
/*      */   }
/*      */ 
/*      */   
/*      */   public void visitMultiANewArrayInsn(String descriptor, int numDimensions) {
/*  675 */     multianewarray(descriptor, numDimensions);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void nop() {
/*  682 */     this.mv.visitInsn(0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void aconst(Object value) {
/*  696 */     if (value == null) {
/*  697 */       this.mv.visitInsn(1);
/*      */     } else {
/*  699 */       this.mv.visitLdcInsn(value);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void iconst(int intValue) {
/*  709 */     if (intValue >= -1 && intValue <= 5) {
/*  710 */       this.mv.visitInsn(3 + intValue);
/*  711 */     } else if (intValue >= -128 && intValue <= 127) {
/*  712 */       this.mv.visitIntInsn(16, intValue);
/*  713 */     } else if (intValue >= -32768 && intValue <= 32767) {
/*  714 */       this.mv.visitIntInsn(17, intValue);
/*      */     } else {
/*  716 */       this.mv.visitLdcInsn(Integer.valueOf(intValue));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void lconst(long longValue) {
/*  726 */     if (longValue == 0L || longValue == 1L) {
/*  727 */       this.mv.visitInsn(9 + (int)longValue);
/*      */     } else {
/*  729 */       this.mv.visitLdcInsn(Long.valueOf(longValue));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void fconst(float floatValue) {
/*  739 */     int bits = Float.floatToIntBits(floatValue);
/*  740 */     if (bits == 0L || bits == 1065353216 || bits == 1073741824) {
/*  741 */       this.mv.visitInsn(11 + (int)floatValue);
/*      */     } else {
/*  743 */       this.mv.visitLdcInsn(Float.valueOf(floatValue));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void dconst(double doubleValue) {
/*  753 */     long bits = Double.doubleToLongBits(doubleValue);
/*  754 */     if (bits == 0L || bits == 4607182418800017408L) {
/*  755 */       this.mv.visitInsn(14 + (int)doubleValue);
/*      */     } else {
/*  757 */       this.mv.visitLdcInsn(Double.valueOf(doubleValue));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void tconst(Type type) {
/*  767 */     this.mv.visitLdcInsn(type);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void hconst(Handle handle) {
/*  776 */     this.mv.visitLdcInsn(handle);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void cconst(ConstantDynamic constantDynamic) {
/*  785 */     this.mv.visitLdcInsn(constantDynamic);
/*      */   }
/*      */   
/*      */   public void load(int var, Type type) {
/*  789 */     this.mv.visitVarInsn(type.getOpcode(21), var);
/*      */   }
/*      */   
/*      */   public void aload(Type type) {
/*  793 */     this.mv.visitInsn(type.getOpcode(46));
/*      */   }
/*      */   
/*      */   public void store(int var, Type type) {
/*  797 */     this.mv.visitVarInsn(type.getOpcode(54), var);
/*      */   }
/*      */   
/*      */   public void astore(Type type) {
/*  801 */     this.mv.visitInsn(type.getOpcode(79));
/*      */   }
/*      */   
/*      */   public void pop() {
/*  805 */     this.mv.visitInsn(87);
/*      */   }
/*      */   
/*      */   public void pop2() {
/*  809 */     this.mv.visitInsn(88);
/*      */   }
/*      */   
/*      */   public void dup() {
/*  813 */     this.mv.visitInsn(89);
/*      */   }
/*      */   
/*      */   public void dup2() {
/*  817 */     this.mv.visitInsn(92);
/*      */   }
/*      */   
/*      */   public void dupX1() {
/*  821 */     this.mv.visitInsn(90);
/*      */   }
/*      */   
/*      */   public void dupX2() {
/*  825 */     this.mv.visitInsn(91);
/*      */   }
/*      */   
/*      */   public void dup2X1() {
/*  829 */     this.mv.visitInsn(93);
/*      */   }
/*      */   
/*      */   public void dup2X2() {
/*  833 */     this.mv.visitInsn(94);
/*      */   }
/*      */   
/*      */   public void swap() {
/*  837 */     this.mv.visitInsn(95);
/*      */   }
/*      */   
/*      */   public void add(Type type) {
/*  841 */     this.mv.visitInsn(type.getOpcode(96));
/*      */   }
/*      */   
/*      */   public void sub(Type type) {
/*  845 */     this.mv.visitInsn(type.getOpcode(100));
/*      */   }
/*      */   
/*      */   public void mul(Type type) {
/*  849 */     this.mv.visitInsn(type.getOpcode(104));
/*      */   }
/*      */   
/*      */   public void div(Type type) {
/*  853 */     this.mv.visitInsn(type.getOpcode(108));
/*      */   }
/*      */   
/*      */   public void rem(Type type) {
/*  857 */     this.mv.visitInsn(type.getOpcode(112));
/*      */   }
/*      */   
/*      */   public void neg(Type type) {
/*  861 */     this.mv.visitInsn(type.getOpcode(116));
/*      */   }
/*      */   
/*      */   public void shl(Type type) {
/*  865 */     this.mv.visitInsn(type.getOpcode(120));
/*      */   }
/*      */   
/*      */   public void shr(Type type) {
/*  869 */     this.mv.visitInsn(type.getOpcode(122));
/*      */   }
/*      */   
/*      */   public void ushr(Type type) {
/*  873 */     this.mv.visitInsn(type.getOpcode(124));
/*      */   }
/*      */   
/*      */   public void and(Type type) {
/*  877 */     this.mv.visitInsn(type.getOpcode(126));
/*      */   }
/*      */   
/*      */   public void or(Type type) {
/*  881 */     this.mv.visitInsn(type.getOpcode(128));
/*      */   }
/*      */   
/*      */   public void xor(Type type) {
/*  885 */     this.mv.visitInsn(type.getOpcode(130));
/*      */   }
/*      */   
/*      */   public void iinc(int var, int increment) {
/*  889 */     this.mv.visitIincInsn(var, increment);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void cast(Type from, Type to) {
/*  899 */     cast(this.mv, from, to);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static void cast(MethodVisitor methodVisitor, Type from, Type to) {
/*  910 */     if (from != to) {
/*  911 */       if (from == Type.DOUBLE_TYPE) {
/*  912 */         if (to == Type.FLOAT_TYPE) {
/*  913 */           methodVisitor.visitInsn(144);
/*  914 */         } else if (to == Type.LONG_TYPE) {
/*  915 */           methodVisitor.visitInsn(143);
/*      */         } else {
/*  917 */           methodVisitor.visitInsn(142);
/*  918 */           cast(methodVisitor, Type.INT_TYPE, to);
/*      */         } 
/*  920 */       } else if (from == Type.FLOAT_TYPE) {
/*  921 */         if (to == Type.DOUBLE_TYPE) {
/*  922 */           methodVisitor.visitInsn(141);
/*  923 */         } else if (to == Type.LONG_TYPE) {
/*  924 */           methodVisitor.visitInsn(140);
/*      */         } else {
/*  926 */           methodVisitor.visitInsn(139);
/*  927 */           cast(methodVisitor, Type.INT_TYPE, to);
/*      */         } 
/*  929 */       } else if (from == Type.LONG_TYPE) {
/*  930 */         if (to == Type.DOUBLE_TYPE) {
/*  931 */           methodVisitor.visitInsn(138);
/*  932 */         } else if (to == Type.FLOAT_TYPE) {
/*  933 */           methodVisitor.visitInsn(137);
/*      */         } else {
/*  935 */           methodVisitor.visitInsn(136);
/*  936 */           cast(methodVisitor, Type.INT_TYPE, to);
/*      */         }
/*      */       
/*  939 */       } else if (to == Type.BYTE_TYPE) {
/*  940 */         methodVisitor.visitInsn(145);
/*  941 */       } else if (to == Type.CHAR_TYPE) {
/*  942 */         methodVisitor.visitInsn(146);
/*  943 */       } else if (to == Type.DOUBLE_TYPE) {
/*  944 */         methodVisitor.visitInsn(135);
/*  945 */       } else if (to == Type.FLOAT_TYPE) {
/*  946 */         methodVisitor.visitInsn(134);
/*  947 */       } else if (to == Type.LONG_TYPE) {
/*  948 */         methodVisitor.visitInsn(133);
/*  949 */       } else if (to == Type.SHORT_TYPE) {
/*  950 */         methodVisitor.visitInsn(147);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void lcmp() {
/*  957 */     this.mv.visitInsn(148);
/*      */   }
/*      */   
/*      */   public void cmpl(Type type) {
/*  961 */     this.mv.visitInsn((type == Type.FLOAT_TYPE) ? 149 : 151);
/*      */   }
/*      */   
/*      */   public void cmpg(Type type) {
/*  965 */     this.mv.visitInsn((type == Type.FLOAT_TYPE) ? 150 : 152);
/*      */   }
/*      */   
/*      */   public void ifeq(Label label) {
/*  969 */     this.mv.visitJumpInsn(153, label);
/*      */   }
/*      */   
/*      */   public void ifne(Label label) {
/*  973 */     this.mv.visitJumpInsn(154, label);
/*      */   }
/*      */   
/*      */   public void iflt(Label label) {
/*  977 */     this.mv.visitJumpInsn(155, label);
/*      */   }
/*      */   
/*      */   public void ifge(Label label) {
/*  981 */     this.mv.visitJumpInsn(156, label);
/*      */   }
/*      */   
/*      */   public void ifgt(Label label) {
/*  985 */     this.mv.visitJumpInsn(157, label);
/*      */   }
/*      */   
/*      */   public void ifle(Label label) {
/*  989 */     this.mv.visitJumpInsn(158, label);
/*      */   }
/*      */   
/*      */   public void ificmpeq(Label label) {
/*  993 */     this.mv.visitJumpInsn(159, label);
/*      */   }
/*      */   
/*      */   public void ificmpne(Label label) {
/*  997 */     this.mv.visitJumpInsn(160, label);
/*      */   }
/*      */   
/*      */   public void ificmplt(Label label) {
/* 1001 */     this.mv.visitJumpInsn(161, label);
/*      */   }
/*      */   
/*      */   public void ificmpge(Label label) {
/* 1005 */     this.mv.visitJumpInsn(162, label);
/*      */   }
/*      */   
/*      */   public void ificmpgt(Label label) {
/* 1009 */     this.mv.visitJumpInsn(163, label);
/*      */   }
/*      */   
/*      */   public void ificmple(Label label) {
/* 1013 */     this.mv.visitJumpInsn(164, label);
/*      */   }
/*      */   
/*      */   public void ifacmpeq(Label label) {
/* 1017 */     this.mv.visitJumpInsn(165, label);
/*      */   }
/*      */   
/*      */   public void ifacmpne(Label label) {
/* 1021 */     this.mv.visitJumpInsn(166, label);
/*      */   }
/*      */   
/*      */   public void goTo(Label label) {
/* 1025 */     this.mv.visitJumpInsn(167, label);
/*      */   }
/*      */   
/*      */   public void jsr(Label label) {
/* 1029 */     this.mv.visitJumpInsn(168, label);
/*      */   }
/*      */   
/*      */   public void ret(int var) {
/* 1033 */     this.mv.visitVarInsn(169, var);
/*      */   }
/*      */   
/*      */   public void tableswitch(int min, int max, Label dflt, Label... labels) {
/* 1037 */     this.mv.visitTableSwitchInsn(min, max, dflt, labels);
/*      */   }
/*      */   
/*      */   public void lookupswitch(Label dflt, int[] keys, Label[] labels) {
/* 1041 */     this.mv.visitLookupSwitchInsn(dflt, keys, labels);
/*      */   }
/*      */   
/*      */   public void areturn(Type type) {
/* 1045 */     this.mv.visitInsn(type.getOpcode(172));
/*      */   }
/*      */   
/*      */   public void getstatic(String owner, String name, String descriptor) {
/* 1049 */     this.mv.visitFieldInsn(178, owner, name, descriptor);
/*      */   }
/*      */   
/*      */   public void putstatic(String owner, String name, String descriptor) {
/* 1053 */     this.mv.visitFieldInsn(179, owner, name, descriptor);
/*      */   }
/*      */   
/*      */   public void getfield(String owner, String name, String descriptor) {
/* 1057 */     this.mv.visitFieldInsn(180, owner, name, descriptor);
/*      */   }
/*      */   
/*      */   public void putfield(String owner, String name, String descriptor) {
/* 1061 */     this.mv.visitFieldInsn(181, owner, name, descriptor);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public void invokevirtual(String owner, String name, String descriptor) {
/* 1074 */     if (this.api >= 327680) {
/* 1075 */       invokevirtual(owner, name, descriptor, false);
/*      */       return;
/*      */     } 
/* 1078 */     this.mv.visitMethodInsn(182, owner, name, descriptor);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void invokevirtual(String owner, String name, String descriptor, boolean isInterface) {
/* 1092 */     if (this.api < 327680) {
/* 1093 */       if (isInterface) {
/* 1094 */         throw new IllegalArgumentException("INVOKEVIRTUAL on interfaces require ASM 5");
/*      */       }
/* 1096 */       invokevirtual(owner, name, descriptor);
/*      */       return;
/*      */     } 
/* 1099 */     this.mv.visitMethodInsn(182, owner, name, descriptor, isInterface);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public void invokespecial(String owner, String name, String descriptor) {
/* 1112 */     if (this.api >= 327680) {
/* 1113 */       invokespecial(owner, name, descriptor, false);
/*      */       return;
/*      */     } 
/* 1116 */     this.mv.visitMethodInsn(183, owner, name, descriptor, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void invokespecial(String owner, String name, String descriptor, boolean isInterface) {
/* 1130 */     if (this.api < 327680) {
/* 1131 */       if (isInterface) {
/* 1132 */         throw new IllegalArgumentException("INVOKESPECIAL on interfaces require ASM 5");
/*      */       }
/* 1134 */       invokespecial(owner, name, descriptor);
/*      */       return;
/*      */     } 
/* 1137 */     this.mv.visitMethodInsn(183, owner, name, descriptor, isInterface);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public void invokestatic(String owner, String name, String descriptor) {
/* 1150 */     if (this.api >= 327680) {
/* 1151 */       invokestatic(owner, name, descriptor, false);
/*      */       return;
/*      */     } 
/* 1154 */     this.mv.visitMethodInsn(184, owner, name, descriptor, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void invokestatic(String owner, String name, String descriptor, boolean isInterface) {
/* 1168 */     if (this.api < 327680) {
/* 1169 */       if (isInterface) {
/* 1170 */         throw new IllegalArgumentException("INVOKESTATIC on interfaces require ASM 5");
/*      */       }
/* 1172 */       invokestatic(owner, name, descriptor);
/*      */       return;
/*      */     } 
/* 1175 */     this.mv.visitMethodInsn(184, owner, name, descriptor, isInterface);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void invokeinterface(String owner, String name, String descriptor) {
/* 1187 */     this.mv.visitMethodInsn(185, owner, name, descriptor, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void invokedynamic(String name, String descriptor, Handle bootstrapMethodHandle, Object[] bootstrapMethodArguments) {
/* 1206 */     this.mv.visitInvokeDynamicInsn(name, descriptor, bootstrapMethodHandle, bootstrapMethodArguments);
/*      */   }
/*      */   
/*      */   public void anew(Type type) {
/* 1210 */     this.mv.visitTypeInsn(187, type.getInternalName());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void newarray(Type type) {
/* 1219 */     newarray(this.mv, type);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static void newarray(MethodVisitor methodVisitor, Type type) {
/*      */     int arrayType;
/* 1230 */     switch (type.getSort()) {
/*      */       case 1:
/* 1232 */         arrayType = 4;
/*      */         break;
/*      */       case 2:
/* 1235 */         arrayType = 5;
/*      */         break;
/*      */       case 3:
/* 1238 */         arrayType = 8;
/*      */         break;
/*      */       case 4:
/* 1241 */         arrayType = 9;
/*      */         break;
/*      */       case 5:
/* 1244 */         arrayType = 10;
/*      */         break;
/*      */       case 6:
/* 1247 */         arrayType = 6;
/*      */         break;
/*      */       case 7:
/* 1250 */         arrayType = 11;
/*      */         break;
/*      */       case 8:
/* 1253 */         arrayType = 7;
/*      */         break;
/*      */       default:
/* 1256 */         methodVisitor.visitTypeInsn(189, type.getInternalName());
/*      */         return;
/*      */     } 
/* 1259 */     methodVisitor.visitIntInsn(188, arrayType);
/*      */   }
/*      */   
/*      */   public void arraylength() {
/* 1263 */     this.mv.visitInsn(190);
/*      */   }
/*      */   
/*      */   public void athrow() {
/* 1267 */     this.mv.visitInsn(191);
/*      */   }
/*      */   
/*      */   public void checkcast(Type type) {
/* 1271 */     this.mv.visitTypeInsn(192, type.getInternalName());
/*      */   }
/*      */   
/*      */   public void instanceOf(Type type) {
/* 1275 */     this.mv.visitTypeInsn(193, type.getInternalName());
/*      */   }
/*      */   
/*      */   public void monitorenter() {
/* 1279 */     this.mv.visitInsn(194);
/*      */   }
/*      */   
/*      */   public void monitorexit() {
/* 1283 */     this.mv.visitInsn(195);
/*      */   }
/*      */   
/*      */   public void multianewarray(String descriptor, int numDimensions) {
/* 1287 */     this.mv.visitMultiANewArrayInsn(descriptor, numDimensions);
/*      */   }
/*      */   
/*      */   public void ifnull(Label label) {
/* 1291 */     this.mv.visitJumpInsn(198, label);
/*      */   }
/*      */   
/*      */   public void ifnonnull(Label label) {
/* 1295 */     this.mv.visitJumpInsn(199, label);
/*      */   }
/*      */   
/*      */   public void mark(Label label) {
/* 1299 */     this.mv.visitLabel(label);
/*      */   }
/*      */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\objectweb\asm\commons\InstructionAdapter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */