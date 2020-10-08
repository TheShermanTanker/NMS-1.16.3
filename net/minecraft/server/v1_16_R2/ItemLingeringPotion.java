/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public class ItemLingeringPotion
/*    */   extends ItemPotionThrowable {
/*    */   public ItemLingeringPotion(Item.Info item_info) {
/*  6 */     super(item_info);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public InteractionResultWrapper<ItemStack> a(World world, EntityHuman entityhuman, EnumHand enumhand) {
/* 12 */     InteractionResultWrapper<ItemStack> wrapper = super.a(world, entityhuman, enumhand);
/* 13 */     if (wrapper.getResult() != EnumInteractionResult.FAIL) {
/* 14 */       world.playSound((EntityHuman)null, entityhuman.locX(), entityhuman.locY(), entityhuman.locZ(), SoundEffects.ENTITY_LINGERING_POTION_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (RANDOM.nextFloat() * 0.4F + 0.8F));
/*    */     }
/* 16 */     return wrapper;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemLingeringPotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */