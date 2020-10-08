/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ public class BlockOre
/*    */   extends Block {
/*    */   public BlockOre(BlockBase.Info blockbase_info) {
/*  8 */     super(blockbase_info);
/*    */   }
/*    */   
/*    */   protected int a(Random random) {
/* 12 */     return (this == Blocks.COAL_ORE) ? MathHelper.nextInt(random, 0, 2) : ((this == Blocks.DIAMOND_ORE) ? MathHelper.nextInt(random, 3, 7) : ((this == Blocks.EMERALD_ORE) ? MathHelper.nextInt(random, 3, 7) : ((this == Blocks.LAPIS_ORE) ? MathHelper.nextInt(random, 2, 5) : ((this == Blocks.NETHER_QUARTZ_ORE) ? MathHelper.nextInt(random, 2, 5) : ((this == Blocks.NETHER_GOLD_ORE) ? MathHelper.nextInt(random, 0, 1) : 0)))));
/*    */   }
/*    */ 
/*    */   
/*    */   public void dropNaturally(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, ItemStack itemstack) {
/* 17 */     super.dropNaturally(iblockdata, worldserver, blockposition, itemstack);
/*    */   }
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
/*    */   public int getExpDrop(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, ItemStack itemstack) {
/* 32 */     if (EnchantmentManager.getEnchantmentLevel(Enchantments.SILK_TOUCH, itemstack) == 0) {
/* 33 */       int i = a(worldserver.random);
/*    */       
/* 35 */       if (i > 0) {
/* 36 */         return i;
/*    */       }
/*    */     } 
/*    */     
/* 40 */     return 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockOre.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */