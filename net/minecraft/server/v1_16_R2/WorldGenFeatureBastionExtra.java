/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableList;
/*    */ import com.mojang.datafixers.util.Pair;
/*    */ import java.util.List;
/*    */ import java.util.function.Function;
/*    */ 
/*    */ public class WorldGenFeatureBastionExtra
/*    */ {
/*    */   static {
/* 11 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("bastion/mobs/piglin"), new MinecraftKey("empty"), 
/*    */ 
/*    */           
/* 14 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 15 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/mobs/melee_piglin"), Integer.valueOf(1)), 
/* 16 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/mobs/sword_piglin"), Integer.valueOf(4)), 
/* 17 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/mobs/crossbow_piglin"), Integer.valueOf(4)), 
/* 18 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/mobs/empty"), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 23 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("bastion/mobs/hoglin"), new MinecraftKey("empty"), 
/*    */ 
/*    */           
/* 26 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 27 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/mobs/hoglin"), Integer.valueOf(2)), 
/* 28 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/mobs/empty"), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 33 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("bastion/blocks/gold"), new MinecraftKey("empty"), 
/*    */ 
/*    */           
/* 36 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 37 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/blocks/air"), Integer.valueOf(3)), 
/* 38 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/blocks/gold"), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 43 */     WorldGenFeaturePieces.a(new WorldGenFeatureDefinedStructurePoolTemplate(new MinecraftKey("bastion/mobs/piglin_melee"), new MinecraftKey("empty"), 
/*    */ 
/*    */           
/* 46 */           (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(
/* 47 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/mobs/melee_piglin_always"), Integer.valueOf(1)), 
/* 48 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/mobs/melee_piglin"), Integer.valueOf(5)), 
/* 49 */             Pair.of(WorldGenFeatureDefinedStructurePoolStructure.b("bastion/mobs/sword_piglin"), Integer.valueOf(1))), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*    */   }
/*    */   
/*    */   public static void a() {}
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureBastionExtra.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */