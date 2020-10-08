/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import com.mojang.datafixers.kinds.App;
/*     */ import com.mojang.datafixers.kinds.Applicative;
/*     */ import com.mojang.datafixers.util.Function11;
/*     */ import com.mojang.serialization.Codec;
/*     */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import java.util.stream.Collectors;
/*     */ 
/*     */ public class WorldGenFeatureRandomPatchConfiguration
/*     */   implements WorldGenFeatureConfiguration {
/*     */   static {
/*  17 */     a = RecordCodecBuilder.create(var0 -> var0.group((App)WorldGenFeatureStateProvider.a.fieldOf("state_provider").forGetter(()), (App)WorldGenBlockPlacer.a.fieldOf("block_placer").forGetter(()), (App)IBlockData.b.listOf().fieldOf("whitelist").forGetter(()), (App)IBlockData.b.listOf().fieldOf("blacklist").forGetter(()), (App)Codec.INT.fieldOf("tries").orElse(Integer.valueOf(128)).forGetter(()), (App)Codec.INT.fieldOf("xspread").orElse(Integer.valueOf(7)).forGetter(()), (App)Codec.INT.fieldOf("yspread").orElse(Integer.valueOf(3)).forGetter(()), (App)Codec.INT.fieldOf("zspread").orElse(Integer.valueOf(7)).forGetter(()), (App)Codec.BOOL.fieldOf("can_replace").orElse(Boolean.valueOf(false)).forGetter(()), (App)Codec.BOOL.fieldOf("project").orElse(Boolean.valueOf(true)).forGetter(()), (App)Codec.BOOL.fieldOf("need_water").orElse(Boolean.valueOf(false)).forGetter(())).apply((Applicative)var0, WorldGenFeatureRandomPatchConfiguration::new));
/*     */   }
/*     */ 
/*     */   
/*     */   public static final Codec<WorldGenFeatureRandomPatchConfiguration> a;
/*     */   
/*     */   public final WorldGenFeatureStateProvider b;
/*     */   
/*     */   public final WorldGenBlockPlacer c;
/*     */   
/*     */   public final Set<Block> d;
/*     */   
/*     */   public final Set<IBlockData> e;
/*     */   
/*     */   public final int f;
/*     */   
/*     */   public final int g;
/*     */   
/*     */   public final int h;
/*     */   
/*     */   public final int i;
/*     */   
/*     */   public final boolean j;
/*     */   public final boolean l;
/*     */   public final boolean m;
/*     */   
/*     */   private WorldGenFeatureRandomPatchConfiguration(WorldGenFeatureStateProvider var0, WorldGenBlockPlacer var1, List<IBlockData> var2, List<IBlockData> var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, boolean var10) {
/*  44 */     this(var0, var1, (Set<Block>)var2
/*  45 */         .stream().map(BlockBase.BlockData::getBlock).collect(Collectors.toSet()), 
/*  46 */         (Set<IBlockData>)ImmutableSet.copyOf(var3), var4, var5, var6, var7, var8, var9, var10);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private WorldGenFeatureRandomPatchConfiguration(WorldGenFeatureStateProvider var0, WorldGenBlockPlacer var1, Set<Block> var2, Set<IBlockData> var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, boolean var10) {
/*  52 */     this.b = var0;
/*  53 */     this.c = var1;
/*  54 */     this.d = var2;
/*  55 */     this.e = var3;
/*  56 */     this.f = var4;
/*  57 */     this.g = var5;
/*  58 */     this.h = var6;
/*  59 */     this.i = var7;
/*  60 */     this.j = var8;
/*  61 */     this.l = var9;
/*  62 */     this.m = var10;
/*     */   }
/*     */   
/*     */   public static class a {
/*     */     private final WorldGenFeatureStateProvider a;
/*     */     private final WorldGenBlockPlacer b;
/*  68 */     private Set<Block> c = (Set<Block>)ImmutableSet.of();
/*  69 */     private Set<IBlockData> d = (Set<IBlockData>)ImmutableSet.of();
/*  70 */     private int e = 64;
/*  71 */     private int f = 7;
/*  72 */     private int g = 3;
/*  73 */     private int h = 7;
/*     */     private boolean i;
/*     */     private boolean j = true;
/*     */     private boolean k = false;
/*     */     
/*     */     public a(WorldGenFeatureStateProvider var0, WorldGenBlockPlacer var1) {
/*  79 */       this.a = var0;
/*  80 */       this.b = var1;
/*     */     }
/*     */     
/*     */     public a a(Set<Block> var0) {
/*  84 */       this.c = var0;
/*  85 */       return this;
/*     */     }
/*     */     
/*     */     public a b(Set<IBlockData> var0) {
/*  89 */       this.d = var0;
/*  90 */       return this;
/*     */     }
/*     */     
/*     */     public a a(int var0) {
/*  94 */       this.e = var0;
/*  95 */       return this;
/*     */     }
/*     */     
/*     */     public a b(int var0) {
/*  99 */       this.f = var0;
/* 100 */       return this;
/*     */     }
/*     */     
/*     */     public a c(int var0) {
/* 104 */       this.g = var0;
/* 105 */       return this;
/*     */     }
/*     */     
/*     */     public a d(int var0) {
/* 109 */       this.h = var0;
/* 110 */       return this;
/*     */     }
/*     */     
/*     */     public a a() {
/* 114 */       this.i = true;
/* 115 */       return this;
/*     */     }
/*     */     
/*     */     public a b() {
/* 119 */       this.j = false;
/* 120 */       return this;
/*     */     }
/*     */     
/*     */     public a c() {
/* 124 */       this.k = true;
/* 125 */       return this;
/*     */     }
/*     */     
/*     */     public WorldGenFeatureRandomPatchConfiguration d() {
/* 129 */       return new WorldGenFeatureRandomPatchConfiguration(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureRandomPatchConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */