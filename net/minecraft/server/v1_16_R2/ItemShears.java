/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemShears
/*    */   extends Item
/*    */ {
/*    */   public ItemShears(Item.Info var0) {
/* 13 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(ItemStack var0, World var1, IBlockData var2, BlockPosition var3, EntityLiving var4) {
/* 18 */     if (!var1.isClientSide && !var2.getBlock().a(TagsBlock.FIRE)) {
/* 19 */       var0.damage(1, var4, var0 -> var0.broadcastItemBreak(EnumItemSlot.MAINHAND));
/*    */     }
/*    */     
/* 22 */     if (var2.a(TagsBlock.LEAVES) || var2
/* 23 */       .a(Blocks.COBWEB) || var2
/* 24 */       .a(Blocks.GRASS) || var2
/* 25 */       .a(Blocks.FERN) || var2
/* 26 */       .a(Blocks.DEAD_BUSH) || var2
/* 27 */       .a(Blocks.VINE) || var2
/* 28 */       .a(Blocks.TRIPWIRE) || var2
/* 29 */       .a(TagsBlock.WOOL))
/*    */     {
/* 31 */       return true;
/*    */     }
/* 33 */     return super.a(var0, var1, var2, var3, var4);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canDestroySpecialBlock(IBlockData var0) {
/* 38 */     return (var0.a(Blocks.COBWEB) || var0.a(Blocks.REDSTONE_WIRE) || var0.a(Blocks.TRIPWIRE));
/*    */   }
/*    */ 
/*    */   
/*    */   public float getDestroySpeed(ItemStack var0, IBlockData var1) {
/* 43 */     if (var1.a(Blocks.COBWEB) || var1.a(TagsBlock.LEAVES)) {
/* 44 */       return 15.0F;
/*    */     }
/* 46 */     if (var1.a(TagsBlock.WOOL)) {
/* 47 */       return 5.0F;
/*    */     }
/* 49 */     return super.getDestroySpeed(var0, var1);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemShears.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */