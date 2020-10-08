/*     */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*     */ import java.util.Optional;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.server.v1_16_R2.Entity;
/*     */ import net.minecraft.server.v1_16_R2.EntityAnimal;
/*     */ import net.minecraft.server.v1_16_R2.EntityFox;
/*     */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*     */ import org.bukkit.OfflinePlayer;
/*     */ import org.bukkit.entity.AnimalTamer;
/*     */ import org.bukkit.entity.Fox;
/*     */ import org.bukkit.entity.Player;
/*     */ 
/*     */ public class CraftFox extends CraftAnimals implements Fox {
/*     */   public CraftFox(CraftServer server, EntityFox entity) {
/*  15 */     super(server, (EntityAnimal)entity);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityFox getHandle() {
/*  20 */     return (EntityFox)super.getHandle();
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityType getType() {
/*  25 */     return EntityType.FOX;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  30 */     return "CraftFox";
/*     */   }
/*     */ 
/*     */   
/*     */   public Fox.Type getFoxType() {
/*  35 */     return Fox.Type.values()[getHandle().getFoxType().ordinal()];
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFoxType(Fox.Type type) {
/*  40 */     Preconditions.checkArgument((type != null), "type");
/*     */     
/*  42 */     getHandle().setFoxType(EntityFox.Type.values()[type.ordinal()]);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCrouching() {
/*  47 */     return getHandle().isCrouching();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCrouching(boolean crouching) {
/*  52 */     getHandle().setCrouching(crouching);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSitting() {
/*  57 */     return getHandle().isSitting();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSitting(boolean sitting) {
/*  62 */     getHandle().setSitting(sitting);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSleeping(boolean sleeping) {
/*  67 */     getHandle().setSleeping(sleeping);
/*     */   }
/*     */   
/*     */   public AnimalTamer getFirstTrustedPlayer() {
/*     */     OfflinePlayer offlinePlayer;
/*  72 */     UUID uuid = ((Optional<UUID>)getHandle().getDataWatcher().get(EntityFox.FIRST_TRUSTED_PLAYER)).orElse(null);
/*  73 */     if (uuid == null) {
/*  74 */       return null;
/*     */     }
/*     */     
/*  77 */     Player player = getServer().getPlayer(uuid);
/*  78 */     if (player == null) {
/*  79 */       offlinePlayer = getServer().getOfflinePlayer(uuid);
/*     */     }
/*     */     
/*  82 */     return (AnimalTamer)offlinePlayer;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFirstTrustedPlayer(AnimalTamer player) {
/*  87 */     if (player == null && ((Optional)getHandle().getDataWatcher().get(EntityFox.SECOND_TRUSTED_PLAYER)).isPresent()) {
/*  88 */       throw new IllegalStateException("Must remove second trusted player first");
/*     */     }
/*     */     
/*  91 */     getHandle().getDataWatcher().set(EntityFox.FIRST_TRUSTED_PLAYER, (player == null) ? Optional.empty() : Optional.<UUID>of(player.getUniqueId()));
/*     */   }
/*     */   
/*     */   public AnimalTamer getSecondTrustedPlayer() {
/*     */     OfflinePlayer offlinePlayer;
/*  96 */     UUID uuid = ((Optional<UUID>)getHandle().getDataWatcher().get(EntityFox.SECOND_TRUSTED_PLAYER)).orElse(null);
/*  97 */     if (uuid == null) {
/*  98 */       return null;
/*     */     }
/*     */     
/* 101 */     Player player = getServer().getPlayer(uuid);
/* 102 */     if (player == null) {
/* 103 */       offlinePlayer = getServer().getOfflinePlayer(uuid);
/*     */     }
/*     */     
/* 106 */     return (AnimalTamer)offlinePlayer;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSecondTrustedPlayer(AnimalTamer player) {
/* 111 */     if (player != null && !((Optional)getHandle().getDataWatcher().get(EntityFox.FIRST_TRUSTED_PLAYER)).isPresent()) {
/* 112 */       throw new IllegalStateException("Must add first trusted player first");
/*     */     }
/*     */     
/* 115 */     getHandle().getDataWatcher().set(EntityFox.SECOND_TRUSTED_PLAYER, (player == null) ? Optional.empty() : Optional.<UUID>of(player.getUniqueId()));
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftFox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */