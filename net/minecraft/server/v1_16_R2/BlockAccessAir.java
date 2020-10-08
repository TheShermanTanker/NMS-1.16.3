/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ public enum BlockAccessAir
/*    */   implements IBlockAccess {
/*  7 */   INSTANCE;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public TileEntity getTileEntity(BlockPosition blockposition) {
/* 14 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Fluid getFluidIfLoaded(BlockPosition blockposition) {
/* 20 */     return getFluid(blockposition);
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData getTypeIfLoaded(BlockPosition blockposition) {
/* 25 */     return getType(blockposition);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IBlockData getType(BlockPosition blockposition) {
/* 31 */     return Blocks.AIR.getBlockData();
/*    */   }
/*    */ 
/*    */   
/*    */   public Fluid getFluid(BlockPosition blockposition) {
/* 36 */     return FluidTypes.EMPTY.h();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockAccessAir.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */