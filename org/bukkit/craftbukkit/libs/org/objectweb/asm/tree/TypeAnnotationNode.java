/*    */ package org.bukkit.craftbukkit.libs.org.objectweb.asm.tree;
/*    */ 
/*    */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.TypePath;
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
/*    */ public class TypeAnnotationNode
/*    */   extends AnnotationNode
/*    */ {
/*    */   public int typeRef;
/*    */   public TypePath typePath;
/*    */   
/*    */   public TypeAnnotationNode(int typeRef, TypePath typePath, String descriptor) {
/* 62 */     this(524288, typeRef, typePath, descriptor);
/* 63 */     if (getClass() != TypeAnnotationNode.class) {
/* 64 */       throw new IllegalStateException();
/*    */     }
/*    */   }
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
/*    */   public TypeAnnotationNode(int api, int typeRef, TypePath typePath, String descriptor) {
/* 82 */     super(api, descriptor);
/* 83 */     this.typeRef = typeRef;
/* 84 */     this.typePath = typePath;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\objectweb\asm\tree\TypeAnnotationNode.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */