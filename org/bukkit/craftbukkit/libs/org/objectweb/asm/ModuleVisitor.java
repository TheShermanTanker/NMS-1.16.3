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
/*     */ public abstract class ModuleVisitor
/*     */ {
/*     */   protected final int api;
/*     */   protected ModuleVisitor mv;
/*     */   
/*     */   public ModuleVisitor(int api) {
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
/*     */   public ModuleVisitor(int api, ModuleVisitor moduleVisitor) {
/*  69 */     if (api != 524288 && api != 458752 && api != 393216 && api != 327680 && api != 262144 && api != 17367040)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  75 */       throw new IllegalArgumentException("Unsupported api " + api);
/*     */     }
/*  77 */     if (api == 17367040) {
/*  78 */       Constants.checkAsmExperimental(this);
/*     */     }
/*  80 */     this.api = api;
/*  81 */     this.mv = moduleVisitor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitMainClass(String mainClass) {
/*  90 */     if (this.mv != null) {
/*  91 */       this.mv.visitMainClass(mainClass);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitPackage(String packaze) {
/* 101 */     if (this.mv != null) {
/* 102 */       this.mv.visitPackage(packaze);
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
/*     */   public void visitRequire(String module, int access, String version) {
/* 115 */     if (this.mv != null) {
/* 116 */       this.mv.visitRequire(module, access, version);
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
/*     */   public void visitExport(String packaze, int access, String... modules) {
/* 130 */     if (this.mv != null) {
/* 131 */       this.mv.visitExport(packaze, access, modules);
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
/*     */   public void visitOpen(String packaze, int access, String... modules) {
/* 145 */     if (this.mv != null) {
/* 146 */       this.mv.visitOpen(packaze, access, modules);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitUse(String service) {
/* 157 */     if (this.mv != null) {
/* 158 */       this.mv.visitUse(service);
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
/*     */   public void visitProvide(String service, String... providers) {
/* 170 */     if (this.mv != null) {
/* 171 */       this.mv.visitProvide(service, providers);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitEnd() {
/* 180 */     if (this.mv != null)
/* 181 */       this.mv.visitEnd(); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\objectweb\asm\ModuleVisitor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */