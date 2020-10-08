/*    */ package org.bukkit.craftbukkit.libs.org.objectweb.asm.tree;
/*    */ 
/*    */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.ClassVisitor;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class InnerClassNode
/*    */ {
/*    */   public String name;
/*    */   public String outerName;
/*    */   public String innerName;
/*    */   public int access;
/*    */   
/*    */   public InnerClassNode(String name, String outerName, String innerName, int access) {
/* 71 */     this.name = name;
/* 72 */     this.outerName = outerName;
/* 73 */     this.innerName = innerName;
/* 74 */     this.access = access;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void accept(ClassVisitor classVisitor) {
/* 83 */     classVisitor.visitInnerClass(this.name, this.outerName, this.innerName, this.access);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\objectweb\asm\tree\InnerClassNode.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */