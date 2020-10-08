/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.doubles.AbstractDoubleList;
/*    */ 
/*    */ public class VoxelShapeCubePoint extends AbstractDoubleList {
/*    */   private final int a;
/*    */   
/*    */   VoxelShapeCubePoint(int var0) {
/*  9 */     this.a = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getDouble(int var0) {
/* 14 */     return var0 / this.a;
/*    */   }
/*    */ 
/*    */   
/*    */   public int size() {
/* 19 */     return this.a + 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\VoxelShapeCubePoint.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */