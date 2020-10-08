/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class WorldGenSurface<C extends WorldGenSurfaceConfiguration>
/*    */ {
/* 13 */   private static final IBlockData a = Blocks.DIRT.getBlockData();
/* 14 */   private static final IBlockData b = Blocks.GRASS_BLOCK.getBlockData();
/* 15 */   private static final IBlockData c = Blocks.PODZOL.getBlockData();
/* 16 */   private static final IBlockData d = Blocks.GRAVEL.getBlockData();
/* 17 */   private static final IBlockData e = Blocks.STONE.getBlockData();
/* 18 */   private static final IBlockData K = Blocks.COARSE_DIRT.getBlockData();
/* 19 */   private static final IBlockData L = Blocks.SAND.getBlockData();
/* 20 */   private static final IBlockData M = Blocks.RED_SAND.getBlockData();
/* 21 */   private static final IBlockData N = Blocks.WHITE_TERRACOTTA.getBlockData();
/* 22 */   private static final IBlockData O = Blocks.MYCELIUM.getBlockData();
/* 23 */   private static final IBlockData P = Blocks.SOUL_SAND.getBlockData();
/* 24 */   private static final IBlockData Q = Blocks.NETHERRACK.getBlockData();
/* 25 */   private static final IBlockData R = Blocks.END_STONE.getBlockData();
/* 26 */   private static final IBlockData S = Blocks.CRIMSON_NYLIUM.getBlockData();
/* 27 */   private static final IBlockData T = Blocks.WARPED_NYLIUM.getBlockData();
/* 28 */   private static final IBlockData U = Blocks.NETHER_WART_BLOCK.getBlockData();
/* 29 */   private static final IBlockData V = Blocks.WARPED_WART_BLOCK.getBlockData();
/* 30 */   private static final IBlockData W = Blocks.BLACKSTONE.getBlockData();
/* 31 */   private static final IBlockData X = Blocks.BASALT.getBlockData();
/* 32 */   private static final IBlockData Y = Blocks.MAGMA_BLOCK.getBlockData();
/*    */   
/* 34 */   public static final WorldGenSurfaceConfigurationBase f = new WorldGenSurfaceConfigurationBase(c, a, d);
/* 35 */   public static final WorldGenSurfaceConfigurationBase g = new WorldGenSurfaceConfigurationBase(d, d, d);
/* 36 */   public static final WorldGenSurfaceConfigurationBase h = new WorldGenSurfaceConfigurationBase(b, a, d);
/* 37 */   public static final WorldGenSurfaceConfigurationBase i = new WorldGenSurfaceConfigurationBase(e, e, d);
/* 38 */   public static final WorldGenSurfaceConfigurationBase j = new WorldGenSurfaceConfigurationBase(K, a, d);
/* 39 */   public static final WorldGenSurfaceConfigurationBase k = new WorldGenSurfaceConfigurationBase(L, L, d);
/* 40 */   public static final WorldGenSurfaceConfigurationBase l = new WorldGenSurfaceConfigurationBase(b, a, L);
/* 41 */   public static final WorldGenSurfaceConfigurationBase m = new WorldGenSurfaceConfigurationBase(L, L, L);
/* 42 */   public static final WorldGenSurfaceConfigurationBase n = new WorldGenSurfaceConfigurationBase(M, N, d);
/* 43 */   public static final WorldGenSurfaceConfigurationBase o = new WorldGenSurfaceConfigurationBase(O, a, d);
/* 44 */   public static final WorldGenSurfaceConfigurationBase p = new WorldGenSurfaceConfigurationBase(Q, Q, Q);
/* 45 */   public static final WorldGenSurfaceConfigurationBase q = new WorldGenSurfaceConfigurationBase(P, P, P);
/* 46 */   public static final WorldGenSurfaceConfigurationBase r = new WorldGenSurfaceConfigurationBase(R, R, R);
/* 47 */   public static final WorldGenSurfaceConfigurationBase s = new WorldGenSurfaceConfigurationBase(S, Q, U);
/* 48 */   public static final WorldGenSurfaceConfigurationBase t = new WorldGenSurfaceConfigurationBase(T, Q, V);
/* 49 */   public static final WorldGenSurfaceConfigurationBase u = new WorldGenSurfaceConfigurationBase(W, X, Y);
/*    */   
/* 51 */   public static final WorldGenSurface<WorldGenSurfaceConfigurationBase> v = a("default", new WorldGenSurfaceDefaultBlock(WorldGenSurfaceConfigurationBase.a));
/* 52 */   public static final WorldGenSurface<WorldGenSurfaceConfigurationBase> w = a("mountain", new WorldGenSurfaceExtremeHills(WorldGenSurfaceConfigurationBase.a));
/* 53 */   public static final WorldGenSurface<WorldGenSurfaceConfigurationBase> x = a("shattered_savanna", new WorldGenSurfaceSavannaMutated(WorldGenSurfaceConfigurationBase.a));
/* 54 */   public static final WorldGenSurface<WorldGenSurfaceConfigurationBase> y = a("gravelly_mountain", new WorldGenSurfaceExtremeHillMutated(WorldGenSurfaceConfigurationBase.a));
/* 55 */   public static final WorldGenSurface<WorldGenSurfaceConfigurationBase> z = a("giant_tree_taiga", new WorldGenSurfaceTaigaMega(WorldGenSurfaceConfigurationBase.a));
/* 56 */   public static final WorldGenSurface<WorldGenSurfaceConfigurationBase> A = a("swamp", new WorldGenSurfaceSwamp(WorldGenSurfaceConfigurationBase.a));
/* 57 */   public static final WorldGenSurface<WorldGenSurfaceConfigurationBase> B = a("badlands", new WorldGenSurfaceMesa(WorldGenSurfaceConfigurationBase.a));
/* 58 */   public static final WorldGenSurface<WorldGenSurfaceConfigurationBase> C = a("wooded_badlands", new WorldGenSurfaceMesaForest(WorldGenSurfaceConfigurationBase.a));
/* 59 */   public static final WorldGenSurface<WorldGenSurfaceConfigurationBase> D = a("eroded_badlands", new WorldGenSurfaceMesaBryce(WorldGenSurfaceConfigurationBase.a));
/* 60 */   public static final WorldGenSurface<WorldGenSurfaceConfigurationBase> E = a("frozen_ocean", new WorldGenSurfaceFrozenOcean(WorldGenSurfaceConfigurationBase.a));
/* 61 */   public static final WorldGenSurface<WorldGenSurfaceConfigurationBase> F = a("nether", new WorldGenSurfaceNether(WorldGenSurfaceConfigurationBase.a));
/* 62 */   public static final WorldGenSurface<WorldGenSurfaceConfigurationBase> G = a("nether_forest", new WorldGenSurfaceNetherForest(WorldGenSurfaceConfigurationBase.a));
/* 63 */   public static final WorldGenSurface<WorldGenSurfaceConfigurationBase> H = a("soul_sand_valley", new WorldGenSurfaceSoulSandValley(WorldGenSurfaceConfigurationBase.a));
/* 64 */   public static final WorldGenSurface<WorldGenSurfaceConfigurationBase> I = a("basalt_deltas", new WorldGenSurfaceBasaltDeltas(WorldGenSurfaceConfigurationBase.a));
/* 65 */   public static final WorldGenSurface<WorldGenSurfaceConfigurationBase> J = a("nope", new WorldGenSurfaceEmpty(WorldGenSurfaceConfigurationBase.a));
/*    */   
/*    */   private static <C extends WorldGenSurfaceConfiguration, F extends WorldGenSurface<C>> F a(String var0, F var1) {
/* 68 */     return (F)IRegistry.<WorldGenSurface>a((IRegistry)IRegistry.SURFACE_BUILDER, var0, (WorldGenSurface)var1);
/*    */   }
/*    */   
/*    */   private final Codec<WorldGenSurfaceComposite<C>> Z;
/*    */   
/*    */   public WorldGenSurface(Codec<C> var0) {
/* 74 */     this.Z = var0.fieldOf("config").xmap(this::a, WorldGenSurfaceComposite::a).codec();
/*    */   }
/*    */   
/*    */   public Codec<WorldGenSurfaceComposite<C>> d() {
/* 78 */     return this.Z;
/*    */   }
/*    */   
/*    */   public WorldGenSurfaceComposite<C> a(C var0) {
/* 82 */     return new WorldGenSurfaceComposite<>(this, var0);
/*    */   }
/*    */   
/*    */   public abstract void a(Random paramRandom, IChunkAccess paramIChunkAccess, BiomeBase paramBiomeBase, int paramInt1, int paramInt2, int paramInt3, double paramDouble, IBlockData paramIBlockData1, IBlockData paramIBlockData2, int paramInt4, long paramLong, C paramC);
/*    */   
/*    */   public void a(long var0) {}
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenSurface.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */