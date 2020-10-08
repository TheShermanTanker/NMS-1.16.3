/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableList;
/*    */ import com.mojang.datafixers.util.Pair;
/*    */ import java.util.List;
/*    */ import java.util.function.Function;
/*    */ 
/*    */ public class WorldGenFeaturePillagerOutpostPieces
/*    */ {
/* 10 */   public static final WorldGenFeatureDefinedStructurePoolTemplate a = WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("pillager_outpost/base_plates"), new MinecraftKey("empty"), 
/*    */ 
/*    */         
/* 13 */         (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 14 */           Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("pillager_outpost/base_plate"), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   static {
/* 20 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("pillager_outpost/towers"), new MinecraftKey("empty"), 
/*    */ 
/*    */           
/* 23 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 24 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a((List<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>>)ImmutableList.of(
/* 25 */                   WorldGenFeatureDefinedStructurePoolStructure.a("pillager_outpost/watchtower"), 
/* 26 */                   WorldGenFeatureDefinedStructurePoolStructure.a("pillager_outpost/watchtower_overgrown", ProcessorLists.r))), 
/* 27 */               Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 32 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("pillager_outpost/feature_plates"), new MinecraftKey("empty"), 
/*    */ 
/*    */           
/* 35 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 36 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("pillager_outpost/feature_plate"), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.TERRAIN_MATCHING));
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 41 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("pillager_outpost/features"), new MinecraftKey("empty"), 
/*    */ 
/*    */           
/* 44 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 45 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("pillager_outpost/feature_cage1"), Integer.valueOf(1)), 
/* 46 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("pillager_outpost/feature_cage2"), Integer.valueOf(1)), 
/* 47 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("pillager_outpost/feature_logs"), Integer.valueOf(1)), 
/* 48 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("pillager_outpost/feature_tent1"), Integer.valueOf(1)), 
/* 49 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("pillager_outpost/feature_tent2"), Integer.valueOf(1)), 
/* 50 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.a("pillager_outpost/feature_targets"), Integer.valueOf(1)), 
/* 51 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.g(), Integer.valueOf(6))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*    */   }
/*    */   
/*    */   public static void a() {}
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeaturePillagerOutpostPieces.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */