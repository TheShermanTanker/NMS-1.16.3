/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.List;
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
/*    */ 
/*    */ 
/*    */ public class ItemGlassBottle
/*    */   extends Item
/*    */ {
/*    */   public ItemGlassBottle(Item.Info var0) {
/* 24 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public InteractionResultWrapper<ItemStack> a(World var0, EntityHuman var1, EnumHand var2) {
/* 29 */     List<EntityAreaEffectCloud> var3 = var0.a(EntityAreaEffectCloud.class, var1.getBoundingBox().g(2.0D), var0 -> (var0 != null && var0.isAlive() && var0.getSource() instanceof EntityEnderDragon));
/*    */     
/* 31 */     ItemStack var4 = var1.b(var2);
/*    */     
/* 33 */     if (!var3.isEmpty()) {
/* 34 */       EntityAreaEffectCloud entityAreaEffectCloud = var3.get(0);
/* 35 */       entityAreaEffectCloud.setRadius(entityAreaEffectCloud.getRadius() - 0.5F);
/*    */       
/* 37 */       var0.playSound(null, var1.locX(), var1.locY(), var1.locZ(), SoundEffects.ITEM_BOTTLE_FILL_DRAGONBREATH, SoundCategory.NEUTRAL, 1.0F, 1.0F);
/* 38 */       return InteractionResultWrapper.a(a(var4, var1, new ItemStack(Items.DRAGON_BREATH)), var0.s_());
/*    */     } 
/*    */     
/* 41 */     MovingObjectPosition var5 = a(var0, var1, RayTrace.FluidCollisionOption.SOURCE_ONLY);
/* 42 */     if (var5.getType() == MovingObjectPosition.EnumMovingObjectType.MISS) {
/* 43 */       return InteractionResultWrapper.pass(var4);
/*    */     }
/*    */     
/* 46 */     if (var5.getType() == MovingObjectPosition.EnumMovingObjectType.BLOCK) {
/* 47 */       BlockPosition var6 = ((MovingObjectPositionBlock)var5).getBlockPosition();
/*    */       
/* 49 */       if (!var0.a(var1, var6)) {
/* 50 */         return InteractionResultWrapper.pass(var4);
/*    */       }
/* 52 */       if (var0.getFluid(var6).a(TagsFluid.WATER)) {
/* 53 */         var0.playSound(var1, var1.locX(), var1.locY(), var1.locZ(), SoundEffects.ITEM_BOTTLE_FILL, SoundCategory.NEUTRAL, 1.0F, 1.0F);
/* 54 */         return InteractionResultWrapper.a(a(var4, var1, PotionUtil.a(new ItemStack(Items.POTION), Potions.WATER)), var0.s_());
/*    */       } 
/*    */     } 
/*    */     
/* 58 */     return InteractionResultWrapper.pass(var4);
/*    */   }
/*    */   
/*    */   protected ItemStack a(ItemStack var0, EntityHuman var1, ItemStack var2) {
/* 62 */     var1.b(StatisticList.ITEM_USED.b(this));
/* 63 */     return ItemLiquidUtil.a(var0, var1, var2);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemGlassBottle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */