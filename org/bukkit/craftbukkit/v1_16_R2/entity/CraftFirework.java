/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import java.util.Random;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityFireworks;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import net.minecraft.server.v1_16_R2.IProjectile;
/*    */ import net.minecraft.server.v1_16_R2.ItemStack;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.inventory.meta.FireworkMeta;
/*    */ 
/*    */ public class CraftFirework extends CraftProjectile implements Firework {
/* 16 */   private final Random random = new Random();
/*    */   private final CraftItemStack item;
/*    */   
/*    */   public CraftFirework(CraftServer server, EntityFireworks entity) {
/* 20 */     super(server, (IProjectile)entity);
/*    */     
/* 22 */     ItemStack item = (ItemStack)getHandle().getDataWatcher().get(EntityFireworks.FIREWORK_ITEM);
/*    */     
/* 24 */     if (item.isEmpty()) {
/* 25 */       item = new ItemStack((IMaterial)Items.FIREWORK_ROCKET);
/* 26 */       getHandle().getDataWatcher().set(EntityFireworks.FIREWORK_ITEM, item);
/*    */     } 
/*    */     
/* 29 */     this.item = CraftItemStack.asCraftMirror(item);
/*    */ 
/*    */     
/* 32 */     if (this.item.getType() != Material.FIREWORK_ROCKET) {
/* 33 */       this.item.setType(Material.FIREWORK_ROCKET);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityFireworks getHandle() {
/* 39 */     return (EntityFireworks)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 44 */     return "CraftFirework";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 49 */     return EntityType.FIREWORK;
/*    */   }
/*    */ 
/*    */   
/*    */   public FireworkMeta getFireworkMeta() {
/* 54 */     return (FireworkMeta)this.item.getItemMeta();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setFireworkMeta(FireworkMeta meta) {
/* 59 */     this.item.setItemMeta((ItemMeta)meta);
/*    */ 
/*    */     
/* 62 */     (getHandle()).expectedLifespan = 10 * (1 + meta.getPower()) + this.random.nextInt(6) + this.random.nextInt(7);
/*    */     
/* 64 */     getHandle().getDataWatcher().markDirty(EntityFireworks.FIREWORK_ITEM);
/*    */   }
/*    */ 
/*    */   
/*    */   public void detonate() {
/* 69 */     (getHandle()).expectedLifespan = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isShotAtAngle() {
/* 74 */     return getHandle().isShotAtAngle();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setShotAtAngle(boolean shotAtAngle) {
/* 79 */     getHandle().getDataWatcher().set(EntityFireworks.SHOT_AT_ANGLE, Boolean.valueOf(shotAtAngle));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public UUID getSpawningEntity() {
/* 85 */     return (getHandle()).spawningEntity;
/*    */   }
/*    */ 
/*    */   
/*    */   public LivingEntity getBoostedEntity() {
/* 90 */     EntityLiving boostedEntity = (getHandle()).ridingEntity;
/* 91 */     return (boostedEntity != null) ? (LivingEntity)boostedEntity.getBukkitEntity() : null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftFirework.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */