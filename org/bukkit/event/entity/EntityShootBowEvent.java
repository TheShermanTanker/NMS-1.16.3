/*     */ package org.bukkit.event.entity;
/*     */ 
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.event.Cancellable;
/*     */ import org.bukkit.event.HandlerList;
/*     */ import org.bukkit.inventory.EquipmentSlot;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class EntityShootBowEvent
/*     */   extends EntityEvent
/*     */   implements Cancellable
/*     */ {
/*  17 */   private static final HandlerList handlers = new HandlerList();
/*     */   private final ItemStack bow;
/*     */   private final ItemStack consumable;
/*     */   private Entity projectile;
/*     */   private final EquipmentSlot hand;
/*     */   private final float force;
/*     */   private boolean consumeItem;
/*     */   private boolean cancelled;
/*     */   
/*     */   @Deprecated
/*     */   public void setConsumeArrow(boolean consumeArrow) {
/*  28 */     setConsumeItem(consumeArrow);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public boolean getConsumeArrow() {
/*  33 */     return shouldConsumeItem();
/*     */   }
/*     */   @Deprecated
/*     */   @NotNull
/*     */   public ItemStack getArrowItem() {
/*  38 */     return getConsumable();
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public EntityShootBowEvent(@NotNull LivingEntity shooter, @Nullable ItemStack bow, @NotNull Entity projectile, float force) {
/*  43 */     this(shooter, bow, new ItemStack(Material.AIR), projectile, force);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public EntityShootBowEvent(@NotNull LivingEntity shooter, @Nullable ItemStack bow, @NotNull ItemStack arrowItem, @NotNull Entity projectile, float force) {
/*  48 */     this(shooter, bow, arrowItem, projectile, EquipmentSlot.HAND, force, true);
/*     */   }
/*     */   
/*     */   public EntityShootBowEvent(@NotNull LivingEntity shooter, @Nullable ItemStack bow, @Nullable ItemStack consumable, @NotNull Entity projectile, @NotNull EquipmentSlot hand, float force, boolean consumeItem) {
/*  52 */     super((Entity)shooter);
/*  53 */     this.bow = bow;
/*  54 */     this.consumable = consumable;
/*  55 */     this.projectile = projectile;
/*  56 */     this.hand = hand;
/*  57 */     this.force = force;
/*  58 */     this.consumeItem = consumeItem;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public LivingEntity getEntity() {
/*  64 */     return (LivingEntity)this.entity;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public ItemStack getBow() {
/*  74 */     return this.bow;
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
/*     */   @Nullable
/*     */   public ItemStack getConsumable() {
/*  87 */     return this.consumable;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Entity getProjectile() {
/*  97 */     return this.projectile;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setProjectile(@NotNull Entity projectile) {
/* 106 */     this.projectile = projectile;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public EquipmentSlot getHand() {
/* 116 */     return this.hand;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getForce() {
/* 125 */     return this.force;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConsumeItem(boolean consumeItem) {
/* 142 */     this.consumeItem = consumeItem;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldConsumeItem() {
/* 151 */     return this.consumeItem;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCancelled() {
/* 156 */     return this.cancelled;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCancelled(boolean cancel) {
/* 161 */     this.cancelled = cancel;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public HandlerList getHandlers() {
/* 167 */     return handlers;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static HandlerList getHandlerList() {
/* 172 */     return handlers;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\entity\EntityShootBowEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */