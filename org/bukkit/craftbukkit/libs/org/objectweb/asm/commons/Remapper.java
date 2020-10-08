/*     */ package org.bukkit.craftbukkit.libs.org.objectweb.asm.commons;
/*     */ 
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.ConstantDynamic;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.Handle;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.Type;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.signature.SignatureReader;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.signature.SignatureVisitor;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.signature.SignatureWriter;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class Remapper
/*     */ {
/*     */   public String mapDesc(String descriptor) {
/*  55 */     return mapType(Type.getType(descriptor)).getDescriptor();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Type mapType(Type type) {
/*     */     StringBuilder remappedDescriptor;
/*     */     int i;
/*     */     String remappedInternalName;
/*  69 */     switch (type.getSort()) {
/*     */       case 9:
/*  71 */         remappedDescriptor = new StringBuilder();
/*  72 */         for (i = 0; i < type.getDimensions(); i++) {
/*  73 */           remappedDescriptor.append('[');
/*     */         }
/*  75 */         remappedDescriptor.append(mapType(type.getElementType()).getDescriptor());
/*  76 */         return Type.getType(remappedDescriptor.toString());
/*     */       case 10:
/*  78 */         remappedInternalName = map(type.getInternalName());
/*  79 */         return (remappedInternalName != null) ? Type.getObjectType(remappedInternalName) : type;
/*     */       case 11:
/*  81 */         return Type.getMethodType(mapMethodDesc(type.getDescriptor()));
/*     */     } 
/*  83 */     return type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String mapType(String internalName) {
/*  94 */     if (internalName == null) {
/*  95 */       return null;
/*     */     }
/*  97 */     return mapType(Type.getObjectType(internalName)).getInternalName();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] mapTypes(String[] internalNames) {
/* 107 */     String[] remappedInternalNames = null;
/* 108 */     for (int i = 0; i < internalNames.length; i++) {
/* 109 */       String internalName = internalNames[i];
/* 110 */       String remappedInternalName = mapType(internalName);
/* 111 */       if (remappedInternalName != null) {
/* 112 */         if (remappedInternalNames == null) {
/* 113 */           remappedInternalNames = (String[])internalNames.clone();
/*     */         }
/* 115 */         remappedInternalNames[i] = remappedInternalName;
/*     */       } 
/*     */     } 
/* 118 */     return (remappedInternalNames != null) ? remappedInternalNames : internalNames;
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
/*     */   public String mapMethodDesc(String methodDescriptor) {
/* 130 */     if ("()V".equals(methodDescriptor)) {
/* 131 */       return methodDescriptor;
/*     */     }
/*     */     
/* 134 */     StringBuilder stringBuilder = new StringBuilder("(");
/* 135 */     for (Type argumentType : Type.getArgumentTypes(methodDescriptor)) {
/* 136 */       stringBuilder.append(mapType(argumentType).getDescriptor());
/*     */     }
/* 138 */     Type returnType = Type.getReturnType(methodDescriptor);
/* 139 */     if (returnType == Type.VOID_TYPE) {
/* 140 */       stringBuilder.append(")V");
/*     */     } else {
/* 142 */       stringBuilder.append(')').append(mapType(returnType).getDescriptor());
/*     */     } 
/* 144 */     return stringBuilder.toString();
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
/*     */   public Object mapValue(Object value) {
/* 158 */     if (value instanceof Type) {
/* 159 */       return mapType((Type)value);
/*     */     }
/* 161 */     if (value instanceof Handle) {
/* 162 */       Handle handle = (Handle)value;
/* 163 */       return new Handle(handle
/* 164 */           .getTag(), 
/* 165 */           mapType(handle.getOwner()), 
/* 166 */           mapMethodName(handle.getOwner(), handle.getName(), handle.getDesc()), 
/* 167 */           (handle.getTag() <= 4) ? 
/* 168 */           mapDesc(handle.getDesc()) : 
/* 169 */           mapMethodDesc(handle.getDesc()), handle
/* 170 */           .isInterface());
/*     */     } 
/* 172 */     if (value instanceof ConstantDynamic) {
/* 173 */       ConstantDynamic constantDynamic = (ConstantDynamic)value;
/* 174 */       int bootstrapMethodArgumentCount = constantDynamic.getBootstrapMethodArgumentCount();
/* 175 */       Object[] remappedBootstrapMethodArguments = new Object[bootstrapMethodArgumentCount];
/* 176 */       for (int i = 0; i < bootstrapMethodArgumentCount; i++) {
/* 177 */         remappedBootstrapMethodArguments[i] = 
/* 178 */           mapValue(constantDynamic.getBootstrapMethodArgument(i));
/*     */       }
/* 180 */       String descriptor = constantDynamic.getDescriptor();
/* 181 */       return new ConstantDynamic(
/* 182 */           mapInvokeDynamicMethodName(constantDynamic.getName(), descriptor), 
/* 183 */           mapDesc(descriptor), (Handle)
/* 184 */           mapValue(constantDynamic.getBootstrapMethod()), remappedBootstrapMethodArguments);
/*     */     } 
/*     */     
/* 187 */     return value;
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
/*     */   public String mapSignature(String signature, boolean typeSignature) {
/* 200 */     if (signature == null) {
/* 201 */       return null;
/*     */     }
/* 203 */     SignatureReader signatureReader = new SignatureReader(signature);
/* 204 */     SignatureWriter signatureWriter = new SignatureWriter();
/* 205 */     SignatureVisitor signatureRemapper = createSignatureRemapper((SignatureVisitor)signatureWriter);
/* 206 */     if (typeSignature) {
/* 207 */       signatureReader.acceptType(signatureRemapper);
/*     */     } else {
/* 209 */       signatureReader.accept(signatureRemapper);
/*     */     } 
/* 211 */     return signatureWriter.toString();
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
/*     */   @Deprecated
/*     */   protected SignatureVisitor createRemappingSignatureAdapter(SignatureVisitor signatureVisitor) {
/* 225 */     return createSignatureRemapper(signatureVisitor);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SignatureVisitor createSignatureRemapper(SignatureVisitor signatureVisitor) {
/* 236 */     return new SignatureRemapper(signatureVisitor, this);
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
/*     */   public String mapInnerClassName(String name, String ownerName, String innerName) {
/* 251 */     String remappedInnerName = mapType(name);
/* 252 */     if (remappedInnerName.contains("$")) {
/* 253 */       int index = remappedInnerName.lastIndexOf('$') + 1;
/* 254 */       while (index < remappedInnerName.length() && 
/* 255 */         Character.isDigit(remappedInnerName.charAt(index))) {
/* 256 */         index++;
/*     */       }
/* 258 */       return remappedInnerName.substring(index);
/*     */     } 
/* 260 */     return innerName;
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
/*     */   public String mapMethodName(String owner, String name, String descriptor) {
/* 274 */     return name;
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
/*     */   public String mapInvokeDynamicMethodName(String name, String descriptor) {
/* 286 */     return name;
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
/*     */   public String mapRecordComponentName(String owner, String name, String descriptor) {
/* 300 */     return name;
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
/*     */   public String mapFieldName(String owner, String name, String descriptor) {
/* 313 */     return name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String mapPackageName(String name) {
/* 324 */     return name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String mapModuleName(String name) {
/* 335 */     return name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String map(String internalName) {
/* 346 */     return internalName;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\objectweb\asm\commons\Remapper.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */