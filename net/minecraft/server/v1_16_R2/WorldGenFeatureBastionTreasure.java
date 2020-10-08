/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.mojang.datafixers.util.Pair;
/*     */ import java.util.List;
/*     */ import java.util.function.Function;
/*     */ 
/*     */ public class WorldGenFeatureBastionTreasure
/*     */ {
/*     */   static {
/*  11 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("bastion/treasure/bases"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/*  14 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/*  15 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/bases/lava_basin", ProcessorLists.t), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  20 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("bastion/treasure/stairs"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/*  23 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/*  24 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/stairs/lower_stairs", ProcessorLists.t), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  29 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("bastion/treasure/bases/centers"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/*  32 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/*  33 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/bases/centers/center_0", ProcessorLists.t), Integer.valueOf(1)), 
/*  34 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/bases/centers/center_1", ProcessorLists.t), Integer.valueOf(1)), 
/*  35 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/bases/centers/center_2", ProcessorLists.t), Integer.valueOf(1)), 
/*  36 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/bases/centers/center_3", ProcessorLists.t), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  41 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("bastion/treasure/brains"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/*  44 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/*  45 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/brains/center_brain", ProcessorLists.t), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  50 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("bastion/treasure/walls"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/*  53 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/*  54 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/walls/lava_wall", ProcessorLists.t), Integer.valueOf(1)), 
/*  55 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/walls/entrance_wall", ProcessorLists.C), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  60 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("bastion/treasure/walls/outer"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/*  63 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/*  64 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/walls/outer/top_corner", ProcessorLists.C), Integer.valueOf(1)), 
/*  65 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/walls/outer/mid_corner", ProcessorLists.C), Integer.valueOf(1)), 
/*  66 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/walls/outer/bottom_corner", ProcessorLists.C), Integer.valueOf(1)), 
/*  67 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/walls/outer/outer_wall", ProcessorLists.C), Integer.valueOf(1)), 
/*  68 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/walls/outer/medium_outer_wall", ProcessorLists.C), Integer.valueOf(1)), 
/*  69 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/walls/outer/tall_outer_wall", ProcessorLists.C), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  74 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("bastion/treasure/walls/bottom"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/*  77 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/*  78 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/walls/bottom/wall_0", ProcessorLists.t), Integer.valueOf(1)), 
/*  79 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/walls/bottom/wall_1", ProcessorLists.t), Integer.valueOf(1)), 
/*  80 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/walls/bottom/wall_2", ProcessorLists.t), Integer.valueOf(1)), 
/*  81 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/walls/bottom/wall_3", ProcessorLists.t), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  86 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("bastion/treasure/walls/mid"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/*  89 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/*  90 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/walls/mid/wall_0", ProcessorLists.t), Integer.valueOf(1)), 
/*  91 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/walls/mid/wall_1", ProcessorLists.t), Integer.valueOf(1)), 
/*  92 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/walls/mid/wall_2", ProcessorLists.t), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  97 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("bastion/treasure/walls/top"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/* 100 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 101 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/walls/top/main_entrance", ProcessorLists.t), Integer.valueOf(1)), 
/* 102 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/walls/top/wall_0", ProcessorLists.t), Integer.valueOf(1)), 
/* 103 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/walls/top/wall_1", ProcessorLists.t), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 108 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("bastion/treasure/connectors"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/* 111 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 112 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/connectors/center_to_wall_middle", ProcessorLists.t), Integer.valueOf(1)), 
/* 113 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/connectors/center_to_wall_top", ProcessorLists.t), Integer.valueOf(1)), 
/* 114 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/connectors/center_to_wall_top_entrance", ProcessorLists.t), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 119 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("bastion/treasure/entrances"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/* 122 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 123 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/entrances/entrance_0", ProcessorLists.t), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 128 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("bastion/treasure/ramparts"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/* 131 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 132 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/ramparts/mid_wall_main", ProcessorLists.t), Integer.valueOf(1)), 
/* 133 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/ramparts/mid_wall_side", ProcessorLists.t), Integer.valueOf(1)), 
/* 134 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/ramparts/bottom_wall_0", ProcessorLists.s), Integer.valueOf(1)), 
/* 135 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/ramparts/top_wall", ProcessorLists.D), Integer.valueOf(1)), 
/* 136 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/ramparts/lava_basin_side", ProcessorLists.t), Integer.valueOf(1)), 
/* 137 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/ramparts/lava_basin_main", ProcessorLists.t), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 142 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("bastion/treasure/corners/bottom"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/* 145 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 146 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/corners/bottom/corner_0", ProcessorLists.t), Integer.valueOf(1)), 
/* 147 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/corners/bottom/corner_1", ProcessorLists.t), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 152 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("bastion/treasure/corners/edges"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/* 155 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 156 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/corners/edges/bottom", ProcessorLists.C), Integer.valueOf(1)), 
/* 157 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/corners/edges/middle", ProcessorLists.C), Integer.valueOf(1)), 
/* 158 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/corners/edges/top", ProcessorLists.C), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 163 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("bastion/treasure/corners/middle"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/* 166 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 167 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/corners/middle/corner_0", ProcessorLists.t), Integer.valueOf(1)), 
/* 168 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/corners/middle/corner_1", ProcessorLists.t), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 173 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("bastion/treasure/corners/top"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/* 176 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 177 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/corners/top/corner_0", ProcessorLists.t), Integer.valueOf(1)), 
/* 178 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/corners/top/corner_1", ProcessorLists.t), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 183 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("bastion/treasure/extensions/large_pool"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/* 186 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 187 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/extensions/empty", ProcessorLists.t), Integer.valueOf(1)), 
/* 188 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/extensions/empty", ProcessorLists.t), Integer.valueOf(1)), 
/* 189 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/extensions/fire_room", ProcessorLists.t), Integer.valueOf(1)), 
/* 190 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/extensions/large_bridge_0", ProcessorLists.t), Integer.valueOf(1)), 
/* 191 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/extensions/large_bridge_1", ProcessorLists.t), Integer.valueOf(1)), 
/* 192 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/extensions/large_bridge_2", ProcessorLists.t), Integer.valueOf(1)), 
/* 193 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/extensions/large_bridge_3", ProcessorLists.t), Integer.valueOf(1)), 
/* 194 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/extensions/roofed_bridge", ProcessorLists.t), Integer.valueOf(1)), 
/* 195 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/extensions/empty", ProcessorLists.t), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 200 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("bastion/treasure/extensions/small_pool"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/* 203 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 204 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/extensions/empty", ProcessorLists.t), Integer.valueOf(1)), 
/* 205 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/extensions/fire_room", ProcessorLists.t), Integer.valueOf(1)), 
/* 206 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/extensions/empty", ProcessorLists.t), Integer.valueOf(1)), 
/* 207 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/extensions/small_bridge_0", ProcessorLists.t), Integer.valueOf(1)), 
/* 208 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/extensions/small_bridge_1", ProcessorLists.t), Integer.valueOf(1)), 
/* 209 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/extensions/small_bridge_2", ProcessorLists.t), Integer.valueOf(1)), 
/* 210 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/extensions/small_bridge_3", ProcessorLists.t), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 215 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("bastion/treasure/extensions/houses"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/* 218 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 219 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/extensions/house_0", ProcessorLists.t), Integer.valueOf(1)), 
/* 220 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/extensions/house_1", ProcessorLists.t), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 225 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("bastion/treasure/roofs"), new MinecraftKey("empty"), 
/*     */ 
/*     */           
/* 228 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 229 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/roofs/wall_roof", ProcessorLists.B), Integer.valueOf(1)), 
/* 230 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/roofs/corner_roof", ProcessorLists.B), Integer.valueOf(1)), 
/* 231 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/roofs/center_roof", ProcessorLists.B), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*     */   }
/*     */   
/*     */   public static void a() {}
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureBastionTreasure.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */