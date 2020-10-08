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
/*    */ public class BlockWetSponge
/*    */   extends Block
/*    */ {
/*    */   protected BlockWetSponge(BlockBase.Info var0) {
/* 16 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onPlace(IBlockData var0, World var1, BlockPosition var2, IBlockData var3, boolean var4) {
/* 21 */     if (var1.getDimensionManager().isNether()) {
/* 22 */       var1.setTypeAndData(var2, Blocks.SPONGE.getBlockData(), 3);
/* 23 */       var1.triggerEffect(2009, var2, 0);
/* 24 */       var1.playSound((EntityHuman)null, var2, SoundEffects.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 1.0F, (1.0F + var1.getRandom().nextFloat() * 0.2F) * 0.7F);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockWetSponge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */