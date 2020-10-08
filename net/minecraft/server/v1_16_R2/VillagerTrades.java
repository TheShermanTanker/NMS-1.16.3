/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.google.common.collect.Lists;
/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import java.util.stream.Collectors;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public class VillagerTrades {
/*     */   public static final Map<VillagerProfession, Int2ObjectMap<IMerchantRecipeOption[]>> a;
/*     */   
/*  17 */   static { a = SystemUtils.<Map<VillagerProfession, Int2ObjectMap<IMerchantRecipeOption[]>>>a(Maps.newHashMap(), hashmap -> { hashmap.put(VillagerProfession.FARMER, a(ImmutableMap.of(Integer.valueOf(1), new IMerchantRecipeOption[] { new b(Items.WHEAT, 20, 16, 2), new b(Items.POTATO, 26, 16, 2), new b(Items.CARROT, 22, 16, 2), new b(Items.BEETROOT, 15, 16, 2), new h(Items.BREAD, 1, 6, 16, 1) }Integer.valueOf(2), new IMerchantRecipeOption[] { new b(Blocks.PUMPKIN, 6, 12, 10), new h(Items.PUMPKIN_PIE, 1, 4, 5), new h(Items.APPLE, 1, 4, 16, 5) }Integer.valueOf(3), new IMerchantRecipeOption[] { new h(Items.COOKIE, 3, 18, 10), new b(Blocks.MELON, 4, 12, 20) }Integer.valueOf(4), new IMerchantRecipeOption[] { new h(Blocks.CAKE, 1, 1, 12, 15), new i(MobEffects.NIGHT_VISION, 100, 15), new i(MobEffects.JUMP, 160, 15), new i(MobEffects.WEAKNESS, 140, 15), new i(MobEffects.BLINDNESS, 120, 15), new i(MobEffects.POISON, 280, 15), new i(MobEffects.SATURATION, 7, 15) }Integer.valueOf(5), new IMerchantRecipeOption[] { new h(Items.GOLDEN_CARROT, 3, 3, 30), new h(Items.GLISTERING_MELON_SLICE, 4, 3, 30) }))); hashmap.put(VillagerProfession.FISHERMAN, a(ImmutableMap.of(Integer.valueOf(1), new IMerchantRecipeOption[] { new b(Items.STRING, 20, 16, 2), new b(Items.COAL, 10, 16, 2), new g(Items.COD, 6, Items.COOKED_COD, 6, 16, 1), new h(Items.COD_BUCKET, 3, 1, 16, 1) }Integer.valueOf(2), new IMerchantRecipeOption[] { new b(Items.COD, 15, 16, 10), new g(Items.SALMON, 6, Items.COOKED_SALMON, 6, 16, 5), new h(Items.rn, 2, 1, 5) }Integer.valueOf(3), new IMerchantRecipeOption[] { new b(Items.SALMON, 13, 16, 20), new e(Items.FISHING_ROD, 3, 3, 10, 0.2F) }Integer.valueOf(4), new IMerchantRecipeOption[] { new b(Items.TROPICAL_FISH, 6, 12, 30) }Integer.valueOf(5), new IMerchantRecipeOption[] { new b(Items.PUFFERFISH, 4, 12, 30), new c(1, 12, 30, (Map<VillagerType, Item>)ImmutableMap.builder().put(VillagerType.PLAINS, Items.OAK_BOAT).put(VillagerType.TAIGA, Items.SPRUCE_BOAT).put(VillagerType.SNOW, Items.SPRUCE_BOAT).put(VillagerType.DESERT, Items.JUNGLE_BOAT).put(VillagerType.JUNGLE, Items.JUNGLE_BOAT).put(VillagerType.SAVANNA, Items.ACACIA_BOAT).put(VillagerType.SWAMP, Items.DARK_OAK_BOAT).build()) }))); hashmap.put(VillagerProfession.SHEPHERD, a(ImmutableMap.of(Integer.valueOf(1), new IMerchantRecipeOption[] { new b(Blocks.WHITE_WOOL, 18, 16, 2), new b(Blocks.BROWN_WOOL, 18, 16, 2), new b(Blocks.BLACK_WOOL, 18, 16, 2), new b(Blocks.GRAY_WOOL, 18, 16, 2), new h(Items.SHEARS, 2, 1, 1) }Integer.valueOf(2), new IMerchantRecipeOption[] { new b(Items.WHITE_DYE, 12, 16, 10), new b(Items.GRAY_DYE, 12, 16, 10), new b(Items.BLACK_DYE, 12, 16, 10), new b(Items.LIGHT_BLUE_DYE, 12, 16, 10), new b(Items.LIME_DYE, 12, 16, 10), new h(Blocks.WHITE_WOOL, 1, 1, 16, 5), new h(Blocks.ORANGE_WOOL, 1, 1, 16, 5), new h(Blocks.MAGENTA_WOOL, 1, 1, 16, 5), new h(Blocks.LIGHT_BLUE_WOOL, 1, 1, 16, 5), new h(Blocks.YELLOW_WOOL, 1, 1, 16, 5), 
/*     */                     new h(Blocks.LIME_WOOL, 1, 1, 16, 5), new h(Blocks.PINK_WOOL, 1, 1, 16, 5), new h(Blocks.GRAY_WOOL, 1, 1, 16, 5), new h(Blocks.LIGHT_GRAY_WOOL, 1, 1, 16, 5), new h(Blocks.CYAN_WOOL, 1, 1, 16, 5), new h(Blocks.PURPLE_WOOL, 1, 1, 16, 5), new h(Blocks.BLUE_WOOL, 1, 1, 16, 5), new h(Blocks.BROWN_WOOL, 1, 1, 16, 5), new h(Blocks.GREEN_WOOL, 1, 1, 16, 5), new h(Blocks.RED_WOOL, 1, 1, 16, 5), 
/*     */                     new h(Blocks.BLACK_WOOL, 1, 1, 16, 5), new h(Blocks.WHITE_CARPET, 1, 4, 16, 5), new h(Blocks.ORANGE_CARPET, 1, 4, 16, 5), new h(Blocks.MAGENTA_CARPET, 1, 4, 16, 5), new h(Blocks.LIGHT_BLUE_CARPET, 1, 4, 16, 5), new h(Blocks.YELLOW_CARPET, 1, 4, 16, 5), new h(Blocks.LIME_CARPET, 1, 4, 16, 5), new h(Blocks.PINK_CARPET, 1, 4, 16, 5), new h(Blocks.GRAY_CARPET, 1, 4, 16, 5), new h(Blocks.LIGHT_GRAY_CARPET, 1, 4, 16, 5), 
/*     */                     new h(Blocks.CYAN_CARPET, 1, 4, 16, 5), new h(Blocks.PURPLE_CARPET, 1, 4, 16, 5), new h(Blocks.BLUE_CARPET, 1, 4, 16, 5), new h(Blocks.BROWN_CARPET, 1, 4, 16, 5), new h(Blocks.GREEN_CARPET, 1, 4, 16, 5), new h(Blocks.RED_CARPET, 1, 4, 16, 5), new h(Blocks.BLACK_CARPET, 1, 4, 16, 5) }Integer.valueOf(3), new IMerchantRecipeOption[] { 
/*     */                     new b(Items.YELLOW_DYE, 12, 16, 20), new b(Items.LIGHT_GRAY_DYE, 12, 16, 20), new b(Items.ORANGE_DYE, 12, 16, 20), new b(Items.RED_DYE, 12, 16, 20), new b(Items.PINK_DYE, 12, 16, 20), new h(Blocks.WHITE_BED, 3, 1, 12, 10), new h(Blocks.YELLOW_BED, 3, 1, 12, 10), new h(Blocks.RED_BED, 3, 1, 12, 10), new h(Blocks.BLACK_BED, 3, 1, 12, 10), new h(Blocks.BLUE_BED, 3, 1, 12, 10), 
/*     */                     new h(Blocks.BROWN_BED, 3, 1, 12, 10), new h(Blocks.CYAN_BED, 3, 1, 12, 10), new h(Blocks.GRAY_BED, 3, 1, 12, 10), new h(Blocks.GREEN_BED, 3, 1, 12, 10), new h(Blocks.LIGHT_BLUE_BED, 3, 1, 12, 10), new h(Blocks.LIGHT_GRAY_BED, 3, 1, 12, 10), new h(Blocks.LIME_BED, 3, 1, 12, 10), new h(Blocks.MAGENTA_BED, 3, 1, 12, 10), new h(Blocks.ORANGE_BED, 3, 1, 12, 10), new h(Blocks.PINK_BED, 3, 1, 12, 10), 
/*     */                     new h(Blocks.PURPLE_BED, 3, 1, 12, 10) }Integer.valueOf(4), new IMerchantRecipeOption[] { 
/*     */                     new b(Items.BROWN_DYE, 12, 16, 30), new b(Items.PURPLE_DYE, 12, 16, 30), new b(Items.BLUE_DYE, 12, 16, 30), new b(Items.GREEN_DYE, 12, 16, 30), new b(Items.MAGENTA_DYE, 12, 16, 30), new b(Items.CYAN_DYE, 12, 16, 30), new h(Items.WHITE_BANNER, 3, 1, 12, 15), new h(Items.BLUE_BANNER, 3, 1, 12, 15), new h(Items.LIGHT_BLUE_BANNER, 3, 1, 12, 15), new h(Items.RED_BANNER, 3, 1, 12, 15), 
/*     */                     new h(Items.PINK_BANNER, 3, 1, 12, 15), new h(Items.GREEN_BANNER, 3, 1, 12, 15), new h(Items.LIME_BANNER, 3, 1, 12, 15), new h(Items.GRAY_BANNER, 3, 1, 12, 15), new h(Items.BLACK_BANNER, 3, 1, 12, 15), new h(Items.PURPLE_BANNER, 3, 1, 12, 15), new h(Items.MAGENTA_BANNER, 3, 1, 12, 15), new h(Items.CYAN_BANNER, 3, 1, 12, 15), new h(Items.BROWN_BANNER, 3, 1, 12, 15), new h(Items.YELLOW_BANNER, 3, 1, 12, 15), 
/*     */                     new h(Items.ORANGE_BANNER, 3, 1, 12, 15), new h(Items.LIGHT_GRAY_BANNER, 3, 1, 12, 15) }Integer.valueOf(5), new IMerchantRecipeOption[] { new h(Items.PAINTING, 2, 3, 30) }))); hashmap.put(VillagerProfession.FLETCHER, a(ImmutableMap.of(Integer.valueOf(1), new IMerchantRecipeOption[] { new b(Items.STICK, 32, 16, 2), new h(Items.ARROW, 1, 16, 1), new g(Blocks.GRAVEL, 10, Items.FLINT, 10, 12, 1) }Integer.valueOf(2), new IMerchantRecipeOption[] { new b(Items.FLINT, 26, 12, 10), new h(Items.BOW, 2, 1, 5) }Integer.valueOf(3), new IMerchantRecipeOption[] { new b(Items.STRING, 14, 16, 20), new h(Items.CROSSBOW, 3, 1, 10) }Integer.valueOf(4), new IMerchantRecipeOption[] { new b(Items.FEATHER, 24, 16, 30), new e(Items.BOW, 2, 3, 15) }Integer.valueOf(5), new IMerchantRecipeOption[] { new b(Items.es, 8, 12, 30), new e(Items.CROSSBOW, 3, 3, 15), new j(Items.ARROW, 5, Items.TIPPED_ARROW, 5, 2, 12, 30) }))); hashmap.put(VillagerProfession.LIBRARIAN, a(ImmutableMap.builder().put(Integer.valueOf(1), new IMerchantRecipeOption[] { new b(Items.PAPER, 24, 16, 2), new d(1), new h(Blocks.BOOKSHELF, 9, 1, 12, 1) }).put(Integer.valueOf(2), new IMerchantRecipeOption[] { new b(Items.BOOK, 4, 12, 10), new d(5), new h(Items.rk, 1, 1, 5) }).put(Integer.valueOf(3), new IMerchantRecipeOption[] { new b(Items.INK_SAC, 5, 12, 20), new d(10), new h(Items.az, 1, 4, 10) }).put(Integer.valueOf(4), new IMerchantRecipeOption[] { new b(Items.WRITABLE_BOOK, 2, 12, 30), new d(15), new h(Items.CLOCK, 5, 1, 15), new h(Items.COMPASS, 4, 1, 15) }).put(Integer.valueOf(5), new IMerchantRecipeOption[] { new h(Items.NAME_TAG, 20, 1, 30) }).build())); hashmap.put(VillagerProfession.CARTOGRAPHER, a(ImmutableMap.of(Integer.valueOf(1), new IMerchantRecipeOption[] { new b(Items.PAPER, 24, 16, 2), new h(Items.MAP, 7, 1, 1) }Integer.valueOf(2), new IMerchantRecipeOption[] { new b(Items.dP, 11, 16, 10), new k(13, StructureGenerator.MONUMENT, MapIcon.Type.MONUMENT, 12, 5) }Integer.valueOf(3), new IMerchantRecipeOption[] { new b(Items.COMPASS, 1, 12, 20), new k(14, StructureGenerator.MANSION, MapIcon.Type.MANSION, 12, 10) }Integer.valueOf(4), new IMerchantRecipeOption[] { 
/*     */                     new h(Items.ITEM_FRAME, 7, 1, 15), new h(Items.WHITE_BANNER, 3, 1, 15), new h(Items.BLUE_BANNER, 3, 1, 15), new h(Items.LIGHT_BLUE_BANNER, 3, 1, 15), new h(Items.RED_BANNER, 3, 1, 15), new h(Items.PINK_BANNER, 3, 1, 15), new h(Items.GREEN_BANNER, 3, 1, 15), new h(Items.LIME_BANNER, 3, 1, 15), new h(Items.GRAY_BANNER, 3, 1, 15), new h(Items.BLACK_BANNER, 3, 1, 15), 
/*     */                     new h(Items.PURPLE_BANNER, 3, 1, 15), new h(Items.MAGENTA_BANNER, 3, 1, 15), new h(Items.CYAN_BANNER, 3, 1, 15), new h(Items.BROWN_BANNER, 3, 1, 15), new h(Items.YELLOW_BANNER, 3, 1, 15), new h(Items.ORANGE_BANNER, 3, 1, 15), new h(Items.LIGHT_GRAY_BANNER, 3, 1, 15) }Integer.valueOf(5), new IMerchantRecipeOption[] { new h(Items.GLOBE_BANNER_PATTERN, 8, 1, 30) }))); hashmap.put(VillagerProfession.CLERIC, a(ImmutableMap.of(Integer.valueOf(1), new IMerchantRecipeOption[] { new b(Items.ROTTEN_FLESH, 32, 16, 2), new h(Items.REDSTONE, 1, 2, 1) }Integer.valueOf(2), new IMerchantRecipeOption[] { new b(Items.GOLD_INGOT, 3, 12, 10), new h(Items.LAPIS_LAZULI, 1, 1, 5) }Integer.valueOf(3), new IMerchantRecipeOption[] { new b(Items.RABBIT_FOOT, 2, 12, 20), new h(Blocks.GLOWSTONE, 4, 1, 12, 10) }Integer.valueOf(4), new IMerchantRecipeOption[] { new b(Items.SCUTE, 4, 12, 30), new b(Items.GLASS_BOTTLE, 9, 12, 30), new h(Items.ENDER_PEARL, 5, 1, 15) }Integer.valueOf(5), new IMerchantRecipeOption[] { new b(Items.NETHER_WART, 22, 12, 30), new h(Items.EXPERIENCE_BOTTLE, 3, 1, 30) }))); hashmap.put(VillagerProfession.ARMORER, a(ImmutableMap.of(Integer.valueOf(1), new IMerchantRecipeOption[] { new b(Items.COAL, 15, 16, 2), new h(new ItemStack(Items.IRON_LEGGINGS), 7, 1, 12, 1, 0.2F), new h(new ItemStack(Items.IRON_BOOTS), 4, 1, 12, 1, 0.2F), new h(new ItemStack(Items.IRON_HELMET), 5, 1, 12, 1, 0.2F), new h(new ItemStack(Items.IRON_CHESTPLATE), 9, 1, 12, 1, 0.2F) }Integer.valueOf(2), new IMerchantRecipeOption[] { new b(Items.IRON_INGOT, 4, 12, 10), new h(new ItemStack(Items.rj), 36, 1, 12, 5, 0.2F), new h(new ItemStack(Items.CHAINMAIL_BOOTS), 1, 1, 12, 5, 0.2F), new h(new ItemStack(Items.CHAINMAIL_LEGGINGS), 3, 1, 12, 5, 0.2F) }Integer.valueOf(3), new IMerchantRecipeOption[] { new b(Items.LAVA_BUCKET, 1, 12, 20), new b(Items.DIAMOND, 1, 12, 20), new h(new ItemStack(Items.CHAINMAIL_HELMET), 1, 1, 12, 10, 0.2F), new h(new ItemStack(Items.CHAINMAIL_CHESTPLATE), 4, 1, 12, 10, 0.2F), new h(new ItemStack(Items.SHIELD), 5, 1, 12, 10, 0.2F) }Integer.valueOf(4), new IMerchantRecipeOption[] { new e(Items.DIAMOND_LEGGINGS, 14, 3, 15, 0.2F), new e(Items.DIAMOND_BOOTS, 8, 3, 15, 0.2F) }Integer.valueOf(5), new IMerchantRecipeOption[] { new e(Items.DIAMOND_HELMET, 8, 3, 30, 0.2F), new e(Items.DIAMOND_CHESTPLATE, 16, 3, 30, 0.2F) }))); hashmap.put(VillagerProfession.WEAPONSMITH, a(ImmutableMap.of(Integer.valueOf(1), new IMerchantRecipeOption[] { new b(Items.COAL, 15, 16, 2), new h(new ItemStack(Items.IRON_AXE), 3, 1, 12, 1, 0.2F), new e(Items.IRON_SWORD, 2, 3, 1) }Integer.valueOf(2), new IMerchantRecipeOption[] { new b(Items.IRON_INGOT, 4, 12, 10), new h(new ItemStack(Items.rj), 36, 1, 12, 5, 0.2F) }Integer.valueOf(3), new IMerchantRecipeOption[] { new b(Items.FLINT, 24, 12, 20) }Integer.valueOf(4), new IMerchantRecipeOption[] { new b(Items.DIAMOND, 1, 12, 30), new e(Items.DIAMOND_AXE, 12, 3, 15, 0.2F) }Integer.valueOf(5), new IMerchantRecipeOption[] { new e(Items.DIAMOND_SWORD, 8, 3, 30, 0.2F) }))); hashmap.put(VillagerProfession.TOOLSMITH, a(ImmutableMap.of(Integer.valueOf(1), new IMerchantRecipeOption[] { new b(Items.COAL, 15, 16, 2), new h(new ItemStack(Items.STONE_AXE), 1, 1, 12, 1, 0.2F), new h(new ItemStack(Items.STONE_SHOVEL), 1, 1, 12, 1, 0.2F), new h(new ItemStack(Items.STONE_PICKAXE), 1, 1, 12, 1, 0.2F), new h(new ItemStack(Items.STONE_HOE), 1, 1, 12, 1, 0.2F) }Integer.valueOf(2), new IMerchantRecipeOption[] { new b(Items.IRON_INGOT, 4, 12, 10), new h(new ItemStack(Items.rj), 36, 1, 12, 5, 0.2F) }Integer.valueOf(3), new IMerchantRecipeOption[] { new b(Items.FLINT, 30, 12, 20), new e(Items.IRON_AXE, 1, 3, 10, 0.2F), new e(Items.IRON_SHOVEL, 2, 3, 10, 0.2F), new e(Items.IRON_PICKAXE, 3, 3, 10, 0.2F), new h(new ItemStack(Items.DIAMOND_HOE), 4, 1, 3, 10, 0.2F) }Integer.valueOf(4), new IMerchantRecipeOption[] { new b(Items.DIAMOND, 1, 12, 30), new e(Items.DIAMOND_AXE, 12, 3, 15, 0.2F), new e(Items.DIAMOND_SHOVEL, 5, 3, 15, 0.2F) }Integer.valueOf(5), new IMerchantRecipeOption[] { new e(Items.DIAMOND_PICKAXE, 13, 3, 30, 0.2F) }))); hashmap.put(VillagerProfession.BUTCHER, a(ImmutableMap.of(Integer.valueOf(1), new IMerchantRecipeOption[] { new b(Items.CHICKEN, 14, 16, 2), new b(Items.PORKCHOP, 7, 16, 2), new b(Items.RABBIT, 4, 16, 2), new h(Items.RABBIT_STEW, 1, 1, 1) }Integer.valueOf(2), new IMerchantRecipeOption[] { new b(Items.COAL, 15, 16, 2), new h(Items.COOKED_PORKCHOP, 1, 5, 16, 5), new h(Items.COOKED_CHICKEN, 1, 8, 16, 5) }Integer.valueOf(3), new IMerchantRecipeOption[] { new b(Items.MUTTON, 7, 16, 20), new b(Items.BEEF, 10, 16, 20) }Integer.valueOf(4), new IMerchantRecipeOption[] { new b(Items.ma, 10, 12, 30) }Integer.valueOf(5), new IMerchantRecipeOption[] { new b(Items.SWEET_BERRIES, 10, 12, 30) }))); hashmap.put(VillagerProfession.LEATHERWORKER, a(ImmutableMap.of(Integer.valueOf(1), new IMerchantRecipeOption[] { new b(Items.LEATHER, 6, 16, 2), new a(Items.LEATHER_LEGGINGS, 3), new a(Items.LEATHER_CHESTPLATE, 7) }Integer.valueOf(2), new IMerchantRecipeOption[] { new b(Items.FLINT, 26, 12, 10), new a(Items.LEATHER_HELMET, 5, 12, 5), new a(Items.LEATHER_BOOTS, 4, 12, 5) }Integer.valueOf(3), new IMerchantRecipeOption[] { new b(Items.RABBIT_HIDE, 9, 12, 20), new a(Items.LEATHER_CHESTPLATE, 7) }Integer.valueOf(4), new IMerchantRecipeOption[] { new b(Items.SCUTE, 4, 12, 30), new a(Items.LEATHER_HORSE_ARMOR, 6, 12, 15) }Integer.valueOf(5), new IMerchantRecipeOption[] { new h(new ItemStack(Items.SADDLE), 6, 1, 12, 30, 0.2F), new a(Items.LEATHER_HELMET, 5, 12, 30) }))); hashmap.put(VillagerProfession.MASON, a(ImmutableMap.of(Integer.valueOf(1), new IMerchantRecipeOption[] { new b(Items.CLAY_BALL, 10, 16, 2), new h(Items.BRICK, 1, 10, 16, 1) }Integer.valueOf(2), new IMerchantRecipeOption[] { new b(Blocks.STONE, 20, 16, 10), new h(Blocks.CHISELED_STONE_BRICKS, 1, 4, 16, 5) }Integer.valueOf(3), new IMerchantRecipeOption[] { new b(Blocks.GRANITE, 16, 16, 20), new b(Blocks.ANDESITE, 16, 16, 20), new b(Blocks.DIORITE, 16, 16, 20), new h(Blocks.POLISHED_ANDESITE, 1, 4, 16, 10), new h(Blocks.POLISHED_DIORITE, 1, 4, 16, 10), new h(Blocks.POLISHED_GRANITE, 1, 4, 16, 10) }Integer.valueOf(4), new IMerchantRecipeOption[] { 
/*     */                     new b(Items.QUARTZ, 12, 12, 30), new h(Blocks.ORANGE_TERRACOTTA, 1, 1, 12, 15), new h(Blocks.WHITE_TERRACOTTA, 1, 1, 12, 15), new h(Blocks.BLUE_TERRACOTTA, 1, 1, 12, 15), new h(Blocks.LIGHT_BLUE_TERRACOTTA, 1, 1, 12, 15), new h(Blocks.GRAY_TERRACOTTA, 1, 1, 12, 15), new h(Blocks.LIGHT_GRAY_TERRACOTTA, 1, 1, 12, 15), new h(Blocks.BLACK_TERRACOTTA, 1, 1, 12, 15), new h(Blocks.RED_TERRACOTTA, 1, 1, 12, 15), new h(Blocks.PINK_TERRACOTTA, 1, 1, 12, 15), 
/*     */                     new h(Blocks.MAGENTA_TERRACOTTA, 1, 1, 12, 15), new h(Blocks.LIME_TERRACOTTA, 1, 1, 12, 15), new h(Blocks.GREEN_TERRACOTTA, 1, 1, 12, 15), new h(Blocks.CYAN_TERRACOTTA, 1, 1, 12, 15), new h(Blocks.PURPLE_TERRACOTTA, 1, 1, 12, 15), new h(Blocks.YELLOW_TERRACOTTA, 1, 1, 12, 15), new h(Blocks.BROWN_TERRACOTTA, 1, 1, 12, 15), new h(Blocks.ORANGE_GLAZED_TERRACOTTA, 1, 1, 12, 15), new h(Blocks.WHITE_GLAZED_TERRACOTTA, 1, 1, 12, 15), new h(Blocks.BLUE_GLAZED_TERRACOTTA, 1, 1, 12, 15), 
/*     */                     new h(Blocks.LIGHT_BLUE_GLAZED_TERRACOTTA, 1, 1, 12, 15), new h(Blocks.GRAY_GLAZED_TERRACOTTA, 1, 1, 12, 15), new h(Blocks.LIGHT_GRAY_GLAZED_TERRACOTTA, 1, 1, 12, 15), new h(Blocks.BLACK_GLAZED_TERRACOTTA, 1, 1, 12, 15), new h(Blocks.RED_GLAZED_TERRACOTTA, 1, 1, 12, 15), new h(Blocks.PINK_GLAZED_TERRACOTTA, 1, 1, 12, 15), new h(Blocks.MAGENTA_GLAZED_TERRACOTTA, 1, 1, 12, 15), new h(Blocks.LIME_GLAZED_TERRACOTTA, 1, 1, 12, 15), new h(Blocks.GREEN_GLAZED_TERRACOTTA, 1, 1, 12, 15), new h(Blocks.CYAN_GLAZED_TERRACOTTA, 1, 1, 12, 15), 
/*  32 */                     new h(Blocks.PURPLE_GLAZED_TERRACOTTA, 1, 1, 12, 15), new h(Blocks.YELLOW_GLAZED_TERRACOTTA, 1, 1, 12, 15), new h(Blocks.BROWN_GLAZED_TERRACOTTA, 1, 1, 12, 15) }Integer.valueOf(5), new IMerchantRecipeOption[] { new h(Blocks.QUARTZ_PILLAR, 1, 1, 12, 30), new h(Blocks.QUARTZ_BLOCK, 1, 1, 12, 30) }))); }); } public static final Int2ObjectMap<IMerchantRecipeOption[]> b = a(ImmutableMap.of(Integer.valueOf(1), new IMerchantRecipeOption[] { new h(Items.aP, 2, 1, 5, 1), new h(Items.SLIME_BALL, 4, 1, 5, 1), new h(Items.dq, 2, 1, 5, 1), new h(Items.NAUTILUS_SHELL, 5, 1, 5, 1), new h(Items.aM, 1, 1, 12, 1), new h(Items.bD, 1, 1, 8, 1), new h(Items.di, 1, 1, 4, 1), new h(Items.bE, 3, 1, 12, 1), new h(Items.cX, 3, 1, 8, 1), new h(Items.bh, 1, 1, 12, 1), new h(Items.bi, 1, 1, 12, 1), new h(Items.bj, 1, 1, 8, 1), new h(Items.bk, 1, 1, 12, 1), new h(Items.bl, 1, 1, 12, 1), new h(Items.bm, 1, 1, 12, 1), new h(Items.bn, 1, 1, 12, 1), new h(Items.bo, 1, 1, 12, 1), new h(Items.bp, 1, 1, 12, 1), new h(Items.bq, 1, 1, 12, 1), new h(Items.br, 1, 1, 12, 1), new h(Items.bs, 1, 1, 7, 1), new h(Items.WHEAT_SEEDS, 1, 1, 12, 1), new h(Items.BEETROOT_SEEDS, 1, 1, 12, 1), new h(Items.PUMPKIN_SEEDS, 1, 1, 12, 1), new h(Items.MELON_SEEDS, 1, 1, 12, 1), new h(Items.B, 5, 1, 8, 1), new h(Items.z, 5, 1, 8, 1), new h(Items.C, 5, 1, 8, 1), new h(Items.A, 5, 1, 8, 1), new h(Items.x, 5, 1, 8, 1), new h(Items.y, 5, 1, 8, 1), new h(Items.RED_DYE, 1, 3, 12, 1), new h(Items.WHITE_DYE, 1, 3, 12, 1), new h(Items.BLUE_DYE, 1, 3, 12, 1), new h(Items.PINK_DYE, 1, 3, 12, 1), new h(Items.BLACK_DYE, 1, 3, 12, 1), new h(Items.GREEN_DYE, 1, 3, 12, 1), new h(Items.LIGHT_GRAY_DYE, 1, 3, 12, 1), new h(Items.MAGENTA_DYE, 1, 3, 12, 1), new h(Items.YELLOW_DYE, 1, 3, 12, 1), new h(Items.GRAY_DYE, 1, 3, 12, 1), new h(Items.PURPLE_DYE, 1, 3, 12, 1), new h(Items.LIGHT_BLUE_DYE, 1, 3, 12, 1), new h(Items.LIME_DYE, 1, 3, 12, 1), new h(Items.ORANGE_DYE, 1, 3, 12, 1), new h(Items.BROWN_DYE, 1, 3, 12, 1), new h(Items.CYAN_DYE, 1, 3, 12, 1), new h(Items.iJ, 3, 1, 8, 1), new h(Items.iK, 3, 1, 8, 1), new h(Items.iL, 3, 1, 8, 1), new h(Items.iM, 3, 1, 8, 1), new h(Items.iI, 3, 1, 8, 1), new h(Items.dR, 1, 1, 12, 1), new h(Items.bu, 1, 1, 12, 1), new h(Items.bv, 1, 1, 12, 1), new h(Items.ed, 1, 2, 5, 1), new h(Items.E, 1, 8, 8, 1), new h(Items.F, 1, 4, 6, 1) }Integer.valueOf(2), new IMerchantRecipeOption[] { new h(Items.TROPICAL_FISH_BUCKET, 5, 1, 4, 1), new h(Items.PUFFERFISH_BUCKET, 5, 1, 4, 1), new h(Items.ge, 3, 1, 6, 1), new h(Items.jh, 6, 1, 6, 1), new h(Items.GUNPOWDER, 1, 1, 8, 1), new h(Items.l, 3, 3, 6, 1) }));
/*     */   
/*     */   private static Int2ObjectMap<IMerchantRecipeOption[]> a(ImmutableMap<Integer, IMerchantRecipeOption[]> immutablemap) {
/*  35 */     return (Int2ObjectMap<IMerchantRecipeOption[]>)new Int2ObjectOpenHashMap((Map)immutablemap);
/*     */   }
/*     */   
/*     */   static class g
/*     */     implements IMerchantRecipeOption {
/*     */     private final ItemStack a;
/*     */     private final int b;
/*     */     private final int c;
/*     */     private final ItemStack d;
/*     */     private final int e;
/*     */     private final int f;
/*     */     private final int g;
/*     */     private final float h;
/*     */     
/*     */     public g(IMaterial imaterial, int i, Item item, int j, int k, int l) {
/*  50 */       this(imaterial, i, 1, item, j, k, l);
/*     */     }
/*     */     
/*     */     public g(IMaterial imaterial, int i, int j, Item item, int k, int l, int i1) {
/*  54 */       this.a = new ItemStack(imaterial);
/*  55 */       this.b = i;
/*  56 */       this.c = j;
/*  57 */       this.d = new ItemStack(item);
/*  58 */       this.e = k;
/*  59 */       this.f = l;
/*  60 */       this.g = i1;
/*  61 */       this.h = 0.05F;
/*     */     }
/*     */ 
/*     */     
/*     */     @Nullable
/*     */     public MerchantRecipe a(Entity entity, Random random) {
/*  67 */       return new MerchantRecipe(new ItemStack(Items.EMERALD, this.c), new ItemStack(this.a.getItem(), this.b), new ItemStack(this.d.getItem(), this.e), this.f, this.g, this.h);
/*     */     }
/*     */   }
/*     */   
/*     */   static class k
/*     */     implements IMerchantRecipeOption {
/*     */     private final int a;
/*     */     private final StructureGenerator<?> b;
/*     */     private final MapIcon.Type c;
/*     */     private final int d;
/*     */     private final int e;
/*     */     
/*     */     public k(int i, StructureGenerator<?> structuregenerator, MapIcon.Type mapicon_type, int j, int m) {
/*  80 */       this.a = i;
/*  81 */       this.b = structuregenerator;
/*  82 */       this.c = mapicon_type;
/*  83 */       this.d = j;
/*  84 */       this.e = m;
/*     */     }
/*     */ 
/*     */     
/*     */     @Nullable
/*     */     public MerchantRecipe a(Entity entity, Random random) {
/*  90 */       if (!(entity.world instanceof WorldServer)) {
/*  91 */         return null;
/*     */       }
/*  93 */       WorldServer worldserver = (WorldServer)entity.world;
/*  94 */       if (!worldserver.paperConfig.enableTreasureMaps) return null; 
/*  95 */       BlockPosition blockposition = worldserver.a(this.b, entity.getChunkCoordinates(), 100, !worldserver.paperConfig.treasureMapsAlreadyDiscovered);
/*     */       
/*  97 */       if (blockposition != null) {
/*  98 */         ItemStack itemstack = ItemWorldMap.createFilledMapView(worldserver, blockposition.getX(), blockposition.getZ(), (byte)2, true, true);
/*     */         
/* 100 */         ItemWorldMap.applySepiaFilter(worldserver, itemstack);
/* 101 */         WorldMap.decorateMap(itemstack, blockposition, "+", this.c);
/* 102 */         itemstack.a(new ChatMessage("filled_map." + this.b.i().toLowerCase(Locale.ROOT)));
/* 103 */         return new MerchantRecipe(new ItemStack(Items.EMERALD, this.a), new ItemStack(Items.COMPASS), itemstack, this.d, this.e, 0.2F);
/*     */       } 
/* 105 */       return null;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   static class d
/*     */     implements IMerchantRecipeOption
/*     */   {
/*     */     private final int a;
/*     */     
/*     */     public d(int i) {
/* 116 */       this.a = i;
/*     */     }
/*     */ 
/*     */     
/*     */     public MerchantRecipe a(Entity entity, Random random) {
/* 121 */       List<Enchantment> list = (List<Enchantment>)IRegistry.ENCHANTMENT.g().filter(Enchantment::h).collect(Collectors.toList());
/* 122 */       Enchantment enchantment = list.get(random.nextInt(list.size()));
/* 123 */       int i = MathHelper.nextInt(random, enchantment.getStartLevel(), enchantment.getMaxLevel());
/* 124 */       ItemStack itemstack = ItemEnchantedBook.a(new WeightedRandomEnchant(enchantment, i));
/* 125 */       int j = 2 + random.nextInt(5 + i * 10) + 3 * i;
/*     */       
/* 127 */       if (enchantment.isTreasure()) {
/* 128 */         j *= 2;
/*     */       }
/*     */       
/* 131 */       if (j > 64) {
/* 132 */         j = 64;
/*     */       }
/*     */       
/* 135 */       return new MerchantRecipe(new ItemStack(Items.EMERALD, j), new ItemStack(Items.BOOK), itemstack, 12, this.a, 0.2F);
/*     */     }
/*     */   }
/*     */   
/*     */   static class a
/*     */     implements IMerchantRecipeOption {
/*     */     private final Item a;
/*     */     private final int b;
/*     */     private final int c;
/*     */     private final int d;
/*     */     
/*     */     public a(Item item, int i) {
/* 147 */       this(item, i, 12, 1);
/*     */     }
/*     */     
/*     */     public a(Item item, int i, int j, int k) {
/* 151 */       this.a = item;
/* 152 */       this.b = i;
/* 153 */       this.c = j;
/* 154 */       this.d = k;
/*     */     }
/*     */ 
/*     */     
/*     */     public MerchantRecipe a(Entity entity, Random random) {
/* 159 */       ItemStack itemstack = new ItemStack(Items.EMERALD, this.b);
/* 160 */       ItemStack itemstack1 = new ItemStack(this.a);
/*     */       
/* 162 */       if (this.a instanceof ItemArmorColorable) {
/* 163 */         List<ItemDye> list = Lists.newArrayList();
/*     */         
/* 165 */         list.add(a(random));
/* 166 */         if (random.nextFloat() > 0.7F) {
/* 167 */           list.add(a(random));
/*     */         }
/*     */         
/* 170 */         if (random.nextFloat() > 0.8F) {
/* 171 */           list.add(a(random));
/*     */         }
/*     */         
/* 174 */         itemstack1 = IDyeable.a(itemstack1, list);
/*     */       } 
/*     */       
/* 177 */       return new MerchantRecipe(itemstack, itemstack1, this.c, this.d, 0.2F);
/*     */     }
/*     */     
/*     */     private static ItemDye a(Random random) {
/* 181 */       return ItemDye.a(EnumColor.fromColorIndex(random.nextInt(16)));
/*     */     }
/*     */   }
/*     */   
/*     */   static class j
/*     */     implements IMerchantRecipeOption {
/*     */     private final ItemStack a;
/*     */     private final int b;
/*     */     private final int c;
/*     */     private final int d;
/*     */     private final int e;
/*     */     private final Item f;
/*     */     private final int g;
/*     */     private final float h;
/*     */     
/*     */     public j(Item item, int i, Item item1, int m, int k, int l, int i1) {
/* 197 */       this.a = new ItemStack(item1);
/* 198 */       this.c = k;
/* 199 */       this.d = l;
/* 200 */       this.e = i1;
/* 201 */       this.f = item;
/* 202 */       this.g = i;
/* 203 */       this.b = m;
/* 204 */       this.h = 0.05F;
/*     */     }
/*     */ 
/*     */     
/*     */     public MerchantRecipe a(Entity entity, Random random) {
/* 209 */       ItemStack itemstack = new ItemStack(Items.EMERALD, this.c);
/*     */ 
/*     */       
/* 212 */       List<PotionRegistry> list = (List<PotionRegistry>)IRegistry.POTION.g().filter(potionregistry -> (!potionregistry.a().isEmpty() && PotionBrewer.a(potionregistry))).collect(Collectors.toList());
/* 213 */       PotionRegistry potionregistry = list.get(random.nextInt(list.size()));
/* 214 */       ItemStack itemstack1 = PotionUtil.a(new ItemStack(this.a.getItem(), this.b), potionregistry);
/*     */       
/* 216 */       return new MerchantRecipe(itemstack, new ItemStack(this.f, this.g), itemstack1, this.d, this.e, this.h);
/*     */     }
/*     */   }
/*     */   
/*     */   static class e
/*     */     implements IMerchantRecipeOption {
/*     */     private final ItemStack a;
/*     */     private final int b;
/*     */     private final int c;
/*     */     private final int d;
/*     */     private final float e;
/*     */     
/*     */     public e(Item item, int i, int j, int k) {
/* 229 */       this(item, i, j, k, 0.05F);
/*     */     }
/*     */     
/*     */     public e(Item item, int i, int j, int k, float f) {
/* 233 */       this.a = new ItemStack(item);
/* 234 */       this.b = i;
/* 235 */       this.c = j;
/* 236 */       this.d = k;
/* 237 */       this.e = f;
/*     */     }
/*     */ 
/*     */     
/*     */     public MerchantRecipe a(Entity entity, Random random) {
/* 242 */       int i = 5 + random.nextInt(15);
/* 243 */       ItemStack itemstack = EnchantmentManager.a(random, new ItemStack(this.a.getItem()), i, false);
/* 244 */       int j = Math.min(this.b + i, 64);
/* 245 */       ItemStack itemstack1 = new ItemStack(Items.EMERALD, j);
/*     */       
/* 247 */       return new MerchantRecipe(itemstack1, itemstack, this.c, this.d, this.e);
/*     */     }
/*     */   }
/*     */   
/*     */   static class i
/*     */     implements IMerchantRecipeOption {
/*     */     final MobEffectList a;
/*     */     final int b;
/*     */     final int c;
/*     */     private final float d;
/*     */     
/*     */     public i(MobEffectList mobeffectlist, int k, int j) {
/* 259 */       this.a = mobeffectlist;
/* 260 */       this.b = k;
/* 261 */       this.c = j;
/* 262 */       this.d = 0.05F;
/*     */     }
/*     */ 
/*     */     
/*     */     @Nullable
/*     */     public MerchantRecipe a(Entity entity, Random random) {
/* 268 */       ItemStack itemstack = new ItemStack(Items.SUSPICIOUS_STEW, 1);
/*     */       
/* 270 */       ItemSuspiciousStew.a(itemstack, this.a, this.b);
/* 271 */       return new MerchantRecipe(new ItemStack(Items.EMERALD, 1), itemstack, 12, this.c, this.d);
/*     */     }
/*     */   }
/*     */   
/*     */   static class h
/*     */     implements IMerchantRecipeOption {
/*     */     private final ItemStack a;
/*     */     private final int b;
/*     */     private final int c;
/*     */     private final int d;
/*     */     private final int e;
/*     */     private final float f;
/*     */     
/*     */     public h(Block block, int i, int j, int k, int l) {
/* 285 */       this(new ItemStack(block), i, j, k, l);
/*     */     }
/*     */     
/*     */     public h(Item item, int i, int j, int k) {
/* 289 */       this(new ItemStack(item), i, j, 12, k);
/*     */     }
/*     */     
/*     */     public h(Item item, int i, int j, int k, int l) {
/* 293 */       this(new ItemStack(item), i, j, k, l);
/*     */     }
/*     */     
/*     */     public h(ItemStack itemstack, int i, int j, int k, int l) {
/* 297 */       this(itemstack, i, j, k, l, 0.05F);
/*     */     }
/*     */     
/*     */     public h(ItemStack itemstack, int i, int j, int k, int l, float f) {
/* 301 */       this.a = itemstack;
/* 302 */       this.b = i;
/* 303 */       this.c = j;
/* 304 */       this.d = k;
/* 305 */       this.e = l;
/* 306 */       this.f = f;
/*     */     }
/*     */ 
/*     */     
/*     */     public MerchantRecipe a(Entity entity, Random random) {
/* 311 */       return new MerchantRecipe(new ItemStack(Items.EMERALD, this.b), new ItemStack(this.a.getItem(), this.c), this.d, this.e, this.f);
/*     */     }
/*     */   }
/*     */   
/*     */   static class c
/*     */     implements IMerchantRecipeOption {
/*     */     private final Map<VillagerType, Item> a;
/*     */     private final int b;
/*     */     private final int c;
/*     */     private final int d;
/*     */     
/*     */     public c(int i, int j, int k, Map<VillagerType, Item> map) {
/* 323 */       IRegistry.VILLAGER_TYPE.g().filter(villagertype -> !map.containsKey(villagertype))
/*     */         
/* 325 */         .findAny().ifPresent(villagertype -> {
/*     */             throw new IllegalStateException("Missing trade for villager type: " + IRegistry.VILLAGER_TYPE.getKey(villagertype));
/*     */           });
/* 328 */       this.a = map;
/* 329 */       this.b = i;
/* 330 */       this.c = j;
/* 331 */       this.d = k;
/*     */     }
/*     */ 
/*     */     
/*     */     @Nullable
/*     */     public MerchantRecipe a(Entity entity, Random random) {
/* 337 */       if (entity instanceof VillagerDataHolder) {
/* 338 */         ItemStack itemstack = new ItemStack(this.a.get(((VillagerDataHolder)entity).getVillagerData().getType()), this.b);
/*     */         
/* 340 */         return new MerchantRecipe(itemstack, new ItemStack(Items.EMERALD), this.c, this.d, 0.05F);
/*     */       } 
/* 342 */       return null;
/*     */     }
/*     */   }
/*     */   
/*     */   static class b
/*     */     implements IMerchantRecipeOption
/*     */   {
/*     */     private final Item a;
/*     */     private final int b;
/*     */     private final int c;
/*     */     private final int d;
/*     */     private final float e;
/*     */     
/*     */     public b(IMaterial imaterial, int i, int j, int k) {
/* 356 */       this.a = imaterial.getItem();
/* 357 */       this.b = i;
/* 358 */       this.c = j;
/* 359 */       this.d = k;
/* 360 */       this.e = 0.05F;
/*     */     }
/*     */ 
/*     */     
/*     */     public MerchantRecipe a(Entity entity, Random random) {
/* 365 */       ItemStack itemstack = new ItemStack(this.a, this.b);
/*     */       
/* 367 */       return new MerchantRecipe(itemstack, new ItemStack(Items.EMERALD), this.c, this.d, this.e);
/*     */     }
/*     */   }
/*     */   
/*     */   public static interface IMerchantRecipeOption {
/*     */     @Nullable
/*     */     MerchantRecipe a(Entity param1Entity, Random param1Random);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\VillagerTrades.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */