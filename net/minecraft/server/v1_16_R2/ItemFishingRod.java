/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.player.PlayerFishEvent;
/*    */ 
/*    */ public class ItemFishingRod extends Item implements ItemVanishable {
/*    */   public ItemFishingRod(Item.Info item_info) {
/*  8 */     super(item_info);
/*    */   }
/*    */ 
/*    */   
/*    */   public InteractionResultWrapper<ItemStack> a(World world, EntityHuman entityhuman, EnumHand enumhand) {
/* 13 */     ItemStack itemstack = entityhuman.b(enumhand);
/*    */ 
/*    */     
/* 16 */     if (entityhuman.hookedFish != null) {
/* 17 */       if (!world.isClientSide) {
/* 18 */         int i = entityhuman.hookedFish.b(itemstack);
/* 19 */         itemstack.damage(i, entityhuman, entityhuman1 -> entityhuman1.broadcastItemBreak(enumhand));
/*    */       } 
/*    */ 
/*    */ 
/*    */       
/* 24 */       world.playSound((EntityHuman)null, entityhuman.locX(), entityhuman.locY(), entityhuman.locZ(), SoundEffects.ENTITY_FISHING_BOBBER_RETRIEVE, SoundCategory.NEUTRAL, 1.0F, 0.4F / (RANDOM.nextFloat() * 0.4F + 0.8F));
/*    */     } else {
/*    */       
/* 27 */       if (!world.isClientSide) {
/* 28 */         int i = EnchantmentManager.c(itemstack);
/* 29 */         int j = EnchantmentManager.b(itemstack);
/*    */ 
/*    */         
/* 32 */         EntityFishingHook entityfishinghook = new EntityFishingHook(entityhuman, world, j, i);
/* 33 */         PlayerFishEvent playerFishEvent = new PlayerFishEvent((Player)entityhuman.getBukkitEntity(), null, (FishHook)entityfishinghook.getBukkitEntity(), PlayerFishEvent.State.FISHING);
/* 34 */         world.getServer().getPluginManager().callEvent((Event)playerFishEvent);
/*    */         
/* 36 */         if (playerFishEvent.isCancelled()) {
/* 37 */           entityhuman.hookedFish = null;
/* 38 */           return new InteractionResultWrapper<>(EnumInteractionResult.PASS, itemstack);
/*    */         } 
/* 40 */         world.playSound((EntityHuman)null, entityhuman.locX(), entityhuman.locY(), entityhuman.locZ(), SoundEffects.ENTITY_FISHING_BOBBER_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (RANDOM.nextFloat() * 0.4F + 0.8F));
/* 41 */         world.addEntity(entityfishinghook);
/*    */       } 
/*    */ 
/*    */       
/* 45 */       entityhuman.b(StatisticList.ITEM_USED.b(this));
/*    */     } 
/*    */     
/* 48 */     return InteractionResultWrapper.a(itemstack, world.s_());
/*    */   }
/*    */ 
/*    */   
/*    */   public int c() {
/* 53 */     return 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemFishingRod.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */