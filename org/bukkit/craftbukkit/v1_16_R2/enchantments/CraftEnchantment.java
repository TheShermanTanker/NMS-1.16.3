/*     */ package org.bukkit.craftbukkit.v1_16_R2.enchantments;
/*     */ 
/*     */ import net.minecraft.server.v1_16_R2.Enchantment;
/*     */ import net.minecraft.server.v1_16_R2.EnchantmentSlotType;
/*     */ import net.minecraft.server.v1_16_R2.IRegistry;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftNamespacedKey;
/*     */ import org.bukkit.enchantments.Enchantment;
/*     */ import org.bukkit.enchantments.EnchantmentTarget;
/*     */ import org.bukkit.enchantments.EnchantmentWrapper;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ public class CraftEnchantment extends Enchantment {
/*     */   private final Enchantment target;
/*     */   
/*     */   public CraftEnchantment(Enchantment target) {
/*  17 */     super(CraftNamespacedKey.fromMinecraft(IRegistry.ENCHANTMENT.getKey(target)));
/*  18 */     this.target = target;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxLevel() {
/*  23 */     return this.target.getMaxLevel();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getStartLevel() {
/*  28 */     return this.target.getStartLevel();
/*     */   }
/*     */ 
/*     */   
/*     */   public EnchantmentTarget getItemTarget() {
/*  33 */     switch (this.target.itemTarget) {
/*     */       case ARMOR:
/*  35 */         return EnchantmentTarget.ARMOR;
/*     */       case ARMOR_FEET:
/*  37 */         return EnchantmentTarget.ARMOR_FEET;
/*     */       case ARMOR_HEAD:
/*  39 */         return EnchantmentTarget.ARMOR_HEAD;
/*     */       case ARMOR_LEGS:
/*  41 */         return EnchantmentTarget.ARMOR_LEGS;
/*     */       case ARMOR_CHEST:
/*  43 */         return EnchantmentTarget.ARMOR_TORSO;
/*     */       case DIGGER:
/*  45 */         return EnchantmentTarget.TOOL;
/*     */       case WEAPON:
/*  47 */         return EnchantmentTarget.WEAPON;
/*     */       case BOW:
/*  49 */         return EnchantmentTarget.BOW;
/*     */       case FISHING_ROD:
/*  51 */         return EnchantmentTarget.FISHING_ROD;
/*     */       case BREAKABLE:
/*  53 */         return EnchantmentTarget.BREAKABLE;
/*     */       case WEARABLE:
/*  55 */         return EnchantmentTarget.WEARABLE;
/*     */       case TRIDENT:
/*  57 */         return EnchantmentTarget.TRIDENT;
/*     */       case CROSSBOW:
/*  59 */         return EnchantmentTarget.CROSSBOW;
/*     */       case VANISHABLE:
/*  61 */         return EnchantmentTarget.VANISHABLE;
/*     */     } 
/*  63 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isTreasure() {
/*  69 */     return this.target.isTreasure();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCursed() {
/*  74 */     return (this.target instanceof net.minecraft.server.v1_16_R2.EnchantmentBinding || this.target instanceof net.minecraft.server.v1_16_R2.EnchantmentVanishing);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canEnchantItem(ItemStack item) {
/*  79 */     return this.target.canEnchant(CraftItemStack.asNMSCopy(item));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/*  85 */     switch (IRegistry.ENCHANTMENT.a(this.target)) {
/*     */       case 0:
/*  87 */         return "PROTECTION_ENVIRONMENTAL";
/*     */       case 1:
/*  89 */         return "PROTECTION_FIRE";
/*     */       case 2:
/*  91 */         return "PROTECTION_FALL";
/*     */       case 3:
/*  93 */         return "PROTECTION_EXPLOSIONS";
/*     */       case 4:
/*  95 */         return "PROTECTION_PROJECTILE";
/*     */       case 5:
/*  97 */         return "OXYGEN";
/*     */       case 6:
/*  99 */         return "WATER_WORKER";
/*     */       case 7:
/* 101 */         return "THORNS";
/*     */       case 8:
/* 103 */         return "DEPTH_STRIDER";
/*     */       case 9:
/* 105 */         return "FROST_WALKER";
/*     */       case 10:
/* 107 */         return "BINDING_CURSE";
/*     */       case 11:
/* 109 */         return "SOUL_SPEED";
/*     */       case 12:
/* 111 */         return "DAMAGE_ALL";
/*     */       case 13:
/* 113 */         return "DAMAGE_UNDEAD";
/*     */       case 14:
/* 115 */         return "DAMAGE_ARTHROPODS";
/*     */       case 15:
/* 117 */         return "KNOCKBACK";
/*     */       case 16:
/* 119 */         return "FIRE_ASPECT";
/*     */       case 17:
/* 121 */         return "LOOT_BONUS_MOBS";
/*     */       case 18:
/* 123 */         return "SWEEPING_EDGE";
/*     */       case 19:
/* 125 */         return "DIG_SPEED";
/*     */       case 20:
/* 127 */         return "SILK_TOUCH";
/*     */       case 21:
/* 129 */         return "DURABILITY";
/*     */       case 22:
/* 131 */         return "LOOT_BONUS_BLOCKS";
/*     */       case 23:
/* 133 */         return "ARROW_DAMAGE";
/*     */       case 24:
/* 135 */         return "ARROW_KNOCKBACK";
/*     */       case 25:
/* 137 */         return "ARROW_FIRE";
/*     */       case 26:
/* 139 */         return "ARROW_INFINITE";
/*     */       case 27:
/* 141 */         return "LUCK";
/*     */       case 28:
/* 143 */         return "LURE";
/*     */       case 29:
/* 145 */         return "LOYALTY";
/*     */       case 30:
/* 147 */         return "IMPALING";
/*     */       case 31:
/* 149 */         return "RIPTIDE";
/*     */       case 32:
/* 151 */         return "CHANNELING";
/*     */       case 33:
/* 153 */         return "MULTISHOT";
/*     */       case 34:
/* 155 */         return "QUICK_CHARGE";
/*     */       case 35:
/* 157 */         return "PIERCING";
/*     */       case 36:
/* 159 */         return "MENDING";
/*     */       case 37:
/* 161 */         return "VANISHING_CURSE";
/*     */     } 
/* 163 */     return "UNKNOWN_ENCHANT_" + IRegistry.ENCHANTMENT.a(this.target);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Enchantment getRaw(Enchantment enchantment) {
/* 168 */     if (enchantment instanceof EnchantmentWrapper) {
/* 169 */       enchantment = ((EnchantmentWrapper)enchantment).getEnchantment();
/*     */     }
/*     */     
/* 172 */     if (enchantment instanceof CraftEnchantment) {
/* 173 */       return ((CraftEnchantment)enchantment).target;
/*     */     }
/*     */     
/* 176 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean conflictsWith(Enchantment other) {
/* 181 */     if (other instanceof EnchantmentWrapper) {
/* 182 */       other = ((EnchantmentWrapper)other).getEnchantment();
/*     */     }
/* 184 */     if (!(other instanceof CraftEnchantment)) {
/* 185 */       return false;
/*     */     }
/* 187 */     CraftEnchantment ench = (CraftEnchantment)other;
/* 188 */     return !this.target.isCompatible(ench.target);
/*     */   }
/*     */   
/*     */   public Enchantment getHandle() {
/* 192 */     return this.target;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\enchantments\CraftEnchantment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */