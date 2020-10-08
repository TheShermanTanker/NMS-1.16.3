/*     */ package org.bukkit.craftbukkit.libs.org.objectweb.asm.tree;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.ClassVisitor;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ModuleNode
/*     */   extends ModuleVisitor
/*     */ {
/*     */   public String name;
/*     */   public int access;
/*     */   public String version;
/*     */   public String mainClass;
/*     */   public List<String> packages;
/*     */   public List<ModuleRequireNode> requires;
/*     */   public List<ModuleExportNode> exports;
/*     */   public List<ModuleOpenNode> opens;
/*     */   public List<String> uses;
/*     */   public List<ModuleProvideNode> provides;
/*     */   
/*     */   public ModuleNode(String name, int access, String version) {
/*  87 */     super(524288);
/*  88 */     if (getClass() != ModuleNode.class) {
/*  89 */       throw new IllegalStateException();
/*     */     }
/*  91 */     this.name = name;
/*  92 */     this.access = access;
/*  93 */     this.version = version;
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
/*     */ 
/*     */   
/*     */   public ModuleNode(int api, String name, int access, String version, List<ModuleRequireNode> requires, List<ModuleExportNode> exports, List<ModuleOpenNode> opens, List<String> uses, List<ModuleProvideNode> provides) {
/* 122 */     super(api);
/* 123 */     this.name = name;
/* 124 */     this.access = access;
/* 125 */     this.version = version;
/* 126 */     this.requires = requires;
/* 127 */     this.exports = exports;
/* 128 */     this.opens = opens;
/* 129 */     this.uses = uses;
/* 130 */     this.provides = provides;
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitMainClass(String mainClass) {
/* 135 */     this.mainClass = mainClass;
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitPackage(String packaze) {
/* 140 */     if (this.packages == null) {
/* 141 */       this.packages = new ArrayList<String>(5);
/*     */     }
/* 143 */     this.packages.add(packaze);
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitRequire(String module, int access, String version) {
/* 148 */     if (this.requires == null) {
/* 149 */       this.requires = new ArrayList<ModuleRequireNode>(5);
/*     */     }
/* 151 */     this.requires.add(new ModuleRequireNode(module, access, version));
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitExport(String packaze, int access, String... modules) {
/* 156 */     if (this.exports == null) {
/* 157 */       this.exports = new ArrayList<ModuleExportNode>(5);
/*     */     }
/* 159 */     this.exports.add(new ModuleExportNode(packaze, access, Util.asArrayList(modules)));
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitOpen(String packaze, int access, String... modules) {
/* 164 */     if (this.opens == null) {
/* 165 */       this.opens = new ArrayList<ModuleOpenNode>(5);
/*     */     }
/* 167 */     this.opens.add(new ModuleOpenNode(packaze, access, Util.asArrayList(modules)));
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitUse(String service) {
/* 172 */     if (this.uses == null) {
/* 173 */       this.uses = new ArrayList<String>(5);
/*     */     }
/* 175 */     this.uses.add(service);
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitProvide(String service, String... providers) {
/* 180 */     if (this.provides == null) {
/* 181 */       this.provides = new ArrayList<ModuleProvideNode>(5);
/*     */     }
/* 183 */     this.provides.add(new ModuleProvideNode(service, Util.asArrayList(providers)));
/*     */   }
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
/*     */   public void accept(ClassVisitor classVisitor) {
/* 197 */     ModuleVisitor moduleVisitor = classVisitor.visitModule(this.name, this.access, this.version);
/* 198 */     if (moduleVisitor == null) {
/*     */       return;
/*     */     }
/* 201 */     if (this.mainClass != null) {
/* 202 */       moduleVisitor.visitMainClass(this.mainClass);
/*     */     }
/* 204 */     if (this.packages != null) {
/* 205 */       for (int i = 0, n = this.packages.size(); i < n; i++) {
/* 206 */         moduleVisitor.visitPackage(this.packages.get(i));
/*     */       }
/*     */     }
/* 209 */     if (this.requires != null) {
/* 210 */       for (int i = 0, n = this.requires.size(); i < n; i++) {
/* 211 */         ((ModuleRequireNode)this.requires.get(i)).accept(moduleVisitor);
/*     */       }
/*     */     }
/* 214 */     if (this.exports != null) {
/* 215 */       for (int i = 0, n = this.exports.size(); i < n; i++) {
/* 216 */         ((ModuleExportNode)this.exports.get(i)).accept(moduleVisitor);
/*     */       }
/*     */     }
/* 219 */     if (this.opens != null) {
/* 220 */       for (int i = 0, n = this.opens.size(); i < n; i++) {
/* 221 */         ((ModuleOpenNode)this.opens.get(i)).accept(moduleVisitor);
/*     */       }
/*     */     }
/* 224 */     if (this.uses != null) {
/* 225 */       for (int i = 0, n = this.uses.size(); i < n; i++) {
/* 226 */         moduleVisitor.visitUse(this.uses.get(i));
/*     */       }
/*     */     }
/* 229 */     if (this.provides != null)
/* 230 */       for (int i = 0, n = this.provides.size(); i < n; i++)
/* 231 */         ((ModuleProvideNode)this.provides.get(i)).accept(moduleVisitor);  
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\objectweb\asm\tree\ModuleNode.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */