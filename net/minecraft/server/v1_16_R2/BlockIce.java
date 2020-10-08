/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Random;
/*    */ import javax.annotation.Nullable;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*    */ 
/*    */ public class BlockIce extends BlockHalfTransparent {
/*    */   public BlockIce(BlockBase.Info blockbase_info) {
/*  9 */     super(blockbase_info);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(World world, EntityHuman entityhuman, BlockPosition blockposition, IBlockData iblockdata, @Nullable TileEntity tileentity, ItemStack itemstack) {
/* 14 */     super.a(world, entityhuman, blockposition, iblockdata, tileentity, itemstack);
/* 15 */     if (EnchantmentManager.getEnchantmentLevel(Enchantments.SILK_TOUCH, itemstack) == 0) {
/* 16 */       if (world.getDimensionManager().isNether()) {
/* 17 */         world.a(blockposition, false);
/*    */         
/*    */         return;
/*    */       } 
/* 21 */       Material material = world.getType(blockposition.down()).getMaterial();
/*    */       
/* 23 */       if (material.isSolid() || material.isLiquid()) {
/* 24 */         world.setTypeUpdate(blockposition, Blocks.WATER.getBlockData());
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void tick(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, Random random) {
/* 32 */     if (worldserver.getBrightness(EnumSkyBlock.BLOCK, blockposition) > 11 - iblockdata.b(worldserver, blockposition)) {
/* 33 */       melt(iblockdata, worldserver, blockposition);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void melt(IBlockData iblockdata, World world, BlockPosition blockposition) {
/* 40 */     if (CraftEventFactory.callBlockFadeEvent(world, blockposition, world.getDimensionManager().isNether() ? Blocks.AIR.getBlockData() : Blocks.WATER.getBlockData()).isCancelled()) {
/*    */       return;
/*    */     }
/*    */     
/* 44 */     if (world.getDimensionManager().isNether()) {
/* 45 */       world.a(blockposition, false);
/*    */     } else {
/* 47 */       world.setTypeUpdate(blockposition, Blocks.WATER.getBlockData());
/* 48 */       world.a(blockposition, Blocks.WATER, blockposition);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumPistonReaction getPushReaction(IBlockData iblockdata) {
/* 54 */     return EnumPistonReaction.NORMAL;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockIce.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */