/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Map;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemBlockWallable
/*    */   extends ItemBlock
/*    */ {
/*    */   public final Block wallBlock;
/*    */   
/*    */   public ItemBlockWallable(Block var0, Block var1, Item.Info var2) {
/* 18 */     super(var0, var2);
/* 19 */     this.wallBlock = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   protected IBlockData c(BlockActionContext var0) {
/* 25 */     IBlockData var1 = this.wallBlock.getPlacedState(var0);
/*    */     
/* 27 */     IBlockData var2 = null;
/*    */     
/* 29 */     IWorldReader var3 = var0.getWorld();
/* 30 */     BlockPosition var4 = var0.getClickPosition();
/* 31 */     for (EnumDirection var8 : var0.e()) {
/* 32 */       if (var8 != EnumDirection.UP) {
/*    */ 
/*    */ 
/*    */         
/* 36 */         IBlockData var9 = (var8 == EnumDirection.DOWN) ? getBlock().getPlacedState(var0) : var1;
/* 37 */         if (var9 != null && var9.canPlace(var3, var4)) {
/* 38 */           var2 = var9;
/*    */           break;
/*    */         } 
/*    */       } 
/*    */     } 
/* 43 */     return (var2 != null && var3.a(var2, var4, VoxelShapeCollision.a())) ? var2 : null;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(Map<Block, Item> var0, Item var1) {
/* 48 */     super.a(var0, var1);
/*    */     
/* 50 */     var0.put(this.wallBlock, var1);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemBlockWallable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */