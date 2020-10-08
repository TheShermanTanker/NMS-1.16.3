/*     */ package org.bukkit.craftbukkit.libs.org.objectweb.asm.tree.analysis;
/*     */ 
/*     */ import java.util.List;
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
/*     */ public class SimpleVerifier
/*     */   extends BasicVerifier
/*     */ {
/*     */   private final Type currentClass;
/*     */   private final Type currentSuperClass;
/*     */   private final List<Type> currentClassInterfaces;
/*     */   private final boolean isInterface;
/*  56 */   private ClassLoader loader = getClass().getClassLoader();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SimpleVerifier() {
/*  63 */     this(null, null, false);
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
/*     */   public SimpleVerifier(Type currentClass, Type currentSuperClass, boolean isInterface) {
/*  77 */     this(currentClass, currentSuperClass, null, isInterface);
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
/*     */   public SimpleVerifier(Type currentClass, Type currentSuperClass, List<Type> currentClassInterfaces, boolean isInterface) {
/*  96 */     this(524288, currentClass, currentSuperClass, currentClassInterfaces, isInterface);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 102 */     if (getClass() != SimpleVerifier.class) {
/* 103 */       throw new IllegalStateException();
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
/*     */   protected SimpleVerifier(int api, Type currentClass, Type currentSuperClass, List<Type> currentClassInterfaces, boolean isInterface) {
/* 127 */     super(api);
/* 128 */     this.currentClass = currentClass;
/* 129 */     this.currentSuperClass = currentSuperClass;
/* 130 */     this.currentClassInterfaces = currentClassInterfaces;
/* 131 */     this.isInterface = isInterface;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setClassLoader(ClassLoader loader) {
/* 140 */     this.loader = loader;
/*     */   }
/*     */ 
/*     */   
/*     */   public BasicValue newValue(Type type) {
/* 145 */     if (type == null) {
/* 146 */       return BasicValue.UNINITIALIZED_VALUE;
/*     */     }
/*     */     
/* 149 */     boolean isArray = (type.getSort() == 9);
/* 150 */     if (isArray) {
/* 151 */       switch (type.getElementType().getSort()) {
/*     */         case 1:
/*     */         case 2:
/*     */         case 3:
/*     */         case 4:
/* 156 */           return new BasicValue(type);
/*     */       } 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 162 */     BasicValue value = super.newValue(type);
/* 163 */     if (BasicValue.REFERENCE_VALUE.equals(value)) {
/* 164 */       if (isArray) {
/* 165 */         value = newValue(type.getElementType());
/* 166 */         StringBuilder descriptor = new StringBuilder();
/* 167 */         for (int i = 0; i < type.getDimensions(); i++) {
/* 168 */           descriptor.append('[');
/*     */         }
/* 170 */         descriptor.append(value.getType().getDescriptor());
/* 171 */         value = new BasicValue(Type.getType(descriptor.toString()));
/*     */       } else {
/* 173 */         value = new BasicValue(type);
/*     */       } 
/*     */     }
/* 176 */     return value;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isArrayValue(BasicValue value) {
/* 181 */     Type type = value.getType();
/* 182 */     return (type != null && (type.getSort() == 9 || type.equals(NULL_TYPE)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected BasicValue getElementValue(BasicValue objectArrayValue) throws AnalyzerException {
/* 187 */     Type arrayType = objectArrayValue.getType();
/* 188 */     if (arrayType != null) {
/* 189 */       if (arrayType.getSort() == 9)
/* 190 */         return newValue(Type.getType(arrayType.getDescriptor().substring(1))); 
/* 191 */       if (arrayType.equals(NULL_TYPE)) {
/* 192 */         return objectArrayValue;
/*     */       }
/*     */     } 
/* 195 */     throw new AssertionError();
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isSubTypeOf(BasicValue value, BasicValue expected) {
/* 200 */     Type expectedType = expected.getType();
/* 201 */     Type type = value.getType();
/* 202 */     switch (expectedType.getSort()) {
/*     */       case 5:
/*     */       case 6:
/*     */       case 7:
/*     */       case 8:
/* 207 */         return type.equals(expectedType);
/*     */       case 9:
/*     */       case 10:
/* 210 */         if (type.equals(NULL_TYPE))
/* 211 */           return true; 
/* 212 */         if (type.getSort() == 10 || type.getSort() == 9) {
/* 213 */           if (isAssignableFrom(expectedType, type))
/* 214 */             return true; 
/* 215 */           if (getClass(expectedType).isInterface())
/*     */           {
/*     */ 
/*     */ 
/*     */             
/* 220 */             return Object.class.isAssignableFrom(getClass(type));
/*     */           }
/* 222 */           return false;
/*     */         } 
/*     */         
/* 225 */         return false;
/*     */     } 
/*     */     
/* 228 */     throw new AssertionError();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BasicValue merge(BasicValue value1, BasicValue value2) {
/* 234 */     if (!value1.equals(value2)) {
/* 235 */       Type type1 = value1.getType();
/* 236 */       Type type2 = value2.getType();
/* 237 */       if (type1 != null && (type1
/* 238 */         .getSort() == 10 || type1.getSort() == 9) && type2 != null && (type2
/*     */         
/* 240 */         .getSort() == 10 || type2.getSort() == 9)) {
/* 241 */         if (type1.equals(NULL_TYPE)) {
/* 242 */           return value2;
/*     */         }
/* 244 */         if (type2.equals(NULL_TYPE)) {
/* 245 */           return value1;
/*     */         }
/* 247 */         if (isAssignableFrom(type1, type2)) {
/* 248 */           return value1;
/*     */         }
/* 250 */         if (isAssignableFrom(type2, type1)) {
/* 251 */           return value2;
/*     */         }
/* 253 */         int numDimensions = 0;
/* 254 */         if (type1.getSort() == 9 && type2
/* 255 */           .getSort() == 9 && type1
/* 256 */           .getDimensions() == type2.getDimensions() && type1
/* 257 */           .getElementType().getSort() == 10 && type2
/* 258 */           .getElementType().getSort() == 10) {
/* 259 */           numDimensions = type1.getDimensions();
/* 260 */           type1 = type1.getElementType();
/* 261 */           type2 = type2.getElementType();
/*     */         } 
/*     */         while (true) {
/* 264 */           if (type1 == null || isInterface(type1)) {
/* 265 */             return newArrayValue(Type.getObjectType("java/lang/Object"), numDimensions);
/*     */           }
/* 267 */           type1 = getSuperClass(type1);
/* 268 */           if (isAssignableFrom(type1, type2)) {
/* 269 */             return newArrayValue(type1, numDimensions);
/*     */           }
/*     */         } 
/*     */       } 
/* 273 */       return BasicValue.UNINITIALIZED_VALUE;
/*     */     } 
/* 275 */     return value1;
/*     */   }
/*     */   
/*     */   private BasicValue newArrayValue(Type type, int dimensions) {
/* 279 */     if (dimensions == 0) {
/* 280 */       return newValue(type);
/*     */     }
/* 282 */     StringBuilder descriptor = new StringBuilder();
/* 283 */     for (int i = 0; i < dimensions; i++) {
/* 284 */       descriptor.append('[');
/*     */     }
/* 286 */     descriptor.append(type.getDescriptor());
/* 287 */     return newValue(Type.getType(descriptor.toString()));
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
/*     */   protected boolean isInterface(Type type) {
/* 300 */     if (this.currentClass != null && this.currentClass.equals(type)) {
/* 301 */       return this.isInterface;
/*     */     }
/* 303 */     return getClass(type).isInterface();
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
/*     */   protected Type getSuperClass(Type type) {
/* 315 */     if (this.currentClass != null && this.currentClass.equals(type)) {
/* 316 */       return this.currentSuperClass;
/*     */     }
/* 318 */     Class<?> superClass = getClass(type).getSuperclass();
/* 319 */     return (superClass == null) ? null : Type.getType(superClass);
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
/*     */   protected boolean isAssignableFrom(Type type1, Type type2) {
/* 335 */     if (type1.equals(type2)) {
/* 336 */       return true;
/*     */     }
/* 338 */     if (this.currentClass != null && this.currentClass.equals(type1)) {
/* 339 */       if (getSuperClass(type2) == null) {
/* 340 */         return false;
/*     */       }
/* 342 */       if (this.isInterface) {
/* 343 */         return (type2.getSort() == 10 || type2.getSort() == 9);
/*     */       }
/* 345 */       return isAssignableFrom(type1, getSuperClass(type2));
/*     */     } 
/*     */     
/* 348 */     if (this.currentClass != null && this.currentClass.equals(type2)) {
/* 349 */       if (isAssignableFrom(type1, this.currentSuperClass)) {
/* 350 */         return true;
/*     */       }
/* 352 */       if (this.currentClassInterfaces != null) {
/* 353 */         for (Type currentClassInterface : this.currentClassInterfaces) {
/* 354 */           if (isAssignableFrom(type1, currentClassInterface)) {
/* 355 */             return true;
/*     */           }
/*     */         } 
/*     */       }
/* 359 */       return false;
/*     */     } 
/* 361 */     return getClass(type1).isAssignableFrom(getClass(type2));
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
/*     */   protected Class<?> getClass(Type type) {
/*     */     try {
/* 374 */       if (type.getSort() == 9) {
/* 375 */         return Class.forName(type.getDescriptor().replace('/', '.'), false, this.loader);
/*     */       }
/* 377 */       return Class.forName(type.getClassName(), false, this.loader);
/* 378 */     } catch (ClassNotFoundException e) {
/* 379 */       throw new TypeNotPresentException(e.toString(), e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\objectweb\asm\tree\analysis\SimpleVerifier.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */