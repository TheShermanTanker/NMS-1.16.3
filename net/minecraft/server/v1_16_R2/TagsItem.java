/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class TagsItem
/*    */ {
/*  9 */   protected static final TagUtil<Item> a = TagStatic.a(new MinecraftKey("item"), ITagRegistry::getItemTags);
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 14 */   public static final Tag.e<Item> WOOL = a("wool");
/* 15 */   public static final Tag.e<Item> PLANKS = a("planks");
/* 16 */   public static final Tag.e<Item> STONE_BRICKS = a("stone_bricks");
/* 17 */   public static final Tag.e<Item> WOODEN_BUTTONS = a("wooden_buttons");
/* 18 */   public static final Tag.e<Item> BUTTONS = a("buttons");
/* 19 */   public static final Tag.e<Item> CARPETS = a("carpets");
/* 20 */   public static final Tag.e<Item> WOODEN_DOORS = a("wooden_doors");
/* 21 */   public static final Tag.e<Item> WOODEN_STAIRS = a("wooden_stairs");
/* 22 */   public static final Tag.e<Item> WOODEN_SLABS = a("wooden_slabs");
/* 23 */   public static final Tag.e<Item> WOODEN_FENCES = a("wooden_fences");
/* 24 */   public static final Tag.e<Item> WOODEN_PRESSURE_PLATES = a("wooden_pressure_plates");
/* 25 */   public static final Tag.e<Item> WOODEN_TRAPDOORS = a("wooden_trapdoors");
/* 26 */   public static final Tag.e<Item> DOORS = a("doors");
/* 27 */   public static final Tag.e<Item> SAPLINGS = a("saplings");
/* 28 */   public static final Tag.e<Item> LOGS_THAT_BURN = a("logs_that_burn");
/* 29 */   public static final Tag.e<Item> LOGS = a("logs");
/* 30 */   public static final Tag.e<Item> DARK_OAK_LOGS = a("dark_oak_logs");
/* 31 */   public static final Tag.e<Item> OAK_LOGS = a("oak_logs");
/* 32 */   public static final Tag.e<Item> BIRCH_LOGS = a("birch_logs");
/* 33 */   public static final Tag.e<Item> ACACIA_LOGS = a("acacia_logs");
/* 34 */   public static final Tag.e<Item> JUNGLE_LOGS = a("jungle_logs");
/* 35 */   public static final Tag.e<Item> SPRUCE_LOGS = a("spruce_logs");
/* 36 */   public static final Tag.e<Item> CRIMSON_STEMS = a("crimson_stems");
/* 37 */   public static final Tag.e<Item> WARPED_STEMS = a("warped_stems");
/* 38 */   public static final Tag.e<Item> BANNERS = a("banners");
/* 39 */   public static final Tag.e<Item> SAND = a("sand");
/* 40 */   public static final Tag.e<Item> STAIRS = a("stairs");
/* 41 */   public static final Tag.e<Item> SLABS = a("slabs");
/* 42 */   public static final Tag.e<Item> WALLS = a("walls");
/* 43 */   public static final Tag.e<Item> ANVIL = a("anvil");
/* 44 */   public static final Tag.e<Item> RAILS = a("rails");
/* 45 */   public static final Tag.e<Item> LEAVES = a("leaves");
/* 46 */   public static final Tag.e<Item> TRAPDOORS = a("trapdoors");
/* 47 */   public static final Tag.e<Item> SMALL_FLOWERS = a("small_flowers");
/* 48 */   public static final Tag.e<Item> BEDS = a("beds");
/* 49 */   public static final Tag.e<Item> FENCES = a("fences");
/* 50 */   public static final Tag.e<Item> TALL_FLOWERS = a("tall_flowers");
/* 51 */   public static final Tag.e<Item> FLOWERS = a("flowers");
/* 52 */   public static final Tag.e<Item> PIGLIN_REPELLENTS = a("piglin_repellents");
/* 53 */   public static final Tag.e<Item> PIGLIN_LOVED = a("piglin_loved");
/* 54 */   public static final Tag.e<Item> GOLD_ORES = a("gold_ores");
/* 55 */   public static final Tag.e<Item> NON_FLAMMABLE_WOOD = a("non_flammable_wood");
/* 56 */   public static final Tag.e<Item> SOUL_FIRE_BASE_BLOCKS = a("soul_fire_base_blocks");
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 61 */   public static final Tag.e<Item> BOATS = a("boats");
/* 62 */   public static final Tag.e<Item> FISHES = a("fishes");
/* 63 */   public static final Tag.e<Item> SIGNS = a("signs");
/* 64 */   public static final Tag.e<Item> MUSIC_DISCS = a("music_discs");
/* 65 */   public static final Tag.e<Item> CREEPER_DROP_MUSIC_DISCS = a("creeper_drop_music_discs");
/* 66 */   public static final Tag.e<Item> COALS = a("coals");
/* 67 */   public static final Tag.e<Item> ARROWS = a("arrows");
/* 68 */   public static final Tag.e<Item> LECTERN_BOOKS = a("lectern_books");
/* 69 */   public static final Tag.e<Item> BEACON_PAYMENT_ITEMS = a("beacon_payment_items");
/* 70 */   public static final Tag.e<Item> STONE_TOOL_MATERIALS = a("stone_tool_materials");
/* 71 */   public static final Tag.e<Item> STONE_CRAFTING_MATERIALS = a("stone_crafting_materials");
/*    */   
/*    */   private static Tag.e<Item> a(String var0) {
/* 74 */     return a.a(var0);
/*    */   }
/*    */   
/*    */   public static Tags<Item> a() {
/* 78 */     return a.b();
/*    */   }
/*    */   
/*    */   public static List<? extends Tag.e<Item>> b() {
/* 82 */     return a.c();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\TagsItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */