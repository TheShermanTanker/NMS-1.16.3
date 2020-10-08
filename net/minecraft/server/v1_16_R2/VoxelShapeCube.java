/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.doubles.DoubleList;
/*    */ 
/*    */ public final class VoxelShapeCube
/*    */   extends VoxelShape
/*    */ {
/*    */   protected VoxelShapeCube(VoxelShapeDiscrete var0) {
/*  9 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   protected DoubleList a(EnumDirection.EnumAxis var0) {
/* 14 */     return (DoubleList)new VoxelShapeCubePoint(this.a.c(var0));
/*    */   }
/*    */ 
/*    */   
/*    */   protected int a(EnumDirection.EnumAxis var0, double var1) {
/* 19 */     int var3 = this.a.c(var0);
/* 20 */     return MathHelper.clamp(MathHelper.floor(var1 * var3), -1, var3);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\VoxelShapeCube.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */