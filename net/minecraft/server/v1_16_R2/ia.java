/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.function.BiConsumer;
/*     */ import java.util.function.Consumer;
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
/*     */ public class ia
/*     */   implements Consumer<BiConsumer<MinecraftKey, LootTable.a>>
/*     */ {
/*     */   public void accept(BiConsumer<MinecraftKey, LootTable.a> var0) {
/*  33 */     var0.accept(LootTables.u, 
/*  34 */         LootTable.b()
/*  35 */         .a(LootSelector.a()
/*  36 */           .a(LootValueConstant.a(1))
/*  37 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.GOLDEN_APPLE).a(20))
/*  38 */           .a(LootItem.a(Items.ENCHANTED_GOLDEN_APPLE))
/*  39 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.NAME_TAG).a(30))
/*  40 */           .a(LootItem.a(Items.BOOK).a(10).b(LootItemFunctionEnchant.d()))
/*  41 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.IRON_PICKAXE).a(5))
/*  42 */           .a((LootEntryAbstract.a<?>)LootSelectorEmpty.b().a(5)))
/*     */         
/*  44 */         .a(LootSelector.a()
/*  45 */           .a(LootValueBounds.a(2.0F, 4.0F))
/*  46 */           .a(LootItem.a(Items.IRON_INGOT).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 5.0F))))
/*  47 */           .a(LootItem.a(Items.GOLD_INGOT).a(5).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/*  48 */           .a(LootItem.a(Items.REDSTONE).a(5).b(LootItemFunctionSetCount.a(LootValueBounds.a(4.0F, 9.0F))))
/*  49 */           .a(LootItem.a(Items.LAPIS_LAZULI).a(5).b(LootItemFunctionSetCount.a(LootValueBounds.a(4.0F, 9.0F))))
/*  50 */           .a(LootItem.a(Items.DIAMOND).a(3).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 2.0F))))
/*  51 */           .a(LootItem.a(Items.COAL).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(3.0F, 8.0F))))
/*  52 */           .a(LootItem.a(Items.BREAD).a(15).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/*  53 */           .a(LootItem.a(Items.MELON_SEEDS).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(2.0F, 4.0F))))
/*  54 */           .a(LootItem.a(Items.PUMPKIN_SEEDS).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(2.0F, 4.0F))))
/*  55 */           .a(LootItem.a(Items.BEETROOT_SEEDS).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(2.0F, 4.0F)))))
/*     */         
/*  57 */         .a(LootSelector.a()
/*  58 */           .a(LootValueConstant.a(3))
/*  59 */           .a(LootItem.a(Blocks.RAIL).a(20).b(LootItemFunctionSetCount.a(LootValueBounds.a(4.0F, 8.0F))))
/*  60 */           .a(LootItem.a(Blocks.POWERED_RAIL).a(5).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 4.0F))))
/*  61 */           .a(LootItem.a(Blocks.DETECTOR_RAIL).a(5).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 4.0F))))
/*  62 */           .a(LootItem.a(Blocks.ACTIVATOR_RAIL).a(5).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 4.0F))))
/*  63 */           .a(LootItem.a(Blocks.TORCH).a(15).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 16.0F))))));
/*     */ 
/*     */ 
/*     */     
/*  67 */     var0.accept(LootTables.N, 
/*  68 */         LootTable.b()
/*  69 */         .a(LootSelector.a()
/*  70 */           .a(LootValueConstant.a(1))
/*  71 */           .a((LootEntryAbstract.a<?>)LootItem.a(Blocks.LODESTONE).b(LootItemFunctionSetCount.a(LootValueConstant.a(1)))))
/*     */         
/*  73 */         .a(LootSelector.a()
/*  74 */           .a(LootValueBounds.a(1.0F, 2.0F))
/*  75 */           .a(LootItem.a(Items.CROSSBOW).b(LootItemFunctionSetDamage.a(LootValueBounds.a(0.1F, 0.5F))).b(LootItemFunctionEnchant.d()))
/*  76 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.SPECTRAL_ARROW).b(LootItemFunctionSetCount.a(LootValueBounds.a(10.0F, 28.0F))))
/*  77 */           .a((LootEntryAbstract.a<?>)LootItem.a(Blocks.GILDED_BLACKSTONE).b(LootItemFunctionSetCount.a(LootValueBounds.a(8.0F, 12.0F))))
/*  78 */           .a((LootEntryAbstract.a<?>)LootItem.a(Blocks.CRYING_OBSIDIAN).b(LootItemFunctionSetCount.a(LootValueBounds.a(3.0F, 8.0F))))
/*  79 */           .a((LootEntryAbstract.a<?>)LootItem.a(Blocks.GOLD_BLOCK).b(LootItemFunctionSetCount.a(LootValueConstant.a(1))))
/*  80 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.GOLD_INGOT).b(LootItemFunctionSetCount.a(LootValueBounds.a(4.0F, 9.0F))))
/*  81 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.IRON_INGOT).b(LootItemFunctionSetCount.a(LootValueBounds.a(4.0F, 9.0F))))
/*  82 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.GOLDEN_SWORD).b(LootItemFunctionSetCount.a(LootValueConstant.a(1))))
/*  83 */           .a(LootItem.a(Items.GOLDEN_CHESTPLATE).b(LootItemFunctionSetCount.a(LootValueConstant.a(1))).b(LootItemFunctionEnchant.d()))
/*  84 */           .a(LootItem.a(Items.GOLDEN_HELMET).b(LootItemFunctionSetCount.a(LootValueConstant.a(1))).b(LootItemFunctionEnchant.d()))
/*  85 */           .a(LootItem.a(Items.GOLDEN_LEGGINGS).b(LootItemFunctionSetCount.a(LootValueConstant.a(1))).b(LootItemFunctionEnchant.d()))
/*  86 */           .a(LootItem.a(Items.GOLDEN_BOOTS).b(LootItemFunctionSetCount.a(LootValueConstant.a(1))).b(LootItemFunctionEnchant.d()))
/*  87 */           .a(LootItem.a(Items.GOLDEN_AXE).b(LootItemFunctionSetCount.a(LootValueConstant.a(1))).b(LootItemFunctionEnchant.d())))
/*     */         
/*  89 */         .a(LootSelector.a()
/*  90 */           .a(LootValueBounds.a(2.0F, 4.0F))
/*  91 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.STRING).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 6.0F))))
/*  92 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.LEATHER).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/*  93 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.ARROW).b(LootItemFunctionSetCount.a(LootValueBounds.a(5.0F, 17.0F))))
/*  94 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.IRON_NUGGET).b(LootItemFunctionSetCount.a(LootValueBounds.a(2.0F, 6.0F))))
/*  95 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.GOLD_NUGGET).b(LootItemFunctionSetCount.a(LootValueBounds.a(2.0F, 6.0F))))));
/*     */ 
/*     */ 
/*     */     
/*  99 */     var0.accept(LootTables.O, 
/* 100 */         LootTable.b()
/* 101 */         .a(LootSelector.a()
/* 102 */           .a(LootValueConstant.a(1))
/* 103 */           .a(LootItem.a(Items.DIAMOND_SHOVEL).a(15).b(LootItemFunctionSetDamage.a(LootValueBounds.a(0.15F, 0.8F))).b(LootItemFunctionEnchant.d()))
/* 104 */           .a(LootItem.a(Items.DIAMOND_PICKAXE).a(12).b(LootItemFunctionSetDamage.a(LootValueBounds.a(0.15F, 0.95F))).b(LootItemFunctionSetCount.a(LootValueConstant.a(1))).b(LootItemFunctionEnchant.d()))
/* 105 */           .a(LootItem.a(Items.NETHERITE_SCRAP).a(8).b(LootItemFunctionSetCount.a(LootValueConstant.a(1))))
/* 106 */           .a(LootItem.a(Items.ry).a(12).b(LootItemFunctionSetCount.a(LootValueConstant.a(1))))
/* 107 */           .a(LootItem.a(Items.ry).a(5).b(LootItemFunctionSetCount.a(LootValueConstant.a(2))))
/* 108 */           .a(LootItem.a(Items.SADDLE).a(12).b(LootItemFunctionSetCount.a(LootValueConstant.a(1))))
/* 109 */           .a(LootItem.a(Blocks.GOLD_BLOCK).a(16).b(LootItemFunctionSetCount.a(LootValueBounds.a(2.0F, 4.0F))))
/* 110 */           .a(LootItem.a(Items.GOLDEN_CARROT).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(8.0F, 17.0F))))
/* 111 */           .a(LootItem.a(Items.GOLDEN_APPLE).a(10).b(LootItemFunctionSetCount.a(LootValueConstant.a(1)))))
/*     */         
/* 113 */         .a(LootSelector.a()
/* 114 */           .a(LootValueBounds.a(3.0F, 4.0F))
/* 115 */           .a(LootItem.a(Items.GOLDEN_AXE).b(LootItemFunctionSetCount.a(LootValueConstant.a(1))).b(LootItemFunctionEnchant.d()))
/* 116 */           .a((LootEntryAbstract.a<?>)LootItem.a(Blocks.CRYING_OBSIDIAN).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 5.0F))))
/* 117 */           .a((LootEntryAbstract.a<?>)LootItem.a(Blocks.GLOWSTONE).b(LootItemFunctionSetCount.a(LootValueBounds.a(3.0F, 6.0F))))
/* 118 */           .a((LootEntryAbstract.a<?>)LootItem.a(Blocks.GILDED_BLACKSTONE).b(LootItemFunctionSetCount.a(LootValueBounds.a(2.0F, 5.0F))))
/* 119 */           .a((LootEntryAbstract.a<?>)LootItem.a(Blocks.SOUL_SAND).b(LootItemFunctionSetCount.a(LootValueBounds.a(2.0F, 7.0F))))
/* 120 */           .a((LootEntryAbstract.a<?>)LootItem.a(Blocks.CRIMSON_NYLIUM).b(LootItemFunctionSetCount.a(LootValueBounds.a(2.0F, 7.0F))))
/* 121 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.GOLD_NUGGET).b(LootItemFunctionSetCount.a(LootValueBounds.a(2.0F, 8.0F))))
/* 122 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.LEATHER).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/* 123 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.ARROW).b(LootItemFunctionSetCount.a(LootValueBounds.a(5.0F, 17.0F))))
/* 124 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.STRING).b(LootItemFunctionSetCount.a(LootValueBounds.a(3.0F, 8.0F))))
/* 125 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.PORKCHOP).b(LootItemFunctionSetCount.a(LootValueBounds.a(2.0F, 5.0F))))
/* 126 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.COOKED_PORKCHOP).b(LootItemFunctionSetCount.a(LootValueBounds.a(2.0F, 5.0F))))
/* 127 */           .a((LootEntryAbstract.a<?>)LootItem.a(Blocks.CRIMSON_FUNGUS).b(LootItemFunctionSetCount.a(LootValueBounds.a(2.0F, 7.0F))))
/* 128 */           .a((LootEntryAbstract.a<?>)LootItem.a(Blocks.CRIMSON_ROOTS).b(LootItemFunctionSetCount.a(LootValueBounds.a(2.0F, 7.0F))))));
/*     */ 
/*     */ 
/*     */     
/* 132 */     var0.accept(LootTables.M, 
/* 133 */         LootTable.b()
/* 134 */         .a(LootSelector.a()
/* 135 */           .a(LootValueConstant.a(1))
/* 136 */           .a(LootItem.a(Items.DIAMOND_PICKAXE).a(6).b(LootItemFunctionSetCount.a(LootValueConstant.a(1))).b(LootItemFunctionEnchant.d()))
/* 137 */           .a(LootItem.a(Items.DIAMOND_SHOVEL).a(6).b(LootItemFunctionSetCount.a(LootValueConstant.a(1))))
/* 138 */           .a(LootItem.a(Items.CROSSBOW).a(6).b(LootItemFunctionSetDamage.a(LootValueBounds.a(0.1F, 0.9F))).b(LootItemFunctionEnchant.d()))
/* 139 */           .a(LootItem.a(Items.ry).a(12).b(LootItemFunctionSetCount.a(LootValueConstant.a(1))))
/* 140 */           .a(LootItem.a(Items.NETHERITE_SCRAP).a(4).b(LootItemFunctionSetCount.a(LootValueConstant.a(1))))
/* 141 */           .a(LootItem.a(Items.SPECTRAL_ARROW).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(10.0F, 22.0F))))
/* 142 */           .a(LootItem.a(Items.PIGLIN_BANNER_PATTERN).a(9).b(LootItemFunctionSetCount.a(LootValueConstant.a(1))))
/* 143 */           .a(LootItem.a(Items.MUSIC_DISC_PIGSTEP).a(5).b(LootItemFunctionSetCount.a(LootValueConstant.a(1))))
/* 144 */           .a(LootItem.a(Items.GOLDEN_CARROT).a(12).b(LootItemFunctionSetCount.a(LootValueBounds.a(6.0F, 17.0F))))
/* 145 */           .a(LootItem.a(Items.GOLDEN_APPLE).a(9).b(LootItemFunctionSetCount.a(LootValueConstant.a(1))))
/* 146 */           .a(LootItem.a(Items.BOOK).a(10).b((new LootItemFunctionEnchant.a()).a(Enchantments.SOUL_SPEED))))
/*     */         
/* 148 */         .a(LootSelector.a()
/* 149 */           .a(LootValueConstant.a(2))
/* 150 */           .a(LootItem.a(Items.IRON_SWORD).a(2).b(LootItemFunctionSetDamage.a(LootValueBounds.a(0.1F, 0.9F))).b(LootItemFunctionSetCount.a(LootValueConstant.a(1))).b(LootItemFunctionEnchant.d()))
/* 151 */           .a(LootItem.a(Blocks.IRON_BLOCK).a(2).b(LootItemFunctionSetCount.a(LootValueConstant.a(1))))
/* 152 */           .a(LootItem.a(Items.GOLDEN_BOOTS).b(LootItemFunctionSetCount.a(LootValueConstant.a(1))).b((new LootItemFunctionEnchant.a()).a(Enchantments.SOUL_SPEED)))
/* 153 */           .a(LootItem.a(Items.GOLDEN_AXE).b(LootItemFunctionSetCount.a(LootValueConstant.a(1))).b(LootItemFunctionEnchant.d()))
/* 154 */           .a(LootItem.a(Blocks.GOLD_BLOCK).a(2).b(LootItemFunctionSetCount.a(LootValueConstant.a(1))))
/* 155 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.CROSSBOW).b(LootItemFunctionSetCount.a(LootValueConstant.a(1))))
/* 156 */           .a(LootItem.a(Items.GOLD_INGOT).a(2).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 6.0F))))
/* 157 */           .a(LootItem.a(Items.IRON_INGOT).a(2).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 6.0F))))
/* 158 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.GOLDEN_SWORD).b(LootItemFunctionSetCount.a(LootValueConstant.a(1))))
/* 159 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.GOLDEN_CHESTPLATE).b(LootItemFunctionSetCount.a(LootValueConstant.a(1))))
/* 160 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.GOLDEN_HELMET).b(LootItemFunctionSetCount.a(LootValueConstant.a(1))))
/* 161 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.GOLDEN_LEGGINGS).b(LootItemFunctionSetCount.a(LootValueConstant.a(1))))
/* 162 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.GOLDEN_BOOTS).b(LootItemFunctionSetCount.a(LootValueConstant.a(1))))
/* 163 */           .a(LootItem.a(Blocks.CRYING_OBSIDIAN).a(2).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 5.0F)))))
/*     */         
/* 165 */         .a(LootSelector.a()
/* 166 */           .a(LootValueBounds.a(3.0F, 4.0F))
/* 167 */           .a(LootItem.a(Blocks.GILDED_BLACKSTONE).a(2).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 5.0F))))
/* 168 */           .a((LootEntryAbstract.a<?>)LootItem.a(Blocks.CHAIN).b(LootItemFunctionSetCount.a(LootValueBounds.a(2.0F, 10.0F))))
/* 169 */           .a(LootItem.a(Items.MAGMA_CREAM).a(2).b(LootItemFunctionSetCount.a(LootValueBounds.a(2.0F, 6.0F))))
/* 170 */           .a((LootEntryAbstract.a<?>)LootItem.a(Blocks.BONE_BLOCK).b(LootItemFunctionSetCount.a(LootValueBounds.a(3.0F, 6.0F))))
/* 171 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.IRON_NUGGET).b(LootItemFunctionSetCount.a(LootValueBounds.a(2.0F, 8.0F))))
/* 172 */           .a((LootEntryAbstract.a<?>)LootItem.a(Blocks.OBSIDIAN).b(LootItemFunctionSetCount.a(LootValueBounds.a(4.0F, 6.0F))))
/* 173 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.GOLD_NUGGET).b(LootItemFunctionSetCount.a(LootValueBounds.a(2.0F, 8.0F))))
/* 174 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.STRING).b(LootItemFunctionSetCount.a(LootValueBounds.a(4.0F, 6.0F))))
/* 175 */           .a(LootItem.a(Items.ARROW).a(2).b(LootItemFunctionSetCount.a(LootValueBounds.a(5.0F, 17.0F))))
/* 176 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.COOKED_PORKCHOP).b(LootItemFunctionSetCount.a(LootValueConstant.a(1))))));
/*     */ 
/*     */ 
/*     */     
/* 180 */     var0.accept(LootTables.L, 
/* 181 */         LootTable.b()
/* 182 */         .a(LootSelector.a()
/* 183 */           .a(LootValueConstant.a(3))
/* 184 */           .a(LootItem.a(Items.NETHERITE_INGOT).a(15).b(LootItemFunctionSetCount.a(LootValueConstant.a(1))))
/* 185 */           .a(LootItem.a(Blocks.ANCIENT_DEBRIS).a(10).b(LootItemFunctionSetCount.a(LootValueConstant.a(1))))
/* 186 */           .a(LootItem.a(Items.NETHERITE_SCRAP).a(8).b(LootItemFunctionSetCount.a(LootValueConstant.a(1))))
/* 187 */           .a(LootItem.a(Blocks.ANCIENT_DEBRIS).a(4).b(LootItemFunctionSetCount.a(LootValueConstant.a(2))))
/* 188 */           .a(LootItem.a(Items.DIAMOND_SWORD).a(6).b(LootItemFunctionSetDamage.a(LootValueBounds.a(0.8F, 1.0F))).b(LootItemFunctionEnchant.d()))
/* 189 */           .a(LootItem.a(Items.DIAMOND_CHESTPLATE).a(6).b(LootItemFunctionSetDamage.a(LootValueBounds.a(0.8F, 1.0F))).b(LootItemFunctionEnchant.d()))
/* 190 */           .a(LootItem.a(Items.DIAMOND_HELMET).a(6).b(LootItemFunctionSetDamage.a(LootValueBounds.a(0.8F, 1.0F))).b(LootItemFunctionEnchant.d()))
/* 191 */           .a(LootItem.a(Items.DIAMOND_LEGGINGS).a(6).b(LootItemFunctionSetDamage.a(LootValueBounds.a(0.8F, 1.0F))).b(LootItemFunctionEnchant.d()))
/* 192 */           .a(LootItem.a(Items.DIAMOND_BOOTS).a(6).b(LootItemFunctionSetDamage.a(LootValueBounds.a(0.8F, 1.0F))).b(LootItemFunctionEnchant.d()))
/* 193 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.DIAMOND_SWORD).a(6))
/* 194 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.DIAMOND_CHESTPLATE).a(5))
/* 195 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.DIAMOND_HELMET).a(5))
/* 196 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.DIAMOND_BOOTS).a(5))
/* 197 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.DIAMOND_LEGGINGS).a(5))
/* 198 */           .a(LootItem.a(Items.DIAMOND).a(5).b(LootItemFunctionSetCount.a(LootValueBounds.a(2.0F, 6.0F))))
/* 199 */           .a(LootItem.a(Items.ENCHANTED_GOLDEN_APPLE).a(2).b(LootItemFunctionSetCount.a(LootValueConstant.a(1)))))
/*     */         
/* 201 */         .a(LootSelector.a()
/* 202 */           .a(LootValueBounds.a(3.0F, 4.0F))
/* 203 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.SPECTRAL_ARROW).b(LootItemFunctionSetCount.a(LootValueBounds.a(12.0F, 25.0F))))
/* 204 */           .a((LootEntryAbstract.a<?>)LootItem.a(Blocks.GOLD_BLOCK).b(LootItemFunctionSetCount.a(LootValueBounds.a(2.0F, 5.0F))))
/* 205 */           .a((LootEntryAbstract.a<?>)LootItem.a(Blocks.IRON_BLOCK).b(LootItemFunctionSetCount.a(LootValueBounds.a(2.0F, 5.0F))))
/* 206 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.GOLD_INGOT).b(LootItemFunctionSetCount.a(LootValueBounds.a(3.0F, 9.0F))))
/* 207 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.IRON_INGOT).b(LootItemFunctionSetCount.a(LootValueBounds.a(3.0F, 9.0F))))
/* 208 */           .a((LootEntryAbstract.a<?>)LootItem.a(Blocks.CRYING_OBSIDIAN).b(LootItemFunctionSetCount.a(LootValueBounds.a(3.0F, 5.0F))))
/* 209 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.QUARTZ).b(LootItemFunctionSetCount.a(LootValueBounds.a(8.0F, 23.0F))))
/* 210 */           .a((LootEntryAbstract.a<?>)LootItem.a(Blocks.GILDED_BLACKSTONE).b(LootItemFunctionSetCount.a(LootValueBounds.a(5.0F, 15.0F))))
/* 211 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.MAGMA_CREAM).b(LootItemFunctionSetCount.a(LootValueBounds.a(3.0F, 8.0F))))));
/*     */ 
/*     */ 
/*     */     
/* 215 */     var0.accept(LootTables.G, 
/* 216 */         LootTable.b()
/* 217 */         .a(LootSelector.a()
/* 218 */           .a(LootValueConstant.a(1))
/* 219 */           .a(LootItem.a(Items.HEART_OF_THE_SEA)))
/*     */         
/* 221 */         .a(LootSelector.a()
/* 222 */           .a(LootValueBounds.a(5.0F, 8.0F))
/* 223 */           .a(LootItem.a(Items.IRON_INGOT).a(20).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 4.0F))))
/* 224 */           .a(LootItem.a(Items.GOLD_INGOT).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 4.0F))))
/* 225 */           .a(LootItem.a(Blocks.TNT).a(5).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 2.0F)))))
/*     */         
/* 227 */         .a(LootSelector.a()
/* 228 */           .a(LootValueBounds.a(1.0F, 3.0F))
/* 229 */           .a(LootItem.a(Items.EMERALD).a(5).b(LootItemFunctionSetCount.a(LootValueBounds.a(4.0F, 8.0F))))
/* 230 */           .a(LootItem.a(Items.DIAMOND).a(5).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 2.0F))))
/* 231 */           .a(LootItem.a(Items.PRISMARINE_CRYSTALS).a(5).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 5.0F)))))
/*     */         
/* 233 */         .a(LootSelector.a()
/* 234 */           .a(LootValueBounds.a(0.0F, 1.0F))
/* 235 */           .a(LootItem.a(Items.LEATHER_CHESTPLATE))
/* 236 */           .a(LootItem.a(Items.IRON_SWORD)))
/*     */         
/* 238 */         .a(LootSelector.a()
/* 239 */           .a(LootValueConstant.a(2))
/* 240 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.COOKED_COD).b(LootItemFunctionSetCount.a(LootValueBounds.a(2.0F, 4.0F))))
/* 241 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.COOKED_SALMON).b(LootItemFunctionSetCount.a(LootValueBounds.a(2.0F, 4.0F))))));
/*     */ 
/*     */ 
/*     */     
/* 245 */     var0.accept(LootTables.z, 
/* 246 */         LootTable.b()
/* 247 */         .a(LootSelector.a()
/* 248 */           .a(LootValueBounds.a(2.0F, 4.0F))
/* 249 */           .a(LootItem.a(Items.DIAMOND).a(5).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/* 250 */           .a(LootItem.a(Items.IRON_INGOT).a(15).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 5.0F))))
/* 251 */           .a(LootItem.a(Items.GOLD_INGOT).a(15).b(LootItemFunctionSetCount.a(LootValueBounds.a(2.0F, 7.0F))))
/* 252 */           .a(LootItem.a(Items.EMERALD).a(15).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/* 253 */           .a(LootItem.a(Items.BONE).a(25).b(LootItemFunctionSetCount.a(LootValueBounds.a(4.0F, 6.0F))))
/* 254 */           .a(LootItem.a(Items.SPIDER_EYE).a(25).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/* 255 */           .a(LootItem.a(Items.ROTTEN_FLESH).a(25).b(LootItemFunctionSetCount.a(LootValueBounds.a(3.0F, 7.0F))))
/* 256 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.SADDLE).a(20))
/* 257 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.IRON_HORSE_ARMOR).a(15))
/* 258 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.GOLDEN_HORSE_ARMOR).a(10))
/* 259 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.DIAMOND_HORSE_ARMOR).a(5))
/* 260 */           .a(LootItem.a(Items.BOOK).a(20).b(LootItemFunctionEnchant.d()))
/* 261 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.GOLDEN_APPLE).a(20))
/* 262 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.ENCHANTED_GOLDEN_APPLE).a(2))
/* 263 */           .a((LootEntryAbstract.a<?>)LootSelectorEmpty.b().a(15)))
/*     */         
/* 265 */         .a(LootSelector.a()
/* 266 */           .a(LootValueConstant.a(4))
/* 267 */           .a(LootItem.a(Items.BONE).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 8.0F))))
/* 268 */           .a(LootItem.a(Items.GUNPOWDER).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 8.0F))))
/* 269 */           .a(LootItem.a(Items.ROTTEN_FLESH).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 8.0F))))
/* 270 */           .a(LootItem.a(Items.STRING).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 8.0F))))
/* 271 */           .a(LootItem.a(Blocks.SAND).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 8.0F))))));
/*     */ 
/*     */ 
/*     */     
/* 275 */     var0.accept(LootTables.c, 
/* 276 */         LootTable.b()
/* 277 */         .a(LootSelector.a()
/* 278 */           .a(LootValueBounds.a(2.0F, 6.0F))
/* 279 */           .a(LootItem.a(Items.DIAMOND).a(5).b(LootItemFunctionSetCount.a(LootValueBounds.a(2.0F, 7.0F))))
/* 280 */           .a(LootItem.a(Items.IRON_INGOT).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(4.0F, 8.0F))))
/* 281 */           .a(LootItem.a(Items.GOLD_INGOT).a(15).b(LootItemFunctionSetCount.a(LootValueBounds.a(2.0F, 7.0F))))
/* 282 */           .a(LootItem.a(Items.EMERALD).a(2).b(LootItemFunctionSetCount.a(LootValueBounds.a(2.0F, 6.0F))))
/* 283 */           .a(LootItem.a(Items.BEETROOT_SEEDS).a(5).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 10.0F))))
/* 284 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.SADDLE).a(3))
/* 285 */           .a(LootItem.a(Items.IRON_HORSE_ARMOR))
/* 286 */           .a(LootItem.a(Items.GOLDEN_HORSE_ARMOR))
/* 287 */           .a(LootItem.a(Items.DIAMOND_HORSE_ARMOR))
/* 288 */           .a(LootItem.a(Items.DIAMOND_SWORD).a(3).b(LootEnchantLevel.a(LootValueBounds.a(20.0F, 39.0F)).e()))
/* 289 */           .a(LootItem.a(Items.DIAMOND_BOOTS).a(3).b(LootEnchantLevel.a(LootValueBounds.a(20.0F, 39.0F)).e()))
/* 290 */           .a(LootItem.a(Items.DIAMOND_CHESTPLATE).a(3).b(LootEnchantLevel.a(LootValueBounds.a(20.0F, 39.0F)).e()))
/* 291 */           .a(LootItem.a(Items.DIAMOND_LEGGINGS).a(3).b(LootEnchantLevel.a(LootValueBounds.a(20.0F, 39.0F)).e()))
/* 292 */           .a(LootItem.a(Items.DIAMOND_HELMET).a(3).b(LootEnchantLevel.a(LootValueBounds.a(20.0F, 39.0F)).e()))
/* 293 */           .a(LootItem.a(Items.DIAMOND_PICKAXE).a(3).b(LootEnchantLevel.a(LootValueBounds.a(20.0F, 39.0F)).e()))
/* 294 */           .a(LootItem.a(Items.DIAMOND_SHOVEL).a(3).b(LootEnchantLevel.a(LootValueBounds.a(20.0F, 39.0F)).e()))
/* 295 */           .a(LootItem.a(Items.IRON_SWORD).a(3).b(LootEnchantLevel.a(LootValueBounds.a(20.0F, 39.0F)).e()))
/* 296 */           .a(LootItem.a(Items.IRON_BOOTS).a(3).b(LootEnchantLevel.a(LootValueBounds.a(20.0F, 39.0F)).e()))
/* 297 */           .a(LootItem.a(Items.IRON_CHESTPLATE).a(3).b(LootEnchantLevel.a(LootValueBounds.a(20.0F, 39.0F)).e()))
/* 298 */           .a(LootItem.a(Items.IRON_LEGGINGS).a(3).b(LootEnchantLevel.a(LootValueBounds.a(20.0F, 39.0F)).e()))
/* 299 */           .a(LootItem.a(Items.IRON_HELMET).a(3).b(LootEnchantLevel.a(LootValueBounds.a(20.0F, 39.0F)).e()))
/* 300 */           .a(LootItem.a(Items.IRON_PICKAXE).a(3).b(LootEnchantLevel.a(LootValueBounds.a(20.0F, 39.0F)).e()))
/* 301 */           .a(LootItem.a(Items.IRON_SHOVEL).a(3).b(LootEnchantLevel.a(LootValueBounds.a(20.0F, 39.0F)).e()))));
/*     */ 
/*     */ 
/*     */     
/* 305 */     var0.accept(LootTables.C, 
/* 306 */         LootTable.b()
/* 307 */         .a(LootSelector.a()
/* 308 */           .a(LootValueBounds.a(2.0F, 8.0F))
/* 309 */           .a(LootItem.a(Items.APPLE).a(15).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/* 310 */           .a(LootItem.a(Items.COAL).a(15).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 4.0F))))
/* 311 */           .a(LootItem.a(Items.GOLD_NUGGET).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/* 312 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.STONE_AXE).a(2))
/* 313 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.ROTTEN_FLESH).a(10))
/* 314 */           .a(LootItem.a(Items.EMERALD))
/* 315 */           .a(LootItem.a(Items.WHEAT).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(2.0F, 3.0F)))))
/*     */         
/* 317 */         .a(LootSelector.a()
/* 318 */           .a(LootValueConstant.a(1))
/* 319 */           .a(LootItem.a(Items.GOLDEN_APPLE))));
/*     */ 
/*     */ 
/*     */     
/* 323 */     var0.accept(LootTables.A, 
/* 324 */         LootTable.b()
/* 325 */         .a(LootSelector.a()
/* 326 */           .a(LootValueBounds.a(2.0F, 6.0F))
/* 327 */           .a(LootItem.a(Items.DIAMOND).a(3).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/* 328 */           .a(LootItem.a(Items.IRON_INGOT).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 5.0F))))
/* 329 */           .a(LootItem.a(Items.GOLD_INGOT).a(15).b(LootItemFunctionSetCount.a(LootValueBounds.a(2.0F, 7.0F))))
/* 330 */           .a(LootItem.a(Blocks.BAMBOO).a(15).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/* 331 */           .a(LootItem.a(Items.EMERALD).a(2).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/* 332 */           .a(LootItem.a(Items.BONE).a(20).b(LootItemFunctionSetCount.a(LootValueBounds.a(4.0F, 6.0F))))
/* 333 */           .a(LootItem.a(Items.ROTTEN_FLESH).a(16).b(LootItemFunctionSetCount.a(LootValueBounds.a(3.0F, 7.0F))))
/* 334 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.SADDLE).a(3))
/* 335 */           .a(LootItem.a(Items.IRON_HORSE_ARMOR))
/* 336 */           .a(LootItem.a(Items.GOLDEN_HORSE_ARMOR))
/* 337 */           .a(LootItem.a(Items.DIAMOND_HORSE_ARMOR))
/* 338 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.BOOK).b(LootEnchantLevel.a(LootValueConstant.a(30)).e()))));
/*     */ 
/*     */ 
/*     */     
/* 342 */     var0.accept(LootTables.B, 
/* 343 */         LootTable.b()
/* 344 */         .a(LootSelector.a()
/* 345 */           .a(LootValueBounds.a(1.0F, 2.0F))
/* 346 */           .a(LootItem.a(Items.ARROW).a(30).b(LootItemFunctionSetCount.a(LootValueBounds.a(2.0F, 7.0F))))));
/*     */ 
/*     */ 
/*     */     
/* 350 */     var0.accept(LootTables.v, 
/* 351 */         LootTable.b()
/* 352 */         .a(LootSelector.a()
/* 353 */           .a(LootValueBounds.a(2.0F, 4.0F))
/* 354 */           .a(LootItem.a(Items.DIAMOND).a(5).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/* 355 */           .a(LootItem.a(Items.IRON_INGOT).a(5).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 5.0F))))
/* 356 */           .a(LootItem.a(Items.GOLD_INGOT).a(15).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/* 357 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.GOLDEN_SWORD).a(5))
/* 358 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.GOLDEN_CHESTPLATE).a(5))
/* 359 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.FLINT_AND_STEEL).a(5))
/* 360 */           .a(LootItem.a(Items.NETHER_WART).a(5).b(LootItemFunctionSetCount.a(LootValueBounds.a(3.0F, 7.0F))))
/* 361 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.SADDLE).a(10))
/* 362 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.GOLDEN_HORSE_ARMOR).a(8))
/* 363 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.IRON_HORSE_ARMOR).a(5))
/* 364 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.DIAMOND_HORSE_ARMOR).a(3))
/* 365 */           .a(LootItem.a(Blocks.OBSIDIAN).a(2).b(LootItemFunctionSetCount.a(LootValueBounds.a(2.0F, 4.0F))))));
/*     */ 
/*     */ 
/*     */     
/* 369 */     var0.accept(LootTables.K, 
/* 370 */         LootTable.b()
/* 371 */         .a(LootSelector.a()
/* 372 */           .a(LootValueBounds.a(0.0F, 1.0F))
/* 373 */           .a(LootItem.a(Items.CROSSBOW)))
/*     */         
/* 375 */         .a(LootSelector.a()
/* 376 */           .a(LootValueBounds.a(2.0F, 3.0F))
/* 377 */           .a(LootItem.a(Items.WHEAT).a(7).b(LootItemFunctionSetCount.a(LootValueBounds.a(3.0F, 5.0F))))
/* 378 */           .a(LootItem.a(Items.POTATO).a(5).b(LootItemFunctionSetCount.a(LootValueBounds.a(2.0F, 5.0F))))
/* 379 */           .a(LootItem.a(Items.CARROT).a(5).b(LootItemFunctionSetCount.a(LootValueBounds.a(3.0F, 5.0F)))))
/*     */         
/* 381 */         .a(LootSelector.a()
/* 382 */           .a(LootValueBounds.a(1.0F, 3.0F))
/* 383 */           .a((LootEntryAbstract.a<?>)LootItem.a(Blocks.DARK_OAK_LOG).b(LootItemFunctionSetCount.a(LootValueBounds.a(2.0F, 3.0F)))))
/*     */         
/* 385 */         .a(LootSelector.a()
/* 386 */           .a(LootValueBounds.a(2.0F, 3.0F))
/* 387 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.EXPERIENCE_BOTTLE).a(7))
/* 388 */           .a(LootItem.a(Items.STRING).a(4).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 6.0F))))
/* 389 */           .a(LootItem.a(Items.ARROW).a(4).b(LootItemFunctionSetCount.a(LootValueBounds.a(2.0F, 7.0F))))
/* 390 */           .a(LootItem.a(Items.es).a(3).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/* 391 */           .a(LootItem.a(Items.IRON_INGOT).a(3).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/* 392 */           .a(LootItem.a(Items.BOOK).a(1).b(LootItemFunctionEnchant.d()))));
/*     */ 
/*     */ 
/*     */     
/* 396 */     var0.accept(LootTables.H, 
/* 397 */         LootTable.b()
/* 398 */         .a(LootSelector.a()
/* 399 */           .a(LootValueConstant.a(1))
/* 400 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.MAP).b(LootItemFunctionExplorationMap.c().a(StructureGenerator.BURIED_TREASURE).a(MapIcon.Type.RED_X).a((byte)1).a(false))))
/*     */         
/* 402 */         .a(LootSelector.a()
/* 403 */           .a(LootValueConstant.a(3))
/* 404 */           .a(LootItem.a(Items.COMPASS))
/* 405 */           .a(LootItem.a(Items.MAP))
/* 406 */           .a(LootItem.a(Items.CLOCK))
/* 407 */           .a(LootItem.a(Items.PAPER).a(20).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 10.0F))))
/* 408 */           .a(LootItem.a(Items.FEATHER).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 5.0F))))
/* 409 */           .a(LootItem.a(Items.BOOK).a(5).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 5.0F))))));
/*     */ 
/*     */ 
/*     */     
/* 413 */     var0.accept(LootTables.I, 
/* 414 */         LootTable.b()
/* 415 */         .a(LootSelector.a()
/* 416 */           .a(LootValueBounds.a(3.0F, 10.0F))
/* 417 */           .a(LootItem.a(Items.PAPER).a(8).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 12.0F))))
/* 418 */           .a(LootItem.a(Items.POTATO).a(7).b(LootItemFunctionSetCount.a(LootValueBounds.a(2.0F, 6.0F))))
/* 419 */           .a(LootItem.a(Items.POISONOUS_POTATO).a(7).b(LootItemFunctionSetCount.a(LootValueBounds.a(2.0F, 6.0F))))
/* 420 */           .a(LootItem.a(Items.CARROT).a(7).b(LootItemFunctionSetCount.a(LootValueBounds.a(4.0F, 8.0F))))
/* 421 */           .a(LootItem.a(Items.WHEAT).a(7).b(LootItemFunctionSetCount.a(LootValueBounds.a(8.0F, 21.0F))))
/* 422 */           .a(LootItem.a(Items.SUSPICIOUS_STEW).a(10).b(LootItemFunctionSetStewEffect.c()
/* 423 */               .a(MobEffects.NIGHT_VISION, LootValueBounds.a(7.0F, 10.0F))
/* 424 */               .a(MobEffects.JUMP, LootValueBounds.a(7.0F, 10.0F))
/* 425 */               .a(MobEffects.WEAKNESS, LootValueBounds.a(6.0F, 8.0F))
/* 426 */               .a(MobEffects.BLINDNESS, LootValueBounds.a(5.0F, 7.0F))
/* 427 */               .a(MobEffects.POISON, LootValueBounds.a(10.0F, 20.0F))
/* 428 */               .a(MobEffects.SATURATION, LootValueBounds.a(7.0F, 10.0F))))
/*     */           
/* 430 */           .a(LootItem.a(Items.COAL).a(6).b(LootItemFunctionSetCount.a(LootValueBounds.a(2.0F, 8.0F))))
/* 431 */           .a(LootItem.a(Items.ROTTEN_FLESH).a(5).b(LootItemFunctionSetCount.a(LootValueBounds.a(5.0F, 24.0F))))
/* 432 */           .a(LootItem.a(Blocks.PUMPKIN).a(2).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/* 433 */           .a(LootItem.a(Blocks.BAMBOO).a(2).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/* 434 */           .a(LootItem.a(Items.GUNPOWDER).a(3).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 5.0F))))
/* 435 */           .a((LootEntryAbstract.a<?>)LootItem.a(Blocks.TNT).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 2.0F))))
/* 436 */           .a(LootItem.a(Items.LEATHER_HELMET).a(3).b(LootItemFunctionEnchant.d()))
/* 437 */           .a(LootItem.a(Items.LEATHER_CHESTPLATE).a(3).b(LootItemFunctionEnchant.d()))
/* 438 */           .a(LootItem.a(Items.LEATHER_LEGGINGS).a(3).b(LootItemFunctionEnchant.d()))
/* 439 */           .a(LootItem.a(Items.LEATHER_BOOTS).a(3).b(LootItemFunctionEnchant.d()))));
/*     */ 
/*     */ 
/*     */     
/* 443 */     var0.accept(LootTables.J, 
/* 444 */         LootTable.b()
/* 445 */         .a(LootSelector.a()
/* 446 */           .a(LootValueBounds.a(3.0F, 6.0F))
/* 447 */           .a(LootItem.a(Items.IRON_INGOT).a(90).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 5.0F))))
/* 448 */           .a(LootItem.a(Items.GOLD_INGOT).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 5.0F))))
/* 449 */           .a(LootItem.a(Items.EMERALD).a(40).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 5.0F))))
/* 450 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.DIAMOND).a(5))
/* 451 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.EXPERIENCE_BOTTLE).a(5)))
/*     */         
/* 453 */         .a(LootSelector.a()
/* 454 */           .a(LootValueBounds.a(2.0F, 5.0F))
/* 455 */           .a(LootItem.a(Items.IRON_NUGGET).a(50).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 10.0F))))
/* 456 */           .a(LootItem.a(Items.GOLD_NUGGET).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 10.0F))))
/* 457 */           .a(LootItem.a(Items.LAPIS_LAZULI).a(20).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 10.0F))))));
/*     */ 
/*     */ 
/*     */     
/* 461 */     var0.accept(LootTables.d, 
/* 462 */         LootTable.b()
/* 463 */         .a(LootSelector.a()
/* 464 */           .a(LootValueBounds.a(1.0F, 3.0F))
/* 465 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.SADDLE).a(20))
/* 466 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.GOLDEN_APPLE).a(15))
/* 467 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.ENCHANTED_GOLDEN_APPLE).a(2))
/* 468 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.MUSIC_DISC_13).a(15))
/* 469 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.MUSIC_DISC_CAT).a(15))
/* 470 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.NAME_TAG).a(20))
/* 471 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.GOLDEN_HORSE_ARMOR).a(10))
/* 472 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.IRON_HORSE_ARMOR).a(15))
/* 473 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.DIAMOND_HORSE_ARMOR).a(5))
/* 474 */           .a(LootItem.a(Items.BOOK).a(10).b(LootItemFunctionEnchant.d())))
/*     */         
/* 476 */         .a(LootSelector.a()
/* 477 */           .a(LootValueBounds.a(1.0F, 4.0F))
/* 478 */           .a(LootItem.a(Items.IRON_INGOT).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 4.0F))))
/* 479 */           .a(LootItem.a(Items.GOLD_INGOT).a(5).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 4.0F))))
/* 480 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.BREAD).a(20))
/* 481 */           .a(LootItem.a(Items.WHEAT).a(20).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 4.0F))))
/* 482 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.BUCKET).a(10))
/* 483 */           .a(LootItem.a(Items.REDSTONE).a(15).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 4.0F))))
/* 484 */           .a(LootItem.a(Items.COAL).a(15).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 4.0F))))
/* 485 */           .a(LootItem.a(Items.MELON_SEEDS).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(2.0F, 4.0F))))
/* 486 */           .a(LootItem.a(Items.PUMPKIN_SEEDS).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(2.0F, 4.0F))))
/* 487 */           .a(LootItem.a(Items.BEETROOT_SEEDS).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(2.0F, 4.0F)))))
/*     */         
/* 489 */         .a(LootSelector.a()
/* 490 */           .a(LootValueConstant.a(3))
/* 491 */           .a(LootItem.a(Items.BONE).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 8.0F))))
/* 492 */           .a(LootItem.a(Items.GUNPOWDER).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 8.0F))))
/* 493 */           .a(LootItem.a(Items.ROTTEN_FLESH).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 8.0F))))
/* 494 */           .a(LootItem.a(Items.STRING).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 8.0F))))));
/*     */ 
/*     */ 
/*     */     
/* 498 */     var0.accept(LootTables.b, 
/* 499 */         LootTable.b()
/* 500 */         .a(LootSelector.a()
/* 501 */           .a(LootValueConstant.a(1))
/* 502 */           .a(LootItem.a(Items.STONE_AXE))
/* 503 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.WOODEN_AXE).a(3)))
/*     */         
/* 505 */         .a(LootSelector.a()
/* 506 */           .a(LootValueConstant.a(1))
/* 507 */           .a(LootItem.a(Items.STONE_PICKAXE))
/* 508 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.WOODEN_PICKAXE).a(3)))
/*     */         
/* 510 */         .a(LootSelector.a()
/* 511 */           .a(LootValueConstant.a(3))
/* 512 */           .a(LootItem.a(Items.APPLE).a(5).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 2.0F))))
/* 513 */           .a(LootItem.a(Items.BREAD).a(3).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 2.0F))))
/* 514 */           .a(LootItem.a(Items.SALMON).a(3).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 2.0F)))))
/*     */         
/* 516 */         .a(LootSelector.a()
/* 517 */           .a(LootValueConstant.a(4))
/* 518 */           .a(LootItem.a(Items.STICK).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 12.0F))))
/* 519 */           .a(LootItem.a(Blocks.OAK_PLANKS).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 12.0F))))
/* 520 */           .a(LootItem.a(Blocks.OAK_LOG).a(3).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/* 521 */           .a(LootItem.a(Blocks.SPRUCE_LOG).a(3).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/* 522 */           .a(LootItem.a(Blocks.BIRCH_LOG).a(3).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/* 523 */           .a(LootItem.a(Blocks.JUNGLE_LOG).a(3).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/* 524 */           .a(LootItem.a(Blocks.ACACIA_LOG).a(3).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/* 525 */           .a(LootItem.a(Blocks.DARK_OAK_LOG).a(3).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))));
/*     */ 
/*     */ 
/*     */     
/* 529 */     var0.accept(LootTables.y, 
/* 530 */         LootTable.b()
/* 531 */         .a(LootSelector.a()
/* 532 */           .a(LootValueBounds.a(2.0F, 3.0F))
/* 533 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.ENDER_PEARL).a(10))
/* 534 */           .a(LootItem.a(Items.DIAMOND).a(3).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/* 535 */           .a(LootItem.a(Items.IRON_INGOT).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 5.0F))))
/* 536 */           .a(LootItem.a(Items.GOLD_INGOT).a(5).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/* 537 */           .a(LootItem.a(Items.REDSTONE).a(5).b(LootItemFunctionSetCount.a(LootValueBounds.a(4.0F, 9.0F))))
/* 538 */           .a(LootItem.a(Items.BREAD).a(15).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/* 539 */           .a(LootItem.a(Items.APPLE).a(15).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/* 540 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.IRON_PICKAXE).a(5))
/* 541 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.IRON_SWORD).a(5))
/* 542 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.IRON_CHESTPLATE).a(5))
/* 543 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.IRON_HELMET).a(5))
/* 544 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.IRON_LEGGINGS).a(5))
/* 545 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.IRON_BOOTS).a(5))
/* 546 */           .a(LootItem.a(Items.GOLDEN_APPLE))
/* 547 */           .a(LootItem.a(Items.SADDLE))
/* 548 */           .a(LootItem.a(Items.IRON_HORSE_ARMOR))
/* 549 */           .a(LootItem.a(Items.GOLDEN_HORSE_ARMOR))
/* 550 */           .a(LootItem.a(Items.DIAMOND_HORSE_ARMOR))
/* 551 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.BOOK).b(LootEnchantLevel.a(LootValueConstant.a(30)).e()))));
/*     */ 
/*     */ 
/*     */     
/* 555 */     var0.accept(LootTables.x, 
/* 556 */         LootTable.b()
/* 557 */         .a(LootSelector.a()
/* 558 */           .a(LootValueBounds.a(1.0F, 4.0F))
/* 559 */           .a(LootItem.a(Items.IRON_INGOT).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 5.0F))))
/* 560 */           .a(LootItem.a(Items.GOLD_INGOT).a(5).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/* 561 */           .a(LootItem.a(Items.REDSTONE).a(5).b(LootItemFunctionSetCount.a(LootValueBounds.a(4.0F, 9.0F))))
/* 562 */           .a(LootItem.a(Items.COAL).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(3.0F, 8.0F))))
/* 563 */           .a(LootItem.a(Items.BREAD).a(15).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/* 564 */           .a(LootItem.a(Items.APPLE).a(15).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/* 565 */           .a(LootItem.a(Items.IRON_PICKAXE))
/* 566 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.BOOK).b(LootEnchantLevel.a(LootValueConstant.a(30)).e()))));
/*     */ 
/*     */ 
/*     */     
/* 570 */     var0.accept(LootTables.w, 
/* 571 */         LootTable.b()
/* 572 */         .a(LootSelector.a()
/* 573 */           .a(LootValueBounds.a(2.0F, 10.0F))
/* 574 */           .a(LootItem.a(Items.BOOK).a(20).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/* 575 */           .a(LootItem.a(Items.PAPER).a(20).b(LootItemFunctionSetCount.a(LootValueBounds.a(2.0F, 7.0F))))
/* 576 */           .a(LootItem.a(Items.MAP))
/* 577 */           .a(LootItem.a(Items.COMPASS))
/* 578 */           .a(LootItem.a(Items.BOOK).a(10).b(LootEnchantLevel.a(LootValueConstant.a(30)).e()))));
/*     */ 
/*     */ 
/*     */     
/* 582 */     var0.accept(LootTables.F, 
/* 583 */         LootTable.b()
/* 584 */         .a(LootSelector.a()
/* 585 */           .a(LootValueBounds.a(2.0F, 8.0F))
/* 586 */           .a(LootItem.a(Items.COAL).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 4.0F))))
/* 587 */           .a(LootItem.a(Items.GOLD_NUGGET).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/* 588 */           .a(LootItem.a(Items.EMERALD))
/* 589 */           .a(LootItem.a(Items.WHEAT).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(2.0F, 3.0F)))))
/*     */         
/* 591 */         .a(LootSelector.a()
/* 592 */           .a(LootValueConstant.a(1))
/* 593 */           .a(LootItem.a(Items.GOLDEN_APPLE))
/* 594 */           .a(LootItem.a(Items.BOOK).a(5).b(LootItemFunctionEnchant.d()))
/* 595 */           .a(LootItem.a(Items.LEATHER_CHESTPLATE))
/* 596 */           .a(LootItem.a(Items.GOLDEN_HELMET))
/* 597 */           .a(LootItem.a(Items.FISHING_ROD).a(5).b(LootItemFunctionEnchant.d()))
/* 598 */           .a(LootItem.a(Items.MAP).a(10).b(LootItemFunctionExplorationMap.c().a(StructureGenerator.BURIED_TREASURE).a(MapIcon.Type.RED_X).a((byte)1).a(false)))));
/*     */ 
/*     */ 
/*     */     
/* 602 */     var0.accept(LootTables.E, 
/* 603 */         LootTable.b()
/* 604 */         .a(LootSelector.a()
/* 605 */           .a(LootValueBounds.a(2.0F, 8.0F))
/* 606 */           .a(LootItem.a(Items.COAL).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 4.0F))))
/* 607 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.STONE_AXE).a(2))
/* 608 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.ROTTEN_FLESH).a(5))
/* 609 */           .a(LootItem.a(Items.EMERALD))
/* 610 */           .a(LootItem.a(Items.WHEAT).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(2.0F, 3.0F)))))
/*     */         
/* 612 */         .a(LootSelector.a()
/* 613 */           .a(LootValueConstant.a(1))
/* 614 */           .a(LootItem.a(Items.LEATHER_CHESTPLATE))
/* 615 */           .a(LootItem.a(Items.GOLDEN_HELMET))
/* 616 */           .a(LootItem.a(Items.FISHING_ROD).a(5).b(LootItemFunctionEnchant.d()))
/* 617 */           .a(LootItem.a(Items.MAP).a(5).b(LootItemFunctionExplorationMap.c().a(StructureGenerator.BURIED_TREASURE).a(MapIcon.Type.RED_X).a((byte)1).a(false)))));
/*     */ 
/*     */ 
/*     */     
/* 621 */     var0.accept(LootTables.e, 
/* 622 */         LootTable.b()
/* 623 */         .a(LootSelector.a()
/* 624 */           .a(LootValueBounds.a(3.0F, 8.0F))
/* 625 */           .a(LootItem.a(Items.DIAMOND).a(3).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/* 626 */           .a(LootItem.a(Items.IRON_INGOT).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 5.0F))))
/* 627 */           .a(LootItem.a(Items.GOLD_INGOT).a(5).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/* 628 */           .a(LootItem.a(Items.BREAD).a(15).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/* 629 */           .a(LootItem.a(Items.APPLE).a(15).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/* 630 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.IRON_PICKAXE).a(5))
/* 631 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.IRON_SWORD).a(5))
/* 632 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.IRON_CHESTPLATE).a(5))
/* 633 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.IRON_HELMET).a(5))
/* 634 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.IRON_LEGGINGS).a(5))
/* 635 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.IRON_BOOTS).a(5))
/* 636 */           .a(LootItem.a(Blocks.OBSIDIAN).a(5).b(LootItemFunctionSetCount.a(LootValueBounds.a(3.0F, 7.0F))))
/* 637 */           .a(LootItem.a(Blocks.OAK_SAPLING).a(5).b(LootItemFunctionSetCount.a(LootValueBounds.a(3.0F, 7.0F))))
/* 638 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.SADDLE).a(3))
/* 639 */           .a(LootItem.a(Items.IRON_HORSE_ARMOR))
/* 640 */           .a(LootItem.a(Items.GOLDEN_HORSE_ARMOR))
/* 641 */           .a(LootItem.a(Items.DIAMOND_HORSE_ARMOR))));
/*     */ 
/*     */ 
/*     */     
/* 645 */     var0.accept(LootTables.f, 
/* 646 */         LootTable.b()
/* 647 */         .a(LootSelector.a()
/* 648 */           .a(LootValueBounds.a(3.0F, 8.0F))
/* 649 */           .a(LootItem.a(Items.DIAMOND).a(1).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/* 650 */           .a(LootItem.a(Items.IRON_INGOT).a(5).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 5.0F))))
/* 651 */           .a(LootItem.a(Items.GOLD_INGOT).a(1).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/* 652 */           .a(LootItem.a(Items.BREAD).a(15).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/* 653 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.IRON_PICKAXE).a(5))
/* 654 */           .a(LootItem.a(Items.COAL).a(1).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/* 655 */           .a(LootItem.a(Items.STICK).a(20).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/* 656 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.IRON_SHOVEL).a(5))));
/*     */ 
/*     */ 
/*     */     
/* 660 */     var0.accept(LootTables.h, 
/* 661 */         LootTable.b()
/* 662 */         .a(LootSelector.a()
/* 663 */           .a(LootValueBounds.a(1.0F, 5.0F))
/* 664 */           .a(LootItem.a(Items.MAP).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/* 665 */           .a(LootItem.a(Items.PAPER).a(15).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 5.0F))))
/* 666 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.COMPASS).a(5))
/* 667 */           .a(LootItem.a(Items.BREAD).a(15).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 4.0F))))
/* 668 */           .a(LootItem.a(Items.STICK).a(5).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 2.0F))))));
/*     */ 
/*     */ 
/*     */     
/* 672 */     var0.accept(LootTables.i, 
/* 673 */         LootTable.b()
/* 674 */         .a(LootSelector.a()
/* 675 */           .a(LootValueBounds.a(1.0F, 5.0F))
/* 676 */           .a(LootItem.a(Items.CLAY_BALL).a(1).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/* 677 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.oX).a(1))
/* 678 */           .a((LootEntryAbstract.a<?>)LootItem.a(Blocks.STONE).a(2))
/* 679 */           .a((LootEntryAbstract.a<?>)LootItem.a(Blocks.STONE_BRICKS).a(2))
/* 680 */           .a(LootItem.a(Items.BREAD).a(4).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 4.0F))))
/* 681 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.YELLOW_DYE).a(1))
/* 682 */           .a((LootEntryAbstract.a<?>)LootItem.a(Blocks.SMOOTH_STONE).a(1))
/* 683 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.EMERALD).a(1))));
/*     */ 
/*     */ 
/*     */     
/* 687 */     var0.accept(LootTables.g, 
/* 688 */         LootTable.b()
/* 689 */         .a(LootSelector.a()
/* 690 */           .a(LootValueBounds.a(1.0F, 5.0F))
/* 691 */           .a(LootItem.a(Items.IRON_INGOT).a(2).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/* 692 */           .a(LootItem.a(Items.BREAD).a(4).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 4.0F))))
/* 693 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.IRON_HELMET).a(1))
/* 694 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.EMERALD).a(1))));
/*     */ 
/*     */ 
/*     */     
/* 698 */     var0.accept(LootTables.j, 
/* 699 */         LootTable.b()
/* 700 */         .a(LootSelector.a()
/* 701 */           .a(LootValueBounds.a(1.0F, 5.0F))
/* 702 */           .a(LootItem.a(Blocks.WHITE_WOOL).a(6).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 8.0F))))
/* 703 */           .a(LootItem.a(Blocks.BLACK_WOOL).a(3).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/* 704 */           .a(LootItem.a(Blocks.GRAY_WOOL).a(2).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/* 705 */           .a(LootItem.a(Blocks.BROWN_WOOL).a(2).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/* 706 */           .a(LootItem.a(Blocks.LIGHT_GRAY_WOOL).a(2).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/* 707 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.EMERALD).a(1))
/* 708 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.SHEARS).a(1))
/* 709 */           .a(LootItem.a(Items.WHEAT).a(6).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 6.0F))))));
/*     */ 
/*     */ 
/*     */     
/* 713 */     var0.accept(LootTables.k, 
/* 714 */         LootTable.b()
/* 715 */         .a(LootSelector.a()
/* 716 */           .a(LootValueBounds.a(1.0F, 5.0F))
/* 717 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.EMERALD).a(1))
/* 718 */           .a(LootItem.a(Items.PORKCHOP).a(6).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/* 719 */           .a(LootItem.a(Items.WHEAT).a(6).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/* 720 */           .a(LootItem.a(Items.BEEF).a(6).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/* 721 */           .a(LootItem.a(Items.MUTTON).a(6).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/* 722 */           .a(LootItem.a(Items.COAL).a(3).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))));
/*     */ 
/*     */ 
/*     */     
/* 726 */     var0.accept(LootTables.l, 
/* 727 */         LootTable.b()
/* 728 */         .a(LootSelector.a()
/* 729 */           .a(LootValueBounds.a(1.0F, 5.0F))
/* 730 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.EMERALD).a(1))
/* 731 */           .a(LootItem.a(Items.ARROW).a(2).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/* 732 */           .a(LootItem.a(Items.FEATHER).a(6).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/* 733 */           .a(LootItem.a(Items.EGG).a(2).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/* 734 */           .a(LootItem.a(Items.FLINT).a(6).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/* 735 */           .a(LootItem.a(Items.STICK).a(6).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))));
/*     */ 
/*     */ 
/*     */     
/* 739 */     var0.accept(LootTables.m, 
/* 740 */         LootTable.b()
/* 741 */         .a(LootSelector.a()
/* 742 */           .a(LootValueBounds.a(1.0F, 5.0F))
/* 743 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.EMERALD).a(1))
/* 744 */           .a(LootItem.a(Items.COD).a(2).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/* 745 */           .a(LootItem.a(Items.SALMON).a(1).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/* 746 */           .a(LootItem.a(Items.WATER_BUCKET).a(1).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/* 747 */           .a(LootItem.a(Items.ra).a(1).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/* 748 */           .a(LootItem.a(Items.WHEAT_SEEDS).a(3).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/* 749 */           .a(LootItem.a(Items.COAL).a(2).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))));
/*     */ 
/*     */ 
/*     */     
/* 753 */     var0.accept(LootTables.n, 
/* 754 */         LootTable.b()
/* 755 */         .a(LootSelector.a()
/* 756 */           .a(LootValueBounds.a(1.0F, 5.0F))
/* 757 */           .a(LootItem.a(Items.LEATHER).a(1).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/* 758 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.LEATHER_CHESTPLATE).a(2))
/* 759 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.LEATHER_BOOTS).a(2))
/* 760 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.LEATHER_HELMET).a(2))
/* 761 */           .a(LootItem.a(Items.BREAD).a(5).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 4.0F))))
/* 762 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.LEATHER_LEGGINGS).a(2))
/* 763 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.SADDLE).a(1))
/* 764 */           .a(LootItem.a(Items.EMERALD).a(1).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 4.0F))))));
/*     */ 
/*     */ 
/*     */     
/* 768 */     var0.accept(LootTables.o, 
/* 769 */         LootTable.b()
/* 770 */         .a(LootSelector.a()
/* 771 */           .a(LootValueBounds.a(3.0F, 8.0F))
/* 772 */           .a(LootItem.a(Items.REDSTONE).a(2).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 4.0F))))
/* 773 */           .a(LootItem.a(Items.BREAD).a(7).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 4.0F))))
/* 774 */           .a(LootItem.a(Items.ROTTEN_FLESH).a(7).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 4.0F))))
/* 775 */           .a(LootItem.a(Items.LAPIS_LAZULI).a(1).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 4.0F))))
/* 776 */           .a(LootItem.a(Items.GOLD_INGOT).a(1).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 4.0F))))
/* 777 */           .a(LootItem.a(Items.EMERALD).a(1).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 4.0F))))));
/*     */ 
/*     */ 
/*     */     
/* 781 */     var0.accept(LootTables.q, 
/* 782 */         LootTable.b()
/* 783 */         .a(LootSelector.a()
/* 784 */           .a(LootValueBounds.a(3.0F, 8.0F))
/* 785 */           .a(LootItem.a(Items.GOLD_NUGGET).a(1).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/* 786 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.bh).a(2))
/* 787 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.bi).a(1))
/* 788 */           .a(LootItem.a(Items.POTATO).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 7.0F))))
/* 789 */           .a(LootItem.a(Items.BREAD).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 4.0F))))
/* 790 */           .a(LootItem.a(Items.APPLE).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 5.0F))))
/* 791 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.BOOK).a(1))
/* 792 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.FEATHER).a(1))
/* 793 */           .a(LootItem.a(Items.EMERALD).a(2).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 4.0F))))
/* 794 */           .a(LootItem.a(Blocks.OAK_SAPLING).a(5).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 2.0F))))));
/*     */ 
/*     */ 
/*     */     
/* 798 */     var0.accept(LootTables.r, 
/* 799 */         LootTable.b()
/* 800 */         .a(LootSelector.a()
/* 801 */           .a(LootValueBounds.a(3.0F, 8.0F))
/* 802 */           .a(LootItem.a(Items.IRON_NUGGET).a(1).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 5.0F))))
/* 803 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.aM).a(2))
/* 804 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.go).a(2))
/* 805 */           .a(LootItem.a(Items.POTATO).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 7.0F))))
/* 806 */           .a(LootItem.a(Items.SWEET_BERRIES).a(5).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 7.0F))))
/* 807 */           .a(LootItem.a(Items.BREAD).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 4.0F))))
/* 808 */           .a(LootItem.a(Items.PUMPKIN_SEEDS).a(5).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 5.0F))))
/* 809 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.PUMPKIN_PIE).a(1))
/* 810 */           .a(LootItem.a(Items.EMERALD).a(2).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 4.0F))))
/* 811 */           .a(LootItem.a(Blocks.SPRUCE_SAPLING).a(5).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 5.0F))))
/* 812 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.SPRUCE_SIGN).a(1))
/* 813 */           .a(LootItem.a(Items.M).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 5.0F))))));
/*     */ 
/*     */ 
/*     */     
/* 817 */     var0.accept(LootTables.t, 
/* 818 */         LootTable.b()
/* 819 */         .a(LootSelector.a()
/* 820 */           .a(LootValueBounds.a(3.0F, 8.0F))
/* 821 */           .a(LootItem.a(Items.GOLD_NUGGET).a(1).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/* 822 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.aL).a(5))
/* 823 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.gn).a(5))
/* 824 */           .a(LootItem.a(Items.BREAD).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 4.0F))))
/* 825 */           .a(LootItem.a(Items.WHEAT_SEEDS).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 5.0F))))
/* 826 */           .a(LootItem.a(Items.EMERALD).a(2).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 4.0F))))
/* 827 */           .a(LootItem.a(Blocks.ACACIA_SAPLING).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 2.0F))))
/* 828 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.SADDLE).a(1))
/* 829 */           .a(LootItem.a(Blocks.TORCH).a(1).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 2.0F))))
/* 830 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.BUCKET).a(1))));
/*     */ 
/*     */ 
/*     */     
/* 834 */     var0.accept(LootTables.s, 
/* 835 */         LootTable.b()
/* 836 */         .a(LootSelector.a()
/* 837 */           .a(LootValueBounds.a(3.0F, 8.0F))
/* 838 */           .a((LootEntryAbstract.a<?>)LootItem.a(Blocks.BLUE_ICE).a(1))
/* 839 */           .a((LootEntryAbstract.a<?>)LootItem.a(Blocks.SNOW_BLOCK).a(4))
/* 840 */           .a(LootItem.a(Items.POTATO).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 7.0F))))
/* 841 */           .a(LootItem.a(Items.BREAD).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 4.0F))))
/* 842 */           .a(LootItem.a(Items.BEETROOT_SEEDS).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 5.0F))))
/* 843 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.BEETROOT_SOUP).a(1))
/* 844 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.cD).a(1))
/* 845 */           .a(LootItem.a(Items.EMERALD).a(1).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 4.0F))))
/* 846 */           .a(LootItem.a(Items.SNOWBALL).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 7.0F))))
/* 847 */           .a(LootItem.a(Items.COAL).a(5).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 4.0F))))));
/*     */ 
/*     */ 
/*     */     
/* 851 */     var0.accept(LootTables.p, 
/* 852 */         LootTable.b()
/* 853 */         .a(LootSelector.a()
/* 854 */           .a(LootValueBounds.a(3.0F, 8.0F))
/* 855 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.CLAY_BALL).a(1))
/* 856 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.GREEN_DYE).a(1))
/* 857 */           .a(LootItem.a(Blocks.CACTUS).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 4.0F))))
/* 858 */           .a(LootItem.a(Items.WHEAT).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 7.0F))))
/* 859 */           .a(LootItem.a(Items.BREAD).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 4.0F))))
/* 860 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.BOOK).a(1))
/* 861 */           .a(LootItem.a(Blocks.DEAD_BUSH).a(2).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))
/* 862 */           .a(LootItem.a(Items.EMERALD).a(1).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))))));
/*     */ 
/*     */ 
/*     */     
/* 866 */     var0.accept(LootTables.D, 
/* 867 */         LootTable.b()
/* 868 */         .a(LootSelector.a()
/* 869 */           .a(LootValueBounds.a(1.0F, 3.0F))
/* 870 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.LEAD).a(20))
/* 871 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.GOLDEN_APPLE).a(15))
/* 872 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.ENCHANTED_GOLDEN_APPLE).a(2))
/* 873 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.MUSIC_DISC_13).a(15))
/* 874 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.MUSIC_DISC_CAT).a(15))
/* 875 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.NAME_TAG).a(20))
/* 876 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.CHAINMAIL_CHESTPLATE).a(10))
/* 877 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.DIAMOND_HOE).a(15))
/* 878 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.DIAMOND_CHESTPLATE).a(5))
/* 879 */           .a(LootItem.a(Items.BOOK).a(10).b(LootItemFunctionEnchant.d())))
/*     */         
/* 881 */         .a(LootSelector.a()
/* 882 */           .a(LootValueBounds.a(1.0F, 4.0F))
/* 883 */           .a(LootItem.a(Items.IRON_INGOT).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 4.0F))))
/* 884 */           .a(LootItem.a(Items.GOLD_INGOT).a(5).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 4.0F))))
/* 885 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.BREAD).a(20))
/* 886 */           .a(LootItem.a(Items.WHEAT).a(20).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 4.0F))))
/* 887 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.BUCKET).a(10))
/* 888 */           .a(LootItem.a(Items.REDSTONE).a(15).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 4.0F))))
/* 889 */           .a(LootItem.a(Items.COAL).a(15).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 4.0F))))
/* 890 */           .a(LootItem.a(Items.MELON_SEEDS).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(2.0F, 4.0F))))
/* 891 */           .a(LootItem.a(Items.PUMPKIN_SEEDS).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(2.0F, 4.0F))))
/* 892 */           .a(LootItem.a(Items.BEETROOT_SEEDS).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(2.0F, 4.0F)))))
/*     */         
/* 894 */         .a(LootSelector.a()
/* 895 */           .a(LootValueConstant.a(3))
/* 896 */           .a(LootItem.a(Items.BONE).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 8.0F))))
/* 897 */           .a(LootItem.a(Items.GUNPOWDER).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 8.0F))))
/* 898 */           .a(LootItem.a(Items.ROTTEN_FLESH).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 8.0F))))
/* 899 */           .a(LootItem.a(Items.STRING).a(10).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 8.0F))))));
/*     */ 
/*     */ 
/*     */     
/* 903 */     var0.accept(LootTables.P, 
/* 904 */         LootTable.b()
/* 905 */         .a(LootSelector.a()
/* 906 */           .a(LootValueBounds.a(4.0F, 8.0F))
/* 907 */           .a(LootItem.a(Items.co).a(40).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 2.0F))))
/* 908 */           .a(LootItem.a(Items.FLINT).a(40).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 4.0F))))
/* 909 */           .a(LootItem.a(Items.IRON_NUGGET).a(40).b(LootItemFunctionSetCount.a(LootValueBounds.a(9.0F, 18.0F))))
/* 910 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.FLINT_AND_STEEL).a(40))
/* 911 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.FIRE_CHARGE).a(40))
/*     */           
/* 913 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.GOLDEN_APPLE).a(15))
/* 914 */           .a(LootItem.a(Items.GOLD_NUGGET).a(15).b(LootItemFunctionSetCount.a(LootValueBounds.a(4.0F, 24.0F))))
/* 915 */           .a(LootItem.a(Items.GOLDEN_SWORD).a(15).b(LootItemFunctionEnchant.d()))
/* 916 */           .a(LootItem.a(Items.GOLDEN_AXE).a(15).b(LootItemFunctionEnchant.d()))
/* 917 */           .a(LootItem.a(Items.GOLDEN_HOE).a(15).b(LootItemFunctionEnchant.d()))
/* 918 */           .a(LootItem.a(Items.GOLDEN_SHOVEL).a(15).b(LootItemFunctionEnchant.d()))
/* 919 */           .a(LootItem.a(Items.GOLDEN_PICKAXE).a(15).b(LootItemFunctionEnchant.d()))
/* 920 */           .a(LootItem.a(Items.GOLDEN_BOOTS).a(15).b(LootItemFunctionEnchant.d()))
/* 921 */           .a(LootItem.a(Items.GOLDEN_CHESTPLATE).a(15).b(LootItemFunctionEnchant.d()))
/* 922 */           .a(LootItem.a(Items.GOLDEN_HELMET).a(15).b(LootItemFunctionEnchant.d()))
/* 923 */           .a(LootItem.a(Items.GOLDEN_LEGGINGS).a(15).b(LootItemFunctionEnchant.d()))
/*     */           
/* 925 */           .a(LootItem.a(Items.GLISTERING_MELON_SLICE).a(5).b(LootItemFunctionSetCount.a(LootValueBounds.a(4.0F, 12.0F))))
/* 926 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.GOLDEN_HORSE_ARMOR).a(5))
/* 927 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.fg).a(5))
/* 928 */           .a(LootItem.a(Items.GOLDEN_CARROT).a(5).b(LootItemFunctionSetCount.a(LootValueBounds.a(4.0F, 12.0F))))
/* 929 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.CLOCK).a(5))
/* 930 */           .a(LootItem.a(Items.GOLD_INGOT).a(5).b(LootItemFunctionSetCount.a(LootValueBounds.a(2.0F, 8.0F))))
/*     */           
/* 932 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.rj).a(1))
/* 933 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.ENCHANTED_GOLDEN_APPLE).a(1))
/* 934 */           .a(LootItem.a(Items.bG).a(1).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 2.0F))))));
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ia.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */