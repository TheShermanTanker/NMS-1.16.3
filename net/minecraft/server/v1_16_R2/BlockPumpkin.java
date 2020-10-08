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
/*    */ public class BlockPumpkin
/*    */   extends BlockStemmed
/*    */ {
/*    */   protected BlockPumpkin(BlockBase.Info var0) {
/* 19 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumInteractionResult interact(IBlockData var0, World var1, BlockPosition var2, EntityHuman var3, EnumHand var4, MovingObjectPositionBlock var5) {
/* 24 */     ItemStack var6 = var3.b(var4);
/* 25 */     if (var6.getItem() == Items.SHEARS) {
/* 26 */       if (!var1.isClientSide) {
/* 27 */         EnumDirection var7 = var5.getDirection();
/* 28 */         EnumDirection var8 = (var7.n() == EnumDirection.EnumAxis.Y) ? var3.getDirection().opposite() : var7;
/*    */         
/* 30 */         var1.playSound((EntityHuman)null, var2, SoundEffects.BLOCK_PUMPKIN_CARVE, SoundCategory.BLOCKS, 1.0F, 1.0F);
/* 31 */         var1.setTypeAndData(var2, Blocks.CARVED_PUMPKIN.getBlockData().set(BlockPumpkinCarved.a, var8), 11);
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 36 */         EntityItem var9 = new EntityItem(var1, var2.getX() + 0.5D + var8.getAdjacentX() * 0.65D, var2.getY() + 0.1D, var2.getZ() + 0.5D + var8.getAdjacentZ() * 0.65D, new ItemStack(Items.PUMPKIN_SEEDS, 4));
/*    */ 
/*    */ 
/*    */         
/* 40 */         var9.setMot(0.05D * var8
/* 41 */             .getAdjacentX() + var1.random.nextDouble() * 0.02D, 0.05D, 0.05D * var8
/*    */             
/* 43 */             .getAdjacentZ() + var1.random.nextDouble() * 0.02D);
/*    */ 
/*    */         
/* 46 */         var1.addEntity(var9);
/*    */         
/* 48 */         var6.damage(1, var3, var1 -> var1.broadcastItemBreak(var0));
/*    */       } 
/*    */       
/* 51 */       return EnumInteractionResult.a(var1.isClientSide);
/*    */     } 
/*    */     
/* 54 */     return super.interact(var0, var1, var2, var3, var4, var5);
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockStem c() {
/* 59 */     return (BlockStem)Blocks.PUMPKIN_STEM;
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockStemAttached d() {
/* 64 */     return (BlockStemAttached)Blocks.ATTACHED_PUMPKIN_STEM;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockPumpkin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */