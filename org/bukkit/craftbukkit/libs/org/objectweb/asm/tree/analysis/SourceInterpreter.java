/*     */ package org.bukkit.craftbukkit.libs.org.objectweb.asm.tree.analysis;
/*     */ 
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.Opcodes;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.Type;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.tree.AbstractInsnNode;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.tree.FieldInsnNode;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.tree.InvokeDynamicInsnNode;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.tree.LdcInsnNode;
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
/*     */ public class SourceInterpreter
/*     */   extends Interpreter<SourceValue>
/*     */   implements Opcodes
/*     */ {
/*     */   public SourceInterpreter() {
/*  54 */     super(524288);
/*  55 */     if (getClass() != SourceInterpreter.class) {
/*  56 */       throw new IllegalStateException();
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
/*     */   protected SourceInterpreter(int api) {
/*  69 */     super(api);
/*     */   }
/*     */ 
/*     */   
/*     */   public SourceValue newValue(Type type) {
/*  74 */     if (type == Type.VOID_TYPE) {
/*  75 */       return null;
/*     */     }
/*  77 */     return new SourceValue((type == null) ? 1 : type.getSize());
/*     */   }
/*     */ 
/*     */   
/*     */   public SourceValue newOperation(AbstractInsnNode insn) {
/*     */     Object value;
/*  83 */     switch (insn.getOpcode())
/*     */     { case 9:
/*     */       case 10:
/*     */       case 14:
/*     */       case 15:
/*  88 */         size = 2;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 101 */         return new SourceValue(size, insn);case 18: value = ((LdcInsnNode)insn).cst; size = (value instanceof Long || value instanceof Double) ? 2 : 1; return new SourceValue(size, insn);case 178: size = Type.getType(((FieldInsnNode)insn).desc).getSize(); return new SourceValue(size, insn); }  int size = 1; return new SourceValue(size, insn);
/*     */   }
/*     */ 
/*     */   
/*     */   public SourceValue copyOperation(AbstractInsnNode insn, SourceValue value) {
/* 106 */     return new SourceValue(value.getSize(), insn);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public SourceValue unaryOperation(AbstractInsnNode insn, SourceValue value) {
/* 112 */     switch (insn.getOpcode())
/*     */     { case 117:
/*     */       case 119:
/*     */       case 133:
/*     */       case 135:
/*     */       case 138:
/*     */       case 140:
/*     */       case 141:
/*     */       case 143:
/* 121 */         size = 2;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 130 */         return new SourceValue(size, insn);case 180: size = Type.getType(((FieldInsnNode)insn).desc).getSize(); return new SourceValue(size, insn); }  int size = 1; return new SourceValue(size, insn);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SourceValue binaryOperation(AbstractInsnNode insn, SourceValue value1, SourceValue value2) {
/* 137 */     switch (insn.getOpcode())
/*     */     { case 47:
/*     */       case 49:
/*     */       case 97:
/*     */       case 99:
/*     */       case 101:
/*     */       case 103:
/*     */       case 105:
/*     */       case 107:
/*     */       case 109:
/*     */       case 111:
/*     */       case 113:
/*     */       case 115:
/*     */       case 121:
/*     */       case 123:
/*     */       case 125:
/*     */       case 127:
/*     */       case 129:
/*     */       case 131:
/* 156 */         size = 2;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 162 */         return new SourceValue(size, insn); }  int size = 1; return new SourceValue(size, insn);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SourceValue ternaryOperation(AbstractInsnNode insn, SourceValue value1, SourceValue value2, SourceValue value3) {
/* 171 */     return new SourceValue(1, insn);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SourceValue naryOperation(AbstractInsnNode insn, List<? extends SourceValue> values) {
/* 178 */     int size, opcode = insn.getOpcode();
/* 179 */     if (opcode == 197) {
/* 180 */       size = 1;
/* 181 */     } else if (opcode == 186) {
/* 182 */       size = Type.getReturnType(((InvokeDynamicInsnNode)insn).desc).getSize();
/*     */     } else {
/* 184 */       size = Type.getReturnType(((MethodInsnNode)insn).desc).getSize();
/*     */     } 
/* 186 */     return new SourceValue(size, insn);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void returnOperation(AbstractInsnNode insn, SourceValue value, SourceValue expected) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public SourceValue merge(SourceValue value1, SourceValue value2) {
/* 197 */     if (value1.insns instanceof SmallSet && value2.insns instanceof SmallSet) {
/*     */ 
/*     */       
/* 200 */       Set<AbstractInsnNode> setUnion = ((SmallSet<AbstractInsnNode>)value1.insns).union((SmallSet<AbstractInsnNode>)value2.insns);
/* 201 */       if (setUnion == value1.insns && value1.size == value2.size) {
/* 202 */         return value1;
/*     */       }
/* 204 */       return new SourceValue(Math.min(value1.size, value2.size), setUnion);
/*     */     } 
/*     */     
/* 207 */     if (value1.size != value2.size || !containsAll(value1.insns, value2.insns)) {
/* 208 */       HashSet<AbstractInsnNode> setUnion = new HashSet<AbstractInsnNode>();
/* 209 */       setUnion.addAll(value1.insns);
/* 210 */       setUnion.addAll(value2.insns);
/* 211 */       return new SourceValue(Math.min(value1.size, value2.size), setUnion);
/*     */     } 
/* 213 */     return value1;
/*     */   }
/*     */   
/*     */   private static <E> boolean containsAll(Set<E> self, Set<E> other) {
/* 217 */     if (self.size() < other.size()) {
/* 218 */       return false;
/*     */     }
/* 220 */     return self.containsAll(other);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\objectweb\asm\tree\analysis\SourceInterpreter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */