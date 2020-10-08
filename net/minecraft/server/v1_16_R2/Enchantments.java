/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import org.bukkit.enchantments.Enchantment;
/*    */ 
/*    */ public class Enchantments {
/*  5 */   private static final EnumItemSlot[] M = new EnumItemSlot[] { EnumItemSlot.HEAD, EnumItemSlot.CHEST, EnumItemSlot.LEGS, EnumItemSlot.FEET };
/*  6 */   public static final Enchantment PROTECTION_ENVIRONMENTAL = a("protection", new EnchantmentProtection(Enchantment.Rarity.COMMON, EnchantmentProtection.DamageType.ALL, M));
/*  7 */   public static final Enchantment PROTECTION_FIRE = a("fire_protection", new EnchantmentProtection(Enchantment.Rarity.UNCOMMON, EnchantmentProtection.DamageType.FIRE, M));
/*  8 */   public static final Enchantment PROTECTION_FALL = a("feather_falling", new EnchantmentProtection(Enchantment.Rarity.UNCOMMON, EnchantmentProtection.DamageType.FALL, M));
/*  9 */   public static final Enchantment PROTECTION_EXPLOSIONS = a("blast_protection", new EnchantmentProtection(Enchantment.Rarity.RARE, EnchantmentProtection.DamageType.EXPLOSION, M));
/* 10 */   public static final Enchantment PROTECTION_PROJECTILE = a("projectile_protection", new EnchantmentProtection(Enchantment.Rarity.UNCOMMON, EnchantmentProtection.DamageType.PROJECTILE, M));
/* 11 */   public static final Enchantment OXYGEN = a("respiration", new EnchantmentOxygen(Enchantment.Rarity.RARE, M));
/* 12 */   public static final Enchantment WATER_WORKER = a("aqua_affinity", new EnchantmentWaterWorker(Enchantment.Rarity.RARE, M));
/* 13 */   public static final Enchantment THORNS = a("thorns", new EnchantmentThorns(Enchantment.Rarity.VERY_RARE, M));
/* 14 */   public static final Enchantment DEPTH_STRIDER = a("depth_strider", new EnchantmentDepthStrider(Enchantment.Rarity.RARE, M));
/* 15 */   public static final Enchantment FROST_WALKER = a("frost_walker", new EnchantmentFrostWalker(Enchantment.Rarity.RARE, new EnumItemSlot[] { EnumItemSlot.FEET }));
/* 16 */   public static final Enchantment BINDING_CURSE = a("binding_curse", new EnchantmentBinding(Enchantment.Rarity.VERY_RARE, M));
/* 17 */   public static final Enchantment SOUL_SPEED = a("soul_speed", new EnchantmentSoulSpeed(Enchantment.Rarity.VERY_RARE, new EnumItemSlot[] { EnumItemSlot.FEET }));
/* 18 */   public static final Enchantment DAMAGE_ALL = a("sharpness", new EnchantmentWeaponDamage(Enchantment.Rarity.COMMON, 0, new EnumItemSlot[] { EnumItemSlot.MAINHAND }));
/* 19 */   public static final Enchantment DAMAGE_UNDEAD = a("smite", new EnchantmentWeaponDamage(Enchantment.Rarity.UNCOMMON, 1, new EnumItemSlot[] { EnumItemSlot.MAINHAND }));
/* 20 */   public static final Enchantment DAMAGE_ARTHROPODS = a("bane_of_arthropods", new EnchantmentWeaponDamage(Enchantment.Rarity.UNCOMMON, 2, new EnumItemSlot[] { EnumItemSlot.MAINHAND }));
/* 21 */   public static final Enchantment KNOCKBACK = a("knockback", new EnchantmentKnockback(Enchantment.Rarity.UNCOMMON, new EnumItemSlot[] { EnumItemSlot.MAINHAND }));
/* 22 */   public static final Enchantment FIRE_ASPECT = a("fire_aspect", new EnchantmentFire(Enchantment.Rarity.RARE, new EnumItemSlot[] { EnumItemSlot.MAINHAND }));
/* 23 */   public static final Enchantment LOOT_BONUS_MOBS = a("looting", new EnchantmentLootBonus(Enchantment.Rarity.RARE, EnchantmentSlotType.WEAPON, new EnumItemSlot[] { EnumItemSlot.MAINHAND }));
/* 24 */   public static final Enchantment SWEEPING = a("sweeping", new EnchantmentSweeping(Enchantment.Rarity.RARE, new EnumItemSlot[] { EnumItemSlot.MAINHAND }));
/* 25 */   public static final Enchantment DIG_SPEED = a("efficiency", new EnchantmentDigging(Enchantment.Rarity.COMMON, new EnumItemSlot[] { EnumItemSlot.MAINHAND }));
/* 26 */   public static final Enchantment SILK_TOUCH = a("silk_touch", new EnchantmentSilkTouch(Enchantment.Rarity.VERY_RARE, new EnumItemSlot[] { EnumItemSlot.MAINHAND }));
/* 27 */   public static final Enchantment DURABILITY = a("unbreaking", new EnchantmentDurability(Enchantment.Rarity.UNCOMMON, new EnumItemSlot[] { EnumItemSlot.MAINHAND }));
/* 28 */   public static final Enchantment LOOT_BONUS_BLOCKS = a("fortune", new EnchantmentLootBonus(Enchantment.Rarity.RARE, EnchantmentSlotType.DIGGER, new EnumItemSlot[] { EnumItemSlot.MAINHAND }));
/* 29 */   public static final Enchantment ARROW_DAMAGE = a("power", new EnchantmentArrowDamage(Enchantment.Rarity.COMMON, new EnumItemSlot[] { EnumItemSlot.MAINHAND }));
/* 30 */   public static final Enchantment ARROW_KNOCKBACK = a("punch", new EnchantmentArrowKnockback(Enchantment.Rarity.RARE, new EnumItemSlot[] { EnumItemSlot.MAINHAND }));
/* 31 */   public static final Enchantment ARROW_FIRE = a("flame", new EnchantmentFlameArrows(Enchantment.Rarity.RARE, new EnumItemSlot[] { EnumItemSlot.MAINHAND }));
/* 32 */   public static final Enchantment ARROW_INFINITE = a("infinity", new EnchantmentInfiniteArrows(Enchantment.Rarity.VERY_RARE, new EnumItemSlot[] { EnumItemSlot.MAINHAND }));
/* 33 */   public static final Enchantment LUCK = a("luck_of_the_sea", new EnchantmentLootBonus(Enchantment.Rarity.RARE, EnchantmentSlotType.FISHING_ROD, new EnumItemSlot[] { EnumItemSlot.MAINHAND }));
/* 34 */   public static final Enchantment LURE = a("lure", new EnchantmentLure(Enchantment.Rarity.RARE, EnchantmentSlotType.FISHING_ROD, new EnumItemSlot[] { EnumItemSlot.MAINHAND }));
/* 35 */   public static final Enchantment LOYALTY = a("loyalty", new EnchantmentTridentLoyalty(Enchantment.Rarity.UNCOMMON, new EnumItemSlot[] { EnumItemSlot.MAINHAND }));
/* 36 */   public static final Enchantment IMPALING = a("impaling", new EnchantmentTridentImpaling(Enchantment.Rarity.RARE, new EnumItemSlot[] { EnumItemSlot.MAINHAND }));
/* 37 */   public static final Enchantment RIPTIDE = a("riptide", new EnchantmentTridentRiptide(Enchantment.Rarity.RARE, new EnumItemSlot[] { EnumItemSlot.MAINHAND }));
/* 38 */   public static final Enchantment CHANNELING = a("channeling", new EnchantmentTridentChanneling(Enchantment.Rarity.VERY_RARE, new EnumItemSlot[] { EnumItemSlot.MAINHAND }));
/* 39 */   public static final Enchantment MULTISHOT = a("multishot", new EnchantmentMultishot(Enchantment.Rarity.RARE, new EnumItemSlot[] { EnumItemSlot.MAINHAND }));
/* 40 */   public static final Enchantment QUICK_CHARGE = a("quick_charge", new EnchantmentQuickCharge(Enchantment.Rarity.UNCOMMON, new EnumItemSlot[] { EnumItemSlot.MAINHAND }));
/* 41 */   public static final Enchantment PIERCING = a("piercing", new EnchantmentPiercing(Enchantment.Rarity.COMMON, new EnumItemSlot[] { EnumItemSlot.MAINHAND }));
/* 42 */   public static final Enchantment MENDING = a("mending", new EnchantmentMending(Enchantment.Rarity.RARE, EnumItemSlot.values()));
/* 43 */   public static final Enchantment VANISHING_CURSE = a("vanishing_curse", new EnchantmentVanishing(Enchantment.Rarity.VERY_RARE, EnumItemSlot.values()));
/*    */ 
/*    */   
/*    */   static {
/* 47 */     for (Enchantment enchantment : IRegistry.ENCHANTMENT) {
/* 48 */       Enchantment.registerEnchantment((Enchantment)new CraftEnchantment(enchantment));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   private static Enchantment a(String s, Enchantment enchantment) {
/* 54 */     return IRegistry.<Enchantment>a(IRegistry.ENCHANTMENT, s, enchantment);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\Enchantments.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */