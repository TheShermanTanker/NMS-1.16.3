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
/*     */ public class hs
/*     */   implements Consumer<Consumer<Advancement>>
/*     */ {
/*     */   public void accept(Consumer<Advancement> var0) {
/*  35 */     Advancement var1 = Advancement.SerializedAdvancement.a().a(Blocks.GRASS_BLOCK, new ChatMessage("advancements.story.root.title"), new ChatMessage("advancements.story.root.description"), new MinecraftKey("textures/gui/advancements/backgrounds/stone.png"), AdvancementFrameType.TASK, false, false, false).a("crafting_table", CriterionTriggerInventoryChanged.a.a(new IMaterial[] { Blocks.CRAFTING_TABLE })).a(var0, "story/root");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  41 */     Advancement var2 = Advancement.SerializedAdvancement.a().a(var1).a(Items.WOODEN_PICKAXE, new ChatMessage("advancements.story.mine_stone.title"), new ChatMessage("advancements.story.mine_stone.description"), (MinecraftKey)null, AdvancementFrameType.TASK, true, true, false).a("get_stone", CriterionTriggerInventoryChanged.a.a(new CriterionConditionItem[] { CriterionConditionItem.a.a().a(TagsItem.STONE_TOOL_MATERIALS).b() })).a(var0, "story/mine_stone");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  47 */     Advancement var3 = Advancement.SerializedAdvancement.a().a(var2).a(Items.STONE_PICKAXE, new ChatMessage("advancements.story.upgrade_tools.title"), new ChatMessage("advancements.story.upgrade_tools.description"), (MinecraftKey)null, AdvancementFrameType.TASK, true, true, false).a("stone_pickaxe", CriterionTriggerInventoryChanged.a.a(new IMaterial[] { Items.STONE_PICKAXE })).a(var0, "story/upgrade_tools");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  53 */     Advancement var4 = Advancement.SerializedAdvancement.a().a(var3).a(Items.IRON_INGOT, new ChatMessage("advancements.story.smelt_iron.title"), new ChatMessage("advancements.story.smelt_iron.description"), (MinecraftKey)null, AdvancementFrameType.TASK, true, true, false).a("iron", CriterionTriggerInventoryChanged.a.a(new IMaterial[] { Items.IRON_INGOT })).a(var0, "story/smelt_iron");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  59 */     Advancement var5 = Advancement.SerializedAdvancement.a().a(var4).a(Items.IRON_PICKAXE, new ChatMessage("advancements.story.iron_tools.title"), new ChatMessage("advancements.story.iron_tools.description"), (MinecraftKey)null, AdvancementFrameType.TASK, true, true, false).a("iron_pickaxe", CriterionTriggerInventoryChanged.a.a(new IMaterial[] { Items.IRON_PICKAXE })).a(var0, "story/iron_tools");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  65 */     Advancement var6 = Advancement.SerializedAdvancement.a().a(var5).a(Items.DIAMOND, new ChatMessage("advancements.story.mine_diamond.title"), new ChatMessage("advancements.story.mine_diamond.description"), (MinecraftKey)null, AdvancementFrameType.TASK, true, true, false).a("diamond", CriterionTriggerInventoryChanged.a.a(new IMaterial[] { Items.DIAMOND })).a(var0, "story/mine_diamond");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  71 */     Advancement var7 = Advancement.SerializedAdvancement.a().a(var4).a(Items.LAVA_BUCKET, new ChatMessage("advancements.story.lava_bucket.title"), new ChatMessage("advancements.story.lava_bucket.description"), (MinecraftKey)null, AdvancementFrameType.TASK, true, true, false).a("lava_bucket", CriterionTriggerInventoryChanged.a.a(new IMaterial[] { Items.LAVA_BUCKET })).a(var0, "story/lava_bucket");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  81 */     Advancement var8 = Advancement.SerializedAdvancement.a().a(var4).a(Items.IRON_CHESTPLATE, new ChatMessage("advancements.story.obtain_armor.title"), new ChatMessage("advancements.story.obtain_armor.description"), (MinecraftKey)null, AdvancementFrameType.TASK, true, true, false).a(AdvancementRequirements.OR).a("iron_helmet", CriterionTriggerInventoryChanged.a.a(new IMaterial[] { Items.IRON_HELMET })).a("iron_chestplate", CriterionTriggerInventoryChanged.a.a(new IMaterial[] { Items.IRON_CHESTPLATE })).a("iron_leggings", CriterionTriggerInventoryChanged.a.a(new IMaterial[] { Items.IRON_LEGGINGS })).a("iron_boots", CriterionTriggerInventoryChanged.a.a(new IMaterial[] { Items.IRON_BOOTS })).a(var0, "story/obtain_armor");
/*     */     
/*  83 */     Advancement.SerializedAdvancement.a()
/*  84 */       .a(var6)
/*  85 */       .a(Items.ENCHANTED_BOOK, new ChatMessage("advancements.story.enchant_item.title"), new ChatMessage("advancements.story.enchant_item.description"), (MinecraftKey)null, AdvancementFrameType.TASK, true, true, false)
/*  86 */       .a("enchanted_item", CriterionTriggerEnchantedItem.a.c())
/*  87 */       .a(var0, "story/enchant_item");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  93 */     Advancement var9 = Advancement.SerializedAdvancement.a().a(var7).a(Blocks.OBSIDIAN, new ChatMessage("advancements.story.form_obsidian.title"), new ChatMessage("advancements.story.form_obsidian.description"), (MinecraftKey)null, AdvancementFrameType.TASK, true, true, false).a("obsidian", CriterionTriggerInventoryChanged.a.a(new IMaterial[] { Blocks.OBSIDIAN })).a(var0, "story/form_obsidian");
/*     */     
/*  95 */     Advancement.SerializedAdvancement.a()
/*  96 */       .a(var8)
/*  97 */       .a(Items.SHIELD, new ChatMessage("advancements.story.deflect_arrow.title"), new ChatMessage("advancements.story.deflect_arrow.description"), (MinecraftKey)null, AdvancementFrameType.TASK, true, true, false)
/*  98 */       .a("deflected_projectile", CriterionTriggerEntityHurtPlayer.a.a(CriterionConditionDamage.a.a().a(CriterionConditionDamageSource.a.a().a(Boolean.valueOf(true))).a(Boolean.valueOf(true))))
/*  99 */       .a(var0, "story/deflect_arrow");
/*     */     
/* 101 */     Advancement.SerializedAdvancement.a()
/* 102 */       .a(var6)
/* 103 */       .a(Items.DIAMOND_CHESTPLATE, new ChatMessage("advancements.story.shiny_gear.title"), new ChatMessage("advancements.story.shiny_gear.description"), (MinecraftKey)null, AdvancementFrameType.TASK, true, true, false)
/* 104 */       .a(AdvancementRequirements.OR)
/* 105 */       .a("diamond_helmet", CriterionTriggerInventoryChanged.a.a(new IMaterial[] { Items.DIAMOND_HELMET
/* 106 */           })).a("diamond_chestplate", CriterionTriggerInventoryChanged.a.a(new IMaterial[] { Items.DIAMOND_CHESTPLATE
/* 107 */           })).a("diamond_leggings", CriterionTriggerInventoryChanged.a.a(new IMaterial[] { Items.DIAMOND_LEGGINGS
/* 108 */           })).a("diamond_boots", CriterionTriggerInventoryChanged.a.a(new IMaterial[] { Items.DIAMOND_BOOTS
/* 109 */           })).a(var0, "story/shiny_gear");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 115 */     Advancement var10 = Advancement.SerializedAdvancement.a().a(var9).a(Items.FLINT_AND_STEEL, new ChatMessage("advancements.story.enter_the_nether.title"), new ChatMessage("advancements.story.enter_the_nether.description"), (MinecraftKey)null, AdvancementFrameType.TASK, true, true, false).a("entered_nether", CriterionTriggerChangedDimension.a.a(World.THE_NETHER)).a(var0, "story/enter_the_nether");
/*     */     
/* 117 */     Advancement.SerializedAdvancement.a()
/* 118 */       .a(var10)
/* 119 */       .a(Items.GOLDEN_APPLE, new ChatMessage("advancements.story.cure_zombie_villager.title"), new ChatMessage("advancements.story.cure_zombie_villager.description"), (MinecraftKey)null, AdvancementFrameType.GOAL, true, true, false)
/* 120 */       .a("cured_zombie", CriterionTriggerCuredZombieVillager.a.c())
/* 121 */       .a(var0, "story/cure_zombie_villager");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 127 */     Advancement var11 = Advancement.SerializedAdvancement.a().a(var10).a(Items.ENDER_EYE, new ChatMessage("advancements.story.follow_ender_eye.title"), new ChatMessage("advancements.story.follow_ender_eye.description"), (MinecraftKey)null, AdvancementFrameType.TASK, true, true, false).a("in_stronghold", CriterionTriggerLocation.a.a(CriterionConditionLocation.a(StructureGenerator.STRONGHOLD))).a(var0, "story/follow_ender_eye");
/*     */     
/* 129 */     Advancement.SerializedAdvancement.a()
/* 130 */       .a(var11)
/* 131 */       .a(Blocks.END_STONE, new ChatMessage("advancements.story.enter_the_end.title"), new ChatMessage("advancements.story.enter_the_end.description"), (MinecraftKey)null, AdvancementFrameType.TASK, true, true, false)
/* 132 */       .a("entered_end", CriterionTriggerChangedDimension.a.a(World.THE_END))
/* 133 */       .a(var0, "story/enter_the_end");
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\hs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */