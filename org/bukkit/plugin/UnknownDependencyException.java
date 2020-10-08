/*    */ package org.bukkit.plugin;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class UnknownDependencyException
/*    */   extends RuntimeException
/*    */ {
/*    */   private static final long serialVersionUID = 5721389371901775895L;
/*    */   
/*    */   public UnknownDependencyException(Throwable throwable) {
/* 17 */     super(throwable);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public UnknownDependencyException(String message) {
/* 26 */     super(message);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public UnknownDependencyException(Throwable throwable, String message) {
/* 37 */     super(message, throwable);
/*    */   }
/*    */   
/*    */   public UnknownDependencyException() {}
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\plugin\UnknownDependencyException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */