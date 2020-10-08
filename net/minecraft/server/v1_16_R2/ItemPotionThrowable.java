/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.destroystokyo.paper.event.player.PlayerLaunchProjectileEvent;
/*    */ 
/*    */ public class ItemPotionThrowable extends ItemPotion {
/*    */   public ItemPotionThrowable(Item.Info item_info) {
/*  6 */     super(item_info);
/*    */   }
/*    */ 
/*    */   
/*    */   public InteractionResultWrapper<ItemStack> a(World world, EntityHuman entityhuman, EnumHand enumhand) {
/* 11 */     ItemStack itemstack = entityhuman.b(enumhand);
/*    */     
/* 13 */     if (!world.isClientSide) {
/* 14 */       EntityPotion entitypotion = new EntityPotion(world, entityhuman);
/*    */       
/* 16 */       entitypotion.setItem(itemstack);
/* 17 */       entitypotion.a(entityhuman, entityhuman.pitch, entityhuman.yaw, -20.0F, 0.5F, 1.0F);
/*    */       
/* 19 */       PlayerLaunchProjectileEvent event = new PlayerLaunchProjectileEvent((Player)entityhuman.getBukkitEntity(), (ItemStack)CraftItemStack.asCraftMirror(itemstack), (Projectile)entitypotion.getBukkitEntity());
/* 20 */       if (event.callEvent() && world.addEntity(entitypotion)) {
/* 21 */         if (event.shouldConsume() && !entityhuman.abilities.canInstantlyBuild) {
/* 22 */           itemstack.subtract(1);
/* 23 */         } else if (entityhuman instanceof EntityPlayer) {
/* 24 */           ((EntityPlayer)entityhuman).getBukkitEntity().updateInventory();
/*    */         } 
/*    */         
/* 27 */         entityhuman.b(StatisticList.ITEM_USED.b(this));
/*    */       } else {
/* 29 */         if (entityhuman instanceof EntityPlayer) {
/* 30 */           ((EntityPlayer)entityhuman).getBukkitEntity().updateInventory();
/*    */         }
/* 32 */         return new InteractionResultWrapper<>(EnumInteractionResult.FAIL, itemstack);
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
/* 44 */     return InteractionResultWrapper.a(itemstack, world.s_());
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemPotionThrowable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */