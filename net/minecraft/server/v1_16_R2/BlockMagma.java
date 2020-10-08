/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Random;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*    */ 
/*    */ public class BlockMagma extends Block {
/*    */   public BlockMagma(BlockBase.Info blockbase_info) {
/*  8 */     super(blockbase_info);
/*    */   }
/*    */ 
/*    */   
/*    */   public void stepOn(World world, BlockPosition blockposition, Entity entity) {
/* 13 */     if (!entity.isFireProof() && entity instanceof EntityLiving && !EnchantmentManager.i((EntityLiving)entity)) {
/* 14 */       CraftEventFactory.blockDamage = world.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ());
/* 15 */       entity.damageEntity(DamageSource.HOT_FLOOR, 1.0F);
/* 16 */       CraftEventFactory.blockDamage = null;
/*    */     } 
/*    */     
/* 19 */     super.stepOn(world, blockposition, entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public void tickAlways(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, Random random) {
/* 24 */     BlockBubbleColumn.a(worldserver, blockposition.up(), true);
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData updateState(IBlockData iblockdata, EnumDirection enumdirection, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) {
/* 29 */     if (enumdirection == EnumDirection.UP && iblockdata1.a(Blocks.WATER)) {
/* 30 */       generatoraccess.getBlockTickList().a(blockposition, this, 20);
/*    */     }
/*    */     
/* 33 */     return super.updateState(iblockdata, enumdirection, iblockdata1, generatoraccess, blockposition, blockposition1);
/*    */   }
/*    */ 
/*    */   
/*    */   public void tick(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, Random random) {
/* 38 */     BlockPosition blockposition1 = blockposition.up();
/*    */     
/* 40 */     if (worldserver.getFluid(blockposition).a(TagsFluid.WATER)) {
/* 41 */       worldserver.playSound((EntityHuman)null, blockposition, SoundEffects.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (worldserver.random.nextFloat() - worldserver.random.nextFloat()) * 0.8F);
/* 42 */       worldserver.a(Particles.LARGE_SMOKE, blockposition1.getX() + 0.5D, blockposition1.getY() + 0.25D, blockposition1.getZ() + 0.5D, 8, 0.5D, 0.25D, 0.5D, 0.0D);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onPlace(IBlockData iblockdata, World world, BlockPosition blockposition, IBlockData iblockdata1, boolean flag) {
/* 49 */     world.getBlockTickList().a(blockposition, this, 20);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockMagma.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */