/*    */ package com.mysql.fabric;
/*    */ 
/*    */ import java.util.concurrent.TimeUnit;
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
/*    */ public class FabricStateResponse<T>
/*    */ {
/*    */   private T data;
/*    */   private int secsTtl;
/*    */   private long expireTimeMillis;
/*    */   
/*    */   public FabricStateResponse(T data, int secsTtl) {
/* 34 */     this.data = data;
/* 35 */     this.secsTtl = secsTtl;
/* 36 */     this.expireTimeMillis = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(secsTtl);
/*    */   }
/*    */   
/*    */   public FabricStateResponse(T data, int secsTtl, long presetExpireTimeMillis) {
/* 40 */     this.data = data;
/* 41 */     this.secsTtl = secsTtl;
/* 42 */     this.expireTimeMillis = presetExpireTimeMillis;
/*    */   }
/*    */   
/*    */   public T getData() {
/* 46 */     return this.data;
/*    */   }
/*    */   
/*    */   public int getTtl() {
/* 50 */     return this.secsTtl;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public long getExpireTimeMillis() {
/* 57 */     return this.expireTimeMillis;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\fabric\FabricStateResponse.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */