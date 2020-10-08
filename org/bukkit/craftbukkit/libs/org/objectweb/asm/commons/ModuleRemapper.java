/*     */ package org.bukkit.craftbukkit.libs.org.objectweb.asm.commons;
/*     */ 
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.ModuleVisitor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ModuleRemapper
/*     */   extends ModuleVisitor
/*     */ {
/*     */   protected final Remapper remapper;
/*     */   
/*     */   public ModuleRemapper(ModuleVisitor moduleVisitor, Remapper remapper) {
/*  52 */     this(524288, moduleVisitor, remapper);
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
/*     */   protected ModuleRemapper(int api, ModuleVisitor moduleVisitor, Remapper remapper) {
/*  67 */     super(api, moduleVisitor);
/*  68 */     this.remapper = remapper;
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitMainClass(String mainClass) {
/*  73 */     super.visitMainClass(this.remapper.mapType(mainClass));
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitPackage(String packaze) {
/*  78 */     super.visitPackage(this.remapper.mapPackageName(packaze));
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitRequire(String module, int access, String version) {
/*  83 */     super.visitRequire(this.remapper.mapModuleName(module), access, version);
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitExport(String packaze, int access, String... modules) {
/*  88 */     String[] remappedModules = null;
/*  89 */     if (modules != null) {
/*  90 */       remappedModules = new String[modules.length];
/*  91 */       for (int i = 0; i < modules.length; i++) {
/*  92 */         remappedModules[i] = this.remapper.mapModuleName(modules[i]);
/*     */       }
/*     */     } 
/*  95 */     super.visitExport(this.remapper.mapPackageName(packaze), access, remappedModules);
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitOpen(String packaze, int access, String... modules) {
/* 100 */     String[] remappedModules = null;
/* 101 */     if (modules != null) {
/* 102 */       remappedModules = new String[modules.length];
/* 103 */       for (int i = 0; i < modules.length; i++) {
/* 104 */         remappedModules[i] = this.remapper.mapModuleName(modules[i]);
/*     */       }
/*     */     } 
/* 107 */     super.visitOpen(this.remapper.mapPackageName(packaze), access, remappedModules);
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitUse(String service) {
/* 112 */     super.visitUse(this.remapper.mapType(service));
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitProvide(String service, String... providers) {
/* 117 */     String[] remappedProviders = new String[providers.length];
/* 118 */     for (int i = 0; i < providers.length; i++) {
/* 119 */       remappedProviders[i] = this.remapper.mapType(providers[i]);
/*     */     }
/* 121 */     super.visitProvide(this.remapper.mapType(service), remappedProviders);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\objectweb\asm\commons\ModuleRemapper.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */