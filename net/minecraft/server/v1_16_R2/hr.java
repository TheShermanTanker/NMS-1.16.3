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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class hr
/*     */   implements Consumer<Consumer<Advancement>>
/*     */ {
/*  59 */   private static final List<ResourceKey<BiomeBase>> a = (List<ResourceKey<BiomeBase>>)ImmutableList.of(Biomes.NETHER_WASTES, Biomes.SOUL_SAND_VALLEY, Biomes.WARPED_FOREST, Biomes.CRIMSON_FOREST, Biomes.BASALT_DELTAS);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  67 */   private static final CriterionConditionEntity.b b = CriterionConditionEntity.b.a(new LootItemCondition[] {
/*  68 */         LootItemConditionEntityProperty.a(LootTableInfo.EntityTarget.THIS, CriterionConditionEntity.a.a().a(CriterionConditionEntityEquipment.a.a().a(CriterionConditionItem.a.a().a(Items.GOLDEN_HELMET).b()).b())).a().build(), 
/*  69 */         LootItemConditionEntityProperty.a(LootTableInfo.EntityTarget.THIS, CriterionConditionEntity.a.a().a(CriterionConditionEntityEquipment.a.a().b(CriterionConditionItem.a.a().a(Items.GOLDEN_CHESTPLATE).b()).b())).a().build(), 
/*  70 */         LootItemConditionEntityProperty.a(LootTableInfo.EntityTarget.THIS, CriterionConditionEntity.a.a().a(CriterionConditionEntityEquipment.a.a().c(CriterionConditionItem.a.a().a(Items.GOLDEN_LEGGINGS).b()).b())).a().build(), 
/*  71 */         LootItemConditionEntityProperty.a(LootTableInfo.EntityTarget.THIS, CriterionConditionEntity.a.a().a(CriterionConditionEntityEquipment.a.a().d(CriterionConditionItem.a.a().a(Items.GOLDEN_BOOTS).b()).b())).a().build()
/*     */       });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void accept(Consumer<Advancement> var0) {
/*  79 */     Advancement var1 = Advancement.SerializedAdvancement.a().a(Blocks.RED_NETHER_BRICKS, new ChatMessage("advancements.nether.root.title"), new ChatMessage("advancements.nether.root.description"), new MinecraftKey("textures/gui/advancements/backgrounds/nether.png"), AdvancementFrameType.TASK, false, false, false).a("entered_nether", CriterionTriggerChangedDimension.a.a(World.THE_NETHER)).a(var0, "nether/root");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  86 */     Advancement var2 = Advancement.SerializedAdvancement.a().a(var1).a(Items.FIRE_CHARGE, new ChatMessage("advancements.nether.return_to_sender.title"), new ChatMessage("advancements.nether.return_to_sender.description"), (MinecraftKey)null, AdvancementFrameType.CHALLENGE, true, true, false).a(AdvancementRewards.a.a(50)).a("killed_ghast", CriterionTriggerKilled.a.a(CriterionConditionEntity.a.a().a(EntityTypes.GHAST), CriterionConditionDamageSource.a.a().a(Boolean.valueOf(true)).a(CriterionConditionEntity.a.a().a(EntityTypes.FIREBALL)))).a(var0, "nether/return_to_sender");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  92 */     Advancement var3 = Advancement.SerializedAdvancement.a().a(var1).a(Blocks.NETHER_BRICKS, new ChatMessage("advancements.nether.find_fortress.title"), new ChatMessage("advancements.nether.find_fortress.description"), (MinecraftKey)null, AdvancementFrameType.TASK, true, true, false).a("fortress", CriterionTriggerLocation.a.a(CriterionConditionLocation.a(StructureGenerator.FORTRESS))).a(var0, "nether/find_fortress");
/*     */     
/*  94 */     Advancement.SerializedAdvancement.a()
/*  95 */       .a(var1)
/*  96 */       .a(Items.MAP, new ChatMessage("advancements.nether.fast_travel.title"), new ChatMessage("advancements.nether.fast_travel.description"), (MinecraftKey)null, AdvancementFrameType.CHALLENGE, true, true, false)
/*  97 */       .a(AdvancementRewards.a.a(100))
/*  98 */       .a("travelled", CriterionTriggerNetherTravel.a.a(CriterionConditionDistance.a(CriterionConditionValue.FloatRange.b(7000.0F))))
/*  99 */       .a(var0, "nether/fast_travel");
/*     */     
/* 101 */     Advancement.SerializedAdvancement.a()
/* 102 */       .a(var2)
/* 103 */       .a(Items.GHAST_TEAR, new ChatMessage("advancements.nether.uneasy_alliance.title"), new ChatMessage("advancements.nether.uneasy_alliance.description"), (MinecraftKey)null, AdvancementFrameType.CHALLENGE, true, true, false)
/* 104 */       .a(AdvancementRewards.a.a(100))
/* 105 */       .a("killed_ghast", CriterionTriggerKilled.a.a(CriterionConditionEntity.a.a().a(EntityTypes.GHAST).a(CriterionConditionLocation.b(World.OVERWORLD))))
/* 106 */       .a(var0, "nether/uneasy_alliance");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 112 */     Advancement var4 = Advancement.SerializedAdvancement.a().a(var3).a(Blocks.WITHER_SKELETON_SKULL, new ChatMessage("advancements.nether.get_wither_skull.title"), new ChatMessage("advancements.nether.get_wither_skull.description"), (MinecraftKey)null, AdvancementFrameType.TASK, true, true, false).a("wither_skull", CriterionTriggerInventoryChanged.a.a(new IMaterial[] { Blocks.WITHER_SKELETON_SKULL })).a(var0, "nether/get_wither_skull");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 118 */     Advancement var5 = Advancement.SerializedAdvancement.a().a(var4).a(Items.NETHER_STAR, new ChatMessage("advancements.nether.summon_wither.title"), new ChatMessage("advancements.nether.summon_wither.description"), (MinecraftKey)null, AdvancementFrameType.TASK, true, true, false).a("summoned", CriterionTriggerSummonedEntity.a.a(CriterionConditionEntity.a.a().a(EntityTypes.WITHER))).a(var0, "nether/summon_wither");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 124 */     Advancement var6 = Advancement.SerializedAdvancement.a().a(var3).a(Items.BLAZE_ROD, new ChatMessage("advancements.nether.obtain_blaze_rod.title"), new ChatMessage("advancements.nether.obtain_blaze_rod.description"), (MinecraftKey)null, AdvancementFrameType.TASK, true, true, false).a("blaze_rod", CriterionTriggerInventoryChanged.a.a(new IMaterial[] { Items.BLAZE_ROD })).a(var0, "nether/obtain_blaze_rod");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 130 */     Advancement var7 = Advancement.SerializedAdvancement.a().a(var5).a(Blocks.BEACON, new ChatMessage("advancements.nether.create_beacon.title"), new ChatMessage("advancements.nether.create_beacon.description"), (MinecraftKey)null, AdvancementFrameType.TASK, true, true, false).a("beacon", CriterionTriggerConstructBeacon.a.a(CriterionConditionValue.IntegerRange.b(1))).a(var0, "nether/create_beacon");
/*     */     
/* 132 */     Advancement.SerializedAdvancement.a()
/* 133 */       .a(var7)
/* 134 */       .a(Blocks.BEACON, new ChatMessage("advancements.nether.create_full_beacon.title"), new ChatMessage("advancements.nether.create_full_beacon.description"), (MinecraftKey)null, AdvancementFrameType.GOAL, true, true, false)
/* 135 */       .a("beacon", CriterionTriggerConstructBeacon.a.a(CriterionConditionValue.IntegerRange.a(4)))
/* 136 */       .a(var0, "nether/create_full_beacon");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 142 */     Advancement var8 = Advancement.SerializedAdvancement.a().a(var6).a(Items.POTION, new ChatMessage("advancements.nether.brew_potion.title"), new ChatMessage("advancements.nether.brew_potion.description"), (MinecraftKey)null, AdvancementFrameType.TASK, true, true, false).a("potion", CriterionTriggerBrewedPotion.a.c()).a(var0, "nether/brew_potion");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 149 */     Advancement var9 = Advancement.SerializedAdvancement.a().a(var8).a(Items.MILK_BUCKET, new ChatMessage("advancements.nether.all_potions.title"), new ChatMessage("advancements.nether.all_potions.description"), (MinecraftKey)null, AdvancementFrameType.CHALLENGE, true, true, false).a(AdvancementRewards.a.a(100)).a("all_effects", CriterionTriggerEffectsChanged.a.a(CriterionConditionMobEffect.a().a(MobEffects.FASTER_MOVEMENT).a(MobEffects.SLOWER_MOVEMENT).a(MobEffects.INCREASE_DAMAGE).a(MobEffects.JUMP).a(MobEffects.REGENERATION).a(MobEffects.FIRE_RESISTANCE).a(MobEffects.WATER_BREATHING).a(MobEffects.INVISIBILITY).a(MobEffects.NIGHT_VISION).a(MobEffects.WEAKNESS).a(MobEffects.POISON).a(MobEffects.SLOW_FALLING).a(MobEffects.RESISTANCE))).a(var0, "nether/all_potions");
/*     */     
/* 151 */     Advancement.SerializedAdvancement.a()
/* 152 */       .a(var9)
/* 153 */       .a(Items.BUCKET, new ChatMessage("advancements.nether.all_effects.title"), new ChatMessage("advancements.nether.all_effects.description"), (MinecraftKey)null, AdvancementFrameType.CHALLENGE, true, true, true)
/* 154 */       .a(AdvancementRewards.a.a(1000))
/* 155 */       .a("all_effects", CriterionTriggerEffectsChanged.a.a(CriterionConditionMobEffect.a().a(MobEffects.FASTER_MOVEMENT).a(MobEffects.SLOWER_MOVEMENT).a(MobEffects.INCREASE_DAMAGE).a(MobEffects.JUMP).a(MobEffects.REGENERATION).a(MobEffects.FIRE_RESISTANCE).a(MobEffects.WATER_BREATHING).a(MobEffects.INVISIBILITY).a(MobEffects.NIGHT_VISION).a(MobEffects.WEAKNESS).a(MobEffects.POISON).a(MobEffects.WITHER).a(MobEffects.FASTER_DIG).a(MobEffects.SLOWER_DIG).a(MobEffects.LEVITATION).a(MobEffects.GLOWING).a(MobEffects.ABSORBTION).a(MobEffects.HUNGER).a(MobEffects.CONFUSION).a(MobEffects.RESISTANCE).a(MobEffects.SLOW_FALLING).a(MobEffects.CONDUIT_POWER).a(MobEffects.DOLPHINS_GRACE).a(MobEffects.BLINDNESS).a(MobEffects.BAD_OMEN).a(MobEffects.HERO_OF_THE_VILLAGE)))
/* 156 */       .a(var0, "nether/all_effects");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 162 */     Advancement var10 = Advancement.SerializedAdvancement.a().a(var1).a(Items.ry, new ChatMessage("advancements.nether.obtain_ancient_debris.title"), new ChatMessage("advancements.nether.obtain_ancient_debris.description"), (MinecraftKey)null, AdvancementFrameType.TASK, true, true, false).a("ancient_debris", CriterionTriggerInventoryChanged.a.a(new IMaterial[] { Items.ry })).a(var0, "nether/obtain_ancient_debris");
/*     */     
/* 164 */     Advancement.SerializedAdvancement.a()
/* 165 */       .a(var10)
/* 166 */       .a(Items.NETHERITE_CHESTPLATE, new ChatMessage("advancements.nether.netherite_armor.title"), new ChatMessage("advancements.nether.netherite_armor.description"), (MinecraftKey)null, AdvancementFrameType.CHALLENGE, true, true, false)
/* 167 */       .a(AdvancementRewards.a.a(100))
/* 168 */       .a("netherite_armor", CriterionTriggerInventoryChanged.a.a(new IMaterial[] { Items.NETHERITE_HELMET, Items.NETHERITE_CHESTPLATE, Items.NETHERITE_LEGGINGS, Items.NETHERITE_BOOTS
/* 169 */           })).a(var0, "nether/netherite_armor");
/*     */     
/* 171 */     Advancement.SerializedAdvancement.a()
/* 172 */       .a(var10)
/* 173 */       .a(Items.rw, new ChatMessage("advancements.nether.use_lodestone.title"), new ChatMessage("advancements.nether.use_lodestone.description"), (MinecraftKey)null, AdvancementFrameType.TASK, true, true, false)
/* 174 */       .a("use_lodestone", CriterionTriggerInteractBlock.a.a(CriterionConditionLocation.a.a().a(CriterionConditionBlock.a.a().a(Blocks.LODESTONE).b()), CriterionConditionItem.a.a().a(Items.COMPASS)))
/* 175 */       .a(var0, "nether/use_lodestone");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 181 */     Advancement var11 = Advancement.SerializedAdvancement.a().a(var1).a(Items.rA, new ChatMessage("advancements.nether.obtain_crying_obsidian.title"), new ChatMessage("advancements.nether.obtain_crying_obsidian.description"), (MinecraftKey)null, AdvancementFrameType.TASK, true, true, false).a("crying_obsidian", CriterionTriggerInventoryChanged.a.a(new IMaterial[] { Items.rA })).a(var0, "nether/obtain_crying_obsidian");
/*     */     
/* 183 */     Advancement.SerializedAdvancement.a()
/* 184 */       .a(var11)
/* 185 */       .a(Items.rN, new ChatMessage("advancements.nether.charge_respawn_anchor.title"), new ChatMessage("advancements.nether.charge_respawn_anchor.description"), (MinecraftKey)null, AdvancementFrameType.TASK, true, true, false)
/* 186 */       .a("charge_respawn_anchor", CriterionTriggerInteractBlock.a.a(CriterionConditionLocation.a.a().a(CriterionConditionBlock.a.a().a(Blocks.RESPAWN_ANCHOR).a(CriterionTriggerProperties.a.a().a(BlockRespawnAnchor.a, 4).b()).b()), CriterionConditionItem.a.a().a(Blocks.GLOWSTONE)))
/* 187 */       .a(var0, "nether/charge_respawn_anchor");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 196 */     Advancement var12 = Advancement.SerializedAdvancement.a().a(var1).a(Items.WARPED_FUNGUS_ON_A_STICK, new ChatMessage("advancements.nether.ride_strider.title"), new ChatMessage("advancements.nether.ride_strider.description"), (MinecraftKey)null, AdvancementFrameType.TASK, true, true, false).a("used_warped_fungus_on_a_stick", CriterionTriggerItemDurabilityChanged.a.a(CriterionConditionEntity.b.a(CriterionConditionEntity.a.a().a(CriterionConditionEntity.a.a().a(EntityTypes.STRIDER).b()).b()), CriterionConditionItem.a.a().a(Items.WARPED_FUNGUS_ON_A_STICK).b(), CriterionConditionValue.IntegerRange.e)).a(var0, "nether/ride_strider");
/*     */     
/* 198 */     hp.a(Advancement.SerializedAdvancement.a(), a)
/* 199 */       .a(var12)
/* 200 */       .a(Items.NETHERITE_BOOTS, new ChatMessage("advancements.nether.explore_nether.title"), new ChatMessage("advancements.nether.explore_nether.description"), (MinecraftKey)null, AdvancementFrameType.CHALLENGE, true, true, false)
/* 201 */       .a(AdvancementRewards.a.a(500))
/* 202 */       .a(var0, "nether/explore_nether");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 208 */     Advancement var13 = Advancement.SerializedAdvancement.a().a(var1).a(Items.rJ, new ChatMessage("advancements.nether.find_bastion.title"), new ChatMessage("advancements.nether.find_bastion.description"), (MinecraftKey)null, AdvancementFrameType.TASK, true, true, false).a("bastion", CriterionTriggerLocation.a.a(CriterionConditionLocation.a(StructureGenerator.BASTION_REMNANT))).a(var0, "nether/find_bastion");
/*     */     
/* 210 */     Advancement.SerializedAdvancement.a()
/* 211 */       .a(var13)
/* 212 */       .a(Blocks.CHEST, new ChatMessage("advancements.nether.loot_bastion.title"), new ChatMessage("advancements.nether.loot_bastion.description"), (MinecraftKey)null, AdvancementFrameType.TASK, true, true, false)
/* 213 */       .a(AdvancementRequirements.OR)
/* 214 */       .a("loot_bastion_other", CriterionTriggerPlayerGeneratesContainerLoot.a.a(new MinecraftKey("minecraft:chests/bastion_other")))
/* 215 */       .a("loot_bastion_treasure", CriterionTriggerPlayerGeneratesContainerLoot.a.a(new MinecraftKey("minecraft:chests/bastion_treasure")))
/* 216 */       .a("loot_bastion_hoglin_stable", CriterionTriggerPlayerGeneratesContainerLoot.a.a(new MinecraftKey("minecraft:chests/bastion_hoglin_stable")))
/* 217 */       .a("loot_bastion_bridge", CriterionTriggerPlayerGeneratesContainerLoot.a.a(new MinecraftKey("minecraft:chests/bastion_bridge")))
/* 218 */       .a(var0, "nether/loot_bastion");
/*     */     
/* 220 */     Advancement.SerializedAdvancement.a()
/* 221 */       .a(var1)
/* 222 */       .a(AdvancementRequirements.OR)
/* 223 */       .a(Items.GOLD_INGOT, new ChatMessage("advancements.nether.distract_piglin.title"), new ChatMessage("advancements.nether.distract_piglin.description"), (MinecraftKey)null, AdvancementFrameType.TASK, true, true, false)
/* 224 */       .a("distract_piglin", CriterionTriggerThrownItemPickedUpByEntity.a.a(b, 
/*     */           
/* 226 */           CriterionConditionItem.a.a().a(TagsItem.PIGLIN_LOVED), 
/* 227 */           CriterionConditionEntity.b.a(CriterionConditionEntity.a.a().a(EntityTypes.PIGLIN).a(CriterionConditionEntityFlags.a.a().e(Boolean.valueOf(false)).b()).b())))
/*     */       
/* 229 */       .a("distract_piglin_directly", CriterionTriggerPlayerInteractedWithEntity.a.a(b, 
/*     */           
/* 231 */           CriterionConditionItem.a.a().a(PiglinAI.a), 
/* 232 */           CriterionConditionEntity.b.a(CriterionConditionEntity.a.a().a(EntityTypes.PIGLIN).a(CriterionConditionEntityFlags.a.a().e(Boolean.valueOf(false)).b()).b())))
/*     */       
/* 234 */       .a(var0, "nether/distract_piglin");
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\hr.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */