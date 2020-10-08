/*     */ package org.bukkit.craftbukkit.v1_16_R2.inventory;
/*     */ import net.md_5.bungee.api.chat.BaseComponent;
/*     */ import net.md_5.bungee.api.chat.hover.content.Content;
/*     */ import net.minecraft.server.v1_16_R2.ItemStack;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.Color;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ 
/*     */ public final class CraftItemFactory implements ItemFactory {
/*  13 */   static final Color DEFAULT_LEATHER_COLOR = Color.fromRGB(10511680);
/*     */ 
/*     */ 
/*     */   
/*  17 */   private static final CraftItemFactory instance = new CraftItemFactory(); static {
/*  18 */     ConfigurationSerialization.registerClass(CraftMetaItem.SerializableMeta.class);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isApplicable(ItemMeta meta, ItemStack itemstack) {
/*  26 */     if (itemstack == null) {
/*  27 */       return false;
/*     */     }
/*  29 */     return isApplicable(meta, itemstack.getType());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isApplicable(ItemMeta meta, Material type) {
/*  34 */     type = CraftLegacy.fromLegacy(type);
/*  35 */     if (type == null || meta == null) {
/*  36 */       return false;
/*     */     }
/*  38 */     if (!(meta instanceof CraftMetaItem)) {
/*  39 */       throw new IllegalArgumentException("Meta of " + meta.getClass().toString() + " not created by " + CraftItemFactory.class.getName());
/*     */     }
/*     */     
/*  42 */     return ((CraftMetaItem)meta).applicableTo(type);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemMeta getItemMeta(Material material) {
/*  47 */     Validate.notNull(material, "Material cannot be null");
/*  48 */     return getItemMeta(material, null);
/*     */   }
/*     */   
/*     */   private ItemMeta getItemMeta(Material material, CraftMetaItem meta) {
/*  52 */     material = CraftLegacy.fromLegacy(material);
/*  53 */     switch (material) {
/*     */       case AIR:
/*  55 */         return null;
/*     */       case WRITTEN_BOOK:
/*  57 */         return (meta instanceof CraftMetaBookSigned) ? meta : new CraftMetaBookSigned(meta);
/*     */       case WRITABLE_BOOK:
/*  59 */         return (meta != null && meta.getClass().equals(CraftMetaBook.class)) ? meta : new CraftMetaBook(meta);
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
/*  72 */         return (meta instanceof CraftMetaSkull) ? meta : new CraftMetaSkull(meta);
/*     */       case LEATHER_HELMET:
/*     */       case LEATHER_HORSE_ARMOR:
/*     */       case LEATHER_CHESTPLATE:
/*     */       case LEATHER_LEGGINGS:
/*     */       case LEATHER_BOOTS:
/*  78 */         return (meta instanceof CraftMetaLeatherArmor) ? meta : new CraftMetaLeatherArmor(meta);
/*     */       case POTION:
/*     */       case SPLASH_POTION:
/*     */       case LINGERING_POTION:
/*     */       case TIPPED_ARROW:
/*  83 */         return (meta instanceof CraftMetaPotion) ? meta : new CraftMetaPotion(meta);
/*     */       case FILLED_MAP:
/*  85 */         return (meta instanceof CraftMetaMap) ? meta : new CraftMetaMap(meta);
/*     */       case FIREWORK_ROCKET:
/*  87 */         return (meta instanceof CraftMetaFirework) ? meta : new CraftMetaFirework(meta);
/*     */       case FIREWORK_STAR:
/*  89 */         return (meta instanceof CraftMetaCharge) ? meta : new CraftMetaCharge(meta);
/*     */       case ENCHANTED_BOOK:
/*  91 */         return (meta instanceof CraftMetaEnchantedBook) ? meta : new CraftMetaEnchantedBook(meta);
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
/* 124 */         return (meta instanceof CraftMetaBanner) ? meta : new CraftMetaBanner(meta);
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
/* 188 */         return (meta instanceof CraftMetaSpawnEgg) ? meta : new CraftMetaSpawnEgg(meta);
/*     */       case ARMOR_STAND:
/* 190 */         return (meta instanceof CraftMetaArmorStand) ? meta : new CraftMetaArmorStand(meta);
/*     */       case KNOWLEDGE_BOOK:
/* 192 */         return (meta instanceof CraftMetaKnowledgeBook) ? meta : new CraftMetaKnowledgeBook(meta);
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
/* 255 */         return new CraftMetaBlockState(meta, material);
/*     */       case TROPICAL_FISH_BUCKET:
/* 257 */         return (meta instanceof CraftMetaTropicalFishBucket) ? meta : new CraftMetaTropicalFishBucket(meta);
/*     */       case CROSSBOW:
/* 259 */         return (meta instanceof CraftMetaCrossbow) ? meta : new CraftMetaCrossbow(meta);
/*     */       case SUSPICIOUS_STEW:
/* 261 */         return (meta instanceof CraftMetaSuspiciousStew) ? meta : new CraftMetaSuspiciousStew(meta);
/*     */       case COD_BUCKET:
/*     */       case PUFFERFISH_BUCKET:
/*     */       case SALMON_BUCKET:
/*     */       case ITEM_FRAME:
/*     */       case PAINTING:
/* 267 */         return (meta instanceof CraftMetaEntityTag) ? meta : new CraftMetaEntityTag(meta);
/*     */       case COMPASS:
/* 269 */         return (meta instanceof CraftMetaCompass) ? meta : new CraftMetaCompass(meta);
/*     */     } 
/* 271 */     return new CraftMetaItem(meta);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(ItemMeta meta1, ItemMeta meta2) {
/* 277 */     if (meta1 == meta2) {
/* 278 */       return true;
/*     */     }
/* 280 */     if (meta1 != null && !(meta1 instanceof CraftMetaItem)) {
/* 281 */       throw new IllegalArgumentException("First meta of " + meta1.getClass().getName() + " does not belong to " + CraftItemFactory.class.getName());
/*     */     }
/* 283 */     if (meta2 != null && !(meta2 instanceof CraftMetaItem)) {
/* 284 */       throw new IllegalArgumentException("Second meta " + meta2.getClass().getName() + " does not belong to " + CraftItemFactory.class.getName());
/*     */     }
/* 286 */     if (meta1 == null) {
/* 287 */       return ((CraftMetaItem)meta2).isEmpty();
/*     */     }
/* 289 */     if (meta2 == null) {
/* 290 */       return ((CraftMetaItem)meta1).isEmpty();
/*     */     }
/*     */     
/* 293 */     return equals((CraftMetaItem)meta1, (CraftMetaItem)meta2);
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
/*     */   boolean equals(CraftMetaItem meta1, CraftMetaItem meta2) {
/* 306 */     return (meta1.equalsCommon(meta2) && meta1.notUncommon(meta2) && meta2.notUncommon(meta1));
/*     */   }
/*     */   
/*     */   public static CraftItemFactory instance() {
/* 310 */     return instance;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemMeta asMetaFor(ItemMeta meta, ItemStack stack) {
/* 315 */     Validate.notNull(stack, "Stack cannot be null");
/* 316 */     return asMetaFor(meta, stack.getType());
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemMeta asMetaFor(ItemMeta meta, Material material) {
/* 321 */     Validate.notNull(material, "Material cannot be null");
/* 322 */     if (!(meta instanceof CraftMetaItem)) {
/* 323 */       throw new IllegalArgumentException("Meta of " + ((meta != null) ? meta.getClass().toString() : "null") + " not created by " + CraftItemFactory.class.getName());
/*     */     }
/* 325 */     return getItemMeta(material, (CraftMetaItem)meta);
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getDefaultLeatherColor() {
/* 330 */     return DEFAULT_LEATHER_COLOR;
/*     */   }
/*     */ 
/*     */   
/*     */   public Material updateMaterial(ItemMeta meta, Material material) throws IllegalArgumentException {
/* 335 */     return ((CraftMetaItem)meta).updateMaterial(material);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack ensureServerConversions(ItemStack item) {
/* 340 */     return CraftItemStack.asCraftMirror(CraftItemStack.asNMSCopy(item));
/*     */   }
/*     */ 
/*     */   
/*     */   public String getI18NDisplayName(ItemStack item) {
/* 345 */     ItemStack nms = null;
/* 346 */     if (item instanceof CraftItemStack) {
/* 347 */       nms = ((CraftItemStack)item).handle;
/*     */     }
/* 349 */     if (nms == null) {
/* 350 */       nms = CraftItemStack.asNMSCopy(item);
/*     */     }
/*     */     
/* 353 */     return (nms != null) ? LocaleLanguage.getInstance().translateKey(nms.getItem().getName()) : null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Content hoverContentOf(ItemStack itemStack) {
/* 358 */     ItemTag itemTag = ItemTag.ofNbt(CraftItemStack.asNMSCopy(itemStack).getOrCreateTag().toString());
/* 359 */     return (Content)new Item(itemStack
/* 360 */         .getType().getKey().toString(), itemStack
/* 361 */         .getAmount(), itemTag);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Content hoverContentOf(Entity entity) {
/* 367 */     return hoverContentOf(entity, StringUtils.isBlank(entity.getCustomName()) ? null : (BaseComponent)new TextComponent(entity.getCustomName()));
/*     */   }
/*     */ 
/*     */   
/*     */   public Content hoverContentOf(Entity entity, String customName) {
/* 372 */     return hoverContentOf(entity, StringUtils.isBlank(customName) ? null : (BaseComponent)new TextComponent(customName));
/*     */   }
/*     */ 
/*     */   
/*     */   public Content hoverContentOf(Entity entity, BaseComponent customName) {
/* 377 */     return (Content)new Entity(entity
/* 378 */         .getType().getKey().toString(), entity
/* 379 */         .getUniqueId().toString(), customName);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Content hoverContentOf(Entity entity, BaseComponent[] customName) {
/* 385 */     return (Content)new Entity(entity
/* 386 */         .getType().getKey().toString(), entity
/* 387 */         .getUniqueId().toString(), (BaseComponent)new TextComponent(customName));
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\inventory\CraftItemFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */