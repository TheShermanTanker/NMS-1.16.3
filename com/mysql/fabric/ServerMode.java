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
/*    */ 
/*    */ public enum ServerMode
/*    */ {
/* 31 */   OFFLINE, READ_ONLY, WRITE_ONLY, READ_WRITE;
/*    */   
/*    */   public static ServerMode getFromConstant(Integer constant) {
/* 34 */     return values()[constant.intValue()];
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\fabric\ServerMode.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */