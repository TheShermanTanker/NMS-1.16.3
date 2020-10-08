/*    */ package org.bukkit.potion;
/*    */ 
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum PotionType
/*    */ {
/* 10 */   UNCRAFTABLE(null, false, false),
/* 11 */   WATER(null, false, false),
/* 12 */   MUNDANE(null, false, false),
/* 13 */   THICK(null, false, false),
/* 14 */   AWKWARD(null, false, false),
/* 15 */   NIGHT_VISION(PotionEffectType.NIGHT_VISION, false, true),
/* 16 */   INVISIBILITY(PotionEffectType.INVISIBILITY, false, true),
/* 17 */   JUMP(PotionEffectType.JUMP, true, true),
/* 18 */   FIRE_RESISTANCE(PotionEffectType.FIRE_RESISTANCE, false, true),
/* 19 */   SPEED(PotionEffectType.SPEED, true, true),
/* 20 */   SLOWNESS(PotionEffectType.SLOW, true, true),
/* 21 */   WATER_BREATHING(PotionEffectType.WATER_BREATHING, false, true),
/* 22 */   INSTANT_HEAL(PotionEffectType.HEAL, true, false),
/* 23 */   INSTANT_DAMAGE(PotionEffectType.HARM, true, false),
/* 24 */   POISON(PotionEffectType.POISON, true, true),
/* 25 */   REGEN(PotionEffectType.REGENERATION, true, true),
/* 26 */   STRENGTH(PotionEffectType.INCREASE_DAMAGE, true, true),
/* 27 */   WEAKNESS(PotionEffectType.WEAKNESS, false, true),
/* 28 */   LUCK(PotionEffectType.LUCK, false, false),
/* 29 */   TURTLE_MASTER(PotionEffectType.SLOW, true, true),
/* 30 */   SLOW_FALLING(PotionEffectType.SLOW_FALLING, false, true);
/*    */   
/*    */   private final PotionEffectType effect;
/*    */   
/*    */   private final boolean upgradeable;
/*    */   private final boolean extendable;
/*    */   
/*    */   PotionType(PotionEffectType effect, boolean upgradeable, boolean extendable) {
/* 38 */     this.effect = effect;
/* 39 */     this.upgradeable = upgradeable;
/* 40 */     this.extendable = extendable;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public PotionEffectType getEffectType() {
/* 45 */     return this.effect;
/*    */   }
/*    */   
/*    */   public boolean isInstant() {
/* 49 */     return (this.effect != null && this.effect.isInstant());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isUpgradeable() {
/* 60 */     return this.upgradeable;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isExtendable() {
/* 70 */     return this.extendable;
/*    */   }
/*    */   
/*    */   public int getMaxLevel() {
/* 74 */     return this.upgradeable ? 2 : 1;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   @Nullable
/*    */   public static PotionType getByEffect(@Nullable PotionEffectType effectType) {
/* 85 */     if (effectType == null)
/* 86 */       return WATER; 
/* 87 */     for (PotionType type : values()) {
/* 88 */       if (effectType.equals(type.effect))
/* 89 */         return type; 
/*    */     } 
/* 91 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\potion\PotionType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */