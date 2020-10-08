/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.mojang.datafixers.util.Pair;
/*     */ import java.util.List;
/*     */ import java.util.function.Function;
/*     */ 
/*     */ public class WorldGenFeatureBastionHoglinStable
/*     */ {
/*     */   static {
/*  11 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("bastion/hoglin_stable/starting_pieces"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/*  14 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/*  15 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/hoglin_stable/starting_pieces/starting_stairs_0", ProcessorLists.w), Integer.valueOf(1)), 
/*  16 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/hoglin_stable/starting_pieces/starting_stairs_1", ProcessorLists.w), Integer.valueOf(1)), 
/*  17 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/hoglin_stable/starting_pieces/starting_stairs_2", ProcessorLists.w), Integer.valueOf(1)), 
/*  18 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/hoglin_stable/starting_pieces/starting_stairs_3", ProcessorLists.w), Integer.valueOf(1)), 
/*  19 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/hoglin_stable/starting_pieces/starting_stairs_4", ProcessorLists.w), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  24 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("bastion/hoglin_stable/mirrored_starting_pieces"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/*  27 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/*  28 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/hoglin_stable/starting_pieces/stairs_0_mirrored", ProcessorLists.w), Integer.valueOf(1)), 
/*  29 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/hoglin_stable/starting_pieces/stairs_1_mirrored", ProcessorLists.w), Integer.valueOf(1)), 
/*  30 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/hoglin_stable/starting_pieces/stairs_2_mirrored", ProcessorLists.w), Integer.valueOf(1)), 
/*  31 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/hoglin_stable/starting_pieces/stairs_3_mirrored", ProcessorLists.w), Integer.valueOf(1)), 
/*  32 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/hoglin_stable/starting_pieces/stairs_4_mirrored", ProcessorLists.w), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  37 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("bastion/hoglin_stable/wall_bases"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/*  40 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/*  41 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/hoglin_stable/walls/wall_base", ProcessorLists.w), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  46 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("bastion/hoglin_stable/walls"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/*  49 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/*  50 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/hoglin_stable/walls/side_wall_0", ProcessorLists.v), Integer.valueOf(1)), 
/*  51 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/hoglin_stable/walls/side_wall_1", ProcessorLists.v), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  56 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("bastion/hoglin_stable/stairs"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/*  59 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/*  60 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/hoglin_stable/stairs/stairs_1_0", ProcessorLists.w), Integer.valueOf(1)), 
/*  61 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/hoglin_stable/stairs/stairs_1_1", ProcessorLists.w), Integer.valueOf(1)), 
/*  62 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/hoglin_stable/stairs/stairs_1_2", ProcessorLists.w), Integer.valueOf(1)), 
/*  63 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/hoglin_stable/stairs/stairs_1_3", ProcessorLists.w), Integer.valueOf(1)), 
/*  64 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/hoglin_stable/stairs/stairs_1_4", ProcessorLists.w), Integer.valueOf(1)), 
/*  65 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/hoglin_stable/stairs/stairs_2_0", ProcessorLists.w), Integer.valueOf(1)), 
/*  66 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/hoglin_stable/stairs/stairs_2_1", ProcessorLists.w), Integer.valueOf(1)), 
/*  67 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/hoglin_stable/stairs/stairs_2_2", ProcessorLists.w), Integer.valueOf(1)), 
/*  68 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/hoglin_stable/stairs/stairs_2_3", ProcessorLists.w), Integer.valueOf(1)), 
/*  69 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/hoglin_stable/stairs/stairs_2_4", ProcessorLists.w), Integer.valueOf(1)), 
/*  70 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/hoglin_stable/stairs/stairs_3_0", ProcessorLists.w), Integer.valueOf(1)), 
/*  71 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/hoglin_stable/stairs/stairs_3_1", ProcessorLists.w), Integer.valueOf(1)), (Object[])new Pair[] {
/*  72 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/hoglin_stable/stairs/stairs_3_2", ProcessorLists.w), Integer.valueOf(1)), 
/*  73 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/hoglin_stable/stairs/stairs_3_3", ProcessorLists.w), Integer.valueOf(1)), 
/*  74 */               Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/hoglin_stable/stairs/stairs_3_4", ProcessorLists.w), Integer.valueOf(1))
/*     */             }), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */     
/*  79 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("bastion/hoglin_stable/small_stables/inner"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/*  82 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/*  83 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/hoglin_stable/small_stables/inner_0", ProcessorLists.w), Integer.valueOf(1)), 
/*  84 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/hoglin_stable/small_stables/inner_1", ProcessorLists.w), Integer.valueOf(1)), 
/*  85 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/hoglin_stable/small_stables/inner_2", ProcessorLists.w), Integer.valueOf(1)), 
/*  86 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/hoglin_stable/small_stables/inner_3", ProcessorLists.w), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  91 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("bastion/hoglin_stable/small_stables/outer"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/*  94 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/*  95 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/hoglin_stable/small_stables/outer_0", ProcessorLists.w), Integer.valueOf(1)), 
/*  96 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/hoglin_stable/small_stables/outer_1", ProcessorLists.w), Integer.valueOf(1)), 
/*  97 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/hoglin_stable/small_stables/outer_2", ProcessorLists.w), Integer.valueOf(1)), 
/*  98 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/hoglin_stable/small_stables/outer_3", ProcessorLists.w), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 103 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("bastion/hoglin_stable/large_stables/inner"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/* 106 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 107 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/hoglin_stable/large_stables/inner_0", ProcessorLists.w), Integer.valueOf(1)), 
/* 108 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/hoglin_stable/large_stables/inner_1", ProcessorLists.w), Integer.valueOf(1)), 
/* 109 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/hoglin_stable/large_stables/inner_2", ProcessorLists.w), Integer.valueOf(1)), 
/* 110 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/hoglin_stable/large_stables/inner_3", ProcessorLists.w), Integer.valueOf(1)), 
/* 111 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/hoglin_stable/large_stables/inner_4", ProcessorLists.w), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 116 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("bastion/hoglin_stable/large_stables/outer"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/* 119 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 120 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/hoglin_stable/large_stables/outer_0", ProcessorLists.w), Integer.valueOf(1)), 
/* 121 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/hoglin_stable/large_stables/outer_1", ProcessorLists.w), Integer.valueOf(1)), 
/* 122 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/hoglin_stable/large_stables/outer_2", ProcessorLists.w), Integer.valueOf(1)), 
/* 123 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/hoglin_stable/large_stables/outer_3", ProcessorLists.w), Integer.valueOf(1)), 
/* 124 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/hoglin_stable/large_stables/outer_4", ProcessorLists.w), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 129 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("bastion/hoglin_stable/posts"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/* 132 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 133 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/hoglin_stable/posts/stair_post", ProcessorLists.w), Integer.valueOf(1)), 
/* 134 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/hoglin_stable/posts/end_post", ProcessorLists.w), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 139 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("bastion/hoglin_stable/ramparts"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/* 142 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 143 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/hoglin_stable/ramparts/ramparts_1", ProcessorLists.w), Integer.valueOf(1)), 
/* 144 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/hoglin_stable/ramparts/ramparts_2", ProcessorLists.w), Integer.valueOf(1)), 
/* 145 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/hoglin_stable/ramparts/ramparts_3", ProcessorLists.w), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 150 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("bastion/hoglin_stable/rampart_plates"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/* 153 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 154 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/hoglin_stable/rampart_plates/rampart_plate_1", ProcessorLists.w), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 159 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("bastion/hoglin_stable/connectors"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/* 162 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 163 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/hoglin_stable/connectors/end_post_connector", ProcessorLists.w), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */   }
/*     */   
/*     */   public static void a() {}
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureBastionHoglinStable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */