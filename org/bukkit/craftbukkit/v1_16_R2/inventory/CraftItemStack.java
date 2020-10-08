/*     */ package org.bukkit.craftbukkit.v1_16_R2.inventory;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import java.util.Map;
/*     */ import net.minecraft.server.v1_16_R2.IMaterial;
/*     */ import net.minecraft.server.v1_16_R2.Item;
/*     */ import net.minecraft.server.v1_16_R2.ItemStack;
/*     */ import net.minecraft.server.v1_16_R2.NBTTagCompound;
/*     */ import net.minecraft.server.v1_16_R2.NBTTagList;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.configuration.serialization.DelegateDeserialization;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftLegacy;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftMagicNumbers;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftNamespacedKey;
/*     */ import org.bukkit.enchantments.Enchantment;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.material.MaterialData;
/*     */ 
/*     */ @DelegateDeserialization(ItemStack.class)
/*     */ public final class CraftItemStack extends ItemStack {
/*     */   ItemStack handle;
/*     */   
/*     */   public static ItemStack asNMSCopy(ItemStack original) {
/*  26 */     if (original instanceof CraftItemStack) {
/*  27 */       CraftItemStack craftItemStack = (CraftItemStack)original;
/*  28 */       return (craftItemStack.handle == null) ? ItemStack.b : craftItemStack.handle.cloneItemStack();
/*     */     } 
/*  30 */     if (original == null || original.getType() == Material.AIR) {
/*  31 */       return ItemStack.b;
/*     */     }
/*     */     
/*  34 */     Item item = CraftMagicNumbers.getItem(original.getType(), original.getDurability());
/*     */     
/*  36 */     if (item == null) {
/*  37 */       return ItemStack.b;
/*     */     }
/*     */     
/*  40 */     ItemStack stack = new ItemStack((IMaterial)item, original.getAmount());
/*  41 */     if (original.hasItemMeta()) {
/*  42 */       setItemMeta(stack, original.getItemMeta());
/*     */     }
/*  44 */     return stack;
/*     */   }
/*     */   
/*     */   public static ItemStack copyNMSStack(ItemStack original, int amount) {
/*  48 */     ItemStack stack = original.cloneItemStack();
/*  49 */     stack.setCount(amount);
/*  50 */     return stack;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ItemStack asBukkitCopy(ItemStack original) {
/*  57 */     if (original.isEmpty()) {
/*  58 */       return new ItemStack(Material.AIR);
/*     */     }
/*  60 */     ItemStack stack = new ItemStack(CraftMagicNumbers.getMaterial(original.getItem()), original.getCount());
/*  61 */     if (hasItemMeta(original)) {
/*  62 */       stack.setItemMeta(getItemMeta(original));
/*     */     }
/*  64 */     return stack;
/*     */   }
/*     */   
/*     */   public static CraftItemStack asCraftMirror(ItemStack original) {
/*  68 */     return new CraftItemStack((original == null || original.isEmpty()) ? null : original);
/*     */   }
/*     */   
/*     */   public static CraftItemStack asCraftCopy(ItemStack original) {
/*  72 */     if (original instanceof CraftItemStack) {
/*  73 */       CraftItemStack stack = (CraftItemStack)original;
/*  74 */       return new CraftItemStack((stack.handle == null) ? null : stack.handle.cloneItemStack());
/*     */     } 
/*  76 */     return new CraftItemStack(original);
/*     */   }
/*     */   
/*     */   public static CraftItemStack asNewCraftStack(Item item) {
/*  80 */     return asNewCraftStack(item, 1);
/*     */   }
/*     */   
/*     */   public static CraftItemStack asNewCraftStack(Item item, int amount) {
/*  84 */     return new CraftItemStack(CraftMagicNumbers.getMaterial(item), amount, (short)0, null);
/*     */   }
/*     */   
/*     */   public ItemStack getHandle() {
/*  88 */     return this.handle;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private CraftItemStack(ItemStack item) {
/*  94 */     this.handle = item;
/*     */   }
/*     */   
/*     */   private CraftItemStack(ItemStack item) {
/*  98 */     this(item.getType(), item.getAmount(), item.getDurability(), item.hasItemMeta() ? item.getItemMeta() : null);
/*     */   }
/*     */   
/*     */   private CraftItemStack(Material type, int amount, short durability, ItemMeta itemMeta) {
/* 102 */     setType(type);
/* 103 */     setAmount(amount);
/* 104 */     setDurability(durability);
/* 105 */     setItemMeta(itemMeta);
/*     */   }
/*     */ 
/*     */   
/*     */   public MaterialData getData() {
/* 110 */     return (this.handle != null) ? CraftMagicNumbers.getMaterialData(this.handle.getItem()) : super.getData();
/*     */   }
/*     */ 
/*     */   
/*     */   public Material getType() {
/* 115 */     return (this.handle != null) ? CraftMagicNumbers.getMaterial(this.handle.getItem()) : Material.AIR;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setType(Material type) {
/* 120 */     if (getType() == type)
/*     */       return; 
/* 122 */     if (type == Material.AIR) {
/* 123 */       this.handle = null;
/* 124 */     } else if (CraftMagicNumbers.getItem(type) == null) {
/* 125 */       this.handle = null;
/* 126 */     } else if (this.handle == null) {
/* 127 */       this.handle = new ItemStack((IMaterial)CraftMagicNumbers.getItem(type), 1);
/*     */     } else {
/* 129 */       this.handle.setItem(CraftMagicNumbers.getItem(type));
/* 130 */       if (hasItemMeta())
/*     */       {
/* 132 */         setItemMeta(this.handle, getItemMeta(this.handle));
/*     */       }
/*     */     } 
/* 135 */     setData(null);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getAmount() {
/* 140 */     return (this.handle != null) ? this.handle.getCount() : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAmount(int amount) {
/* 145 */     if (this.handle == null) {
/*     */       return;
/*     */     }
/*     */     
/* 149 */     this.handle.setCount(amount);
/* 150 */     if (amount == 0) {
/* 151 */       this.handle = null;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDurability(short durability) {
/* 158 */     if (this.handle != null) {
/* 159 */       this.handle.setDamage(durability);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public short getDurability() {
/* 165 */     if (this.handle != null) {
/* 166 */       return (short)this.handle.getDamage();
/*     */     }
/* 168 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxStackSize() {
/* 174 */     return (this.handle == null) ? Material.AIR.getMaxStackSize() : this.handle.getItem().getMaxStackSize();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxItemUseDuration() {
/* 180 */     return (this.handle == null) ? 0 : this.handle.getItemUseMaxDuration();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addUnsafeEnchantment(Enchantment ench, int level) {
/* 186 */     Validate.notNull(ench, "Cannot add null enchantment");
/*     */ 
/*     */     
/* 189 */     ItemMeta itemMeta = getItemMeta();
/* 190 */     itemMeta.addEnchant(ench, level, true);
/* 191 */     setItemMeta(itemMeta);
/*     */   }
/*     */ 
/*     */   
/*     */   static boolean makeTag(ItemStack item) {
/* 196 */     if (item == null) {
/* 197 */       return false;
/*     */     }
/*     */     
/* 200 */     if (item.getTag() == null) {
/* 201 */       item.setTag(new NBTTagCompound());
/*     */     }
/*     */     
/* 204 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean containsEnchantment(Enchantment ench) {
/* 209 */     return (hasItemMeta() && getItemMeta().hasEnchant(ench));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getEnchantmentLevel(Enchantment ench) {
/* 214 */     return hasItemMeta() ? getItemMeta().getEnchantLevel(ench) : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public int removeEnchantment(Enchantment ench) {
/* 219 */     Validate.notNull(ench, "Cannot remove null enchantment");
/*     */ 
/*     */     
/* 222 */     ItemMeta itemMeta = getItemMeta();
/* 223 */     int level = itemMeta.getEnchantLevel(ench);
/* 224 */     if (level > 0) {
/* 225 */       itemMeta.removeEnchant(ench);
/* 226 */       setItemMeta(itemMeta);
/*     */     } 
/*     */ 
/*     */     
/* 230 */     return level;
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<Enchantment, Integer> getEnchantments() {
/* 235 */     return hasItemMeta() ? getItemMeta().getEnchants() : (Map<Enchantment, Integer>)ImmutableMap.of();
/*     */   }
/*     */   
/*     */   static Map<Enchantment, Integer> getEnchantments(ItemStack item) {
/* 239 */     NBTTagList list = (item != null && item.hasEnchantments()) ? item.getEnchantments() : null;
/*     */     
/* 241 */     if (list == null || list.size() == 0) {
/* 242 */       return (Map<Enchantment, Integer>)ImmutableMap.of();
/*     */     }
/*     */     
/* 245 */     ImmutableMap.Builder<Enchantment, Integer> result = ImmutableMap.builder();
/*     */     
/* 247 */     for (int i = 0; i < list.size(); i++) {
/* 248 */       String id = ((NBTTagCompound)list.get(i)).getString(CraftMetaItem.ENCHANTMENTS_ID.NBT);
/* 249 */       int level = 0xFFFF & ((NBTTagCompound)list.get(i)).getShort(CraftMetaItem.ENCHANTMENTS_LVL.NBT);
/*     */       
/* 251 */       Enchantment enchant = Enchantment.getByKey(CraftNamespacedKey.fromStringOrNull(id));
/* 252 */       if (enchant != null) {
/* 253 */         result.put(enchant, Integer.valueOf(level));
/*     */       }
/*     */     } 
/*     */     
/* 257 */     return (Map<Enchantment, Integer>)result.build();
/*     */   }
/*     */   
/*     */   static NBTTagList getEnchantmentList(ItemStack item) {
/* 261 */     return (item != null && item.hasEnchantments()) ? item.getEnchantments() : null;
/*     */   }
/*     */ 
/*     */   
/*     */   public CraftItemStack clone() {
/* 266 */     CraftItemStack itemStack = (CraftItemStack)super.clone();
/* 267 */     if (this.handle != null) {
/* 268 */       itemStack.handle = this.handle.cloneItemStack();
/*     */     }
/* 270 */     return itemStack;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemMeta getItemMeta() {
/* 275 */     return getItemMeta(this.handle);
/*     */   }
/*     */   
/*     */   public static ItemMeta getItemMeta(ItemStack item) {
/* 279 */     if (!hasItemMeta(item)) {
/* 280 */       return CraftItemFactory.instance().getItemMeta(getType(item));
/*     */     }
/* 282 */     switch (getType(item)) {
/*     */       case WRITTEN_BOOK:
/* 284 */         return new CraftMetaBookSigned(item.getTag());
/*     */       case WRITABLE_BOOK:
/* 286 */         return new CraftMetaBook(item.getTag());
/*     */       case CREEPER_HEAD:
/*     */       case CREEPER_WALL_HEAD:
/*     */       case DRAGON_HEAD:
/*     */       case DRAGON_WALL_HEAD:
/*     */       case PLAYER_HEAD:
/*     */       case PLAYER_WALL_HEAD:
/*     */       case SKELETON_SKULL:
/*     */       case SKELETON_WALL_SKULL:
/*     */       case WITHER_SKELETON_SKULL:
/*     */       case WITHER_SKELETON_WALL_SKULL:
/*     */       case ZOMBIE_HEAD:
/*     */       case ZOMBIE_WALL_HEAD:
/* 299 */         return new CraftMetaSkull(item.getTag());
/*     */       case LEATHER_HELMET:
/*     */       case LEATHER_HORSE_ARMOR:
/*     */       case LEATHER_CHESTPLATE:
/*     */       case LEATHER_LEGGINGS:
/*     */       case LEATHER_BOOTS:
/* 305 */         return new CraftMetaLeatherArmor(item.getTag());
/*     */       case POTION:
/*     */       case SPLASH_POTION:
/*     */       case LINGERING_POTION:
/*     */       case TIPPED_ARROW:
/* 310 */         return new CraftMetaPotion(item.getTag());
/*     */       case FILLED_MAP:
/* 312 */         return new CraftMetaMap(item.getTag());
/*     */       case FIREWORK_ROCKET:
/* 314 */         return new CraftMetaFirework(item.getTag());
/*     */       case FIREWORK_STAR:
/* 316 */         return new CraftMetaCharge(item.getTag());
/*     */       case ENCHANTED_BOOK:
/* 318 */         return new CraftMetaEnchantedBook(item.getTag());
/*     */       case BLACK_BANNER:
/*     */       case BLACK_WALL_BANNER:
/*     */       case BLUE_BANNER:
/*     */       case BLUE_WALL_BANNER:
/*     */       case BROWN_BANNER:
/*     */       case BROWN_WALL_BANNER:
/*     */       case CYAN_BANNER:
/*     */       case CYAN_WALL_BANNER:
/*     */       case GRAY_BANNER:
/*     */       case GRAY_WALL_BANNER:
/*     */       case GREEN_BANNER:
/*     */       case GREEN_WALL_BANNER:
/*     */       case LIGHT_BLUE_BANNER:
/*     */       case LIGHT_BLUE_WALL_BANNER:
/*     */       case LIGHT_GRAY_BANNER:
/*     */       case LIGHT_GRAY_WALL_BANNER:
/*     */       case LIME_BANNER:
/*     */       case LIME_WALL_BANNER:
/*     */       case MAGENTA_BANNER:
/*     */       case MAGENTA_WALL_BANNER:
/*     */       case ORANGE_BANNER:
/*     */       case ORANGE_WALL_BANNER:
/*     */       case PINK_BANNER:
/*     */       case PINK_WALL_BANNER:
/*     */       case PURPLE_BANNER:
/*     */       case PURPLE_WALL_BANNER:
/*     */       case RED_BANNER:
/*     */       case RED_WALL_BANNER:
/*     */       case WHITE_BANNER:
/*     */       case WHITE_WALL_BANNER:
/*     */       case YELLOW_BANNER:
/*     */       case YELLOW_WALL_BANNER:
/* 351 */         return new CraftMetaBanner(item.getTag());
/*     */       case BAT_SPAWN_EGG:
/*     */       case BEE_SPAWN_EGG:
/*     */       case BLAZE_SPAWN_EGG:
/*     */       case CAT_SPAWN_EGG:
/*     */       case CAVE_SPIDER_SPAWN_EGG:
/*     */       case CHICKEN_SPAWN_EGG:
/*     */       case COD_SPAWN_EGG:
/*     */       case COW_SPAWN_EGG:
/*     */       case CREEPER_SPAWN_EGG:
/*     */       case DOLPHIN_SPAWN_EGG:
/*     */       case DONKEY_SPAWN_EGG:
/*     */       case DROWNED_SPAWN_EGG:
/*     */       case ELDER_GUARDIAN_SPAWN_EGG:
/*     */       case ENDERMAN_SPAWN_EGG:
/*     */       case ENDERMITE_SPAWN_EGG:
/*     */       case EVOKER_SPAWN_EGG:
/*     */       case FOX_SPAWN_EGG:
/*     */       case GHAST_SPAWN_EGG:
/*     */       case GUARDIAN_SPAWN_EGG:
/*     */       case HOGLIN_SPAWN_EGG:
/*     */       case HORSE_SPAWN_EGG:
/*     */       case HUSK_SPAWN_EGG:
/*     */       case LLAMA_SPAWN_EGG:
/*     */       case MAGMA_CUBE_SPAWN_EGG:
/*     */       case MOOSHROOM_SPAWN_EGG:
/*     */       case MULE_SPAWN_EGG:
/*     */       case OCELOT_SPAWN_EGG:
/*     */       case PANDA_SPAWN_EGG:
/*     */       case PARROT_SPAWN_EGG:
/*     */       case PHANTOM_SPAWN_EGG:
/*     */       case PIGLIN_SPAWN_EGG:
/*     */       case PIG_SPAWN_EGG:
/*     */       case PILLAGER_SPAWN_EGG:
/*     */       case POLAR_BEAR_SPAWN_EGG:
/*     */       case PUFFERFISH_SPAWN_EGG:
/*     */       case RABBIT_SPAWN_EGG:
/*     */       case RAVAGER_SPAWN_EGG:
/*     */       case SALMON_SPAWN_EGG:
/*     */       case SHEEP_SPAWN_EGG:
/*     */       case SHULKER_SPAWN_EGG:
/*     */       case SILVERFISH_SPAWN_EGG:
/*     */       case SKELETON_HORSE_SPAWN_EGG:
/*     */       case SKELETON_SPAWN_EGG:
/*     */       case SLIME_SPAWN_EGG:
/*     */       case SPIDER_SPAWN_EGG:
/*     */       case SQUID_SPAWN_EGG:
/*     */       case STRAY_SPAWN_EGG:
/*     */       case STRIDER_SPAWN_EGG:
/*     */       case TRADER_LLAMA_SPAWN_EGG:
/*     */       case TROPICAL_FISH_SPAWN_EGG:
/*     */       case TURTLE_SPAWN_EGG:
/*     */       case VEX_SPAWN_EGG:
/*     */       case VILLAGER_SPAWN_EGG:
/*     */       case VINDICATOR_SPAWN_EGG:
/*     */       case WANDERING_TRADER_SPAWN_EGG:
/*     */       case WITCH_SPAWN_EGG:
/*     */       case WITHER_SKELETON_SPAWN_EGG:
/*     */       case WOLF_SPAWN_EGG:
/*     */       case ZOGLIN_SPAWN_EGG:
/*     */       case ZOMBIE_HORSE_SPAWN_EGG:
/*     */       case ZOMBIE_SPAWN_EGG:
/*     */       case ZOMBIE_VILLAGER_SPAWN_EGG:
/*     */       case ZOMBIFIED_PIGLIN_SPAWN_EGG:
/* 415 */         return new CraftMetaSpawnEgg(item.getTag());
/*     */       case ARMOR_STAND:
/* 417 */         return new CraftMetaArmorStand(item.getTag());
/*     */       case KNOWLEDGE_BOOK:
/* 419 */         return new CraftMetaKnowledgeBook(item.getTag());
/*     */       case FURNACE:
/*     */       case CHEST:
/*     */       case TRAPPED_CHEST:
/*     */       case JUKEBOX:
/*     */       case DISPENSER:
/*     */       case DROPPER:
/*     */       case ACACIA_SIGN:
/*     */       case ACACIA_WALL_SIGN:
/*     */       case BIRCH_SIGN:
/*     */       case BIRCH_WALL_SIGN:
/*     */       case CRIMSON_SIGN:
/*     */       case CRIMSON_WALL_SIGN:
/*     */       case DARK_OAK_SIGN:
/*     */       case DARK_OAK_WALL_SIGN:
/*     */       case JUNGLE_SIGN:
/*     */       case JUNGLE_WALL_SIGN:
/*     */       case OAK_SIGN:
/*     */       case OAK_WALL_SIGN:
/*     */       case SPRUCE_SIGN:
/*     */       case SPRUCE_WALL_SIGN:
/*     */       case WARPED_SIGN:
/*     */       case WARPED_WALL_SIGN:
/*     */       case SPAWNER:
/*     */       case BREWING_STAND:
/*     */       case ENCHANTING_TABLE:
/*     */       case COMMAND_BLOCK:
/*     */       case REPEATING_COMMAND_BLOCK:
/*     */       case CHAIN_COMMAND_BLOCK:
/*     */       case BEACON:
/*     */       case DAYLIGHT_DETECTOR:
/*     */       case HOPPER:
/*     */       case COMPARATOR:
/*     */       case SHIELD:
/*     */       case STRUCTURE_BLOCK:
/*     */       case SHULKER_BOX:
/*     */       case WHITE_SHULKER_BOX:
/*     */       case ORANGE_SHULKER_BOX:
/*     */       case MAGENTA_SHULKER_BOX:
/*     */       case LIGHT_BLUE_SHULKER_BOX:
/*     */       case YELLOW_SHULKER_BOX:
/*     */       case LIME_SHULKER_BOX:
/*     */       case PINK_SHULKER_BOX:
/*     */       case GRAY_SHULKER_BOX:
/*     */       case LIGHT_GRAY_SHULKER_BOX:
/*     */       case CYAN_SHULKER_BOX:
/*     */       case PURPLE_SHULKER_BOX:
/*     */       case BLUE_SHULKER_BOX:
/*     */       case BROWN_SHULKER_BOX:
/*     */       case GREEN_SHULKER_BOX:
/*     */       case RED_SHULKER_BOX:
/*     */       case BLACK_SHULKER_BOX:
/*     */       case ENDER_CHEST:
/*     */       case BARREL:
/*     */       case BELL:
/*     */       case BLAST_FURNACE:
/*     */       case CAMPFIRE:
/*     */       case SOUL_CAMPFIRE:
/*     */       case JIGSAW:
/*     */       case LECTERN:
/*     */       case SMOKER:
/*     */       case BEEHIVE:
/*     */       case BEE_NEST:
/* 482 */         return new CraftMetaBlockState(item.getTag(), CraftMagicNumbers.getMaterial(item.getItem()));
/*     */       case TROPICAL_FISH_BUCKET:
/* 484 */         return new CraftMetaTropicalFishBucket(item.getTag());
/*     */       case CROSSBOW:
/* 486 */         return new CraftMetaCrossbow(item.getTag());
/*     */       case SUSPICIOUS_STEW:
/* 488 */         return new CraftMetaSuspiciousStew(item.getTag());
/*     */       case COD_BUCKET:
/*     */       case PUFFERFISH_BUCKET:
/*     */       case SALMON_BUCKET:
/*     */       case ITEM_FRAME:
/*     */       case PAINTING:
/* 494 */         return new CraftMetaEntityTag(item.getTag());
/*     */       case COMPASS:
/* 496 */         return new CraftMetaCompass(item.getTag());
/*     */     } 
/* 498 */     return new CraftMetaItem(item.getTag());
/*     */   }
/*     */ 
/*     */   
/*     */   static Material getType(ItemStack item) {
/* 503 */     return (item == null) ? Material.AIR : CraftMagicNumbers.getMaterial(item.getItem());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean setItemMeta(ItemMeta itemMeta) {
/* 508 */     return setItemMeta(this.handle, itemMeta);
/*     */   }
/*     */   
/*     */   public static boolean setItemMeta(ItemStack item, ItemMeta itemMeta) {
/* 512 */     if (item == null) {
/* 513 */       return false;
/*     */     }
/* 515 */     if (CraftItemFactory.instance().equals(itemMeta, (ItemMeta)null)) {
/* 516 */       item.setTag(null);
/* 517 */       return true;
/*     */     } 
/* 519 */     if (!CraftItemFactory.instance().isApplicable(itemMeta, getType(item))) {
/* 520 */       return false;
/*     */     }
/*     */     
/* 523 */     itemMeta = CraftItemFactory.instance().asMetaFor(itemMeta, getType(item));
/* 524 */     if (itemMeta == null) return true;
/*     */     
/* 526 */     Item oldItem = item.getItem();
/* 527 */     Item newItem = CraftMagicNumbers.getItem(CraftItemFactory.instance().updateMaterial(itemMeta, CraftMagicNumbers.getMaterial(oldItem)));
/* 528 */     if (oldItem != newItem) {
/* 529 */       item.setItem(newItem);
/*     */     }
/*     */     
/* 532 */     NBTTagCompound tag = new NBTTagCompound();
/* 533 */     item.setTag(tag);
/*     */     
/* 535 */     ((CraftMetaItem)itemMeta).applyToItem(tag);
/* 536 */     item.convertStack(((CraftMetaItem)itemMeta).getVersion());
/*     */     
/* 538 */     if (item.getItem() != null && item.getItem().usesDurability()) {
/* 539 */       item.setDamage(item.getDamage());
/*     */     }
/*     */     
/* 542 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSimilar(ItemStack stack) {
/* 547 */     if (stack == null) {
/* 548 */       return false;
/*     */     }
/* 550 */     if (stack == this) {
/* 551 */       return true;
/*     */     }
/* 553 */     if (!(stack instanceof CraftItemStack)) {
/* 554 */       return (stack.getClass() == ItemStack.class && stack.isSimilar(this));
/*     */     }
/*     */     
/* 557 */     CraftItemStack that = (CraftItemStack)stack;
/* 558 */     if (this.handle == that.handle) {
/* 559 */       return true;
/*     */     }
/* 561 */     if (this.handle == null || that.handle == null) {
/* 562 */       return false;
/*     */     }
/* 564 */     Material comparisonType = CraftLegacy.fromLegacy(that.getType());
/* 565 */     if (comparisonType != getType() || getDurability() != that.getDurability()) {
/* 566 */       return false;
/*     */     }
/* 568 */     return hasItemMeta() ? ((that.hasItemMeta() && this.handle.getTag().equals(that.handle.getTag()))) : (!that.hasItemMeta());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasItemMeta() {
/* 573 */     return (hasItemMeta(this.handle) && (this.handle.getDamage() != 0 || (this.handle.getTag() != null && (this.handle.getTag()).map.size() >= (this.handle.getTag().hasKey(CraftMetaItem.DAMAGE.NBT) ? 2 : 1))));
/*     */   }
/*     */   
/*     */   static boolean hasItemMeta(ItemStack item) {
/* 577 */     return (item != null && item.getTag() != null && !item.getTag().isEmpty());
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\inventory\CraftItemStack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */