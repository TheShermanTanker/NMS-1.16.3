/*     */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*     */ import net.minecraft.server.v1_16_R2.Entity;
/*     */ import net.minecraft.server.v1_16_R2.EntityInsentient;
/*     */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*     */ import net.minecraft.server.v1_16_R2.EntityMonster;
/*     */ import net.minecraft.server.v1_16_R2.EntityPiglinAbstract;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*     */ 
/*     */ public class CraftPiglinAbstract extends CraftMonster implements PiglinAbstract {
/*     */   public CraftPiglinAbstract(CraftServer server, EntityPiglinAbstract entity) {
/*  11 */     super(server, (EntityMonster)entity);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isImmuneToZombification() {
/*  16 */     return getHandle().isImmuneToZombification();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setImmuneToZombification(boolean flag) {
/*  21 */     getHandle().setImmuneToZombification(flag);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getConversionTime() {
/*  26 */     Preconditions.checkState(isConverting(), "Entity not converting");
/*  27 */     return (getHandle()).conversionTicks;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setConversionTime(int time) {
/*  32 */     if (time < 0) {
/*  33 */       (getHandle()).conversionTicks = -1;
/*  34 */       getHandle().setImmuneToZombification(false);
/*     */     } else {
/*  36 */       (getHandle()).conversionTicks = time;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isConverting() {
/*  42 */     return getHandle().isConverting();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBaby() {
/*  47 */     return getHandle().isBaby();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBaby(boolean flag) {
/*  52 */     getHandle().setBaby(flag);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getAge() {
/*  57 */     return getHandle().isBaby() ? -1 : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAge(int i) {
/*  62 */     getHandle().setBaby((i < 0));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAgeLock(boolean b) {}
/*     */ 
/*     */   
/*     */   public boolean getAgeLock() {
/*  71 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBaby() {
/*  76 */     getHandle().setBaby(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAdult() {
/*  81 */     getHandle().setBaby(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAdult() {
/*  86 */     return !getHandle().isBaby();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBreed() {
/*  91 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBreed(boolean b) {}
/*     */ 
/*     */   
/*     */   public EntityPiglinAbstract getHandle() {
/* 100 */     return (EntityPiglinAbstract)super.getHandle();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftPiglinAbstract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */