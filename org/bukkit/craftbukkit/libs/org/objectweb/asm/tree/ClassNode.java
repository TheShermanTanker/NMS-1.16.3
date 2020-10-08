/*     */ package org.bukkit.craftbukkit.libs.org.objectweb.asm.tree;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.AnnotationVisitor;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.Attribute;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.ClassVisitor;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.FieldVisitor;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.MethodVisitor;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.ModuleVisitor;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.RecordComponentVisitor;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.TypePath;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ClassNode
/*     */   extends ClassVisitor
/*     */ {
/*     */   public int version;
/*     */   public int access;
/*     */   public String name;
/*     */   public String signature;
/*     */   public String superName;
/*     */   public List<String> interfaces;
/*     */   public String sourceFile;
/*     */   public String sourceDebug;
/*     */   public ModuleNode module;
/*     */   public String outerClass;
/*     */   public String outerMethod;
/*     */   public String outerMethodDesc;
/*     */   public List<AnnotationNode> visibleAnnotations;
/*     */   public List<AnnotationNode> invisibleAnnotations;
/*     */   public List<TypeAnnotationNode> visibleTypeAnnotations;
/*     */   public List<TypeAnnotationNode> invisibleTypeAnnotations;
/*     */   public List<Attribute> attrs;
/*     */   public List<InnerClassNode> innerClasses;
/*     */   public String nestHostClass;
/*     */   public List<String> nestMembers;
/*     */   @Deprecated
/*     */   public List<String> permittedSubtypesExperimental;
/*     */   public List<RecordComponentNode> recordComponents;
/*     */   public List<FieldNode> fields;
/*     */   public List<MethodNode> methods;
/*     */   
/*     */   public ClassNode() {
/* 155 */     this(524288);
/* 156 */     if (getClass() != ClassNode.class) {
/* 157 */       throw new IllegalStateException();
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
/*     */   public ClassNode(int api) {
/* 169 */     super(api);
/* 170 */     this.interfaces = new ArrayList<String>();
/* 171 */     this.innerClasses = new ArrayList<InnerClassNode>();
/* 172 */     this.fields = new ArrayList<FieldNode>();
/* 173 */     this.methods = new ArrayList<MethodNode>();
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
/*     */   public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
/* 188 */     this.version = version;
/* 189 */     this.access = access;
/* 190 */     this.name = name;
/* 191 */     this.signature = signature;
/* 192 */     this.superName = superName;
/* 193 */     this.interfaces = Util.asArrayList(interfaces);
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitSource(String file, String debug) {
/* 198 */     this.sourceFile = file;
/* 199 */     this.sourceDebug = debug;
/*     */   }
/*     */ 
/*     */   
/*     */   public ModuleVisitor visitModule(String name, int access, String version) {
/* 204 */     this.module = new ModuleNode(name, access, version);
/* 205 */     return this.module;
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitNestHost(String nestHost) {
/* 210 */     this.nestHostClass = nestHost;
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitOuterClass(String owner, String name, String descriptor) {
/* 215 */     this.outerClass = owner;
/* 216 */     this.outerMethod = name;
/* 217 */     this.outerMethodDesc = descriptor;
/*     */   }
/*     */ 
/*     */   
/*     */   public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
/* 222 */     AnnotationNode annotation = new AnnotationNode(descriptor);
/* 223 */     if (visible) {
/* 224 */       this.visibleAnnotations = Util.add(this.visibleAnnotations, annotation);
/*     */     } else {
/* 226 */       this.invisibleAnnotations = Util.add(this.invisibleAnnotations, annotation);
/*     */     } 
/* 228 */     return annotation;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, String descriptor, boolean visible) {
/* 234 */     TypeAnnotationNode typeAnnotation = new TypeAnnotationNode(typeRef, typePath, descriptor);
/* 235 */     if (visible) {
/* 236 */       this.visibleTypeAnnotations = Util.add(this.visibleTypeAnnotations, typeAnnotation);
/*     */     } else {
/* 238 */       this.invisibleTypeAnnotations = Util.add(this.invisibleTypeAnnotations, typeAnnotation);
/*     */     } 
/* 240 */     return typeAnnotation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitAttribute(Attribute attribute) {
/* 245 */     this.attrs = Util.add(this.attrs, attribute);
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitNestMember(String nestMember) {
/* 250 */     this.nestMembers = Util.add(this.nestMembers, nestMember);
/*     */   }
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
/* 262 */     this.permittedSubtypesExperimental = Util.add(this.permittedSubtypesExperimental, permittedSubtype);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitInnerClass(String name, String outerName, String innerName, int access) {
/* 268 */     InnerClassNode innerClass = new InnerClassNode(name, outerName, innerName, access);
/* 269 */     this.innerClasses.add(innerClass);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public RecordComponentVisitor visitRecordComponent(String name, String descriptor, String signature) {
/* 275 */     RecordComponentNode recordComponent = new RecordComponentNode(name, descriptor, signature);
/* 276 */     this.recordComponents = Util.add(this.recordComponents, recordComponent);
/* 277 */     return recordComponent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
/* 287 */     FieldNode field = new FieldNode(access, name, descriptor, signature, value);
/* 288 */     this.fields.add(field);
/* 289 */     return field;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
/* 299 */     MethodNode method = new MethodNode(access, name, descriptor, signature, exceptions);
/* 300 */     this.methods.add(method);
/* 301 */     return method;
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
/*     */   public void visitEnd() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void check(int api) {
/* 322 */     if (api != 17367040 && this.permittedSubtypesExperimental != null) {
/* 323 */       throw new UnsupportedClassVersionException();
/*     */     }
/* 325 */     if (api < 524288 && ((this.access & 0x10000) != 0 || this.recordComponents != null)) {
/* 326 */       throw new UnsupportedClassVersionException();
/*     */     }
/* 328 */     if (api < 458752 && (this.nestHostClass != null || this.nestMembers != null)) {
/* 329 */       throw new UnsupportedClassVersionException();
/*     */     }
/* 331 */     if (api < 393216 && this.module != null) {
/* 332 */       throw new UnsupportedClassVersionException();
/*     */     }
/* 334 */     if (api < 327680) {
/* 335 */       if (this.visibleTypeAnnotations != null && !this.visibleTypeAnnotations.isEmpty()) {
/* 336 */         throw new UnsupportedClassVersionException();
/*     */       }
/* 338 */       if (this.invisibleTypeAnnotations != null && !this.invisibleTypeAnnotations.isEmpty()) {
/* 339 */         throw new UnsupportedClassVersionException();
/*     */       }
/*     */     } 
/*     */     
/* 343 */     if (this.visibleAnnotations != null) {
/* 344 */       for (int j = this.visibleAnnotations.size() - 1; j >= 0; j--) {
/* 345 */         ((AnnotationNode)this.visibleAnnotations.get(j)).check(api);
/*     */       }
/*     */     }
/* 348 */     if (this.invisibleAnnotations != null) {
/* 349 */       for (int j = this.invisibleAnnotations.size() - 1; j >= 0; j--) {
/* 350 */         ((AnnotationNode)this.invisibleAnnotations.get(j)).check(api);
/*     */       }
/*     */     }
/* 353 */     if (this.visibleTypeAnnotations != null) {
/* 354 */       for (int j = this.visibleTypeAnnotations.size() - 1; j >= 0; j--) {
/* 355 */         ((TypeAnnotationNode)this.visibleTypeAnnotations.get(j)).check(api);
/*     */       }
/*     */     }
/* 358 */     if (this.invisibleTypeAnnotations != null) {
/* 359 */       for (int j = this.invisibleTypeAnnotations.size() - 1; j >= 0; j--) {
/* 360 */         ((TypeAnnotationNode)this.invisibleTypeAnnotations.get(j)).check(api);
/*     */       }
/*     */     }
/* 363 */     if (this.recordComponents != null)
/* 364 */       for (int j = this.recordComponents.size() - 1; j >= 0; j--) {
/* 365 */         ((RecordComponentNode)this.recordComponents.get(j)).check(api);
/*     */       } 
/*     */     int i;
/* 368 */     for (i = this.fields.size() - 1; i >= 0; i--) {
/* 369 */       ((FieldNode)this.fields.get(i)).check(api);
/*     */     }
/* 371 */     for (i = this.methods.size() - 1; i >= 0; i--) {
/* 372 */       ((MethodNode)this.methods.get(i)).check(api);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void accept(ClassVisitor classVisitor) {
/* 383 */     String[] interfacesArray = new String[this.interfaces.size()];
/* 384 */     this.interfaces.toArray(interfacesArray);
/* 385 */     classVisitor.visit(this.version, this.access, this.name, this.signature, this.superName, interfacesArray);
/*     */     
/* 387 */     if (this.sourceFile != null || this.sourceDebug != null) {
/* 388 */       classVisitor.visitSource(this.sourceFile, this.sourceDebug);
/*     */     }
/*     */     
/* 391 */     if (this.module != null) {
/* 392 */       this.module.accept(classVisitor);
/*     */     }
/*     */     
/* 395 */     if (this.nestHostClass != null) {
/* 396 */       classVisitor.visitNestHost(this.nestHostClass);
/*     */     }
/*     */     
/* 399 */     if (this.outerClass != null) {
/* 400 */       classVisitor.visitOuterClass(this.outerClass, this.outerMethod, this.outerMethodDesc);
/*     */     }
/*     */     
/* 403 */     if (this.visibleAnnotations != null) {
/* 404 */       for (int j = 0, k = this.visibleAnnotations.size(); j < k; j++) {
/* 405 */         AnnotationNode annotation = this.visibleAnnotations.get(j);
/* 406 */         annotation.accept(classVisitor.visitAnnotation(annotation.desc, true));
/*     */       } 
/*     */     }
/* 409 */     if (this.invisibleAnnotations != null) {
/* 410 */       for (int j = 0, k = this.invisibleAnnotations.size(); j < k; j++) {
/* 411 */         AnnotationNode annotation = this.invisibleAnnotations.get(j);
/* 412 */         annotation.accept(classVisitor.visitAnnotation(annotation.desc, false));
/*     */       } 
/*     */     }
/* 415 */     if (this.visibleTypeAnnotations != null) {
/* 416 */       for (int j = 0, k = this.visibleTypeAnnotations.size(); j < k; j++) {
/* 417 */         TypeAnnotationNode typeAnnotation = this.visibleTypeAnnotations.get(j);
/* 418 */         typeAnnotation.accept(classVisitor
/* 419 */             .visitTypeAnnotation(typeAnnotation.typeRef, typeAnnotation.typePath, typeAnnotation.desc, true));
/*     */       } 
/*     */     }
/*     */     
/* 423 */     if (this.invisibleTypeAnnotations != null) {
/* 424 */       for (int j = 0, k = this.invisibleTypeAnnotations.size(); j < k; j++) {
/* 425 */         TypeAnnotationNode typeAnnotation = this.invisibleTypeAnnotations.get(j);
/* 426 */         typeAnnotation.accept(classVisitor
/* 427 */             .visitTypeAnnotation(typeAnnotation.typeRef, typeAnnotation.typePath, typeAnnotation.desc, false));
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 432 */     if (this.attrs != null) {
/* 433 */       for (int j = 0, k = this.attrs.size(); j < k; j++) {
/* 434 */         classVisitor.visitAttribute(this.attrs.get(j));
/*     */       }
/*     */     }
/*     */     
/* 438 */     if (this.nestMembers != null) {
/* 439 */       for (int j = 0, k = this.nestMembers.size(); j < k; j++) {
/* 440 */         classVisitor.visitNestMember(this.nestMembers.get(j));
/*     */       }
/*     */     }
/*     */     
/* 444 */     if (this.permittedSubtypesExperimental != null) {
/* 445 */       for (int j = 0, k = this.permittedSubtypesExperimental.size(); j < k; j++) {
/* 446 */         classVisitor.visitPermittedSubtypeExperimental(this.permittedSubtypesExperimental.get(j));
/*     */       }
/*     */     }
/*     */     int i, n;
/* 450 */     for (i = 0, n = this.innerClasses.size(); i < n; i++) {
/* 451 */       ((InnerClassNode)this.innerClasses.get(i)).accept(classVisitor);
/*     */     }
/*     */     
/* 454 */     if (this.recordComponents != null) {
/* 455 */       for (i = 0, n = this.recordComponents.size(); i < n; i++) {
/* 456 */         ((RecordComponentNode)this.recordComponents.get(i)).accept(classVisitor);
/*     */       }
/*     */     }
/*     */     
/* 460 */     for (i = 0, n = this.fields.size(); i < n; i++) {
/* 461 */       ((FieldNode)this.fields.get(i)).accept(classVisitor);
/*     */     }
/*     */     
/* 464 */     for (i = 0, n = this.methods.size(); i < n; i++) {
/* 465 */       ((MethodNode)this.methods.get(i)).accept(classVisitor);
/*     */     }
/* 467 */     classVisitor.visitEnd();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\objectweb\asm\tree\ClassNode.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */