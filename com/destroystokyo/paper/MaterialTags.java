/*     */ package com.destroystokyo.paper;
/*     */ 
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.NamespacedKey;
/*     */ import org.bukkit.Tag;
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
/*     */ public class MaterialTags
/*     */ {
/*     */   private static NamespacedKey keyFor(String key) {
/*  39 */     return new NamespacedKey("paper", key + "_settag");
/*     */   }
/*  41 */   public static final MaterialSetTag ARROWS = (new MaterialSetTag(keyFor("arrows"), new Material[0]))
/*  42 */     .endsWith("ARROW")
/*  43 */     .ensureSize("ARROWS", 3);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  48 */   public static final MaterialSetTag BEDS = (new MaterialSetTag(keyFor("beds"), new Material[0]))
/*  49 */     .endsWith("_BED")
/*  50 */     .ensureSize("BEDS", 16);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  55 */   public static final MaterialSetTag BUCKETS = (new MaterialSetTag(keyFor("buckets"), new Material[0]))
/*  56 */     .endsWith("BUCKET")
/*  57 */     .ensureSize("BUCKETS", 8);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  62 */   public static final MaterialSetTag COALS = (new MaterialSetTag(keyFor("coals"), new Material[0]))
/*  63 */     .add(new Material[] { Material.COAL, Material.CHARCOAL });
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  68 */   public static final MaterialSetTag COBBLESTONE_WALLS = (new MaterialSetTag(keyFor("cobblestone_walls"), new Material[0]))
/*  69 */     .endsWith("COBBLESTONE_WALL")
/*  70 */     .ensureSize("COBBLESTONE_WALLS", 2);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  75 */   public static final MaterialSetTag COBBLESTONES = (new MaterialSetTag(keyFor("cobblestones"), new Material[0]))
/*  76 */     .add(new Material[] { Material.COBBLESTONE, Material.MOSSY_COBBLESTONE });
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  81 */   public static final MaterialSetTag CONCRETES = (new MaterialSetTag(keyFor("concretes"), new Material[0]))
/*  82 */     .endsWith("_CONCRETE")
/*  83 */     .ensureSize("CONCRETES", 16);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  88 */   public static final MaterialSetTag CONCRETE_POWDER = (new MaterialSetTag(keyFor("concrete_powder"), new Material[0]))
/*  89 */     .endsWith("_CONCRETE_POWDER")
/*  90 */     .ensureSize("CONCRETE_POWDER", 16);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  95 */   public static final MaterialSetTag COOKED_FISH = (new MaterialSetTag(keyFor("cooked_fish"), new Material[0]))
/*  96 */     .add(new Material[] { Material.COOKED_COD, Material.COOKED_SALMON });
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 101 */   public static final MaterialSetTag DYES = (new MaterialSetTag(keyFor("dyes"), new Material[0]))
/* 102 */     .endsWith("_DYE")
/* 103 */     .ensureSize("DYES", 16);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 108 */   public static final MaterialSetTag FENCE_GATES = (new MaterialSetTag(keyFor("fence_gates"), new Material[0]))
/* 109 */     .endsWith("_GATE")
/* 110 */     .ensureSize("FENCE_GATES", 8);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 115 */   public static final MaterialSetTag FENCES = (new MaterialSetTag(keyFor("fences"), new Material[0]))
/* 116 */     .endsWith("_FENCE")
/* 117 */     .ensureSize("FENCES", 9);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 122 */   public static final MaterialSetTag FISH_BUCKETS = (new MaterialSetTag(keyFor("fish_buckets"), new Material[0]))
/* 123 */     .add(new Material[] { Material.COD_BUCKET, Material.PUFFERFISH_BUCKET, Material.SALMON_BUCKET, Material.TROPICAL_FISH_BUCKET });
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 128 */   public static final MaterialSetTag GLASS = (new MaterialSetTag(keyFor("glass"), new Material[0]))
/* 129 */     .endsWith("_GLASS")
/* 130 */     .add(new Material[] { Material.GLASS
/* 131 */       }).ensureSize("GLASS", 17);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 136 */   public static final MaterialSetTag GLASS_PANES = (new MaterialSetTag(keyFor("glass_panes"), new Material[0]))
/* 137 */     .endsWith("GLASS_PANE")
/* 138 */     .ensureSize("GLASS_PANES", 17);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 143 */   public static final MaterialSetTag GLAZED_TERRACOTTA = (new MaterialSetTag(keyFor("glazed_terracotta"), new Material[0]))
/* 144 */     .endsWith("GLAZED_TERRACOTTA")
/* 145 */     .ensureSize("GLAZED_TERRACOTTA", 16);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 150 */   public static final MaterialSetTag STAINED_TERRACOTTA = (new MaterialSetTag(keyFor("stained_terracotta"), new Material[0]))
/* 151 */     .endsWith("TERRACOTTA")
/* 152 */     .not(new Material[] { Material.TERRACOTTA
/* 153 */       }).notEndsWith("GLAZED_TERRACOTTA")
/* 154 */     .ensureSize("STAINED_TERRACOTTA", 16);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 159 */   public static final MaterialSetTag TERRACOTTA = (new MaterialSetTag(keyFor("terracotta"), new Material[0]))
/* 160 */     .endsWith("TERRACOTTA")
/* 161 */     .ensureSize("TERRACOTTA", 33);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 167 */   public static final MaterialSetTag GOLDEN_APPLES = (new MaterialSetTag(keyFor("golden_apples"), new Material[0]))
/* 168 */     .endsWith("GOLDEN_APPLE")
/* 169 */     .ensureSize("GOLDEN_APPLES", 2);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 174 */   public static final MaterialSetTag HORSE_ARMORS = (new MaterialSetTag(keyFor("horse_armors"), new Material[0]))
/* 175 */     .endsWith("_HORSE_ARMOR")
/* 176 */     .ensureSize("HORSE_ARMORS", 4);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 181 */   public static final MaterialSetTag INFESTED_BLOCKS = (new MaterialSetTag(keyFor("infested_blocks"), new Material[0]))
/* 182 */     .startsWith("INFESTED_")
/* 183 */     .ensureSize("INFESTED_BLOCKS", 6);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 188 */   public static final MaterialSetTag MUSHROOM_BLOCKS = (new MaterialSetTag(keyFor("mushroom_blocks"), new Material[0]))
/* 189 */     .endsWith("MUSHROOM_BLOCK")
/* 190 */     .add(new Material[] { Material.MUSHROOM_STEM
/* 191 */       }).ensureSize("MUSHROOM_BLOCKS", 3);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 196 */   public static final MaterialSetTag MUSHROOMS = (new MaterialSetTag(keyFor("mushrooms"), new Material[0]))
/* 197 */     .add(new Material[] { Material.BROWN_MUSHROOM, Material.RED_MUSHROOM });
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 202 */   public static final MaterialSetTag MUSIC_DISCS = (new MaterialSetTag(keyFor("music_discs"), new Material[0]))
/* 203 */     .startsWith("MUSIC_DISC_");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 208 */   public static final MaterialSetTag ORES = (new MaterialSetTag(keyFor("ores"), new Material[0]))
/* 209 */     .add(new Material[] { Material.ANCIENT_DEBRIS
/* 210 */       }).endsWith("_ORE")
/* 211 */     .ensureSize("ORES", 10);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 216 */   public static final MaterialSetTag PISTONS = (new MaterialSetTag(keyFor("pistons"), new Material[0]))
/* 217 */     .contains("PISTON")
/* 218 */     .ensureSize("PISTONS", 4);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 223 */   public static final MaterialSetTag POTATOES = (new MaterialSetTag(keyFor("potatoes"), new Material[0]))
/* 224 */     .endsWith("POTATO")
/* 225 */     .ensureSize("POTATOES", 3);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 230 */   public static final MaterialSetTag PRESSURE_PLATES = (new MaterialSetTag(keyFor("pressure_plates"), new Material[0]))
/* 231 */     .endsWith("_PRESSURE_PLATE")
/* 232 */     .ensureSize("PRESSURE_PLATES", 12);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 237 */   public static final MaterialSetTag PRISMARINE = (new MaterialSetTag(keyFor("prismarine"), new Material[0]))
/* 238 */     .add(new Material[] { Material.PRISMARINE, Material.PRISMARINE_BRICKS, Material.DARK_PRISMARINE });
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 243 */   public static final MaterialSetTag PRISMARINE_SLABS = (new MaterialSetTag(keyFor("prismarine_slabs"), new Material[0]))
/* 244 */     .add(new Material[] { Material.PRISMARINE_SLAB, Material.PRISMARINE_BRICK_SLAB, Material.DARK_PRISMARINE_SLAB });
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 249 */   public static final MaterialSetTag PRISMARINE_STAIRS = (new MaterialSetTag(keyFor("prismarine_stairs"), new Material[0]))
/* 250 */     .add(new Material[] { Material.PRISMARINE_STAIRS, Material.PRISMARINE_BRICK_STAIRS, Material.DARK_PRISMARINE_STAIRS });
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 255 */   public static final MaterialSetTag PUMPKINS = (new MaterialSetTag(keyFor("pumpkins"), new Material[0]))
/* 256 */     .add(new Material[] { Material.CARVED_PUMPKIN, Material.JACK_O_LANTERN, Material.PUMPKIN });
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 261 */   public static final MaterialSetTag QUARTZ_BLOCKS = (new MaterialSetTag(keyFor("quartz_blocks"), new Material[0]))
/* 262 */     .add(new Material[] { Material.QUARTZ_BLOCK, Material.QUARTZ_PILLAR, Material.CHISELED_QUARTZ_BLOCK, Material.SMOOTH_QUARTZ });
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 267 */   public static final MaterialSetTag RAW_FISH = (new MaterialSetTag(keyFor("raw_fish"), new Material[0]))
/* 268 */     .add(new Material[] { Material.COD, Material.PUFFERFISH, Material.SALMON, Material.TROPICAL_FISH });
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 273 */   public static final MaterialSetTag RED_SANDSTONES = (new MaterialSetTag(keyFor("red_sandstones"), new Material[0]))
/* 274 */     .endsWith("RED_SANDSTONE")
/* 275 */     .ensureSize("RED_SANDSTONES", 4);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 280 */   public static final MaterialSetTag SANDSTONES = (new MaterialSetTag(keyFor("sandstones"), new Material[0]))
/* 281 */     .add(new Material[] { Material.SANDSTONE, Material.CHISELED_SANDSTONE, Material.CUT_SANDSTONE, Material.SMOOTH_SANDSTONE });
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 286 */   public static final MaterialSetTag SPONGES = (new MaterialSetTag(keyFor("sponges"), new Material[0]))
/* 287 */     .endsWith("SPONGE")
/* 288 */     .ensureSize("SPONGES", 2);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 293 */   public static final MaterialSetTag SHULKER_BOXES = (new MaterialSetTag(keyFor("shulker_boxes"), new Material[0]))
/* 294 */     .endsWith("SHULKER_BOX")
/* 295 */     .ensureSize("SHULKER_BOXES", 17);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 300 */   public static final MaterialSetTag SKULLS = (new MaterialSetTag(keyFor("skulls"), new Material[0]))
/* 301 */     .endsWith("_HEAD")
/* 302 */     .endsWith("_SKULL")
/* 303 */     .not(new Material[] { Material.PISTON_HEAD
/* 304 */       }).ensureSize("SKULLS", 12);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 309 */   public static final MaterialSetTag SPAWN_EGGS = (new MaterialSetTag(keyFor("spawn_eggs"), new Material[0]))
/* 310 */     .endsWith("_SPAWN_EGG")
/* 311 */     .ensureSize("SPAWN_EGGS", 64);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 316 */   public static final MaterialSetTag STAINED_GLASS = (new MaterialSetTag(keyFor("stained_glass"), new Material[0]))
/* 317 */     .endsWith("_STAINED_GLASS")
/* 318 */     .ensureSize("STAINED_GLASS", 16);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 323 */   public static final MaterialSetTag STAINED_GLASS_PANES = (new MaterialSetTag(keyFor("stained_glass_panes"), new Material[0]))
/* 324 */     .endsWith("STAINED_GLASS_PANE")
/* 325 */     .ensureSize("STAINED_GLASS_PANES", 16);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 330 */   public static final MaterialSetTag TRAPDOORS = (new MaterialSetTag(keyFor("trapdoors"), new Material[0]))
/* 331 */     .endsWith("_TRAPDOOR")
/* 332 */     .ensureSize("TRAPDOORS", 9);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 337 */   public static final MaterialSetTag WOODEN_FENCES = (new MaterialSetTag(keyFor("wooden_fences"), new Material[0]))
/* 338 */     .endsWith("_FENCE")
/* 339 */     .not(new Material[] { Material.NETHER_BRICK_FENCE
/* 340 */       }).ensureSize("WOODEN_FENCES", 8);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 345 */   public static final MaterialSetTag WOODEN_TRAPDOORS = (new MaterialSetTag(keyFor("wooden_trapdoors"), new Material[0]))
/* 346 */     .endsWith("_TRAPDOOR")
/* 347 */     .not(new Material[] { Material.IRON_TRAPDOOR
/* 348 */       }).ensureSize("WOODEN_TRAPDOORS", 8);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 353 */   public static final MaterialSetTag WOODEN_GATES = (new MaterialSetTag(keyFor("wooden_gates"), new Material[0]))
/* 354 */     .endsWith("_GATE")
/* 355 */     .ensureSize("WOODEN_GATES", 8);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 360 */   public static final MaterialSetTag PURPUR = (new MaterialSetTag(keyFor("purpur"), new Material[0]))
/* 361 */     .startsWith("PURPUR_")
/* 362 */     .ensureSize("PURPUR", 4);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 367 */   public static final MaterialSetTag SIGNS = (new MaterialSetTag(keyFor("signs"), new Material[0]))
/* 368 */     .endsWith("_SIGN")
/* 369 */     .ensureSize("SIGNS", 16);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 374 */   public static final MaterialSetTag TORCH = (new MaterialSetTag(keyFor("torch"), new Material[0]))
/* 375 */     .add(new Material[] { Material.TORCH, Material.WALL_TORCH
/* 376 */       }).ensureSize("TORCH", 2);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 381 */   public static final MaterialSetTag REDSTONE_TORCH = (new MaterialSetTag(keyFor("restone_torch"), new Material[0]))
/* 382 */     .add(new Material[] { Material.REDSTONE_TORCH, Material.REDSTONE_WALL_TORCH
/* 383 */       }).ensureSize("REDSTONE_TORCH", 2);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 388 */   public static final MaterialSetTag SOUL_TORCH = (new MaterialSetTag(keyFor("soul_torch"), new Material[0]))
/* 389 */     .add(new Material[] { Material.SOUL_TORCH, Material.SOUL_WALL_TORCH
/* 390 */       }).ensureSize("SOUL_TORCH", 2);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 395 */   public static final MaterialSetTag TORCHES = (new MaterialSetTag(keyFor("torches"), new Material[0]))
/* 396 */     .add(new MaterialSetTag[] { TORCH, REDSTONE_TORCH, SOUL_TORCH
/* 397 */       }).ensureSize("TORCHES", 6);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 402 */   public static final MaterialSetTag LANTERNS = (new MaterialSetTag(keyFor("lanterns"), new Material[0]))
/* 403 */     .add(new Material[] { Material.LANTERN, Material.SOUL_LANTERN
/* 404 */       }).ensureSize("LANTERNS", 2);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 409 */   public static final MaterialSetTag RAILS = (new MaterialSetTag(keyFor("rails"), new Material[0]))
/* 410 */     .endsWith("RAIL")
/* 411 */     .ensureSize("RAILS", 4);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 416 */   public static final MaterialSetTag SWORDS = (new MaterialSetTag(keyFor("swords"), new Material[0]))
/* 417 */     .endsWith("_SWORD")
/* 418 */     .ensureSize("SWORDS", 6);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 423 */   public static final MaterialSetTag SHOVELS = (new MaterialSetTag(keyFor("shovels"), new Material[0]))
/* 424 */     .endsWith("_SHOVEL")
/* 425 */     .ensureSize("SHOVELS", 6);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 430 */   public static final MaterialSetTag PICKAXES = (new MaterialSetTag(keyFor("pickaxes"), new Material[0]))
/* 431 */     .endsWith("_PICKAXE")
/* 432 */     .ensureSize("PICKAXES", 6);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 437 */   public static final MaterialSetTag AXES = (new MaterialSetTag(keyFor("axes"), new Material[0]))
/* 438 */     .endsWith("_AXE")
/* 439 */     .ensureSize("AXES", 6);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 444 */   public static final MaterialSetTag HOES = (new MaterialSetTag(keyFor("hoes"), new Material[0]))
/* 445 */     .endsWith("_HOE")
/* 446 */     .ensureSize("HOES", 6);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 451 */   public static final MaterialSetTag HELMETS = (new MaterialSetTag(keyFor("helmets"), new Material[0]))
/* 452 */     .endsWith("_HELMET")
/* 453 */     .ensureSize("HELMETS", 7);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 458 */   public static final MaterialSetTag HEAD_EQUIPPABLE = (new MaterialSetTag(keyFor("head_equippable"), new Material[0]))
/* 459 */     .endsWith("_HELMET")
/* 460 */     .add(new MaterialSetTag[] { SKULLS
/* 461 */       }).add(new Material[] { Material.CARVED_PUMPKIN
/* 462 */       }).ensureSize("HEAD_EQUIPPABLE", 20);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 467 */   public static final MaterialSetTag CHESTPLATES = (new MaterialSetTag(keyFor("chestplates"), new Material[0]))
/* 468 */     .endsWith("_CHESTPLATE")
/* 469 */     .ensureSize("CHESTPLATES", 6);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 474 */   public static final MaterialSetTag CHEST_EQUIPPABLE = (new MaterialSetTag(keyFor("chest_equippable"), new Material[0]))
/* 475 */     .endsWith("_CHESTPLATE")
/* 476 */     .add(new Material[] { Material.ELYTRA
/* 477 */       }).ensureSize("CHEST_EQUIPPABLE", 7);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 482 */   public static final MaterialSetTag LEGGINGS = (new MaterialSetTag(keyFor("leggings"), new Material[0]))
/* 483 */     .endsWith("_LEGGINGS")
/* 484 */     .ensureSize("LEGGINGS", 6);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 489 */   public static final MaterialSetTag BOOTS = (new MaterialSetTag(keyFor("boots"), new Material[0]))
/* 490 */     .endsWith("_BOOTS")
/* 491 */     .ensureSize("BOOTS", 6);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 496 */   public static final MaterialSetTag BOWS = (new MaterialSetTag(keyFor("bows"), new Material[0]))
/* 497 */     .add(new Material[] { Material.BOW
/* 498 */       }).add(new Material[] { Material.CROSSBOW
/* 499 */       }).ensureSize("BOWS", 2);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 504 */   public static final MaterialSetTag THROWABLE_PROJECTILES = (new MaterialSetTag(keyFor("throwable_projectiles"), new Material[0]))
/* 505 */     .add(new Material[] { Material.EGG, Material.SNOWBALL, Material.SPLASH_POTION, Material.TRIDENT, Material.ENDER_PEARL, Material.EXPERIENCE_BOTTLE, Material.FIREWORK_ROCKET });
/*     */ 
/*     */   
/* 508 */   public static final MaterialSetTag COLORABLE = (new MaterialSetTag(keyFor("colorable"), new Material[0]))
/* 509 */     .add((Tag<Material>[])new Tag[] { Tag.WOOL, Tag.CARPETS }).add(new MaterialSetTag[] { SHULKER_BOXES, STAINED_GLASS, STAINED_GLASS_PANES, CONCRETES, BEDS });
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\MaterialTags.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */