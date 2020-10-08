/*    */ package org.bukkit.configuration;
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
/*    */ public class InvalidConfigurationException
/*    */   extends Exception
/*    */ {
/*    */   public InvalidConfigurationException() {}
/*    */   
/*    */   public InvalidConfigurationException(String msg) {
/* 22 */     super(msg);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public InvalidConfigurationException(Throwable cause) {
/* 32 */     super(cause);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public InvalidConfigurationException(String msg, Throwable cause) {
/* 43 */     super(msg, cause);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\configuration\InvalidConfigurationException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */