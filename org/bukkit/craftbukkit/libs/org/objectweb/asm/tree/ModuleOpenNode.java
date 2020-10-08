/*    */ package org.bukkit.craftbukkit.libs.org.objectweb.asm.tree;
/*    */ 
/*    */ import java.util.List;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ModuleOpenNode
/*    */ {
/*    */   public String packaze;
/*    */   public int access;
/*    */   public List<String> modules;
/*    */   
/*    */   public ModuleOpenNode(String packaze, int access, List<String> modules) {
/* 65 */     this.packaze = packaze;
/* 66 */     this.access = access;
/* 67 */     this.modules = modules;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void accept(ModuleVisitor moduleVisitor) {
/* 76 */     moduleVisitor.visitOpen(this.packaze, this.access, (this.modules == null) ? null : this.modules
/* 77 */         .<String>toArray(new String[0]));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\objectweb\asm\tree\ModuleOpenNode.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */