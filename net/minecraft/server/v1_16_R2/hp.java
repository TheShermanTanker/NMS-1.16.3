/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import java.util.List;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class hp
/*     */   implements Consumer<Consumer<Advancement>>
/*     */ {
/*  50 */   private static final List<ResourceKey<BiomeBase>> a = (List<ResourceKey<BiomeBase>>)ImmutableList.of(Biomes.BIRCH_FOREST_HILLS, Biomes.RIVER, Biomes.SWAMP, Biomes.DESERT, Biomes.WOODED_HILLS, Biomes.GIANT_TREE_TAIGA_HILLS, Biomes.SNOWY_TAIGA, Biomes.BADLANDS, Biomes.FOREST, Biomes.STONE_SHORE, Biomes.SNOWY_TUNDRA, Biomes.TAIGA_HILLS, (Object[])new ResourceKey[] { Biomes.SNOWY_MOUNTAINS, Biomes.WOODED_BADLANDS_PLATEAU, Biomes.SAVANNA, Biomes.PLAINS, Biomes.FROZEN_RIVER, Biomes.GIANT_TREE_TAIGA, Biomes.SNOWY_BEACH, Biomes.JUNGLE_HILLS, Biomes.JUNGLE_EDGE, Biomes.MUSHROOM_FIELD_SHORE, Biomes.MOUNTAINS, Biomes.DESERT_HILLS, Biomes.JUNGLE, Biomes.BEACH, Biomes.SAVANNA_PLATEAU, Biomes.SNOWY_TAIGA_HILLS, Biomes.BADLANDS_PLATEAU, Biomes.DARK_FOREST, Biomes.TAIGA, Biomes.BIRCH_FOREST, Biomes.MUSHROOM_FIELDS, Biomes.WOODED_MOUNTAINS, Biomes.WARM_OCEAN, Biomes.LUKEWARM_OCEAN, Biomes.COLD_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN, Biomes.DEEP_COLD_OCEAN, Biomes.DEEP_FROZEN_OCEAN, Biomes.BAMBOO_JUNGLE, Biomes.BAMBOO_JUNGLE_HILLS });
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
/*  96 */   private static final EntityTypes<?>[] b = new EntityTypes[] { EntityTypes.BLAZE, EntityTypes.CAVE_SPIDER, EntityTypes.CREEPER, EntityTypes.DROWNED, EntityTypes.ELDER_GUARDIAN, EntityTypes.ENDER_DRAGON, EntityTypes.ENDERMAN, EntityTypes.ENDERMITE, EntityTypes.EVOKER, EntityTypes.GHAST, EntityTypes.GUARDIAN, EntityTypes.HOGLIN, EntityTypes.HUSK, EntityTypes.MAGMA_CUBE, EntityTypes.PHANTOM, EntityTypes.PIGLIN, EntityTypes.PIGLIN_BRUTE, EntityTypes.PILLAGER, EntityTypes.RAVAGER, EntityTypes.SHULKER, EntityTypes.SILVERFISH, EntityTypes.SKELETON, EntityTypes.SLIME, EntityTypes.SPIDER, EntityTypes.STRAY, EntityTypes.VEX, EntityTypes.VINDICATOR, EntityTypes.WITCH, EntityTypes.WITHER_SKELETON, EntityTypes.WITHER, EntityTypes.ZOGLIN, EntityTypes.ZOMBIE_VILLAGER, EntityTypes.ZOMBIE, EntityTypes.ZOMBIFIED_PIGLIN };
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
/* 140 */     Advancement var1 = Advancement.SerializedAdvancement.a().a(Items.MAP, new ChatMessage("advancements.adventure.root.title"), new ChatMessage("advancements.adventure.root.description"), new MinecraftKey("textures/gui/advancements/backgrounds/adventure.png"), AdvancementFrameType.TASK, false, false, false).a(AdvancementRequirements.OR).a("killed_something", CriterionTriggerKilled.a.c()).a("killed_by_something", CriterionTriggerKilled.a.d()).a(var0, "adventure/root");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 146 */     Advancement var2 = Advancement.SerializedAdvancement.a().a(var1).a(Blocks.RED_BED, new ChatMessage("advancements.adventure.sleep_in_bed.title"), new ChatMessage("advancements.adventure.sleep_in_bed.description"), (MinecraftKey)null, AdvancementFrameType.TASK, true, true, false).a("slept_in_bed", CriterionTriggerLocation.a.c()).a(var0, "adventure/sleep_in_bed");
/*     */     
/* 148 */     a(Advancement.SerializedAdvancement.a(), a)
/* 149 */       .a(var2)
/* 150 */       .a(Items.DIAMOND_BOOTS, new ChatMessage("advancements.adventure.adventuring_time.title"), new ChatMessage("advancements.adventure.adventuring_time.description"), (MinecraftKey)null, AdvancementFrameType.CHALLENGE, true, true, false)
/* 151 */       .a(AdvancementRewards.a.a(500))
/* 152 */       .a(var0, "adventure/adventuring_time");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 158 */     Advancement var3 = Advancement.SerializedAdvancement.a().a(var1).a(Items.EMERALD, new ChatMessage("advancements.adventure.trade.title"), new ChatMessage("advancements.adventure.trade.description"), (MinecraftKey)null, AdvancementFrameType.TASK, true, true, false).a("traded", CriterionTriggerVillagerTrade.a.c()).a(var0, "adventure/trade");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 164 */     Advancement var4 = a(Advancement.SerializedAdvancement.a()).a(var1).a(Items.IRON_SWORD, new ChatMessage("advancements.adventure.kill_a_mob.title"), new ChatMessage("advancements.adventure.kill_a_mob.description"), (MinecraftKey)null, AdvancementFrameType.TASK, true, true, false).a(AdvancementRequirements.OR).a(var0, "adventure/kill_a_mob");
/*     */     
/* 166 */     a(Advancement.SerializedAdvancement.a())
/* 167 */       .a(var4)
/* 168 */       .a(Items.DIAMOND_SWORD, new ChatMessage("advancements.adventure.kill_all_mobs.title"), new ChatMessage("advancements.adventure.kill_all_mobs.description"), (MinecraftKey)null, AdvancementFrameType.CHALLENGE, true, true, false)
/* 169 */       .a(AdvancementRewards.a.a(100))
/* 170 */       .a(var0, "adventure/kill_all_mobs");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 176 */     Advancement var5 = Advancement.SerializedAdvancement.a().a(var4).a(Items.BOW, new ChatMessage("advancements.adventure.shoot_arrow.title"), new ChatMessage("advancements.adventure.shoot_arrow.description"), (MinecraftKey)null, AdvancementFrameType.TASK, true, true, false).a("shot_arrow", CriterionTriggerPlayerHurtEntity.a.a(CriterionConditionDamage.a.a().a(CriterionConditionDamageSource.a.a().a(Boolean.valueOf(true)).a(CriterionConditionEntity.a.a().a(TagsEntity.ARROWS))))).a(var0, "adventure/shoot_arrow");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 182 */     Advancement var6 = Advancement.SerializedAdvancement.a().a(var4).a(Items.TRIDENT, new ChatMessage("advancements.adventure.throw_trident.title"), new ChatMessage("advancements.adventure.throw_trident.description"), (MinecraftKey)null, AdvancementFrameType.TASK, true, true, false).a("shot_trident", CriterionTriggerPlayerHurtEntity.a.a(CriterionConditionDamage.a.a().a(CriterionConditionDamageSource.a.a().a(Boolean.valueOf(true)).a(CriterionConditionEntity.a.a().a(EntityTypes.TRIDENT))))).a(var0, "adventure/throw_trident");
/*     */     
/* 184 */     Advancement.SerializedAdvancement.a()
/* 185 */       .a(var6)
/* 186 */       .a(Items.TRIDENT, new ChatMessage("advancements.adventure.very_very_frightening.title"), new ChatMessage("advancements.adventure.very_very_frightening.description"), (MinecraftKey)null, AdvancementFrameType.TASK, true, true, false)
/* 187 */       .a("struck_villager", CriterionTriggerChanneledLightning.a.a(new CriterionConditionEntity[] { CriterionConditionEntity.a.a().a(EntityTypes.VILLAGER).b()
/* 188 */           })).a(var0, "adventure/very_very_frightening");
/*     */     
/* 190 */     Advancement.SerializedAdvancement.a()
/* 191 */       .a(var3)
/* 192 */       .a(Blocks.CARVED_PUMPKIN, new ChatMessage("advancements.adventure.summon_iron_golem.title"), new ChatMessage("advancements.adventure.summon_iron_golem.description"), (MinecraftKey)null, AdvancementFrameType.GOAL, true, true, false)
/* 193 */       .a("summoned_golem", CriterionTriggerSummonedEntity.a.a(CriterionConditionEntity.a.a().a(EntityTypes.IRON_GOLEM)))
/* 194 */       .a(var0, "adventure/summon_iron_golem");
/*     */     
/* 196 */     Advancement.SerializedAdvancement.a()
/* 197 */       .a(var5)
/* 198 */       .a(Items.ARROW, new ChatMessage("advancements.adventure.sniper_duel.title"), new ChatMessage("advancements.adventure.sniper_duel.description"), (MinecraftKey)null, AdvancementFrameType.CHALLENGE, true, true, false)
/* 199 */       .a(AdvancementRewards.a.a(50))
/* 200 */       .a("killed_skeleton", CriterionTriggerKilled.a.a(CriterionConditionEntity.a.a().a(EntityTypes.SKELETON).a(CriterionConditionDistance.a(CriterionConditionValue.FloatRange.b(50.0F))), CriterionConditionDamageSource.a.a().a(Boolean.valueOf(true))))
/* 201 */       .a(var0, "adventure/sniper_duel");
/*     */     
/* 203 */     Advancement.SerializedAdvancement.a()
/* 204 */       .a(var4)
/* 205 */       .a(Items.TOTEM_OF_UNDYING, new ChatMessage("advancements.adventure.totem_of_undying.title"), new ChatMessage("advancements.adventure.totem_of_undying.description"), (MinecraftKey)null, AdvancementFrameType.GOAL, true, true, false)
/* 206 */       .a("used_totem", CriterionTriggerUsedTotem.a.a(Items.TOTEM_OF_UNDYING))
/* 207 */       .a(var0, "adventure/totem_of_undying");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 213 */     Advancement var7 = Advancement.SerializedAdvancement.a().a(var1).a(Items.CROSSBOW, new ChatMessage("advancements.adventure.ol_betsy.title"), new ChatMessage("advancements.adventure.ol_betsy.description"), (MinecraftKey)null, AdvancementFrameType.TASK, true, true, false).a("shot_crossbow", CriterionTriggerShotCrossbow.a.a(Items.CROSSBOW)).a(var0, "adventure/ol_betsy");
/*     */     
/* 215 */     Advancement.SerializedAdvancement.a()
/* 216 */       .a(var7)
/* 217 */       .a(Items.CROSSBOW, new ChatMessage("advancements.adventure.whos_the_pillager_now.title"), new ChatMessage("advancements.adventure.whos_the_pillager_now.description"), (MinecraftKey)null, AdvancementFrameType.TASK, true, true, false)
/* 218 */       .a("kill_pillager", CriterionTriggerKilledByCrossbow.a.a(new CriterionConditionEntity.a[] { CriterionConditionEntity.a.a().a(EntityTypes.PILLAGER)
/* 219 */           })).a(var0, "adventure/whos_the_pillager_now");
/*     */     
/* 221 */     Advancement.SerializedAdvancement.a()
/* 222 */       .a(var7)
/* 223 */       .a(Items.CROSSBOW, new ChatMessage("advancements.adventure.two_birds_one_arrow.title"), new ChatMessage("advancements.adventure.two_birds_one_arrow.description"), (MinecraftKey)null, AdvancementFrameType.CHALLENGE, true, true, false)
/* 224 */       .a(AdvancementRewards.a.a(65))
/* 225 */       .a("two_birds", CriterionTriggerKilledByCrossbow.a.a(new CriterionConditionEntity.a[] { CriterionConditionEntity.a.a().a(EntityTypes.PHANTOM), CriterionConditionEntity.a.a().a(EntityTypes.PHANTOM)
/* 226 */           })).a(var0, "adventure/two_birds_one_arrow");
/*     */     
/* 228 */     Advancement.SerializedAdvancement.a()
/* 229 */       .a(var7)
/* 230 */       .a(Items.CROSSBOW, new ChatMessage("advancements.adventure.arbalistic.title"), new ChatMessage("advancements.adventure.arbalistic.description"), (MinecraftKey)null, AdvancementFrameType.CHALLENGE, true, true, true)
/* 231 */       .a(AdvancementRewards.a.a(85))
/* 232 */       .a("arbalistic", CriterionTriggerKilledByCrossbow.a.a(CriterionConditionValue.IntegerRange.a(5)))
/* 233 */       .a(var0, "adventure/arbalistic");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 239 */     Advancement var8 = Advancement.SerializedAdvancement.a().a(var1).a(Raid.s(), new ChatMessage("advancements.adventure.voluntary_exile.title"), new ChatMessage("advancements.adventure.voluntary_exile.description"), (MinecraftKey)null, AdvancementFrameType.TASK, true, true, true).a("voluntary_exile", CriterionTriggerKilled.a.a(CriterionConditionEntity.a.a().a(TagsEntity.RADIERS).a(CriterionConditionEntityEquipment.b))).a(var0, "adventure/voluntary_exile");
/*     */     
/* 241 */     Advancement.SerializedAdvancement.a()
/* 242 */       .a(var8)
/* 243 */       .a(Raid.s(), new ChatMessage("advancements.adventure.hero_of_the_village.title"), new ChatMessage("advancements.adventure.hero_of_the_village.description"), (MinecraftKey)null, AdvancementFrameType.CHALLENGE, true, true, true)
/* 244 */       .a(AdvancementRewards.a.a(100))
/* 245 */       .a("hero_of_the_village", CriterionTriggerLocation.a.d())
/* 246 */       .a(var0, "adventure/hero_of_the_village");
/*     */     
/* 248 */     Advancement.SerializedAdvancement.a()
/* 249 */       .a(var1)
/* 250 */       .a(Blocks.HONEY_BLOCK.getItem(), new ChatMessage("advancements.adventure.honey_block_slide.title"), new ChatMessage("advancements.adventure.honey_block_slide.description"), (MinecraftKey)null, AdvancementFrameType.TASK, true, true, false)
/* 251 */       .a("honey_block_slide", CriterionSlideDownBlock.a.a(Blocks.HONEY_BLOCK))
/* 252 */       .a(var0, "adventure/honey_block_slide");
/*     */     
/* 254 */     Advancement.SerializedAdvancement.a()
/* 255 */       .a(var5)
/* 256 */       .a(Blocks.TARGET.getItem(), new ChatMessage("advancements.adventure.bullseye.title"), new ChatMessage("advancements.adventure.bullseye.description"), (MinecraftKey)null, AdvancementFrameType.CHALLENGE, true, true, false)
/* 257 */       .a(AdvancementRewards.a.a(50))
/* 258 */       .a("bullseye", CriterionTriggerTargetHit.a.a(CriterionConditionValue.IntegerRange.a(15), CriterionConditionEntity.b.a(CriterionConditionEntity.a.a().a(CriterionConditionDistance.a(CriterionConditionValue.FloatRange.b(30.0F))).b())))
/* 259 */       .a(var0, "adventure/bullseye");
/*     */   }
/*     */   
/*     */   private Advancement.SerializedAdvancement a(Advancement.SerializedAdvancement var0) {
/* 263 */     for (EntityTypes<?> var4 : b) {
/* 264 */       var0.a(IRegistry.ENTITY_TYPE.getKey(var4).toString(), CriterionTriggerKilled.a.a(CriterionConditionEntity.a.a().a(var4)));
/*     */     }
/* 266 */     return var0;
/*     */   }
/*     */   
/*     */   protected static Advancement.SerializedAdvancement a(Advancement.SerializedAdvancement var0, List<ResourceKey<BiomeBase>> var1) {
/* 270 */     for (ResourceKey<BiomeBase> var3 : var1) {
/* 271 */       var0.a(var3.a().toString(), CriterionTriggerLocation.a.a(CriterionConditionLocation.a(var3)));
/*     */     }
/* 273 */     return var0;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\hp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */