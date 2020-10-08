/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnchantmentFrostWalker
/*    */   extends Enchantment
/*    */ {
/*    */   public EnchantmentFrostWalker(Enchantment.Rarity enchantment_rarity, EnumItemSlot... aenumitemslot) {
/* 12 */     super(enchantment_rarity, EnchantmentSlotType.ARMOR_FEET, aenumitemslot);
/*    */   }
/*    */ 
/*    */   
/*    */   public int a(int i) {
/* 17 */     return i * 10;
/*    */   }
/*    */ 
/*    */   
/*    */   public int b(int i) {
/* 22 */     return a(i) + 15;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isTreasure() {
/* 27 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaxLevel() {
/* 32 */     return 2;
/*    */   }
/*    */   
/*    */   public static void a(EntityLiving entityliving, World world, BlockPosition blockposition, int i) {
/* 36 */     if (entityliving.isOnGround()) {
/* 37 */       IBlockData iblockdata = Blocks.FROSTED_ICE.getBlockData();
/* 38 */       float f = Math.min(16, 2 + i);
/* 39 */       BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();
/* 40 */       Iterator<BlockPosition> iterator = BlockPosition.a(blockposition.a(-f, -1.0D, -f), blockposition.a(f, -1.0D, f)).iterator();
/*    */       
/* 42 */       while (iterator.hasNext()) {
/* 43 */         BlockPosition blockposition1 = iterator.next();
/*    */         
/* 45 */         if (blockposition1.a(entityliving.getPositionVector(), f)) {
/* 46 */           blockposition_mutableblockposition.d(blockposition1.getX(), blockposition1.getY() + 1, blockposition1.getZ());
/* 47 */           IBlockData iblockdata1 = world.getType(blockposition_mutableblockposition);
/*    */           
/* 49 */           if (iblockdata1.isAir()) {
/* 50 */             IBlockData iblockdata2 = world.getType(blockposition1);
/*    */             
/* 52 */             if (iblockdata2.getMaterial() == Material.WATER && ((Integer)iblockdata2.get(BlockFluids.LEVEL)).intValue() == 0 && iblockdata.canPlace(world, blockposition1) && world.a(iblockdata, blockposition1, VoxelShapeCollision.a()))
/*    */             {
/* 54 */               if (CraftEventFactory.handleBlockFormEvent(world, blockposition1, iblockdata, entityliving)) {
/* 55 */                 world.getBlockTickList().a(blockposition1, Blocks.FROSTED_ICE, MathHelper.nextInt(entityliving.getRandom(), 60, 120));
/*    */               }
/*    */             }
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean a(Enchantment enchantment) {
/* 68 */     return (super.a(enchantment) && enchantment != Enchantments.DEPTH_STRIDER);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EnchantmentFrostWalker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */