/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import java.util.UUID;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityAnimal;
/*    */ import net.minecraft.server.v1_16_R2.EntityTameableAnimal;
/*    */ import org.bukkit.OfflinePlayer;
/*    */ import org.bukkit.entity.AnimalTamer;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class CraftTameableAnimal extends CraftAnimals implements Tameable, Creature {
/*    */   public CraftTameableAnimal(CraftServer server, EntityTameableAnimal entity) {
/* 12 */     super(server, (EntityAnimal)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityTameableAnimal getHandle() {
/* 17 */     return (EntityTameableAnimal)super.getHandle();
/*    */   }
/*    */   
/*    */   public UUID getOwnerUniqueId() {
/* 21 */     return getOwnerUUID();
/*    */   }
/*    */   public UUID getOwnerUUID() {
/*    */     try {
/* 25 */       return getHandle().getOwnerUUID();
/* 26 */     } catch (IllegalArgumentException ex) {
/* 27 */       return null;
/*    */     } 
/*    */   }
/*    */   
/*    */   public void setOwnerUUID(UUID uuid) {
/* 32 */     getHandle().setOwnerUUID(uuid);
/*    */   }
/*    */   
/*    */   public AnimalTamer getOwner() {
/*    */     OfflinePlayer offlinePlayer;
/* 37 */     if (getOwnerUUID() == null) {
/* 38 */       return null;
/*    */     }
/*    */     
/* 41 */     Player player = getServer().getPlayer(getOwnerUUID());
/* 42 */     if (player == null) {
/* 43 */       offlinePlayer = getServer().getOfflinePlayer(getOwnerUUID());
/*    */     }
/*    */     
/* 46 */     return (AnimalTamer)offlinePlayer;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isTamed() {
/* 51 */     return getHandle().isTamed();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setOwner(AnimalTamer tamer) {
/* 56 */     if (tamer != null) {
/* 57 */       setTamed(true);
/* 58 */       getHandle().setGoalTarget(null, null, false);
/* 59 */       setOwnerUUID(tamer.getUniqueId());
/*    */     } else {
/* 61 */       setTamed(false);
/* 62 */       setOwnerUUID(null);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void setTamed(boolean tame) {
/* 68 */     getHandle().setTamed(tame);
/* 69 */     if (!tame) {
/* 70 */       setOwnerUUID(null);
/*    */     }
/*    */   }
/*    */   
/*    */   public boolean isSitting() {
/* 75 */     return getHandle().isSitting();
/*    */   }
/*    */   
/*    */   public void setSitting(boolean sitting) {
/* 79 */     getHandle().setSitting(sitting);
/* 80 */     getHandle().setWillSit(sitting);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 85 */     return getClass().getSimpleName() + "{owner=" + getOwner() + ",tamed=" + isTamed() + "}";
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftTameableAnimal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */