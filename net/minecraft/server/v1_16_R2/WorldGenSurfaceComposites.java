/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenSurfaceComposites
/*    */ {
/* 11 */   public static final WorldGenSurfaceComposite<WorldGenSurfaceConfigurationBase> a = a("badlands", WorldGenSurface.B.a(WorldGenSurface.n));
/* 12 */   public static final WorldGenSurfaceComposite<WorldGenSurfaceConfigurationBase> b = a("basalt_deltas", WorldGenSurface.I.a(WorldGenSurface.u));
/* 13 */   public static final WorldGenSurfaceComposite<WorldGenSurfaceConfigurationBase> c = a("crimson_forest", WorldGenSurface.G.a(WorldGenSurface.s));
/* 14 */   public static final WorldGenSurfaceComposite<WorldGenSurfaceConfigurationBase> d = a("desert", WorldGenSurface.v.a(WorldGenSurface.k));
/* 15 */   public static final WorldGenSurfaceComposite<WorldGenSurfaceConfigurationBase> e = a("end", WorldGenSurface.v.a(WorldGenSurface.r));
/* 16 */   public static final WorldGenSurfaceComposite<WorldGenSurfaceConfigurationBase> f = a("eroded_badlands", WorldGenSurface.D.a(WorldGenSurface.n));
/* 17 */   public static final WorldGenSurfaceComposite<WorldGenSurfaceConfigurationBase> g = a("frozen_ocean", WorldGenSurface.E.a(WorldGenSurface.h));
/* 18 */   public static final WorldGenSurfaceComposite<WorldGenSurfaceConfigurationBase> h = a("full_sand", WorldGenSurface.v.a(WorldGenSurface.m));
/* 19 */   public static final WorldGenSurfaceComposite<WorldGenSurfaceConfigurationBase> i = a("giant_tree_taiga", WorldGenSurface.z.a(WorldGenSurface.h));
/* 20 */   public static final WorldGenSurfaceComposite<WorldGenSurfaceConfigurationBase> j = a("grass", WorldGenSurface.v.a(WorldGenSurface.h));
/* 21 */   public static final WorldGenSurfaceComposite<WorldGenSurfaceConfigurationBase> k = a("gravelly_mountain", WorldGenSurface.y.a(WorldGenSurface.h));
/* 22 */   public static final WorldGenSurfaceComposite<WorldGenSurfaceConfigurationBase> l = a("ice_spikes", WorldGenSurface.v.a(new WorldGenSurfaceConfigurationBase(Blocks.SNOW_BLOCK.getBlockData(), Blocks.DIRT.getBlockData(), Blocks.GRAVEL.getBlockData())));
/* 23 */   public static final WorldGenSurfaceComposite<WorldGenSurfaceConfigurationBase> m = a("mountain", WorldGenSurface.w.a(WorldGenSurface.h));
/* 24 */   public static final WorldGenSurfaceComposite<WorldGenSurfaceConfigurationBase> n = a("mycelium", WorldGenSurface.v.a(WorldGenSurface.o));
/* 25 */   public static final WorldGenSurfaceComposite<WorldGenSurfaceConfigurationBase> o = a("nether", WorldGenSurface.F.a(WorldGenSurface.p));
/* 26 */   public static final WorldGenSurfaceComposite<WorldGenSurfaceConfigurationBase> p = a("nope", WorldGenSurface.J.a(WorldGenSurface.i));
/* 27 */   public static final WorldGenSurfaceComposite<WorldGenSurfaceConfigurationBase> q = a("ocean_sand", WorldGenSurface.v.a(WorldGenSurface.l));
/* 28 */   public static final WorldGenSurfaceComposite<WorldGenSurfaceConfigurationBase> r = a("shattered_savanna", WorldGenSurface.x.a(WorldGenSurface.h));
/* 29 */   public static final WorldGenSurfaceComposite<WorldGenSurfaceConfigurationBase> s = a("soul_sand_valley", WorldGenSurface.H.a(WorldGenSurface.q));
/* 30 */   public static final WorldGenSurfaceComposite<WorldGenSurfaceConfigurationBase> t = a("stone", WorldGenSurface.v.a(WorldGenSurface.i));
/* 31 */   public static final WorldGenSurfaceComposite<WorldGenSurfaceConfigurationBase> u = a("swamp", WorldGenSurface.A.a(WorldGenSurface.h));
/* 32 */   public static final WorldGenSurfaceComposite<WorldGenSurfaceConfigurationBase> v = a("warped_forest", WorldGenSurface.G.a(WorldGenSurface.t));
/* 33 */   public static final WorldGenSurfaceComposite<WorldGenSurfaceConfigurationBase> w = a("wooded_badlands", WorldGenSurface.C.a(WorldGenSurface.n));
/*    */   
/*    */   private static <SC extends WorldGenSurfaceConfiguration> WorldGenSurfaceComposite<SC> a(String var0, WorldGenSurfaceComposite<SC> var1) {
/* 36 */     return (WorldGenSurfaceComposite<SC>)RegistryGeneration.<WorldGenSurfaceComposite<?>>a(RegistryGeneration.c, var0, var1);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenSurfaceComposites.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */