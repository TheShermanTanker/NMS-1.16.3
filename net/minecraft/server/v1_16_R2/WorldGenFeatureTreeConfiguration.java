/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.mojang.datafixers.kinds.App;
/*     */ import com.mojang.datafixers.kinds.Applicative;
/*     */ import com.mojang.datafixers.util.Function9;
/*     */ import com.mojang.serialization.Codec;
/*     */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*     */ import java.util.List;
/*     */ 
/*     */ public class WorldGenFeatureTreeConfiguration implements WorldGenFeatureConfiguration {
/*     */   public static final Codec<WorldGenFeatureTreeConfiguration> a;
/*     */   public final WorldGenFeatureStateProvider b;
/*     */   
/*     */   static {
/*  16 */     a = RecordCodecBuilder.create(var0 -> var0.group((App)WorldGenFeatureStateProvider.a.fieldOf("trunk_provider").forGetter(()), (App)WorldGenFeatureStateProvider.a.fieldOf("leaves_provider").forGetter(()), (App)WorldGenFoilagePlacer.d.fieldOf("foliage_placer").forGetter(()), (App)TrunkPlacer.c.fieldOf("trunk_placer").forGetter(()), (App)FeatureSize.a.fieldOf("minimum_size").forGetter(()), (App)WorldGenFeatureTree.c.listOf().fieldOf("decorators").forGetter(()), (App)Codec.INT.fieldOf("max_water_depth").orElse(Integer.valueOf(0)).forGetter(()), (App)Codec.BOOL.fieldOf("ignore_vines").orElse(Boolean.valueOf(false)).forGetter(()), (App)HeightMap.Type.g.fieldOf("heightmap").forGetter(())).apply((Applicative)var0, WorldGenFeatureTreeConfiguration::new));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final WorldGenFeatureStateProvider c;
/*     */ 
/*     */   
/*     */   public final List<WorldGenFeatureTree> d;
/*     */ 
/*     */   
/*     */   public transient boolean e;
/*     */ 
/*     */   
/*     */   public final WorldGenFoilagePlacer f;
/*     */ 
/*     */   
/*     */   public final TrunkPlacer g;
/*     */ 
/*     */   
/*     */   public final FeatureSize h;
/*     */ 
/*     */   
/*     */   public final int i;
/*     */ 
/*     */   
/*     */   public final boolean j;
/*     */ 
/*     */   
/*     */   public final HeightMap.Type l;
/*     */ 
/*     */   
/*     */   protected WorldGenFeatureTreeConfiguration(WorldGenFeatureStateProvider var0, WorldGenFeatureStateProvider var1, WorldGenFoilagePlacer var2, TrunkPlacer var3, FeatureSize var4, List<WorldGenFeatureTree> var5, int var6, boolean var7, HeightMap.Type var8) {
/*  49 */     this.b = var0;
/*  50 */     this.c = var1;
/*  51 */     this.d = var5;
/*  52 */     this.f = var2;
/*  53 */     this.h = var4;
/*  54 */     this.g = var3;
/*  55 */     this.i = var6;
/*  56 */     this.j = var7;
/*  57 */     this.l = var8;
/*     */   }
/*     */   
/*     */   public void b() {
/*  61 */     this.e = true;
/*     */   }
/*     */   
/*     */   public WorldGenFeatureTreeConfiguration a(List<WorldGenFeatureTree> var0) {
/*  65 */     return new WorldGenFeatureTreeConfiguration(this.b, this.c, this.f, this.g, this.h, var0, this.i, this.j, this.l);
/*     */   }
/*     */   
/*     */   public static class a {
/*     */     public final WorldGenFeatureStateProvider a;
/*     */     public final WorldGenFeatureStateProvider b;
/*     */     private final WorldGenFoilagePlacer c;
/*     */     private final TrunkPlacer d;
/*     */     private final FeatureSize e;
/*  74 */     private List<WorldGenFeatureTree> f = (List<WorldGenFeatureTree>)ImmutableList.of();
/*     */     private int g;
/*     */     private boolean h;
/*  77 */     private HeightMap.Type i = HeightMap.Type.OCEAN_FLOOR;
/*     */     
/*     */     public a(WorldGenFeatureStateProvider var0, WorldGenFeatureStateProvider var1, WorldGenFoilagePlacer var2, TrunkPlacer var3, FeatureSize var4) {
/*  80 */       this.a = var0;
/*  81 */       this.b = var1;
/*  82 */       this.c = var2;
/*  83 */       this.d = var3;
/*  84 */       this.e = var4;
/*     */     }
/*     */     
/*     */     public a a(List<WorldGenFeatureTree> var0) {
/*  88 */       this.f = var0;
/*  89 */       return this;
/*     */     }
/*     */     
/*     */     public a a(int var0) {
/*  93 */       this.g = var0;
/*  94 */       return this;
/*     */     }
/*     */     
/*     */     public a a() {
/*  98 */       this.h = true;
/*  99 */       return this;
/*     */     }
/*     */     
/*     */     public a a(HeightMap.Type var0) {
/* 103 */       this.i = var0;
/* 104 */       return this;
/*     */     }
/*     */     
/*     */     public WorldGenFeatureTreeConfiguration b() {
/* 108 */       return new WorldGenFeatureTreeConfiguration(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureTreeConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */