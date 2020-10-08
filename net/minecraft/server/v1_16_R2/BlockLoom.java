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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockLoom
/*    */   extends BlockFacingHorizontal
/*    */ {
/* 21 */   private static final IChatBaseComponent a = new ChatMessage("container.loom");
/*    */   
/*    */   protected BlockLoom(BlockBase.Info var0) {
/* 24 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumInteractionResult interact(IBlockData var0, World var1, BlockPosition var2, EntityHuman var3, EnumHand var4, MovingObjectPositionBlock var5) {
/* 29 */     if (var1.isClientSide) {
/* 30 */       return EnumInteractionResult.SUCCESS;
/*    */     }
/*    */     
/* 33 */     var3.openContainer(var0.b(var1, var2));
/* 34 */     var3.a(StatisticList.INTERACT_WITH_LOOM);
/* 35 */     return EnumInteractionResult.CONSUME;
/*    */   }
/*    */ 
/*    */   
/*    */   public ITileInventory getInventory(IBlockData var0, World var1, BlockPosition var2) {
/* 40 */     return new TileInventory((var2, var3, var4) -> new ContainerLoom(var2, var3, ContainerAccess.at(var0, var1)), a);
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData getPlacedState(BlockActionContext var0) {
/* 45 */     return getBlockData().set(FACING, var0.f().opposite());
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(BlockStateList.a<Block, IBlockData> var0) {
/* 50 */     var0.a((IBlockState<?>[])new IBlockState[] { FACING });
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockLoom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */