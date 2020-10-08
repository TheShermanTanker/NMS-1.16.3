/*      */ package org.bukkit.craftbukkit.libs.org.objectweb.asm.commons;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.List;
/*      */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.ClassVisitor;
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
/*      */ 
/*      */ public class GeneratorAdapter
/*      */   extends LocalVariablesSorter
/*      */ {
/*      */   private static final String CLASS_DESCRIPTOR = "Ljava/lang/Class;";
/*   87 */   private static final Type BYTE_TYPE = Type.getObjectType("java/lang/Byte");
/*      */   
/*   89 */   private static final Type BOOLEAN_TYPE = Type.getObjectType("java/lang/Boolean");
/*      */   
/*   91 */   private static final Type SHORT_TYPE = Type.getObjectType("java/lang/Short");
/*      */   
/*   93 */   private static final Type CHARACTER_TYPE = Type.getObjectType("java/lang/Character");
/*      */   
/*   95 */   private static final Type INTEGER_TYPE = Type.getObjectType("java/lang/Integer");
/*      */   
/*   97 */   private static final Type FLOAT_TYPE = Type.getObjectType("java/lang/Float");
/*      */   
/*   99 */   private static final Type LONG_TYPE = Type.getObjectType("java/lang/Long");
/*      */   
/*  101 */   private static final Type DOUBLE_TYPE = Type.getObjectType("java/lang/Double");
/*      */   
/*  103 */   private static final Type NUMBER_TYPE = Type.getObjectType("java/lang/Number");
/*      */   
/*  105 */   private static final Type OBJECT_TYPE = Type.getObjectType("java/lang/Object");
/*      */   
/*  107 */   private static final Method BOOLEAN_VALUE = Method.getMethod("boolean booleanValue()");
/*      */   
/*  109 */   private static final Method CHAR_VALUE = Method.getMethod("char charValue()");
/*      */   
/*  111 */   private static final Method INT_VALUE = Method.getMethod("int intValue()");
/*      */   
/*  113 */   private static final Method FLOAT_VALUE = Method.getMethod("float floatValue()");
/*      */   
/*  115 */   private static final Method LONG_VALUE = Method.getMethod("long longValue()");
/*      */   
/*  117 */   private static final Method DOUBLE_VALUE = Method.getMethod("double doubleValue()");
/*      */ 
/*      */   
/*      */   public static final int ADD = 96;
/*      */ 
/*      */   
/*      */   public static final int SUB = 100;
/*      */ 
/*      */   
/*      */   public static final int MUL = 104;
/*      */ 
/*      */   
/*      */   public static final int DIV = 108;
/*      */ 
/*      */   
/*      */   public static final int REM = 112;
/*      */ 
/*      */   
/*      */   public static final int NEG = 116;
/*      */ 
/*      */   
/*      */   public static final int SHL = 120;
/*      */ 
/*      */   
/*      */   public static final int SHR = 122;
/*      */ 
/*      */   
/*      */   public static final int USHR = 124;
/*      */ 
/*      */   
/*      */   public static final int AND = 126;
/*      */ 
/*      */   
/*      */   public static final int OR = 128;
/*      */ 
/*      */   
/*      */   public static final int XOR = 130;
/*      */ 
/*      */   
/*      */   public static final int EQ = 153;
/*      */ 
/*      */   
/*      */   public static final int NE = 154;
/*      */ 
/*      */   
/*      */   public static final int LT = 155;
/*      */ 
/*      */   
/*      */   public static final int GE = 156;
/*      */ 
/*      */   
/*      */   public static final int GT = 157;
/*      */ 
/*      */   
/*      */   public static final int LE = 158;
/*      */ 
/*      */   
/*      */   private final int access;
/*      */ 
/*      */   
/*      */   private final String name;
/*      */ 
/*      */   
/*      */   private final Type returnType;
/*      */ 
/*      */   
/*      */   private final Type[] argumentTypes;
/*      */ 
/*      */   
/*  186 */   private final List<Type> localTypes = new ArrayList<Type>();
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
/*      */   public GeneratorAdapter(MethodVisitor methodVisitor, int access, String name, String descriptor) {
/*  204 */     this(524288, methodVisitor, access, name, descriptor);
/*  205 */     if (getClass() != GeneratorAdapter.class) {
/*  206 */       throw new IllegalStateException();
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected GeneratorAdapter(int api, MethodVisitor methodVisitor, int access, String name, String descriptor) {
/*  227 */     super(api, access, descriptor, methodVisitor);
/*  228 */     this.access = access;
/*  229 */     this.name = name;
/*  230 */     this.returnType = Type.getReturnType(descriptor);
/*  231 */     this.argumentTypes = Type.getArgumentTypes(descriptor);
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
/*      */   public GeneratorAdapter(int access, Method method, MethodVisitor methodVisitor) {
/*  245 */     this(methodVisitor, access, method.getName(), method.getDescriptor());
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
/*      */   
/*      */   public GeneratorAdapter(int access, Method method, String signature, Type[] exceptions, ClassVisitor classVisitor) {
/*  265 */     this(access, method, classVisitor
/*      */ 
/*      */         
/*  268 */         .visitMethod(access, method
/*      */           
/*  270 */           .getName(), method
/*  271 */           .getDescriptor(), signature, (exceptions == null) ? null : 
/*      */           
/*  273 */           getInternalNames(exceptions)));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static String[] getInternalNames(Type[] types) {
/*  283 */     String[] names = new String[types.length];
/*  284 */     for (int i = 0; i < names.length; i++) {
/*  285 */       names[i] = types[i].getInternalName();
/*      */     }
/*  287 */     return names;
/*      */   }
/*      */   
/*      */   public int getAccess() {
/*  291 */     return this.access;
/*      */   }
/*      */   
/*      */   public String getName() {
/*  295 */     return this.name;
/*      */   }
/*      */   
/*      */   public Type getReturnType() {
/*  299 */     return this.returnType;
/*      */   }
/*      */   
/*      */   public Type[] getArgumentTypes() {
/*  303 */     return (Type[])this.argumentTypes.clone();
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
/*      */   public void push(boolean value) {
/*  316 */     push(value ? 1 : 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void push(int value) {
/*  325 */     if (value >= -1 && value <= 5) {
/*  326 */       this.mv.visitInsn(3 + value);
/*  327 */     } else if (value >= -128 && value <= 127) {
/*  328 */       this.mv.visitIntInsn(16, value);
/*  329 */     } else if (value >= -32768 && value <= 32767) {
/*  330 */       this.mv.visitIntInsn(17, value);
/*      */     } else {
/*  332 */       this.mv.visitLdcInsn(Integer.valueOf(value));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void push(long value) {
/*  342 */     if (value == 0L || value == 1L) {
/*  343 */       this.mv.visitInsn(9 + (int)value);
/*      */     } else {
/*  345 */       this.mv.visitLdcInsn(Long.valueOf(value));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void push(float value) {
/*  355 */     int bits = Float.floatToIntBits(value);
/*  356 */     if (bits == 0L || bits == 1065353216 || bits == 1073741824) {
/*  357 */       this.mv.visitInsn(11 + (int)value);
/*      */     } else {
/*  359 */       this.mv.visitLdcInsn(Float.valueOf(value));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void push(double value) {
/*  369 */     long bits = Double.doubleToLongBits(value);
/*  370 */     if (bits == 0L || bits == 4607182418800017408L) {
/*  371 */       this.mv.visitInsn(14 + (int)value);
/*      */     } else {
/*  373 */       this.mv.visitLdcInsn(Double.valueOf(value));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void push(String value) {
/*  383 */     if (value == null) {
/*  384 */       this.mv.visitInsn(1);
/*      */     } else {
/*  386 */       this.mv.visitLdcInsn(value);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void push(Type value) {
/*  396 */     if (value == null) {
/*  397 */       this.mv.visitInsn(1);
/*      */     } else {
/*  399 */       switch (value.getSort()) {
/*      */         case 1:
/*  401 */           this.mv.visitFieldInsn(178, "java/lang/Boolean", "TYPE", "Ljava/lang/Class;");
/*      */           return;
/*      */         case 2:
/*  404 */           this.mv.visitFieldInsn(178, "java/lang/Character", "TYPE", "Ljava/lang/Class;");
/*      */           return;
/*      */         case 3:
/*  407 */           this.mv.visitFieldInsn(178, "java/lang/Byte", "TYPE", "Ljava/lang/Class;");
/*      */           return;
/*      */         case 4:
/*  410 */           this.mv.visitFieldInsn(178, "java/lang/Short", "TYPE", "Ljava/lang/Class;");
/*      */           return;
/*      */         case 5:
/*  413 */           this.mv.visitFieldInsn(178, "java/lang/Integer", "TYPE", "Ljava/lang/Class;");
/*      */           return;
/*      */         case 6:
/*  416 */           this.mv.visitFieldInsn(178, "java/lang/Float", "TYPE", "Ljava/lang/Class;");
/*      */           return;
/*      */         case 7:
/*  419 */           this.mv.visitFieldInsn(178, "java/lang/Long", "TYPE", "Ljava/lang/Class;");
/*      */           return;
/*      */         case 8:
/*  422 */           this.mv.visitFieldInsn(178, "java/lang/Double", "TYPE", "Ljava/lang/Class;");
/*      */           return;
/*      */       } 
/*  425 */       this.mv.visitLdcInsn(value);
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
/*      */   public void push(Handle handle) {
/*  437 */     if (handle == null) {
/*  438 */       this.mv.visitInsn(1);
/*      */     } else {
/*  440 */       this.mv.visitLdcInsn(handle);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void push(ConstantDynamic constantDynamic) {
/*  450 */     if (constantDynamic == null) {
/*  451 */       this.mv.visitInsn(1);
/*      */     } else {
/*  453 */       this.mv.visitLdcInsn(constantDynamic);
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
/*      */ 
/*      */   
/*      */   private int getArgIndex(int arg) {
/*  468 */     int index = ((this.access & 0x8) == 0) ? 1 : 0;
/*  469 */     for (int i = 0; i < arg; i++) {
/*  470 */       index += this.argumentTypes[i].getSize();
/*      */     }
/*  472 */     return index;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void loadInsn(Type type, int index) {
/*  482 */     this.mv.visitVarInsn(type.getOpcode(21), index);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void storeInsn(Type type, int index) {
/*  492 */     this.mv.visitVarInsn(type.getOpcode(54), index);
/*      */   }
/*      */ 
/*      */   
/*      */   public void loadThis() {
/*  497 */     if ((this.access & 0x8) != 0) {
/*  498 */       throw new IllegalStateException("no 'this' pointer within static method");
/*      */     }
/*  500 */     this.mv.visitVarInsn(25, 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void loadArg(int arg) {
/*  509 */     loadInsn(this.argumentTypes[arg], getArgIndex(arg));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void loadArgs(int arg, int count) {
/*  519 */     int index = getArgIndex(arg);
/*  520 */     for (int i = 0; i < count; i++) {
/*  521 */       Type argumentType = this.argumentTypes[arg + i];
/*  522 */       loadInsn(argumentType, index);
/*  523 */       index += argumentType.getSize();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void loadArgs() {
/*  529 */     loadArgs(0, this.argumentTypes.length);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void loadArgArray() {
/*  537 */     push(this.argumentTypes.length);
/*  538 */     newArray(OBJECT_TYPE);
/*  539 */     for (int i = 0; i < this.argumentTypes.length; i++) {
/*  540 */       dup();
/*  541 */       push(i);
/*  542 */       loadArg(i);
/*  543 */       box(this.argumentTypes[i]);
/*  544 */       arrayStore(OBJECT_TYPE);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void storeArg(int arg) {
/*  554 */     storeInsn(this.argumentTypes[arg], getArgIndex(arg));
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
/*      */   public Type getLocalType(int local) {
/*  569 */     return this.localTypes.get(local - this.firstLocal);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void setLocalType(int local, Type type) {
/*  574 */     int index = local - this.firstLocal;
/*  575 */     while (this.localTypes.size() < index + 1) {
/*  576 */       this.localTypes.add(null);
/*      */     }
/*  578 */     this.localTypes.set(index, type);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void loadLocal(int local) {
/*  588 */     loadInsn(getLocalType(local), local);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void loadLocal(int local, Type type) {
/*  599 */     setLocalType(local, type);
/*  600 */     loadInsn(type, local);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void storeLocal(int local) {
/*  610 */     storeInsn(getLocalType(local), local);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void storeLocal(int local, Type type) {
/*  621 */     setLocalType(local, type);
/*  622 */     storeInsn(type, local);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void arrayLoad(Type type) {
/*  631 */     this.mv.visitInsn(type.getOpcode(46));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void arrayStore(Type type) {
/*  640 */     this.mv.visitInsn(type.getOpcode(79));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void pop() {
/*  649 */     this.mv.visitInsn(87);
/*      */   }
/*      */ 
/*      */   
/*      */   public void pop2() {
/*  654 */     this.mv.visitInsn(88);
/*      */   }
/*      */ 
/*      */   
/*      */   public void dup() {
/*  659 */     this.mv.visitInsn(89);
/*      */   }
/*      */ 
/*      */   
/*      */   public void dup2() {
/*  664 */     this.mv.visitInsn(92);
/*      */   }
/*      */ 
/*      */   
/*      */   public void dupX1() {
/*  669 */     this.mv.visitInsn(90);
/*      */   }
/*      */ 
/*      */   
/*      */   public void dupX2() {
/*  674 */     this.mv.visitInsn(91);
/*      */   }
/*      */ 
/*      */   
/*      */   public void dup2X1() {
/*  679 */     this.mv.visitInsn(93);
/*      */   }
/*      */ 
/*      */   
/*      */   public void dup2X2() {
/*  684 */     this.mv.visitInsn(94);
/*      */   }
/*      */ 
/*      */   
/*      */   public void swap() {
/*  689 */     this.mv.visitInsn(95);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void swap(Type prev, Type type) {
/*  699 */     if (type.getSize() == 1) {
/*  700 */       if (prev.getSize() == 1) {
/*  701 */         swap();
/*      */       } else {
/*  703 */         dupX2();
/*  704 */         pop();
/*      */       }
/*      */     
/*  707 */     } else if (prev.getSize() == 1) {
/*  708 */       dup2X1();
/*  709 */       pop2();
/*      */     } else {
/*  711 */       dup2X2();
/*  712 */       pop2();
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
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void math(int op, Type type) {
/*  729 */     this.mv.visitInsn(type.getOpcode(op));
/*      */   }
/*      */ 
/*      */   
/*      */   public void not() {
/*  734 */     this.mv.visitInsn(4);
/*  735 */     this.mv.visitInsn(130);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void iinc(int local, int amount) {
/*  745 */     this.mv.visitIincInsn(local, amount);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void cast(Type from, Type to) {
/*  755 */     if (from != to) {
/*  756 */       if (from.getSort() < 1 || from
/*  757 */         .getSort() > 8 || to
/*  758 */         .getSort() < 1 || to
/*  759 */         .getSort() > 8) {
/*  760 */         throw new IllegalArgumentException("Cannot cast from " + from + " to " + to);
/*      */       }
/*  762 */       InstructionAdapter.cast(this.mv, from, to);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static Type getBoxedType(Type type) {
/*  771 */     switch (type.getSort()) {
/*      */       case 3:
/*  773 */         return BYTE_TYPE;
/*      */       case 1:
/*  775 */         return BOOLEAN_TYPE;
/*      */       case 4:
/*  777 */         return SHORT_TYPE;
/*      */       case 2:
/*  779 */         return CHARACTER_TYPE;
/*      */       case 5:
/*  781 */         return INTEGER_TYPE;
/*      */       case 6:
/*  783 */         return FLOAT_TYPE;
/*      */       case 7:
/*  785 */         return LONG_TYPE;
/*      */       case 8:
/*  787 */         return DOUBLE_TYPE;
/*      */     } 
/*  789 */     return type;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void box(Type type) {
/*  800 */     if (type.getSort() == 10 || type.getSort() == 9) {
/*      */       return;
/*      */     }
/*  803 */     if (type == Type.VOID_TYPE) {
/*  804 */       push((String)null);
/*      */     } else {
/*  806 */       Type boxedType = getBoxedType(type);
/*  807 */       newInstance(boxedType);
/*  808 */       if (type.getSize() == 2) {
/*      */         
/*  810 */         dupX2();
/*  811 */         dupX2();
/*  812 */         pop();
/*      */       } else {
/*      */         
/*  815 */         dupX1();
/*  816 */         swap();
/*      */       } 
/*  818 */       invokeConstructor(boxedType, new Method("<init>", Type.VOID_TYPE, new Type[] { type }));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void valueOf(Type type) {
/*  829 */     if (type.getSort() == 10 || type.getSort() == 9) {
/*      */       return;
/*      */     }
/*  832 */     if (type == Type.VOID_TYPE) {
/*  833 */       push((String)null);
/*      */     } else {
/*  835 */       Type boxedType = getBoxedType(type);
/*  836 */       invokeStatic(boxedType, new Method("valueOf", boxedType, new Type[] { type }));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void unbox(Type type) {
/*      */     Method unboxMethod;
/*  847 */     Type boxedType = NUMBER_TYPE;
/*      */     
/*  849 */     switch (type.getSort()) {
/*      */       case 0:
/*      */         return;
/*      */       case 2:
/*  853 */         boxedType = CHARACTER_TYPE;
/*  854 */         unboxMethod = CHAR_VALUE;
/*      */         break;
/*      */       case 1:
/*  857 */         boxedType = BOOLEAN_TYPE;
/*  858 */         unboxMethod = BOOLEAN_VALUE;
/*      */         break;
/*      */       case 8:
/*  861 */         unboxMethod = DOUBLE_VALUE;
/*      */         break;
/*      */       case 6:
/*  864 */         unboxMethod = FLOAT_VALUE;
/*      */         break;
/*      */       case 7:
/*  867 */         unboxMethod = LONG_VALUE;
/*      */         break;
/*      */       case 3:
/*      */       case 4:
/*      */       case 5:
/*  872 */         unboxMethod = INT_VALUE;
/*      */         break;
/*      */       default:
/*  875 */         unboxMethod = null;
/*      */         break;
/*      */     } 
/*  878 */     if (unboxMethod == null) {
/*  879 */       checkCast(type);
/*      */     } else {
/*  881 */       checkCast(boxedType);
/*  882 */       invokeVirtual(boxedType, unboxMethod);
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
/*      */   
/*      */   public Label newLabel() {
/*  896 */     return new Label();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void mark(Label label) {
/*  905 */     this.mv.visitLabel(label);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Label mark() {
/*  914 */     Label label = new Label();
/*  915 */     this.mv.visitLabel(label);
/*  916 */     return label;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void ifCmp(Type type, int mode, Label label) {
/*      */     int intOp;
/*  928 */     switch (type.getSort()) {
/*      */       case 7:
/*  930 */         this.mv.visitInsn(148);
/*      */         break;
/*      */       case 8:
/*  933 */         this.mv.visitInsn((mode == 156 || mode == 157) ? 151 : 152);
/*      */         break;
/*      */       case 6:
/*  936 */         this.mv.visitInsn((mode == 156 || mode == 157) ? 149 : 150);
/*      */         break;
/*      */       case 9:
/*      */       case 10:
/*  940 */         if (mode == 153) {
/*  941 */           this.mv.visitJumpInsn(165, label); return;
/*      */         } 
/*  943 */         if (mode == 154) {
/*  944 */           this.mv.visitJumpInsn(166, label);
/*      */           return;
/*      */         } 
/*  947 */         throw new IllegalArgumentException("Bad comparison for type " + type);
/*      */       
/*      */       default:
/*  950 */         intOp = -1;
/*  951 */         switch (mode) {
/*      */           case 153:
/*  953 */             intOp = 159;
/*      */             break;
/*      */           case 154:
/*  956 */             intOp = 160;
/*      */             break;
/*      */           case 156:
/*  959 */             intOp = 162;
/*      */             break;
/*      */           case 155:
/*  962 */             intOp = 161;
/*      */             break;
/*      */           case 158:
/*  965 */             intOp = 164;
/*      */             break;
/*      */           case 157:
/*  968 */             intOp = 163;
/*      */             break;
/*      */           default:
/*  971 */             throw new IllegalArgumentException("Bad comparison mode " + mode);
/*      */         } 
/*  973 */         this.mv.visitJumpInsn(intOp, label);
/*      */         return;
/*      */     } 
/*  976 */     this.mv.visitJumpInsn(mode, label);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void ifICmp(int mode, Label label) {
/*  987 */     ifCmp(Type.INT_TYPE, mode, label);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void ifZCmp(int mode, Label label) {
/*  998 */     this.mv.visitJumpInsn(mode, label);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void ifNull(Label label) {
/* 1007 */     this.mv.visitJumpInsn(198, label);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void ifNonNull(Label label) {
/* 1016 */     this.mv.visitJumpInsn(199, label);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void goTo(Label label) {
/* 1025 */     this.mv.visitJumpInsn(167, label);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void ret(int local) {
/* 1035 */     this.mv.visitVarInsn(169, local);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void tableSwitch(int[] keys, TableSwitchGenerator generator) {
/*      */     float density;
/* 1046 */     if (keys.length == 0) {
/* 1047 */       density = 0.0F;
/*      */     } else {
/* 1049 */       density = keys.length / (keys[keys.length - 1] - keys[0] + 1);
/*      */     } 
/* 1051 */     tableSwitch(keys, generator, (density >= 0.5F));
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
/*      */   public void tableSwitch(int[] keys, TableSwitchGenerator generator, boolean useTable) {
/* 1064 */     for (int i = 1; i < keys.length; i++) {
/* 1065 */       if (keys[i] < keys[i - 1]) {
/* 1066 */         throw new IllegalArgumentException("keys must be sorted in ascending order");
/*      */       }
/*      */     } 
/* 1069 */     Label defaultLabel = newLabel();
/* 1070 */     Label endLabel = newLabel();
/* 1071 */     if (keys.length > 0) {
/* 1072 */       int numKeys = keys.length;
/* 1073 */       if (useTable) {
/* 1074 */         int min = keys[0];
/* 1075 */         int max = keys[numKeys - 1];
/* 1076 */         int range = max - min + 1;
/* 1077 */         Label[] labels = new Label[range];
/* 1078 */         Arrays.fill((Object[])labels, defaultLabel); int j;
/* 1079 */         for (j = 0; j < numKeys; j++) {
/* 1080 */           labels[keys[j] - min] = newLabel();
/*      */         }
/* 1082 */         this.mv.visitTableSwitchInsn(min, max, defaultLabel, labels);
/* 1083 */         for (j = 0; j < range; j++) {
/* 1084 */           Label label = labels[j];
/* 1085 */           if (label != defaultLabel) {
/* 1086 */             mark(label);
/* 1087 */             generator.generateCase(j + min, endLabel);
/*      */           } 
/*      */         } 
/*      */       } else {
/* 1091 */         Label[] labels = new Label[numKeys]; int j;
/* 1092 */         for (j = 0; j < numKeys; j++) {
/* 1093 */           labels[j] = newLabel();
/*      */         }
/* 1095 */         this.mv.visitLookupSwitchInsn(defaultLabel, keys, labels);
/* 1096 */         for (j = 0; j < numKeys; j++) {
/* 1097 */           mark(labels[j]);
/* 1098 */           generator.generateCase(keys[j], endLabel);
/*      */         } 
/*      */       } 
/*      */     } 
/* 1102 */     mark(defaultLabel);
/* 1103 */     generator.generateDefault();
/* 1104 */     mark(endLabel);
/*      */   }
/*      */ 
/*      */   
/*      */   public void returnValue() {
/* 1109 */     this.mv.visitInsn(this.returnType.getOpcode(172));
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
/*      */   private void fieldInsn(int opcode, Type ownerType, String name, Type fieldType) {
/* 1126 */     this.mv.visitFieldInsn(opcode, ownerType.getInternalName(), name, fieldType.getDescriptor());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void getStatic(Type owner, String name, Type type) {
/* 1137 */     fieldInsn(178, owner, name, type);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void putStatic(Type owner, String name, Type type) {
/* 1148 */     fieldInsn(179, owner, name, type);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void getField(Type owner, String name, Type type) {
/* 1159 */     fieldInsn(180, owner, name, type);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void putField(Type owner, String name, Type type) {
/* 1170 */     fieldInsn(181, owner, name, type);
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
/*      */   private void invokeInsn(int opcode, Type type, Method method, boolean isInterface) {
/* 1187 */     String owner = (type.getSort() == 9) ? type.getDescriptor() : type.getInternalName();
/* 1188 */     this.mv.visitMethodInsn(opcode, owner, method.getName(), method.getDescriptor(), isInterface);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void invokeVirtual(Type owner, Method method) {
/* 1198 */     invokeInsn(182, owner, method, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void invokeConstructor(Type type, Method method) {
/* 1208 */     invokeInsn(183, type, method, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void invokeStatic(Type owner, Method method) {
/* 1218 */     invokeInsn(184, owner, method, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void invokeInterface(Type owner, Method method) {
/* 1228 */     invokeInsn(185, owner, method, true);
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
/*      */   public void invokeDynamic(String name, String descriptor, Handle bootstrapMethodHandle, Object... bootstrapMethodArguments) {
/* 1247 */     this.mv.visitInvokeDynamicInsn(name, descriptor, bootstrapMethodHandle, bootstrapMethodArguments);
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
/*      */   private void typeInsn(int opcode, Type type) {
/* 1261 */     this.mv.visitTypeInsn(opcode, type.getInternalName());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void newInstance(Type type) {
/* 1270 */     typeInsn(187, type);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void newArray(Type type) {
/* 1279 */     InstructionAdapter.newarray(this.mv, type);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void arrayLength() {
/* 1288 */     this.mv.visitInsn(190);
/*      */   }
/*      */ 
/*      */   
/*      */   public void throwException() {
/* 1293 */     this.mv.visitInsn(191);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void throwException(Type type, String message) {
/* 1304 */     newInstance(type);
/* 1305 */     dup();
/* 1306 */     push(message);
/* 1307 */     invokeConstructor(type, Method.getMethod("void <init> (String)"));
/* 1308 */     throwException();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void checkCast(Type type) {
/* 1317 */     if (!type.equals(OBJECT_TYPE)) {
/* 1318 */       typeInsn(192, type);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void instanceOf(Type type) {
/* 1328 */     typeInsn(193, type);
/*      */   }
/*      */ 
/*      */   
/*      */   public void monitorEnter() {
/* 1333 */     this.mv.visitInsn(194);
/*      */   }
/*      */ 
/*      */   
/*      */   public void monitorExit() {
/* 1338 */     this.mv.visitInsn(195);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void endMethod() {
/* 1347 */     if ((this.access & 0x400) == 0) {
/* 1348 */       this.mv.visitMaxs(0, 0);
/*      */     }
/* 1350 */     this.mv.visitEnd();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void catchException(Label start, Label end, Type exception) {
/* 1361 */     Label catchLabel = new Label();
/* 1362 */     if (exception == null) {
/* 1363 */       this.mv.visitTryCatchBlock(start, end, catchLabel, null);
/*      */     } else {
/* 1365 */       this.mv.visitTryCatchBlock(start, end, catchLabel, exception.getInternalName());
/*      */     } 
/* 1367 */     mark(catchLabel);
/*      */   }
/*      */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\objectweb\asm\commons\GeneratorAdapter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */