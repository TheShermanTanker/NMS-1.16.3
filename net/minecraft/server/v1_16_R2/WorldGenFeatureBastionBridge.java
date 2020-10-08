/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableList;
/*    */ import com.mojang.datafixers.util.Pair;
/*    */ import java.util.List;
/*    */ import java.util.function.Function;
/*    */ 
/*    */ public class WorldGenFeatureBastionBridge
/*    */ {
/*    */   static {
/* 11 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("bastion/bridge/starting_pieces"), new MinecraftKey("empty"), 
/*    */ 
/*    */           
/* 14 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 15 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/bridge/starting_pieces/entrance", ProcessorLists.z), Integer.valueOf(1)), 
/* 16 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/bridge/starting_pieces/entrance_face", ProcessorLists.x), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 21 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("bastion/bridge/bridge_pieces"), new MinecraftKey("empty"), 
/*    */ 
/*    */           
/* 24 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 25 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/bridge/bridge_pieces/bridge", ProcessorLists.A), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 30 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("bastion/bridge/legs"), new MinecraftKey("empty"), 
/*    */ 
/*    */           
/* 33 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 34 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/bridge/legs/leg_0", ProcessorLists.x), Integer.valueOf(1)), 
/* 35 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/bridge/legs/leg_1", ProcessorLists.x), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 40 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("bastion/bridge/walls"), new MinecraftKey("empty"), 
/*    */ 
/*    */           
/* 43 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 44 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/bridge/walls/wall_base_0", ProcessorLists.y), Integer.valueOf(1)), 
/* 45 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/bridge/walls/wall_base_1", ProcessorLists.y), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 50 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("bastion/bridge/ramparts"), new MinecraftKey("empty"), 
/*    */ 
/*    */           
/* 53 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 54 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/bridge/ramparts/rampart_0", ProcessorLists.y), Integer.valueOf(1)), 
/* 55 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/bridge/ramparts/rampart_1", ProcessorLists.y), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 60 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("bastion/bridge/rampart_plates"), new MinecraftKey("empty"), 
/*    */ 
/*    */           
/* 63 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 64 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/bridge/rampart_plates/plate_0", ProcessorLists.y), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 69 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("bastion/bridge/connectors"), new MinecraftKey("empty"), 
/*    */ 
/*    */           
/* 72 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 73 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/bridge/connectors/back_bridge_top", ProcessorLists.x), Integer.valueOf(1)), 
/* 74 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/bridge/connectors/back_bridge_bottom", ProcessorLists.x), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*    */   }
/*    */   
/*    */   public static void a() {}
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureBastionBridge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */