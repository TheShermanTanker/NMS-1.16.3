/*     */ package org.bukkit.craftbukkit.libs.org.objectweb.asm.tree.analysis;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.ConstantDynamic;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.Opcodes;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.Type;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.tree.AbstractInsnNode;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.tree.FieldInsnNode;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.tree.IntInsnNode;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.tree.InvokeDynamicInsnNode;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.tree.LdcInsnNode;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.tree.MethodInsnNode;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.tree.MultiANewArrayInsnNode;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.tree.TypeInsnNode;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BasicInterpreter
/*     */   extends Interpreter<BasicValue>
/*     */   implements Opcodes
/*     */ {
/*  56 */   public static final Type NULL_TYPE = Type.getObjectType("null");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BasicInterpreter() {
/*  64 */     super(524288);
/*  65 */     if (getClass() != BasicInterpreter.class) {
/*  66 */       throw new IllegalStateException();
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
/*     */   protected BasicInterpreter(int api) {
/*  79 */     super(api);
/*     */   }
/*     */ 
/*     */   
/*     */   public BasicValue newValue(Type type) {
/*  84 */     if (type == null) {
/*  85 */       return BasicValue.UNINITIALIZED_VALUE;
/*     */     }
/*  87 */     switch (type.getSort()) {
/*     */       case 0:
/*  89 */         return null;
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/*     */       case 4:
/*     */       case 5:
/*  95 */         return BasicValue.INT_VALUE;
/*     */       case 6:
/*  97 */         return BasicValue.FLOAT_VALUE;
/*     */       case 7:
/*  99 */         return BasicValue.LONG_VALUE;
/*     */       case 8:
/* 101 */         return BasicValue.DOUBLE_VALUE;
/*     */       case 9:
/*     */       case 10:
/* 104 */         return BasicValue.REFERENCE_VALUE;
/*     */     } 
/* 106 */     throw new AssertionError();
/*     */   }
/*     */ 
/*     */   
/*     */   public BasicValue newOperation(AbstractInsnNode insn) throws AnalyzerException {
/*     */     Object value;
/* 112 */     switch (insn.getOpcode()) {
/*     */       case 1:
/* 114 */         return newValue(NULL_TYPE);
/*     */       case 2:
/*     */       case 3:
/*     */       case 4:
/*     */       case 5:
/*     */       case 6:
/*     */       case 7:
/*     */       case 8:
/* 122 */         return BasicValue.INT_VALUE;
/*     */       case 9:
/*     */       case 10:
/* 125 */         return BasicValue.LONG_VALUE;
/*     */       case 11:
/*     */       case 12:
/*     */       case 13:
/* 129 */         return BasicValue.FLOAT_VALUE;
/*     */       case 14:
/*     */       case 15:
/* 132 */         return BasicValue.DOUBLE_VALUE;
/*     */       case 16:
/*     */       case 17:
/* 135 */         return BasicValue.INT_VALUE;
/*     */       case 18:
/* 137 */         value = ((LdcInsnNode)insn).cst;
/* 138 */         if (value instanceof Integer)
/* 139 */           return BasicValue.INT_VALUE; 
/* 140 */         if (value instanceof Float)
/* 141 */           return BasicValue.FLOAT_VALUE; 
/* 142 */         if (value instanceof Long)
/* 143 */           return BasicValue.LONG_VALUE; 
/* 144 */         if (value instanceof Double)
/* 145 */           return BasicValue.DOUBLE_VALUE; 
/* 146 */         if (value instanceof String)
/* 147 */           return newValue(Type.getObjectType("java/lang/String")); 
/* 148 */         if (value instanceof Type) {
/* 149 */           int sort = ((Type)value).getSort();
/* 150 */           if (sort == 10 || sort == 9)
/* 151 */             return newValue(Type.getObjectType("java/lang/Class")); 
/* 152 */           if (sort == 11) {
/* 153 */             return newValue(Type.getObjectType("java/lang/invoke/MethodType"));
/*     */           }
/* 155 */           throw new AnalyzerException(insn, "Illegal LDC value " + value);
/*     */         } 
/* 157 */         if (value instanceof org.bukkit.craftbukkit.libs.org.objectweb.asm.Handle)
/* 158 */           return newValue(Type.getObjectType("java/lang/invoke/MethodHandle")); 
/* 159 */         if (value instanceof ConstantDynamic) {
/* 160 */           return newValue(Type.getType(((ConstantDynamic)value).getDescriptor()));
/*     */         }
/* 162 */         throw new AnalyzerException(insn, "Illegal LDC value " + value);
/*     */       
/*     */       case 168:
/* 165 */         return BasicValue.RETURNADDRESS_VALUE;
/*     */       case 178:
/* 167 */         return newValue(Type.getType(((FieldInsnNode)insn).desc));
/*     */       case 187:
/* 169 */         return newValue(Type.getObjectType(((TypeInsnNode)insn).desc));
/*     */     } 
/* 171 */     throw new AssertionError();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BasicValue copyOperation(AbstractInsnNode insn, BasicValue value) throws AnalyzerException {
/* 178 */     return value;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BasicValue unaryOperation(AbstractInsnNode insn, BasicValue value) throws AnalyzerException {
/* 184 */     switch (insn.getOpcode()) {
/*     */       case 116:
/*     */       case 132:
/*     */       case 136:
/*     */       case 139:
/*     */       case 142:
/*     */       case 145:
/*     */       case 146:
/*     */       case 147:
/* 193 */         return BasicValue.INT_VALUE;
/*     */       case 118:
/*     */       case 134:
/*     */       case 137:
/*     */       case 144:
/* 198 */         return BasicValue.FLOAT_VALUE;
/*     */       case 117:
/*     */       case 133:
/*     */       case 140:
/*     */       case 143:
/* 203 */         return BasicValue.LONG_VALUE;
/*     */       case 119:
/*     */       case 135:
/*     */       case 138:
/*     */       case 141:
/* 208 */         return BasicValue.DOUBLE_VALUE;
/*     */       case 153:
/*     */       case 154:
/*     */       case 155:
/*     */       case 156:
/*     */       case 157:
/*     */       case 158:
/*     */       case 170:
/*     */       case 171:
/*     */       case 172:
/*     */       case 173:
/*     */       case 174:
/*     */       case 175:
/*     */       case 176:
/*     */       case 179:
/* 223 */         return null;
/*     */       case 180:
/* 225 */         return newValue(Type.getType(((FieldInsnNode)insn).desc));
/*     */       case 188:
/* 227 */         switch (((IntInsnNode)insn).operand) {
/*     */           case 4:
/* 229 */             return newValue(Type.getType("[Z"));
/*     */           case 5:
/* 231 */             return newValue(Type.getType("[C"));
/*     */           case 8:
/* 233 */             return newValue(Type.getType("[B"));
/*     */           case 9:
/* 235 */             return newValue(Type.getType("[S"));
/*     */           case 10:
/* 237 */             return newValue(Type.getType("[I"));
/*     */           case 6:
/* 239 */             return newValue(Type.getType("[F"));
/*     */           case 7:
/* 241 */             return newValue(Type.getType("[D"));
/*     */           case 11:
/* 243 */             return newValue(Type.getType("[J"));
/*     */         } 
/*     */ 
/*     */         
/* 247 */         throw new AnalyzerException(insn, "Invalid array type");
/*     */       case 189:
/* 249 */         return newValue(Type.getType("[" + Type.getObjectType(((TypeInsnNode)insn).desc)));
/*     */       case 190:
/* 251 */         return BasicValue.INT_VALUE;
/*     */       case 191:
/* 253 */         return null;
/*     */       case 192:
/* 255 */         return newValue(Type.getObjectType(((TypeInsnNode)insn).desc));
/*     */       case 193:
/* 257 */         return BasicValue.INT_VALUE;
/*     */       case 194:
/*     */       case 195:
/*     */       case 198:
/*     */       case 199:
/* 262 */         return null;
/*     */     } 
/* 264 */     throw new AssertionError();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BasicValue binaryOperation(AbstractInsnNode insn, BasicValue value1, BasicValue value2) throws AnalyzerException {
/* 272 */     switch (insn.getOpcode()) {
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
/* 288 */         return BasicValue.INT_VALUE;
/*     */       case 48:
/*     */       case 98:
/*     */       case 102:
/*     */       case 106:
/*     */       case 110:
/*     */       case 114:
/* 295 */         return BasicValue.FLOAT_VALUE;
/*     */       case 47:
/*     */       case 97:
/*     */       case 101:
/*     */       case 105:
/*     */       case 109:
/*     */       case 113:
/*     */       case 121:
/*     */       case 123:
/*     */       case 125:
/*     */       case 127:
/*     */       case 129:
/*     */       case 131:
/* 308 */         return BasicValue.LONG_VALUE;
/*     */       case 49:
/*     */       case 99:
/*     */       case 103:
/*     */       case 107:
/*     */       case 111:
/*     */       case 115:
/* 315 */         return BasicValue.DOUBLE_VALUE;
/*     */       case 50:
/* 317 */         return BasicValue.REFERENCE_VALUE;
/*     */       case 148:
/*     */       case 149:
/*     */       case 150:
/*     */       case 151:
/*     */       case 152:
/* 323 */         return BasicValue.INT_VALUE;
/*     */       case 159:
/*     */       case 160:
/*     */       case 161:
/*     */       case 162:
/*     */       case 163:
/*     */       case 164:
/*     */       case 165:
/*     */       case 166:
/*     */       case 181:
/* 333 */         return null;
/*     */     } 
/* 335 */     throw new AssertionError();
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
/* 346 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BasicValue naryOperation(AbstractInsnNode insn, List<? extends BasicValue> values) throws AnalyzerException {
/* 353 */     int opcode = insn.getOpcode();
/* 354 */     if (opcode == 197)
/* 355 */       return newValue(Type.getType(((MultiANewArrayInsnNode)insn).desc)); 
/* 356 */     if (opcode == 186) {
/* 357 */       return newValue(Type.getReturnType(((InvokeDynamicInsnNode)insn).desc));
/*     */     }
/* 359 */     return newValue(Type.getReturnType(((MethodInsnNode)insn).desc));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void returnOperation(AbstractInsnNode insn, BasicValue value, BasicValue expected) throws AnalyzerException {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BasicValue merge(BasicValue value1, BasicValue value2) {
/* 372 */     if (!value1.equals(value2)) {
/* 373 */       return BasicValue.UNINITIALIZED_VALUE;
/*     */     }
/* 375 */     return value1;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\objectweb\asm\tree\analysis\BasicInterpreter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */