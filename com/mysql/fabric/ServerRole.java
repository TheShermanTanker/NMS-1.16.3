/*    */ package com.mysql.fabric;
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
/*    */ public enum ServerRole
/*    */ {
/* 30 */   FAULTY, SPARE, SECONDARY, PRIMARY, CONFIGURING;
/*    */   
/*    */   public static ServerRole getFromConstant(Integer constant) {
/* 33 */     return values()[constant.intValue()];
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\fabric\ServerRole.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */