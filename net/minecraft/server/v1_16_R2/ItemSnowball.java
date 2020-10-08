/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.destroystokyo.paper.event.player.PlayerLaunchProjectileEvent;
/*    */ 
/*    */ public class ItemSnowball extends Item {
/*    */   public ItemSnowball(Item.Info item_info) {
/*  6 */     super(item_info);
/*    */   }
/*    */ 
/*    */   
/*    */   public InteractionResultWrapper<ItemStack> a(World world, EntityHuman entityhuman, EnumHand enumhand) {
/* 11 */     ItemStack itemstack = entityhuman.b(enumhand);
/*    */ 
/*    */ 
/*    */     
/* 15 */     if (!world.isClientSide) {
/* 16 */       EntitySnowball entitysnowball = new EntitySnowball(world, entityhuman);
/*    */       
/* 18 */       entitysnowball.setItem(itemstack);
/* 19 */       entitysnowball.a(entityhuman, entityhuman.pitch, entityhuman.yaw, 0.0F, 1.5F, 1.0F);
/*    */       
/* 21 */       PlayerLaunchProjectileEvent event = new PlayerLaunchProjectileEvent((Player)entityhuman.getBukkitEntity(), (ItemStack)CraftItemStack.asCraftMirror(itemstack), (Projectile)entitysnowball.getBukkitEntity());
/* 22 */       if (event.callEvent() && world.addEntity(entitysnowball)) {
/* 23 */         if (event.shouldConsume() && !entityhuman.abilities.canInstantlyBuild) {
/*    */           
/* 25 */           itemstack.subtract(1);
/* 26 */         } else if (entityhuman instanceof EntityPlayer) {
/* 27 */           ((EntityPlayer)entityhuman).getBukkitEntity().updateInventory();
/*    */         } 
/*    */         
/* 30 */         world.playSound((EntityHuman)null, entityhuman.locX(), entityhuman.locY(), entityhuman.locZ(), SoundEffects.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (RANDOM.nextFloat() * 0.4F + 0.8F));
/*    */       } else {
/* 32 */         if (entityhuman instanceof EntityPlayer) ((EntityPlayer)entityhuman).getBukkitEntity().updateInventory(); 
/* 33 */         return new InteractionResultWrapper<>(EnumInteractionResult.FAIL, itemstack);
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 38 */     entityhuman.b(StatisticList.ITEM_USED.b(this));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 46 */     return InteractionResultWrapper.a(itemstack, world.s_());
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemSnowball.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */