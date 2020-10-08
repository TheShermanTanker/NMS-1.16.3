/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableList;
/*    */ import com.mojang.datafixers.util.Pair;
/*    */ import java.util.List;
/*    */ import java.util.function.Function;
/*    */ 
/*    */ public class WorldGenFeatureBastionPieces
/*    */ {
/* 10 */   public static final WorldGenFeatureDefinedStructurePoolTemplate a = WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("bastion/starts"), new MinecraftKey("empty"), 
/*    */ 
/*    */         
/* 13 */         (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 14 */           Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/units/air_base", ProcessorLists.x), Integer.valueOf(1)), 
/* 15 */           Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/hoglin_stable/air_base", ProcessorLists.x), Integer.valueOf(1)), 
/* 16 */           Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/treasure/big_air_full", ProcessorLists.x), Integer.valueOf(1)), 
/* 17 */           Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/bridge/starting_pieces/entrance_base", ProcessorLists.x), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void a() {
/* 23 */     WorldGenFeatureBastionUnits.a();
/* 24 */     WorldGenFeatureBastionHoglinStable.a();
/* 25 */     WorldGenFeatureBastionTreasure.a();
/* 26 */     WorldGenFeatureBastionBridge.a();
/* 27 */     WorldGenFeatureBastionExtra.a();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureBastionPieces.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */