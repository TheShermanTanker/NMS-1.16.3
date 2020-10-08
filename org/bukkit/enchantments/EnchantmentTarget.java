/*     */ package org.bukkit.enchantments;
/*     */ 
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public enum EnchantmentTarget
/*     */ {
/*  16 */   ALL
/*     */   {
/*     */     public boolean includes(@NotNull Material item)
/*     */     {
/*  20 */       for (EnchantmentTarget target : values()) {
/*  21 */         if (target != this && target.includes(item)) {
/*  22 */           return true;
/*     */         }
/*     */       } 
/*     */       
/*  26 */       return false;
/*     */     }
/*     */   },
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  33 */   ARMOR
/*     */   {
/*     */     public boolean includes(@NotNull Material item) {
/*  36 */       return (ARMOR_FEET.includes(item) || ARMOR_LEGS
/*  37 */         .includes(item) || ARMOR_HEAD
/*  38 */         .includes(item) || ARMOR_TORSO
/*  39 */         .includes(item));
/*     */     }
/*     */   },
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  46 */   ARMOR_FEET
/*     */   {
/*     */     public boolean includes(@NotNull Material item) {
/*  49 */       return (item.equals(Material.LEATHER_BOOTS) || item
/*  50 */         .equals(Material.CHAINMAIL_BOOTS) || item
/*  51 */         .equals(Material.IRON_BOOTS) || item
/*  52 */         .equals(Material.DIAMOND_BOOTS) || item
/*  53 */         .equals(Material.GOLDEN_BOOTS) || item
/*  54 */         .equals(Material.NETHERITE_BOOTS));
/*     */     }
/*     */   },
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  61 */   ARMOR_LEGS
/*     */   {
/*     */     public boolean includes(@NotNull Material item) {
/*  64 */       return (item.equals(Material.LEATHER_LEGGINGS) || item
/*  65 */         .equals(Material.CHAINMAIL_LEGGINGS) || item
/*  66 */         .equals(Material.IRON_LEGGINGS) || item
/*  67 */         .equals(Material.DIAMOND_LEGGINGS) || item
/*  68 */         .equals(Material.GOLDEN_LEGGINGS) || item
/*  69 */         .equals(Material.NETHERITE_LEGGINGS));
/*     */     }
/*     */   },
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  76 */   ARMOR_TORSO
/*     */   {
/*     */     public boolean includes(@NotNull Material item) {
/*  79 */       return (item.equals(Material.LEATHER_CHESTPLATE) || item
/*  80 */         .equals(Material.CHAINMAIL_CHESTPLATE) || item
/*  81 */         .equals(Material.IRON_CHESTPLATE) || item
/*  82 */         .equals(Material.DIAMOND_CHESTPLATE) || item
/*  83 */         .equals(Material.GOLDEN_CHESTPLATE) || item
/*  84 */         .equals(Material.NETHERITE_CHESTPLATE));
/*     */     }
/*     */   },
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  91 */   ARMOR_HEAD
/*     */   {
/*     */     public boolean includes(@NotNull Material item) {
/*  94 */       return (item.equals(Material.LEATHER_HELMET) || item
/*  95 */         .equals(Material.CHAINMAIL_HELMET) || item
/*  96 */         .equals(Material.DIAMOND_HELMET) || item
/*  97 */         .equals(Material.IRON_HELMET) || item
/*  98 */         .equals(Material.GOLDEN_HELMET) || item
/*  99 */         .equals(Material.TURTLE_HELMET) || item
/* 100 */         .equals(Material.NETHERITE_HELMET));
/*     */     }
/*     */   },
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 107 */   WEAPON
/*     */   {
/*     */     public boolean includes(@NotNull Material item) {
/* 110 */       return (item.equals(Material.WOODEN_SWORD) || item
/* 111 */         .equals(Material.STONE_SWORD) || item
/* 112 */         .equals(Material.IRON_SWORD) || item
/* 113 */         .equals(Material.DIAMOND_SWORD) || item
/* 114 */         .equals(Material.GOLDEN_SWORD) || item
/* 115 */         .equals(Material.NETHERITE_SWORD));
/*     */     }
/*     */   },
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 122 */   TOOL
/*     */   {
/*     */     public boolean includes(@NotNull Material item) {
/* 125 */       return (item.equals(Material.WOODEN_SHOVEL) || item
/* 126 */         .equals(Material.STONE_SHOVEL) || item
/* 127 */         .equals(Material.IRON_SHOVEL) || item
/* 128 */         .equals(Material.DIAMOND_SHOVEL) || item
/* 129 */         .equals(Material.GOLDEN_SHOVEL) || item
/* 130 */         .equals(Material.NETHERITE_SHOVEL) || item
/* 131 */         .equals(Material.WOODEN_PICKAXE) || item
/* 132 */         .equals(Material.STONE_PICKAXE) || item
/* 133 */         .equals(Material.IRON_PICKAXE) || item
/* 134 */         .equals(Material.DIAMOND_PICKAXE) || item
/* 135 */         .equals(Material.GOLDEN_PICKAXE) || item
/* 136 */         .equals(Material.NETHERITE_PICKAXE) || item
/* 137 */         .equals(Material.WOODEN_AXE) || item
/* 138 */         .equals(Material.STONE_AXE) || item
/* 139 */         .equals(Material.IRON_AXE) || item
/* 140 */         .equals(Material.DIAMOND_AXE) || item
/* 141 */         .equals(Material.GOLDEN_AXE) || item
/* 142 */         .equals(Material.NETHERITE_AXE) || item
/* 143 */         .equals(Material.WOODEN_HOE) || item
/* 144 */         .equals(Material.STONE_HOE) || item
/* 145 */         .equals(Material.IRON_HOE) || item
/* 146 */         .equals(Material.DIAMOND_HOE) || item
/* 147 */         .equals(Material.GOLDEN_HOE) || item
/* 148 */         .equals(Material.NETHERITE_HOE));
/*     */     }
/*     */   },
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 155 */   BOW
/*     */   {
/*     */     public boolean includes(@NotNull Material item) {
/* 158 */       return item.equals(Material.BOW);
/*     */     }
/*     */   },
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 165 */   FISHING_ROD
/*     */   {
/*     */     public boolean includes(@NotNull Material item) {
/* 168 */       return item.equals(Material.FISHING_ROD);
/*     */     }
/*     */   },
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 175 */   BREAKABLE
/*     */   {
/*     */     public boolean includes(@NotNull Material item) {
/* 178 */       return (item.getMaxDurability() > 0 && item.getMaxStackSize() == 1);
/*     */     }
/*     */   },
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 185 */   WEARABLE
/*     */   {
/*     */     public boolean includes(@NotNull Material item) {
/* 188 */       return (ARMOR.includes(item) || item
/* 189 */         .equals(Material.ELYTRA) || item
/* 190 */         .equals(Material.CARVED_PUMPKIN) || item
/* 191 */         .equals(Material.JACK_O_LANTERN) || item
/* 192 */         .equals(Material.SKELETON_SKULL) || item
/* 193 */         .equals(Material.WITHER_SKELETON_SKULL) || item
/* 194 */         .equals(Material.ZOMBIE_HEAD) || item
/* 195 */         .equals(Material.PLAYER_HEAD) || item
/* 196 */         .equals(Material.CREEPER_HEAD) || item
/* 197 */         .equals(Material.DRAGON_HEAD));
/*     */     }
/*     */   },
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 204 */   TRIDENT
/*     */   {
/*     */     public boolean includes(@NotNull Material item) {
/* 207 */       return item.equals(Material.TRIDENT);
/*     */     }
/*     */   },
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 214 */   CROSSBOW
/*     */   {
/*     */     public boolean includes(@NotNull Material item) {
/* 217 */       return item.equals(Material.CROSSBOW);
/*     */     }
/*     */   },
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 224 */   VANISHABLE
/*     */   {
/*     */     public boolean includes(@NotNull Material item) {
/* 227 */       return (BREAKABLE.includes(item) || (WEARABLE.includes(item) && !item.equals(Material.ELYTRA)) || item.equals(Material.COMPASS));
/*     */     }
/*     */   };
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
/*     */   
/*     */   public boolean includes(@NotNull ItemStack item) {
/* 246 */     return includes(item.getType());
/*     */   }
/*     */   
/*     */   public abstract boolean includes(@NotNull Material paramMaterial);
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\enchantments\EnchantmentTarget.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */