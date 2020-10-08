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
/*    */ public class BlockWorkbench
/*    */   extends Block
/*    */ {
/* 19 */   private static final IChatBaseComponent a = new ChatMessage("container.crafting");
/*    */   
/*    */   protected BlockWorkbench(BlockBase.Info var0) {
/* 22 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumInteractionResult interact(IBlockData var0, World var1, BlockPosition var2, EntityHuman var3, EnumHand var4, MovingObjectPositionBlock var5) {
/* 27 */     if (var1.isClientSide) {
/* 28 */       return EnumInteractionResult.SUCCESS;
/*    */     }
/*    */     
/* 31 */     var3.openContainer(var0.b(var1, var2));
/* 32 */     var3.a(StatisticList.INTERACT_WITH_CRAFTING_TABLE);
/* 33 */     return EnumInteractionResult.CONSUME;
/*    */   }
/*    */ 
/*    */   
/*    */   public ITileInventory getInventory(IBlockData var0, World var1, BlockPosition var2) {
/* 38 */     return new TileInventory((var2, var3, var4) -> new ContainerWorkbench(var2, var3, ContainerAccess.at(var0, var1)), a);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockWorkbench.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */