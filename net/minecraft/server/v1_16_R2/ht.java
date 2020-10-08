/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.function.Consumer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ht
/*    */   implements Consumer<Consumer<Advancement>>
/*    */ {
/*    */   public void accept(Consumer<Advancement> var0) {
/* 36 */     Advancement var1 = Advancement.SerializedAdvancement.a().a(Blocks.END_STONE, new ChatMessage("advancements.end.root.title"), new ChatMessage("advancements.end.root.description"), new MinecraftKey("textures/gui/advancements/backgrounds/end.png"), AdvancementFrameType.TASK, false, false, false).a("entered_end", CriterionTriggerChangedDimension.a.a(World.THE_END)).a(var0, "end/root");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 42 */     Advancement var2 = Advancement.SerializedAdvancement.a().a(var1).a(Blocks.DRAGON_HEAD, new ChatMessage("advancements.end.kill_dragon.title"), new ChatMessage("advancements.end.kill_dragon.description"), (MinecraftKey)null, AdvancementFrameType.TASK, true, true, false).a("killed_dragon", CriterionTriggerKilled.a.a(CriterionConditionEntity.a.a().a(EntityTypes.ENDER_DRAGON))).a(var0, "end/kill_dragon");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 48 */     Advancement var3 = Advancement.SerializedAdvancement.a().a(var2).a(Items.ENDER_PEARL, new ChatMessage("advancements.end.enter_end_gateway.title"), new ChatMessage("advancements.end.enter_end_gateway.description"), (MinecraftKey)null, AdvancementFrameType.TASK, true, true, false).a("entered_end_gateway", CriterionTriggerEnterBlock.a.a(Blocks.END_GATEWAY)).a(var0, "end/enter_end_gateway");
/*    */     
/* 50 */     Advancement.SerializedAdvancement.a()
/* 51 */       .a(var2)
/* 52 */       .a(Items.END_CRYSTAL, new ChatMessage("advancements.end.respawn_dragon.title"), new ChatMessage("advancements.end.respawn_dragon.description"), (MinecraftKey)null, AdvancementFrameType.GOAL, true, true, false)
/* 53 */       .a("summoned_dragon", CriterionTriggerSummonedEntity.a.a(CriterionConditionEntity.a.a().a(EntityTypes.ENDER_DRAGON)))
/* 54 */       .a(var0, "end/respawn_dragon");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 60 */     Advancement var4 = Advancement.SerializedAdvancement.a().a(var3).a(Blocks.PURPUR_BLOCK, new ChatMessage("advancements.end.find_end_city.title"), new ChatMessage("advancements.end.find_end_city.description"), (MinecraftKey)null, AdvancementFrameType.TASK, true, true, false).a("in_city", CriterionTriggerLocation.a.a(CriterionConditionLocation.a(StructureGenerator.ENDCITY))).a(var0, "end/find_end_city");
/*    */     
/* 62 */     Advancement.SerializedAdvancement.a()
/* 63 */       .a(var2)
/* 64 */       .a(Items.DRAGON_BREATH, new ChatMessage("advancements.end.dragon_breath.title"), new ChatMessage("advancements.end.dragon_breath.description"), (MinecraftKey)null, AdvancementFrameType.GOAL, true, true, false)
/* 65 */       .a("dragon_breath", CriterionTriggerInventoryChanged.a.a(new IMaterial[] { Items.DRAGON_BREATH
/* 66 */           })).a(var0, "end/dragon_breath");
/*    */     
/* 68 */     Advancement.SerializedAdvancement.a()
/* 69 */       .a(var4)
/* 70 */       .a(Items.SHULKER_SHELL, new ChatMessage("advancements.end.levitate.title"), new ChatMessage("advancements.end.levitate.description"), (MinecraftKey)null, AdvancementFrameType.CHALLENGE, true, true, false)
/* 71 */       .a(AdvancementRewards.a.a(50))
/* 72 */       .a("levitated", CriterionTriggerLevitation.a.a(CriterionConditionDistance.b(CriterionConditionValue.FloatRange.b(50.0F))))
/* 73 */       .a(var0, "end/levitate");
/*    */     
/* 75 */     Advancement.SerializedAdvancement.a()
/* 76 */       .a(var4)
/* 77 */       .a(Items.ELYTRA, new ChatMessage("advancements.end.elytra.title"), new ChatMessage("advancements.end.elytra.description"), (MinecraftKey)null, AdvancementFrameType.GOAL, true, true, false)
/* 78 */       .a("elytra", CriterionTriggerInventoryChanged.a.a(new IMaterial[] { Items.ELYTRA
/* 79 */           })).a(var0, "end/elytra");
/*    */     
/* 81 */     Advancement.SerializedAdvancement.a()
/* 82 */       .a(var2)
/* 83 */       .a(Blocks.DRAGON_EGG, new ChatMessage("advancements.end.dragon_egg.title"), new ChatMessage("advancements.end.dragon_egg.description"), (MinecraftKey)null, AdvancementFrameType.GOAL, true, true, false)
/* 84 */       .a("dragon_egg", CriterionTriggerInventoryChanged.a.a(new IMaterial[] { Blocks.DRAGON_EGG
/* 85 */           })).a(var0, "end/dragon_egg");
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ht.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */