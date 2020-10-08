/*     */ package org.bukkit.event.entity;
/*     */ 
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.event.Cancellable;
/*     */ import org.bukkit.event.HandlerList;
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ import org.bukkit.potion.PotionEffectType;
/*     */ import org.jetbrains.annotations.Contract;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityPotionEffectEvent
/*     */   extends EntityEvent
/*     */   implements Cancellable
/*     */ {
/*  19 */   private static final HandlerList handlers = new HandlerList();
/*     */   private boolean cancel;
/*     */   private final PotionEffect oldEffect;
/*     */   private final PotionEffect newEffect;
/*     */   private final Cause cause;
/*     */   private final Action action;
/*     */   private boolean override;
/*     */   
/*     */   @Contract("_, null, null, _, _, _ -> fail")
/*     */   public EntityPotionEffectEvent(@NotNull LivingEntity livingEntity, @Nullable PotionEffect oldEffect, @Nullable PotionEffect newEffect, @NotNull Cause cause, @NotNull Action action, boolean override) {
/*  29 */     super((Entity)livingEntity);
/*  30 */     this.oldEffect = oldEffect;
/*  31 */     this.newEffect = newEffect;
/*  32 */     this.cause = cause;
/*  33 */     this.action = action;
/*  34 */     this.override = override;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public PotionEffect getOldEffect() {
/*  45 */     return this.oldEffect;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public PotionEffect getNewEffect() {
/*  56 */     return this.newEffect;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Cause getCause() {
/*  66 */     return this.cause;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Action getAction() {
/*  76 */     return this.action;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public PotionEffectType getModifiedType() {
/*  86 */     return (this.oldEffect == null) ? ((this.newEffect == null) ? null : this.newEffect.getType()) : this.oldEffect.getType();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isOverride() {
/*  96 */     return this.override;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOverride(boolean override) {
/* 106 */     this.override = override;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCancelled() {
/* 111 */     return this.cancel;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCancelled(boolean cancel) {
/* 116 */     this.cancel = cancel;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public HandlerList getHandlers() {
/* 122 */     return handlers;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static HandlerList getHandlerList() {
/* 127 */     return handlers;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum Action
/*     */   {
/* 139 */     ADDED,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 144 */     CHANGED,
/*     */ 
/*     */ 
/*     */     
/* 148 */     CLEARED,
/*     */ 
/*     */ 
/*     */     
/* 152 */     REMOVED;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum Cause
/*     */   {
/* 163 */     AREA_EFFECT_CLOUD,
/*     */ 
/*     */ 
/*     */     
/* 167 */     ARROW,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 172 */     ATTACK,
/*     */ 
/*     */ 
/*     */     
/* 176 */     BEACON,
/*     */ 
/*     */ 
/*     */     
/* 180 */     COMMAND,
/*     */ 
/*     */ 
/*     */     
/* 184 */     CONDUIT,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 189 */     CONVERSION,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 194 */     DEATH,
/*     */ 
/*     */ 
/*     */     
/* 198 */     DOLPHIN,
/*     */ 
/*     */ 
/*     */     
/* 202 */     EXPIRATION,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 207 */     FOOD,
/*     */ 
/*     */ 
/*     */     
/* 211 */     ILLUSION,
/*     */ 
/*     */ 
/*     */     
/* 215 */     MILK,
/*     */ 
/*     */ 
/*     */     
/* 219 */     PATROL_CAPTAIN,
/*     */ 
/*     */ 
/*     */     
/* 223 */     PLUGIN,
/*     */ 
/*     */ 
/*     */     
/* 227 */     POTION_DRINK,
/*     */ 
/*     */ 
/*     */     
/* 231 */     POTION_SPLASH,
/*     */ 
/*     */ 
/*     */     
/* 235 */     SPIDER_SPAWN,
/*     */ 
/*     */ 
/*     */     
/* 239 */     TOTEM,
/*     */ 
/*     */ 
/*     */     
/* 243 */     TURTLE_HELMET,
/*     */ 
/*     */ 
/*     */     
/* 247 */     UNKNOWN,
/*     */ 
/*     */ 
/*     */     
/* 251 */     VILLAGER_TRADE,
/*     */ 
/*     */ 
/*     */     
/* 255 */     WITHER_ROSE;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\entity\EntityPotionEffectEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */