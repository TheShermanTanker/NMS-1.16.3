/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
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
/*     */ public class hq
/*     */   implements Consumer<Consumer<Advancement>>
/*     */ {
/*  43 */   private static final EntityTypes<?>[] a = new EntityTypes[] { EntityTypes.HORSE, EntityTypes.DONKEY, EntityTypes.MULE, EntityTypes.SHEEP, EntityTypes.COW, EntityTypes.MOOSHROOM, EntityTypes.PIG, EntityTypes.CHICKEN, EntityTypes.WOLF, EntityTypes.OCELOT, EntityTypes.RABBIT, EntityTypes.LLAMA, EntityTypes.CAT, EntityTypes.PANDA, EntityTypes.FOX, EntityTypes.BEE, EntityTypes.HOGLIN, EntityTypes.STRIDER };
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
/*  65 */   private static final Item[] b = new Item[] { Items.COD, Items.TROPICAL_FISH, Items.PUFFERFISH, Items.SALMON };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  72 */   private static final Item[] c = new Item[] { Items.COD_BUCKET, Items.TROPICAL_FISH_BUCKET, Items.PUFFERFISH_BUCKET, Items.SALMON_BUCKET };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  79 */   private static final Item[] d = new Item[] { Items.APPLE, Items.MUSHROOM_STEW, Items.BREAD, Items.PORKCHOP, Items.COOKED_PORKCHOP, Items.GOLDEN_APPLE, Items.ENCHANTED_GOLDEN_APPLE, Items.COD, Items.SALMON, Items.TROPICAL_FISH, Items.PUFFERFISH, Items.COOKED_COD, Items.COOKED_SALMON, Items.COOKIE, Items.MELON_SLICE, Items.BEEF, Items.COOKED_BEEF, Items.CHICKEN, Items.COOKED_CHICKEN, Items.ROTTEN_FLESH, Items.SPIDER_EYE, Items.CARROT, Items.POTATO, Items.BAKED_POTATO, Items.POISONOUS_POTATO, Items.GOLDEN_CARROT, Items.PUMPKIN_PIE, Items.RABBIT, Items.COOKED_RABBIT, Items.RABBIT_STEW, Items.MUTTON, Items.COOKED_MUTTON, Items.CHORUS_FRUIT, Items.BEETROOT, Items.BEETROOT_SOUP, Items.DRIED_KELP, Items.SUSPICIOUS_STEW, Items.SWEET_BERRIES, Items.HONEY_BOTTLE };
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
/*     */   public void accept(Consumer<Advancement> var0) {
/* 126 */     Advancement var1 = Advancement.SerializedAdvancement.a().a(Blocks.HAY_BLOCK, new ChatMessage("advancements.husbandry.root.title"), new ChatMessage("advancements.husbandry.root.description"), new MinecraftKey("textures/gui/advancements/backgrounds/husbandry.png"), AdvancementFrameType.TASK, false, false, false).a("consumed_item", CriterionTriggerConsumeItem.a.c()).a(var0, "husbandry/root");
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
/* 137 */     Advancement var2 = Advancement.SerializedAdvancement.a().a(var1).a(Items.WHEAT, new ChatMessage("advancements.husbandry.plant_seed.title"), new ChatMessage("advancements.husbandry.plant_seed.description"), (MinecraftKey)null, AdvancementFrameType.TASK, true, true, false).a(AdvancementRequirements.OR).a("wheat", CriterionTriggerPlacedBlock.a.a(Blocks.WHEAT)).a("pumpkin_stem", CriterionTriggerPlacedBlock.a.a(Blocks.PUMPKIN_STEM)).a("melon_stem", CriterionTriggerPlacedBlock.a.a(Blocks.MELON_STEM)).a("beetroots", CriterionTriggerPlacedBlock.a.a(Blocks.BEETROOTS)).a("nether_wart", CriterionTriggerPlacedBlock.a.a(Blocks.NETHER_WART)).a(var0, "husbandry/plant_seed");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 144 */     Advancement var3 = Advancement.SerializedAdvancement.a().a(var1).a(Items.WHEAT, new ChatMessage("advancements.husbandry.breed_an_animal.title"), new ChatMessage("advancements.husbandry.breed_an_animal.description"), (MinecraftKey)null, AdvancementFrameType.TASK, true, true, false).a(AdvancementRequirements.OR).a("bred", CriterionTriggerBredAnimals.a.c()).a(var0, "husbandry/breed_an_animal");
/*     */     
/* 146 */     a(Advancement.SerializedAdvancement.a())
/* 147 */       .a(var2)
/* 148 */       .a(Items.APPLE, new ChatMessage("advancements.husbandry.balanced_diet.title"), new ChatMessage("advancements.husbandry.balanced_diet.description"), (MinecraftKey)null, AdvancementFrameType.CHALLENGE, true, true, false)
/* 149 */       .a(AdvancementRewards.a.a(100))
/* 150 */       .a(var0, "husbandry/balanced_diet");
/*     */     
/* 152 */     Advancement.SerializedAdvancement.a()
/* 153 */       .a(var2)
/* 154 */       .a(Items.NETHERITE_HOE, new ChatMessage("advancements.husbandry.netherite_hoe.title"), new ChatMessage("advancements.husbandry.netherite_hoe.description"), (MinecraftKey)null, AdvancementFrameType.CHALLENGE, true, true, false)
/* 155 */       .a(AdvancementRewards.a.a(100))
/* 156 */       .a("netherite_hoe", CriterionTriggerInventoryChanged.a.a(new IMaterial[] { Items.NETHERITE_HOE
/* 157 */           })).a(var0, "husbandry/obtain_netherite_hoe");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 163 */     Advancement var4 = Advancement.SerializedAdvancement.a().a(var1).a(Items.LEAD, new ChatMessage("advancements.husbandry.tame_an_animal.title"), new ChatMessage("advancements.husbandry.tame_an_animal.description"), (MinecraftKey)null, AdvancementFrameType.TASK, true, true, false).a("tamed_animal", CriterionTriggerTamedAnimal.a.c()).a(var0, "husbandry/tame_an_animal");
/*     */     
/* 165 */     b(Advancement.SerializedAdvancement.a())
/* 166 */       .a(var3)
/* 167 */       .a(Items.GOLDEN_CARROT, new ChatMessage("advancements.husbandry.breed_all_animals.title"), new ChatMessage("advancements.husbandry.breed_all_animals.description"), (MinecraftKey)null, AdvancementFrameType.CHALLENGE, true, true, false)
/* 168 */       .a(AdvancementRewards.a.a(100))
/* 169 */       .a(var0, "husbandry/bred_all_animals");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 175 */     Advancement var5 = d(Advancement.SerializedAdvancement.a()).a(var1).a(AdvancementRequirements.OR).a(Items.FISHING_ROD, new ChatMessage("advancements.husbandry.fishy_business.title"), new ChatMessage("advancements.husbandry.fishy_business.description"), (MinecraftKey)null, AdvancementFrameType.TASK, true, true, false).a(var0, "husbandry/fishy_business");
/*     */     
/* 177 */     c(Advancement.SerializedAdvancement.a())
/* 178 */       .a(var5)
/* 179 */       .a(AdvancementRequirements.OR)
/* 180 */       .a(Items.PUFFERFISH_BUCKET, new ChatMessage("advancements.husbandry.tactical_fishing.title"), new ChatMessage("advancements.husbandry.tactical_fishing.description"), (MinecraftKey)null, AdvancementFrameType.TASK, true, true, false)
/* 181 */       .a(var0, "husbandry/tactical_fishing");
/*     */     
/* 183 */     e(Advancement.SerializedAdvancement.a())
/* 184 */       .a(var4)
/* 185 */       .a(Items.COD, new ChatMessage("advancements.husbandry.complete_catalogue.title"), new ChatMessage("advancements.husbandry.complete_catalogue.description"), (MinecraftKey)null, AdvancementFrameType.CHALLENGE, true, true, false)
/* 186 */       .a(AdvancementRewards.a.a(50))
/* 187 */       .a(var0, "husbandry/complete_catalogue");
/*     */     
/* 189 */     Advancement.SerializedAdvancement.a()
/* 190 */       .a(var1)
/* 191 */       .a("safely_harvest_honey", CriterionTriggerInteractBlock.a.a(CriterionConditionLocation.a.a().a(CriterionConditionBlock.a.a().a(TagsBlock.BEEHIVES).b()).a(Boolean.valueOf(true)), CriterionConditionItem.a.a().a(Items.GLASS_BOTTLE)))
/* 192 */       .a(Items.HONEY_BOTTLE, new ChatMessage("advancements.husbandry.safely_harvest_honey.title"), new ChatMessage("advancements.husbandry.safely_harvest_honey.description"), (MinecraftKey)null, AdvancementFrameType.TASK, true, true, false)
/* 193 */       .a(var0, "husbandry/safely_harvest_honey");
/*     */     
/* 195 */     Advancement.SerializedAdvancement.a()
/* 196 */       .a(var1)
/* 197 */       .a("silk_touch_nest", CriterionTriggerBeeNestDestroyed.a.a(Blocks.BEE_NEST, CriterionConditionItem.a.a().a(new CriterionConditionEnchantments(Enchantments.SILK_TOUCH, CriterionConditionValue.IntegerRange.b(1))), CriterionConditionValue.IntegerRange.a(3)))
/* 198 */       .a(Blocks.BEE_NEST, new ChatMessage("advancements.husbandry.silk_touch_nest.title"), new ChatMessage("advancements.husbandry.silk_touch_nest.description"), (MinecraftKey)null, AdvancementFrameType.TASK, true, true, false)
/* 199 */       .a(var0, "husbandry/silk_touch_nest");
/*     */   }
/*     */   
/*     */   private Advancement.SerializedAdvancement a(Advancement.SerializedAdvancement var0) {
/* 203 */     for (Item var4 : d) {
/* 204 */       var0.a(IRegistry.ITEM.getKey(var4).getKey(), CriterionTriggerConsumeItem.a.a(var4));
/*     */     }
/* 206 */     return var0;
/*     */   }
/*     */   
/*     */   private Advancement.SerializedAdvancement b(Advancement.SerializedAdvancement var0) {
/* 210 */     for (EntityTypes<?> var4 : a) {
/* 211 */       var0.a(EntityTypes.getName(var4).toString(), CriterionTriggerBredAnimals.a.a(CriterionConditionEntity.a.a().a(var4)));
/*     */     }
/* 213 */     var0.a(EntityTypes.getName(EntityTypes.TURTLE).toString(), CriterionTriggerBredAnimals.a.a(CriterionConditionEntity.a.a().a(EntityTypes.TURTLE).b(), CriterionConditionEntity.a.a().a(EntityTypes.TURTLE).b(), CriterionConditionEntity.a));
/* 214 */     return var0;
/*     */   }
/*     */   
/*     */   private Advancement.SerializedAdvancement c(Advancement.SerializedAdvancement var0) {
/* 218 */     for (Item var4 : c) {
/* 219 */       var0.a(IRegistry.ITEM.getKey(var4).getKey(), CriterionTriggerFilledBucket.a.a(CriterionConditionItem.a.a().a(var4).b()));
/*     */     }
/* 221 */     return var0;
/*     */   }
/*     */   
/*     */   private Advancement.SerializedAdvancement d(Advancement.SerializedAdvancement var0) {
/* 225 */     for (Item var4 : b) {
/* 226 */       var0.a(IRegistry.ITEM.getKey(var4).getKey(), CriterionTriggerFishingRodHooked.a.a(CriterionConditionItem.a, CriterionConditionEntity.a, CriterionConditionItem.a.a().a(var4).b()));
/*     */     }
/* 228 */     return var0;
/*     */   }
/*     */   
/*     */   private Advancement.SerializedAdvancement e(Advancement.SerializedAdvancement var0) {
/* 232 */     EntityCat.bq.forEach((var1, var2) -> var0.a(var2.getKey(), CriterionTriggerTamedAnimal.a.a(CriterionConditionEntity.a.a().a(var2).b())));
/*     */     
/* 234 */     return var0;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\hq.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */