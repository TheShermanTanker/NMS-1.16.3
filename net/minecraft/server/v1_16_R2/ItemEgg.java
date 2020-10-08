/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.destroystokyo.paper.event.player.PlayerLaunchProjectileEvent;
/*    */ 
/*    */ public class ItemEgg extends Item {
/*    */   public ItemEgg(Item.Info item_info) {
/*  6 */     super(item_info);
/*    */   }
/*    */ 
/*    */   
/*    */   public InteractionResultWrapper<ItemStack> a(World world, EntityHuman entityhuman, EnumHand enumhand) {
/* 11 */     ItemStack itemstack = entityhuman.b(enumhand);
/*    */ 
/*    */     
/* 14 */     if (!world.isClientSide) {
/* 15 */       EntityEgg entityegg = new EntityEgg(world, entityhuman);
/*    */       
/* 17 */       entityegg.setItem(itemstack);
/* 18 */       entityegg.a(entityhuman, entityhuman.pitch, entityhuman.yaw, 0.0F, 1.5F, 1.0F);
/*    */       
/* 20 */       PlayerLaunchProjectileEvent event = new PlayerLaunchProjectileEvent((Player)entityhuman.getBukkitEntity(), (ItemStack)CraftItemStack.asCraftMirror(itemstack), (Projectile)entityegg.getBukkitEntity());
/* 21 */       if (event.callEvent() && world.addEntity(entityegg)) {
/* 22 */         if (event.shouldConsume() && !entityhuman.abilities.canInstantlyBuild) {
/* 23 */           itemstack.subtract(1);
/* 24 */         } else if (entityhuman instanceof EntityPlayer) {
/* 25 */           ((EntityPlayer)entityhuman).getBukkitEntity().updateInventory();
/*    */         } 
/*    */         
/* 28 */         world.playSound((EntityHuman)null, entityhuman.locX(), entityhuman.locY(), entityhuman.locZ(), SoundEffects.ENTITY_EGG_THROW, SoundCategory.PLAYERS, 0.5F, 0.4F / (Entity.SHARED_RANDOM.nextFloat() * 0.4F + 0.8F));
/* 29 */         entityhuman.b(StatisticList.ITEM_USED.b(this));
/*    */       } else {
/* 31 */         if (entityhuman instanceof EntityPlayer) {
/* 32 */           ((EntityPlayer)entityhuman).getBukkitEntity().updateInventory();
/*    */         }
/* 34 */         return new InteractionResultWrapper<>(EnumInteractionResult.FAIL, itemstack);
/*    */       } 
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 40 */     world.playSound((EntityHuman)null, entityhuman.locX(), entityhuman.locY(), entityhuman.locZ(), SoundEffects.ENTITY_EGG_THROW, SoundCategory.PLAYERS, 0.5F, 0.4F / (RANDOM.nextFloat() * 0.4F + 0.8F));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 49 */     return InteractionResultWrapper.a(itemstack, world.s_());
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemEgg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */