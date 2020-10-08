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
/*    */ public class ModuleProvideNode
/*    */ {
/*    */   public String service;
/*    */   public List<String> providers;
/*    */   
/*    */   public ModuleProvideNode(String service, List<String> providers) {
/* 54 */     this.service = service;
/* 55 */     this.providers = providers;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void accept(ModuleVisitor moduleVisitor) {
/* 64 */     moduleVisitor.visitProvide(this.service, this.providers.<String>toArray(new String[0]));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\objectweb\asm\tree\ModuleProvideNode.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */