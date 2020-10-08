/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftPlayer;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.player.PlayerTeleportEvent;
/*    */ 
/*    */ public class ItemChorusFruit
/*    */   extends Item {
/*    */   public ItemChorusFruit(Item.Info item_info) {
/* 12 */     super(item_info);
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack a(ItemStack itemstack, World world, EntityLiving entityliving) {
/* 17 */     ItemStack itemstack1 = super.a(itemstack, world, entityliving);
/*    */     
/* 19 */     if (!world.isClientSide) {
/* 20 */       double d0 = entityliving.locX();
/* 21 */       double d1 = entityliving.locY();
/* 22 */       double d2 = entityliving.locZ();
/*    */       
/* 24 */       for (int i = 0; i < 16; i++) {
/* 25 */         double d3 = entityliving.locX() + (entityliving.getRandom().nextDouble() - 0.5D) * 16.0D;
/* 26 */         double d4 = MathHelper.a(entityliving.locY() + (entityliving.getRandom().nextInt(16) - 8), 0.0D, (world.getHeight() - 1));
/* 27 */         double d5 = entityliving.locZ() + (entityliving.getRandom().nextDouble() - 0.5D) * 16.0D;
/*    */ 
/*    */         
/* 30 */         if (entityliving instanceof EntityPlayer) {
/* 31 */           CraftPlayer craftPlayer = ((EntityPlayer)entityliving).getBukkitEntity();
/* 32 */           PlayerTeleportEvent teleEvent = new PlayerTeleportEvent((Player)craftPlayer, craftPlayer.getLocation(), new Location(craftPlayer.getWorld(), d3, d4, d5), PlayerTeleportEvent.TeleportCause.CHORUS_FRUIT);
/* 33 */           world.getServer().getPluginManager().callEvent((Event)teleEvent);
/* 34 */           if (teleEvent.isCancelled()) {
/*    */             break;
/*    */           }
/* 37 */           d3 = teleEvent.getTo().getX();
/* 38 */           d4 = teleEvent.getTo().getY();
/* 39 */           d5 = teleEvent.getTo().getZ();
/*    */         } 
/*    */ 
/*    */         
/* 43 */         if (entityliving.isPassenger()) {
/* 44 */           entityliving.stopRiding();
/*    */         }
/*    */         
/* 47 */         if (entityliving.a(d3, d4, d5, true)) {
/* 48 */           SoundEffect soundeffect = (entityliving instanceof EntityFox) ? SoundEffects.ENTITY_FOX_TELEPORT : SoundEffects.ITEM_CHORUS_FRUIT_TELEPORT;
/*    */           
/* 50 */           world.playSound((EntityHuman)null, d0, d1, d2, soundeffect, SoundCategory.PLAYERS, 1.0F, 1.0F);
/* 51 */           entityliving.playSound(soundeffect, 1.0F, 1.0F);
/*    */           
/*    */           break;
/*    */         } 
/*    */       } 
/* 56 */       if (entityliving instanceof EntityHuman) {
/* 57 */         ((EntityHuman)entityliving).getCooldownTracker().setCooldown(this, 20);
/*    */       }
/*    */     } 
/*    */     
/* 61 */     return itemstack1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemChorusFruit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */