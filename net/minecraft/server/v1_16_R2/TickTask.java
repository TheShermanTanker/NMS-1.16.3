/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public class TickTask implements Runnable {
/*    */   private final int a;
/*    */   private final Runnable b;
/*    */   
/*    */   public TickTask(int var0, Runnable var1) {
/*  8 */     this.a = var0;
/*  9 */     this.b = var1;
/*    */   }
/*    */   
/*    */   public int a() {
/* 13 */     return this.a;
/*    */   }
/*    */ 
/*    */   
/*    */   public void run() {
/* 18 */     this.b.run();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\TickTask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */