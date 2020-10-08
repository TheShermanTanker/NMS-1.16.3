/*     */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*     */ import net.minecraft.server.v1_16_R2.Entity;
/*     */ import net.minecraft.server.v1_16_R2.EntityCreeper;
/*     */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*     */ import net.minecraft.server.v1_16_R2.EntityMonster;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*     */ import org.bukkit.entity.Creeper;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.entity.CreeperPowerEvent;
/*     */ 
/*     */ public class CraftCreeper extends CraftMonster implements Creeper {
/*     */   public CraftCreeper(CraftServer server, EntityCreeper entity) {
/*  13 */     super(server, (EntityMonster)entity);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPowered() {
/*  18 */     return getHandle().isPowered();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPowered(boolean powered) {
/*  23 */     CraftServer server = this.server;
/*  24 */     Creeper entity = (Creeper)getHandle().getBukkitEntity();
/*     */     
/*  26 */     if (powered) {
/*  27 */       CreeperPowerEvent event = new CreeperPowerEvent(entity, CreeperPowerEvent.PowerCause.SET_ON);
/*  28 */       server.getPluginManager().callEvent((Event)event);
/*     */       
/*  30 */       if (!event.isCancelled()) {
/*  31 */         getHandle().setPowered(true);
/*     */       }
/*     */     } else {
/*  34 */       CreeperPowerEvent event = new CreeperPowerEvent(entity, CreeperPowerEvent.PowerCause.SET_OFF);
/*  35 */       server.getPluginManager().callEvent((Event)event);
/*     */       
/*  37 */       if (!event.isCancelled()) {
/*  38 */         getHandle().setPowered(false);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMaxFuseTicks(int ticks) {
/*  45 */     Preconditions.checkArgument((ticks >= 0), "ticks < 0");
/*     */     
/*  47 */     (getHandle()).maxFuseTicks = ticks;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxFuseTicks() {
/*  52 */     return (getHandle()).maxFuseTicks;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExplosionRadius(int radius) {
/*  57 */     Preconditions.checkArgument((radius >= 0), "radius < 0");
/*     */     
/*  59 */     (getHandle()).explosionRadius = radius;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getExplosionRadius() {
/*  64 */     return (getHandle()).explosionRadius;
/*     */   }
/*     */ 
/*     */   
/*     */   public void explode() {
/*  69 */     getHandle().explode();
/*     */   }
/*     */ 
/*     */   
/*     */   public void ignite() {
/*  74 */     getHandle().ignite();
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityCreeper getHandle() {
/*  79 */     return (EntityCreeper)this.entity;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  84 */     return "CraftCreeper";
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityType getType() {
/*  89 */     return EntityType.CREEPER;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIgnited(boolean ignited) {
/*  94 */     getHandle().setIgnited(ignited);
/*     */   }
/*     */   
/*     */   public boolean isIgnited() {
/*  98 */     return getHandle().isIgnited();
/*     */   }
/*     */   
/*     */   public int getFuseTicks() {
/* 102 */     return (getHandle()).fuseTicks;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftCreeper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */