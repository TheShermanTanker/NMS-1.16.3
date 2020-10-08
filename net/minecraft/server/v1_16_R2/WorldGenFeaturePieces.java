/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableList;
/*    */ import com.mojang.datafixers.util.Pair;
/*    */ import java.util.List;
/*    */ import java.util.function.Function;
/*    */ 
/*    */ 
/*    */ public class WorldGenFeaturePieces
/*    */ {
/* 11 */   public static final ResourceKey<WorldGenFeatureDefinedStructurePoolTemplate> a = ResourceKey.a(IRegistry.ax, new MinecraftKey("empty"));
/*    */   
/* 13 */   private static final WorldGenFeatureDefinedStructurePoolTemplate b = a(new WorldGenFeatureDefinedStructurePoolTemplate(a.a(), a.a(), (List<Pair<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>>)ImmutableList.of(), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
/*    */   
/*    */   static {
/* 16 */     a();
/*    */   }
/*    */   
/*    */   public static WorldGenFeatureDefinedStructurePoolTemplate a(WorldGenFeatureDefinedStructurePoolTemplate var0) {
/* 20 */     return RegistryGeneration.<WorldGenFeatureDefinedStructurePoolTemplate, WorldGenFeatureDefinedStructurePoolTemplate>a(RegistryGeneration.h, var0.b(), var0);
/*    */   }
/*    */   
/*    */   public static WorldGenFeatureDefinedStructurePoolTemplate a() {
/* 24 */     WorldGenFeatureBastionPieces.a();
/* 25 */     WorldGenFeaturePillagerOutpostPieces.a();
/* 26 */     WorldGenFeatureVillages.a();
/* 27 */     return b;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeaturePieces.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */