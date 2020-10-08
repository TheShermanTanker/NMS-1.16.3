/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenCarvers
/*    */ {
/* 10 */   public static final WorldGenCarverWrapper<WorldGenFeatureConfigurationChance> a = a("cave", WorldGenCarverAbstract.a.a(new WorldGenFeatureConfigurationChance(0.14285715F)));
/* 11 */   public static final WorldGenCarverWrapper<WorldGenFeatureConfigurationChance> b = a("canyon", WorldGenCarverAbstract.c.a(new WorldGenFeatureConfigurationChance(0.02F)));
/* 12 */   public static final WorldGenCarverWrapper<WorldGenFeatureConfigurationChance> c = a("ocean_cave", WorldGenCarverAbstract.a.a(new WorldGenFeatureConfigurationChance(0.06666667F)));
/* 13 */   public static final WorldGenCarverWrapper<WorldGenFeatureConfigurationChance> d = a("underwater_canyon", WorldGenCarverAbstract.d.a(new WorldGenFeatureConfigurationChance(0.02F)));
/* 14 */   public static final WorldGenCarverWrapper<WorldGenFeatureConfigurationChance> e = a("underwater_cave", WorldGenCarverAbstract.e.a(new WorldGenFeatureConfigurationChance(0.06666667F)));
/* 15 */   public static final WorldGenCarverWrapper<WorldGenFeatureConfigurationChance> f = a("nether_cave", WorldGenCarverAbstract.b.a(new WorldGenFeatureConfigurationChance(0.2F)));
/*    */   
/*    */   private static <WC extends WorldGenCarverConfiguration> WorldGenCarverWrapper<WC> a(String var0, WorldGenCarverWrapper<WC> var1) {
/* 18 */     return (WorldGenCarverWrapper<WC>)RegistryGeneration.<WorldGenCarverWrapper<?>>a(RegistryGeneration.d, var0, var1);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenCarvers.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */