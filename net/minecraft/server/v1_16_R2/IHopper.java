/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ public interface IHopper
/*    */   extends IInventory
/*    */ {
/*  7 */   public static final VoxelShape a = Block.a(2.0D, 11.0D, 2.0D, 14.0D, 16.0D, 14.0D);
/*  8 */   public static final VoxelShape b = Block.a(0.0D, 16.0D, 0.0D, 16.0D, 32.0D, 16.0D);
/*  9 */   public static final VoxelShape c = VoxelShapes.a(a, b);
/*    */   
/*    */   default VoxelShape aa_() {
/* 12 */     return c;
/*    */   }
/*    */   World getWorld();
/*    */   double x();
/*    */   default BlockPosition getBlockPosition() {
/* 17 */     return new BlockPosition(getX(), getY(), getZ());
/*    */   } default double getX() {
/* 19 */     return x();
/*    */   } double z(); default double getY() {
/* 21 */     return z();
/*    */   } double A(); default double getZ() {
/* 23 */     return A();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\IHopper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */