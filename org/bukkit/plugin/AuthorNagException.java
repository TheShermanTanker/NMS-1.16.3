/*    */ package org.bukkit.plugin;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AuthorNagException
/*    */   extends RuntimeException
/*    */ {
/*    */   private final String message;
/*    */   
/*    */   public AuthorNagException(String message) {
/* 13 */     this.message = message;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getMessage() {
/* 18 */     return this.message;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\plugin\AuthorNagException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */