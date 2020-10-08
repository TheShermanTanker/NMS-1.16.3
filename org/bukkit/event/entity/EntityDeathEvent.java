/*     */ package org.bukkit.event.entity;
/*     */ import java.util.List;
/*     */ import org.bukkit.Sound;
/*     */ import org.bukkit.SoundCategory;
/*     */ import org.bukkit.attribute.Attribute;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.event.HandlerList;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class EntityDeathEvent extends EntityEvent implements Cancellable {
/*  14 */   private static final HandlerList handlers = new HandlerList();
/*     */   private final List<ItemStack> drops;
/*  16 */   private int dropExp = 0;
/*     */   
/*     */   private boolean cancelled;
/*  19 */   private double reviveHealth = 0.0D; private boolean shouldPlayDeathSound;
/*     */   @Nullable
/*     */   private Sound deathSound;
/*     */   @Nullable
/*     */   private SoundCategory deathSoundCategory;
/*     */   private float deathSoundVolume;
/*     */   private float deathSoundPitch;
/*     */   
/*     */   public EntityDeathEvent(@NotNull LivingEntity entity, @NotNull List<ItemStack> drops) {
/*  28 */     this(entity, drops, 0);
/*     */   }
/*     */   
/*     */   public EntityDeathEvent(@NotNull LivingEntity what, @NotNull List<ItemStack> drops, int droppedExp) {
/*  32 */     super((Entity)what);
/*  33 */     this.drops = drops;
/*  34 */     this.dropExp = droppedExp;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public LivingEntity getEntity() {
/*  40 */     return (LivingEntity)this.entity;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDroppedExp() {
/*  52 */     return this.dropExp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDroppedExp(int exp) {
/*  64 */     this.dropExp = exp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public List<ItemStack> getDrops() {
/*  74 */     return this.drops;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public HandlerList getHandlers() {
/*  80 */     return handlers;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static HandlerList getHandlerList() {
/*  85 */     return handlers;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCancelled() {
/*  91 */     return this.cancelled;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCancelled(boolean cancel) {
/*  96 */     this.cancelled = cancel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getReviveHealth() {
/* 106 */     return this.reviveHealth;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReviveHealth(double reviveHealth) throws IllegalArgumentException {
/* 117 */     double maxHealth = ((LivingEntity)this.entity).getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
/* 118 */     if ((maxHealth != 0.0D && reviveHealth <= 0.0D) || reviveHealth > maxHealth) {
/* 119 */       throw new IllegalArgumentException("Health must be between 0 (exclusive) and " + maxHealth + " (inclusive), but was " + reviveHealth);
/*     */     }
/* 121 */     this.reviveHealth = reviveHealth;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldPlayDeathSound() {
/* 131 */     return this.shouldPlayDeathSound;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setShouldPlayDeathSound(boolean playDeathSound) {
/* 140 */     this.shouldPlayDeathSound = playDeathSound;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Sound getDeathSound() {
/* 150 */     return this.deathSound;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDeathSound(@Nullable Sound sound) {
/* 159 */     this.deathSound = sound;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public SoundCategory getDeathSoundCategory() {
/* 169 */     return this.deathSoundCategory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDeathSoundCategory(@Nullable SoundCategory soundCategory) {
/* 178 */     this.deathSoundCategory = soundCategory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getDeathSoundVolume() {
/* 187 */     return this.deathSoundVolume;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDeathSoundVolume(float volume) {
/* 196 */     this.deathSoundVolume = volume;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getDeathSoundPitch() {
/* 205 */     return this.deathSoundPitch;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDeathSoundPitch(float pitch) {
/* 214 */     this.deathSoundPitch = pitch;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\entity\EntityDeathEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */