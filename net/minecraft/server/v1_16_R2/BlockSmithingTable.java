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
/*    */ public class BlockSmithingTable
/*    */   extends BlockWorkbench
/*    */ {
/*    */   protected BlockSmithingTable(BlockBase.Info var0) {
/* 20 */     super(var0);
/*    */   }
/*    */   
/* 23 */   private static final IChatBaseComponent a = new ChatMessage("container.upgrade");
/*    */ 
/*    */   
/*    */   public ITileInventory getInventory(IBlockData var0, World var1, BlockPosition var2) {
/* 27 */     return new TileInventory((var2, var3, var4) -> new ContainerSmithing(var2, var3, ContainerAccess.at(var0, var1)), a);
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumInteractionResult interact(IBlockData var0, World var1, BlockPosition var2, EntityHuman var3, EnumHand var4, MovingObjectPositionBlock var5) {
/* 32 */     if (var1.isClientSide) {
/* 33 */       return EnumInteractionResult.SUCCESS;
/*    */     }
/*    */     
/* 36 */     var3.openContainer(var0.b(var1, var2));
/* 37 */     var3.a(StatisticList.INTERACT_WITH_SMITHING_TABLE);
/* 38 */     return EnumInteractionResult.CONSUME;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockSmithingTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */