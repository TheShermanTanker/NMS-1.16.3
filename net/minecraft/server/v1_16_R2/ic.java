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
/*     */ 
/*     */ 
/*     */ public class ic
/*     */   implements Consumer<BiConsumer<MinecraftKey, LootTable.a>>
/*     */ {
/*  34 */   public static final LootItemCondition.a a = LootItemConditionLocationCheck.a(CriterionConditionLocation.a.a().a(Biomes.JUNGLE));
/*  35 */   public static final LootItemCondition.a b = LootItemConditionLocationCheck.a(CriterionConditionLocation.a.a().a(Biomes.JUNGLE_HILLS));
/*  36 */   public static final LootItemCondition.a c = LootItemConditionLocationCheck.a(CriterionConditionLocation.a.a().a(Biomes.JUNGLE_EDGE));
/*  37 */   public static final LootItemCondition.a d = LootItemConditionLocationCheck.a(CriterionConditionLocation.a.a().a(Biomes.BAMBOO_JUNGLE));
/*  38 */   public static final LootItemCondition.a e = LootItemConditionLocationCheck.a(CriterionConditionLocation.a.a().a(Biomes.MODIFIED_JUNGLE));
/*  39 */   public static final LootItemCondition.a f = LootItemConditionLocationCheck.a(CriterionConditionLocation.a.a().a(Biomes.MODIFIED_JUNGLE_EDGE));
/*  40 */   public static final LootItemCondition.a g = LootItemConditionLocationCheck.a(CriterionConditionLocation.a.a().a(Biomes.BAMBOO_JUNGLE_HILLS));
/*     */ 
/*     */   
/*     */   public void accept(BiConsumer<MinecraftKey, LootTable.a> var0) {
/*  44 */     var0.accept(LootTables.ag, 
/*  45 */         LootTable.b()
/*  46 */         .a(LootSelector.a()
/*  47 */           .a(LootValueConstant.a(1))
/*  48 */           .a(LootSelectorLootTable.a(LootTables.ah).a(10).b(-2))
/*  49 */           .a(LootSelectorLootTable.a(LootTables.ai).a(5).b(2)
/*  50 */             .b(LootItemConditionEntityProperty.a(LootTableInfo.EntityTarget.THIS, CriterionConditionEntity.a.a().a(CriterionConditionInOpenWater.a(true)))))
/*  51 */           .a(LootSelectorLootTable.a(LootTables.aj).a(85).b(-1))));
/*     */ 
/*     */ 
/*     */     
/*  55 */     var0.accept(LootTables.aj, 
/*  56 */         LootTable.b()
/*  57 */         .a(LootSelector.a()
/*  58 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.COD).a(60))
/*  59 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.SALMON).a(25))
/*  60 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.TROPICAL_FISH).a(2))
/*  61 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.PUFFERFISH).a(13))));
/*     */ 
/*     */ 
/*     */     
/*  65 */     var0.accept(LootTables.ah, 
/*  66 */         LootTable.b()
/*  67 */         .a(LootSelector.a()
/*  68 */           .a((LootEntryAbstract.a<?>)LootItem.a(Blocks.LILY_PAD).a(17))
/*  69 */           .a(LootItem.a(Items.LEATHER_BOOTS).a(10).b(LootItemFunctionSetDamage.a(LootValueBounds.a(0.0F, 0.9F))))
/*  70 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.LEATHER).a(10))
/*  71 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.BONE).a(10))
/*  72 */           .a(LootItem.a(Items.POTION).a(10)
/*  73 */             .b(LootItemFunctionSetTag.a(SystemUtils.<NBTTagCompound>a(new NBTTagCompound(), var0 -> var0.setString("Potion", "minecraft:water")))))
/*  74 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.STRING).a(5))
/*  75 */           .a(LootItem.a(Items.FISHING_ROD).a(2).b(LootItemFunctionSetDamage.a(LootValueBounds.a(0.0F, 0.9F))))
/*  76 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.BOWL).a(10))
/*  77 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.STICK).a(5))
/*  78 */           .a(LootItem.a(Items.INK_SAC).a(1).b(LootItemFunctionSetCount.a(LootValueConstant.a(10))))
/*  79 */           .a((LootEntryAbstract.a<?>)LootItem.a(Blocks.TRIPWIRE_HOOK).a(10))
/*  80 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.ROTTEN_FLESH).a(10))
/*  81 */           .a(((LootSelectorEntry.a<LootEntryAbstract.a<?>>)LootItem.a(Blocks.BAMBOO)
/*  82 */             .b(a.a(b).a(c).a(d).a(e).a(f).a(g)))
/*  83 */             .a(10))));
/*     */ 
/*     */ 
/*     */     
/*  87 */     var0.accept(LootTables.ai, 
/*  88 */         LootTable.b()
/*  89 */         .a(LootSelector.a()
/*  90 */           .a(LootItem.a(Items.NAME_TAG))
/*  91 */           .a(LootItem.a(Items.SADDLE))
/*  92 */           .a(LootItem.a(Items.BOW)
/*  93 */             .b(LootItemFunctionSetDamage.a(LootValueBounds.a(0.0F, 0.25F)))
/*  94 */             .b(LootEnchantLevel.a(LootValueConstant.a(30)).e()))
/*     */           
/*  96 */           .a(LootItem.a(Items.FISHING_ROD)
/*  97 */             .b(LootItemFunctionSetDamage.a(LootValueBounds.a(0.0F, 0.25F)))
/*  98 */             .b(LootEnchantLevel.a(LootValueConstant.a(30)).e()))
/*     */           
/* 100 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.BOOK)
/* 101 */             .b(LootEnchantLevel.a(LootValueConstant.a(30)).e()))
/*     */           
/* 103 */           .a(LootItem.a(Items.NAUTILUS_SHELL))));
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */