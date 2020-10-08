/*     */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*     */ import net.minecraft.server.v1_16_R2.Entity;
/*     */ import net.minecraft.server.v1_16_R2.EntityCreature;
/*     */ import net.minecraft.server.v1_16_R2.EntityInsentient;
/*     */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*     */ import net.minecraft.server.v1_16_R2.EntityMonster;
/*     */ import net.minecraft.server.v1_16_R2.EntityZombie;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.entity.Villager;
/*     */ 
/*     */ public class CraftZombie extends CraftMonster implements Zombie {
/*     */   public CraftZombie(CraftServer server, EntityZombie entity) {
/*  14 */     super(server, (EntityMonster)entity);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityZombie getHandle() {
/*  19 */     return (EntityZombie)this.entity;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  24 */     return "CraftZombie";
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityType getType() {
/*  29 */     return EntityType.ZOMBIE;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBaby() {
/*  34 */     return getHandle().isBaby();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBaby(boolean flag) {
/*  39 */     getHandle().setBaby(flag);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isVillager() {
/*  44 */     return getHandle() instanceof net.minecraft.server.v1_16_R2.EntityZombieVillager;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setVillager(boolean flag) {
/*  49 */     throw new UnsupportedOperationException("Not supported.");
/*     */   }
/*     */ 
/*     */   
/*     */   public void setVillagerProfession(Villager.Profession profession) {
/*  54 */     throw new UnsupportedOperationException("Not supported.");
/*     */   }
/*     */ 
/*     */   
/*     */   public Villager.Profession getVillagerProfession() {
/*  59 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isConverting() {
/*  64 */     return getHandle().isDrownConverting();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getConversionTime() {
/*  69 */     Preconditions.checkState(isConverting(), "Entity not converting");
/*     */     
/*  71 */     return (getHandle()).drownedConversionTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setConversionTime(int time) {
/*  76 */     if (time < 0) {
/*  77 */       (getHandle()).drownedConversionTime = -1;
/*  78 */       getHandle().getDataWatcher().set(EntityZombie.DROWN_CONVERTING, Boolean.valueOf(false));
/*     */     } else {
/*  80 */       getHandle().startDrownedConversion(time);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getAge() {
/*  86 */     return getHandle().isBaby() ? -1 : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAge(int i) {
/*  91 */     getHandle().setBaby((i < 0));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAgeLock(boolean b) {}
/*     */ 
/*     */   
/*     */   public boolean isDrowning() {
/* 100 */     return getHandle().isDrownConverting();
/*     */   }
/*     */ 
/*     */   
/*     */   public void startDrowning(int drownedConversionTime) {
/* 105 */     getHandle().startDrownedConversion(drownedConversionTime);
/*     */   }
/*     */ 
/*     */   
/*     */   public void stopDrowning() {
/* 110 */     getHandle().stopDrowning();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldBurnInDay() {
/* 115 */     return getHandle().shouldBurnInDay();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isArmsRaised() {
/* 120 */     return getHandle().isAggressive();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setArmsRaised(boolean raised) {
/* 125 */     getHandle().setAggressive(raised);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setShouldBurnInDay(boolean shouldBurnInDay) {
/* 130 */     getHandle().setShouldBurnInDay(shouldBurnInDay);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getAgeLock() {
/* 136 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBaby() {
/* 141 */     getHandle().setBaby(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAdult() {
/* 146 */     getHandle().setBaby(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAdult() {
/* 151 */     return !getHandle().isBaby();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBreed() {
/* 156 */     return false;
/*     */   }
/*     */   
/*     */   public void setBreed(boolean b) {}
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftZombie.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */