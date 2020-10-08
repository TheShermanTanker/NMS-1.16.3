/*    */ package org.bukkit.craftbukkit.libs.org.objectweb.asm.tree;
/*    */ 
/*    */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.ModuleVisitor;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ModuleRequireNode
/*    */ {
/*    */   public String module;
/*    */   public int access;
/*    */   public String version;
/*    */   
/*    */   public ModuleRequireNode(String module, int access, String version) {
/* 60 */     this.module = module;
/* 61 */     this.access = access;
/* 62 */     this.version = version;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void accept(ModuleVisitor moduleVisitor) {
/* 71 */     moduleVisitor.visitRequire(this.module, this.access, this.version);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\objectweb\asm\tree\ModuleRequireNode.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */