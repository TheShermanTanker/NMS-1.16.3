/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.destroystokyo.paper.event.player.PlayerLaunchProjectileEvent;
/*    */ 
/*    */ public class ItemEnderPearl extends Item {
/*    */   public ItemEnderPearl(Item.Info item_info) {
/*  6 */     super(item_info);
/*    */   }
/*    */ 
/*    */   
/*    */   public InteractionResultWrapper<ItemStack> a(World world, EntityHuman entityhuman, EnumHand enumhand) {
/* 11 */     ItemStack itemstack = entityhuman.b(enumhand);
/*    */ 
/*    */     
/* 14 */     if (!world.isClientSide) {
/* 15 */       EntityEnderPearl entityenderpearl = new EntityEnderPearl(world, entityhuman);
/*    */       
/* 17 */       entityenderpearl.setItem(itemstack);
/* 18 */       entityenderpearl.a(entityhuman, entityhuman.pitch, entityhuman.yaw, 0.0F, 1.5F, 1.0F);
/*    */       
/* 20 */       PlayerLaunchProjectileEvent event = new PlayerLaunchProjectileEvent((Player)entityhuman.getBukkitEntity(), (ItemStack)CraftItemStack.asCraftMirror(itemstack), (Projectile)entityenderpearl.getBukkitEntity());
/* 21 */       if (event.callEvent() && world.addEntity(entityenderpearl)) {
/* 22 */         if (event.shouldConsume() && !entityhuman.abilities.canInstantlyBuild) {
/* 23 */           itemstack.subtract(1);
/* 24 */         } else if (entityhuman instanceof EntityPlayer) {
/* 25 */           ((EntityPlayer)entityhuman).getBukkitEntity().updateInventory();
/*    */         } 
/*    */         
/* 28 */         world.playSound((EntityHuman)null, entityhuman.locX(), entityhuman.locY(), entityhuman.locZ(), SoundEffects.ENTITY_ENDER_PEARL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (Entity.SHARED_RANDOM.nextFloat() * 0.4F + 0.8F));
/* 29 */         entityhuman.b(StatisticList.ITEM_USED.b(this));
/* 30 */         entityhuman.getCooldownTracker().setCooldown(this, 20);
/*    */       } else {
/*    */         
/* 33 */         if (entityhuman instanceof EntityPlayer) {
/* 34 */           ((EntityPlayer)entityhuman).getBukkitEntity().updateInventory();
/*    */         }
/* 36 */         return new InteractionResultWrapper<>(EnumInteractionResult.FAIL, itemstack);
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
/*    */ 
/*    */ 
/*    */     
/* 51 */     return InteractionResultWrapper.a(itemstack, world.s_());
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemEnderPearl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */