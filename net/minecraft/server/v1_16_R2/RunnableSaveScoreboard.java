/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public class RunnableSaveScoreboard implements Runnable {
/*    */   private final PersistentBase a;
/*    */   
/*    */   public RunnableSaveScoreboard(PersistentBase var0) {
/*  7 */     this.a = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void run() {
/* 12 */     this.a.b();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\RunnableSaveScoreboard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */