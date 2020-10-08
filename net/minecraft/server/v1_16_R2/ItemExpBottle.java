/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.destroystokyo.paper.event.player.PlayerLaunchProjectileEvent;
/*    */ 
/*    */ public class ItemExpBottle extends Item {
/*    */   public ItemExpBottle(Item.Info item_info) {
/*  6 */     super(item_info);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean e(ItemStack itemstack) {
/* 11 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public InteractionResultWrapper<ItemStack> a(World world, EntityHuman entityhuman, EnumHand enumhand) {
/* 16 */     ItemStack itemstack = entityhuman.b(enumhand);
/*    */ 
/*    */     
/* 19 */     if (!world.isClientSide) {
/* 20 */       EntityThrownExpBottle entitythrownexpbottle = new EntityThrownExpBottle(world, entityhuman);
/*    */       
/* 22 */       entitythrownexpbottle.setItem(itemstack);
/* 23 */       entitythrownexpbottle.a(entityhuman, entityhuman.pitch, entityhuman.yaw, -20.0F, 0.7F, 1.0F);
/*    */       
/* 25 */       PlayerLaunchProjectileEvent event = new PlayerLaunchProjectileEvent((Player)entityhuman.getBukkitEntity(), (ItemStack)CraftItemStack.asCraftMirror(itemstack), (Projectile)entitythrownexpbottle.getBukkitEntity());
/* 26 */       if (event.callEvent() && world.addEntity(entitythrownexpbottle)) {
/* 27 */         if (event.shouldConsume() && !entityhuman.abilities.canInstantlyBuild) {
/* 28 */           itemstack.subtract(1);
/* 29 */         } else if (entityhuman instanceof EntityPlayer) {
/* 30 */           ((EntityPlayer)entityhuman).getBukkitEntity().updateInventory();
/*    */         } 
/*    */         
/* 33 */         world.playSound((EntityHuman)null, entityhuman.locX(), entityhuman.locY(), entityhuman.locZ(), SoundEffects.ENTITY_EXPERIENCE_BOTTLE_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (Entity.SHARED_RANDOM.nextFloat() * 0.4F + 0.8F));
/* 34 */         entityhuman.b(StatisticList.ITEM_USED.b(this));
/*    */       } else {
/* 36 */         if (entityhuman instanceof EntityPlayer) {
/* 37 */           ((EntityPlayer)entityhuman).getBukkitEntity().updateInventory();
/*    */         }
/* 39 */         return new InteractionResultWrapper<>(EnumInteractionResult.FAIL, itemstack);
/*    */       } 
/*    */     } 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 51 */     return InteractionResultWrapper.a(itemstack, world.s_());
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemExpBottle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */