/*     */ package org.bukkit.craftbukkit.libs.org.objectweb.asm;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class ClassVisitor
/*     */ {
/*     */   protected final int api;
/*     */   protected ClassVisitor cv;
/*     */   
/*     */   public ClassVisitor(int api) {
/*  57 */     this(api, null);
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
/*     */   public ClassVisitor(int api, ClassVisitor classVisitor) {
/*  70 */     if (api != 524288 && api != 458752 && api != 393216 && api != 327680 && api != 262144 && api != 17367040)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  76 */       throw new IllegalArgumentException("Unsupported api " + api);
/*     */     }
/*  78 */     if (api == 17367040) {
/*  79 */       Constants.checkAsmExperimental(this);
/*     */     }
/*  81 */     this.api = api;
/*  82 */     this.cv = classVisitor;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
/* 109 */     if (this.api < 524288 && (access & 0x10000) != 0) {
/* 110 */       throw new UnsupportedOperationException("Records requires ASM8");
/*     */     }
/* 112 */     if (this.cv != null) {
/* 113 */       this.cv.visit(version, access, name, signature, superName, interfaces);
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
/*     */   public void visitSource(String source, String debug) {
/* 126 */     if (this.cv != null) {
/* 127 */       this.cv.visitSource(source, debug);
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
/*     */   public ModuleVisitor visitModule(String name, int access, String version) {
/* 142 */     if (this.api < 393216) {
/* 143 */       throw new UnsupportedOperationException("This feature requires ASM6");
/*     */     }
/* 145 */     if (this.cv != null) {
/* 146 */       return this.cv.visitModule(name, access, version);
/*     */     }
/* 148 */     return null;
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
/*     */   public void visitNestHost(String nestHost) {
/* 162 */     if (this.api < 458752) {
/* 163 */       throw new UnsupportedOperationException("This feature requires ASM7");
/*     */     }
/* 165 */     if (this.cv != null) {
/* 166 */       this.cv.visitNestHost(nestHost);
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
/*     */   public void visitOuterClass(String owner, String name, String descriptor) {
/* 181 */     if (this.cv != null) {
/* 182 */       this.cv.visitOuterClass(owner, name, descriptor);
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
/*     */   public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
/* 195 */     if (this.cv != null) {
/* 196 */       return this.cv.visitAnnotation(descriptor, visible);
/*     */     }
/* 198 */     return null;
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
/*     */   public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, String descriptor, boolean visible) {
/* 218 */     if (this.api < 327680) {
/* 219 */       throw new UnsupportedOperationException("This feature requires ASM5");
/*     */     }
/* 221 */     if (this.cv != null) {
/* 222 */       return this.cv.visitTypeAnnotation(typeRef, typePath, descriptor, visible);
/*     */     }
/* 224 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitAttribute(Attribute attribute) {
/* 233 */     if (this.cv != null) {
/* 234 */       this.cv.visitAttribute(attribute);
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
/*     */   public void visitNestMember(String nestMember) {
/* 248 */     if (this.api < 458752) {
/* 249 */       throw new UnsupportedOperationException("This feature requires ASM7");
/*     */     }
/* 251 */     if (this.cv != null) {
/* 252 */       this.cv.visitNestMember(nestMember);
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
/*     */   @Deprecated
/*     */   public void visitPermittedSubtypeExperimental(String permittedSubtype) {
/* 266 */     if (this.api != 17367040) {
/* 267 */       throw new UnsupportedOperationException("This feature requires ASM9_EXPERIMENTAL");
/*     */     }
/* 269 */     if (this.cv != null) {
/* 270 */       this.cv.visitPermittedSubtypeExperimental(permittedSubtype);
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
/*     */   public void visitInnerClass(String name, String outerName, String innerName, int access) {
/* 288 */     if (this.cv != null) {
/* 289 */       this.cv.visitInnerClass(name, outerName, innerName, access);
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
/*     */   public RecordComponentVisitor visitRecordComponent(String name, String descriptor, String signature) {
/* 305 */     if (this.api < 524288) {
/* 306 */       throw new UnsupportedOperationException("This feature requires ASM8");
/*     */     }
/* 308 */     if (this.cv != null) {
/* 309 */       return this.cv.visitRecordComponent(name, descriptor, signature);
/*     */     }
/* 311 */     return null;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
/* 338 */     if (this.cv != null) {
/* 339 */       return this.cv.visitField(access, name, descriptor, signature, value);
/*     */     }
/* 341 */     return null;
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
/*     */ 
/*     */   
/*     */   public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
/* 366 */     if (this.cv != null) {
/* 367 */       return this.cv.visitMethod(access, name, descriptor, signature, exceptions);
/*     */     }
/* 369 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitEnd() {
/* 377 */     if (this.cv != null)
/* 378 */       this.cv.visitEnd(); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\objectweb\asm\ClassVisitor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */