/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ 
/*    */ public interface WorldGenFeatureDefinedStructurePools<P extends WorldGenFeatureDefinedStructurePoolStructure>
/*    */ {
/*  7 */   public static final WorldGenFeatureDefinedStructurePools<WorldGenFeatureDefinedStructurePoolSingle> a = a("single_pool_element", WorldGenFeatureDefinedStructurePoolSingle.b);
/*  8 */   public static final WorldGenFeatureDefinedStructurePools<WorldGenFeatureDefinedStructurePoolList> b = a("list_pool_element", WorldGenFeatureDefinedStructurePoolList.a);
/*  9 */   public static final WorldGenFeatureDefinedStructurePools<WorldGenFeatureDefinedStructurePoolFeature> c = a("feature_pool_element", WorldGenFeatureDefinedStructurePoolFeature.a);
/* 10 */   public static final WorldGenFeatureDefinedStructurePools<WorldGenFeatureDefinedStructurePoolEmpty> d = a("empty_pool_element", WorldGenFeatureDefinedStructurePoolEmpty.a);
/* 11 */   public static final WorldGenFeatureDefinedStructurePools<WorldGenFeatureDefinedStructurePoolLegacySingle> e = a("legacy_single_pool_element", WorldGenFeatureDefinedStructurePoolLegacySingle.a);
/*    */ 
/*    */   
/*    */   Codec<P> codec();
/*    */   
/*    */   static <P extends WorldGenFeatureDefinedStructurePoolStructure> WorldGenFeatureDefinedStructurePools<P> a(String var0, Codec<P> var1) {
/* 17 */     return (WorldGenFeatureDefinedStructurePools<P>)IRegistry.<WorldGenFeatureDefinedStructurePools<?>>a(IRegistry.STRUCTURE_POOL_ELEMENT, var0, () -> var0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureDefinedStructurePools.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */