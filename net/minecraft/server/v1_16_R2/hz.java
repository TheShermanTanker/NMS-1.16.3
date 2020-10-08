/*      */ package net.minecraft.server.v1_16_R2;
/*      */ 
/*      */ import com.google.common.collect.ImmutableSet;
/*      */ import com.google.common.collect.Maps;
/*      */ import com.google.common.collect.Sets;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import java.util.function.BiConsumer;
/*      */ import java.util.function.Consumer;
/*      */ import java.util.function.Function;
/*      */ import java.util.stream.Stream;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class hz
/*      */   implements Consumer<BiConsumer<MinecraftKey, LootTable.a>>
/*      */ {
/*   96 */   private static final LootItemCondition.a a = LootItemConditionMatchTool.a(CriterionConditionItem.a.a().a(new CriterionConditionEnchantments(Enchantments.SILK_TOUCH, CriterionConditionValue.IntegerRange.b(1))));
/*   97 */   private static final LootItemCondition.a b = a.a();
/*      */   
/*   99 */   private static final LootItemCondition.a c = LootItemConditionMatchTool.a(CriterionConditionItem.a.a().a(Items.SHEARS));
/*  100 */   private static final LootItemCondition.a d = c.a(a);
/*      */   
/*  102 */   private static final LootItemCondition.a e = d.a();
/*      */   
/*  104 */   private static final Set<Item> f = (Set<Item>)Stream.<Block>of(new Block[] { Blocks.DRAGON_EGG, Blocks.BEACON, Blocks.CONDUIT, Blocks.SKELETON_SKULL, Blocks.WITHER_SKELETON_SKULL, Blocks.PLAYER_HEAD, Blocks.ZOMBIE_HEAD, Blocks.CREEPER_HEAD, Blocks.DRAGON_HEAD, Blocks.SHULKER_BOX, Blocks.BLACK_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.LIGHT_GRAY_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.WHITE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  135 */       }).map(IMaterial::getItem).collect(ImmutableSet.toImmutableSet());
/*      */   
/*  137 */   private static final float[] g = new float[] { 0.05F, 0.0625F, 0.083333336F, 0.1F };
/*  138 */   private static final float[] h = new float[] { 0.025F, 0.027777778F, 0.03125F, 0.041666668F, 0.1F };
/*      */   
/*  140 */   private final Map<MinecraftKey, LootTable.a> i = Maps.newHashMap();
/*      */   
/*      */   private static <T> T a(IMaterial var0, LootItemFunctionUser<T> var1) {
/*  143 */     if (!f.contains(var0.getItem())) {
/*  144 */       return var1.b(LootItemFunctionExplosionDecay.c());
/*      */     }
/*      */     
/*  147 */     return var1.c();
/*      */   }
/*      */   
/*      */   private static <T> T a(IMaterial var0, LootItemConditionUser<T> var1) {
/*  151 */     if (!f.contains(var0.getItem())) {
/*  152 */       return var1.b(LootItemConditionSurvivesExplosion.c());
/*      */     }
/*      */     
/*  155 */     return var1.c();
/*      */   }
/*      */   
/*      */   private static LootTable.a a(IMaterial var0) {
/*  159 */     return LootTable.b()
/*  160 */       .a(a(var0, LootSelector.a()
/*  161 */           .a(LootValueConstant.a(1))
/*  162 */           .a(LootItem.a(var0))));
/*      */   }
/*      */ 
/*      */   
/*      */   private static LootTable.a a(Block var0, LootItemCondition.a var1, LootEntryAbstract.a<?> var2) {
/*  167 */     return LootTable.b()
/*  168 */       .a(LootSelector.a()
/*  169 */         .a(LootValueConstant.a(1))
/*  170 */         .a(((LootSelectorEntry.a)LootItem.a(var0)
/*  171 */           .b(var1))
/*  172 */           .a(var2)));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static LootTable.a a(Block var0, LootEntryAbstract.a<?> var1) {
/*  178 */     return a(var0, a, var1);
/*      */   }
/*      */   
/*      */   private static LootTable.a b(Block var0, LootEntryAbstract.a<?> var1) {
/*  182 */     return a(var0, c, var1);
/*      */   }
/*      */   
/*      */   private static LootTable.a c(Block var0, LootEntryAbstract.a<?> var1) {
/*  186 */     return a(var0, d, var1);
/*      */   }
/*      */   
/*      */   private static LootTable.a b(Block var0, IMaterial var1) {
/*  190 */     return a(var0, (LootEntryAbstract.a)a(var0, LootItem.a(var1)));
/*      */   }
/*      */   
/*      */   private static LootTable.a a(IMaterial var0, LootValue var1) {
/*  194 */     return LootTable.b()
/*  195 */       .a(LootSelector.a()
/*  196 */         .a(LootValueConstant.a(1))
/*  197 */         .a(a(var0, (LootItemFunctionUser<LootEntryAbstract.a>)LootItem.a(var0).b(LootItemFunctionSetCount.a(var1)))));
/*      */   }
/*      */ 
/*      */   
/*      */   private static LootTable.a a(Block var0, IMaterial var1, LootValue var2) {
/*  202 */     return a(var0, a(var0, (LootItemFunctionUser<LootEntryAbstract.a>)LootItem.a(var1).b(LootItemFunctionSetCount.a(var2))));
/*      */   }
/*      */   
/*      */   private static LootTable.a b(IMaterial var0) {
/*  206 */     return LootTable.b()
/*  207 */       .a(LootSelector.a()
/*  208 */         .b(a)
/*  209 */         .a(LootValueConstant.a(1))
/*  210 */         .a(LootItem.a(var0)));
/*      */   }
/*      */ 
/*      */   
/*      */   private static LootTable.a c(IMaterial var0) {
/*  215 */     return LootTable.b()
/*  216 */       .a(a(Blocks.FLOWER_POT, LootSelector.a()
/*  217 */           .a(LootValueConstant.a(1))
/*  218 */           .a(LootItem.a(Blocks.FLOWER_POT))))
/*      */       
/*  220 */       .a(a(var0, LootSelector.a()
/*  221 */           .a(LootValueConstant.a(1))
/*  222 */           .a(LootItem.a(var0))));
/*      */   }
/*      */ 
/*      */   
/*      */   private static LootTable.a e(Block var0) {
/*  227 */     return LootTable.b()
/*  228 */       .a(LootSelector.a()
/*  229 */         .a(LootValueConstant.a(1))
/*  230 */         .a(a(var0, (LootItemFunctionUser<LootEntryAbstract.a>)LootItem.a(var0)
/*  231 */             .b((LootItemFunction.a)LootItemFunctionSetCount.a(LootValueConstant.a(2)).b(
/*  232 */                 LootItemConditionBlockStateProperty.a(var0).a(CriterionTriggerProperties.a.a().a(BlockStepAbstract.a, BlockPropertySlabType.DOUBLE)))))));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static <T extends Comparable<T> & INamable> LootTable.a a(Block var0, IBlockState<T> var1, T var2) {
/*  239 */     return LootTable.b()
/*  240 */       .a(a(var0, LootSelector.a()
/*  241 */           .a(LootValueConstant.a(1))
/*  242 */           .a((LootEntryAbstract.a<?>)LootItem.a(var0)
/*  243 */             .b(LootItemConditionBlockStateProperty.a(var0).a(CriterionTriggerProperties.a.a().a(var1, var2))))));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static LootTable.a f(Block var0) {
/*  249 */     return LootTable.b()
/*  250 */       .a(a(var0, LootSelector.a()
/*  251 */           .a(LootValueConstant.a(1))
/*  252 */           .a((LootEntryAbstract.a<?>)LootItem.a(var0)
/*  253 */             .b(LootItemFunctionCopyName.a(LootItemFunctionCopyName.Source.BLOCK_ENTITY)))));
/*      */   }
/*      */ 
/*      */   
/*      */   private static LootTable.a g(Block var0) {
/*  258 */     return LootTable.b()
/*  259 */       .a(a(var0, LootSelector.a()
/*  260 */           .a(LootValueConstant.a(1))
/*  261 */           .a(LootItem.a(var0)
/*  262 */             .b(LootItemFunctionCopyName.a(LootItemFunctionCopyName.Source.BLOCK_ENTITY))
/*  263 */             .b(LootItemFunctionCopyNBT.a(LootItemFunctionCopyNBT.Source.BLOCK_ENTITY)
/*  264 */               .a("Lock", "BlockEntityTag.Lock")
/*  265 */               .a("LootTable", "BlockEntityTag.LootTable")
/*  266 */               .a("LootTableSeed", "BlockEntityTag.LootTableSeed"))
/*      */             
/*  268 */             .b(LootItemFunctionSetContents.c().a(LootSelectorDynamic.a(BlockShulkerBox.b))))));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static LootTable.a h(Block var0) {
/*  274 */     return LootTable.b()
/*  275 */       .a(a(var0, LootSelector.a()
/*  276 */           .a(LootValueConstant.a(1))
/*  277 */           .a(LootItem.a(var0)
/*  278 */             .b(LootItemFunctionCopyName.a(LootItemFunctionCopyName.Source.BLOCK_ENTITY))
/*  279 */             .b(LootItemFunctionCopyNBT.a(LootItemFunctionCopyNBT.Source.BLOCK_ENTITY)
/*  280 */               .a("Patterns", "BlockEntityTag.Patterns")))));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static LootTable.a i(Block var0) {
/*  287 */     return LootTable.b()
/*  288 */       .a(LootSelector.a()
/*  289 */         .b(a)
/*  290 */         .a(LootValueConstant.a(1))
/*  291 */         .a(LootItem.a(var0)
/*  292 */           .b(LootItemFunctionCopyNBT.a(LootItemFunctionCopyNBT.Source.BLOCK_ENTITY)
/*  293 */             .a("Bees", "BlockEntityTag.Bees"))
/*      */           
/*  295 */           .b(LootItemFunctionCopyState.a(var0).a(BlockBeehive.b))));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static LootTable.a j(Block var0) {
/*  301 */     return LootTable.b()
/*  302 */       .a(LootSelector.a()
/*  303 */         .a(LootValueConstant.a(1))
/*  304 */         .a(((LootSelectorEntry.a)LootItem.a(var0)
/*  305 */           .b(a))
/*  306 */           .b(LootItemFunctionCopyNBT.a(LootItemFunctionCopyNBT.Source.BLOCK_ENTITY)
/*  307 */             .a("Bees", "BlockEntityTag.Bees"))
/*      */           
/*  309 */           .b(LootItemFunctionCopyState.a(var0).a(BlockBeehive.b))
/*  310 */           .a(LootItem.a(var0))));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static LootTable.a a(Block var0, Item var1) {
/*  316 */     return a(var0, 
/*  317 */         a(var0, (LootItemFunctionUser<LootEntryAbstract.a>)LootItem.a(var1)
/*  318 */           .b(LootItemFunctionApplyBonus.a(Enchantments.LOOT_BONUS_BLOCKS))));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static LootTable.a c(Block var0, IMaterial var1) {
/*  324 */     return a(var0, a(var0, LootItem.a(var1)
/*  325 */           .b(LootItemFunctionSetCount.a(LootValueBounds.a(-6.0F, 2.0F)))
/*  326 */           .b(LootItemFunctionLimitCount.a(LootIntegerLimit.a(0)))));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static LootTable.a k(Block var0) {
/*  332 */     return b(var0, a(var0, ((LootSelectorEntry.a<LootItemFunctionUser<LootEntryAbstract.a>>)LootItem.a(Items.WHEAT_SEEDS)
/*  333 */           .b(LootItemConditionRandomChance.a(0.125F)))
/*  334 */           .b(LootItemFunctionApplyBonus.a(Enchantments.LOOT_BONUS_BLOCKS, 2))));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static LootTable.a b(Block var0, Item var1) {
/*  340 */     return LootTable.b()
/*  341 */       .a(a(var0, LootSelector.a()
/*  342 */           .a(LootValueConstant.a(1))
/*  343 */           .a(LootItem.a(var1)
/*  344 */             .b((LootItemFunction.a)LootItemFunctionSetCount.a(LootValueBinomial.a(3, 0.06666667F)).b(LootItemConditionBlockStateProperty.a(var0).a(CriterionTriggerProperties.a.a().a(BlockStem.AGE, 0))))
/*  345 */             .b((LootItemFunction.a)LootItemFunctionSetCount.a(LootValueBinomial.a(3, 0.13333334F)).b(LootItemConditionBlockStateProperty.a(var0).a(CriterionTriggerProperties.a.a().a(BlockStem.AGE, 1))))
/*  346 */             .b((LootItemFunction.a)LootItemFunctionSetCount.a(LootValueBinomial.a(3, 0.2F)).b(LootItemConditionBlockStateProperty.a(var0).a(CriterionTriggerProperties.a.a().a(BlockStem.AGE, 2))))
/*  347 */             .b((LootItemFunction.a)LootItemFunctionSetCount.a(LootValueBinomial.a(3, 0.26666668F)).b(LootItemConditionBlockStateProperty.a(var0).a(CriterionTriggerProperties.a.a().a(BlockStem.AGE, 3))))
/*  348 */             .b((LootItemFunction.a)LootItemFunctionSetCount.a(LootValueBinomial.a(3, 0.33333334F)).b(LootItemConditionBlockStateProperty.a(var0).a(CriterionTriggerProperties.a.a().a(BlockStem.AGE, 4))))
/*  349 */             .b((LootItemFunction.a)LootItemFunctionSetCount.a(LootValueBinomial.a(3, 0.4F)).b(LootItemConditionBlockStateProperty.a(var0).a(CriterionTriggerProperties.a.a().a(BlockStem.AGE, 5))))
/*  350 */             .b((LootItemFunction.a)LootItemFunctionSetCount.a(LootValueBinomial.a(3, 0.46666667F)).b(LootItemConditionBlockStateProperty.a(var0).a(CriterionTriggerProperties.a.a().a(BlockStem.AGE, 6))))
/*  351 */             .b((LootItemFunction.a)LootItemFunctionSetCount.a(LootValueBinomial.a(3, 0.53333336F)).b(LootItemConditionBlockStateProperty.a(var0).a(CriterionTriggerProperties.a.a().a(BlockStem.AGE, 7)))))));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static LootTable.a c(Block var0, Item var1) {
/*  357 */     return LootTable.b()
/*  358 */       .a(a(var0, LootSelector.a()
/*  359 */           .a(LootValueConstant.a(1))
/*  360 */           .a((LootEntryAbstract.a<?>)LootItem.a(var1)
/*  361 */             .b(LootItemFunctionSetCount.a(LootValueBinomial.a(3, 0.53333336F))))));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static LootTable.a d(IMaterial var0) {
/*  367 */     return LootTable.b()
/*  368 */       .a(LootSelector.a()
/*  369 */         .a(LootValueConstant.a(1))
/*  370 */         .b(c)
/*  371 */         .a(LootItem.a(var0)));
/*      */   }
/*      */ 
/*      */   
/*      */   private static LootTable.a a(Block var0, Block var1, float... var2) {
/*  376 */     return c(var0, (
/*  377 */         (LootSelectorEntry.a<LootEntryAbstract.a<?>>)a(var0, LootItem.a(var1)))
/*  378 */         .b(LootItemConditionTableBonus.a(Enchantments.LOOT_BONUS_BLOCKS, var2)))
/*      */       
/*  380 */       .a(LootSelector.a()
/*  381 */         .a(LootValueConstant.a(1))
/*  382 */         .b(e)
/*  383 */         .a(((LootSelectorEntry.a<LootEntryAbstract.a<?>>)a(var0, (LootItemFunctionUser<LootSelectorEntry.a<LootEntryAbstract.a<?>>>)LootItem.a(Items.STICK).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 2.0F)))))
/*  384 */           .b(LootItemConditionTableBonus.a(Enchantments.LOOT_BONUS_BLOCKS, new float[] { 0.02F, 0.022222223F, 0.025F, 0.033333335F, 0.1F }))));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static LootTable.a b(Block var0, Block var1, float... var2) {
/*  390 */     return 
/*  391 */       a(var0, var1, var2)
/*  392 */       .a(LootSelector.a()
/*  393 */         .a(LootValueConstant.a(1))
/*  394 */         .b(e)
/*  395 */         .a(((LootSelectorEntry.a<LootEntryAbstract.a<?>>)a(var0, LootItem.a(Items.APPLE)))
/*  396 */           .b(LootItemConditionTableBonus.a(Enchantments.LOOT_BONUS_BLOCKS, new float[] { 0.005F, 0.0055555557F, 0.00625F, 0.008333334F, 0.025F }))));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static LootTable.a a(Block var0, Item var1, Item var2, LootItemCondition.a var3) {
/*  402 */     return a(var0, LootTable.b()
/*  403 */         .a(LootSelector.a()
/*  404 */           .a(((LootSelectorEntry.a)LootItem.a(var1)
/*  405 */             .b(var3))
/*  406 */             .a(LootItem.a(var2))))
/*      */ 
/*      */         
/*  409 */         .a(LootSelector.a()
/*  410 */           .b(var3)
/*  411 */           .a((LootEntryAbstract.a<?>)LootItem.a(var2).b(LootItemFunctionApplyBonus.a(Enchantments.LOOT_BONUS_BLOCKS, 0.5714286F, 3)))));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static LootTable.a l(Block var0) {
/*  417 */     return LootTable.b().a(LootSelector.a()
/*  418 */         .b(c)
/*  419 */         .a((LootEntryAbstract.a<?>)LootItem.a(var0).b(LootItemFunctionSetCount.a(LootValueConstant.a(2)))));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static LootTable.a b(Block var0, Block var1) {
/*  425 */     LootEntryAbstract.a<?> var2 = ((LootSelectorEntry.a)LootItem.a(var1).b(LootItemFunctionSetCount.a(LootValueConstant.a(2))).b(c)).a(((LootSelectorEntry.a<LootEntryAbstract.a<?>>)a(var0, LootItem.a(Items.WHEAT_SEEDS)))
/*  426 */         .b(LootItemConditionRandomChance.a(0.125F)));
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  431 */     return LootTable.b()
/*  432 */       .a(
/*  433 */         LootSelector.a()
/*  434 */         .a(var2)
/*  435 */         .b(LootItemConditionBlockStateProperty.a(var0).a(CriterionTriggerProperties.a.a().a(BlockTallPlant.HALF, BlockPropertyDoubleBlockHalf.LOWER)))
/*  436 */         .b(LootItemConditionLocationCheck.a(CriterionConditionLocation.a.a().a(CriterionConditionBlock.a.a().a(var0).a(CriterionTriggerProperties.a.a().<BlockPropertyDoubleBlockHalf>a(BlockTallPlant.HALF, BlockPropertyDoubleBlockHalf.UPPER).b()).b()), new BlockPosition(0, 1, 0))))
/*      */       
/*  438 */       .a(
/*  439 */         LootSelector.a()
/*  440 */         .a(var2)
/*  441 */         .b(LootItemConditionBlockStateProperty.a(var0).a(CriterionTriggerProperties.a.a().a(BlockTallPlant.HALF, BlockPropertyDoubleBlockHalf.UPPER)))
/*  442 */         .b(LootItemConditionLocationCheck.a(CriterionConditionLocation.a.a().a(CriterionConditionBlock.a.a().a(var0).a(CriterionTriggerProperties.a.a().<BlockPropertyDoubleBlockHalf>a(BlockTallPlant.HALF, BlockPropertyDoubleBlockHalf.LOWER).b()).b()), new BlockPosition(0, -1, 0))));
/*      */   }
/*      */ 
/*      */   
/*      */   public static LootTable.a a() {
/*  447 */     return LootTable.b();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void accept(BiConsumer<MinecraftKey, LootTable.a> var0) {
/*  453 */     d(Blocks.GRANITE);
/*  454 */     d(Blocks.POLISHED_GRANITE);
/*  455 */     d(Blocks.DIORITE);
/*  456 */     d(Blocks.POLISHED_DIORITE);
/*  457 */     d(Blocks.ANDESITE);
/*  458 */     d(Blocks.POLISHED_ANDESITE);
/*  459 */     d(Blocks.DIRT);
/*  460 */     d(Blocks.COARSE_DIRT);
/*  461 */     d(Blocks.COBBLESTONE);
/*  462 */     d(Blocks.OAK_PLANKS);
/*  463 */     d(Blocks.SPRUCE_PLANKS);
/*  464 */     d(Blocks.BIRCH_PLANKS);
/*  465 */     d(Blocks.JUNGLE_PLANKS);
/*  466 */     d(Blocks.ACACIA_PLANKS);
/*  467 */     d(Blocks.DARK_OAK_PLANKS);
/*  468 */     d(Blocks.OAK_SAPLING);
/*  469 */     d(Blocks.SPRUCE_SAPLING);
/*  470 */     d(Blocks.BIRCH_SAPLING);
/*  471 */     d(Blocks.JUNGLE_SAPLING);
/*  472 */     d(Blocks.ACACIA_SAPLING);
/*  473 */     d(Blocks.DARK_OAK_SAPLING);
/*  474 */     d(Blocks.SAND);
/*  475 */     d(Blocks.RED_SAND);
/*  476 */     d(Blocks.GOLD_ORE);
/*  477 */     d(Blocks.IRON_ORE);
/*  478 */     d(Blocks.OAK_LOG);
/*  479 */     d(Blocks.SPRUCE_LOG);
/*  480 */     d(Blocks.BIRCH_LOG);
/*  481 */     d(Blocks.JUNGLE_LOG);
/*  482 */     d(Blocks.ACACIA_LOG);
/*  483 */     d(Blocks.DARK_OAK_LOG);
/*  484 */     d(Blocks.STRIPPED_SPRUCE_LOG);
/*  485 */     d(Blocks.STRIPPED_BIRCH_LOG);
/*  486 */     d(Blocks.STRIPPED_JUNGLE_LOG);
/*  487 */     d(Blocks.STRIPPED_ACACIA_LOG);
/*  488 */     d(Blocks.STRIPPED_DARK_OAK_LOG);
/*  489 */     d(Blocks.STRIPPED_OAK_LOG);
/*  490 */     d(Blocks.STRIPPED_WARPED_STEM);
/*  491 */     d(Blocks.STRIPPED_CRIMSON_STEM);
/*  492 */     d(Blocks.OAK_WOOD);
/*  493 */     d(Blocks.SPRUCE_WOOD);
/*  494 */     d(Blocks.BIRCH_WOOD);
/*  495 */     d(Blocks.JUNGLE_WOOD);
/*  496 */     d(Blocks.ACACIA_WOOD);
/*  497 */     d(Blocks.DARK_OAK_WOOD);
/*  498 */     d(Blocks.STRIPPED_OAK_WOOD);
/*  499 */     d(Blocks.STRIPPED_SPRUCE_WOOD);
/*  500 */     d(Blocks.STRIPPED_BIRCH_WOOD);
/*  501 */     d(Blocks.STRIPPED_JUNGLE_WOOD);
/*  502 */     d(Blocks.STRIPPED_ACACIA_WOOD);
/*  503 */     d(Blocks.STRIPPED_DARK_OAK_WOOD);
/*  504 */     d(Blocks.STRIPPED_CRIMSON_HYPHAE);
/*  505 */     d(Blocks.STRIPPED_WARPED_HYPHAE);
/*  506 */     d(Blocks.SPONGE);
/*  507 */     d(Blocks.WET_SPONGE);
/*  508 */     d(Blocks.LAPIS_BLOCK);
/*  509 */     d(Blocks.SANDSTONE);
/*  510 */     d(Blocks.CHISELED_SANDSTONE);
/*  511 */     d(Blocks.CUT_SANDSTONE);
/*  512 */     d(Blocks.NOTE_BLOCK);
/*  513 */     d(Blocks.POWERED_RAIL);
/*  514 */     d(Blocks.DETECTOR_RAIL);
/*  515 */     d(Blocks.STICKY_PISTON);
/*  516 */     d(Blocks.PISTON);
/*  517 */     d(Blocks.WHITE_WOOL);
/*  518 */     d(Blocks.ORANGE_WOOL);
/*  519 */     d(Blocks.MAGENTA_WOOL);
/*  520 */     d(Blocks.LIGHT_BLUE_WOOL);
/*  521 */     d(Blocks.YELLOW_WOOL);
/*  522 */     d(Blocks.LIME_WOOL);
/*  523 */     d(Blocks.PINK_WOOL);
/*  524 */     d(Blocks.GRAY_WOOL);
/*  525 */     d(Blocks.LIGHT_GRAY_WOOL);
/*  526 */     d(Blocks.CYAN_WOOL);
/*  527 */     d(Blocks.PURPLE_WOOL);
/*  528 */     d(Blocks.BLUE_WOOL);
/*  529 */     d(Blocks.BROWN_WOOL);
/*  530 */     d(Blocks.GREEN_WOOL);
/*  531 */     d(Blocks.RED_WOOL);
/*  532 */     d(Blocks.BLACK_WOOL);
/*  533 */     d(Blocks.DANDELION);
/*  534 */     d(Blocks.POPPY);
/*  535 */     d(Blocks.BLUE_ORCHID);
/*  536 */     d(Blocks.ALLIUM);
/*  537 */     d(Blocks.AZURE_BLUET);
/*  538 */     d(Blocks.RED_TULIP);
/*  539 */     d(Blocks.ORANGE_TULIP);
/*  540 */     d(Blocks.WHITE_TULIP);
/*  541 */     d(Blocks.PINK_TULIP);
/*  542 */     d(Blocks.OXEYE_DAISY);
/*  543 */     d(Blocks.CORNFLOWER);
/*  544 */     d(Blocks.WITHER_ROSE);
/*  545 */     d(Blocks.LILY_OF_THE_VALLEY);
/*  546 */     d(Blocks.BROWN_MUSHROOM);
/*  547 */     d(Blocks.RED_MUSHROOM);
/*  548 */     d(Blocks.GOLD_BLOCK);
/*  549 */     d(Blocks.IRON_BLOCK);
/*  550 */     d(Blocks.BRICKS);
/*  551 */     d(Blocks.MOSSY_COBBLESTONE);
/*  552 */     d(Blocks.OBSIDIAN);
/*  553 */     d(Blocks.CRYING_OBSIDIAN);
/*  554 */     d(Blocks.TORCH);
/*  555 */     d(Blocks.OAK_STAIRS);
/*  556 */     d(Blocks.REDSTONE_WIRE);
/*  557 */     d(Blocks.DIAMOND_BLOCK);
/*  558 */     d(Blocks.CRAFTING_TABLE);
/*  559 */     d(Blocks.OAK_SIGN);
/*  560 */     d(Blocks.SPRUCE_SIGN);
/*  561 */     d(Blocks.BIRCH_SIGN);
/*  562 */     d(Blocks.ACACIA_SIGN);
/*  563 */     d(Blocks.JUNGLE_SIGN);
/*  564 */     d(Blocks.DARK_OAK_SIGN);
/*  565 */     d(Blocks.LADDER);
/*  566 */     d(Blocks.RAIL);
/*  567 */     d(Blocks.COBBLESTONE_STAIRS);
/*  568 */     d(Blocks.LEVER);
/*  569 */     d(Blocks.STONE_PRESSURE_PLATE);
/*  570 */     d(Blocks.OAK_PRESSURE_PLATE);
/*  571 */     d(Blocks.SPRUCE_PRESSURE_PLATE);
/*  572 */     d(Blocks.BIRCH_PRESSURE_PLATE);
/*  573 */     d(Blocks.JUNGLE_PRESSURE_PLATE);
/*  574 */     d(Blocks.ACACIA_PRESSURE_PLATE);
/*  575 */     d(Blocks.DARK_OAK_PRESSURE_PLATE);
/*  576 */     d(Blocks.REDSTONE_TORCH);
/*  577 */     d(Blocks.STONE_BUTTON);
/*  578 */     d(Blocks.CACTUS);
/*  579 */     d(Blocks.SUGAR_CANE);
/*  580 */     d(Blocks.JUKEBOX);
/*  581 */     d(Blocks.OAK_FENCE);
/*  582 */     d(Blocks.PUMPKIN);
/*  583 */     d(Blocks.NETHERRACK);
/*  584 */     d(Blocks.SOUL_SAND);
/*  585 */     d(Blocks.SOUL_SOIL);
/*  586 */     d(Blocks.BASALT);
/*  587 */     d(Blocks.cP);
/*  588 */     d(Blocks.SOUL_TORCH);
/*  589 */     d(Blocks.CARVED_PUMPKIN);
/*  590 */     d(Blocks.JACK_O_LANTERN);
/*  591 */     d(Blocks.REPEATER);
/*  592 */     d(Blocks.OAK_TRAPDOOR);
/*  593 */     d(Blocks.SPRUCE_TRAPDOOR);
/*  594 */     d(Blocks.BIRCH_TRAPDOOR);
/*  595 */     d(Blocks.JUNGLE_TRAPDOOR);
/*  596 */     d(Blocks.ACACIA_TRAPDOOR);
/*  597 */     d(Blocks.DARK_OAK_TRAPDOOR);
/*  598 */     d(Blocks.STONE_BRICKS);
/*  599 */     d(Blocks.MOSSY_STONE_BRICKS);
/*  600 */     d(Blocks.CRACKED_STONE_BRICKS);
/*  601 */     d(Blocks.CHISELED_STONE_BRICKS);
/*  602 */     d(Blocks.IRON_BARS);
/*  603 */     d(Blocks.OAK_FENCE_GATE);
/*  604 */     d(Blocks.BRICK_STAIRS);
/*  605 */     d(Blocks.STONE_BRICK_STAIRS);
/*  606 */     d(Blocks.LILY_PAD);
/*  607 */     d(Blocks.NETHER_BRICKS);
/*  608 */     d(Blocks.NETHER_BRICK_FENCE);
/*  609 */     d(Blocks.NETHER_BRICK_STAIRS);
/*  610 */     d(Blocks.CAULDRON);
/*  611 */     d(Blocks.END_STONE);
/*  612 */     d(Blocks.REDSTONE_LAMP);
/*  613 */     d(Blocks.SANDSTONE_STAIRS);
/*  614 */     d(Blocks.TRIPWIRE_HOOK);
/*  615 */     d(Blocks.EMERALD_BLOCK);
/*  616 */     d(Blocks.SPRUCE_STAIRS);
/*  617 */     d(Blocks.BIRCH_STAIRS);
/*  618 */     d(Blocks.JUNGLE_STAIRS);
/*  619 */     d(Blocks.COBBLESTONE_WALL);
/*  620 */     d(Blocks.MOSSY_COBBLESTONE_WALL);
/*  621 */     d(Blocks.FLOWER_POT);
/*  622 */     d(Blocks.OAK_BUTTON);
/*  623 */     d(Blocks.SPRUCE_BUTTON);
/*  624 */     d(Blocks.BIRCH_BUTTON);
/*  625 */     d(Blocks.JUNGLE_BUTTON);
/*  626 */     d(Blocks.ACACIA_BUTTON);
/*  627 */     d(Blocks.DARK_OAK_BUTTON);
/*  628 */     d(Blocks.SKELETON_SKULL);
/*  629 */     d(Blocks.WITHER_SKELETON_SKULL);
/*  630 */     d(Blocks.ZOMBIE_HEAD);
/*  631 */     d(Blocks.CREEPER_HEAD);
/*  632 */     d(Blocks.DRAGON_HEAD);
/*  633 */     d(Blocks.ANVIL);
/*  634 */     d(Blocks.CHIPPED_ANVIL);
/*  635 */     d(Blocks.DAMAGED_ANVIL);
/*  636 */     d(Blocks.LIGHT_WEIGHTED_PRESSURE_PLATE);
/*  637 */     d(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE);
/*  638 */     d(Blocks.COMPARATOR);
/*  639 */     d(Blocks.DAYLIGHT_DETECTOR);
/*  640 */     d(Blocks.REDSTONE_BLOCK);
/*  641 */     d(Blocks.QUARTZ_BLOCK);
/*  642 */     d(Blocks.CHISELED_QUARTZ_BLOCK);
/*  643 */     d(Blocks.QUARTZ_PILLAR);
/*  644 */     d(Blocks.QUARTZ_STAIRS);
/*  645 */     d(Blocks.ACTIVATOR_RAIL);
/*  646 */     d(Blocks.WHITE_TERRACOTTA);
/*  647 */     d(Blocks.ORANGE_TERRACOTTA);
/*  648 */     d(Blocks.MAGENTA_TERRACOTTA);
/*  649 */     d(Blocks.LIGHT_BLUE_TERRACOTTA);
/*  650 */     d(Blocks.YELLOW_TERRACOTTA);
/*  651 */     d(Blocks.LIME_TERRACOTTA);
/*  652 */     d(Blocks.PINK_TERRACOTTA);
/*  653 */     d(Blocks.GRAY_TERRACOTTA);
/*  654 */     d(Blocks.LIGHT_GRAY_TERRACOTTA);
/*  655 */     d(Blocks.CYAN_TERRACOTTA);
/*  656 */     d(Blocks.PURPLE_TERRACOTTA);
/*  657 */     d(Blocks.BLUE_TERRACOTTA);
/*  658 */     d(Blocks.BROWN_TERRACOTTA);
/*  659 */     d(Blocks.GREEN_TERRACOTTA);
/*  660 */     d(Blocks.RED_TERRACOTTA);
/*  661 */     d(Blocks.BLACK_TERRACOTTA);
/*  662 */     d(Blocks.ACACIA_STAIRS);
/*  663 */     d(Blocks.DARK_OAK_STAIRS);
/*  664 */     d(Blocks.SLIME_BLOCK);
/*  665 */     d(Blocks.IRON_TRAPDOOR);
/*  666 */     d(Blocks.PRISMARINE);
/*  667 */     d(Blocks.PRISMARINE_BRICKS);
/*  668 */     d(Blocks.DARK_PRISMARINE);
/*  669 */     d(Blocks.PRISMARINE_STAIRS);
/*  670 */     d(Blocks.PRISMARINE_BRICK_STAIRS);
/*  671 */     d(Blocks.DARK_PRISMARINE_STAIRS);
/*  672 */     d(Blocks.HAY_BLOCK);
/*  673 */     d(Blocks.WHITE_CARPET);
/*  674 */     d(Blocks.ORANGE_CARPET);
/*  675 */     d(Blocks.MAGENTA_CARPET);
/*  676 */     d(Blocks.LIGHT_BLUE_CARPET);
/*  677 */     d(Blocks.YELLOW_CARPET);
/*  678 */     d(Blocks.LIME_CARPET);
/*  679 */     d(Blocks.PINK_CARPET);
/*  680 */     d(Blocks.GRAY_CARPET);
/*  681 */     d(Blocks.LIGHT_GRAY_CARPET);
/*  682 */     d(Blocks.CYAN_CARPET);
/*  683 */     d(Blocks.PURPLE_CARPET);
/*  684 */     d(Blocks.BLUE_CARPET);
/*  685 */     d(Blocks.BROWN_CARPET);
/*  686 */     d(Blocks.GREEN_CARPET);
/*  687 */     d(Blocks.RED_CARPET);
/*  688 */     d(Blocks.BLACK_CARPET);
/*  689 */     d(Blocks.TERRACOTTA);
/*  690 */     d(Blocks.COAL_BLOCK);
/*  691 */     d(Blocks.RED_SANDSTONE);
/*  692 */     d(Blocks.CHISELED_RED_SANDSTONE);
/*  693 */     d(Blocks.CUT_RED_SANDSTONE);
/*  694 */     d(Blocks.RED_SANDSTONE_STAIRS);
/*  695 */     d(Blocks.SMOOTH_STONE);
/*  696 */     d(Blocks.SMOOTH_SANDSTONE);
/*  697 */     d(Blocks.SMOOTH_QUARTZ);
/*  698 */     d(Blocks.SMOOTH_RED_SANDSTONE);
/*  699 */     d(Blocks.SPRUCE_FENCE_GATE);
/*  700 */     d(Blocks.BIRCH_FENCE_GATE);
/*  701 */     d(Blocks.JUNGLE_FENCE_GATE);
/*  702 */     d(Blocks.ACACIA_FENCE_GATE);
/*  703 */     d(Blocks.DARK_OAK_FENCE_GATE);
/*  704 */     d(Blocks.SPRUCE_FENCE);
/*  705 */     d(Blocks.BIRCH_FENCE);
/*  706 */     d(Blocks.JUNGLE_FENCE);
/*  707 */     d(Blocks.ACACIA_FENCE);
/*  708 */     d(Blocks.DARK_OAK_FENCE);
/*  709 */     d(Blocks.END_ROD);
/*  710 */     d(Blocks.PURPUR_BLOCK);
/*  711 */     d(Blocks.PURPUR_PILLAR);
/*  712 */     d(Blocks.PURPUR_STAIRS);
/*  713 */     d(Blocks.END_STONE_BRICKS);
/*  714 */     d(Blocks.MAGMA_BLOCK);
/*  715 */     d(Blocks.NETHER_WART_BLOCK);
/*  716 */     d(Blocks.RED_NETHER_BRICKS);
/*  717 */     d(Blocks.BONE_BLOCK);
/*  718 */     d(Blocks.OBSERVER);
/*  719 */     d(Blocks.TARGET);
/*  720 */     d(Blocks.WHITE_GLAZED_TERRACOTTA);
/*  721 */     d(Blocks.ORANGE_GLAZED_TERRACOTTA);
/*  722 */     d(Blocks.MAGENTA_GLAZED_TERRACOTTA);
/*  723 */     d(Blocks.LIGHT_BLUE_GLAZED_TERRACOTTA);
/*  724 */     d(Blocks.YELLOW_GLAZED_TERRACOTTA);
/*  725 */     d(Blocks.LIME_GLAZED_TERRACOTTA);
/*  726 */     d(Blocks.PINK_GLAZED_TERRACOTTA);
/*  727 */     d(Blocks.GRAY_GLAZED_TERRACOTTA);
/*  728 */     d(Blocks.LIGHT_GRAY_GLAZED_TERRACOTTA);
/*  729 */     d(Blocks.CYAN_GLAZED_TERRACOTTA);
/*  730 */     d(Blocks.PURPLE_GLAZED_TERRACOTTA);
/*  731 */     d(Blocks.BLUE_GLAZED_TERRACOTTA);
/*  732 */     d(Blocks.BROWN_GLAZED_TERRACOTTA);
/*  733 */     d(Blocks.GREEN_GLAZED_TERRACOTTA);
/*  734 */     d(Blocks.RED_GLAZED_TERRACOTTA);
/*  735 */     d(Blocks.BLACK_GLAZED_TERRACOTTA);
/*  736 */     d(Blocks.WHITE_CONCRETE);
/*  737 */     d(Blocks.ORANGE_CONCRETE);
/*  738 */     d(Blocks.MAGENTA_CONCRETE);
/*  739 */     d(Blocks.LIGHT_BLUE_CONCRETE);
/*  740 */     d(Blocks.YELLOW_CONCRETE);
/*  741 */     d(Blocks.LIME_CONCRETE);
/*  742 */     d(Blocks.PINK_CONCRETE);
/*  743 */     d(Blocks.GRAY_CONCRETE);
/*  744 */     d(Blocks.LIGHT_GRAY_CONCRETE);
/*  745 */     d(Blocks.CYAN_CONCRETE);
/*  746 */     d(Blocks.PURPLE_CONCRETE);
/*  747 */     d(Blocks.BLUE_CONCRETE);
/*  748 */     d(Blocks.BROWN_CONCRETE);
/*  749 */     d(Blocks.GREEN_CONCRETE);
/*  750 */     d(Blocks.RED_CONCRETE);
/*  751 */     d(Blocks.BLACK_CONCRETE);
/*  752 */     d(Blocks.WHITE_CONCRETE_POWDER);
/*  753 */     d(Blocks.ORANGE_CONCRETE_POWDER);
/*  754 */     d(Blocks.MAGENTA_CONCRETE_POWDER);
/*  755 */     d(Blocks.LIGHT_BLUE_CONCRETE_POWDER);
/*  756 */     d(Blocks.YELLOW_CONCRETE_POWDER);
/*  757 */     d(Blocks.LIME_CONCRETE_POWDER);
/*  758 */     d(Blocks.PINK_CONCRETE_POWDER);
/*  759 */     d(Blocks.GRAY_CONCRETE_POWDER);
/*  760 */     d(Blocks.LIGHT_GRAY_CONCRETE_POWDER);
/*  761 */     d(Blocks.CYAN_CONCRETE_POWDER);
/*  762 */     d(Blocks.PURPLE_CONCRETE_POWDER);
/*  763 */     d(Blocks.BLUE_CONCRETE_POWDER);
/*  764 */     d(Blocks.BROWN_CONCRETE_POWDER);
/*  765 */     d(Blocks.GREEN_CONCRETE_POWDER);
/*  766 */     d(Blocks.RED_CONCRETE_POWDER);
/*  767 */     d(Blocks.BLACK_CONCRETE_POWDER);
/*  768 */     d(Blocks.KELP);
/*  769 */     d(Blocks.DRIED_KELP_BLOCK);
/*  770 */     d(Blocks.DEAD_TUBE_CORAL_BLOCK);
/*  771 */     d(Blocks.DEAD_BRAIN_CORAL_BLOCK);
/*  772 */     d(Blocks.DEAD_BUBBLE_CORAL_BLOCK);
/*  773 */     d(Blocks.DEAD_FIRE_CORAL_BLOCK);
/*  774 */     d(Blocks.DEAD_HORN_CORAL_BLOCK);
/*  775 */     d(Blocks.CONDUIT);
/*  776 */     d(Blocks.DRAGON_EGG);
/*  777 */     d(Blocks.BAMBOO);
/*  778 */     d(Blocks.POLISHED_GRANITE_STAIRS);
/*  779 */     d(Blocks.SMOOTH_RED_SANDSTONE_STAIRS);
/*  780 */     d(Blocks.MOSSY_STONE_BRICK_STAIRS);
/*  781 */     d(Blocks.POLISHED_DIORITE_STAIRS);
/*  782 */     d(Blocks.MOSSY_COBBLESTONE_STAIRS);
/*  783 */     d(Blocks.END_STONE_BRICK_STAIRS);
/*  784 */     d(Blocks.STONE_STAIRS);
/*  785 */     d(Blocks.SMOOTH_SANDSTONE_STAIRS);
/*  786 */     d(Blocks.SMOOTH_QUARTZ_STAIRS);
/*  787 */     d(Blocks.GRANITE_STAIRS);
/*  788 */     d(Blocks.ANDESITE_STAIRS);
/*  789 */     d(Blocks.RED_NETHER_BRICK_STAIRS);
/*  790 */     d(Blocks.POLISHED_ANDESITE_STAIRS);
/*  791 */     d(Blocks.DIORITE_STAIRS);
/*  792 */     d(Blocks.BRICK_WALL);
/*  793 */     d(Blocks.PRISMARINE_WALL);
/*  794 */     d(Blocks.RED_SANDSTONE_WALL);
/*  795 */     d(Blocks.MOSSY_STONE_BRICK_WALL);
/*  796 */     d(Blocks.GRANITE_WALL);
/*  797 */     d(Blocks.STONE_BRICK_WALL);
/*  798 */     d(Blocks.NETHER_BRICK_WALL);
/*  799 */     d(Blocks.ANDESITE_WALL);
/*  800 */     d(Blocks.RED_NETHER_BRICK_WALL);
/*  801 */     d(Blocks.SANDSTONE_WALL);
/*  802 */     d(Blocks.END_STONE_BRICK_WALL);
/*  803 */     d(Blocks.DIORITE_WALL);
/*  804 */     d(Blocks.LOOM);
/*  805 */     d(Blocks.SCAFFOLDING);
/*  806 */     d(Blocks.HONEY_BLOCK);
/*  807 */     d(Blocks.HONEYCOMB_BLOCK);
/*  808 */     d(Blocks.RESPAWN_ANCHOR);
/*  809 */     d(Blocks.LODESTONE);
/*  810 */     d(Blocks.WARPED_STEM);
/*  811 */     d(Blocks.WARPED_HYPHAE);
/*  812 */     d(Blocks.WARPED_FUNGUS);
/*  813 */     d(Blocks.WARPED_WART_BLOCK);
/*  814 */     d(Blocks.CRIMSON_STEM);
/*  815 */     d(Blocks.CRIMSON_HYPHAE);
/*  816 */     d(Blocks.CRIMSON_FUNGUS);
/*  817 */     d(Blocks.SHROOMLIGHT);
/*  818 */     d(Blocks.CRIMSON_PLANKS);
/*  819 */     d(Blocks.WARPED_PLANKS);
/*  820 */     d(Blocks.WARPED_PRESSURE_PLATE);
/*  821 */     d(Blocks.WARPED_FENCE);
/*  822 */     d(Blocks.WARPED_TRAPDOOR);
/*  823 */     d(Blocks.WARPED_FENCE_GATE);
/*  824 */     d(Blocks.WARPED_STAIRS);
/*  825 */     d(Blocks.WARPED_BUTTON);
/*  826 */     d(Blocks.WARPED_SIGN);
/*  827 */     d(Blocks.CRIMSON_PRESSURE_PLATE);
/*  828 */     d(Blocks.CRIMSON_FENCE);
/*  829 */     d(Blocks.CRIMSON_TRAPDOOR);
/*  830 */     d(Blocks.CRIMSON_FENCE_GATE);
/*  831 */     d(Blocks.CRIMSON_STAIRS);
/*  832 */     d(Blocks.CRIMSON_BUTTON);
/*  833 */     d(Blocks.CRIMSON_SIGN);
/*  834 */     d(Blocks.NETHERITE_BLOCK);
/*  835 */     d(Blocks.ANCIENT_DEBRIS);
/*  836 */     d(Blocks.BLACKSTONE);
/*  837 */     d(Blocks.POLISHED_BLACKSTONE_BRICKS);
/*  838 */     d(Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS);
/*  839 */     d(Blocks.BLACKSTONE_STAIRS);
/*  840 */     d(Blocks.BLACKSTONE_WALL);
/*  841 */     d(Blocks.POLISHED_BLACKSTONE_BRICK_WALL);
/*  842 */     d(Blocks.CHISELED_POLISHED_BLACKSTONE);
/*  843 */     d(Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS);
/*  844 */     d(Blocks.POLISHED_BLACKSTONE);
/*  845 */     d(Blocks.POLISHED_BLACKSTONE_STAIRS);
/*  846 */     d(Blocks.POLISHED_BLACKSTONE_PRESSURE_PLATE);
/*  847 */     d(Blocks.POLISHED_BLACKSTONE_BUTTON);
/*  848 */     d(Blocks.POLISHED_BLACKSTONE_WALL);
/*  849 */     d(Blocks.CHISELED_NETHER_BRICKS);
/*  850 */     d(Blocks.CRACKED_NETHER_BRICKS);
/*  851 */     d(Blocks.QUARTZ_BRICKS);
/*  852 */     d(Blocks.CHAIN);
/*  853 */     d(Blocks.WARPED_ROOTS);
/*  854 */     d(Blocks.CRIMSON_ROOTS);
/*      */ 
/*      */     
/*  857 */     a(Blocks.FARMLAND, Blocks.DIRT);
/*  858 */     a(Blocks.TRIPWIRE, Items.STRING);
/*  859 */     a(Blocks.GRASS_PATH, Blocks.DIRT);
/*  860 */     a(Blocks.KELP_PLANT, Blocks.KELP);
/*  861 */     a(Blocks.BAMBOO_SAPLING, Blocks.BAMBOO);
/*      */ 
/*      */     
/*  864 */     a(Blocks.STONE, var0 -> b(var0, Blocks.COBBLESTONE));
/*  865 */     a(Blocks.GRASS_BLOCK, var0 -> b(var0, Blocks.DIRT));
/*  866 */     a(Blocks.PODZOL, var0 -> b(var0, Blocks.DIRT));
/*  867 */     a(Blocks.MYCELIUM, var0 -> b(var0, Blocks.DIRT));
/*  868 */     a(Blocks.TUBE_CORAL_BLOCK, var0 -> b(var0, Blocks.DEAD_TUBE_CORAL_BLOCK));
/*  869 */     a(Blocks.BRAIN_CORAL_BLOCK, var0 -> b(var0, Blocks.DEAD_BRAIN_CORAL_BLOCK));
/*  870 */     a(Blocks.BUBBLE_CORAL_BLOCK, var0 -> b(var0, Blocks.DEAD_BUBBLE_CORAL_BLOCK));
/*  871 */     a(Blocks.FIRE_CORAL_BLOCK, var0 -> b(var0, Blocks.DEAD_FIRE_CORAL_BLOCK));
/*  872 */     a(Blocks.HORN_CORAL_BLOCK, var0 -> b(var0, Blocks.DEAD_HORN_CORAL_BLOCK));
/*  873 */     a(Blocks.CRIMSON_NYLIUM, var0 -> b(var0, Blocks.NETHERRACK));
/*  874 */     a(Blocks.WARPED_NYLIUM, var0 -> b(var0, Blocks.NETHERRACK));
/*      */ 
/*      */     
/*  877 */     a(Blocks.BOOKSHELF, var0 -> a(var0, Items.BOOK, LootValueConstant.a(3)));
/*  878 */     a(Blocks.CLAY, var0 -> a(var0, Items.CLAY_BALL, LootValueConstant.a(4)));
/*  879 */     a(Blocks.ENDER_CHEST, var0 -> a(var0, Blocks.OBSIDIAN, LootValueConstant.a(8)));
/*  880 */     a(Blocks.SNOW_BLOCK, var0 -> a(var0, Items.SNOWBALL, LootValueConstant.a(4)));
/*      */     
/*  882 */     a(Blocks.CHORUS_PLANT, a(Items.CHORUS_FRUIT, LootValueBounds.a(0.0F, 1.0F)));
/*      */ 
/*      */     
/*  885 */     b(Blocks.POTTED_OAK_SAPLING);
/*  886 */     b(Blocks.POTTED_SPRUCE_SAPLING);
/*  887 */     b(Blocks.POTTED_BIRCH_SAPLING);
/*  888 */     b(Blocks.POTTED_JUNGLE_SAPLING);
/*  889 */     b(Blocks.POTTED_ACACIA_SAPLING);
/*  890 */     b(Blocks.POTTED_DARK_OAK_SAPLING);
/*  891 */     b(Blocks.POTTED_FERN);
/*  892 */     b(Blocks.POTTED_DANDELION);
/*  893 */     b(Blocks.POTTED_POPPY);
/*  894 */     b(Blocks.POTTED_BLUE_ORCHID);
/*  895 */     b(Blocks.POTTED_ALLIUM);
/*  896 */     b(Blocks.POTTED_AZURE_BLUET);
/*  897 */     b(Blocks.POTTED_RED_TULIP);
/*  898 */     b(Blocks.POTTED_ORANGE_TULIP);
/*  899 */     b(Blocks.POTTED_WHITE_TULIP);
/*  900 */     b(Blocks.POTTED_PINK_TULIP);
/*  901 */     b(Blocks.POTTED_OXEYE_DAISY);
/*  902 */     b(Blocks.POTTED_CORNFLOWER);
/*  903 */     b(Blocks.POTTED_LILY_OF_THE_VALLEY);
/*  904 */     b(Blocks.POTTED_WITHER_ROSE);
/*  905 */     b(Blocks.POTTED_RED_MUSHROOM);
/*  906 */     b(Blocks.POTTED_BROWN_MUSHROOM);
/*  907 */     b(Blocks.POTTED_DEAD_BUSH);
/*  908 */     b(Blocks.POTTED_CACTUS);
/*  909 */     b(Blocks.POTTED_BAMBOO);
/*  910 */     b(Blocks.POTTED_CRIMSON_FUNGUS);
/*  911 */     b(Blocks.POTTED_WARPED_FUNGUS);
/*  912 */     b(Blocks.POTTED_CRIMSON_ROOTS);
/*  913 */     b(Blocks.POTTED_WARPED_ROOTS);
/*      */ 
/*      */     
/*  916 */     a(Blocks.ACACIA_SLAB, hz::e);
/*  917 */     a(Blocks.BIRCH_SLAB, hz::e);
/*  918 */     a(Blocks.BRICK_SLAB, hz::e);
/*  919 */     a(Blocks.COBBLESTONE_SLAB, hz::e);
/*  920 */     a(Blocks.DARK_OAK_SLAB, hz::e);
/*  921 */     a(Blocks.DARK_PRISMARINE_SLAB, hz::e);
/*  922 */     a(Blocks.JUNGLE_SLAB, hz::e);
/*  923 */     a(Blocks.NETHER_BRICK_SLAB, hz::e);
/*  924 */     a(Blocks.OAK_SLAB, hz::e);
/*  925 */     a(Blocks.PETRIFIED_OAK_SLAB, hz::e);
/*  926 */     a(Blocks.PRISMARINE_BRICK_SLAB, hz::e);
/*  927 */     a(Blocks.PRISMARINE_SLAB, hz::e);
/*  928 */     a(Blocks.PURPUR_SLAB, hz::e);
/*  929 */     a(Blocks.QUARTZ_SLAB, hz::e);
/*  930 */     a(Blocks.RED_SANDSTONE_SLAB, hz::e);
/*  931 */     a(Blocks.SANDSTONE_SLAB, hz::e);
/*  932 */     a(Blocks.CUT_RED_SANDSTONE_SLAB, hz::e);
/*  933 */     a(Blocks.CUT_SANDSTONE_SLAB, hz::e);
/*  934 */     a(Blocks.SPRUCE_SLAB, hz::e);
/*  935 */     a(Blocks.STONE_BRICK_SLAB, hz::e);
/*  936 */     a(Blocks.STONE_SLAB, hz::e);
/*  937 */     a(Blocks.SMOOTH_STONE_SLAB, hz::e);
/*  938 */     a(Blocks.POLISHED_GRANITE_SLAB, hz::e);
/*  939 */     a(Blocks.SMOOTH_RED_SANDSTONE_SLAB, hz::e);
/*  940 */     a(Blocks.MOSSY_STONE_BRICK_SLAB, hz::e);
/*  941 */     a(Blocks.POLISHED_DIORITE_SLAB, hz::e);
/*  942 */     a(Blocks.MOSSY_COBBLESTONE_SLAB, hz::e);
/*  943 */     a(Blocks.END_STONE_BRICK_SLAB, hz::e);
/*  944 */     a(Blocks.SMOOTH_SANDSTONE_SLAB, hz::e);
/*  945 */     a(Blocks.SMOOTH_QUARTZ_SLAB, hz::e);
/*  946 */     a(Blocks.GRANITE_SLAB, hz::e);
/*  947 */     a(Blocks.ANDESITE_SLAB, hz::e);
/*  948 */     a(Blocks.RED_NETHER_BRICK_SLAB, hz::e);
/*  949 */     a(Blocks.POLISHED_ANDESITE_SLAB, hz::e);
/*  950 */     a(Blocks.DIORITE_SLAB, hz::e);
/*  951 */     a(Blocks.CRIMSON_SLAB, hz::e);
/*  952 */     a(Blocks.WARPED_SLAB, hz::e);
/*  953 */     a(Blocks.BLACKSTONE_SLAB, hz::e);
/*  954 */     a(Blocks.POLISHED_BLACKSTONE_BRICK_SLAB, hz::e);
/*  955 */     a(Blocks.POLISHED_BLACKSTONE_SLAB, hz::e);
/*      */ 
/*      */     
/*  958 */     a(Blocks.ACACIA_DOOR, hz::a);
/*  959 */     a(Blocks.BIRCH_DOOR, hz::a);
/*  960 */     a(Blocks.DARK_OAK_DOOR, hz::a);
/*  961 */     a(Blocks.IRON_DOOR, hz::a);
/*  962 */     a(Blocks.JUNGLE_DOOR, hz::a);
/*  963 */     a(Blocks.OAK_DOOR, hz::a);
/*  964 */     a(Blocks.SPRUCE_DOOR, hz::a);
/*  965 */     a(Blocks.WARPED_DOOR, hz::a);
/*  966 */     a(Blocks.CRIMSON_DOOR, hz::a);
/*      */ 
/*      */     
/*  969 */     a(Blocks.BLACK_BED, var0 -> a(var0, BlockBed.PART, BlockPropertyBedPart.HEAD));
/*  970 */     a(Blocks.BLUE_BED, var0 -> a(var0, BlockBed.PART, BlockPropertyBedPart.HEAD));
/*  971 */     a(Blocks.BROWN_BED, var0 -> a(var0, BlockBed.PART, BlockPropertyBedPart.HEAD));
/*  972 */     a(Blocks.CYAN_BED, var0 -> a(var0, BlockBed.PART, BlockPropertyBedPart.HEAD));
/*  973 */     a(Blocks.GRAY_BED, var0 -> a(var0, BlockBed.PART, BlockPropertyBedPart.HEAD));
/*  974 */     a(Blocks.GREEN_BED, var0 -> a(var0, BlockBed.PART, BlockPropertyBedPart.HEAD));
/*  975 */     a(Blocks.LIGHT_BLUE_BED, var0 -> a(var0, BlockBed.PART, BlockPropertyBedPart.HEAD));
/*  976 */     a(Blocks.LIGHT_GRAY_BED, var0 -> a(var0, BlockBed.PART, BlockPropertyBedPart.HEAD));
/*  977 */     a(Blocks.LIME_BED, var0 -> a(var0, BlockBed.PART, BlockPropertyBedPart.HEAD));
/*  978 */     a(Blocks.MAGENTA_BED, var0 -> a(var0, BlockBed.PART, BlockPropertyBedPart.HEAD));
/*  979 */     a(Blocks.PURPLE_BED, var0 -> a(var0, BlockBed.PART, BlockPropertyBedPart.HEAD));
/*  980 */     a(Blocks.ORANGE_BED, var0 -> a(var0, BlockBed.PART, BlockPropertyBedPart.HEAD));
/*  981 */     a(Blocks.PINK_BED, var0 -> a(var0, BlockBed.PART, BlockPropertyBedPart.HEAD));
/*  982 */     a(Blocks.RED_BED, var0 -> a(var0, BlockBed.PART, BlockPropertyBedPart.HEAD));
/*  983 */     a(Blocks.WHITE_BED, var0 -> a(var0, BlockBed.PART, BlockPropertyBedPart.HEAD));
/*  984 */     a(Blocks.YELLOW_BED, var0 -> a(var0, BlockBed.PART, BlockPropertyBedPart.HEAD));
/*      */ 
/*      */     
/*  987 */     a(Blocks.LILAC, var0 -> a(var0, BlockTallPlant.HALF, BlockPropertyDoubleBlockHalf.LOWER));
/*  988 */     a(Blocks.SUNFLOWER, var0 -> a(var0, BlockTallPlant.HALF, BlockPropertyDoubleBlockHalf.LOWER));
/*  989 */     a(Blocks.PEONY, var0 -> a(var0, BlockTallPlant.HALF, BlockPropertyDoubleBlockHalf.LOWER));
/*  990 */     a(Blocks.ROSE_BUSH, var0 -> a(var0, BlockTallPlant.HALF, BlockPropertyDoubleBlockHalf.LOWER));
/*      */ 
/*      */     
/*  993 */     a(Blocks.TNT, LootTable.b()
/*  994 */         .a(a(Blocks.TNT, LootSelector.a()
/*  995 */             .a(LootValueConstant.a(1))
/*  996 */             .a((LootEntryAbstract.a<?>)LootItem.a(Blocks.TNT)
/*  997 */               .b(LootItemConditionBlockStateProperty.a(Blocks.TNT).a(CriterionTriggerProperties.a.a().a(BlockTNT.a, false)))))));
/*      */ 
/*      */ 
/*      */     
/* 1001 */     a(Blocks.COCOA, var0 -> LootTable.b().a(LootSelector.a().a(LootValueConstant.a(1)).a(a(var0, (LootItemFunctionUser<LootEntryAbstract.a>)LootItem.a(Items.COCOA_BEANS).b((LootItemFunction.a)LootItemFunctionSetCount.a(LootValueConstant.a(3)).b(LootItemConditionBlockStateProperty.a(var0).a(CriterionTriggerProperties.a.a().a(BlockCocoa.AGE, 2))))))));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1011 */     a(Blocks.SEA_PICKLE, var0 -> LootTable.b().a(LootSelector.a().a(LootValueConstant.a(1)).a(a(Blocks.SEA_PICKLE, LootItem.a(var0).b((LootItemFunction.a)LootItemFunctionSetCount.a(LootValueConstant.a(2)).b(LootItemConditionBlockStateProperty.a(var0).a(CriterionTriggerProperties.a.a().a(BlockSeaPickle.a, 2)))).b((LootItemFunction.a)LootItemFunctionSetCount.a(LootValueConstant.a(3)).b(LootItemConditionBlockStateProperty.a(var0).a(CriterionTriggerProperties.a.a().a(BlockSeaPickle.a, 3)))).b((LootItemFunction.a)LootItemFunctionSetCount.a(LootValueConstant.a(4)).b(LootItemConditionBlockStateProperty.a(var0).a(CriterionTriggerProperties.a.a().a(BlockSeaPickle.a, 4))))))));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1021 */     a(Blocks.COMPOSTER, var0 -> LootTable.b().a(LootSelector.a().a((LootEntryAbstract.a)a(var0, LootItem.a(Items.qZ)))).a(LootSelector.a().a(LootItem.a(Items.BONE_MEAL)).b(LootItemConditionBlockStateProperty.a(var0).a(CriterionTriggerProperties.a.a().a(BlockComposter.a, 8)))));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1031 */     a(Blocks.BEACON, hz::f);
/* 1032 */     a(Blocks.BREWING_STAND, hz::f);
/* 1033 */     a(Blocks.CHEST, hz::f);
/* 1034 */     a(Blocks.DISPENSER, hz::f);
/* 1035 */     a(Blocks.DROPPER, hz::f);
/* 1036 */     a(Blocks.ENCHANTING_TABLE, hz::f);
/* 1037 */     a(Blocks.FURNACE, hz::f);
/* 1038 */     a(Blocks.HOPPER, hz::f);
/* 1039 */     a(Blocks.TRAPPED_CHEST, hz::f);
/* 1040 */     a(Blocks.SMOKER, hz::f);
/* 1041 */     a(Blocks.BLAST_FURNACE, hz::f);
/*      */     
/* 1043 */     a(Blocks.BARREL, hz::f);
/* 1044 */     a(Blocks.CARTOGRAPHY_TABLE, hz::f);
/* 1045 */     a(Blocks.FLETCHING_TABLE, hz::f);
/* 1046 */     a(Blocks.GRINDSTONE, hz::f);
/* 1047 */     a(Blocks.LECTERN, hz::f);
/* 1048 */     a(Blocks.SMITHING_TABLE, hz::f);
/* 1049 */     a(Blocks.STONECUTTER, hz::f);
/*      */     
/* 1051 */     a(Blocks.BELL, hz::a);
/* 1052 */     a(Blocks.LANTERN, hz::a);
/* 1053 */     a(Blocks.SOUL_LANTERN, hz::a);
/*      */ 
/*      */     
/* 1056 */     a(Blocks.SHULKER_BOX, hz::g);
/* 1057 */     a(Blocks.BLACK_SHULKER_BOX, hz::g);
/* 1058 */     a(Blocks.BLUE_SHULKER_BOX, hz::g);
/* 1059 */     a(Blocks.BROWN_SHULKER_BOX, hz::g);
/* 1060 */     a(Blocks.CYAN_SHULKER_BOX, hz::g);
/* 1061 */     a(Blocks.GRAY_SHULKER_BOX, hz::g);
/* 1062 */     a(Blocks.GREEN_SHULKER_BOX, hz::g);
/* 1063 */     a(Blocks.LIGHT_BLUE_SHULKER_BOX, hz::g);
/* 1064 */     a(Blocks.LIGHT_GRAY_SHULKER_BOX, hz::g);
/* 1065 */     a(Blocks.LIME_SHULKER_BOX, hz::g);
/* 1066 */     a(Blocks.MAGENTA_SHULKER_BOX, hz::g);
/* 1067 */     a(Blocks.ORANGE_SHULKER_BOX, hz::g);
/* 1068 */     a(Blocks.PINK_SHULKER_BOX, hz::g);
/* 1069 */     a(Blocks.PURPLE_SHULKER_BOX, hz::g);
/* 1070 */     a(Blocks.RED_SHULKER_BOX, hz::g);
/* 1071 */     a(Blocks.WHITE_SHULKER_BOX, hz::g);
/* 1072 */     a(Blocks.YELLOW_SHULKER_BOX, hz::g);
/*      */ 
/*      */     
/* 1075 */     a(Blocks.BLACK_BANNER, hz::h);
/* 1076 */     a(Blocks.BLUE_BANNER, hz::h);
/* 1077 */     a(Blocks.BROWN_BANNER, hz::h);
/* 1078 */     a(Blocks.CYAN_BANNER, hz::h);
/* 1079 */     a(Blocks.GRAY_BANNER, hz::h);
/* 1080 */     a(Blocks.GREEN_BANNER, hz::h);
/* 1081 */     a(Blocks.LIGHT_BLUE_BANNER, hz::h);
/* 1082 */     a(Blocks.LIGHT_GRAY_BANNER, hz::h);
/* 1083 */     a(Blocks.LIME_BANNER, hz::h);
/* 1084 */     a(Blocks.MAGENTA_BANNER, hz::h);
/* 1085 */     a(Blocks.ORANGE_BANNER, hz::h);
/* 1086 */     a(Blocks.PINK_BANNER, hz::h);
/* 1087 */     a(Blocks.PURPLE_BANNER, hz::h);
/* 1088 */     a(Blocks.RED_BANNER, hz::h);
/* 1089 */     a(Blocks.WHITE_BANNER, hz::h);
/* 1090 */     a(Blocks.YELLOW_BANNER, hz::h);
/*      */     
/* 1092 */     a(Blocks.PLAYER_HEAD, var0 -> LootTable.b().a(a(var0, LootSelector.a().a(LootValueConstant.a(1)).a((LootEntryAbstract.a<?>)LootItem.a(var0).b(LootItemFunctionCopyNBT.a(LootItemFunctionCopyNBT.Source.BLOCK_ENTITY).a("SkullOwner", "SkullOwner"))))));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1103 */     a(Blocks.BEE_NEST, hz::i);
/* 1104 */     a(Blocks.BEEHIVE, hz::j);
/*      */ 
/*      */     
/* 1107 */     a(Blocks.BIRCH_LEAVES, var0 -> a(var0, Blocks.BIRCH_SAPLING, g));
/* 1108 */     a(Blocks.ACACIA_LEAVES, var0 -> a(var0, Blocks.ACACIA_SAPLING, g));
/* 1109 */     a(Blocks.JUNGLE_LEAVES, var0 -> a(var0, Blocks.JUNGLE_SAPLING, h));
/* 1110 */     a(Blocks.SPRUCE_LEAVES, var0 -> a(var0, Blocks.SPRUCE_SAPLING, g));
/*      */     
/* 1112 */     a(Blocks.OAK_LEAVES, var0 -> b(var0, Blocks.OAK_SAPLING, g));
/* 1113 */     a(Blocks.DARK_OAK_LEAVES, var0 -> b(var0, Blocks.DARK_OAK_SAPLING, g));
/*      */ 
/*      */     
/* 1116 */     LootItemCondition.a var1 = LootItemConditionBlockStateProperty.a(Blocks.BEETROOTS).a(CriterionTriggerProperties.a.a().a(BlockBeetroot.a, 3));
/* 1117 */     a(Blocks.BEETROOTS, a(Blocks.BEETROOTS, Items.BEETROOT, Items.BEETROOT_SEEDS, var1));
/*      */     
/* 1119 */     LootItemCondition.a var2 = LootItemConditionBlockStateProperty.a(Blocks.WHEAT).a(CriterionTriggerProperties.a.a().a(BlockCrops.AGE, 7));
/* 1120 */     a(Blocks.WHEAT, a(Blocks.WHEAT, Items.WHEAT, Items.WHEAT_SEEDS, var2));
/*      */     
/* 1122 */     LootItemCondition.a var3 = LootItemConditionBlockStateProperty.a(Blocks.CARROTS).a(CriterionTriggerProperties.a.a().a(BlockCarrots.AGE, 7));
/*      */     
/* 1124 */     a(Blocks.CARROTS, a(Blocks.CARROTS, LootTable.b()
/* 1125 */           .a(LootSelector.a()
/* 1126 */             .a(LootItem.a(Items.CARROT)))
/*      */           
/* 1128 */           .a(LootSelector.a()
/* 1129 */             .b(var3)
/* 1130 */             .a((LootEntryAbstract.a<?>)LootItem.a(Items.CARROT).b(LootItemFunctionApplyBonus.a(Enchantments.LOOT_BONUS_BLOCKS, 0.5714286F, 3))))));
/*      */ 
/*      */ 
/*      */     
/* 1134 */     LootItemCondition.a var4 = LootItemConditionBlockStateProperty.a(Blocks.POTATOES).a(CriterionTriggerProperties.a.a().a(BlockPotatoes.AGE, 7));
/* 1135 */     a(Blocks.POTATOES, a(Blocks.POTATOES, LootTable.b()
/* 1136 */           .a(LootSelector.a()
/* 1137 */             .a(LootItem.a(Items.POTATO)))
/*      */           
/* 1139 */           .a(LootSelector.a()
/* 1140 */             .b(var4)
/* 1141 */             .a((LootEntryAbstract.a<?>)LootItem.a(Items.POTATO).b(LootItemFunctionApplyBonus.a(Enchantments.LOOT_BONUS_BLOCKS, 0.5714286F, 3))))
/*      */           
/* 1143 */           .a(LootSelector.a()
/* 1144 */             .b(var4)
/* 1145 */             .a((LootEntryAbstract.a<?>)LootItem.a(Items.POISONOUS_POTATO).b(LootItemConditionRandomChance.a(0.02F))))));
/*      */ 
/*      */ 
/*      */     
/* 1149 */     a(Blocks.SWEET_BERRY_BUSH, var0 -> (LootTable.a)a(var0, LootTable.b().a(LootSelector.a().b(LootItemConditionBlockStateProperty.a(Blocks.SWEET_BERRY_BUSH).a(CriterionTriggerProperties.a.a().a(BlockSweetBerryBush.a, 3))).a(LootItem.a(Items.SWEET_BERRIES)).b(LootItemFunctionSetCount.a(LootValueBounds.a(2.0F, 3.0F))).b(LootItemFunctionApplyBonus.b(Enchantments.LOOT_BONUS_BLOCKS))).a(LootSelector.a().b(LootItemConditionBlockStateProperty.a(Blocks.SWEET_BERRY_BUSH).a(CriterionTriggerProperties.a.a().a(BlockSweetBerryBush.a, 2))).a(LootItem.a(Items.SWEET_BERRIES)).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 2.0F))).b(LootItemFunctionApplyBonus.b(Enchantments.LOOT_BONUS_BLOCKS)))));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1167 */     a(Blocks.BROWN_MUSHROOM_BLOCK, var0 -> c(var0, Blocks.BROWN_MUSHROOM));
/* 1168 */     a(Blocks.RED_MUSHROOM_BLOCK, var0 -> c(var0, Blocks.RED_MUSHROOM));
/*      */ 
/*      */     
/* 1171 */     a(Blocks.COAL_ORE, var0 -> a(var0, Items.COAL));
/* 1172 */     a(Blocks.EMERALD_ORE, var0 -> a(var0, Items.EMERALD));
/* 1173 */     a(Blocks.NETHER_QUARTZ_ORE, var0 -> a(var0, Items.QUARTZ));
/* 1174 */     a(Blocks.DIAMOND_ORE, var0 -> a(var0, Items.DIAMOND));
/*      */     
/* 1176 */     a(Blocks.NETHER_GOLD_ORE, var0 -> a(var0, a(var0, LootItem.a(Items.GOLD_NUGGET).b(LootItemFunctionSetCount.a(LootValueBounds.a(2.0F, 6.0F))).b(LootItemFunctionApplyBonus.a(Enchantments.LOOT_BONUS_BLOCKS)))));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1183 */     a(Blocks.LAPIS_ORE, var0 -> a(var0, a(var0, LootItem.a(Items.LAPIS_LAZULI).b(LootItemFunctionSetCount.a(LootValueBounds.a(4.0F, 9.0F))).b(LootItemFunctionApplyBonus.a(Enchantments.LOOT_BONUS_BLOCKS)))));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1191 */     a(Blocks.COBWEB, var0 -> c(var0, (LootEntryAbstract.a)a(var0, LootItem.a(Items.STRING))));
/*      */ 
/*      */ 
/*      */     
/* 1195 */     a(Blocks.DEAD_BUSH, var0 -> b(var0, a(var0, (LootItemFunctionUser<LootEntryAbstract.a>)LootItem.a(Items.STICK).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 2.0F))))));
/*      */ 
/*      */ 
/*      */     
/* 1199 */     a(Blocks.NETHER_SPROUTS, hz::d);
/* 1200 */     a(Blocks.SEAGRASS, hz::d);
/* 1201 */     a(Blocks.VINE, hz::d);
/* 1202 */     a(Blocks.TALL_SEAGRASS, l(Blocks.SEAGRASS));
/* 1203 */     a(Blocks.LARGE_FERN, var0 -> b(var0, Blocks.FERN));
/* 1204 */     a(Blocks.TALL_GRASS, var0 -> b(var0, Blocks.GRASS));
/*      */ 
/*      */     
/* 1207 */     a(Blocks.MELON_STEM, var0 -> b(var0, Items.MELON_SEEDS));
/* 1208 */     a(Blocks.ATTACHED_MELON_STEM, var0 -> c(var0, Items.MELON_SEEDS));
/* 1209 */     a(Blocks.PUMPKIN_STEM, var0 -> b(var0, Items.PUMPKIN_SEEDS));
/* 1210 */     a(Blocks.ATTACHED_PUMPKIN_STEM, var0 -> c(var0, Items.PUMPKIN_SEEDS));
/*      */ 
/*      */     
/* 1213 */     a(Blocks.CHORUS_FLOWER, var0 -> LootTable.b().a(LootSelector.a().a(LootValueConstant.a(1)).a(((LootSelectorEntry.a<LootEntryAbstract.a<?>>)a(var0, LootItem.a(var0))).b(LootItemConditionEntityProperty.a(LootTableInfo.EntityTarget.THIS)))));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1220 */     a(Blocks.FERN, hz::k);
/* 1221 */     a(Blocks.GRASS, hz::k);
/*      */     
/* 1223 */     a(Blocks.GLOWSTONE, var0 -> a(var0, a(var0, LootItem.a(Items.GLOWSTONE_DUST).b(LootItemFunctionSetCount.a(LootValueBounds.a(2.0F, 4.0F))).b(LootItemFunctionApplyBonus.b(Enchantments.LOOT_BONUS_BLOCKS)).b(LootItemFunctionLimitCount.a(LootIntegerLimit.a(1, 4))))));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1231 */     a(Blocks.MELON, var0 -> a(var0, a(var0, LootItem.a(Items.MELON_SLICE).b(LootItemFunctionSetCount.a(LootValueBounds.a(3.0F, 7.0F))).b(LootItemFunctionApplyBonus.b(Enchantments.LOOT_BONUS_BLOCKS)).b(LootItemFunctionLimitCount.a(LootIntegerLimit.b(9))))));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1239 */     a(Blocks.REDSTONE_ORE, var0 -> a(var0, a(var0, LootItem.a(Items.REDSTONE).b(LootItemFunctionSetCount.a(LootValueBounds.a(4.0F, 5.0F))).b(LootItemFunctionApplyBonus.b(Enchantments.LOOT_BONUS_BLOCKS)))));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1246 */     a(Blocks.SEA_LANTERN, var0 -> a(var0, a(var0, LootItem.a(Items.PRISMARINE_CRYSTALS).b(LootItemFunctionSetCount.a(LootValueBounds.a(2.0F, 3.0F))).b(LootItemFunctionApplyBonus.b(Enchantments.LOOT_BONUS_BLOCKS)).b(LootItemFunctionLimitCount.a(LootIntegerLimit.a(1, 5))))));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1254 */     a(Blocks.NETHER_WART, var0 -> LootTable.b().a(a(var0, LootSelector.a().a(LootValueConstant.a(1)).a(LootItem.a(Items.NETHER_WART).b((LootItemFunction.a)LootItemFunctionSetCount.a(LootValueBounds.a(2.0F, 4.0F)).b(LootItemConditionBlockStateProperty.a(var0).a(CriterionTriggerProperties.a.a().a(BlockNetherWart.AGE, 3)))).b((LootItemFunction.a)LootItemFunctionApplyBonus.b(Enchantments.LOOT_BONUS_BLOCKS).b(LootItemConditionBlockStateProperty.a(var0).a(CriterionTriggerProperties.a.a().a(BlockNetherWart.AGE, 3))))))));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1267 */     a(Blocks.SNOW, var0 -> LootTable.b().a(LootSelector.a().b(LootItemConditionEntityProperty.a(LootTableInfo.EntityTarget.THIS)).a(LootEntryAlternatives.a((LootEntryAbstract.a<?>[])new LootEntryAbstract.a[] { LootEntryAlternatives.a((LootEntryAbstract.a<?>[])new LootEntryAbstract.a[] { (LootEntryAbstract.a)LootItem.a(Items.SNOWBALL).b(LootItemConditionBlockStateProperty.a(var0).a(CriterionTriggerProperties.a.a().a(BlockSnow.LAYERS, 1))), ((LootSelectorEntry.a<LootEntryAbstract.a>)LootItem.a(Items.SNOWBALL).b(LootItemConditionBlockStateProperty.a(var0).a(CriterionTriggerProperties.a.a().a(BlockSnow.LAYERS, 2)))).b(LootItemFunctionSetCount.a(LootValueConstant.a(2))), ((LootSelectorEntry.a<LootEntryAbstract.a>)LootItem.a(Items.SNOWBALL).b(LootItemConditionBlockStateProperty.a(var0).a(CriterionTriggerProperties.a.a().a(BlockSnow.LAYERS, 3)))).b(LootItemFunctionSetCount.a(LootValueConstant.a(3))), ((LootSelectorEntry.a<LootEntryAbstract.a>)LootItem.a(Items.SNOWBALL).b(LootItemConditionBlockStateProperty.a(var0).a(CriterionTriggerProperties.a.a().a(BlockSnow.LAYERS, 4)))).b(LootItemFunctionSetCount.a(LootValueConstant.a(4))), ((LootSelectorEntry.a<LootEntryAbstract.a>)LootItem.a(Items.SNOWBALL).b(LootItemConditionBlockStateProperty.a(var0).a(CriterionTriggerProperties.a.a().a(BlockSnow.LAYERS, 5)))).b(LootItemFunctionSetCount.a(LootValueConstant.a(5))), ((LootSelectorEntry.a<LootEntryAbstract.a>)LootItem.a(Items.SNOWBALL).b(LootItemConditionBlockStateProperty.a(var0).a(CriterionTriggerProperties.a.a().a(BlockSnow.LAYERS, 6)))).b(LootItemFunctionSetCount.a(LootValueConstant.a(6))), ((LootSelectorEntry.a<LootEntryAbstract.a>)LootItem.a(Items.SNOWBALL).b(LootItemConditionBlockStateProperty.a(var0).a(CriterionTriggerProperties.a.a().a(BlockSnow.LAYERS, 7)))).b(LootItemFunctionSetCount.a(LootValueConstant.a(7))), (LootEntryAbstract.a)LootItem.a(Items.SNOWBALL).b(LootItemFunctionSetCount.a(LootValueConstant.a(8))) }).b(b), LootEntryAlternatives.a((LootEntryAbstract.a<?>[])new LootEntryAbstract.a[] { (LootEntryAbstract.a)LootItem.a(Blocks.SNOW).b(LootItemConditionBlockStateProperty.a(var0).a(CriterionTriggerProperties.a.a().a(BlockSnow.LAYERS, 1))), LootItem.a(Blocks.SNOW).b(LootItemFunctionSetCount.a(LootValueConstant.a(2))).b(LootItemConditionBlockStateProperty.a(var0).a(CriterionTriggerProperties.a.a().a(BlockSnow.LAYERS, 2))), LootItem.a(Blocks.SNOW).b(LootItemFunctionSetCount.a(LootValueConstant.a(3))).b(LootItemConditionBlockStateProperty.a(var0).a(CriterionTriggerProperties.a.a().a(BlockSnow.LAYERS, 3))), LootItem.a(Blocks.SNOW).b(LootItemFunctionSetCount.a(LootValueConstant.a(4))).b(LootItemConditionBlockStateProperty.a(var0).a(CriterionTriggerProperties.a.a().a(BlockSnow.LAYERS, 4))), LootItem.a(Blocks.SNOW).b(LootItemFunctionSetCount.a(LootValueConstant.a(5))).b(LootItemConditionBlockStateProperty.a(var0).a(CriterionTriggerProperties.a.a().a(BlockSnow.LAYERS, 5))), LootItem.a(Blocks.SNOW).b(LootItemFunctionSetCount.a(LootValueConstant.a(6))).b(LootItemConditionBlockStateProperty.a(var0).a(CriterionTriggerProperties.a.a().a(BlockSnow.LAYERS, 6))), LootItem.a(Blocks.SNOW).b(LootItemFunctionSetCount.a(LootValueConstant.a(7))).b(LootItemConditionBlockStateProperty.a(var0).a(CriterionTriggerProperties.a.a().a(BlockSnow.LAYERS, 7))), LootItem.a(Blocks.SNOW_BLOCK) }) }))));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1296 */     a(Blocks.GRAVEL, var0 -> a(var0, a(var0, ((LootSelectorEntry.a)LootItem.a(Items.FLINT).b(LootItemConditionTableBonus.a(Enchantments.LOOT_BONUS_BLOCKS, new float[] { 0.1F, 0.14285715F, 0.25F, 1.0F }))).a(LootItem.a(var0)))));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1303 */     a(Blocks.CAMPFIRE, var0 -> a(var0, a(var0, (LootItemConditionUser<LootEntryAbstract.a>)LootItem.a(Items.CHARCOAL).b(LootItemFunctionSetCount.a(LootValueConstant.a(2))))));
/*      */ 
/*      */ 
/*      */     
/* 1307 */     a(Blocks.GILDED_BLACKSTONE, var0 -> a(var0, a(var0, ((LootSelectorEntry.a)LootItem.a(Items.GOLD_NUGGET).b(LootItemFunctionSetCount.a(LootValueBounds.a(2.0F, 5.0F))).b(LootItemConditionTableBonus.a(Enchantments.LOOT_BONUS_BLOCKS, new float[] { 0.1F, 0.14285715F, 0.25F, 1.0F }))).a(LootItem.a(var0)))));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1314 */     a(Blocks.SOUL_CAMPFIRE, var0 -> a(var0, a(var0, (LootItemConditionUser<LootEntryAbstract.a>)LootItem.a(Items.dm).b(LootItemFunctionSetCount.a(LootValueConstant.a(1))))));
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1319 */     c(Blocks.GLASS);
/* 1320 */     c(Blocks.WHITE_STAINED_GLASS);
/* 1321 */     c(Blocks.ORANGE_STAINED_GLASS);
/* 1322 */     c(Blocks.MAGENTA_STAINED_GLASS);
/* 1323 */     c(Blocks.LIGHT_BLUE_STAINED_GLASS);
/* 1324 */     c(Blocks.YELLOW_STAINED_GLASS);
/* 1325 */     c(Blocks.LIME_STAINED_GLASS);
/* 1326 */     c(Blocks.PINK_STAINED_GLASS);
/* 1327 */     c(Blocks.GRAY_STAINED_GLASS);
/* 1328 */     c(Blocks.LIGHT_GRAY_STAINED_GLASS);
/* 1329 */     c(Blocks.CYAN_STAINED_GLASS);
/* 1330 */     c(Blocks.PURPLE_STAINED_GLASS);
/* 1331 */     c(Blocks.BLUE_STAINED_GLASS);
/* 1332 */     c(Blocks.BROWN_STAINED_GLASS);
/* 1333 */     c(Blocks.GREEN_STAINED_GLASS);
/* 1334 */     c(Blocks.RED_STAINED_GLASS);
/* 1335 */     c(Blocks.BLACK_STAINED_GLASS);
/*      */     
/* 1337 */     c(Blocks.GLASS_PANE);
/* 1338 */     c(Blocks.WHITE_STAINED_GLASS_PANE);
/* 1339 */     c(Blocks.ORANGE_STAINED_GLASS_PANE);
/* 1340 */     c(Blocks.MAGENTA_STAINED_GLASS_PANE);
/* 1341 */     c(Blocks.LIGHT_BLUE_STAINED_GLASS_PANE);
/* 1342 */     c(Blocks.YELLOW_STAINED_GLASS_PANE);
/* 1343 */     c(Blocks.LIME_STAINED_GLASS_PANE);
/* 1344 */     c(Blocks.PINK_STAINED_GLASS_PANE);
/* 1345 */     c(Blocks.GRAY_STAINED_GLASS_PANE);
/* 1346 */     c(Blocks.LIGHT_GRAY_STAINED_GLASS_PANE);
/* 1347 */     c(Blocks.CYAN_STAINED_GLASS_PANE);
/* 1348 */     c(Blocks.PURPLE_STAINED_GLASS_PANE);
/* 1349 */     c(Blocks.BLUE_STAINED_GLASS_PANE);
/* 1350 */     c(Blocks.BROWN_STAINED_GLASS_PANE);
/* 1351 */     c(Blocks.GREEN_STAINED_GLASS_PANE);
/* 1352 */     c(Blocks.RED_STAINED_GLASS_PANE);
/* 1353 */     c(Blocks.BLACK_STAINED_GLASS_PANE);
/*      */     
/* 1355 */     c(Blocks.ICE);
/* 1356 */     c(Blocks.PACKED_ICE);
/* 1357 */     c(Blocks.BLUE_ICE);
/*      */     
/* 1359 */     c(Blocks.TURTLE_EGG);
/*      */     
/* 1361 */     c(Blocks.MUSHROOM_STEM);
/*      */     
/* 1363 */     c(Blocks.DEAD_TUBE_CORAL);
/* 1364 */     c(Blocks.DEAD_BRAIN_CORAL);
/* 1365 */     c(Blocks.DEAD_BUBBLE_CORAL);
/* 1366 */     c(Blocks.DEAD_FIRE_CORAL);
/* 1367 */     c(Blocks.DEAD_HORN_CORAL);
/*      */     
/* 1369 */     c(Blocks.TUBE_CORAL);
/* 1370 */     c(Blocks.BRAIN_CORAL);
/* 1371 */     c(Blocks.BUBBLE_CORAL);
/* 1372 */     c(Blocks.FIRE_CORAL);
/* 1373 */     c(Blocks.HORN_CORAL);
/*      */     
/* 1375 */     c(Blocks.DEAD_TUBE_CORAL_FAN);
/* 1376 */     c(Blocks.DEAD_BRAIN_CORAL_FAN);
/* 1377 */     c(Blocks.DEAD_BUBBLE_CORAL_FAN);
/* 1378 */     c(Blocks.DEAD_FIRE_CORAL_FAN);
/* 1379 */     c(Blocks.DEAD_HORN_CORAL_FAN);
/*      */     
/* 1381 */     c(Blocks.TUBE_CORAL_FAN);
/* 1382 */     c(Blocks.BRAIN_CORAL_FAN);
/* 1383 */     c(Blocks.BUBBLE_CORAL_FAN);
/* 1384 */     c(Blocks.FIRE_CORAL_FAN);
/* 1385 */     c(Blocks.HORN_CORAL_FAN);
/*      */     
/* 1387 */     a(Blocks.INFESTED_STONE, Blocks.STONE);
/* 1388 */     a(Blocks.INFESTED_COBBLESTONE, Blocks.COBBLESTONE);
/* 1389 */     a(Blocks.INFESTED_STONE_BRICKS, Blocks.STONE_BRICKS);
/* 1390 */     a(Blocks.INFESTED_MOSSY_STONE_BRICKS, Blocks.MOSSY_STONE_BRICKS);
/* 1391 */     a(Blocks.INFESTED_CRACKED_STONE_BRICKS, Blocks.CRACKED_STONE_BRICKS);
/* 1392 */     a(Blocks.INFESTED_CHISELED_STONE_BRICKS, Blocks.CHISELED_STONE_BRICKS);
/*      */     
/* 1394 */     c(Blocks.WEEPING_VINES, Blocks.WEEPING_VINES_PLANT);
/* 1395 */     c(Blocks.TWISTING_VINES, Blocks.TWISTING_VINES_PLANT);
/*      */ 
/*      */     
/* 1398 */     a(Blocks.CAKE, a());
/* 1399 */     a(Blocks.FROSTED_ICE, a());
/* 1400 */     a(Blocks.SPAWNER, a());
/* 1401 */     a(Blocks.FIRE, a());
/* 1402 */     a(Blocks.SOUL_FIRE, a());
/* 1403 */     a(Blocks.NETHER_PORTAL, a());
/*      */     
/* 1405 */     Set<MinecraftKey> var5 = Sets.newHashSet();
/* 1406 */     for (Block var7 : IRegistry.BLOCK) {
/* 1407 */       MinecraftKey var8 = var7.r();
/* 1408 */       if (var8 != LootTables.a && var5.add(var8)) {
/* 1409 */         LootTable.a var9 = this.i.remove(var8);
/* 1410 */         if (var9 == null) {
/* 1411 */           throw new IllegalStateException(String.format("Missing loottable '%s' for '%s'", new Object[] { var8, IRegistry.BLOCK.getKey(var7) }));
/*      */         }
/* 1413 */         var0.accept(var8, var9);
/*      */       } 
/*      */     } 
/*      */     
/* 1417 */     if (!this.i.isEmpty()) {
/* 1418 */       throw new IllegalStateException("Created block loot tables for non-blocks: " + this.i.keySet());
/*      */     }
/*      */   }
/*      */   
/*      */   private void c(Block var0, Block var1) {
/* 1423 */     LootTable.a var2 = c(var0, 
/* 1424 */         (LootEntryAbstract.a<?>)LootItem.a(var0).b(LootItemConditionTableBonus.a(Enchantments.LOOT_BONUS_BLOCKS, new float[] { 0.33F, 0.55F, 0.77F, 1.0F })));
/* 1425 */     a(var0, var2);
/* 1426 */     a(var1, var2);
/*      */   }
/*      */   
/*      */   public static LootTable.a a(Block var0) {
/* 1430 */     return a(var0, BlockDoor.HALF, BlockPropertyDoubleBlockHalf.LOWER);
/*      */   }
/*      */   
/*      */   public void b(Block var0) {
/* 1434 */     a(var0, var0 -> c(((BlockFlowerPot)var0).c()));
/*      */   }
/*      */   
/*      */   public void a(Block var0, Block var1) {
/* 1438 */     a(var0, b(var1));
/*      */   }
/*      */   
/*      */   public void a(Block var0, IMaterial var1) {
/* 1442 */     a(var0, a(var1));
/*      */   }
/*      */   
/*      */   public void c(Block var0) {
/* 1446 */     a(var0, var0);
/*      */   }
/*      */   
/*      */   public void d(Block var0) {
/* 1450 */     a(var0, var0);
/*      */   }
/*      */   
/*      */   private void a(Block var0, Function<Block, LootTable.a> var1) {
/* 1454 */     a(var0, var1.apply(var0));
/*      */   }
/*      */   
/*      */   private void a(Block var0, LootTable.a var1) {
/* 1458 */     this.i.put(var0.r(), var1);
/*      */   }
/*      */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\hz.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */