/*     */ package org.bukkit.craftbukkit.libs.org.objectweb.asm.commons;
/*     */ 
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
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
/*     */ public class Method
/*     */ {
/*     */   private final String name;
/*     */   private final String descriptor;
/*     */   private static final Map<String, String> PRIMITIVE_TYPE_DESCRIPTORS;
/*     */   
/*     */   static {
/*  53 */     HashMap<String, String> descriptors = new HashMap<String, String>();
/*  54 */     descriptors.put("void", "V");
/*  55 */     descriptors.put("byte", "B");
/*  56 */     descriptors.put("char", "C");
/*  57 */     descriptors.put("double", "D");
/*  58 */     descriptors.put("float", "F");
/*  59 */     descriptors.put("int", "I");
/*  60 */     descriptors.put("long", "J");
/*  61 */     descriptors.put("short", "S");
/*  62 */     descriptors.put("boolean", "Z");
/*  63 */     PRIMITIVE_TYPE_DESCRIPTORS = descriptors;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Method(String name, String descriptor) {
/*  73 */     this.name = name;
/*  74 */     this.descriptor = descriptor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Method(String name, Type returnType, Type[] argumentTypes) {
/*  85 */     this(name, Type.getMethodDescriptor(returnType, argumentTypes));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Method getMethod(Method method) {
/*  95 */     return new Method(method.getName(), Type.getMethodDescriptor(method));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Method getMethod(Constructor<?> constructor) {
/* 105 */     return new Method("<init>", Type.getConstructorDescriptor(constructor));
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
/*     */   public static Method getMethod(String method) {
/* 119 */     return getMethod(method, false);
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
/*     */   public static Method getMethod(String method, boolean defaultPackage) {
/* 137 */     int spaceIndex = method.indexOf(' ');
/* 138 */     int currentArgumentStartIndex = method.indexOf('(', spaceIndex) + 1;
/* 139 */     int endIndex = method.indexOf(')', currentArgumentStartIndex);
/* 140 */     if (spaceIndex == -1 || currentArgumentStartIndex == 0 || endIndex == -1) {
/* 141 */       throw new IllegalArgumentException();
/*     */     }
/* 143 */     String returnType = method.substring(0, spaceIndex);
/*     */     
/* 145 */     String methodName = method.substring(spaceIndex + 1, currentArgumentStartIndex - 1).trim();
/* 146 */     StringBuilder stringBuilder = new StringBuilder();
/* 147 */     stringBuilder.append('(');
/*     */     
/*     */     while (true) {
/*     */       String argumentDescriptor;
/* 151 */       int currentArgumentEndIndex = method.indexOf(',', currentArgumentStartIndex);
/* 152 */       if (currentArgumentEndIndex == -1) {
/*     */         
/* 154 */         argumentDescriptor = getDescriptorInternal(method
/* 155 */             .substring(currentArgumentStartIndex, endIndex).trim(), defaultPackage);
/*     */       } else {
/*     */         
/* 158 */         argumentDescriptor = getDescriptorInternal(method
/* 159 */             .substring(currentArgumentStartIndex, currentArgumentEndIndex).trim(), defaultPackage);
/*     */         
/* 161 */         currentArgumentStartIndex = currentArgumentEndIndex + 1;
/*     */       } 
/* 163 */       stringBuilder.append(argumentDescriptor);
/* 164 */       if (currentArgumentEndIndex == -1) {
/* 165 */         stringBuilder.append(')').append(getDescriptorInternal(returnType, defaultPackage));
/* 166 */         return new Method(methodName, stringBuilder.toString());
/*     */       } 
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
/*     */   private static String getDescriptorInternal(String type, boolean defaultPackage) {
/* 179 */     if ("".equals(type)) {
/* 180 */       return type;
/*     */     }
/*     */     
/* 183 */     StringBuilder stringBuilder = new StringBuilder();
/* 184 */     int arrayBracketsIndex = 0;
/* 185 */     while ((arrayBracketsIndex = type.indexOf("[]", arrayBracketsIndex) + 1) > 0) {
/* 186 */       stringBuilder.append('[');
/*     */     }
/*     */     
/* 189 */     String elementType = type.substring(0, type.length() - stringBuilder.length() * 2);
/* 190 */     String descriptor = PRIMITIVE_TYPE_DESCRIPTORS.get(elementType);
/* 191 */     if (descriptor != null) {
/* 192 */       stringBuilder.append(descriptor);
/*     */     } else {
/* 194 */       stringBuilder.append('L');
/* 195 */       if (elementType.indexOf('.') < 0) {
/* 196 */         if (!defaultPackage) {
/* 197 */           stringBuilder.append("java/lang/");
/*     */         }
/* 199 */         stringBuilder.append(elementType);
/*     */       } else {
/* 201 */         stringBuilder.append(elementType.replace('.', '/'));
/*     */       } 
/* 203 */       stringBuilder.append(';');
/*     */     } 
/* 205 */     return stringBuilder.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 214 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDescriptor() {
/* 223 */     return this.descriptor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Type getReturnType() {
/* 232 */     return Type.getReturnType(this.descriptor);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Type[] getArgumentTypes() {
/* 241 */     return Type.getArgumentTypes(this.descriptor);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 246 */     return this.name + this.descriptor;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object other) {
/* 251 */     if (!(other instanceof Method)) {
/* 252 */       return false;
/*     */     }
/* 254 */     Method otherMethod = (Method)other;
/* 255 */     return (this.name.equals(otherMethod.name) && this.descriptor.equals(otherMethod.descriptor));
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 260 */     return this.name.hashCode() ^ this.descriptor.hashCode();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\objectweb\asm\commons\Method.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */