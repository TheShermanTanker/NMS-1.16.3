/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public interface IWorldBorderListener {
/*    */   void a(WorldBorder paramWorldBorder, double paramDouble);
/*    */   
/*    */   void a(WorldBorder paramWorldBorder, double paramDouble1, double paramDouble2, long paramLong);
/*    */   
/*    */   void a(WorldBorder paramWorldBorder, double paramDouble1, double paramDouble2);
/*    */   
/*    */   void a(WorldBorder paramWorldBorder, int paramInt);
/*    */   
/*    */   void b(WorldBorder paramWorldBorder, int paramInt);
/*    */   
/*    */   void b(WorldBorder paramWorldBorder, double paramDouble);
/*    */   
/*    */   void c(WorldBorder paramWorldBorder, double paramDouble);
/*    */   
/*    */   public static class a implements IWorldBorderListener {
/*    */     private final WorldBorder a;
/*    */     
/*    */     public a(WorldBorder var0) {
/* 22 */       this.a = var0;
/*    */     }
/*    */ 
/*    */     
/*    */     public void a(WorldBorder var0, double var1) {
/* 27 */       this.a.setSize(var1);
/*    */     }
/*    */ 
/*    */     
/*    */     public void a(WorldBorder var0, double var1, double var3, long var5) {
/* 32 */       this.a.transitionSizeBetween(var1, var3, var5);
/*    */     }
/*    */ 
/*    */     
/*    */     public void a(WorldBorder var0, double var1, double var3) {
/* 37 */       this.a.setCenter(var1, var3);
/*    */     }
/*    */ 
/*    */     
/*    */     public void a(WorldBorder var0, int var1) {
/* 42 */       this.a.setWarningTime(var1);
/*    */     }
/*    */ 
/*    */     
/*    */     public void b(WorldBorder var0, int var1) {
/* 47 */       this.a.setWarningDistance(var1);
/*    */     }
/*    */ 
/*    */     
/*    */     public void b(WorldBorder var0, double var1) {
/* 52 */       this.a.setDamageAmount(var1);
/*    */     }
/*    */ 
/*    */     
/*    */     public void c(WorldBorder var0, double var1) {
/* 57 */       this.a.setDamageBuffer(var1);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\IWorldBorderListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */