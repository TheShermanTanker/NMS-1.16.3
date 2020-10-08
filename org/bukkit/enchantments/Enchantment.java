/*     */ package org.bukkit.enchantments;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.bukkit.Keyed;
/*     */ import org.bukkit.NamespacedKey;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.jetbrains.annotations.Contract;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class Enchantment
/*     */   implements Keyed
/*     */ {
/*  19 */   public static final Enchantment PROTECTION_ENVIRONMENTAL = new EnchantmentWrapper("protection");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  24 */   public static final Enchantment PROTECTION_FIRE = new EnchantmentWrapper("fire_protection");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  29 */   public static final Enchantment PROTECTION_FALL = new EnchantmentWrapper("feather_falling");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  34 */   public static final Enchantment PROTECTION_EXPLOSIONS = new EnchantmentWrapper("blast_protection");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  39 */   public static final Enchantment PROTECTION_PROJECTILE = new EnchantmentWrapper("projectile_protection");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  44 */   public static final Enchantment OXYGEN = new EnchantmentWrapper("respiration");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  49 */   public static final Enchantment WATER_WORKER = new EnchantmentWrapper("aqua_affinity");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  54 */   public static final Enchantment THORNS = new EnchantmentWrapper("thorns");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  59 */   public static final Enchantment DEPTH_STRIDER = new EnchantmentWrapper("depth_strider");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  64 */   public static final Enchantment FROST_WALKER = new EnchantmentWrapper("frost_walker");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  69 */   public static final Enchantment BINDING_CURSE = new EnchantmentWrapper("binding_curse");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  74 */   public static final Enchantment DAMAGE_ALL = new EnchantmentWrapper("sharpness");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  79 */   public static final Enchantment DAMAGE_UNDEAD = new EnchantmentWrapper("smite");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  84 */   public static final Enchantment DAMAGE_ARTHROPODS = new EnchantmentWrapper("bane_of_arthropods");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  89 */   public static final Enchantment KNOCKBACK = new EnchantmentWrapper("knockback");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  94 */   public static final Enchantment FIRE_ASPECT = new EnchantmentWrapper("fire_aspect");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  99 */   public static final Enchantment LOOT_BONUS_MOBS = new EnchantmentWrapper("looting");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 104 */   public static final Enchantment SWEEPING_EDGE = new EnchantmentWrapper("sweeping");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 109 */   public static final Enchantment DIG_SPEED = new EnchantmentWrapper("efficiency");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 115 */   public static final Enchantment SILK_TOUCH = new EnchantmentWrapper("silk_touch");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 120 */   public static final Enchantment DURABILITY = new EnchantmentWrapper("unbreaking");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 125 */   public static final Enchantment LOOT_BONUS_BLOCKS = new EnchantmentWrapper("fortune");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 130 */   public static final Enchantment ARROW_DAMAGE = new EnchantmentWrapper("power");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 135 */   public static final Enchantment ARROW_KNOCKBACK = new EnchantmentWrapper("punch");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 140 */   public static final Enchantment ARROW_FIRE = new EnchantmentWrapper("flame");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 145 */   public static final Enchantment ARROW_INFINITE = new EnchantmentWrapper("infinity");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 150 */   public static final Enchantment LUCK = new EnchantmentWrapper("luck_of_the_sea");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 155 */   public static final Enchantment LURE = new EnchantmentWrapper("lure");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 160 */   public static final Enchantment LOYALTY = new EnchantmentWrapper("loyalty");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 165 */   public static final Enchantment IMPALING = new EnchantmentWrapper("impaling");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 170 */   public static final Enchantment RIPTIDE = new EnchantmentWrapper("riptide");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 176 */   public static final Enchantment CHANNELING = new EnchantmentWrapper("channeling");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 181 */   public static final Enchantment MULTISHOT = new EnchantmentWrapper("multishot");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 186 */   public static final Enchantment QUICK_CHARGE = new EnchantmentWrapper("quick_charge");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 191 */   public static final Enchantment PIERCING = new EnchantmentWrapper("piercing");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 196 */   public static final Enchantment MENDING = new EnchantmentWrapper("mending");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 201 */   public static final Enchantment VANISHING_CURSE = new EnchantmentWrapper("vanishing_curse");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 206 */   public static final Enchantment SOUL_SPEED = new EnchantmentWrapper("soul_speed");
/*     */   
/* 208 */   private static final Map<NamespacedKey, Enchantment> byKey = new HashMap<>();
/* 209 */   private static final Map<String, Enchantment> byName = new HashMap<>();
/*     */   private static boolean acceptingNew = true;
/*     */   private final NamespacedKey key;
/*     */   
/*     */   public Enchantment(@NotNull NamespacedKey key) {
/* 214 */     this.key = key;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public NamespacedKey getKey() {
/* 220 */     return this.key;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   @NotNull
/*     */   public abstract String getName();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract int getMaxLevel();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract int getStartLevel();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public abstract EnchantmentTarget getItemTarget();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract boolean isTreasure();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public abstract boolean isCursed();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract boolean conflictsWith(@NotNull Enchantment paramEnchantment);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract boolean canEnchantItem(@NotNull ItemStack paramItemStack);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 300 */     if (obj == null) {
/* 301 */       return false;
/*     */     }
/* 303 */     if (!(obj instanceof Enchantment)) {
/* 304 */       return false;
/*     */     }
/* 306 */     Enchantment other = (Enchantment)obj;
/* 307 */     if (!this.key.equals(other.key)) {
/* 308 */       return false;
/*     */     }
/* 310 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 315 */     return this.key.hashCode();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 320 */     return "Enchantment[" + this.key + ", " + getName() + "]";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void registerEnchantment(@NotNull Enchantment enchantment) {
/* 331 */     if (byKey.containsKey(enchantment.key) || byName.containsKey(enchantment.getName()))
/* 332 */       throw new IllegalArgumentException("Cannot set already-set enchantment"); 
/* 333 */     if (!isAcceptingRegistrations()) {
/* 334 */       throw new IllegalStateException("No longer accepting new enchantments (can only be done by the server implementation)");
/*     */     }
/*     */     
/* 337 */     byKey.put(enchantment.key, enchantment);
/* 338 */     byName.put(enchantment.getName(), enchantment);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isAcceptingRegistrations() {
/* 347 */     return acceptingNew;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void stopAcceptingRegistrations() {
/* 354 */     acceptingNew = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Contract("null -> null")
/*     */   @Nullable
/*     */   public static Enchantment getByKey(@Nullable NamespacedKey key) {
/* 366 */     return byKey.get(key);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   @Contract("null -> null")
/*     */   @Nullable
/*     */   public static Enchantment getByName(@Nullable String name) {
/* 380 */     return byName.get(name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static Enchantment[] values() {
/* 390 */     return (Enchantment[])byName.values().toArray((Object[])new Enchantment[byName.size()]);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\enchantments\Enchantment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */