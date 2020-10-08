/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.mojang.datafixers.util.Pair;
/*     */ import java.util.List;
/*     */ import java.util.function.Function;
/*     */ 
/*     */ public class WorldGenFeatureBastionUnits
/*     */ {
/*     */   static {
/*  11 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("bastion/units/center_pieces"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/*  14 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/*  15 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/units/center_pieces/center_0", ProcessorLists.u), Integer.valueOf(1)), 
/*  16 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/units/center_pieces/center_1", ProcessorLists.u), Integer.valueOf(1)), 
/*  17 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/units/center_pieces/center_2", ProcessorLists.u), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  22 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("bastion/units/pathways"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/*  25 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/*  26 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/units/pathways/pathway_0", ProcessorLists.u), Integer.valueOf(1)), 
/*  27 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/units/pathways/pathway_wall_0", ProcessorLists.u), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  32 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("bastion/units/walls/wall_bases"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/*  35 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/*  36 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/units/walls/wall_base", ProcessorLists.u), Integer.valueOf(1)), 
/*  37 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/units/walls/connected_wall", ProcessorLists.u), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  42 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("bastion/units/stages/stage_0"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/*  45 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/*  46 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/units/stages/stage_0_0", ProcessorLists.u), Integer.valueOf(1)), 
/*  47 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/units/stages/stage_0_1", ProcessorLists.u), Integer.valueOf(1)), 
/*  48 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/units/stages/stage_0_2", ProcessorLists.u), Integer.valueOf(1)), 
/*  49 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/units/stages/stage_0_3", ProcessorLists.u), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  54 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("bastion/units/stages/stage_1"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/*  57 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/*  58 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/units/stages/stage_1_0", ProcessorLists.u), Integer.valueOf(1)), 
/*  59 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/units/stages/stage_1_1", ProcessorLists.u), Integer.valueOf(1)), 
/*  60 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/units/stages/stage_1_2", ProcessorLists.u), Integer.valueOf(1)), 
/*  61 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/units/stages/stage_1_3", ProcessorLists.u), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  67 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("bastion/units/stages/rot/stage_1"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/*  70 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/*  71 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/units/stages/rot/stage_1_0", ProcessorLists.u), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  76 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("bastion/units/stages/stage_2"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/*  79 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/*  80 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/units/stages/stage_2_0", ProcessorLists.u), Integer.valueOf(1)), 
/*  81 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/units/stages/stage_2_1", ProcessorLists.u), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  86 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("bastion/units/stages/stage_3"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/*  89 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/*  90 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/units/stages/stage_3_0", ProcessorLists.u), Integer.valueOf(1)), 
/*  91 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/units/stages/stage_3_1", ProcessorLists.u), Integer.valueOf(1)), 
/*  92 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/units/stages/stage_3_2", ProcessorLists.u), Integer.valueOf(1)), 
/*  93 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/units/stages/stage_3_3", ProcessorLists.u), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  98 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("bastion/units/fillers/stage_0"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/* 101 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 102 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/units/fillers/stage_0", ProcessorLists.u), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 107 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("bastion/units/edges"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/* 110 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 111 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/units/edges/edge_0", ProcessorLists.u), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 116 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("bastion/units/wall_units"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/* 119 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 120 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/units/wall_units/unit_0", ProcessorLists.u), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 125 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("bastion/units/edge_wall_units"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/* 128 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 129 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/units/wall_units/edge_0_large", ProcessorLists.u), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 134 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("bastion/units/ramparts"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/* 137 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 138 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/units/ramparts/ramparts_0", ProcessorLists.u), Integer.valueOf(1)), 
/* 139 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/units/ramparts/ramparts_1", ProcessorLists.u), Integer.valueOf(1)), 
/* 140 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/units/ramparts/ramparts_2", ProcessorLists.u), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 145 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("bastion/units/large_ramparts"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/* 148 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 149 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/units/ramparts/ramparts_0", ProcessorLists.u), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 154 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("bastion/units/rampart_plates"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/* 157 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 158 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/units/rampart_plates/plate_0", ProcessorLists.u), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */   }
/*     */   
/*     */   public static void a() {}
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureBastionUnits.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */