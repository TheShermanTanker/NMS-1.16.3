/*     */ package org.bukkit.event.entity;
/*     */ 
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.event.Cancellable;
/*     */ import org.bukkit.event.HandlerList;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class EntityBreedEvent
/*     */   extends EntityEvent
/*     */   implements Cancellable
/*     */ {
/*  16 */   private static final HandlerList handlers = new HandlerList();
/*     */   
/*     */   private final LivingEntity mother;
/*     */   
/*     */   private final LivingEntity father;
/*     */   private final LivingEntity breeder;
/*     */   private final ItemStack bredWith;
/*     */   private int experience;
/*     */   private boolean cancel;
/*     */   
/*     */   public EntityBreedEvent(@NotNull LivingEntity child, @NotNull LivingEntity mother, @NotNull LivingEntity father, @Nullable LivingEntity breeder, @Nullable ItemStack bredWith, int experience) {
/*  27 */     super((Entity)child);
/*     */     
/*  29 */     Validate.notNull(child, "Cannot have null child");
/*  30 */     Validate.notNull(mother, "Cannot have null mother");
/*  31 */     Validate.notNull(father, "Cannot have null father");
/*     */ 
/*     */     
/*  34 */     this.mother = mother;
/*  35 */     this.father = father;
/*  36 */     this.breeder = breeder;
/*  37 */     this.bredWith = bredWith;
/*     */     
/*  39 */     setExperience(experience);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public LivingEntity getEntity() {
/*  45 */     return (LivingEntity)this.entity;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public LivingEntity getMother() {
/*  55 */     return this.mother;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public LivingEntity getFather() {
/*  65 */     return this.father;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public LivingEntity getBreeder() {
/*  76 */     return this.breeder;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public ItemStack getBredWith() {
/*  86 */     return this.bredWith;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getExperience() {
/*  95 */     return this.experience;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExperience(int experience) {
/* 104 */     Validate.isTrue((experience >= 0), "Experience cannot be negative");
/* 105 */     this.experience = experience;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCancelled() {
/* 110 */     return this.cancel;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCancelled(boolean cancel) {
/* 115 */     this.cancel = cancel;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public HandlerList getHandlers() {
/* 121 */     return handlers;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static HandlerList getHandlerList() {
/* 126 */     return handlers;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\entity\EntityBreedEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */