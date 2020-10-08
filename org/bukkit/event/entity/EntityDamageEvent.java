/*     */ package org.bukkit.event.entity;
/*     */ 
/*     */ import com.google.common.base.Function;
/*     */ import com.google.common.base.Functions;
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import java.util.EnumMap;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.event.Cancellable;
/*     */ import org.bukkit.event.HandlerList;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityDamageEvent
/*     */   extends EntityEvent
/*     */   implements Cancellable
/*     */ {
/*  20 */   private static final HandlerList handlers = new HandlerList();
/*  21 */   private static final DamageModifier[] MODIFIERS = DamageModifier.values();
/*  22 */   private static final Function<? super Double, Double> ZERO = Functions.constant(Double.valueOf(-0.0D));
/*     */   private final Map<DamageModifier, Double> modifiers;
/*     */   private final Map<DamageModifier, ? extends Function<? super Double, Double>> modifierFunctions;
/*     */   private final Map<DamageModifier, Double> originals;
/*     */   private boolean cancelled;
/*     */   private final DamageCause cause;
/*     */   
/*     */   public EntityDamageEvent(@NotNull Entity damagee, @NotNull DamageCause cause, double damage) {
/*  30 */     this(damagee, cause, new EnumMap<>((Map<DamageModifier, ? extends Double>)ImmutableMap.of(DamageModifier.BASE, Double.valueOf(damage))), new EnumMap<>((Map<DamageModifier, ? extends Function<? super Double, Double>>)ImmutableMap.of(DamageModifier.BASE, ZERO)));
/*     */   }
/*     */   
/*     */   public EntityDamageEvent(@NotNull Entity damagee, @NotNull DamageCause cause, @NotNull Map<DamageModifier, Double> modifiers, @NotNull Map<DamageModifier, ? extends Function<? super Double, Double>> modifierFunctions) {
/*  34 */     super(damagee);
/*  35 */     Validate.isTrue(modifiers.containsKey(DamageModifier.BASE), "BASE DamageModifier missing");
/*  36 */     Validate.isTrue(!modifiers.containsKey(null), "Cannot have null DamageModifier");
/*  37 */     Validate.noNullElements(modifiers.values(), "Cannot have null modifier values");
/*  38 */     Validate.isTrue(modifiers.keySet().equals(modifierFunctions.keySet()), "Must have a modifier function for each DamageModifier");
/*  39 */     Validate.noNullElements(modifierFunctions.values(), "Cannot have null modifier function");
/*  40 */     this.originals = new EnumMap<>(modifiers);
/*  41 */     this.cause = cause;
/*  42 */     this.modifiers = modifiers;
/*  43 */     this.modifierFunctions = modifierFunctions;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCancelled() {
/*  48 */     return this.cancelled;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCancelled(boolean cancel) {
/*  53 */     this.cancelled = cancel;
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
/*     */   public double getOriginalDamage(@NotNull DamageModifier type) throws IllegalArgumentException {
/*  65 */     Double damage = this.originals.get(type);
/*  66 */     if (damage != null) {
/*  67 */       return damage.doubleValue();
/*     */     }
/*  69 */     if (type == null) {
/*  70 */       throw new IllegalArgumentException("Cannot have null DamageModifier");
/*     */     }
/*  72 */     return 0.0D;
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
/*     */   public void setDamage(@NotNull DamageModifier type, double damage) throws IllegalArgumentException, UnsupportedOperationException {
/*  87 */     if (!this.modifiers.containsKey(type)) {
/*  88 */       throw (type == null) ? new IllegalArgumentException("Cannot have null DamageModifier") : new UnsupportedOperationException(type + " is not applicable to " + getEntity());
/*     */     }
/*  90 */     this.modifiers.put(type, Double.valueOf(damage));
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
/*     */   public double getDamage(@NotNull DamageModifier type) throws IllegalArgumentException {
/* 102 */     Validate.notNull(type, "Cannot have null DamageModifier");
/* 103 */     Double damage = this.modifiers.get(type);
/* 104 */     return (damage == null) ? 0.0D : damage.doubleValue();
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
/*     */   public boolean isApplicable(@NotNull DamageModifier type) throws IllegalArgumentException {
/* 119 */     Validate.notNull(type, "Cannot have null DamageModifier");
/* 120 */     return this.modifiers.containsKey(type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getDamage() {
/* 130 */     return getDamage(DamageModifier.BASE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final double getFinalDamage() {
/* 140 */     double damage = 0.0D;
/* 141 */     for (DamageModifier modifier : MODIFIERS) {
/* 142 */       damage += getDamage(modifier);
/*     */     }
/* 144 */     return damage;
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
/*     */   public void setDamage(double damage) {
/* 158 */     double remaining = damage;
/* 159 */     double oldRemaining = getDamage(DamageModifier.BASE);
/* 160 */     for (DamageModifier modifier : MODIFIERS) {
/* 161 */       if (isApplicable(modifier)) {
/*     */ 
/*     */ 
/*     */         
/* 165 */         Function<? super Double, Double> modifierFunction = this.modifierFunctions.get(modifier);
/* 166 */         double newVanilla = ((Double)modifierFunction.apply(Double.valueOf(remaining))).doubleValue();
/* 167 */         double oldVanilla = ((Double)modifierFunction.apply(Double.valueOf(oldRemaining))).doubleValue();
/* 168 */         double difference = oldVanilla - newVanilla;
/*     */ 
/*     */         
/* 171 */         double old = getDamage(modifier);
/* 172 */         if (old > 0.0D) {
/* 173 */           setDamage(modifier, Math.max(0.0D, old - difference));
/*     */         } else {
/* 175 */           setDamage(modifier, Math.min(0.0D, old - difference));
/*     */         } 
/* 177 */         remaining += newVanilla;
/* 178 */         oldRemaining += oldVanilla;
/*     */       } 
/*     */     } 
/* 181 */     setDamage(DamageModifier.BASE, damage);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public DamageCause getCause() {
/* 191 */     return this.cause;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public HandlerList getHandlers() {
/* 197 */     return handlers;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static HandlerList getHandlerList() {
/* 202 */     return handlers;
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
/*     */   @Deprecated
/*     */   public enum DamageModifier
/*     */   {
/* 219 */     BASE,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 224 */     HARD_HAT,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 229 */     BLOCKING,
/*     */ 
/*     */ 
/*     */     
/* 233 */     ARMOR,
/*     */ 
/*     */ 
/*     */     
/* 237 */     RESISTANCE,
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
/* 248 */     MAGIC,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 253 */     ABSORPTION;
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
/*     */   public enum DamageCause
/*     */   {
/* 267 */     CONTACT,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 273 */     ENTITY_ATTACK,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 279 */     ENTITY_SWEEP_ATTACK,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 285 */     PROJECTILE,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 291 */     SUFFOCATION,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 297 */     FALL,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 303 */     FIRE,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 309 */     FIRE_TICK,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 315 */     MELTING,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 321 */     LAVA,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 327 */     DROWNING,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 333 */     BLOCK_EXPLOSION,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 340 */     ENTITY_EXPLOSION,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 346 */     VOID,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 352 */     LIGHTNING,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 358 */     SUICIDE,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 364 */     STARVATION,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 370 */     POISON,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 376 */     MAGIC,
/*     */ 
/*     */ 
/*     */     
/* 380 */     WITHER,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 388 */     FALLING_BLOCK,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 395 */     THORNS,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 401 */     DRAGON_BREATH,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 407 */     CUSTOM,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 413 */     FLY_INTO_WALL,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 419 */     HOT_FLOOR,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 426 */     CRAMMING,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 432 */     DRYOUT;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\entity\EntityDamageEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */