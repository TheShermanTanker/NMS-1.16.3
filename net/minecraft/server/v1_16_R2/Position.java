/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public class Position implements IPosition {
/*    */   protected final double a;
/*    */   protected final double b;
/*    */   protected final double c;
/*    */   
/*    */   public Position(double var0, double var2, double var4) {
/*  9 */     this.a = var0;
/* 10 */     this.b = var2;
/* 11 */     this.c = var4;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getX() {
/* 16 */     return this.a;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getY() {
/* 21 */     return this.b;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getZ() {
/* 26 */     return this.c;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\Position.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */