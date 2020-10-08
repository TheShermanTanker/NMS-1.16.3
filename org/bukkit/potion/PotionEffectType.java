/*     */ package org.bukkit.potion;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.Color;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class PotionEffectType
/*     */ {
/*  18 */   public static final PotionEffectType SPEED = new PotionEffectTypeWrapper(1);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  23 */   public static final PotionEffectType SLOW = new PotionEffectTypeWrapper(2);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  28 */   public static final PotionEffectType FAST_DIGGING = new PotionEffectTypeWrapper(3);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  33 */   public static final PotionEffectType SLOW_DIGGING = new PotionEffectTypeWrapper(4);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  38 */   public static final PotionEffectType INCREASE_DAMAGE = new PotionEffectTypeWrapper(5);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  43 */   public static final PotionEffectType HEAL = new PotionEffectTypeWrapper(6);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  48 */   public static final PotionEffectType HARM = new PotionEffectTypeWrapper(7);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  53 */   public static final PotionEffectType JUMP = new PotionEffectTypeWrapper(8);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  58 */   public static final PotionEffectType CONFUSION = new PotionEffectTypeWrapper(9);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  63 */   public static final PotionEffectType REGENERATION = new PotionEffectTypeWrapper(10);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  68 */   public static final PotionEffectType DAMAGE_RESISTANCE = new PotionEffectTypeWrapper(11);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  73 */   public static final PotionEffectType FIRE_RESISTANCE = new PotionEffectTypeWrapper(12);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  78 */   public static final PotionEffectType WATER_BREATHING = new PotionEffectTypeWrapper(13);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  83 */   public static final PotionEffectType INVISIBILITY = new PotionEffectTypeWrapper(14);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  88 */   public static final PotionEffectType BLINDNESS = new PotionEffectTypeWrapper(15);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  93 */   public static final PotionEffectType NIGHT_VISION = new PotionEffectTypeWrapper(16);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  98 */   public static final PotionEffectType HUNGER = new PotionEffectTypeWrapper(17);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 103 */   public static final PotionEffectType WEAKNESS = new PotionEffectTypeWrapper(18);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 108 */   public static final PotionEffectType POISON = new PotionEffectTypeWrapper(19);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 114 */   public static final PotionEffectType WITHER = new PotionEffectTypeWrapper(20);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 119 */   public static final PotionEffectType HEALTH_BOOST = new PotionEffectTypeWrapper(21);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 125 */   public static final PotionEffectType ABSORPTION = new PotionEffectTypeWrapper(22);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 130 */   public static final PotionEffectType SATURATION = new PotionEffectTypeWrapper(23);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 135 */   public static final PotionEffectType GLOWING = new PotionEffectTypeWrapper(24);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 140 */   public static final PotionEffectType LEVITATION = new PotionEffectTypeWrapper(25);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 145 */   public static final PotionEffectType LUCK = new PotionEffectTypeWrapper(26);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 150 */   public static final PotionEffectType UNLUCK = new PotionEffectTypeWrapper(27);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 155 */   public static final PotionEffectType SLOW_FALLING = new PotionEffectTypeWrapper(28);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 160 */   public static final PotionEffectType CONDUIT_POWER = new PotionEffectTypeWrapper(29);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 165 */   public static final PotionEffectType DOLPHINS_GRACE = new PotionEffectTypeWrapper(30);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 170 */   public static final PotionEffectType BAD_OMEN = new PotionEffectTypeWrapper(31);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 175 */   public static final PotionEffectType HERO_OF_THE_VILLAGE = new PotionEffectTypeWrapper(32);
/*     */   
/*     */   private final int id;
/*     */   
/*     */   protected PotionEffectType(int id) {
/* 180 */     this.id = id;
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
/*     */   @NotNull
/*     */   public PotionEffect createEffect(int duration, int amplifier) {
/* 194 */     return new PotionEffect(this, isInstant() ? 1 : (int)(duration * getDurationModifier()), amplifier);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public abstract double getDurationModifier();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public int getId() {
/* 214 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public abstract String getName();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract boolean isInstant();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public abstract Color getColor();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 242 */     if (obj == null) {
/* 243 */       return false;
/*     */     }
/* 245 */     if (!(obj instanceof PotionEffectType)) {
/* 246 */       return false;
/*     */     }
/* 248 */     PotionEffectType other = (PotionEffectType)obj;
/* 249 */     if (this.id != other.id) {
/* 250 */       return false;
/*     */     }
/* 252 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 257 */     return this.id;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 262 */     return "PotionEffectType[" + this.id + ", " + getName() + "]";
/*     */   }
/*     */   
/* 265 */   private static final PotionEffectType[] byId = new PotionEffectType[33];
/* 266 */   private static final Map<String, PotionEffectType> byName = new HashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean acceptingNew = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   @Nullable
/*     */   public static PotionEffectType getById(int id) {
/* 280 */     if (id >= byId.length || id < 0)
/* 281 */       return null; 
/* 282 */     return byId[id];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static PotionEffectType getByName(@NotNull String name) {
/* 293 */     Validate.notNull(name, "name cannot be null");
/* 294 */     return byName.get(name.toLowerCase(Locale.ENGLISH));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void registerPotionEffectType(@NotNull PotionEffectType type) {
/* 305 */     if (byId[type.id] != null || byName.containsKey(type.getName().toLowerCase(Locale.ENGLISH)))
/* 306 */       throw new IllegalArgumentException("Cannot set already-set type"); 
/* 307 */     if (!acceptingNew) {
/* 308 */       throw new IllegalStateException("No longer accepting new potion effect types (can only be done by the server implementation)");
/*     */     }
/*     */ 
/*     */     
/* 312 */     byId[type.id] = type;
/* 313 */     byName.put(type.getName().toLowerCase(Locale.ENGLISH), type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void stopAcceptingRegistrations() {
/* 320 */     acceptingNew = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static PotionEffectType[] values() {
/* 331 */     return Arrays.<PotionEffectType>copyOfRange(byId, 1, byId.length);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\potion\PotionEffectType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */