/*    */ package net.minecraft.server.v1_16_R2;
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
/*    */ public class FluidTypeEmpty
/*    */   extends FluidType
/*    */ {
/*    */   public Item a() {
/* 19 */     return Items.AIR;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(Fluid var0, IBlockAccess var1, BlockPosition var2, FluidType var3, EnumDirection var4) {
/* 24 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public Vec3D a(IBlockAccess var0, BlockPosition var1, Fluid var2) {
/* 29 */     return Vec3D.ORIGIN;
/*    */   }
/*    */ 
/*    */   
/*    */   public int a(IWorldReader var0) {
/* 34 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean b() {
/* 39 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   protected float c() {
/* 44 */     return 0.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public float a(Fluid var0, IBlockAccess var1, BlockPosition var2) {
/* 49 */     return 0.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public float a(Fluid var0) {
/* 54 */     return 0.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   protected IBlockData b(Fluid var0) {
/* 59 */     return Blocks.AIR.getBlockData();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean c(Fluid var0) {
/* 64 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public int d(Fluid var0) {
/* 69 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public VoxelShape b(Fluid var0, IBlockAccess var1, BlockPosition var2) {
/* 74 */     return VoxelShapes.a();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\FluidTypeEmpty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */