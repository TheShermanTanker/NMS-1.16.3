/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.nio.file.Path;
/*     */ import java.util.function.Function;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class jv
/*     */   extends jw<Item>
/*     */ {
/*     */   private final Function<Tag.e<Block>, Tag.a> d;
/*     */   
/*     */   public jv(DebugReportGenerator var0, js var1) {
/*  20 */     super(var0, IRegistry.ITEM);
/*  21 */     this.d = var1::b;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void b() {
/*  26 */     a(TagsBlock.WOOL, TagsItem.WOOL);
/*  27 */     a(TagsBlock.PLANKS, TagsItem.PLANKS);
/*  28 */     a(TagsBlock.STONE_BRICKS, TagsItem.STONE_BRICKS);
/*  29 */     a(TagsBlock.WOODEN_BUTTONS, TagsItem.WOODEN_BUTTONS);
/*  30 */     a(TagsBlock.BUTTONS, TagsItem.BUTTONS);
/*  31 */     a(TagsBlock.CARPETS, TagsItem.CARPETS);
/*  32 */     a(TagsBlock.WOODEN_DOORS, TagsItem.WOODEN_DOORS);
/*  33 */     a(TagsBlock.WOODEN_STAIRS, TagsItem.WOODEN_STAIRS);
/*  34 */     a(TagsBlock.WOODEN_SLABS, TagsItem.WOODEN_SLABS);
/*  35 */     a(TagsBlock.WOODEN_FENCES, TagsItem.WOODEN_FENCES);
/*  36 */     a(TagsBlock.WOODEN_PRESSURE_PLATES, TagsItem.WOODEN_PRESSURE_PLATES);
/*  37 */     a(TagsBlock.DOORS, TagsItem.DOORS);
/*  38 */     a(TagsBlock.SAPLINGS, TagsItem.SAPLINGS);
/*  39 */     a(TagsBlock.OAK_LOGS, TagsItem.OAK_LOGS);
/*  40 */     a(TagsBlock.DARK_OAK_LOGS, TagsItem.DARK_OAK_LOGS);
/*  41 */     a(TagsBlock.BIRCH_LOGS, TagsItem.BIRCH_LOGS);
/*  42 */     a(TagsBlock.ACACIA_LOGS, TagsItem.ACACIA_LOGS);
/*  43 */     a(TagsBlock.SPRUCE_LOGS, TagsItem.SPRUCE_LOGS);
/*  44 */     a(TagsBlock.JUNGLE_LOGS, TagsItem.JUNGLE_LOGS);
/*  45 */     a(TagsBlock.CRIMSON_STEMS, TagsItem.CRIMSON_STEMS);
/*  46 */     a(TagsBlock.WARPED_STEMS, TagsItem.WARPED_STEMS);
/*  47 */     a(TagsBlock.LOGS_THAT_BURN, TagsItem.LOGS_THAT_BURN);
/*  48 */     a(TagsBlock.LOGS, TagsItem.LOGS);
/*  49 */     a(TagsBlock.SAND, TagsItem.SAND);
/*  50 */     a(TagsBlock.SLABS, TagsItem.SLABS);
/*  51 */     a(TagsBlock.WALLS, TagsItem.WALLS);
/*  52 */     a(TagsBlock.STAIRS, TagsItem.STAIRS);
/*  53 */     a(TagsBlock.ANVIL, TagsItem.ANVIL);
/*  54 */     a(TagsBlock.RAILS, TagsItem.RAILS);
/*  55 */     a(TagsBlock.LEAVES, TagsItem.LEAVES);
/*  56 */     a(TagsBlock.WOODEN_TRAPDOORS, TagsItem.WOODEN_TRAPDOORS);
/*  57 */     a(TagsBlock.TRAPDOORS, TagsItem.TRAPDOORS);
/*  58 */     a(TagsBlock.SMALL_FLOWERS, TagsItem.SMALL_FLOWERS);
/*  59 */     a(TagsBlock.BEDS, TagsItem.BEDS);
/*  60 */     a(TagsBlock.FENCES, TagsItem.FENCES);
/*  61 */     a(TagsBlock.TALL_FLOWERS, TagsItem.TALL_FLOWERS);
/*  62 */     a(TagsBlock.FLOWERS, TagsItem.FLOWERS);
/*  63 */     a(TagsBlock.GOLD_ORES, TagsItem.GOLD_ORES);
/*  64 */     a(TagsBlock.SOUL_FIRE_BASE_BLOCKS, TagsItem.SOUL_FIRE_BASE_BLOCKS);
/*     */     
/*  66 */     a(TagsItem.BANNERS).a(new Item[] { Items.WHITE_BANNER, Items.ORANGE_BANNER, Items.MAGENTA_BANNER, Items.LIGHT_BLUE_BANNER, Items.YELLOW_BANNER, Items.LIME_BANNER, Items.PINK_BANNER, Items.GRAY_BANNER, Items.LIGHT_GRAY_BANNER, Items.CYAN_BANNER, Items.PURPLE_BANNER, Items.BLUE_BANNER, Items.BROWN_BANNER, Items.GREEN_BANNER, Items.RED_BANNER, Items.BLACK_BANNER });
/*  67 */     a(TagsItem.BOATS).a(new Item[] { Items.OAK_BOAT, Items.SPRUCE_BOAT, Items.BIRCH_BOAT, Items.JUNGLE_BOAT, Items.ACACIA_BOAT, Items.DARK_OAK_BOAT });
/*  68 */     a(TagsItem.FISHES).a(new Item[] { Items.COD, Items.COOKED_COD, Items.SALMON, Items.COOKED_SALMON, Items.PUFFERFISH, Items.TROPICAL_FISH });
/*  69 */     a(TagsBlock.STANDING_SIGNS, TagsItem.SIGNS);
/*  70 */     a(TagsItem.CREEPER_DROP_MUSIC_DISCS).a(new Item[] { Items.MUSIC_DISC_13, Items.MUSIC_DISC_CAT, Items.MUSIC_DISC_BLOCKS, Items.MUSIC_DISC_CHIRP, Items.MUSIC_DISC_FAR, Items.MUSIC_DISC_MALL, Items.MUSIC_DISC_MELLOHI, Items.MUSIC_DISC_STAL, Items.MUSIC_DISC_STRAD, Items.MUSIC_DISC_WARD, Items.MUSIC_DISC_11, Items.MUSIC_DISC_WAIT });
/*  71 */     a(TagsItem.MUSIC_DISCS).a(TagsItem.CREEPER_DROP_MUSIC_DISCS).a(Items.MUSIC_DISC_PIGSTEP);
/*  72 */     a(TagsItem.COALS).a(new Item[] { Items.COAL, Items.CHARCOAL });
/*  73 */     a(TagsItem.ARROWS).a(new Item[] { Items.ARROW, Items.TIPPED_ARROW, Items.SPECTRAL_ARROW });
/*  74 */     a(TagsItem.LECTERN_BOOKS).a(new Item[] { Items.WRITTEN_BOOK, Items.WRITABLE_BOOK });
/*  75 */     a(TagsItem.BEACON_PAYMENT_ITEMS).a(new Item[] { Items.NETHERITE_INGOT, Items.EMERALD, Items.DIAMOND, Items.GOLD_INGOT, Items.IRON_INGOT });
/*  76 */     a(TagsItem.PIGLIN_REPELLENTS).a(Items.dp).a(Items.rl).a(Items.ro);
/*  77 */     a(TagsItem.PIGLIN_LOVED).a(TagsItem.GOLD_ORES).a(new Item[] { Items.bG, Items.rE, Items.fg, Items.GOLD_INGOT, Items.rj, Items.CLOCK, Items.GOLDEN_CARROT, Items.GLISTERING_MELON_SLICE, Items.GOLDEN_APPLE, Items.ENCHANTED_GOLDEN_APPLE, Items.GOLDEN_HELMET, Items.GOLDEN_CHESTPLATE, Items.GOLDEN_LEGGINGS, Items.GOLDEN_BOOTS, Items.GOLDEN_HORSE_ARMOR, Items.GOLDEN_SWORD, Items.GOLDEN_PICKAXE, Items.GOLDEN_SHOVEL, Items.GOLDEN_AXE, Items.GOLDEN_HOE });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  83 */     a(TagsItem.NON_FLAMMABLE_WOOD).a(new Item[] { Items.S, Items.aa, Items.aq, Items.ai, Items.R, Items.Z, Items.ap, Items.ah, Items.v, Items.w, Items.bO, Items.bP, Items.cP, Items.cQ, Items.dg, Items.dh, Items.dy, Items.dz, Items.dY, Items.dZ, Items.ex, Items.ey, Items.eZ, Items.fa, Items.jS, Items.jT, Items.CRIMSON_SIGN, Items.WARPED_SIGN });
/*  84 */     a(TagsItem.STONE_TOOL_MATERIALS).a(new Item[] { Items.o, Items.rB });
/*  85 */     a(TagsItem.STONE_CRAFTING_MATERIALS).a(new Item[] { Items.o, Items.rB });
/*     */   }
/*     */   
/*     */   protected void a(Tag.e<Block> var0, Tag.e<Item> var1) {
/*  89 */     Tag.a var2 = b(var1);
/*  90 */     Tag.a var3 = this.d.apply(var0);
/*  91 */     var3.b().forEach(var2::a);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Path a(MinecraftKey var0) {
/*  96 */     return this.b.b().resolve("data/" + var0.getNamespace() + "/tags/items/" + var0.getKey() + ".json");
/*     */   }
/*     */ 
/*     */   
/*     */   public String a() {
/* 101 */     return "Item Tags";
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\jv.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */