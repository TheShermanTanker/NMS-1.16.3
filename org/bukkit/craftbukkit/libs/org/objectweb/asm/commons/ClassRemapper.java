/*     */ package org.bukkit.craftbukkit.libs.org.objectweb.asm.commons;
/*     */ 
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
/*     */ public class ClassRemapper
/*     */   extends ClassVisitor
/*     */ {
/*     */   protected final Remapper remapper;
/*     */   protected String className;
/*     */   
/*     */   public ClassRemapper(ClassVisitor classVisitor, Remapper remapper) {
/*  77 */     this(524288, classVisitor, remapper);
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
/*     */   protected ClassRemapper(int api, ClassVisitor classVisitor, Remapper remapper) {
/*  91 */     super(api, classVisitor);
/*  92 */     this.remapper = remapper;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
/* 103 */     this.className = name;
/* 104 */     super.visit(version, access, this.remapper
/*     */ 
/*     */         
/* 107 */         .mapType(name), this.remapper
/* 108 */         .mapSignature(signature, false), this.remapper
/* 109 */         .mapType(superName), (interfaces == null) ? null : this.remapper
/* 110 */         .mapTypes(interfaces));
/*     */   }
/*     */ 
/*     */   
/*     */   public ModuleVisitor visitModule(String name, int flags, String version) {
/* 115 */     ModuleVisitor moduleVisitor = super.visitModule(this.remapper.mapModuleName(name), flags, version);
/* 116 */     return (moduleVisitor == null) ? null : createModuleRemapper(moduleVisitor);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
/* 122 */     AnnotationVisitor annotationVisitor = super.visitAnnotation(this.remapper.mapDesc(descriptor), visible);
/* 123 */     return (annotationVisitor == null) ? null : createAnnotationRemapper(annotationVisitor);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, String descriptor, boolean visible) {
/* 130 */     AnnotationVisitor annotationVisitor = super.visitTypeAnnotation(typeRef, typePath, this.remapper.mapDesc(descriptor), visible);
/* 131 */     return (annotationVisitor == null) ? null : createAnnotationRemapper(annotationVisitor);
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitAttribute(Attribute attribute) {
/* 136 */     if (attribute instanceof ModuleHashesAttribute) {
/* 137 */       ModuleHashesAttribute moduleHashesAttribute = (ModuleHashesAttribute)attribute;
/* 138 */       List<String> modules = moduleHashesAttribute.modules;
/* 139 */       for (int i = 0; i < modules.size(); i++) {
/* 140 */         modules.set(i, this.remapper.mapModuleName(modules.get(i)));
/*     */       }
/*     */     } 
/* 143 */     super.visitAttribute(attribute);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RecordComponentVisitor visitRecordComponent(String name, String descriptor, String signature) {
/* 150 */     RecordComponentVisitor recordComponentVisitor = super.visitRecordComponent(this.remapper
/* 151 */         .mapRecordComponentName(this.className, name, descriptor), this.remapper
/* 152 */         .mapDesc(descriptor), this.remapper
/* 153 */         .mapSignature(signature, true));
/* 154 */     return (recordComponentVisitor == null) ? null : 
/*     */       
/* 156 */       createRecordComponentRemapper(recordComponentVisitor);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
/* 167 */     FieldVisitor fieldVisitor = super.visitField(access, this.remapper
/*     */         
/* 169 */         .mapFieldName(this.className, name, descriptor), this.remapper
/* 170 */         .mapDesc(descriptor), this.remapper
/* 171 */         .mapSignature(signature, true), (value == null) ? null : this.remapper
/* 172 */         .mapValue(value));
/* 173 */     return (fieldVisitor == null) ? null : createFieldRemapper(fieldVisitor);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
/* 183 */     String remappedDescriptor = this.remapper.mapMethodDesc(descriptor);
/*     */     
/* 185 */     MethodVisitor methodVisitor = super.visitMethod(access, this.remapper
/*     */         
/* 187 */         .mapMethodName(this.className, name, descriptor), remappedDescriptor, this.remapper
/*     */         
/* 189 */         .mapSignature(signature, false), (exceptions == null) ? null : this.remapper
/* 190 */         .mapTypes(exceptions));
/* 191 */     return (methodVisitor == null) ? null : createMethodRemapper(methodVisitor);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitInnerClass(String name, String outerName, String innerName, int access) {
/* 197 */     super.visitInnerClass(this.remapper
/* 198 */         .mapType(name), (outerName == null) ? null : this.remapper
/* 199 */         .mapType(outerName), (innerName == null) ? null : this.remapper
/* 200 */         .mapInnerClassName(name, outerName, innerName), access);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitOuterClass(String owner, String name, String descriptor) {
/* 206 */     super.visitOuterClass(this.remapper
/* 207 */         .mapType(owner), (name == null) ? null : this.remapper
/* 208 */         .mapMethodName(owner, name, descriptor), (descriptor == null) ? null : this.remapper
/* 209 */         .mapMethodDesc(descriptor));
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitNestHost(String nestHost) {
/* 214 */     super.visitNestHost(this.remapper.mapType(nestHost));
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitNestMember(String nestMember) {
/* 219 */     super.visitNestMember(this.remapper.mapType(nestMember));
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
/* 231 */     super.visitPermittedSubtypeExperimental(this.remapper.mapType(permittedSubtype));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected FieldVisitor createFieldRemapper(FieldVisitor fieldVisitor) {
/* 242 */     return new FieldRemapper(this.api, fieldVisitor, this.remapper);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected MethodVisitor createMethodRemapper(MethodVisitor methodVisitor) {
/* 253 */     return new MethodRemapper(this.api, methodVisitor, this.remapper);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AnnotationVisitor createAnnotationRemapper(AnnotationVisitor annotationVisitor) {
/* 264 */     return new AnnotationRemapper(this.api, annotationVisitor, this.remapper);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ModuleVisitor createModuleRemapper(ModuleVisitor moduleVisitor) {
/* 275 */     return new ModuleRemapper(this.api, moduleVisitor, this.remapper);
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
/*     */   protected RecordComponentVisitor createRecordComponentRemapper(RecordComponentVisitor recordComponentVisitor) {
/* 287 */     return new RecordComponentRemapper(this.api, recordComponentVisitor, this.remapper);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\objectweb\asm\commons\ClassRemapper.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */