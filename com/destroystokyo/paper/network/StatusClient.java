/*    */ package com.destroystokyo.paper.network;
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
/*    */ public interface StatusClient
/*    */   extends NetworkClient
/*    */ {
/*    */   default boolean isLegacy() {
/* 22 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\network\StatusClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */