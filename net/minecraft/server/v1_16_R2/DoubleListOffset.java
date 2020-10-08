/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.doubles.AbstractDoubleList;
/*    */ import it.unimi.dsi.fastutil.doubles.DoubleList;
/*    */ 
/*    */ public class DoubleListOffset extends AbstractDoubleList {
/*    */   private final DoubleList a;
/*    */   private final double b;
/*    */   
/*    */   public DoubleListOffset(DoubleList var0, double var1) {
/* 11 */     this.a = var0;
/* 12 */     this.b = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getDouble(int var0) {
/* 17 */     return this.a.getDouble(var0) + this.b;
/*    */   }
/*    */ 
/*    */   
/*    */   public int size() {
/* 22 */     return this.a.size();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DoubleListOffset.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */