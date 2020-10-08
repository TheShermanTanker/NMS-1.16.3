/*     */ package org.bukkit.craftbukkit.libs.org.objectweb.asm.tree.analysis;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.Type;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.tree.AbstractInsnNode;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.tree.FieldInsnNode;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.tree.InvokeDynamicInsnNode;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.tree.MethodInsnNode;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BasicVerifier
/*     */   extends BasicInterpreter
/*     */ {
/*     */   public BasicVerifier() {
/*  50 */     super(524288);
/*  51 */     if (getClass() != BasicVerifier.class) {
/*  52 */       throw new IllegalStateException();
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
/*     */   protected BasicVerifier(int api) {
/*  65 */     super(api);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BasicValue copyOperation(AbstractInsnNode insn, BasicValue value) throws AnalyzerException {
/*     */     Value expected;
/*  72 */     switch (insn.getOpcode()) {
/*     */       case 21:
/*     */       case 54:
/*  75 */         expected = BasicValue.INT_VALUE;
/*     */         break;
/*     */       case 23:
/*     */       case 56:
/*  79 */         expected = BasicValue.FLOAT_VALUE;
/*     */         break;
/*     */       case 22:
/*     */       case 55:
/*  83 */         expected = BasicValue.LONG_VALUE;
/*     */         break;
/*     */       case 24:
/*     */       case 57:
/*  87 */         expected = BasicValue.DOUBLE_VALUE;
/*     */         break;
/*     */       case 25:
/*  90 */         if (!value.isReference()) {
/*  91 */           throw new AnalyzerException(insn, null, "an object reference", value);
/*     */         }
/*  93 */         return value;
/*     */       case 58:
/*  95 */         if (!value.isReference() && !BasicValue.RETURNADDRESS_VALUE.equals(value)) {
/*  96 */           throw new AnalyzerException(insn, null, "an object reference or a return address", value);
/*     */         }
/*  98 */         return value;
/*     */       default:
/* 100 */         return value;
/*     */     } 
/* 102 */     if (!expected.equals(value)) {
/* 103 */       throw new AnalyzerException(insn, null, expected, value);
/*     */     }
/* 105 */     return value;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BasicValue unaryOperation(AbstractInsnNode insn, BasicValue value) throws AnalyzerException {
/*     */     BasicValue expected;
/* 112 */     switch (insn.getOpcode()) {
/*     */       case 116:
/*     */       case 132:
/*     */       case 133:
/*     */       case 134:
/*     */       case 135:
/*     */       case 145:
/*     */       case 146:
/*     */       case 147:
/*     */       case 153:
/*     */       case 154:
/*     */       case 155:
/*     */       case 156:
/*     */       case 157:
/*     */       case 158:
/*     */       case 170:
/*     */       case 171:
/*     */       case 172:
/*     */       case 188:
/*     */       case 189:
/* 132 */         expected = BasicValue.INT_VALUE;
/*     */         break;
/*     */       case 118:
/*     */       case 139:
/*     */       case 140:
/*     */       case 141:
/*     */       case 174:
/* 139 */         expected = BasicValue.FLOAT_VALUE;
/*     */         break;
/*     */       case 117:
/*     */       case 136:
/*     */       case 137:
/*     */       case 138:
/*     */       case 173:
/* 146 */         expected = BasicValue.LONG_VALUE;
/*     */         break;
/*     */       case 119:
/*     */       case 142:
/*     */       case 143:
/*     */       case 144:
/*     */       case 175:
/* 153 */         expected = BasicValue.DOUBLE_VALUE;
/*     */         break;
/*     */       case 180:
/* 156 */         expected = newValue(Type.getObjectType(((FieldInsnNode)insn).owner));
/*     */         break;
/*     */       case 190:
/* 159 */         if (!isArrayValue(value)) {
/* 160 */           throw new AnalyzerException(insn, null, "an array reference", value);
/*     */         }
/* 162 */         return super.unaryOperation(insn, value);
/*     */       case 176:
/*     */       case 191:
/*     */       case 192:
/*     */       case 193:
/*     */       case 194:
/*     */       case 195:
/*     */       case 198:
/*     */       case 199:
/* 171 */         if (!value.isReference()) {
/* 172 */           throw new AnalyzerException(insn, null, "an object reference", value);
/*     */         }
/* 174 */         return super.unaryOperation(insn, value);
/*     */       case 179:
/* 176 */         expected = newValue(Type.getType(((FieldInsnNode)insn).desc));
/*     */         break;
/*     */       default:
/* 179 */         throw new AssertionError();
/*     */     } 
/* 181 */     if (!isSubTypeOf(value, expected)) {
/* 182 */       throw new AnalyzerException(insn, null, expected, value);
/*     */     }
/* 184 */     return super.unaryOperation(insn, value);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BasicValue binaryOperation(AbstractInsnNode insn, BasicValue value1, BasicValue value2) throws AnalyzerException {
/*     */     BasicValue expected1;
/*     */     BasicValue expected2;
/*     */     FieldInsnNode fieldInsn;
/* 193 */     switch (insn.getOpcode()) {
/*     */       case 46:
/* 195 */         expected1 = newValue(Type.getType("[I"));
/* 196 */         expected2 = BasicValue.INT_VALUE;
/*     */         break;
/*     */       case 51:
/* 199 */         if (isSubTypeOf(value1, newValue(Type.getType("[Z")))) {
/* 200 */           expected1 = newValue(Type.getType("[Z"));
/*     */         } else {
/* 202 */           expected1 = newValue(Type.getType("[B"));
/*     */         } 
/* 204 */         expected2 = BasicValue.INT_VALUE;
/*     */         break;
/*     */       case 52:
/* 207 */         expected1 = newValue(Type.getType("[C"));
/* 208 */         expected2 = BasicValue.INT_VALUE;
/*     */         break;
/*     */       case 53:
/* 211 */         expected1 = newValue(Type.getType("[S"));
/* 212 */         expected2 = BasicValue.INT_VALUE;
/*     */         break;
/*     */       case 47:
/* 215 */         expected1 = newValue(Type.getType("[J"));
/* 216 */         expected2 = BasicValue.INT_VALUE;
/*     */         break;
/*     */       case 48:
/* 219 */         expected1 = newValue(Type.getType("[F"));
/* 220 */         expected2 = BasicValue.INT_VALUE;
/*     */         break;
/*     */       case 49:
/* 223 */         expected1 = newValue(Type.getType("[D"));
/* 224 */         expected2 = BasicValue.INT_VALUE;
/*     */         break;
/*     */       case 50:
/* 227 */         expected1 = newValue(Type.getType("[Ljava/lang/Object;"));
/* 228 */         expected2 = BasicValue.INT_VALUE;
/*     */         break;
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
/*     */       case 159:
/*     */       case 160:
/*     */       case 161:
/*     */       case 162:
/*     */       case 163:
/*     */       case 164:
/* 247 */         expected1 = BasicValue.INT_VALUE;
/* 248 */         expected2 = BasicValue.INT_VALUE;
/*     */         break;
/*     */       case 98:
/*     */       case 102:
/*     */       case 106:
/*     */       case 110:
/*     */       case 114:
/*     */       case 149:
/*     */       case 150:
/* 257 */         expected1 = BasicValue.FLOAT_VALUE;
/* 258 */         expected2 = BasicValue.FLOAT_VALUE;
/*     */         break;
/*     */       case 97:
/*     */       case 101:
/*     */       case 105:
/*     */       case 109:
/*     */       case 113:
/*     */       case 127:
/*     */       case 129:
/*     */       case 131:
/*     */       case 148:
/* 269 */         expected1 = BasicValue.LONG_VALUE;
/* 270 */         expected2 = BasicValue.LONG_VALUE;
/*     */         break;
/*     */       case 121:
/*     */       case 123:
/*     */       case 125:
/* 275 */         expected1 = BasicValue.LONG_VALUE;
/* 276 */         expected2 = BasicValue.INT_VALUE;
/*     */         break;
/*     */       case 99:
/*     */       case 103:
/*     */       case 107:
/*     */       case 111:
/*     */       case 115:
/*     */       case 151:
/*     */       case 152:
/* 285 */         expected1 = BasicValue.DOUBLE_VALUE;
/* 286 */         expected2 = BasicValue.DOUBLE_VALUE;
/*     */         break;
/*     */       case 165:
/*     */       case 166:
/* 290 */         expected1 = BasicValue.REFERENCE_VALUE;
/* 291 */         expected2 = BasicValue.REFERENCE_VALUE;
/*     */         break;
/*     */       case 181:
/* 294 */         fieldInsn = (FieldInsnNode)insn;
/* 295 */         expected1 = newValue(Type.getObjectType(fieldInsn.owner));
/* 296 */         expected2 = newValue(Type.getType(fieldInsn.desc));
/*     */         break;
/*     */       default:
/* 299 */         throw new AssertionError();
/*     */     } 
/* 301 */     if (!isSubTypeOf(value1, expected1))
/* 302 */       throw new AnalyzerException(insn, "First argument", expected1, value1); 
/* 303 */     if (!isSubTypeOf(value2, expected2)) {
/* 304 */       throw new AnalyzerException(insn, "Second argument", expected2, value2);
/*     */     }
/* 306 */     if (insn.getOpcode() == 50) {
/* 307 */       return getElementValue(value1);
/*     */     }
/* 309 */     return super.binaryOperation(insn, value1, value2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BasicValue ternaryOperation(AbstractInsnNode insn, BasicValue value1, BasicValue value2, BasicValue value3) throws AnalyzerException {
/*     */     BasicValue expected1;
/*     */     BasicValue expected3;
/* 322 */     switch (insn.getOpcode()) {
/*     */       case 79:
/* 324 */         expected1 = newValue(Type.getType("[I"));
/* 325 */         expected3 = BasicValue.INT_VALUE;
/*     */         break;
/*     */       case 84:
/* 328 */         if (isSubTypeOf(value1, newValue(Type.getType("[Z")))) {
/* 329 */           expected1 = newValue(Type.getType("[Z"));
/*     */         } else {
/* 331 */           expected1 = newValue(Type.getType("[B"));
/*     */         } 
/* 333 */         expected3 = BasicValue.INT_VALUE;
/*     */         break;
/*     */       case 85:
/* 336 */         expected1 = newValue(Type.getType("[C"));
/* 337 */         expected3 = BasicValue.INT_VALUE;
/*     */         break;
/*     */       case 86:
/* 340 */         expected1 = newValue(Type.getType("[S"));
/* 341 */         expected3 = BasicValue.INT_VALUE;
/*     */         break;
/*     */       case 80:
/* 344 */         expected1 = newValue(Type.getType("[J"));
/* 345 */         expected3 = BasicValue.LONG_VALUE;
/*     */         break;
/*     */       case 81:
/* 348 */         expected1 = newValue(Type.getType("[F"));
/* 349 */         expected3 = BasicValue.FLOAT_VALUE;
/*     */         break;
/*     */       case 82:
/* 352 */         expected1 = newValue(Type.getType("[D"));
/* 353 */         expected3 = BasicValue.DOUBLE_VALUE;
/*     */         break;
/*     */       case 83:
/* 356 */         expected1 = value1;
/* 357 */         expected3 = BasicValue.REFERENCE_VALUE;
/*     */         break;
/*     */       default:
/* 360 */         throw new AssertionError();
/*     */     } 
/* 362 */     if (!isSubTypeOf(value1, expected1)) {
/* 363 */       throw new AnalyzerException(insn, "First argument", "a " + expected1 + " array reference", value1);
/*     */     }
/* 365 */     if (!BasicValue.INT_VALUE.equals(value2))
/* 366 */       throw new AnalyzerException(insn, "Second argument", BasicValue.INT_VALUE, value2); 
/* 367 */     if (!isSubTypeOf(value3, expected3)) {
/* 368 */       throw new AnalyzerException(insn, "Third argument", expected3, value3);
/*     */     }
/* 370 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BasicValue naryOperation(AbstractInsnNode insn, List<? extends BasicValue> values) throws AnalyzerException {
/* 377 */     int opcode = insn.getOpcode();
/* 378 */     if (opcode == 197) {
/* 379 */       for (BasicValue value : values) {
/* 380 */         if (!BasicValue.INT_VALUE.equals(value)) {
/* 381 */           throw new AnalyzerException(insn, null, BasicValue.INT_VALUE, value);
/*     */         }
/*     */       } 
/*     */     } else {
/* 385 */       int i = 0;
/* 386 */       int j = 0;
/* 387 */       if (opcode != 184 && opcode != 186) {
/* 388 */         Type owner = Type.getObjectType(((MethodInsnNode)insn).owner);
/* 389 */         if (!isSubTypeOf(values.get(i++), newValue(owner))) {
/* 390 */           throw new AnalyzerException(insn, "Method owner", newValue(owner), (Value)values.get(0));
/*     */         }
/*     */       } 
/* 393 */       String methodDescriptor = (opcode == 186) ? ((InvokeDynamicInsnNode)insn).desc : ((MethodInsnNode)insn).desc;
/*     */ 
/*     */ 
/*     */       
/* 397 */       Type[] args = Type.getArgumentTypes(methodDescriptor);
/* 398 */       while (i < values.size()) {
/* 399 */         BasicValue expected = newValue(args[j++]);
/* 400 */         BasicValue actual = values.get(i++);
/* 401 */         if (!isSubTypeOf(actual, expected)) {
/* 402 */           throw new AnalyzerException(insn, "Argument " + j, expected, actual);
/*     */         }
/*     */       } 
/*     */     } 
/* 406 */     return super.naryOperation(insn, values);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void returnOperation(AbstractInsnNode insn, BasicValue value, BasicValue expected) throws AnalyzerException {
/* 413 */     if (!isSubTypeOf(value, expected)) {
/* 414 */       throw new AnalyzerException(insn, "Incompatible return type", expected, value);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isArrayValue(BasicValue value) {
/* 425 */     return value.isReference();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected BasicValue getElementValue(BasicValue objectArrayValue) throws AnalyzerException {
/* 436 */     return BasicValue.REFERENCE_VALUE;
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
/*     */   protected boolean isSubTypeOf(BasicValue value, BasicValue expected) {
/* 449 */     return value.equals(expected);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\objectweb\asm\tree\analysis\BasicVerifier.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */