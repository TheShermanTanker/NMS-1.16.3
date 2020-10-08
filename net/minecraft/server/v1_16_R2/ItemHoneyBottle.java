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
/*    */ public class ItemHoneyBottle
/*    */   extends Item
/*    */ {
/*    */   public ItemHoneyBottle(Item.Info var0) {
/* 20 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack a(ItemStack var0, World var1, EntityLiving var2) {
/* 25 */     super.a(var0, var1, var2);
/* 26 */     if (var2 instanceof EntityPlayer) {
/* 27 */       EntityPlayer var3 = (EntityPlayer)var2;
/* 28 */       CriterionTriggers.z.a(var3, var0);
/* 29 */       var3.b(StatisticList.ITEM_USED.b(this));
/*    */     } 
/*    */ 
/*    */     
/* 33 */     if (!var1.isClientSide) {
/* 34 */       var2.removeEffect(MobEffects.POISON);
/*    */     }
/*    */     
/* 37 */     if (var0.isEmpty())
/* 38 */       return new ItemStack(Items.GLASS_BOTTLE); 
/* 39 */     if (var2 instanceof EntityHuman && !((EntityHuman)var2).abilities.canInstantlyBuild) {
/* 40 */       ItemStack var3 = new ItemStack(Items.GLASS_BOTTLE);
/* 41 */       EntityHuman var4 = (EntityHuman)var2;
/* 42 */       if (!var4.inventory.pickup(var3)) {
/* 43 */         var4.drop(var3, false);
/*    */       }
/*    */     } 
/*    */     
/* 47 */     return var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public int e_(ItemStack var0) {
/* 52 */     return 40;
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumAnimation d_(ItemStack var0) {
/* 57 */     return EnumAnimation.DRINK;
/*    */   }
/*    */ 
/*    */   
/*    */   public SoundEffect ae_() {
/* 62 */     return SoundEffects.ITEM_HONEY_BOTTLE_DRINK;
/*    */   }
/*    */ 
/*    */   
/*    */   public SoundEffect ad_() {
/* 67 */     return SoundEffects.ITEM_HONEY_BOTTLE_DRINK;
/*    */   }
/*    */ 
/*    */   
/*    */   public InteractionResultWrapper<ItemStack> a(World var0, EntityHuman var1, EnumHand var2) {
/* 72 */     return ItemLiquidUtil.a(var0, var1, var2);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemHoneyBottle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */