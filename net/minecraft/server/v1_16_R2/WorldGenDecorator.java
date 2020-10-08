/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ import java.util.stream.Stream;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class WorldGenDecorator<DC extends WorldGenFeatureDecoratorConfiguration>
/*    */ {
/* 20 */   public static final WorldGenDecorator<WorldGenFeatureEmptyConfiguration2> a = a("nope", new WorldGenDecoratorEmpty(WorldGenFeatureEmptyConfiguration2.a));
/*    */   
/* 22 */   public static final WorldGenDecorator<WorldGenDecoratorDungeonConfiguration> b = a("chance", new WorldGenDecoratorChance(WorldGenDecoratorDungeonConfiguration.a));
/* 23 */   public static final WorldGenDecorator<WorldGenDecoratorFrequencyConfiguration> c = a("count", new WorldGenDecoratorCount(WorldGenDecoratorFrequencyConfiguration.a));
/* 24 */   public static final WorldGenDecorator<WorldGenFeatureDecoratorNoiseConfiguration> d = a("count_noise", new WorldGenDecoratorCountNoise(WorldGenFeatureDecoratorNoiseConfiguration.a));
/* 25 */   public static final WorldGenDecorator<WorldGenDecoratorNoiseConfiguration> e = a("count_noise_biased", new WorldGenDecoratorCountNoiseBiased(WorldGenDecoratorNoiseConfiguration.a));
/* 26 */   public static final WorldGenDecorator<WorldGenDecoratorFrequencyExtraChanceConfiguration> f = a("count_extra", new WorldGenDecoratorCountExtra(WorldGenDecoratorFrequencyExtraChanceConfiguration.a));
/*    */   
/* 28 */   public static final WorldGenDecorator<WorldGenFeatureEmptyConfiguration2> g = a("square", new WorldGenDecoratorSquare(WorldGenFeatureEmptyConfiguration2.a));
/*    */   
/* 30 */   public static final WorldGenDecorator<WorldGenFeatureEmptyConfiguration2> h = a("heightmap", new WorldGenDecoratorHeightmap<>(WorldGenFeatureEmptyConfiguration2.a));
/* 31 */   public static final WorldGenDecorator<WorldGenFeatureEmptyConfiguration2> i = a("heightmap_spread_double", new WorldGenDecoratorHeightmapSpreadDouble<>(WorldGenFeatureEmptyConfiguration2.a));
/* 32 */   public static final WorldGenDecorator<WorldGenFeatureEmptyConfiguration2> j = a("top_solid_heightmap", new WorldGenDecoratorSolidTop(WorldGenFeatureEmptyConfiguration2.a));
/* 33 */   public static final WorldGenDecorator<WorldGenFeatureEmptyConfiguration2> k = a("heightmap_world_surface", new WorldGenDecoratorHeightmapWorldSurface(WorldGenFeatureEmptyConfiguration2.a));
/*    */   
/* 35 */   public static final WorldGenDecorator<WorldGenFeatureChanceDecoratorRangeConfiguration> l = a("range", new WorldGenDecoratorRange(WorldGenFeatureChanceDecoratorRangeConfiguration.a));
/* 36 */   public static final WorldGenDecorator<WorldGenFeatureChanceDecoratorRangeConfiguration> m = a("range_biased", new WorldGenDecoratorRangeBiased(WorldGenFeatureChanceDecoratorRangeConfiguration.a));
/* 37 */   public static final WorldGenDecorator<WorldGenFeatureChanceDecoratorRangeConfiguration> n = a("range_very_biased", new WorldGenDecoratorRangeVeryBiased(WorldGenFeatureChanceDecoratorRangeConfiguration.a));
/* 38 */   public static final WorldGenDecorator<WorldGenDecoratorHeightAverageConfiguration> o = a("depth_average", new WorldGenDecoratorDepthAverage(WorldGenDecoratorHeightAverageConfiguration.a));
/*    */   
/* 40 */   public static final WorldGenDecorator<WorldGenFeatureEmptyConfiguration2> p = a("spread_32_above", new WorldGenDecoratorSpread32Above(WorldGenFeatureEmptyConfiguration2.a));
/*    */   
/* 42 */   public static final WorldGenDecorator<WorldGenDecoratorCarveMaskConfiguration> q = a("carving_mask", new WorldGenDecoratorCarveMask(WorldGenDecoratorCarveMaskConfiguration.a));
/* 43 */   public static final WorldGenDecorator<WorldGenDecoratorFrequencyConfiguration> r = a("fire", new WorldGenDecoratorNetherFire(WorldGenDecoratorFrequencyConfiguration.a));
/* 44 */   public static final WorldGenDecorator<WorldGenFeatureEmptyConfiguration2> s = a("magma", new WorldGenDecoratorNetherMagma(WorldGenFeatureEmptyConfiguration2.a));
/* 45 */   public static final WorldGenDecorator<WorldGenFeatureEmptyConfiguration2> t = a("emerald_ore", new WorldGenDecoratorEmerald(WorldGenFeatureEmptyConfiguration2.a));
/* 46 */   public static final WorldGenDecorator<WorldGenDecoratorDungeonConfiguration> u = a("lava_lake", new WorldGenDecoratorLakeLava(WorldGenDecoratorDungeonConfiguration.a));
/* 47 */   public static final WorldGenDecorator<WorldGenDecoratorDungeonConfiguration> v = a("water_lake", new WorldGenDecoratorLakeWater(WorldGenDecoratorDungeonConfiguration.a));
/* 48 */   public static final WorldGenDecorator<WorldGenDecoratorFrequencyConfiguration> w = a("glowstone", new WorldGenDecoratorNetherGlowstone(WorldGenDecoratorFrequencyConfiguration.a));
/* 49 */   public static final WorldGenDecorator<WorldGenFeatureEmptyConfiguration2> x = a("end_gateway", new WorldGenDecoratorEndGateway(WorldGenFeatureEmptyConfiguration2.a));
/*    */   
/* 51 */   public static final WorldGenDecorator<WorldGenFeatureEmptyConfiguration2> y = a("dark_oak_tree", new WorldGenDecoratorRoofedTree(WorldGenFeatureEmptyConfiguration2.a));
/* 52 */   public static final WorldGenDecorator<WorldGenFeatureEmptyConfiguration2> z = a("iceberg", new WorldGenDecoratorIceburg(WorldGenFeatureEmptyConfiguration2.a));
/* 53 */   public static final WorldGenDecorator<WorldGenFeatureEmptyConfiguration2> A = a("end_island", new WorldGenDecoratorEndIsland(WorldGenFeatureEmptyConfiguration2.a));
/*    */   
/* 55 */   public static final WorldGenDecorator<WorldGenDecoratorDecpratedConfiguration> B = a("decorated", new WorldGenDecoratorDecorated(WorldGenDecoratorDecpratedConfiguration.a));
/*    */   
/* 57 */   public static final WorldGenDecorator<WorldGenDecoratorFrequencyConfiguration> C = a("count_multilayer", new WorldGenDecoratorCountMultilayer(WorldGenDecoratorFrequencyConfiguration.a));
/*    */   
/*    */   private static <T extends WorldGenFeatureDecoratorConfiguration, G extends WorldGenDecorator<T>> G a(String var0, G var1) {
/* 60 */     return (G)IRegistry.<WorldGenDecorator>a((IRegistry)IRegistry.DECORATOR, var0, (WorldGenDecorator)var1);
/*    */   }
/*    */   
/*    */   private final Codec<WorldGenDecoratorConfigured<DC>> D;
/*    */   
/*    */   public WorldGenDecorator(Codec<DC> var0) {
/* 66 */     this.D = var0.fieldOf("config").xmap(var0 -> new WorldGenDecoratorConfigured<>(this, (DC)var0), WorldGenDecoratorConfigured::b).codec();
/*    */   }
/*    */   
/*    */   public WorldGenDecoratorConfigured<DC> b(DC var0) {
/* 70 */     return new WorldGenDecoratorConfigured<>(this, var0);
/*    */   }
/*    */   
/*    */   public Codec<WorldGenDecoratorConfigured<DC>> a() {
/* 74 */     return this.D;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 81 */     return getClass().getSimpleName() + "@" + Integer.toHexString(hashCode());
/*    */   }
/*    */   
/*    */   public abstract Stream<BlockPosition> a(WorldGenDecoratorContext paramWorldGenDecoratorContext, Random paramRandom, DC paramDC, BlockPosition paramBlockPosition);
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenDecorator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */