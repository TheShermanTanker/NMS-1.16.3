/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.gson.JsonElement;
/*     */ import java.util.function.BiConsumer;
/*     */ import java.util.function.Supplier;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ij
/*     */ {
/*     */   private final BiConsumer<MinecraftKey, Supplier<JsonElement>> a;
/*     */   
/*     */   public ij(BiConsumer<MinecraftKey, Supplier<JsonElement>> var0) {
/*  20 */     this.a = var0;
/*     */   }
/*     */   
/*     */   private void a(Item var0, ix var1) {
/*  24 */     var1.a(iw.a(var0), iz.b(var0), this.a);
/*     */   }
/*     */   
/*     */   private void a(Item var0, String var1, ix var2) {
/*  28 */     var2.a(iw.a(var0, var1), iz.j(iz.a(var0, var1)), this.a);
/*     */   }
/*     */ 
/*     */   
/*     */   private void a(Item var0, Item var1, ix var2) {
/*  33 */     var2.a(iw.a(var0), iz.b(var1), this.a);
/*     */   }
/*     */   
/*     */   public void a() {
/*  37 */     a(Items.ACACIA_BOAT, iy.aK);
/*  38 */     a(Items.APPLE, iy.aK);
/*  39 */     a(Items.ARMOR_STAND, iy.aK);
/*  40 */     a(Items.ARROW, iy.aK);
/*  41 */     a(Items.BAKED_POTATO, iy.aK);
/*  42 */     a(Items.bF, iy.aL);
/*  43 */     a(Items.BEEF, iy.aK);
/*  44 */     a(Items.BEETROOT, iy.aK);
/*  45 */     a(Items.BEETROOT_SOUP, iy.aK);
/*  46 */     a(Items.BIRCH_BOAT, iy.aK);
/*  47 */     a(Items.BLACK_DYE, iy.aK);
/*  48 */     a(Items.BLAZE_POWDER, iy.aK);
/*  49 */     a(Items.BLAZE_ROD, iy.aL);
/*  50 */     a(Items.BLUE_DYE, iy.aK);
/*  51 */     a(Items.BONE_MEAL, iy.aK);
/*  52 */     a(Items.BOOK, iy.aK);
/*  53 */     a(Items.BOWL, iy.aK);
/*  54 */     a(Items.BREAD, iy.aK);
/*  55 */     a(Items.BRICK, iy.aK);
/*  56 */     a(Items.BROWN_DYE, iy.aK);
/*  57 */     a(Items.BUCKET, iy.aK);
/*  58 */     a(Items.CARROT_ON_A_STICK, iy.aM);
/*  59 */     a(Items.WARPED_FUNGUS_ON_A_STICK, iy.aM);
/*  60 */     a(Items.CHAINMAIL_BOOTS, iy.aK);
/*  61 */     a(Items.CHAINMAIL_CHESTPLATE, iy.aK);
/*  62 */     a(Items.CHAINMAIL_HELMET, iy.aK);
/*  63 */     a(Items.CHAINMAIL_LEGGINGS, iy.aK);
/*  64 */     a(Items.CHARCOAL, iy.aK);
/*  65 */     a(Items.CHEST_MINECART, iy.aK);
/*  66 */     a(Items.CHICKEN, iy.aK);
/*  67 */     a(Items.CHORUS_FRUIT, iy.aK);
/*  68 */     a(Items.CLAY_BALL, iy.aK);
/*     */     int var0;
/*  70 */     for (var0 = 1; var0 < 64; var0++) {
/*  71 */       a(Items.CLOCK, String.format("_%02d", new Object[] { Integer.valueOf(var0) }), iy.aK);
/*     */     } 
/*     */     
/*  74 */     a(Items.COAL, iy.aK);
/*  75 */     a(Items.COD_BUCKET, iy.aK);
/*  76 */     a(Items.COMMAND_BLOCK_MINECART, iy.aK);
/*     */     
/*  78 */     for (var0 = 0; var0 < 32; var0++) {
/*  79 */       if (var0 != 16)
/*     */       {
/*     */         
/*  82 */         a(Items.COMPASS, String.format("_%02d", new Object[] { Integer.valueOf(var0) }), iy.aK);
/*     */       }
/*     */     } 
/*  85 */     a(Items.COOKED_BEEF, iy.aK);
/*  86 */     a(Items.COOKED_CHICKEN, iy.aK);
/*  87 */     a(Items.COOKED_COD, iy.aK);
/*  88 */     a(Items.COOKED_MUTTON, iy.aK);
/*  89 */     a(Items.COOKED_PORKCHOP, iy.aK);
/*  90 */     a(Items.COOKED_RABBIT, iy.aK);
/*  91 */     a(Items.COOKED_SALMON, iy.aK);
/*  92 */     a(Items.COOKIE, iy.aK);
/*  93 */     a(Items.CREEPER_BANNER_PATTERN, iy.aK);
/*  94 */     a(Items.CYAN_DYE, iy.aK);
/*  95 */     a(Items.DARK_OAK_BOAT, iy.aK);
/*  96 */     a(Items.DIAMOND, iy.aK);
/*  97 */     a(Items.DIAMOND_AXE, iy.aL);
/*  98 */     a(Items.DIAMOND_BOOTS, iy.aK);
/*  99 */     a(Items.DIAMOND_CHESTPLATE, iy.aK);
/* 100 */     a(Items.DIAMOND_HELMET, iy.aK);
/* 101 */     a(Items.DIAMOND_HOE, iy.aL);
/* 102 */     a(Items.DIAMOND_HORSE_ARMOR, iy.aK);
/* 103 */     a(Items.DIAMOND_LEGGINGS, iy.aK);
/* 104 */     a(Items.DIAMOND_PICKAXE, iy.aL);
/* 105 */     a(Items.DIAMOND_SHOVEL, iy.aL);
/* 106 */     a(Items.DIAMOND_SWORD, iy.aL);
/* 107 */     a(Items.DRAGON_BREATH, iy.aK);
/* 108 */     a(Items.DRIED_KELP, iy.aK);
/* 109 */     a(Items.EGG, iy.aK);
/* 110 */     a(Items.EMERALD, iy.aK);
/* 111 */     a(Items.ENCHANTED_BOOK, iy.aK);
/* 112 */     a(Items.ENDER_EYE, iy.aK);
/* 113 */     a(Items.ENDER_PEARL, iy.aK);
/* 114 */     a(Items.END_CRYSTAL, iy.aK);
/* 115 */     a(Items.EXPERIENCE_BOTTLE, iy.aK);
/* 116 */     a(Items.FERMENTED_SPIDER_EYE, iy.aK);
/* 117 */     a(Items.FIREWORK_ROCKET, iy.aK);
/* 118 */     a(Items.FIRE_CHARGE, iy.aK);
/* 119 */     a(Items.FLINT, iy.aK);
/* 120 */     a(Items.FLINT_AND_STEEL, iy.aK);
/* 121 */     a(Items.FLOWER_BANNER_PATTERN, iy.aK);
/* 122 */     a(Items.FURNACE_MINECART, iy.aK);
/* 123 */     a(Items.GHAST_TEAR, iy.aK);
/* 124 */     a(Items.GLASS_BOTTLE, iy.aK);
/* 125 */     a(Items.GLISTERING_MELON_SLICE, iy.aK);
/* 126 */     a(Items.GLOBE_BANNER_PATTERN, iy.aK);
/* 127 */     a(Items.GLOWSTONE_DUST, iy.aK);
/* 128 */     a(Items.GOLDEN_APPLE, iy.aK);
/* 129 */     a(Items.GOLDEN_AXE, iy.aL);
/* 130 */     a(Items.GOLDEN_BOOTS, iy.aK);
/* 131 */     a(Items.GOLDEN_CARROT, iy.aK);
/* 132 */     a(Items.GOLDEN_CHESTPLATE, iy.aK);
/* 133 */     a(Items.GOLDEN_HELMET, iy.aK);
/* 134 */     a(Items.GOLDEN_HOE, iy.aL);
/* 135 */     a(Items.GOLDEN_HORSE_ARMOR, iy.aK);
/* 136 */     a(Items.GOLDEN_LEGGINGS, iy.aK);
/* 137 */     a(Items.GOLDEN_PICKAXE, iy.aL);
/* 138 */     a(Items.GOLDEN_SHOVEL, iy.aL);
/* 139 */     a(Items.GOLDEN_SWORD, iy.aL);
/* 140 */     a(Items.GOLD_INGOT, iy.aK);
/* 141 */     a(Items.GOLD_NUGGET, iy.aK);
/* 142 */     a(Items.GRAY_DYE, iy.aK);
/* 143 */     a(Items.GREEN_DYE, iy.aK);
/* 144 */     a(Items.GUNPOWDER, iy.aK);
/* 145 */     a(Items.HEART_OF_THE_SEA, iy.aK);
/* 146 */     a(Items.HONEYCOMB, iy.aK);
/* 147 */     a(Items.HONEY_BOTTLE, iy.aK);
/* 148 */     a(Items.HOPPER_MINECART, iy.aK);
/* 149 */     a(Items.INK_SAC, iy.aK);
/* 150 */     a(Items.IRON_AXE, iy.aL);
/* 151 */     a(Items.IRON_BOOTS, iy.aK);
/* 152 */     a(Items.IRON_CHESTPLATE, iy.aK);
/* 153 */     a(Items.IRON_HELMET, iy.aK);
/* 154 */     a(Items.IRON_HOE, iy.aL);
/* 155 */     a(Items.IRON_HORSE_ARMOR, iy.aK);
/* 156 */     a(Items.IRON_INGOT, iy.aK);
/* 157 */     a(Items.IRON_LEGGINGS, iy.aK);
/* 158 */     a(Items.IRON_NUGGET, iy.aK);
/* 159 */     a(Items.IRON_PICKAXE, iy.aL);
/* 160 */     a(Items.IRON_SHOVEL, iy.aL);
/* 161 */     a(Items.IRON_SWORD, iy.aL);
/* 162 */     a(Items.ITEM_FRAME, iy.aK);
/* 163 */     a(Items.JUNGLE_BOAT, iy.aK);
/* 164 */     a(Items.KNOWLEDGE_BOOK, iy.aK);
/* 165 */     a(Items.LAPIS_LAZULI, iy.aK);
/* 166 */     a(Items.LAVA_BUCKET, iy.aK);
/* 167 */     a(Items.LEATHER, iy.aK);
/* 168 */     a(Items.LEATHER_HORSE_ARMOR, iy.aK);
/* 169 */     a(Items.LIGHT_BLUE_DYE, iy.aK);
/* 170 */     a(Items.LIGHT_GRAY_DYE, iy.aK);
/* 171 */     a(Items.LIME_DYE, iy.aK);
/* 172 */     a(Items.MAGENTA_DYE, iy.aK);
/* 173 */     a(Items.MAGMA_CREAM, iy.aK);
/* 174 */     a(Items.MAP, iy.aK);
/* 175 */     a(Items.MELON_SLICE, iy.aK);
/* 176 */     a(Items.MILK_BUCKET, iy.aK);
/* 177 */     a(Items.MINECART, iy.aK);
/* 178 */     a(Items.MOJANG_BANNER_PATTERN, iy.aK);
/* 179 */     a(Items.MUSHROOM_STEW, iy.aK);
/* 180 */     a(Items.MUSIC_DISC_11, iy.aK);
/* 181 */     a(Items.MUSIC_DISC_13, iy.aK);
/* 182 */     a(Items.MUSIC_DISC_BLOCKS, iy.aK);
/* 183 */     a(Items.MUSIC_DISC_CAT, iy.aK);
/* 184 */     a(Items.MUSIC_DISC_CHIRP, iy.aK);
/* 185 */     a(Items.MUSIC_DISC_FAR, iy.aK);
/* 186 */     a(Items.MUSIC_DISC_MALL, iy.aK);
/* 187 */     a(Items.MUSIC_DISC_MELLOHI, iy.aK);
/* 188 */     a(Items.MUSIC_DISC_PIGSTEP, iy.aK);
/* 189 */     a(Items.MUSIC_DISC_STAL, iy.aK);
/* 190 */     a(Items.MUSIC_DISC_STRAD, iy.aK);
/* 191 */     a(Items.MUSIC_DISC_WAIT, iy.aK);
/* 192 */     a(Items.MUSIC_DISC_WARD, iy.aK);
/* 193 */     a(Items.MUTTON, iy.aK);
/* 194 */     a(Items.NAME_TAG, iy.aK);
/* 195 */     a(Items.NAUTILUS_SHELL, iy.aK);
/* 196 */     a(Items.NETHERITE_AXE, iy.aL);
/* 197 */     a(Items.NETHERITE_BOOTS, iy.aK);
/* 198 */     a(Items.NETHERITE_CHESTPLATE, iy.aK);
/* 199 */     a(Items.NETHERITE_HELMET, iy.aK);
/* 200 */     a(Items.NETHERITE_HOE, iy.aL);
/* 201 */     a(Items.NETHERITE_INGOT, iy.aK);
/* 202 */     a(Items.NETHERITE_LEGGINGS, iy.aK);
/* 203 */     a(Items.NETHERITE_PICKAXE, iy.aL);
/* 204 */     a(Items.NETHERITE_SCRAP, iy.aK);
/* 205 */     a(Items.NETHERITE_SHOVEL, iy.aL);
/* 206 */     a(Items.NETHERITE_SWORD, iy.aL);
/* 207 */     a(Items.NETHER_BRICK, iy.aK);
/* 208 */     a(Items.NETHER_STAR, iy.aK);
/* 209 */     a(Items.OAK_BOAT, iy.aK);
/* 210 */     a(Items.ORANGE_DYE, iy.aK);
/* 211 */     a(Items.PAINTING, iy.aK);
/* 212 */     a(Items.PAPER, iy.aK);
/* 213 */     a(Items.PHANTOM_MEMBRANE, iy.aK);
/* 214 */     a(Items.PIGLIN_BANNER_PATTERN, iy.aK);
/* 215 */     a(Items.PINK_DYE, iy.aK);
/* 216 */     a(Items.POISONOUS_POTATO, iy.aK);
/* 217 */     a(Items.POPPED_CHORUS_FRUIT, iy.aK);
/* 218 */     a(Items.PORKCHOP, iy.aK);
/* 219 */     a(Items.PRISMARINE_CRYSTALS, iy.aK);
/* 220 */     a(Items.PRISMARINE_SHARD, iy.aK);
/* 221 */     a(Items.PUFFERFISH, iy.aK);
/* 222 */     a(Items.PUFFERFISH_BUCKET, iy.aK);
/* 223 */     a(Items.PUMPKIN_PIE, iy.aK);
/* 224 */     a(Items.PURPLE_DYE, iy.aK);
/* 225 */     a(Items.QUARTZ, iy.aK);
/* 226 */     a(Items.RABBIT, iy.aK);
/* 227 */     a(Items.RABBIT_FOOT, iy.aK);
/* 228 */     a(Items.RABBIT_HIDE, iy.aK);
/* 229 */     a(Items.RABBIT_STEW, iy.aK);
/* 230 */     a(Items.RED_DYE, iy.aK);
/* 231 */     a(Items.ROTTEN_FLESH, iy.aK);
/* 232 */     a(Items.SADDLE, iy.aK);
/* 233 */     a(Items.SALMON, iy.aK);
/* 234 */     a(Items.SALMON_BUCKET, iy.aK);
/* 235 */     a(Items.SCUTE, iy.aK);
/* 236 */     a(Items.SHEARS, iy.aK);
/* 237 */     a(Items.SHULKER_SHELL, iy.aK);
/* 238 */     a(Items.SKULL_BANNER_PATTERN, iy.aK);
/* 239 */     a(Items.SLIME_BALL, iy.aK);
/* 240 */     a(Items.SNOWBALL, iy.aK);
/* 241 */     a(Items.SPECTRAL_ARROW, iy.aK);
/* 242 */     a(Items.SPIDER_EYE, iy.aK);
/* 243 */     a(Items.SPRUCE_BOAT, iy.aK);
/* 244 */     a(Items.STICK, iy.aL);
/* 245 */     a(Items.STONE_AXE, iy.aL);
/* 246 */     a(Items.STONE_HOE, iy.aL);
/* 247 */     a(Items.STONE_PICKAXE, iy.aL);
/* 248 */     a(Items.STONE_SHOVEL, iy.aL);
/* 249 */     a(Items.STONE_SWORD, iy.aL);
/* 250 */     a(Items.SUGAR, iy.aK);
/* 251 */     a(Items.SUSPICIOUS_STEW, iy.aK);
/* 252 */     a(Items.TNT_MINECART, iy.aK);
/* 253 */     a(Items.TOTEM_OF_UNDYING, iy.aK);
/* 254 */     a(Items.TRIDENT, iy.aK);
/* 255 */     a(Items.TROPICAL_FISH, iy.aK);
/* 256 */     a(Items.TROPICAL_FISH_BUCKET, iy.aK);
/* 257 */     a(Items.TURTLE_HELMET, iy.aK);
/* 258 */     a(Items.WATER_BUCKET, iy.aK);
/* 259 */     a(Items.WHEAT, iy.aK);
/* 260 */     a(Items.WHITE_DYE, iy.aK);
/* 261 */     a(Items.WOODEN_AXE, iy.aL);
/* 262 */     a(Items.WOODEN_HOE, iy.aL);
/* 263 */     a(Items.WOODEN_PICKAXE, iy.aL);
/* 264 */     a(Items.WOODEN_SHOVEL, iy.aL);
/* 265 */     a(Items.WOODEN_SWORD, iy.aL);
/* 266 */     a(Items.WRITABLE_BOOK, iy.aK);
/* 267 */     a(Items.WRITTEN_BOOK, iy.aK);
/* 268 */     a(Items.YELLOW_DYE, iy.aK);
/*     */     
/* 270 */     a(Items.DEBUG_STICK, Items.STICK, iy.aL);
/* 271 */     a(Items.ENCHANTED_GOLDEN_APPLE, Items.GOLDEN_APPLE, iy.aK);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ij.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */